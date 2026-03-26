// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur.internal

import top.yukonga.miuix.kmp.blur.BackdropEffectScope

/**
 * Applies blur effect to the backdrop using LM Gaussian algorithm.
 */
internal object BlurEffects {

    fun applyBlur(scope: BackdropEffectScope, radius: Float) {
        if (radius <= 0f) return
        scope.gaussianBlur(radius)
    }

    fun applyBlur(scope: BackdropEffectScope, radiusX: Float, radiusY: Float) {
        if (radiusX <= 0f && radiusY <= 0f) return
        scope.gaussianBlur(radiusX, radiusY)
    }
}
