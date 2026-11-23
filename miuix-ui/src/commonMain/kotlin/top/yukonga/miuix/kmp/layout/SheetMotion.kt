// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Immutable
import top.yukonga.miuix.kmp.anim.AccelerateEasing
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.anim.folmeSpring

/**
 * How a bottom sheet moves when it opens, closes and settles after a drag.
 *
 * A preset sets the slide and settle springs and the scrim timing.
 */
@Immutable
class SheetMotion private constructor(
    private val name: String,
) {
    internal val isGlass: Boolean get() = this === Glass

    override fun toString(): String = "SheetMotion.$name"

    companion object {
        /**
         * The motion of the HyperOS system bottom sheet (miuix `BottomSheetModal`).
         *
         * Slides in and out over the full container height on a 0.9 / 0.38 s spring, so it
         * overshoots slightly before resting, and settles after a drag on a 0.85 / 0.4 s spring.
         * The scrim fades in over 300 ms and out over 250 ms.
         */
        val Default: SheetMotion = SheetMotion("Default")

        /**
         * The motion of the HyperOS launcher's sheet, as in its settings page.
         *
         * Slides in and out over its own height on a 0.9 / 0.38 s spring, overshooting slightly
         * before it rests, and settles after a drag on a firmer 0.85 / 0.35 s spring. The scrim
         * fades in over 300 ms and out over 250 ms, slowly at first.
         */
        val Glass: SheetMotion = SheetMotion("Glass")
    }
}

private val SheetDecelerate = DecelerateEasing(1.5f)

private val SheetAccelerate = AccelerateEasing(1.5f)

/**
 * Visibility threshold for the slide progress. The 0.9 slide spring overshoots by only 0.15% of
 * the travel (a few pixels on a phone); the spring's default 0.01 threshold would end the
 * animation at the zero crossing and cut that overshoot off.
 */
internal const val SHEET_PROGRESS_THRESHOLD = 0.00005f

/**
 * Spring that slides the sheet in, and out again when it is closed without a drag. Both presets
 * use the same spring; they differ in travel and settling.
 */
@Suppress("UnusedReceiverParameter")
internal fun SheetMotion.slideSpec(): AnimationSpec<Float> = folmeSpring(damping = 0.9f, response = 0.38f, visibilityThreshold = SHEET_PROGRESS_THRESHOLD)

/** Spring that returns a dragged sheet to rest. */
internal fun SheetMotion.settleSpec(): AnimationSpec<Float> = if (isGlass) {
    folmeSpring(damping = 0.85f, response = 0.35f)
} else {
    folmeSpring(damping = 0.85f, response = 0.4f)
}

/**
 * Spring that carries a released sheet off screen. The launcher reverses its slide spring from
 * the release velocity; the default motion settles towards the hidden state.
 */
internal fun SheetMotion.dragDismissSpec(): AnimationSpec<Float> = if (isGlass) {
    folmeSpring(damping = 0.9f, response = 0.38f)
} else {
    folmeSpring(damping = 0.85f, response = 0.4f)
}

internal fun SheetMotion.dimEnterSpec(): AnimationSpec<Float> = tween(durationMillis = 300, easing = SheetDecelerate)

/**
 * Spec of the scrim fade out. The launcher runs its decelerate curve backwards along the barrier
 * controller, so the fade starts slowly; the platform sheet decelerates.
 */
internal fun SheetMotion.dimExitSpec(): AnimationSpec<Float> = if (isGlass) {
    tween(durationMillis = 250, easing = SheetAccelerate)
} else {
    tween(durationMillis = 250, easing = SheetDecelerate)
}

/**
 * Downward offset of the sheet from its resting place for a slide [progress] (0 hidden, 1 at rest).
 *
 * The platform sheet starts a full container height below its resting place; the launcher sheet
 * starts its own height below, just out of view. Neither clamps the spring's overshoot.
 */
internal fun SheetMotion.slideOffset(
    progress: Float,
    sheetHeightPx: Float,
    containerHeightPx: Float,
): Float {
    val distance = if (isGlass && sheetHeightPx > 0f) sheetHeightPx else containerHeightPx
    return distance * (1f - progress)
}
