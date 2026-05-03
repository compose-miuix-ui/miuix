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

    js(IR) {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            api(projects.miuixCore)
            api(libs.jetbrains.compose.foundation)

            implementation(libs.jetbrains.androidx.navigationevent)
            implementation(libs.jetbrains.compose.window.size)

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
        "top/yukonga/miuix/kmp/navigation3/ui/",
        rootProject.layout.projectDirectory
            .file(
                "miuix-navigation3-ui/src/androidMain/baselineProfiles/baseline-prof.txt",
            ).asFile.absolutePath,
    )
}

tasks.matching { it.name == "generateBaselineProfile" }.configureEach {
    finalizedBy(convertBaselineProfile)
}

dependencies {
    baselineProfile(project(":baselineprofile"))
}
