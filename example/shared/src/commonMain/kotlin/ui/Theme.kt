// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import top.yukonga.miuix.kmp.theme.ColorSchemeMode
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.TextStyles
import top.yukonga.miuix.kmp.theme.ThemeColorSpec
import top.yukonga.miuix.kmp.theme.ThemeController
import top.yukonga.miuix.kmp.theme.ThemePaletteStyle

val LocalColorMode = compositionLocalOf { 0 }
val LocalIsDarkTheme = compositionLocalOf<Boolean?> { null }

@Composable
fun AppTheme(
    colorMode: Int = 0,
    keyColor: Color? = null,
    paletteStyle: Int = 0,
    colorSpec: Int = 0,
    isDark: Boolean? = null,
    textStyles: TextStyles = MiuixTheme.textStyles,
    content: @Composable () -> Unit,
) {
    val spec = ThemeColorSpec.entries.getOrNull(colorSpec) ?: ThemeColorSpec.Spec2021
    val style = ThemePaletteStyle.entries.getOrNull(paletteStyle) ?: ThemePaletteStyle.TonalSpot
    val parentIsDark = LocalIsDarkTheme.current
    val resolvedIsDark = isDark ?: parentIsDark
    val controller = remember(colorMode, keyColor, spec, style, resolvedIsDark) {
        when (colorMode) {
            1 -> ThemeController(ColorSchemeMode.Light)
            2 -> ThemeController(ColorSchemeMode.Dark)
            3 -> ThemeController(ColorSchemeMode.MonetSystem, keyColor = keyColor, colorSpec = spec, paletteStyle = style, isDark = resolvedIsDark)
            4 -> ThemeController(ColorSchemeMode.MonetLight, keyColor = keyColor, colorSpec = spec, paletteStyle = style)
            5 -> ThemeController(ColorSchemeMode.MonetDark, keyColor = keyColor, colorSpec = spec, paletteStyle = style)
            else -> ThemeController(ColorSchemeMode.System, isDark = resolvedIsDark)
        }
    }
    CompositionLocalProvider(
        LocalColorMode provides colorMode,
        LocalIsDarkTheme provides resolvedIsDark,
    ) {
        MiuixTheme(
            controller = controller,
            textStyles = textStyles,
            content = content,
        )
    }
}

fun TextStyles.withFontFamily(fontFamily: FontFamily): TextStyles = copy(
    main = main.copy(fontFamily = fontFamily),
    paragraph = paragraph.copy(fontFamily = fontFamily),
    body1 = body1.copy(fontFamily = fontFamily),
    body2 = body2.copy(fontFamily = fontFamily),
    button = button.copy(fontFamily = fontFamily),
    footnote1 = footnote1.copy(fontFamily = fontFamily),
    footnote2 = footnote2.copy(fontFamily = fontFamily),
    headline1 = headline1.copy(fontFamily = fontFamily),
    headline2 = headline2.copy(fontFamily = fontFamily),
    subtitle = subtitle.copy(fontFamily = fontFamily),
    title1 = title1.copy(fontFamily = fontFamily),
    title2 = title2.copy(fontFamily = fontFamily),
    title3 = title3.copy(fontFamily = fontFamily),
    title4 = title4.copy(fontFamily = fontFamily),
)

@Composable
fun isInDarkTheme(): Boolean = when (LocalColorMode.current) {
    1, 4 -> false
    2, 5, 6 -> true
    else -> LocalIsDarkTheme.current ?: isSystemInDarkTheme()
}

val KeyColors: List<Pair<String, Color>> = listOf(
    "Blue" to Color(0xFF3482FF),
    "Green" to Color(0xFF36D167),
    "Purple" to Color(0xFF7C4DFF),
    "Yellow" to Color(0xFFFFB21D),
    "Orange" to Color(0xFFFF5722),
    "Pink" to Color(0xFFE91E63),
    "Teal" to Color(0xFF00BCD4),
)

fun keyColorFor(index: Int): Color? = if (index <= 0) null else KeyColors.getOrNull(index - 1)?.second
