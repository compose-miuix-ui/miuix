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
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
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
import top.yukonga.miuix.kmp.blur.highlight.Highlight
import top.yukonga.miuix.kmp.blur.highlight.drawHighlight
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
    highlight: (BackdropEffectScope.() -> Highlight?)? = null,
    layerBlock: (GraphicsLayerScope.() -> Unit)? = null,
    onDrawBehind: (DrawScope.() -> Unit)? = null,
    onDrawBackdrop: DrawScope.(drawBackdrop: DrawScope.() -> Unit) -> Unit = DefaultOnDrawBackdrop,
    onDrawSurface: (DrawScope.() -> Unit)? = null,
    onDrawFront: (DrawScope.() -> Unit)? = null,
    contentBlendMode: BlendMode = BlendMode.SrcOver,
    enabled: Boolean = true,
): Modifier {
    // The effect pipeline (blur / blend / highlight / custom passes) is built on RuntimeShader.
    val effectiveEnabled = enabled && isRuntimeShaderSupported()
    return this
        .then(
            if (layerBlock != null) Modifier.graphicsLayer(layerBlock) else Modifier,
        )
        .then(
            DrawBackdropElement(
                backdrop = backdrop,
                shape = shape,
                effects = effects,
                highlight = highlight,
                layerBlock = layerBlock,
                onDrawBehind = onDrawBehind,
                onDrawBackdrop = onDrawBackdrop,
                onDrawSurface = onDrawSurface,
                onDrawFront = onDrawFront,
                contentBlendMode = contentBlendMode,
                enabled = effectiveEnabled,
            ),
        )
}

