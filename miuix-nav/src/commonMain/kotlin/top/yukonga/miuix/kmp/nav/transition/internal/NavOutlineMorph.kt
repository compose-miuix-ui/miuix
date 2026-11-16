// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathIterator
import androidx.compose.ui.graphics.PathSegment
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.util.lerp
import top.yukonga.miuix.kmp.nav.transition.NavTransitionCorners
import top.yukonga.miuix.kmp.squircle.SquircleDefaults
import top.yukonga.miuix.kmp.squircle.addSquircleRect
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

/** Default outline adapter, shared by all component families. */
@Immutable
internal data class NavCornerShape(val corners: NavTransitionCorners, val squircleEnabled: Boolean) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline = Outline.Generic(
        Path().apply {
            with(density) {
                addSquircleRect(size.width, size.height, corners.topLeft.toPx(), corners.topRight.toPx(), corners.bottomRight.toPx(), corners.bottomLeft.toPx(), squircleEnabled = squircleEnabled)
            }
        },
    )
}

internal data class NavExpandShape(
    val morph: NavOutlineMorph,
    val frame: NavExpandFrame,
    val sourceShape: Shape?,
    val sourceSize: Size,
    val sourceBounds: Rect?,
    val squircleEnabled: Boolean,
) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline = morph.outline(
        sourceShape,
        sourceSize,
        sourceBounds,
        frame,
        squircleEnabled,
        layoutDirection,
        density,
    )
}

/**
 * Morph the source into one rounded rectangle. A single corner scale keeps the whole outline
 * outside the source; fitting each direction separately would flatten and stretch individual
 * corners. Curves supply exact extrema instead of sampled chords that can miss square corners.
 */
internal class NavOutlineMorph {
    private val sourceOutline = NavSourceOutline()
    private val sourceCurves get() = sourceOutline.curves
    private var cachedSquircle: Boolean? = null
    private var cachedProjection = Size.Zero
    private var cachedSourceClip = Rect.Zero
    private var sourceClipped = false
    private var coverageCount = 0
    private val pageGaps = FloatArray(PLANE_COUNT)
    private val outlineGaps = FloatArray(PLANE_COUNT)
    private val outlineLimits = FloatArray(PLANE_COUNT)
    private val sourceSupports = FloatArray(PLANE_COUNT)
    private val coverageSupports = FloatArray(PLANE_COUNT)
    private val coveragePoints = FloatArray((PLANE_COUNT + 4) * 2)
    private val pointsA = FloatArray((PLANE_COUNT + 4) * 2)
    private val pointsB = FloatArray((PLANE_COUNT + 4) * 2)

