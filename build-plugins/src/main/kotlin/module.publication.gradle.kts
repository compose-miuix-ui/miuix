// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import java.io.FileInputStream
import java.net.HttpURLConnection
import java.net.URI
import java.nio.file.Files
import java.util.Base64
import java.util.Properties
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

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
val githubPackagesUrl = "$githubPkgUrl/$owner/$repository"
val sonatypePackageUrl = layout.buildDirectory.dir("publishing/mavenCentral")
val localPackageUrl = layout.buildDirectory.dir("repository/local")

val miuixDescription = "A UI library for Compose Multiplatform"
val miuixIconsDescription = "An extended icon library for Miuix"
val miuixNavigation3UiDescription = "A navigation3 UI library for Miuix"
val miuixNavigation3AdaptiveDescription = "A navigation3 adaptive library for Miuix"

val localPropertiesFile = project.rootProject.file("local.properties")
val localProperties = Properties()
if (localPropertiesFile.exists()) {
    FileInputStream(localPropertiesFile).use { localProperties.load(it) }
}

publishing {
    // Configure the publication repository
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
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(javadocJar.get())
        // Provide artifacts information required
        pom {
            name.set(project.name)
            description.set(
                when (project.name) {
                    "miuix-icons" -> miuixIconsDescription
                    "miuix-navigation3-ui" -> miuixNavigation3UiDescription
                    "miuix-navigation3-adaptive" -> miuixNavigation3AdaptiveDescription
                    else -> miuixDescription
                }
            )
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
                    url.set("$githubUrl/compose-miuix-ui")
                }
            }
        }
    }
}

// Task to publish to Maven Central using Central Publisher API
tasks.register("publishToMavenCentralUsingCentralApi") {
    dependsOn("publishAllPublicationsToSonatypeRepository")
    doLast {
        val sourceDir = sonatypePackageUrl.get().asFile.toPath()
        if (!Files.exists(sourceDir)) {
            throw GradleException("Maven Central repository directory does not exist: $sourceDir")
        }
        val zipFileName = "${project.name}-${project.version}.zip"
        val zipFile = layout.buildDirectory.file("publishing/$zipFileName").get().asFile.toPath()
        Files.createDirectories(zipFile.parent)
        ZipOutputStream(Files.newOutputStream(zipFile)).use { zos ->
            Files.walk(sourceDir).use { paths ->
                paths.filter { Files.isRegularFile(it) }.forEach { path ->
                    val entryName = sourceDir.relativize(path).toString().replace('\\', '/')
                    zos.putNextEntry(ZipEntry(entryName))
                    Files.newInputStream(path).use { input ->
                        input.copyTo(zos)
                    }
                    zos.closeEntry()
                }
            }
        }
        val zipSize = Files.size(zipFile)
        println("Uploading archive to Maven Central: $zipFile (size=${zipSize} bytes)")
        val centralTokenId = System.getenv("CENTRAL_TOKEN_ID") ?: localProperties.getProperty("CENTRAL_TOKEN_ID")
        val centralTokenSecret = System.getenv("CENTRAL_TOKEN_SECRET") ?: localProperties.getProperty("CENTRAL_TOKEN_SECRET")
        if (centralTokenId.isNullOrBlank() || centralTokenSecret.isNullOrBlank()) {
            throw GradleException("Central publisher token is not configured")
        }
        val auth = Base64.getEncoder().encodeToString("$centralTokenId:$centralTokenSecret".toByteArray(Charsets.UTF_8))
        val url = URI("https://central.sonatype.com/api/v1/publisher/upload?publishingType=AUTOMATIC").toURL()
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        val boundary = "------------------------" + System.currentTimeMillis().toString(16)
        connection.setRequestProperty("Authorization", "Bearer $auth")
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")
        connection.setRequestProperty("Accept", "text/plain")
        try {
            zipFile.toFile().inputStream().use { input ->
                connection.outputStream.use { output ->
                    output.write("--$boundary\r\n".toByteArray(Charsets.UTF_8))
                    output.write("Content-Disposition: form-data; name=\"bundle\"; filename=\"$zipFileName\"\r\n".toByteArray(Charsets.UTF_8))
                    output.write("Content-Type: application/zip\r\n\r\n".toByteArray(Charsets.UTF_8))
                    input.copyTo(output)
                    output.write("\r\n--$boundary--\r\n".toByteArray(Charsets.UTF_8))
                    output.flush()
                }
            }
        } catch (e: java.io.IOException) {
            val responseCodeDuringError = runCatching { connection.responseCode }.getOrElse { -1 }
            val errorBody = runCatching {
                (connection.errorStream ?: connection.inputStream)?.bufferedReader()?.use { it.readText() }
            }.getOrNull()
            val message = buildString {
                append("Central publisher API upload failed during streaming: ").append(e.message)
                append(" (HTTP ").append(responseCodeDuringError).append(")")
                if (!errorBody.isNullOrBlank()) {
                    append(": ").append(errorBody)
                }
            }
            throw GradleException(message, e)
        }
        val responseCode = connection.responseCode
        if (responseCode !in 200..299) {
            val errorStream = connection.errorStream ?: connection.inputStream
            val message = errorStream.bufferedReader().use { it.readText() }
            throw GradleException("Central publisher API request failed: $responseCode $message")
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
