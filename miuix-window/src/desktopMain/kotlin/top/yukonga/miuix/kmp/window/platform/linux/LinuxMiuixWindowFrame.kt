// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowDecoration
import androidx.compose.ui.window.WindowPlacement
import top.yukonga.miuix.kmp.theme.MiuixTheme
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
import top.yukonga.miuix.kmp.window.internal.LocalMiuixWindowContentDecoration
import top.yukonga.miuix.kmp.window.internal.MiuixWindowContentDecoration
import java.awt.event.WindowEvent

@Suppress("ktlint:compose:content-slot-reused")
@OptIn(ExperimentalComposeUiApi::class, ExperimentalLayoutApi::class)
@Composable
internal fun FrameWindowScope.LinuxMiuixWindowFrame(
    resizable: Boolean,
    decoration: WindowDecoration,
    properties: MiuixWindowProperties<ComposeWindow>,
    content: @Composable FrameWindowScope.() -> Unit,
) {
    val backend = LinuxWindowBackend.current
    if (backend == LinuxWindowBackend.FallbackSystem) {
        // Non-JBR pure Wayland fallback
        System.getLogger("MiuixWindow").log(
            System.Logger.Level.WARNING,
            "Running on pure Wayland without JBR (WLToolkit). For custom window title bar support on Wayland, please use JetBrains Runtime (JBR).",
        )
        content()
        return
    }

    val currentProperties by rememberUpdatedState(properties)
    val isHitTestInCaptionBar = remember { mutableStateOf(false) }
    val captionBarHitTest = remember { LinuxCaptionBarHitTest() }
    val undecoratedWindowResizer = remember {
        UndecoratedWindowResizer(
            window = window,
            onResizeEdgeChange = { edge ->
                currentProperties.onResizeEdgeChange(window, edge)
            },
        )
    }
    val windowState = LocalWindowState.current
    val isFullscreen = windowState.placement == WindowPlacement.Fullscreen
    val clientShadow = LinuxClientShadow.shouldUseClientShadow(decoration)
    val isMaximizedOrFullScreen = windowState.placement == WindowPlacement.Maximized || isFullscreen
    val shadowMargin = if (clientShadow && !isMaximizedOrFullScreen) {
        LinuxClientShadow.margin
    } else {
        0.dp
    }
    val effectiveCaptionBarHeight = if (isFullscreen) 0.dp else properties.captionBarHeight
    val effectiveButtonsWidth = if (isFullscreen || !properties.captionButtonsVisible) 0.dp else MiuixCaptionButtonWidth * 3f

    CompositionLocalProvider(
        LocalMiuixWindowInfo provides MiuixWindowInfo(
            captionBarHeight = effectiveCaptionBarHeight,
            captionButtonsAlign = CaptionButtonsAlign.End,
            captionButtonsFullWidth = effectiveButtonsWidth,
        ),
        LocalMiuixWindowContentDecoration provides MiuixWindowContentDecoration(
            inset = shadowMargin,
            cornerRadius = if (shadowMargin > 0.dp) LinuxClientShadow.cornerRadius else 0.dp,
        ),
        LocalLinuxCaptionBarHitTest provides captionBarHitTest,
        LocalIsHitTestInCaptionBarState provides isHitTestInCaptionBar,
    ) {
        if (currentProperties.moveable) {
            LinuxCaptionBarDragHandler(
                window = window,
                hitTest = captionBarHitTest,
                isMoveable = properties.moveable,
                isHitTestInCaptionBar = isHitTestInCaptionBar.value,
                canDrag = windowState.placement != WindowPlacement.Fullscreen,
                canMaximize = resizable && properties.maximizeOrRestoreButtonEnabled,
            )
        }

        if (clientShadow && backend == LinuxWindowBackend.X11OrXWayland) {
            LinuxClientShadowEffect(
                window = window,
                margin = shadowMargin,
            )
        }

        LaunchedEffect(decoration) {
            undecoratedWindowResizer.resizerThickness =
                if (decoration == WindowDecoration.SystemDefault) {
                    4.dp
                } else {
                    window.undecoratedResizerThickness
                }
            window.undecoratedResizerThickness = 0.dp
        }

        LaunchedEffect(resizable, windowState.placement) {
            undecoratedWindowResizer.enabled = resizable && !isMaximizedOrFullScreen
        }

        val backgroundColor = MiuixTheme.colorScheme.background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(shadowMargin),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .thenIf(shadowMargin > 0.dp) {
                        linuxClientShadow()
                    }
                    .thenIf(clientShadow) {
                        clipToBounds().background(backgroundColor)
                    },
            ) {
                content()

                if (properties.captionButtonsVisible && !isFullscreen) {
                    DisposableEffect(captionBarHitTest) {
                        onDispose { captionBarHitTest.buttonsBounds = Rect.Zero }
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .onGloballyPositioned {
                                captionBarHitTest.buttonsBounds = it.boundsInWindow()
                            },
                    ) {
                        MiuixCaptionButtonMinimize(
                            onClick = {
                                windowState.isMinimized = true
                            },
                            height = properties.captionButtonHeight,
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
                            enabled = properties.maximizeOrRestoreButtonEnabled &&
                                resizable &&
                                windowState.placement != WindowPlacement.Fullscreen,
                        )
                        MiuixCaptionButtonClose(
                            onClick = {
                                window.dispatchEvent(WindowEvent(window, WindowEvent.WINDOW_CLOSING))
                            },
                            height = properties.captionButtonHeight,
                        )
                    }
                }
            }

            undecoratedWindowResizer.Content(
                modifier = Modifier.layoutId("UndecoratedWindowResizer"),
            )
        }
    }
}

private inline fun Modifier.thenIf(condition: Boolean, crossinline other: Modifier.() -> Modifier): Modifier = if (condition) other() else this
