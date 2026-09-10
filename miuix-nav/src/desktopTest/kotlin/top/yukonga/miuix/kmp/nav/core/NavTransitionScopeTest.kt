// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
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
}
