// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import androidx.compose.ui.graphics.Color

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

/** Composites a foreground color over a background color using alpha blending.
 *
 * @param fg The foreground color.
 * @param bg The background color.
 * @return The resulting color after compositing.
 */
private fun compositeOver(fg: Color, bg: Color): Color {
    val fa = fg.alpha
    val ba = bg.alpha
    val outA = fa + ba * (1f - fa)
    if (outA == 0f) return Color(0f, 0f, 0f, 0f)
    val r = (fg.red * fa + bg.red * ba * (1f - fa)) / outA
    val g = (fg.green * fa + bg.green * ba * (1f - fa)) / outA
    val b = (fg.blue * fa + bg.blue * ba * (1f - fa)) / outA
    return Color(r, g, b, outA)
}

/** Composites a foreground color over a background color and returns an opaque color.
 *
 * @param fg The foreground color.
 * @param bg The background color.
 * @return The resulting opaque color after compositing.
 */
private fun opaqueOver(fg: Color, bg: Color): Color {
    val c = compositeOver(fg, bg)
    return Color(c.red, c.green, c.blue, 1f)
}

/** Ensures that the foreground color is opaque over the background color.
 *
 * @param fg The foreground color.
 * @param bg The background color.
 * @return The resulting opaque color.
 */
private fun ensureOpaqueOver(fg: Color, bg: Color): Color = if (fg.alpha >= 1f) fg else opaqueOver(fg, bg)

/** Maps Monet color roles to MIUIX color scheme.
 *
 * This function flattens colors with alpha transparency over appropriate background colors
 * to ensure all colors in the resulting [Colors] are opaque.
 *
 * @param roles The Monet color roles.
 * @param dark Whether the color scheme is for dark mode.
 * @return The mapped MIUIX [Colors].
 */
