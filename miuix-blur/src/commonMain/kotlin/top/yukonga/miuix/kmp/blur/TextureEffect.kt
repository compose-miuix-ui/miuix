// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import top.yukonga.miuix.kmp.blur.highlight.Highlight
import androidx.compose.ui.graphics.BlendMode as ComposeBlendMode

/**
 * Applies background blur to the content behind this composable.
 *
 * Blend colors support both standard SkBlendMode (0-29, GPU hardware) and
 * custom modes (100-121, 200-203, runtime shader). See [BlurBlendMode].
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape The shape provider for the blur region clipping.
 * @param blurRadius The blur radius in dp. Internally converted to pixels using display density.
 *   Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param highlight Optional edge highlight painted on top of the content. `null` skips drawing.
 * @param contentBlendMode Optional [ComposeBlendMode] for compositing content over the blur.
 *   Use [ComposeBlendMode.DstIn] for foreground blur (content alpha masks the blur).
 *   null means content draws normally on top.
 * @param enabled Whether blur is active. When false, the effect is skipped and content draws normally.
 */
fun Modifier.textureBlur(
    backdrop: Backdrop,
    shape: Shape,
    blurRadius: Float = BlurDefaults.BlurRadius,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    highlight: Highlight? = null,
    contentBlendMode: ComposeBlendMode = ComposeBlendMode.SrcOver,
    enabled: Boolean = true,
): Modifier = textureEffect(
    backdrop = backdrop,
    shape = shape,
    blurRadius = blurRadius,
    noiseCoefficient = noiseCoefficient,
    colors = colors,
    highlight = highlight,
    contentBlendMode = contentBlendMode,
    enabled = enabled,
)

/**
 * Applies background blur with independent horizontal and vertical radii.
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape The shape provider for the blur region clipping.
 * @param blurRadiusX The horizontal blur radius in dp. Internally converted to pixels using display density.
 *   Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param blurRadiusY The vertical blur radius in dp. Internally converted to pixels using display density.
 *   Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param highlight Optional edge highlight painted on top of the content. `null` skips drawing.
 * @param contentBlendMode Optional [ComposeBlendMode] for compositing content over the blur.
 *   Use [ComposeBlendMode.DstIn] for foreground blur (content alpha masks the blur).
 *   null means content draws normally on top.
 * @param enabled Whether blur is active. When false, the effect is skipped and content draws normally.
 */
fun Modifier.textureBlur(
    backdrop: Backdrop,
    shape: Shape,
    blurRadiusX: Float,
    blurRadiusY: Float,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    highlight: Highlight? = null,
    contentBlendMode: ComposeBlendMode = ComposeBlendMode.SrcOver,
    enabled: Boolean = true,
): Modifier = textureEffect(
    backdrop = backdrop,
    shape = shape,
    blurRadiusX = blurRadiusX,
    blurRadiusY = blurRadiusY,
    noiseCoefficient = noiseCoefficient,
    colors = colors,
    highlight = highlight,
    contentBlendMode = contentBlendMode,
    enabled = enabled,
)

/**
 * Applies the complete texture effect: backdrop blur + color blending
 * (with all custom blend modes).
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape Shape provider for the blur region clipping.
 * @param blurRadius The blur radius in dp. Internally converted to pixels using display density.
 *   Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param highlight Optional edge highlight painted on top of the content. `null` skips drawing.
 * @param contentBlendMode Optional [ComposeBlendMode] for compositing content over the blur.
 *   Use [ComposeBlendMode.DstIn] for foreground blur (content alpha masks the blur).
 *   null means content draws normally on top.
 * @param enabled Whether the effect is active. When false, the effect is skipped and content draws normally.
 */
fun Modifier.textureEffect(
    backdrop: Backdrop,
    shape: Shape,
    blurRadius: Float = BlurDefaults.BlurRadius,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    highlight: Highlight? = null,
    contentBlendMode: ComposeBlendMode = ComposeBlendMode.SrcOver,
    enabled: Boolean = true,
): Modifier = textureEffect(
    backdrop = backdrop,
    shape = shape,
    blurRadiusX = blurRadius,
    blurRadiusY = blurRadius,
    noiseCoefficient = noiseCoefficient,
    colors = colors,
    highlight = highlight,
    contentBlendMode = contentBlendMode,
    enabled = enabled,
)

