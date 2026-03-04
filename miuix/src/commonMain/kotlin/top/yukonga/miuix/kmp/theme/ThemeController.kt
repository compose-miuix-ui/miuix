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
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.materialkolor.dynamiccolor.ColorSpec
import com.materialkolor.hct.Hct
import com.materialkolor.scheme.DynamicScheme
import com.materialkolor.scheme.SchemeContent
import com.materialkolor.scheme.SchemeExpressive
import com.materialkolor.scheme.SchemeFidelity
import com.materialkolor.scheme.SchemeFruitSalad
import com.materialkolor.scheme.SchemeMonochrome
import com.materialkolor.scheme.SchemeNeutral
import com.materialkolor.scheme.SchemeRainbow
import com.materialkolor.scheme.SchemeTonalSpot
import com.materialkolor.scheme.SchemeVibrant

@Stable
enum class ThemeColorSpec {
    Spec2021,
    Spec2025,
}

@Stable
enum class ThemePaletteStyle {
    TonalSpot,
    Neutral,
    Vibrant,
    Expressive,
    Rainbow,
    FruitSalad,
    Monochrome,
    Fidelity,
    Content,
}

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
internal fun colorsFromSeed(
    seed: Color,
    colorSpec: ThemeColorSpec,
    paletteStyle: ThemePaletteStyle,
    dark: Boolean,
): Colors {
    // Check if the selected style supports SPEC_2025
    val isSpec2025Supported = paletteStyle in listOf(
        ThemePaletteStyle.TonalSpot,
        ThemePaletteStyle.Neutral,
        ThemePaletteStyle.Vibrant,
        ThemePaletteStyle.Expressive,
    )

    // Gracefully downgrade to SPEC_2021 if the style doesn't support SPEC_2025
    val internalSpec = if (colorSpec == ThemeColorSpec.Spec2025 && isSpec2025Supported) {
        ColorSpec.SpecVersion.SPEC_2025
    } else {
        ColorSpec.SpecVersion.SPEC_2021
    }

    val hctColor = Hct.fromInt(seed.toArgb())
    val scheme: DynamicScheme = when (paletteStyle) {
        ThemePaletteStyle.TonalSpot -> SchemeTonalSpot(sourceColorHct = hctColor, isDark = dark, contrastLevel = 0.0, specVersion = internalSpec, platform = DynamicScheme.Platform.PHONE)

        ThemePaletteStyle.Neutral -> SchemeNeutral(
            sourceColorHct = hctColor,
            isDark = dark,
            contrastLevel = 0.0,
            specVersion = internalSpec,
            platform = DynamicScheme.Platform.PHONE,
        )

        ThemePaletteStyle.Vibrant -> SchemeVibrant(
            sourceColorHct = hctColor,
            isDark = dark,
            contrastLevel = 0.0,
            specVersion = internalSpec,
            platform = DynamicScheme.Platform.PHONE,
        )

        ThemePaletteStyle.Expressive -> SchemeExpressive(
            sourceColorHct = hctColor,
            isDark = dark,
            contrastLevel = 0.0,
            specVersion = internalSpec,
            platform = DynamicScheme.Platform.PHONE,
        )

        ThemePaletteStyle.Rainbow -> SchemeRainbow(
            sourceColorHct = hctColor,
            isDark = dark,
            contrastLevel = 0.0,
            specVersion = internalSpec,
            platform = DynamicScheme.Platform.PHONE,
        )

        ThemePaletteStyle.FruitSalad -> SchemeFruitSalad(
            sourceColorHct = hctColor,
            isDark = dark,
            contrastLevel = 0.0,
            specVersion = internalSpec,
            platform = DynamicScheme.Platform.PHONE,
        )

        ThemePaletteStyle.Monochrome -> SchemeMonochrome(
            sourceColorHct = hctColor,
            isDark = dark,
            contrastLevel = 0.0,
            specVersion = internalSpec,
            platform = DynamicScheme.Platform.PHONE,
        )

        ThemePaletteStyle.Fidelity -> SchemeFidelity(
            sourceColorHct = hctColor,
            isDark = dark,
            contrastLevel = 0.0,
            specVersion = internalSpec,
            platform = DynamicScheme.Platform.PHONE,
        )

        ThemePaletteStyle.Content -> SchemeContent(
            sourceColorHct = hctColor,
            isDark = dark,
            contrastLevel = 0.0,
            specVersion = internalSpec,
            platform = DynamicScheme.Platform.PHONE,
        )
    }
    val roles = MonetRoles(
        primary = Color(scheme.primary),
        onPrimary = Color(scheme.onPrimary),
        primaryFixed = Color(scheme.primaryFixed),
        onPrimaryFixed = Color(scheme.onPrimaryFixed),
        error = Color(scheme.error),
        onError = Color(scheme.onError),
        errorContainer = Color(scheme.errorContainer),
        onErrorContainer = Color(scheme.onErrorContainer),
        primaryContainer = Color(scheme.primaryContainer),
        onPrimaryContainer = Color(scheme.onPrimaryContainer),
        secondary = Color(scheme.secondary),
        onSecondary = Color(scheme.onSecondary),
        secondaryContainer = Color(scheme.secondaryContainer),
        onSecondaryContainer = Color(scheme.onSecondaryContainer),
        tertiaryContainer = Color(scheme.tertiaryContainer),
        onTertiaryContainer = Color(scheme.onTertiaryContainer),
        background = Color(scheme.background),
        onBackground = Color(scheme.onBackground),
        surface = Color(scheme.surface),
        onSurface = Color(scheme.onSurface),
        surfaceVariant = Color(scheme.surfaceVariant),
        surfaceContainer = Color(scheme.surfaceContainer),
        surfaceContainerHigh = Color(scheme.surfaceContainerHigh),
        surfaceContainerHighest = Color(scheme.surfaceContainerHighest),
        outline = Color(scheme.outline),
        outlineVariant = Color(scheme.outlineVariant),
        onSurfaceVariant = Color(scheme.onSurfaceVariant),
    )
    return mapMd3RolesToMiuixColorsCommon(roles, dark)
}

