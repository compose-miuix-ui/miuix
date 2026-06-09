// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer

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
 * @param block the per-frame graphics-layer transform.
 */
fun navGraphicsTransition(
    opaqueDepth: Float = 1f,
    block: GraphicsLayerScope.(NavTransitionScope) -> Unit,
): NavTransition = object : NavTransition {
    override val opaqueDepth: Float = opaqueDepth

    override fun Modifier.transformEntry(scope: NavTransitionScope): Modifier = this.graphicsLayer { block(scope) }
}
