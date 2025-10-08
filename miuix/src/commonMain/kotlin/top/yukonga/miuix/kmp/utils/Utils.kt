// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

/**
 * Window size data class.
 */
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
    Js
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
 * Handles the back event.
 */
@Composable
expect fun BackHandler(
    enabled: Boolean,
    onBack: () -> Unit
)

/**
 * Data class representing the progress of a predictive back gesture.
 *
 * @param progress The progress of the back gesture, ranging from 0.0 (just started) to 1.0 (completed).
 * @param swipeEdge The edge from which the swipe originated.
 * @param touchX The horizontal position of the touch event.
 * @param touchY The vertical position of the touch event.
 */
data class BackEventCompat(
    val progress: Float,
    val swipeEdge: Int = 0,
    val touchX: Float = 0f,
    val touchY: Float = 0f
)

/**
 * Handles the predictive back gesture with callbacks for different stages.
 * On non-Android platforms, this falls back to simple BackHandler behavior.
 *
 * @param enabled Whether the back handler is enabled.
 * @param onBackStarted Called when the back gesture starts. Receives initial BackEventCompat.
 * @param onBackProgressed Called when the back gesture progresses. Receives updated BackEventCompat.
 * @param onBackCancelled Called when the back gesture is cancelled (user didn't complete the swipe).
 * @param onBack Called when the back gesture is completed or when back button is pressed.
 */
@Composable
expect fun PredictiveBackHandler(
    enabled: Boolean = true,
    onBackStarted: ((BackEventCompat) -> Unit)? = null,
    onBackProgressed: ((BackEventCompat) -> Unit)? = null,
    onBackCancelled: (() -> Unit)? = null,
    onBack: () -> Unit
)