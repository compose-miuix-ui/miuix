// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class TabRowResizeTest {

    private val tabs = List(8) { "Tab $it" }

    @Test
    fun tabsFollowAWidthChangedDuringLayout() = runComposeUiTest {
        var width by mutableStateOf(400.dp)
        setContent {
            MiuixTheme {
                // The width changes only in layout.
                Box(Modifier.layoutWidth { width }.testTag("row")) {
                    TabRow(tabs = tabs.take(3), selectedTabIndex = 0, onTabSelected = {})
                }
            }
        }
        waitForIdle()
        assertTabWidth(400f)
        width = 600.dp
        waitForIdle()
        assertTabWidth(600f)
    }

    @Test
    fun selectedTabStaysCenteredAfterResize() = runComposeUiTest {
        var width by mutableStateOf(300.dp)
        setContent {
            MiuixTheme {
                Box(Modifier.layoutWidth { width }.testTag("row")) {
                    TabRowWithContour(tabs = tabs, selectedTabIndex = 4, onTabSelected = {})
                }
            }
        }
        waitForIdle()
        assertCentered("Tab 4")
        width = 360.dp
        waitForIdle()
        assertCentered("Tab 4")
    }

    @Test
    fun selectionChangedWithAResizeStillAnimates() = runComposeUiTest {
        var width by mutableStateOf(300.dp)
        var selected by mutableIntStateOf(3)
        setContent {
            MiuixTheme {
                Box(Modifier.layoutWidth { width }.testTag("row")) {
                    TabRowWithContour(tabs = tabs, selectedTabIndex = selected, onTabSelected = {})
                }
            }
        }
        waitForIdle()
        assertCentered("Tab 3")
        mainClock.autoAdvance = false
        width = 310.dp
        selected = 5
        mainClock.advanceTimeByFrame()
        // The resize re-centers the settled tab; the new one is still being scrolled to.
        assertCentered("Tab 3")
        mainClock.autoAdvance = true
        waitForIdle()
        assertCentered("Tab 5")
    }

    // (width - 2 * spacing) / 3: wider than the max, but three max-width tabs fit.
    private fun ComposeUiTest.assertTabWidth(rowWidth: Float) {
        val row = onNodeWithTag("row").fetchSemanticsNode().boundsInRoot
        val density = row.width / rowWidth
        val tab = onNodeWithText("Tab 0").fetchSemanticsNode().boundsInRoot
        assertEquals((rowWidth - 2 * 9f) / 3f * density, tab.width, 1.5f)
    }

    private fun ComposeUiTest.assertCentered(text: String) {
        val row = onNodeWithTag("row").fetchSemanticsNode().boundsInRoot
        val tab = onNodeWithText(text).fetchSemanticsNode().boundsInRoot
        assertEquals(row.center.x, tab.center.x, 1.5f)
    }

    private fun Modifier.layoutWidth(width: () -> Dp): Modifier = layout { measurable, constraints ->
        val placeable = measurable.measure(Constraints.fixedWidth(width().roundToPx()).copy(maxHeight = constraints.maxHeight))
        layout(placeable.width, placeable.height) { placeable.place(0, 0) }
    }
}
