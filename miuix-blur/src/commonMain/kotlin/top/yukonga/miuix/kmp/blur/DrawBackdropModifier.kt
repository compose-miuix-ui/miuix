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
import androidx.compose.ui.geometry.Rect
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
import top.yukonga.miuix.kmp.blur.internal.DOWNSAMPLE_2X_SHADER
import top.yukonga.miuix.kmp.blur.internal.DOWNSAMPLE_4X_SHADER
import top.yukonga.miuix.kmp.blur.internal.NOISE_DITHER_SHADER
import top.yukonga.miuix.kmp.blur.internal.ShapeProvider
import top.yukonga.miuix.kmp.blur.internal.chain
import top.yukonga.miuix.kmp.blur.internal.createProgressiveSharpOverlayEffect
import top.yukonga.miuix.kmp.blur.internal.createProgressiveStackEffect
import top.yukonga.miuix.kmp.blur.internal.progressiveLoopTapBound
import top.yukonga.miuix.kmp.blur.internal.progressiveSharpBandBounds
import top.yukonga.miuix.kmp.blur.internal.progressiveSharpLoopRadius
import top.yukonga.miuix.kmp.blur.internal.recordLayer
import top.yukonga.miuix.kmp.blur.internal.runtimeShaderEffect
import top.yukonga.miuix.kmp.material.LocalRuntimeShaderCache
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

private val DefaultOnDrawBackdrop: DrawScope.(DrawScope.() -> Unit) -> Unit = { it() }

/** Float tolerance for "a box step lands exactly on the level". */
private const val LEVEL_EPS = 1e-3f

// The stack dither exists to break quantization staircases, which ~1 LSB (≈ 0.008 peak-to-peak)
// already does; anything above only adds downscale-texel-sized grain that grows with the radius.
private const val STACK_DITHER_MAX = 0.009f

// Above this coefficient the noise is deliberate visible grain, which must land per screen pixel
// to stay radius-independent — the full-resolution noise pass runs. At or below it the grain is
// imperceptible and the capped stack dither alone covers anti-banding, so the round-trip is
// skipped.
private const val NOISE_GRAIN_THRESHOLD = 0.005f

