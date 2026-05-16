package com.nfctool.app.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.nfctool.app.ui.screen.HistoryScreen
import com.nfctool.app.ui.screen.ScanScreen
import com.nfctool.app.ui.screen.SettingsScreen
import com.nfctool.app.ui.screen.TagDetailScreen
import com.nfctool.app.ui.screen.WriteScreen
import com.nfctool.app.ui.state.AppState
import com.nfctool.app.ui.state.LocalNfcManager
import com.nfctool.app.ui.theme.NfcToolTheme
import com.nfctool.core.model.NfcTag

enum class Screen {
    SCAN,
    TAG_DETAIL,
    WRITE,
    HISTORY,
    SETTINGS,
}

@Composable
fun NfcToolApp() {
    val snackbarHostState = remember { SnackbarHostState() }
    var currentScreen by remember { mutableStateOf(Screen.SCAN) }
    var selectedTag by remember { mutableStateOf<NfcTag?>(null) }
    var appState by remember { mutableStateOf(AppState()) }

    NfcToolTheme {
        CompositionLocalProvider(
            LocalNfcManager provides com.nfctool.core.NfcManager(),
        ) {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
            ) { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                ) {
                    AnimatedContent(targetState = currentScreen) { screen ->
                        when (screen) {
                            Screen.SCAN -> ScanScreen(
                                appState = appState,
                                onAppStateChange = { appState = it },
                                onTagScanned = { tag ->
                                    selectedTag = tag
                                    currentScreen = Screen.TAG_DETAIL
                                },
                                onNavigateToHistory = { currentScreen = Screen.HISTORY },
                                onNavigateToSettings = { currentScreen = Screen.SETTINGS },
                                onNavigateToWrite = {
                                    currentScreen = Screen.WRITE
                                },
                            )

                            Screen.TAG_DETAIL -> TagDetailScreen(
                                tag = selectedTag,
                                onBack = {
                                    currentScreen = Screen.SCAN
                                    selectedTag = null
                                },
                                onNavigateToWrite = { tag ->
                                    selectedTag = tag
                                    currentScreen = Screen.WRITE
                                },
                            )

                            Screen.WRITE -> WriteScreen(
                                tag = selectedTag,
                                onBack = { currentScreen = Screen.SCAN },
                                onWriteComplete = { message ->
                                    appState = appState.copy(snackbarMessage = message)
                                    currentScreen = Screen.SCAN
                                },
                            )

                            Screen.HISTORY -> HistoryScreen(
                                appState = appState,
                                onBack = { currentScreen = Screen.SCAN },
                                onSelectRecord = { record ->
                                    selectedTag = record.tag
                                    currentScreen = Screen.TAG_DETAIL
                                },
                            )

                            Screen.SETTINGS -> SettingsScreen(
                                onBack = { currentScreen = Screen.SCAN },
                            )
                        }
                    }
                }
            }
        }
    }
}