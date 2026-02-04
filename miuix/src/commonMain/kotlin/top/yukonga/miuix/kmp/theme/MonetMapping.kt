// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.materialkolor.hct.Hct

/**
 * A data class representing the Monet color roles.
 */
internal data class MonetRoles(
    val primary: Color,
    val onPrimary: Color,
    val primaryFixed: Color,
    val onPrimaryFixed: Color,
    val error: Color,
    val onError: Color,
    val errorContainer: Color,
    val onErrorContainer: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val tertiaryContainer: Color,
    val onTertiaryContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val surfaceContainer: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainerHighest: Color,
    val outline: Color,
    val outlineVariant: Color,
    val onSurfaceVariant: Color,
)

/**
 * Creates a new color by adjusting the tone of the base color.
 *
 * @param base The base color to adjust.
 * @param tone The target tone value (0-100).
 * @return A new color with the adjusted tone.
 */
private fun adjustTone(base: Color, tone: Double): Color {
    val hct = Hct.fromInt(base.toArgb())
    val adjusted = Hct.from(hct.hue, hct.chroma, tone)
    return Color(adjusted.toInt())
}

/**
 * Blends two colors with a given ratio, maintaining full opacity.
 *
 * @param foreground The foreground color.
 * @param background The background color.
 * @param ratio The blend ratio (0.0 = background, 1.0 = foreground).
 * @return The blended opaque color.
 */
private fun blendColors(foreground: Color, background: Color, ratio: Float): Color {
    val r = foreground.red * ratio + background.red * (1f - ratio)
    val g = foreground.green * ratio + background.green * (1f - ratio)
    val b = foreground.blue * ratio + background.blue * (1f - ratio)
    return Color(r, g, b, 1f)
}

/** Maps Monet color roles to MIUIX color scheme.
 *
 * This function uses direct color calculations based on tone adjustments and color blending
 * to create a cohesive color scheme that follows Material Design 3 principles.
 *
 * @param roles The Monet color roles.
 * @param dark Whether the color scheme is for dark mode.
 * @return The mapped MIUIX [Colors].
 */
internal fun mapMd3RolesToMiuixColorsCommon(roles: MonetRoles, dark: Boolean): Colors {
    // Base colors from roles
    val baseSurface = roles.surface
    val baseSurfaceContainer = roles.surfaceContainer

    // For disabled states, blend colors directly instead of using alpha compositing
    // This produces cleaner, more predictable colors
    val disabledPrimary = blendColors(roles.primary, baseSurface, 0.38f)
    val disabledOnPrimary = blendColors(roles.onPrimary, disabledPrimary, 0.38f)

    val disabledPrimaryButton = blendColors(roles.primary, baseSurface, 0.38f)
    val disabledOnPrimaryButton = blendColors(roles.onPrimary, disabledPrimaryButton, 0.6f)

    val disabledPrimarySlider = blendColors(roles.primary, baseSurface, 0.38f)

    // Secondary colors with proper blending
    val secondaryBlended = blendColors(roles.secondary, baseSurface, 0.38f)
    val secondaryVariant = blendColors(roles.primary, baseSurface, 0.24f)
    val onSecondaryVariant = blendColors(roles.secondary, secondaryVariant, 1.0f)

    val disabledSecondary = blendColors(roles.secondary, baseSurface, 0.24f)
    val disabledOnSecondary = blendColors(roles.onSecondary, disabledSecondary, 0.42f)

    val disabledSecondaryVariant = blendColors(roles.secondary, baseSurface, 0.12f)
    val disabledOnSecondaryVariant = blendColors(roles.secondary, disabledSecondaryVariant, 0.24f)

    // Surface text colors with proper opacity simulation through blending
    val onSurfaceSecondary = blendColors(roles.onSurface, baseSurface, 0.8f)
    val onSurfaceVariantSummary = blendColors(roles.onSurface, baseSurfaceContainer, 0.6f)
    val onSurfaceVariantActions = blendColors(roles.onSurface, baseSurfaceContainer, 0.6f)
    val onSurfaceContainerVariant = blendColors(roles.onSurfaceVariant, baseSurfaceContainer, 0.6f)

    // Use tone adjustment for onSurfaceContainerHigh to maintain proper contrast
    val onSurfaceContainerHigh = if (dark) {
        adjustTone(roles.onSurface, 65.0)
    } else {
        adjustTone(roles.onSurface, 60.0)
    }

    // Use tone adjustment for disabled surface text
    val disabledOnSurface = if (dark) {
        adjustTone(roles.onSurface, 40.0)
    } else {
        adjustTone(roles.onSurface, 60.0)
    }

    return Colors(
        primary = roles.primary,
        onPrimary = roles.onPrimary,
        primaryVariant = roles.primaryFixed,
        onPrimaryVariant = roles.onPrimaryFixed,
        error = roles.error,
        onError = roles.onError,
        errorContainer = roles.errorContainer,
        onErrorContainer = roles.onErrorContainer,
        disabledPrimary = disabledPrimary,
        disabledOnPrimary = disabledOnPrimary,
        disabledPrimaryButton = disabledPrimaryButton,
        disabledOnPrimaryButton = disabledOnPrimaryButton,
        disabledPrimarySlider = disabledPrimarySlider,
        primaryContainer = roles.primaryContainer,
        onPrimaryContainer = roles.onPrimaryContainer,
        secondary = secondaryBlended,
        onSecondary = roles.onSecondary,
        secondaryVariant = secondaryVariant,
        onSecondaryVariant = onSecondaryVariant,
        disabledSecondary = disabledSecondary,
        disabledOnSecondary = disabledOnSecondary,
        disabledSecondaryVariant = disabledSecondaryVariant,
        disabledOnSecondaryVariant = disabledOnSecondaryVariant,
        secondaryContainer = roles.secondaryContainer,
        onSecondaryContainer = roles.onSecondaryContainer,
        secondaryContainerVariant = roles.secondaryContainer,
        onSecondaryContainerVariant = roles.onSecondaryContainer,
        tertiaryContainer = roles.tertiaryContainer,
        onTertiaryContainer = roles.onTertiaryContainer,
        tertiaryContainerVariant = roles.onTertiaryContainer,
        background = roles.background,
        onBackground = roles.onBackground,
        onBackgroundVariant = roles.primary,
        surface = roles.surface,
        onSurface = roles.onSurface,
        surfaceVariant = roles.surfaceVariant,
        onSurfaceSecondary = onSurfaceSecondary,
        onSurfaceVariantSummary = onSurfaceVariantSummary,
        onSurfaceVariantActions = onSurfaceVariantActions,
        disabledOnSurface = disabledOnSurface,
        surfaceContainer = roles.surfaceContainer,
        onSurfaceContainer = roles.onSurface,
        onSurfaceContainerVariant = onSurfaceContainerVariant,
        surfaceContainerHigh = roles.surfaceContainerHigh,
        onSurfaceContainerHigh = onSurfaceContainerHigh,
        surfaceContainerHighest = roles.surfaceContainerHighest,
        onSurfaceContainerHighest = roles.onSurface,
        outline = roles.outline,
        dividerLine = roles.outlineVariant,
        windowDimming = if (dark) Color.Black.copy(alpha = 0.6f) else Color.Black.copy(alpha = 0.3f),
        sliderKeyPoint = roles.onSecondaryContainer,
        sliderKeyPointForeground = roles.onPrimary,
        sliderBackground = secondaryVariant,
    )
}
