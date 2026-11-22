// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.window

import top.yukonga.miuix.kmp.window.WindowResizeEdge
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Pointer
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Position
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.WindowGestureEffects
import top.yukonga.miuix.kmp.window.platform.linux.x11.LinuxX11WindowMover
import java.awt.Frame
import java.awt.Point
import java.awt.Window

/**
 * Executes gesture commands through native or fallback window operations. Each operation owns its
 * geometry and queued work; neither can change contact ownership or interpret another pointer's input.
 */
internal class LinuxWindowGestureEffects(
    window: Window,
    resizeRegions: UndecoratedWindowResizer,
) : WindowGestureEffects {
    override val mover: WindowGestureEffects.Mover = WindowMover(window)
    override val resizer: WindowGestureEffects.Resizer = object : WindowGestureEffects.Resizer {
        private var operation: LinuxWindowResizeOperation? = null

        override fun setEnabled(
            enabled: Boolean,
        ) {
            resizeRegions.enabled = enabled
        }

        override fun start(
            edge: WindowResizeEdge,
            position: Position,
        ) {
            operation?.finish()
            operation = LinuxWindowResizeOperation(
                window = window,
                edge = edge,
                start = Point(position.x, position.y),
            )
        }

        override fun update(
            position: Position,
        ) {
            operation?.update(Point(position.x, position.y))
        }

        override fun finish(
            applyPending: Boolean,
        ) {
            val active = operation
            operation = null
            active?.finish(applyPending)
        }

        override fun showEdge(
            edge: WindowResizeEdge,
        ) = resizeRegions.showEdge(edge)
    }

    private class WindowMover(
        private val window: Window,
    ) : WindowGestureEffects.Mover {
        private val fallback = LinuxFallbackWindowMover(window)
        private var useNativeMouseDrag = true
        private var nativeDrag: NativeDrag? = null

        override val isNativeMouseMove: Boolean
            get() {
                val active = nativeDrag ?: return false
                return active.pointer == Pointer.Mouse && active.move.started
            }

        override fun releaseNativeMouseMove(): Boolean {
            if (!isNativeMouseMove) return false
            nativeDrag?.move?.detach()
            nativeDrag = null
            return true
        }

        override fun prepareTouchMove(
            id: Long,
            start: Position,
            current: Position,
        ): Boolean {
            finish()
            nativeDrag = prepareNativeMove(Pointer.Touch(id), start, current) ?: return false
            return true
        }

        override fun commitTouchMove(
            id: Long,
        ): Boolean {
            val active = nativeDrag ?: return false
            if (active.pointer != Pointer.Touch(id)) return false
            if (active.move.start()) return true
            finish()
            return false
        }

        override fun install(): () -> Unit {
            val uninstallFallback = fallback.install()
            return {
                uninstallFallback()
                finish()
            }
        }

        override fun start(
            pointer: Pointer,
            start: Position,
            current: Position,
        ) {
            finish()
            if (pointer == Pointer.Mouse && useNativeMouseDrag) {
                nativeDrag = prepareNativeMove(pointer, start, current)
                if (nativeDrag?.move?.start() == true) return
                finish()
                useNativeMouseDrag = false
            }
            fallback.startDrag(Point(start.x, start.y), Point(current.x, current.y))
        }

        override fun update(
            position: Position,
        ) = fallback.updateDrag(Point(position.x, position.y))

        override fun finish() {
            val active = nativeDrag
            nativeDrag = null
            try {
                active?.move?.close()
            } finally {
                fallback.endDrag()
            }
        }

        override fun toggleMaximize() {
            val frame = window as? Frame ?: return
            frame.extendedState = if (frame.extendedState == Frame.MAXIMIZED_BOTH) {
                Frame.NORMAL
            } else {
                Frame.MAXIMIZED_BOTH
            }
        }

        private fun prepareNativeMove(
            pointer: Pointer,
            start: Position,
            current: Position,
        ): NativeDrag? {
            val move = LinuxX11WindowMover.prepare(
                window = window,
                start = Point(start.x, start.y),
                current = Point(current.x, current.y),
                fromTouch = pointer is Pointer.Touch,
            ) ?: return null
            return NativeDrag(pointer, move)
        }

        private class NativeDrag(
            val pointer: Pointer,
            val move: LinuxX11WindowMover.Move,
        )
    }
}
