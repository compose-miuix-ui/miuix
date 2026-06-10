// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Which corners of the transitioning top entry the corner-clip effect rounds.
 *
 * The clip is the effects layer's job (not the transition's) because it needs composable
 * machinery the per-frame transition transform cannot host: the smooth (squircle) corner shape
 * and a platform-provided radius ([rememberNavSystemCornerRadius]).
 */
enum class NavCornerClipMode {
    /**
     * Only the leading-edge corners (left under LTR, right under RTL) — the corners that meet
     * the screen edge as a slide-style entering page moves in over the layer below.
     */
    Leading,

    /**
     * All four corners — for card-style transitions (e.g.
     * [top.yukonga.miuix.kmp.nav.transition.NavTransitions.AndroidCrossActivity]) that scale the
     * whole page, so every corner meets visible background.
     */
    All,
}

/**
 * Orthogonal visual effects applied by [NavDisplay] on top of the active
 * [top.yukonga.miuix.kmp.nav.transition.NavTransition].
 *
 * All switches are computed against each entry's relative depth, independently of the chosen
 * transition. This type holds no lambda fields and is therefore [Immutable]; the per-depth helpers
 * are pure functions of the switches and the depth.
 *
 * @property enableCornerClip whether to clip the transitioning top entry with smooth rounded
 *   corners (per [cornerClipMode]) while it animates over the layer below.
 * @property cornerClipRadius the corner radius used when [enableCornerClip] is on. Defaults to
 *   0.dp (no rounding); pass [rememberNavSystemCornerRadius] to follow the device screen corner
 *   (matching the platform), which still yields no rounding where the platform reports 0.
 * @property cornerClipMode which corners to round; [NavCornerClipMode.Leading] for slide-style
 *   transitions, [NavCornerClipMode.All] for card-style ones.
 * @property dimAmount maximum dim alpha applied to the covered layer during a transition. Set to 0f
 *   to disable dimming.
 * @property blockInputDuringTransition whether to swallow touch input on non-settled entries while a
 *   transition is in progress, so taps cannot reach a half-animated screen.
 * @property backdropColor solid color drawn behind every entry layer. Card-style transitions scale
 *   layers below full size, revealing the area behind the navigation host; the reference shell
 *   animation fills it with a color layer in the entering page's background color, so the page
 *   background reads as extending outward. Pass the theme background to reproduce that;
 *   [Color.Unspecified] (default) draws no backdrop.
 */
@Immutable
data class NavDisplayEffects(
    val enableCornerClip: Boolean = true,
    val cornerClipRadius: Dp = 0.dp,
    val cornerClipMode: NavCornerClipMode = NavCornerClipMode.Leading,
    val dimAmount: Float = 0.5f,
    val blockInputDuringTransition: Boolean = true,
    val backdropColor: Color = Color.Unspecified,
) {
    /**
     * Dim alpha to apply to a covered layer at relative depth [relativeDepth]. The covered layer
     * (0 < d) fades to at most [dimAmount] as it recedes; the top layer (d <= 0) is never dimmed.
     *
     * @param relativeDepth this entry's relative depth (`animatedTop - entryIndex`).
     */
    @Stable
    fun dimAlphaAt(relativeDepth: Float): Float = if (dimAmount <= 0f) 0f else relativeDepth.coerceIn(0f, 1f) * dimAmount

    /**
     * Whether the entering top entry should be corner-clipped at relative depth [relativeDepth]. Only
     * the entry strictly crossing the front edge (-1 < d < 0) is clipped, and only when
     * [enableCornerClip]; a settled top (d == 0) or fully-exited entry (d == -1) is not clipped.
     */
    @Stable
    fun shouldClipCornersAt(relativeDepth: Float): Boolean = enableCornerClip && relativeDepth > -1f && relativeDepth < 0f

    /**
     * Whether pointer input should be blocked for an entry at relative depth [relativeDepth]. Input is
     * blocked only on a layer that is mid-transition — neither the settled top (d == 0) nor any other
     * settled integer depth (e.g. a fully covered layer at d == 1) — while [blockInputDuringTransition].
     */
    @Stable
    fun shouldBlockInputAt(relativeDepth: Float): Boolean {
        if (!blockInputDuringTransition) return false
        val isSettledTop = relativeDepth == 0f
        val isSettled = relativeDepth == relativeDepth.toInt().toFloat()
        return !isSettledTop && !isSettled
    }

    companion object {
        /** The default effects (corner clip on, 0.5 dim, input blocking on). */
        val Default: NavDisplayEffects = NavDisplayEffects()

        /** All effects disabled: no corner clip, no dim, no input blocking. */
        val None: NavDisplayEffects = NavDisplayEffects(
            enableCornerClip = false,
            dimAmount = 0f,
            blockInputDuringTransition = false,
        )
    }
}
