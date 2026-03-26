// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import top.yukonga.miuix.kmp.blur.internal.BlurEffects
import top.yukonga.miuix.kmp.blur.internal.applyBlendColors
import top.yukonga.miuix.kmp.blur.internal.drawStandardBlendColors
import top.yukonga.miuix.kmp.blur.internal.noiseDither
import androidx.compose.ui.graphics.BlendMode as ComposeBlendMode

/**
 * Applies the complete foreground effect: backdrop blur masked by content alpha.
 *
 * This is the internal implementation for [foregroundBlur]. It uses the same
 * effect chain as [textureEffect] but composites the content with
 * [BlendMode.DstIn][androidx.compose.ui.graphics.BlendMode.DstIn] so that
 * only content-covered pixels show the blurred backdrop.
 */
fun Modifier.foregroundEffect(
    backdrop: Backdrop,
    shape: Shape,
    blurRadius: Float = BlurDefaults.BlurRadius,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    enabled: Boolean = true,
): Modifier = foregroundEffect(
    backdrop = backdrop,
    shape = shape,
    blurRadiusX = blurRadius,
    blurRadiusY = blurRadius,
    noiseCoefficient = noiseCoefficient,
    colors = colors,
    enabled = enabled,
)

/**
 * Applies the complete foreground effect with independent horizontal and vertical
 * blur radii: backdrop blur masked by content alpha (with all custom blend modes).
 */
fun Modifier.foregroundEffect(
    backdrop: Backdrop,
    shape: Shape,
    blurRadiusX: Float,
    blurRadiusY: Float,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    enabled: Boolean = true,
): Modifier {
    if (!enabled) return this

    val clampedX = blurRadiusX.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    val clampedY = blurRadiusY.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    val hasStandardBlend = colors.blendColors.any { !BlendMode.isCustomMode(it.mode) }

    return this.drawPlainBackdrop(
        backdrop = backdrop,
        shape = { shape },
        effects = {
            BlurEffects.applyBlur(this, clampedX, clampedY)
            noiseDither(noiseCoefficient)
            colorControls(colors.brightness, colors.contrast, colors.saturation)
            applyBlendColors(colors)
        },
        onDrawSurface = if (hasStandardBlend) {
            { drawStandardBlendColors(colors.blendColors) }
        } else {
            null
        },
        contentBlendMode = ComposeBlendMode.DstIn,
    )
}
