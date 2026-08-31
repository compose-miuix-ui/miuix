// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import kotlin.math.PI

/** The motion the source system animates its glass surfaces with. */
object GlassMotion {

    /**
     * Converts one of the source system's spring definitions into a Compose [SpringSpec].
     *
     * @param damping The damping ratio. 1 settles without overshoot; below 1 overshoots once.
     * @param response The period of the undamped oscillation, in seconds. Smaller is faster. Must
     *   be greater than zero — the stiffness is derived by dividing by it, and zero would hand the
     *   animation an infinite or undefined stiffness rather than failing where the mistake is.
     */
    @Stable
    fun <T> springOf(damping: Float, response: Float): SpringSpec<T> {
        require(response > 0f) { "A spring's response must be greater than zero, was $response" }
        val omega = (2.0 * PI / response).toFloat()
        return spring(dampingRatio = damping, stiffness = omega * omega)
    }

    /**
     * The spring the leading edge of the bottom bar's capsule travels on.
     *
     * The capsule does not travel rigidly. Its `OverlayView` puts the two edges on different
     * springs and swaps which edge gets which by the direction of travel, so the trailing edge is
     * always the slower of the two ([navIndicatorTrail]) and the capsule stretches on its way. That
     * stretch, and the overshoot this spring's damping ratio leaves, are the whole of what makes
     * the capsule read as elastic rather than as a box on rails.
     */
    @Stable
    fun <T> navIndicator(): SpringSpec<T> = springOf(0.7f, 0.4f)

    /** The spring the trailing edge of the capsule travels on. Slower, so the capsule stretches. */
    @Stable
    fun <T> navIndicatorTrail(): SpringSpec<T> = springOf(0.75f, 0.5f)

    /** [navIndicator] for the edge that leads the travel, [navIndicatorTrail] for the one behind. */
    @Stable
    fun <T> edgeSpring(leading: Boolean): SpringSpec<T> = if (leading) navIndicator() else navIndicatorTrail()

    /** The capsule's fill arriving under a finger. Critically damped, and quick. */
    @Stable
    fun navPressEnter(): SpringSpec<Color> = springOf(1f, 0.2f)

    /** The capsule's fill leaving on release. Slower than the press, so the lift reads as a fade. */
    @Stable
    fun navPressExit(): SpringSpec<Color> = springOf(0.95f, 0.35f)

    /** The spring the bottom bar shows and hides on, from the same source class. */
    @Stable
    fun <T> navShowHide(): SpringSpec<T> = springOf(1f, 0.3f)

    /** The system's default spring, and the one its segmented tab indicator moves on. */
    @Stable
    fun <T> default(): SpringSpec<T> = springOf(0.95f, 0.35f)

    /** A top bar expanding back to its large title. */
    @Stable
    fun <T> barExpand(): SpringSpec<T> = springOf(1f, 0.3f)

    /** A top bar collapsing to its compact title. Deliberately quicker than the expansion. */
    @Stable
    fun <T> barCollapse(): SpringSpec<T> = springOf(1f, 0.15f)

    /** A large title tracking a scroll. Slow, so it trails the content rather than snapping. */
    @Stable
    fun <T> barTrack(): SpringSpec<T> = springOf(1f, 0.6f)

    /** A popup opening from its anchor. The only under-damped spring in the set — it overshoots. */
    @Stable
    fun <T> popupEnter(): SpringSpec<T> = springOf(0.8f, 0.28f)

    /** A popup closing. */
    @Stable
    fun <T> popupExit(): SpringSpec<T> = springOf(0.95f, 0.2f)

    /** A dialog entering. */
    @Stable
    fun <T> dialogEnter(): SpringSpec<T> = springOf(0.95f, 0.35f)

    /** A dialog leaving. */
    @Stable
    fun <T> dialogExit(): SpringSpec<T> = springOf(0.95f, 0.15f)

    /** A surface being pressed. */
    @Stable
    fun <T> pressDown(): SpringSpec<T> = springOf(0.99f, 0.15f)

    /** A surface released from a press. Slower than the press, so the lift reads as a rebound. */
    @Stable
    fun <T> pressUp(): SpringSpec<T> = springOf(0.99f, 0.3f)

    /** A surface appearing with a scale. */
    @Stable
    fun <T> scaleIn(): SpringSpec<T> = springOf(0.6f, 0.35f)

    /** A surface disappearing with a scale. */
    @Stable
    fun <T> scaleOut(): SpringSpec<T> = springOf(0.75f, 0.2f)

    /**
     * The curve the bottom bar grades its own contents on — the indicator's fill, and a
     * destination's tint as the selection passes over it.
     */
    @Stable
    fun navContent(): FiniteAnimationSpec<Color> = tween(
        durationMillis = 150,
        easing = CubicBezierEasing(0.33f, 0f, 0.67f, 1f),
    )

    /** [navContent], for anything animated as a plain number. */
    @Stable
    fun navContentFloat(): FiniteAnimationSpec<Float> = tween(
        durationMillis = 150,
        easing = CubicBezierEasing(0.33f, 0f, 0.67f, 1f),
    )

    /**
     * Opacity is not sprung. A popup fades in over this, faster than its scale settles, so the
     * surface is already legible while it is still moving.
     */
    @Stable
    fun fadeIn(): FiniteAnimationSpec<Float> = tween(durationMillis = 80, easing = LinearEasing)

    /** Opacity on the way out, slower than [fadeIn] so a dismissal does not read as a flicker. */
    @Stable
    fun fadeOut(): FiniteAnimationSpec<Float> = tween(durationMillis = 150, easing = LinearEasing)

    /** The scale a popup grows from, measured at its anchor corner. */
    const val POPUP_ENTER_SCALE: Float = 0.2f

    /** The scale a popup shrinks back to. It leaves larger than it arrived. */
    const val POPUP_EXIT_SCALE: Float = 0.5f

    /** The floor on the pressed scale, whatever the surface measures. */
    const val PRESS_SCALE_MIN: Float = 0.9f

    /** How much a press takes off a surface, across the whole of its smaller side, in dp. */
    const val PRESS_INSET_DP: Float = 10f

    /** The scale the bottom bar shrinks to as it leaves. */
    const val NAV_HIDE_SCALE: Float = 0.6f

    /** How far the bottom bar blurs itself at the end of its exit, in dp. */
    const val NAV_HIDE_BLUR_DP: Float = 18f

    /** How long the bottom bar waits before it comes back, in milliseconds. */
    const val NAV_SHOW_DELAY_MS: Long = 100
}
