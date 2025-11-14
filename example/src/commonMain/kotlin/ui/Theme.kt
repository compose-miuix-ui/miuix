// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package ui

import androidx.compose.runtime.Composable
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeController

@Composable
fun AppTheme(
    colorMode: Int = 0,
    content: @Composable () -> Unit
) {
    return MiuixTheme(
        controller = when (colorMode) {
            1 -> ThemeController(ColorSchemeMode.Light)
            2 -> ThemeController(ColorSchemeMode.Dark)
            3 -> ThemeController(ColorSchemeMode.DynamicSystem)
            4 -> ThemeController(ColorSchemeMode.DynamicLight)
            5 -> ThemeController(ColorSchemeMode.DynamicDark)
            else -> ThemeController(ColorSchemeMode.System)
        },
        content = content
    )
}