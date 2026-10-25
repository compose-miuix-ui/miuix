// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin

plugins.withType<KotlinBasePlugin> {
    extensions.configure<KotlinBaseExtension> {
        jvmToolchain(BuildConfig.JDK_VERSION)
    }
}

// CMP 1.12.0's checkComposeUiTestConfigurationFor* false-positives on any Skiko
// dependency; this repo runs no Compose UI browser tests (CMP-4906).
tasks.matching { it.name.startsWith("checkComposeUiTestConfigurationFor") }.configureEach {
    enabled = false
}
