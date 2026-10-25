// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.window

import java.awt.Cursor
import java.awt.Frame
import java.awt.Point
import java.awt.Window
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

/**
 * Owns AWT window movement, including asynchronous restore from maximized state.
 * All positions and the pointer anchor use logical AWT screen coordinates.
 */
internal class LinuxFallbackWindowMover(
    private val window: Window,
) {
    private var restoreWidth = window.width
    private var drag: Drag? = null

    fun install(): () -> Unit {
        val listener = object : ComponentAdapter() {
            override fun componentResized(event: ComponentEvent) = onWindowResized()
        }
        window.addComponentListener(listener)
        return {
            window.removeComponentListener(listener)
            endDrag()
        }
    }

    fun startDrag(
        start: Point,
        current: Point,
    ) {
        val frame = window as? Frame
        val restoring = frame?.extendedState == Frame.MAXIMIZED_BOTH
        val offsetX = start.x - window.x
        val anchor = Point(
            if (restoring) (restoreWidth * offsetX.toFloat() / window.width).toInt() else offsetX,
            start.y - window.y,
        )
        drag = Drag(anchor, Point(current), restoring)
        if (restoring) {
            frame.extendedState = Frame.NORMAL
        } else {
            updateDrag(current)
        }
    }

    fun updateDrag(position: Point) {
        val active = drag ?: return
        active.position = Point(position)
        if (active.restoring) return
        window.setLocation(position.x - active.anchor.x, position.y - active.anchor.y)
    }

    fun endDrag() {
        if (drag != null) window.cursor = Cursor.getDefaultCursor()
        drag = null
    }

    private fun onWindowResized() {
        if ((window as? Frame)?.extendedState == Frame.MAXIMIZED_BOTH) return
        restoreWidth = window.width
        val active = drag ?: return
        if (!active.restoring) return
        active.restoring = false
        // Apply the latest pointer only after restore preserves the WM's saved geometry.
        updateDrag(active.position)
    }

    private class Drag(
        val anchor: Point,
        var position: Point,
        var restoring: Boolean,
    )
}
