// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.shapes.SmoothRoundedCornerShape
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.shapeComparisonSection() {
    item(key = "shapeComparison") {
        SmallTitle(text = "Shape Comparison")
        var radius by remember { mutableFloatStateOf(48f) }
        val primaryColor = MiuixTheme.colorScheme.primary
        val errorColor = MiuixTheme.colorScheme.error
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                    .drawWithContent {
                        val smoothOutline = SmoothRoundedCornerShape(radius.dp)
                            .createOutline(size, LayoutDirection.Ltr, this as Density)
                        val standardOutline = RoundedCornerShape(radius.dp)
                            .createOutline(size, LayoutDirection.Ltr, this as Density)

                        // Background: standard shape filled with secondary color
                        drawOutline(standardOutline, primaryColor)
                        drawContent()

                        // Diff: XOR in a separate layer so it doesn't affect the background
                        drawContext.canvas.saveLayer(
                            Rect(0f, 0f, size.width, size.height),
                            Paint(),
                        )
                        drawOutline(smoothOutline, errorColor)
                        drawOutline(standardOutline, errorColor, blendMode = BlendMode.Xor)
                        drawContext.canvas.restore()
                    },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Red = Diff, R = ${radius.toInt()}dp",
                    color = MiuixTheme.colorScheme.onPrimary,
                )
            }
            Slider(
                value = radius,
                onValueChange = { radius = it },
                valueRange = 0f..80f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 12.dp),
            )
        }
    }
}
