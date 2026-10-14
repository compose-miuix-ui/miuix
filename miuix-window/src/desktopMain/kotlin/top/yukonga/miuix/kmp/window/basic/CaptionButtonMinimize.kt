// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.basic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.Text

@Composable
fun MiuixCaptionButtonMinimize(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    height: Dp = 32.dp,
    iconFontFamily: FontFamily? = null,
    contentDescription: String? = "Minimize",
) {
    MiuixCaptionButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        height = height,
        contentDescription = contentDescription,
    ) { iconColor ->
        if (iconFontFamily != null) {
            Text(
                text = CaptionButtonMinimizeIconGlyph.toString(),
                color = iconColor,
                fontSize = 10.sp,
                fontFamily = iconFontFamily,
            )
        } else {
            Canvas(modifier = Modifier.size(16.dp)) {
                val scale = size.width / 16f
                drawRect(
                    color = iconColor,
                    topLeft = Offset(3f * scale, 7.5f * scale),
                    size = Size(10f * scale, 1f * scale),
                )
            }
        }
    }
}
