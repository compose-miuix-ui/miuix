// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.basic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.Text

@Composable
fun MiuixCaptionButtonClose(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp = 32.dp,
    iconFontFamily: FontFamily? = null,
    contentDescription: String? = "Close",
) {
    MiuixCaptionButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        height = height,
        isClose = true,
        contentDescription = contentDescription,
    ) { iconColor ->
        if (iconFontFamily != null) {
            Text(
                text = CaptionButtonCloseIconGlyph.toString(),
                color = iconColor,
                fontSize = 10.sp,
                fontFamily = iconFontFamily,
            )
        } else {
            Canvas(modifier = Modifier.size(16.dp)) {
                val s = size.width / 16f
                val stroke = 1f * s
                drawLine(
                    color = iconColor,
                    start = Offset(4f * s, 4f * s),
                    end = Offset(12f * s, 12f * s),
                    strokeWidth = stroke,
                )
                drawLine(
                    color = iconColor,
                    start = Offset(12f * s, 4f * s),
                    end = Offset(4f * s, 12f * s),
                    strokeWidth = stroke,
                )
            }
        }
    }
}
