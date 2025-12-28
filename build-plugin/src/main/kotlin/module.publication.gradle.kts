// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import java.io.FileInputStream
import java.util.Properties

plugins {
    `maven-publish`
    signing
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

val githubUrl = "https://github.com"
val githubPkgUrl = "https://maven.pkg.github.com"
val owner = "compose-miuix-ui"
val repository = "miuix"
val projectUrl = "$githubUrl/$owner/$repository"
val projectPackagesUrl = "$githubPkgUrl/$owner/$repository"

val localPropertiesFile = project.rootProject.file("local.properties")
val localProperties = Properties()
if (localPropertiesFile.exists()) {
    FileInputStream(localPropertiesFile).use { localProperties.load(it) }
}

publishing {
    // Configure the publication repository
    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("repo"))
        }
        maven {
            name = "github"
            url = uri(projectPackagesUrl)
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: localProperties.getProperty("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN") ?: localProperties.getProperty("GITHUB_TOKEN")
            }
        }
    }
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(javadocJar.get())
        // Provide artifacts information required
        pom {
            name.set(project.name)
            description.set(project.description)
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
                    id.set("YuKongA")
                    name.set("YuKongA")
                    url.set("$githubUrl/YuKongA")
                }
                developer {
                    id.set("HowieHChen")
                    name.set("Howie")
                    url.set("$githubUrl/HowieHChen")
                }
                developer {
                    id.set("Voemp")
                    name.set("Voemp")
                    url.set("$githubUrl/Voemp")
                }
                developer {
                    id.set("HChenX")
                    name.set("焕晨HChen")
                    url.set("$githubUrl/HChenX")
                }
                developer {
                    id.set("wxxsfxyzm")
                    name.set("wxxsfxyzm")
                    url.set("$githubUrl/wxxsfxyzm")
                }
            }
        }
    }
}

// Signing artifacts. Signing.* extra properties values will be used
signing {
    val signingKey = System.getenv("GPG_SIGNING_KEY") ?: localProperties.getProperty("GPG_SIGNING_KEY")
    val signingPassword = System.getenv("GPG_PASSPHRASE") ?: localProperties.getProperty("GPG_PASSPHRASE")
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

// Ensure all publish tasks depend on corresponding sign tasks
tasks.withType<PublishToMavenRepository>().configureEach {
    dependsOn(tasks.withType<Sign>())
}