@Stable
internal fun monetSystemColors(dark: Boolean): Colors = colorsFromSeed(seed = Color(0xFF6750A4), colorSpec = ThemeColorSpec.Spec2021, paletteStyle = ThemePaletteStyle.TonalSpot, dark = dark)

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
 * @param keyColor The key color for generating dynamic color schemes. This is used when the
 *   [colorSchemeMode] is set to a Monet mode.
 * @param colorSpec The color specification version to use when generating dynamic color schemes. This
 *  is used when the [colorSchemeMode] is set to a Monet mode.
 * @param paletteStyle The palette style to use when generating dynamic color schemes.
 * @param isDark Whether the system is in dark mode. This is used when the [colorSchemeMode] is
 *   set to a System or MonetSystem mode and the dark mode is not explicitly specified.
 */
@Stable
class ThemeController(
    colorSchemeMode: ColorSchemeMode = ColorSchemeMode.System,
    lightColors: Colors = lightColorScheme(),
    darkColors: Colors = darkColorScheme(),
    keyColor: Color? = null,
    colorSpec: ThemeColorSpec = ThemeColorSpec.Spec2021,
    paletteStyle: ThemePaletteStyle = ThemePaletteStyle.TonalSpot,
    isDark: Boolean? = null,
) {
    val colorSchemeMode: ColorSchemeMode by mutableStateOf(colorSchemeMode)
    val lightColors: Colors by mutableStateOf(lightColors)
    val darkColors: Colors by mutableStateOf(darkColors)
    val keyColor: Color? by mutableStateOf(keyColor)
    val colorSpec: ThemeColorSpec by mutableStateOf(colorSpec)
    val paletteStyle: ThemePaletteStyle by mutableStateOf(paletteStyle)
    val isDark: Boolean? by mutableStateOf(isDark)

    @Composable
    fun currentColors(): Colors = when (colorSchemeMode) {
        ColorSchemeMode.System -> {
            val dark = isDark ?: isSystemInDarkTheme()
            if (dark) darkColors else lightColors
        }

        ColorSchemeMode.Light -> lightColors

        ColorSchemeMode.Dark -> darkColors

        ColorSchemeMode.MonetSystem -> {
            val dark = isDark ?: isSystemInDarkTheme()
            keyColor?.let {
                remember(keyColor, dark, colorSpec, paletteStyle) { colorsFromSeed(seed = it, colorSpec = colorSpec, paletteStyle = paletteStyle, dark = dark) }
            } ?: platformDynamicColors(dark = dark)
        }

        ColorSchemeMode.MonetLight -> {
            keyColor?.let {
                remember(keyColor, colorSpec, paletteStyle) { colorsFromSeed(seed = it, colorSpec = colorSpec, paletteStyle = paletteStyle, dark = false) }
            } ?: platformDynamicColors(dark = false)
        }

        ColorSchemeMode.MonetDark -> {
            keyColor?.let {
                remember(keyColor, colorSpec, paletteStyle) { colorsFromSeed(seed = it, colorSpec = colorSpec, paletteStyle = paletteStyle, dark = true) }
            } ?: platformDynamicColors(dark = true)
        }
    }
}

internal val LocalColorSchemeMode: ProvidableCompositionLocal<ColorSchemeMode?> = staticCompositionLocalOf { null }
