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
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

includeBuild("build-logic")

include(":miuix-core")
include(":miuix-ui")
include(":miuix-preference")
include(":miuix-shader")
include(":miuix-blur")
include(":miuix-glass")
include(":miuix-glass-icons")
include(":miuix-squircle")
include(":miuix-icons")
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
