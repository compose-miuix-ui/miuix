// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.node.invalidateMeasurement
import androidx.compose.ui.node.invalidatePlacement
import androidx.compose.ui.node.observeReads
import androidx.compose.ui.node.requireGraphicsContext
import androidx.compose.ui.node.updateLayerBlock
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.util.fastRoundToInt
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.blur.internal.DOWNSAMPLE_2X_SHADER
import top.yukonga.miuix.kmp.blur.internal.DOWNSAMPLE_4X_SHADER
import top.yukonga.miuix.kmp.blur.internal.NOISE_DITHER_SHADER
import top.yukonga.miuix.kmp.blur.internal.ShapeProvider
import top.yukonga.miuix.kmp.blur.internal.chain
import top.yukonga.miuix.kmp.blur.internal.computeProgressiveEdgePadding
import top.yukonga.miuix.kmp.blur.internal.createProgressiveSharpOverlayEffect
import top.yukonga.miuix.kmp.blur.internal.progressiveMidSharpLoopRadius
import top.yukonga.miuix.kmp.blur.internal.progressiveMidSharpThreshold
import top.yukonga.miuix.kmp.blur.internal.progressiveSharpBandBounds
import top.yukonga.miuix.kmp.blur.internal.progressiveSharpLoopRadius
import top.yukonga.miuix.kmp.blur.internal.progressiveSharpThreshold
import top.yukonga.miuix.kmp.blur.internal.recordLayer
import top.yukonga.miuix.kmp.blur.internal.runtimeShaderEffect
import top.yukonga.miuix.kmp.material.LocalRuntimeShaderCache
import top.yukonga.miuix.kmp.material.internal.graphicsResourceLifecycle
import top.yukonga.miuix.kmp.material.internal.graphicsResourcesResetOnResume
import top.yukonga.miuix.kmp.material.internal.observeGraphicsResourceResets
import top.yukonga.miuix.kmp.material.internal.refreshGraphicsResourceGeneration
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import androidx.compose.ui.graphics.layer.CompositingStrategy as LayerCompositingStrategy

private val DefaultOnDrawBackdrop: DrawScope.(DrawScope.() -> Unit) -> Unit = { it() }
private val EmptyEffects: BackdropEffectScope.() -> Unit = {}
private val DefaultShapeBlock: () -> Shape = { androidx.compose.ui.graphics.RectangleShape }

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

/**
 * A backdrop pipeline described as values.
 *
 * [Modifier.drawBackdrop] compares callbacks by identity, so a pipeline assembled from lambdas is
 * rebuilt whenever the caller recomposes. Implement this interface instead to describe the same
 * pipeline with a value type: the modifier node is kept while two instances compare equal.
 */
interface BackdropPipeline {
    /** Silhouette read on each draw, so an animated shape stays current. */
    val shape: Shape

    /** Configures the blur, colour and shading passes. */
    fun configureEffects(scope: BackdropEffectScope)

    /** Draws above the content, such as a rim or mask. */
    fun drawFront(scope: DrawScope) {}

    /** Whether [drawFront] uses destination blending that must be isolated from the parent canvas. */
    val requiresOffscreenCompositing: Boolean get() = false

    /** Optional progressive gradient for multi-level composite rendering. */
    val progressiveGradient: ProgressiveBlur? get() = null
}

/** Applies [pipeline] as a value-equal backdrop, so unchanged inputs keep the node. */
fun Modifier.drawBackdrop(
    backdrop: Backdrop,
    pipeline: BackdropPipeline,
    contentBlendMode: BlendMode = BlendMode.SrcOver,
    enabled: Boolean = true,
): Modifier {
    val effectiveEnabled = enabled && isRuntimeShaderSupported()
    return this.then(
        BackdropPipelineElement(
            backdrop = backdrop,
            pipeline = pipeline,
            contentBlendMode = contentBlendMode,
            enabled = effectiveEnabled,
        ),
    )
}

