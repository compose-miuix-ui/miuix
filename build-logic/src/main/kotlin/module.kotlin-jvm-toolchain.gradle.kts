// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin

plugins.withType<KotlinBasePlugin> {
    extensions.configure<KotlinBaseExtension> {
        jvmToolchain(BuildConfig.JDK_VERSION)
    }
}
