// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassShadow
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.GlassStyle
import top.yukonga.miuix.kmp.material.drawEdgeStroke
import top.yukonga.miuix.kmp.material.drawGlassMask
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.material.withUnderlayBlur
import top.yukonga.miuix.kmp.utils.MiuixIndication

/**
 * A round glass icon button that inherits material and motion inside [GlassTopAppBar].
 *
 * @param onClick Called when the button is tapped.
 * @param backdrop Optional backdrop. Inside [GlassTopAppBar], the button inherits the bar backdrop;
 *   elsewhere `null` uses [fill].
 * @param modifier Modifier applied to the button.
 * @param inheritTopBarMaterial Whether to inherit the bar's backdrop and underlay material. When
 *   false, use the button's own material over [backdrop], falling back to the bar's backdrop.
 * @param surfaceAlpha Opacity multiplier for the pill colour layers and stroke.
 * @param style Glass style forwarded to the renderer.
 * @param material Explicit button material. `null` inherits the bar material when
 *   [inheritTopBarMaterial] is true, otherwise uses [GlassTopAppBarDefaults.buttonMaterial].
 *   An inherited bar underlay contributes its blur to an explicit material.
 * @param size Button diameter.
 * @param shape Button silhouette.
 * @param shadow Shadow beneath the button; `null` disables it.
 * @param stroke Optional bloom stroke.
 * @param content Button content.
 */
@Composable
fun GlassIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    inheritTopBarMaterial: Boolean = true,
    backdrop: Backdrop? = null,
    surfaceAlpha: Float = 1f,
    style: GlassStyle = LocalGlassTopAppBarContext.current?.style ?: GlassDefaults.Style,
    material: GlassMaterial? = null,
    size: Dp = LocalGlassTopAppBarContext.current?.size ?: GlassTopAppBarDefaults.ButtonSize,
    shape: GlassShape = LocalGlassTopAppBarContext.current?.shape ?: remember(size) { GlassShape(size / 2) },
    fill: Color = LocalGlassTopAppBarContext.current?.fill ?: GlassTopAppBarDefaults.buttonFill(),
    stroke: EdgeStroke? = LocalGlassTopAppBarContext.current.let { if (it != null) it.stroke else GlassTopAppBarDefaults.buttonStroke() },
    shadow: GlassShadow? = LocalGlassTopAppBarContext.current.let { if (it != null) it.shadow else GlassShadows.Regular },
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val topBarContext = LocalGlassTopAppBarContext.current
    val usesTopBarButtonBackdrop = topBarContext?.onButtonBackdropConsumerChange != null &&
        (inheritTopBarMaterial || backdrop == null)
    DisposableEffect(topBarContext, usesTopBarButtonBackdrop) {
        val callback = if (usesTopBarButtonBackdrop) topBarContext.onButtonBackdropConsumerChange else null
        callback?.invoke(1)
        onDispose { callback?.invoke(-1) }
    }
    val materialContext = topBarContext.takeIf { inheritTopBarMaterial }
    val resolvedSurfaceAlpha = surfaceAlpha * (topBarContext?.alpha ?: 1f)
    val topBarProgress = topBarContext?.materialProgress
    val shadowAlpha = remember(resolvedSurfaceAlpha, topBarProgress) {
        { resolvedSurfaceAlpha * (topBarProgress?.value ?: 1f) }
    }
    GlassButtonSurface(
        backdrop = materialContext?.buttonBackdrop ?: backdrop ?: topBarContext?.buttonBackdrop,
        floating = topBarContext?.floating ?: true,
        surfaceAlpha = resolvedSurfaceAlpha,
        shadowAlpha = shadowAlpha,
        style = style,
        material = remember(material, materialContext?.buttonUnderlayMaterial) {
            material?.withUnderlayBlur(materialContext?.buttonUnderlayMaterial)
        } ?: materialContext?.material ?: GlassTopAppBarDefaults.buttonMaterial(),
        underlayMaterial = materialContext?.buttonUnderlayMaterial,
        shape = shape,
        size = size,
        fill = fill,
        stroke = stroke,
        shadow = shadow,
        sharedProgress = topBarContext?.materialProgress,
        sharedKeepMaterial = topBarContext?.keepMaterial,
        interactionSource = interactionSource,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            ),
        content = content,
    )
}

