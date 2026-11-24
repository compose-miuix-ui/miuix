// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp

/**
 * Squircle stroke around this layout, inset by half the stroke width so it lines up with a
 * same-radius [squircleBackground] / [squircleSurface]. Path-based; no shader required, rebuilt
 * only when geometry changes. Falls back to `Modifier.border(...)` with `RoundedCornerShape`
 * whenever [isSquircleEnabled] is `false`, keeping borders aligned with the fill APIs' fallback.
 *
 * @param width The stroke width of the border.
 * @param color The stroke [Color] of the border.
 * @param cornerRadius The radius applied uniformly to all four corners.
 * @param extension The corner-tile size as a multiple of [cornerRadius], clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.squircleBorder(
    width: Dp,
    color: Color,
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    if (!(width.value > 0f) || !color.isSpecified || color.alpha <= 0f) return this
    val radius = sanitizePositiveDp(cornerRadius)
    if (!isSquircleEnabled()) {
        return this.border(width, color, RoundedCornerShape(radius))
    }
    return this then SquircleBorderElement(
        width = width,
        color = color,
        cornerRadius = radius,
        extension = sanitizeExtension(extension),
    )
}

/**
 * Deferred-read variant of [squircleBorder]. [width] and [color] are sampled inside the draw
 * scope, so animating either via `animateDpAsState` / `animateColorAsState` updates the stroke
 * without recomposing this composable or its subtree. Falls back to a stroked rounded-rect path
 * (rather than [Modifier.border]) when [isSquircleEnabled] is `false`.
 *
 * @param width Lambda returning the stroke width, read inside the draw scope each frame.
 * @param color Lambda returning the stroke [Color], read inside the draw scope each frame.
 * @param cornerRadius The radius applied uniformly to all four corners.
 * @param extension The corner-tile size as a multiple of [cornerRadius], clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.squircleBorder(
    width: () -> Dp,
    color: () -> Color,
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier = this then DeferredSquircleBorderElement(
    width = width,
    color = color,
    cornerRadius = sanitizePositiveDp(cornerRadius),
    extension = sanitizeExtension(extension),
    squircleEnabled = isSquircleEnabled(),
)

private data class SquircleBorderElement(
    val width: Dp,
    val color: Color,
    val cornerRadius: Dp,
    val extension: Float,
) : ModifierNodeElement<SquircleBorderNode>() {
    override fun create(): SquircleBorderNode = SquircleBorderNode(
        width = width,
        color = color,
        cornerRadius = cornerRadius,
        extension = extension,
    )

    override fun update(node: SquircleBorderNode) {
        node.updateParams(width, color, cornerRadius, extension)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "squircleBorder"
        properties["width"] = width
        properties["color"] = color
        properties["cornerRadius"] = cornerRadius
        properties["extension"] = extension
    }
}

private class SquircleBorderNode(
    private var width: Dp,
    private var color: Color,
    private var cornerRadius: Dp,
    private var extension: Float,
) : Modifier.Node(),
    DrawModifierNode {

    private val strokeCache = SquircleStrokeCache()

    fun updateParams(
        width: Dp,
        color: Color,
        cornerRadius: Dp,
        extension: Float,
    ) {
        if (this.width != width ||
            this.color != color ||
            this.cornerRadius != cornerRadius ||
            this.extension != extension
        ) {
            this.width = width
            this.color = color
            this.cornerRadius = cornerRadius
            this.extension = extension
            invalidateDraw()
        }
    }

    override fun ContentDrawScope.draw() {
        strokeCache.drawStroke(
            scope = this,
            color = color,
            widthPx = width.toPx(),
            cornerRadiusPx = cornerRadius.toPx(),
            extension = extension,
            squircleEnabled = true,
        )
        drawContent()
    }

    override fun onDetach() {
        strokeCache.clear()
    }
}

private data class DeferredSquircleBorderElement(
    val width: () -> Dp,
    val color: () -> Color,
    val cornerRadius: Dp,
    val extension: Float,
    val squircleEnabled: Boolean,
) : ModifierNodeElement<DeferredSquircleBorderNode>() {
    override fun create(): DeferredSquircleBorderNode = DeferredSquircleBorderNode(
        width = width,
        color = color,
        cornerRadius = cornerRadius,
        extension = extension,
        squircleEnabled = squircleEnabled,
    )

    override fun update(node: DeferredSquircleBorderNode) {
        node.updateParams(width, color, cornerRadius, extension, squircleEnabled)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "squircleBorder"
        properties["cornerRadius"] = cornerRadius
        properties["extension"] = extension
    }
}

private class DeferredSquircleBorderNode(
    private var width: () -> Dp,
    private var color: () -> Color,
    private var cornerRadius: Dp,
    private var extension: Float,
    private var squircleEnabled: Boolean,
) : Modifier.Node(),
    DrawModifierNode {

    private val strokeCache = SquircleStrokeCache()

    fun updateParams(
        width: () -> Dp,
        color: () -> Color,
        cornerRadius: Dp,
        extension: Float,
        squircleEnabled: Boolean,
    ) {
        if (this.width != width ||
            this.color != color ||
            this.cornerRadius != cornerRadius ||
            this.extension != extension ||
            this.squircleEnabled != squircleEnabled
        ) {
            this.width = width
            this.color = color
            this.cornerRadius = cornerRadius
            this.extension = extension
            this.squircleEnabled = squircleEnabled
            invalidateDraw()
        }
    }

    override fun ContentDrawScope.draw() {
        val strokeColor = color()
        if (strokeColor.isSpecified && strokeColor.alpha > 0f) {
            val rawWidth = width()
            if (rawWidth.value > 0f) {
                strokeCache.drawStroke(
                    scope = this,
                    color = strokeColor,
                    widthPx = rawWidth.toPx(),
                    cornerRadiusPx = cornerRadius.toPx(),
                    extension = extension,
                    squircleEnabled = squircleEnabled,
                )
            }
        }
        drawContent()
    }

    override fun onDetach() {
        strokeCache.clear()
    }
}

private class SquircleStrokeCache {
    private var path: Path? = null
    private var cachedStroke: Stroke? = null
    private var lastSize: Size = Size.Unspecified
    private var lastWidthPx: Float = Float.NaN
    private var lastCornerRadiusPx: Float = Float.NaN
    private var lastExtension: Float = Float.NaN
    private var lastSquircleEnabled: Boolean = true

    fun drawStroke(
        scope: ContentDrawScope,
        color: Color,
        widthPx: Float,
        cornerRadiusPx: Float,
        extension: Float,
        squircleEnabled: Boolean,
    ) {
        val size = scope.size
        val innerW = size.width - widthPx
        val innerH = size.height - widthPx
        if (!(widthPx > 0f && innerW > 0f && innerH > 0f)) return
        val halfStroke = widthPx * 0.5f
        val existingStroke = cachedStroke
        val existingPath = path
        val targetPath: Path
        val targetStroke: Stroke
        if (existingStroke != null &&
            existingPath != null &&
            lastSize == size &&
            lastWidthPx == widthPx &&
            lastCornerRadiusPx == cornerRadiusPx &&
            lastExtension == extension &&
            lastSquircleEnabled == squircleEnabled
        ) {
            targetPath = existingPath
            targetStroke = existingStroke
        } else {
            targetPath = (existingPath ?: Path().also { path = it }).apply {
                rewind()
                addSquircleRect(
                    width = innerW,
                    height = innerH,
                    cornerRadius = (cornerRadiusPx - halfStroke).coerceAtLeast(0f),
                    extension = extension,
                    squircleEnabled = squircleEnabled,
                )
            }
            targetStroke = (existingStroke?.takeIf { it.width == widthPx } ?: Stroke(width = widthPx)).also {
                cachedStroke = it
            }
            lastSize = size
            lastWidthPx = widthPx
            lastCornerRadiusPx = cornerRadiusPx
            lastExtension = extension
            lastSquircleEnabled = squircleEnabled
        }
        scope.translate(halfStroke, halfStroke) {
            drawPath(path = targetPath, color = color, style = targetStroke)
        }
    }

    fun clear() {
        path = null
        cachedStroke = null
        lastSize = Size.Unspecified
        lastWidthPx = Float.NaN
    }
}
