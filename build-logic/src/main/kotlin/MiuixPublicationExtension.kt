// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import org.gradle.api.provider.Property

/**
 * Per-module publication metadata supplied by each module that applies `module.publication`.
 */
interface MiuixPublicationExtension {
    /** Short human-readable description used as the published POM description. */
    val description: Property<String>
}
