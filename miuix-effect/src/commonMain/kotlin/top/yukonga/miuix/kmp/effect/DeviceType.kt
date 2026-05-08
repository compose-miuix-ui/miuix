// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect

/**
 * Identifies the device form factor for effect configuration selection.
 *
 * Different device types receive distinct visual presets optimized for their
 * screen characteristics (size, pixel density, viewing distance). Phone presets
 * use tighter gradient radii; pad presets use wider, more subtle gradients.
 *
 * Used by [BgEffectBackground] and the internal [top.yukonga.miuix.kmp.effect.bg.BgEffectConfig]
 * to select the appropriate shader uniform configuration.
 */
enum class DeviceType {
    /** Smartphone form factor. Default preset with standard gradient density. */
    PHONE,

    /** Tablet/pad form factor. Wider gradient spread with adjusted color saturation. */
    PAD,
}
