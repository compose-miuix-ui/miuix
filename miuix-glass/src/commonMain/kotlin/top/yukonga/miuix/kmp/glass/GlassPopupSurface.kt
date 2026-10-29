// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("ktlint:compose:multiple-emitters-check")

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.LayerOutsets
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.glass.internal.drawGlassStroke
import kotlin.math.roundToInt

/**
 * Size limits and window margin for a glass popup.
 *
 * @property minWidth Minimum panel width.
 * @property maxWidth Maximum panel width.
 * @property maxHeight Maximum panel height before content scrolls.
 * @property safeMargin Minimum margin from the window edges.
 */
@Immutable
data class GlassPopupSizing(
    val minWidth: Dp = GlassPopupDefaults.MinWidth,
    val maxWidth: Dp = GlassPopupDefaults.MaxWidth,
    val maxHeight: Dp = GlassPopupDefaults.MaxHeight,
    val safeMargin: Dp = GlassPopupDefaults.SafeMargin,
)

/**
 * Surface appearance of a glass popup.
 *
 * @property style Glass style supplying blur and rim geometry; glass shading is disabled.
 * @property alpha Material opacity multiplier.
 * @property stroke Optional bloom stroke.
 * @property shadow Optional panel shadow.
 * @property material Panel material.
 * @property containerColor Opaque fill used without a backdrop; stroke and shadow remain.
 */
@Immutable
data class GlassPopupVisuals(
    val style: GlassStyle,
    val alpha: Float,
    val stroke: GlassStroke?,
    val shadow: GlassShadow?,
    val material: GlassMaterial?,
    val containerColor: Color,
)

/** Panel bounds and corner radius for an animation frame. */
@Immutable
data class GlassPopupFrame(
    val rect: Rect,
    val cornerRadius: Dp,
)

/** Final popup bounds and vertical anchor alignment. */
@Immutable
class GlassPopupPlacement internal constructor(
    val rect: Rect,
    /** Whether the panel aligns to the anchor's top rather than its bottom. */
    val alignTop: Boolean,
)

/**
 * Aligns the popup to the anchor's end edge and clamps it to the page margins.
 * Opens upward when there is insufficient space below and more space above.
 * All bounds, sizes and margins use the same coordinate space and pixel units.
 */
internal fun placeGlassPopup(
    anchor: Rect,
    size: Size,
    margin: Float,
    page: Size,
    direction: LayoutDirection,
): GlassPopupPlacement {
    val endAlignedLeft = if (direction == LayoutDirection.Ltr) {
        anchor.right - size.width
    } else {
        anchor.left
    }
    val left = endAlignedLeft
        .coerceIn(margin, (page.width - size.width - margin).coerceAtLeast(margin))
    val below = page.height - margin - anchor.top
    val above = anchor.bottom - margin
    val alignTop = size.height <= below || below >= above
    val wanted = if (alignTop) anchor.top else anchor.bottom - size.height
    val top = wanted.coerceIn(margin, (page.height - size.height - margin).coerceAtLeast(margin))
    return GlassPopupPlacement(Rect(left, top, left + size.width, top + size.height), alignTop)
}

/**
 * Shared glass popup surface with outside-tap dismissal and fixed-size content during transitions.
 *
 * @param onDismissRequest Called when a tap outside should close the menu.
 * @param backdrop The [Backdrop] behind the glass. `null` uses [GlassPopupVisuals.containerColor]
 *   while retaining the configured bloom stroke and shadow.
 * @param modifier The modifier applied to the panel.
 * @param sizing Panel size limits and window margin.
 * @param visuals Panel surface appearance.
 * @param surface Optional background layers, drawn behind the rows inside the panel silhouette.
 * @param contentPadding Padding around the rows.
 * @param panelLayer Layer properties applied to the whole panel.
 * @param overlay Drawn over the panel and its rows, inside the silhouette.
 * @param frame Current panel bounds and corner radius, given the final panel size and page size.
 * @param contentLayer Row layer properties, given the final panel size and current bounds.
 * @param reveal Optional opening clip, such as [popupClipReveal].
 * @param onMeasured Called with the final panel size on each measurement.
 * @param interactive Whether the panel's rows can receive input and accessibility actions.
 * @param scrollable Whether rows scroll within the safe-area-constrained viewport.
 * @param content The rows.
 */