private class BackdropPipelineElement(
    val backdrop: Backdrop,
    val pipeline: BackdropPipeline,
    val contentBlendMode: BlendMode,
    val enabled: Boolean,
) : ModifierNodeElement<DrawBackdropNode>() {

    override fun create(): DrawBackdropNode = DrawBackdropNode(
        backdrop = backdrop,
        shape = DefaultShapeBlock,
        effects = EmptyEffects,
        layerBlock = null,
        onDrawBehind = null,
        onDrawBackdrop = DefaultOnDrawBackdrop,
        onDrawSurface = null,
        onDrawFront = null,
        contentBlendMode = contentBlendMode,
        progressiveGradient = pipeline.progressiveGradient,
        enabled = enabled,
    ).apply {
        updatePipeline(pipeline)
    }

    override fun update(node: DrawBackdropNode) {
        val enabledChanged = node.enabled != enabled
        val pipelineChanged = node.pipelineRef != pipeline
        val blendChanged = node.contentBlendMode != contentBlendMode
        node.backdrop = backdrop
        if (pipelineChanged) {
            node.updatePipeline(pipeline)
        }
        node.contentBlendMode = contentBlendMode
        node.progressiveGradient = pipeline.progressiveGradient
        node.enabled = enabled
        if (enabledChanged) {
            if (!enabled) {
                node.releaseGraphicsLayers()
            }
            node.invalidateMeasurement()
        }
        if (!enabled) {
            node.invalidateDraw()
            return
        }
        if (pipelineChanged || enabledChanged) {
            node.invalidateDrawCache()
        } else if (blendChanged) {
            node.invalidateDraw()
            node.invalidatePlacement()
        } else {
            node.invalidateDraw()
        }
    }
    override fun InspectorInfo.inspectableProperties() {
        name = "drawBackdrop"
        properties["backdrop"] = backdrop
        properties["pipeline"] = pipeline
        properties["enabled"] = enabled
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BackdropPipelineElement) return false
        if (backdrop != other.backdrop) return false
        if (pipeline != other.pipeline) return false
        if (contentBlendMode != other.contentBlendMode) return false
        if (enabled != other.enabled) return false
        return true
    }

    override fun hashCode(): Int {
        var result = backdrop.hashCode()
        result = 31 * result + pipeline.hashCode()
        result = 31 * result + contentBlendMode.hashCode()
        result = 31 * result + enabled.hashCode()
        return result
    }
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
        if (enabledChanged) {
            if (!enabled) {
                node.releaseGraphicsLayers()
            }
            node.invalidateMeasurement()
        }
        if (enabled) {
            node.invalidateDrawCache()
        } else {
            node.invalidateDraw()
        }
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

    var pipelineRef: BackdropPipeline? = null
        private set
    private var requiresOffscreenCompositing by mutableStateOf(false)

    private val pipelineShape: () -> Shape = { pipelineRef!!.shape }
    private val pipelineEffects: BackdropEffectScope.() -> Unit = { pipelineRef!!.configureEffects(this) }
    private val pipelineDrawFront: DrawScope.() -> Unit = { pipelineRef!!.drawFront(this) }

    fun updatePipeline(pipeline: BackdropPipeline) {
        pipelineRef = pipeline
        requiresOffscreenCompositing = pipeline.requiresOffscreenCompositing
        shapeProvider.shapeBlock = pipelineShape
        effects = pipelineEffects
        onDrawFront = pipelineDrawFront
    }

    fun updateShape(shape: () -> Shape) {
        pipelineRef = null
        requiresOffscreenCompositing = false
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
        var padLeft: Float = 0f
        var padTop: Float = 0f
        var padRight: Float = 0f
        var padBottom: Float = 0f

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

    private inner class SharpBandOverlay(private val downScale: Int) {
        var layer: GraphicsLayer? = null
        var active = false
        private var effect: RenderEffect? = null
        private var cachedGradient: ProgressiveBlur? = null
        private var cachedW = Float.NaN
        private var cachedH = Float.NaN
        private var cachedPre: RenderEffect? = null
        private var cachedRadiusX = Float.NaN
        private var cachedRadiusY = Float.NaN
        private var cachedExp = -1
        private var bandOffset = IntOffset.Zero
        private var bandTexSize = IntSize.Zero

        fun update(
            scope: BackdropEffectScopeImpl,
            gradient: ProgressiveBlur,
            sw: Float,
            sh: Float,
            maxRX: Float,
            maxRY: Float,
            exp: Int,
            threshold: Float,
            midDecay: Boolean,
            pre: RenderEffect?,
        ) {
            if (maxRX < 0.5f && maxRY < 0.5f) {
                active = false
                return
            }
            val isMaskMode = !midDecay && downScale == 1 && maxRX < 5.5f && maxRY < 5.5f
            val keyRX = if (isMaskMode) -1f else maxRX
            val keyRY = if (isMaskMode) -1f else maxRY
            if (cachedGradient != gradient || cachedW != sw || cachedH != sh ||
                cachedPre !== pre || cachedRadiusX != keyRX || cachedRadiusY != keyRY || cachedExp != exp
            ) {
                val dsFloat = downScale.toFloat()
                val loopReach = if (isMaskMode) {
                    1f
                } else {
                    (ceil(maxOf(maxRX, maxRY)).toInt() + 1) * dsFloat
                }
                val band = progressiveSharpBandBounds(
                    threshold = threshold,
                    compW = sw,
                    compH = sh,
                    angleDeg = gradient.angle,
                    startFraction = gradient.startFraction,
                    endFraction = gradient.endFraction,
                    curve = gradient.curve,
                    margin = loopReach,
                )
                if (band == null || band.width < 1f || band.height < 1f) {
                    effect = null
                    bandOffset = IntOffset.Zero
                    bandTexSize = IntSize.Zero
                } else {
                    val left = (floor(band.left).toInt() / downScale) * downScale
                    val top = (floor(band.top).toInt() / downScale) * downScale
                    val texW = ((ceil(band.right).toInt() - left + downScale - 1) / downScale).coerceAtLeast(1)
                    val texH = ((ceil(band.bottom).toInt() - top + downScale - 1) / downScale).coerceAtLeast(1)
                    bandOffset = IntOffset(left, top)
                    bandTexSize = IntSize(texW, texH)
                    effect = createProgressiveSharpOverlayEffect(
                        maxRadiusX = maxRX,
                        maxRadiusY = maxRY,
                        downScale = dsFloat,
                        threshold = threshold,
                        midDecay = midDecay,
                        compW = sw,
                        compH = sh,
                        originX = left.toFloat(),
                        originY = top.toFloat(),
                        bandW = texW.toFloat(),
                        bandH = texH.toFloat(),
                        angleDeg = gradient.angle,
                        startFraction = gradient.startFraction,
                        endFraction = gradient.endFraction,
                        curve = gradient.curve,
                        preEffect = pre,
                        scope = scope,
                    )
                }
                cachedGradient = gradient
                cachedW = sw
                cachedH = sh
                cachedPre = pre
                cachedRadiusX = keyRX
                cachedRadiusY = keyRY
                cachedExp = exp
            }
            active = effect != null
        }

        fun DrawScope.draw(
            shared2xSource: GraphicsLayer? = null,
            sharedPadLeft: Float = 0f,
            sharedPadTop: Float = 0f,
        ) {
            if (!active) return
            val texSize = bandTexSize
            if (texSize.width <= 0 || texSize.height <= 0) return
            val offset = bandOffset
            val dsFloat = downScale.toFloat()
            val targetLayer = layer ?: graphicsContext.createGraphicsLayer().also { layer = it }
            if (downScale == 2 && shared2xSource != null) {
                recordLayer(targetLayer, size = texSize) {
                    translate(-(sharedPadLeft + offset.x) / 2f, -(sharedPadTop + offset.y) / 2f) {
                        drawLayer(shared2xSource)
                    }
                }
            } else {
                recordLayer(targetLayer, size = texSize) {
                    translate(-offset.x / dsFloat, -offset.y / dsFloat) {
                        onDrawBackdrop {
                            with(backdrop) {
                                drawBackdrop(
                                    density = effectScope,
                                    coordinates = layoutCoordinates,
                                    layerBlock = layerBlock,
                                    downscaleFactor = dsFloat,
                                )
                            }
                        }
                    }
                }
            }
            targetLayer.renderEffect = effect
            if (downScale == 1) {
                targetLayer.topLeft = offset
                drawLayer(targetLayer)
            } else {
                targetLayer.topLeft = IntOffset.Zero
                translate(offset.x.toFloat(), offset.y.toFloat()) {
                    scale(dsFloat, dsFloat, Offset.Zero) { drawLayer(targetLayer) }
                }
            }
        }

        fun release() {
            layer?.let { graphicsContext.releaseGraphicsLayer(it) }
            layer = null
            active = false
            effect = null
            cachedGradient = null
            cachedPre = null
            cachedW = Float.NaN
            cachedH = Float.NaN
            cachedRadiusX = Float.NaN
            cachedRadiusY = Float.NaN
            cachedExp = -1
            bandOffset = IntOffset.Zero
            bandTexSize = IntSize.Zero
        }
    }

    private val midSharpOverlay = SharpBandOverlay(downScale = 2)
    private val sharpOverlay = SharpBandOverlay(downScale = 1)
    private val primary = LevelTarget()
    private val layoutLayerBlock: GraphicsLayerScope.() -> Unit = {
        clip = true
        shape = shapeProvider.shape
        compositingStrategy = if (contentBlendMode != BlendMode.SrcOver || requiresOffscreenCompositing) {
            CompositingStrategy.Offscreen
        } else {
            CompositingStrategy.Auto
        }
    }

    private var layoutCoordinates: LayoutCoordinates? by mutableStateOf(null, neverEqualPolicy())

    private fun DrawScope.recordBackdrop(target: LevelTarget) {
        val scaleFactor = target.cascadeFirstStepScale
        // Exact (fractional) padding: snapping it shifts the recording by up to a texel whenever
        // `reach × level` crosses an integer.
        val scaledPadX = if (target.padLeft == 0f) 0f else target.padLeft / scaleFactor
        val scaledPadY = if (target.padTop == 0f) 0f else target.padTop / scaleFactor
        translate(scaledPadX, scaledPadY) {
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
        val padL = target.padLeft
        val padT = target.padTop
        val level = target.downscaleFactor
        val fullWidth = (size.width + padL + target.padRight).toInt()
        val fullHeight = (size.height + padT + target.padBottom).toInt()

        // Texel size of the padded region recorded at [scale].
        fun recordedSize(scale: Float): IntSize = IntSize(
            (fullWidth / scale).fastRoundToInt().coerceAtLeast(1),
            (fullHeight / scale).fastRoundToInt().coerceAtLeast(1),
        )

        // Filters from an earlier frame must not survive into this one: the cascade layers are
        // reused across frames and swap between destination and source roles.
        target.clearStepFilters()

        if (level <= 1.001f) {
            target.cascadeFirstStepScale = 1f
            if (layer.renderEffect == null && padL == 0f && padT == 0f && target.padRight == 0f && target.padBottom == 0f) {
                recordBackdrop(target)
            } else {
                recordLayer(layer, size = IntSize(fullWidth, fullHeight)) { recordBackdrop(target) }
                drawShiftedLayer(layer, padL, padT, 1f)
            }
            return
        }

        if (level <= 2.001f) {
            // Half or finer: a single bilinear record straight to the level.
            target.cascadeFirstStepScale = level
            recordLayer(layer, size = recordedSize(level)) { recordBackdrop(target) }
            drawUpscaledLayer(target, layer, level, padL, padT)
            return
        }

        // Step 0: record the backdrop at half size (GPU bilinear, no shader).
        target.cascadeFirstStepScale = 2f
        var source = target.obtainCascade(0)
        var sourceSize = recordedSize(2f)
        source.compositingStrategy = if (midSharpOverlay.active) {
            LayerCompositingStrategy.Offscreen
        } else {
            LayerCompositingStrategy.Auto
        }
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

        drawUpscaledLayer(target, layer, level, padL, padT)
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

    // Set once updateEffects() has run with a measured size, i.e. the effect observer is live.
    private var effectsSized = false

    // Dither chained after the assembled ramp, cached on its coefficient.
    private var stackDitherEffect: RenderEffect? = null
    private var stackDitherCoeff = Float.NaN

    // True while the downscaled dither covers the noise duty (coefficient ≤ [NOISE_GRAIN_THRESHOLD]):
    // the full-resolution noise round-trip is skipped.
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

    private var progBasePadLeft = 0f
    private var progBasePadTop = 0f
    private var progBasePadRight = 0f
    private var progBasePadBottom = 0f

    private fun DrawScope.clampProgressivePaddingToBackdrop() {
        if (!effectScope.progressiveRampActive || effectScope.progressivePreEffect != null || layerBlock != null) return
        val layerBackdrop = backdrop as? LayerBackdrop ?: return
        val layerCoords = layerBackdrop.layerCoordinates?.takeIf { it.isAttached } ?: return
        val coords = layoutCoordinates?.takeIf { it.isAttached } ?: return
        val pos = try {
            layerCoords.localPositionOf(coords)
        } catch (_: Exception) {
            return
        }
        val layerW = layerCoords.size.width.toFloat()
        val layerH = layerCoords.size.height.toFloat()
        val availL = pos.x.coerceAtLeast(0f)
        val availT = pos.y.coerceAtLeast(0f)
        val availR = (layerW - (pos.x + size.width)).coerceAtLeast(0f)
        val availB = (layerH - (pos.y + size.height)).coerceAtLeast(0f)
        val nextL = minOf(progBasePadLeft, availL)
        val nextT = minOf(progBasePadTop, availT)
        val nextR = minOf(progBasePadRight, availR)
        val nextB = minOf(progBasePadBottom, availB)
        if (nextL != primary.padLeft || nextT != primary.padTop || nextR != primary.padRight || nextB != primary.padBottom) {
            primary.padLeft = nextL
            primary.padTop = nextT
            primary.padRight = nextR
            primary.padBottom = nextB
            rebuildPrimaryProgressiveEffect(nextL, nextT, nextR, nextB)
        }
    }

    private fun DrawScope.drawBlurredBackdrop() {
        clampProgressivePaddingToBackdrop()
        renderBlurInto(primary)
    }

    override fun ContentDrawScope.draw() {
        if (!enabled) {
            drawContent()
            return
        }
        if (effectScope.update(this)) {
            // Attach skipped construction for lack of a size; re-observe with real geometry.
            if (effectsSized) updateEffects() else observeEffects()
        }
        onDrawBehind?.invoke(this)

        drawBlurredBackdrop()
        val shared2x = if (midSharpOverlay.active && primary.downscaleFactor > 2.001f) primary.cascadeLayers.firstOrNull() else null
        with(midSharpOverlay) { draw(shared2x, primary.padLeft, primary.padTop) }
        with(sharpOverlay) { draw() }
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
        if (!enabled) return
        observeEffects()
        if (isAttached && effectsSized) {
            // placeWithLayer inside an ancestor GraphicsLayer (e.g. squircleSurface) can skip
            // re-recording draw() on invalidateDraw() alone, leaving stale padding/downscale in
            // mainLayer paired with the updated renderEffect.
            invalidateDraw()
            invalidatePlacement()
        }
    }

    private fun observeEffects() {
        observeReads {
            if (graphicsResourcesResetOnResume) {
                val cache = currentValueOf(LocalRuntimeShaderCache)
                if (effectScope.runtimeShaderCache !== cache) {
                    releaseGraphicsLayers()
                    effectScope.runtimeShaderCache = cache
                }
                observeLifecycle(graphicsResourceLifecycle())
            }
            // No size at attach time: building here would create an effect from NaN dimensions.
            if (effectScope.size.width.isNaN() || effectScope.size.height.isNaN()) return@observeReads
            updateEffects()
            effectsSized = true
        }
    }

    private var lifecycle: Lifecycle? = null
    private var stopObservingResets: (() -> Unit)? = null
    private var resumeJob: Job? = null

    private fun observeLifecycle(ownerLifecycle: Lifecycle?) {
        if (lifecycle === ownerLifecycle) return
        stopObservingLifecycle()
        lifecycle = ownerLifecycle
        if (ownerLifecycle == null) return
        stopObservingResets = observeGraphicsResourceResets(ownerLifecycle) { generation ->
            resumeJob?.cancel()
            recreateGraphicsResources()
            resumeJob = coroutineScope.launch {
                // HWUI can discard resources after ON_RESUME; refresh shaders and layers again.
                withFrameNanos { }
                withFrameNanos { }
                if (ownerLifecycle.currentState == Lifecycle.State.RESUMED) {
                    refreshGraphicsResourceGeneration(generation)
                    recreateGraphicsResources()
                }
            }
        }
    }

    private fun stopObservingLifecycle() {
        resumeJob?.cancel()
        resumeJob = null
        stopObservingResets?.invoke()
        stopObservingResets = null
        lifecycle = null
    }

    private fun recreateGraphicsResources() {
        if (!enabled) return
        releaseGraphicsLayers()
        effectsSized = false
        // Drop the layer that contains the drawn content so placement records it into a fresh layer.
        updateLayerBlock(null)
        invalidatePlacement()
        invalidateDraw()
    }

    private fun updateEffects() {
        if (!enabled) return
        primary.ensureMain()

        effectScope.progressiveRampActive = progressiveGradient != null
        effectScope.apply(effects)
        if (effectScope.progressiveRampActive) {
            finalizeProgressiveRamp()
        } else {
            chainFullResNoiseIfNeeded()
            stackDitherChained = false
            midSharpOverlay.active = false
            sharpOverlay.active = false
            primary.padLeft = effectScope.padding
            primary.padTop = effectScope.padding
            primary.padRight = effectScope.padding
            primary.padBottom = effectScope.padding
        }
        primary.mainLayer?.renderEffect = effectScope.renderEffect
        primary.downscaleFactor = effectScope.downscaleFactor.coerceAtLeast(1f)
    }

    private fun rebuildPrimaryProgressiveEffect(
        padLeft: Float,
        padTop: Float,
        padRight: Float,
        padBottom: Float,
    ) {
        val scope = effectScope
        val gradient = progressiveGradient ?: return
        val rX = scope.progRecordedRadiusX
        val rY = scope.progRecordedRadiusY
        val exp = scope.progRecordedExp
        if (rX.isNaN() || rY.isNaN() || exp < 0) return
        val pre = scope.progressivePreEffect
        val post = progPostEffect
        val postBlend = progRampBlend
        val inlineTint = postBlend.singleSrcOverColorOrUnspecified()
        val noiseCoeff = scope.noiseCoefficient
        val ditherCoeff = if (exp > 0 && noiseCoeff > 0f && noiseCoeff <= NOISE_GRAIN_THRESHOLD) {
            minOf(noiseCoeff * 2f, STACK_DITHER_MAX)
        } else {
            0f
        }
        val inlineDitherCoeff = if (post == null && (postBlend == null || inlineTint != Color.Unspecified)) ditherCoeff else 0f
        val blurEffect = obtainProgressiveBlurEffect(
            scope = scope,
            radiusX = rX,
            radiusY = rY,
            exp = exp,
            gradient = gradient,
            padLeft = padLeft,
            padTop = padTop,
            padRight = padRight,
            padBottom = padBottom,
            tintColor = inlineTint,
            ditherCoeff = inlineDitherCoeff,
        )
        var chain = pre?.let { if (blurEffect != null) it.chain(blurEffect) else it } ?: blurEffect
        if (postBlend != null && (blurEffect == null || inlineTint == Color.Unspecified)) {
            val rampBlend = buildRampBlendColorsEffect(
                scope = scope,
                colors = postBlend,
                gradVecX = scope.progGradVecX,
                gradVecY = scope.progGradVecY,
                gradOffset = scope.progGradOffset,
                curve = scope.progClampedCurve,
            )
            chain = chain?.chain(rampBlend) ?: rampBlend
        } else if (post != null) {
            chain = chain?.chain(post) ?: post
        }
        if (ditherCoeff > 0f && (blurEffect == null || inlineDitherCoeff == 0f)) {
            chain = chain?.chain(obtainStackDitherEffect(ditherCoeff))
        }
        scope.renderEffect = chain
        primary.mainLayer?.renderEffect = chain
    }

    private var progPostEffect: RenderEffect? = null
    private var progRampBlend: BlurColors? = null

    /**
     * Progressive mode: chains pre-blur effects, the separable H/V progressive loop blur, folded
     * ramp blend colors, and dither on [primary]; when `exp > 0` and safe to overlay, drives a
     * narrow 1× clear-end band in [sharpLayer] for a ghost-free pixel-sharp transition.
     */
    private fun finalizeProgressiveRamp() {
        val scope = effectScope
        val gradient = progressiveGradient
        val rX = scope.progRecordedRadiusX
        val rY = scope.progRecordedRadiusY
        val exp = scope.progRecordedExp
        if (gradient == null || rX.isNaN() || rY.isNaN() || exp < 0) {
            chainFullResNoiseIfNeeded()
            midSharpOverlay.active = false
            sharpOverlay.active = false
            stackDitherChained = false
            progPostEffect = null
            progRampBlend = null
            primary.padLeft = scope.padding
            primary.padTop = scope.padding
            primary.padRight = scope.padding
            primary.padBottom = scope.padding
            return
        }
        val pre = scope.progressivePreEffect
        var post = scope.renderEffect
        var postBlend = scope.progressivePostBlend
        if (postBlend != null && post != null) {
            post = obtainBlendColorsEffect(scope, postBlend).chain(post)
            postBlend = null
        }
        progPostEffect = post
        progRampBlend = postBlend

        if (pre == null) {
            computeProgressiveEdgePadding(
                compW = scope.size.width,
                compH = scope.size.height,
                reachX = if (rX > 0f) scope.padding else 0f,
                reachY = if (rY > 0f) scope.padding else 0f,
                angleDeg = gradient.angle,
                startFraction = gradient.startFraction,
                endFraction = gradient.endFraction,
                curve = gradient.curve,
                outPadding = scope.progEdgePadding,
            )
            progBasePadLeft = scope.progEdgePadding[0]
            progBasePadTop = scope.progEdgePadding[1]
            progBasePadRight = scope.progEdgePadding[2]
            progBasePadBottom = scope.progEdgePadding[3]
        } else {
            progBasePadLeft = scope.padding
            progBasePadTop = scope.padding
            progBasePadRight = scope.padding
            progBasePadBottom = scope.padding
        }
        var padL = progBasePadLeft
        var padT = progBasePadTop
        var padR = progBasePadRight
        var padB = progBasePadBottom
        if (pre == null && layerBlock == null) {
            val layerCoords = (backdrop as? LayerBackdrop)?.layerCoordinates?.takeIf { it.isAttached }
            val coords = layoutCoordinates?.takeIf { it.isAttached }
            if (layerCoords != null && coords != null) {
                val pos = try {
                    layerCoords.localPositionOf(coords)
                } catch (_: Exception) {
                    null
                }
                if (pos != null) {
                    val layerW = layerCoords.size.width.toFloat()
                    val layerH = layerCoords.size.height.toFloat()
                    padL = minOf(padL, pos.x.coerceAtLeast(0f))
                    padT = minOf(padT, pos.y.coerceAtLeast(0f))
                    padR = minOf(padR, (layerW - (pos.x + scope.size.width)).coerceAtLeast(0f))
                    padB = minOf(padB, (layerH - (pos.y + scope.size.height)).coerceAtLeast(0f))
                }
            }
        }
        primary.padLeft = padL
        primary.padTop = padT
        primary.padRight = padR
        primary.padBottom = padB

        val inlineTint = postBlend.singleSrcOverColorOrUnspecified()
        val noiseCoeff = scope.noiseCoefficient
        val ditherCoeff = if (exp > 0 && noiseCoeff > 0f && noiseCoeff <= NOISE_GRAIN_THRESHOLD) {
            minOf(noiseCoeff * 2f, STACK_DITHER_MAX)
        } else {
            0f
        }
        val inlineDitherCoeff = if (post == null && (postBlend == null || inlineTint != Color.Unspecified)) ditherCoeff else 0f
        val blurEffect = obtainProgressiveBlurEffect(
            scope = scope,
            radiusX = rX,
            radiusY = rY,
            exp = exp,
            gradient = gradient,
            padLeft = padL,
            padTop = padT,
            padRight = padR,
            padBottom = padB,
            tintColor = inlineTint,
            ditherCoeff = inlineDitherCoeff,
        )
        var chain = pre?.let { if (blurEffect != null) it.chain(blurEffect) else it } ?: blurEffect
        if (postBlend != null && (blurEffect == null || inlineTint == Color.Unspecified)) {
            val rampBlend = buildRampBlendColorsEffect(
                scope = scope,
                colors = postBlend,
                gradVecX = scope.progGradVecX,
                gradVecY = scope.progGradVecY,
                gradOffset = scope.progGradOffset,
                curve = scope.progClampedCurve,
            )
            chain = chain?.chain(rampBlend) ?: rampBlend
        } else if (post != null) {
            chain = chain?.chain(post) ?: post
        }
        scope.renderEffect = chain
        if (exp == 0) {
            chainFullResNoiseIfNeeded()
            stackDitherChained = false
            midSharpOverlay.active = false
            sharpOverlay.active = false
            return
        }
        if (ditherCoeff > 0f) {
            if (blurEffect == null || inlineDitherCoeff == 0f) {
                scope.renderEffect = scope.renderEffect?.chain(obtainStackDitherEffect(ditherCoeff))
            }
            stackDitherChained = true
        } else {
            stackDitherChained = false
        }

        if (post != null || contentBlendMode != BlendMode.SrcOver) {
            midSharpOverlay.active = false
            sharpOverlay.active = false
            return
        }
        val sw = scope.size.width
        val sh = scope.size.height
        if (exp >= 2) {
            midSharpOverlay.update(
                scope = scope,
                gradient = gradient,
                sw = sw,
                sh = sh,
                maxRX = progressiveMidSharpLoopRadius(rX, exp),
                maxRY = progressiveMidSharpLoopRadius(rY, exp),
                exp = exp,
                threshold = progressiveMidSharpThreshold(exp),
                midDecay = true,
                pre = pre,
            )
        } else {
            midSharpOverlay.active = false
        }
        sharpOverlay.update(
            scope = scope,
            gradient = gradient,
            sw = sw,
            sh = sh,
            maxRX = progressiveSharpLoopRadius(rX, exp),
            maxRY = progressiveSharpLoopRadius(rY, exp),
            exp = exp,
            threshold = progressiveSharpThreshold(exp),
            midDecay = false,
            pre = pre,
        )
    }

    /**
     * Full-res path: chains noise into the RenderEffect; the downscaled path defers it to
     * [drawUpscaledLayer] so each screen pixel dithers independently (unless [stackDitherChained]).
     * Cached on input identity + coefficient, so repeated [updateEffects] runs don't allocate.
     */
    private fun chainFullResNoiseIfNeeded() {
        val noiseCoeff = effectScope.noiseCoefficient
        if (noiseCoeff > 0f && effectScope.downscaleFactor <= 1f) {
            chainNoisePass(noiseCoeff)
        }
    }

    private fun chainNoisePass(coeff: Float) {
        val input = effectScope.renderEffect
        if (effectScope.cachedNoiseChainResult != null &&
            effectScope.cachedNoiseChainInput === input &&
            effectScope.cachedNoiseChainCoeff == coeff
        ) {
            effectScope.renderEffect = effectScope.cachedNoiseChainResult
            return
        }
        effectScope.renderEffect = input.chain(obtainStackDitherEffect(coeff))
        effectScope.cachedNoiseChainInput = input
        effectScope.cachedNoiseChainCoeff = coeff
        effectScope.cachedNoiseChainResult = effectScope.renderEffect
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
        padLeft: Float,
        padTop: Float,
    ) {
        val noiseCoeff = if (stackDitherChained) 0f else effectScope.noiseCoefficient
        val residualX = backdrop.offsetResidualX
        val residualY = backdrop.offsetResidualY
        if (noiseCoeff > 0f) {
            layer.topLeft = IntOffset.Zero
            val compW = ceil(size.width).toInt().coerceAtLeast(1)
            val compH = ceil(size.height).toInt().coerceAtLeast(1)
            val noiseL = target.noiseLayer
                ?: graphicsContext.createGraphicsLayer().also { target.noiseLayer = it }
            recordLayer(noiseL, size = IntSize(compW, compH)) {
                translate(-padLeft - residualX, -padTop - residualY) {
                    scale(texelScale, texelScale, Offset.Zero) { drawLayer(layer) }
                }
            }
            noiseL.renderEffect = target.noiseRenderEffect(noiseCoeff) {
                val noiseShader = effectScope.obtainRuntimeShader("NoiseDither", NOISE_DITHER_SHADER)
                noiseShader.setFloatUniform("noise_coeff", noiseCoeff)
                runtimeShaderEffect(noiseShader, "child")
            }
            noiseL.topLeft = IntOffset.Zero
            drawLayer(noiseL)
        } else {
            target.noiseLayer?.let {
                graphicsContext.releaseGraphicsLayer(it)
                target.noiseLayer = null
            }
            drawShiftedLayer(layer, padLeft / texelScale, padTop / texelScale, texelScale, residualX, residualY)
        }
    }

    /**
     * Draws [layer] with its content origin exactly [padLeft]/[padTop] layer-texels before this
     * surface's origin: the integral part via [GraphicsLayer.topLeft], the fraction via a draw-time
     * translate. Snapping the fraction moved the picture a pixel every time the padding's integer
     * part changed — a stepping shimmer during a radius sweep.
     */
    private fun DrawScope.drawShiftedLayer(
        layer: GraphicsLayer,
        padLeft: Float,
        padTop: Float,
        texelScale: Float,
        residualX: Float = 0f,
        residualY: Float = 0f,
    ) {
        val wholeX = floor(padLeft)
        val wholeY = floor(padTop)
        layer.topLeft =
            if (padLeft != 0f || padTop != 0f) {
                IntOffset(-wholeX.toInt(), -wholeY.toInt())
            } else {
                IntOffset.Zero
            }
        val fracX = (padLeft - wholeX) * texelScale
        val fracY = (padTop - wholeY) * texelScale
        translate(-residualX - fracX, -residualY - fracY) {
            scale(texelScale, texelScale, Offset.Zero) { drawLayer(layer) }
        }
    }

    fun releaseGraphicsLayers() {
        primary.release()
        midSharpOverlay.release()
        sharpOverlay.release()
        stackDitherEffect = null
        stackDitherCoeff = Float.NaN
        stackDitherChained = false
        progPostEffect = null
        progRampBlend = null
        progBasePadLeft = 0f
        progBasePadTop = 0f
        progBasePadRight = 0f
        progBasePadBottom = 0f
        effectScope.reset()
    }

    override fun onDetach() {
        stopObservingLifecycle()
        releaseGraphicsLayers()
        layoutCoordinates = null
        effectsSized = false
    }
}

private fun BlurColors?.singleSrcOverColorOrUnspecified(): Color {
    if (this == null || blendColors.size != 1) return Color.Unspecified
    val entry = blendColors[0]
    if (entry.mode != BlurBlendMode.SrcOver) return Color.Unspecified
    return entry.color
}
