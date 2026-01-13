// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
}

val generatedSrcDir =
    layout.buildDirectory
        .dir("generated")
        .get()
        .asFile
        .resolve("miuix-example")

kotlin {
    android {
        androidResources.enable = true
        buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
        compileSdk = BuildConfig.COMPILE_SDK
        namespace = BuildConfig.APPLICATION_SHARED_ID
    }

    jvm("desktop")

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            binaryOption("smallBinary", "true")
            binaryOption("preCodegenInlineThreshold", "40")
        }
    }

    macosArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    js(IR) {
        browser()
    }

    sourceSets {
        commonMain {
            kotlin.srcDir(generatedSrcDir.resolve("kotlin").absolutePath)
            dependencies {
                api(projects.miuix)
                api(libs.jetbrains.compose.components.resources)
                implementation(projects.miuixIcons)
                implementation(projects.miuixNavigation3Ui)
                implementation(projects.miuixNavigation3Adaptive)
                implementation(libs.androidx.navigation3.runtime)
            }
        }

        val skikoMain by creating {
            dependsOn(commonMain.get())
        }

        val darwinMain by creating {
            dependsOn(skikoMain)
        }

        val iosMain by creating {
            dependsOn(darwinMain)
        }

        iosArm64Main {
            dependsOn(iosMain)
        }

        iosSimulatorArm64Main {
            dependsOn(iosMain)
        }

        val macosMain by creating {
            dependsOn(darwinMain)
        }

        macosArm64Main {
            dependsOn(macosMain)
        }

        named("desktopMain") {
            dependsOn(skikoMain)
        }

        val webMain by creating {
            dependsOn(skikoMain)
        }

        wasmJsMain {
            dependsOn(webMain)
        }

        jsMain {
            dependsOn(webMain)
        }
    }
}

compose.resources {
    publicResClass = true
}

val generateVersionInfo by tasks.registering {
    doLast {
        val file = generatedSrcDir.resolve("kotlin/misc/VersionInfo.kt")
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }
        file.writeText(
            """
            package misc

            object VersionInfo {
                const val VERSION_NAME = "${BuildConfig.APPLICATION_VERSION_NAME}"
                const val VERSION_CODE = ${BuildConfig.APPLICATION_VERSION_CODE}
                const val JDK_VERSION = "${System.getProperty("java.version")}"
            }
            """.trimIndent(),
        )
        val iosPlist = project.rootDir.resolve("example/ios/iosApp/Info.plist")
        if (iosPlist.exists()) {
            val content = iosPlist.readText()
            val updatedContent =
                content
                    .replace(
                        Regex("<key>CFBundleShortVersionString</key>\\s*<string>[^<]*</string>"),
                        "<key>CFBundleShortVersionString</key>\n\t<string>${BuildConfig.APPLICATION_VERSION_NAME}</string>",
                    ).replace(
                        Regex("<key>CFBundleVersion</key>\\s*<string>[^<]*</string>"),
                        "<key>CFBundleVersion</key>\n\t<string>${BuildConfig.APPLICATION_VERSION_CODE}</string>",
                    )
            iosPlist.writeText(updatedContent)
        }
    }
}

tasks.named("generateComposeResClass").configure {
    dependsOn(generateVersionInfo)
}
