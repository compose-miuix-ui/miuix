// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package navigation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastRoundToInt
import top.yukonga.miuix.kmp.nav.transition.NavGesture
import top.yukonga.miuix.kmp.nav.transition.NavMotion
import top.yukonga.miuix.kmp.nav.transition.NavRole
import top.yukonga.miuix.kmp.nav.transition.NavSettle
import top.yukonga.miuix.kmp.nav.transition.NavSettlePhase
import top.yukonga.miuix.kmp.nav.transition.NavSettleSpec
import top.yukonga.miuix.kmp.nav.transition.NavSwipeEdge
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.navDirectionalTransition
import top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Exact port of the reference window-animation interpolator (`fast_out_extra_slow_in`), a
 * two-segment cubic path: `M 0,0 C 0.05,0 0.133333,0.06 0.166666,0.4` then
 * `C 0.208333,0.82 0.25,1 1,1`. Each segment is evaluated as a unit-square cubic (the same
 * solver [CubicBezierEasing] uses) and rescaled back into its sub-rectangle, so the curve
 * matches the reference point for point — a single-cubic approximation deviates by up to 0.14
 * in the mid-section, which visibly slows the slide's front. The reference post-commit
 * interpolator is the same curve.
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

/** The reference pre-commit gesture interpolator (decelerate with a slight acceleration start). */
private val BackGestureEasing: Easing = CubicBezierEasing(0.1f, 0.1f, 0f, 1f)

/** Reference fling-bounce spring on the scale-x100 axis. */
private const val BOUNCE_STIFFNESS = 200f
private const val BOUNCE_DAMPING = 0.75f
private const val BOUNCE_MAX_KICK = 1000f
private const val BOUNCE_MIN_KICK = 120f

/**
 * Closed-form reference fling bounce: the underdamped spring response to the commit kick,
 * `-(kick/omegaD) * exp(-zeta*omega*t) * sin(omegaD*t)` on the scale-x100 axis, multiplied onto
 * both layers' scale and capped at 1 (shrink-then-recover, never enlarging). The kick stays in
 * the PROGRESS-velocity domain (the reference feeds its gesture-progress velocity straight into
 * the spring): `|v| * 100 * (1 - 0.9)`, doubled for edge swipes, clamped to [0, 1000], floored
 * at 120 for near-instant commits so even a tap-back bounces. A hard fling (~2-4 progress/s)
 * lands a subtle ~1-3% dip and the floor ~3.7% — converting to px/s would slam every commit
 * into the 1000 cap (a ~31% monster dip).
 */
private fun bounceScale(settle: NavSettle?, gesture: NavGesture?): Float {
    if (settle == null || settle.phase != NavSettlePhase.Commit || gesture == null) return 1f
    val factor = if (gesture.swipeEdge != NavSwipeEdge.None) 2f else 1f
    val floorKick = if (gesture.progress < 0.1f) BOUNCE_MIN_KICK else 0f
    val kick = (abs(settle.releaseVelocity) * 100f * (1f - CROSS_ACTIVITY_MIN_SCALE) * factor)
        .coerceIn(floorKick, BOUNCE_MAX_KICK)
    if (kick <= 0f) return 1f
    val omega = sqrt(BOUNCE_STIFFNESS)
    val omegaD = omega * sqrt(1f - BOUNCE_DAMPING * BOUNCE_DAMPING)
    val t = settle.elapsedMillis / 1000f
    val overlay = -(kick / omegaD) * exp(-BOUNCE_DAMPING * omega * t) * sin(omegaD * t)
    return ((100f + overlay) / 100f).coerceAtMost(1f)
}

/**
 * Pre-commit gesture shaping on the pop-travel axis: the reference eases the delivered gesture
 * progress (not the depth) before its rect lerp, so the card front-loads its travel. Identity
 * when no gesture drives.
 */
private fun shapedTopProgress(p: Float, gesture: NavGesture?): Float = if (gesture == null) p else 1f - BackGestureEasing.transform((1f - p).coerceIn(0f, 1f))

