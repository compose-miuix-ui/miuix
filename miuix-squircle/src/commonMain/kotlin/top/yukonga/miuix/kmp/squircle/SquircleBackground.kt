// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.node.invalidatePlacement
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.material.LocalRuntimeShaderCache
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.RuntimeShaderCache
import top.yukonga.miuix.kmp.material.asBrush
import top.yukonga.miuix.kmp.material.isRuntimeShaderBrushImmutable
import top.yukonga.miuix.kmp.squircle.internal.BakedSquircleSdf
import top.yukonga.miuix.kmp.squircle.internal.makeLinearAlphaShader
import kotlin.math.ceil
import kotlin.math.floor

/**
 * Squircle solid background — fill-only, descendants are NOT clipped.
 *
 * Falls back to `Modifier.background(color, RoundedCornerShape(cornerRadius))`
 * when runtime shaders are unavailable (Android < API 33).
 *
 * @param color The fill [Color] of the squircle background.
 * @param cornerRadius The radius applied uniformly to all four corners.
 * @param extension The corner-tile size as a multiple of [cornerRadius]: 1.0 matches a circular
 *   arc, 1.1 is the default continuous-corner look. Clamped to [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.squircleBackground(
    color: Color,
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier = squircleBackground(color, cornerRadius, cornerRadius, cornerRadius, cornerRadius, extension)

/**
 * Per-corner variant of [squircleBackground]. Ordering matches [RoundedCornerShape].
 *
 * @param color The fill [Color] of the squircle background.
 * @param topStart The corner radius of the top-start corner (flipped by `LocalLayoutDirection`).
 * @param topEnd The corner radius of the top-end corner (flipped by `LocalLayoutDirection`).
 * @param bottomEnd The corner radius of the bottom-end corner (flipped by `LocalLayoutDirection`).
 * @param bottomStart The corner radius of the bottom-start corner (flipped by `LocalLayoutDirection`).
 * @param extension The corner-tile size as a multiple of each corner radius, clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.squircleBackground(
    color: Color,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    if (!color.isSpecified || color.alpha <= 0f) return this
    val ts = sanitizePositiveDp(topStart)
    val te = sanitizePositiveDp(topEnd)
    val be = sanitizePositiveDp(bottomEnd)
    val bs = sanitizePositiveDp(bottomStart)
    if (ts.value == 0f && te.value == 0f && be.value == 0f && bs.value == 0f) {
        return this.background(color)
    }
    if (!isSquircleEnabled()) {
        return this.background(
            color = color,
            shape = RoundedCornerShape(ts, te, be, bs),
        )
    }
    return this then SquircleBackgroundElement(
        color = color,
        topStart = ts,
        topEnd = te,
        bottomEnd = be,
        bottomStart = bs,
        extension = sanitizeExtension(extension),
        layoutDirectionAware = true,
    )
}

/**
 * Clips the subtree to the squircle silhouette.
 *
 * Falls back to `Modifier.clip(RoundedCornerShape(cornerRadius))` when
 * runtime shaders are unavailable.
 *
 * @param cornerRadius The radius applied uniformly to all four corners.
 * @param extension The corner-tile size as a multiple of [cornerRadius], clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.squircleClip(
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier = squircleClip(cornerRadius, cornerRadius, cornerRadius, cornerRadius, extension)

/**
 * Per-corner variant of [squircleClip].
 *
 * @param topStart The corner radius of the top-start corner (flipped by `LocalLayoutDirection`).
 * @param topEnd The corner radius of the top-end corner (flipped by `LocalLayoutDirection`).
 * @param bottomEnd The corner radius of the bottom-end corner (flipped by `LocalLayoutDirection`).
 * @param bottomStart The corner radius of the bottom-start corner (flipped by `LocalLayoutDirection`).
 * @param extension The corner-tile size as a multiple of each corner radius, clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.squircleClip(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val ts = sanitizePositiveDp(topStart)
    val te = sanitizePositiveDp(topEnd)
    val be = sanitizePositiveDp(bottomEnd)
    val bs = sanitizePositiveDp(bottomStart)
    if (ts.value == 0f && te.value == 0f && be.value == 0f && bs.value == 0f) {
        return this
    }
    if (!isSquircleEnabled()) {
        return this.clip(RoundedCornerShape(ts, te, be, bs))
    }
    return this.squircleShaderMask(
        fillColor = null,
        topStart = ts,
        topEnd = te,
        bottomEnd = be,
        bottomStart = bs,
        extension = sanitizeExtension(extension),
        layoutDirectionAware = true,
    )
}

/**
 * Fill + clip behind a squircle silhouette — drop-in replacement for
 * `.clip(RoundedCornerShape(r)).background(color)` with a squircle outline.
 *
 * @param color The fill [Color] drawn behind the clipped content.
 * @param cornerRadius The radius applied uniformly to all four corners.
 * @param extension The corner-tile size as a multiple of [cornerRadius], clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.squircleSurface(
    color: Color,
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier = squircleSurface(color, cornerRadius, cornerRadius, cornerRadius, cornerRadius, extension)

/**
 * Per-corner variant of [squircleSurface].
 *
 * @param color The fill [Color] drawn behind the clipped content.
 * @param topStart The corner radius of the top-start corner (flipped by `LocalLayoutDirection`).
 * @param topEnd The corner radius of the top-end corner (flipped by `LocalLayoutDirection`).
 * @param bottomEnd The corner radius of the bottom-end corner (flipped by `LocalLayoutDirection`).
 * @param bottomStart The corner radius of the bottom-start corner (flipped by `LocalLayoutDirection`).
 * @param extension The corner-tile size as a multiple of each corner radius, clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.squircleSurface(
    color: Color,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val effectiveFillColor = color.takeIf { it.isSpecified && it.alpha > 0f }
    val ts = sanitizePositiveDp(topStart)
    val te = sanitizePositiveDp(topEnd)
    val be = sanitizePositiveDp(bottomEnd)
    val bs = sanitizePositiveDp(bottomStart)
    if (ts.value == 0f && te.value == 0f && be.value == 0f && bs.value == 0f) {
        return if (effectiveFillColor != null) this.background(effectiveFillColor) else this
    }
    if (!isSquircleEnabled()) {
        return this
            .clip(RoundedCornerShape(ts, te, be, bs))
            .then(if (effectiveFillColor != null) Modifier.background(effectiveFillColor) else Modifier)
    }
    return this.squircleShaderMask(
        fillColor = effectiveFillColor,
        topStart = ts,
        topEnd = te,
        bottomEnd = be,
        bottomStart = bs,
        extension = sanitizeExtension(extension),
        layoutDirectionAware = true,
    )
}

/**
 * Absolute-positioning variant of [squircleBackground]. Corner parameters are physical
 * (clockwise from top-left) and are NOT flipped by `LocalLayoutDirection` — mirrors
 * `AbsoluteRoundedCornerShape`. Reach for this when corners are anchored to physical sides
 * regardless of layout direction (e.g. transition reveals tied to a swipe edge).
 *
 * @param color The fill [Color] of the squircle background.
 * @param topLeft The physical top-left corner radius, never flipped by `LocalLayoutDirection`.
 * @param topRight The physical top-right corner radius, never flipped by `LocalLayoutDirection`.
 * @param bottomRight The physical bottom-right corner radius, never flipped by `LocalLayoutDirection`.
 * @param bottomLeft The physical bottom-left corner radius, never flipped by `LocalLayoutDirection`.
 * @param extension The corner-tile size as a multiple of each corner radius, clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.absoluteSquircleBackground(
    color: Color,
    topLeft: Dp,
    topRight: Dp,
    bottomRight: Dp,
    bottomLeft: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    if (!color.isSpecified || color.alpha <= 0f) return this
    val tl = sanitizePositiveDp(topLeft)
    val tr = sanitizePositiveDp(topRight)
    val br = sanitizePositiveDp(bottomRight)
    val bl = sanitizePositiveDp(bottomLeft)
    if (tl.value == 0f && tr.value == 0f && br.value == 0f && bl.value == 0f) {
        return this.background(color)
    }
    if (!isSquircleEnabled()) {
        return this.background(
            color = color,
            shape = AbsoluteRoundedCornerShape(tl, tr, br, bl),
        )
    }
    return this then SquircleBackgroundElement(
        color = color,
        topStart = tl,
        topEnd = tr,
        bottomEnd = br,
        bottomStart = bl,
        extension = sanitizeExtension(extension),
        layoutDirectionAware = false,
    )
}

/**
 * Absolute-positioning variant of [squircleClip]. See [absoluteSquircleBackground] for semantics.
 *
 * @param topLeft The physical top-left corner radius, never flipped by `LocalLayoutDirection`.
 * @param topRight The physical top-right corner radius, never flipped by `LocalLayoutDirection`.
 * @param bottomRight The physical bottom-right corner radius, never flipped by `LocalLayoutDirection`.
 * @param bottomLeft The physical bottom-left corner radius, never flipped by `LocalLayoutDirection`.
 * @param extension The corner-tile size as a multiple of each corner radius, clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.absoluteSquircleClip(
    topLeft: Dp,
    topRight: Dp,
    bottomRight: Dp,
    bottomLeft: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val tl = sanitizePositiveDp(topLeft)
    val tr = sanitizePositiveDp(topRight)
    val br = sanitizePositiveDp(bottomRight)
    val bl = sanitizePositiveDp(bottomLeft)
    if (tl.value == 0f && tr.value == 0f && br.value == 0f && bl.value == 0f) {
        return this
    }
    if (!isSquircleEnabled()) {
        return this.clip(AbsoluteRoundedCornerShape(tl, tr, br, bl))
    }
    return this.squircleShaderMask(
        fillColor = null,
        topStart = tl,
        topEnd = tr,
        bottomEnd = br,
        bottomStart = bl,
        extension = sanitizeExtension(extension),
        layoutDirectionAware = false,
    )
}

/**
 * Absolute-positioning variant of [squircleSurface]. See [absoluteSquircleBackground] for semantics.
 *
 * @param color The fill [Color] drawn behind the clipped content.
 * @param topLeft The physical top-left corner radius, never flipped by `LocalLayoutDirection`.
 * @param topRight The physical top-right corner radius, never flipped by `LocalLayoutDirection`.
 * @param bottomRight The physical bottom-right corner radius, never flipped by `LocalLayoutDirection`.
 * @param bottomLeft The physical bottom-left corner radius, never flipped by `LocalLayoutDirection`.
 * @param extension The corner-tile size as a multiple of each corner radius, clamped to
 *   [SquircleDefaults.ExtensionMin]..[SquircleDefaults.ExtensionMax].
 */
