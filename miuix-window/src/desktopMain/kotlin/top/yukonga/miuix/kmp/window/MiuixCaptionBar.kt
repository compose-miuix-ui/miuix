// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.theme.LocalContentColor

/**
 * A caption bar container that automatically reserves space for native/custom window
 * caption buttons (minimize, maximize, close).
 *
 * Scoped to [BoxScope], allowing custom title bar items to use alignment modifiers.
 */
@Composable
fun MiuixCaptionBar(
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    contentColor: Color = Color.Unspecified,
    content: @Composable BoxScope.() -> Unit,
) {
    val windowInfo = LocalMiuixWindowInfo.current
    if (windowInfo.captionBarHeight <= 0.dp) return
    val properties = LocalMiuixWindowProperties.current

    val resolvedContentColor = when {
        contentColor != Color.Unspecified -> contentColor
        properties.captionButtonIsDarkTheme == true -> Color.White
        else -> Color(0xFF1F1F1F)
    }

    val padding = when (windowInfo.captionButtonsAlign) {
        CaptionButtonsAlign.Start -> PaddingValues(start = windowInfo.captionButtonsFullWidth)
        CaptionButtonsAlign.End -> PaddingValues(end = windowInfo.captionButtonsFullWidth)
    }
    CompositionLocalProvider(
        LocalContentColor provides resolvedContentColor,
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(windowInfo.captionBarHeight)
                .background(color)
                .padding(padding),
            content = content,
        )
    }
}
