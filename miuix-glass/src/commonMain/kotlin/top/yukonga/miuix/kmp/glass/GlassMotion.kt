// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.anim.folmeSpring

/** Animation specifications for glass surfaces and components. */
object GlassMotion {

    /**
     * Converts a damping ratio and a response time into a Compose [SpringSpec].
     *
     * @param damping The damping ratio. 1 settles without overshoot; below 1 overshoots once.
     * @param response The period of the undamped oscillation, in seconds. Smaller is faster, and it
     *   must be greater than zero — the stiffness is derived by dividing by it.
     */
    @Stable
    fun <T> springOf(damping: Float, response: Float): SpringSpec<T> {
        require(response > 0f) { "A spring's response must be greater than zero, was $response" }
        return folmeSpring(damping, response)
    }

/** Transform spring using the platform settle threshold. */
    @Stable
    private fun transformSpring(damping: Float, response: Float): SpringSpec<Float> = folmeSpring(
        damping = damping,
        response = response,
        visibilityThreshold = TRANSFORM_VISIBILITY_THRESHOLD,
    )

/** Spring for the leading edge of the navigation indicator. The trailing edge uses the slower
     *   [navIndicatorTrail] spring to stretch the capsule during travel.
     */
    @Stable
    fun <T> navIndicator(): SpringSpec<T> = springOf(0.7f, 0.4f)

/** Spring for the trailing edge of the navigation indicator. */
    @Stable
    fun <T> navIndicatorTrail(): SpringSpec<T> = springOf(0.75f, 0.5f)

/** Critically damped spring for following navigation drag targets. */
    @Stable
    fun <T> navDragFollow(): SpringSpec<T> = springOf(1f, 0.15f)

/** Selects [navIndicator] for the leading edge and [navIndicatorTrail] for the trailing edge. */
    @Stable
    fun <T> edgeSpring(leading: Boolean): SpringSpec<T> = if (leading) navIndicator() else navIndicatorTrail()

/** Spring for the navigation indicator fill entering a press. */
    @Stable
    fun navPressEnter(): SpringSpec<Color> = springOf(1f, 0.2f)

/** Spring for the navigation indicator fill leaving a press. */
    @Stable
    fun navPressExit(): SpringSpec<Color> = springOf(0.95f, 0.35f)

/** Spring for a menu-row press release. */
    @Stable
    fun popupPressExit(): SpringSpec<Color> = springOf(1f, 0.15f)

/** Spring for showing and hiding the navigation bar. */
    @Stable
    fun <T> navShowHide(): SpringSpec<T> = springOf(1f, 0.3f)

/** Default spring used by the segmented tab indicator. */
    @Stable
    fun <T> default(): SpringSpec<T> = springOf(0.95f, 0.35f)

/** Opening and closing spring for a secondary popup. */
    @Stable
    fun secondaryPopup(expanding: Boolean): SpringSpec<Float> = if (expanding) {
        transformSpring(0.95f, 0.35f)
    } else {
        transformSpring(0.95f, 0.2f)
    }

/** Alpha spring for the secondary popup mask. */
    @Stable
    internal fun secondaryPopupMask(expanding: Boolean): SpringSpec<Float> = folmeSpring(
        damping = 0.95f,
        response = if (expanding) 0.35f else 0.2f,
        visibilityThreshold = POPUP_MASK_MIN_VISIBLE_CHANGE * 0.75f,
    )

/** Minimum visible alpha change for the popup mask. */
    internal const val POPUP_MASK_MIN_VISIBLE_CHANGE: Float = 1f / 256f

/** Spring for expanding a top bar to its large title. */
    @Stable
    fun <T> barExpand(): SpringSpec<T> = springOf(1f, 0.3f)

/** Spring for collapsing a top bar to its compact title. */
    @Stable
    fun <T> barCollapse(): SpringSpec<T> = springOf(1f, 0.15f)

/** Spring for a large title tracking scroll. */
    @Stable
    fun <T> barTrack(): SpringSpec<T> = springOf(1f, 0.6f)

/** Linear transition for a floating action-bar button. */
    @Stable
    fun topBarButtonFloat(): FiniteAnimationSpec<Float> = tween(
        durationMillis = 350,
        easing = LinearEasing,
    )

/** Linear transition for the action-bar overlay mask. */
    @Stable
    fun topBarMask(): FiniteAnimationSpec<Float> = tween(durationMillis = 100, easing = LinearEasing)

/** Spring for popup entry. */
    @Stable
    fun <T> popupEnter(): SpringSpec<T> = springOf(0.8f, 0.28f)

/** Spring for popup exit. */
    @Stable
    fun <T> popupExit(): SpringSpec<T> = springOf(0.95f, 0.2f)

/** Spring for dialog entry. */
    @Stable
    fun <T> dialogEnter(): SpringSpec<T> = springOf(0.95f, 0.35f)

/** Spring for dialog exit. */
    @Stable
    fun <T> dialogExit(): SpringSpec<T> = springOf(0.95f, 0.15f)

/** Spring for a pressed surface. */
    @Stable
    fun <T> pressDown(): SpringSpec<T> = springOf(0.99f, 0.15f)

/** Spring for releasing a pressed surface. */
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

