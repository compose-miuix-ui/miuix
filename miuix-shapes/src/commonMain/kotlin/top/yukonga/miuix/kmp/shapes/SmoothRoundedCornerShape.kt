// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shapes

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

/**
 * A shape with smooth rounded corners that provides a gradual curvature transition
 * from straight edges to curves, rather than the abrupt transition of [RoundedCornerShape].
 *
 * @param cornerRadius the radius applied to all four corners
 */
class SmoothRoundedCornerShape(private val cornerRadius: Dp) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val radiusPx = with(density) { cornerRadius.toPx() }.coerceAtLeast(0f)
        if (radiusPx <= 0f || size.width <= 0f || size.height <= 0f) {
            return Outline.Rectangle(Rect(0f, 0f, size.width, size.height))
        }
        val path = Path()
        SmoothCornerPath.buildEqualPath(path, size.width, size.height, radiusPx)
        return Outline.Generic(path)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SmoothRoundedCornerShape) return false
        return cornerRadius == other.cornerRadius
    }

    override fun hashCode(): Int = cornerRadius.hashCode()

    override fun toString(): String = "SmoothRoundedCornerShape(cornerRadius=$cornerRadius)"
}
