// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.nav.core.LiveNavTransitionScope
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.runtime.NavChange
import top.yukonga.miuix.kmp.nav.transition.internal.NavCornerShape
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpandSourceBounds

/** Physical source corners, clockwise from top-left. Use zero for square joining corners. */
@Immutable
data class NavTransitionCorners(
    val topLeft: Dp = 16.dp,
    val topRight: Dp = topLeft,
    val bottomRight: Dp = topLeft,
    val bottomLeft: Dp = topLeft,
) {
    init {
        require(listOf(topLeft, topRight, bottomRight, bottomLeft).all { it.value.isFinite() && it >= 0.dp }) {
            "Source corner radii must be finite and non-negative"
        }
    }
}

/**
 * Source geometry for [NavTransitions.expandFrom]. Prefer [NavTransitions.Expansion] with
 * route-bound sources for components.
 * Captured anchors own drawing resources and use identity equality; release them after exit.
 */
@Stable
internal class NavTransitionAnchor(
    val bounds: Rect,
    val cornerRadius: Dp = 16.dp,
    val containerColor: Color = Color.Unspecified,
    val corners: NavTransitionCorners = NavTransitionCorners(cornerRadius),
    val shape: Shape? = null,
) {
    internal var source: NavTransitionSource? by mutableStateOf(null)
    internal var sourceOrigin: NavKey? = null
    internal var sourceKey: String? = null
    internal var openingBounds: Rect by mutableStateOf(bounds)
    internal var openingShape: Shape? by mutableStateOf(shape)
    private var retained = false

    init {
        require(cornerRadius.value.isFinite() && cornerRadius >= 0.dp)
    }

    internal fun retain() {
        if (!retained && source != null) {
            source?.retain()
            retained = true
        }
    }

    /** Release a low-level retained anchor after exit. Route-bound sources are managed by NavDisplay. */
    fun release() {
        if (retained) {
            source?.release()
            retained = false
        }
    }

    internal fun reconnectSource(source: NavTransitionSource) {
        release()
        this.source = source
        retain()
    }

    internal fun updateOrigin() {
        openingBounds = source?.currentBounds ?: openingBounds
        openingShape = source?.sourceShape(corners) ?: openingShape
    }

    internal fun boundsInHost(scope: NavTransitionScope): Rect {
        // Read the actual source in the host's coordinate space on both push and pop. A source
        // inside a still-settling parent must not keep the window bounds captured by its click.
        source?.observePlacement()
        val live = source?.coordinates?.let { (scope as? LiveNavTransitionScope)?.boundsInHost(it) }
        if (live != null) return live
        val bounds = source?.currentBounds ?: openingBounds
        return Rect(scope.windowToLocal(bounds.topLeft), scope.windowToLocal(bounds.bottomRight))
    }

    internal fun visibleSourceBounds(scope: LiveNavTransitionScope): NavExpandSourceBounds? {
        source?.observePlacement()
        val coordinates = source?.coordinates ?: return null
        val bounds = scope.boundsInHost(coordinates) ?: return null
        val visible = scope.visibleBoundsInHost(coordinates) ?: return null
        return NavExpandSourceBounds(bounds, visible)
    }

    internal fun shapeFor(scope: NavTransitionScope, squircleEnabled: Boolean): Shape = (if (scope.isReturning()) source?.sourceShape(corners) else openingShape)
        ?: shape ?: NavCornerShape(corners, squircleEnabled)
}

private fun NavTransitionScope.isReturning(): Boolean = gesture != null || change == NavChange.Pop || change is NavChange.MultiPop
