// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.SpringSpec
import androidx.compose.ui.geometry.Offset
import kotlin.math.abs

/** Unscaled drag-target bounds, in pixels, before the indicator's press transform. */
internal data class NavigationDragTarget(
    val left: Float,
    val right: Float,
    val following: Boolean,
    val movingRight: Boolean,
) {
    val leftSpring: SpringSpec<Float>
        get() = if (following) GlassMotion.navDragFollow() else GlassMotion.edgeSpring(!movingRight)

    val rightSpring: SpringSpec<Float>
        get() = if (following) GlassMotion.navDragFollow() else GlassMotion.edgeSpring(movingRight)
}

/**
 * Computes same-item drag-trail and cross-item edge-spring targets.
 *
 * The chip travels within [minLeft]..[maxRight], which reaches slightly past the destinations so
 * [navigationIndicatorBounds] can squeeze it at either end. The 60px trail cap is intentionally not
 * density-scaled. Raw spring targets may extend beyond the travel; rendered edges are limited.
 * Stretch is disabled when the leading target reaches the travel's boundary.
 */
internal fun navigationDragTarget(
    left: Float,
    width: Float,
    minLeft: Float,
    maxRight: Float,
    delta: Float,
    changedItem: Boolean,
): NavigationDragTarget {
    val start = left.coerceIn(minLeft, (maxRight - width).coerceAtLeast(minLeft))
    val end = start + width
    val trail = if (changedItem) 0f else (abs(delta) * 4f).coerceAtMost(60f)
    return NavigationDragTarget(
        left = if (delta > 0f && end < maxRight) start - trail else start,
        right = if (delta < 0f && start > minLeft) end + trail else end,
        following = !changedItem,
        movingRight = delta > 0f,
    )
}

/** Limits rendered edges to the outermost items without stopping the underlying spring. */
internal fun navigationIndicatorBounds(
    left: Float,
    right: Float,
    containerWidth: Float,
    inset: Float,
    endInset: Float = inset,
): Offset {
    val min = inset.coerceIn(0f, containerWidth.coerceAtLeast(0f) / 2f)
    val max = (containerWidth - endInset.coerceAtLeast(0f)).coerceAtLeast(min)
    val start = left.coerceIn(min, max)
    return Offset(start, right.coerceIn(start, max))
}

/** Converts a fractional destination index into its measured boundary coordinate. */
internal fun navigationSlotPosition(slot: Float, widths: List<Float>): Float {
    val index = slot.toInt().coerceIn(0, widths.lastIndex)
    return widths.take(index).sum() + (slot - index) * widths[index]
}

/** Inverse mapping used by hit testing and drag spring targets for variable-width tabs. */
internal fun navigationSlotAt(position: Float, widths: List<Float>): Float {
    var start = 0f
    widths.forEachIndexed { index, width ->
        if (position < start + width || index == widths.lastIndex) {
            return index + (position - start) / width.coerceAtLeast(1f)
        }
        start += width
    }
    return 0f
}

/** Interpolates the held width between destination centres without a jump at hit boundaries. */
internal fun navigationDragWidth(position: Float, widths: List<Float>): Float {
    if (widths.isEmpty()) return 0f
    var centre = widths.first() / 2f
    if (position <= centre) return widths.first()
    for (index in 1..widths.lastIndex) {
        val nextCentre = centre + (widths[index - 1] + widths[index]) / 2f
        if (position <= nextCentre) {
            val fraction = ((position - centre) / (nextCentre - centre).coerceAtLeast(1f)).coerceIn(0f, 1f)
            return widths[index - 1] + (widths[index] - widths[index - 1]) * fraction
        }
        centre = nextCentre
    }
    return widths.last()
}
