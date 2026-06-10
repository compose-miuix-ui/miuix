// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package navigation

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.nav.transition.NavGesture
import top.yukonga.miuix.kmp.nav.transition.NavRole
import top.yukonga.miuix.kmp.nav.transition.NavSwipeEdge
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition
import kotlin.math.abs
import kotlin.math.min

/**
 * Platform cross-activity feel, transcribed from the reference `CrossActivityBackAnimation` /
 * `DefaultCrossActivityBackAnimation` (AOSP WM Shell).
 *
 * Lives in the example app — NOT in miuix-nav — as a demonstration that the public customization
 * surface ([navGraphicsTransition] + [top.yukonga.miuix.kmp.nav.transition.NavTransitionScope])
 * is sufficient to express a complete platform-grade transition, including its scrim curve,
 * entirely outside the library:
 *
 * - **Pre-commit (finger down)**: the top card scales **centered** toward
 *   [CROSS_ACTIVITY_MIN_SCALE] (0.9, the reference `MAX_SCALE`) with **no fade**; a LEFT-edge
 *   gesture additionally offsets it so its right side hugs the screen edge minus
 *   [CrossActivityEdgeMargin], while a RIGHT-edge gesture stays centered (reference
 *   behavior). Both layers ride vertically with the finger: a **damped delta from the
 *   initial touch** (the reference `getYOffset`: decelerate-eased, capped at half the freed
 *   vertical space minus the margin). The revealed layer below stays **parked**
 *   [CrossActivityDrift] behind (physical left) for the whole gesture — it scales **in
 *   sync** with the card around its own center, so it barely moves until release.
 * - **Post-commit (released)**: the card flies a further [CrossActivityDrift] out and fades
 *   away within the **first 20%** of the remaining sweep (`alpha = 1 - 5·post`, the reference
 *   curve); the revealed layer grows back to full size and slides to rest. The frozen
 *   [top.yukonga.miuix.kmp.nav.transition.NavTransitionScope.gesture] marks the release point,
 *   so while the finger still drives, the post fraction is exactly 0 and the pre-commit mapping
 *   holds — the two phases connect without a jump. A programmatic pop is all post-commit
 *   (release point = rest), giving the classic back-button close; a push plays the reverse with
 *   a quick fade-in.
 * - **Scrim**: holds steady while the finger drives and fades only across the post-commit sweep
 *   (the reference card-animation scrim), declared via the `scrim` curve below; the darkness cap
 *   stays on `NavDisplayEffects.dimAmount` (the reference dims at 0.2 light / 0.8 dark).
 *
 * Directions are physical (not mirrored for RTL), like the reference. Corner clipping is the
 * effects layer — pair with `NavDisplayEffects(cornerClipMode = NavCornerClipMode.All,
 * cornerClipRadius = rememberNavSystemCornerRadius())` to round all four corners with the
 * screen radius, exactly like the reference's `setCornerRadius(windowCornerRadius)`.
 */
val CrossActivityTransition: NavTransition = navGraphicsTransition(
    opaqueDepth = 1f,
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
        // A LEFT-edge gesture hugs the card's right side against the screen edge minus the
        // display-bounds margin; a RIGHT-edge gesture scales centered (reference behavior).
        var tx = 0f
        if (gesture?.swipeEdge == NavSwipeEdge.Left) {
            val hugMax = scope.layoutSize.width.toFloat() * (1f - CROSS_ACTIVITY_MIN_SCALE) / 2f -
                with(scope.density) { CrossActivityEdgeMargin.toPx() }
            tx += (1f - p) * hugMax.coerceAtLeast(0f)
        }
        if (scope.role == NavRole.Outgoing) {
            // Committed (or programmatic) exit: fly a further CrossActivityDrift toward the
            // physical right (the reference always exits rightward) and fade out at the start
            // of the post-commit sweep. While the finger still drives, p == release progress
            // and the post fraction stays 0 (the reference applies no alpha pre-commit). The
            // reference rate is 5x over the first 20% of its fixed 450ms (i.e. 90ms of wall
            // time); our post fraction runs on the depth axis, which the settle covers faster
            // early on, so 3.5x (~29% of the sweep) lands on the same wall-clock window.
            val releaseP = if (gesture != null) (1f - gesture.progress).coerceAtLeast(0.01f) else 1f
            val post = (1f - p / releaseP).coerceIn(0f, 1f)
            alpha = (1f - post * 3.5f).coerceAtLeast(0f)
            tx += post * driftPx
        } else {
            // While a gesture drives (or settles a cancel), the card stays FULLY OPAQUE at any
            // depth — the reference never touches alpha pre-commit. The quick fade-in applies
            // only to a programmatic push. Without this guard a deep drag (system progress
            // approaches 1 quickly) would run the fade-in formula backwards and turn the card
            // translucent mid-drag, then flash back to opaque the instant a commit flips the
            // role to Outgoing (post = 0 -> alpha 1).
            alpha = if (gesture != null) 1f else (p / 0.2f).coerceIn(0f, 1f)
        }
        translationX = tx
        translationY = crossActivityYShift(gesture, scope.layoutSize.height.toFloat(), scaleX, scope.density)
    } else {
        val dc = coverProgress(d) // 0 at top, 1 covered
        // Post-commit fraction: 0 while the finger drives (the reference keeps the revealed
        // layer parked at its behind-offset during the whole gesture — it scales centered, so
        // its position barely moves), growing only once the commit settle passes the release
        // point. A programmatic transition is all post-commit.
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
 * Behind-offset of the revealed layer and post-commit fly-out distance of the card (the
 * reference `cross_activity_back_entering_start_offset = 96dp`).
 */
private val CrossActivityDrift = 96.dp

/**
 * Margin kept between the hugging card edge and the screen edge for LEFT-edge gestures (the
 * reference `cross_task_back_vertical_margin = 8dp`, reused as the display-bounds margin).
 */
private val CrossActivityEdgeMargin = 8.dp
