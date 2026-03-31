// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.GlobalPositionAwareModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ObserverModifierNode
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateMeasurement
import androidx.compose.ui.node.observeReads
import androidx.compose.ui.node.requireGraphicsContext
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import top.yukonga.miuix.kmp.blur.internal.DOWNSAMPLE_2X_SHADER
import top.yukonga.miuix.kmp.blur.internal.ShapeProvider
import top.yukonga.miuix.kmp.blur.internal.recordLayer
import top.yukonga.miuix.kmp.blur.internal.runtimeShaderEffect

private val DefaultOnDrawBackdrop: DrawScope.(DrawScope.() -> Unit) -> Unit = { it() }

/**
 * Applies a backdrop effect to this composable.
 */
fun Modifier.drawBackdrop(
    backdrop: Backdrop,
    shape: () -> Shape,
    effects: BackdropEffectScope.() -> Unit,
    layerBlock: (GraphicsLayerScope.() -> Unit)? = null,
    onDrawBehind: (DrawScope.() -> Unit)? = null,
    onDrawBackdrop: DrawScope.(drawBackdrop: DrawScope.() -> Unit) -> Unit = DefaultOnDrawBackdrop,
    onDrawSurface: (DrawScope.() -> Unit)? = null,
    onDrawFront: (DrawScope.() -> Unit)? = null,
    contentBlendMode: BlendMode? = null,
    enabled: Boolean = true,
): Modifier {
    val shapeProvider = ShapeProvider(shape)
    return this
        .then(
            if (layerBlock != null) Modifier.graphicsLayer(layerBlock) else Modifier,
        )
        .then(
            DrawBackdropElement(
                backdrop = backdrop,
                shapeProvider = shapeProvider,
                effects = effects,
                layerBlock = layerBlock,
                onDrawBehind = onDrawBehind,
                onDrawBackdrop = onDrawBackdrop,
                onDrawSurface = onDrawSurface,
                onDrawFront = onDrawFront,
                contentBlendMode = contentBlendMode,
                enabled = enabled,
            ),
        )
}

private class DrawBackdropElement(
    val backdrop: Backdrop,
    val shapeProvider: ShapeProvider,
    val effects: BackdropEffectScope.() -> Unit,
    val layerBlock: (GraphicsLayerScope.() -> Unit)?,
    val onDrawBehind: (DrawScope.() -> Unit)?,
    val onDrawBackdrop: DrawScope.(drawBackdrop: DrawScope.() -> Unit) -> Unit,
    val onDrawSurface: (DrawScope.() -> Unit)?,
    val onDrawFront: (DrawScope.() -> Unit)?,
    val contentBlendMode: BlendMode? = null,
    val enabled: Boolean = true,
) : ModifierNodeElement<DrawBackdropNode>() {

    override fun create(): DrawBackdropNode = DrawBackdropNode(
        backdrop = backdrop,
        shapeProvider = shapeProvider,
        effects = effects,
        layerBlock = layerBlock,
        onDrawBehind = onDrawBehind,
        onDrawBackdrop = onDrawBackdrop,
        onDrawSurface = onDrawSurface,
        onDrawFront = onDrawFront,
        contentBlendMode = contentBlendMode,
        enabled = enabled,
    )

    override fun update(node: DrawBackdropNode) {
        val enabledChanged = node.enabled != enabled
        node.backdrop = backdrop
        node.shapeProvider = shapeProvider
        node.effects = effects
        node.layerBlock = layerBlock
        node.onDrawBehind = onDrawBehind
        node.onDrawBackdrop = onDrawBackdrop
        node.onDrawSurface = onDrawSurface
        node.onDrawFront = onDrawFront
        node.contentBlendMode = contentBlendMode
        node.enabled = enabled
        if (enabledChanged) {
            if (!enabled) {
                node.releaseGraphicsLayers()
            }
            node.invalidateMeasurement()
        }
        node.invalidateDrawCache()
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "drawBackdrop"
        properties["backdrop"] = backdrop
        properties["enabled"] = enabled
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DrawBackdropElement) return false
        if (backdrop != other.backdrop) return false
        if (shapeProvider != other.shapeProvider) return false
        if (effects != other.effects) return false
        if (layerBlock != other.layerBlock) return false
        if (onDrawBehind != other.onDrawBehind) return false
        if (onDrawBackdrop != other.onDrawBackdrop) return false
        if (onDrawSurface != other.onDrawSurface) return false
        if (onDrawFront != other.onDrawFront) return false
        if (contentBlendMode != other.contentBlendMode) return false
        if (enabled != other.enabled) return false
        return true
    }

    override fun hashCode(): Int {
        var result = backdrop.hashCode()
        result = 31 * result + shapeProvider.hashCode()
        result = 31 * result + effects.hashCode()
        result = 31 * result + (layerBlock?.hashCode() ?: 0)
        result = 31 * result + (onDrawBehind?.hashCode() ?: 0)
        result = 31 * result + onDrawBackdrop.hashCode()
        result = 31 * result + (onDrawSurface?.hashCode() ?: 0)
        result = 31 * result + (onDrawFront?.hashCode() ?: 0)
        result = 31 * result + (contentBlendMode?.hashCode() ?: 0)
        result = 31 * result + enabled.hashCode()
        return result
    }
}

