// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Search
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassIconDropdownMenuTest {
    @Test
    fun submenuUsesTheClickedRowAndPreservesWidthAtHighDensity() = checkCascade(LayoutDirection.Ltr, false)

    @Test
    fun submenuUsesTheClickedRowInRtlAndCollapsesOnSelection() = checkCascade(LayoutDirection.Rtl, true)

    private fun checkCascade(direction: LayoutDirection, collapseOnSelection: Boolean) = runComposeUiTest {
        val show = mutableStateOf(false)
        val selection = mutableIntStateOf(0)
        setContent {
            CompositionLocalProvider(
                LocalDensity provides Density(2f),
                LocalLayoutDirection provides direction,
            ) {
                DialogTestHost {
                    val backdrop = rememberLayerBackdrop()
                    val anchor = rememberGlassPopupAnchor()
                    Box(Modifier.size(420.dp, 600.dp)) {
                        Box(Modifier.fillMaxSize().layerBackdrop(backdrop).background(Color.White))
                        GlassIconButton(
                            onClick = { show.value = true },
                            modifier = Modifier.offset(320.dp, 30.dp)
                                .glassPopupAnchor(anchor, 20.dp)
                                .testTag("trigger"),
                            backdrop = backdrop,
                        ) {
                            Icon(
                                MiuixIcons.Basic.Search,
                                contentDescription = "Options",
                                modifier = Modifier.size(24.dp).glassPopupAnchorContent(anchor),
                            )
                        }
                        GlassIconDropdownMenu(
                            show = show.value,
                            onDismissRequest = { show.value = false },
                            anchor = anchor,
                            anchorContent = { Icon(MiuixIcons.Basic.Search, contentDescription = null) },
                            entries = listOf(
                                DropdownEntry(
                                    listOf(
                                        DropdownItem(
                                            "View mode",
                                            children = listOf(
                                                DropdownItem("Group by date", selected = selection.intValue == 0, onClick = { selection.intValue = 0 }),
                                                DropdownItem("Compact", selected = selection.intValue == 1, onClick = { selection.intValue = 1 }),
                                            ),
                                        ),
                                        DropdownItem("Filter", children = listOf(DropdownItem("Camera album"))),
                                    ),
                                ),
                            ),
                            backdrop = backdrop,
                            collapseOnSelection = collapseOnSelection,
                        )
                    }
                }
            }
        }
        onNodeWithTag("trigger").performClick()
        val parent = onNodeWithText("View mode").fetchSemanticsNode().boundsInRoot
        onNodeWithText("View mode").performClick()
        val header = onNodeWithText("View mode").fetchSemanticsNode().boundsInRoot
        assertEquals(parent.width, header.width, 1f, "Submenu must inherit primary width, not treat pixels as dp")
        assertEquals(parent.top + 16f, header.top, 2f, "Submenu must open from View mode, not the last Filter row")
        onNodeWithText("Compact").assertIsDisplayed()
        saveImage("icon-menu-cascade-${direction.name}", onRoot().captureToImage())

        onNodeWithText("View mode").performClick()
        onNodeWithText("Compact").assertDoesNotExist()
        val returned = onNodeWithText("View mode").fetchSemanticsNode().boundsInRoot
        assertEquals(parent, returned, "Primary must return to its original geometry after closing the submenu")
        onNodeWithText("Filter").performClick()
        onNodeWithText("Camera album").assertIsDisplayed()
        onNodeWithText("Filter").performClick()
        onNodeWithText("View mode").performClick()
        if (collapseOnSelection) {
            val before = selectedPixels(onRoot().captureToImage())
            mainClock.autoAdvance = false
            onNodeWithText("Group by date").performClick()
            mainClock.advanceTimeBy(80)
            val frame = onRoot().captureToImage()
            val closing = selectedPixels(frame)
            assertTrue(closing.width < before.width * 0.95f, "Secondary contents must shrink with the closing menu")
            assertTrue(closing.center.y < before.center.y, "Secondary must travel toward the toolbar trigger")
            saveImage("icon-menu-cascade-closing", frame)
            mainClock.autoAdvance = true
            waitForIdle()
        } else {
            onNodeWithText("Compact").performClick()
        }
        assertEquals(if (collapseOnSelection) 0 else 1, selection.intValue)
        if (collapseOnSelection) {
            assertFalse(show.value)
            onNodeWithText("Compact").assertDoesNotExist()
            onNodeWithTag("trigger").performClick()
            onNodeWithText("Filter").assertIsDisplayed()
            onNodeWithText("Compact").assertDoesNotExist()
            onNodeWithText("View mode").performClick()
            onNodeWithText("Group by date").assertIsSelected()
        } else {
            onNodeWithText("Compact").assertIsSelected()
            onNodeWithText("Group by date").performClick()
            onNodeWithText("Group by date").assertIsSelected()
            assertEquals(0, selection.intValue)
        }
    }

    private fun selectedPixels(image: ImageBitmap): Rect {
        val pixels = image.toPixelMap()
        var left = image.width
        var right = -1
        var top = image.height
        var bottom = -1
        for (y in 0 until image.height) {
            for (x in 0 until image.width) {
                val color = pixels[x, y]
                if (color.blue - color.red > 0.15f && color.blue - color.green > 0.1f) {
                    left = minOf(left, x)
                    right = maxOf(right, x)
                    top = minOf(top, y)
                    bottom = maxOf(bottom, y)
                }
            }
        }
        assertTrue(right >= left, "The selected submenu row must still be visible during dismissal")
        return Rect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
    }
}
