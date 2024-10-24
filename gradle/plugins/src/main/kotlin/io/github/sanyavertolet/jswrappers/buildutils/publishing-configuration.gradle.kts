package io.github.sanyavertolet.jswrappers.buildutils

plugins {
    `maven-publish`
    signing
    id("com.vanniktech.maven.publish")
}

run {
    configureMavenPublishPlugin()
}
