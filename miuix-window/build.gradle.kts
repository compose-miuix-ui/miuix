// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinMultiplatform)
    id("module.kotlin-jvm-toolchain")
    id("module.publication")
    id("module.spotless")
}

miuixPublication {
    description.set("Window and caption bar styling for Miuix Desktop")
}

kotlin {
    withSourcesJar(true)

    jvm("desktop")

    sourceSets {
        getByName("desktopMain") {
            dependencies {
                api(projects.miuixCore)
                api(projects.miuixSquircle)
                api(projects.miuixUi)
                api(libs.jetbrains.compose.foundation)
                implementation(libs.jna)
                implementation(libs.jna.platform)
            }
        }
        getByName("desktopTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(compose.desktop.currentOs)
                implementation(libs.jetbrains.compose.ui.test)
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    if (System.getProperty("os.name").startsWith("Linux")) {
        jvmArgs(
            "--add-opens=java.desktop/java.awt=ALL-UNNAMED",
            "--add-opens=java.desktop/sun.awt=ALL-UNNAMED",
            "--add-opens=java.desktop/sun.awt.X11=ALL-UNNAMED",
        )
    }
}
