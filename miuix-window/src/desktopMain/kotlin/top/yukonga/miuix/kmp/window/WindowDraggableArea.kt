// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.window.internal.DesktopOS
import top.yukonga.miuix.kmp.window.platform.linux.linuxCaptionBarHitTest
import top.yukonga.miuix.kmp.window.platform.windows.windowsCaptionBarTouchInput

/**
 * A spacer placed between title bar background content and clickable components.
 * It coordinates hit-testing for window drag operations without blocking underlying or overlay clickable elements.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WindowDraggableArea(
    modifier: Modifier = Modifier,
) {
    val windowInfo = LocalMiuixWindowInfo.current
    if (windowInfo.captionBarHeight <= 0.dp) return
    val isHitTestInCaptionBarState = LocalIsHitTestInCaptionBarState.current
    val properties = LocalMiuixWindowProperties.current

    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(windowInfo.captionBarHeight)
            .run {
                when {
                    DesktopOS.isWindows() -> windowsCaptionBarTouchInput(properties.moveable)
                    DesktopOS.isLinux() -> linuxCaptionBarHitTest()
                    else -> this
                }
            }
            .onPointerEvent(PointerEventType.Enter) {
                isHitTestInCaptionBarState.value = true
            }
            .onPointerEvent(PointerEventType.Exit) {
                isHitTestInCaptionBarState.value = false
            },
    )
}
