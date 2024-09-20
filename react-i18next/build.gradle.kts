plugins {
    alias(libs.plugins.kotlin.multiplatform)

    id("io.github.sanyavertolet.jswrappers.buildutils.code-quality-convention")
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
            }
        }
    }
}
