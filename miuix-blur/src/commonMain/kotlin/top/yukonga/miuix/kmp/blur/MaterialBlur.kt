// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import top.yukonga.miuix.kmp.blur.internal.BlurEffects
import top.yukonga.miuix.kmp.blur.internal.applyBlendColors
import top.yukonga.miuix.kmp.blur.internal.drawStandardBlendColors
import top.yukonga.miuix.kmp.blur.internal.noiseDither

/**
 * Applies MIUI-style background blur to the content behind this composable.
 *
 * Blend colors support both standard SkBlendMode (0-29, GPU hardware) and
 * Xiaomi custom modes (100-121, 200-203, runtime shader). See [BlendMode].
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape Shape provider for the blur region clipping.
 * @param blurRadius The blur radius in pixels. Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param enabled Whether blur is active. When false, the modifier is a no-op.
 */
fun Modifier.materialBlur(
    backdrop: Backdrop,
    shape: () -> Shape,
    blurRadius: Float = BlurDefaults.BlurRadius,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    enabled: Boolean = true,
): Modifier {
    if (!enabled) return this

    val clampedRadius = blurRadius.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    val hasStandardBlend = colors.blendColors.any { !BlendMode.isCustomMode(it.mode) }

    return this.drawPlainBackdrop(
        backdrop = backdrop,
        shape = shape,
        effects = {
            BlurEffects.applyBlur(this, clampedRadius)
            noiseDither(noiseCoefficient)
            applyBlendColors(colors)
        },
        onDrawSurface = if (hasStandardBlend) {
            { drawStandardBlendColors(colors.blendColors) }
        } else {
            null
        },
    )
}
