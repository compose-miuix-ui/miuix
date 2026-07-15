// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import android.graphics.BlendMode
import android.graphics.Shader
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import top.yukonga.miuix.kmp.blur.RuntimeShaderCache
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

// A runtime shader binds one source child here, so the composite is a blend-mode DAG instead:
// masked levels SrcOver-stacked over the sharp base ≡ PROGRESSIVE_COMPOSITE_SHADER's mix chain.
internal actual fun progressiveCompositeEffect(
    shaderCache: RuntimeShaderCache,
    sigmaX: Float,
    sigmaY: Float,
    ax: Float,
    ay: Float,
    projFull: Float,
    projZero: Float,
    preEffect: RenderEffect?,
    postEffect: RenderEffect?,
): RenderEffect? {
    val mask = shaderCache.obtainRuntimeShader("ProgLevelMask", PROGRESSIVE_LEVEL_MASK_SHADER)
    mask.setFloatUniform("in_gradAxis", ax, ay)
    mask.setFloatUniform("in_gradBand", projFull, projZero)
    val androidMask = mask.asAndroidRuntimeShader()
    val pre = preEffect?.asAndroidRenderEffect()

    // createRuntimeShaderEffect snapshots uniforms, so re-setting them between creations is safe.
    fun bandMask(level: Float, slope: Float): android.graphics.RenderEffect {
        mask.setFloatUniform("in_level", level)
        mask.setFloatUniform("in_slope", slope)
        return android.graphics.RenderEffect.createRuntimeShaderEffect(androidMask, "child")
    }

    fun maskedLevel(fraction: Float, level: Float): android.graphics.RenderEffect {
        val maskEffect = bandMask(level, 3f)
        val radiusX = sigmaToBlurRadius(sigmaX * fraction)
        val radiusY = sigmaToBlurRadius(sigmaY * fraction)
        var node: android.graphics.RenderEffect? = pre
        if (radiusX > 0f || radiusY > 0f) {
            node = if (node == null) {
                android.graphics.RenderEffect.createBlurEffect(radiusX, radiusY, Shader.TileMode.CLAMP)
            } else {
                android.graphics.RenderEffect.createBlurEffect(radiusX, radiusY, node, Shader.TileMode.CLAMP)
            }
        }
        return if (node == null) maskEffect else android.graphics.RenderEffect.createChainEffect(maskEffect, node)
    }

    var stack = android.graphics.RenderEffect.createOffsetEffect(0f, 0f)
    stack = android.graphics.RenderEffect.createBlendModeEffect(stack, maskedLevel(PROGRESSIVE_LEVEL_FRACTION_2, 3f), BlendMode.SRC_OVER)
    stack = android.graphics.RenderEffect.createBlendModeEffect(stack, maskedLevel(PROGRESSIVE_LEVEL_FRACTION_1, 2f), BlendMode.SRC_OVER)
    stack = android.graphics.RenderEffect.createBlendModeEffect(stack, maskedLevel(PROGRESSIVE_LEVEL_FRACTION_0, 1f), BlendMode.SRC_OVER)
    if (postEffect != null) {
        val postBranch = android.graphics.RenderEffect.createChainEffect(
            bandMask(1f, 1f),
            android.graphics.RenderEffect.createChainEffect(postEffect.asAndroidRenderEffect(), stack),
        )
        stack = android.graphics.RenderEffect.createBlendModeEffect(stack, postBranch, BlendMode.SRC_OVER)
    }
    return stack.asComposeRenderEffect()
}
