// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.ui.appkit.appkitEventOrNull
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

/**
 * Mouse wheel behavior of macOS, mirroring `MacOsScrollConfig`: precise (trackpad) deltas are used
 * as is, stepping wheel deltas are scaled by 10 dp.
 */
internal actual fun CompositionLocalConsumerModifierNode.pagerScrollConfig(): PagerScrollConfig = MacOSPagerScrollConfig

private object MacOSPagerScrollConfig : PagerScrollConfig {
    // See https://developer.apple.com/documentation/appkit/nsevent/1535387-scrollingdeltay
    override fun Density.calculateMouseWheelScroll(event: PointerEvent, bounds: IntSize): Offset {
        val nativeEvent = event.appkitEventOrNull ?: return Offset.Zero

        // The multiplier value was derived from the desktop MacOSCocoaConfig
        val multiplier = if (nativeEvent.hasPreciseScrollingDeltas) 1.0F else 10.dp.toPx()

        return Offset(
            x = nativeEvent.scrollingDeltaX.toFloat() * multiplier,
            y = nativeEvent.scrollingDeltaY.toFloat() * multiplier,
        )
    }
}
