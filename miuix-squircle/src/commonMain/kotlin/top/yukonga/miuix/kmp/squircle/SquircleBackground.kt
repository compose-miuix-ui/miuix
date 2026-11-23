// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import top.yukonga.miuix.kmp.material.LocalRuntimeShaderCache
import top.yukonga.miuix.kmp.material.RuntimeShaderCache
import top.yukonga.miuix.kmp.material.asBrush
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.squircle.internal.BakedSquircleSdf
import top.yukonga.miuix.kmp.squircle.internal.isShaderBrushImmutable
import top.yukonga.miuix.kmp.squircle.internal.makeAlphaImageBitmap
import top.yukonga.miuix.kmp.squircle.internal.makeLinearShader

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
fun Modifier.squircleBackground(
    color: Color,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val tiles = rememberSquircleTiles(topStart, topEnd, bottomEnd, bottomStart, extension)
        ?: return this.background(
            color = color,
            shape = RoundedCornerShape(topStart, topEnd, bottomEnd, bottomStart),
        )
    return if (color.isSpecified && color.alpha > 0f) {
        this then SquircleBackgroundElement(color, tiles, LocalRuntimeShaderCache.current)
    } else {
        this
    }
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
fun Modifier.squircleClip(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val tiles = rememberSquircleTiles(topStart, topEnd, bottomEnd, bottomStart, extension)
        ?: return this.clip(RoundedCornerShape(topStart, topEnd, bottomEnd, bottomStart))
    return this.squircleShaderMask(tiles, fillColor = null, shaderCache = LocalRuntimeShaderCache.current)
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
fun Modifier.squircleSurface(
    color: Color,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val effectiveFillColor = color.takeIf { it.isSpecified && it.alpha > 0f }
    val tiles = rememberSquircleTiles(topStart, topEnd, bottomEnd, bottomStart, extension)
        ?: return this
            .clip(RoundedCornerShape(topStart, topEnd, bottomEnd, bottomStart))
            .then(if (effectiveFillColor != null) Modifier.background(effectiveFillColor) else Modifier)
    return this.squircleShaderMask(tiles, fillColor = effectiveFillColor, shaderCache = LocalRuntimeShaderCache.current)
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
fun Modifier.absoluteSquircleBackground(
    color: Color,
    topLeft: Dp,
    topRight: Dp,
    bottomRight: Dp,
    bottomLeft: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val tiles = rememberSquircleTiles(topLeft, topRight, bottomRight, bottomLeft, extension)
        ?: return this.background(
            color = color,
            shape = AbsoluteRoundedCornerShape(topLeft, topRight, bottomRight, bottomLeft),
        )
    return if (color.isSpecified && color.alpha > 0f) {
        this then SquircleBackgroundElement(color, tiles, LocalRuntimeShaderCache.current)
    } else {
        this
    }
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
fun Modifier.absoluteSquircleClip(
    topLeft: Dp,
    topRight: Dp,
    bottomRight: Dp,
    bottomLeft: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val tiles = rememberSquircleTiles(topLeft, topRight, bottomRight, bottomLeft, extension)
        ?: return this.clip(AbsoluteRoundedCornerShape(topLeft, topRight, bottomRight, bottomLeft))
    return this.squircleShaderMask(tiles, fillColor = null, shaderCache = LocalRuntimeShaderCache.current)
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
fun Modifier.absoluteSquircleSurface(
    color: Color,
    topLeft: Dp,
    topRight: Dp,
    bottomRight: Dp,
    bottomLeft: Dp,
    extension: Float = SquircleDefaults.Extension,
): Modifier {
    val effectiveFillColor = color.takeIf { it.isSpecified && it.alpha > 0f }
    val tiles = rememberSquircleTiles(topLeft, topRight, bottomRight, bottomLeft, extension)
        ?: return this
            .clip(AbsoluteRoundedCornerShape(topLeft, topRight, bottomRight, bottomLeft))
            .then(if (effectiveFillColor != null) Modifier.background(effectiveFillColor) else Modifier)
    return this.squircleShaderMask(tiles, fillColor = effectiveFillColor, shaderCache = LocalRuntimeShaderCache.current)
}

/**
 * Shared squircle clip/surface mask for [squircleClip] / [squircleSurface] and their absolute
 * variants. Up to [MAX_OFFSCREEN_PX] the node composites through one [CompositingStrategy.Offscreen]
 * layer (cheap to cache and re-blit while scrolling) and only the corners are carved; past that a
 * full-node offscreen would overflow the GPU max texture and render transparent, so the content is
 * recorded and replayed per corner band instead — offscreen work tracks the corners, not the height.
 */
private val SquircleOffscreenLayerBlock: GraphicsLayerScope.() -> Unit = {
    compositingStrategy =
        if (maxOf(size.width, size.height) <= MAX_OFFSCREEN_PX) {
            CompositingStrategy.Offscreen
        } else {
            CompositingStrategy.Auto
        }
}

private fun Modifier.squircleShaderMask(
    cornerTilesPx: FloatArray,
    fillColor: Color?,
    shaderCache: RuntimeShaderCache,
): Modifier = this
    .graphicsLayer(SquircleOffscreenLayerBlock)
    .then(SquircleShaderMaskElement(cornerTilesPx, fillColor, shaderCache))

private class SquircleBackgroundElement(
    val color: Color,
    val cornerTilesPx: FloatArray,
    val shaderCache: RuntimeShaderCache,
) : ModifierNodeElement<SquircleBackgroundNode>() {
    override fun create(): SquircleBackgroundNode = SquircleBackgroundNode(color, cornerTilesPx, shaderCache)

    override fun update(node: SquircleBackgroundNode) {
        node.updateParams(color, cornerTilesPx, shaderCache)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "squircleBackground"
        properties["color"] = color
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SquircleBackgroundElement) return false
        if (color != other.color) return false
        if (shaderCache !== other.shaderCache) return false
        return cornerTilesPx === other.cornerTilesPx || cornerTilesPx.contentEquals(other.cornerTilesPx)
    }

    override fun hashCode(): Int {
        var result = color.hashCode()
        result = 31 * result + cornerTilesPx.contentHashCode()
        result = 31 * result + shaderCache.hashCode()
        return result
    }
}

private class SquircleBackgroundNode(
    private var color: Color,
    cornerTilesPx: FloatArray,
    shaderCache: RuntimeShaderCache,
) : Modifier.Node(),
    DrawModifierNode {

    private val shaderHolder = SquircleShaderHolder(cornerTilesPx, shaderCache)

    fun updateParams(color: Color, cornerTilesPx: FloatArray, shaderCache: RuntimeShaderCache) {
        val tilesChanged = shaderHolder.cornerTilesPx !== cornerTilesPx &&
            !shaderHolder.cornerTilesPx.contentEquals(cornerTilesPx)
        val cacheChanged = shaderHolder.shaderCache !== shaderCache
        if (this.color != color || tilesChanged || cacheChanged) {
            this.color = color
            shaderHolder.cornerTilesPx = cornerTilesPx
            shaderHolder.shaderCache = shaderCache
            shaderHolder.invalidateCache()
            invalidateDraw()
        }
    }

    override fun ContentDrawScope.draw() {
        if (size.width > 0f && size.height > 0f) {
            drawRect(brush = shaderHolder.obtainBrush(size, color))
        }
        drawContent()
    }

    override fun onDetach() {
        shaderHolder.invalidateCache()
    }
}

private class SquircleShaderMaskElement(
    val cornerTilesPx: FloatArray,
    val fillColor: Color?,
    val shaderCache: RuntimeShaderCache,
) : ModifierNodeElement<SquircleShaderMaskNode>() {
    override fun create(): SquircleShaderMaskNode = SquircleShaderMaskNode(cornerTilesPx, fillColor, shaderCache)

    override fun update(node: SquircleShaderMaskNode) {
        node.updateParams(cornerTilesPx, fillColor, shaderCache)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "squircleShaderMask"
        properties["fillColor"] = fillColor
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SquircleShaderMaskElement) return false
        if (fillColor != other.fillColor) return false
        if (shaderCache !== other.shaderCache) return false
        return cornerTilesPx === other.cornerTilesPx || cornerTilesPx.contentEquals(other.cornerTilesPx)
    }

    override fun hashCode(): Int {
        var result = cornerTilesPx.contentHashCode()
        result = 31 * result + fillColor.hashCode()
        result = 31 * result + shaderCache.hashCode()
        return result
    }
}

private class SquircleShaderMaskNode(
    cornerTilesPx: FloatArray,
    private var fillColor: Color?,
    shaderCache: RuntimeShaderCache,
) : Modifier.Node(),
    DrawModifierNode {

    private val shaderHolder = SquircleShaderHolder(cornerTilesPx, shaderCache)
    private var clipPath: Path? = null
    private var cachedPathSize: Size = Size.Unspecified
    private var cachedPathTiles: FloatArray? = null

    fun updateParams(cornerTilesPx: FloatArray, fillColor: Color?, shaderCache: RuntimeShaderCache) {
        val tilesChanged = shaderHolder.cornerTilesPx !== cornerTilesPx &&
            !shaderHolder.cornerTilesPx.contentEquals(cornerTilesPx)
        val cacheChanged = shaderHolder.shaderCache !== shaderCache
        if (this.fillColor != fillColor || tilesChanged || cacheChanged) {
            this.fillColor = fillColor
            shaderHolder.cornerTilesPx = cornerTilesPx
            shaderHolder.shaderCache = shaderCache
            if (tilesChanged) {
                cachedPathSize = Size.Unspecified
            }
            if (tilesChanged || cacheChanged) {
                shaderHolder.invalidateCache()
            }
            invalidateDraw()
        }
    }

    override fun ContentDrawScope.draw() {
        if (size.width <= 0f || size.height <= 0f) {
            drawContent()
            return
        }
        if (maxOf(size.width, size.height) <= MAX_OFFSCREEN_PX) {
            clipPath = null
            cachedPathSize = Size.Unspecified
            drawSquircleFast(shaderHolder, fillColor)
        } else {
            val path = obtainOversizeClipPath(size)
            val fill = fillColor
            clipPath(path) {
                if (fill != null) drawRect(fill)
                this@draw.drawContent()
            }
        }
    }

    private fun obtainOversizeClipPath(size: Size): Path {
        val existing = clipPath
        if (existing != null && cachedPathSize == size && cachedPathTiles === shaderHolder.cornerTilesPx) {
            return existing
        }
        val path = (existing ?: Path().also { clipPath = it }).apply { rewind() }
        val w = size.width
        val h = size.height
        val tl = shaderHolder.effectiveCornerPx(0, size)
        val tr = shaderHolder.effectiveCornerPx(1, size)
        val br = shaderHolder.effectiveCornerPx(2, size)
        val bl = shaderHolder.effectiveCornerPx(3, size)
        val handleFactor = 1f - SQUIRCLE_CONTROL
        path.moveTo(tl, 0f)
        path.lineTo(w - tr, 0f)
        if (tr > 0f) {
            val hTr = tr * handleFactor
            path.cubicTo(w - hTr, 0f, w, hTr, w, tr)
        }
        path.lineTo(w, h - br)
        if (br > 0f) {
            val hBr = br * handleFactor
            path.cubicTo(w, h - hBr, w - hBr, h, w - br, h)
        }
        path.lineTo(bl, h)
        if (bl > 0f) {
            val hBl = bl * handleFactor
            path.cubicTo(hBl, h, 0f, h - hBl, 0f, h - bl)
        }
        path.lineTo(0f, tl)
        if (tl > 0f) {
            val hTl = tl * handleFactor
            path.cubicTo(0f, hTl, hTl, 0f, tl, 0f)
        }
        path.close()
        cachedPathSize = size
        cachedPathTiles = shaderHolder.cornerTilesPx
        return path
    }

    override fun onDetach() {
        clipPath = null
        cachedPathSize = Size.Unspecified
        cachedPathTiles = null
        shaderHolder.invalidateCache()
    }
}

// Fast path: the enclosing Offscreen layer holds fill + content, so DstIn carves straight against it.
private fun ContentDrawScope.drawSquircleFast(
    holder: SquircleShaderHolder,
    fillColor: Color?,
) {
    if (fillColor != null) drawRect(fillColor)
    drawContent()
    maskSquircleCorners(holder, top = true, bottom = true)
}

// Carve the corner squares (the shader is opaque elsewhere). Must run inside an offscreen already
// holding fill + content, so DstIn has an alpha channel to carve against.
private fun ContentDrawScope.maskSquircleCorners(
    holder: SquircleShaderHolder,
    top: Boolean,
    bottom: Boolean,
) {
    val tl = if (top) holder.effectiveCornerPx(0, size) else 0f
    val tr = if (top) holder.effectiveCornerPx(1, size) else 0f
    val br = if (bottom) holder.effectiveCornerPx(2, size) else 0f
    val bl = if (bottom) holder.effectiveCornerPx(3, size) else 0f
    if (tl <= 0f && tr <= 0f && br <= 0f && bl <= 0f) return
    val brush = holder.obtainBrush(size, Color.White)
    val width = size.width
    val height = size.height
    val canvas = drawContext.canvas
    fun corner(left: Float, topPx: Float, side: Float) {
        if (side <= 0f) return
        canvas.save()
        canvas.clipRect(left, topPx, left + side, topPx + side)
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
        canvas.restore()
    }
    corner(0f, 0f, tl)
    corner(width - tr, 0f, tr)
    corner(width - br, height - br, br)
    corner(0f, height - bl, bl)
}

@Composable
private fun rememberSquircleTiles(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float,
): FloatArray? {
    if (!LocalSquircleEnabled.current || !isRuntimeShaderSupported()) return null
    val density = LocalDensity.current
    val extClamped = extension.coerceIn(SquircleDefaults.ExtensionMin, SquircleDefaults.ExtensionMax)
    return remember(topStart, topEnd, bottomEnd, bottomStart, extClamped, density) {
        with(density) {
            floatArrayOf(
                topStart.toPx().coerceAtLeast(0f) * extClamped,
                topEnd.toPx().coerceAtLeast(0f) * extClamped,
                bottomEnd.toPx().coerceAtLeast(0f) * extClamped,
                bottomStart.toPx().coerceAtLeast(0f) * extClamped,
            )
        }
    }
}

private class SquircleShaderHolder(
    var cornerTilesPx: FloatArray,
    var shaderCache: RuntimeShaderCache,
) {
    private val effectiveSizes = FloatArray(4)
    private val halfRanges = FloatArray(4)
    private val weights = FloatArray(4)
    private var cachedBrush: ShaderBrush? = null
    private var cachedSize: Size = Size.Unspecified
    private var cachedColor: Color = Color.Unspecified
    private var cachedTiles: FloatArray? = null

    fun invalidateCache() {
        cachedBrush = null
    }

    fun obtainBrush(size: Size, color: Color): ShaderBrush {
        if (isShaderBrushImmutable) {
            val cached = cachedBrush
            if (cached != null && cachedSize == size && cachedColor == color && cachedTiles === cornerTilesPx) {
                return cached
            }
        }
        val halfMin = minOf(size.width, size.height) * 0.5f
        val threshold = halfMin * BLEND_THRESHOLD_RATIO
        val range = halfMin - threshold
        for (i in 0..3) {
            val tile = cornerTilesPx[i]
            val effective = effectiveCornerPx(i, size)
            effectiveSizes[i] = effective
            halfRanges[i] = BakedSquircleSdf.HALF_RANGE * effective
            weights[i] = if (range <= 0f) 1f else ((tile - threshold) / range).coerceIn(0f, 1f)
        }
        val shader = shaderCache.obtainRuntimeShader(SQUIRCLE_SHADER_KEY, SQUIRCLE_SHADER)
        shader.setColorUniform("color", color)
        shader.setFloatUniform("size", size.width, size.height)
        shader.setFloatUniform("cornerSizes", effectiveSizes)
        shader.setFloatUniform("halfRangesPx", halfRanges)
        shader.setFloatUniform("blendWeights", weights)
        shader.setFloatUniform("bitmapSize", BakedSquircleSdf.SIZE.toFloat())
        shader.setInputShader("cornerSdf", bakedSdfShader)
        val brush = shader.asBrush()
        if (isShaderBrushImmutable) {
            cachedBrush = brush
            cachedSize = size
            cachedColor = color
            cachedTiles = cornerTilesPx
        }
        return brush
    }

    // Corner tile size the shader uses at [index], clamped to half the smaller side.
    fun effectiveCornerPx(index: Int, size: Size): Float {
        val halfMin = minOf(size.width, size.height) * 0.5f
        return cornerTilesPx[index].coerceIn(0f, halfMin)
    }
}

private const val SQUIRCLE_SHADER_KEY = "SquircleBackground"

// Max node dimension composited in one offscreen layer. 4096 px is well within GPU max texture
// limits on RuntimeShader-capable targets and avoids banded saveLayer clipping on tall cards.
private const val MAX_OFFSCREEN_PX = 4096f

// π/4 — squircle → circle blend threshold. Above this ratio of cornerSize / halfMin the SDF result
// is mixed with a pure circle SDF so capsule / pill / circle silhouettes degrade cleanly.
private const val BLEND_THRESHOLD_RATIO = 0.7853982f

// Decoded once per process; LINEAR-sampled so the corner branch does one bilinear tap (see [makeLinearShader]).
private val bakedSdfShader: Shader by lazy {
    makeLinearShader(makeAlphaImageBitmap(BakedSquircleSdf.SIZE, BakedSquircleSdf.bytes))
}

private const val SQUIRCLE_SHADER = """
uniform shader cornerSdf;
uniform float2 size;
uniform float4 cornerSizes;
uniform float4 halfRangesPx;
uniform float4 blendWeights;
uniform float bitmapSize;
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
    // vec4 swizzle: AGSL forbids dynamic indexing. Channel order: x=topStart, y=topEnd, z=bottomEnd, w=bottomStart.
    float cornerSize = top
        ? (left ? cornerSizes.x : cornerSizes.y)
        : (left ? cornerSizes.w : cornerSizes.z);
    float halfRangePx = top
        ? (left ? halfRangesPx.x : halfRangesPx.y)
        : (left ? halfRangesPx.w : halfRangesPx.z);
    float blendWeight = top
        ? (left ? blendWeights.x : blendWeights.y)
        : (left ? blendWeights.w : blendWeights.z);
    if (dx >= cornerSize || dy >= cornerSize) {
        return color;
    }
    float2 uv = float2(dx, dy) / cornerSize;
    // LINEAR-sampled child: one tap == old 4-tap. Coord is uv*bitmapSize (NO -0.5; sampler adds half-texel).
    half sdfSample = cornerSdf.eval(uv * bitmapSize).a;
    float squircleSdf = (1.0 - 2.0 * float(sdfSample)) * halfRangePx;
    float qx = cornerSize - dx;
    float qy = cornerSize - dy;
    float circleSdf = sqrt(qx * qx + qy * qy) - cornerSize;
    float dist = mix(squircleSdf, circleSdf, blendWeight);
    return color * half(smoothstep(0.5, -0.5, dist));
}
"""
