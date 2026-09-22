// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalWasmJsInterop::class)

package top.yukonga.miuix.kmp.utils

import androidx.compose.ui.dom.domEventOrNull
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFold
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.WheelEvent
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.js
import kotlin.math.abs

/**
 * Mouse wheel behavior of the web, mirroring `JsConfig`: the conversion depends on the wheel
 * event's `deltaMode`, and high-resolution devices (trackpads, freely rotating wheels) are detected
 * so their deltas apply immediately instead of being animated.
 */
internal actual fun CompositionLocalConsumerModifierNode.pagerScrollConfig(): PagerScrollConfig = WebPagerScrollConfig

private object WebPagerScrollConfig : PagerScrollConfig {
    override fun Density.calculateMouseWheelScroll(event: PointerEvent, bounds: IntSize): Offset = when (val deltaMode = (event.domEventOrNull as? WheelEvent)?.deltaMode) {
        WheelEvent.DOM_DELTA_LINE -> event.totalScrollDelta * -defaultLineScrollHeight.dp.toPx()

        WheelEvent.DOM_DELTA_PAGE -> Offset(
            x = event.totalScrollDelta.x * bounds.width,
            y = event.totalScrollDelta.y * bounds.height,
        ) * -1f

        WheelEvent.DOM_DELTA_PIXEL -> event.totalScrollDelta * -1.dp.toPx()

        else -> {
            println("Unknown delta mode: $deltaMode")
            event.totalScrollDelta * -1.dp.toPx()
        }
    }

    private class LastWheelEvent(val deltaX: Double, val deltaY: Double, val timeStamp: Double)

    // Information about the previously processed wheel event, used to disambiguate
    // trackpad gestures from mouse wheel ticks (see [isTrackpadEvent]).
    private var lastWheelEvent: LastWheelEvent? = null
    private var lastWheelEventWasTrackpad = false

    override fun isPreciseWheelScroll(event: PointerEvent): Boolean {
        val wheelEvent = event.domEventOrNull as? WheelEvent
        if (wheelEvent == null) {
            lastWheelEvent = null
            lastWheelEventWasTrackpad = false
            return false
        }
        val isTrackpad = isTrackpadEvent(wheelEvent)
        val isPrecise = wheelEvent.deltaMode != WheelEvent.DOM_DELTA_PIXEL || isTrackpad
        lastWheelEvent = LastWheelEvent(
            deltaX = wheelEvent.deltaX,
            deltaY = wheelEvent.deltaY,
            timeStamp = wheelEvent.timeStamp.toDouble(),
        )
        lastWheelEventWasTrackpad = isTrackpad
        return isPrecise
    }

    /**
     * Heuristically detects whether a wheel event comes from a high-resolution input device (a
     * trackpad or a freely rotating, notch-less wheel) rather than a regular stepping mouse wheel.
     * High-resolution input should be applied immediately, while a stepping wheel animates between
     * ticks.
     */
    private fun isTrackpadEvent(event: WheelEvent): Boolean {
        // The disambiguation below reasons about pixel deltas. Line- and page-mode deltas are
        // already discrete, device-independent units (a line, a viewport), so there is no
        // trackpad/stepping-wheel ambiguity to resolve.
        if (event.deltaMode != WheelEvent.DOM_DELTA_PIXEL) {
            return false
        }
        // Firefox restricts the legacy wheelDelta properties, so they don't provide enough
        // information to reliably disambiguate trackpad events from mouse wheel events.
        if (isFirefox) {
            return false
        }
        // Safari/WebKit keeps the legacy wheelDelta proportional to the already-accelerated pixel
        // delta, so the acceleration heuristic never fires and a plain notch mouse wheel is
        // misclassified as a trackpad.
        if (isSafari) {
            return false
        }
        val wheelDeltaX = legacyWheelDeltaX(event).takeUnless { it.isNaN() }
        val wheelDeltaY = legacyWheelDeltaY(event).takeUnless { it.isNaN() }
        if (
            isAcceleratedMouseWheelDelta(event.deltaX, wheelDeltaX) ||
            isAcceleratedMouseWheelDelta(event.deltaY, wheelDeltaY)
        ) {
            return false
        }
        // Blink and WebKit use a delta of 120 to represent one mouse wheel turn. If both axes of
        // the delta (or of wheelDelta) are divisible by 120, this event is probably from a mouse.
        val looksLikeMouseTick =
            (event.deltaX % 120.0 == 0.0 && event.deltaY % 120.0 == 0.0) ||
                ((wheelDeltaX ?: 1.0) % 120.0 == 0.0 && (wheelDeltaY ?: 1.0) % 120.0 == 0.0)
        if (looksLikeMouseTick) {
            val last = lastWheelEvent ?: return false
            val deltaXChange = abs(event.deltaX - last.deltaX)
            val deltaYChange = abs(event.deltaY - last.deltaY)
            // A trackpad event might by chance have a delta of exactly 120, so make sure this
            // event doesn't have a similar delta to the previous one before treating it as a
            // mouse wheel. The 50ms window and the 20.0 delta-change threshold are empirical
            // anti-flapping values.
            return lastWheelEventWasTrackpad &&
                event.timeStamp.toDouble() - last.timeStamp < 50.0 &&
                ((deltaXChange == 0.0 && deltaYChange == 0.0) || !(deltaXChange < 20.0 && deltaYChange < 20.0))
        }
        return true
    }

