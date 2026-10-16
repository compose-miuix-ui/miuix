// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    id("module.spotless")
}

plugins.withType<YarnPlugin> {
    the<YarnRootExtension>().lockFileDirectoryProperty =
        layout.projectDirectory.dir("docs/demo/kotlin-js-store").asFile
}
