// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.IntrinsicMeasurable
import androidx.compose.ui.layout.IntrinsicMeasureScope
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Measured
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import kotlin.math.roundToInt

private const val POPUP_PROBE_COUNT = 8

/** Bounds intrinsic sampling while measuring all content for scrolling. */
@Composable
internal fun GlassPopupColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Layout(
        content = { PopupColumnScope.content() },
        modifier = modifier,
        measurePolicy = PopupColumnMeasurePolicy,
    )
}

// Preserve ColumnScope modifiers with bounded intrinsic sampling.
private object PopupColumnScope : ColumnScope {
    @Stable
    override fun Modifier.weight(weight: Float, fill: Boolean): Modifier {
        require(weight > 0f) { "weight must be greater than zero" }
        return then(PopupWeight(weight.coerceAtMost(Float.MAX_VALUE), fill))
    }

    @Stable
    override fun Modifier.align(alignment: Alignment.Horizontal): Modifier = then(PopupAlignment(alignment))

    @Stable
    override fun Modifier.alignBy(alignmentLine: VerticalAlignmentLine): Modifier = then(PopupAlignment(line = alignmentLine))

    @Stable
    override fun Modifier.alignBy(alignmentLineBlock: (Measured) -> Int): Modifier = then(PopupAlignment(block = alignmentLineBlock))
}

private data class PopupColumnData(
    var weight: Float = 0f,
    var fill: Boolean = true,
    var alignment: Alignment.Horizontal? = null,
    var line: VerticalAlignmentLine? = null,
    var block: ((Measured) -> Int)? = null,
)

@Immutable
private data class PopupWeight(val weight: Float, val fill: Boolean) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any = (parentData as? PopupColumnData ?: PopupColumnData()).also {
        it.weight = weight
        it.fill = fill
    }
}

@Stable
private data class PopupAlignment(
    val alignment: Alignment.Horizontal? = null,
    val line: VerticalAlignmentLine? = null,
    val block: ((Measured) -> Int)? = null,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any = (parentData as? PopupColumnData ?: PopupColumnData()).also {
        it.alignment = alignment
        it.line = line
        it.block = block
    }
}

private object PopupColumnMeasurePolicy : MeasurePolicy {
    override fun MeasureScope.measure(measurables: List<Measurable>, constraints: Constraints): MeasureResult {
        val placeables = arrayOfNulls<Placeable>(measurables.size)
        var height = 0
        var width = 0
        var totalWeight = 0.0
        for (index in measurables.indices) {
            val measurable = measurables[index]
            val weight = (measurable.parentData as? PopupColumnData)?.weight ?: 0f
            if (weight > 0f) {
                totalWeight += weight
            } else {
                val child = measurable.measure(
                    Constraints(
                        maxWidth = constraints.maxWidth,
                        maxHeight = if (constraints.hasBoundedHeight) {
                            (constraints.maxHeight - height).coerceAtLeast(0)
                        } else {
                            Constraints.Infinity
                        },
                    ),
                )
                placeables[index] = child
                height += child.height
                width = maxOf(width, child.width)
            }
        }
        if (totalWeight > 0.0) {
            // As with Column, weights have no available extra height inside verticalScroll.
            val target = if (constraints.hasBoundedHeight) constraints.maxHeight else constraints.minHeight
            var remaining = (target - height).coerceAtLeast(0)
            for (index in measurables.indices) {
                if (placeables[index] != null) continue
                val measurable = measurables[index]
                val data = measurable.parentData as PopupColumnData
                val allocated = (remaining * data.weight / totalWeight).roundToInt().coerceIn(0, remaining)
                remaining -= allocated
                totalWeight -= data.weight
                val child = measurable.measure(
                    Constraints(
                        minHeight = if (data.fill) allocated else 0,
                        maxHeight = allocated,
                        maxWidth = constraints.maxWidth,
                    ),
                )
                placeables[index] = child
                height += child.height
                width = maxOf(width, child.width)
            }
        }
        var beforeLine = 0
        var afterLine = 0
        var lines: IntArray? = null
        for (index in measurables.indices) {
            val data = measurables[index].parentData as? PopupColumnData ?: continue
            val child = placeables[index]!!
            val line = data.line?.let { child[it] } ?: data.block?.invoke(child) ?: continue
            if (line != AlignmentLine.Unspecified) {
                val positions = lines ?: IntArray(measurables.size) { AlignmentLine.Unspecified }.also { lines = it }
                positions[index] = line
                beforeLine = maxOf(beforeLine, line)
                afterLine = maxOf(afterLine, child.width - line)
            }
        }
        width = constraints.constrainWidth(maxOf(width, beforeLine + afterLine))
        return layout(width, constraints.constrainHeight(height)) {
            var y = 0
            for (index in placeables.indices) {
                val child = placeables[index]!!
                val data = measurables[index].parentData as? PopupColumnData
                val line = lines?.get(index) ?: AlignmentLine.Unspecified
                val x = if (line != AlignmentLine.Unspecified) {
                    beforeLine - line
                } else {
                    (data?.alignment ?: Alignment.Start).align(child.width, width, layoutDirection)
                }
                child.place(x, y)
                y += child.height
            }
        }
    }

    override fun IntrinsicMeasureScope.minIntrinsicWidth(measurables: List<IntrinsicMeasurable>, height: Int): Int = probeWidth(measurables, height, minimum = true)

    override fun IntrinsicMeasureScope.maxIntrinsicWidth(measurables: List<IntrinsicMeasurable>, height: Int): Int = probeWidth(measurables, height, minimum = false)

    override fun IntrinsicMeasureScope.minIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int = probeHeight(measurables, width, minimum = true)

    override fun IntrinsicMeasureScope.maxIntrinsicHeight(measurables: List<IntrinsicMeasurable>, width: Int): Int = probeHeight(measurables, width, minimum = false)

    private fun probeWidth(measurables: List<IntrinsicMeasurable>, height: Int, minimum: Boolean): Int {
        var width = 0
        for (index in 0 until minOf(POPUP_PROBE_COUNT, measurables.size)) {
            val child = measurables[index]
            width = maxOf(width, if (minimum) child.minIntrinsicWidth(height) else child.maxIntrinsicWidth(height))
        }
        return width
    }

    private fun probeHeight(measurables: List<IntrinsicMeasurable>, width: Int, minimum: Boolean): Int {
        var height = 0L
        for (index in 0 until minOf(POPUP_PROBE_COUNT, measurables.size)) {
            val child = measurables[index]
            height += if (minimum) child.minIntrinsicHeight(width) else child.maxIntrinsicHeight(width)
        }
        return height.coerceAtMost(Constraints.Infinity.toLong()).toInt()
    }
}
