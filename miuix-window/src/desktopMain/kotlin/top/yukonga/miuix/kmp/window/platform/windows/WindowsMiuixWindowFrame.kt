// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.MutableWindowInsets
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowPlacement
import top.yukonga.miuix.kmp.window.CaptionButtonsAlign
import top.yukonga.miuix.kmp.window.LocalIsHitTestInCaptionBarState
import top.yukonga.miuix.kmp.window.LocalMiuixWindowInfo
import top.yukonga.miuix.kmp.window.LocalWindowState
import top.yukonga.miuix.kmp.window.MiuixWindowInfo
import top.yukonga.miuix.kmp.window.MiuixWindowProperties
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonClose
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonMaximize
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonMinimize
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonWidth
import top.yukonga.miuix.kmp.window.basic.rememberWindowsCaptionBarFontIconFamily
import java.awt.event.WindowEvent

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun FrameWindowScope.WindowsMiuixWindowFrame(
    resizable: Boolean,
    properties: MiuixWindowProperties<ComposeWindow>,
    content: @Composable FrameWindowScope.() -> Unit,
) {
    val windowState = LocalWindowState.current

    val currentResizable by rememberUpdatedState(resizable)
    val currentProperties by rememberUpdatedState(properties)
    val currentIsFullscreen by rememberUpdatedState(windowState.placement == WindowPlacement.Fullscreen)

    val isHitTestInCaptionBar = remember { mutableStateOf(false) }
    val effectiveCaptionBarHeight = if (currentIsFullscreen) 0.dp else properties.captionBarHeight
    val effectiveButtonsWidth = if (currentIsFullscreen || !properties.captionButtonsVisible) 0.dp else MiuixCaptionButtonWidth * 3f
    val iconFontFamily by rememberWindowsCaptionBarFontIconFamily()
    CompositionLocalProvider(
        LocalMiuixWindowInfo provides MiuixWindowInfo(
            captionBarHeight = effectiveCaptionBarHeight,
            captionButtonsAlign = CaptionButtonsAlign.End,
            captionButtonsFullWidth = effectiveButtonsWidth,
        ),
        LocalIsHitTestInCaptionBarState provides isHitTestInCaptionBar,
    ) {
        val windowClientInsets = remember { MutableWindowInsets() }

        var captionBarRect by remember { mutableStateOf(Rect.Zero) }
        var minimizeButtonRect by remember { mutableStateOf(Rect.Zero) }
        var maximizeButtonRect by remember { mutableStateOf(Rect.Zero) }
        var closeButtonRect by remember { mutableStateOf(Rect.Zero) }

        val styler = remember(window) {
            WindowsMiuixWindowStyler(
                window = window,
                hitTest = { x, y ->
                    when {
                        !currentIsFullscreen &&
                            currentProperties.captionButtonsVisible &&
                            minimizeButtonRect.contains(x, y) -> HitTestResult.HTMINBUTTON

                        !currentIsFullscreen &&
                            currentProperties.captionButtonsVisible &&
                            currentResizable &&
                            maximizeButtonRect.contains(x, y) -> HitTestResult.HTMAXBUTTON

                        !currentIsFullscreen &&
                            currentProperties.captionButtonsVisible &&
                            closeButtonRect.contains(x, y) -> HitTestResult.HTCLOSE

                        !currentIsFullscreen &&
                            currentProperties.moveable &&
                            captionBarRect.contains(x, y) &&
                            isHitTestInCaptionBar.value -> HitTestResult.HTCAPTION

                        else -> HitTestResult.HTCLIENT
                    }
                },
                onWindowInsetUpdate = { windowInsets ->
                    windowClientInsets.insets = windowInsets
                },
                onResizeEdgeChange = { edge ->
                    currentProperties.onResizeEdgeChange(window, edge)
                },
            )
        }

        LaunchedEffect(resizable) {
            styler.updateIsResizable(resizable)
        }

        LaunchedEffect(currentIsFullscreen) {
            styler.updateBorderAndShadow(!currentIsFullscreen)
        }

        LaunchedEffect(properties.backgroundType, properties.backgroundIsDarkTheme) {
            styler.updateBackground(
                type = properties.backgroundType,
                isDarkTheme = properties.backgroundIsDarkTheme == true,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(windowClientInsets),
        ) {
            if (!currentIsFullscreen && effectiveCaptionBarHeight > 0.dp) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(effectiveCaptionBarHeight)
                        .onGloballyPositioned {
                            captionBarRect = it.boundsInWindow()
                        },
                )
            } else {
                captionBarRect = Rect.Zero
            }

            content()

            if (properties.captionButtonsVisible && !currentIsFullscreen) {
                Row(
                    modifier = Modifier.align(Alignment.TopEnd),
                ) {
                    MiuixCaptionButtonMinimize(
                        onClick = {
                            windowState.isMinimized = true
                        },
                        height = properties.captionButtonHeight,
                        iconFontFamily = iconFontFamily,
                        modifier = Modifier.onGloballyPositioned {
                            minimizeButtonRect = it.boundsInWindow()
                        },
                        enabled = properties.minimizeButtonEnabled,
                    )
                    val isMaximized = windowState.placement == WindowPlacement.Maximized
                    MiuixCaptionButtonMaximize(
                        onClick = {
                            if (isMaximized) {
                                windowState.placement = WindowPlacement.Floating
                            } else {
                                windowState.placement = WindowPlacement.Maximized
                            }
                        },
                        maximized = isMaximized,
                        height = properties.captionButtonHeight,
                        iconFontFamily = iconFontFamily,
                        modifier = Modifier.onGloballyPositioned {
                            maximizeButtonRect = it.boundsInWindow()
                        },
                        enabled = properties.maximizeOrRestoreButtonEnabled &&
                            resizable &&
                            windowState.placement != WindowPlacement.Fullscreen,
                    )
                    MiuixCaptionButtonClose(
                        onClick = {
                            window.dispatchEvent(WindowEvent(window, WindowEvent.WINDOW_CLOSING))
                        },
                        height = properties.captionButtonHeight,
                        iconFontFamily = iconFontFamily,
                        modifier = Modifier.onGloballyPositioned {
                            closeButtonRect = it.boundsInWindow()
                        },
                    )
                }
            }
        }
    }
}

private fun Rect.contains(x: Float, y: Float): Boolean = x in left..right && y in top..bottom
