// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

/**
 * Caches [Shape] outlines. [shapeBlock] is mutable so a single provider survives modifier
 * updates; the outline cache is keyed on the resolved [Shape] instance.
 */
@Stable
internal class ShapeProvider(var shapeBlock: () -> Shape) {

    private var cachedShape: Shape? = null
    private var cachedOutline: Outline? = null
    private var cachedSize: Size = Size.Unspecified
    private var cachedLayoutDirection: LayoutDirection? = null
    private var cachedDensity: Float = Float.NaN
    private var cachedTopStart: Float = Float.NaN
    private var cachedTopEnd: Float = Float.NaN
    private var cachedBottomEnd: Float = Float.NaN
    private var cachedBottomStart: Float = Float.NaN

    val innerShape: Shape
        get() = shapeBlock()

    val shape = object : Shape {

        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density,
        ): Outline {
            val shape = shapeBlock()
            var cornerChanged = false
            var ts = Float.NaN
            var te = Float.NaN
            var be = Float.NaN
            var bs = Float.NaN
            if (shape is CornerBasedShape) {
                ts = shape.topStart.toPx(size, density)
                te = shape.topEnd.toPx(size, density)
                be = shape.bottomEnd.toPx(size, density)
                bs = shape.bottomStart.toPx(size, density)
                cornerChanged = ts != cachedTopStart ||
                    te != cachedTopEnd ||
                    be != cachedBottomEnd ||
                    bs != cachedBottomStart
            }
            if (cachedShape != shape) {
                cachedShape = shape
                cachedOutline = null
            }
            if (
                cachedOutline == null ||
                cachedSize != size ||
                cachedLayoutDirection != layoutDirection ||
                cachedDensity != density.density ||
                cornerChanged
            ) {
                cachedSize = size
                cachedLayoutDirection = layoutDirection
                cachedDensity = density.density
                cachedTopStart = ts
                cachedTopEnd = te
                cachedBottomEnd = be
                cachedBottomStart = bs
                cachedOutline = shape.createOutline(size, layoutDirection, density)
            }
            return cachedOutline!!
        }
    }
}
