// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SkikoComposeUiTest
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.Search
import top.yukonga.miuix.kmp.symbol.toImageVector
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class, InternalComposeUiApi::class)
class GlassNavigationRailInteractionTest {
    private val destinations = listOf(
        GlassNavigationItem(MiuixIcons.Symbol.Search.toImageVector(), "Home"),
        GlassNavigationItem(MiuixIcons.Symbol.Search.toImageVector(), "Library"),
        GlassNavigationItem(MiuixIcons.Symbol.Search.toImageVector(), "Settings"),
    )

    @Test
    fun expandedRowsUseIndicationWithoutMovingTheSelectionUntilRelease() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        var selected by mutableIntStateOf(0)
        val selections = mutableListOf<Int>()
        setContent {
            Scene(onSelect = {
                selected = it
                selections += it
            }, state = state, selectedIndex = selected)
        }
        val home = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        val library = onNodeWithContentDescription("Library").fetchSemanticsNode().boundsInRoot
        val before = onRoot().captureToImage().toPixelMap()
        val sampleX = (library.right - 20f).toInt()
        val sampleY = library.center.y.toInt()
        mainClock.autoAdvance = false
        onRoot().performTouchInput { down(library.center) }
        mainClock.advanceTimeBy(500)
        val held = onRoot().captureToImage()
        val pixel = held.toPixelMap()[sampleX, sampleY]
        assertTrue(pixel.red < before[sampleX, sampleY].red - 0.05f, "Expanded row must show the Miuix indication overlay")
        assertEquals(0, countFill(held, library, Color.Blue), "Expanded press must not show the capsule feedback")
        assertTrue(countFill(held, home, Color.Red) > 20, "Expanded selection must stay on the controlled destination")
        assertTrue(selections.isEmpty())
        onRoot().performTouchInput { up() }
        mainClock.advanceTimeBy(600)
        assertEquals(listOf(1), selections)
        onNodeWithContentDescription("Library").assertIsSelected()
    }

    @Test
    fun expandedHorizontalDragCancelsRatherThanSelectingAnotherRow() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        val selections = mutableListOf<Int>()
        setContent { Scene(onSelect = { selections += it }, state = state) }
        val library = onNodeWithContentDescription("Library").fetchSemanticsNode().boundsInRoot
        onRoot().performTouchInput {
            down(library.center)
            moveTo(Offset(library.right - 4f, library.center.y))
            up()
        }
        waitForIdle()
        assertTrue(selections.isEmpty())
        onNodeWithContentDescription("Home").assertIsSelected()
        assertEquals(0, countFill(onRoot().captureToImage(), library, Color.Blue))
    }

    @Test
    fun heldPressSurvivesTheEndOfCollapseAndKeepsItsFeedback() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        var selected by mutableIntStateOf(0)
        val selections = mutableListOf<Int>()
        setContent {
            Scene(
                onSelect = {
                    selected = it
                    selections += it
                },
                state = state,
                selectedIndex = selected,
            )
        }
        mainClock.autoAdvance = false
        runOnIdle { state.expanded = false }
        mainClock.advanceTimeBy(350)
        val label = onNodeWithText("Library", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        onRoot().performTouchInput { down(label.center) }
        mainClock.advanceTimeBy(2000)

        val slot = onNodeWithContentDescription("Library").fetchSemanticsNode().boundsInRoot
        val held = onRoot().captureToImage()
        assertTrue(countFill(held, slot, Color.Blue) > 20, "The held destination lost its pressed indicator")
        assertTrue(selections.isEmpty(), "A press must not select before release")

        onRoot().performTouchInput { up() }
        mainClock.advanceTimeBy(600)
        assertEquals(listOf(1), selections)
        onNodeWithContentDescription("Library").assertIsSelected()
        assertEquals(0, countFill(onRoot().captureToImage(), slot, Color.Blue))
    }

    @Test
    fun draggingOneSlotAfterHoldingThroughCollapseSelectsTheNextDestination() = assertDragAfterHoldingThroughCollapse(LayoutDirection.Ltr)

    @Test
    fun draggingOneSlotAfterHoldingThroughCollapseSelectsTheNextDestinationInRtl() = assertDragAfterHoldingThroughCollapse(LayoutDirection.Rtl)

    private fun assertDragAfterHoldingThroughCollapse(direction: LayoutDirection) = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        var selected by mutableIntStateOf(0)
        val selections = mutableListOf<Int>()
        setContent {
            Scene(
                onSelect = {
                    selected = it
                    selections += it
                },
                state = state,
                selectedIndex = selected,
                direction = direction,
            )
        }
        mainClock.autoAdvance = false
        runOnIdle { state.expanded = false }
        mainClock.advanceTimeBy(32)
        val downPosition = onNodeWithText("Library", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot.center
        onRoot().performTouchInput { down(downPosition) }
        mainClock.advanceTimeBy(2000)

        val library = onNodeWithContentDescription("Library").fetchSemanticsNode().boundsInRoot
        val settings = onNodeWithContentDescription("Settings").fetchSemanticsNode().boundsInRoot
        assertTrue(countFill(onRoot().captureToImage(), library, Color.Blue) > 20, "The press must survive collapse before dragging")
        assertTrue(selections.isEmpty(), "Holding through collapse must not commit a selection")

        // Move one settled slot from the held pointer, not to the slot that moved beneath it.
        val deltaX = settings.center.x - library.center.x
        onRoot().performTouchInput { moveTo(downPosition + Offset(deltaX, 0f)) }
        mainClock.advanceTimeBy(600)
        assertTrue(selections.isEmpty(), "Dragging must not select before release")
        onNodeWithContentDescription("Home").assertIsSelected()
        assertTrue(countFill(onRoot().captureToImage(), settings, Color.Blue) > 20, "Drag distance must use the settled slot width")

        onRoot().performTouchInput { up() }
        mainClock.advanceTimeBy(600)
        assertEquals(listOf(2), selections)
        onNodeWithContentDescription("Settings").assertIsSelected()
    }

    @Test
    fun cancelAfterReversingTheMorphDoesNotSelectOrLeavePressedFeedback() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        val selections = mutableListOf<Int>()
        setContent { Scene(onSelect = { selections += it }, state = state) }
        mainClock.autoAdvance = false
        runOnIdle { state.expanded = false }
        mainClock.advanceTimeBy(350)
        val label = onNodeWithText("Library", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        onRoot().performTouchInput { down(label.center) }
        runOnIdle { state.expanded = true }
        mainClock.advanceTimeBy(500)
        runOnIdle { state.expanded = false }
        mainClock.advanceTimeBy(2000)
        val slot = onNodeWithContentDescription("Library").fetchSemanticsNode().boundsInRoot
        assertTrue(countFill(onRoot().captureToImage(), slot, Color.Blue) > 20)

        onRoot().performTouchInput { cancel() }
        // Skiko cancel() resets only the injector; cancel the scene's active pointer too.
        runOnUiThread { (this as SkikoComposeUiTest).scene.cancelPointerInput() }
        waitForIdle()
        mainClock.advanceTimeBy(600)
        assertTrue(selections.isEmpty())
        onNodeWithContentDescription("Home").assertIsSelected()
        assertEquals(0, countFill(onRoot().captureToImage(), slot, Color.Blue))
    }

    @Test
    fun draggingDuringCollapseCommitsOnlyOnRelease() = assertDragDuringCollapse(LayoutDirection.Ltr)

    @Test
    fun draggingDuringCollapseWorksInRtl() = assertDragDuringCollapse(LayoutDirection.Rtl)

    private fun assertDragDuringCollapse(direction: LayoutDirection) = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        var selected by mutableIntStateOf(0)
        val selections = mutableListOf<Int>()
        setContent {
            Scene(
                onSelect = {
                    selected = it
                    selections += it
                },
                state = state,
                selectedIndex = selected,
                direction = direction,
            )
        }
        mainClock.autoAdvance = false
        runOnIdle { state.expanded = false }
        mainClock.advanceTimeBy(350)
        val home = onNodeWithText("Home", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val settings = onNodeWithText("Settings", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        onRoot().performTouchInput {
            down(home.center)
            moveTo(settings.center)
        }
        mainClock.advanceTimeBy(2000)
        assertTrue(selections.isEmpty())
        onRoot().performTouchInput { up() }
        mainClock.advanceTimeBy(600)
        assertEquals(listOf(2), selections)
        onNodeWithContentDescription("Settings").assertIsSelected()
    }

    @Test
    fun theTrailingEdgeOfATabDoesNotActivateItsNeighbourDuringCollapse() = assertSlotBoundary(LayoutDirection.Ltr)

    @Test
    fun theTrailingEdgeOfATabDoesNotActivateItsNeighbourInRtl() = assertSlotBoundary(LayoutDirection.Rtl)

    private fun assertSlotBoundary(direction: LayoutDirection) = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        val selections = mutableListOf<Int>()
        setContent { Scene(onSelect = { selections += it }, state = state, direction = direction) }
        mainClock.autoAdvance = false
        runOnIdle { state.expanded = false }
        mainClock.advanceTimeBy(350)
        val home = onNodeWithText("Home").fetchSemanticsNode().boundsInRoot
        val x = if (direction == LayoutDirection.Ltr) home.right - 4f else home.left + 4f
        onRoot().performTouchInput { click(Offset(x, home.center.y)) }
        mainClock.advanceTimeBy(600)
        assertEquals(listOf(0), selections, "The neighbour must not steal the tab's trailing hit area")
    }

    @Test
    fun aRejectedSelectionRestoresTheControlledIndicator() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = false)
        val selections = mutableListOf<Int>()
        setContent { Scene(onSelect = { selections += it }, state = state) }
        val library = onNodeWithContentDescription("Library").fetchSemanticsNode().boundsInRoot
        onRoot().performTouchInput { click(library.center) }
        waitForIdle()
        assertEquals(listOf(1), selections)
        onNodeWithContentDescription("Home").assertIsSelected()
        val home = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        val image = onRoot().captureToImage()
        assertTrue(countFill(image, home, Color.Red) > 20)
        assertEquals(0, countFill(image, library, Color.Red))
    }

    @Test
    fun verticalScrollingTheExpandedPanelCancelsThePress() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        val scroll = ScrollState(0)
        val selections = mutableListOf<Int>()
        val items = List(14) { GlassNavigationItem(MiuixIcons.Symbol.Search.toImageVector(), "Item $it") }
        setContent {
            Scene(onSelect = { selections += it }, state = state, items = items, scrollState = scroll)
        }
        val row = onNodeWithText("Item 4").fetchSemanticsNode().boundsInRoot
        onRoot().performTouchInput {
            swipe(start = row.center, end = Offset(row.center.x, 80f), durationMillis = 300)
        }
        waitForIdle()
        assertTrue(scroll.value > 0, "The panel must retain its vertical scrolling gesture")
        assertTrue(selections.isEmpty(), "Scrolling must cancel rather than commit a destination")
    }

    @Composable
    private fun Scene(
        onSelect: (Int) -> Unit,
        state: GlassNavigationRailState,
        selectedIndex: Int = 0,
        direction: LayoutDirection = LayoutDirection.Ltr,
        items: List<GlassNavigationItem> = destinations,
        scrollState: ScrollState = rememberScrollState(),
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides direction) {
            DialogTestHost {
                Box(Modifier.size(800.dp, 360.dp)) {
                    GlassNavigationRail(
                        items = items,
                        selectedIndex = selectedIndex,
                        onSelect = onSelect,
                        backdrop = null,
                        state = state,
                        defaultWindowInsetsPadding = false,
                        indicatorColor = Color.Red,
                        indicatorPressedColor = Color.Blue,
                        selectedColor = Color.Black,
                        unselectedColor = Color.Black,
                        stroke = null,
                        shadow = null,
                        scrollState = scrollState,
                    )
                }
            }
        }
    }

    private fun countFill(image: ImageBitmap, bounds: Rect, color: Color): Int {
        val pixels = image.toPixelMap()
        var count = 0
        for (y in bounds.top.toInt().coerceAtLeast(0) until bounds.bottom.toInt().coerceAtMost(image.height)) {
            for (x in bounds.left.toInt().coerceAtLeast(0) until bounds.right.toInt().coerceAtMost(image.width)) {
                val pixel = pixels[x, y]
                if (abs(pixel.red - color.red) < 0.1f && abs(pixel.green - color.green) < 0.1f && abs(pixel.blue - color.blue) < 0.1f) count++
            }
        }
        return count
    }
}
