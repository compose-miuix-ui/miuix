// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.color.space

import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.color.core.Transforms

/**
 * OkLAB representation with normalized, user-friendly ranges.
 * - `l`: 0..100 (lightness percent)
 * - `a`: -100..100 (green-red axis, mapped to internal [-0.4, 0.4])
 * - `b`: -100..100 (blue-yellow axis, mapped to internal [-0.4, 0.4])
 */
data class OkLab(val l: Float, val a: Float, val b: Float) {
    fun toColor(alpha: Float = 1f): Color {
        val lN = (l / 100f).coerceIn(0f, 1f)
        val aN = ((a / 100f) * 0.4f).coerceIn(-0.4f, 0.4f)
        val bN = ((b / 100f) * 0.4f).coerceIn(-0.4f, 0.4f)
        val ok = floatArrayOf(lN, aN, bN)
        return Transforms.okLabToColor(ok, alpha)
    }
}
