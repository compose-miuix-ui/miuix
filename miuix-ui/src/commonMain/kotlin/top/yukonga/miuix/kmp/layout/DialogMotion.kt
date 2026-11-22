// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Immutable
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.anim.folmeSpring
import kotlin.math.max

/**
 * How a dialog moves when it opens and closes.
 *
 * The bottom-attached presentation slides the panel up from below its resting place. The
 * centered (large-screen) presentation scales and fades it. A preset sets the springs, curves
 * and scrim timing for both presentations.
 */
@Immutable
class DialogMotion private constructor(
    private val name: String,
) {
    internal val isGlass: Boolean get() = this === Glass

    override fun toString(): String = "DialogMotion.$name"

    companion object {
        /**
         * The motion of the HyperOS system dialog (miuix `AlertDialog`).
         *
         * Bottom-attached: slides up on a 0.82 / 0.3 s spring and leaves in 200 ms.
         * Centered: scales up from slightly smaller and fades in on a 0.8 / 0.3 s spring, then
         * reverses in 200 ms. The scrim fades in over 300 ms and out over 350 ms.
         */
        val Default: DialogMotion = DialogMotion("Default")

        /**
         * The motion of the HyperOS launcher's glass dialog.
         *
         * Bottom-attached: slides up on a 0.84 / 0.3 s spring and drops back on a softer
         * 0.97 / 0.33 s spring. Centered: scales up from 0.75 on a 0.7 / 0.45 s spring while
         * fading in, then fades out at full size. The scrim fades in and out linearly over 100 ms.
         */
        val Glass: DialogMotion = DialogMotion("Glass")
    }
}

/** Visibility threshold for the 0..1 progress values the dialog animates. */
internal const val DIALOG_PROGRESS_THRESHOLD = 0.0001f

// The launcher drives its dialog off a 723 ms route; its fades are fractions of that route.
private const val GLASS_ROUTE_MILLIS = 723

// Cubic(0.39, 0.575, 0.565, 1.0), Flutter's Curves.easeOutSine.
private val GlassFadeEasing: Easing = CubicBezierEasing(0.39f, 0.575f, 0.565f, 1f)

/** The launcher's reverse fade: [GlassFadeEasing] read backwards along the route. */
private val GlassFadeOutEasing: Easing = Easing { fraction -> 1f - GlassFadeEasing.transform(1f - fraction) }

private val ClassicDecelerate = DecelerateEasing(1.5f)

internal fun DialogMotion.dimEnterSpec(): AnimationSpec<Float> = if (isGlass) {
    tween(durationMillis = 100, easing = LinearEasing)
} else {
    tween(durationMillis = 300, easing = ClassicDecelerate)
}

internal fun DialogMotion.dimExitSpec(): AnimationSpec<Float> = if (isGlass) {
    tween(durationMillis = 100, easing = LinearEasing)
} else {
    tween(durationMillis = 350, easing = ClassicDecelerate)
}

/** Spring that carries the panel's slide (bottom-attached) or scale (centered) in. */
internal fun DialogMotion.enterSpec(largeScreen: Boolean): AnimationSpec<Float> = when {
    isGlass && largeScreen -> folmeSpring(damping = 0.7f, response = 0.45f, visibilityThreshold = DIALOG_PROGRESS_THRESHOLD)
    isGlass -> folmeSpring(damping = 0.84f, response = 0.3f, visibilityThreshold = DIALOG_PROGRESS_THRESHOLD)
    largeScreen -> folmeSpring(damping = 0.8f, response = 0.3f, visibilityThreshold = DIALOG_PROGRESS_THRESHOLD)
    else -> folmeSpring(damping = 0.82f, response = 0.3f, visibilityThreshold = DIALOG_PROGRESS_THRESHOLD)
}

/**
 * Spec that carries the panel's slide or scale out.
 *
 * @param interrupted Whether the panel is still opening. The glass sheet then drops back on a
 *   slower spring.
 */
internal fun DialogMotion.exitSpec(interrupted: Boolean): AnimationSpec<Float> = when {
    isGlass && interrupted -> folmeSpring(damping = 0.97f, response = 0.45f, visibilityThreshold = DIALOG_PROGRESS_THRESHOLD)
    isGlass -> folmeSpring(damping = 0.97f, response = 0.33f, visibilityThreshold = DIALOG_PROGRESS_THRESHOLD)
    else -> tween(durationMillis = 200, easing = ClassicDecelerate)
}

/**
 * Spec of the glass centered panel's fade in: easeOutSine over the first 0.2315 of the route
 * (~167 ms). The default motion fades on [enterSpec] instead.
 */
internal fun DialogMotion.glassFadeInSpec(): AnimationSpec<Float> = tween(durationMillis = (GLASS_ROUTE_MILLIS * 0.23148148f).toInt(), easing = GlassFadeEasing)

/**
 * Spec of the glass centered panel's fade out: easeOutSine run backwards over the first 0.1543
 * of the reverse route (~112 ms). The default motion fades on [exitSpec] instead.
 */
internal fun DialogMotion.glassFadeOutSpec(): AnimationSpec<Float> = tween(durationMillis = (GLASS_ROUTE_MILLIS * 0.15432099f).toInt(), easing = GlassFadeOutEasing)

/**
 * Scale the centered panel starts from and returns to.
 *
 * The default motion shrinks the panel by 60 px on its longer side, but never below 0.8.
 */
internal fun DialogMotion.startScale(panelWidthPx: Int, panelHeightPx: Int): Float {
    if (isGlass) return 0.75f
    val longerSide = max(panelWidthPx, panelHeightPx)
    if (longerSide <= 0) return 0.8f
    return max(0.8f, 1f - 60f / longerSide)
}