@Composable
@ReadOnlyComposable
fun Modifier.absoluteSquircleSurface(
    color: Color,
    topLeft: Dp,
    topRight: Dp,
    bottomRight: Dp,
    bottomLeft: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val effectiveFillColor = color.takeIf { it.isSpecified && it.alpha > 0f }
    val tl = sanitizePositiveDp(topLeft)
    val tr = sanitizePositiveDp(topRight)
    val br = sanitizePositiveDp(bottomRight)
    val bl = sanitizePositiveDp(bottomLeft)
    if (tl.value == 0f && tr.value == 0f && br.value == 0f && bl.value == 0f) {
        return if (effectiveFillColor != null) this.background(effectiveFillColor) else this
    }
    if (!isSquircleEnabled()) {
        return this
            .clip(AbsoluteRoundedCornerShape(tl, tr, br, bl))
            .then(if (effectiveFillColor != null) Modifier.background(effectiveFillColor) else Modifier)
    }
    return this.squircleShaderMask(
        fillColor = effectiveFillColor,
        topStart = tl,
        topEnd = tr,
        bottomEnd = br,
        bottomStart = bl,
        extension = sanitizeExtension(extension),
        layoutDirectionAware = false,
    )
}

private val OffscreenLayerBlock: GraphicsLayerScope.() -> Unit = {
    compositingStrategy = CompositingStrategy.Offscreen
}

