// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * Preset library of [NavTransition]s, all implemented as pure functions of the single float
 * driver [NavTransitionScope.relativeDepth] (d = animatedTop - entryIndex).
 *
 * Every preset maps `d` to spatial graphics-layer properties only (translationX / scaleX /
 * scaleY / alpha / clip / shape) and reads `d` deferred inside the [navGraphicsTransition]
 * block, so animations cost zero recomposition. The same `d -> visual` mapping is reused by
 * both the spring-convergence path and the interactive gesture path (predictive back / edge
 * swipe), so no preset needs a separate predictive-back animation.
 *
 * Depth segments handled by each preset:
 * - `d <= 0`  : this entry is entering/leaving the top (use its own transition).
 * - `0 < d <= opaqueDepth` : this entry is covered by an upper entry (covered treatment).
 *
 * Slide-style presets keep `translation` strictly linear in `d` so that during a finger-driven
 * `snapTo`, the visual follows the finger 1:1 (§7.1). Spatial easing (parallax / dim curves) may
 * be non-linear because it is identical in both drive modes.
 */
object NavTransitions {

    /**
     * Default preset. iOS-style horizontal slide: the entering page slides in from the trailing
     * edge (d: -1 -> 0, translationX: +width -> 0), and the covered page parallaxes a quarter
     * width toward the leading edge while dimming slightly (d: 0 -> 1).
     *
     * Geometry is strictly linear in `d` for 1:1 finger tracking. The dark scrim is left to
     * [NavDisplayEffects.dimAmount] (orthogonal effect); this preset only applies a light alpha
     * falloff on the covered layer so the two never double-darken.
     */
    val Cupertino: NavTransition = navGraphicsTransition(opaqueDepth = 1f) { scope ->
        val width = scope.layoutSize.width.toFloat()
        val d = scope.relativeDepth
        translationX = when {
            d <= 0f -> {
                // Entering/leaving the top: slide from the trailing edge. Mirrored for RTL.
                val sign = if (scope.layoutDirection == LayoutDirection.Rtl) -1f else 1f
                sign * (-d).coerceIn(0f, 1f) * width
            }

            else -> {
                // Covered: parallax a quarter width toward the leading edge.
                val sign = if (scope.layoutDirection == LayoutDirection.Rtl) 1f else -1f
                sign * coverProgress(d) * width * 0.25f
            }
        }
        // Light alpha falloff while covered (scrim handled separately by NavDisplayEffects).
        alpha = if (d > 0f) 1f - 0.1f * coverProgress(d) else 1f
    }

    /**
     * Reproduces the established miuix navigation feel: a full-width horizontal slide (same geometry
     * as [Cupertino]) with a quarter-width parallax and light alpha falloff on the covered page.
     *
     * The leading-edge corner clip and the dark scrim are NOT baked into this transition — they are
     * the orthogonal [NavDisplayEffects] layer (`enableCornerClip` + `cornerClipRadius`, `dimAmount`),
     * mirroring the reference implementation where the clip is an effect applied to the entering top.
     * Keeping it out here lets the clip radius follow the platform screen corner instead of a fixed
     * value, and avoids double-clipping when both a transition and the effect are in play.
     */
    val MiuixDefault: NavTransition = navGraphicsTransition(opaqueDepth = 1f) { scope ->
        val width = scope.layoutSize.width.toFloat()
        val d = scope.relativeDepth
        val rtl = scope.layoutDirection == LayoutDirection.Rtl
        if (d <= 0f) {
            // Entering/leaving the top: full-width slide from the trailing edge. Mirrored for RTL.
            translationX = (if (rtl) -1f else 1f) * (-d).coerceIn(0f, 1f) * width
        } else {
            // Covered: parallax a quarter width toward the leading edge with a light alpha falloff.
            translationX = (if (rtl) 1f else -1f) * coverProgress(d) * width * 0.25f
            alpha = 1f - 0.1f * coverProgress(d)
        }
    }

