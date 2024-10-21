plugins {
    alias(libs.plugins.kotlin.multiplatform)

    id("io.github.sanyavertolet.jswrappers.buildutils.code-quality-convention")
    id("io.github.sanyavertolet.jswrappers.buildutils.publishing-configuration")
}

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser()
    }

    sourceSets {
        jsMain {
            dependencies {
                implementation(projects.i18next)

                implementation(libs.kotlin.wrappers.kotlin.js)
                implementation(libs.kotlin.wrappers.kotlin.extensions)
            }
        }
    }
}
