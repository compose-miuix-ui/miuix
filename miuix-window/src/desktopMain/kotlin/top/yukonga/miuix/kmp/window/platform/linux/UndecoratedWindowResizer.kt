// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Constraints
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
    private val sideCursors = mutableMapOf<Int, Int>()
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
        DisposableEffect(this, enabled) {
            val unregister = LinuxWindowMouseInput.register(
                window = window,
                handler = this@UndecoratedWindowResizer,
                priority = 1,
            )
            onDispose { unregister() }
        }
        if (!enabled) return

        Layout(
            {
                Side(Cursor.W_RESIZE_CURSOR, Side.Left)
                Side(Cursor.E_RESIZE_CURSOR, Side.Right)
                Side(Cursor.N_RESIZE_CURSOR, Side.Top)
                Side(Cursor.S_RESIZE_CURSOR, Side.Bottom)
                Side(Cursor.NW_RESIZE_CURSOR, Side.Left or Side.Top)
                Side(Cursor.NE_RESIZE_CURSOR, Side.Right or Side.Top)
                Side(Cursor.SW_RESIZE_CURSOR, Side.Left or Side.Bottom)
                Side(Cursor.SE_RESIZE_CURSOR, Side.Right or Side.Bottom)
            },
            modifier = modifier,
            measurePolicy = { measurables, constraints ->
                val b = resizerThickness.roundToPx()

                fun Measurable.measureSide(width: Int, height: Int) = measure(
                    Constraints.fixed(width.coerceAtLeast(0), height.coerceAtLeast(0)),
                )

                val left = measurables[0].measureSide(b, constraints.maxHeight - 2 * b)
                val right = measurables[1].measureSide(b, constraints.maxHeight - 2 * b)
                val top = measurables[2].measureSide(constraints.maxWidth - 2 * b, b)
                val bottom = measurables[3].measureSide(constraints.maxWidth - 2 * b, b)
                val leftTop = measurables[4].measureSide(b, b)
                val rightTop = measurables[5].measureSide(b, b)
                val leftBottom = measurables[6].measureSide(b, b)
                val rightBottom = measurables[7].measureSide(b, b)
                layout(constraints.maxWidth, constraints.maxHeight) {
                    left.place(0, b)
                    right.place(constraints.maxWidth - b, b)
                    top.place(b, 0)
                    bottom.place(b, constraints.maxHeight - b)
                    leftTop.place(0, 0)
                    rightTop.place(constraints.maxWidth - b, 0)
                    leftBottom.place(0, constraints.maxHeight - b)
                    rightBottom.place(constraints.maxWidth - b, constraints.maxHeight - b)
                }
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
                resizeCursor = LinuxResizeCursor.get(sideCursors.getValue(side))
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
            previousCursor = component.cursor
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

    @Composable
    private fun Side(cursorId: Int, sides: Int) {
        DisposableEffect(sides) {
            sideCursors[sides] = cursorId
            onDispose {
                sideBounds.remove(sides)
                sideCursors.remove(sides)
            }
        }
        Layout(
            content = {},
            modifier = Modifier.onGloballyPositioned {
                sideBounds[sides] = it.boundsInWindow()
            },
            measurePolicy = { _, constraints ->
                layout(constraints.maxWidth, constraints.maxHeight) {}
            },
        )
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
        window.setLocation(newXPos, newYPos)
        window.setSize(newWidth, newHeight)
    }

    @Suppress("ConstPropertyName")
    private object Side {
        const val Left = 0x0001
        const val Top = 0x0010
        const val Right = 0x0100
        const val Bottom = 0x1000
    }
}