private val CapsuleLayerBlock: GraphicsLayerScope.() -> Unit = {
    shape = CircleShape
    clip = true
    compositingStrategy = CompositingStrategy.Auto
}

/**
 * Shared squircle clip/surface chain for [squircleClip] / [squircleSurface] and their absolute
 * variants:
 * - Pure capsules (`tile >= min(w, h) / 2` on all four corners) clip via hardware [CircleShape]
 *   with [CompositingStrategy.Auto] — zero offscreen buffer and zero shader passes.
 * - Nodes within [fitsInOffscreenLayer] composite through one [CompositingStrategy.Offscreen]
 *   layer (`clip = false`, cached across scroll frames) and carve only the corner quads via `DstIn`.
 * - Oversize / ultra-tall nodes skip `placeWithLayer` altogether (`placeable.place(0, 0)`), keeping
 *   offscreen memory at zero and pointer hit-testing unrestricted at arbitrary height while
 *   clipping drawing via a cached squircle [Path].
 */
private fun Modifier.squircleShaderMask(
    fillColor: Color?,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float,
    layoutDirectionAware: Boolean,
): Modifier = this
    .then(
        SquircleLayerElement(
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            extension = extension,
        ),
    )
    .then(
        SquircleShaderMaskElement(
            fillColor = fillColor,
            topStart = topStart,
            topEnd = topEnd,
            bottomEnd = bottomEnd,
            bottomStart = bottomStart,
            extension = extension,
            layoutDirectionAware = layoutDirectionAware,
        ),
    )

