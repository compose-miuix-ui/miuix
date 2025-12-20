// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("UnstableApiUsage")

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.compose")
    id("org.jetbrains.compose.hot-reload")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.diffplug.spotless")
}

val generatedSrcDir = layout.buildDirectory.dir("generated").get().asFile.resolve("miuix-example")

kotlin {
    sourceSets {
        commonMain {
            kotlin.srcDir(generatedSrcDir.resolve("kotlin").absolutePath)
        }
    }

    androidTarget()

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.compilerOptions {
            freeCompilerArgs.add("-Xbinary=preCodegenInlineThreshold=40")
        }
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    listOf(
        macosArm64(),
    ).forEach {
        it.compilerOptions {
            freeCompilerArgs.add("-Xbinary=preCodegenInlineThreshold=40")
        }
        it.binaries.executable {
            entryPoint = "main"
        }
    }

    jvm("desktop")

    js(IR) {
        outputModuleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        compilerOptions {
            freeCompilerArgs.add("-Xes-long-as-bigint")
            freeCompilerArgs.add("-XXLanguage:+JsAllowLongInExportedDeclarations")
        }
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        outputModuleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
            }
        }
        binaries.executable()
    }
}

android {
    compileSdk = BuildConfig.COMPILE_SDK
    namespace = BuildConfig.APPLICATION_ID
    buildToolsVersion = BuildConfig.BUILD_TOOLS_VERSION
    defaultConfig {
        targetSdk = BuildConfig.TARGET_SDK
        minSdk = BuildConfig.MIN_SDK
        applicationId = BuildConfig.APPLICATION_ID
        versionName = BuildConfig.APPLICATION_VERSION
        versionCode = getVersionCode()
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
    dependencies {
        debugImplementation(compose.uiTooling)
    }
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
    packaging {
        applicationVariants.all {
            outputs.all {
                (this as BaseVariantOutputImpl).outputFileName = "${BuildConfig.APPLICATION_NAME}-v$versionName($versionCode)-$name.apk"
            }
        }
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
}

androidComponents {
    onVariants(selector().withBuildType("release")) {
        it.packaging.resources.excludes.add("**")
    }
}

compose.desktop {
    application {
        mainClass = "Main_desktopKt"
        buildTypes.release.proguard {
            configurationFiles.from("proguard-rules-jvm.pro")
        }
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = BuildConfig.APPLICATION_NAME
            packageVersion = BuildConfig.APPLICATION_VERSION

            macOS.iconFile = project.file("src/desktopMain/resources/macos/icon.icns")
            linux.iconFile = project.file("src/desktopMain/resources/linux/icon.png")
            windows.iconFile = project.file("src/desktopMain/resources/windows/icon.ico")
        }
    }
    nativeApplication {
        targets(kotlin.targets.getByName("macosArm64"))
        distributions {
            targetFormats(TargetFormat.Dmg)
            packageName = BuildConfig.APPLICATION_NAME
            packageVersion = BuildConfig.APPLICATION_VERSION

            macOS.iconFile = project.file("src/macosMain/resources/Miuix.icns")
        }
    }
}

rootProject.plugins.withType<YarnPlugin> {
    rootProject.the<YarnRootExtension>().lockFileDirectory = rootProject.file("example").resolve("kotlin-js-store")
}

spotless {
    kotlin {
        target("src/**/*.kt")
        licenseHeaderFile(rootProject.file("./spotless/copyright.txt"), "(^(?![\\/ ]\\**).*$)")
    }

    kotlinGradle {
        target("*.kts")
        licenseHeaderFile(rootProject.file("./spotless/copyright.txt"), "(^(?![\\/ ]\\**).*$)")
    }
}

fun getGitCommitCount(): Int {
    val process = Runtime.getRuntime().exec(arrayOf("git", "rev-list", "--count", "HEAD"))
    return process.inputStream.bufferedReader().use { it.readText().trim().toInt() }
}

fun getVersionCode(): Int = getGitCommitCount()

val generateVersionInfo by tasks.registering {
    doLast {
        val file = generatedSrcDir.resolve("kotlin/misc/VersionInfo.kt")
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }
        file.writeText(
            """
            package misc

            object VersionInfo {
                const val VERSION_NAME = "${BuildConfig.APPLICATION_VERSION}"
                const val VERSION_CODE = ${getVersionCode()}
                const val JDK_VERSION = "${System.getProperty("java.version")}"
            }
            """.trimIndent(),
        )
        val iosPlist = project.rootDir.resolve("iosApp/iosApp/Info.plist")
        if (iosPlist.exists()) {
            val content = iosPlist.readText()
            val updatedContent = content
                .replace(
                    Regex("<key>CFBundleShortVersionString</key>\\s*<string>[^<]*</string>"),
                    "<key>CFBundleShortVersionString</key>\n\t<string>${BuildConfig.APPLICATION_VERSION}</string>"
                )
                .replace(
                    Regex("<key>CFBundleVersion</key>\\s*<string>[^<]*</string>"),
                    "<key>CFBundleVersion</key>\n\t<string>${getVersionCode()}</string>"
                )
            iosPlist.writeText(updatedContent)
        }
    }
}

tasks.named("generateComposeResClass").configure {
    dependsOn(generateVersionInfo)
}

tasks.named<JavaExec>("hotRunDesktop") {
    jvmArgs("-Dapp.mode=hot")
}