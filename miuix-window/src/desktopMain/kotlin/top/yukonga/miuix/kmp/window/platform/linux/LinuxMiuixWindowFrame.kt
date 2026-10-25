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
import top.yukonga.miuix.kmp.window.internal.LocalMiuixWindowContentDecoration
import top.yukonga.miuix.kmp.window.internal.MiuixWindowContentDecoration
import top.yukonga.miuix.kmp.window.platform.linux.input.LinuxWindowGestures
import top.yukonga.miuix.kmp.window.platform.linux.input.LinuxWindowInputEffect
import top.yukonga.miuix.kmp.window.platform.linux.input.LocalLinuxWindowGestures
import top.yukonga.miuix.kmp.window.platform.linux.window.LinuxCaptionBarHitTest
import top.yukonga.miuix.kmp.window.platform.linux.window.LinuxWindowGestureEffects
import top.yukonga.miuix.kmp.window.platform.linux.window.LocalLinuxCaptionBarHitTest
import top.yukonga.miuix.kmp.window.platform.linux.window.UndecoratedWindowResizer
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
    val systemDecoration = rememberLinuxSystemDecoration()
    val isHitTestInCaptionBar = remember { mutableStateOf(false) }
    val captionBarHitTest = remember { LinuxCaptionBarHitTest() }
    val undecoratedWindowResizer = remember {
        UndecoratedWindowResizer(
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
    val effectiveButtonsWidth = if (isFullscreen || !properties.captionButtonsVisible) {
        0.dp
    } else {
        systemDecoration.buttonsWidth
    }

    val windowGestures = remember(window) {
        LinuxWindowGestures(
            window = window,
            caption = captionBarHitTest,
            resizer = undecoratedWindowResizer,
            captionHovered = isHitTestInCaptionBar,
            effects = LinuxWindowGestureEffects(window, undecoratedWindowResizer),
        )
    }

    CompositionLocalProvider(
        LocalMiuixWindowInfo provides MiuixWindowInfo(
            captionBarHeight = effectiveCaptionBarHeight,
            captionButtonsAlign = systemDecoration.alignment,
            captionButtonsFullWidth = effectiveButtonsWidth,
        ),
        LocalMiuixWindowContentDecoration provides MiuixWindowContentDecoration(
            inset = shadowMargin,
            cornerRadius = if (shadowMargin > 0.dp) LinuxClientShadow.cornerRadius else 0.dp,
        ),
        LocalLinuxCaptionBarHitTest provides captionBarHitTest,
        LocalLinuxWindowGestures provides windowGestures,
        LocalIsHitTestInCaptionBarState provides isHitTestInCaptionBar,
    ) {
        LinuxWindowInputEffect(
            window = window,
            gestures = windowGestures,
            moveable = properties.moveable,
            resizable = resizable && !isMaximizedOrFullScreen,
            canDrag = windowState.placement != WindowPlacement.Fullscreen,
            canMaximize = resizable && properties.maximizeOrRestoreButtonEnabled,
        )

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

        val backgroundColor = MiuixTheme.colorScheme.surface
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
                            .align(
                                if (systemDecoration.alignment == CaptionButtonsAlign.Start) {
                                    Alignment.TopStart
                                } else {
                                    Alignment.TopEnd
                                },
                            )
                            .onGloballyPositioned {
                                captionBarHitTest.buttonsBounds = it.boundsInWindow()
                            },
                    ) {
                        val isMaximized = windowState.placement == WindowPlacement.Maximized
                        systemDecoration.buttons.forEach { button ->
                            LinuxSystemCaptionButton(
                                type = button,
                                iconPack = systemDecoration.iconPack,
                                iconTheme = systemDecoration.iconTheme,
                                onClick = {
                                    when (button) {
                                        LinuxCaptionButtonType.Minimize -> windowState.isMinimized = true

                                        LinuxCaptionButtonType.Maximize -> {
                                            windowState.placement = if (isMaximized) {
                                                WindowPlacement.Floating
                                            } else {
                                                WindowPlacement.Maximized
                                            }
                                        }

                                        LinuxCaptionButtonType.Close ->
                                            window.dispatchEvent(WindowEvent(window, WindowEvent.WINDOW_CLOSING))
                                    }
                                },
                                maximized = isMaximized,
                                height = properties.captionButtonHeight,
                                enabled = when (button) {
                                    LinuxCaptionButtonType.Minimize -> properties.minimizeButtonEnabled

                                    LinuxCaptionButtonType.Maximize ->
                                        properties.maximizeOrRestoreButtonEnabled &&
                                            resizable &&
                                            windowState.placement != WindowPlacement.Fullscreen

                                    LinuxCaptionButtonType.Close -> true
                                },
                            )
                        }
                    }
                }
            }

            undecoratedWindowResizer.Content(
                modifier = Modifier
                    .fillMaxSize()
                    .layoutId("UndecoratedWindowResizer"),
            )
        }
    }
}

private inline fun Modifier.thenIf(condition: Boolean, crossinline other: Modifier.() -> Modifier): Modifier = if (condition) other() else this
