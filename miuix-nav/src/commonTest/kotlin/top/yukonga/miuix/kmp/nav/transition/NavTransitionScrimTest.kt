// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.nav.runtime.NavChange
import kotlin.test.Test
import kotlin.test.assertEquals

class NavTransitionScrimTest {

    private class FakeScope(
        override val relativeDepth: Float,
        override val gesture: NavGesture? = null,
    ) : NavTransitionScope {
        override val role: NavRole = NavRole.Covered
        override val change: NavChange = NavChange.None
        override val layoutSize: IntSize = IntSize(1080, 2400)
        override val layoutDirection: LayoutDirection = LayoutDirection.Ltr
        override val density: Density = Density(1f)
    }

    private val transition = navGraphicsTransition { }

    @Test
    fun defaultScrim_followsDepthLinearly() {
        assertEquals(0f, transition.scrimFraction(FakeScope(0f)))
        assertEquals(0.5f, transition.scrimFraction(FakeScope(0.5f)))
        assertEquals(1f, transition.scrimFraction(FakeScope(1f)))
    }

    @Test
    fun defaultScrim_clampsOutsideCoveredSegment() {
        assertEquals(0f, transition.scrimFraction(FakeScope(-0.5f)))
        assertEquals(1f, transition.scrimFraction(FakeScope(2f)))
    }

    @Test
    fun customScrim_overridesDefaultCurve() {
        val held = navGraphicsTransition(
            scrim = { scope ->
                val g = scope.gesture
                val d = scope.relativeDepth.coerceIn(0f, 1f)
                if (g != null) (d / (1f - g.progress).coerceAtLeast(0.01f)).coerceIn(0f, 1f) else d
            },
        ) { }
        // While the finger drives, the covered depth equals the remaining gesture progress, so
        // the held curve stays at full strength where the linear default would have faded.
        val midGesture = FakeScope(0.5f, gesture = NavGesture(progress = 0.5f, swipeEdge = NavSwipeEdge.Left, touchY = 0f))
        assertEquals(1f, held.scrimFraction(midGesture))
        // Without a gesture the curve degrades to the depth-linear (all-post-commit) case.
        assertEquals(0.5f, held.scrimFraction(FakeScope(0.5f)))
    }

    @Test
    fun nullScrimParameter_inheritsDefaultCurve() {
        val explicitNull = navGraphicsTransition(scrim = null) { }
        assertEquals(0.5f, explicitNull.scrimFraction(FakeScope(0.5f)))
    }
}
