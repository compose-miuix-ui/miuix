// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import java.util.Properties

plugins {
    kotlin("android") // Removed after the release of AGP 9.0.0
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
}

dependencies {
    implementation(projects.example.shared)
    implementation(libs.androidx.activity)
}

@Suppress("UnstableApiUsage")
android {
    buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
    compileSdk = BuildConfig.COMPILE_SDK
    namespace = BuildConfig.APPLICATION_ID
    defaultConfig {
        targetSdk = BuildConfig.TARGET_SDK
        minSdk = BuildConfig.MIN_SDK
        applicationId = BuildConfig.APPLICATION_ID
        versionName = BuildConfig.APPLICATION_VERSION_NAME
        versionCode = BuildConfig.APPLICATION_VERSION_CODE
    }
    val properties = Properties()
    runCatching { properties.load(project.rootProject.file("local.properties").inputStream()) }
    val keystorePath = properties.getProperty("KEYSTORE_PATH") ?: System.getenv("KEYSTORE_PATH")
    val keystorePwd = properties.getProperty("KEYSTORE_PASS") ?: System.getenv("KEYSTORE_PASS")
    val alias = properties.getProperty("KEY_ALIAS") ?: System.getenv("KEY_ALIAS")
    val pwd = properties.getProperty("KEY_PASSWORD") ?: System.getenv("KEY_PASSWORD")
    if (keystorePath != null) {
        signingConfigs {
            register("github") {
                storeFile = file(keystorePath)
                storePassword = keystorePwd
                keyAlias = alias
                keyPassword = pwd
                enableV3Signing = true
                enableV4Signing = true
            }
        }
    } else {
        signingConfigs {
            register("release") {
                enableV3Signing = true
                enableV4Signing = true
            }
        }
    }
    base {
        archivesName.set(
            "${BuildConfig.APPLICATION_NAME}-v${BuildConfig.APPLICATION_VERSION_NAME}(${BuildConfig.APPLICATION_VERSION_CODE})",
        )
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            vcsInfo.include = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules-android.pro")
            signingConfig = signingConfigs.getByName(if (keystorePath != null) "github" else "release")
        }
        debug {
            if (keystorePath != null) signingConfig = signingConfigs.getByName("github")
        }
    }
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}

androidComponents {
    onVariants(selector().withBuildType("release")) {
        it.packaging.resources.excludes
            .add("**")
    }
}