internal fun mapMd3RolesToMiuixColorsCommon(roles: MonetRoles, dark: Boolean): Colors {
    val baseSurface = roles.surface
    val baseSurfaceContainer = roles.surfaceContainer
    val baseSurfaceContainerHigh = roles.surfaceContainerHigh

    // Flatten secondaryContainer/onSecondaryContainer on surface
    val secondaryContainerOpaque = ensureOpaqueOver(roles.secondaryContainer, baseSurface)
    val onSecondaryContainerOpaque = ensureOpaqueOver(roles.onSecondaryContainer, secondaryContainerOpaque)

    // Flatten secondaryVariant/onSecondaryVariant on surface
    val secondaryVariantSource = roles.primary.copy(alpha = 0.24f)
    val secondaryVariantOpaque = ensureOpaqueOver(secondaryVariantSource, baseSurface)
    val onSecondaryVariantOpaque = ensureOpaqueOver(roles.secondary, secondaryVariantOpaque)

    // Flatten all other alpha usages to opaque
    val disabledPrimaryOpaque = ensureOpaqueOver(roles.primary.copy(alpha = 0.38f), baseSurface)
    val disabledOnPrimaryOpaque = ensureOpaqueOver(roles.onPrimary.copy(alpha = 0.38f), disabledPrimaryOpaque)

    val disabledPrimaryButtonOpaque = ensureOpaqueOver(roles.primary.copy(alpha = 0.38f), baseSurface)
    val disabledOnPrimaryButtonOpaque = ensureOpaqueOver(roles.onPrimary.copy(alpha = 0.6f), disabledPrimaryButtonOpaque)

    val disabledPrimarySliderOpaque = ensureOpaqueOver(roles.primary.copy(alpha = 0.38f), baseSurface)

    val secondaryOpaque = ensureOpaqueOver(roles.secondary.copy(alpha = 0.38f), baseSurface)

    val disabledSecondaryOpaque = ensureOpaqueOver(roles.secondary.copy(alpha = 0.24f), baseSurface)
    val disabledOnSecondaryOpaque = ensureOpaqueOver(roles.onSecondary.copy(alpha = 0.42f), disabledSecondaryOpaque)

    val disabledSecondaryVariantOpaque = ensureOpaqueOver(roles.secondary.copy(alpha = 0.12f), baseSurface)
    val disabledOnSecondaryVariantOpaque = ensureOpaqueOver(roles.secondary.copy(alpha = 0.24f), disabledSecondaryVariantOpaque)

    val onSurfaceSecondaryOpaque = ensureOpaqueOver(roles.onSurface.copy(alpha = 0.8f), baseSurface)
    val onSurfaceVariantSummaryOpaque = ensureOpaqueOver(roles.onSurface.copy(alpha = 0.6f), baseSurfaceContainer)
    val onSurfaceVariantActionsOpaque = ensureOpaqueOver(roles.onSurface.copy(alpha = 0.6f), baseSurfaceContainer)

    val onSurfaceContainerVariantOpaque = ensureOpaqueOver(roles.onSurfaceVariant.copy(alpha = 0.6f), baseSurfaceContainer)
    val onSurfaceContainerHighOpaque = ensureOpaqueOver(roles.onSurface.copy(alpha = 0.8f), baseSurfaceContainerHigh)

    return Colors(
        primary = roles.primary,
        onPrimary = roles.onPrimary,
        primaryVariant = roles.primaryFixed,
        onPrimaryVariant = roles.onPrimaryFixed,
        error = roles.error,
        onError = roles.onError,
        errorContainer = roles.errorContainer,
        onErrorContainer = roles.onErrorContainer,
        disabledPrimary = disabledPrimaryOpaque,
        disabledOnPrimary = disabledOnPrimaryOpaque,
        disabledPrimaryButton = disabledPrimaryButtonOpaque,
        disabledOnPrimaryButton = disabledOnPrimaryButtonOpaque,
        disabledPrimarySlider = disabledPrimarySliderOpaque,
        primaryContainer = roles.primaryContainer,
        onPrimaryContainer = roles.onPrimaryContainer,
        secondary = secondaryOpaque,
        onSecondary = roles.onSecondary,
        secondaryVariant = secondaryVariantOpaque,
        onSecondaryVariant = onSecondaryVariantOpaque,
        disabledSecondary = disabledSecondaryOpaque,
        disabledOnSecondary = disabledOnSecondaryOpaque,
        disabledSecondaryVariant = disabledSecondaryVariantOpaque,
        disabledOnSecondaryVariant = disabledOnSecondaryVariantOpaque,
        secondaryContainer = secondaryContainerOpaque,
        onSecondaryContainer = onSecondaryContainerOpaque,
        secondaryContainerVariant = secondaryContainerOpaque,
        onSecondaryContainerVariant = onSecondaryContainerOpaque,
        tertiaryContainer = roles.tertiaryContainer,
        onTertiaryContainer = roles.onTertiaryContainer,
        tertiaryContainerVariant = roles.onTertiaryContainer,
        background = roles.background,
        onBackground = roles.onBackground,
        onBackgroundVariant = roles.primary,
        surface = roles.surface,
        onSurface = roles.onSurface,
        surfaceVariant = roles.surfaceVariant,
        onSurfaceSecondary = onSurfaceSecondaryOpaque,
        onSurfaceVariantSummary = onSurfaceVariantSummaryOpaque,
        onSurfaceVariantActions = onSurfaceVariantActionsOpaque,
        disabledOnSurface = roles.onSurface,
        surfaceContainer = roles.surfaceContainer,
        onSurfaceContainer = roles.onSurface,
        onSurfaceContainerVariant = onSurfaceContainerVariantOpaque,
        surfaceContainerHigh = roles.surfaceContainerHigh,
        onSurfaceContainerHigh = onSurfaceContainerHighOpaque,
        surfaceContainerHighest = roles.surfaceContainerHighest,
        onSurfaceContainerHighest = roles.onSurface,
        outline = roles.outline,
        dividerLine = roles.outlineVariant,
        windowDimming = if (dark) Color.Black.copy(alpha = 0.6f) else Color.Black.copy(alpha = 0.3f),
        sliderKeyPoint = onSecondaryContainerOpaque,
        sliderKeyPointForeground = roles.onPrimary,
        sliderBackground = secondaryVariantOpaque,
    )
}
