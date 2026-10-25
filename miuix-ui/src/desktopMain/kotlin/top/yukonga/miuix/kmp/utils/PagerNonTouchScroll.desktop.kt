// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.ui.awt.awtEventOrNull
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFold
import java.awt.event.MouseWheelEvent
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Mouse wheel behavior of Compose Desktop, mirroring `LocalScrollConfig` and the platform
 * `ScrollConfig`s: Linux scrolls by a fraction of the square root of the bounds, Windows by a
 * fraction of the bounds and macOS by dp, all scaled by the wheel's `scrollAmount`.
 */
internal actual fun CompositionLocalConsumerModifierNode.pagerScrollConfig(): PagerScrollConfig = when (desktopPlatform) {
    DesktopPlatform.Linux -> LinuxPagerScrollConfig
    DesktopPlatform.Windows, DesktopPlatform.Unknown -> WindowsPagerScrollConfig
    DesktopPlatform.MacOS -> MacOSPagerScrollConfig
}

private enum class DesktopPlatform { Linux, Windows, MacOS, Unknown }

private val desktopPlatform: DesktopPlatform = System.getProperty("os.name")?.let { name ->
    when {
        name.startsWith("Linux") -> DesktopPlatform.Linux
        name.startsWith("Win") -> DesktopPlatform.Windows
        name == "Mac OS X" -> DesktopPlatform.MacOS
        else -> DesktopPlatform.Unknown
    }
} ?: DesktopPlatform.Unknown

private abstract class DesktopPagerScrollConfig : PagerScrollConfig {
    override val isSmoothScrollingEnabled: Boolean =
        System.getProperty("compose.scrolling.smooth.enabled") != "false"

    override fun isPreciseWheelScroll(event: PointerEvent): Boolean = event.isPreciseWheelRotation

    protected fun calculateOffsetByPage(event: PointerEvent, bounds: IntSize): Offset = Offset(
        x = event.totalScrollDelta.x * bounds.width,
        y = event.totalScrollDelta.y * bounds.height,
    )

    protected val PointerEvent.totalScrollDelta: Offset
        get() = changes.fastFold(Offset.Zero) { acc, change -> acc + change.scrollDelta }

    protected val PointerEvent.totalPanGestureOffset: Offset
        get() = -changes.fastFold(Offset.Zero) { acc, change -> acc + change.panOffset }

    protected val PointerEvent.scrollAmount: Float
        get() = (awtEventOrNull as? MouseWheelEvent)?.scrollAmount?.toFloat() ?: 1f

    protected val PointerEvent.shouldScrollByPage: Boolean
        get() = (awtEventOrNull as? MouseWheelEvent)?.scrollType == MouseWheelEvent.WHEEL_BLOCK_SCROLL

    private val PointerEvent.isPreciseWheelRotation: Boolean
        get() = (awtEventOrNull as? MouseWheelEvent)?.isPreciseWheelRotation ?: false

    private val MouseWheelEvent.isPreciseWheelRotation: Boolean
        get() = when (desktopPlatform) {
            // On Windows, even free scrolling wheels should trigger animation
            DesktopPlatform.Windows -> false

            // For other platforms, apply this heuristic to determine if it's
            // a high-precision wheel/trackpad or a regular stepping mouse wheel.
            else -> abs(preciseWheelRotation - wheelRotation.toDouble()) > 0.001
        }
}

private object LinuxPagerScrollConfig : DesktopPagerScrollConfig() {
    override fun Density.calculateMouseWheelScroll(event: PointerEvent, bounds: IntSize): Offset {
        if (event.type == PointerEventType.PanMove) {
            return event.totalPanGestureOffset
        }

        return if (event.shouldScrollByPage) {
            calculateOffsetByPage(event, bounds)
        } else {
            Offset(
                x = event.totalScrollDelta.x * sqrt(bounds.width.toFloat()),
                y = event.totalScrollDelta.y * sqrt(bounds.height.toFloat()),
            )
        } * -event.scrollAmount
    }
}

private object WindowsPagerScrollConfig : DesktopPagerScrollConfig() {
    override fun Density.calculateMouseWheelScroll(event: PointerEvent, bounds: IntSize): Offset {
        if (event.type == PointerEventType.PanMove) {
            return event.totalPanGestureOffset
        }

        return if (event.shouldScrollByPage) {
            calculateOffsetByPage(event, bounds)
        } else {
            Offset(
                x = event.totalScrollDelta.x * (bounds.width / 20f),
                y = event.totalScrollDelta.y * (bounds.height / 20f),
            )
        } * -event.scrollAmount
    }
}

private object MacOSPagerScrollConfig : DesktopPagerScrollConfig() {
    override fun Density.calculateMouseWheelScroll(event: PointerEvent, bounds: IntSize): Offset = if (event.shouldScrollByPage) {
        calculateOffsetByPage(event, bounds) * -event.scrollAmount
    } else {
        event.totalScrollDelta * 10.dp.toPx() * -event.scrollAmount
    }
}
