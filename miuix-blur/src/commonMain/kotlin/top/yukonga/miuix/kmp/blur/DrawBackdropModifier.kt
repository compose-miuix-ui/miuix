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
import top.yukonga.miuix.kmp.blur.highlight.Highlight
import top.yukonga.miuix.kmp.blur.highlight.drawHighlight
import top.yukonga.miuix.kmp.blur.internal.DOWNSAMPLE_2X_SHADER
import top.yukonga.miuix.kmp.blur.internal.DOWNSAMPLE_4X_SHADER
import top.yukonga.miuix.kmp.blur.internal.InverseLayerScope
import top.yukonga.miuix.kmp.blur.internal.NOISE_DITHER_SHADER
import top.yukonga.miuix.kmp.blur.internal.ShapeProvider
import top.yukonga.miuix.kmp.blur.internal.createProgressiveCompositeEffect
import top.yukonga.miuix.kmp.blur.internal.recordLayer
import top.yukonga.miuix.kmp.blur.internal.runtimeShaderEffect

private val DefaultOnDrawBackdrop: DrawScope.(DrawScope.() -> Unit) -> Unit = { it() }

/**
 * Applies a backdrop effect to this composable.
 *
 * @param backdrop The [Backdrop] providing the background content to capture and blur.
 * @param shape The shape provider for the blur region clipping; re-read on each draw so an
 *   animated shape stays current.
 * @param effects The effect pipeline applied to the captured backdrop, configured against a
 *   [BackdropEffectScope] (e.g. [blur], [blendColors], [colorControls]).
 * @param highlight Optional edge highlight resolved against the [BackdropEffectScope] and painted
 *   on top of the content; returning `null` skips drawing.
 * @param layerBlock Optional graphics layer transformation applied to this composable and inverted
 *   on the backdrop sampling so the backdrop stays screen-aligned; safe to animate.
 * @param onDrawBehind Optional draw callback invoked before the blurred backdrop, behind it.
 * @param onDrawBackdrop Wraps the backdrop drawing call, letting callers transform or intercept the
 *   recorded content; defaults to invoking it directly.
 * @param onDrawSurface Optional draw callback invoked after the blurred backdrop and before the
 *   composable's own content.
 * @param onDrawFront Optional draw callback invoked last, on top of the content and highlight.
 * @param contentBlendMode The [BlendMode] used to composite the composable's content over the blur.
 *   [BlendMode.DstIn] masks the blur by the content alpha (foreground blur).
 * @param progressiveGradient When non-null, renders the backdrop as a multi-level progressive
 *   composite: a full-resolution sharp base plus graduated native Gaussian levels of the same
 *   source, cross-faded along this gradient so the blur strength ramps continuously from full to
 *   pixel-sharp. Requires a matching [progressiveBlur] in [effects] (as
 *   [Modifier.progressiveTextureBlur] wires) — it records the target radii the composite renders;
 *   without one the gradient is ignored and [effects] draws uniformly.
 * @param enabled Whether the backdrop effect is active. When false, content draws normally without
 *   blur. Also gated by [isRuntimeShaderSupported].
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
    progressiveGradient: ProgressiveBlur? = null,
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
                progressiveGradient = progressiveGradient,
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
    val progressiveGradient: ProgressiveBlur? = null,
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
        progressiveGradient = progressiveGradient,
        enabled = enabled,
    )

    override fun update(node: DrawBackdropNode) {
        val enabledChanged = node.enabled != enabled
        val gradientCleared = node.progressiveGradient != null && progressiveGradient == null
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
        if (highlight != other.highlight) return false
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
        result = 31 * result + (highlight?.hashCode() ?: 0)
        result = 31 * result + (layerBlock?.hashCode() ?: 0)
        result = 31 * result + (onDrawBehind?.hashCode() ?: 0)
        result = 31 * result + onDrawBackdrop.hashCode()
        result = 31 * result + (onDrawSurface?.hashCode() ?: 0)
        result = 31 * result + (onDrawFront?.hashCode() ?: 0)
        result = 31 * result + contentBlendMode.hashCode()
        result = 31 * result + (progressiveGradient?.hashCode() ?: 0)
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
    var progressiveGradient: ProgressiveBlur? = null,
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

    /** The node's graphics context; the (necessarily `inner`) [LevelTarget] reaches it through here. */
    private val graphicsContext get() = requireGraphicsContext()

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
        // A RenderEffect captures its shader's uniforms when created, so a built instance is
        // unaffected by later re-sets of the tree-shared RuntimeShader — reuse is safe and saves a
        // native allocation per frame. Two slots cover the deepest cascade (sf=16).
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
    private val secondary = LevelTarget()
    private var crossfadeResultLayer: GraphicsLayer? = null

    // layerBlock scale captured per draw for the display-space inverse (see renderBlurInto);
    // 1f when absent or rotating.
    private var displayCompScaleX = 1f
    private var displayCompScaleY = 1f
    private var inverseCapture: InverseLayerScope? = null

    private fun DrawScope.captureLayerScale() {
        val lb = layerBlock
        if (lb == null) {
            displayCompScaleX = 1f
            displayCompScaleY = 1f
            return
        }
        val capture = inverseCapture ?: InverseLayerScope().also { inverseCapture = it }
        capture.reset()
        capture.size = effectScope.size
        capture.density = density
        capture.fontScale = fontScale
        capture.lb()
        if (capture.rotationZ == 0f && capture.scaleX != 0f && capture.scaleY != 0f) {
            displayCompScaleX = capture.scaleX
            displayCompScaleY = capture.scaleY
        } else {
            displayCompScaleX = 1f
            displayCompScaleY = 1f
        }
    }

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

    private fun DrawScope.recordBackdrop(target: LevelTarget, inverseInRecord: Boolean) {
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
                        layerBlock = if (inverseInRecord) layerBlock else null,
                        downscaleFactor = scaleFactor,
                    )
                }
            }
        }
    }

    /**
     * Renders [target]'s blurred backdrop, choosing where the [layerBlock] inverse is applied.
     *
     * A pure scale on a downscaled level is inverted here at full display resolution — inverting
     * inside the recording resamples the downscaled grid fractionally every animation frame and
     * flickers. Pivot is the node origin: `localPositionOf` already absorbs any transformOrigin
     * displacement, leaving exactly a pivot-zero scale. Rotation and full-resolution levels keep
     * the record-space inverse, whose recording covers the inverse-scaled region by construction.
     */
    private fun DrawScope.renderBlurInto(target: LevelTarget) {
        val sx = displayCompScaleX
        val sy = displayCompScaleY
        if (target.downscaleFactor > 1 && (sx != 1f || sy != 1f)) {
            scale(1f / sx, 1f / sy, Offset.Zero) { renderBlurLevel(target, inverseInRecord = false) }
        } else {
            renderBlurLevel(target, inverseInRecord = true)
        }
    }

    /** Record → cascade downsample → upscale for one level. */
    private fun DrawScope.renderBlurLevel(target: LevelTarget, inverseInRecord: Boolean) {
        val layer = target.mainLayer ?: return
        val currentPadding = target.padding
        val scaleFactor = target.downscaleFactor
        val fullWidth = (size.width + currentPadding * 2).toInt()
        val fullHeight = (size.height + currentPadding * 2).toInt()

        if (scaleFactor <= 1) {
            target.cascadeFirstStepScale = 1
            recordLayer(layer, size = IntSize(fullWidth, fullHeight)) { recordBackdrop(target, inverseInRecord) }
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
            recordLayer(layer, size = IntSize(w, h)) { recordBackdrop(target, inverseInRecord) }
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
            recordLayer(firstCascade, size = IntSize(firstW, firstH)) { recordBackdrop(target, inverseInRecord) }

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

    // Reused saveLayer bounds for the non-SrcOver content path (DstIn / foreground blur): Rect is a
    // plain class, so caching it avoids a per-frame allocation; rebuilt only when the size changes.
    private var contentBoundsRect: Rect? = null

    // Progressive (gradient) blur: a full-res sharp layer carrying the multi-level composite
    // RenderEffect, cached on its inputs. The supported flags gate platforms whose
    // progressiveCompositeEffect() returns null.
    private var compositeLayer: GraphicsLayer? = null
    private var compositeAttempted = false
    private var compositeSupported = false
    private var cachedCompositeEffect: RenderEffect? = null
    private var cachedCompositeRadiusX = Float.NaN
    private var cachedCompositeRadiusY = Float.NaN
    private var cachedCompositeW = Float.NaN
    private var cachedCompositeH = Float.NaN
    private var cachedCompositeGradient: ProgressiveBlur? = null

    // Pre-/post-blur chains folded into the cached composite; compared by identity (the scope's
    // per-effect caches keep the references stable while their inputs hold).
    private var cachedCompositePre: RenderEffect? = null
    private var cachedCompositePost: RenderEffect? = null

    /** Renders the blurred backdrop into [primary] and, in a cross-fade band, the [secondary] level. */
    private fun DrawScope.drawBlurredBackdrop() {
        renderBlurInto(primary)
        if (blending) {
            // Cross-fade band: composite the higher level on top of the lower one with alpha = blendFactor.
            val result = crossfadeResultLayer
                ?: graphicsContext.createGraphicsLayer().also { crossfadeResultLayer = it }
            recordLayer(
                result,
                size = IntSize(size.width.toInt().coerceAtLeast(1), size.height.toInt().coerceAtLeast(1)),
            ) {
                renderBlurInto(secondary)
            }
            result.alpha = blendFactor
            drawLayer(result)
        }
    }

    /**
     * Records the sharp backdrop at full resolution into [compositeLayer] and attaches the
     * multi-level composite [RenderEffect]. Returns false when [progressiveBlur] recorded no radii
     * or the platform can't express the composite — the caller then draws uniformly.
     */
    private fun DrawScope.drawProgressiveComposite(gradient: ProgressiveBlur): Boolean {
        if (compositeAttempted && !compositeSupported) return false
        val rX = effectScope.cachedProgRadiusX
        val rY = effectScope.cachedProgRadiusY
        if (rX.isNaN() || rY.isNaN()) return false
        val sw = size.width
        val sh = size.height
        val pre = effectScope.progressivePreEffect
        val post = effectScope.renderEffect
        val composite = if (cachedCompositeEffect != null &&
            cachedCompositeRadiusX == rX &&
            cachedCompositeRadiusY == rY &&
            cachedCompositeW == sw &&
            cachedCompositeH == sh &&
            cachedCompositeGradient == gradient &&
            cachedCompositePre === pre &&
            cachedCompositePost === post
        ) {
            cachedCompositeEffect
        } else {
            val built = createProgressiveCompositeEffect(
                rX,
                rY,
                sw,
                sh,
                gradient.angle,
                gradient.startFraction,
                gradient.endFraction,
                gradient.curve,
                pre,
                post,
                effectScope,
            )
            compositeAttempted = true
            compositeSupported = built != null
            if (built != null) {
                cachedCompositeEffect = built
                cachedCompositeRadiusX = rX
                cachedCompositeRadiusY = rY
                cachedCompositeW = sw
                cachedCompositeH = sh
                cachedCompositeGradient = gradient
                cachedCompositePre = pre
                cachedCompositePost = post
            }
            built
        } ?: return false
        val w = sw.toInt().coerceAtLeast(1)
        val h = sh.toInt().coerceAtLeast(1)
        val layer = compositeLayer ?: graphicsContext.createGraphicsLayer().also { compositeLayer = it }
        recordLayer(layer, size = IntSize(w, h)) {
            onDrawBackdrop {
                with(backdrop) {
                    drawBackdrop(
                        density = effectScope,
                        coordinates = layoutCoordinates,
                        layerBlock = layerBlock,
                        downscaleFactor = 1,
                    )
                }
            }
        }
        layer.renderEffect = composite
        drawLayer(layer)
        return true
    }

    override fun ContentDrawScope.draw() {
        if (!enabled) {
            drawContent()
            return
        }
        if (effectScope.update(this)) {
            updateEffects()
        }
        captureLayerScale()
        onDrawBehind?.invoke(this)

        val gradient = progressiveGradient
        if (gradient == null) {
            drawBlurredBackdrop()
        } else if (!drawProgressiveComposite(gradient)) {
            // Composite unsupported: the effects chain skipped its own build while the flag was
            // still assumed, so rebuild (the flag re-derives as false) before drawing uniformly.
            if (effectScope.progressiveCompositeActive && compositeAttempted && !compositeSupported) {
                updateEffects()
            }
            drawBlurredBackdrop()
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

        highlight?.invoke(effectScope)?.let { resolved ->
            drawHighlight(
                highlight = resolved,
                shape = effectScope.shape,
                // onAttach cached this from LocalRuntimeShaderCache (a staticCompositionLocalOf, so
                // its identity is stable) — read the field instead of a per-frame currentValueOf.
                runtimeShaderCache = effectScope.runtimeShaderCache,
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

        // Gradient mode: progressiveBlur() only records radii for the composite; re-derives as
        // false once a platform reports the composite unsupported.
        effectScope.progressiveCompositeActive =
            progressiveGradient != null && !(compositeAttempted && !compositeSupported)

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
     *
     * Cached on input identity + coefficient: the noise pass ends the composite's post-blur
     * chain, which [drawProgressiveComposite] compares by identity — a fresh effect per pass
     * would rebuild the whole composite on every [updateEffects].
     */
    private fun chainFullResNoiseIfNeeded() {
        val noiseCoeff = effectScope.noiseCoefficient
        if (noiseCoeff > 0f && effectScope.downscaleFactor <= 1) {
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
                ?: graphicsContext.createGraphicsLayer().also { target.noiseLayer = it }
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

    /**
     * Drops the composite layer and its caches. Called when [progressiveGradient] is cleared on a
     * live node so the full-resolution offscreen layer doesn't outlive the gradient mode; the
     * supported flags survive (platform capability doesn't change).
     */
    fun releaseCompositeResources() {
        compositeLayer?.let { graphicsContext.releaseGraphicsLayer(it) }
        compositeLayer = null
        cachedCompositeEffect = null
        cachedCompositeRadiusX = Float.NaN
        cachedCompositeRadiusY = Float.NaN
        cachedCompositeW = Float.NaN
        cachedCompositeH = Float.NaN
        cachedCompositeGradient = null
        cachedCompositePre = null
        cachedCompositePost = null
    }

    fun releaseGraphicsLayers() {
        primary.release()
        secondary.release()
        crossfadeResultLayer?.let { graphicsContext.releaseGraphicsLayer(it) }
        crossfadeResultLayer = null
        releaseCompositeResources()
        blending = false
        effectScope.reset()
    }

    override fun onDetach() {
        releaseGraphicsLayers()
        layoutCoordinates = null
    }
}
