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
 * Exact port of the reference window-animation interpolator (`fast_out_extra_slow_in`), a
 * two-segment cubic path: `M 0,0 C 0.05,0 0.133333,0.06 0.166666,0.4` then
 * `C 0.208333,0.82 0.25,1 1,1`. Each segment is evaluated as a unit-square cubic (the same
 * solver [CubicBezierEasing] uses) and rescaled back into its sub-rectangle, so the curve
 * matches the reference point for point — a single-cubic approximation deviates by up to 0.14
 * in the mid-section, which visibly slows the slide's front.
 */
private val FastOutExtraSlowIn: Easing = run {
    val knotX = 0.166666f
    val knotY = 0.4f
    // Segment control points normalized into each segment's unit square.
    val first = CubicBezierEasing(0.05f / knotX, 0f, 0.133333f / knotX, 0.06f / knotY)
    val second = CubicBezierEasing(
        (0.208333f - knotX) / (1f - knotX),
        (0.82f - knotY) / (1f - knotY),
        (0.25f - knotX) / (1f - knotX),
        (1f - knotY) / (1f - knotY),
    )
    Easing { fraction ->
        if (fraction < knotX) {
            knotY * first.transform(fraction / knotX)
        } else {
            knotY + (1f - knotY) * second.transform((fraction - knotX) / (1f - knotX))
        }
    }
}

/**
 * Fraction-of-travel windows where the reference 83ms linear-time alpha ramps sit once the 450ms
 * `fast_out_extra_slow_in` timeline is mapped onto the travel axis. The easing front-loads the
 * motion, so the short time windows stretch wide on the travel axis (verified frame-by-frame
 * against a stock recording): open fade-in `[50ms, 133ms]` -> travel `[0.12, 0.83]`; close
 * fade-out `[35ms, 118ms]` -> travel `[0.05, 0.79]`. Alpha is linear in time in the reference;
 * linear-in-travel matches it within ~1% across the window.
 */
private const val OPEN_FADE_START = 0.12f
private const val OPEN_FADE_SPAN = 0.71f
private const val CLOSE_FADE_START = 0.21f
private const val CLOSE_FADE_SPAN = 0.74f

/** Programmatic timing of the classic window animations: a fixed 450ms eased tween. */
private val ClassicMotion = NavMotion(
    programmatic = NavSettleSpec.Tween(durationMillis = 450, easing = FastOutExtraSlowIn),
)

/**
 * Classic activity open (push), the reference `activity_open_enter` / `activity_open_exit`
 * pair: the entering page slides in from [CrossActivityDrift] to the physical right while
 * fading in across the front of the motion; the page being covered slides out the SAME distance
 * to the physical left at full opacity (`open_exit` keeps alpha at 1 throughout). Both layers
 * ride the same 450ms eased timeline; there is no dim and no scale — the card treatment belongs
 * exclusively to the gesture branch. (The reference `extend` elements paint window edges beyond
 * their bounds during the slide; the host's backdrop fill stands in for that here.)
 */
private val ClassicActivityOpen: NavTransition = navGraphicsTransition(
    motion = ClassicMotion,
    scrim = { 0f },
) { scope ->
    val d = scope.relativeDepth
    val driftPx = with(scope.density) { CrossActivityDrift.toPx() }
    if (d <= 0f) {
        val p = topProgress(d)
        translationX = (1f - p) * driftPx
        alpha = ((p - OPEN_FADE_START) / OPEN_FADE_SPAN).coerceIn(0f, 1f)
    } else {
        // Covered page: slides 0 -> -96dp as it is covered (open_exit), never fading.
        translationX = -coverProgress(d) * driftPx
    }
}

/**
 * Classic activity close (pop), the reference `activity_close_exit` / `activity_close_enter`
 * pair: the leaving page slides out [CrossActivityDrift] to the physical right while fading out
 * across the front of the motion (early in TIME means `p` still near 1 on the depth axis); the
 * revealed page below slides in from -[CrossActivityDrift] (physical left) to rest at full
 * opacity (`close_enter` keeps alpha at 1 throughout). No dim, no scale.
 */
private val ClassicActivityClose: NavTransition = navGraphicsTransition(
    motion = ClassicMotion,
    scrim = { 0f },
) { scope ->
    val d = scope.relativeDepth
    val driftPx = with(scope.density) { CrossActivityDrift.toPx() }
    if (d <= 0f) {
        val p = topProgress(d)
        translationX = (1f - p) * driftPx
        alpha = ((p - CLOSE_FADE_START) / CLOSE_FADE_SPAN).coerceIn(0f, 1f)
    } else {
        // Revealed page: parked at -96dp while covered, slides to rest as the top leaves
        // (close_enter), never fading.
        translationX = -coverProgress(d) * driftPx
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
 *   window animations — BOTH layers slide 96dp on a fixed 450ms eased timeline (the top fades
 *   across the front of the motion, the other layer stays fully opaque), with no dim and no
 *   scale: a back-button pop on the platform never runs the gesture animation.
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
