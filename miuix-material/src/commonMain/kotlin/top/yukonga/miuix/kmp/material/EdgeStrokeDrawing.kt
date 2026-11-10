// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.material.internal.drawEdgeStrokeShape

/**
 * Draws [stroke] along the silhouette of [shape], on top of whatever was drawn before it.
 *
 * The silhouette is a rounded superellipse, so [shape] has to be a rounded-corner shape, such as
 * `RoundedCornerShape` or [GlassShape]. A [GlassShape] also contributes its continuous-corner
 * smoothing. [alpha] is an overall opacity multiplier such as a press progress. Skips when the
 * platform has no runtime shader, or when [shape] has no rounded silhouette to follow.
 */
fun DrawScope.drawEdgeStroke(
    shape: Shape,
    stroke: EdgeStroke,
    alpha: Float = 1f,
) {
    if (stroke.width.value <= 0f || alpha <= 0f) return
    if (shape is CutCornerShape) return
    val cornerShape = shape as? CornerBasedShape ?: return
    val isLtr = layoutDirection == LayoutDirection.Ltr
    val topStart = cornerShape.topStart.toPx(size, this)
    val topEnd = cornerShape.topEnd.toPx(size, this)
    val bottomEnd = cornerShape.bottomEnd.toPx(size, this)
    val bottomStart = cornerShape.bottomStart.toPx(size, this)
    drawEdgeStrokeShape(
        topLeft = if (isLtr) topStart else topEnd,
        topRight = if (isLtr) topEnd else topStart,
        bottomRight = if (isLtr) bottomEnd else bottomStart,
        bottomLeft = if (isLtr) bottomStart else bottomEnd,
        smoothing = (shape as? GlassShape)?.smoothing ?: 0f,
        stroke = stroke,
        alpha = alpha,
    )
}
