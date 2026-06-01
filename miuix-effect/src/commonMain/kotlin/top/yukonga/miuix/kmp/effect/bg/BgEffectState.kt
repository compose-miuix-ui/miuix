// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.bg

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember

/**
 * State holder for the background gradient effect.
 *
 * Owns the [BgEffectPainter] that manages GPU resources and an [Animatable]
 * that drives the color palette interpolation cycle. This class is `@Stable`
 * because its mutable properties ([animatableColorStage]) are Compose-observable
 * and its identity is preserved across recompositions via `remember`.
 *
 * Instances are created by [rememberBgEffectState] and should not be constructed
 * directly outside of composition.
 *
 * @property painter The shader painter managing GPU uniforms.
 * @property animatableColorStage Animatable driving the color cycle interpolation.
 *   Values like `0.0`, `1.0`, `2.0` represent discrete palette stages; fractional
 *   values represent smooth transitions between them.
 * @property isOs3 Whether this state uses the HyperOS 3 effect variant.
 */
@Stable
class BgEffectState internal constructor(
    internal val painter: BgEffectPainter,
    internal val animatableColorStage: Animatable<Float, *>,
    val isOs3Effect: Boolean,
) {
    /** Current color cycle stage, derived from [animatableColorStage]. */
    internal val colorStage: Float get() = animatableColorStage.value
}

/**
 * Creates and remembers a [BgEffectState] for the given effect variant.
 *
 * The returned state is keyed on [isOs3Effect]; changing this value will create
 * a new state with a fresh shader and reset the color animation.
 *
 * @param isOs3Effect `true` for the HyperOS 3 effect variant, `false` for HyperOS 2.
 *   Defaults to `true`.
 * @return A [BgEffectState] instance that survives recomposition.
 */
@Composable
fun rememberBgEffectState(
    isOs3Effect: Boolean = true,
): BgEffectState {
    val painter = remember(isOs3Effect) { BgEffectPainter(isOs3Effect) }
    val colorStage = remember { Animatable(0f) }
    return remember(painter) {
        BgEffectState(
            painter = painter,
            animatableColorStage = colorStage,
            isOs3Effect = isOs3Effect,
        )
    }
}