private fun cornerScale(density: Float, extension: Float): Float = density * sanitizeExtension(extension)

private fun cornerTilePx(radius: Dp, scale: Float, halfMin: Float): Float = if (radius.value > 0f) (radius.value * scale).coerceAtMost(halfMin) else 0f

private fun fitsInOffscreenLayer(width: Float, height: Float): Boolean = width > 0f &&
    height > 0f &&
    maxOf(width, height) <= MAX_OFFSCREEN_PX &&
    width * height <= MAX_OFFSCREEN_AREA_PX

private fun resolveLayerBlock(
    width: Float,
    height: Float,
    density: Float,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float,
): (GraphicsLayerScope.() -> Unit)? {
    if (!fitsInOffscreenLayer(width, height)) return null
    val halfMin = minOf(width, height) * 0.5f
    val scale = cornerScale(density, extension)
    val ts = cornerTilePx(topStart, scale, halfMin)
    val te = cornerTilePx(topEnd, scale, halfMin)
    val be = cornerTilePx(bottomEnd, scale, halfMin)
    val bs = cornerTilePx(bottomStart, scale, halfMin)
    if (ts <= 0f && te <= 0f && be <= 0f && bs <= 0f) return null
    return if (ts >= halfMin && te >= halfMin && be >= halfMin && bs >= halfMin) {
        CapsuleLayerBlock
    } else {
        OffscreenLayerBlock
    }
}

private data class SquircleBackgroundElement(
    val color: Color,
    val topStart: Dp,
    val topEnd: Dp,
    val bottomEnd: Dp,
    val bottomStart: Dp,
    val extension: Float,
    val layoutDirectionAware: Boolean,
) : ModifierNodeElement<SquircleBackgroundNode>() {
    override fun create(): SquircleBackgroundNode = SquircleBackgroundNode(
        color = color,
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
        extension = extension,
        layoutDirectionAware = layoutDirectionAware,
    )

    override fun update(node: SquircleBackgroundNode) {
        node.updateParams(color, topStart, topEnd, bottomEnd, bottomStart, extension, layoutDirectionAware)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "squircleBackground"
        properties["color"] = color
        properties["topStart"] = topStart
        properties["topEnd"] = topEnd
        properties["bottomEnd"] = bottomEnd
        properties["bottomStart"] = bottomStart
        properties["extension"] = extension
    }
}

private class SquircleBackgroundNode(
    private var color: Color,
    private var topStart: Dp,
    private var topEnd: Dp,
    private var bottomEnd: Dp,
    private var bottomStart: Dp,
    private var extension: Float,
    private var layoutDirectionAware: Boolean,
) : Modifier.Node(),
    DrawModifierNode,
    CompositionLocalConsumerModifierNode {

    private val shaderHolder = SquircleShaderHolder()

    fun updateParams(
        color: Color,
        topStart: Dp,
        topEnd: Dp,
        bottomEnd: Dp,
        bottomStart: Dp,
        extension: Float,
        layoutDirectionAware: Boolean,
    ) {
        if (this.color != color ||
            this.topStart != topStart ||
            this.topEnd != topEnd ||
            this.bottomEnd != bottomEnd ||
            this.bottomStart != bottomStart ||
            this.extension != extension ||
            this.layoutDirectionAware != layoutDirectionAware
        ) {
            this.color = color
            this.topStart = topStart
            this.topEnd = topEnd
            this.bottomEnd = bottomEnd
            this.bottomStart = bottomStart
            this.extension = extension
            this.layoutDirectionAware = layoutDirectionAware
            invalidateDraw()
        }
    }

    override fun ContentDrawScope.draw() {
        val w = size.width
        val h = size.height
        if (w > 0f && h > 0f) {
            val halfMin = minOf(w, h) * 0.5f
            val scale = cornerScale(density, extension)
            val sTop = cornerTilePx(topStart, scale, halfMin)
            val eTop = cornerTilePx(topEnd, scale, halfMin)
            val eBottom = cornerTilePx(bottomEnd, scale, halfMin)
            val sBottom = cornerTilePx(bottomStart, scale, halfMin)
            val flip = layoutDirectionAware && layoutDirection == LayoutDirection.Rtl
            val tl = if (flip) eTop else sTop
            val tr = if (flip) sTop else eTop
            val br = if (flip) sBottom else eBottom
            val bl = if (flip) eBottom else sBottom
            if (tl <= 0f && tr <= 0f && br <= 0f && bl <= 0f) {
                drawRect(color = color)
            } else if (tl >= halfMin && tr >= halfMin && br >= halfMin && bl >= halfMin) {
                drawRoundRect(color = color, cornerRadius = CornerRadius(halfMin, halfMin))
            } else {
                val cache = currentValueOf(LocalRuntimeShaderCache)
                drawRect(brush = shaderHolder.obtainBrush(size, color, tl, tr, br, bl, cache))
            }
        }
        drawContent()
    }

    override fun onDetach() {
        shaderHolder.invalidateCache()
    }
}

