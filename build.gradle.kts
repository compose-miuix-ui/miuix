// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

plugins {
    id("module.spotless")
}

// CMP 1.12.0's checkComposeUiTestConfigurationFor* false-positives on any Skiko
// dependency; this repo runs no Compose UI browser tests (CMP-4906).
subprojects {
    tasks.matching { it.name.startsWith("checkComposeUiTestConfigurationFor") }.configureEach {
        enabled = false
    }
}
