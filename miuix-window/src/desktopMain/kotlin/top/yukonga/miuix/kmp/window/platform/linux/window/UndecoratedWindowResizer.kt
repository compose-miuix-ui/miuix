// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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
import java.awt.event.MouseEvent

internal class UndecoratedWindowResizer(
    private val onResizeEdgeChange: (WindowResizeEdge) -> Unit,
) {
    var enabled: Boolean by mutableStateOf(false)
    var resizerThickness: Dp by mutableStateOf(4.dp)

    private val sideBounds = mutableMapOf<WindowResizeEdge, Rect>()
    private val sideCursors = mutableMapOf<WindowResizeEdge, Int>()
    private var hoveredSide = WindowResizeEdge.None
    private var resizeCursor: Cursor? = null
    private var cursorComponent: Component? = null
    private var previousCursor: Cursor? = null

    @Composable
    fun Content(
        modifier: Modifier = Modifier,
    ) {
        if (enabled) {
            ResizerLayout(
                modifier = modifier,
            )
        }
    }

    @Composable
    private fun ResizerLayout(
        modifier: Modifier = Modifier,
    ) {
        Layout(
            {
                Side(Cursor.W_RESIZE_CURSOR, WindowResizeEdge.Left)
                Side(Cursor.E_RESIZE_CURSOR, WindowResizeEdge.Right)
                Side(Cursor.N_RESIZE_CURSOR, WindowResizeEdge.Top)
                Side(Cursor.S_RESIZE_CURSOR, WindowResizeEdge.Bottom)
                Side(Cursor.NW_RESIZE_CURSOR, WindowResizeEdge.TopLeft)
                Side(Cursor.NE_RESIZE_CURSOR, WindowResizeEdge.TopRight)
                Side(Cursor.SW_RESIZE_CURSOR, WindowResizeEdge.BottomLeft)
                Side(Cursor.SE_RESIZE_CURSOR, WindowResizeEdge.BottomRight)
            },
            modifier = modifier,
            measurePolicy = { measurables, constraints ->
                val b = resizerThickness.roundToPx()

                fun Measurable.measureSide(
                    width: Int,
                    height: Int,
                ) = measure(
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

    fun edgeAt(
        position: Offset,
    ): WindowResizeEdge = if (enabled) {
        sideBounds.entries.firstOrNull { it.value.contains(position) }?.key ?: WindowResizeEdge.None
    } else {
        WindowResizeEdge.None
    }

    fun showEdge(
        edge: WindowResizeEdge,
    ) {
        if (edge == hoveredSide) return
        hoveredSide = edge
        onResizeEdgeChange(edge)
        resizeCursor = sideCursors[edge]?.let(LinuxResizeCursor::get)
        if (edge == WindowResizeEdge.None) restoreCursor()
    }

    fun afterMouseEvent(
        event: MouseEvent,
    ) {
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

    private fun restoreCursor() {
        cursorComponent?.cursor = previousCursor
        cursorComponent = null
        previousCursor = null
    }

    @Composable
    private fun Side(
        cursorId: Int,
        sides: WindowResizeEdge,
    ) {
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
}
