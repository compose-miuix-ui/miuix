// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButton

@Composable
internal fun LinuxSystemCaptionButton(
    type: LinuxCaptionButtonType,
    onClick: () -> Unit,
    iconPack: LinuxCaptionIconPack,
    modifier: Modifier = Modifier,
    iconTheme: String? = null,
    enabled: Boolean = true,
    maximized: Boolean = false,
    height: Dp = 32.dp,
    iconLoader: LinuxSystemIconLoader = LinuxSystemIconLoader.Default,
) {
    MiuixCaptionButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        height = height,
        isClose = type == LinuxCaptionButtonType.Close,
        contentDescription = when (type) {
            LinuxCaptionButtonType.Minimize -> "Minimize"
            LinuxCaptionButtonType.Maximize -> if (maximized) "Restore" else "Maximize"
            LinuxCaptionButtonType.Close -> "Close"
        },
    ) { iconColor ->
        val iconSize = 16.dp
        val pixelSize = with(LocalDensity.current) { iconSize.roundToPx() }.coerceAtLeast(1)
        val systemIcon = rememberLinuxSystemCaptionIcon(
            iconPack = iconPack,
            iconTheme = iconTheme,
            type = type,
            maximized = maximized,
            pixelSize = pixelSize,
            iconLoader = iconLoader,
        )
        if (systemIcon != null) {
            Image(
                bitmap = systemIcon,
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                colorFilter = ColorFilter.tint(iconColor),
            )
        } else {
            Canvas(modifier = Modifier.size(iconSize)) {
                val scale = size.width / 16f
                val stroke = if (iconPack == LinuxCaptionIconPack.Kde) 1.2f else 1f
                when (type) {
                    LinuxCaptionButtonType.Minimize -> {
                        drawRect(
                            color = iconColor,
                            topLeft = Offset(3f * scale, 7.5f * scale),
                            size = Size(10f * scale, stroke * scale),
                        )
                    }

                    LinuxCaptionButtonType.Maximize -> {
                        if (!maximized) {
                            drawRect(
                                color = iconColor,
                                topLeft = Offset(3.5f * scale, 3.5f * scale),
                                size = Size(9f * scale, 9f * scale),
                                style = Stroke(width = stroke * scale),
                            )
                        } else {
                            val backPath = Path().apply {
                                moveTo(5f * scale, 3f * scale)
                                lineTo(13f * scale, 3f * scale)
                                lineTo(13f * scale, 11f * scale)
                                lineTo(10f * scale, 11f * scale)
                                lineTo(10f * scale, 10f * scale)
                                lineTo(12f * scale, 10f * scale)
                                lineTo(12f * scale, 4f * scale)
                                lineTo(6f * scale, 4f * scale)
                                lineTo(6f * scale, 6f * scale)
                                lineTo(5f * scale, 6f * scale)
                                close()
                            }
                            drawPath(backPath, color = iconColor)

                            val frontPath = Path().apply {
                                fillType = PathFillType.EvenOdd
                                moveTo(11f * scale, 5f * scale)
                                lineTo(3f * scale, 5f * scale)
                                lineTo(3f * scale, 13f * scale)
                                lineTo(11f * scale, 13f * scale)
                                close()
                                moveTo(10f * scale, 6f * scale)
                                lineTo(4f * scale, 6f * scale)
                                lineTo(4f * scale, 12f * scale)
                                lineTo(10f * scale, 12f * scale)
                                close()
                            }
                            drawPath(frontPath, color = iconColor)
                        }
                    }

                    LinuxCaptionButtonType.Close -> {
                        drawLine(
                            color = iconColor,
                            start = Offset(4f * scale, 4f * scale),
                            end = Offset(12f * scale, 12f * scale),
                            strokeWidth = stroke * scale,
                        )
                        drawLine(
                            color = iconColor,
                            start = Offset(12f * scale, 4f * scale),
                            end = Offset(4f * scale, 12f * scale),
                            strokeWidth = stroke * scale,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun rememberLinuxSystemCaptionIcon(
    iconPack: LinuxCaptionIconPack,
    iconTheme: String?,
    type: LinuxCaptionButtonType,
    maximized: Boolean,
    pixelSize: Int,
    iconLoader: LinuxSystemIconLoader,
): ImageBitmap? {
    var icon by remember(iconPack, iconTheme, type, maximized, pixelSize, iconLoader) { mutableStateOf<ImageBitmap?>(null) }
    LaunchedEffect(iconPack, iconTheme, type, maximized, pixelSize, iconLoader) {
        icon = withContext(Dispatchers.IO) {
            iconLoader.load(iconPack, iconTheme, type, maximized, pixelSize)
        }
    }
    return icon
}
