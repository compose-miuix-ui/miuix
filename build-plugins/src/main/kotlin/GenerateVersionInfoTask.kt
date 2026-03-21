// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class GenerateVersionInfoTask : DefaultTask() {
    @get:Input
    abstract val versionName: Property<String>

    @get:Input
    abstract val versionCode: Property<Int>

    @get:OutputFile
    abstract val outputFile: RegularFileProperty

    @get:Internal
    abstract val iosPlistFile: RegularFileProperty

    @TaskAction
    fun generate() {
        val file = outputFile.get().asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            package misc

            object VersionInfo {
                const val VERSION_NAME = "${versionName.get()}"
                const val VERSION_CODE = ${versionCode.get()}
            }
            """.trimIndent(),
        )

        val plist = iosPlistFile.orNull?.asFile
        if (plist != null && plist.exists()) {
            val content = plist.readText()
            val updatedContent =
                content
                    .replace(
                        Regex("<key>CFBundleShortVersionString</key>\\s*<string>[^<]*</string>"),
                        "<key>CFBundleShortVersionString</key>\n\t<string>${versionName.get()}</string>",
                    ).replace(
                        Regex("<key>CFBundleVersion</key>\\s*<string>[^<]*</string>"),
                        "<key>CFBundleVersion</key>\n\t<string>${versionCode.get()}</string>",
                    )
            plist.writeText(updatedContent)
        }
    }
}