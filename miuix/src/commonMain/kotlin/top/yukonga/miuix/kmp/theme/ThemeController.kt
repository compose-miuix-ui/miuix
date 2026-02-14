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
import androidx.compose.ui.graphics.toArgb
import com.materialkolor.dynamiccolor.ColorSpec
import com.materialkolor.hct.Hct
import com.materialkolor.scheme.DynamicScheme
import com.materialkolor.scheme.SchemeTonalSpot
import com.materialkolor.scheme.Variant

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
internal fun colorsFromSeed(seed: Color, dark: Boolean): Colors {
    val hctColor = Hct.fromInt(seed.toArgb())
    val tonalSpot = SchemeTonalSpot(
        sourceColorHct = hctColor,
        isDark = dark,
        contrastLevel = 0.0,
        specVersion = ColorSpec.SpecVersion.SPEC_2025,
        platform = DynamicScheme.Platform.PHONE,
    )
    val colors = DynamicScheme(
        sourceColorHct = hctColor,
        variant = Variant.TONAL_SPOT,
        isDark = dark,
        contrastLevel = 0.0,
        specVersion = ColorSpec.SpecVersion.SPEC_2025,
        platform = DynamicScheme.Platform.PHONE,
        primaryPalette = tonalSpot.primaryPalette,
        secondaryPalette = tonalSpot.secondaryPalette,
        tertiaryPalette = tonalSpot.tertiaryPalette,
        neutralPalette = tonalSpot.neutralPalette,
        neutralVariantPalette = tonalSpot.neutralVariantPalette,
        errorPalette = tonalSpot.errorPalette,
    )
    val roles = MonetRoles(
        primary = Color(colors.primary),
        onPrimary = Color(colors.onPrimary),
        primaryFixed = Color(colors.primaryFixed),
        onPrimaryFixed = Color(colors.onPrimaryFixed),
        error = Color(colors.error),
        onError = Color(colors.onError),
        errorContainer = Color(colors.errorContainer),
        onErrorContainer = Color(colors.onErrorContainer),
        primaryContainer = Color(colors.primaryContainer),
        onPrimaryContainer = Color(colors.onPrimaryContainer),
        secondary = Color(colors.secondary),
        onSecondary = Color(colors.onSecondary),
        secondaryContainer = Color(colors.secondaryContainer),
        onSecondaryContainer = Color(colors.onSecondaryContainer),
        tertiaryContainer = Color(colors.tertiaryContainer),
        onTertiaryContainer = Color(colors.onTertiaryContainer),
        background = Color(colors.background),
        onBackground = Color(colors.onBackground),
        surface = Color(colors.surface),
        onSurface = Color(colors.onSurface),
        surfaceVariant = Color(colors.surfaceVariant),
        surfaceContainer = Color(colors.surfaceContainer),
        surfaceContainerHigh = Color(colors.surfaceContainerHigh),
        surfaceContainerHighest = Color(colors.surfaceContainerHighest),
        outline = Color(colors.outline),
        outlineVariant = Color(colors.outlineVariant),
        onSurfaceVariant = Color(colors.onSurfaceVariant),
    )
    return mapMd3RolesToMiuixColorsCommon(roles, dark)
}

@Stable
internal fun monetSystemColors(dark: Boolean): Colors = colorsFromSeed(seed = Color(0xFF6750A4), dark = dark)

/**
 * A controller for managing the current color scheme of the Miuix theme.
 *
 * @param colorSchemeMode The mode of the color scheme, which can be [ColorSchemeMode.System],
 *   [ColorSchemeMode.Light], [ColorSchemeMode.Dark], [ColorSchemeMode.MonetSystem],
 *   [ColorSchemeMode.MonetLight], or [ColorSchemeMode.MonetDark].
 * @param lightColors The color scheme to use when the light appearance is active. This is used when
 *   the [colorSchemeMode] is [ColorSchemeMode.Light] or when it is [ColorSchemeMode.System] and a
 *   light theme is selected.
 * @param darkColors The color scheme to use when the dark appearance is active. This is used when
 *   the [colorSchemeMode] is [ColorSchemeMode.Dark] or when it is [ColorSchemeMode.System] and a
 *   dark theme is selected.
 * @param keyColor The key color for generating dynamic color schemes. This is used when the [colorSchemeMode] is set to a Monet mode.
 * @param isDark Whether the system is in dark mode. This is used when the [colorSchemeMode] is
 *   set to a System or MonetSystem mode and the dark mode is not explicitly specified.
 */
@Stable
class ThemeController(
    colorSchemeMode: ColorSchemeMode = ColorSchemeMode.System,
    lightColors: Colors = lightColorScheme(),
    darkColors: Colors = darkColorScheme(),
    keyColor: Color? = null,
    isDark: Boolean? = null,
) {
    var colorSchemeMode: ColorSchemeMode by mutableStateOf(colorSchemeMode)
    val lightColors: Colors by mutableStateOf(lightColors)
    val darkColors: Colors by mutableStateOf(darkColors)
    var keyColor: Color? by mutableStateOf(keyColor)
    var isDark: Boolean? by mutableStateOf(isDark)

    @Composable
    fun currentColors(): Colors = when (colorSchemeMode) {
        ColorSchemeMode.System -> {
            val dark = isDark ?: isSystemInDarkTheme()
            remember(dark) { if (dark) darkColors else lightColors }
        }

        ColorSchemeMode.Light -> remember { lightColors }

        ColorSchemeMode.Dark -> remember { darkColors }

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

internal val LocalColorSchemeMode: ProvidableCompositionLocal<ColorSchemeMode?> = staticCompositionLocalOf { null }
