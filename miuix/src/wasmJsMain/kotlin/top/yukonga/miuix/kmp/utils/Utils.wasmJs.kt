// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.browser.window
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent

@Composable
@OptIn(ExperimentalComposeUiApi::class)
actual fun getWindowSize(): WindowSize {
    val window = LocalWindowInfo.current
    val windowSize by remember(window) {
        derivedStateOf {
            WindowSize(
                width = window.containerSize.width,
                height = window.containerSize.height
            )
        }
    }
    return windowSize
}

actual fun platform(): Platform = Platform.WasmJs

@Composable
actual fun getRoundedCorner(): Dp = 0.dp

@Composable
actual fun BackHandler(
    enabled: Boolean,
    onBack: () -> Unit
) {
    val currentOnBack by rememberUpdatedState(onBack)
    DisposableEffect(enabled) {
        if (!enabled) return@DisposableEffect onDispose { }
        val handler: (Event) -> Unit = { event ->
            if ((event as KeyboardEvent).key == "Escape") {
                currentOnBack()
            }
        }
        window.addEventListener("keydown", handler)
        onDispose {
            window.removeEventListener("keydown", handler)
        }
    }
}

@Composable
actual fun PredictiveBackHandler(
    enabled: Boolean,
    onBackStarted: ((BackEventCompat) -> Unit)?,
    onBackProgressed: ((BackEventCompat) -> Unit)?,
    onBackCancelled: (() -> Unit)?,
    onBack: () -> Unit
) {
    // WasmJs doesn't support predictive back gesture, fallback to simple BackHandler
    BackHandler(enabled = enabled, onBack = onBack)
}