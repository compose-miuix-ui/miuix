// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
enum class ColorSchemeMode {
    System,
    Light,
    Dark,
    MonetSystem,
    MonetLight,
    MonetDark,
}

@Stable
class ThemeController(
    initialMode: ColorSchemeMode = ColorSchemeMode.System
) {
    var colorScheme: ColorSchemeMode by mutableStateOf(initialMode)

    @Composable
    fun currentColors(): Colors {
        return when (colorScheme) {
            ColorSchemeMode.System -> {
                val dark = isSystemInDarkTheme()
                if (dark) darkColorScheme() else lightColorScheme()
            }

            ColorSchemeMode.Light -> lightColorScheme()
            ColorSchemeMode.Dark -> darkColorScheme()
            ColorSchemeMode.MonetSystem -> {
                val dark = isSystemInDarkTheme()
                if (dark) {
                    platformDynamicColors(dark = true)
                } else {
                    platformDynamicColors(dark = false)
                }
            }

            ColorSchemeMode.MonetLight -> {
                platformDynamicColors(dark = false)
            }

            ColorSchemeMode.MonetDark -> {
                platformDynamicColors(dark = true)
            }
        }
    }
}

internal val LocalColorSchemeMode = staticCompositionLocalOf { ColorSchemeMode.System }