/**
 * Fraction-of-travel FALLBACK windows for frames without a settle context (e.g. a mid-grab
 * frame): the exact 83ms wall-clock alpha ramps ride [NavSettle.elapsedMillis]; these travel
 * windows only place the ramp endpoints correctly on the eased 450ms timeline (open fade-in
 * `[50ms, 133ms]` -> travel `[0.12, 0.83]`; close fade-out `[35ms, 118ms]` -> travel
 * `[0.05, 0.79]`) — mid-window the wall-clock profile differs, which is why the time track is
 * preferred whenever available.
 */
private const val OPEN_FADE_START = 0.12f
private const val OPEN_FADE_SPAN = 0.71f
private const val CLOSE_FADE_START = 0.21f
private const val CLOSE_FADE_SPAN = 0.74f

/** Reference alpha-track timing: an 83ms linear ramp offset 50ms (open) / 35ms (close). */
private const val CLASSIC_FADE_DURATION = 83f
private const val OPEN_FADE_OFFSET = 50f
private const val CLOSE_FADE_OFFSET = 35f

/** Programmatic timing of the classic window animations: a fixed 450ms eased tween. */
private val ClassicMotion = NavMotion(
    programmatic = NavSettleSpec.Tween(durationMillis = 450, easing = FastOutExtraSlowIn),
)

/**
 * Classic activity open (push), the reference `activity_open_enter` / `activity_open_exit`
 * pair: the entering page slides in from [CrossActivityDrift] to the physical right while
 * fading in on its own 83ms wall-clock alpha track (offset 50ms, linear in time, decoupled from
 * the motion easing — exactly the reference's separate alpha element); the page being covered
 * slides out the SAME distance to the physical left at full opacity (`open_exit` keeps alpha at
 * 1 throughout). No dim and no scale — the card treatment belongs exclusively to the gesture
 * branch. (The reference `extend` elements paint window edges beyond their bounds during the
 * slide; the host's backdrop fill stands in for that here.)
 */
private val ClassicActivityOpen: NavTransition = navGraphicsTransition(
    motion = ClassicMotion,
    scrim = { 0f },
) { scope ->
    val d = scope.relativeDepth
    val driftPx = with(scope.density) { CrossActivityDrift.toPx() }
    if (d <= 0f) {
        val p = topProgress(d)
        // Pixel-snapped so the corner-clipped edge does not shimmer at fractional X.
        translationX = ((1f - p) * driftPx).fastRoundToInt().toFloat()
        // The alpha ramp belongs to the ANIMATING window only (the reference open_enter targets
        // the opening activity; the page about to be covered runs open_exit at alpha 1). The
        // wall-clock ramp is depth-blind, so without the role gate it would blank the resident
        // top for the first frames of a push while its depth still sits at exactly 0.
        alpha = if (scope.role == NavRole.Incoming) {
            val settle = scope.settle
            if (settle != null) {
                ((settle.elapsedMillis - OPEN_FADE_OFFSET) / CLASSIC_FADE_DURATION).coerceIn(0f, 1f)
            } else {
                ((p - OPEN_FADE_START) / OPEN_FADE_SPAN).coerceIn(0f, 1f)
            }
        } else {
            1f
        }
    } else {
        // Covered page: slides 0 -> -96dp as it is covered (open_exit), never fading.
        translationX = (-coverProgress(d) * driftPx).fastRoundToInt().toFloat()
    }
}

/**
 * Classic activity close (pop), the reference `activity_close_exit` / `activity_close_enter`
 * pair: the leaving page slides out [CrossActivityDrift] to the physical right while fading out
 * on its own 83ms wall-clock alpha track (offset 35ms — gone within the first fifth of the
 * window, then it keeps sliding invisibly); the revealed page below slides in from
 * -[CrossActivityDrift] (physical left) to rest at full opacity (`close_enter` keeps alpha at 1
 * throughout). No dim, no scale.
 */
