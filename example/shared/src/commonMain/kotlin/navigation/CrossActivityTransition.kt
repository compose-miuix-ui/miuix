// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package navigation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.nav.transition.NavGesture
import top.yukonga.miuix.kmp.nav.transition.NavMotion
import top.yukonga.miuix.kmp.nav.transition.NavRole
import top.yukonga.miuix.kmp.nav.transition.NavSettleSpec
import top.yukonga.miuix.kmp.nav.transition.NavSwipeEdge
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.navDirectionalTransition
import top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition
import kotlin.math.abs
import kotlin.math.min

/**
 * Cubic approximation of the reference window-animation interpolator (`fast_out_extra_slow_in`):
 * a fast start with a long gentle tail. The reference ships a two-segment path; a single cubic
 * keeps the perceived pacing without a path evaluator.
 */
private val FastOutExtraSlowIn: Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f)

/**
 * Fraction-of-travel window where the reference 83ms alpha ramp sits once the 450ms eased
 * timeline is mapped onto the depth axis: the ramp occupies ≈8%-26% of wall time, which the
 * fast-start easing has already converted into ≈18%-55% of the travelled distance.
 */
private const val CLASSIC_FADE_START = 0.18f
private const val CLASSIC_FADE_SPAN = 0.37f

/** Programmatic timing of the classic window animations: a fixed 450ms eased tween. */
private val ClassicMotion = NavMotion(
    programmatic = NavSettleSpec.Tween(durationMillis = 450, easing = FastOutExtraSlowIn),
)

/**
 * Classic activity open (push): the entering page slides in from [CrossActivityDrift] to the
 * physical right of its resting place while fading in early in the motion (the reference
 * `activity_open_enter`: a 96dp translate over 450ms with a short alpha ramp near the start).
 * The page being covered stays parked, like the reference open/close pairs. No scale anywhere —
 * the card treatment belongs exclusively to the gesture branch.
 */
private val ClassicActivityOpen: NavTransition = navGraphicsTransition(motion = ClassicMotion) { scope ->
    val d = scope.relativeDepth
    if (d <= 0f) {
        val p = topProgress(d)
        translationX = (1f - p) * with(scope.density) { CrossActivityDrift.toPx() }
        alpha = ((p - CLASSIC_FADE_START) / CLASSIC_FADE_SPAN).coerceIn(0f, 1f)
    }
}

/**
 * Classic activity close (pop): the leaving page slides out [CrossActivityDrift] to the physical
 * right while fading out early in the motion (the reference `activity_close_exit`: the page is
 * gone visually within the first fifth of the 450ms window, then keeps sliding invisibly). The
 * revealed page below stays parked. Mirrors [ClassicActivityOpen] on the time axis, which is the
 * opposite end of the depth axis: early time on a pop means `p` still near 1.
 */
private val ClassicActivityClose: NavTransition = navGraphicsTransition(motion = ClassicMotion) { scope ->
    val d = scope.relativeDepth
    if (d <= 0f) {
        val p = topProgress(d)
        translationX = (1f - p) * with(scope.density) { CrossActivityDrift.toPx() }
        alpha = ((p - (1f - CLASSIC_FADE_START - CLASSIC_FADE_SPAN)) / CLASSIC_FADE_SPAN).coerceIn(0f, 1f)
    }
}

/**
 * Gesture-driven cross-activity card, transcribed from the reference `CrossActivityBackAnimation`
 * / `DefaultCrossActivityBackAnimation` (AOSP WM Shell):
 *
 * - **Pre-commit (finger down)**: the top card scales **centered** toward
 *   [CROSS_ACTIVITY_MIN_SCALE] (0.9, the reference `MAX_SCALE`) with **no fade**; any gesture
 *   that did not start from the RIGHT edge additionally offsets the card so its right side hugs
 *   the screen edge minus [CrossActivityEdgeMargin] (the reference tests `swipeEdge != EDGE_RIGHT`,
 *   so LEFT and edge-less sources both hug; only a RIGHT-edge gesture stays centered). Both
 *   layers ride vertically with the finger: a **damped delta from the initial touch** (the
 *   reference `getYOffset`, defined in the parent class upstream: decelerate-eased, capped at
 *   half the freed vertical space minus the margin). The revealed layer below stays **parked**
 *   [CrossActivityDrift] behind (physical left) for the whole gesture — it scales **in sync**
 *   with the card around its own center, so it barely moves until release.
 * - **Post-commit (released)**: the card grows **back to full size** while flying a further
 *   [CrossActivityDrift] out (the reference sets its target rect to the full bounds offset
 *   right), fading away early in the sweep (`alpha = 1 - 3.5·post` on the depth axis — the
 *   reference fades at 5x over the first 20% of its fixed 450ms wall clock; the settle covers
 *   depth faster early on, so 3.5x lands on the same wall-clock window). The revealed layer
 *   grows back to full size and slides to rest. The frozen
 *   [top.yukonga.miuix.kmp.nav.transition.NavTransitionScope.gesture] marks the release point,
 *   so while the finger still drives, the post fraction is exactly 0 and the pre-commit mapping
 *   holds — the two phases connect without a jump.
 * - **Commit physics**: an underdamped spring seeded with the release velocity
 *   (`dampingRatio 0.8`, `stiffness 280`, overshoot unclamped) — from rest it settles in roughly
 *   the reference 450ms with a subtle (~1.5%) overshoot, and a flung release bounces in
 *   proportion to the throw, like the reference fling behavior.
 * - **Scrim**: holds steady while the finger drives and fades only across the post-commit sweep
 *   (the reference card-animation scrim), declared via the `scrim` curve below; the darkness cap
 *   stays on `NavDisplayEffects.dimAmount`.
 *
 * Directions are physical (not mirrored for RTL), like the reference. Corner clipping is the
 * effects layer — pair with `NavDisplayEffects(cornerClipMode = NavCornerClipMode.All,
 * cornerClipRadius = rememberNavSystemCornerRadius())` to round all four corners with the
 * screen radius, exactly like the reference's `setCornerRadius(windowCornerRadius)`.
 */
