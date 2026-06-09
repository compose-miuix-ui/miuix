// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Direction of the interactive swipe that dismisses (pops) the current top entry.
 *
 * The renderer maps the finger travel along the matching axis onto the shared `animatedTop` driver
 * 1:1, so the dismiss gesture follows the same motion as the transition: a horizontal slide is
 * dismissed by a horizontal swipe, a bottom-up modal by a downward swipe. Each [NavTransition]
 * declares the natural direction for its motion via [NavTransition.dismissDirection]; a route may
 * override it (including disabling the gesture with [None]) through the `entry { }` DSL.
 */
enum class NavSwipeDirection {
    /** No interactive swipe-to-dismiss; the entry is popped only via the back button / system back. */
    None,

    /** Horizontal swipe toward the trailing edge (finger moves right). Standard back gesture. */
    LeftToRight,

    /** Horizontal swipe toward the leading edge (finger moves left). */
    RightToLeft,

    /** Vertical swipe downward (finger moves down). Natural dismiss for a bottom-up modal. */
    TopToBottom,

    /** Vertical swipe upward (finger moves up). */
    BottomToTop,
}

/**
 * The visual contract for a single navigation entry layer.
 *
 * A [NavTransition] maps an entry's continuous [relativeDepth][NavTransitionScope.relativeDepth]
 * onto a [Modifier] transform (translation, scale, alpha, clip ...). The same transition governs
 * both normal push/pop animation and predictive-back gestures, because the visual is always a pure
 * function of [animatedTop][NavTransitionScope.relativeDepth] (spec section 7.1).
 *
 * Marked [Stable] rather than [androidx.compose.runtime.Immutable] because a transition holds a
 * lambda ([transformEntry]); lambda identity is reference-based, which breaks the equals-stays-equal
 * contract of `@Immutable`.
 *
 * @see navGraphicsTransition for the deferred-read convenience constructor that covers most cases.
 */
@Stable
fun interface NavTransition {
    /**
     * Applies this transition's visual transform to [this] entry layer using [scope].
     *
     * Implementations should read [scope] properties inside a deferred-read block
     * (`graphicsLayer { }` / layout) so the animation runs without recomposition.
     */
    fun Modifier.transformEntry(scope: NavTransitionScope): Modifier

    /**
     * The relative depth beyond which the layer below this entry is considered fully occluded and may
     * be culled. Defaults to `1f` (opaque slide: only the top plus one covered layer renders). A
     * modal transition keeps lower layers visible by returning a larger value (for example `2f`).
     */
    val opaqueDepth: Float get() = 1f

    /**
     * The natural direction of the interactive swipe that dismisses this entry, matching the
     * transition's own motion. Defaults to [NavSwipeDirection.LeftToRight] (the standard back swipe for
     * a horizontal slide). A bottom-up modal overrides this to [NavSwipeDirection.TopToBottom]; an
     * instant transition disables the gesture with [NavSwipeDirection.None]. A route may further
     * override it via the `entry { }` DSL.
     */
    val dismissDirection: NavSwipeDirection get() = NavSwipeDirection.LeftToRight
}

/**
 * Builds a [NavTransition] from a deferred-read [graphicsLayer][androidx.compose.ui.graphics.graphicsLayer]
 * block, the recommended way to author custom transitions.
 *
 * The [block] receiver is [GraphicsLayerScope] (so callers set `translationX`, `scaleX`, `alpha`,
 * `cameraDistance` ... directly) and its single argument is the [NavTransitionScope] (read
 * `scope.relativeDepth`, `scope.layoutSize`, etc. inside the block). Because the block runs inside
 * `Modifier.graphicsLayer { }`, every depth read is deferred to the draw phase, so transitions cause
 * zero recomposition while they animate (spec section 6.2).
 *
 * @param opaqueDepth see [NavTransition.opaqueDepth]; defaults to `1f`.
 * @param dismissDirection see [NavTransition.dismissDirection]; defaults to [NavSwipeDirection.LeftToRight].
 * @param block the per-frame graphics-layer transform.
 */
fun navGraphicsTransition(
    opaqueDepth: Float = 1f,
    dismissDirection: NavSwipeDirection = NavSwipeDirection.LeftToRight,
    block: GraphicsLayerScope.(NavTransitionScope) -> Unit,
): NavTransition = object : NavTransition {
    override val opaqueDepth: Float = opaqueDepth

    override val dismissDirection: NavSwipeDirection = dismissDirection

    override fun Modifier.transformEntry(scope: NavTransitionScope): Modifier = this.graphicsLayer { block(scope) }
}
