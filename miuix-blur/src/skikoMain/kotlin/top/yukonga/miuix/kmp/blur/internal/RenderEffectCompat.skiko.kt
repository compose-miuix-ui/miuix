// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.skiaImageFilter
import org.jetbrains.skia.BlendMode
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.ImageFilter
import top.yukonga.miuix.kmp.blur.BackdropEffectScopeImpl
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.asSkikoRuntimeShader

internal actual fun RenderEffect?.chain(other: RenderEffect): RenderEffect = if (this != null) {
    ImageFilter.makeCompose(
        other.skiaImageFilter,
        this.skiaImageFilter,
    ).asComposeRenderEffect()
} else {
    other
}

internal actual fun runtimeShaderEffect(
    runtimeShader: RuntimeShader,
    uniformShaderName: String,
): RenderEffect = ImageFilter.makeRuntimeShader(
    runtimeShader.asSkikoRuntimeShader(),
    uniformShaderName,
    null,
).asComposeRenderEffect()

internal actual fun progressiveStackEffect(
    scope: BackdropEffectScopeImpl,
    fullSigmaX: Float,
    fullSigmaY: Float,
    lightSigmaX: Float,
    lightSigmaY: Float,
    ax: Float,
    ay: Float,
    projFull: Float,
    projZero: Float,
    curve: Float,
    preEffect: RenderEffect?,
    postEffect: RenderEffect?,
    nativeSharpEnd: Boolean,
): RenderEffect? {
    val shader = scope.obtainRuntimeShader("ProgComposite", PROGRESSIVE_COMPOSITE_SHADER).apply {
        setFloatUniform("in_gradAxis", ax, ay)
        setFloatUniform("in_gradBand", projFull, projZero)
        setFloatUniform("in_curve", curve)
    }
    val pre = preEffect?.skiaImageFilter

    // Each level is pre → blur; null = the (pre-chained) source itself.
    fun level(sigmaX: Float, sigmaY: Float): ImageFilter? = if (sigmaX <= 0f && sigmaY <= 0f) {
        pre
    } else {
        ImageFilter.makeBlur(sigmaX, sigmaY, FilterTileMode.CLAMP, pre, null)
    }

    // clearChild := lightBlur by default, degenerating the last mix segment (the sharp end is the
    // caller's full-res overlay); with nativeSharpEnd the (pre-chained) source binds instead —
    // a null child samples the source. Skia evaluates the shared lightBlur instance once.
    val lightBlur = level(lightSigmaX, lightSigmaY)
    var filter = ImageFilter.makeRuntimeShader(
        shader.asSkikoRuntimeShader(),
        arrayOf("clearChild", "fullBlur", "lightBlur"),
        arrayOf(
            if (nativeSharpEnd) pre else lightBlur,
            level(fullSigmaX, fullSigmaY),
            lightBlur,
        ),
    )
    if (postEffect != null) {
        val maskShader = scope.obtainRuntimeShader("ProgLevelMask", PROGRESSIVE_LEVEL_MASK_SHADER).apply {
            setFloatUniform("in_gradAxis", ax, ay)
            setFloatUniform("in_gradBand", projFull, projZero)
            setFloatUniform("in_curve", curve)
            setFloatUniform("in_level", 1f)
            setFloatUniform("in_slope", 1f)
        }
        val postBranch = ImageFilter.makeRuntimeShader(
            maskShader.asSkikoRuntimeShader(),
            "child",
            ImageFilter.makeCompose(postEffect.skiaImageFilter, filter),
        )
        filter = ImageFilter.makeBlend(BlendMode.SRC_OVER, filter, postBranch, null)
    }
    return filter.asComposeRenderEffect()
}
