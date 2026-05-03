// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf

@Stable
data class AppState(
    // Theme
    val colorMode: Int = 0,
    val seedIndex: Int = 0,
    val paletteStyle: Int = 0,
    val colorSpec: Int = 0,
    // UI
    val showFPSMonitor: Boolean = false,
    val showTopAppBar: Boolean = true,
    val showNavigationBar: Boolean = true,
    val navigationBarMode: Int = 0,
    val navigationRailMode: Int = 0,
    val useFloatingNavigationBar: Boolean = false,
    val floatingNavigationBarMode: Int = 0,
    val floatingNavigationBarPosition: Int = 0,
    val showFloatingToolbar: Boolean = false,
    val floatingToolbarPosition: Int = 1,
    val floatingToolbarOrientation: Int = 1,
    val showFloatingActionButton: Boolean = false,
    val floatingActionButtonPosition: Int = 2,
    val enablePageUserScroll: Boolean = true,
    val enableScrollEndHaptic: Boolean = true,
    val enableCornerClip: Boolean = true,
    val enableDim: Boolean = true,
    val blockInputDuringTransition: Boolean = true,
    val popDirectionFollowsSwipeEdge: Boolean = false,
    // Blur
    val enableBlur: Boolean = true,
)

val LocalAppState = compositionLocalOf<AppState> {
    error("No AppState provided!")
}

val LocalUpdateAppState = staticCompositionLocalOf<((AppState) -> AppState) -> Unit> {
    error("No AppState updater provided!")
}
