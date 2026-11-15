// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

/** Measured in the scrolling content's logical coordinates; updated only by layout. */
internal class NavigationRailSlot {
    var left = 0f
    var top = 0f
    var width = 0f
    var height = 0f
}

/** One press and one pair of indicator springs for the entire panel/capsule lifetime. */
internal class NavigationRailInteraction(
    val widths: List<Float>,
    val contentStart: Float,
    val endPadding: Float,
    selectedIndex: Int,
    private val scope: CoroutineScope,
) {
    val slots = List(widths.size) { NavigationRailSlot() }
    val rowInteractions = List(widths.size) { MutableInteractionSource() }
    private val starts = FloatArray(widths.size + 1).also { starts ->
        starts[0] = contentStart
        for (index in widths.indices) starts[index + 1] = starts[index] + widths[index]
    }
    val width: Float get() = starts.last() + endPadding
    val left = Animatable(starts[selectedIndex])
    val right = Animatable(starts[selectedIndex + 1])
    var pressedIndex by mutableIntStateOf(-1)
        private set
    var releaseGeneration by mutableIntStateOf(0)
        private set

    fun indexAt(x: Float, y: Float): Int {
        var nearest = -1
        var distance = Float.POSITIVE_INFINITY
        for (index in slots.indices) {
            val slot = slots[index]
            if (x < slot.left || x >= slot.left + slot.width || y < slot.top || y >= slot.top + slot.height) continue
            // Flying rows can cross. Prefer the visible slot centre, never the hidden icon column.
            val dx = (x - slot.left - slot.width / 2f) / slot.width.coerceAtLeast(1f)
            val dy = (y - slot.top - slot.height / 2f) / slot.height.coerceAtLeast(1f)
            val candidate = dx * dx + dy * dy
            if (candidate < distance) {
                nearest = index
                distance = candidate
            }
        }
        return nearest
    }

    fun positionAt(index: Int, x: Float): Float {
        val slot = slots[index]
        return starts[index] + ((x - slot.left) / slot.width.coerceAtLeast(1f)).coerceIn(0f, 1f) * widths[index]
    }

    fun indexAt(position: Float): Int = navigationSlotAt(position - contentStart, widths).toInt().coerceIn(widths.indices)

    fun press(index: Int) {
        pressedIndex = index
        settle(index)
    }

    fun release() {
        pressedIndex = -1
        releaseGeneration++
    }

    fun settle(index: Int) {
        animate(starts[index], starts[index + 1])
    }

    fun drag(position: Float, grabOffset: Float, delta: Float, previousIndex: Int) {
        val index = indexAt(position)
        val chipWidth = navigationDragWidth(position - contentStart, widths)
        val travelStart = contentStart - endPadding
        val target = navigationDragTarget(
            left = position - grabOffset,
            width = chipWidth,
            minLeft = travelStart,
            maxRight = width,
            delta = delta,
            changedItem = index != previousIndex,
        )
        pressedIndex = index
        animate(target.left, target.right, target.leftSpring, target.rightSpring)
    }

    fun start(index: Int): Float = starts[index]

    private fun animate(
        start: Float,
        end: Float,
        leftSpring: SpringSpec<Float> = GlassMotion.edgeSpring(start <= left.value),
        rightSpring: SpringSpec<Float> = GlassMotion.edgeSpring(end > right.value),
    ) {
        if (left.targetValue != start) scope.launch { left.animateTo(start, leftSpring) }
        if (right.targetValue != end) scope.launch { right.animateTo(end, rightSpring) }
    }
}

/** A stable recognizer: morph frames and the expanded target never replace an in-flight press. */
internal fun Modifier.navigationRailGestures(
    interaction: NavigationRailInteraction,
    layoutDirection: LayoutDirection,
    expanded: State<Boolean>,
    headerBottom: State<() -> Float>,
    onSelect: State<(Int) -> Unit>,
): Modifier = pointerInput(interaction, layoutDirection) {
    awaitEachGesture {
        val down = awaitFirstDown()
        fun logicalX(position: Offset): Float = if (layoutDirection == LayoutDirection.Rtl) size.width - position.x else position.x
        if (down.position.y < headerBottom.value()) return@awaitEachGesture
        val initial = interaction.indexAt(logicalX(down.position), down.position.y)
        if (initial < 0) return@awaitEachGesture
        // Lock the gesture policy at down, not at the end of an unrelated morph animation.
        val horizontal = !expanded.value
        var position = interaction.positionAt(initial, logicalX(down.position))
        val dragScale = interaction.widths[initial] / interaction.slots[initial].width.coerceAtLeast(1f)
        var movementX = 0f
        var movementY = 0f
        var dragging = false
        var current = initial
        val grabOffset = position - interaction.start(initial)
        val source = interaction.rowInteractions[initial]
        val rowPress = if (horizontal) null else PressInteraction.Press(down.position)
        var committed = false
        if (horizontal) interaction.press(initial) else source.tryEmit(checkNotNull(rowPress))
        try {
            while (true) {
                val event = awaitPointerEvent()
                val change = event.changes.firstOrNull { it.id == down.id } ?: break
                if (change.isConsumed) break
                val physicalDelta = change.position - change.previousPosition
                val delta = (if (layoutDirection == LayoutDirection.Rtl) -physicalDelta.x else physicalDelta.x) * dragScale
                movementX += physicalDelta.x
                movementY += physicalDelta.y
                position += delta
                if (!change.pressed) {
                    if (change.changedToUp()) {
                        onSelect.value(if (horizontal) interaction.indexAt(position) else initial)
                        change.consume()
                        committed = true
                    }
                    break
                }
                if (!dragging) {
                    if (abs(movementY) > viewConfiguration.touchSlop && abs(movementY) > abs(movementX)) break
                    if (abs(movementX) > viewConfiguration.touchSlop) {
                        if (!horizontal) break
                        dragging = true
                    }
                }
                if (dragging) {
                    interaction.drag(position, grabOffset, delta, current)
                    current = interaction.indexAt(position)
                    change.consume()
                } else {
                    // Let an ancestor's vertical scroller win without committing a destination.
                    val final = awaitPointerEvent(PointerEventPass.Final)
                    if (final.changes.any { it.id == down.id && it.isConsumed }) break
                }
            }
        } finally {
            if (horizontal) {
                interaction.release()
            } else if (rowPress != null) {
                source.tryEmit(if (committed) PressInteraction.Release(rowPress) else PressInteraction.Cancel(rowPress))
            }
        }
    }
}
