// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.foundation.background
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
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import top.yukonga.miuix.kmp.shader.RuntimeShader
import top.yukonga.miuix.kmp.shader.asComposeShader
import top.yukonga.miuix.kmp.shader.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.squircle.internal.makeAlphaImageBitmap
import kotlin.math.sqrt

/**
 * Squircle solid background — fill-only, descendants are NOT clipped.
 *
 * Falls back to `Modifier.background(color, RoundedCornerShape(cornerRadius))`
 * when runtime shaders are unavailable (Android < API 33).
 *
 * @param extension corner-tile size as a multiple of [cornerRadius]; 1.0
 *   matches a circular arc, 1.1 is the default continuous-corner look.
 *   Clamped to [1.0, 2.0].
 * @param control cubic Bézier handle ratio; 0.55228 reproduces a quarter
 *   circle, 0.63 is the default. Clamped to [0.3, 0.9].
 */
@Composable
fun Modifier.squircleBackground(
    color: Color,
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
    control: Float = SquircleDefaults.Control,
): Modifier = squircleBackground(color, cornerRadius, cornerRadius, cornerRadius, cornerRadius, extension, control)

/** Per-corner variant of [squircleBackground]. Ordering matches [RoundedCornerShape]. */
@Composable
fun Modifier.squircleBackground(
    color: Color,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float = SquircleDefaults.Extension,
    control: Float = SquircleDefaults.Control,
): Modifier {
    val brush = rememberSquircleBrush(color, topStart, topEnd, bottomEnd, bottomStart, extension, control)
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
 */
@Composable
fun Modifier.squircleClip(
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
    control: Float = SquircleDefaults.Control,
): Modifier = squircleClip(cornerRadius, cornerRadius, cornerRadius, cornerRadius, extension, control)

/** Per-corner variant of [squircleClip]. */
@Composable
fun Modifier.squircleClip(
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float = SquircleDefaults.Extension,
    control: Float = SquircleDefaults.Control,
): Modifier {
    val brush = rememberSquircleBrush(Color.White, topStart, topEnd, bottomEnd, bottomStart, extension, control)
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
 */
@Composable
fun Modifier.squircleSurface(
    color: Color,
    cornerRadius: Dp,
    extension: Float = SquircleDefaults.Extension,
    control: Float = SquircleDefaults.Control,
): Modifier = squircleSurface(color, cornerRadius, cornerRadius, cornerRadius, cornerRadius, extension, control)

/** Per-corner variant of [squircleSurface]. */
@Composable
fun Modifier.squircleSurface(
    color: Color,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float = SquircleDefaults.Extension,
    control: Float = SquircleDefaults.Control,
): Modifier {
    val brush = rememberSquircleBrush(Color.White, topStart, topEnd, bottomEnd, bottomStart, extension, control)
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

@Composable
private fun rememberSquircleBrush(
    color: Color,
    topStart: Dp,
    topEnd: Dp,
    bottomEnd: Dp,
    bottomStart: Dp,
    extension: Float,
    control: Float,
): SquircleShaderBrush? {
    if (!isRuntimeShaderSupported()) return null
    val density = LocalDensity.current
    val extClamped = extension.coerceIn(SquircleDefaults.ExtensionMin, SquircleDefaults.ExtensionMax)
    val ctrlClamped = control.coerceIn(SquircleDefaults.ControlMin, SquircleDefaults.ControlMax)
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
    val ctrlKey = (ctrlClamped * CONTROL_KEY_PRECISION).toInt()
    val sdfShader = remember(ctrlKey) { getOrCreateSdfShader(ctrlClamped, ctrlKey) }
    return remember(color, tiles, sdfShader) {
        SquircleShaderBrush(color, tiles, sdfShader)
    }
}

private class SquircleShaderBrush(
    private val color: Color,
    private val cornerTilesPx: FloatArray,
    private val sdfShader: Shader,
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
            halfRanges[i] = SDF_HALF_RANGE * effective
            weights[i] = if (range <= 0f) 1f else ((tile - threshold) / range).coerceIn(0f, 1f)
        }
        runtimeShader.setColorUniform("color", color)
        runtimeShader.setFloatUniform("size", size.width, size.height)
        runtimeShader.setFloatUniform("cornerSizes", effectiveSizes)
        runtimeShader.setFloatUniform("halfRangesPx", halfRanges)
        runtimeShader.setFloatUniform("blendWeights", weights)
        runtimeShader.setFloatUniform("bitmapSize", SDF_BITMAP_SIZE.toFloat())
        runtimeShader.setInputShader("cornerSdf", sdfShader)
        return runtimeShader.asComposeShader()
    }
}

private const val SDF_BITMAP_SIZE = 256
private const val SDF_HALF_RANGE = 0.125f
private const val BEZIER_SAMPLES = 64
private const val BLEND_THRESHOLD_RATIO = 0.7853982f // π/4 — squircle→circle blend starts here
private const val CONTROL_KEY_PRECISION = 100f

@OptIn(InternalCoroutinesApi::class)
private val sdfCacheLock = SynchronizedObject()
private val sdfShaderCache = mutableMapOf<Int, Shader>()

@OptIn(InternalCoroutinesApi::class)
private fun getOrCreateSdfShader(control: Float, key: Int): Shader = synchronized(sdfCacheLock) {
    sdfShaderCache.getOrPut(key) {
        ImageShader(
            makeAlphaImageBitmap(SDF_BITMAP_SIZE, generateSdfBytes(SDF_BITMAP_SIZE, control)),
            TileMode.Clamp,
            TileMode.Clamp,
        )
    }
}

private fun generateSdfBytes(size: Int, control: Float): ByteArray {
    val handle = 1f - control

    val bx = FloatArray(BEZIER_SAMPLES + 1)
    val by = FloatArray(BEZIER_SAMPLES + 1)
    for (i in 0..BEZIER_SAMPLES) {
        val t = i.toFloat() / BEZIER_SAMPLES
        val omt = 1f - t
        bx[i] = 3f * omt * t * t * handle + t * t * t
        by[i] = omt * omt * omt + 3f * omt * omt * t * handle
    }

    val bytes = ByteArray(size * size)
    val invSize = 1f / size
    val invRange2 = 1f / (2f * SDF_HALF_RANGE)

    for (py in 0 until size) {
        val y = (py + 0.5f) * invSize
        for (px in 0 until size) {
            val x = (px + 0.5f) * invSize

            var minSqDist = Float.MAX_VALUE
            var closestIdx = 0
            for (i in 0 until BEZIER_SAMPLES) {
                val ax = bx[i]
                val ay = by[i]
                val sx = bx[i + 1] - ax
                val sy = by[i + 1] - ay
                val len2 = sx * sx + sy * sy
                if (len2 < 1e-12f) continue
                var u = ((x - ax) * sx + (y - ay) * sy) / len2
                if (u < 0f) {
                    u = 0f
                } else if (u > 1f) {
                    u = 1f
                }
                val qx = ax + u * sx
                val qy = ay + u * sy
                val ddx = x - qx
                val ddy = y - qy
                val sqDist = ddx * ddx + ddy * ddy
                if (sqDist < minSqDist) {
                    minSqDist = sqDist
                    closestIdx = i
                }
            }
            val dist = sqrt(minSqDist)

            // Sign: tangent × point-to-curve cross product. Fill region is on the left of the bezier motion.
            val tx = bx[closestIdx + 1] - bx[closestIdx]
            val ty = by[closestIdx + 1] - by[closestIdx]
            val pdx = x - bx[closestIdx]
            val pdy = y - by[closestIdx]
            val cross = tx * pdy - ty * pdx
            val signedDist = if (cross > 0f) -dist else dist

            val alpha = (0.5f - signedDist * invRange2).coerceIn(0f, 1f)
            bytes[py * size + px] = (alpha * 255f + 0.5f).toInt().toByte()
        }
    }
    return bytes
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