private val ClassicActivityClose: NavTransition = navGraphicsTransition(
    motion = ClassicMotion,
    scrim = { 0f },
) { scope ->
    val d = scope.relativeDepth
    val driftPx = with(scope.density) { CrossActivityDrift.toPx() }
    if (d <= 0f) {
        val p = topProgress(d)
        // Pixel-snapped so the corner-clipped edge does not shimmer at fractional X.
        translationX = ((1f - p) * driftPx).fastRoundToInt().toFloat()
        // Role-gated like the open ramp: only the LEAVING page fades (the reference
        // close_exit); the revealed page reaching the top at the end of the pop would
        // otherwise hit the exhausted wall-clock ramp (1 - (450-35)/83 < 0) and blank out.
        alpha = if (scope.role == NavRole.Outgoing) {
            val settle = scope.settle
            if (settle != null) {
                (1f - (settle.elapsedMillis - CLOSE_FADE_OFFSET) / CLASSIC_FADE_DURATION).coerceIn(0f, 1f)
            } else {
                ((p - CLOSE_FADE_START) / CLOSE_FADE_SPAN).coerceIn(0f, 1f)
            }
        } else {
            1f
        }
    } else {
        // Revealed page: parked at -96dp while covered, slides to rest as the top leaves
        // (close_enter), never fading.
        translationX = (-coverProgress(d) * driftPx).fastRoundToInt().toFloat()
    }
}

/**
 * Gesture-driven cross-activity card, transcribed from the reference `CrossActivityBackAnimation`
 * / `DefaultCrossActivityBackAnimation` (AOSP WM Shell) with the reference's exact two-channel
 * decomposition:
 *
 * - **Pre-commit (finger down)**: gesture progress is shaped by [BackGestureEasing] before it
 *   drives geometry (the reference applies its gesture interpolator ahead of the rect lerp), so
 *   the card front-loads its travel. The card scales **centered** toward
 *   [CROSS_ACTIVITY_MIN_SCALE] (0.9, the reference `MAX_SCALE`) with **no fade**; any gesture
 *   not starting from the RIGHT edge offsets the card so its right side hugs the screen edge
 *   minus [CrossActivityEdgeMargin] (`swipeEdge != EDGE_RIGHT`). Both layers ride vertically
 *   with the finger (the reference `getYOffset`); the revealed layer stays parked
 *   [CrossActivityDrift] behind and scales in sync.
 * - **Post-commit (released)**: the main track is a fixed 450ms tween on the reference curve
 *   ([NavMotion.commit] = `Tween(450, FastOutExtraSlowIn)` — velocity never enters the track),
 *   along which the card grows from its EASED commit pose back to full size while flying a
 *   further [CrossActivityDrift] out with the hug frozen at its commit value; the card fades on
 *   the **wall clock** (`1 - 5·t/450`, fully gone after exactly 90ms) and the scrim fades
 *   linearly over the same clock. The velocity-scaled **bounce** is a separate closed-form
 *   spring overlay ([bounceScale]) multiplied onto both layers' scale, exactly the reference
 *   `FLING_BOUNCE` channel (stiffness 200, damping 0.75, kick capped at 1000 and floored at 120
 *   for near-instant commits, doubled for edge swipes).
 * - **Cancel**: rides a stiff no-bounce spring ([NavMotion.cancel] = `Spring(1500)`), matching
 *   the reference progress-spring snap-back; geometry retraces the same eased mapping.
 *
 * Directions are physical (not mirrored for RTL), like the reference. Corner clipping is the
 * effects layer — pair with `NavDisplayEffects(cornerClipMode = NavCornerClipMode.All,
 * cornerClipRadius = rememberNavSystemCornerRadius())` to round all four corners with the
 * screen radius, exactly like the reference's `setCornerRadius(windowCornerRadius)`.
 */
