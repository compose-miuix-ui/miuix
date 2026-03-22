// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlinMultiplatform)
    id("module.publication")
}

kotlin {
    withSourcesJar(true)

    android {
        buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
        compileSdk = BuildConfig.COMPILE_SDK
        minSdk = BuildConfig.MIN_SDK
        namespace = BuildConfig.LIBRARY_ID
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
            api(libs.jetbrains.compose.foundation)

            implementation(libs.jetbrains.androidx.navigationevent)
            implementation(libs.jetbrains.compose.window.size)

            implementation(libs.kyant.shapes) // Capsule for Multiplatform
            implementation(libs.materialKolor.utilities) // Material Color for Multiplatform
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

baselineProfile {
    filter {
        include("top.yukonga.miuix.kmp.**")
    }
}

val convertBaselineProfile by tasks.registering(ConvertBaselineProfileTask::class) {
    inputFile.set(
        layout.projectDirectory.file("src/androidMain/generated/baselineProfiles/baseline-prof.txt"),
    )
    outputFile.set(
        layout.projectDirectory.file("src/androidMain/baselineProfiles/baseline-prof.txt"),
    )
    targetPackage.set("top/yukonga/miuix/kmp/")
    excludePackages.set(listOf("top/yukonga/miuix/kmp/icon/extended/"))
}

val convertStartupProfile by tasks.registering(ConvertBaselineProfileTask::class) {
    inputFile.set(
        rootProject.layout.projectDirectory.file(
            "example/android/src/release/generated/baselineProfiles/startup-prof.txt",
        ),
    )
    outputFile.set(
        layout.projectDirectory.file("src/androidMain/baselineProfiles/startup-prof.txt"),
    )
    targetPackage.set("top/yukonga/miuix/kmp/")
    excludePackages.set(listOf("top/yukonga/miuix/kmp/icon/extended/"))
}

tasks.matching { it.name == "generateBaselineProfile" }.configureEach {
    finalizedBy(convertBaselineProfile, convertStartupProfile)
}

dependencies {
    baselineProfile(project(":baselineprofile"))
}
