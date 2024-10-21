plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    id("io.github.sanyavertolet.jswrappers.buildutils.code-quality-convention")
    id("io.github.sanyavertolet.jswrappers.buildutils.versioning-configuration")
    id("io.github.sanyavertolet.jswrappers.buildutils.publishing-configuration")
}

repositories {
    mavenCentral()
}
