// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.skiaImageFilter
import org.jetbrains.skia.BlendMode
import org.jetbrains.skia.FilterTileMode
import org.jetbrains.skia.ImageFilter
import top.yukonga.miuix.kmp.blur.RuntimeShaderCache
import top.yukonga.miuix.kmp.shader.RuntimeShader
import top.yukonga.miuix.kmp.shader.asSkikoRuntimeShader

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
    val shader = shaderCache.obtainRuntimeShader("ProgComposite", PROGRESSIVE_COMPOSITE_SHADER).apply {
        setFloatUniform("in_gradAxis", ax, ay)
        setFloatUniform("in_gradBand", projFull, projZero)
    }
    val pre = preEffect?.skiaImageFilter

    // Each level is pre → blur; clearChild samples the source untouched (null input).
    fun level(fraction: Float): ImageFilter = ImageFilter.makeBlur(sigmaX * fraction, sigmaY * fraction, FilterTileMode.CLAMP, pre, null)
    var filter = ImageFilter.makeRuntimeShader(
        shader.asSkikoRuntimeShader(),
        arrayOf("clearChild", "blur0", "blur1", "blur2"),
        arrayOf<ImageFilter?>(
            null,
            level(PROGRESSIVE_LEVEL_FRACTION_0),
            level(PROGRESSIVE_LEVEL_FRACTION_1),
            level(PROGRESSIVE_LEVEL_FRACTION_2),
        ),
    )
    if (postEffect != null) {
        val maskShader = shaderCache.obtainRuntimeShader("ProgLevelMask", PROGRESSIVE_LEVEL_MASK_SHADER).apply {
            setFloatUniform("in_gradAxis", ax, ay)
            setFloatUniform("in_gradBand", projFull, projZero)
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
