// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.desktop.application.tasks.AbstractProguardTask
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

plugins {
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinMultiplatform)
    id("module.kotlin-jvm-toolchain")
    id("module.spotless")
}

kotlin {
    jvm("desktop")

    sourceSets {
        named("desktopMain") {
            dependencies {
                implementation(projects.miuixWindow)
                implementation(projects.example.shared)
                implementation(compose.desktop.currentOs)
                implementation(libs.nucleus.darkmode.detector)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "Main_desktopKt"
        jvmArgs += listOf("--enable-native-access=ALL-UNNAMED")
        if (System.getProperty("os.name").startsWith("Linux")) {
            jvmArgs +=
                listOf(
                    "--add-opens=java.desktop/sun.awt=ALL-UNNAMED",
                    "--add-opens=java.desktop/sun.awt.X11=ALL-UNNAMED",
                    "--add-opens=java.desktop/java.awt=ALL-UNNAMED",
                )
        }
        buildTypes.release.proguard {
            isEnabled = true
            optimize = true
            configurationFiles.from(project.file("proguard-rules.pro"))
        }
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = BuildConfig.APPLICATION_NAME
            packageVersion = BuildConfig.APPLICATION_VERSION_NAME

            macOS.iconFile = project.file("resources/macos/icon.icns")
            linux.iconFile = project.file("resources/linux/icon.png")
            windows.iconFile = project.file("resources/windows/icon.ico")
        }
    }
}

tasks.withType<AbstractProguardTask>().configureEach {
    doLast {
        val osName = System.getProperty("os.name").lowercase()
        val osArch = System.getProperty("os.arch").lowercase()

        val jnaTarget =
            when {
                osName.contains("linux") && (osArch == "x86_64" || osArch == "amd64") -> "linux-x86-64"
                osName.contains("linux") && (osArch == "aarch64" || osArch == "arm64") -> "linux-aarch64"
                osName.contains("windows") && (osArch == "x86_64" || osArch == "amd64") -> "win32-x86-64"
                osName.contains("windows") && (osArch == "aarch64" || osArch == "arm64") -> "win32-aarch64"
                osName.contains("mac") && (osArch == "x86_64" || osArch == "amd64") -> "darwin-x86-64"
                osName.contains("mac") && (osArch == "aarch64" || osArch == "arm64") -> "darwin-aarch64"
                else -> null
            }
        val nucleusTarget =
            when {
                osName.contains("linux") && (osArch == "x86_64" || osArch == "amd64") -> "linux-x64"
                osName.contains("linux") && (osArch == "aarch64" || osArch == "arm64") -> "linux-aarch64"
                osName.contains("windows") && (osArch == "x86_64" || osArch == "amd64") -> "win32-x64"
                osName.contains("windows") && (osArch == "aarch64" || osArch == "arm64") -> "win32-aarch64"
                osName.contains("mac") && (osArch == "x86_64" || osArch == "amd64") -> "darwin-x64"
                osName.contains("mac") && (osArch == "aarch64" || osArch == "arm64") -> "darwin-aarch64"
                else -> null
            }

        if (jnaTarget != null) {
            val nativeExts = listOf(".so", ".dll", ".dylib", ".a")
            destinationDir.asFile.get().listFiles()?.filter { it.extension == "jar" }?.forEach { jarFile ->
                val tempFile = File(jarFile.parentFile, jarFile.name + ".tmp")
                var modified = false
                ZipFile(jarFile).use { zipIn ->
                    val hasForeign =
                        zipIn.entries().asSequence().any { entry ->
                            val name = entry.name
                            val isNative = nativeExts.any { name.endsWith(it) }
                            (name.startsWith("com/sun/jna/") && !name.contains(jnaTarget) && isNative) ||
                                (name.startsWith("nucleus/native/") && nucleusTarget != null && !name.contains(nucleusTarget) && isNative)
                        }
                    if (hasForeign) {
                        ZipOutputStream(tempFile.outputStream()).use { zipOut ->
                            for (entry in zipIn.entries()) {
                                val name = entry.name
                                val isNative = nativeExts.any { name.endsWith(it) }
                                val isForeignJna = name.startsWith("com/sun/jna/") && !name.contains(jnaTarget) && isNative
                                val isForeignNucleus =
                                    name.startsWith("nucleus/native/") && nucleusTarget != null && !name.contains(nucleusTarget) && isNative
                                if (!isForeignJna && !isForeignNucleus) {
                                    zipOut.putNextEntry(ZipEntry(name))
                                    zipIn.getInputStream(entry).copyTo(zipOut)
                                    zipOut.closeEntry()
                                }
                            }
                        }
                        modified = true
                    }
                }
                if (modified) {
                    jarFile.delete()
                    tempFile.renameTo(jarFile)
                }
            }
        }
    }
}
