// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEvent
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassDropdownPredictiveBackTest {
    @Test
    fun systemTakeoverKeepsDropdownHeldUntilBackIsCancelledOrCompleted() = runComposeUiTest {
        mainClock.autoAdvance = false
        val owner = TestNavigationOwner()
        val expansions = mutableListOf<Boolean>()
        setContent {
            DialogTestHost(owner) {
                GlassDropdownPreference(
                    onSelectedIndexChange = {},
                    items = listOf("Debug", "Warn", "Error"),
                    selectedIndex = 0,
                    title = "Log level",
                    backdrop = null,
                    sizing = GlassPopupSizing(minWidth = 200.dp, maxWidth = 200.dp),
                    visuals = GlassPopupDefaults.visuals(stroke = null, shadow = null),
                    onExpandedChange = { expansions += it },
                )
            }
        }
        onNodeWithText("Log level").performClick()
        mainClock.advanceTimeBy(1000)
        val expanded = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot

        // Android cancels the app's pointer stream when the system takes over an edge swipe.
        onRoot().performTouchInput { down(Offset(1f, height * 0.75f)) }
        runOnIdle { owner.input.backStarted(NavigationEvent(progress = 0f)) }
        onRoot().performTouchInput { cancel() }
        runOnIdle { assertEquals(listOf(true), expansions, "System takeover must not dismiss the menu") }
        runOnIdle { owner.input.backProgressed(NavigationEvent(progress = 0.4f)) }
        mainClock.advanceTimeByFrame()
        val dragged = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot
        assertTrue(dragged.width < expanded.width, "The dropdown must follow predictive-back progress")
        mainClock.advanceTimeBy(500)
        assertEquals(dragged, onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot, "Holding the gesture must hold the menu")

        runOnIdle { owner.input.backProgressed(NavigationEvent(progress = 0.2f)) }
        mainClock.advanceTimeByFrame()
        val reversed = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot
        assertTrue(reversed.width > dragged.width, "Dragging back must restore the menu proportionally")
        runOnIdle { owner.input.backCancelled() }
        mainClock.advanceTimeBy(1000)
        val restored = onNodeWithText("Warn").assertIsDisplayed().fetchSemanticsNode().boundsInRoot
        assertEquals(expanded.width, restored.width, 0.01f)
        assertEquals(listOf(true), expansions)

        runOnIdle { owner.input.backStarted(NavigationEvent(progress = 0f)) }
        runOnIdle { owner.input.backProgressed(NavigationEvent(progress = 0.6f)) }
        mainClock.advanceTimeByFrame()
        assertEquals(listOf(true), expansions, "Progress alone must not dismiss")
        runOnIdle { owner.input.backCompleted() }
        mainClock.advanceTimeBy(1000)
        onNodeWithText("Warn").assertDoesNotExist()
        assertEquals(listOf(true, false), expansions)
    }
}
