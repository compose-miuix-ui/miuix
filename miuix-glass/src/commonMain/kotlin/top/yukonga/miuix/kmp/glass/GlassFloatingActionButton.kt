// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassColorBlendMode
import top.yukonga.miuix.kmp.material.GlassColorLayer
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassShadow
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.GlassStyle
import top.yukonga.miuix.kmp.theme.LocalContentColor
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Default values for [GlassFloatingActionButton]. */
object GlassFloatingActionButtonDefaults {

    /** Diameter of the glass floating action button. */
    val Size: Dp = 56.dp

    /** Glass material with a translucent primary colour layer. */
    @Composable
    fun material(): GlassMaterial {
        val base = GlassTopAppBarDefaults.buttonMaterial()
        val primary = MiuixTheme.colorScheme.primary
        return remember(base, primary) {
            base.copy(third = GlassColorLayer(primary.copy(alpha = 0.92f), GlassColorBlendMode.SrcOver))
        }
    }

    /** Primary colour fill when backdrop rendering is unavailable. */
    @Composable
    @ReadOnlyComposable
    fun fill(): Color = MiuixTheme.colorScheme.primary

    @Composable
    @ReadOnlyComposable
    fun stroke(): EdgeStroke = GlassTopAppBarDefaults.buttonStroke()
}

/**
 * A round, primary-tinted glass floating action button that scales down on press.
 *
 * @param onClick Called when the button is tapped.
 * @param modifier Modifier applied to the button.
 * @param backdrop Optional backdrop sampled behind the button; `null` uses [fill].
 * @param surfaceAlpha Opacity multiplier for the glass colour layers and stroke.
 * @param style Glass style forwarded to the renderer.
 * @param material Glass material applied when [backdrop] is provided.
 * @param size Button diameter.
 * @param shape Button silhouette.
 * @param fill Fallback fill colour when backdrop rendering is unavailable.
 * @param stroke Optional bloom stroke.
 * @param shadow Shadow beneath the button; `null` disables it.
 * @param content Button content.
 */
@Composable
fun GlassFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backdrop: Backdrop? = null,
    surfaceAlpha: Float = 1f,
    style: GlassStyle = GlassDefaults.Style,
    material: GlassMaterial = GlassFloatingActionButtonDefaults.material(),
    size: Dp = GlassFloatingActionButtonDefaults.Size,
    shape: GlassShape = remember(size) { GlassShape(size / 2) },
    fill: Color = GlassFloatingActionButtonDefaults.fill(),
    stroke: EdgeStroke? = GlassFloatingActionButtonDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Regular,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val pressScale = animateFloatAsState(
        targetValue = if (pressed) 0.9f else 1f,
        animationSpec = GlassMotion.springOf(damping = 1f, response = if (pressed) 0.15f else 0.3f),
        label = "glassFabPressScale",
    )
    val shadowAlpha = remember(surfaceAlpha) { { surfaceAlpha } }
    GlassButtonSurface(
        backdrop = backdrop,
        floating = true,
        surfaceAlpha = surfaceAlpha,
        shadowAlpha = shadowAlpha,
        style = style,
        material = material,
        underlayMaterial = null,
        shape = shape,
        size = size,
        fill = fill,
        stroke = stroke,
        shadow = shadow,
        interactionSource = interactionSource,
        modifier = modifier
            .semantics { role = Role.Button }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .graphicsLayer {
                val scale = pressScale.value
                scaleX = scale
                scaleY = scale
            },
        content = {
            CompositionLocalProvider(LocalContentColor provides MiuixTheme.colorScheme.onPrimary) {
                content()
            }
        },
    )
}
