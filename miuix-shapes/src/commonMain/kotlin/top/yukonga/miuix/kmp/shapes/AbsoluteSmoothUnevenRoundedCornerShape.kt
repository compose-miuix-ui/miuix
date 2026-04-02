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
 * A shape with smooth rounded corners using absolute (physical) corner positions,
 * independent of layout direction.
 *
 * Unlike [SmoothUnevenRoundedCornerShape] which uses logical start/end corners and
 * automatically mirrors for RTL, this class uses physical left/right corners that
 * remain fixed regardless of [LayoutDirection].
 *
 * @param topLeft the radius of the top-left corner
 * @param topRight the radius of the top-right corner
 * @param bottomRight the radius of the bottom-right corner
 * @param bottomLeft the radius of the bottom-left corner
 */
class AbsoluteSmoothUnevenRoundedCornerShape(
    private val topLeft: Dp = 0.dp,
    private val topRight: Dp = 0.dp,
    private val bottomRight: Dp = 0.dp,
    private val bottomLeft: Dp = 0.dp,
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        if (size.width <= 0f || size.height <= 0f) {
            return Outline.Rectangle(Rect(0f, 0f, size.width, size.height))
        }

        val tlPx = with(density) { topLeft.toPx() }.coerceAtLeast(0f)
        val trPx = with(density) { topRight.toPx() }.coerceAtLeast(0f)
        val brPx = with(density) { bottomRight.toPx() }.coerceAtLeast(0f)
        val blPx = with(density) { bottomLeft.toPx() }.coerceAtLeast(0f)

        if (tlPx <= 0f && trPx <= 0f && brPx <= 0f && blPx <= 0f) {
            return Outline.Rectangle(Rect(0f, 0f, size.width, size.height))
        }

        val path = Path()
        SmoothCornerPath.buildUnevenPath(
            path,
            size.width,
            size.height,
            tlPx,
            trPx,
            brPx,
            blPx,
        )
        return Outline.Generic(path)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbsoluteSmoothUnevenRoundedCornerShape) return false
        return topLeft == other.topLeft &&
            topRight == other.topRight &&
            bottomRight == other.bottomRight &&
            bottomLeft == other.bottomLeft
    }

    override fun hashCode(): Int {
        var result = topLeft.hashCode()
        result = 31 * result + topRight.hashCode()
        result = 31 * result + bottomRight.hashCode()
        result = 31 * result + bottomLeft.hashCode()
        return result
    }

    override fun toString(): String = "AbsoluteSmoothUnevenRoundedCornerShape(topLeft=$topLeft, topRight=$topRight, " +
        "bottomRight=$bottomRight, bottomLeft=$bottomLeft)"
}
