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
import top.yukonga.miuix.kmp.blur.internal.DOWNSAMPLE_4X_SHADER
import top.yukonga.miuix.kmp.blur.internal.NOISE_DITHER_SHADER
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
    contentBlendMode: BlendMode = BlendMode.SrcOver,
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
    val contentBlendMode: BlendMode = BlendMode.SrcOver,
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
        result = 31 * result + contentBlendMode.hashCode()
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
    var contentBlendMode: BlendMode = BlendMode.SrcOver,
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
    private val cascadeLayers: MutableList<GraphicsLayer> = mutableListOf()
    private var noiseLayer: GraphicsLayer? = null

    private fun obtainCascadeLayer(index: Int): GraphicsLayer {
        val ctx = requireGraphicsContext()
        while (cascadeLayers.size <= index) {
            cascadeLayers.add(ctx.createGraphicsLayer())
        }
        return cascadeLayers[index]
    }

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
                drawUpscaledLayer(
                    layer,
                    scaleFactor.toFloat(),
                    currentPadding,
                    scaleFactor,
                    backdrop.offsetResidualX,
                    backdrop.offsetResidualY,
                    fullWidth,
                    fullHeight,
                )
            } else {
                // Multi-step cascade, single-pass wider filter when possible:
                //   sf =  4: backdrop ½ → 2x box → ¼                     (1 cascade layer)
                //   sf =  8: backdrop ½ → 4x box → ⅛                     (1 cascade layer)
                //   sf = 16: backdrop ½ → 4x box → ⅛ → 2x box → 1/16     (2 cascade layers)
                cascadeFirstStepScale = 2
                val firstW = (fullWidth / 2).coerceAtLeast(1)
                val firstH = (fullHeight / 2).coerceAtLeast(1)

                // Step 0: record backdrop at ½ size (GPU bilinear, no shader).
                val firstCascade = obtainCascadeLayer(0)
                recordLayer(firstCascade, size = IntSize(firstW, firstH), block = recordBackdropBlock)

                when (scaleFactor) {
                    4 -> {
                        // Single 2× box step: ½ → ¼
                        applyDownsampleStep(
                            source = firstCascade,
                            sourceW = firstW,
                            sourceH = firstH,
                            dest = layer,
                            destW = (firstW / 2).coerceAtLeast(1),
                            destH = (firstH / 2).coerceAtLeast(1),
                            scale = 0.5f,
                            shaderKey = "Downsample2x",
                            shaderSrc = DOWNSAMPLE_2X_SHADER,
                        )
                    }

                    8 -> {
                        // Single 4× box step: ½ → ⅛
                        applyDownsampleStep(
                            source = firstCascade,
                            sourceW = firstW,
                            sourceH = firstH,
                            dest = layer,
                            destW = (firstW / 4).coerceAtLeast(1),
                            destH = (firstH / 4).coerceAtLeast(1),
                            scale = 0.25f,
                            shaderKey = "Downsample4x",
                            shaderSrc = DOWNSAMPLE_4X_SHADER,
                        )
                    }

                    16 -> {
                        // 4× then 2× cascade: ½ → ⅛ → 1/16
                        val midW = (firstW / 4).coerceAtLeast(1)
                        val midH = (firstH / 4).coerceAtLeast(1)
                        val midCascade = obtainCascadeLayer(1)
                        applyDownsampleStep(
                            source = firstCascade,
                            sourceW = firstW,
                            sourceH = firstH,
                            dest = midCascade,
                            destW = midW,
                            destH = midH,
                            scale = 0.25f,
                            shaderKey = "Downsample4x",
                            shaderSrc = DOWNSAMPLE_4X_SHADER,
                        )
                        applyDownsampleStep(
                            source = midCascade,
                            sourceW = midW,
                            sourceH = midH,
                            dest = layer,
                            destW = (midW / 2).coerceAtLeast(1),
                            destH = (midH / 2).coerceAtLeast(1),
                            scale = 0.5f,
                            shaderKey = "Downsample2x",
                            shaderSrc = DOWNSAMPLE_2X_SHADER,
                        )
                    }

                    else -> error("Unsupported scaleFactor: $scaleFactor (must be 1/2/4/8/16)")
                }

                drawUpscaledLayer(
                    layer,
                    scaleFactor.toFloat(),
                    currentPadding,
                    scaleFactor,
                    backdrop.offsetResidualX,
                    backdrop.offsetResidualY,
                    fullWidth,
                    fullHeight,
                )
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

        if (contentBlendMode == BlendMode.SrcOver) {
            drawContent()
        } else {
            contentPaint.blendMode = contentBlendMode
            drawContext.canvas.saveLayer(
                androidx.compose.ui.geometry.Rect(0f, 0f, size.width, size.height),
                contentPaint,
            )
            drawContent()
            drawContext.canvas.restore()
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

        // When not downscaled, noise can go directly in the RenderEffect chain
        // at full resolution. When downscaled, noise is deferred to a separate
        // full-resolution layer in drawBackdropLayer.
        val noiseCoeff = effectScope.noiseCoefficient
        if (noiseCoeff > 0f && effectScope.downscaleFactor <= 1 && isRuntimeShaderSupported()) {
            effectScope.runtimeShaderEffect(
                key = "NoiseDither",
                shaderString = NOISE_DITHER_SHADER,
                uniformShaderName = "child",
            ) {
                setFloatUniform("noise_coeff", noiseCoeff)
            }
        }

        graphicsLayer?.renderEffect = effectScope.renderEffect
        padding = effectScope.padding
        downscaleFactor = effectScope.downscaleFactor.coerceAtLeast(1)
    }

    private fun ensureGraphicsLayer(): GraphicsLayer = graphicsLayer ?: requireGraphicsContext().createGraphicsLayer().also {
        graphicsLayer = it
    }

    override fun onAttach() {
        effectScope.runtimeShaderCache = currentValueOf(LocalRuntimeShaderCache)
        if (enabled) {
            ensureGraphicsLayer()
            observeEffects()
        }
    }

    /**
     * Applies a single cascade downsample step: sets [shaderSrc] as a render effect on
     * [source] then records [dest] at ([destW], [destH]) by drawing [source] with [scale].
     * Clears the render effect from [source] when done so it does not leak to the next pass.
     */
    private fun DrawScope.applyDownsampleStep(
        source: GraphicsLayer,
        sourceW: Int,
        sourceH: Int,
        dest: GraphicsLayer,
        destW: Int,
        destH: Int,
        scale: Float,
        shaderKey: String,
        shaderSrc: String,
    ) {
        if (isRuntimeShaderSupported()) {
            source.renderEffect = runtimeShaderEffect(
                runtimeShader = effectScope.obtainRuntimeShader(shaderKey, shaderSrc).apply {
                    setFloatUniform("imageWH", floatArrayOf(sourceW.toFloat(), sourceH.toFloat()))
                },
                uniformShaderName = "child",
            )
        }
        recordLayer(dest, size = IntSize(destW, destH)) {
            scale(scale, scale, Offset.Zero) { drawLayer(source) }
        }
        source.renderEffect = null
    }

    /**
     * Draws the downscaled [layer] upscaled to full resolution.
     * When noise dithering is active, the upscaled content is recorded into a
     * full-resolution [noiseLayer] and noise is applied per-pixel, avoiding the
     * coarse block artifacts that occur when noise is applied at downscaled resolution.
     */
    private fun DrawScope.drawUpscaledLayer(
        layer: GraphicsLayer,
        scaleUp: Float,
        currentPadding: Float,
        scaleFactor: Int,
        residualX: Float,
        residualY: Float,
        fullWidth: Int,
        fullHeight: Int,
    ) {
        val noiseCoeff = effectScope.noiseCoefficient
        if (noiseCoeff > 0f && isRuntimeShaderSupported()) {
            // Record the upscaled blur into a full-resolution layer for per-pixel noise
            layer.topLeft = IntOffset.Zero
            val noiseL = noiseLayer
                ?: requireGraphicsContext().createGraphicsLayer().also { noiseLayer = it }
            recordLayer(noiseL, size = IntSize(fullWidth, fullHeight)) {
                scale(scaleUp, scaleUp, Offset.Zero) { drawLayer(layer) }
            }
            val noiseShader = effectScope.obtainRuntimeShader("NoiseDither", NOISE_DITHER_SHADER)
            noiseShader.setFloatUniform("noise_coeff", noiseCoeff)
            noiseL.renderEffect = runtimeShaderEffect(noiseShader, "child")
            noiseL.topLeft =
                if (currentPadding != 0f) {
                    IntOffset(-currentPadding.toInt(), -currentPadding.toInt())
                } else {
                    IntOffset.Zero
                }
            drawContext.canvas.translate(-residualX, -residualY)
            drawLayer(noiseL)
            drawContext.canvas.translate(residualX, residualY)
            noiseL.renderEffect = null
        } else {
            layer.topLeft =
                if (currentPadding != 0f) {
                    IntOffset(-(currentPadding / scaleFactor).toInt(), -(currentPadding / scaleFactor).toInt())
                } else {
                    IntOffset.Zero
                }
            drawContext.canvas.translate(-residualX, -residualY)
            scale(scaleUp, scaleUp, Offset.Zero) { drawLayer(layer) }
            drawContext.canvas.translate(residualX, residualY)
        }
    }

    fun releaseGraphicsLayers() {
        val ctx = requireGraphicsContext()
        graphicsLayer?.let {
            ctx.releaseGraphicsLayer(it)
        }
        graphicsLayer = null
        for (cascade in cascadeLayers) {
            ctx.releaseGraphicsLayer(cascade)
        }
        cascadeLayers.clear()
        noiseLayer?.let {
            ctx.releaseGraphicsLayer(it)
        }
        noiseLayer = null
        effectScope.reset()
    }

    override fun onDetach() {
        releaseGraphicsLayers()
        layoutCoordinates = null
    }
}