private data class SquircleLayerElement(
    val topStart: Dp,
    val topEnd: Dp,
    val bottomEnd: Dp,
    val bottomStart: Dp,
    val extension: Float,
) : ModifierNodeElement<SquircleLayerNode>() {
    override fun create(): SquircleLayerNode = SquircleLayerNode(
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
        extension = extension,
    )

    override fun update(node: SquircleLayerNode) {
        node.updateParams(topStart, topEnd, bottomEnd, bottomStart, extension)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "squircleLayer"
    }
}

private class SquircleLayerNode(
    private var topStart: Dp,
    private var topEnd: Dp,
    private var bottomEnd: Dp,
    private var bottomStart: Dp,
    private var extension: Float,
) : Modifier.Node(),
    LayoutModifierNode {

    fun updateParams(
        topStart: Dp,
        topEnd: Dp,
        bottomEnd: Dp,
        bottomStart: Dp,
        extension: Float,
    ) {
        if (this.topStart != topStart ||
            this.topEnd != topEnd ||
            this.bottomEnd != bottomEnd ||
            this.bottomStart != bottomStart ||
            this.extension != extension
        ) {
            this.topStart = topStart
            this.topEnd = topEnd
            this.bottomEnd = bottomEnd
            this.bottomStart = bottomStart
            this.extension = extension
            invalidatePlacement()
        }
    }

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints,
    ): MeasureResult {
        val placeable = measurable.measure(constraints)
        val w = placeable.width
        val h = placeable.height
        val wf = w.toFloat()
        val hf = h.toFloat()
        val densityPx = density
        return layout(w, h) {
            val block = resolveLayerBlock(wf, hf, densityPx, topStart, topEnd, bottomEnd, bottomStart, extension)
            if (block == null) {
                placeable.place(0, 0)
            } else {
                placeable.placeWithLayer(0, 0, layerBlock = block)
            }
        }
    }
}

private data class SquircleShaderMaskElement(
    val fillColor: Color?,
    val topStart: Dp,
    val topEnd: Dp,
    val bottomEnd: Dp,
    val bottomStart: Dp,
    val extension: Float,
    val layoutDirectionAware: Boolean,
) : ModifierNodeElement<SquircleShaderMaskNode>() {
    override fun create(): SquircleShaderMaskNode = SquircleShaderMaskNode(
        fillColor = fillColor,
        topStart = topStart,
        topEnd = topEnd,
        bottomEnd = bottomEnd,
        bottomStart = bottomStart,
        extension = extension,
        layoutDirectionAware = layoutDirectionAware,
    )

    override fun update(node: SquircleShaderMaskNode) {
        node.updateParams(fillColor, topStart, topEnd, bottomEnd, bottomStart, extension, layoutDirectionAware)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "squircleShaderMask"
        properties["fillColor"] = fillColor
        properties["topStart"] = topStart
        properties["topEnd"] = topEnd
        properties["bottomEnd"] = bottomEnd
        properties["bottomStart"] = bottomStart
        properties["extension"] = extension
    }
}

