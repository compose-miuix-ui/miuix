// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

/**
 * Applies the Miuix custom source set hierarchy on top of the KMP default template:
 *
 * ```
 * commonMain
 * └── skikoMain
 *     ├── darwinMain
 *     │   ├── iosMain (iosArm64Main, iosSimulatorArm64Main)
 *     │   └── macosMain (macosArm64Main)
 *     ├── desktopMain
 *     └── webMain (wasmJsMain, jsMain)
 * ```
 *
 * Call this after declaring targets so that target-specific source set accessors exist.
 */
fun KotlinMultiplatformExtension.applyMiuixSourceSetHierarchy() {
    sourceSets.apply {
        val skikoMain = create("skikoMain") {
            dependsOn(getByName("commonMain"))
        }
        val darwinMain = create("darwinMain") {
            dependsOn(skikoMain)
        }
        val iosMain = create("iosMain") {
            dependsOn(darwinMain)
        }
        val macosMain = create("macosMain") {
            dependsOn(darwinMain)
        }
        val webMain = create("webMain") {
            dependsOn(skikoMain)
        }

        named("iosArm64Main") { dependsOn(iosMain) }
        named("iosSimulatorArm64Main") { dependsOn(iosMain) }
        named("macosArm64Main") { dependsOn(macosMain) }
        named("desktopMain") { dependsOn(skikoMain) }
        named("wasmJsMain") { dependsOn(webMain) }
        named("jsMain") { dependsOn(webMain) }
    }
}
