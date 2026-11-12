// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.LayoutDirection
import kotlin.test.Test
import kotlin.test.assertEquals

class GlassDropdownPopupTest {
    private val window = Rect(0f, 0f, 400f, 800f)
    private val row = Rect(12f, 200f, 388f, 260f)
    private val panel = Size(200f, 300f)

    @Test
    fun rightHandValueAlignsInsideTheRowPadding() {
        val value = Rect(290f, 214f, 372f, 246f)
        val result = placeGlassDropdownPopup(row, value, panel, 12f, window)
        assertEquals(Rect(172f, 214f, 372f, 514f), result.rect)
        assertEquals(true, result.alignTop)
    }

    @Test
    fun leftHandValueAlignsItsLeftEdgeIncludingMirroredLayouts() {
        val value = Rect(28f, 214f, 110f, 246f)
        assertEquals(Rect(28f, 214f, 228f, 514f), placeGlassDropdownPopup(row, value, panel, 12f, window).rect)
    }

    @Test
    fun alignmentUsesValuePositionRatherThanLayoutDirectionOrRowCenter() {
        val value = Rect(150f, 214f, 190f, 246f)
        assertEquals(Rect(150f, 214f, 350f, 514f), placeGlassDropdownPopup(row, value, panel, 12f, window).rect)
    }

    @Test
    fun valueAlignmentStillRespectsBothSafeEdges() {
        val leftValue = Rect(0f, 214f, 40f, 246f)
        val rightValue = Rect(360f, 214f, 400f, 246f)
        assertEquals(12f, placeGlassDropdownPopup(row, leftValue, panel, 12f, window).rect.left)
        assertEquals(388f, placeGlassDropdownPopup(row, rightValue, panel, 12f, window).rect.right)
    }

    @Test
    fun verticalPlacementFollowsTheValueInsteadOfTheRow() {
        val lowRow = Rect(12f, 650f, 388f, 710f)
        val value = Rect(290f, 664f, 372f, 696f)
        val original = placeGlassPopup(value, panel, 12f, window, LayoutDirection.Ltr)
        val result = placeGlassDropdownPopup(lowRow, value, panel, 12f, window)
        assertEquals(original.rect.top, result.rect.top)
        assertEquals(original.rect.bottom, result.rect.bottom)
        assertEquals(original.alignTop, result.alignTop)
        assertEquals(372f, result.rect.right)
    }

    @Test
    fun callersWithoutAValueAnchorKeepExistingPlacement() {
        for (direction in listOf(LayoutDirection.Ltr, LayoutDirection.Rtl)) {
            val original = placeGlassPopup(row, panel, 12f, window, direction)
            val result = placeGlassDropdownPopup(row, null, panel, 12f, window, direction)
            assertEquals(original.rect, result.rect)
            assertEquals(original.alignTop, result.alignTop)
        }
    }

    @Test
    fun topEdgeAnchorUsesItsPositionInsideTheSafeMargin() {
        val anchor = Rect(100f, 4f, 200f, 44f)
        val result = placeGlassPopup(anchor, Size(100f, 200f), 12f, window, LayoutDirection.Ltr)
        assertEquals(4f, result.rect.top)
    }
}
