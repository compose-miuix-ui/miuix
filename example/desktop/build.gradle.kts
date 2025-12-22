// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("org.jetbrains.compose")
    id("org.jetbrains.compose.hot-reload")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    jvm("desktop")

    sourceSets {
        val desktopMain by getting
        desktopMain.dependencies {
            implementation(projects.example.shared)
            implementation(compose.desktop.currentOs)
        }
    }
}

compose.desktop {
    application {
        mainClass = "Main_desktopKt"
        buildTypes.release.proguard {
            configurationFiles.from("proguard-rules-desktop.pro")
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

tasks.named<JavaExec>("hotRunDesktop") {
    jvmArgs("-Dapp.mode=hot")
}