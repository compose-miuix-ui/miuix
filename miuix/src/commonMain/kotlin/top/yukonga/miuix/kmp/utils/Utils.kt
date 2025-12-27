// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.DialogProperties

/**
 * Window size data class.
 */
@Immutable
data class WindowSize(val width: Int, val height: Int)

/**
 * Returns the current window size.
 */
@Composable
expect fun getWindowSize(): WindowSize

/**
 * Platform enum class.
 */
enum class Platform {
    Android,
    IOS,
    Desktop,
    WasmJs,
    MacOS,
    Js,
}

/**
 * Returns the current platform name.
 */
expect fun platform(): Platform

/**
 * Returns the rounded corner of the current device.
 */
@Composable
expect fun getRoundedCorner(): Dp

/**
 * Returns platform-specific DialogProperties.
 */
@Composable
expect fun platformDialogProperties(): DialogProperties

/**
 * Removes platform default dialog effects such as window animations and dimming.
 *
 * Platform implementations should clear default window animations and dim amount to ensure
 * Miuix custom dialog animations and dim layer behave consistently across devices.
 */
@Composable
expect fun RemovePlatformDialogDefaultEffects()
