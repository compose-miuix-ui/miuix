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
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private data object TransitionScopeRoute : NavKey

private data object TransitionScopeRouteB : NavKey

@OptIn(ExperimentalTestApi::class)
class NavTransitionScopeTest {

    @Test
    fun reportsRunningUntilNavigationSettles() = runComposeUiTest {
        var sawRunning = false
        var runningAtEnd = false
        val backStack = navBackStackOf(TransitionScopeRoute)

        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<TransitionScopeRoute> {
                    val running = LocalNavTransitionScope.current.isRunning
                    sawRunning = sawRunning || running
                    runningAtEnd = running
                    Box(Modifier.fillMaxSize())
                }
                entry<TransitionScopeRouteB> {
                    val running = LocalNavTransitionScope.current.isRunning
                    sawRunning = sawRunning || running
                    runningAtEnd = running
                    Box(Modifier.fillMaxSize())
                }
            }
        }

        waitForIdle()
        backStack.add(TransitionScopeRouteB)
        waitForIdle()
        assertFalse(runningAtEnd)
        assertTrue(sawRunning)
    }

    @Test
    fun reportsRunningDuringSwipeAndCancelSettle() = runComposeUiTest {
        var sawGestureRunning = false
        var sawSettleRunning = false
        var runningAtEnd = false
        val backStack = navBackStackOf(TransitionScopeRoute, TransitionScopeRouteB)

        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<TransitionScopeRoute> {
                    val scope = LocalNavTransitionScope.current
                    sawGestureRunning = sawGestureRunning || (scope.gesture != null && scope.isRunning)
                    sawSettleRunning = sawSettleRunning || (scope.settle != null && scope.isRunning)
                    runningAtEnd = scope.isRunning
                    Box(Modifier.fillMaxSize())
                }
                entry<TransitionScopeRouteB>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    val scope = LocalNavTransitionScope.current
                    sawGestureRunning = sawGestureRunning || (scope.gesture != null && scope.isRunning)
                    sawSettleRunning = sawSettleRunning || (scope.settle != null && scope.isRunning)
                    runningAtEnd = scope.isRunning
                    Box(Modifier.fillMaxSize())
                }
            }
        }

        waitForIdle()
        onRoot().performTouchInput {
            down(Offset(width * 0.05f, centerY))
            repeat(6) { step ->
                moveTo(Offset(width * (0.05f + 0.05f * (step + 1)), centerY))
            }
            repeat(5) { moveTo(Offset(width * 0.35f, centerY), delayMillis = 100) }
        }
        waitForIdle()
        assertTrue(sawGestureRunning)

        onRoot().performTouchInput { up() }
        waitForIdle()
        assertTrue(sawSettleRunning)
        assertFalse(runningAtEnd)
    }
}
