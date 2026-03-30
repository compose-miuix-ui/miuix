// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.interfaces

/**
 * Marks ScrollBar APIs as experimental.
 *
 * This scrollbar component is in an early experimental stage with known issues.
 * Its API surface is unstable and subject to breaking changes in future releases.
 */
@RequiresOptIn(
    message = "This ScrollBar API is experimental. It has known issues and may change without notice.",
    level = RequiresOptIn.Level.WARNING,
)
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
)
annotation class ExperimentalScrollBarApi