private class SquircleShaderMaskNode(
    private var fillColor: Color?,
    private var topStart: Dp,
    private var topEnd: Dp,
    private var bottomEnd: Dp,
    private var bottomStart: Dp,
    private var extension: Float,
    private var layoutDirectionAware: Boolean,
) : Modifier.Node(),
    DrawModifierNode,
    CompositionLocalConsumerModifierNode {

    private val shaderHolder = SquircleShaderHolder()
    private var clipPath: Path? = null
    private var cachedPathSize: Size = Size.Unspecified
    private var cachedPathTl: Float = Float.NaN
    private var cachedPathTr: Float = Float.NaN
    private var cachedPathBr: Float = Float.NaN
    private var cachedPathBl: Float = Float.NaN

    fun updateParams(
        fillColor: Color?,
        topStart: Dp,
        topEnd: Dp,
        bottomEnd: Dp,
        bottomStart: Dp,
        extension: Float,
        layoutDirectionAware: Boolean,
    ) {
        val geometryChanged = this.topStart != topStart ||
            this.topEnd != topEnd ||
            this.bottomEnd != bottomEnd ||
            this.bottomStart != bottomStart ||
            this.extension != extension ||
            this.layoutDirectionAware != layoutDirectionAware
        if (this.fillColor != fillColor || geometryChanged) {
            this.fillColor = fillColor
            this.topStart = topStart
            this.topEnd = topEnd
            this.bottomEnd = bottomEnd
            this.bottomStart = bottomStart
            this.extension = extension
            this.layoutDirectionAware = layoutDirectionAware
            if (geometryChanged) {
                cachedPathSize = Size.Unspecified
            }
            invalidateDraw()
        }
    }

    override fun ContentDrawScope.draw() {
        val w = size.width
        val h = size.height
        if (w <= 0f || h <= 0f) {
            drawContent()
            return
        }
        val halfMin = minOf(w, h) * 0.5f
        val scale = cornerScale(density, extension)
        val sTop = cornerTilePx(topStart, scale, halfMin)
        val eTop = cornerTilePx(topEnd, scale, halfMin)
        val eBottom = cornerTilePx(bottomEnd, scale, halfMin)
        val sBottom = cornerTilePx(bottomStart, scale, halfMin)
        val flip = layoutDirectionAware && layoutDirection == LayoutDirection.Rtl
        val tl = if (flip) eTop else sTop
        val tr = if (flip) sTop else eTop
        val br = if (flip) sBottom else eBottom
        val bl = if (flip) eBottom else sBottom

        if (tl <= 0f && tr <= 0f && br <= 0f && bl <= 0f) {
            clipPath = null
            cachedPathSize = Size.Unspecified
            fillColor?.let { drawRect(it) }
            drawContent()
            return
        }
        if (fitsInOffscreenLayer(w, h)) {
            clipPath = null
            cachedPathSize = Size.Unspecified
            fillColor?.let { drawRect(it) }
            drawContent()
            if (!(tl >= halfMin && tr >= halfMin && br >= halfMin && bl >= halfMin)) {
                val cache = currentValueOf(LocalRuntimeShaderCache)
                maskSquircleCorners(shaderHolder, cache, tl, tr, br, bl)
            }
        } else {
            val path = obtainOversizeClipPath(size, tl, tr, br, bl)
            val fill = fillColor
            clipPath(path) {
                if (fill != null) drawRect(fill)
                this@draw.drawContent()
            }
        }
    }

    private fun obtainOversizeClipPath(
        size: Size,
        tl: Float,
        tr: Float,
        br: Float,
        bl: Float,
    ): Path {
        val existing = clipPath
        if (existing != null &&
            cachedPathSize == size &&
            cachedPathTl == tl &&
            cachedPathTr == tr &&
            cachedPathBr == br &&
            cachedPathBl == bl
        ) {
            return existing
        }
        val path = (existing ?: Path().also { clipPath = it }).apply {
            rewind()
            addSquircleTilesRect(size.width, size.height, tl, tr, br, bl)
        }
        cachedPathSize = size
        cachedPathTl = tl
        cachedPathTr = tr
        cachedPathBr = br
        cachedPathBl = bl
        return path
    }

    override fun onDetach() {
        clipPath = null
        cachedPathSize = Size.Unspecified
        shaderHolder.invalidateCache()
    }
}