private val CrossActivityPredictive: NavTransition = navGraphicsTransition(
    opaqueDepth = 1f,
    motion = NavMotion(
        commit = NavSettleSpec.Tween(durationMillis = 450, easing = FastOutExtraSlowIn),
        cancel = NavSettleSpec.Spring(stiffness = 1500f),
    ),
    scrim = { scope ->
        val s = scope.settle
        val g = scope.gesture
        when {
            // Post-commit: linear fade on the 450ms wall clock (the reference scrim track).
            s?.phase == NavSettlePhase.Commit -> (1f - s.elapsedMillis / 450f).coerceIn(0f, 1f)

            // Finger driving (and the cancel settle, context frozen): hold at full strength.
            g != null -> (scope.relativeDepth.coerceIn(0f, 1f) / (1f - g.progress).coerceAtLeast(MIN_RELEASE_SPAN)).coerceIn(0f, 1f)

            else -> scope.relativeDepth.coerceIn(0f, 1f)
        }
    },
) { scope ->
    val d = scope.relativeDepth
    val gesture = scope.gesture
    val settle = scope.settle
    val committing = settle?.phase == NavSettlePhase.Commit
    val widthPx = scope.layoutSize.width.toFloat()
    val heightPx = scope.layoutSize.height.toFloat()
    val driftPx = with(scope.density) { CrossActivityDrift.toPx() }
    val bounce = bounceScale(settle, gesture)
    val hugMax = (
        widthPx * (1f - CROSS_ACTIVITY_MIN_SCALE) / 2f -
            with(scope.density) { CrossActivityEdgeMargin.toPx() }
        ).coerceAtLeast(0f)
    val hugs = gesture?.swipeEdge != NavSwipeEdge.Right
    if (d <= 0f) {
        val p = topProgress(d) // raw travel: 1 at top, 0 fully popped
        if (scope.role == NavRole.Outgoing && committing && gesture != null) {
            // Post-commit closing card: with the tween track, `post` IS the track's eased time
            // fraction, so this lerp is point-for-point the reference rect lerp — from the EASED
            // commit pose back to full size while flying out, hug frozen at its commit value
            // (the reference offsets the commit-time rect; it never re-centers).
            val releaseP = (1f - gesture.progress).coerceAtLeast(MIN_RELEASE_SPAN)
            val post = (1f - p / releaseP).coerceIn(0f, 1f)
            val releasePE = shapedTopProgress(releaseP, gesture)
            val committedScale = CROSS_ACTIVITY_MIN_SCALE + (1f - CROSS_ACTIVITY_MIN_SCALE) * releasePE
            val grown = committedScale + (1f - committedScale) * post
            scaleX = snapScaleToPixelWidth(grown * bounce, widthPx)
            scaleY = scaleX
            var tx = if (hugs) (1f - releasePE) * hugMax else 0f
            tx += post * driftPx
            // The reference card is fully transparent after exactly 90ms of wall time.
            alpha = (1f - 5f * (settle.elapsedMillis / 450f)).coerceAtLeast(0f)
            translationX = snapEdgeTranslation(tx, scaleX, widthPx)
            translationY = snapEdgeTranslation(crossActivityYShift(gesture, heightPx, scaleX, scope.density), scaleX, heightPx)
        } else {
            // Finger driving (or cancel settling back, or a gesture-less standalone use):
            // geometry runs on the EASED travel axis, fully opaque while a gesture drives.
            val pE = shapedTopProgress(p, gesture)
            scaleX = snapScaleToPixelWidth((CROSS_ACTIVITY_MIN_SCALE + (1f - CROSS_ACTIVITY_MIN_SCALE) * pE) * bounce, widthPx)
            scaleY = scaleX
            translationX = snapEdgeTranslation(if (hugs) (1f - pE) * hugMax else 0f, scaleX, widthPx)
            alpha = when {
                scope.role == NavRole.Outgoing && gesture != null -> {
                    // Interrupted-commit fallback (no settle context): keep the depth-axis fade
                    // so a grabbed leaving card never flashes back to full opacity.
                    val releaseP = (1f - gesture.progress).coerceAtLeast(MIN_RELEASE_SPAN)
                    (1f - (1f - p / releaseP).coerceIn(0f, 1f) * 3.5f).coerceAtLeast(0f)
                }

                gesture != null -> 1f

                else -> (p / 0.2f).coerceIn(0f, 1f)
            }
            translationY = snapEdgeTranslation(crossActivityYShift(gesture, heightPx, scaleX, scope.density), scaleX, heightPx)
        }
    } else {
        val dc = coverProgress(d) // raw: 0 at top, 1 covered
        // Post-commit fraction of the revealed layer: 0 while the finger drives, the track's
        // eased time fraction across the commit sweep. A gesture-less transition is all post.
        val post = if (gesture != null) {
            val releaseProgress = gesture.progress
            if (releaseProgress >= 1f) 1f else (((1f - dc) - releaseProgress) / (1f - releaseProgress)).coerceIn(0f, 1f)
        } else {
            1f - dc
        }
        if (gesture != null) {
            // Scales in sync with the card on the eased travel axis while the finger drives;
            // grows back to full size across the post-commit sweep from its EASED commit pose.
            val travel = if (committing) gesture.progress else (1f - dc)
            val eased = BackGestureEasing.transform(travel.coerceIn(0f, 1f))
            val liveScale = CROSS_ACTIVITY_MIN_SCALE + (1f - CROSS_ACTIVITY_MIN_SCALE) * (1f - eased)
            scaleX = snapScaleToPixelWidth((liveScale + (1f - liveScale) * post) * bounce, widthPx)
            scaleY = scaleX
        }
        translationX = snapEdgeTranslation(-(1f - post) * driftPx, scaleX, widthPx)
        // The revealed layer rides with the finger too (reference allowEnteringYShift); the
        // shift collapses with its scale returning to full, so it lands at rest untranslated.
        translationY = snapEdgeTranslation(crossActivityYShift(gesture, heightPx, scaleX, scope.density), scaleX, heightPx)
    }
}

