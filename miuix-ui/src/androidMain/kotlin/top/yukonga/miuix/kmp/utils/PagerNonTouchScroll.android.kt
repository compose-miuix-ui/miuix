// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import android.os.Build
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.requireView
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFold

/**
 * Mouse wheel behavior of Android, mirroring `AndroidScrollConfig`: wheel deltas are scaled by the
 * view's scroll factors.
 */
internal actual fun CompositionLocalConsumerModifierNode.pagerScrollConfig(): PagerScrollConfig = AndroidPagerScrollConfig(android.view.ViewConfiguration.get(requireView().context))

private class AndroidPagerScrollConfig(
    private val viewConfiguration: android.view.ViewConfiguration,
) : PagerScrollConfig {
    // 64 dp value is taken from ViewConfiguration.java, replace with better solution
    private fun Density.getVerticalScrollFactor() = if (Build.VERSION.SDK_INT > 26) {
        viewConfiguration.scaledVerticalScrollFactor
    } else {
        64.dp.toPx()
    }

    private fun Density.getHorizontalScrollFactor() = if (Build.VERSION.SDK_INT > 26) {
        viewConfiguration.scaledHorizontalScrollFactor
    } else {
        64.dp.toPx()
    }

    override fun Density.calculateMouseWheelScroll(event: PointerEvent, bounds: IntSize): Offset {
        val verticalScrollFactor = -getVerticalScrollFactor()
        val horizontalScrollFactor = -getHorizontalScrollFactor()

        return event.changes
            .fastFold(Offset.Zero) { acc, change -> acc + change.scrollDelta }
            .let { Offset(it.x * horizontalScrollFactor, it.y * verticalScrollFactor) }
    }
}
