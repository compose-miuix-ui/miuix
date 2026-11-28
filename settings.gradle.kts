// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("UnstableApiUsage")

rootProject.name = "compose-miuix-ui"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("NO_IMPLICIT_LOOKUP_IN_PARENT_PROJECTS")
enableFeaturePreview("ENHANCED_GRAPH_ORDERING")

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        if (System.getenv("CI") == "true") {
            maven("https://maven-central.storage-download.googleapis.com/maven2")
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    repositories {
        google()
        if (System.getenv("CI") == "true") {
            maven("https://maven-central.storage-download.googleapis.com/maven2")
        }
        mavenCentral()
    }
}

includeBuild("build-logic")

include(":miuix-core")
include(":miuix-ui")
include(":miuix-preference")
include(":miuix-material")
include(":miuix-blur")
include(":miuix-glass")
include(":miuix-squircle")
include(":miuix-icons-symbol")
include(":miuix-nav")
include(":miuix-window")

include(":example:shared")
include(":example:android")
include(":example:desktop")
include(":example:desktop-nucleus")
include(":example:web")
include(":example:macos")

include(":baselineprofile")

include(":docs:demo")
include(":docs:iconGen")
