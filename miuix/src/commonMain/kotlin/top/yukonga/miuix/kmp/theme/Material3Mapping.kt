// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0
package top.yukonga.miuix.kmp.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

internal fun mapMd3ToMiuixColorsCommon(cs: ColorScheme, dark: Boolean): Colors {
    return Colors(
        primary = cs.primary,
        onPrimary = cs.onPrimary,
        primaryVariant = cs.primaryFixed,
        onPrimaryVariant = cs.onPrimaryFixed,
        error = cs.error,
        onError = cs.onError,
        errorContainer = cs.errorContainer,
        onErrorContainer = cs.onErrorContainer,
        disabledPrimary = cs.inversePrimary,
        disabledOnPrimary = cs.onPrimary.copy(alpha = 0.38f),
        disabledPrimaryButton = cs.primary.copy(alpha = 0.4f),
        disabledOnPrimaryButton = cs.onPrimary.copy(alpha = 0.6f),
        disabledPrimarySlider = cs.inversePrimary,
        primaryContainer = cs.primaryContainer,
        onPrimaryContainer = cs.onPrimaryContainer,
        secondary = cs.secondary.copy(alpha = 0.4f),
        onSecondary = cs.onSecondary,
        secondaryVariant = cs.secondaryContainer,
        onSecondaryVariant = cs.onSecondaryFixed,
        disabledSecondary = cs.secondary.copy(alpha = 0.38f),
        disabledOnSecondary = cs.onSecondary.copy(alpha = 0.4f),
        disabledSecondaryVariant = cs.secondary.copy(alpha = 0.38f),
        disabledOnSecondaryVariant = cs.secondary.copy(alpha = 0.4f),
        secondaryContainer = cs.secondaryContainer,
        onSecondaryContainer = cs.onSecondaryContainer,
        secondaryContainerVariant = cs.secondaryContainer,
        onSecondaryContainerVariant = cs.onSecondaryContainer,
        tertiaryContainer = cs.primaryContainer,
        onTertiaryContainer = cs.onPrimaryContainer,
        tertiaryContainerVariant = cs.onSurfaceVariant.copy(alpha = 0.3f),
        background = cs.background,
        onBackground = cs.onBackground,
        onBackgroundVariant = cs.onBackground.copy(alpha = 0.7f),
        surface = cs.surface,
        onSurface = cs.onSurface,
        surfaceVariant = cs.surfaceVariant,
        onSurfaceSecondary = cs.onSurface.copy(alpha = 0.8f),
        onSurfaceVariantSummary = cs.onSurface.copy(alpha = 0.6f),
        onSurfaceVariantActions = cs.onSurface.copy(alpha = 0.6f),
        disabledOnSurface = cs.onSurface,
        surfaceContainer = cs.surfaceContainer,
        onSurfaceContainer = cs.onSurface,
        onSurfaceContainerVariant = cs.onSurfaceVariant.copy(alpha = 0.6f),
        surfaceContainerHigh = cs.surfaceVariant,
        onSurfaceContainerHigh = cs.onSurface.copy(alpha = 0.8f),
        surfaceContainerHighest = if (dark) Color(0xFF2D2D2D) else Color(0xFFE8E8E8),
        onSurfaceContainerHighest = cs.onSurface,
        outline = cs.outline,
        dividerLine = cs.outlineVariant,
        windowDimming = if (dark) Color.Black.copy(alpha = 0.6f) else Color.Black.copy(alpha = 0.3f),
        sliderKeyPoint = cs.onSecondaryContainer,
        sliderKeyPointForeground = cs.onPrimary,
    )
}
