// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.liquid

// Adapted from Kyant0/AndroidLiquidGlass — https://github.com/Kyant0/AndroidLiquidGlass (Apache 2.0).

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.layer.CompositingStrategy
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.node.requireGraphicsContext
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

/**
 * Configuration for an inner shadow effect rendered inside a clipped shape.
 *
 * An inner shadow simulates a light source casting shadow inward from the edges
 * of a shape. It uses offscreen compositing with a blur render effect to produce
 * a soft, physically-plausible shadow that respects the shape's outline.
 *
 * The shadow is rendered by:
 * 1. Drawing the shape filled with [color] into an offscreen graphics layer.
 * 2. Overlaying a "shadow mask" using [BlendMode.Clear] offset by [offset], which
 *    erases the interior and leaves only the edge fringe.
 * 3. Applying a [BlurEffect] with the given [radius] to soften the fringe.
 * 4. Compositing the result inside the original shape clip.
 *
 * @property radius Blur radius for the shadow softness. Larger values produce a wider,
 *   more diffused shadow. `0.dp` renders a crisp, unblurred shadow edge.
 *   Default: `24.dp`.
 * @property offset Directional offset of the shadow from the shape's edge, in [DpOffset].
 *   Positive `y` values cast the shadow downward (simulating a top-down light).
 *   Default: `DpOffset(0.dp, radius)` — directly below, scaled to the blur radius.
 * @property color Fill color of the shadow. Alpha controls the shadow's base opacity
 *   before the [alpha] multiplier is applied.
 *   Default: `Color.Black.copy(alpha = 0.15f)`.
 * @property alpha Overall opacity multiplier for the entire shadow layer, in range `[0, 1]`.
 *   `0f` makes the shadow fully transparent; `1f` is fully opaque.
 *   Default: `1f`.
 * @property blendMode Compositing mode used when drawing the shadow layer over the content.
 *   Default: [DrawScope.DefaultBlendMode] (platform default, typically SrcOver).
 */
@Immutable
data class InnerShadow(
    val radius: Dp = 24.dp,
    val offset: DpOffset = DpOffset(0.dp, radius),
    val color: Color = Color.Black.copy(alpha = 0.15f),
    val alpha: Float = 1f,
    val blendMode: BlendMode = DrawScope.DefaultBlendMode,
) {
    companion object {
        /** A default [InnerShadow] instance with standard parameters. */
        @Stable
        val Default: InnerShadow = InnerShadow()
    }
}

/**
 * Applies an inner shadow effect to this [Modifier], rendered inside the given [shape].
 *
 * The inner shadow is drawn **after** the content (`drawContent()`), overlaying the
 * shadow within the shape's clipped region. The [shadow] lambda is called on every
 * draw frame, allowing reactive shadow parameters (e.g., animating [InnerShadow.alpha]
 * with press progress).
 *
 * ### Usage
 *
 * ```kotlin
 * Box(
 *     modifier = Modifier
 *         .size(200.dp, 100.dp)
 *         .innerShadow(shape = RoundedCornerShape(16.dp)) {
 *             InnerShadow(
 *                 radius = 12.dp,
 *                 color = Color.Black.copy(alpha = 0.2f),
 *                 alpha = pressProgress,
 *             )
 *         }
 * )
 * ```
 *
 * ### Performance Notes
 *
 * - The blur render effect is cached and only re-created when [InnerShadow.radius] changes.
 * - A [GraphicsLayer] is allocated on attach and released on detach to prevent leaks.
 * - When [shadow] returns `null`, the effect is skipped entirely (zero cost).
 *
 * @param shape The [Shape] defining the clipping region for the shadow. Must match the
 *   component's visual shape for correct edge alignment.
 * @param shadow A lambda returning the current [InnerShadow] configuration, or `null`
 *   to skip rendering. Called once per draw frame.
 */
fun Modifier.innerShadow(
    shape: Shape,
    shadow: () -> InnerShadow?,
): Modifier = this then InnerShadowElement(shape, shadow)

/**
 * [ModifierNodeElement] that creates and manages [InnerShadowNode] instances.
 *
 * Uses structural equality on [shape] and [shadow] to detect parameter changes.
 */
