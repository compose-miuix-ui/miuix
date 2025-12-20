// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    id("build-plugin.library")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.window) // Android WindowMetrics
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material3)

            implementation(libs.jetbrains.compose.ui.backhandler)
            implementation(libs.jetbrains.compose.window.size)

            implementation(libs.gaze.capsule) // Capsule for Multiplatform
            implementation(libs.materialkolor.material.kolor) // Material Color for Multiplatform
        }
    }
}