/**
 * Applies a backdrop effect to this composable.
 *
 * @param backdrop The [Backdrop] providing the background content to capture and blur.
 * @param shape The shape provider for the blur region clipping; re-read on each draw so an
 *   animated shape stays current.
 * @param effects The effect pipeline applied to the captured backdrop, configured against a
 *   [BackdropEffectScope] (e.g. [blur], [blendColors], [colorControls]).
 * @param layerBlock Optional graphics layer transformation applied to this composable and inverted
 *   on the backdrop sampling so the backdrop stays screen-aligned; safe to animate.
 * @param onDrawBehind Optional draw callback invoked before the blurred backdrop, behind it.
 * @param onDrawBackdrop Wraps the backdrop drawing call, letting callers transform or intercept the
 *   recorded content; defaults to invoking it directly.
 * @param onDrawSurface Optional draw callback invoked after the blurred backdrop and before the
 *   composable's own content.
 * @param onDrawFront Optional draw callback invoked last, on top of the content.
 * @param contentBlendMode The [BlendMode] used to composite the composable's content over the blur.
 *   [BlendMode.DstIn] masks the blur by the content alpha (foreground blur).
 * @param progressiveGradient When non-null, renders the backdrop as a multi-level progressive
 *   composite: graduated native Gaussian levels cross-faded along this gradient on the downscaled
 *   layer, with a genuinely sharp full-resolution clear end — the blur strength ramps
 *   continuously from full to pixel-sharp. Requires a matching [progressiveBlur] in [effects] (as
 *   [Modifier.progressiveTextureBlur] wires) — it records the target radii the composite renders;
 *   without one the gradient is ignored and [effects] draws uniformly.
 * @param enabled Whether the backdrop effect is active. When false, content draws normally without
 *   blur. Also gated by [isRuntimeShaderSupported].
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
    progressiveGradient: ProgressiveBlur? = null,
    enabled: Boolean = true,
): Modifier {
    // The effect pipeline (blur / blend / custom passes) is built on RuntimeShader.
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
                layerBlock = layerBlock,
                onDrawBehind = onDrawBehind,
                onDrawBackdrop = onDrawBackdrop,
                onDrawSurface = onDrawSurface,
                onDrawFront = onDrawFront,
                contentBlendMode = contentBlendMode,
                progressiveGradient = progressiveGradient,
                enabled = effectiveEnabled,
            ),
        )
}

private class DrawBackdropElement(
    val backdrop: Backdrop,
    val shape: () -> Shape,
    val effects: BackdropEffectScope.() -> Unit,
    val layerBlock: (GraphicsLayerScope.() -> Unit)?,
    val onDrawBehind: (DrawScope.() -> Unit)?,
    val onDrawBackdrop: DrawScope.(drawBackdrop: DrawScope.() -> Unit) -> Unit,
    val onDrawSurface: (DrawScope.() -> Unit)?,
    val onDrawFront: (DrawScope.() -> Unit)?,
    val contentBlendMode: BlendMode = BlendMode.SrcOver,
    val progressiveGradient: ProgressiveBlur? = null,
    val enabled: Boolean = true,
) : ModifierNodeElement<DrawBackdropNode>() {

    override fun create(): DrawBackdropNode = DrawBackdropNode(
        backdrop = backdrop,
        shape = shape,
        effects = effects,
        layerBlock = layerBlock,
        onDrawBehind = onDrawBehind,
        onDrawBackdrop = onDrawBackdrop,
        onDrawSurface = onDrawSurface,
        onDrawFront = onDrawFront,
        contentBlendMode = contentBlendMode,
        progressiveGradient = progressiveGradient,
        enabled = enabled,
    )

    override fun update(node: DrawBackdropNode) {
        val enabledChanged = node.enabled != enabled
        val gradientCleared = node.progressiveGradient != null && progressiveGradient == null
        node.backdrop = backdrop
        node.updateShape(shape)
        node.effects = effects
        node.layerBlock = layerBlock
        node.onDrawBehind = onDrawBehind
        node.onDrawBackdrop = onDrawBackdrop
        node.onDrawSurface = onDrawSurface
        node.onDrawFront = onDrawFront
        node.contentBlendMode = contentBlendMode
        node.progressiveGradient = progressiveGradient
        node.enabled = enabled
        if (gradientCleared) {
            node.releaseCompositeResources()
        }
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
        if (layerBlock != other.layerBlock) return false
        if (onDrawBehind != other.onDrawBehind) return false
        if (onDrawBackdrop != other.onDrawBackdrop) return false
        if (onDrawSurface != other.onDrawSurface) return false
        if (onDrawFront != other.onDrawFront) return false
        if (contentBlendMode != other.contentBlendMode) return false
        if (progressiveGradient != other.progressiveGradient) return false
        if (enabled != other.enabled) return false
        return true
    }

    override fun hashCode(): Int {
        var result = backdrop.hashCode()
        result = 31 * result + shape.hashCode()
        result = 31 * result + effects.hashCode()
        result = 31 * result + layerBlock.hashCode()
        result = 31 * result + onDrawBehind.hashCode()
        result = 31 * result + onDrawBackdrop.hashCode()
        result = 31 * result + onDrawSurface.hashCode()
        result = 31 * result + onDrawFront.hashCode()
        result = 31 * result + contentBlendMode.hashCode()
        result = 31 * result + progressiveGradient.hashCode()
        result = 31 * result + enabled.hashCode()
        return result
    }
}

private class DrawBackdropNode(
    var backdrop: Backdrop,
    val shape: () -> Shape,
    var effects: BackdropEffectScope.() -> Unit,
    var layerBlock: (GraphicsLayerScope.() -> Unit)?,
    var onDrawBehind: (DrawScope.() -> Unit)?,
    var onDrawBackdrop: DrawScope.(drawBackdrop: DrawScope.() -> Unit) -> Unit,
    var onDrawSurface: (DrawScope.() -> Unit)?,
    var onDrawFront: (DrawScope.() -> Unit)?,
    var contentBlendMode: BlendMode = BlendMode.SrcOver,
    var progressiveGradient: ProgressiveBlur? = null,
    var enabled: Boolean = true,
) : Modifier.Node(),
    LayoutModifierNode,
    DrawModifierNode,
    GlobalPositionAwareModifierNode,
    ObserverModifierNode,
    CompositionLocalConsumerModifierNode {

    override val shouldAutoInvalidate: Boolean = false

    private val shapeProvider: ShapeProvider = ShapeProvider(shape)

    fun updateShape(shape: () -> Shape) {
        shapeProvider.shapeBlock = shape
    }

    private val effectScope = object : BackdropEffectScopeImpl() {
        override val shape: Shape get() = shapeProvider.innerShape
    }

    /** The node's graphics context; the (necessarily `inner`) [LevelTarget] reaches it through here. */
    private val graphicsContext get() = requireGraphicsContext()

    /** Layers + level parameters for rendering one downscale level. */
    private inner class LevelTarget {
        var mainLayer: GraphicsLayer? = null
        val cascadeLayers: MutableList<GraphicsLayer> = mutableListOf()
        var noiseLayer: GraphicsLayer? = null
        var downscaleFactor: Float = 1f
        var padding: Float by mutableFloatStateOf(0f)

        /** Scale of the first (bilinear) record step: the level, or 2 for the ½ cascade below it. */
        var cascadeFirstStepScale: Float = 1f

        // Draw-path RenderEffects cached while source size / noise coefficient hold: a built effect
        // captured its uniforms, so later re-sets of the shared RuntimeShader can't affect it.
        // Two slots cover the deepest cascade.
        val dsKeys = arrayOf("", "")
        val dsW = intArrayOf(-1, -1)
        val dsH = intArrayOf(-1, -1)
        val dsEffects = arrayOfNulls<RenderEffect>(2)
        var noiseEffect: RenderEffect? = null
        var noiseEffectCoeff: Float = Float.NaN

        fun ensureMain(): GraphicsLayer = mainLayer ?: graphicsContext.createGraphicsLayer().also { mainLayer = it }

        fun obtainCascade(index: Int): GraphicsLayer {
            val ctx = graphicsContext
            while (cascadeLayers.size <= index) {
                cascadeLayers.add(ctx.createGraphicsLayer())
            }
            return cascadeLayers[index]
        }

        /**
         * Drops step filters left by earlier frames: these layers swap between a step's source and
         * its destination, and a stale filter would be applied twice (ghosted, clipped blur).
         */
        fun clearStepFilters() {
            for (i in cascadeLayers.indices) cascadeLayers[i].renderEffect = null
        }

        /** Cached downsample effect for [slot]; rebuilt via [build] when [key]/[w]/[h] change. */
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
            val ctx = graphicsContext
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

    private val layoutLayerBlock: GraphicsLayerScope.() -> Unit = {
        clip = true
        shape = shapeProvider.shape
        compositingStrategy = CompositingStrategy.Offscreen
    }

    private var layoutCoordinates: LayoutCoordinates? by mutableStateOf(null, neverEqualPolicy())

    private fun DrawScope.recordBackdrop(target: LevelTarget) {
        val currentPadding = target.padding
        val scaleFactor = target.cascadeFirstStepScale
        // Exact (fractional) padding: snapping it shifts the recording by up to a texel whenever
        // `reach × level` crosses an integer.
        val scaledPadding = if (currentPadding == 0f) 0f else currentPadding / scaleFactor
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

    /**
     * Renders [target]'s blurred backdrop: record at `1 / level` (½ first, then box steps while they
     * fit, then a prefiltered remainder), filter with [LevelTarget.mainLayer]'s effect, upscale.
     */
    private fun DrawScope.renderBlurInto(target: LevelTarget) {
        val layer = target.mainLayer ?: return
        val currentPadding = target.padding
        val level = target.downscaleFactor
        val fullWidth = (size.width + currentPadding * 2).toInt()
        val fullHeight = (size.height + currentPadding * 2).toInt()

        // Texel size of the padded region recorded at [scale].
        fun recordedSize(scale: Float): IntSize = IntSize(
            (fullWidth / scale).roundToInt().coerceAtLeast(1),
            (fullHeight / scale).roundToInt().coerceAtLeast(1),
        )

        // Filters from an earlier frame must not survive into this one: the cascade layers are
        // reused across frames and swap between destination and source roles.
        target.clearStepFilters()

        if (level <= 1.001f) {
            target.cascadeFirstStepScale = 1f
            recordLayer(layer, size = IntSize(fullWidth, fullHeight)) { recordBackdrop(target) }
            drawShiftedLayer(layer, currentPadding, 1f)
            return
        }

        if (level <= 2.001f) {
            // Half or finer: a single bilinear record straight to the level.
            target.cascadeFirstStepScale = level
            recordLayer(layer, size = recordedSize(level)) { recordBackdrop(target) }
            drawUpscaledLayer(target, layer, level, currentPadding, fullWidth, fullHeight)
            return
        }

        // Step 0: record the backdrop at half size (GPU bilinear, no shader).
        target.cascadeFirstStepScale = 2f
        var source = target.obtainCascade(0)
        var sourceSize = recordedSize(2f)
        recordLayer(source, size = sourceSize) { recordBackdrop(target) }

        var covered = 2f
        var step = 0

        // 4× box steps while a full 4× reduction still fits inside the level (one pass instead of two).
        while (covered * 4f <= level + LEVEL_EPS) {
            val after = covered * 4f
            val dest = if (abs(after - level) < LEVEL_EPS) layer else target.obtainCascade(step + 1)
            val destSize = recordedSize(after)
            applyDownsampleStep(
                target = target,
                slot = step,
                source = source,
                sourceSize = sourceSize,
                dest = dest,
                destSize = destSize,
                scale = 0.25f,
                shaderKey = "Downsample4x",
                shaderSrc = DOWNSAMPLE_4X_SHADER,
            )
            source = dest
            sourceSize = destSize
            covered = after
            step++
        }

        // One 2× box step when a halving still fits.
        if (covered * 2f <= level + LEVEL_EPS) {
            val after = covered * 2f
            val dest = if (abs(after - level) < LEVEL_EPS) layer else target.obtainCascade(step + 1)
            val destSize = recordedSize(after)
            applyDownsampleStep(
                target = target,
                slot = step,
                source = source,
                sourceSize = sourceSize,
                dest = dest,
                destSize = destSize,
                scale = 0.5f,
                shaderKey = "Downsample2x",
                shaderSrc = DOWNSAMPLE_2X_SHADER,
            )
            source = dest
            sourceSize = destSize
            covered = after
            // Fresh slot: the remainder step would otherwise evict this 2x effect every frame.
            step++
        }

        // Remainder step: same 2× box prefilter, only the decimation shrunk — so the filter
        // character doesn't change where a full box step enters the chain.
        if (abs(covered - level) > LEVEL_EPS) {
            applyDownsampleStep(
                target = target,
                slot = step,
                source = source,
                sourceSize = sourceSize,
                dest = layer,
                destSize = recordedSize(level),
                scale = covered / level,
                shaderKey = "Downsample2x",
                shaderSrc = DOWNSAMPLE_2X_SHADER,
            )
        }

        drawUpscaledLayer(target, layer, level, currentPadding, fullWidth, fullHeight)
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

    // Reused saveLayer bounds for the non-SrcOver content path (DstIn / foreground blur): Rect is a
    // plain class, so caching it avoids a per-frame allocation; rebuilt only when the size changes.
    private var contentBoundsRect: Rect? = null

    // Progressive blur split by resolution: the level stack runs through the downscaled [primary]
    // machinery, [sharpLayer] overlays the ramp's clear end at full resolution (skipped while the
    // stack renders it natively). Flags gate platforms without a stack effect.
    private var sharpLayer: GraphicsLayer? = null
    private var compositeAttempted = false
    private var compositeSupported = false
    private var sharpOverlayActive = false

    // Level-stack effect cached on its inputs: chains by identity, the recorded blend by value.
    private var cachedStackEffect: RenderEffect? = null
    private var cachedStackRadiusX = Float.NaN
    private var cachedStackRadiusY = Float.NaN
    private var cachedStackW = Float.NaN
    private var cachedStackH = Float.NaN
    private var cachedStackGradient: ProgressiveBlur? = null
    private var cachedStackPre: RenderEffect? = null
    private var cachedStackPost: RenderEffect? = null
    private var cachedStackPostBlend: BlurColors? = null
    private var cachedStackDither = Float.NaN

    // Dither chained after the assembled stack, cached on its coefficient. exp > 0: dither before
    // the 8-bit write at min(2 × coefficient, [STACK_DITHER_MAX]) — dithering after it only masks
    // the staircase, and the default alone is ~±0.6 LSB against the ~1 LSB it needs. exp == 0: the
    // same pass carries the full coefficient, already per screen pixel.
    private var stackDitherEffect: RenderEffect? = null
    private var stackDitherCoeff = Float.NaN

    // True while the stack dither covers the noise duty (coefficient ≤ [NOISE_GRAIN_THRESHOLD]):
    // the full-resolution noise round-trip is skipped. Deliberate grain keeps that pass, so grain
    // size stays one screen pixel.
    private var stackDitherChained = false

    private fun obtainStackDitherEffect(coeff: Float): RenderEffect {
        val cached = stackDitherEffect
        if (cached != null && stackDitherCoeff == coeff) return cached
        val shader = effectScope.obtainRuntimeShader("NoiseDither", NOISE_DITHER_SHADER)
        shader.setFloatUniform("noise_coeff", coeff)
        return runtimeShaderEffect(shader, "child").also {
            stackDitherEffect = it
            stackDitherCoeff = coeff
        }
    }

    // Sharp overlay effect + band bbox (the only full-resolution region), cached on gradient, size
    // and radii/exp. A null effect with matching keys means the band misses the component.
    private var sharpOverlayEffect: RenderEffect? = null
    private var sharpOverlayGradient: ProgressiveBlur? = null
    private var sharpOverlayW = Float.NaN
    private var sharpOverlayH = Float.NaN
    private var sharpRadiusX = Float.NaN
    private var sharpRadiusY = Float.NaN
    private var sharpExp = -1
    private var sharpBandOffset = IntOffset.Zero
    private var sharpBandSize = IntSize.Zero

    private fun DrawScope.drawBlurredBackdrop() {
        renderBlurInto(primary)
    }

    /**
     * Records the backdrop's ramp band into [sharpLayer] at full resolution and draws the overlay
     * over the level stack: a loop blur ramping to zero, so the composite ends genuinely sharp.
     */
    private fun DrawScope.drawSharpOverlay() {
        val bandSize = sharpBandSize
        if (bandSize.width <= 0 || bandSize.height <= 0) return
        val bandOffset = sharpBandOffset
        val layer = sharpLayer ?: graphicsContext.createGraphicsLayer().also { sharpLayer = it }
        recordLayer(layer, size = bandSize) {
            translate(-bandOffset.x.toFloat(), -bandOffset.y.toFloat()) {
                onDrawBackdrop {
                    with(backdrop) {
                        drawBackdrop(
                            density = effectScope,
                            coordinates = layoutCoordinates,
                            layerBlock = layerBlock,
                            downscaleFactor = 1f,
                        )
                    }
                }
            }
        }
        layer.renderEffect = sharpOverlayEffect
        layer.topLeft = bandOffset
        drawLayer(layer)
    }

    override fun ContentDrawScope.draw() {
        if (!enabled) {
            drawContent()
            return
        }
        if (effectScope.update(this)) {
            updateEffects()
        }
        onDrawBehind?.invoke(this)

        drawBlurredBackdrop()
        if (sharpOverlayActive) {
            drawSharpOverlay()
        }

        onDrawSurface?.invoke(this)

        if (contentBlendMode == BlendMode.SrcOver) {
            drawContent()
        } else {
            val w = size.width
            val h = size.height
            val bounds = contentBoundsRect?.takeIf { it.right == w && it.bottom == h } ?: Rect(0f, 0f, w, h).also { contentBoundsRect = it }
            contentPaint.blendMode = contentBlendMode
            drawContext.canvas.saveLayer(bounds, contentPaint)
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
        if (!enabled) return
        primary.ensureMain()

        // Gradient mode: progressiveBlur() records radii + downscale and splits the chain; the
        // stack is assembled below once the post chain is known. Re-derives as false once a
        // platform reports the stack unsupported.
        effectScope.progressiveCompositeActive =
            progressiveGradient != null && !(compositeAttempted && !compositeSupported)

        effectScope.apply(effects)
        if (effectScope.progressiveCompositeActive) {
            // Noise is finalize's duty here — chained after the assembled stack, not into the
            // post chain (which the composite ramp-weights).
            finalizeProgressiveComposite()
        } else {
            chainFullResNoiseIfNeeded()
            sharpOverlayActive = false
            stackDitherChained = false
        }
        primary.mainLayer?.renderEffect = effectScope.renderEffect
        primary.padding = effectScope.padding
        primary.downscaleFactor = effectScope.downscaleFactor.coerceAtLeast(1f)
    }

    /**
     * Composite mode: progressiveBlur() recorded the radii + downscale and split the chain; this
     * assembles the level-stack effect for [primary] and the full-res overlay for [sharpLayer].
     * Falls back to running the effects uniformly when the platform can't express the stack.
     */
    private fun finalizeProgressiveComposite() {
        val scope = effectScope
        val gradient = progressiveGradient
        val rX = scope.cachedProgRadiusX
        val rY = scope.cachedProgRadiusY
        if (gradient == null || rX.isNaN() || rY.isNaN()) {
            // No progressiveBlur call in the effects block: draw uniformly, gradient ignored.
            chainFullResNoiseIfNeeded()
            sharpOverlayActive = false
            stackDitherChained = false
            return
        }
        val paddedW = scope.cachedProgSizeW
        val paddedH = scope.cachedProgSizeH
        val pre = scope.progressivePreEffect
        var post = scope.renderEffect
        var postBlend = scope.progressivePostBlend
        if (postBlend != null && post != null) {
            // Mixed post chain (effects chained after the recorded blend): restore the blend at
            // the chain head and take the general branch form for the whole chain.
            post = obtainBlendColorsEffect(scope, postBlend).chain(post)
            postBlend = null
        }
        // exp > 0: pre-quantization dither capped at the anti-banding amplitude (the aesthetic
        // remainder renders per screen pixel via the upscale noise pass); exp == 0: the layer is
        // already full resolution, so this slot carries the full coefficient.
        val noiseCoeff = scope.noiseCoefficient
        val ditherCoeff = when {
            noiseCoeff <= 0f -> 0f
            scope.cachedProgExp > 0 -> minOf(noiseCoeff * 2f, STACK_DITHER_MAX)
            else -> noiseCoeff
        }
        val stack = if (cachedStackEffect != null &&
            cachedStackRadiusX == rX &&
            cachedStackRadiusY == rY &&
            cachedStackW == paddedW &&
            cachedStackH == paddedH &&
            cachedStackGradient == gradient &&
            cachedStackPre === pre &&
            cachedStackPost === post &&
            cachedStackPostBlend == postBlend &&
            cachedStackDither == ditherCoeff
        ) {
            cachedStackEffect
        } else {
            createProgressiveStackEffect(
                rX,
                rY,
                scope.cachedProgExp,
                scope.padding,
                scope.size.width,
                scope.size.height,
                gradient.angle,
                gradient.startFraction,
                gradient.endFraction,
                gradient.curve,
                pre,
                post,
                postBlend,
                scope,
            )?.let { built ->
                if (ditherCoeff > 0f) built.chain(obtainStackDitherEffect(ditherCoeff)) else built
            }?.also {
                cachedStackEffect = it
                cachedStackRadiusX = rX
                cachedStackRadiusY = rY
                cachedStackW = paddedW
                cachedStackH = paddedH
                cachedStackGradient = gradient
                cachedStackPre = pre
                cachedStackPost = post
                cachedStackPostBlend = postBlend
                cachedStackDither = ditherCoeff
            }
        }
        compositeAttempted = true
        compositeSupported = stack != null
        if (stack == null) {
            // Unsupported: rebuild the whole chain with the flag re-derived as false, so
            // progressiveBlur() falls back to its uniform loop-shader build.
            scope.progressiveCompositeActive = false
            scope.apply(effects)
            chainFullResNoiseIfNeeded()
            sharpOverlayActive = false
            stackDitherChained = false
            return
        }
        scope.renderEffect = stack
        stackDitherChained = ditherCoeff > 0f && noiseCoeff <= NOISE_GRAIN_THRESHOLD

        val sw = scope.size.width
        val sh = scope.size.height
        val exp = scope.cachedProgExp
        if (exp == 0) {
            // nativeSharpEnd already bound the clear end to the native source — no overlay needed.
            sharpOverlayActive = false
            return
        }
        if (sharpOverlayGradient != gradient || sharpOverlayW != sw || sharpOverlayH != sh ||
            sharpRadiusX != rX || sharpRadiusY != rY || sharpExp != exp
        ) {
            // The bbox margin covers the overlay loop blur's tap reach so edge-clamp smear stays
            // on rows/columns the ramp mask zeroes out; quantized to the loop's tap bound
            // (multiples of 4, ≥ the reach) so an animating radius doesn't resize the band and
            // reallocate the overlay layer's backing texture.
            val loopReach =
                (progressiveLoopTapBound(maxOf(progressiveSharpLoopRadius(rX, exp), progressiveSharpLoopRadius(rY, exp))) + 1).toFloat()
            val band = progressiveSharpBandBounds(
                sw,
                sh,
                gradient.angle,
                gradient.startFraction,
                gradient.endFraction,
                gradient.curve,
                margin = loopReach,
            )
            if (band == null || band.width < 1f || band.height < 1f) {
                sharpOverlayEffect = null
                sharpBandOffset = IntOffset.Zero
                sharpBandSize = IntSize.Zero
            } else {
                val left = floor(band.left).toInt()
                val top = floor(band.top).toInt()
                sharpBandOffset = IntOffset(left, top)
                sharpBandSize = IntSize(
                    (ceil(band.right).toInt() - left).coerceAtLeast(1),
                    (ceil(band.bottom).toInt() - top).coerceAtLeast(1),
                )
                sharpOverlayEffect = createProgressiveSharpOverlayEffect(
                    rX,
                    rY,
                    exp,
                    sw,
                    sh,
                    left.toFloat(),
                    top.toFloat(),
                    sharpBandSize.width.toFloat(),
                    sharpBandSize.height.toFloat(),
                    gradient.angle,
                    gradient.startFraction,
                    gradient.endFraction,
                    gradient.curve,
                    scope,
                )
            }
            sharpOverlayGradient = gradient
            sharpOverlayW = sw
            sharpOverlayH = sh
            sharpRadiusX = rX
            sharpRadiusY = rY
            sharpExp = exp
        }
        sharpOverlayActive = sharpOverlayEffect != null
    }

    /**
     * Full-res path: chains noise into the RenderEffect; the downscaled path defers it to
     * [drawUpscaledLayer] so each screen pixel dithers independently (unless [stackDitherChained]).
     * Cached on input identity + coefficient, so repeated [updateEffects] runs don't allocate.
     */
    private fun chainFullResNoiseIfNeeded() {
        val noiseCoeff = effectScope.noiseCoefficient
        if (noiseCoeff > 0f && effectScope.downscaleFactor <= 1f) {
            val input = effectScope.renderEffect
            if (effectScope.cachedNoiseChainResult != null &&
                effectScope.cachedNoiseChainInput === input &&
                effectScope.cachedNoiseChainCoeff == noiseCoeff
            ) {
                effectScope.renderEffect = effectScope.cachedNoiseChainResult
                return
            }
            effectScope.runtimeShaderEffect(
                key = "NoiseDither",
                shaderString = NOISE_DITHER_SHADER,
                uniformShaderName = "child",
            ) {
                setFloatUniform("noise_coeff", noiseCoeff)
            }
            effectScope.cachedNoiseChainInput = input
            effectScope.cachedNoiseChainCoeff = noiseCoeff
            effectScope.cachedNoiseChainResult = effectScope.renderEffect
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
     * Sets [source]'s downsample effect (cached per [target]/[slot]) and records [dest] at
     * [destSize] with [source] drawn at [scale]. The filter stays attached — the recorded
     * destination references the layer, so detaching skips the prefilter — and
     * [LevelTarget.clearStepFilters] drops it next frame.
     */
    private fun DrawScope.applyDownsampleStep(
        target: LevelTarget,
        slot: Int,
        source: GraphicsLayer,
        sourceSize: IntSize,
        dest: GraphicsLayer,
        destSize: IntSize,
        scale: Float,
        shaderKey: String,
        shaderSrc: String,
    ) {
        source.renderEffect = target.downsampleEffect(slot, shaderKey, sourceSize.width, sourceSize.height) {
            runtimeShaderEffect(
                runtimeShader = effectScope.obtainRuntimeShader(shaderKey, shaderSrc).apply {
                    setFloatUniform("maxCoord", sourceSize.width - 0.5f, sourceSize.height - 0.5f)
                },
                uniformShaderName = "child",
            )
        }
        recordLayer(dest, size = destSize) {
            scale(scale, scale, Offset.Zero) { drawLayer(source) }
        }
    }

    /**
     * Upscales [layer] (recorded at [texelScale] texels per surface px) to full resolution. With
     * noise active — and not carried by the stack ([stackDitherChained]) — the result is staged in
     * [target]'s noise layer first, so noise lands per screen pixel rather than per scaled block.
     */
    private fun DrawScope.drawUpscaledLayer(
        target: LevelTarget,
        layer: GraphicsLayer,
        texelScale: Float,
        currentPadding: Float,
        fullWidth: Int,
        fullHeight: Int,
    ) {
        val noiseCoeff = if (stackDitherChained) 0f else effectScope.noiseCoefficient
        val residualX = backdrop.offsetResidualX
        val residualY = backdrop.offsetResidualY
        if (noiseCoeff > 0f) {
            layer.topLeft = IntOffset.Zero
            val noiseL = target.noiseLayer
                ?: graphicsContext.createGraphicsLayer().also { target.noiseLayer = it }
            recordLayer(noiseL, size = IntSize(fullWidth, fullHeight)) {
                scale(texelScale, texelScale, Offset.Zero) { drawLayer(layer) }
            }
            noiseL.renderEffect = target.noiseRenderEffect(noiseCoeff) {
                val noiseShader = effectScope.obtainRuntimeShader("NoiseDither", NOISE_DITHER_SHADER)
                noiseShader.setFloatUniform("noise_coeff", noiseCoeff)
                runtimeShaderEffect(noiseShader, "child")
            }
            drawShiftedLayer(noiseL, currentPadding, 1f, residualX, residualY)
            noiseL.renderEffect = null
        } else {
            drawShiftedLayer(layer, currentPadding / texelScale, texelScale, residualX, residualY)
        }
    }

    /**
     * Draws [layer] with its content origin exactly [padding] layer-texels before this surface's
     * origin: the integral part via [GraphicsLayer.topLeft], the fraction via a draw-time
     * translate. Snapping the fraction moved the picture a pixel every time the padding's integer
     * part changed — a stepping shimmer during a radius sweep.
     */
    private fun DrawScope.drawShiftedLayer(
        layer: GraphicsLayer,
        padding: Float,
        texelScale: Float,
        residualX: Float = 0f,
        residualY: Float = 0f,
    ) {
        val whole = floor(padding)
        layer.topLeft =
            if (padding != 0f) {
                IntOffset(-whole.toInt(), -whole.toInt())
            } else {
                IntOffset.Zero
            }
        val fraction = (padding - whole) * texelScale
        translate(-residualX - fraction, -residualY - fraction) {
            scale(texelScale, texelScale, Offset.Zero) { drawLayer(layer) }
        }
    }

    /**
     * Drops the composite layer and its caches when [progressiveGradient] is cleared, so the
     * full-resolution offscreen layer doesn't outlive the gradient mode.
     */
    fun releaseCompositeResources() {
        sharpLayer?.let { graphicsContext.releaseGraphicsLayer(it) }
        sharpLayer = null
        sharpOverlayActive = false
        cachedStackEffect = null
        cachedStackRadiusX = Float.NaN
        cachedStackRadiusY = Float.NaN
        cachedStackW = Float.NaN
        cachedStackH = Float.NaN
        cachedStackGradient = null
        cachedStackPre = null
        cachedStackPost = null
        cachedStackPostBlend = null
        cachedStackDither = Float.NaN
        stackDitherEffect = null
        stackDitherCoeff = Float.NaN
        stackDitherChained = false
        sharpOverlayEffect = null
        sharpOverlayGradient = null
        sharpOverlayW = Float.NaN
        sharpOverlayH = Float.NaN
        sharpRadiusX = Float.NaN
        sharpRadiusY = Float.NaN
        sharpExp = -1
        sharpBandOffset = IntOffset.Zero
        sharpBandSize = IntSize.Zero
    }

    fun releaseGraphicsLayers() {
        primary.release()
        releaseCompositeResources()
        effectScope.reset()
    }

    override fun onDetach() {
        releaseGraphicsLayers()
        layoutCoordinates = null
    }
}
