// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import java.awt.Cursor
import java.awt.Point
import java.awt.Window
import java.awt.event.MouseEvent

/**
 * Fallback window mover using AWT mouse events.
 *
 * From `androidx.compose.foundation.window.StandardMoveHandler`
 */

internal class LinuxFallbackWindowMover(
    private val window: Window,
) {
    private var dragStartScreenPos: Point? = null
    private var dragStartWindowPos: Point? = null
    private var isDragging = false

    fun startDrag(e: MouseEvent) {
        dragStartScreenPos = Point(e.xOnScreen, e.yOnScreen)
        dragStartWindowPos = window.location
        isDragging = true
    }

    fun updateDrag(e: MouseEvent) {
        if (!isDragging) return

        val startScreen = dragStartScreenPos ?: return
        val startWindow = dragStartWindowPos ?: return

        val newX = startWindow.x + (e.xOnScreen - startScreen.x)
        val newY = startWindow.y + (e.yOnScreen - startScreen.y)

        window.location = Point(newX, newY)
    }

    fun endDrag() {
        if (isDragging) {
            window.cursor = Cursor.getDefaultCursor()
        }
        isDragging = false
        dragStartScreenPos = null
        dragStartWindowPos = null
    }

    val dragging: Boolean
        get() = isDragging
}
