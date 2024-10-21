/**
 * JReleaser configuration file.
 */

@file:Suppress(
    "MISSING_KDOC_TOP_LEVEL",
    "MISSING_KDOC_ON_FUNCTION",
    "GENERIC_VARIABLE_WRONG_DECLARATION",
    "FILE_NAME_INCORRECT",
)

package io.github.sanyavertolet.jswrappers.buildutils

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jreleaser.gradle.plugin.JReleaserExtension
import org.jreleaser.model.Active

@Suppress("FUNCTION_NAME_INCORRECT_CASE")
fun Project.configureJReleaser() {
    configure<JReleaserExtension> {
        gitRootSearch.set(true)
        project {
            description.set("Kotlin wrappers and utils for js")
            authors.set(listOf("sanyavertolet"))
            license.set("MIT")
            links {
                homepage.set("https://github.com/sanyavertolet/kotlin-js-wrappers")
            }
            inceptionYear.set("2024")
            snapshot {
                fullChangelog.set(true)
            }
        }

        release {
            github {
                overwrite.set(true)
                tagName.set("{{projectVersion}}")
                changelog {
                    formatted.set(Active.ALWAYS)
                    preset.set("conventional-commits")
                    format.set("- {{commitShortHash}} {{commitTitle}}")
                    contributors {
                        enabled.set(true)
                    }
                }
            }
        }

        signing {
            active.set(Active.ALWAYS)
            armored.set(true)
        }

        deploy {
            active.set(Active.ALWAYS)
            maven {
                mavenCentral {
                    create("sonatype") {
                        active.set(Active.ALWAYS)
                        url.set("https://central.sonatype.com/api/v1/publisher")
                        snapshotSupported.set(true)
                        verifyPom.set(false)
                        stagingRepository("build/staging-deploy")
                    }
                }
            }
        }
    }
}
