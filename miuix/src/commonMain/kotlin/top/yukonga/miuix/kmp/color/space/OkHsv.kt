// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.color.space

import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.color.core.Transforms

/**
 * OkHSV representation with normalized, user-friendly ranges.
 * - `h`: hue in degrees [0, 360)
 * - `s`: saturation in percent [0.0, 100.0]
 * - `v`: value/brightness in percent [0.0, 100.0
 */
data class OkHsv(val h: Float, val s: Float, val v: Float) {
    fun toColor(alpha: Float = 1f): Color = Transforms.okhsvToColor(h, s, v, alpha)
}