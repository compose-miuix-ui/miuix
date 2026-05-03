// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import kotlin.jvm.JvmInline

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
 * See [BlurBlendMode] for all available constants.
 *
 * @param color The color to blend.
 * @param mode The blend mode. Defaults to [BlurBlendMode.SrcOver].
 */
@Immutable
data class BlendColorEntry(
    val color: Color,
    val mode: BlurBlendMode = BlurBlendMode.SrcOver,
)

/**
 * Blend mode for blur color blending.
 *
 * Wraps standard SkBlendMode values (0-31) plus extended modes (100-121, 200-203)
 * implementing Lab color space operations, linear light blending, and more.
 */
@JvmInline
value class BlurBlendMode(val value: Int) {

    companion object {
        // region Standard SkBlendMode (0-31)

        val Clear = BlurBlendMode(0)
        val Src = BlurBlendMode(1)
        val Dst = BlurBlendMode(2)
        val SrcOver = BlurBlendMode(3)
        val DstOver = BlurBlendMode(4)
        val SrcIn = BlurBlendMode(5)
        val DstIn = BlurBlendMode(6)
        val SrcOut = BlurBlendMode(7)
        val DstOut = BlurBlendMode(8)
        val SrcAtop = BlurBlendMode(9)
        val DstAtop = BlurBlendMode(10)
        val Xor = BlurBlendMode(11)
        val Plus = BlurBlendMode(12)
        val Modulate = BlurBlendMode(13)
        val Screen = BlurBlendMode(14)
        val Overlay = BlurBlendMode(15)
        val Darken = BlurBlendMode(16)
        val Lighten = BlurBlendMode(17)
        val ColorDodge = BlurBlendMode(18)
        val ColorBurn = BlurBlendMode(19)
        val HardLight = BlurBlendMode(20)
        val SoftLight = BlurBlendMode(21)
        val Difference = BlurBlendMode(22)
        val Exclusion = BlurBlendMode(23)
        val Multiply = BlurBlendMode(24)
        val Hue = BlurBlendMode(25)
        val Saturation = BlurBlendMode(26)
        val Color = BlurBlendMode(27)
        val Luminosity = BlurBlendMode(28)

        // endregion

        // region Custom modes (>=100)

        /** Linear light blend. */
        val LinearLight = BlurBlendMode(100)

        /** Linear light with greyscale modulation. */
        val LinearLightWithGreyscale = BlurBlendMode(101)

        /** Absolute difference blend. */
        val MiDifference = BlurBlendMode(102)

        /** Lab lighten with greyscale modulation. */
        val LabLightenWithGreyscale = BlurBlendMode(103)

        /** Lab darken with greyscale modulation. */
        val LabDarkenWithGreyscale = BlurBlendMode(105)

        /** Lab color mapping. Uses color.r as m, color.g as n. */
        val Lab = BlurBlendMode(106)

        /** Linear light in Lab color space. */
        val LinearLightLab = BlurBlendMode(107)

        /** Color dodge V2. */
        val MiColorDodge = BlurBlendMode(118)

        /** Color burn V2. */
        val MiColorBurn = BlurBlendMode(119)

        /** Plus darker with alpha-aware compositing. */
        val PlusDarker = BlurBlendMode(120)

        /** Plus lighter with alpha-aware compositing. */
        val PlusLighter = BlurBlendMode(121)

        /** Alpha blend with child modulation. */
        val AlphaBlend = BlurBlendMode(200)

        /** Saturation adjustment. Requires [BlurColors.saturation]. */
        val MiSaturation = BlurBlendMode(201)

        /** Brightness adjustment. Requires [BlurColors.brightness]. */
        val MiBrightness = BlurBlendMode(202)

        /** Luminance curve adjustment. */
        val MiLuminance = BlurBlendMode(203)

        // endregion
    }
}

/**
 * Default values for texture blur effects.
 */
object BlurDefaults {

    /** Default blur radius in dp. Internally converted to pixels using display density. */
    val BlurRadius: Float = 20f

    /** Default noise dithering coefficient for anti-banding. 0 disables noise. */
    val NoiseCoefficient: Float = 0.0045f

    /** Maximum allowed blur radius in dp. */
    val MaxBlurRadius: Float = 150f

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
