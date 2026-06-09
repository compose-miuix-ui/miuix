// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.foundation.shape.RoundedCornerShape
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
     * Reproduces the current miuix navigation feel: horizontal slide (same geometry as
     * [Cupertino]) with a leading-edge rounded-corner clip on the top page while it enters or
     * leaves, plus a parallax on the covered page. The dark scrim is provided by the orthogonal
     * NavDisplayEffects layer.
     *
     * The corner clip uses a plain [RoundedCornerShape] (this module does not depend on the
     * squircle module); the radius follows a bell curve — 0 at rest (d = 0), peaking at
     * [TransitionCornerRadius] mid-slide, back to 0 fully off-screen.
     */
    val MiuixDefault: NavTransition = navGraphicsTransition(opaqueDepth = 1f) { scope ->
        val width = scope.layoutSize.width.toFloat()
        val d = scope.relativeDepth
        val rtl = scope.layoutDirection == LayoutDirection.Rtl
        if (d <= 0f) {
            val p = (-d).coerceIn(0f, 1f) // 0 at top, 1 fully off the trailing edge
            translationX = (if (rtl) -1f else 1f) * p * width
            // Leading-edge corner clip, strongest mid-slide (bell curve), zero at both ends.
            val bell = 1f - (2f * p - 1f) * (2f * p - 1f)
            val radius = TransitionCornerRadius * bell // Dp * Float = Dp
            clip = radius > 0.5.dp
            // Round only the leading edge (start side, auto-mirrored for RTL).
            shape = RoundedCornerShape(topStart = radius, topEnd = 0.dp, bottomEnd = 0.dp, bottomStart = radius)
        } else {
            translationX = (if (rtl) 1f else -1f) * coverProgress(d) * width * 0.25f
            alpha = 1f - 0.1f * coverProgress(d)
        }
    }

    /**
     * AOSP cross-activity feel: the entering page scales up from 0.9 while fading in
     * (d: -1 -> 0); the covered page scales down to 0.9 while fading out (d: 0 -> 1). No
     * horizontal translation. Transform origin is the layer center (default).
     */
    val AndroidCrossActivity: NavTransition = navGraphicsTransition(opaqueDepth = 1f) { scope ->
        val d = scope.relativeDepth
        if (d <= 0f) {
            val p = topProgress(d) // 0 off-edge, 1 at top
            scaleX = 0.9f + 0.1f * p
            scaleY = scaleX
            alpha = p
        } else {
            val p = coverProgress(d) // 0 at top, 1 covered
            scaleX = 1f - 0.1f * p
            scaleY = scaleX
            alpha = 1f - p
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
     * [opaqueDepth] is raised to 2f to stop the host from culling it. Translation is linear so a
     * downward dismiss gesture follows the finger 1:1.
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
    val None: NavTransition = navGraphicsTransition(opaqueDepth = 1f) {
        // Intentionally empty: identity transform, instant swap.
    }

    /** Peak leading-edge corner radius applied mid-slide by [MiuixDefault]. */
    private val TransitionCornerRadius = 16.dp

    /** Fixed horizontal offset used by [SharedAxisX]. */
    private val SharedAxisOffset = 30.dp
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
