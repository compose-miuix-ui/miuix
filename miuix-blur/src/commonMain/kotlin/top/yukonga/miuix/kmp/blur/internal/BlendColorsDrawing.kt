// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.colorspace.ColorSpaces
import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.runtimeShaderEffect

/** Maximum number of blend layers supported by the shader. */
private const val MAX_LAYERS = 8

/** Chains all blend color layers from [colors] as a runtime shader effect. */
internal fun BackdropEffectScope.blendColors(colors: BlurColors) {
    if (colors.blendColors.isEmpty()) return
    if (!isRuntimeShaderSupported()) return

    val layers = colors.blendColors.take(MAX_LAYERS)

    runtimeShaderEffect(
        key = "MiBlendModes",
        shaderString = MI_BLEND_MODE_SHADER,
        uniformShaderName = "child",
    ) {
        setFloatUniform("layerCount", layers.size.toFloat())

        // Pack blend modes as float array (Skiko lacks IntArray uniform support)
        val modes = FloatArray(MAX_LAYERS)
        for (i in layers.indices) {
            modes[i] = layers[i].mode.value.toFloat()
        }
        setFloatUniform("blendModes", modes)

        // Pack colors as flat float array with premultiplied alpha
        // (array-indexed setColorUniform is not supported on Android/Skiko)
        val colorData = FloatArray(MAX_LAYERS * 4)
        for (i in layers.indices) {
            val c = layers[i].color.convert(ColorSpaces.Srgb)
            val a = c.alpha
            colorData[i * 4] = c.red * a
            colorData[i * 4 + 1] = c.green * a
            colorData[i * 4 + 2] = c.blue * a
            colorData[i * 4 + 3] = a
        }
        setFloatUniform("layerColors", colorData)
        setFloatUniform("uSaturation", colors.saturation)
        setFloatUniform("uBrightness", colors.brightness)
        setFloatUniform("uLuminanceAmount", 0f)
        setFloatUniform("uLuminanceValues", 0f, 0f, 0f, 0f)
    }
}
