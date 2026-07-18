// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.Immutable

/**
 * A two-point linear gradient describing how a progressive blur ramps from full strength at
 * [startFraction] to zero at [endFraction], measured along [angle]. Use the [Top] / [Bottom] /
 * [Left] / [Right] presets for the common edge-fade cases.
 *
 * @param angle Gradient direction in degrees, clockwise from the +X axis: `0` fades left→right,
 *   `90` top→bottom, `180` right→left, `270` bottom→top.
 * @param startFraction Fraction in `[0, 1]` of the surface's projected extent along [angle] where
 *   the blur is at full strength.
 * @param endFraction Fraction in `[0, 1]` where the blur fades to zero. May be smaller than
 *   [startFraction] to reverse the ramp; must differ from it.
 * @param curve Power-curve exponent reshaping the falloff between the fixed endpoints: `< 1`
 *   concentrates the radius change toward the strong end (fast early change, long soft tail),
 *   `> 1` toward the clear end. Must be positive.
 */
@Immutable
data class ProgressiveBlur(
    val angle: Float = 90f,
    val startFraction: Float = 0f,
    val endFraction: Float = 1f,
    val curve: Float = 1f,
) {
    companion object {
        /** Blur strongest at the top edge, fading to clear at the bottom. */
        val Top: ProgressiveBlur = ProgressiveBlur(angle = 90f, startFraction = 0f, endFraction = 1f)

        /** Blur strongest at the bottom edge, fading to clear at the top. */
        val Bottom: ProgressiveBlur = ProgressiveBlur(angle = 270f, startFraction = 0f, endFraction = 1f)

        /** Blur strongest at the left edge, fading to clear at the right. */
        val Left: ProgressiveBlur = ProgressiveBlur(angle = 0f, startFraction = 0f, endFraction = 1f)

        /** Blur strongest at the right edge, fading to clear at the left. */
        val Right: ProgressiveBlur = ProgressiveBlur(angle = 180f, startFraction = 0f, endFraction = 1f)
    }
}
