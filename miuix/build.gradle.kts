// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.jetbrains.dokka)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.spotless)
    id("module.publication")
}

android {
    namespace = "top.yukonga.miuix.kmp"
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

kotlin {
    withSourcesJar(true)

    jvmToolchain(21)

    androidTarget {
        publishLibraryVariants("release")
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

    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.window) // Android WindowMetrics
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)

            implementation(libs.jetbrains.compose.ui.backhandler)
            implementation(libs.jetbrains.compose.window.size)

            implementation(libs.gaze.capsule) // Capsule for Multiplatform
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
