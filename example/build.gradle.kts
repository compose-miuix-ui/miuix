// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    id("build-plugin.example")
}

kotlin {
    sourceSets {
        val desktopMain by getting
        androidMain.dependencies {
            implementation(libs.androidx.activity)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(projects.miuix)
            implementation(libs.jetbrains.androidx.navigation)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}