// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.glass.internal.drawGlassRim
import top.yukonga.miuix.kmp.glass.internal.drawGlassStroke
import kotlin.test.Test

class GlassOverlaysTest {

    @Test
    fun rimDrawsAtOnePixelAndSubpixelSizes() {
        forEachSize {
            drawGlassRim(
                shape = GlassShape(16.dp),
                layoutDirection = layoutDirection,
                style = GlassDefaults.Style,
                surface = Color.White,
                alpha = 1f,
            )
        }
    }

    @Test
    fun bloomStrokeDrawsAtOnePixelAndSubpixelSizes() {
        forEachSize {
            drawGlassStroke(
                shape = GlassShape(16.dp),
                layoutDirection = layoutDirection,
                stroke = GlassStrokes.SmallLight,
                alpha = 1f,
            )
        }
    }

    private fun forEachSize(draw: DrawScope.() -> Unit) {
        val bitmap = ImageBitmap(32, 32)
        val canvas = Canvas(bitmap)
        val scope = CanvasDrawScope()
        // Cover both orientations, the clamp thresholds, and the existing empty-size guard.
        for (shortSide in listOf(0f, 0.5f, 1f, 1.5f, 2f, 32f)) {
            for (size in listOf(Size(shortSide, 32f), Size(32f, shortSide))) {
                scope.draw(Density(1f), LayoutDirection.Ltr, canvas, size, draw)
            }
        }
    }
}
