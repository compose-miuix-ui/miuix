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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import top.yukonga.miuix.kmp.shader.RuntimeShader
import top.yukonga.miuix.kmp.shader.asComposeShader
import top.yukonga.miuix.kmp.shader.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.squircle.internal.BakedSquircleSdf
import top.yukonga.miuix.kmp.squircle.internal.makeAlphaImageBitmap

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
    val brush = rememberSquircleBrush(color, topStart, topEnd, bottomEnd, bottomStart, extension)
        ?: return this.background(
            color = color,
            shape = RoundedCornerShape(topStart, topEnd, bottomEnd, bottomStart),
        )
    return this.background(brush = brush, shape = RectangleShape)
}

/**
 * Clips the subtree to the squircle silhouette. Costs one offscreen GPU
 * layer per node (cached when contents are stable).
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
    val brush = rememberSquircleBrush(Color.White, topStart, topEnd, bottomEnd, bottomStart, extension)
        ?: return this.clip(RoundedCornerShape(topStart, topEnd, bottomEnd, bottomStart))
    return this
        .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
        .drawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.DstIn)
        }
}

/**
 * Fill + clip in a single offscreen layer — drop-in replacement for
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
    val brush = rememberSquircleBrush(Color.White, topStart, topEnd, bottomEnd, bottomStart, extension)
        ?: return this
            .clip(RoundedCornerShape(topStart, topEnd, bottomEnd, bottomStart))
            .background(color)
    return this
        .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
        .drawWithContent {
            drawRect(color)
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.DstIn)
        }
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
    val brush = rememberSquircleBrush(color, topLeft, topRight, bottomRight, bottomLeft, extension)
        ?: return this.background(
            color = color,
            shape = AbsoluteRoundedCornerShape(topLeft, topRight, bottomRight, bottomLeft),
        )
    return this.background(brush = brush, shape = RectangleShape)
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
    val brush = rememberSquircleBrush(Color.White, topLeft, topRight, bottomRight, bottomLeft, extension)
        ?: return this.clip(AbsoluteRoundedCornerShape(topLeft, topRight, bottomRight, bottomLeft))
    return this
        .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
        .drawWithContent {
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.DstIn)
        }
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
    val brush = rememberSquircleBrush(Color.White, topLeft, topRight, bottomRight, bottomLeft, extension)
        ?: return this
            .clip(AbsoluteRoundedCornerShape(topLeft, topRight, bottomRight, bottomLeft))
            .background(color)
    return this
        .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
        .drawWithContent {
            drawRect(color)
            drawContent()
            drawRect(brush = brush, blendMode = BlendMode.DstIn)
        }
}

@Composable
private fun rememberSquircleBrush(
    color: Color,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float,
): SquircleShaderBrush? {
    if (!LocalSquircleEnabled.current || !isRuntimeShaderSupported()) return null
    val density = LocalDensity.current
    val extClamped = extension.coerceIn(SquircleDefaults.ExtensionMin, SquircleDefaults.ExtensionMax)
    val tiles = remember(topStart, topEnd, bottomEnd, bottomStart, extClamped, density) {
        with(density) {
            floatArrayOf(
                topStart.toPx().coerceAtLeast(0f) * extClamped,
                topEnd.toPx().coerceAtLeast(0f) * extClamped,
                bottomEnd.toPx().coerceAtLeast(0f) * extClamped,
                bottomStart.toPx().coerceAtLeast(0f) * extClamped,
            )
        }
    }
    return remember(color, tiles) {
        SquircleShaderBrush(color, tiles)
    }
}

private class SquircleShaderBrush(
    private val color: Color,
    private val cornerTilesPx: FloatArray,
) : ShaderBrush() {
    private val runtimeShader = RuntimeShader(SQUIRCLE_SHADER)
    private val effectiveSizes = FloatArray(4)
    private val halfRanges = FloatArray(4)
    private val weights = FloatArray(4)

    override fun createShader(size: Size): Shader {
        val halfMin = minOf(size.width, size.height) * 0.5f
        val threshold = halfMin * BLEND_THRESHOLD_RATIO
        val range = halfMin - threshold
        for (i in 0..3) {
            val tile = cornerTilesPx[i]
            val effective = tile.coerceIn(0f, halfMin)
            effectiveSizes[i] = effective
            halfRanges[i] = BakedSquircleSdf.HALF_RANGE * effective
            weights[i] = if (range <= 0f) 1f else ((tile - threshold) / range).coerceIn(0f, 1f)
        }
        runtimeShader.setColorUniform("color", color)
        runtimeShader.setFloatUniform("size", size.width, size.height)
        runtimeShader.setFloatUniform("cornerSizes", effectiveSizes)
        runtimeShader.setFloatUniform("halfRangesPx", halfRanges)
        runtimeShader.setFloatUniform("blendWeights", weights)
        runtimeShader.setFloatUniform("bitmapSize", BakedSquircleSdf.SIZE.toFloat())
        runtimeShader.setInputShader("cornerSdf", bakedSdfShader)
        return runtimeShader.asComposeShader()
    }
}

// π/4 — squircle → circle blend threshold. Above this ratio of cornerSize / halfMin the SDF result
// is mixed with a pure circle SDF so capsule / pill / circle silhouettes degrade cleanly.
private const val BLEND_THRESHOLD_RATIO = 0.7853982f

// Decoded once per process — wraps the baked SDF bytes into a Skia ImageShader.
private val bakedSdfShader: Shader by lazy {
    ImageShader(
        makeAlphaImageBitmap(BakedSquircleSdf.SIZE, BakedSquircleSdf.bytes),
        TileMode.Clamp,
        TileMode.Clamp,
    )
}

private const val SQUIRCLE_SHADER = """
uniform shader cornerSdf;
uniform float2 size;
uniform float4 cornerSizes;
uniform float4 halfRangesPx;
uniform float4 blendWeights;
uniform float bitmapSize;
layout(color) uniform half4 color;

half sampleSdfBilinear(float2 uv) {
    float2 px = uv * bitmapSize - 0.5;
    float2 i = floor(px);
    float2 f = px - i;
    half a00 = cornerSdf.eval(i + float2(0.5, 0.5)).a;
    half a10 = cornerSdf.eval(i + float2(1.5, 0.5)).a;
    half a01 = cornerSdf.eval(i + float2(0.5, 1.5)).a;
    half a11 = cornerSdf.eval(i + float2(1.5, 1.5)).a;
    half a0 = mix(a00, a10, half(f.x));
    half a1 = mix(a01, a11, half(f.x));
    return mix(a0, a1, half(f.y));
}

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
    half sdfSample = sampleSdfBilinear(uv);
    float squircleSdf = (1.0 - 2.0 * float(sdfSample)) * halfRangePx;
    float qx = cornerSize - dx;
    float qy = cornerSize - dy;
    float circleSdf = sqrt(qx * qx + qy * qy) - cornerSize;
    float dist = mix(squircleSdf, circleSdf, blendWeight);
    return color * half(smoothstep(0.5, -0.5, dist));
}
"""
