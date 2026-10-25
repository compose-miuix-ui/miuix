// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.window

import top.yukonga.miuix.kmp.window.WindowResizeEdge
import java.awt.EventQueue
import java.awt.Point
import java.awt.Window

/**
 * Applies one mouse or touch resize from its initial window bounds, confined to the AWT event thread.
 *
 * Updates retain only the latest position until the queued AWT task runs. Finishing the operation
 * invalidates that task without consulting the gesture owner or a subsequent gesture.
 */
internal class LinuxWindowResizeOperation(
    private val window: Window,
    private val edge: WindowResizeEdge,
    private val start: Point,
) {
    private val bounds = window.bounds
    private var pendingPosition: Point? = null
    private var finished = false

    fun update(position: Point) {
        if (finished) return
        val alreadyQueued = pendingPosition != null
        pendingPosition = position
        if (alreadyQueued) return
        EventQueue.invokeLater {
            val latest = pendingPosition ?: return@invokeLater
            pendingPosition = null
            resize(latest)
        }
    }

    /**
     * Ends this operation and prevents queued updates from affecting later gestures.
     *
     * @param applyPendingResize Pass `true` on mouse or touch release to apply the final pending
     * position. Leave `false` on cancellation to discard it without changing the window bounds.
     */
    fun finish(applyPendingResize: Boolean = false) {
        if (finished) return
        finished = true
        val latest = pendingPosition
        pendingPosition = null
        if (applyPendingResize && latest != null) resize(latest)
    }

    private fun resize(position: Point) {
        val dx = position.x - start.x
        val dy = position.y - start.y
        var x = bounds.x
        var y = bounds.y
        var width = bounds.width
        var height = bounds.height
        when (edge) {
            WindowResizeEdge.Left, WindowResizeEdge.TopLeft, WindowResizeEdge.BottomLeft -> {
                width = (bounds.width - dx).coerceAtLeast(window.minimumSize.width)
                x = bounds.x + bounds.width - width
            }

            WindowResizeEdge.Right, WindowResizeEdge.TopRight, WindowResizeEdge.BottomRight ->
                width = (bounds.width + dx).coerceAtLeast(window.minimumSize.width)

            else -> Unit
        }
        when (edge) {
            WindowResizeEdge.Top, WindowResizeEdge.TopLeft, WindowResizeEdge.TopRight -> {
                height = (bounds.height - dy).coerceAtLeast(window.minimumSize.height)
                y = bounds.y + bounds.height - height
            }

            WindowResizeEdge.Bottom, WindowResizeEdge.BottomLeft, WindowResizeEdge.BottomRight ->
                height = (bounds.height + dy).coerceAtLeast(window.minimumSize.height)

            else -> Unit
        }
        window.setLocation(x, y)
        window.setSize(width, height)
    }
}
