// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    implementation(libs.android.gradle.plugin)
    implementation(libs.android.library.gradle.plugin)
    implementation(libs.android.application.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.compose.multiplatform.gradle.plugin)
    implementation(libs.kotlin.compose.compiler.gradle.plugin)
    implementation(libs.hot.reload.gradle.plugin)
    implementation(libs.dokka.gradle.plugin)
    implementation(libs.spotless.plugin.gradle)
}
