// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    listOf(
        macosArm64()
    ).forEach {
        it.binaries.executable {
            entryPoint = "main"
            binaryOption("smallBinary", "true")
            binaryOption("preCodegenInlineThreshold", "40")
        }
    }

    sourceSets {
        macosArm64Main {
            kotlin.srcDir("src/macosMain/kotlin")
            dependencies {
                implementation(projects.example.shared)
            }
        }
    }
}

compose.desktop {
    nativeApplication {
        targets(kotlin.targets.getByName("macosArm64"))
        distributions {
            targetFormats(TargetFormat.Dmg)
            packageName = BuildConfig.APPLICATION_NAME
            packageVersion = BuildConfig.APPLICATION_VERSION_NAME

            macOS.iconFile = project.file("resources/Miuix.icns")
        }
    }
}