// Carve the corner squares (the shader is opaque elsewhere) using disjoint integer-aligned quads.
// Must run inside an Offscreen layer already holding fill + content so DstIn carves against alpha.
private fun ContentDrawScope.maskSquircleCorners(
    holder: SquircleShaderHolder,
    shaderCache: RuntimeShaderCache,
    tl: Float,
    tr: Float,
    br: Float,
    bl: Float,
) {
    val width = size.width
    val height = size.height
    val brush = holder.obtainBrush(size, Color.White, tl, tr, br, bl, shaderCache)

    val tlSide = minOf(ceil(tl), width, height)
    val trSide = minOf(ceil(tr), width, height)
    val brSide = minOf(ceil(br), width, height)
    val blSide = minOf(ceil(bl), width, height)

    val topH = maxOf(tlSide, trSide)
    val blTop = if (blSide > 0f) floor(height - bl).coerceAtLeast(0f) else height
    val brTop = if (brSide > 0f) floor(height - br).coerceAtLeast(0f) else height
    val bottomBandTop = minOf(blTop, brTop)

    val trLeft = if (trSide > 0f) floor(width - tr).coerceAtLeast(0f) else width
    val brLeft = if (brSide > 0f) floor(width - br).coerceAtLeast(0f) else width

    // Top and bottom corner bands touch vertically: split into disjoint left/right end-caps when
    // wide enough, falling back to a single full-surface pass only when all four corners meet.
    if (topH >= bottomBandTop) {
        val leftW = maxOf(tlSide, blSide)
        val rightBandLeft = minOf(trLeft, brLeft)
        if (leftW > 0f && rightBandLeft < width && leftW >= rightBandLeft) {
            drawRect(brush = brush, blendMode = BlendMode.DstIn)
        } else {
            if (leftW > 0f) {
                drawRect(
                    brush = brush,
                    topLeft = Offset.Zero,
                    size = Size(leftW, height),
                    blendMode = BlendMode.DstIn,
                )
            }
            if (rightBandLeft < width) {
                drawRect(
                    brush = brush,
                    topLeft = Offset(rightBandLeft, 0f),
                    size = Size(width - rightBandLeft, height),
                    blendMode = BlendMode.DstIn,
                )
            }
        }
        return
    }

    if (topH > 0f) {
        if (tlSide > 0f && trSide > 0f && tlSide >= trLeft) {
            drawRect(
                brush = brush,
                topLeft = Offset.Zero,
                size = Size(width, topH),
                blendMode = BlendMode.DstIn,
            )
        } else {
            if (tlSide > 0f) {
                drawRect(
                    brush = brush,
                    topLeft = Offset.Zero,
                    size = Size(tlSide, tlSide),
                    blendMode = BlendMode.DstIn,
                )
            }
            if (trSide > 0f) {
                drawRect(
                    brush = brush,
                    topLeft = Offset(trLeft, 0f),
                    size = Size(width - trLeft, trSide),
                    blendMode = BlendMode.DstIn,
                )
            }
        }
    }

    if (blSide > 0f || brSide > 0f) {
        if (blSide > 0f && brSide > 0f && blSide >= brLeft) {
            drawRect(
                brush = brush,
                topLeft = Offset(0f, bottomBandTop),
                size = Size(width, height - bottomBandTop),
                blendMode = BlendMode.DstIn,
            )
        } else {
            if (brSide > 0f) {
                drawRect(
                    brush = brush,
                    topLeft = Offset(brLeft, brTop),
                    size = Size(width - brLeft, height - brTop),
                    blendMode = BlendMode.DstIn,
                )
            }
            if (blSide > 0f) {
                drawRect(
                    brush = brush,
                    topLeft = Offset(0f, blTop),
                    size = Size(blSide, height - blTop),
                    blendMode = BlendMode.DstIn,
                )
            }
        }
    }
}

private class SquircleShaderHolder {
    private var boundCache: RuntimeShaderCache? = null
    private var boundShader: RuntimeShader? = null
    private var cachedBrush: ShaderBrush? = null
    private var cachedWidth: Float = Float.NaN
    private var cachedHeight: Float = Float.NaN
    private var cachedColor: Color = Color.Unspecified
    private var cachedTl: Float = Float.NaN
    private var cachedTr: Float = Float.NaN
    private var cachedBr: Float = Float.NaN
    private var cachedBl: Float = Float.NaN
    private var w0: Float = 0f
    private var w1: Float = 0f
    private var w2: Float = 0f
    private var w3: Float = 0f

    fun invalidateCache() {
        cachedBrush = null
        boundShader = null
        boundCache = null
        cachedWidth = Float.NaN
    }

