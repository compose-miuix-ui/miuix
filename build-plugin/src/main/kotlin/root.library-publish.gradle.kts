// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

val versionSuffix = when {
    project.hasProperty("release") -> ""
    project.hasProperty("alpha") -> {
        val v = project.property("alpha")?.toString()
        if (v.isNullOrBlank()) "-alpha" else "-alpha$v"
    }
    project.hasProperty("beta") -> {
        val v = project.property("beta")?.toString()
        if (v.isNullOrBlank()) "-beta" else "-beta$v"
    }
    project.hasProperty("rc") -> {
        val v = project.property("rc")?.toString()
        if (v.isNullOrBlank()) "-rc" else "-rc$v"
    }
    else -> "-${getGitHashShort()}-SNAPSHOT"
}

val computedVersion = "${BuildConfig.LIBRARY_VERSION}$versionSuffix"

allprojects {
    group = BuildConfig.LIBRARY_ID
    version = computedVersion
}