    /**
     * The spring an anchored menu's geometry runs on.
     *
     * It carries the width and the aspect ratio together, not a uniform scale. The panel's right
     * and top edges never move; it opens leftward and downward, wide and flat at first and only
     * then letting its height out.
     */
    @Stable
    fun popupMorph(): SpringSpec<Float> = springOf(0.82f, 0.33f)

    /**
     * The curve a menu's opacity and its blur correction run on: 200ms linear in, 150ms out, so the
     * panel is already fully drawn while the geometry is still settling.
     *
     * @param entering Whether the menu is opening.
     */
    @Stable
    fun popupMorphFade(entering: Boolean): FiniteAnimationSpec<Float> = tween(durationMillis = if (entering) 200 else 150, easing = LinearEasing)

    /**
     * The curve a menu blurs its own contents on: 200ms linear in both directions. The blur is what
     * carries the opening for a menu with no icon to hand over to.
     */
    @Stable
    fun popupMorphBlur(): FiniteAnimationSpec<Float> = tween(durationMillis = 200, easing = LinearEasing)

    /**
     * The spring the geometry of a menu grown out of its own control runs on.
     *
     * This is the other of the two openings: it applies when the control itself takes part, so the
     * panel is not revealed beside the control but *is* the control, grown from the control's own
     * rectangle and corner radius out to the panel's.
     *
     * @param entering Whether the menu is opening.
     */
    @Stable
    fun transformBounds(entering: Boolean): SpringSpec<Float> = if (entering) {
        transformSpring(0.8f, 0.4f)
    } else {
        transformSpring(0.8f, 0.28f)
    }

    /**
     * The spring the *centre* of that panel travels on. Deliberately not [transformBounds]: the
     * panel reaches where it belongs sooner than it finishes growing to size.
     *
     * @param entering Whether the menu is opening.
     */
    @Stable
    fun transformCenter(entering: Boolean): SpringSpec<Float> = if (entering) {
        transformSpring(0.8f, 0.25f)
    } else {
        transformSpring(0.8f, 0.4f)
    }

    /**
     * The ramp the control's own contents dissolve out on as the panel takes over. The two
     * dissolves share an 80ms ramp with 50ms between them, and which one waits swaps with the
     * direction.
     *
     * @param entering Whether the menu is opening.
     */
    @Stable
    fun transformIconMaterial(entering: Boolean): FiniteAnimationSpec<Float> = tween(
        durationMillis = TRANSFORM_MATERIAL_MS,
        delayMillis = if (entering) 0 else TRANSFORM_MATERIAL_DELAY_MS,
        easing = LinearEasing,
    )