private val CrossActivityPredictive: NavTransition = navGraphicsTransition(
    opaqueDepth = 1f,
    motion = NavMotion(
        commit = NavSettleSpec.Spring(dampingRatio = 0.8f, stiffness = 280f, clampOvershoot = false),
    ),
    scrim = { scope ->
        // The reference scrim holds at full strength for the whole gesture (the covered layer's
        // depth equals the remaining gesture progress, so the ratio stays 1) and fades out only
        // across the post-commit sweep; a programmatic pop follows the depth linearly, which is
        // exactly the all-post-commit case.
        val d = scope.relativeDepth.coerceIn(0f, 1f)
        val g = scope.gesture
        if (g != null) (d / (1f - g.progress).coerceAtLeast(0.01f)).coerceIn(0f, 1f) else d
    },
) { scope ->
    val d = scope.relativeDepth
    val gesture = scope.gesture
    val driftPx = with(scope.density) { CrossActivityDrift.toPx() }
    if (d <= 0f) {
        val p = topProgress(d) // 0 off-edge, 1 at top
        scaleX = CROSS_ACTIVITY_MIN_SCALE + (1f - CROSS_ACTIVITY_MIN_SCALE) * p
        scaleY = scaleX
        // Any gesture not starting from the RIGHT edge hugs the card's right side against the
        // screen edge minus the display-bounds margin; only a RIGHT-edge gesture scales centered
        // (the reference tests swipeEdge != EDGE_RIGHT, so edge-less sources hug too).
        var tx = 0f
        if (gesture?.swipeEdge != NavSwipeEdge.Right) {
            val hugMax = scope.layoutSize.width.toFloat() * (1f - CROSS_ACTIVITY_MIN_SCALE) / 2f -
                with(scope.density) { CrossActivityEdgeMargin.toPx() }
            tx += (1f - p) * hugMax.coerceAtLeast(0f)
        }
        if (scope.role == NavRole.Outgoing) {
            // Committed exit: the card grows BACK toward full size while flying a further
            // CrossActivityDrift toward the physical right (the reference commit sets the target
            // rect to the full bounds offset right of the commit-time rect) and fades out early
            // in the post-commit sweep. While the finger still drives, p == release progress and
            // the post fraction stays 0 (the reference applies no alpha pre-commit).
            val releaseP = if (gesture != null) (1f - gesture.progress).coerceAtLeast(0.01f) else 1f
            val post = (1f - p / releaseP).coerceIn(0f, 1f)
            val committedScale = CROSS_ACTIVITY_MIN_SCALE + (1f - CROSS_ACTIVITY_MIN_SCALE) * releaseP
            val grown = committedScale + (1f - committedScale) * post
            scaleX = grown
            scaleY = grown
            alpha = (1f - post * 3.5f).coerceAtLeast(0f)
            tx += post * driftPx
        } else {
            // While a gesture drives (or settles a cancel), the card stays FULLY OPAQUE at any
            // depth — the reference never touches alpha pre-commit. The quick fade-in applies
            // only to a gesture-less entering state (this branch standalone). Without this guard
            // a deep drag (system progress approaches 1 quickly) would run the fade-in formula
            // backwards and turn the card translucent mid-drag, then flash back to opaque the
            // instant a commit flips the role to Outgoing (post = 0 -> alpha 1).
            alpha = if (gesture != null) 1f else (p / 0.2f).coerceIn(0f, 1f)
        }
        translationX = tx
        translationY = crossActivityYShift(gesture, scope.layoutSize.height.toFloat(), scaleX, scope.density)
    } else {
        val dc = coverProgress(d) // 0 at top, 1 covered
        // Post-commit fraction: 0 while the finger drives (the reference keeps the revealed
        // layer parked at its behind-offset during the whole gesture — it scales centered, so
        // its position barely moves), growing only once the commit settle passes the release
        // point. A gesture-less transition is all post-commit.
        val post = if (gesture != null) {
            val releaseProgress = gesture.progress
            if (releaseProgress >= 1f) {
                1f
            } else {
                (((1f - dc) - releaseProgress) / (1f - releaseProgress)).coerceIn(0f, 1f)
            }
        } else {
            1f - dc
        }
        // Revealed layer: parked CrossActivityDrift behind (physical left) through the
        // gesture; slides to rest only across the post-commit sweep.
        translationX = -(1f - post) * driftPx
        if (gesture != null) {
            // Scales in sync with the card while the finger drives; grows back to full size
            // across the post-commit sweep (release point from the frozen gesture context).
            val liveScale = CROSS_ACTIVITY_MIN_SCALE + (1f - CROSS_ACTIVITY_MIN_SCALE) * dc
            scaleX = liveScale + (1f - liveScale) * post
            scaleY = scaleX
        }
        // The revealed layer rides with the finger too (reference allowEnteringYShift); the
        // shift collapses with its scale returning to full, so it lands at rest untranslated.
        translationY = crossActivityYShift(gesture, scope.layoutSize.height.toFloat(), scaleX, scope.density)
    }
}

