// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.window.WindowResizeEdge
import java.awt.Component
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Point
import java.awt.Window
import java.awt.event.MouseEvent

internal class UndecoratedWindowResizer(
    private val window: Window,
    private val onResizeEdgeChange: (WindowResizeEdge) -> Unit,
) : LinuxWindowMouseInput.Handler {
    var enabled: Boolean by mutableStateOf(false)
    var resizerThickness: Dp by mutableStateOf(4.dp)

    private val sideBounds = mutableMapOf<Int, Rect>()
    private val sideCursors = mapOf(
        Side.Left to Cursor.W_RESIZE_CURSOR,
        Side.Right to Cursor.E_RESIZE_CURSOR,
        Side.Top to Cursor.N_RESIZE_CURSOR,
        Side.Bottom to Cursor.S_RESIZE_CURSOR,
        (Side.Left or Side.Top) to Cursor.NW_RESIZE_CURSOR,
        (Side.Right or Side.Top) to Cursor.NE_RESIZE_CURSOR,
        (Side.Left or Side.Bottom) to Cursor.SW_RESIZE_CURSOR,
        (Side.Right or Side.Bottom) to Cursor.SE_RESIZE_CURSOR,
    )
    private var resizingSide = 0
    private var hoveredSide = 0
    private var suppressClick = false
    private var resizeCursor: Cursor? = null
    private var cursorComponent: Component? = null
    private var previousCursor: Cursor? = null
    private var initialPointPos = Point()
    private var initialWindowPos = Point()
    private var initialWindowSize = Dimension()

    @Composable
    fun Content(modifier: Modifier = Modifier) {
        DisposableEffect(this) {
            val unregister = LinuxWindowMouseInput.register(
                window = window,
                handler = this@UndecoratedWindowResizer,
                priority = 1,
            )
            onDispose {
                unregister()
                sideBounds.clear()
            }
        }

        val density = LocalDensity.current
        Spacer(
            modifier = modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    if (!enabled) {
                        sideBounds.clear()
                        return@onGloballyPositioned
                    }
                    val bounds = coordinates.boundsInWindow()
                    val b = with(density) { resizerThickness.toPx() }
                    val l = bounds.left
                    val t = bounds.top
                    val w = bounds.width
                    val h = bounds.height

                    sideBounds[Side.Left] = Rect(l, t + b, l + b, t + h - b)
                    sideBounds[Side.Right] = Rect(l + w - b, t + b, l + w, t + h - b)
                    sideBounds[Side.Top] = Rect(l + b, t, l + w - b, t + b)
                    sideBounds[Side.Bottom] = Rect(l + b, t + h - b, l + w - b, t + h)
                    sideBounds[Side.Left or Side.Top] = Rect(l, t, l + b, t + b)
                    sideBounds[Side.Right or Side.Top] = Rect(l + w - b, t, l + w, t + b)
                    sideBounds[Side.Left or Side.Bottom] = Rect(l, t + h - b, l + b, t + h)
                    sideBounds[Side.Right or Side.Bottom] = Rect(l + w - b, t + h - b, l + w, t + h)
                },
        )
    }

    override fun onMouseEvent(event: MouseEvent): Boolean {
        if (!enabled) return false
        val position = LinuxWindowMouseInput.positionInContent(event, window)
        val side = sideBounds.entries.firstOrNull { it.value.contains(position) }?.key ?: 0
        when (event.id) {
            MouseEvent.MOUSE_MOVED, MouseEvent.MOUSE_ENTERED -> setHoveredSide(side)

            MouseEvent.MOUSE_EXITED -> if (resizingSide == 0) setHoveredSide(0)

            MouseEvent.MOUSE_PRESSED -> {
                if (event.button != MouseEvent.BUTTON1) return resizingSide != 0
                suppressClick = false
                if (side == 0) return false
                resizingSide = side
                initialPointPos = event.locationOnScreen
                initialWindowPos = window.location
                initialWindowSize = window.size
                setHoveredSide(side)
                resizeCursor = sideCursors[side]?.let(LinuxResizeCursor::get)
                return true
            }

            MouseEvent.MOUSE_DRAGGED -> {
                if (resizingSide == 0) return false
                resize(resizingSide, event.locationOnScreen)
                return true
            }

            MouseEvent.MOUSE_RELEASED -> {
                if (resizingSide == 0) return false
                if (event.button == MouseEvent.BUTTON1) {
                    resizingSide = 0
                    suppressClick = true
                    setHoveredSide(side)
                }
                return true
            }

            MouseEvent.MOUSE_CLICKED -> if (suppressClick && event.button == MouseEvent.BUTTON1) {
                suppressClick = false
                return true
            }
        }
        return false
    }

    override fun afterMouseEvent(event: MouseEvent) {
        val cursor = resizeCursor ?: return
        val component = event.component
        if (cursorComponent !== component) {
            restoreCursor()
            cursorComponent = component
            previousCursor = if (component.isCursorSet) component.cursor else null
        }
        // Compose may update the cursor while dispatching hover events to a Dialog.
        component.cursor = cursor
    }

    override fun cancel() {
        resizingSide = 0
        suppressClick = false
        setHoveredSide(0)
        restoreCursor()
    }

    private fun setHoveredSide(side: Int) {
        if (side == hoveredSide) return
        hoveredSide = side
        onResizeEdgeChange(side.toWindowResizeEdge())
        resizeCursor = sideCursors[side]?.let(LinuxResizeCursor::get)
        if (side == 0) restoreCursor()
    }

    private fun restoreCursor() {
        cursorComponent?.cursor = previousCursor
        cursorComponent = null
        previousCursor = null
    }

    private fun Int.toWindowResizeEdge(): WindowResizeEdge {
        fun Int.contains(value: Int) = this and value == value
        val hasLeft = contains(Side.Left)
        val hasRight = contains(Side.Right)
        val hasTop = contains(Side.Top)
        val hasBottom = contains(Side.Bottom)
        return when {
            hasLeft && hasTop -> WindowResizeEdge.TopLeft
            hasRight && hasTop -> WindowResizeEdge.TopRight
            hasLeft && hasBottom -> WindowResizeEdge.BottomLeft
            hasRight && hasBottom -> WindowResizeEdge.BottomRight
            hasLeft -> WindowResizeEdge.Left
            hasRight -> WindowResizeEdge.Right
            hasTop -> WindowResizeEdge.Top
            hasBottom -> WindowResizeEdge.Bottom
            else -> WindowResizeEdge.None
        }
    }

    private fun resize(sides: Int, pointPos: Point) {
        val diffX = pointPos.x - initialPointPos.x
        val diffY = pointPos.y - initialPointPos.y
        var newXPos = window.x
        var newYPos = window.y
        var newWidth = window.width
        var newHeight = window.height

        fun Int.contains(value: Int) = this and value == value

        if (sides.contains(Side.Left)) {
            newWidth = initialWindowSize.width - diffX
            newWidth = newWidth.coerceAtLeast(window.minimumSize.width)
            newXPos = initialWindowPos.x + initialWindowSize.width - newWidth
        } else if (sides.contains(Side.Right)) {
            newWidth = (initialWindowSize.width + diffX).coerceAtLeast(window.minimumSize.width)
        }
        if (sides.contains(Side.Top)) {
            newHeight = initialWindowSize.height - diffY
            newHeight = newHeight.coerceAtLeast(window.minimumSize.height)
            newYPos = initialWindowPos.y + initialWindowSize.height - newHeight
        } else if (sides.contains(Side.Bottom)) {
            newHeight = (initialWindowSize.height + diffY).coerceAtLeast(window.minimumSize.height)
        }
        window.setBounds(newXPos, newYPos, newWidth, newHeight)
    }

    @Suppress("ConstPropertyName")
    private object Side {
        const val Left = 0x0001
        const val Top = 0x0010
        const val Right = 0x0100
        const val Bottom = 0x1000
    }
}