    /** The ramp the panel's contents dissolve in on. The other half of [transformIconMaterial]. */
    @Stable
    fun transformContentMaterial(entering: Boolean): FiniteAnimationSpec<Float> = tween(
        durationMillis = TRANSFORM_MATERIAL_MS,
        delayMillis = if (entering) TRANSFORM_MATERIAL_DELAY_MS else 0,
        easing = LinearEasing,
    )

    /** How long either half of that dissolve takes, in milliseconds. */
    const val TRANSFORM_MATERIAL_MS: Int = 80

    /** How long the second half of the dissolve waits for the first, in milliseconds. */
    const val TRANSFORM_MATERIAL_DELAY_MS: Int = 50

    /**
     * How far the two halves of that dissolve blur, in pixels.
     *
     * Pixels, not dp, because this goes straight to the platform's blur and is never scaled by
     * density. A denser screen therefore gets a proportionally softer dissolve.
     */
    const val TRANSFORM_BLUR_PX: Float = 50f

    /** Folme `ValueTarget`: `0.002` minimum visible change times its `0.75` multiplier. */
    internal const val TRANSFORM_VISIBILITY_THRESHOLD: Float = 0.0015f

    /** Width a menu opens from, as a fraction of the width it settles at. */
    const val POPUP_START_WIDTH: Float = 0.15f

    /** Its height at that moment, as a fraction of its own width. Far flatter than it ends up. */
    const val POPUP_START_RATIO: Float = 0.2f

    /** Corner radius a menu opens from, in dp. It relaxes to the panel's own. */
    const val POPUP_START_CORNER_DP: Float = 4f

    /** How far a menu blurs its own contents at the start, in pixels rather than dp. */
    const val POPUP_MORPH_BLUR_PX: Float = 40f

    /**
     * The spring the *size* of a dropdown runs on.
     *
     * The third of the openings, and the one that holds no edge still: the panel's size and its
     * centre run on two springs of different speeds, which is what makes the travel an arc. Opening
     * the centre is the quicker of the two; closing they swap.
     *
     * @param entering Whether the dropdown is opening.
     */
    @Stable
    fun <T> arcBounds(entering: Boolean): SpringSpec<T> = if (entering) springOf(0.8f, 0.35f) else springOf(0.8f, 0.22f)

    /** The spring the *centre* of that dropdown travels on. The other half of [arcBounds]. */
    @Stable
    fun <T> arcPosition(entering: Boolean): SpringSpec<T> = if (entering) springOf(0.8f, 0.22f) else springOf(0.8f, 0.35f)

    /**
     * The ramp a dropdown fades on: 50ms in and 200ms out, so the panel is already legible by the
     * time it becomes visible at all.
     *
     * @param entering Whether the dropdown is opening.
     */
    @Stable
    fun arcFade(entering: Boolean): FiniteAnimationSpec<Float> = tween(durationMillis = if (entering) 50 else 200, easing = LinearEasing)

    /**
     * The ramp that dropdown blurs itself on as it leaves.
     *
     * One-sided. The panel is blurred only on the way out, so the opening has none of it at all
     * and the value snaps sharp rather than arriving out of focus.
     *
     * @param entering Whether the dropdown is opening.
     */
    @Stable
    fun arcBlur(entering: Boolean): FiniteAnimationSpec<Float> = if (entering) snap() else tween(durationMillis = 200, easing = LinearEasing)

    /** Width that dropdown opens from, as a fraction of the width it settles at. */
    const val ARC_START_WIDTH: Float = 0.69f

    /** Its height at that moment, as a fraction of its own width. */
    const val ARC_START_RATIO: Float = 0.2f

    /** How far that dropdown blurs its own contents on the way out, in dp. */
    const val ARC_EXIT_BLUR_DP: Float = 30f

    /**
     * How far the size has to travel before the panel is shown at all.
     *
     * Roughly 30ms in. By then the opacity is already at about 60%, so what appears is a legible
     * capsule rather than a tiny transparent speck.
     */
    const val ARC_VISIBLE_FRACTION: Float = 0.1f

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
