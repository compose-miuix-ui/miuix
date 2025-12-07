// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.materialkolor.dynamicColorScheme

@Stable
enum class ColorSchemeMode {
    System,
    Light,
    Dark,
    MonetSystem,
    MonetLight,
    MonetDark,
}

internal fun colorsFromSeed(seed: Color, dark: Boolean): Colors {
    val cs = dynamicColorScheme(seedColor = seed, isDark = dark)
    return mapMd3ToMiuixColorsCommon(cs, dark)
}

@Stable
class ThemeController(
    colorSchemeMode: ColorSchemeMode = ColorSchemeMode.System,
    keyColor: Color? = null,
    isDark: Boolean? = null,
) {
    var colorSchemeMode: ColorSchemeMode by mutableStateOf(colorSchemeMode)
    var keyColor: Color? by mutableStateOf(keyColor)
    var isDark: Boolean? by mutableStateOf(isDark)

    @Composable
    fun currentColors(): Colors {
        return when (colorSchemeMode) {
            ColorSchemeMode.System -> {
                val dark = isDark ?: isSystemInDarkTheme()
                remember(dark) { if (dark) darkColorScheme() else lightColorScheme() }
            }

            ColorSchemeMode.Light -> remember { lightColorScheme() }
            ColorSchemeMode.Dark -> remember { darkColorScheme() }
            ColorSchemeMode.MonetSystem -> {
                val dark = isDark ?: isSystemInDarkTheme()
                keyColor?.let {
                    remember(keyColor, dark) { colorsFromSeed(seed = it, dark = dark) }
                } ?: platformDynamicColors(dark = dark)
            }

            ColorSchemeMode.MonetLight -> {
                keyColor?.let {
                    remember(keyColor) { colorsFromSeed(seed = it, dark = false) }
                } ?: platformDynamicColors(dark = false)
            }

            ColorSchemeMode.MonetDark -> {
                keyColor?.let {
                    remember(keyColor) { colorsFromSeed(seed = it, dark = true) }
                } ?: platformDynamicColors(dark = true)
            }
        }
    }
}

internal val LocalColorSchemeMode: ProvidableCompositionLocal<ColorSchemeMode?> = staticCompositionLocalOf { null }
