// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Color configuration applied after blur processing.
 *
 * @param blendColors Colors blended over the blurred backdrop, drawn in order.
 * @param brightness Brightness adjustment in range [-1, 1]. 0 means no change.
 * @param contrast Contrast multiplier. 1 means no change.
 * @param saturation Saturation multiplier. 1 means no change.
 */
@Immutable
data class BlurColors(
    val blendColors: List<BlendColorEntry> = emptyList(),
    val brightness: Float = 0f,
    val contrast: Float = 1f,
    val saturation: Float = 1f,
)

/**
 * A single color blend entry applied over the blurred backdrop.
 *
 * Supports both standard SkBlendMode values (0-29, handled by GPU hardware)
 * and custom modes (100-121, 200-203, handled by runtime shader).
 * See [BlendMode] for all available constants.
 *
 * @param color The color to blend.
 * @param mode The blend mode ID. Defaults to [BlendMode.SRC_OVER].
 */
@Immutable
data class BlendColorEntry(
    val color: Color,
    val mode: Int = BlendMode.SRC_OVER,
)

/**
 * Blend mode constants covering both standard SkBlendMode (0-31) and
 * custom modes (100-121, 200-203).
 *
 * All modes are executed by a runtime shader matching libhwui's
 * premultiplied-alpha blend implementations. Standard modes (0-31)
 * use Skia-compatible formulas; custom modes (100+) implement
 * Lab color space operations, linear light blending, and more.
 */
object BlendMode {
    // region Standard SkBlendMode (0-31)

    const val CLEAR = 0
    const val SRC = 1
    const val DST = 2
    const val SRC_OVER = 3
    const val DST_OVER = 4
    const val SRC_IN = 5
    const val DST_IN = 6
    const val SRC_OUT = 7
    const val DST_OUT = 8
    const val SRC_ATOP = 9
    const val DST_ATOP = 10
    const val XOR = 11
    const val PLUS = 12
    const val MODULATE = 13
    const val SCREEN = 14
    const val OVERLAY = 15
    const val DARKEN = 16
    const val LIGHTEN = 17
    const val COLOR_DODGE = 18
    const val COLOR_BURN = 19
    const val HARD_LIGHT = 20
    const val SOFT_LIGHT = 21
    const val DIFFERENCE_STD = 22
    const val EXCLUSION = 23
    const val MULTIPLY = 24
    const val HUE = 25
    const val SATURATION_STD = 26
    const val COLOR = 27
    const val LUMINOSITY = 28

    // endregion

    // region Custom modes (>=100)

    /** Linear light blend. */
    const val LINEAR_LIGHT = 100

    /** Linear light with greyscale modulation. */
    const val LINEAR_LIGHT_WITH_GREYSCALE = 101

    /** Absolute difference blend. */
    const val MI_DIFFERENCE = 102

    /** Lab lighten with greyscale modulation. */
    const val LAB_LIGHTEN_WITH_GREYSCALE = 103

    /** Lab darken with greyscale modulation. */
    const val LAB_DARKEN_WITH_GREYSCALE = 105

    /** Lab color mapping. Uses color.r as m, color.g as n. */
    const val LAB = 106

    /** Linear light in Lab color space. */
    const val LINEAR_LIGHT_LAB = 107

    /** Color dodge V2. */
    const val MI_COLOR_DODGE = 118

    /** Color burn V2. */
    const val MI_COLOR_BURN = 119

    /** Plus darker with alpha-aware compositing. */
    const val PLUS_DARKER = 120

    /** Plus lighter with alpha-aware compositing. */
    const val PLUS_LIGHTER = 121

    /** Alpha blend with child modulation. */
    const val ALPHA_BLEND = 200

    /** Saturation adjustment. Requires [BlurColors.saturation]. */
    const val MI_SATURATION = 201

    /** Brightness adjustment. Requires [BlurColors.brightness]. */
    const val MI_BRIGHTNESS = 202

    /** Luminance curve adjustment. */
    const val MI_LUMINANCE = 203

    // endregion
}

/**
 * Default values for texture blur effects.
 */
object BlurDefaults {

    /** Default blur radius in pixels. */
    val BlurRadius: Float = 60f

    /** Default noise dithering coefficient for anti-banding. 0 disables noise. */
    val NoiseCoefficient: Float = 0.02f

    /** Maximum allowed blur radius in pixels. */
    val MaxBlurRadius: Float = 400f

    /** Default corner radius for [BlurSurface]. */
    val CornerRadius: Dp = 16.dp

    /**
     * Creates a [BlurColors] instance with the given parameters.
     */
    @Composable
    fun blurColors(
        blendColors: List<BlendColorEntry> = emptyList(),
        brightness: Float = 0f,
        contrast: Float = 1f,
        saturation: Float = 1f,
    ): BlurColors = remember(blendColors, brightness, contrast, saturation) {
        BlurColors(
            blendColors = blendColors,
            brightness = brightness,
            contrast = contrast,
            saturation = saturation,
        )
    }
}
