// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.diffplug.spotless")
    id("module.publication")
}

kotlin {
    withSourcesJar(true)

    android {
        compileSdk = BuildConfig.COMPILE_SDK
        namespace = BuildConfig.LIBRARY_ID
        buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
    }

    jvm("desktop")

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64(),
    ).forEach {
        it.compilerOptions {
            freeCompilerArgs.add("-Xbinary=preCodegenInlineThreshold=40")
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    js(IR) {
        browser()
        compilerOptions {
            freeCompilerArgs.add("-Xes-long-as-bigint")
            freeCompilerArgs.add("-XXLanguage:+JsAllowLongInExportedDeclarations")
        }
    }
}

spotless {
    kotlin {
        target("src/**/*.kt")
        licenseHeaderFile(rootProject.file("./spotless/copyright.txt"), "(^(?![\\/ ]\\**).*$)")
    }

    kotlinGradle {
        target("*.kts")
        licenseHeaderFile(rootProject.file("./spotless/copyright.txt"), "(^(?![\\/ ]\\**).*$)")
    }
}
