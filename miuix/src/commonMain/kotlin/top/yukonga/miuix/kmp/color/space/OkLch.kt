// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.color.space

import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.color.core.Transforms

/**
 * OkLCH representation with normalized, user-friendly ranges.
 * - `l`: 0.0..100.0 (lightness percent)
 * - `c`: 0.0..100.0 (chroma percent, mapped to internal [0.0, 0.4])
 * - `h`: hue in degrees [0, 360)
 */
data class OkLch(val l: Double, val c: Double, val h: Double) {
    fun toColor(alpha: Float = 1f): Color {
        val lN = (l / 100.0).coerceIn(0.0, 1.0).toFloat()
        val cN = ((c / 100.0) * 0.4).toFloat().coerceIn(0f, 0.4f)
        val hDeg = (((h % 360.0) + 360.0) % 360.0).toFloat()
        return Transforms.oklchToColor(lN, cN, hDeg, alpha)
    }
}