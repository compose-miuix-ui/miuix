// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Badge
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Search
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassNavigationRailBadgeTest {
    @Test
    fun badgeSurvivesCollapse() = checkBadge(LayoutDirection.Ltr)

    @Test
    fun badgeSurvivesCollapseInRtl() = checkBadge(LayoutDirection.Rtl)

    @Test
    fun badgeChangeDoesNotCancelHeldPress() = runComposeUiTest {
        val items = mutableStateOf(
            listOf(
                GlassNavigationItem(MiuixIcons.Basic.Search, "Home"),
                GlassNavigationItem(MiuixIcons.Basic.Search, "Library"),
            ),
        )
        val selections = mutableListOf<Int>()
        setContent {
            DialogTestHost {
                Box(Modifier.size(800.dp, 600.dp)) {
                    GlassNavigationRail(
                        items = items.value,
                        selectedIndex = 0,
                        onSelect = { selections += it },
                        backdrop = null,
                        state = rememberGlassNavigationRailState(initialExpanded = false),
                        defaultWindowInsetsPadding = false,
                        stroke = null,
                        shadow = null,
                    )
                }
            }
        }
        val library = onNodeWithContentDescription("Library").fetchSemanticsNode().boundsInRoot
        onRoot().performTouchInput { down(library.center) }
        runOnIdle {
            items.value = items.value.map { it.copy(badge = { Badge { Text("8") } }) }
        }
        waitForIdle()
        onRoot().performTouchInput { up() }
        assertEquals(listOf(1), selections)
    }

    private fun checkBadge(direction: LayoutDirection) = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        var instances = 0
        val items = listOf(
            GlassNavigationItem(MiuixIcons.Basic.Search, "Home", badge = {
                val instance = remember { ++instances }
                Badge(modifier = Modifier.testTag("badge"), containerColor = Color.Magenta) { Text("$instance") }
            }),
            GlassNavigationItem(MiuixIcons.Basic.Search, "Library"),
        )
        val shownItems = mutableStateOf(items)
        setContent {
            DialogTestHost {
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Box(Modifier.size(800.dp, 600.dp)) {
                        GlassNavigationRail(
                            items = shownItems.value,
                            selectedIndex = 0,
                            onSelect = {},
                            backdrop = null,
                            state = state,
                            defaultWindowInsetsPadding = false,
                            stroke = null,
                            shadow = null,
                        )
                    }
                }
            }
        }
        val badgeId = onNodeWithText("1", useUnmergedTree = true).fetchSemanticsNode().id
        runOnIdle { state.expanded = false }
        waitForIdle()
        val badge = onNodeWithTag("badge", useUnmergedTree = true).fetchSemanticsNode()
        val label = onNodeWithText("Home", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        assertEquals(badgeId, onNodeWithText("1", useUnmergedTree = true).fetchSemanticsNode().id)
        assertEquals(1, instances)
        if (direction == LayoutDirection.Ltr) {
            assertTrue(badge.boundsInRoot.left > label.right)
        } else {
            assertTrue(badge.boundsInRoot.right < label.left)
        }
        assertEquals(label.center.y, badge.boundsInRoot.center.y, 1f)
        val slot = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        val groupCenter = (minOf(label.left, badge.boundsInRoot.left) + maxOf(label.right, badge.boundsInRoot.right)) / 2
        assertEquals(slot.center.x, groupCenter, 1f)
        val pixels = onRoot().captureToImage().toPixelMap()
        var magentaPixels = 0
        for (y in 0 until pixels.height) {
            for (x in 0 until pixels.width) {
                val color = pixels[x, y]
                if (color.red > 0.9f && color.blue > 0.9f && color.green < 0.1f) magentaPixels++
            }
        }
        assertTrue(magentaPixels > 20, "Collapsed badge must remain painted")
        fun capsuleCenter(): Float {
            val home = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
            val library = onNodeWithContentDescription("Library").fetchSemanticsNode().boundsInRoot
            return (minOf(home.left, library.left) + maxOf(home.right, library.right)) / 2
        }

        val capsuleCenter = capsuleCenter()
        mainClock.autoAdvance = false
        runOnIdle { shownItems.value = items.map { it.copy(badge = null) } }
        repeat(5) {
            mainClock.advanceTimeBy(16)
            assertEquals(capsuleCenter, capsuleCenter(), 1f)
        }
        onNodeWithTag("badge", useUnmergedTree = true).assertExists()
        val movingLabel = onNodeWithText("Home", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val movingSlot = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        assertTrue(kotlin.math.abs(movingLabel.center.x - movingSlot.center.x) > 1f)
        mainClock.advanceTimeBy(224)
        val endingLabel = onNodeWithText("Home", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val endingSlot = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        assertTrue(kotlin.math.abs(endingLabel.center.x - endingSlot.center.x) <= 1f)
        mainClock.advanceTimeBy(1000)
        onNodeWithTag("badge", useUnmergedTree = true).assertDoesNotExist()
        val plainLabel = onNodeWithText("Home", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val plainSlot = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        assertEquals(plainSlot.center.x, plainLabel.center.x, 1f)
        runOnIdle { shownItems.value = items }
        repeat(5) {
            mainClock.advanceTimeBy(48)
            assertEquals(capsuleCenter, capsuleCenter(), 1f)
            val enteringBadge = onNodeWithTag("badge", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
            val enteringLabel = onNodeWithText("Home", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
            assertEquals(enteringLabel.center.y, enteringBadge.center.y, 1f)
        }
        mainClock.advanceTimeBy(1000)
        mainClock.autoAdvance = true
        val restoredId = onNodeWithText("2", useUnmergedTree = true).fetchSemanticsNode().id
        runOnIdle { state.expanded = true }
        waitForIdle()
        assertEquals(restoredId, onNodeWithText("2", useUnmergedTree = true).fetchSemanticsNode().id)
        assertEquals(2, instances)
        val expandedBadge = onNodeWithTag("badge", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        mainClock.autoAdvance = false
        runOnIdle { shownItems.value = items.map { it.copy(badge = null) } }
        repeat(5) {
            mainClock.advanceTimeBy(48)
            val exiting = onNodeWithTag("badge", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
            assertEquals(expandedBadge.center.x, exiting.center.x, 1f)
            assertEquals(expandedBadge.center.y, exiting.center.y, 1f)
        }
        mainClock.advanceTimeBy(1000)
        runOnIdle { shownItems.value = items }
        repeat(5) {
            mainClock.advanceTimeBy(48)
            val entering = onNodeWithTag("badge", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
            assertEquals(expandedBadge.center.x, entering.center.x, 1f)
            assertEquals(expandedBadge.center.y, entering.center.y, 1f)
        }
        mainClock.autoAdvance = true
    }
}
