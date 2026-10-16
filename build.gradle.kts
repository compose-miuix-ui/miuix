// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.RootPackageJsonTask
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    id("module.spotless")
}

plugins.withType<YarnPlugin> {
    the<YarnRootExtension>().lockFileDirectoryProperty =
        layout.projectDirectory.dir("docs/demo/kotlin-js-store").asFile
}

if (providers.systemProperty("idea.sync.active").map(String::toBoolean).getOrElse(false)) {
    gradle.projectsEvaluated {
        // Work around KGP's task realization race during parallel IDE sync.
        // Initialize JS/Wasm root package tasks before parallel model queries.
        // https://slack-chats.kotlinlang.org/t/32838689/sometimes-while-making-gradle-sync-project-got-exception-see
        tasks.withType<RootPackageJsonTask>().forEach { }
    }
}
