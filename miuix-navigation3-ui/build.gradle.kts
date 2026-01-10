// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
    id("module.publication")
}

kotlin {
    withSourcesJar(true)

    android {
        buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
        compileSdk = BuildConfig.COMPILE_SDK
        namespace = "${BuildConfig.LIBRARY_ID}.navigation3.ui"
    }

    jvm("desktop")

    iosArm64()
    iosSimulatorArm64()
    macosArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    js(IR) {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.miuix)
            implementation(libs.androidx.collection)
            implementation(libs.androidx.navigation3.runtime)
            implementation(libs.gaze.capsule)
            implementation(libs.jetbrains.androidx.navigationevent)
            implementation(libs.jetbrains.compose.foundation)
            implementation(libs.jetbrains.lifecycle.runtime)
            implementation(libs.jetbrains.lifecycle.runtime.compose)
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
