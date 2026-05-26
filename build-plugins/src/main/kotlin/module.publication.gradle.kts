// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import java.io.FileInputStream
import java.util.Properties

plugins {
    `maven-publish`
    signing
}

val versionSuffix = when {
    project.hasProperty("release") -> ""
    project.hasProperty("alpha") -> {
        val v = project.property("alpha")?.toString()
        if (v.isNullOrBlank()) "-alpha" else "-alpha$v"
    }

    project.hasProperty("beta") -> {
        val v = project.property("beta")?.toString()
        if (v.isNullOrBlank()) "-beta" else "-beta$v"
    }

    project.hasProperty("rc") -> {
        val v = project.property("rc")?.toString()
        if (v.isNullOrBlank()) "-rc" else "-rc$v"
    }

    else -> "-${getGitHashShort()}-SNAPSHOT"
}

group = BuildConfig.LIBRARY_ID
version = "${BuildConfig.LIBRARY_VERSION}$versionSuffix"

val publicationExt = extensions.create<MiuixPublicationExtension>("miuixPublication")

val javadocJar by tasks.registering(Jar::class) {
    description = "javadocJar"
    archiveClassifier.set("javadoc")
}

val projectUrl = "https://github.com/compose-miuix-ui/miuix"
val githubPackagesUrl = "https://maven.pkg.github.com/compose-miuix-ui/miuix"
val sonatypePackageUrl = layout.buildDirectory.dir("publishing/mavenCentral")
val localPackageUrl = layout.buildDirectory.dir("repository/local")

val localPropertiesFile: File = project.rootProject.file("local.properties")
val localProperties = Properties()
if (localPropertiesFile.exists()) {
    FileInputStream(localPropertiesFile).use { localProperties.load(it) }
}

publishing {
    repositories {
        maven {
            name = "local"
            url = uri(localPackageUrl)
        }
        maven {
            name = "sonatype"
            url = uri(sonatypePackageUrl)
        }
        maven {
            name = "github"
            url = uri(githubPackagesUrl)
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: localProperties.getProperty("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN") ?: localProperties.getProperty("GITHUB_TOKEN")
            }
        }
    }
    publications.withType<MavenPublication> {
        artifact(javadocJar.get())
        pom {
            name.set(project.name)
            description.set(publicationExt.description)
            url.set(projectUrl)
            licenses {
                license {
                    name.set("The Apache Software License, Version 2.0")
                    url.set("$projectUrl/blob/main/LICENSE")
                }
            }
            issueManagement {
                system.set("Github")
                url.set("$projectUrl/issues")
            }
            scm {
                connection.set("$projectUrl.git")
                url.set(projectUrl)
            }
            developers {
                developer {
                    id.set("compose-miuix-ui")
                    name.set("compose-miuix-ui")
                    url.set("https://github.com/compose-miuix-ui")
                }
            }
        }
    }
}

tasks.register<CentralPublisherUploadTask>("publishToMavenCentralUsingCentralApi") {
    description = "Upload the staged Maven Central bundle via the Central Publisher API"
    dependsOn("publishAllPublicationsToSonatypeRepository")
    sonatypeRepoDir.set(sonatypePackageUrl)
    bundleFileName.set(provider { "${project.name}-${project.version}.zip" })
    bundleFile.set(
        layout.buildDirectory.file(
            provider { "publishing/${project.name}-${project.version}.zip" },
        ),
    )
    centralTokenId.set(
        provider {
            System.getenv("CENTRAL_TOKEN_ID") ?: localProperties.getProperty("CENTRAL_TOKEN_ID")
        },
    )
    centralTokenSecret.set(
        provider {
            System.getenv("CENTRAL_TOKEN_SECRET") ?: localProperties.getProperty("CENTRAL_TOKEN_SECRET")
        },
    )
}

val signingKey = System.getenv("GPG_SIGNING_KEY") ?: localProperties.getProperty("GPG_SIGNING_KEY")
val signingPassword = System.getenv("GPG_PASSPHRASE") ?: localProperties.getProperty("GPG_PASSPHRASE")
if (!signingKey.isNullOrBlank()) {
    signing {
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications)
    }
    tasks.withType<PublishToMavenRepository>().configureEach {
        dependsOn(tasks.withType<Sign>())
    }
}