    private fun isAcceleratedMouseWheelDelta(delta: Double, wheelDelta: Double?): Boolean {
        // On macOS, scrolling with a mouse wheel applies an acceleration curve, so delta values
        // ramp up and are not fixed multiples of 120, but the wheelDelta property keeps its
        // original value: by convention three times the delta with the opposite sign.
        if (wheelDelta == null) return false
        // Real wheel events always report wheelDelta with the sign opposite to delta.
        if (delta * wheelDelta >= 0.0) return false
        return abs(wheelDelta - (-3.0 * delta)) > 1.0
    }
}

private val PointerEvent.totalScrollDelta: Offset
    get() = changes.fastFold(Offset.Zero) { acc, change -> acc + change.scrollDelta }

/** Whether the current browser is Firefox, detected once from the user agent. */
private val isFirefox: Boolean by lazy {
    window.navigator.userAgent.contains("firefox", ignoreCase = true)
}

/**
 * Whether the current browser is Safari/WebKit, detected once from the user agent. Chromium-based
 * browsers also carry "Safari" in their user agent, so they are explicitly excluded.
 */
private val isSafari: Boolean by lazy {
    val userAgent = window.navigator.userAgent
    userAgent.contains("safari", ignoreCase = true) &&
        !userAgent.contains("chrome", ignoreCase = true) &&
        !userAgent.contains("chromium", ignoreCase = true) &&
        !userAgent.contains("android", ignoreCase = true)
}

// The legacy wheelDeltaX/wheelDeltaY properties are non-standard and may be absent (e.g. in
// Firefox), in which case these helpers return NaN to represent an unavailable value.
private fun legacyWheelDeltaX(event: WheelEvent): Double = js("(event.wheelDeltaX == null) ? NaN : event.wheelDeltaX")

private fun legacyWheelDeltaY(event: WheelEvent): Double = js("(event.wheelDeltaY == null) ? NaN : event.wheelDeltaY")

/** The default line height (in dp) used to convert line-mode wheel deltas to pixels. */
private val defaultLineScrollHeight: Float by lazy { computeDefaultLineScrollHeight() }

private const val FALLBACK_LINE_SCROLL_HEIGHT = 16f

private fun computeDefaultLineScrollHeight(): Float {
    val body = document.body ?: return FALLBACK_LINE_SCROLL_HEIGHT
    val probe = document.createElement("div") as HTMLElement
    probe.style.fontSize = "initial"
    probe.style.display = "none"
    body.appendChild(probe)
    val fontSize = window.getComputedStyle(probe).fontSize
    body.removeChild(probe)
    return fontSize.removeSuffix("px").toFloatOrNull() ?: FALLBACK_LINE_SCROLL_HEIGHT
}
