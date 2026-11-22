// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.Immutable

/**
 * Two-point gradient for a progressive blur: full strength at [startFraction], zero at
 * [endFraction], measured along [angle]. [Top] / [Bottom] / [Left] / [Right] cover the common
 * edge fades.
 *
 * @param angle Direction in degrees clockwise from +X: `0` left→right, `90` top→bottom.
 * @param startFraction Where the blur is at full strength, as a fraction of the surface's extent
 *   along [angle].
 * @param endFraction Where it reaches zero; may be smaller to reverse the ramp, must differ.
 * @param curve Falloff exponent: `< 1` concentrates the change toward the strong end, `> 1` toward
 *   the clear end. Must be positive.
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
