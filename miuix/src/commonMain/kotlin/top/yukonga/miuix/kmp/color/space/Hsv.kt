// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.color.space

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.hsv

/**
 * HSV representation with normalized, user-friendly ranges.
 * - `h`: hue in degrees [0, 360)
 * - `s`: saturation in percent [0.0, 100.0]
 * - `v`: value/brightness in percent [0.0, 100.0]
 */
data class Hsv(val h: Double, val s: Double, val v: Double) {
    fun toColor(alpha: Float = 1f): Color {
        val hue = (((h % 360.0) + 360.0) % 360.0).toFloat()
        val sN = (s / 100.0).coerceIn(0.0, 1.0).toFloat()
        val vN = (v / 100.0).coerceIn(0.0, 1.0).toFloat()
        return hsv(hue, sN, vN, alpha)
    }
}