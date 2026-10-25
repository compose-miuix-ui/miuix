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
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindowScope
import androidx.compose.ui.window.WindowDecoration
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.window.CaptionButtonsAlign
import top.yukonga.miuix.kmp.window.LocalIsHitTestInCaptionBarState
import top.yukonga.miuix.kmp.window.LocalMiuixWindowInfo
import top.yukonga.miuix.kmp.window.MiuixWindowInfo
import top.yukonga.miuix.kmp.window.MiuixWindowProperties
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonWidth
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
@OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class)
@Composable
internal fun DialogWindowScope.LinuxMiuixDialogWindowFrame(
    resizable: Boolean,
    decoration: WindowDecoration,
    properties: MiuixWindowProperties<ComposeDialog>,
    content: @Composable DialogWindowScope.() -> Unit,
) {
    val backend = LinuxWindowBackend.current
    if (backend == LinuxWindowBackend.FallbackSystem) {
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
    val clientShadow = LinuxClientShadow.shouldUseClientShadow(decoration)
    val shadowMargin = if (clientShadow) LinuxClientShadow.margin else 0.dp

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
            captionBarHeight = properties.captionBarHeight,
            captionButtonsAlign = systemDecoration.alignment,
            captionButtonsFullWidth = if (properties.captionButtonsVisible) MiuixCaptionButtonWidth else 0.dp,
        ),
        LocalMiuixWindowContentDecoration provides MiuixWindowContentDecoration(
            inset = shadowMargin,
            cornerRadius = if (clientShadow) LinuxClientShadow.cornerRadius else 0.dp,
        ),
        LocalLinuxCaptionBarHitTest provides captionBarHitTest,
        LocalLinuxWindowGestures provides windowGestures,
        LocalIsHitTestInCaptionBarState provides isHitTestInCaptionBar,
    ) {
        LinuxWindowInputEffect(
            window = window,
            gestures = windowGestures,
            moveable = properties.moveable,
            resizable = resizable,
        )

        LaunchedEffect(decoration) {
            undecoratedWindowResizer.resizerThickness =
                if (decoration == WindowDecoration.SystemDefault) {
                    4.dp
                } else {
                    window.undecoratedResizerThickness
                }
            window.undecoratedResizerThickness = 0.dp
        }

        if (clientShadow && backend == LinuxWindowBackend.X11OrXWayland) {
            LinuxClientShadowEffect(
                window = window,
                margin = shadowMargin,
            )
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
                    .thenIf(clientShadow) {
                        linuxClientShadow()
                            .clipToBounds()
                            .background(backgroundColor)
                    },
            ) {
                content()

                if (properties.captionButtonsVisible) {
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
                        systemDecoration.buttons.forEach { button ->
                            if (button == LinuxCaptionButtonType.Close) {
                                LinuxSystemCaptionButton(
                                    type = button,
                                    iconPack = systemDecoration.iconPack,
                                    iconTheme = systemDecoration.iconTheme,
                                    onClick = {
                                        window.dispatchEvent(WindowEvent(window, WindowEvent.WINDOW_CLOSING))
                                    },
                                    maximized = false,
                                    height = properties.captionButtonHeight,
                                    enabled = true,
                                )
                            }
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
