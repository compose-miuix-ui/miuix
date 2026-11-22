// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.input.gesture

import top.yukonga.miuix.kmp.window.WindowResizeEdge
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Pointer
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Position

/**
 * Side effects injected into the platform dispatcher, never into the gesture state machine.
 * Coordinates use logical screen units. Implementations own native resources and queued work.
 */
internal interface WindowGestureEffects {
    val mover: Mover
    val resizer: Resizer

    interface Mover {
        val isNativeMouseMove: Boolean
            get() = false

        /** Drops ownership of a native mouse move without cancelling it in the WM. */
        fun releaseNativeMouseMove(): Boolean

        fun prepareTouchMove(
            id: Long,
            start: Position,
            current: Position,
        ): Boolean

        fun commitTouchMove(
            id: Long,
        ): Boolean

        fun install(): () -> Unit

        fun start(
            pointer: Pointer,
            start: Position,
            current: Position,
        )

        fun update(
            position: Position,
        )

        fun finish()

        fun toggleMaximize()
    }

    interface Resizer {
        fun setEnabled(
            enabled: Boolean,
        )

        fun start(
            edge: WindowResizeEdge,
            position: Position,
        )

        fun update(
            position: Position,
        )

        fun finish(
            applyPending: Boolean,
        )

        fun showEdge(
            edge: WindowResizeEdge,
        )
    }

    /**
     * Activates the bound input target using native server time, independently of gesture capture.
     */
    fun interface Focus {
        fun onPress(
            serverTime: Long,
        )
    }
}
