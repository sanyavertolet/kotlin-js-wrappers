plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
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

                implementation(libs.kotlinx.serialization.json)

                api(npm("i18next", "^23.12.2"))
                api(npm("react-i18next", "^15.0.0"))
                api(npm("i18next-http-backend", "^2.5.2"))
            }
        }

        jsTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

