// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import com.diffplug.spotless.LineEnding

plugins {
    id("com.diffplug.spotless")
}

spotless {
    lineEndings = LineEnding.PLATFORM_NATIVE

    kotlin {
        target("src/**/*.kt")
        targetExclude("**/build/**/*.kt", "miuix-icons/**/*.kt")
        ktlint("1.8.0")
            .customRuleSets(
                listOf(
                    "io.nlopez.compose.rules:ktlint:0.5.3",
                ),
            ).editorConfigOverride(
                mapOf(
                    "ktlint_standard_backing-property-naming" to "disabled",
                    "ktlint_standard_property-naming" to "disabled",
                    "ktlint_function_naming_ignore_when_annotated_with" to "Composable",
                    "ktlint_compose_modifier-missing-check" to "disabled",
                    "ktlint_compose_compositionlocal-allowlist" to "disabled",
                    "ktlint_compose_modifier-composed-check" to "disabled",
                    "ktlint_compose_mutable-state-param-check" to "disabled",
                    "ktlint_compose_mutable-state-autoboxing" to "disabled",
                    "ktlint_compose_param-order-check" to "disabled",
                    "ktlint_compose_parameter-naming" to "disabled",
                    "ktlint_compose_modifier-not-used-at-root" to "disabled",
                    "ktlint_compose_lambda-param-event-trailing" to "disabled",
                    "ktlint_compose_modifier-naming" to "disabled",
                    "ktlint_compose_multiple-emitters-check" to "disabled",
                    "ktlint_compose_naming-check" to "disabled",
                ),
            )
        licenseHeaderFile(rootProject.file("spotless/copyright.txt"), "^(?![ \\t]*(?:\\/\\/|\\/\\*)).*[\\w].*$")
    }

    kotlinGradle {
        target("*.kts")
        targetExclude("**/build/**/*.kts")
        ktlint("1.8.0")
        licenseHeaderFile(rootProject.file("spotless/copyright.txt"), "^(?![ \\t]*(?:\\/\\/|\\/\\*)).*[\\w].*$")
    }
}
