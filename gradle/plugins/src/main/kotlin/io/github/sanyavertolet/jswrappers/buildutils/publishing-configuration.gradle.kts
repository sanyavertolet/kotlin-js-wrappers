package io.github.sanyavertolet.jswrappers.buildutils

plugins {
    `maven-publish`
    signing
    // id("org.jreleaser")
    id("com.vanniktech.maven.publish")
}

run {
    tasks.assemble {
        doFirst {
            mkdir("${layout.buildDirectory.get()}/jreleaser")
        }
    }
    configureGitHubPublishing()
    configurePublications()
    configureSigning()

    // configureJReleaser()

    configureMavenPublishPlugin()
}