/** The material surface and press feedback shared by glass icon buttons. */
@Composable
// foldIn only reads the anchor marker; the modifier is still applied to exactly one root Box.
@Suppress("ktlint:compose:modifier-reused-check", "ktlint:compose:state-param-check")
internal fun GlassButtonSurface(
    backdrop: Backdrop?,
    floating: Boolean,
    surfaceAlpha: Float,
    shadowAlpha: () -> Float,
    style: GlassStyle,
    material: GlassMaterial,
    underlayMaterial: GlassMaterial?,
    fill: Color,
    shape: GlassShape,
    size: Dp,
    stroke: EdgeStroke?,
    shadow: GlassShadow?,
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource? = null,
    // Retain State for draw-phase reads; the bar owns the shared animation clock.
    sharedProgress: State<Float>? = null,
    sharedKeepMaterial: Boolean? = null,
    content: @Composable () -> Unit,
) {
    val popupAnchor = modifier.foldIn<GlassPopupAnchor?>(null) { found, element ->
        if (element is GlassPopupAnchorElement) element.anchor else found
    }
    DisposableEffect(popupAnchor) {
        onDispose { popupAnchor?.surface = null }
    }
    val progress = sharedProgress ?: run {
        val floatingTransition = updateTransition(targetState = floating, label = "glassTopBarButtonFloat")
        floatingTransition.animateFloat(
            transitionSpec = { GlassMotion.topBarButtonFloat() },
            label = "glassTopBarButtonMaterial",
        ) { active ->
            if (active) 1f else 0f
        }
    }
    val keepMaterial = sharedKeepMaterial ?: remember(floating, progress) {
        computedStateOf { floating || progress.value > 0f }
    }.value
    val opacity = surfaceAlpha.coerceIn(0f, 1f)
    val effectiveBackdrop = backdrop?.takeIf { isRuntimeShaderSupported() }
    val anchorSurface = if (popupAnchor != null) {
        remember(effectiveBackdrop, style, material, underlayMaterial, stroke, fill, shadow) {
            GlassAnchorSurface(effectiveBackdrop, style, material, underlayMaterial, stroke, fill, shadow)
        }
    } else {
        null
    }
    SideEffect {
        val anchor = popupAnchor ?: return@SideEffect
        anchor.surface = anchorSurface
        anchor.surfaceProgress = progress
        anchor.surfaceMaterialAlpha = opacity
        anchor.surfaceFloating = floating
    }
    val pressedOverlay = GlassTopAppBarDefaults.buttonPressedOverlay()

    Box(
        modifier = modifier
            .size(size)
            .glassShadow(shape, shadow, shadowAlpha),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer { alpha = progress.value }
                .then(
                    if (effectiveBackdrop != null) {
                        if (underlayMaterial != null) {
                            Modifier.glassOnActionBar(
                                backdrop = effectiveBackdrop,
                                shape = shape,
                                style = style,
                                alpha = opacity,
                                material = material,
                                underlayMaterial = underlayMaterial,
                                stroke = stroke.takeIf { keepMaterial },
                                enabled = keepMaterial,
                            )
                        } else {
                            Modifier.glass(
                                backdrop = effectiveBackdrop,
                                shape = shape,
                                style = style,
                                alpha = opacity,
                                material = material,
                                stroke = stroke.takeIf { keepMaterial },
                                enabled = keepMaterial,
                                shading = false,
                            )
                        }
                    } else {
                        Modifier
                            .then(
                                if (isRuntimeShaderSupported()) {
                                    Modifier.graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                                } else {
                                    Modifier.clip(shape)
                                },
                            )
                            .background(fill.copy(alpha = fill.alpha * opacity))
                            .drawWithContent {
                                drawContent()
                                if (stroke != null && keepMaterial) {
                                    drawEdgeStroke(shape, stroke, opacity)
                                }
                                drawGlassMask(shape)
                            }
                    },
                ),
        )
        if (interactionSource != null) {
            val indication = remember(pressedOverlay) {
                MiuixIndication(
                    color = pressedOverlay.copy(alpha = 1f),
                    pressAlpha = pressedOverlay.alpha,
                    hoverAlpha = 0f,
                )
            }
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(shape)
                    .indication(interactionSource, indication),
            )
        }
        content()
    }
}
