// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathSegment
import androidx.compose.ui.util.lerp
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sin

/** Fills a polygon's notches outwards from its visibility kernel into the moving convex outline. */
internal class NavConcavePolygon private constructor(private val center: Offset, private val profile: FloatArray) {
    private var pointsA = FloatArray(0)
    private var pointsB = FloatArray(0)

    companion object {
        fun create(curves: List<NavPathSegment>): NavConcavePolygon? {
            if (curves.any { it.type != PathSegment.Type.Move && it.type != PathSegment.Type.Line }) return null
            val vertices = curves.map { Offset(it.points[it.points.size - 2], it.points[it.points.size - 1]) }.toMutableList()
            if (vertices.size > 1 && vertices.first() == vertices.last()) vertices.removeAt(vertices.lastIndex)
            if (vertices.size < 3) return null
            var area = 0.0
            for (i in vertices.indices) {
                val a = vertices[i]
                val b = vertices[(i + 1) % vertices.size]
                area += a.x.toDouble() * b.y - a.y.toDouble() * b.x
            }
            val winding = if (area > 0.0) 1f else -1f
            val bounds = Rect(vertices.minOf { it.x }, vertices.minOf { it.y }, vertices.maxOf { it.x }, vertices.maxOf { it.y })
            var kernel = listOf(bounds.topLeft, bounds.topRight, bounds.bottomRight, bounds.bottomLeft)
            for (i in vertices.indices) {
                if (kernel.isEmpty()) break
                val a = vertices[i]
                val edge = vertices[(i + 1) % vertices.size] - a
                val clipped = mutableListOf<Offset>()
                var previous = kernel.last()
                var previousDistance = cross(edge, previous - a) * winding
                for (point in kernel) {
                    val distance = cross(edge, point - a) * winding
                    if ((previousDistance >= 0f) != (distance >= 0f)) {
                        val t = previousDistance / (previousDistance - distance)
                        clipped.add(previous + (point - previous) * t)
                    }
                    if (distance >= 0f) clipped.add(point)
                    previous = point
                    previousDistance = distance
                }
                kernel = clipped
            }
            if (kernel.size < 3) return null
            val center = Offset(kernel.sumOf { it.x.toDouble() }.toFloat() / kernel.size, kernel.sumOf { it.y.toDouble() }.toFloat() / kernel.size)
            if (vertices.indices.any { i -> cross(vertices[(i + 1) % vertices.size] - vertices[i], center - vertices[i]) * winding <= 0f }) return null
            // Include every vertex, so a sharp tip is never lost between angular samples.
            val angles = MutableList(RAY_COUNT) { it * 2.0 * PI / RAY_COUNT }
            vertices.forEach { point ->
                val angle = atan2((point.y - center.y).toDouble(), (point.x - center.x).toDouble())
                angles.add(if (angle < 0.0) angle + 2.0 * PI else angle)
            }
            angles.sort()
            val profile = FloatArray(angles.size * 2)
            for (i in angles.indices) {
                val dx = cos(angles[i])
                val dy = sin(angles[i])
                var distance = Double.POSITIVE_INFINITY
                for (j in vertices.indices) {
                    val a = vertices[j] - center
                    val edge = vertices[(j + 1) % vertices.size] - vertices[j]
                    val denominator = dx * edge.y - dy * edge.x
                    if (abs(denominator) <= 1e-12) continue
                    val t = (a.x.toDouble() * edge.y - a.y.toDouble() * edge.x) / denominator
                    val u = (a.x * dy - a.y * dx) / denominator
                    if (t > 0.0 && u >= -1e-6 && u <= 1.0 + 1e-6) distance = min(distance, t)
                }
                if (!distance.isFinite()) return null
                profile[i * 2] = (center.x + dx * distance).toFloat()
                profile[i * 2 + 1] = (center.y + dy * distance).toFloat()
            }
            return NavConcavePolygon(center, profile)
        }

        private fun cross(a: Offset, b: Offset): Float = a.x * b.y - a.y * b.x

        private const val RAY_COUNT = 128
    }

    fun path(sourceSize: Size, sourceBounds: Rect, frameBounds: Rect, normals: FloatArray, limits: FloatArray, fraction: Float): Path {
        val count = profile.size / 2
        val planeCount = limits.size
        val capacity = (count * 3 + planeCount + 4) * 2
        if (pointsA.size < capacity) {
            pointsA = FloatArray(capacity)
            pointsB = FloatArray(capacity)
        }
        val scaleX = sourceBounds.width / sourceSize.width
        val scaleY = sourceBounds.height / sourceSize.height
        val originX = sourceBounds.left - frameBounds.left + center.x * scaleX
        val originY = sourceBounds.top - frameBounds.top + center.y * scaleY
        for (i in 0 until count) {
            val dx = (profile[i * 2] - center.x) * scaleX
            val dy = (profile[i * 2 + 1] - center.y) * scaleY
            var reach = Float.POSITIVE_INFINITY
            for (j in 0 until planeCount) {
                val x = normals[j * 2]
                val y = normals[j * 2 + 1]
                val direction = dx * x + dy * y
                if (direction > 0f) reach = min(reach, (limits[j] - originX * x - originY * y) / direction)
            }
            // Every source edge is subdivided at its vertices. Moving its points only outward
            // keeps the source's fan triangles covered while the notches close.
            val expansion = lerp(1f, max(1f, reach), fraction)
            pointsA[i * 2] = originX + dx * expansion
            pointsA[i * 2 + 1] = originY + dy * expansion
        }
        var input = pointsA
        var output = pointsB
        var visibleCount = count
        for (i in 0 until planeCount) {
            visibleCount = navClipPolygon(input, visibleCount, output, normals[i * 2], normals[i * 2 + 1], limits[i])
            val swap = input
            input = output
            output = swap
        }
        return Path().apply {
            for (i in 0 until visibleCount) {
                if (i == 0) moveTo(input[0], input[1]) else lineTo(input[i * 2], input[i * 2 + 1])
            }
            close()
        }
    }
}
