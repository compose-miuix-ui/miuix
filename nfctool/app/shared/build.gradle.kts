import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    android {
        androidResources.enable = true
        buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
        compileSdk {
            version = release(BuildConfig.COMPILE_SDK) {
                minorApiLevel = BuildConfig.COMPILE_SDK_MINOR
            }
        }
        minSdk = BuildConfig.MIN_SDK
        namespace = BuildConfig.APPLICATION_SHARED_ID
    }

    jvm("desktop")

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            binaryOption("smallBinary", "true")
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    js(IR) {
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.nfctoolCore)
                implementation(libs.jetbrains.compose.components.resources)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.lifecycle.runtime)
                implementation(libs.jetbrains.lifecycle.runtime.compose)
                implementation(libs.kotlinx.serialization.core)
            }
        }

        val skikoMain by creating {
            dependsOn(commonMain.get())
        }

        val darwinMain by creating {
            dependsOn(skikoMain)
        }

        val iosMain by creating {
            dependsOn(darwinMain)
        }

        iosArm64Main {
            dependsOn(iosMain)
        }

        iosSimulatorArm64Main {
            dependsOn(iosMain)
        }

        named("desktopMain") {
            dependsOn(skikoMain)
        }

        val webMain by creating {
            dependsOn(skikoMain)
        }

        wasmJsMain {
            dependsOn(webMain)
        }

        jsMain {
            dependsOn(webMain)
        }
    }
}

compose.resources {
    publicResClass = true
}