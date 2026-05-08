// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.effect.bg.bgEffect
import top.yukonga.miuix.kmp.effect.bg.rememberBgEffectState

/**
 * A composable that renders an animated gradient background effect.
 *
 * This is the primary public API for the background effect system. It renders a
 * multi-point gradient with Perlin noise perturbation, control-point oscillation,
 * and color palette cycling — producing an organic, living background surface.
 *
 * The effect is GPU-accelerated via runtime shaders (AGSL on Android, SkSL on Skiko)
 * and gracefully degrades to a plain [Box] on platforms without shader support.
 *
 * ### Visual Behavior
 *
 * The gradient consists of four colored control points that:
 * - **Drift** slowly using sin/cos oscillation, creating gentle organic movement.
 * - **Cycle colors** through three palettes with spring-physics interpolation.
 * - **Modulate saturation and brightness** based on animated Perlin noise.
 *
 * ### Usage
 *
 * ```kotlin
 * BgEffectBackground(
 *     dynamicBackground = isAnimating,
 *     deviceType = DeviceType.PHONE,
 *     isDarkTheme = isSystemInDarkTheme(),
 *     surface = MiuixTheme.colorScheme.surface,
 *     modifier = Modifier.fillMaxWidth().height(300.dp),
 * ) {
 *     // Foreground content rendered above the gradient
 *     Text("Hello, world!")
 * }
 * ```
 *
 * @param dynamicBackground Whether the gradient animation should be actively running.
 *   Set to `false` to freeze the animation at its current frame, preserving GPU resources.
 * @param deviceType Device form factor controlling gradient preset selection.
 *   Affects control-point positions, color saturation, and movement amplitude.
 * @param isDarkTheme Whether dark theme is active. Dark theme uses deeper colors,
 *   reduced brightness, and different saturation levels.
 * @param surface Base fill color drawn beneath the gradient. Typically matches the
 *   theme's surface or background color.
 * @param modifier Modifier applied to the outer [Box] container.
 * @param bgModifier Modifier applied to the inner [Spacer] that renders the gradient.
 *   Useful for applying backdrop effects (e.g., `layerBackdrop`) to the gradient layer.
 * @param isFullSize `true` to render the gradient across 80% of the viewport height;
 *   `false` for 50%. Higher values produce a more prominent, denser gradient.
 * @param effectBackground Whether to render the gradient overlay at all. When `false`,
 *   only the [surface] color is drawn. Useful for programmatic effect toggling.
 * @param isOs3Effect `true` for the HyperOS 3 effect variant (default); `false` for
 *   HyperOS 2. The OS3 variant has more vibrant colors and faster color cycling.
 * @param alpha Lambda returning the current opacity of the gradient overlay.
 *   Called once per draw frame. Return `0f` to skip gradient rendering entirely.
 *   Defaults to `{ 1f }` (fully opaque).
 * @param content Composable content rendered above the gradient background.
 *   Receives a [BoxScope] for alignment and sizing utilities.
 */
@Composable
fun BgEffectBackground(
    dynamicBackground: Boolean,
    deviceType: DeviceType,
    isDarkTheme: Boolean,
    surface: Color,
    modifier: Modifier = Modifier,
    bgModifier: Modifier = Modifier,
    isFullSize: Boolean = false,
    effectBackground: Boolean = true,
    isOs3Effect: Boolean = true,
    alpha: () -> Float = { 1f },
    content: @Composable (BoxScope.() -> Unit),
) {
    val shaderSupported = remember { isRuntimeShaderSupported() }
    if (!shaderSupported) {
        Box(modifier = modifier, content = content)
        return
    }
    Box(
        modifier = modifier,
    ) {
        val state = rememberBgEffectState(isOs3Effect = isOs3Effect)

        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .then(bgModifier)
                .bgEffect(
                    state = state,
                    deviceType = deviceType,
                    isDarkTheme = isDarkTheme,
                    surface = surface,
                    effectBackground = effectBackground,
                    isFullSize = isFullSize,
                    playing = dynamicBackground,
                    alpha = alpha,
                ),
        )
        content()
    }
}
