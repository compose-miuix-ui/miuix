// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.animation.core.LinearEasing
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.nav.runtime.NavChange
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

private class FakeScope(
    override val change: NavChange,
    override val gesture: NavGesture?,
) : NavTransitionScope {
    override val relativeDepth: Float = 0f
    override val role: NavRole = NavRole.Top
    override val layoutSize: IntSize = IntSize(1080, 2400)
    override val layoutDirection: LayoutDirection = LayoutDirection.Ltr
    override val density: Density = Density(1f)
}

private class Marker(
    override val opaqueDepth: Float = 1f,
    override val dismissDirection: NavSwipeDirection = NavSwipeDirection.None,
    override val motion: NavMotion = NavMotion.Default,
    private val scrim: Float = 0f,
) : NavTransition {
    var picked: Int = 0
        private set

    override fun Modifier.transformEntry(scope: NavTransitionScope): Modifier {
        picked++
        return this
    }

    override fun scrimFraction(scope: NavTransitionScope): Float = scrim
}

class NavDirectionalTransitionTest {

    private fun gesture() = NavGesture(progress = 0.3f, swipeEdge = NavSwipeEdge.Left, touchY = 0f)

    @Test
    fun dispatch_gestureWinsThenChangeSplitsPushPop() {
        val push = Marker(scrim = 0.1f)
        val pop = Marker(scrim = 0.2f)
        val predictive = Marker(scrim = 0.3f)
        val combined = navDirectionalTransition(push = push, pop = pop, predictivePop = predictive)

        with(combined) { Modifier.transformEntry(FakeScope(NavChange.Push, gesture = null)) }
        assertEquals(1, push.picked)
        with(combined) { Modifier.transformEntry(FakeScope(NavChange.Pop, gesture = null)) }
        with(combined) { Modifier.transformEntry(FakeScope(NavChange.MultiPop(2), gesture = null)) }
        assertEquals(2, pop.picked)
        with(combined) { Modifier.transformEntry(FakeScope(NavChange.Pop, gesture = gesture())) }
        assertEquals(1, predictive.picked)

        assertEquals(0.2f, combined.scrimFraction(FakeScope(NavChange.Pop, gesture = null)))
        assertEquals(0.3f, combined.scrimFraction(FakeScope(NavChange.Pop, gesture = gesture())))
        assertEquals(0.1f, combined.scrimFraction(FakeScope(NavChange.Replace, gesture = null)))
    }

    @Test
    fun staticMerge_opaqueDepthMax_dismissAndMotionFromNaturalSources() {
        val pushMotion = NavMotion(programmatic = NavSettleSpec.Tween(111, LinearEasing))
        val popMotion = NavMotion(programmatic = NavSettleSpec.Tween(222, LinearEasing))
        val predictiveMotion = NavMotion(
            commit = NavSettleSpec.Spring(dampingRatio = 0.8f, stiffness = 280f, clampOvershoot = false),
            cancel = NavSettleSpec.Spring(stiffness = 99f),
        )
        val push = Marker(opaqueDepth = 1f, motion = pushMotion)
        val pop = Marker(opaqueDepth = 2f, motion = popMotion)
        val predictive = Marker(
            opaqueDepth = 1.5f,
            dismissDirection = NavSwipeDirection.LeftToRight,
            motion = predictiveMotion,
        )
        val combined = navDirectionalTransition(push = push, pop = pop, predictivePop = predictive)

        assertEquals(2f, combined.opaqueDepth)
        assertEquals(NavSwipeDirection.LeftToRight, combined.dismissDirection)
        assertSame(predictiveMotion.commit, combined.motion.commit)
        assertSame(predictiveMotion.cancel, combined.motion.cancel)
        assertSame(popMotion.programmatic, combined.motion.programmatic)
    }

    @Test
    fun defaults_popFallsBackToPush_predictiveToPop() {
        val push = Marker()
        val combined = navDirectionalTransition(push = push)
        with(combined) { Modifier.transformEntry(FakeScope(NavChange.Pop, gesture = gesture())) }
        with(combined) { Modifier.transformEntry(FakeScope(NavChange.Pop, gesture = null)) }
        with(combined) { Modifier.transformEntry(FakeScope(NavChange.Push, gesture = null)) }
        assertEquals(3, push.picked)
    }
}