/**
 * Applies the complete texture effect with independent horizontal and vertical
 * blur radii: backdrop blur + color blending (with all custom blend modes).
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape Shape provider for the blur region clipping.
 * @param blurRadiusX The horizontal blur radius in dp. Internally converted to pixels using display density.
 *   Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param blurRadiusY The vertical blur radius in dp. Internally converted to pixels using display density.
 *   Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param highlight Optional edge highlight painted on top of the content. `null` skips drawing.
 * @param contentBlendMode Optional [ComposeBlendMode] for compositing content over the blur.
 *   Use [ComposeBlendMode.DstIn] for foreground blur (content alpha masks the blur).
 *   null means content draws normally on top.
 * @param enabled Whether the effect is active. When false, the effect is skipped and content draws normally.
 */
fun Modifier.textureEffect(
    backdrop: Backdrop,
    shape: Shape,
    blurRadiusX: Float,
    blurRadiusY: Float,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    highlight: Highlight? = null,
    contentBlendMode: ComposeBlendMode = ComposeBlendMode.SrcOver,
    enabled: Boolean = true,
): Modifier = this.drawBackdrop(
    backdrop = backdrop,
    shape = { shape },
    effects = {
        textureBlurEffect(
            blurRadiusX = blurRadiusX,
            blurRadiusY = blurRadiusY,
            noiseCoefficient = noiseCoefficient,
            colors = colors,
        )
    },
    highlight = if (highlight != null) {
        { highlight }
    } else {
        null
    },
    contentBlendMode = contentBlendMode,
    enabled = enabled,
)

/**
 * Applies a **progressive (gradient) backdrop blur**: the blur strength ramps from full to zero
 * along [gradient]'s direction — a genuine medium blur in the middle of the ramp, a pixel-sharp
 * full-resolution clear end. Ideal for navigation bars and edge fades. Same color / blend / noise /
 * highlight pipeline as [textureBlur], applied so the effects fade out with the blur.
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape The shape provider for the blur region clipping.
 * @param gradient Direction and band controlling where the blur is full vs zero. Defaults to
 *   [ProgressiveBlur.Top].
 * @param blurRadius The blur radius in dp at full strength. Internally converted to pixels using
 *   display density. Clamped to [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param highlight Optional edge highlight painted on top of the content. `null` skips drawing.
 * @param contentBlendMode Optional [ComposeBlendMode] for compositing content over the blur.
 * @param enabled Whether blur is active. When false, the effect is skipped and content draws normally.
 */
fun Modifier.progressiveTextureBlur(
    backdrop: Backdrop,
    shape: Shape,
    gradient: ProgressiveBlur = ProgressiveBlur.Top,
    blurRadius: Float = BlurDefaults.BlurRadius,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    highlight: Highlight? = null,
    contentBlendMode: ComposeBlendMode = ComposeBlendMode.SrcOver,
    enabled: Boolean = true,
): Modifier = progressiveTextureBlur(
    backdrop = backdrop,
    shape = shape,
    gradient = gradient,
    blurRadiusX = blurRadius,
    blurRadiusY = blurRadius,
    noiseCoefficient = noiseCoefficient,
    colors = colors,
    highlight = highlight,
    contentBlendMode = contentBlendMode,
    enabled = enabled,
)

/**
 * Applies a progressive (gradient) backdrop blur with independent horizontal and vertical radii.
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param shape The shape provider for the blur region clipping.
 * @param gradient Direction and band controlling where the blur is full vs zero.
 * @param blurRadiusX The horizontal blur radius in dp at full strength. Clamped to
 *   [0, [BlurDefaults.MaxBlurRadius]].
 * @param blurRadiusY The vertical blur radius in dp at full strength. Clamped to
 *   [0, [BlurDefaults.MaxBlurRadius]].
 * @param noiseCoefficient Noise dithering coefficient for anti-banding. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 * @param highlight Optional edge highlight painted on top of the content. `null` skips drawing.
 * @param contentBlendMode Optional [ComposeBlendMode] for compositing content over the blur.
 * @param enabled Whether blur is active. When false, the effect is skipped and content draws normally.
 */
fun Modifier.progressiveTextureBlur(
    backdrop: Backdrop,
    shape: Shape,
    gradient: ProgressiveBlur = ProgressiveBlur.Top,
    blurRadiusX: Float,
    blurRadiusY: Float,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
    highlight: Highlight? = null,
    contentBlendMode: ComposeBlendMode = ComposeBlendMode.SrcOver,
    enabled: Boolean = true,
): Modifier = this.drawBackdrop(
    backdrop = backdrop,
    shape = { shape },
    effects = {
        progressiveTextureBlurEffect(
            blurRadiusX = blurRadiusX,
            blurRadiusY = blurRadiusY,
            gradient = gradient,
            noiseCoefficient = noiseCoefficient,
            colors = colors,
        )
    },
    highlight = if (highlight != null) {
        { highlight }
    } else {
        null
    },
    contentBlendMode = contentBlendMode,
    progressiveGradient = gradient,
    enabled = enabled,
)
