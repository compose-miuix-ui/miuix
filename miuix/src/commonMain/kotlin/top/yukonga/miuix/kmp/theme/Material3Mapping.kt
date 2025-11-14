// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0
package top.yukonga.miuix.kmp.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun material3StaticColors(dark: Boolean): Colors {
    val cs: ColorScheme = if (dark) darkColorScheme() else lightColorScheme()
    return mapMd3ToMiuixColorsCommon(cs, dark)
}

internal fun mapMd3ToMiuixColorsCommon(cs: ColorScheme, dark: Boolean): Colors {
    val surface = cs.surfaceContainer
    val onSurfaceSecondary = cs.onSurface.copy(alpha = 0.8f)
    val onSurfaceVariantSummary = cs.onSurface.copy(alpha = 0.6f)
    val onSurfaceVariantActions = cs.onSurface.copy(alpha = 0.4f)
    val disabledOnSurface = cs.onSurface
    val dividerLine = cs.outlineVariant

    val surfaceContainer = cs.surface
    val surfaceContainerHigh = cs.surfaceVariant
    val surfaceContainerHighest = if (dark) Color(0xFF2D2D2D) else Color(0xFFE8E8E8)

    val onSurfaceContainer = cs.onSurface
    val onSurfaceContainerHigh = cs.onSurface.copy(alpha = 0.8f)
    val onSurfaceContainerHighest = cs.onSurface

    val windowDimming = if (dark) Color.Black.copy(alpha = 0.6f) else Color.Black.copy(alpha = 0.3f)

    return Colors(
        primary = cs.primary,
        onPrimary = cs.onPrimary,
        primaryVariant = cs.primary,
        onPrimaryVariant = cs.onPrimaryContainer,
        error = cs.error,
        onError = cs.onError,
        errorContainer = cs.errorContainer,
        onErrorContainer = cs.onErrorContainer,
        disabledPrimary = cs.primary.copy(alpha = 0.4f),
        disabledOnPrimary = cs.onPrimary.copy(alpha = 0.6f),
        disabledPrimaryButton = cs.primary.copy(alpha = 0.4f),
        disabledOnPrimaryButton = cs.onPrimary.copy(alpha = 0.6f),
        disabledPrimarySlider = cs.primary.copy(alpha = 0.5f),
        primaryContainer = cs.primaryContainer,
        onPrimaryContainer = cs.onPrimaryContainer,
        secondary = cs.secondary.copy(alpha = 0.4f),
        onSecondary = cs.onSecondary,
        secondaryVariant = cs.secondary.copy(alpha = 0.6f),
        onSecondaryVariant = cs.onSecondary,
        disabledSecondary = cs.secondary.copy(alpha = 0.4f),
        disabledOnSecondary = cs.onSecondary.copy(alpha = 0.6f),
        disabledSecondaryVariant = cs.secondary.copy(alpha = 0.4f),
        disabledOnSecondaryVariant = cs.secondary.copy(alpha = 0.6f),
        secondaryContainer = cs.secondaryContainer,
        onSecondaryContainer = cs.onSecondaryContainer,
        secondaryContainerVariant = cs.secondaryContainer,
        onSecondaryContainerVariant = cs.onSecondaryContainer,
        tertiaryContainer = cs.tertiaryContainer,
        onTertiaryContainer = cs.onTertiaryContainer,
        tertiaryContainerVariant = cs.onSurfaceVariant.copy(alpha = 0.3f),
        background = cs.background,
        onBackground = cs.onBackground,
        onBackgroundVariant = cs.onBackground.copy(alpha = 0.7f),
        surface = surface,
        onSurface = cs.onSurface,
        surfaceVariant = cs.surfaceVariant,
        onSurfaceSecondary = onSurfaceSecondary,
        onSurfaceVariantSummary = onSurfaceVariantSummary,
        onSurfaceVariantActions = onSurfaceVariantActions,
        disabledOnSurface = disabledOnSurface,
        outline = cs.outline,
        dividerLine = dividerLine,
        surfaceContainer = surfaceContainer,
        onSurfaceContainer = onSurfaceContainer,
        onSurfaceContainerVariant = cs.onSurface.copy(alpha = 0.6f),
        surfaceContainerHigh = surfaceContainerHigh,
        onSurfaceContainerHigh = onSurfaceContainerHigh,
        surfaceContainerHighest = surfaceContainerHighest,
        onSurfaceContainerHighest = onSurfaceContainerHighest,
        windowDimming = windowDimming,
        sliderKeyPoint = cs.onSurfaceVariant.copy(alpha = 0.3f),
        sliderKeyPointForeground = cs.primaryFixedDim,
    )
}
