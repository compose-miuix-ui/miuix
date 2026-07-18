// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import kotlin.test.Test
import kotlin.test.assertEquals

private data object RouteA : NavKey

private data object RouteB : NavKey

private data object RouteC : NavKey

/**
 * A pure back-stack reorder (remove + re-add, membership unchanged) keeps `presented` in its old
 * order; the render must still compose in stack-index order so the new top draws above — and
 * receives input before — the layer it now covers.
 */
@OptIn(ExperimentalTestApi::class)
class NavReorderDrawOrderTest {

    @Test
    fun reorderedTopDrawsAboveAndReceivesInput() = runComposeUiTest {
        var clicksB = 0
        var clicksC = 0
        val backStack = navBackStackOf(RouteA, RouteB, RouteC)
        setContent {
            NavDisplay(
                backStack = backStack,
                transition = NavTransitions.None,
                effects = NavDisplayEffects.None,
            ) {
                entry<RouteA> { Box(Modifier.fillMaxSize()) }
                entry<RouteB> { Box(Modifier.fillMaxSize().clickable { clicksB++ }) }
                entry<RouteC> { Box(Modifier.fillMaxSize().clickable { clicksC++ }) }
            }
        }
        waitForIdle()

        // Bring B to the front: [A, B, C] -> [A, C, B].
        backStack.remove(RouteB)
        backStack.add(RouteB)
        waitForIdle()

        onRoot().performTouchInput { click(center) }
        waitForIdle()

        assertEquals(1, clicksB, "the reordered top must receive input")
        assertEquals(0, clicksC, "the covered layer must not sit above the new top")
    }
}
