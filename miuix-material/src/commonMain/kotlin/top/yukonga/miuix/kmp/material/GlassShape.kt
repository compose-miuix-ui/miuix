// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.util.fastCoerceAtMost

/**
 * Defines the silhouette of a glass surface.
 *
 * @param topStart Corner size at the top-start corner.
 * @param topEnd Corner size at the top-end corner.
 * @param bottomEnd Corner size at the bottom-end corner.
 * @param bottomStart Corner size at the bottom-start corner.
 * @property smoothing 0 uses a circular corner and 1 uses the continuous corner profile. Values
 *   between them interpolate. The glass renderer uses this value; [Modifier.clip] and
 *   [createOutline] use a plain rounded rectangle.
 */
@Stable
class GlassShape(
    topStart: CornerSize,
    topEnd: CornerSize,
    bottomEnd: CornerSize,
    bottomStart: CornerSize,
    val smoothing: Float = 1f,
) : CornerBasedShape(topStart, topEnd, bottomEnd, bottomStart) {

    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection,
    ): Outline {
        val rect = Rect(0f, 0f, size.width, size.height)
        if (topStart + topEnd + bottomEnd + bottomStart == 0f) return Outline.Rectangle(rect)
        val isLtr = layoutDirection == LayoutDirection.Ltr
        return Outline.Rounded(
            RoundRect(
                rect = rect,
                topLeft = CornerRadius(if (isLtr) topStart else topEnd),
                topRight = CornerRadius(if (isLtr) topEnd else topStart),
                bottomRight = CornerRadius(if (isLtr) bottomEnd else bottomStart),
                bottomLeft = CornerRadius(if (isLtr) bottomStart else bottomEnd),
            ),
        )
    }

    /**
     * Whether the SDF silhouette diverges from the plain rounded rectangle produced by
     * [createOutline] at [size] and [density]. Capsules, circles, rectangles, and shapes with
     * zero smoothing match [createOutline] exactly, so hardware clipping suffices without an
     * offscreen [drawGlassMask] pass.
     */
    fun requiresContinuousMask(size: Size, density: Density): Boolean {
        if (smoothing <= 0.001f || size.width <= 0f || size.height <= 0f) return false
        val minHalf = size.minDimension * 0.5f
        return cornerDiverges(topStart, size, density, minHalf) ||
            cornerDiverges(topEnd, size, density, minHalf) ||
            cornerDiverges(bottomEnd, size, density, minHalf) ||
            cornerDiverges(bottomStart, size, density, minHalf)
    }

    private fun cornerDiverges(
        corner: CornerSize,
        size: Size,
        density: Density,
        minHalf: Float,
    ): Boolean {
        val r = corner.toPx(size, density).fastCoerceAtMost(minHalf)
        return r >= 0.5f && (minHalf - r) > 1.0f
    }

    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize,
    ): GlassShape = GlassShape(topStart, topEnd, bottomEnd, bottomStart, smoothing)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GlassShape) return false
        return topStart == other.topStart &&
            topEnd == other.topEnd &&
            bottomEnd == other.bottomEnd &&
            bottomStart == other.bottomStart &&
            smoothing == other.smoothing
    }

    override fun hashCode(): Int {
        var result = topStart.hashCode()
        result = 31 * result + topEnd.hashCode()
        result = 31 * result + bottomEnd.hashCode()
        result = 31 * result + bottomStart.hashCode()
        result = 31 * result + smoothing.hashCode()
        return result
    }

    override fun toString(): String = "GlassShape(topStart=$topStart, topEnd=$topEnd, bottomEnd=$bottomEnd, " +
        "bottomStart=$bottomStart, smoothing=$smoothing)"
}

/**
 * Creates a [GlassShape] with the same radius at all four corners.
 *
 * @param cornerRadius Radius applied to every corner.
 * @param smoothing 0 uses a circular corner and 1 uses the continuous corner profile.
 */
fun GlassShape(cornerRadius: Dp, smoothing: Float = GlassDefaults.Smoothing): GlassShape = GlassShape(
    CornerSize(cornerRadius),
    CornerSize(cornerRadius),
    CornerSize(cornerRadius),
    CornerSize(cornerRadius),
    smoothing,
)

/**
 * Creates a [GlassShape] with an individual radius for each corner, in the order used by
 * `RoundedCornerShape`.
 *
 * @param topStart Radius of the top-start corner.
 * @param topEnd Radius of the top-end corner.
 * @param bottomEnd Radius of the bottom-end corner.
 * @param bottomStart Radius of the bottom-start corner.
 * @param smoothing 0 uses a circular corner and 1 uses the continuous corner profile.
 */
fun GlassShape(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    smoothing: Float = GlassDefaults.Smoothing,
): GlassShape = GlassShape(
    CornerSize(topStart),
    CornerSize(topEnd),
    CornerSize(bottomEnd),
    CornerSize(bottomStart),
    smoothing,
)
