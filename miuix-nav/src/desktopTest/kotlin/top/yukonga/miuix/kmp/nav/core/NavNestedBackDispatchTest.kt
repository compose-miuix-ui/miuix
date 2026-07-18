// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.navigationevent.DirectNavigationEventInput
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventDispatcherOwner
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import kotlin.test.Test
import kotlin.test.assertEquals

private data object OuterA : NavKey

private data object OuterB : NavKey

private data object InnerX : NavKey

private data object InnerY : NavKey

/**
 * Back dispatch across nested displays: every entry's back consumers live under a per-entry child
 * dispatcher enabled only for the interactive top, so a covered entry's inner stack — composed and
 * registered later than the outer display's handler — can never steal the system back via LIFO,
 * while a top entry's inner stack still wins and falls through at its root.
 */
@OptIn(ExperimentalTestApi::class)
class NavNestedBackDispatchTest {

    private class TestOwner : NavigationEventDispatcherOwner {
        override val navigationEventDispatcher = NavigationEventDispatcher()
        val input = DirectNavigationEventInput().also { navigationEventDispatcher.addInput(it) }
    }

    @Test
    fun back_popsOuterTop_notCoveredEntrysInnerStack() = runComposeUiTest {
        val owner = TestOwner()
        val outer = navBackStackOf(OuterA, OuterB)
        val inner = navBackStackOf(InnerX, InnerY)
        setContent {
            CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides owner) {
                NavDisplay(outer, transition = NavTransitions.None, effects = NavDisplayEffects.None) {
                    entry<OuterA> {
                        NavDisplay(inner, transition = NavTransitions.None, effects = NavDisplayEffects.None) {
                            entry<InnerX> { Box(Modifier.fillMaxSize()) }
                            entry<InnerY> { Box(Modifier.fillMaxSize()) }
                        }
                    }
                    entry<OuterB> { Box(Modifier.fillMaxSize()) }
                }
            }
        }
        waitForIdle()

        runOnIdle { owner.input.backCompleted() }
        waitForIdle()

        assertEquals(listOf<NavKey>(OuterA), outer.toList(), "back must pop the outer top")
        assertEquals(listOf<NavKey>(InnerX, InnerY), inner.toList(), "the covered entry's inner stack must not be popped")
    }

    @Test
    fun back_popsTopEntrysInnerStack_thenFallsThroughToOuter() = runComposeUiTest {
        val owner = TestOwner()
        val outer = navBackStackOf(OuterA, OuterB)
        val inner = navBackStackOf(InnerX, InnerY)
        setContent {
            CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides owner) {
                NavDisplay(outer, transition = NavTransitions.None, effects = NavDisplayEffects.None) {
                    entry<OuterA> { Box(Modifier.fillMaxSize()) }
                    entry<OuterB> {
                        NavDisplay(inner, transition = NavTransitions.None, effects = NavDisplayEffects.None) {
                            entry<InnerX> { Box(Modifier.fillMaxSize()) }
                            entry<InnerY> { Box(Modifier.fillMaxSize()) }
                        }
                    }
                }
            }
        }
        waitForIdle()

        // Top entry's inner stack wins first.
        runOnIdle { owner.input.backCompleted() }
        waitForIdle()
        assertEquals(listOf<NavKey>(InnerX), inner.toList(), "back must pop the top entry's inner stack first")
        assertEquals(listOf<NavKey>(OuterA, OuterB), outer.toList())

        // Inner at root: its handler disables and back falls through to the outer display.
        runOnIdle { owner.input.backCompleted() }
        waitForIdle()
        assertEquals(listOf<NavKey>(InnerX), inner.toList())
        assertEquals(listOf<NavKey>(OuterA), outer.toList(), "back must fall through to the outer display")
    }
}
