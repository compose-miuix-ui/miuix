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
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassPopupMeasurementTest {
    @Test
    fun intrinsicTraversalStopsAtEightButMeasuresAllRows() = runComposeUiTest {
        val widths = IntArray(24)
        val heights = IntArray(24)
        val measured = BooleanArray(24)
        setContent {
            DialogTestHost {
                Box(Modifier.size(600.dp)) {
                    GlassPopup(
                        show = true,
                        onDismissRequest = {},
                        anchorBounds = Rect(20f, 20f, 60f, 60f),
                        backdrop = null,
                    ) {
                        repeat(24) { index ->
                            Layout(
                                content = {},
                                measurePolicy = object : MeasurePolicy {
                                    override fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): androidx.compose.ui.layout.MeasureResult {
                                        measured[index] = true
                                        return layout(constraints.maxWidth, 44.dp.roundToPx()) {}
                                    }

                                    override fun IntrinsicMeasureScope.maxIntrinsicWidth(measurables: List<IntrinsicMeasurable>, height: Int): Int {
                                        widths[index]++
                                        return 160.dp.roundToPx()
                                    }

                                    override fun IntrinsicMeasureScope.minIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int {
                                        heights[index]++
                                        return 44.dp.roundToPx()
                                    }
                                },
                            )
                        }
                    }
                }
            }
        }
        waitForIdle()
        for (index in 0 until 24) {
            assertTrue(measured[index], "Row $index must remain in the scrollable content")
            if (index < 8) {
                assertTrue(widths[index] > 0 && heights[index] > 0)
            } else {
                assertEquals(0, widths[index], "No width probe past row eight")
                assertEquals(0, heights[index], "No height probe past row eight")
            }
        }
        println("Intrinsic probes: ${widths.count { it > 0 }} widths, ${heights.count { it > 0 }} heights; measured ${measured.count { it }} rows")
    }

    @Test
    fun laterWideItemsDoNotResizeTheMenuButRemainScrollableAndSelectable() = runComposeUiTest {
        val selected = mutableIntStateOf(0)
        val longLabel = "This ninth option must not widen the menu"
        setContent {
            DialogTestHost {
                Box(Modifier.size(600.dp).background(Color.White)) {
                    GlassPopup(
                        show = true,
                        onDismissRequest = {},
                        anchorBounds = Rect(20f, 20f, 60f, 60f),
                        backdrop = null,
                        sizing = GlassPopupSizing(minWidth = 80.dp, maxWidth = 500.dp, maxHeight = 500.dp),
                    ) {
                        repeat(24) { index ->
                            GlassPopupItem(
                                text = if (index == 8) longLabel else "Row $index",
                                selected = selected.intValue == index,
                                onClick = { selected.intValue = index },
                            )
                        }
                    }
                }
            }
        }
        val first = onNodeWithText("Row 0").fetchSemanticsNode().boundsInRoot
        assertTrue(first.width < with(density) { 200.dp.toPx() }, "A long ninth row must not determine popup width")
        onNodeWithText("Row 7").assertIsDisplayed()
        onNodeWithText(longLabel).performScrollTo().performClick()
        onNodeWithText(longLabel).assertIsSelected()
        assertEquals(first.width, onNodeWithText(longLabel).fetchSemanticsNode().boundsInRoot.width, 1f)
        onNodeWithText("Row 23").performScrollTo().performClick()
        onNodeWithText("Row 23").assertIsDisplayed().assertIsSelected()
        assertEquals(23, selected.intValue)
        assertEquals(first.width, onNodeWithText("Row 23").fetchSemanticsNode().boundsInRoot.width, 1f)
        saveImage("popup-sampled-last-row", onRoot().captureToImage())
        onNodeWithText("Row 0").performScrollTo()
        onNodeWithText("Row 0").assertIsDisplayed()
    }
}
