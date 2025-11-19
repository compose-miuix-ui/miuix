// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    hideLoading()
    ComposeViewport(viewportContainerId = "composeApp") {

        LocalLayoutDirection.current
        var insetTopPx by remember { mutableStateOf(0.0) }
        var safeBottomPx by remember { mutableStateOf(0.0) }
        var insetStartPx by remember { mutableStateOf(0.0) }
        var insetEndPx by remember { mutableStateOf(0.0) }

        LaunchedEffect(Unit) {
            insetTopPx = getCssVar("--safe-area-inset-top")
            insetStartPx = getCssVar("--safe-area-inset-left")
            insetEndPx = getCssVar("--safe-area-inset-right")
            safeBottomPx = getCssVar("--safe-area-inset-bottom")
        }

        App(
            padding = PaddingValues(
                top = Dp(insetTopPx.toFloat()),
                bottom = Dp(safeBottomPx.toFloat()),
                start = Dp(insetStartPx.toFloat()),
                end = Dp(insetEndPx.toFloat())
            ),
            enableOverScroll = isTouchEnabled()
        )
    }
}

private fun hideLoading() = platformHideLoading()

private fun getCssVar(name: String): Double = platformGetCssVar(name)

private fun isTouchEnabled(): Boolean = platformIsTouchEnabled()
