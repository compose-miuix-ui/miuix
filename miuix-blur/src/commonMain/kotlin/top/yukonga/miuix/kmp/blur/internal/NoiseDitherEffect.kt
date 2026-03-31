// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import top.yukonga.miuix.kmp.blur.BackdropEffectScope

/**
 * Registers noise dithering to reduce color banding artifacts.
 *
 * The actual noise application strategy depends on the current [BackdropEffectScope.downscaleFactor]:
 * - When downscaleFactor <= 1 (full resolution), noise is added as a RenderEffect in the chain.
 * - When downscaleFactor > 1 (downsampled), the coefficient is stored for full-resolution
 *   application after upscaling, so each screen pixel gets independent noise.
 */
internal fun BackdropEffectScope.noiseDither(coefficient: Float) {
    if (coefficient <= 0f) return
    noiseCoefficient = coefficient
}
