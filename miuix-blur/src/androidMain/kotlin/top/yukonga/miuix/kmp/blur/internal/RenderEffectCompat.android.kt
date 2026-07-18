// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import android.graphics.BlendMode
import android.graphics.Shader
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import top.yukonga.miuix.kmp.blur.BackdropEffectScopeImpl
import top.yukonga.miuix.kmp.shader.RuntimeShader
import top.yukonga.miuix.kmp.shader.asAndroidRuntimeShader

internal actual fun RenderEffect?.chain(other: RenderEffect): RenderEffect = if (this != null) {
    android.graphics.RenderEffect.createChainEffect(
        other.asAndroidRenderEffect(),
        this.asAndroidRenderEffect(),
    ).asComposeRenderEffect()
} else {
    other
}

internal actual fun runtimeShaderEffect(
    runtimeShader: RuntimeShader,
    uniformShaderName: String,
): RenderEffect = android.graphics.RenderEffect.createRuntimeShaderEffect(
    runtimeShader.asAndroidRuntimeShader(),
    uniformShaderName,
).asComposeRenderEffect()

/** Inverts createBlurEffect's radius→sigma mapping (σ = r·0.57735 + 0.5) to match Skiko's makeBlur sigmas. */
private fun sigmaToBlurRadius(sigma: Float): Float = ((sigma - 0.5f) / 0.57735f).coerceAtLeast(0f)

/**
 * Gradient band masks cached per scope: they depend only on the gradient, never the radii, so an
 * animating radius reuses them — their runtime-shader snapshots are the only expensive nodes in
 * the stack rebuild.
 */
private class ProgressiveMaskCache {
    var ax = Float.NaN
    var ay = Float.NaN
    var projFull = Float.NaN
    var projZero = Float.NaN
    var curve = Float.NaN
    lateinit var level2: android.graphics.RenderEffect
    lateinit var level1: android.graphics.RenderEffect

    /** Continuous `1 − raw` ramp (in_level = in_slope = 1) masking the post-effect branch. */
    lateinit var ramp: android.graphics.RenderEffect

    /** Rising `clamp(3·raw − 2)` ramp (in_level = −2, in_slope = −3) masking the native sharp end. */
    lateinit var sharp: android.graphics.RenderEffect

    /** Uniform-free unpremul pass chained after the unmasked base blur; built once per scope. */
    var unpremul: android.graphics.RenderEffect? = null
}

