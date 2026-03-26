// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import com.kyant.shapes.RoundedRectangle
import top.yukonga.miuix.kmp.theme.LocalContentColor
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A surface composable with background blur effect.
 *
 * This is the highest-level entry point for common use cases. The caller must provide a
 * [Backdrop] instance obtained via [rememberLayerBackdrop] and capture content
 * using [Modifier.layerBackdrop] on the background content.
 *
 * @param backdrop The [Backdrop] providing the background content to blur.
 * @param modifier The modifier to be applied to the surface.
 * @param cornerRadius The corner radius of the blurred surface.
 * @param blurRadius The blur radius in pixels.
 * @param noiseCoefficient Noise dithering coefficient for anti-banding.
 * @param colors Color adjustments applied after blur.
 * @param contentColor The color provided to content children via [LocalContentColor].
 * @param content The composable content displayed on top of the blur.
 */
@Composable
fun BlurSurface(
    backdrop: Backdrop,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = BlurDefaults.CornerRadius,
    blurRadius: Float = BlurDefaults.BlurRadius,
    noiseCoefficient: Float = BlurDefaults.NoiseCoefficient,
    colors: BlurColors = BlurDefaults.blurColors(),
    contentColor: Color = MiuixTheme.colorScheme.onSurface,
    content: @Composable () -> Unit,
) {
    val shape = remember(cornerRadius) { { RoundedRectangle(cornerRadius) } }

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Box(
            modifier = modifier
                .semantics(mergeDescendants = false) {
                    isTraversalGroup = true
                }
                .textureEffect(
                    backdrop = backdrop,
                    shape = shape,
                    blurRadius = blurRadius,
                    noiseCoefficient = noiseCoefficient,
                    colors = colors,
                ),
            propagateMinConstraints = true,
        ) {
            content()
        }
    }
}
