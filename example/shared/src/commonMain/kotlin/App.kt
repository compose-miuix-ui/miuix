// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.utils.Platform
import top.yukonga.miuix.kmp.utils.platform
import ui.AppTheme
import ui.keyColorFor

@Composable
fun App(
    padding: PaddingValues = PaddingValues(0.dp),
    enableOverScroll: Boolean = platform() == Platform.Android || platform() == Platform.IOS,
    onColorModeChange: ((Int) -> Unit)? = null,
) {
    var appState by remember { mutableStateOf(AppState(enableOverScroll = enableOverScroll)) }
    val updateAppState: ((AppState) -> AppState) -> Unit = remember {
        { transform -> appState = transform(appState) }
    }

    val currentOnColorModeChange by rememberUpdatedState(onColorModeChange)
    var previousColorMode by remember { mutableIntStateOf(appState.colorMode) }
    LaunchedEffect(appState.colorMode) {
        if (appState.colorMode != previousColorMode) {
            previousColorMode = appState.colorMode
            currentOnColorModeChange?.invoke(appState.colorMode)
        }
    }

    LaunchedEffect(enableOverScroll) {
        updateAppState { it.copy(enableOverScroll = enableOverScroll) }
    }

    val keyColor = keyColorFor(appState.seedIndex)

    AppTheme(
        colorMode = appState.colorMode,
        keyColor = keyColor,
        paletteStyle = appState.paletteStyle,
        colorSpec = appState.colorSpec,
    ) {
        CompositionLocalProvider(
            LocalAppState provides appState,
            LocalUpdateAppState provides updateAppState,
        ) {
            AppContent(padding = padding)
        }
    }
}
