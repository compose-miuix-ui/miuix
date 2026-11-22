// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Ensures that [androidx.compose.foundation.layout.WindowInsets.Companion.displayCutout]
 * properly reflects the display cutout on all supported platforms and device orientations.
 *
 * This composable is called automatically at the root of [MiuixTheme], but can also be
 * invoked manually in custom window roots or overlays when needed.
 */
@Composable
expect fun ProvideDisplayCutoutFix()

/**
 * Controls whether [ProvideDisplayCutoutFix] is enabled.
 * Defaults to `true`. Can be set to `false` via [androidx.compose.runtime.CompositionLocalProvider]
 * if an application wishes to disable the automatic display cutout insets fix.
 */
val LocalDisplayCutoutFixEnabled = staticCompositionLocalOf { true }
