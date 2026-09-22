// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFold

/**
 * Mouse wheel behavior of iOS, mirroring `UiKitScrollConfig`: scroll and pan deltas scroll by 64 dp.
 */
internal actual fun CompositionLocalConsumerModifierNode.pagerScrollConfig(): PagerScrollConfig = IosPagerScrollConfig

private object IosPagerScrollConfig : PagerScrollConfig {
    override fun Density.calculateMouseWheelScroll(event: PointerEvent, bounds: IntSize): Offset = event.changes.fastFold(Offset.Zero) { acc, change ->
        if (event.type == PointerEventType.PanMove) {
            acc + change.panOffset
        } else {
            acc + change.scrollDelta
        }
    } * -64.dp.toPx()
}
