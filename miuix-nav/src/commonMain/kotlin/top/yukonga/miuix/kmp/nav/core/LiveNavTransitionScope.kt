// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.nav.runtime.NavChange
import top.yukonga.miuix.kmp.nav.runtime.NavPresentation
import top.yukonga.miuix.kmp.nav.runtime.relativeDepth
import top.yukonga.miuix.kmp.nav.runtime.roleFor
import top.yukonga.miuix.kmp.nav.transition.NavGesture
import top.yukonga.miuix.kmp.nav.transition.NavRole
import top.yukonga.miuix.kmp.nav.transition.NavSettle
import top.yukonga.miuix.kmp.nav.transition.NavTransitionAnchor
import top.yukonga.miuix.kmp.nav.transition.NavTransitionScope
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpandFrame
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpandSourceBounds
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpandTransition
import top.yukonga.miuix.kmp.nav.transition.internal.NavOutlineMorph
import top.yukonga.miuix.kmp.nav.transition.navVisibleBoundsOf

/**
 * A [NavTransitionScope] whose [relativeDepth] (and derived [role]) are **deferred reads** of the
 * live [NavPresentation.animatedTop]. Passing this into a transition that itself reads inside a
 * `graphicsLayer { }` (e.g. [top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition]) yields
 * zero-recomposition, per-frame visual updates.
 *
 * @param presentation source of the single driving float.
 * @param entryIndex this entry's index in the back stack (for the depth subtraction).
 * @param isRemoving whether this entry is animating out (affects [role]).
 * @param readLayoutSize reads the host's size; deferred like [relativeDepth].
 */
@Stable
internal class LiveNavTransitionScope(
    private val presentation: NavPresentation,
    private val entryIndex: Int,
    private val isRemoving: () -> Boolean,
    private val readLayoutSize: () -> IntSize,
    override val layoutDirection: LayoutDirection,
    override val density: Density,
    override val entryKey: NavKey? = null,
    private val layoutPosition: () -> Offset = { Offset.Zero },
    private val windowPositionToLocal: ((Offset) -> Offset)? = null,
    private val hasUpperEntry: () -> Boolean = { false },
    internal val squircleEnabled: Boolean = false,
    private val hostCoordinates: (() -> LayoutCoordinates?)? = null,
) : NavTransitionScope {
    internal val expansionOutline by lazy(LazyThreadSafetyMode.NONE) { NavOutlineMorph() }
    private var frameOwner: NavExpandTransition? = null
    private var expansionFrame: State<NavExpandFrame>? = null
    private var returnAnchor: NavTransitionAnchor? = null
    private var returnHasSource: Boolean = false
    private var returnBounds: NavExpandSourceBounds? = null

    internal fun expansionFrame(owner: NavExpandTransition, create: () -> State<NavExpandFrame>): NavExpandFrame {
        if (frameOwner !== owner) {
            frameOwner = owner
            expansionFrame = create()
        }
        return checkNotNull(expansionFrame).value
    }

    internal val coveredByEntry: Boolean get() = hasUpperEntry()

    internal val isReturningExpansion: Boolean
        get() = isRemoving() || (gestureActive && !coveredByEntry)

    internal fun expansionReturnBounds(anchor: NavTransitionAnchor, returning: Boolean = isReturningExpansion): NavExpandSourceBounds? {
        if (!returning) {
            returnAnchor = null
            returnHasSource = false
            returnBounds = null
            return null
        }
        // Keep the initial decision (source vs centered fallback) across gesture/settle,
        // while tracking live source bounds if the underlying content scrolls mid-return.
        if (returnAnchor !== anchor) {
            returnAnchor = anchor
            val initial = anchor.visibleSourceBounds(this)
            returnHasSource = initial != null
            returnBounds = initial
            return initial
        }
        if (!returnHasSource) return null
        val current = anchor.visibleSourceBounds(this)
        if (current != null) {
            returnBounds = current
            return current
        }
        return returnBounds?.let { if (it.isLiveVisible) it.copy(isLiveVisible = false) else it }
    }

    override val change: NavChange get() = presentation.change

    override val layoutSize: IntSize get() = readLayoutSize()

    override val layoutPositionInWindow: Offset get() = layoutPosition()

    override fun windowToLocal(position: Offset): Offset = windowPositionToLocal?.invoke(position) ?: super.windowToLocal(position)

    internal fun boundsInHost(source: LayoutCoordinates): Rect? {
        val host = hostCoordinates?.invoke() ?: return null
        if (!host.isAttached || !source.isAttached) return null
        return host.localBoundingBoxOf(source, clipBounds = false).takeIf { it.isFinite && !it.isEmpty }
    }

    internal fun visibleBoundsInHost(source: LayoutCoordinates): Rect? = hostCoordinates?.invoke()?.navVisibleBoundsOf(source)

    override val relativeDepth: Float
        get() = relativeDepth(presentation.animatedTop.value, entryIndex)

    override val role: NavRole
        get() = roleFor(relativeDepth, isRemoving())

    override val gesture: NavGesture? get() = presentation.gesture

    // gestureActive deliberately uses a derived Boolean: gesture is replaced on every pointer
    // event, but consumers reading this from composition only need the start/end boundaries.
    override val isRunning: Boolean
        get() = presentation.gestureActive || presentation.settle != null || presentation.animatedTop.isRunning

    override val settle: NavSettle? get() = presentation.settle

    /** Coarse gesture flag for composition-time branch dispatch (see navDirectionalTransition). */
    internal val gestureActive: Boolean get() = presentation.gestureActive
}
