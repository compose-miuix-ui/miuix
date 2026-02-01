// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("UnstableApiUsage")

rootProject.name = "compose-ui-miuix"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-plugins")
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

include(":miuix")
include(":miuix-icons")
include(":miuix-navigation3-ui")
include(":miuix-navigation3-adaptive")

include(":example:shared")
include(":example:android")
include(":example:desktop")
include(":example:js")
include(":example:wasmJs")
include(":example:macos")

include(":docs:demo")
include(":docs:iconGen")
