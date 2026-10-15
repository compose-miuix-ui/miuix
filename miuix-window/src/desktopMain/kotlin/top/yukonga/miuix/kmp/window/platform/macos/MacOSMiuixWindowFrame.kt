// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.macos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowPlacement
import top.yukonga.miuix.kmp.window.CaptionButtonsAlign
import top.yukonga.miuix.kmp.window.LocalIsHitTestInCaptionBarState
import top.yukonga.miuix.kmp.window.LocalMiuixWindowInfo
import top.yukonga.miuix.kmp.window.LocalWindowState
import top.yukonga.miuix.kmp.window.MiuixWindowInfo
import top.yukonga.miuix.kmp.window.MiuixWindowProperties

@Composable
internal fun FrameWindowScope.MacOSMiuixWindowFrame(
    properties: MiuixWindowProperties<ComposeWindow>,
    content: @Composable FrameWindowScope.() -> Unit,
) {
    val isHitTestInCaptionBar = remember { mutableStateOf(false) }
    val windowState = LocalWindowState.current
    val isFullscreen = windowState.placement == WindowPlacement.Fullscreen
    val windowInfo = remember(properties.captionBarHeight, isFullscreen) {
        if (isFullscreen) {
            MiuixWindowInfo(
                captionBarHeight = 0.dp,
                captionButtonsAlign = CaptionButtonsAlign.Start,
                captionButtonsFullWidth = 0.dp,
            )
        } else {
            macOSMiuixWindowInfo(properties.captionBarHeight)
        }
    }
    val nativeCaptionBarHeight = macOSCaptionBarHeightInWindowCoordinates(
        window = window,
        captionBarHeight = windowInfo.captionBarHeight,
    )
    CompositionLocalProvider(
        LocalMiuixWindowInfo provides windowInfo,
        LocalIsHitTestInCaptionBarState provides isHitTestInCaptionBar,
    ) {
        val windowState = LocalWindowState.current
        val styler = remember(window) {
            MacOSMiuixWindowStyler(window)
        }

        DisposableEffect(styler) {
            onDispose(styler::dispose)
        }

        LaunchedEffect(properties.backgroundType, properties.backgroundIsDarkTheme) {
            styler.updateBackground(
                type = properties.backgroundType,
                isDarkTheme = properties.backgroundIsDarkTheme == true,
            )
        }

        LaunchedEffect(
            styler,
            window.isUndecorated,
            nativeCaptionBarHeight,
            isFullscreen,
        ) {
            if (!window.isUndecorated && !isFullscreen) {
                styler.updateTitleBar(nativeCaptionBarHeight)
            }
        }

        if (properties.moveable) {
            MacOSCaptionBarDragHandler(
                onDrag = styler::performWindowDrag,
                onDoubleClick = {
                    windowState.placement = when (windowState.placement) {
                        WindowPlacement.Maximized -> WindowPlacement.Floating
                        else -> WindowPlacement.Maximized
                    }
                },
                window = window,
                captionBarHeightInWindowCoordinates = nativeCaptionBarHeight,
                isHitTestInCaptionBar = isHitTestInCaptionBar.value,
                canDrag = windowState.placement != WindowPlacement.Fullscreen,
            )
        }

        content()
    }
}
