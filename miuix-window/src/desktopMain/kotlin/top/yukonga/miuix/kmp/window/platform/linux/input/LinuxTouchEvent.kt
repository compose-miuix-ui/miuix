// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.input

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.unit.IntOffset

/**
 * A native contact sample. Screen coordinates are AWT units; content coordinates are pixels.
 * Time uses the native input session's monotonic clock.
 * Gesture ownership belongs to LinuxWindowGestures, never to the event being dispatched.
 */
internal data class LinuxTouchEvent(
    val id: Long,
    val type: PointerEventType,
    val screenPosition: IntOffset,
    val contentPosition: Offset,
    val timeMillis: Long,
)
