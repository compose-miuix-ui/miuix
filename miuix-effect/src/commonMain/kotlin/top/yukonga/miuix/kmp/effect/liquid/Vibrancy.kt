// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.liquid

// Adapted from Kyant0/AndroidLiquidGlass — https://github.com/Kyant0/AndroidLiquidGlass (Apache 2.0).

import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.colorControls

/**
 * Applies a vibrancy effect that enhances the backdrop's color saturation.
 *
 * This is a lightweight vibrancy implementation that boosts saturation to 1.5x
 * while preserving brightness and contrast. It produces the "vibrant glass"
 * appearance characteristic of iOS/macOS liquid glass effects, where backdrop
 * colors appear more vivid and saturated through the translucent surface.
 *
 * The effect delegates to [colorControls] with fixed parameters. For custom
 * brightness/contrast/saturation combinations, use [colorControls] directly.
 *
 * ### Usage
 *
 * ```kotlin
 * Modifier.drawBackdrop(
 *     backdrop = backdrop,
 *     shape = { RoundedCornerShape(16.dp) },
 *     effects = {
 *         vibrancy()
 *         blur(4.dp.toPx())
 *     },
 * )
 * ```
 */
fun BackdropEffectScope.vibrancy() {
    colorControls(
        brightness = 0f,
        contrast = 1f,
        saturation = 1.5f,
    )
}
