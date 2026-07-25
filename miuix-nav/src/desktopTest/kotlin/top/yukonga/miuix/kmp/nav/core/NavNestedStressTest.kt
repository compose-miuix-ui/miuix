// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import androidx.navigationevent.DirectNavigationEventInput
import androidx.navigationevent.NavigationEvent
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventDispatcherOwner
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import kotlin.test.Test
import kotlin.test.assertEquals

private data object FzRoot : NavKey

private data object FzNested : NavKey

private data object FzTop : NavKey

private data class FzLevel(val depth: Int) : NavKey

/**
 * Stress net for nested displays: mirrors the example structure (outer display, page with a
 * LazyColumn hosting an inner display) and probes interactivity after settled and mid-transition
 * navigation steps.
 */
@OptIn(ExperimentalTestApi::class)
class NavNestedStressTest {

    private class TestOwner : NavigationEventDispatcherOwner {
        override val navigationEventDispatcher = NavigationEventDispatcher()
        val input = DirectNavigationEventInput().also { navigationEventDispatcher.addInput(it) }
    }

    @Test
    fun nestedPage_staysInteractive_throughNavigationSteps() = runComposeUiTest {
        val owner = TestOwner()
        val outer = navBackStackOf(FzRoot, FzNested)
        val inner = navBackStackOf(FzLevel(1))
        var pageClicks = 0
        setContent {
            CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides owner) {
                NavDisplay(outer, effects = NavDisplayEffects()) {
                    entry<FzRoot> { Box(Modifier.fillMaxSize().background(Color.Gray)) }
                    entry<FzNested> {
                        LazyColumn(Modifier.fillMaxSize().background(Color.White)) {
                            item(key = "probe") {
                                Box(
                                    Modifier.size(60.dp).testTag("pageProbe")
                                        .clickable { pageClicks++ },
                                )
                            }
                            item(key = "card") {
                                Box(Modifier.size(300.dp)) {
                                    NavDisplay(inner, effects = NavDisplayEffects()) {
                                        entry<FzLevel> { level ->
                                            InnerLevel(level.depth, inner, outer)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    entry<FzTop> { Box(Modifier.fillMaxSize().background(Color.Black)) }
                }
            }
        }
        waitForIdle()

        fun settle() {
            waitForIdle()
            mainClock.advanceTimeBy(900)
            waitForIdle()
        }

        fun probe(step: String) {
            val before = pageClicks
            onNodeWithTag("pageProbe").performClick()
            waitForIdle()
            assertEquals(before + 1, pageClicks, "page probe dead after: $step")
        }

        probe("initial")

        // Inner pushes.
        onNodeWithTag("push-1").performClick()
        settle()
        assertEquals(2, inner.size, "inner push 1 failed")
        probe("inner push to level 2")

        onNodeWithTag("push-2").performClick()
        settle()
        assertEquals(3, inner.size, "inner push 2 failed")
        probe("inner push to level 3")

        // System back pops inner levels first.
        runOnIdle { owner.input.backCompleted() }
        settle()
        assertEquals(2, inner.size, "back must pop inner level 3")
        probe("back to inner level 2")

        // Predictive gesture stream (started -> progressed -> completed), like a real device.
        runOnIdle {
            owner.input.backStarted(NavigationEvent(progress = 0f))
            owner.input.backProgressed(NavigationEvent(progress = 0.5f))
            owner.input.backCompleted()
        }
        settle()
        assertEquals(1, inner.size, "gesture back must pop inner level 2")
        probe("gesture back to inner level 1")

        // Push an outer page over the nested page, then back.
        onNodeWithTag("pushOuter-1").performClick()
        settle()
        assertEquals(3, outer.size, "outer push failed")
        runOnIdle { owner.input.backCompleted() }
        settle()
        assertEquals(2, outer.size, "back must pop the outer page")
        probe("outer push + back")

        // Inner interactions still work end-to-end.
        onNodeWithTag("push-1").performClick()
        settle()
        assertEquals(2, inner.size, "inner push after outer round-trip failed")
        probe("inner push after outer round-trip")
    }

    @Test
    fun clicksDuringTransition_doNotWedgeTheSettle() = runComposeUiTest {
        mainClock.autoAdvance = false
        val owner = TestOwner()
        val outer = navBackStackOf(FzRoot, FzNested)
        val inner = navBackStackOf(FzLevel(1))
        var pageClicks = 0
        setContent {
            CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides owner) {
                // blockInputDuringTransition = false: mid-transition taps reach the screens.
                NavDisplay(outer, effects = NavDisplayEffects(blockInputDuringTransition = false)) {
                    entry<FzRoot> { Box(Modifier.fillMaxSize().background(Color.Gray)) }
                    entry<FzNested> {
                        LazyColumn(Modifier.fillMaxSize().background(Color.White)) {
                            item(key = "probe") {
                                Box(
                                    Modifier.size(60.dp).testTag("pageProbe")
                                        .clickable { pageClicks++ },
                                )
                            }
                            item(key = "card") {
                                Box(Modifier.size(300.dp)) {
                                    NavDisplay(inner, effects = NavDisplayEffects(blockInputDuringTransition = false)) {
                                        entry<FzLevel> { level ->
                                            InnerLevel(level.depth, inner, outer)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    entry<FzTop> { Box(Modifier.fillMaxSize().background(Color.Black)) }
                }
            }
        }
        mainClock.advanceTimeBy(100)
        waitForIdle()

        fun settleFully() {
            repeat(4) {
                mainClock.advanceTimeBy(600)
                waitForIdle()
            }
        }

        fun probe(step: String) {
            settleFully()
            val before = pageClicks
            onNodeWithTag("pageProbe").performClick()
            mainClock.advanceTimeBy(100)
            waitForIdle()
            assertEquals(before + 1, pageClicks, "page probe dead after: $step")
        }

        // Arm 1: rapid double-push — second click lands mid-settle of the first.
        onNodeWithTag("push-1").performClick()
        mainClock.advanceTimeBy(120)
        waitForIdle()
        onNodeWithTag("push-2").performClick()
        settleFully()
        assertEquals(3, inner.size, "double push must land both levels")
        probe("double push, second mid-settle")

        // Arm 2: back, then re-push the same level mid-pop-settle (revival path).
        runOnIdle { owner.input.backCompleted() }
        mainClock.advanceTimeBy(120)
        waitForIdle()
        onNodeWithTag("push-2").performClick()
        settleFully()
        assertEquals(3, inner.size, "revival push mid-pop must land")
        probe("re-push mid-pop-settle")

        // Arm 3: push an outer page mid-inner-settle, then back mid-outer-settle.
        runOnIdle { owner.input.backCompleted() }
        mainClock.advanceTimeBy(120)
        waitForIdle()
        onNodeWithTag("pushOuter-2").performClick()
        mainClock.advanceTimeBy(120)
        waitForIdle()
        runOnIdle { owner.input.backCompleted() }
        settleFully()
        assertEquals(2, outer.size, "outer push + back mid-settles must resolve")
        probe("outer push mid-inner-settle + back mid-outer-settle")

        // Arm 4: an edge-tap back session (started then cancelled) mid-settle.
        onNodeWithTag("push-2").performClick()
        mainClock.advanceTimeBy(120)
        waitForIdle()
        runOnIdle { owner.input.backStarted(NavigationEvent(progress = 0f)) }
        mainClock.advanceTimeBy(60)
        waitForIdle()
        runOnIdle { owner.input.backCancelled() }
        settleFully()
        assertEquals(3, inner.size, "cancelled back session must leave the push intact")
        probe("back session started+cancelled mid-settle")

        // Arm 5: back session started mid-settle and COMMITTED.
        runOnIdle { owner.input.backStarted(NavigationEvent(progress = 0f)) }
        mainClock.advanceTimeBy(60)
        waitForIdle()
        runOnIdle {
            owner.input.backProgressed(NavigationEvent(progress = 0.6f))
            owner.input.backCompleted()
        }
        settleFully()
        assertEquals(2, inner.size, "committed back session mid-settle must pop")
        probe("back session committed mid-settle")
    }
}

@Composable
private fun InnerLevel(depth: Int, inner: NavBackStack, outer: NavBackStack) {
    Column(Modifier.fillMaxSize().background(Color.LightGray)) {
        Box(
            Modifier.size(50.dp).testTag("push-$depth")
                .clickable { inner.add(FzLevel(depth + 1)) },
        )
        Box(
            Modifier.size(50.dp).testTag("pushOuter-$depth")
                .clickable { outer.add(FzTop) },
        )
    }
}
