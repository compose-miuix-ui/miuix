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
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.window.DialogWindowScope
import top.yukonga.miuix.kmp.window.CaptionButtonsAlign
import top.yukonga.miuix.kmp.window.LocalIsHitTestInCaptionBarState
import top.yukonga.miuix.kmp.window.LocalMiuixWindowInfo
import top.yukonga.miuix.kmp.window.MiuixWindowInfo
import top.yukonga.miuix.kmp.window.MiuixWindowProperties
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonClose
import top.yukonga.miuix.kmp.window.basic.MiuixCaptionButtonWidth
import top.yukonga.miuix.kmp.window.basic.rememberWindowsCaptionBarFontIconFamily
import java.awt.event.WindowEvent
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun DialogWindowScope.WindowsMiuixDialogWindowFrame(
    resizable: Boolean,
    properties: MiuixWindowProperties<ComposeDialog>,
    content: @Composable DialogWindowScope.() -> Unit,
) {
    val currentProperties by rememberUpdatedState(properties)
    val isHitTestInCaptionBar = remember { mutableStateOf(false) }
    val iconFontFamily by rememberWindowsCaptionBarFontIconFamily()
    CompositionLocalProvider(
        LocalMiuixWindowInfo provides MiuixWindowInfo(
            captionBarHeight = properties.captionBarHeight,
            captionButtonsAlign = CaptionButtonsAlign.End,
            captionButtonsFullWidth = MiuixCaptionButtonWidth,
        ),
        LocalIsHitTestInCaptionBarState provides isHitTestInCaptionBar,
    ) {
        val windowClientInsets = remember { MutableWindowInsets() }

        var captionBarRect by remember { mutableStateOf(Rect.Zero) }
        var closeButtonRect by remember { mutableStateOf(Rect.Zero) }

        val styler = remember(window) {
            WindowsMiuixWindowStyler(
                window = window,
                hitTest = { x, y ->
                    when {
                        currentProperties.captionButtonsVisible &&
                            closeButtonRect.contains(x, y) -> HitTestResult.HTCLOSE

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
            ).apply {
                updateBorderAndShadow(true)
                updateIsToolWindow(true)
            }
        }

        LaunchedEffect(resizable) {
            styler.updateIsResizable(resizable)
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
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(properties.captionBarHeight)
                    .onGloballyPositioned {
                        captionBarRect = it.boundsInWindow()
                    },
            )

            content()

            if (properties.captionButtonsVisible) {
                Row(
                    modifier = Modifier.align(Alignment.TopEnd),
                ) {
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