private class DrawBackdropNode(
    var backdrop: Backdrop,
    var shapeProvider: ShapeProvider,
    var effects: BackdropEffectScope.() -> Unit,
    var layerBlock: (GraphicsLayerScope.() -> Unit)?,
    var onDrawBehind: (DrawScope.() -> Unit)?,
    var onDrawBackdrop: DrawScope.(drawBackdrop: DrawScope.() -> Unit) -> Unit,
    var onDrawSurface: (DrawScope.() -> Unit)?,
    var onDrawFront: (DrawScope.() -> Unit)?,
    var contentBlendMode: BlendMode? = null,
    var enabled: Boolean = true,
) : Modifier.Node(),
    LayoutModifierNode,
    DrawModifierNode,
    GlobalPositionAwareModifierNode,
    ObserverModifierNode,
    CompositionLocalConsumerModifierNode {

    private val effectScope = object : BackdropEffectScopeImpl() {
        override val shape: Shape get() = shapeProvider.innerShape
    }

    private var graphicsLayer: GraphicsLayer? = null
    private var cascadeLayer: GraphicsLayer? = null

    private val layoutLayerBlock: GraphicsLayerScope.() -> Unit = {
        clip = true
        shape = shapeProvider.shape
        compositingStrategy = CompositingStrategy.Offscreen
    }

    private var layoutCoordinates: LayoutCoordinates? by mutableStateOf(null, neverEqualPolicy())

    private var padding by mutableFloatStateOf(0f)
    private var downscaleFactor: Int = 1

    private val recordBackdropBlock: (DrawScope.() -> Unit) = {
        val currentPadding = padding
        val scaleFactor = cascadeFirstStepScale
        if (currentPadding != 0f) {
            val scaledPadding = if (scaleFactor > 1) (currentPadding / scaleFactor).toInt().toFloat() else currentPadding
            drawContext.canvas.translate(scaledPadding, scaledPadding)
        }
        onDrawBackdrop {
            with(backdrop) {
                drawBackdrop(
                    density = effectScope,
                    coordinates = layoutCoordinates,
                    layerBlock = layerBlock,
                    downscaleFactor = scaleFactor,
                )
            }
        }
        if (currentPadding != 0f) {
            val scaledPadding = if (scaleFactor > 1) (currentPadding / scaleFactor).toInt().toFloat() else currentPadding
            drawContext.canvas.translate(-scaledPadding, -scaledPadding)
        }
    }

    /** First step downscale for cascade (backdrop draw uses this, not the total factor). */
    private var cascadeFirstStepScale: Int = 1

    private val drawBackdropLayer: DrawScope.() -> Unit = {
        val layer = graphicsLayer
        if (layer != null) {
            val currentPadding = padding
            val scaleFactor = downscaleFactor
            val fullWidth = (size.width + currentPadding * 2).toInt()
            val fullHeight = (size.height + currentPadding * 2).toInt()

            if (scaleFactor <= 1) {
                cascadeFirstStepScale = 1
                recordLayer(layer, size = IntSize(fullWidth, fullHeight), block = recordBackdropBlock)
                layer.topLeft =
                    if (currentPadding != 0f) {
                        IntOffset(-currentPadding.toInt(), -currentPadding.toInt())
                    } else {
                        IntOffset.Zero
                    }
                drawLayer(layer)
            } else if (scaleFactor <= 2) {
                // Single 2x step — no cascade needed
                cascadeFirstStepScale = 2
                val w = (fullWidth / 2).coerceAtLeast(1)
                val h = (fullHeight / 2).coerceAtLeast(1)
                recordLayer(layer, size = IntSize(w, h), block = recordBackdropBlock)
                val scaleUp = scaleFactor.toFloat()
                layer.topLeft =
                    if (currentPadding != 0f) {
                        IntOffset(-(currentPadding / scaleFactor).toInt(), -(currentPadding / scaleFactor).toInt())
                    } else {
                        IntOffset.Zero
                    }
                val residualX = backdrop.offsetResidualX
                val residualY = backdrop.offsetResidualY
                drawContext.canvas.translate(-residualX, -residualY)
                scale(scaleUp, scaleUp, Offset.Zero) { drawLayer(layer) }
                drawContext.canvas.translate(residualX, residualY)
            } else {
                // Two-step cascade: backdrop at 1/(sf/2), then bilinear 2x.
                // Matches libhwui pattern: 4→2x→2x, 8→4x→2x, 16→8x→2x.
                val firstStep = scaleFactor / 2
                cascadeFirstStepScale = firstStep
                val intermediateW = (fullWidth / firstStep).coerceAtLeast(1)
                val intermediateH = (fullHeight / firstStep).coerceAtLeast(1)
                val finalW = (intermediateW / 2).coerceAtLeast(1)
                val finalH = (intermediateH / 2).coerceAtLeast(1)

                // Step 1: record backdrop at 1/firstStep into cascade layer
                val intermediate = cascadeLayer
                    ?: requireGraphicsContext().createGraphicsLayer().also { cascadeLayer = it }
                recordLayer(intermediate, size = IntSize(intermediateW, intermediateH), block = recordBackdropBlock)

                // Step 2: box-filter 2x from cascade layer into blur layer.
                // Apply a 4-point box filter as RenderEffect on the cascade layer
                // so that drawLayer produces pre-filtered output. The subsequent
                // scale(0.5) then downsamples from pre-filtered content, matching
                // libhwui's dedicated downsample shader quality.
                if (isRuntimeShaderSupported()) {
                    intermediate.renderEffect = runtimeShaderEffect(
                        runtimeShader = effectScope.obtainRuntimeShader(
                            "Downsample2x",
                            DOWNSAMPLE_2X_SHADER,
                        ).apply {
                            setFloatUniform(
                                "imageWH",
                                floatArrayOf(intermediateW.toFloat(), intermediateH.toFloat()),
                            )
                        },
                        uniformShaderName = "child",
                    )
                }
                recordLayer(layer, size = IntSize(finalW, finalH)) {
                    scale(0.5f, 0.5f, Offset.Zero) { drawLayer(intermediate) }
                }
                intermediate.renderEffect = null

                val scaleUp = scaleFactor.toFloat()
                layer.topLeft =
                    if (currentPadding != 0f) {
                        IntOffset(-(currentPadding / scaleFactor).toInt(), -(currentPadding / scaleFactor).toInt())
                    } else {
                        IntOffset.Zero
                    }
                val residualX = backdrop.offsetResidualX
                val residualY = backdrop.offsetResidualY
                drawContext.canvas.translate(-residualX, -residualY)
                scale(scaleUp, scaleUp, Offset.Zero) { drawLayer(layer) }
                drawContext.canvas.translate(residualX, residualY)
            }
        }
    }

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints,
    ): MeasureResult {
        val placeable = measurable.measure(constraints)
        return layout(placeable.width, placeable.height) {
            if (enabled) {
                placeable.placeWithLayer(IntOffset.Zero, layerBlock = layoutLayerBlock)
            } else {
                placeable.place(IntOffset.Zero)
            }
        }
    }

    private val contentPaint = Paint()

    override fun ContentDrawScope.draw() {
        if (!enabled) {
            drawContent()
            return
        }
        if (effectScope.update(this)) {
            updateEffects()
        }
        onDrawBehind?.invoke(this)
        drawBackdropLayer()
        onDrawSurface?.invoke(this)

        val blendMode = contentBlendMode
        if (blendMode != null) {
            contentPaint.blendMode = blendMode
            drawContext.canvas.saveLayer(
                androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height),
                contentPaint,
            )
            drawContent()
            drawContext.canvas.restore()
        } else {
            drawContent()
        }

        onDrawFront?.invoke(this)
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        if (coordinates.isAttached) {
            if (backdrop.isCoordinatesDependent) {
                layoutCoordinates = coordinates
            } else {
                if (layoutCoordinates != null) {
                    layoutCoordinates = null
                }
            }
        }
    }

    override fun onObservedReadsChanged() {
        invalidateDrawCache()
    }

    fun invalidateDrawCache() {
        observeEffects()
    }

    private fun observeEffects() {
        observeReads { updateEffects() }
    }

    private fun updateEffects() {
        if (!enabled || !isRenderEffectSupported()) return
        ensureGraphicsLayer()
        effectScope.apply(effects)
        graphicsLayer?.renderEffect = effectScope.renderEffect
        padding = effectScope.padding
        downscaleFactor = effectScope.downscaleFactor.coerceAtLeast(1)
    }

    private fun ensureGraphicsLayer(): GraphicsLayer {
        return graphicsLayer ?: requireGraphicsContext().createGraphicsLayer().also {
            graphicsLayer = it
        }
    }

    override fun onAttach() {
        effectScope.runtimeShaderCache = currentValueOf(LocalRuntimeShaderCache)
        if (enabled) {
            ensureGraphicsLayer()
            observeEffects()
        }
    }

    fun releaseGraphicsLayers() {
        val ctx = requireGraphicsContext()
        graphicsLayer?.let { ctx.releaseGraphicsLayer(it) }
        graphicsLayer = null
        cascadeLayer?.let { ctx.releaseGraphicsLayer(it) }
        cascadeLayer = null
        effectScope.reset()
    }

    override fun onDetach() {
        releaseGraphicsLayers()
        layoutCoordinates = null
    }
}
