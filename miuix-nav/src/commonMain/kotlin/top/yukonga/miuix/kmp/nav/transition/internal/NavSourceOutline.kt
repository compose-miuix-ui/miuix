// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathHitTester
import androidx.compose.ui.graphics.PathIterator
import androidx.compose.ui.graphics.PathSegment
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

/** Cached source geometry shared by capture validation, outline morphing and hit testing. */
internal class NavSourceOutline {
    private var cachedShape: Shape? = null
    private var cachedSize = Size.Zero
    private var cachedDirection = LayoutDirection.Ltr
    private var cachedDensity = 0f
    private var cachedFontScale = 0f
    private var supported = false
    private var hitTester: PathHitTester? = null
    private var hitVersion = -1

    var path = Path()
        private set
    var curves = emptyList<PathSegment>()
        private set
    var concavePolygon: NavConcavePolygon? = null
        private set
    var version = 0
        private set

    fun update(shape: Shape, size: Size, direction: LayoutDirection, density: Density): Boolean {
        if (cachedShape == shape && cachedSize == size && cachedDirection == direction && cachedDensity == density.density && cachedFontScale == density.fontScale) return supported
        cachedShape = shape
        cachedSize = size
        cachedDirection = direction
        cachedDensity = density.density
        cachedFontScale = density.fontScale
        supported = false
        concavePolygon = null
        curves = emptyList()
        version++
        if (!size.width.isFinite() || !size.height.isFinite() || size.width <= 0f || size.height <= 0f) return false
        path = when (val outline = shape.createOutline(size, direction, density)) {
            is Outline.Rectangle -> Path().apply { addRect(outline.rect) }

            is Outline.Rounded -> Path().apply { addRoundRect(outline.roundRect) }

            is Outline.Generic -> Path().apply {
                fillType = outline.path.fillType
                addPath(outline.path)
            }
        }
        if (path.isEmpty) return false
        curves = segments(path) ?: return false
        if (!path.isConvex) concavePolygon = NavConcavePolygon.create(curves) ?: return false
        supported = true
        return true
    }

    fun contains(position: Offset): Boolean {
        if (!supported) return false
        val tester = hitTester ?: PathHitTester().also { hitTester = it }
        if (hitVersion != version) {
            tester.updatePath(path)
            hitVersion = version
        }
        return position in tester
    }

    private fun segments(path: Path): List<PathSegment>? {
        val segments = mutableListOf<PathSegment>()
        val iterator = PathIterator(path, PathIterator.ConicEvaluation.AsConic)
        var contours = 0
        while (iterator.hasNext()) {
            val segment = iterator.next()
            if (segment.type == PathSegment.Type.Move && ++contours > 1) return null
            if (segment.points.any { !it.isFinite() }) return null
            if (segment.type == PathSegment.Type.Conic && (!segment.weight.isFinite() || segment.weight <= 0f)) return null
            if (segment.points.isNotEmpty()) segments.add(segment)
        }
        return segments.takeIf { contours == 1 }
    }
}