private class DrawBackdropElement(
    val backdrop: Backdrop,
    val shape: () -> Shape,
    val effects: BackdropEffectScope.() -> Unit,
    val highlight: (BackdropEffectScope.() -> Highlight?)?,
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
        shape = shape,
        effects = effects,
        highlight = highlight,
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
        node.updateShape(shape)
        node.effects = effects
        node.highlight = highlight
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
        if (shape != other.shape) return false
        if (effects != other.effects) return false
        if (highlight != other.highlight) return false
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
        result = 31 * result + shape.hashCode()
        result = 31 * result + effects.hashCode()
        result = 31 * result + (highlight?.hashCode() ?: 0)
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
    val shape: () -> Shape,
    var effects: BackdropEffectScope.() -> Unit,
    var highlight: (BackdropEffectScope.() -> Highlight?)?,
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

    private val shapeProvider: ShapeProvider = ShapeProvider(shape)

    fun updateShape(shape: () -> Shape) {
        shapeProvider.shapeBlock = shape
    }

    private val effectScope = object : BackdropEffectScopeImpl() {
        override val shape: Shape get() = shapeProvider.innerShape
    }

    /** Layers + level parameters for rendering one downscale level (primary = lo, secondary = hi). */
    private inner class LevelTarget {
        var mainLayer: GraphicsLayer? = null
        val cascadeLayers: MutableList<GraphicsLayer> = mutableListOf()
        var noiseLayer: GraphicsLayer? = null
        var downscaleFactor: Int = 1
        var padding: Float by mutableFloatStateOf(0f)

        /** First step downscale for cascade (backdrop draw uses this, not the total factor). */
        var cascadeFirstStepScale: Int = 1

        // Draw-path RenderEffects cached for reuse while the source size / noise coefficient hold.
        // A RenderEffect snapshots its shader's uniforms at creation, so reusing the instance is
        // safe and avoids a native allocation per frame. Two slots cover the deepest cascade (sf=16).
        val dsKeys = arrayOf("", "")
        val dsW = intArrayOf(-1, -1)
        val dsH = intArrayOf(-1, -1)
        val dsEffects = arrayOfNulls<RenderEffect>(2)
        var noiseEffect: RenderEffect? = null
        var noiseEffectCoeff: Float = Float.NaN

        fun ensureMain(): GraphicsLayer = mainLayer ?: requireGraphicsContext().createGraphicsLayer().also { mainLayer = it }

        fun obtainCascade(index: Int): GraphicsLayer {
            val ctx = requireGraphicsContext()
            while (cascadeLayers.size <= index) {
                cascadeLayers.add(ctx.createGraphicsLayer())
            }
            return cascadeLayers[index]
        }

        /**
         * Returns the cached downsample [RenderEffect] for [slot] when its [key]/[w]/[h] are
         * unchanged, otherwise builds it via [build] (setting the shader uniforms) and caches it.
         */
        inline fun downsampleEffect(slot: Int, key: String, w: Int, h: Int, build: () -> RenderEffect): RenderEffect {
            val cached = dsEffects[slot]
            if (cached != null && dsKeys[slot] == key && dsW[slot] == w && dsH[slot] == h) return cached
            return build().also {
                dsEffects[slot] = it
                dsKeys[slot] = key
                dsW[slot] = w
                dsH[slot] = h
            }
        }

        /** Returns the cached noise [RenderEffect] for [coeff], building via [build] on a miss. */
        inline fun noiseRenderEffect(coeff: Float, build: () -> RenderEffect): RenderEffect {
            val cached = noiseEffect
            if (cached != null && noiseEffectCoeff == coeff) return cached
            return build().also {
                noiseEffect = it
                noiseEffectCoeff = coeff
            }
        }

        fun release() {
            val ctx = requireGraphicsContext()
            mainLayer?.let { ctx.releaseGraphicsLayer(it) }
            mainLayer = null
            for (cascade in cascadeLayers) ctx.releaseGraphicsLayer(cascade)
            cascadeLayers.clear()
            noiseLayer?.let { ctx.releaseGraphicsLayer(it) }
            noiseLayer = null
            dsEffects.fill(null)
            dsKeys.fill("")
            dsW.fill(-1)
            dsH.fill(-1)
            noiseEffect = null
            noiseEffectCoeff = Float.NaN
        }
    }

    private val primary = LevelTarget()
    private val secondary = LevelTarget()
    private var crossfadeResultLayer: GraphicsLayer? = null

    /** True when the radius sits in a transition band and [primary] (lo) + [secondary] (hi) cross-fade. */
    private var blending: Boolean by mutableStateOf(false)

    /** Smoothstep weight of the higher (secondary) level while [blending]. */
    private var blendFactor: Float by mutableFloatStateOf(0f)

    private val layoutLayerBlock: GraphicsLayerScope.() -> Unit = {
        clip = true
        shape = shapeProvider.shape
        compositingStrategy = CompositingStrategy.Offscreen
    }

    private var layoutCoordinates: LayoutCoordinates? by mutableStateOf(null, neverEqualPolicy())

    private fun DrawScope.recordBackdrop(target: LevelTarget) {
        val currentPadding = target.padding
        val scaleFactor = target.cascadeFirstStepScale
        val scaledPadding = if (currentPadding == 0f) {
            0f
        } else if (scaleFactor > 1) {
            (currentPadding / scaleFactor).toInt().toFloat()
        } else {
            currentPadding
        }
        translate(scaledPadding, scaledPadding) {
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
        }
    }

    /** Renders [target]'s blurred backdrop (record → cascade downsample → upscale) into this DrawScope. */
    private fun DrawScope.renderBlurInto(target: LevelTarget) {
        val layer = target.mainLayer ?: return
        val currentPadding = target.padding
        val scaleFactor = target.downscaleFactor
        val fullWidth = (size.width + currentPadding * 2).toInt()
        val fullHeight = (size.height + currentPadding * 2).toInt()

        if (scaleFactor <= 1) {
            target.cascadeFirstStepScale = 1
            recordLayer(layer, size = IntSize(fullWidth, fullHeight)) { recordBackdrop(target) }
            layer.topLeft =
                if (currentPadding != 0f) {
                    IntOffset(-currentPadding.toInt(), -currentPadding.toInt())
                } else {
                    IntOffset.Zero
                }
            drawLayer(layer)
        } else if (scaleFactor <= 2) {
            target.cascadeFirstStepScale = 2
            val w = (fullWidth / 2).coerceAtLeast(1)
            val h = (fullHeight / 2).coerceAtLeast(1)
            recordLayer(layer, size = IntSize(w, h)) { recordBackdrop(target) }
            drawUpscaledLayer(
                target,
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
            // Multistep cascade, single-pass wider filter when possible:
            //   sf =  4: backdrop ½ → 2x box → ¼                     (1 cascade layer)
            //   sf =  8: backdrop ½ → 4x box → ⅛                     (1 cascade layer)
            //   sf = 16: backdrop ½ → 4x box → ⅛ → 2x box → 1/16     (2 cascade layers)
            target.cascadeFirstStepScale = 2
            val firstW = (fullWidth / 2).coerceAtLeast(1)
            val firstH = (fullHeight / 2).coerceAtLeast(1)

            // Step 0: record backdrop at ½ size (GPU bilinear, no shader).
            val firstCascade = target.obtainCascade(0)
            recordLayer(firstCascade, size = IntSize(firstW, firstH)) { recordBackdrop(target) }

            when (scaleFactor) {
                4 -> {
                    // Single 2× box step: ½ → ¼
                    applyDownsampleStep(
                        target = target,
                        slot = 0,
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
                        target = target,
                        slot = 0,
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
                    val midCascade = target.obtainCascade(1)
                    applyDownsampleStep(
                        target = target,
                        slot = 0,
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
                        target = target,
                        slot = 1,
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
                target,
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
    private val highlightPaint = Paint()

    override fun ContentDrawScope.draw() {
        if (!enabled) {
            drawContent()
            return
        }
        if (effectScope.update(this)) {
            updateEffects()
        }
        onDrawBehind?.invoke(this)
        renderBlurInto(primary)
        if (blending) {
            // Cross-fade band: composite the higher level on top of the lower one with alpha = blendFactor.
            val result = crossfadeResultLayer
                ?: requireGraphicsContext().createGraphicsLayer().also { crossfadeResultLayer = it }
            recordLayer(
                result,
                size = IntSize(size.width.toInt().coerceAtLeast(1), size.height.toInt().coerceAtLeast(1)),
            ) {
                renderBlurInto(secondary)
            }
            result.alpha = blendFactor
            drawLayer(result)
        }
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

        highlight?.invoke(effectScope)?.let { resolved ->
            drawHighlight(
                highlight = resolved,
                shape = effectScope.shape,
                runtimeShaderCache = currentValueOf(LocalRuntimeShaderCache),
                paint = highlightPaint,
            )
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
        if (!enabled) return
        primary.ensureMain()

        // Pass 1 (auto): build the lower bracket level into [primary]; blur() also stashes the
        // cross-fade bracket (computeDownScaleBlend) on the scope for us to read below.
        effectScope.forcedDownscaleExp = -1
        effectScope.apply(effects)
        chainFullResNoiseIfNeeded()
        primary.mainLayer?.renderEffect = effectScope.renderEffect
        primary.padding = effectScope.padding
        primary.downscaleFactor = effectScope.downscaleFactor.coerceAtLeast(1)

        val expLo = effectScope.blurBlendExpLo
        val expHi = effectScope.blurBlendExpHi
        val factor = effectScope.blurBlendFactor

        if (expLo == expHi || factor <= 0.001f) {
            blending = false
            return
        }

        // Pass 2: build the higher bracket level into [secondary]; draw() cross-fades the two.
        secondary.ensureMain()
        effectScope.forcedDownscaleExp = expHi
        effectScope.apply(effects)
        chainFullResNoiseIfNeeded()
        secondary.mainLayer?.renderEffect = effectScope.renderEffect
        secondary.padding = effectScope.padding
        secondary.downscaleFactor = effectScope.downscaleFactor.coerceAtLeast(1)
        effectScope.forcedDownscaleExp = -1

        blendFactor = factor
        blending = true
    }

    /**
     * Full-res path: chains noise into the RenderEffect. The downscaled path defers it to
     * [drawUpscaledLayer] so each screen pixel still gets independent dithering.
     */
    private fun chainFullResNoiseIfNeeded() {
        val noiseCoeff = effectScope.noiseCoefficient
        if (noiseCoeff > 0f && effectScope.downscaleFactor <= 1) {
            effectScope.runtimeShaderEffect(
                key = "NoiseDither",
                shaderString = NOISE_DITHER_SHADER,
                uniformShaderName = "child",
            ) {
                setFloatUniform("noise_coeff", noiseCoeff)
            }
        }
    }

    override fun onAttach() {
        effectScope.runtimeShaderCache = currentValueOf(LocalRuntimeShaderCache)
        if (enabled) {
            primary.ensureMain()
            observeEffects()
        }
    }

    /**
     * Sets the downsample effect (cached per [target]/[slot]) on [source], records [dest] at
     * ([destW], [destH]) by drawing [source] with [scale], then clears [source]'s render effect
     * so it does not leak to the next pass.
     */
    private fun DrawScope.applyDownsampleStep(
        target: LevelTarget,
        slot: Int,
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
        source.renderEffect = target.downsampleEffect(slot, shaderKey, sourceW, sourceH) {
            runtimeShaderEffect(
                runtimeShader = effectScope.obtainRuntimeShader(shaderKey, shaderSrc).apply {
                    setFloatUniform("maxCoord", sourceW - 0.5f, sourceH - 0.5f)
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
     * Upscales [layer] to full resolution. When noise dithering is active the upscaled content
     * is staged in [target]'s noise layer first so noise lands per screen pixel instead of per
     * scaled block.
     */
    private fun DrawScope.drawUpscaledLayer(
        target: LevelTarget,
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
        if (noiseCoeff > 0f) {
            layer.topLeft = IntOffset.Zero
            val noiseL = target.noiseLayer
                ?: requireGraphicsContext().createGraphicsLayer().also { target.noiseLayer = it }
            recordLayer(noiseL, size = IntSize(fullWidth, fullHeight)) {
                scale(scaleUp, scaleUp, Offset.Zero) { drawLayer(layer) }
            }
            noiseL.renderEffect = target.noiseRenderEffect(noiseCoeff) {
                val noiseShader = effectScope.obtainRuntimeShader("NoiseDither", NOISE_DITHER_SHADER)
                noiseShader.setFloatUniform("noise_coeff", noiseCoeff)
                runtimeShaderEffect(noiseShader, "child")
            }
            noiseL.topLeft =
                if (currentPadding != 0f) {
                    IntOffset(-currentPadding.toInt(), -currentPadding.toInt())
                } else {
                    IntOffset.Zero
                }
            translate(-residualX, -residualY) { drawLayer(noiseL) }
            noiseL.renderEffect = null
        } else {
            layer.topLeft =
                if (currentPadding != 0f) {
                    IntOffset(-(currentPadding / scaleFactor).toInt(), -(currentPadding / scaleFactor).toInt())
                } else {
                    IntOffset.Zero
                }
            translate(-residualX, -residualY) {
                scale(scaleUp, scaleUp, Offset.Zero) { drawLayer(layer) }
            }
        }
    }

    fun releaseGraphicsLayers() {
        primary.release()
        secondary.release()
        crossfadeResultLayer?.let { requireGraphicsContext().releaseGraphicsLayer(it) }
        crossfadeResultLayer = null
        blending = false
        effectScope.reset()
    }

    override fun onDetach() {
        releaseGraphicsLayers()
        layoutCoordinates = null
    }
}
