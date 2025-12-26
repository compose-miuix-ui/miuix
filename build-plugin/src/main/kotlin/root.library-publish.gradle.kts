// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

val isRelease = project.hasProperty("release")
val computedVersion = if (isRelease) BuildConfig.LIBRARY_VERSION else "${BuildConfig.LIBRARY_VERSION}-${getGitHashShort()}-SNAPSHOT"

allprojects {
    group = BuildConfig.LIBRARY_ID
    version = computedVersion
}