/**
 * Platform cross-activity feel, transcribed from the reference implementation (AOSP WM Shell)
 * as a directional composition — the platform keeps two separate effect systems and so does this
 * preset:
 *
 * - **Programmatic push/pop** ([ClassicActivityOpen] / [ClassicActivityClose]): the classic
 *   window animations — a full-size 96dp slide with a short early fade over a fixed 450ms eased
 *   timeline. No card, no scale: a back-button pop on the platform never runs the gesture
 *   animation.
 * - **Predictive back / edge swipe** ([CrossActivityPredictive]): the gesture-scaled card with
 *   the hold-then-fade scrim, a grow-back-to-full-size fly-out on commit, and a velocity-seeded
 *   underdamped commit spring (a hard fling visibly bounces, like the reference).
 *
 * Lives in the example app — NOT in miuix-nav — as a demonstration that the public customization
 * surface (`navDirectionalTransition` + `navGraphicsTransition` + `NavMotion` +
 * [top.yukonga.miuix.kmp.nav.transition.NavTransitionScope]) is sufficient to express a complete
 * platform-grade transition pair entirely outside the library.
 *
 * Values sourced from the reference: `MAX_SCALE = 0.9`, the 96dp entering start offset, and the
 * 8dp display-bounds margin (named after the upstream `cross_task_back_vertical_margin` dimen);
 * the vertical-follow formula transcribes the upstream parent-class `getYOffset`.
 */
val CrossActivityTransition: NavTransition = navDirectionalTransition(
    push = ClassicActivityOpen,
    pop = ClassicActivityClose,
    predictivePop = CrossActivityPredictive,
)

/**
 * Vertical follow of the cross-activity layers (the reference `getYOffset`): a damped delta
 * from the initial touch — decelerate-eased over half the layout height — capped at the
 * vertical space freed by the current scale minus the display-bounds margin. The cap
 * collapses as the layer returns to full size, so the shift settles back to zero without any
 * extra bookkeeping.
 */
private fun crossActivityYShift(
    gesture: NavGesture?,
    height: Float,
    scale: Float,
    density: Density,
): Float {
    if (gesture == null || height <= 0f) return 0f
    val rawDelta = gesture.touchY - gesture.initialTouchY
    val half = height / 2f
    val ratio = min(half, abs(rawDelta)) / half
    val damped = 1f - (1f - ratio) * (1f - ratio) // DecelerateInterpolator(1f)
    val marginPx = with(density) { CrossActivityEdgeMargin.toPx() }
    val maxShift = ((height - height * scale) / 2f - marginPx).coerceAtLeast(0f)
    return maxShift * damped * (if (rawDelta < 0f) -1f else 1f)
}

/**
 * Linear progress of the "entering/leaving the top" segment: maps d in [-1, 0] -> [0, 1] where
 * 1 means fully at the top (d = 0) and 0 means fully off the leading edge (d = -1).
 */
private fun topProgress(d: Float): Float = (1f + d).coerceIn(0f, 1f)

/**
 * Linear progress of the "covered" segment: maps d in [0, 1] -> [0, 1] where 0 means fully at
 * the top (d = 0) and 1 means fully covered by the layer above (d = 1).
 */
private fun coverProgress(d: Float): Float = d.coerceIn(0f, 1f)

/** Smallest scale of the cross-activity card (the reference `MAX_SCALE = 0.9`). */
private const val CROSS_ACTIVITY_MIN_SCALE = 0.9f

/**
 * Behind-offset of the revealed layer, post-commit fly-out distance of the card, and slide
 * distance of the classic open/close pair (the reference
 * `cross_activity_back_entering_start_offset = 96dp`, matching the classic translate distance).
 */
private val CrossActivityDrift = 96.dp

/**
 * Margin kept between the hugging card edge and the screen edge for non-RIGHT-edge gestures
 * (the reference `cross_task_back_vertical_margin = 8dp`, reused as the display-bounds margin).
 */
private val CrossActivityEdgeMargin = 8.dp
