// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import com.diffplug.spotless.LineEnding

plugins {
    id("com.diffplug.spotless")
}

val composeKtlintRules = "io.nlopez.compose.rules:ktlint:0.6.2"
val copyrightFile = rootProject.file("spotless/copyright.txt")
val copyrightDelimiter = "^(?![ \\t]*(?:\\/\\/|\\/\\*)).*[\\w].*$"

spotless {
    lineEndings = LineEnding.PLATFORM_NATIVE

    kotlin {
        target("src/**/*.kt")
        targetExclude("**/build/**/*.kt", "**/icon/**/*.kt")
        ktlint()
            .customRuleSets(listOf(composeKtlintRules))
            .editorConfigOverride(
                mapOf(
                    "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                    "ktlint_compose_modifier-missing-check" to "disabled",
                    "ktlint_compose_compositionlocal-allowlist" to "disabled",
                    "ktlint_compose_mutable-state-param-check" to "disabled",
                    "ktlint_compose_parameter-naming" to "disabled",
                    "ktlint_compose_modifier-naming" to "disabled",
                ),
            )
        licenseHeaderFile(copyrightFile, copyrightDelimiter)
    }

    kotlinGradle {
        target("*.kts")
        targetExclude("**/build/**/*.kts")
        ktlint()
        licenseHeaderFile(copyrightFile, copyrightDelimiter)
    }
}
