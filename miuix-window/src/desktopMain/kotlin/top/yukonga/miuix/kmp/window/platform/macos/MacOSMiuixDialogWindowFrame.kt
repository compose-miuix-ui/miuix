// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.macos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.window.DialogWindowScope
import top.yukonga.miuix.kmp.window.LocalIsHitTestInCaptionBarState
import top.yukonga.miuix.kmp.window.LocalMiuixWindowInfo
import top.yukonga.miuix.kmp.window.MiuixWindowProperties

@Composable
internal fun DialogWindowScope.MacOSMiuixDialogWindowFrame(
    properties: MiuixWindowProperties<ComposeDialog>,
    content: @Composable DialogWindowScope.() -> Unit,
) {
    val isHitTestInCaptionBar = remember { mutableStateOf(false) }
    val windowInfo = remember(properties.captionBarHeight) {
        macOSMiuixWindowInfo(properties.captionBarHeight)
    }
    val nativeCaptionBarHeight = macOSCaptionBarHeightInWindowCoordinates(
        window = window,
        captionBarHeight = windowInfo.captionBarHeight,
    )
    CompositionLocalProvider(
        LocalMiuixWindowInfo provides windowInfo,
        LocalIsHitTestInCaptionBarState provides isHitTestInCaptionBar,
    ) {
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

        LaunchedEffect(styler, window.isUndecorated, nativeCaptionBarHeight) {
            if (!window.isUndecorated) {
                styler.updateTitleBar(nativeCaptionBarHeight)
            }
        }

        if (properties.moveable) {
            MacOSCaptionBarDragHandler(
                onDrag = styler::performWindowDrag,
                onDoubleClick = null,
                window = window,
                captionBarHeightInWindowCoordinates = nativeCaptionBarHeight,
                isHitTestInCaptionBar = isHitTestInCaptionBar.value,
            )
        }

        content()
    }
}
