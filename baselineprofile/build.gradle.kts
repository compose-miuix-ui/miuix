// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import com.android.build.api.dsl.ManagedVirtualDevice

plugins {
    alias(libs.plugins.androidTest)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "top.yukonga.baselineprofile"

    buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
    compileSdk = BuildConfig.COMPILE_SDK

    defaultConfig {
        minSdk = BuildConfig.MIN_SDK
        targetSdk = BuildConfig.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    targetProjectPath = ":example:android"

    // This code creates the gradle managed device used to generate baseline profiles.
    // To use GMD please invoke generation through the command line:
    // ./gradlew :example:android:generateBaselineProfile
    testOptions.managedDevices.allDevices {
        create<ManagedVirtualDevice>("pixel6Api34") {
            device = "Pixel 6"
            apiLevel = 34
            systemImageSource = "aosp-atd"
        }
    }
}

// This is the configuration block for the Baseline Profile plugin.
// You can specify to run the generators on a managed devices or connected devices.
baselineProfile {
    managedDevices += "pixel6Api34"
    useConnectedDevices = false
}

dependencies {
    implementation(libs.androidx.junit)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.uiautomator)
    implementation(libs.androidx.benchmark.macro.junit4)
}

androidComponents {
    onVariants { v ->
        val artifactsLoader = v.artifacts.getBuiltArtifactsLoader()
        v.instrumentationRunnerArguments.put(
            "targetAppId",
            v.testedApks.map { artifactsLoader.load(it)?.applicationId },
        )
    }
}
