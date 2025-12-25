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
        hctColor,
        dark,
        0.0,
        ColorSpec.SpecVersion.SPEC_2025,
        DynamicScheme.Platform.PHONE
    )
    val colors = DynamicScheme(
        sourceColorHct = hctColor,
        variant = Variant.TONAL_SPOT,
        isDark = dark,
        contrastLevel = 0.0,
        platform = DynamicScheme.Platform.PHONE,
        specVersion = ColorSpec.SpecVersion.SPEC_2021,
        primaryPalette = tonalSpot.primaryPalette,
        secondaryPalette = tonalSpot.secondaryPalette,
        tertiaryPalette = tonalSpot.tertiaryPalette,
        neutralPalette = tonalSpot.neutralPalette,
        neutralVariantPalette = tonalSpot.neutralVariantPalette,
        errorPalette = tonalSpot.errorPalette
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
internal fun monetSystemColors(dark: Boolean): Colors {
    return colorsFromSeed(seed = Color(0xFF6750A4), dark = dark)
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
