// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastRoundToInt
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpandTransition
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpansionPreset

/**
 * Preset library of [NavTransition]s, driven by the single float
 * driver [NavTransitionScope.relativeDepth] (d = animatedTop - entryIndex).
 *
 * Presets read `d` in deferred layer or drawing blocks, so animation frames do not recompose
 * page content. Container presets also control content reveal. The same `d -> visual` mapping is reused by
 * both the spring-convergence path and the interactive gesture path (predictive back / edge
 * swipe), so no preset needs a separate predictive-back animation.
 *
 * Depth segments handled by each preset:
 * - `d <= 0`  : this entry is entering/leaving the top (use its own transition).
 * - `0 < d <= opaqueDepth` : this entry is covered by an upper entry (covered treatment).
 *
 * Slide-style presets keep `translation` linear in `d` (a finger-driven `snapTo` follows the finger
 * 1:1, §7.1) but snap the rendered entering offset to whole device pixels so a corner-clipped page's
 * anti-aliased edge does not shimmer under fractional-offset compositing. Parallax / dim easing may be
 * non-linear since it is identical in both drive modes.
 */
object NavTransitions {

    /** Expands route-bound components into pages. May be the global or a per-entry transition. */
    val Expansion: NavTransition = expansion()

    /**
     * Component expansion with the same motion as [MiuixDefault]. Bind a destination route using
     * `Modifier.navTransitionSource(route)` and navigate normally. The opaque container color comes
     * from [containerColor], the host's backdrop, or the source, in that order. Without a live source
     * or an opaque color, pages use [MiuixDefault]. The host's corner-clip radius supplies the final
     * moving-frame radius. Settled pages have no transition clip.
     */
    fun expansion(
        containerColor: Color = Color.Unspecified,
        cornerRadius: Dp = 28.dp,
        motion: NavMotion = NavMotion.Default,
    ): NavTransition {
        require(cornerRadius.value.isFinite() && cornerRadius >= 0.dp) {
            "cornerRadius must be finite and non-negative"
        }
        return NavExpansionPreset(containerColor, cornerRadius, motion)
    }

    /**
     * Expands from window-space [anchor] bounds; a missing anchor uses a centered zoom.
     * Prefer [Expansion] with route-bound sources for automatic capture and resource lifetime.
     *
     * [containerColor] must match the destination background. [cornerRadius] applies during motion;
     * [targetCornerRadius] is the final moving-frame radius (platform screen radius for a full-screen
     * host, zero for an interior pane). The settled page has no transition clip.
     *
     * Keep anchors until exit and resolve them by [NavTransitionScope.entryKey] for stacked pages.
     * Push, pop and predictive back share the same geometry and [NavMotion.Default] timing.
     */
    internal fun expandFrom(
        containerColor: Color,
        cornerRadius: Dp = 28.dp,
        targetCornerRadius: Dp = 0.dp,
        motion: NavMotion = NavMotion.Default,
        anchor: (NavTransitionScope) -> NavTransitionAnchor?,
    ): NavTransition = expandTransition(
        anchor = anchor,
        initialScale = 0.85f,
        cornerRadius = cornerRadius,
        targetCornerRadius = targetCornerRadius,
        containerColor = containerColor,
        motion = motion,
    )

    /**
     * Default preset. Reproduces the established miuix navigation feel: the entering page slides
     * in full-width from the trailing edge (d: -1 -> 0, translationX: +width -> 0), and the
     * covered page parallaxes a quarter width toward the leading edge with a light alpha falloff
     * (d: 0 -> 1). Geometry is linear in `d` for 1:1 finger tracking (entering offset pixel-snapped,
     * see below); RTL mirrored.
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
            // Entering/leaving the top: full-width slide from the trailing edge (mirrored for RTL).
            // Snap to whole device pixels: the corner-clipped page's anti-aliased edge otherwise
            // shimmers as a thin line at a fractional X over the scrim while moving (<=0.5px, 1:1-safe).
            translationX = ((if (rtl) -1f else 1f) * (-d).coerceIn(0f, 1f) * width).fastRoundToInt().toFloat()
        } else {
            // Covered: parallax a quarter width toward the leading edge with a light alpha falloff.
            translationX = (if (rtl) 1f else -1f) * coverProgress(d) * width * 0.25f
            alpha = 1f - 0.1f * coverProgress(d)
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
}

private fun expandTransition(
    anchor: (NavTransitionScope) -> NavTransitionAnchor?,
    initialScale: Float,
    cornerRadius: Dp,
    targetCornerRadius: Dp,
    containerColor: Color,
    motion: NavMotion,
): NavTransition {
    require(initialScale.isFinite() && initialScale > 0f && initialScale <= 1f) {
        "initialScale must be finite and in (0, 1]"
    }
    require(cornerRadius.value.isFinite() && cornerRadius >= 0.dp) {
        "cornerRadius must be finite and non-negative"
    }
    require(targetCornerRadius.value.isFinite() && targetCornerRadius >= 0.dp) {
        "targetCornerRadius must be finite and non-negative"
    }
    return NavExpandTransition(anchor, initialScale, cornerRadius, targetCornerRadius, containerColor, motion)
}

/**
 * Linear progress of the "covered" segment: maps d in [0, 1] -> [0, 1] where 0 means fully at
 * the top (d = 0) and 1 means fully covered by the layer above (d = 1).
 */
private fun coverProgress(d: Float): Float = d.coerceIn(0f, 1f)
