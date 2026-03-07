// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.DialogProperties

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

/**
 * Whether the platform has a bug where clearing focus causes automatic re-assignment
 * to the first focusable view. This occurs on Android API levels 26-27.
 *
 * See: https://issuetracker.google.com/issues/433382598
 */
expect val hasFocusReassignBug: Boolean
