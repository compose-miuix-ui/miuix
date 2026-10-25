// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.macos

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.window.CaptionButtonsAlign
import top.yukonga.miuix.kmp.window.MiuixWindowInfo
import java.awt.Window

internal object MacOSWindowMetrics {
    const val WINDOW_BUTTON_ARTWORK_DIAMETER = 14.0
    const val WINDOW_BUTTON_VERTICAL_PADDING = 13.0
    const val DEFAULT_CAPTION_BAR_HEIGHT =
        WINDOW_BUTTON_ARTWORK_DIAMETER + WINDOW_BUTTON_VERTICAL_PADDING * 2.0
    const val WINDOW_BUTTON_FRAME_WIDTH = 14.0
    const val WINDOW_BUTTON_FRAME_HEIGHT = 16.0
    const val WINDOW_BUTTON_BOUNDS_WIDTH = 12.0
    const val WINDOW_BUTTON_BOUNDS_WIDTH_RATIO =
        WINDOW_BUTTON_BOUNDS_WIDTH / WINDOW_BUTTON_FRAME_WIDTH
    const val WINDOW_BUTTON_BOUNDS_HEIGHT_RATIO =
        WINDOW_BUTTON_ARTWORK_DIAMETER / WINDOW_BUTTON_FRAME_HEIGHT
    const val WINDOW_BUTTON_LEADING = 16.0
    const val WINDOW_BUTTON_SPACING = 8.0
    const val LEGACY_HORIZONTAL_BUTTON_OFFSET = 20.0
    const val WINDOW_BUTTON_TRAILING_PADDING = 12.0
    const val CAPTION_BUTTONS_FULL_WIDTH =
        WINDOW_BUTTON_LEADING +
            WINDOW_BUTTON_FRAME_WIDTH * 3.0 +
            WINDOW_BUTTON_SPACING * 2.0 +
            WINDOW_BUTTON_TRAILING_PADDING

    val defaultCaptionBarHeight = DEFAULT_CAPTION_BAR_HEIGHT.dp
    val captionButtonsFullWidth = CAPTION_BUTTONS_FULL_WIDTH.dp
}

@Composable
internal fun macOSCaptionBarHeightInWindowCoordinates(
    window: Window,
    captionBarHeight: Dp,
): Float = captionBarHeightInWindowCoordinates(
    captionBarHeight = captionBarHeight,
    density = LocalDensity.current.density,
    displayScale = window.graphicsConfiguration?.defaultTransform?.scaleY ?: 1.0,
)

internal fun captionBarHeightInWindowCoordinates(
    captionBarHeight: Dp,
    density: Float,
    displayScale: Double,
): Float {
    val safeDisplayScale = displayScale.takeIf { it.isFinite() && it > 0.0 } ?: 1.0
    return captionBarHeight.value * density / safeDisplayScale.toFloat()
}

internal fun macOSMiuixWindowInfo(captionBarHeight: Dp) = MiuixWindowInfo(
    captionBarHeight = captionBarHeight,
    captionButtonsAlign = CaptionButtonsAlign.Start,
    captionButtonsFullWidth = MacOSWindowMetrics.captionButtonsFullWidth,
)
