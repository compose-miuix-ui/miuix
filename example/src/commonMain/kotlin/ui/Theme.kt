// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeController

@Composable
fun AppTheme(
    colorMode: Int = 0,
    keyColor: Color? = null,
    content: @Composable () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val controller = when (colorMode) {
        1 -> ThemeController(ColorSchemeMode.Light)
        2 -> ThemeController(ColorSchemeMode.Dark)
        3 -> ThemeController(
            ColorSchemeMode.MonetSystem,
            keyColor = keyColor,
            isDark = isDark
        )

        4 -> ThemeController(
            ColorSchemeMode.MonetLight,
            keyColor = keyColor,
        )

        5 -> ThemeController(
            ColorSchemeMode.MonetDark,
            keyColor = keyColor,
        )

        else -> ThemeController(ColorSchemeMode.System)
    }
    return MiuixTheme(
        controller = controller,
        content = content
    )
}

val KeyColors: List<Pair<String, Color>> = listOf(
    "Blue" to Color(0xFF3482FF),
    "Green" to Color(0xFF36D167),
    "Purple" to Color(0xFF7C4DFF),
    "Yellow" to Color(0xFFFFB21D),
    "Orange" to Color(0xFFFF5722),
    "Pink" to Color(0xFFE91E63),
    "Teal" to Color(0xFF00BCD4)
)

fun keyColorFor(index: Int): Color? = if (index <= 0) null else KeyColors.getOrNull(index - 1)?.second