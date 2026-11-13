// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

/** Offset hosts must place glass popups from window coordinates, like the overlay list popup. */
@OptIn(ExperimentalTestApi::class)
class GlassPopupHostOffsetTest {

    @Test
    fun panelKeepsItsWindowPositionInsideAnOffsetHost() = runComposeUiTest {
        val windowLeft = 200f
        val windowTop = 150f
        setContent {
            DialogTestHost {
                Box(Modifier.size(600.dp)) {
                    Box(Modifier.offset(x = 80.dp, y = 40.dp).size(400.dp)) {
                        GlassPopupSurface(
                            onDismissRequest = {},
                            backdrop = null,
                            sizing = GlassPopupSizing(minWidth = 200.dp, maxWidth = 200.dp),
                            visuals = GlassPopupDefaults.visuals(stroke = null, shadow = null),
                            contentPadding = PaddingValues(0.dp),
                            panelLayer = {},
                            overlay = {},
                            frame = { end, _ -> GlassPopupFrame(Rect(windowLeft, windowTop, windowLeft + end.width, windowTop + end.height), 0.dp) },
                            contentLayer = { _, _ -> },
                        ) {
                            Box(Modifier.fillMaxWidth().height(80.dp).testTag("content"))
                        }
                    }
                }
            }
        }
        waitForIdle()

        // The host starts at (80dp, 40dp) in the window, so the panel's root position must still
        // equal its window position.
        assertEquals(windowLeft, onNodeWithTag("content").fetchSemanticsNode().boundsInRoot.left, 1f)
        assertEquals(windowTop, onNodeWithTag("content").fetchSemanticsNode().boundsInRoot.top, 1f)
    }

    @Test
    fun popupAlignsToItsAnchorInsideAnOffsetHost() = assertPopupAnchor(120.dp)

    private fun assertPopupAnchor(top: Dp) = runComposeUiTest {
        val anchor = with(density) { Rect(300.dp.toPx(), top.toPx(), 400.dp.toPx(), (top + 40.dp).toPx()) }
        setContent {
            DialogTestHost {
                Box(Modifier.fillMaxSize()) {
                    Box(Modifier.offset(x = 80.dp, y = 40.dp).fillMaxSize()) {
                        GlassPopup(
                            show = true,
                            onDismissRequest = {},
                            anchorBounds = anchor,
                            backdrop = null,
                            sizing = GlassPopupSizing(minWidth = 200.dp, maxWidth = 200.dp),
                            contentPadding = PaddingValues(0.dp),
                        ) {
                            Box(Modifier.fillMaxWidth().height(60.dp).testTag("row"))
                        }
                    }
                }
            }
        }
        waitForIdle()

        val row = onNodeWithTag("row").fetchSemanticsNode().boundsInRoot
        assertEquals(anchor.right, row.right, 1f)
        assertEquals(anchor.top, row.top, 1f)
    }

    @Test
    fun closingAnimationKeepsItsPositionInsideAnOffsetHost() = runComposeUiTest {
        mainClock.autoAdvance = false
        val show = mutableStateOf(true)
        val anchor = anchorRect(density)
        setContent {
            DialogTestHost {
                Box(Modifier.fillMaxSize()) {
                    Box(Modifier.offset(x = 80.dp, y = 40.dp).fillMaxSize()) {
                        GlassPopup(
                            show = show.value,
                            onDismissRequest = {},
                            anchorBounds = anchor,
                            backdrop = null,
                            sizing = GlassPopupSizing(minWidth = 200.dp, maxWidth = 200.dp),
                            contentPadding = PaddingValues(0.dp),
                        ) {
                            Box(Modifier.fillMaxWidth().height(60.dp).testTag("row"))
                        }
                    }
                }
            }
        }
        mainClock.advanceTimeBy(1000)
        waitForIdle()
        val opened = onNodeWithTag("row").fetchSemanticsNode().boundsInRoot
        assertEquals(anchor.right, opened.right, 1f)

        runOnIdle { show.value = false }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        // The exit animation must still run instead of unmounting immediately or jumping.
        val closing = onNodeWithTag("row").fetchSemanticsNode().boundsInRoot
        assertEquals(opened.right, closing.right, 1f)

        mainClock.advanceTimeBy(1000)
        waitForIdle()
        onNodeWithTag("row").assertDoesNotExist()
    }

    private fun anchorRect(density: Density): Rect = with(density) {
        Rect(300.dp.toPx(), 120.dp.toPx(), 400.dp.toPx(), 160.dp.toPx())
    }
}
