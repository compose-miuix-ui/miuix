// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.BlurDefaults

/** Default values for glass surfaces. */
object GlassDefaults {

    /** Default corner radius of a glass surface. */
    val CornerRadius: Dp = 24.dp

/** Default corner smoothing. 1 uses the continuous corner profile. */
    val Smoothing: Float = 1f

/**
     * Density, in pixels per dp, at which [GlassStyle] and [GlassShadow] values are authored.
     * Pixel values are scaled by `density / SourceDensity` to preserve physical size across screens.
     */
    val SourceDensity: Float = 3f

/** Default noise dithering coefficient for backdrop blur. 0 disables dithering. */
    val NoiseCoefficient: Float = BlurDefaults.NoiseCoefficient

/**
     * Distance, in dp, that the material extends beyond its silhouette before the final mask clips it.
     */
    val Overspill: Float = 3f

/** Default glass style. */
    @Stable
    val Style: GlassStyle = GlassStyles.CommonMediumRegularLowLight

/** Returns the default glass style for the selected theme.
     *
     * @param isDark Whether to return the dark-theme style.
     */
    @Stable
    fun style(isDark: Boolean): GlassStyle = GlassStyles.forTheme(
        isDark = isDark,
        light = GlassStyles.CommonMediumRegularLowLight,
        dark = GlassStyles.CommonMediumRegularDark,
    )
}