// A runtime shader binds one source child here, so the stack is a blend-mode DAG instead: masked
// levels SrcOver-stacked over the unmasked lightest level ≡ PROGRESSIVE_COMPOSITE_SHADER's first
// two mix segments (the blend to sharp is the caller's full-res overlay, or the in-stack source
// level with nativeSharpEnd).
internal actual fun progressiveStackEffect(
    scope: BackdropEffectScopeImpl,
    sigma0X: Float,
    sigma0Y: Float,
    sigma1X: Float,
    sigma1Y: Float,
    sigma2X: Float,
    sigma2Y: Float,
    ax: Float,
    ay: Float,
    projFull: Float,
    projZero: Float,
    curve: Float,
    preEffect: RenderEffect?,
    postEffect: RenderEffect?,
    nativeSharpEnd: Boolean,
): RenderEffect? {
    val masks = scope.progressiveMaskScratch as? ProgressiveMaskCache
        ?: ProgressiveMaskCache().also { scope.progressiveMaskScratch = it }
    if (masks.ax != ax || masks.ay != ay || masks.projFull != projFull ||
        masks.projZero != projZero || masks.curve != curve
    ) {
        val mask = scope.obtainRuntimeShader("ProgLevelMask", PROGRESSIVE_LEVEL_MASK_SHADER)
        mask.setFloatUniform("in_gradAxis", ax, ay)
        mask.setFloatUniform("in_gradBand", projFull, projZero)
        mask.setFloatUniform("in_curve", curve)
        val androidMask = mask.asAndroidRuntimeShader()

        // createRuntimeShaderEffect snapshots uniforms, so re-setting them between creations is safe.
        fun bandMask(level: Float, slope: Float): android.graphics.RenderEffect {
            mask.setFloatUniform("in_level", level)
            mask.setFloatUniform("in_slope", slope)
            return android.graphics.RenderEffect.createRuntimeShaderEffect(androidMask, "child")
        }

        masks.level2 = bandMask(2f, 3f)
        masks.level1 = bandMask(1f, 3f)
        masks.ramp = bandMask(1f, 1f)
        masks.sharp = bandMask(-2f, -3f)
        masks.ax = ax
        masks.ay = ay
        masks.projFull = projFull
        masks.projZero = projZero
        masks.curve = curve
    }
    val pre = preEffect?.asAndroidRenderEffect()

    // pre → blur for one level; null = the level is the (pre-chained) source itself. A blurred
    // level's alpha must be renormalized to opaque before it composites (its native blur mixes in
    // the padding ring's transparency, and sharp content bleeds through otherwise): the masked
    // levels get that inline from the band mask, the unmasked base from a chained unpremul pass.
    fun blurred(sigmaX: Float, sigmaY: Float): android.graphics.RenderEffect? {
        val radiusX = sigmaToBlurRadius(sigmaX)
        val radiusY = sigmaToBlurRadius(sigmaY)
        if (radiusX <= 0f && radiusY <= 0f) return pre
        return if (pre == null) {
            android.graphics.RenderEffect.createBlurEffect(radiusX, radiusY, Shader.TileMode.CLAMP)
        } else {
            android.graphics.RenderEffect.createBlurEffect(radiusX, radiusY, pre, Shader.TileMode.CLAMP)
        }
    }

    fun maskedLevel(sigmaX: Float, sigmaY: Float, maskEffect: android.graphics.RenderEffect): android.graphics.RenderEffect {
        val node = blurred(sigmaX, sigmaY)
        return if (node == null) maskEffect else android.graphics.RenderEffect.createChainEffect(maskEffect, node)
    }

    val base = blurred(sigma2X, sigma2Y)
    var stack = when {
        base == null -> android.graphics.RenderEffect.createOffsetEffect(0f, 0f)

        base === pre -> base

        else -> {
            val unpremul = masks.unpremul ?: android.graphics.RenderEffect.createRuntimeShaderEffect(
                scope.obtainRuntimeShader("ProgUnpremul", UNPREMUL_SHADER).asAndroidRuntimeShader(),
                "child",
            ).also { masks.unpremul = it }
            android.graphics.RenderEffect.createChainEffect(unpremul, base)
        }
    }
    stack = android.graphics.RenderEffect.createBlendModeEffect(stack, maskedLevel(sigma1X, sigma1Y, masks.level2), BlendMode.SRC_OVER)
    stack = android.graphics.RenderEffect.createBlendModeEffect(stack, maskedLevel(sigma0X, sigma0Y, masks.level1), BlendMode.SRC_OVER)
    if (nativeSharpEnd) {
        // The rising-masked (pre-chained) source ≡ the composite's last mix segment; its
        // smoothstep stays complementary to the level masks.
        val sharpLevel = if (pre == null) {
            masks.sharp
        } else {
            android.graphics.RenderEffect.createChainEffect(masks.sharp, pre)
        }
        stack = android.graphics.RenderEffect.createBlendModeEffect(stack, sharpLevel, BlendMode.SRC_OVER)
    }
    if (postEffect != null) {
        val postBranch = android.graphics.RenderEffect.createChainEffect(
            masks.ramp,
            android.graphics.RenderEffect.createChainEffect(postEffect.asAndroidRenderEffect(), stack),
        )
        stack = android.graphics.RenderEffect.createBlendModeEffect(stack, postBranch, BlendMode.SRC_OVER)
    }
    return stack.asComposeRenderEffect()
}
