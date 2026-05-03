// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
@ReadOnlyComposable
fun demoBackground(): Brush = if (isSystemInDarkTheme()) {
    Brush.linearGradient(listOf(Color(0xFF2E2118), Color(0xFF2A1E1E)))
} else {
    Brush.linearGradient(listOf(Color(0xFFFFEDD5), Color(0xFFFEE2E2)))
}