// The full-page dismiss layer and positioned panel must be siblings in the popup host.
@Composable
internal fun GlassPopupSurface(
    onDismissRequest: () -> Unit,
    backdrop: Backdrop?,
    sizing: GlassPopupSizing,
    visuals: GlassPopupVisuals,
    contentPadding: PaddingValues,
    panelLayer: GraphicsLayerScope.() -> Unit,
    overlay: DrawScope.() -> Unit,
    frame: Density.(end: Size, page: Size) -> GlassPopupFrame,
    contentLayer: GraphicsLayerScope.(end: Size, rect: Rect) -> Unit,
    modifier: Modifier = Modifier,
    reveal: Modifier = Modifier,
    surface: (@Composable BoxScope.(GlassShape) -> Unit)? = null,
    onMeasured: ((Size) -> Unit)? = null,
    interactive: Boolean = true,
    scrollable: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitEachGesture {
                    val down = awaitFirstDown(requireUnconsumed = false)
                    down.consume()
                    while (true) {
                        val event = awaitPointerEvent(PointerEventPass.Initial)
                        val change = event.changes.firstOrNull { it.id == down.id } ?: break
                        change.consume()
                        if (!change.pressed) {
                            currentOnDismissRequest()
                            break
                        }
                    }
                }
            },
    )

    val density = LocalDensity.current
    val shadowOutsets = remember(visuals.shadow, density) {
        with(density) { popupShadowOutsets(visuals.shadow) }
    }
    val measured = remember { MeasuredPanel() }
    val shape = remember(measured) {
        val corner = MeasuredCorner(measured)
        GlassShape(corner, corner, corner, corner)
    }

    Layout(
        modifier = modifier
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                layout(constraints.maxWidth, constraints.maxHeight) {
                    placeable.place(measured.rect.left.roundToInt(), measured.rect.top.roundToInt())
                }
            }
            .graphicsLayer {
                panelLayer()
                // Include shadow pixels in the offscreen buffer used when alpha is below one.
                outsets = shadowOutsets
            }
            .then(reveal)
            .glassShadow(shape, visuals.shadow, visuals.alpha)
            .clip(shape)
            .drawWithContent {
                drawContent()
                overlay()
            }
            .then(
                if (interactive) {
                    Modifier
                } else {
                    Modifier
                        .clearAndSetSemantics { }
                        .pointerInput(Unit) {
                            awaitPointerEventScope {
                                while (true) {
                                    awaitPointerEvent(PointerEventPass.Initial).changes.forEach { it.consume() }
                                }
                            }
                        }
                },
            ),
        content = {
            Box {
                if (surface != null) {
                    surface(shape)
                } else {
                    GlassPopupBackground(backdrop, shape, visuals)
                }
            }
            GlassPopupColumn(
                modifier = Modifier
                    .then(if (scrollable) Modifier.verticalScroll(rememberScrollState()) else Modifier)
                    .graphicsLayer {
                        transformOrigin = TransformOrigin(0f, 0f)
                        contentLayer(measured.end, measured.rect)
                    }
                    .padding(contentPadding),
                content = content,
            )
        },
    ) { measurables, constraints ->
        val safeInset = if (scrollable) sizing.safeMargin.roundToPx() * 2 else 0
        val widest = sizing.maxWidth.roundToPx().coerceAtMost((constraints.maxWidth - safeInset).coerceAtLeast(0))
        val narrowest = sizing.minWidth.roundToPx().coerceAtMost(widest)
        val tallest = sizing.maxHeight.roundToPx().coerceAtMost((constraints.maxHeight - safeInset).coerceAtLeast(0))
        val width = measurables[1].maxIntrinsicWidth(tallest).coerceIn(narrowest, widest)
        val viewportHeight = if (scrollable) {
            measurables[1].minIntrinsicHeight(width).coerceAtMost(tallest)
        } else {
            tallest
        }
        val foreground = measurables[1].measure(
            Constraints(minWidth = width, maxWidth = width, minHeight = 0, maxHeight = viewportHeight),
        )
        measured.end = Size(foreground.width.toFloat(), foreground.height.toFloat())
        onMeasured?.invoke(measured.end)
        val page = Size(constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat())
        val current = frame(measured.end, page)
        measured.rect = current.rect
        measured.cornerRadius = current.cornerRadius
        val panelWidth = current.rect.width.roundToInt().coerceAtLeast(0)
        val panelHeight = current.rect.height.roundToInt().coerceAtLeast(0)
        // Material and rim follow the animated silhouette; rows retain their settled size.
        val background = measurables[0].measure(Constraints.fixed(panelWidth, panelHeight))
        layout(panelWidth, panelHeight) {
            background.place(0, 0)
            foreground.place(0, 0)
        }
    }
}

