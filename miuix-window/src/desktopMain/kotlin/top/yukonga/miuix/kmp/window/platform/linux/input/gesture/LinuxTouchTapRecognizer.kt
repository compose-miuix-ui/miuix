// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.input.gesture

import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Input
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Position

/**
 * Recognizes caption taps from supplied timestamps, scale and thresholds, without a platform clock.
 */
internal class LinuxTouchTapRecognizer {
    data class Configuration(
        val touchSlop: Float,
        val longPressTimeoutMillis: Long,
        val doubleTapMinTimeMillis: Long,
        val doubleTapTimeoutMillis: Long,
    )

    private lateinit var configuration: Configuration
    private var press: Press? = null
    private var previousTap: Tap? = null

    fun configure(
        configuration: Configuration,
    ) {
        this.configuration = configuration
    }

    fun begin(
        position: Position,
        timeMillis: Long,
    ) {
        press = Press(position, timeMillis)
    }

    fun finish(
        input: Input,
    ): Boolean {
        val current = press
        press = null
        if (current == null ||
            input.timeMillis - current.timeMillis !in
            0 until configuration.longPressTimeoutMillis ||
            !isWithinSlop(
                first = current.position,
                second = input.position,
                scaleX = input.scaleX,
                scaleY = input.scaleY,
            )
        ) {
            cancel()
            return false
        }
        val doubleTap = previousTap?.let { previous ->
            val interval = current.timeMillis - previous.upTimeMillis
            val withinTimeout = interval in
                configuration.doubleTapMinTimeMillis..configuration.doubleTapTimeoutMillis
            withinTimeout && isWithinSlop(
                first = previous.position,
                second = current.position,
                scaleX = input.scaleX,
                scaleY = input.scaleY,
            )
        } ?: false
        previousTap = if (doubleTap) null else Tap(current.position, input.timeMillis)
        return doubleTap
    }

    fun isWithinSlop(
        first: Position,
        second: Position,
        scaleX: Double,
        scaleY: Double,
    ): Boolean {
        val dx = (second.x - first.x) * scaleX
        val dy = (second.y - first.y) * scaleY
        val slop = configuration.touchSlop
        return dx * dx + dy * dy <= slop * slop
    }

    fun cancel() {
        press = null
        previousTap = null
    }

    private class Press(
        val position: Position,
        val timeMillis: Long,
    )

    private class Tap(
        val position: Position,
        val upTimeMillis: Long,
    )
}
