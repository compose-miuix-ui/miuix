// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.color.space

import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.color.core.Transforms

/**
 * OkLAB representation with normalized, user-friendly ranges.
 * - `l`: 0.0..100.0 (lightness percent)
 * - `a`: -100.0..100.0 (green-red axis, mapped to internal [-0.4, 0.4])
 * - `b`: -100.0..100.0 (blue-yellow axis, mapped to internal [-0.4, 0.4])
 */
data class OkLab(val l: Double, val a: Double, val b: Double) {
    fun toColor(alpha: Float = 1f): Color {
        val lN = (l / 100.0).coerceIn(0.0, 1.0).toFloat()
        val aN = ((a / 100.0) * 0.4).toFloat().coerceIn(-0.4f, 0.4f)
        val bN = ((b / 100.0) * 0.4).toFloat().coerceIn(-0.4f, 0.4f)
        val ok = floatArrayOf(lN, aN, bN)
        return Transforms.okLabToColor(ok, alpha)
    }
}
