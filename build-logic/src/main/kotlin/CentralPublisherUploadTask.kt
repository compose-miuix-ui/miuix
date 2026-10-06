// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URI
import java.nio.file.Files
import java.util.Base64
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * Packages a staged Maven Central repository directory into a single bundle ZIP
 * and uploads it to the Central Publisher API.
 *
 * Token inputs are marked `@Internal` to keep secrets out of build cache fingerprints.
 */
abstract class CentralPublisherUploadTask : DefaultTask() {
    @get:InputDirectory
    abstract val sonatypeRepoDir: DirectoryProperty

    @get:Input
    abstract val bundleFileName: Property<String>

    @get:OutputFile
    abstract val bundleFile: RegularFileProperty

    @get:Internal
    abstract val centralTokenId: Property<String>

    @get:Internal
    abstract val centralTokenSecret: Property<String>

    @TaskAction
    fun publish() {
        val sourceDir = sonatypeRepoDir.get().asFile.toPath()
        val zipPath = bundleFile.get().asFile.toPath()
        Files.createDirectories(zipPath.parent)

        ZipOutputStream(Files.newOutputStream(zipPath)).use { zos ->
            Files.walk(sourceDir).use { paths ->
                paths.filter { Files.isRegularFile(it) }.forEach { path ->
                    val entryName = sourceDir.relativize(path).toString().replace('\\', '/')
                    zos.putNextEntry(ZipEntry(entryName))
                    Files.newInputStream(path).use { it.copyTo(zos) }
                    zos.closeEntry()
                }
            }
        }

        val tokenId = centralTokenId.orNull
        val tokenSecret = centralTokenSecret.orNull
        if (tokenId.isNullOrBlank() || tokenSecret.isNullOrBlank()) {
            throw GradleException("Central publisher token is not configured")
        }

        val zipSize = Files.size(zipPath)
        logger.lifecycle("Uploading archive to Maven Central: $zipPath (size=$zipSize bytes)")

        val auth = Base64.getEncoder().encodeToString("$tokenId:$tokenSecret".toByteArray(Charsets.UTF_8))
        val url = URI("https://central.sonatype.com/api/v1/publisher/upload?publishingType=AUTOMATIC").toURL()
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true
        val boundary = "------------------------" + System.currentTimeMillis().toString(16)
        val bundleName = bundleFileName.get()
        connection.setRequestProperty("Authorization", "Bearer $auth")
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")
        connection.setRequestProperty("Accept", "text/plain")
        try {
            zipPath.toFile().inputStream().use { input ->
                connection.outputStream.use { output ->
                    output.write("--$boundary\r\n".toByteArray(Charsets.UTF_8))
                    output.write(
                        "Content-Disposition: form-data; name=\"bundle\"; filename=\"$bundleName\"\r\n"
                            .toByteArray(Charsets.UTF_8),
                    )
                    output.write("Content-Type: application/zip\r\n\r\n".toByteArray(Charsets.UTF_8))
                    input.copyTo(output)
                    output.write("\r\n--$boundary--\r\n".toByteArray(Charsets.UTF_8))
                    output.flush()
                }
            }
        } catch (e: IOException) {
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
