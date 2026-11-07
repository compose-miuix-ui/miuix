// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.LayoutDirection
import kotlin.test.Test
import kotlin.test.assertEquals

class GlassDropdownPopupTest {
    private val page = Size(400f, 800f)
    private val row = Rect(12f, 200f, 388f, 260f)
    private val panel = Size(200f, 300f)

    @Test
    fun rightHandValueAlignsInsideTheRowPadding() {
        val value = Rect(290f, 214f, 372f, 246f)
        val result = placeGlassDropdownPopup(row, value, panel, 12f, page)
        assertEquals(Rect(172f, 200f, 372f, 500f), result.rect)
        assertEquals(true, result.alignTop)
    }

    @Test
    fun leftHandValueAlignsItsLeftEdgeIncludingMirroredLayouts() {
        val value = Rect(28f, 214f, 110f, 246f)
        assertEquals(Rect(28f, 200f, 228f, 500f), placeGlassDropdownPopup(row, value, panel, 12f, page).rect)
    }

    @Test
    fun alignmentUsesValuePositionRatherThanLayoutDirectionOrRowCenter() {
        val value = Rect(150f, 214f, 190f, 246f)
        assertEquals(Rect(150f, 200f, 350f, 500f), placeGlassDropdownPopup(row, value, panel, 12f, page).rect)
    }

    @Test
    fun valueAlignmentStillRespectsBothSafeEdges() {
        val leftValue = Rect(0f, 214f, 40f, 246f)
        val rightValue = Rect(360f, 214f, 400f, 246f)
        assertEquals(12f, placeGlassDropdownPopup(row, leftValue, panel, 12f, page).rect.left)
        assertEquals(388f, placeGlassDropdownPopup(row, rightValue, panel, 12f, page).rect.right)
    }

    @Test
    fun verticalOverflowKeepsExistingRowPlacement() {
        val lowRow = Rect(12f, 650f, 388f, 710f)
        val value = Rect(290f, 664f, 372f, 696f)
        val original = placeGlassPopup(lowRow, panel, 12f, page, LayoutDirection.Ltr)
        val result = placeGlassDropdownPopup(lowRow, value, panel, 12f, page)
        assertEquals(original.rect.top, result.rect.top)
        assertEquals(original.rect.bottom, result.rect.bottom)
        assertEquals(original.alignTop, result.alignTop)
        assertEquals(372f, result.rect.right)
    }

    @Test
    fun callersWithoutAValueAnchorKeepExistingPlacement() {
        for (direction in listOf(LayoutDirection.Ltr, LayoutDirection.Rtl)) {
            val original = placeGlassPopup(row, panel, 12f, page, direction)
            val result = placeGlassDropdownPopup(row, null, panel, 12f, page, direction)
            assertEquals(original.rect, result.rect)
            assertEquals(original.alignTop, result.alignTop)
        }
    }
}
