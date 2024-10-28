plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.dokka)

    id("io.github.sanyavertolet.jswrappers.buildutils.code-quality-convention")
    id("io.github.sanyavertolet.jswrappers.buildutils.publishing-configuration")
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
                implementation(libs.kotlin.wrappers.kotlin.js)
                implementation(libs.kotlin.wrappers.kotlin.extensions)
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
