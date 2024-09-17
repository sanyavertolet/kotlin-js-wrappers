plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
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

                implementation(project.dependencies.enforcedPlatform(libs.kotlin.wrappers.bom))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions")

                implementation(libs.kotlinx.serialization.json)
            }
        }
    }
}
