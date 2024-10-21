/**
 * Maven Publish Plugin configuration file.
 */

@file:Suppress(
    "MISSING_KDOC_TOP_LEVEL",
    "MISSING_KDOC_ON_FUNCTION",
    "GENERIC_VARIABLE_WRONG_DECLARATION",
)

package io.github.sanyavertolet.jswrappers.buildutils

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.configureMavenPublishPlugin() {
    configure<MavenPublishBaseExtension> {
        coordinates(project.group.toString(), project.name, project.version.toString())

        pom {
            name.set("${project.name}-js")
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

        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

        signAllPublications()
    }
}