    fun obtainBrush(
        size: Size,
        color: Color,
        tl: Float,
        tr: Float,
        br: Float,
        bl: Float,
        shaderCache: RuntimeShaderCache,
    ): ShaderBrush {
        val geometryUnchanged = cachedWidth == size.width &&
            cachedHeight == size.height &&
            cachedTl == tl &&
            cachedTr == tr &&
            cachedBr == br &&
            cachedBl == bl
        if (isRuntimeShaderBrushImmutable() && boundCache === shaderCache) {
            val cached = cachedBrush
            if (cached != null && geometryUnchanged && cachedColor == color) {
                return cached
            }
        }
        if (!geometryUnchanged) {
            val halfMin = minOf(size.width, size.height) * 0.5f
            val threshold = halfMin * BLEND_THRESHOLD_RATIO
            val range = halfMin - threshold
            if (range <= 0f) {
                w0 = 1f
                w1 = 1f
                w2 = 1f
                w3 = 1f
            } else {
                val invRange = 1f / range
                w0 = ((tl - threshold) * invRange).coerceIn(0f, 1f)
                w1 = ((tr - threshold) * invRange).coerceIn(0f, 1f)
                w2 = ((br - threshold) * invRange).coerceIn(0f, 1f)
                w3 = ((bl - threshold) * invRange).coerceIn(0f, 1f)
            }
            cachedWidth = size.width
            cachedHeight = size.height
            cachedTl = tl
            cachedTr = tr
            cachedBr = br
            cachedBl = bl
        }
        val shader = if (boundCache === shaderCache && boundShader != null) {
            boundShader!!
        } else {
            shaderCache.obtainRuntimeShader(SQUIRCLE_SHADER_KEY, SQUIRCLE_SHADER).also {
                it.setInputShader("cornerSdf", bakedSdfShader)
                boundCache = shaderCache
                boundShader = it
            }
        }
        shader.setColorUniform("color", color)
        shader.setFloatUniform("size", size.width, size.height)
        shader.setFloatUniform("cornerSizes", tl, tr, br, bl)
        shader.setFloatUniform("blendWeights", w0, w1, w2, w3)
        val brush = shader.asBrush()
        if (isRuntimeShaderBrushImmutable()) {
            cachedBrush = brush
            cachedColor = color
        }
        return brush
    }
}

private const val SQUIRCLE_SHADER_KEY = "SquircleBackground"

// Max single-axis dimension and pixel area composited in one offscreen layer. Keeps normal cards and
// up-to-4K full-screen pages on a single cached offscreen layer while preventing oversize / ultra-tall
// cards from overflowing GPU max texture dimensions or memory budgets.
private const val MAX_OFFSCREEN_PX = 4096f
private const val MAX_OFFSCREEN_AREA_PX = 4096f * 2160f

// Decoded once per process; LINEAR-sampled so the corner branch does one bilinear tap (see [makeLinearAlphaShader]).
private val bakedSdfShader: Shader by lazy {
    makeLinearAlphaShader(BakedSquircleSdf.SIZE, BakedSquircleSdf.decodeBytes())
}

private const val SQUIRCLE_SHADER = """
uniform shader cornerSdf;
uniform float2 size;
uniform float4 cornerSizes;
uniform float4 blendWeights;
layout(color) uniform half4 color;

half4 main(float2 coord) {
    float dxL = coord.x;
    float dxR = size.x - coord.x;
    float dyT = coord.y;
    float dyB = size.y - coord.y;
    bool left = dxL < dxR;
    bool top = dyT < dyB;
    float dx = left ? dxL : dxR;
    float dy = top ? dyT : dyB;
    // vec4 swizzle: AGSL forbids dynamic indexing. Channel order: x=topLeft, y=topRight, z=bottomRight, w=bottomLeft.
    float cornerSize = top
        ? (left ? cornerSizes.x : cornerSizes.y)
        : (left ? cornerSizes.w : cornerSizes.z);
    if (dx >= cornerSize || dy >= cornerSize) {
        return color;
    }
    float blendWeight = top
        ? (left ? blendWeights.x : blendWeights.y)
        : (left ? blendWeights.w : blendWeights.z);
    float dist;
    if (blendWeight <= 0.0) {
        float2 uv = float2(dx, dy) / cornerSize;
        half sdfSample = cornerSdf.eval(uv * ${BakedSquircleSdf.SIZE}.0).a;
        dist = (1.0 - 2.0 * float(sdfSample)) * (cornerSize * ${BakedSquircleSdf.HALF_RANGE});
    } else if (blendWeight >= 1.0) {
        float qx = cornerSize - dx;
        float qy = cornerSize - dy;
        dist = sqrt(qx * qx + qy * qy) - cornerSize;
    } else {
        float2 uv = float2(dx, dy) / cornerSize;
        half sdfSample = cornerSdf.eval(uv * ${BakedSquircleSdf.SIZE}.0).a;
        float squircleSdf = (1.0 - 2.0 * float(sdfSample)) * (cornerSize * ${BakedSquircleSdf.HALF_RANGE});
        float qx = cornerSize - dx;
        float qy = cornerSize - dy;
        float circleSdf = sqrt(qx * qx + qy * qy) - cornerSize;
        dist = mix(squircleSdf, circleSdf, blendWeight);
    }
    return color * half(smoothstep(0.5, -0.5, dist));
}
"""
