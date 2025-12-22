// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin

fun Project.configureJvmToolchain() {
    plugins.withType<KotlinBasePlugin> {
        extensions.configure<KotlinBaseExtension> {
            jvmToolchain(BuildConfig.JDK_VERSION)
        }
    }
}

allprojects {
    apply(plugin = "module.spotless")

    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        configureJvmToolchain()
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        configureJvmToolchain()
    }

    plugins.withId("org.jetbrains.kotlin.android") {
        var configured = false
        pluginManager.withPlugin("com.android.application") {
            if (!configured) {
                configureJvmToolchain()
                configured = true
            }
        }
    }
}