    /**
     * Platform cross-activity feel (the AOSP task open/close animation). The top page behaves
     * like the platform's shrinking card (d: -1 -> 0): it scales between [CROSS_ACTIVITY_MIN_SCALE]
     * and 1 while drifting up to [CrossActivityDrift] **away from the gesture's originating
     * edge** (toward the trailing edge for programmatic transitions), staying **fully opaque
     * through the whole interactive range** — alpha falls only across the last
     * [CROSS_ACTIVITY_FADE_WINDOW] of the exit, so a dragged page stays solid (like the platform
     * card) and vanishes quickly once the commit settle carries it out. The covered page stays
     * opaque and parallaxes a quarter width toward the leading edge (d: 0 -> 1), matching the
     * platform's pop spec (`slideIn(-width/4)` for the revealed layer). No dim is baked in: the
     * platform derives depth from scale and parallax alone, so pair this with
     * `NavDisplayEffects(dimAmount = 0f)` for the authentic look.
     *
     * **Gesture-aware**: while a back gesture drives the transition ([NavTransitionScope.gesture]
     * non-null), the card's transform origin tracks the finger — the vertical pivot follows
     * `touchY` (so the card visibly rides up and down with the finger) and the horizontal pivot
     * leans away from the originating edge, like the platform's predictive-back card. The
     * context stays frozen through the release settle, so the pivot never snaps at lift. The
     * remaining platform nuance (the revealed layer shrinking with the gesture and growing back
     * after commit) is not a pure function of the depth sweep and is intentionally not reproduced.
     *
     * Corner clipping is the orthogonal effects layer's job: pair this preset with
     * `NavDisplayEffects(cornerClipMode = NavCornerClipMode.All, cornerClipRadius = ...)` so the
     * card rounds **all four** corners with the platform screen radius (smooth corners included),
     * instead of the slide-style leading-edge clip.
     */
    val AndroidCrossActivity: NavTransition = navGraphicsTransition(opaqueDepth = 1f) { scope ->
        val d = scope.relativeDepth
        if (d <= 0f) {
            val p = topProgress(d) // 0 off-edge, 1 at top
            scaleX = CROSS_ACTIVITY_MIN_SCALE + (1f - CROSS_ACTIVITY_MIN_SCALE) * p
            scaleY = scaleX
            val gesture = scope.gesture
            // The card drifts AWAY from the edge the gesture originated from (a right-edge swipe
            // carries it leftward); without a gesture (programmatic pop) it drifts toward the
            // trailing edge. The drift must follow the edge, not the layout direction — otherwise
            // it overpowers the much smaller pivot lean and the card visibly moves the wrong way.
            val trailing = if (scope.layoutDirection == LayoutDirection.Rtl) -1f else 1f
            val driftSign = when (gesture?.swipeEdge) {
                NavSwipeEdge.Left -> 1f
                NavSwipeEdge.Right -> -1f
                else -> trailing
            }
            translationX = driftSign * (1f - p) * with(scope.density) { CrossActivityDrift.toPx() }
            alpha = (p / CROSS_ACTIVITY_FADE_WINDOW).coerceIn(0f, 1f)
            // Gesture-aware card: while a back gesture drives (or its frozen context settles), the
            // card shrinks toward the finger — the pivot tracks the touch's vertical position, so
            // moving the finger up/down visibly carries the card with it (the platform behavior).
            // The horizontal pivot leans away from the originating edge.
            if (gesture != null) {
                val height = scope.layoutSize.height.toFloat()
                val pivotY = if (height > 0f) (gesture.touchY / height).coerceIn(0.1f, 0.9f) else 0.5f
                val pivotX = when (gesture.swipeEdge) {
                    NavSwipeEdge.Left -> 0.8f
                    NavSwipeEdge.Right -> 0.2f
                    NavSwipeEdge.None -> 0.5f
                }
                transformOrigin = TransformOrigin(pivotX, pivotY)
            }
        } else {
            // Covered: opaque, parallaxed a quarter width toward the leading edge. Mirrored for RTL.
            val sign = if (scope.layoutDirection == LayoutDirection.Rtl) 1f else -1f
            translationX = sign * coverProgress(d) * scope.layoutSize.width.toFloat() * 0.25f
        }
    }

    /**
     * Pure scale: the entering page grows from 0.85 to 1 (d: -1 -> 0); the covered page shrinks
     * to 0.85 (d: 0 -> 1). Alpha only assists near the edges to avoid a hard cut. No translation.
     */
    val Scale: NavTransition = navGraphicsTransition(opaqueDepth = 1f) { scope ->
        val d = scope.relativeDepth
        if (d <= 0f) {
            val p = topProgress(d)
            scaleX = 0.85f + 0.15f * p
            scaleY = scaleX
            alpha = (p * 2f).coerceIn(0f, 1f) // reach full opacity by the midpoint
        } else {
            val p = coverProgress(d)
            scaleX = 1f - 0.15f * p
            scaleY = scaleX
            alpha = (1f - p * 2f).coerceIn(0f, 1f)
        }
    }

