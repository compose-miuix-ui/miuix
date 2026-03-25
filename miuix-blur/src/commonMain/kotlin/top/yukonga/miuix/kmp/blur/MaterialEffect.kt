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
 * Applies the complete MIUI HyperMaterial effect: backdrop blur + color blending
 * (with all Xiaomi custom blend modes).
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape Shape provider for the blur region clipping.
 * @param blurRadius The blur radius in pixels. Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param enabled Whether the effect is active. When false, the modifier is a no-op.
 */
fun Modifier.materialEffect(
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
