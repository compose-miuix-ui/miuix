// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

/**
 * Applies foreground blur to the content of this composable.
 *
 * Instead of showing blurred content behind the composable (like [textureBlur]),
 * this modifier fills the content's shape (e.g., text glyphs) with blurred backdrop,
 * creating a frosted-glass effect on the foreground content itself.
 *
 * Internally, this draws the blurred backdrop first, then composites the content
 * using [BlendMode.DstIn][androidx.compose.ui.graphics.BlendMode.DstIn] so that
 * the blur is masked by the content's alpha channel: `blurredBackdrop * contentAlpha`.
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape The shape provider for the blur region clipping.
 * @param blurRadius The blur radius in pixels. Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param enabled Whether blur is active. When false, the modifier is a no-op.
 */
fun Modifier.foregroundBlur(
    backdrop: Backdrop,
    shape: Shape,
    blurRadius: Float = BlurDefaults.BlurRadius,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    enabled: Boolean = true,
): Modifier = foregroundEffect(
    backdrop = backdrop,
    shape = shape,
    blurRadius = blurRadius,
    noiseCoefficient = noiseCoefficient,
    colors = colors,
    enabled = enabled,
)

/**
 * Applies foreground blur with independent horizontal and vertical radii.
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape The shape provider for the blur region clipping.
 * @param blurRadiusX The horizontal blur radius in pixels. Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param blurRadiusY The vertical blur radius in pixels. Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param enabled Whether blur is active. When false, the modifier is a no-op.
 */
fun Modifier.foregroundBlur(
    backdrop: Backdrop,
    shape: Shape,
    blurRadiusX: Float,
    blurRadiusY: Float,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    enabled: Boolean = true,
): Modifier = foregroundEffect(
    backdrop = backdrop,
    shape = shape,
    blurRadiusX = blurRadiusX,
    blurRadiusY = blurRadiusY,
    noiseCoefficient = noiseCoefficient,
    colors = colors,
    enabled = enabled,
)
