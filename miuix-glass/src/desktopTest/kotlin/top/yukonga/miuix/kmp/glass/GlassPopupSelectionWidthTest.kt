// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassPopupSelectionWidthTest {
    @Test
    fun selectionDoesNotResizeAnIntrinsicallyMeasuredMenu() = runComposeUiTest {
        val selected = mutableIntStateOf(0)
        val labels = listOf("Short", "A considerably longer choice")
        setContent {
            DialogTestHost {
                Box(Modifier.size(600.dp).background(Color.White)) {
                    GlassPopup(
                        show = true,
                        onDismissRequest = {},
                        anchorBounds = Rect(20f, 20f, 60f, 60f),
                        backdrop = null,
                        sizing = GlassPopupSizing(minWidth = 80.dp, maxWidth = 500.dp),
                    ) {
                        labels.forEachIndexed { index, label ->
                            GlassPopupItem(
                                text = label,
                                selected = selected.intValue == index,
                                onClick = { selected.intValue = if (selected.intValue == index) -1 else index },
                            )
                        }
                    }
                }
            }
        }
        val before = onNodeWithText(labels[1]).fetchSemanticsNode().boundsInRoot
        val minimum = with(density) { 80.dp.toPx() }
        val maximum = with(density) { 500.dp.toPx() }
        assertTrue(before.width > minimum && before.width < maximum, "Exercise intrinsic sizing, not a clamped panel")
        onNodeWithText(labels[1]).performClick()
        onNodeWithText(labels[1]).assertIsSelected()
        assertEquals(before, onNodeWithText(labels[1]).fetchSemanticsNode().boundsInRoot)
        saveImage("popup-selection-long", onRoot().captureToImage())
        onNodeWithText(labels[1]).performClick()
        assertEquals(-1, selected.intValue)
        assertEquals(before, onNodeWithText(labels[1]).fetchSemanticsNode().boundsInRoot)
        saveImage("popup-selection-none", onRoot().captureToImage())
        onNodeWithText(labels[0]).performClick()
        onNodeWithText(labels[0]).assertIsSelected()
        assertEquals(before, onNodeWithText(labels[1]).fetchSemanticsNode().boundsInRoot)
    }
}
