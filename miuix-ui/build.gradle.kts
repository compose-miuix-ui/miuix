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
    id("module.kotlin-jvm-toolchain")
    id("module.publication")
    id("module.spotless")
}

miuixPublication {
    description.set("A UI library for Compose Multiplatform")
}

kotlin {
    withSourcesJar(true)

    android {
        buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
        compileSdk {
            version =
                release(BuildConfig.COMPILE_SDK) {
                    minorApiLevel = BuildConfig.COMPILE_SDK_MINOR
                }
        }
        minSdk = BuildConfig.MIN_SDK
        namespace = "${BuildConfig.LIBRARY_ID}.ui"
    }

    jvm("desktop")

    iosArm64()
    iosSimulatorArm64()
    macosArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    js {
        browser()
    }

    applyMiuixSourceSetHierarchy()

    sourceSets {
        commonMain.dependencies {
            api(projects.miuixCore)
            api(projects.miuixSquircle)
            api(libs.jetbrains.compose.foundation)

            implementation(libs.androidx.navigationevent)
            implementation(libs.jetbrains.compose.window.size)

            implementation(libs.materialKolor.utilities) // Material Color for Multiplatform
        }
    }
}

baselineProfile {
    filter {
        include("top.yukonga.miuix.kmp.**")
    }
}

val convertBaselineProfile by tasks.registering(ConvertBaselineProfileTask::class) {
    description = "convertBaselineProfile"
    inputFile.set(
        layout.projectDirectory.file("src/androidMain/generated/baselineProfiles/baseline-prof.txt"),
    )
    outputFile.set(
        layout.projectDirectory.file("src/androidMain/baselineProfiles/baseline-prof.txt"),
    )
    targetPackage.set("top/yukonga/miuix/kmp/")
    excludePackages.set(
        listOf(
            "top/yukonga/miuix/kmp/icon/extended/",
            "top/yukonga/miuix/kmp/shared/",
        ),
    )
    additionalOutputs.put(
        "top/yukonga/miuix/kmp/preference/",
        rootProject.layout.projectDirectory
            .file(
                "miuix-preference/src/androidMain/baselineProfiles/baseline-prof.txt",
            ).asFile.absolutePath,
    )
    additionalOutputs.put(
        "top/yukonga/miuix/kmp/blur/",
        rootProject.layout.projectDirectory
            .file(
                "miuix-blur/src/androidMain/baselineProfiles/baseline-prof.txt",
            ).asFile.absolutePath,
    )
    additionalOutputs.put(
        "top/yukonga/miuix/kmp/nav/",
        rootProject.layout.projectDirectory
            .file(
                "miuix-nav/src/androidMain/baselineProfiles/baseline-prof.txt",
            ).asFile.absolutePath,
    )
    additionalOutputs.put(
        "top/yukonga/miuix/kmp/shader/",
        rootProject.layout.projectDirectory
            .file(
                "miuix-shader/src/androidMain/baselineProfiles/baseline-prof.txt",
            ).asFile.absolutePath,
    )
    additionalOutputs.put(
        "top/yukonga/miuix/kmp/squircle/",
        rootProject.layout.projectDirectory
            .file(
                "miuix-squircle/src/androidMain/baselineProfiles/baseline-prof.txt",
            ).asFile.absolutePath,
    )
}

tasks.matching { it.name == "generateBaselineProfile" }.configureEach {
    finalizedBy(convertBaselineProfile)
}

dependencies {
    baselineProfile(project(":baselineprofile"))
}
