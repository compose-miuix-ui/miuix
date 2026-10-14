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
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonClose
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonWidth
import top.yukonga.miuix.kmp.window.internal.LocalMiuixWindowContentDecoration
import top.yukonga.miuix.kmp.window.internal.MiuixWindowContentDecoration
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
    val clientShadow = LinuxClientShadow.shouldUseClientShadow(decoration)
    val shadowMargin = if (clientShadow) LinuxClientShadow.margin else 0.dp

    CompositionLocalProvider(
        LocalMiuixWindowInfo provides MiuixWindowInfo(
            captionBarHeight = properties.captionBarHeight,
            captionButtonsAlign = CaptionButtonsAlign.End,
            captionButtonsFullWidth = MiuixCaptionButtonWidth,
        ),
        LocalMiuixWindowContentDecoration provides MiuixWindowContentDecoration(
            inset = shadowMargin,
            cornerRadius = if (clientShadow) LinuxClientShadow.cornerRadius else 0.dp,
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

        if (clientShadow && backend == LinuxWindowBackend.X11OrXWayland) {
            LinuxClientShadowEffect(
                window = window,
                margin = shadowMargin,
            )
        }

        LaunchedEffect(resizable) {
            undecoratedWindowResizer.enabled = resizable
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
                            .align(Alignment.TopEnd)
                            .onGloballyPositioned {
                                captionBarHitTest.buttonsBounds = it.boundsInWindow()
                            },
                    ) {
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