/** A background layer that follows the panel without transforming its foreground content. */
@Composable
internal fun BoxScope.GlassPopupBackground(
    backdrop: Backdrop?,
    shape: GlassShape,
    visuals: GlassPopupVisuals,
    modifier: Modifier = Modifier,
    underlayMaterial: GlassMaterial? = null,
) {
    Box(
        modifier = Modifier.matchParentSize().then(modifier).then(
            if (backdrop == null) {
                Modifier.background(visuals.containerColor).drawWithContent {
                    drawContent()
                    visuals.stroke?.let { drawGlassStroke(shape, layoutDirection, it, visuals.alpha) }
                }
            } else if (underlayMaterial != null) {
                Modifier.glassOnActionBar(
                    backdrop = backdrop,
                    shape = shape,
                    style = visuals.style,
                    alpha = visuals.alpha,
                    material = visuals.material,
                    underlayMaterial = underlayMaterial,
                    stroke = visuals.stroke,
                )
            } else {
                Modifier.glass(
                    backdrop = backdrop,
                    shape = shape,
                    style = visuals.style,
                    alpha = visuals.alpha,
                    material = visuals.material,
                    stroke = visuals.stroke,
                    shading = false,
                )
            },
        ),
    )
}

/** Match drawGlassShadow's bounds, including its density conversion and offset. */
internal fun Density.popupShadowOutsets(shadow: GlassShadow?): LayerOutsets {
    if (shadow == null) return LayerOutsets.Zero
    val sourceScale = density / GlassDefaults.SourceDensity
    val reach = (shadow.radius * sourceScale).coerceAtLeast(1f)
    val x = shadow.offsetX * sourceScale
    val y = shadow.offsetY * sourceScale
    return LayerOutsets(
        left = (reach - x).coerceAtLeast(0f).toDp(),
        top = (reach - y).coerceAtLeast(0f).toDp(),
        right = (reach + x).coerceAtLeast(0f).toDp(),
        bottom = (reach + y).coerceAtLeast(0f).toDp(),
    )
}

/**
 * Geometry shared within the measurement pass. Not snapshot state: writes during measurement
 * must not invalidate that pass or defer geometry updates to the next frame.
 */
private class MeasuredPanel {
    var end: Size = Size.Zero
    var rect: Rect = Rect.Zero
    var cornerRadius: Dp = 0.dp
}

/** Resolves the animated radius at draw time to avoid a one-frame delay. */
private class MeasuredCorner(private val measured: MeasuredPanel) : CornerSize {
    override fun toPx(shapeSize: Size, density: Density): Float = with(density) { measured.cornerRadius.toPx() }

    override fun toString(): String = "MeasuredCorner"
}