/**
 * Platform cross-activity feel, transcribed from the reference implementation (AOSP WM Shell)
 * as a directional composition — the platform keeps two separate effect systems and so does this
 * preset:
 *
 * - **Programmatic push/pop** ([ClassicActivityOpen] / [ClassicActivityClose]): the classic
 *   window animations — BOTH layers slide 96dp on a fixed 450ms eased timeline, the top fading
 *   on its own 83ms wall-clock alpha track, with no dim and no scale: a back-button pop on the
 *   platform never runs the gesture animation.
 * - **Predictive back / edge swipe** ([CrossActivityPredictive]): the gesture-scaled card with
 *   the reference two-channel commit (fixed 450ms eased track + closed-form velocity bounce
 *   overlay), wall-clock card/scrim fades, gesture-progress shaping, and the stiff reference
 *   cancel spring.
 *
 * Lives in the example app — NOT in miuix-nav — as a demonstration that the public customization
 * surface (`navDirectionalTransition` + `navGraphicsTransition` + `NavMotion` + `NavSettle` +
 * [top.yukonga.miuix.kmp.nav.transition.NavTransitionScope]) is sufficient to express the
 * complete platform transition pair, both channels included, entirely outside the library.
 *
 * Values sourced from the reference: `MAX_SCALE = 0.9`, the 96dp entering start offset, the 8dp
 * display-bounds margin, the fling-bounce spring constants, and the vertical-follow formula
 * (`getYOffset`).
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
 * Quantizes a center-pivot scale so the scaled width spans a whole pixel count, letting
 * [snapEdgeTranslation] align both vertical edges at once.
 */
private fun snapScaleToPixelWidth(scale: Float, width: Float): Float = if (width <= 0f) scale else (scale * width).fastRoundToInt() / width

/**
 * Snaps a center-pivot translation so the layer's leading edge lands on a whole device pixel;
 * the corner-clipped anti-aliased edge otherwise shimmers as a thin line while moving.
 */
private fun snapEdgeTranslation(translation: Float, scale: Float, extent: Float): Float {
    val inset = extent * (1f - scale) / 2f
    return (translation + inset).fastRoundToInt() - inset
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
 * Floor of every `1 - progress` denominator, at/below the library's sub-1 progress cap: a
 * larger floor breaks the drag identity `depth == 1 - progress` near the cap (scrim collapse,
 * revealed-layer snap) on devices that misreport progress past 1.
 */
private const val MIN_RELEASE_SPAN = 0.001f

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
