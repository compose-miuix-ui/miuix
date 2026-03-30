// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.color.space

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.hsv

/**
 * HSV representation with normalized, user-friendly ranges.
 * - `h`: hue in degrees [0, 360]
 * - `s`: saturation in percent [0, 100]
 * - `v`: value/brightness in percent [0, 100]
 */
data class Hsv(val h: Float, val s: Float, val v: Float) {
    fun toColor(alpha: Float = 1f): Color {
        val hue = (((h % 360f) + 360f) % 360f)
        val sN = (s / 100f).coerceIn(0f, 1f)
        val vN = (v / 100f).coerceIn(0f, 1f)
        return hsv(hue, sN, vN, alpha)
    }
}