    fun outline(
        source: Shape?,
        sourceSize: Size,
        sourceBounds: Rect?,
        frame: NavExpandFrame,
        squircleEnabled: Boolean,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline = Outline.Generic(
        path(source, sourceSize, sourceBounds, frame, squircleEnabled, layoutDirection, density).apply {
            transform(Matrix().apply { scale(1f / frame.scale, 1f / frame.scale) })
        },
    )

    private fun path(
        source: Shape?,
        sourceSize: Size,
        sourceBounds: Rect?,
        frame: NavExpandFrame,
        squircleEnabled: Boolean,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Path {
        // Work in host pixels so a changing page scale does not invalidate source geometry.
        val revealSize = frame.bounds.size
        val fraction = frame.sourceFraction
        val radius = if (fraction < 1f) frame.movingRadius else frame.cornerRadius
        if (source == null || sourceBounds == null || sourceSize.width <= 0f || sourceSize.height <= 0f) {
            return Path().apply { addSquircleRect(revealSize.width, revealSize.height, radius, squircleEnabled = squircleEnabled) }
        }
        val previousVersion = sourceOutline.version
        if (!sourceOutline.update(source, sourceSize, layoutDirection, density)) {
            return Path().apply { addSquircleRect(revealSize.width, revealSize.height, radius, squircleEnabled = squircleEnabled) }
        }
        if (sourceOutline.version != previousVersion) cachedProjection = Size.Zero
        if (fraction <= 0f) {
            return Path().apply {
                addPath(sourceOutline.path)
                transform(
                    Matrix().apply {
                        translate(sourceBounds.left - frame.bounds.left, sourceBounds.top - frame.bounds.top)
                        scale(sourceBounds.width / sourceSize.width, sourceBounds.height / sourceSize.height)
                    },
                )
            }
        }
        if (cachedSquircle != squircleEnabled) {
            val side = 2f * if (squircleEnabled) SquircleDefaults.Extension else 1f
            val cornerCurves = curves(Path().apply { addSquircleRect(side, side, 1f, squircleEnabled = squircleEnabled) })
            for (i in 0 until PLANE_COUNT) {
                val x = NORMALS[i * 2]
                val y = NORMALS[i * 2 + 1]
                pageGaps[i] = max(0f, max(x, 0f) * side + max(y, 0f) * side - support(cornerCurves, x, y))
            }
            cachedSquircle = squircleEnabled
        }
        val sourceClip = Rect(
            frame.origin.left - sourceBounds.left,
            frame.origin.top - sourceBounds.top,
            frame.origin.right - sourceBounds.left,
            frame.origin.bottom - sourceBounds.top,
        )
        updateCoverage(sourceSize, sourceBounds.size, sourceClip)
        if (sourceClipped && coverageCount == 0) {
            return Path().apply { addSquircleRect(revealSize.width, revealSize.height, radius, squircleEnabled = squircleEnabled) }
        }
        val revealScaleX = revealSize.width / sourceSize.width
        val revealScaleY = revealSize.height / sourceSize.height
        val extension = if (squircleEnabled) SquircleDefaults.Extension else 1f
        val targetRadius = min(radius, min(revealSize.width, revealSize.height) / (2f * extension))
        var cornerScale = 1f
        for (i in 0 until PLANE_COUNT) {
            val x = NORMALS[i * 2]
            val y = NORMALS[i * 2 + 1]
            val pageBox = max(x, 0f) * revealSize.width + max(y, 0f) * revealSize.height
            val sourceLimit = x * (sourceBounds.left - frame.bounds.left) + y * (sourceBounds.top - frame.bounds.top) + coverageSupports[i]
            val sourceGap = if (fraction < 1f) {
                val sourceSupport = if (sourceClipped) {
                    val projectedX = x * revealSize.width / sourceClip.width
                    val projectedY = y * revealSize.height / sourceClip.height
                    support(coveragePoints, coverageCount, projectedX, projectedY) - projectedX * sourceClip.left - projectedY * sourceClip.top
                } else {
                    support(sourceCurves, x * revealScaleX, y * revealScaleY)
                }
                max(0f, pageBox - sourceSupport)
            } else {
                0f
            }
            val gap = lerp(sourceGap, pageGaps[i] * targetRadius, fraction)
            outlineGaps[i] = gap
            if (gap > 0f) {
                // One host pixel protects the source's antialiased edge.
                val clearance = max(0f, pageBox - sourceLimit - fraction)
                cornerScale = min(cornerScale, clearance / gap)
            }
        }
        val fillFraction = navExpandFraction(frame.progress, 0f, 0.18f)
        val fillingPolygon = sourceOutline.concavePolygon?.takeIf { fillFraction < 1f }
        if (fraction >= 1f && fillingPolygon == null) {
            return Path().apply { addSquircleRect(revealSize.width, revealSize.height, targetRadius * cornerScale, squircleEnabled = squircleEnabled) }
        }
        for (i in 0 until PLANE_COUNT) {
            val x = NORMALS[i * 2]
            val y = NORMALS[i * 2 + 1]
            outlineLimits[i] = max(x, 0f) * revealSize.width + max(y, 0f) * revealSize.height - outlineGaps[i] * cornerScale
        }
        if (fillingPolygon != null) {
            return fillingPolygon.path(sourceSize, sourceBounds, frame.bounds, NORMALS, outlineLimits, fillFraction)
        }
        var input = pointsA
        var output = pointsB
        rectangle(input, Rect(0f, 0f, revealSize.width, revealSize.height))
        var count = 4
        for (i in 0 until PLANE_COUNT) {
            val x = NORMALS[i * 2]
            val y = NORMALS[i * 2 + 1]
            count = navClipPolygon(input, count, output, x, y, outlineLimits[i])
            val swap = input
            input = output
            output = swap
        }
        return Path().apply {
            for (i in 0 until count) {
                if (i == 0) moveTo(input[0], input[1]) else lineTo(input[i * 2], input[i * 2 + 1])
            }
            close()
        }
    }

    private fun updateCoverage(sourceSize: Size, projection: Size, clip: Rect) {
        val projectionChanged = cachedProjection != projection
        if (!projectionChanged && cachedSourceClip == clip) return
        if (projectionChanged) {
            val scaleX = projection.width / sourceSize.width
            val scaleY = projection.height / sourceSize.height
            for (i in 0 until PLANE_COUNT) {
                sourceSupports[i] = support(sourceCurves, NORMALS[i * 2] * scaleX, NORMALS[i * 2 + 1] * scaleY)
            }
        }
        sourceClipped = clip != Rect(0f, 0f, projection.width, projection.height)
        if (sourceClipped) {
            // Intersect supporting planes with the visible rectangle, avoiding native Path ops.
            // The polygon encloses the visible curve while excluding off-host geometry.
            var input = pointsA
            var output = pointsB
            rectangle(input, clip)
            var count = 4
            for (i in 0 until PLANE_COUNT) {
                count = navClipPolygon(input, count, output, NORMALS[i * 2], NORMALS[i * 2 + 1], sourceSupports[i])
                val swap = input
                input = output
                output = swap
            }
            input.copyInto(coveragePoints, endIndex = count * 2)
            coverageCount = count
            for (i in 0 until PLANE_COUNT) {
                coverageSupports[i] = support(coveragePoints, count, NORMALS[i * 2], NORMALS[i * 2 + 1])
            }
        } else {
            sourceSupports.copyInto(coverageSupports)
        }
        cachedProjection = projection
        cachedSourceClip = clip
    }

    private fun rectangle(points: FloatArray, bounds: Rect) {
        points[0] = bounds.left
        points[1] = bounds.top
        points[2] = bounds.right
        points[3] = bounds.top
        points[4] = bounds.right
        points[5] = bounds.bottom
        points[6] = bounds.left
        points[7] = bounds.bottom
    }

    private fun support(points: FloatArray, count: Int, x: Float, y: Float): Float {
        var result = Float.NEGATIVE_INFINITY
        for (i in 0 until count) result = max(result, points[i * 2] * x + points[i * 2 + 1] * y)
        return result
    }

    private fun curves(path: Path): List<PathSegment> {
        val result = mutableListOf<PathSegment>()
        val iterator = PathIterator(path, PathIterator.ConicEvaluation.AsConic)
        var contours = 0
        while (iterator.hasNext()) {
            val segment = iterator.next()
            if (segment.type == PathSegment.Type.Move) contours++
            if (segment.points.isNotEmpty()) result.add(segment)
        }
        require(contours <= 1) { "Expansion Shape must have one contour without holes" }
        return result
    }

    private fun support(curves: List<PathSegment>, x: Float, y: Float): Float {
        var result = Double.NEGATIVE_INFINITY
        for (index in curves.indices) {
            val curve = curves[index]
            val points = curve.points
            val p0 = points[0].toDouble() * x + points[1].toDouble() * y
            var maximum = p0
            if (points.size >= 4) maximum = max(maximum, points[points.size - 2].toDouble() * x + points[points.size - 1].toDouble() * y)
            when (curve.type) {
                PathSegment.Type.Quadratic, PathSegment.Type.Conic -> {
                    val p1 = points[2].toDouble() * x + points[3].toDouble() * y
                    val p2 = points[4].toDouble() * x + points[5].toDouble() * y
                    val weight = if (curve.type == PathSegment.Type.Conic) curve.weight.toDouble() else 1.0
                    val n1 = 2.0 * (weight * p1 - p0)
                    val n2 = p0 - 2.0 * weight * p1 + p2
                    val d1 = 2.0 * (weight - 1.0)
                    val d2 = -d1
                    maximum = extrema(n2 * d1 - n1 * d2, 2.0 * (n2 - p0 * d2), n1 - p0 * d1, maximum) { t ->
                        (p0 + t * (n1 + t * n2)) / (1.0 + t * (d1 + t * d2))
                    }
                }

                PathSegment.Type.Cubic -> {
                    val p1 = points[2].toDouble() * x + points[3].toDouble() * y
                    val p2 = points[4].toDouble() * x + points[5].toDouble() * y
                    val p3 = points[6].toDouble() * x + points[7].toDouble() * y
                    maximum = extrema(-p0 + 3.0 * p1 - 3.0 * p2 + p3, 2.0 * (p0 - 2.0 * p1 + p2), p1 - p0, maximum) { t ->
                        val a = p0 + (p1 - p0) * t
                        val b = p1 + (p2 - p1) * t
                        val c = p2 + (p3 - p2) * t
                        val d = a + (b - a) * t
                        d + (b + (c - b) * t - d) * t
                    }
                }

                else -> Unit
            }
            result = max(result, maximum)
        }
        return result.toFloat()
    }

    private inline fun extrema(a: Double, b: Double, c: Double, initial: Double, value: (Double) -> Double): Double {
        var result = initial
        if (a == 0.0) {
            if (b != 0.0) {
                val t = -c / b
                if (t > 0.0 && t < 1.0) result = max(result, value(t))
            }
        } else {
            val discriminant = b * b - 4.0 * a * c
            if (discriminant >= 0.0) {
                val root = sqrt(discriminant)
                val q = -0.5 * (b + if (b >= 0.0) root else -root)
                val first = q / a
                if (first > 0.0 && first < 1.0) result = max(result, value(first))
                if (q != 0.0) {
                    val second = c / q
                    if (second > 0.0 && second < 1.0) result = max(result, value(second))
                }
            }
        }
        return result
    }

    private companion object {
        const val PLANE_COUNT = 128
        val NORMALS = FloatArray(PLANE_COUNT * 2) { index ->
            val angle = 2.0 * PI * (index / 2) / PLANE_COUNT
            (if (index % 2 == 0) cos(angle) else sin(angle)).toFloat()
        }
    }
}

internal fun navClipPolygon(input: FloatArray, count: Int, output: FloatArray, x: Float, y: Float, limit: Float): Int {
    if (count == 0) return 0
    var written = 0
    var previousX = input[(count - 1) * 2]
    var previousY = input[(count - 1) * 2 + 1]
    var previousDistance = previousX * x + previousY * y - limit
    for (i in 0 until count) {
        val currentX = input[i * 2]
        val currentY = input[i * 2 + 1]
        val distance = currentX * x + currentY * y - limit
        if ((previousDistance <= 0f) != (distance <= 0f)) {
            val t = previousDistance / (previousDistance - distance)
            output[written++] = previousX + (currentX - previousX) * t
            output[written++] = previousY + (currentY - previousY) * t
        }
        if (distance <= 0f) {
            output[written++] = currentX
            output[written++] = currentY
        }
        previousX = currentX
        previousY = currentY
        previousDistance = distance
    }
    return written / 2
}
