// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shapes

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * A shape with smooth rounded corners where each corner can have an independent radius,
 * providing a gradual curvature transition from straight edges to curves.
 *
 * @param topStart the radius of the top-start corner
 * @param topEnd the radius of the top-end corner
 * @param bottomEnd the radius of the bottom-end corner
 * @param bottomStart the radius of the bottom-start corner
 */
class SmoothUnevenRoundedCornerShape(
    private val topStart: Dp = 0.dp,
    private val topEnd: Dp = 0.dp,
    private val bottomEnd: Dp = 0.dp,
    private val bottomStart: Dp = 0.dp,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        if (size.width <= 0f || size.height <= 0f) {
            return Outline.Rectangle(Rect(0f, 0f, size.width, size.height))
        }

        val tsrPx = with(density) { topStart.toPx() }.coerceAtLeast(0f)
        val terPx = with(density) { topEnd.toPx() }.coerceAtLeast(0f)
        val berPx = with(density) { bottomEnd.toPx() }.coerceAtLeast(0f)
        val bsrPx = with(density) { bottomStart.toPx() }.coerceAtLeast(0f)

        // Resolve for layout direction
        val rTsr: Float
        val rTer: Float
        val rBer: Float
        val rBsr: Float
        if (layoutDirection == LayoutDirection.Rtl) {
            rTsr = terPx
            rTer = tsrPx
            rBer = bsrPx
            rBsr = berPx
        } else {
            rTsr = tsrPx
            rTer = terPx
            rBer = berPx
            rBsr = bsrPx
        }

        if (rTsr <= 0f && rTer <= 0f && rBer <= 0f && rBsr <= 0f) {
            return Outline.Rectangle(Rect(0f, 0f, size.width, size.height))
        }

        val path = Path()
        SmoothCornerPath.buildUnevenPath(
            path,
            size.width,
            size.height,
            rTsr,
            rTer,
            rBer,
            rBsr,
        )
        return Outline.Generic(path)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SmoothUnevenRoundedCornerShape) return false
        return topStart == other.topStart &&
            topEnd == other.topEnd &&
            bottomEnd == other.bottomEnd &&
            bottomStart == other.bottomStart
    }

    override fun hashCode(): Int {
        var result = topStart.hashCode()
        result = 31 * result + topEnd.hashCode()
        result = 31 * result + bottomEnd.hashCode()
        result = 31 * result + bottomStart.hashCode()
        return result
    }

    override fun toString(): String = "SmoothUnevenRoundedCornerShape(topStart=$topStart, topEnd=$topEnd, " +
        "bottomEnd=$bottomEnd, bottomStart=$bottomStart)"
}
