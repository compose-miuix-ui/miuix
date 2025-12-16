// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

val isRelease = project.hasProperty("release")
val baseVersion = "0.7.2"

fun gitShort(): String = try {
    val p = ProcessBuilder("git", "rev-parse", "--short", "HEAD").start()
    p.inputStream.bufferedReader().readText().trim().ifEmpty { "local" }
} catch (_: Exception) {
    "local"
}

val computedVersion = if (isRelease) baseVersion else "$baseVersion-${gitShort()}-SNAPSHOT"
allprojects {
    group = "top.yukonga.miuix.kmp"
    version = computedVersion
}
