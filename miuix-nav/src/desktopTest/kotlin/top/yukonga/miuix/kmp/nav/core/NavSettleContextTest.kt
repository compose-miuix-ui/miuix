// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import top.yukonga.miuix.kmp.nav.transition.NavSettle
import top.yukonga.miuix.kmp.nav.transition.NavSettlePhase
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection
import top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

private data object SettleRouteA : NavKey

private data object SettleRouteB : NavKey

/**
 * The NavSettle context must be published for every self-driven settle (correct phase, advancing
 * wall clock, seeded release velocity) and cleared once the driver rests; finger-driven frames
 * never see one.
 */
@OptIn(ExperimentalTestApi::class)
class NavSettleContextTest {

    /** Captures everything the transition observes per frame. */
    private class Probe {
        var lastSettle: NavSettle? = null
        var sawPhase: NavSettlePhase? = null
        var maxElapsed = 0f
        var sawVelocity = 0f
        var settleFrames = 0
        var fingerFramesWithSettle = 0
    }

    private fun probedTransition(probe: Probe) = navGraphicsTransition { scope ->
        val s = scope.settle
        probe.lastSettle = s
        if (s != null) {
            probe.sawPhase = s.phase
            probe.maxElapsed = maxOf(probe.maxElapsed, s.elapsedMillis)
            probe.sawVelocity = s.releaseVelocity
            probe.settleFrames++
            if (scope.gesture != null && s.phase == NavSettlePhase.Programmatic) probe.fingerFramesWithSettle++
        }
        translationX = (-scope.relativeDepth).coerceIn(0f, 1f) * scope.layoutSize.width
    }

    @Test
    fun programmaticPush_publishesProgrammaticSettle_thenClears() = runComposeUiTest {
        val probe = Probe()
        val backStack = navBackStackOf(SettleRouteA)
        setContent {
            NavDisplay(backStack = backStack, transition = probedTransition(probe), effects = NavDisplayEffects.None) {
                entry<SettleRouteA> { Box(Modifier.fillMaxSize()) }
                entry<SettleRouteB> { Box(Modifier.fillMaxSize()) }
            }
        }
        waitForIdle()
        backStack.add(SettleRouteB)
        waitForIdle()
        assertEquals(NavSettlePhase.Programmatic, probe.sawPhase)
        assertTrue(probe.settleFrames > 0, "settle context must be visible during animation frames")
        assertTrue(probe.maxElapsed > 0f, "elapsedMillis must advance")
        assertNull(probe.lastSettle, "context must clear once settled")
    }

    @Test
    fun swipeCancel_publishesCancelPhase() = runComposeUiTest {
        val probe = Probe()
        val backStack = navBackStackOf(SettleRouteA, SettleRouteB)
        setContent {
            NavDisplay(backStack = backStack, transition = probedTransition(probe), effects = NavDisplayEffects.None) {
                entry<SettleRouteA> { Box(Modifier.fillMaxSize()) }
                entry<SettleRouteB>(swipeDismiss = NavSwipeDirection.LeftToRight) { Box(Modifier.fillMaxSize()) }
            }
        }
        waitForIdle()
        // Drag below the commit thresholds, hold to decay velocity, release: cancels.
        onRoot().performTouchInput {
            down(Offset(width * 0.05f, centerY))
            repeat(6) { step -> moveTo(Offset(width * (0.05f + 0.05f * (step + 1)), centerY)) }
            repeat(5) { moveTo(Offset(width * 0.35f, centerY), delayMillis = 100) }
            up()
        }
        waitForIdle()
        assertEquals(NavSettlePhase.Cancel, probe.sawPhase)
        assertNull(probe.lastSettle)
    }

    @Test
    fun swipeCommit_publishesCommitPhase_withReleaseVelocity() = runComposeUiTest {
        val probe = Probe()
        val backStack = navBackStackOf(SettleRouteA, SettleRouteB)
        setContent {
            NavDisplay(backStack = backStack, transition = probedTransition(probe), effects = NavDisplayEffects.None) {
                entry<SettleRouteA> { Box(Modifier.fillMaxSize()) }
                entry<SettleRouteB>(swipeDismiss = NavSwipeDirection.LeftToRight) { Box(Modifier.fillMaxSize()) }
            }
        }
        waitForIdle()
        // Fast rightward fling: commits by velocity.
        onRoot().performTouchInput {
            down(Offset(width * 0.05f, centerY))
            repeat(4) { step -> moveTo(Offset(width * (0.05f + 0.12f * (step + 1)), centerY), delayMillis = 16) }
            up()
        }
        waitForIdle()
        assertEquals(NavSettlePhase.Commit, probe.sawPhase)
        assertTrue(probe.sawVelocity > 0f, "commit settle must carry the release velocity, got ${probe.sawVelocity}")
        assertEquals(1, backStack.size)
        assertNull(probe.lastSettle)
    }
}
