// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shapes

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import kotlin.math.min

/**
 * A capsule (stadium) shape with smooth rounded ends that provides a gradual
 * curvature transition from straight edges to curves.
 *
 * The corner radius is automatically set to half of the shorter dimension.
 */
class SmoothCapsuleShape : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        if (size.width <= 0f || size.height <= 0f) {
            return Outline.Rectangle(Rect(0f, 0f, size.width, size.height))
        }
        val radius = min(size.width, size.height) / 2f
        val path = Path()
        SmoothCornerPath.buildEqualPath(path, size.width, size.height, radius)
        return Outline.Generic(path)
    }

    override fun equals(other: Any?): Boolean = other is SmoothCapsuleShape

    override fun hashCode(): Int = this::class.hashCode()

    override fun toString(): String = "SmoothCapsuleShape"
}
