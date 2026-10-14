// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.basic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.Text

@Composable
fun MiuixCaptionButtonMaximize(
    onClick: () -> Unit,
    maximized: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp = 32.dp,
    iconFontFamily: FontFamily? = null,
    contentDescription: String? = if (maximized) "Restore" else "Maximize",
) {
    MiuixCaptionButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        height = height,
        contentDescription = contentDescription,
    ) { iconColor ->
        if (iconFontFamily != null) {
            val glyph = if (maximized) {
                CaptionButtonRestoreIconGlyph
            } else {
                CaptionButtonMaximizeIconGlyph
            }
            Text(
                text = glyph.toString(),
                color = iconColor,
                fontSize = 10.sp,
                fontFamily = iconFontFamily,
            )
        } else {
            Canvas(modifier = Modifier.size(16.dp)) {
                val s = size.width / 16f
                if (!maximized) {
                    drawRect(
                        color = iconColor,
                        topLeft = Offset(3.5f * s, 3.5f * s),
                        size = Size(9f * s, 9f * s),
                        style = Stroke(width = 1f * s),
                    )
                } else {
                    val backPath = Path().apply {
                        moveTo(5f * s, 3f * s)
                        lineTo(13f * s, 3f * s)
                        lineTo(13f * s, 11f * s)
                        lineTo(10f * s, 11f * s)
                        lineTo(10f * s, 10f * s)
                        lineTo(12f * s, 10f * s)
                        lineTo(12f * s, 4f * s)
                        lineTo(6f * s, 4f * s)
                        lineTo(6f * s, 6f * s)
                        lineTo(5f * s, 6f * s)
                        close()
                    }
                    drawPath(backPath, color = iconColor)

                    val frontPath = Path().apply {
                        fillType = PathFillType.EvenOdd
                        moveTo(11f * s, 5f * s)
                        lineTo(3f * s, 5f * s)
                        lineTo(3f * s, 13f * s)
                        lineTo(11f * s, 13f * s)
                        close()

                        moveTo(10f * s, 6f * s)
                        lineTo(4f * s, 6f * s)
                        lineTo(4f * s, 12f * s)
                        lineTo(10f * s, 12f * s)
                        close()
                    }
                    drawPath(frontPath, color = iconColor)
                }
            }
        }
    }
}
