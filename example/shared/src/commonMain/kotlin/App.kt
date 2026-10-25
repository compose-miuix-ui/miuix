// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.drop
import top.yukonga.miuix.kmp.squircle.LocalSquircleEnabled
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.TextStyles
import ui.AppTheme
import ui.keyColorFor

@Composable
fun App(
    padding: PaddingValues = PaddingValues(0.dp),
    onColorModeChange: ((Int) -> Unit)? = null,
    textStyles: TextStyles = MiuixTheme.textStyles,
) {
    var appState by remember { mutableStateOf(AppState()) }
    val updateAppState: ((AppState) -> AppState) -> Unit = remember {
        { transform -> appState = transform(appState) }
    }

    val currentOnColorModeChange by rememberUpdatedState(onColorModeChange)
    LaunchedEffect(Unit) {
        snapshotFlow { appState.colorMode }
            .drop(1)
            .collect { currentOnColorModeChange?.invoke(it) }
    }

    AppTheme(
        colorMode = appState.colorMode,
        keyColor = keyColorFor(appState.seedIndex),
        paletteStyle = appState.paletteStyle,
        colorSpec = appState.colorSpec,
        textStyles = textStyles,
    ) {
        App(
            appState = appState,
            onUpdateAppState = updateAppState,
            padding = padding,
        )
    }
}

/** Renders the app using the caller's theme and state. */
@Composable
fun App(
    appState: AppState,
    onUpdateAppState: ((AppState) -> AppState) -> Unit,
    padding: PaddingValues = PaddingValues(0.dp),
) {
    CompositionLocalProvider(
        LocalAppState provides appState,
        LocalUpdateAppState provides onUpdateAppState,
        LocalSquircleEnabled provides appState.enableSquircle,
    ) {
        AppContent(padding = padding)
    }
}
