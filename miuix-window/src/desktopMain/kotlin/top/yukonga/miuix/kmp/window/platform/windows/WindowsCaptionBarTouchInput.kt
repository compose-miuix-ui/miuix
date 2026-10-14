// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.pointerInput

internal class WindowsTouchEvent {
    @Volatile
    var isCaptionDragRequested = false
}

internal fun Modifier.windowsCaptionBarTouchInput(enabled: Boolean): Modifier = pointerInput(enabled) {
    if (!enabled) return@pointerInput
    awaitEachGesture {
        val down = awaitFirstDown()
        if (down.type != PointerType.Touch) return@awaitEachGesture
        val native = currentEvent.nativeEvent as? WindowsTouchEvent ?: return@awaitEachGesture
        down.consume()
        try {
            native.isCaptionDragRequested = true
            waitForUpOrCancellation()
        } finally {
            native.isCaptionDragRequested = false
        }
    }
}
