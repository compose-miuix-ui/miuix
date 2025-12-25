// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

object BuildConfig {
    const val LIBRARY_VERSION = "0.7.3"
    const val LIBRARY_ID = "top.yukonga.miuix.kmp"
    const val APPLICATION_NAME = "Miuix"
    const val APPLICATION_VERSION_NAME = "1.0.7"
    val APPLICATION_VERSION_CODE = getVersionCode()
    const val APPLICATION_ID = "top.yukonga.miuix.uitest"
    const val APPLICATION_SHARED_ID = "top.yukonga.miuix.shared"
    const val COMPILE_SDK = 36
    const val TARGET_SDK = 36
    const val MIN_SDK = 24
    const val BUILD_TOOLS_VERSION = "36.1.0"
    const val JDK_VERSION = 21
}

fun getVersionCode(): Int {
    val process = ProcessBuilder("git", "rev-list", "--count", "HEAD").start()
    return process.inputStream.bufferedReader().use { it.readText().trim().toInt() }
}

fun gitHashShort(): String {
    val process = ProcessBuilder("git", "rev-parse", "--short", "HEAD").start()
    return process.inputStream.bufferedReader().use { it.readText().trim() }
}