/**
 * Publishing configuration file.
 */

@file:Suppress(
    "MISSING_KDOC_TOP_LEVEL",
    "MISSING_KDOC_ON_FUNCTION",
    "GENERIC_VARIABLE_WRONG_DECLARATION",
)

package io.github.sanyavertolet.jswrappers.buildutils

import org.gradle.api.Named
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.internal.logging.text.StyledTextOutput
import org.gradle.internal.logging.text.StyledTextOutput.Style.Failure
import org.gradle.internal.logging.text.StyledTextOutput.Style.Success
import org.gradle.internal.logging.text.StyledTextOutputFactory
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.support.serviceOf
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.SigningExtension

/**
 * Enables signing of the artifacts if the `signingKey` project property is set.
 *
 * Should be explicitly called after each custom `publishing {}` section.
 */
@Suppress("unused")
fun Project.configureSigning() {
    System.getenv("GPG_SECRET_KEY")?.let { extra.set("signingKey", it) }
    System.getenv("GPG_PASSPHRASE")?.let { extra.set("signingPassword", it) }
    if (hasProperty("signingKey")) {
        /*
         * GitHub Actions.
         */
        configureSigningCommon {
            useInMemoryPgpKeys(property("signingKey") as String?, findProperty("signingPassword") as String?)
        }
    } else if (
        hasProperties(
            "signing.keyId",
            "signing.password",
            "signing.secretKeyRingFile",
        )
    ) {
        /*-
         * Pure-Java signing mechanism via `org.bouncycastle.bcpg`.
         *
         * Requires an 8-digit (short form) PGP key id and a present `~/.gnupg/secring.gpg`
         * (for gpg 2.1, run
         * `gpg --keyring secring.gpg --export-secret-keys >~/.gnupg/secring.gpg`
         * to generate one).
         */
        configureSigningCommon()
    } else if (hasProperty("signing.gnupg.keyName")) {
        /*-
         * Use an external `gpg` executable.
         *
         * On Windows, you may need to additionally specify the path to `gpg` via
         * `signing.gnupg.executable`.
         */
        configureSigningCommon {
            useGpgCmd()
        }
    }
}

/**
 * @param useKeys the block which configures the PGP keys. Use either
 *   [SigningExtension.useInMemoryPgpKeys], [SigningExtension.useGpgCmd], or an
 *   empty lambda.
 * @see SigningExtension.useInMemoryPgpKeys
 * @see SigningExtension.useGpgCmd
 */
@Suppress(
    "MaxLineLength",
    "SpreadOperator",
)
fun Project.configureSigningCommon(useKeys: SigningExtension.() -> Unit = {}) {
    configure<SigningExtension> {
        useKeys()
        val publications = extensions.getByType<PublishingExtension>().publications
        val publicationCount = publications.size
        val message = "The following $publicationCount publication(s) are getting signed: ${publications.map(Named::getName)}"
        val style = when (publicationCount) {
            0 -> Failure
            else -> Success
        }
        styledOut(logCategory = "signing").style(style).println(message)
        sign(*publications.toTypedArray())
    }
}

fun Project.styledOut(logCategory: String): StyledTextOutput = serviceOf<StyledTextOutputFactory>().create(logCategory)

/**
 * Determines if this project has all the given properties.
 *
 * @param propertyNames the names of the properties to locate.
 * @return `true` if this project has all the given properties, `false` otherwise.
 * @see Project.hasProperty
 */
fun Project.hasProperties(vararg propertyNames: String): Boolean = propertyNames.asSequence().all(this::hasProperty)

fun Project.configurePublications() {
    val dokkaJar = tasks.create<Jar>("dokkaJar") {
        group = "documentation"
        archiveClassifier.set("javadoc")
        from(tasks.findByName("dokkaHtml"))
    }
    configure<PublishingExtension> {
        publications {
            withType<MavenPublication>().configureEach {
                groupId = rootProject.group.toString()
                version = project.version.toString()
                artifactId = "${project.name}-js"

                artifact(dokkaJar)

                pom {
                    logger.lifecycle("Configuring ${project.name} publication")
                    name.set(project.name)
                    description.set(project.description ?: project.name)
                    url.set("https://sanyavertolet.github.io/kotlin-js-wrappers")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/license/MIT")
                            distribution.set("repo")
                        }
                    }
                    developers {
                        developer {
                            id.set("sanyavertolet")
                            name.set("Alexander Frolov")
                            email.set("lxnfrolov@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/sanyavertolet/kotlin-js-wrappers.git")
                        developerConnection.set("scm:git:ssh://github.com/sanyavertolet/kotlin-js-wrappers.git")
                        url.set("https://github.com/sanyavertolet/kotlin-js-wrappers")
                    }
                }
            }
        }
    }

    afterEvaluate {
        configure<PublishingExtension> {
            publications.all {
                val mavenPublication = this as? MavenPublication
                mavenPublication?.artifactId =
                        "${project.name}${"-$name".takeUnless { "kotlinMultiplatform" in name }.orEmpty()}"
            }
        }
    }
}