    /**
     * Simple cross-fade: the entering page fades in (d: -1 -> 0) and the covered page fades out
     * (d: 0 -> 1). No scale, no translation.
     */
    val Fade: NavTransition = navGraphicsTransition(opaqueDepth = 1f) { scope ->
        val d = scope.relativeDepth
        alpha = if (d <= 0f) topProgress(d) else 1f - coverProgress(d)
    }

    /**
     * Material shared X axis: a small fixed-distance horizontal slide combined with a fade. The
     * entering page slides in from +[SharedAxisOffset] while fading in (d: -1 -> 0); the covered
     * page slides to -[SharedAxisOffset] while fading out (d: 0 -> 1). Translation is linear for
     * 1:1 finger tracking; offset is mirrored for RTL.
     */
    val SharedAxisX: NavTransition = navGraphicsTransition(opaqueDepth = 1f) { scope ->
        val d = scope.relativeDepth
        val offsetPx = with(scope.density) { SharedAxisOffset.toPx() }
        val sign = if (scope.layoutDirection == LayoutDirection.Rtl) -1f else 1f
        if (d <= 0f) {
            val p = topProgress(d) // 0 off-edge, 1 at top
            translationX = sign * (1f - p) * offsetPx
            alpha = p
        } else {
            val p = coverProgress(d) // 0 at top, 1 covered
            translationX = -sign * p * offsetPx
            alpha = 1f - p
        }
    }

    /**
     * Bottom-up modal: the entering page slides up from the bottom edge (d: -1 -> 0,
     * translationY: +height -> 0). The layer below is kept fully visible and untouched, so
     * [opaqueDepth] is raised to 2f to stop the host from culling it. Like every preset, swipe-to-
     * dismiss is opt-in: pass `entry(swipeDismiss = NavSwipeDirection.TopToBottom)` to let a downward
     * swipe dismiss it (the translation is linear, so the gesture follows the finger 1:1).
     */
    val Modal: NavTransition = navGraphicsTransition(opaqueDepth = 2f) { scope ->
        val d = scope.relativeDepth
        if (d <= 0f) {
            val height = scope.layoutSize.height.toFloat()
            translationY = (-d).coerceIn(0f, 1f) * height
        }
        // d > 0: covered layer stays fully visible and untransformed (kept by opaqueDepth = 2f).
    }

    /**
     * Instant transition: no visual change, the top entry simply swaps in/out. Lower layers are
     * fully occluded (opaqueDepth = 1f) so only the top + one covered layer are kept.
     */
    val None: NavTransition = navGraphicsTransition(opaqueDepth = 1f, dismissDirection = NavSwipeDirection.None) {
        // Intentionally empty: identity transform, instant swap. No motion means no swipe to follow,
        // so the interactive dismiss gesture is disabled; pop via the back button / system back.
    }

    /** Fixed horizontal offset used by [SharedAxisX]. */
    private val SharedAxisOffset = 30.dp

    /** Smallest scale the [AndroidCrossActivity] top card shrinks to (the platform gesture value). */
    private const val CROSS_ACTIVITY_MIN_SCALE = 0.85f

    /** Horizontal drift of the [AndroidCrossActivity] top card over its exit (platform commit offset). */
    private val CrossActivityDrift = 96.dp

    /**
     * Fraction of the [AndroidCrossActivity] exit over which the top card fades, measured from the
     * off-edge end: the card is fully opaque for the first 60% of travel (the interactive range)
     * and fades out across the final 40% once a commit carries it away.
     */
    private const val CROSS_ACTIVITY_FADE_WINDOW = 0.4f
}

/**
 * Linear progress of the "entering/leaving the top" segment: maps d in [-1, 0] -> [0, 1] where
 * 1 means fully at the top (d = 0) and 0 means fully off the leading edge (d = -1).
 * Kept linear so finger-driven snapTo follows the finger 1:1.
 */
private fun topProgress(d: Float): Float = (1f + d).coerceIn(0f, 1f)

/**
 * Linear progress of the "covered" segment: maps d in [0, 1] -> [0, 1] where 0 means fully at
 * the top (d = 0) and 1 means fully covered by the layer above (d = 1).
 */
private fun coverProgress(d: Float): Float = d.coerceIn(0f, 1f)
