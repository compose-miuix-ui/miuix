// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.runtimeShaderEffect

/**
 * Applies noise dithering to reduce color banding artifacts.
 */
internal fun BackdropEffectScope.noiseDither(coefficient: Float) {
    if (!isRuntimeShaderSupported()) return
    if (coefficient <= 0f) return

    runtimeShaderEffect(
        key = "NoiseDither",
        shaderString = NOISE_DITHER_SHADER,
        uniformShaderName = "child",
    ) {
        setFloatUniform("noise_coeff", coefficient)
    }
}
