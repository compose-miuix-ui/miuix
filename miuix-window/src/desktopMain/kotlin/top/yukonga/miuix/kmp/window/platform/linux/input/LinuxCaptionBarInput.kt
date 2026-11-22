// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import top.yukonga.miuix.kmp.window.platform.linux.window.LinuxCaptionBarHitTest
import top.yukonga.miuix.kmp.window.platform.linux.window.LocalLinuxCaptionBarHitTest

/**
 * Registers caption geometry and reports Compose touch hits to the window gesture owner.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun Modifier.linuxCaptionBarInput(): Modifier {
    val hitTest = LocalLinuxCaptionBarHitTest.current ?: return this
    val region = remember(hitTest) { LinuxCaptionBarHitTest.Region() }
    DisposableEffect(hitTest, region) {
        hitTest.regions.add(region)
        onDispose { hitTest.regions.remove(region) }
    }

    val modifier = onGloballyPositioned { region.bounds = it.boundsInWindow() }
    val gestures = LocalLinuxWindowGestures.current ?: return modifier
    // Compose hit testing decides whether the caption or a control above it receives the press.
    return modifier.onPointerEvent(PointerEventType.Press) { event ->
        val native = event.nativeEvent as? LinuxTouchEvent ?: return@onPointerEvent
        if (gestures.onCaptionPress(native)) {
            event.changes.forEach { it.consume() }
        }
    }
}
