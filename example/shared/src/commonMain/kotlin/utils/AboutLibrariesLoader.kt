// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAboutLibrariesJsonLoader = staticCompositionLocalOf<(suspend () -> String)?> { null }

@Composable
expect fun rememberAboutLibrariesJsonLoader(): suspend () -> String