private class InnerShadowElement(
    val shape: Shape,
    val shadow: () -> InnerShadow?,
) : ModifierNodeElement<InnerShadowNode>() {

    override fun create(): InnerShadowNode = InnerShadowNode(shape, shadow)

    override fun update(node: InnerShadowNode) {
        node.shape = shape
        node.shadow = shadow
        node.invalidateDraw()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "innerShadow"
        properties["shape"] = shape
        properties["shadow"] = shadow
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is InnerShadowElement) return false
        if (shape != other.shape) return false
        if (shadow != other.shadow) return false
        return true
    }

    override fun hashCode(): Int {
        var result = shape.hashCode()
        result = 31 * result + shadow.hashCode()
        return result
    }
}

/**
 * [DrawModifierNode] that renders the inner shadow effect using offscreen compositing.
 *
 * ### Rendering Pipeline
 *
 * 1. `drawContent()` — renders the component's own content first.
 * 2. Creates an outline from the current [shape] and builds a clip [Path].
 * 3. Records into an offscreen [GraphicsLayer]:
 *    - Clips to the shape outline.
 *    - Draws the outline filled with [InnerShadow.color].
 *    - Translates by the shadow offset and draws the outline again with
 *      [ShadowMaskPaint] ([BlendMode.Clear]), erasing the offset region.
 * 4. Applies a [BlurEffect] to the layer (cached by radius).
 * 5. Draws the composited layer clipped to the original shape.
 *
 * ### Lifecycle
 * - [onAttach] allocates the offscreen [GraphicsLayer] with [CompositingStrategy.Offscreen].
 * - [onDetach] releases the layer back to the graphics context.
 */
private class InnerShadowNode(
    var shape: Shape,
    var shadow: () -> InnerShadow?,
) : Modifier.Node(),
    DrawModifierNode {

    override val shouldAutoInvalidate: Boolean = false

    private var shadowLayer: GraphicsLayer? = null
    private val paint = Paint()
    private val clipPath = Path()
    private var prevRadius = Float.NaN

    override fun ContentDrawScope.draw() {
        drawContent()

        val shadow = shadow() ?: return
        val layer = shadowLayer ?: return

        val radius = shadow.radius.toPx()
        val offsetX = shadow.offset.x.toPx()
        val offsetY = shadow.offset.y.toPx()

        val outline = shape.createOutline(size, layoutDirection, this)
        clipPath.reset()
        when (outline) {
            is Outline.Rectangle -> clipPath.addRect(outline.rect)
            is Outline.Rounded -> clipPath.addRoundRect(outline.roundRect)
            is Outline.Generic -> clipPath.addPath(outline.path)
        }

        paint.color = shadow.color
        layer.alpha = shadow.alpha
        layer.blendMode = shadow.blendMode
        if (prevRadius != radius) {
            layer.renderEffect = if (radius > 0f) BlurEffect(radius, radius, TileMode.Decal) else null
            prevRadius = radius
        }

        layer.record {
            drawContext.canvas.let { canvas ->
                canvas.save()
                canvas.clipPath(clipPath)
                canvas.drawOutline(outline, paint)
                canvas.translate(offsetX, offsetY)
                canvas.drawOutline(outline, ShadowMaskPaint)
                canvas.translate(-offsetX, -offsetY)
                canvas.restore()
            }
        }

        drawContext.canvas.let { canvas ->
            canvas.save()
            canvas.clipPath(clipPath)
            drawLayer(layer)
            canvas.restore()
        }
    }

    override fun onAttach() {
        shadowLayer = requireGraphicsContext().createGraphicsLayer().apply {
            compositingStrategy = CompositingStrategy.Offscreen
        }
    }

    override fun onDetach() {
        shadowLayer?.let { layer ->
            requireGraphicsContext().releaseGraphicsLayer(layer)
            shadowLayer = null
        }
    }
}

/** Paint configured with [BlendMode.Clear] to erase regions when drawing the shadow mask. */
private val ShadowMaskPaint: Paint = Paint().apply {
    blendMode = BlendMode.Clear
}
