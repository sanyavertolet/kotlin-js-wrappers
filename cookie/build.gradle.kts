plugins {
    alias(libs.plugins.kotlin.multiplatform)

    id("io.github.sanyavertolet.jswrappers.buildutils.code-quality-convention")
}

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(project.dependencies.enforcedPlatform(libs.kotlin.wrappers.bom))

                implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions")
            }
        }

        jsTest {
            dependencies {
                implementation(npm("js-cookie", "^3.0.5"))
                implementation(kotlin("test"))
            }
        }
    }
}
