// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import top.yukonga.miuix.kmp.blur.internal.BlurEffects
import top.yukonga.miuix.kmp.blur.internal.applyBlendColors
import top.yukonga.miuix.kmp.blur.internal.noiseDither
import androidx.compose.ui.graphics.BlendMode as ComposeBlendMode

/**
 * Applies the complete texture effect: backdrop blur + color blending
 * (with all custom blend modes).
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape Shape provider for the blur region clipping.
 * @param blurRadius The blur radius in pixels. Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param contentBlendMode Optional [ComposeBlendMode] for compositing content over the blur.
 *   Use [ComposeBlendMode.DstIn] for foreground blur (content alpha masks the blur).
 *   null means content draws normally on top.
 * @param enabled Whether the effect is active. When false, the modifier is a no-op.
 */
fun Modifier.textureEffect(
    backdrop: Backdrop,
    shape: Shape,
    blurRadius: Float = BlurDefaults.BlurRadius,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    contentBlendMode: ComposeBlendMode? = null,
    enabled: Boolean = true,
): Modifier = textureEffect(
    backdrop = backdrop,
    shape = shape,
    blurRadiusX = blurRadius,
    blurRadiusY = blurRadius,
    noiseCoefficient = noiseCoefficient,
    colors = colors,
    contentBlendMode = contentBlendMode,
    enabled = enabled,
)

/**
 * Applies the complete texture effect with independent horizontal and vertical
 * blur radii: backdrop blur + color blending (with all custom blend modes).
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape Shape provider for the blur region clipping.
 * @param blurRadiusX The horizontal blur radius in pixels. Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param blurRadiusY The vertical blur radius in pixels. Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param contentBlendMode Optional [ComposeBlendMode] for compositing content over the blur.
 *   Use [ComposeBlendMode.DstIn] for foreground blur (content alpha masks the blur).
 *   null means content draws normally on top.
 * @param enabled Whether the effect is active. When false, the modifier is a no-op.
 */
fun Modifier.textureEffect(
    backdrop: Backdrop,
    shape: Shape,
    blurRadiusX: Float,
    blurRadiusY: Float,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    contentBlendMode: ComposeBlendMode? = null,
    enabled: Boolean = true,
): Modifier {
    if (!enabled) return this

    val clampedX = blurRadiusX.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    val clampedY = blurRadiusY.coerceIn(0f, BlurDefaults.MaxBlurRadius)

    return this.drawPlainBackdrop(
        backdrop = backdrop,
        shape = { shape },
        effects = {
            BlurEffects.applyBlur(this, clampedX, clampedY)
            noiseDither(noiseCoefficient)
            colorControls(colors.brightness, colors.contrast, colors.saturation)
            applyBlendColors(colors)
        },
        contentBlendMode = contentBlendMode,
    )
}
