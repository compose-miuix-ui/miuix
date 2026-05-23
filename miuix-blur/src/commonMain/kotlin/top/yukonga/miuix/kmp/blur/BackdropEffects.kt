// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import top.yukonga.miuix.kmp.blur.internal.BLEND_MODE_SHADER_EXTENDED
import top.yukonga.miuix.kmp.blur.internal.BLEND_MODE_SHADER_STANDARD
import top.yukonga.miuix.kmp.blur.internal.BLUR_KERNEL_REACH
import top.yukonga.miuix.kmp.blur.internal.BLUR_RADIUS_TO_SIGMA
import top.yukonga.miuix.kmp.blur.internal.chain
import top.yukonga.miuix.kmp.blur.internal.computeDownScaleParams
import top.yukonga.miuix.kmp.blur.internal.createBlurEffect

/** Maximum number of blend layers supported by [blendColors]. Extra entries are dropped. */
internal const val MAX_BLEND_LAYERS = 8

/**
 * Chains a separable Blur into the scope's [BackdropEffectScope.renderEffect],
 * adjusts [BackdropEffectScope.padding] to cover the kernel reach, and updates
 * [BackdropEffectScope.downscaleFactor]. Non-positive radii skip that axis.
 *
 * Typical use:
 * ```
 * Modifier.drawBackdrop(backdrop, shape = { shape }, effects = {
 *     blur(20f * density)
 * })
 * ```
 *
 * @param radiusX Horizontal blur radius in pixels.
 * @param radiusY Vertical blur radius in pixels. Defaults to [radiusX] for isotropic blur.
 */
fun BackdropEffectScope.blur(radiusX: Float, radiusY: Float = radiusX) {
    // Pre-compute downscale factor to set padding before creating the effect.
    val sigmaMax = maxOf(radiusX, radiusY) * BLUR_RADIUS_TO_SIGMA
    val sf = computeDownScaleParams(sigmaMax).downScale

    // Padding covers the blur kernel's maximum sampling reach in original
    // pixel space. Keeps recording dimensions stable across radius changes
    // within the same downscale level.
    val kernelPadding = (BLUR_KERNEL_REACH * sf).toFloat()
    if (kernelPadding > padding) {
        padding = kernelPadding
    }

    val paddedSize = Size(size.width + padding * 2f, size.height + padding * 2f)
    val result = createBlurEffect(radiusX, radiusY, paddedSize, impl) ?: return

    downscaleFactor = result.downscaleFactor
    renderEffect = renderEffect?.chain(result.renderEffect) ?: result.renderEffect
}

/**
 * Registers a noise dither pass with the given [coefficient]. Non-positive values are ignored.
 * Noise is applied at full resolution after upscaling so each screen pixel gets independent
 * dithering, which prevents banding visible at low blur radii.
 */
fun BackdropEffectScope.noiseDither(coefficient: Float) {
    if (coefficient <= 0f) return
    noiseCoefficient = coefficient
}

/**
 * Chains all blend color layers from [colors] as a single runtime shader pass. Up to
 * [MAX_BLEND_LAYERS] entries are honored. Brightness and saturation in [colors] are
 * folded into the blend shader's uniforms (separate from any [colorControls] you may
 * have already chained).
 */
fun BackdropEffectScope.blendColors(colors: BlurColors) {
    if (colors.blendColors.isEmpty()) return

    val layerList = colors.blendColors
    val layerCount = minOf(layerList.size, MAX_BLEND_LAYERS)
    val scope = impl
    val modes = scope.blendModesBuffer
    val colorData = scope.blendColorsBuffer

    val needsExtended = (0 until layerCount).any { layerList[it].mode.value >= 100 }
    val shaderKey = if (needsExtended) "MiBlendModesExt" else "MiBlendModesStd"
    val shaderSource = if (needsExtended) BLEND_MODE_SHADER_EXTENDED else BLEND_MODE_SHADER_STANDARD

    runtimeShaderEffect(
        key = shaderKey,
        shaderString = shaderSource,
        uniformShaderName = "child",
    ) {
        setFloatUniform("layerCount", layerCount.toFloat())

        // Skiko lacks IntArray / array-indexed Color uniform — pack into flat float arrays.
        // Zero unused trailing slots to avoid stale state from a previous invocation.
        for (i in 0 until layerCount) {
            val entry = layerList[i]
            modes[i] = entry.mode.value.toFloat()
            val c = entry.color.convert(ColorSpaces.Srgb)
            val a = c.alpha
            colorData[i * 4] = c.red * a
            colorData[i * 4 + 1] = c.green * a
            colorData[i * 4 + 2] = c.blue * a
            colorData[i * 4 + 3] = a
        }
        for (i in layerCount until MAX_BLEND_LAYERS) {
            modes[i] = 0f
            colorData[i * 4] = 0f
            colorData[i * 4 + 1] = 0f
            colorData[i * 4 + 2] = 0f
            colorData[i * 4 + 3] = 0f
        }
        setFloatUniform("blendModes", modes)
        setFloatUniform("layerColors", colorData)
        // Standard family doesn't declare these uniforms; setting them would error.
        if (needsExtended) {
            setFloatUniform("uSaturation", colors.saturation)
            setFloatUniform("uBrightness", colors.brightness)
            setFloatUniform("uLuminanceAmount", 0f)
            setFloatUniform("uLuminanceValues", 0f, 0f, 0f, 0f)
        }
    }
}

/**
 * Runs the standard texture-blur preset chain inside a custom [drawBackdrop] effect block.
 *
 * Equivalent to what [Modifier.textureBlur] applies internally:
 * 1. [noiseDither] for anti-banding
 * 2. [colorControls] for brightness/contrast/saturation in linear (gamma 2.2) space
 * 3. [blur] with the given radii in dp (multiplied by [BackdropEffectScope.density])
 * 4. [blendColors] for the layered tinting
 *
 * Use this to compose the standard preset with additional custom effects:
 * ```
 * Modifier.drawBackdrop(backdrop, shape = { shape }, effects = {
 *     textureBlurEffect(blurRadius = 30f, colors = colors)
 *     // ...then chain your own effect on top
 * })
 * ```
 *
 * @param blurRadiusX Horizontal blur radius in dp.
 * @param blurRadiusY Vertical blur radius in dp. Defaults to [blurRadiusX].
 * @param noiseCoefficient Noise dithering coefficient. 0 disables noise.
 * @param colors Color adjustments and blend layers applied after blur.
 */
fun BackdropEffectScope.textureBlurEffect(
    blurRadiusX: Float,
    blurRadiusY: Float = blurRadiusX,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurColors(),
) {
    val clampedX = blurRadiusX.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    val clampedY = blurRadiusY.coerceIn(0f, BlurDefaults.MaxBlurRadius)
    noiseDither(noiseCoefficient)
    colorControls(colors.brightness, colors.contrast, colors.saturation)
    blur(clampedX * density, clampedY * density)
    blendColors(colors)
}
