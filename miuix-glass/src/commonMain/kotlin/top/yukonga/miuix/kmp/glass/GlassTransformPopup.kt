// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.layout.CascadingPopupDefaults
import kotlin.math.roundToInt

/**
 * The control a [GlassTransformPopup] grows out of.
 *
 * The source system opens a menu two ways, and this is the second of them. A plain control gets
 * [GlassPopup]: the panel is revealed at the control's corner and the control itself never moves.
 * A control that declares itself transformable instead *becomes* the panel — the panel starts as
 * the control's own rectangle, at the control's own place, with the control's own corner radius,
 * and travels and grows out to where the menu belongs. The control's contents come along with it
 * and dissolve out as the panel's contents dissolve in.
 *
 * Hold one with [rememberGlassPopupAnchor], report the control with [glassPopupAnchor] and its
 * contents with [glassPopupAnchorContent], and hand it to [GlassTransformPopup].
 */
@Stable
class GlassPopupAnchor {

    /** The control's outer bounds, in the root's coordinate space. */
    internal var containerBounds: Rect by mutableStateOf(Rect.Zero)

    /** The control's contents' bounds, in the root's coordinate space. */
    internal var contentBounds: Rect by mutableStateOf(Rect.Zero)

    /** The control's own corner radius. The panel's radius starts here and relaxes to its own. */
    internal var cornerRadius: Dp by mutableStateOf(0.dp)

    /**
     * Whether the control floats over its page rather than sitting in it.
     *
     * A floating control's panel is drawn at full strength for the whole journey, so the panel is
     * watched all the way home. A control that sits in the page instead has its panel fade with the
     * icon, and the panel is gone well before the spring has settled.
     */
    internal var floating: Boolean by mutableStateOf(true)

    /**
     * Whether the control is standing aside for the menu.
     *
     * Both openings that take a control need this, and both hand it back only when the menu has
     * finished leaving. [GlassTransformPopup] stands the whole control down, because from the
     * moment it opens the panel *is* the control and two of it would be drawn. [GlassDropdownPopup]
     * stands down only the value the row displays, because the list about to open is that value's
     * own choices. [glassPopupAnchor] and [glassPopupAnchorValue] read this; a control never has to.
     */
    internal var contentHidden: Boolean by mutableStateOf(false)

    /** How far the row's displayed value has faded. [GlassDropdownPopup] drives it. */
    internal var valueAlpha: Float by mutableFloatStateOf(1f)
}

/**
 * Remembers a [GlassPopupAnchor].
 *
 * The fade [glassPopupAnchorValue] draws with is animated here rather than inside the menu. A menu
 * stops composing the moment it has finished leaving, which is exactly when the control is due to
 * come back — driven from there, the control would be left stranded halfway.
 */
@Composable
fun rememberGlassPopupAnchor(): GlassPopupAnchor {
    val anchor = remember { GlassPopupAnchor() }
    val alpha = animateFloatAsState(
        targetValue = if (anchor.contentHidden) 0f else 1f,
        animationSpec = GlassMotion.default(),
        label = "glassPopupAnchorValue",
    ).value
    SideEffect { anchor.valueAlpha = alpha }
    return anchor
}

/**
 * Reports a control to [anchor], and stands it down while its menu is open.
 *
 * Put this on the control's outermost node — the pill, not the icon inside it. The panel begins
 * life as exactly this rectangle, with this corner radius, so the control itself has to go: two of
 * it would be drawn otherwise. It comes back when the panel has shrunk into it again.
 *
 * @param anchor The anchor to report to.
 * @param cornerRadius The control's own corner radius. Half the control's height, for a pill.
 * @param floating Whether the control floats over its page. A glass pill in a bar does, and its
 *   panel is then drawn at full strength for the whole journey instead of fading with the icon.
 */
@Stable
fun Modifier.glassPopupAnchor(
    anchor: GlassPopupAnchor,
    cornerRadius: Dp,
    floating: Boolean = true,
): Modifier = this
    .onGloballyPositioned {
        anchor.containerBounds = it.boundsInRoot()
        anchor.cornerRadius = cornerRadius
        anchor.floating = floating
    }
    .graphicsLayer { alpha = if (anchor.contentHidden) 0f else 1f }

/**
 * Narrows what the menu copies to one part of a control.
 *
 * Put this on the icon inside a control whose background the panel is already taking over. A glass
 * pill wants exactly that: the panel *is* the pill for the length of the animation, so copying the
 * pill as well would stack two of them and read as a doubled, too-bright glass. Left off, the menu
 * copies the control whole, which is what a control with nothing behind its icon wants.
 *
 * @param anchor The anchor to report to.
 */
@Stable
fun Modifier.glassPopupAnchorContent(anchor: GlassPopupAnchor): Modifier = this.onGloballyPositioned { anchor.contentBounds = it.boundsInRoot() }

/**
 * Reports a row to [anchor], for a [GlassDropdownPopup]. The row itself stays put.
 *
 * @param anchor The anchor to report to.
 */
@Stable
fun Modifier.glassPopupAnchorRow(anchor: GlassPopupAnchor): Modifier = this.onGloballyPositioned { anchor.containerBounds = it.boundsInRoot() }

/**
 * Fades a row's displayed value out while its list of choices is open.
 *
 * Put this on the value the row shows and the chevron beside it, not on the row. The list about to
 * open *is* that value's choices, so the source stands it down the moment the list starts to open
 * and hands it back only once the list has finished leaving. That asymmetry is deliberate: the
 * value going first is what makes the list read as coming out of it.
 *
 * @param anchor The anchor the row reported itself to.
 */
@Stable
fun Modifier.glassPopupAnchorValue(anchor: GlassPopupAnchor): Modifier = this.graphicsLayer {
    alpha = anchor.valueAlpha
}

/**
 * A menu that grows out of the control it belongs to, on glass.
 *
 * The second of the source system's three openings, and the one a control that can take part gets.
 * Four things move at once, each on its own curve:
 *
 * - The **panel's rectangle** runs from the control's out to the menu's on one spring, carrying the
 *   corner radius with it.
 * - The **panel's centre** runs on a quicker spring, so it arrives where it belongs before the
 *   panel has finished growing. That difference is the whole reason the travel does not read as a
 *   slide across the screen.
 * - The **control's contents** are copied, and the copy travels by exactly the centre's
 *   displacement, grows with the panel, fades out and blurs *up*.
 * - The **panel's contents** scale with the panel's width, fade in and blur *down*, 50ms behind the
 *   control's. On the way out the two swap which of them waits.
 *
 * @param show Whether the menu is open.
 * @param onDismissRequest Called when a tap outside should close it.
 * @param anchor The control the menu grows out of.
 * @param backdrop The [Backdrop] behind the glass. `null` falls back to an opaque fill.
 * @param anchorContent A copy of the control — its background as well as its icon, unless
 *   [glassPopupAnchorContent] named a smaller part.
 * @param modifier The modifier applied to the panel.
 * @param stacked Whether a second menu stands in front of this one. It shrinks and takes a wash,
 *   which is what the source does when a submenu opens over a menu.
 * @param maskColor The wash laid over it while [stacked].
 * @param sizing How wide and tall the panel may be.
 * @param visuals What its surface is made of.
 * @param anchorAlpha Opacity the control's own background has right now, so a bar control whose bar
 *   has not collapsed does not have a pill appear under it out of nothing.
 * @param cornerRadius Corner radius the panel settles at.
 * @param gap Gap between the control and the panel.
 * @param contentPadding Padding around the items.
 * @param onMeasured Called with the size the panel settles at. A second menu opened from one of its
 *   rows is measured at least this wide, which is what puts the two panels' edges in line.
 * @param content The items.
 */
@Composable
fun BoxScope.GlassTransformPopup(
    show: Boolean,
    onDismissRequest: () -> Unit,
    anchor: GlassPopupAnchor,
    backdrop: Backdrop?,
    anchorContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    stacked: Boolean = false,
    sizing: GlassPopupSizing = GlassPopupSizing(),
    visuals: GlassPopupVisuals = GlassPopupDefaults.visuals(),
    anchorAlpha: Float = 1f,
    maskColor: Color = GlassPopupDefaults.maskColor(),
    cornerRadius: Dp = GlassPopupDefaults.CornerRadius,
    gap: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical),
    onMeasured: ((Size) -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val bounds by animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = GlassMotion.transformBounds(show),
        label = "glassTransformBounds",
    )
    val center by animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = GlassMotion.transformCenter(show),
        label = "glassTransformCenter",
    )
    val iconMaterial by animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = GlassMotion.transformIconMaterial(show),
        label = "glassTransformIcon",
    )
    val contentMaterial by animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = GlassMotion.transformContentMaterial(show),
        label = "glassTransformContent",
    )

    val pushedBack by animateFloatAsState(
        targetValue = if (stacked) 1f else 0f,
        animationSpec = CascadingPopupDefaults.expandSpring(stacked),
        label = "glassTransformStacked",
    )
    val active = show || bounds > 0.0001f
    DisposableEffect(active, anchor) {
        anchor.contentHidden = active
        onDispose { anchor.contentHidden = false }
    }
    if (!active) return

    val startRect = anchor.containerBounds
    val iconRect = anchor.contentBounds.takeUnless { it.isEmpty } ?: startRect
    val startRadius = anchor.cornerRadius
    val surfaceAlpha = visuals.alpha * if (anchor.floating) {
        lerp(anchorAlpha, 1f, bounds.coerceIn(0f, 1f))
    } else {
        iconMaterial
    }
    val travel = remember { TransformTravel() }

    GlassPopupSurface(
        onDismissRequest = onDismissRequest,
        backdrop = backdrop,
        modifier = modifier,
        sizing = sizing,
        visuals = visuals.copy(alpha = surfaceAlpha),
        contentPadding = contentPadding,
        onMeasured = onMeasured,
        panelLayer = {
            val s = 1f + (CascadingPopupDefaults.PrimaryShrunkScale - 1f) * pushedBack
            scaleX = s
            scaleY = s
        },
        overlay = {
            if (pushedBack > 0.001f) drawRect(maskColor.copy(alpha = maskColor.alpha * pushedBack))
        },
        frame = { end, page ->
            val frame = transformFrame(
                anchor = startRect,
                end = end,
                page = page,
                margin = sizing.safeMargin.toPx(),
                gap = gap.toPx(),
                sizeFraction = bounds,
                positionFraction = center,
                startRadius = startRadius,
                endRadius = cornerRadius,
            )
            val settled = placeGlassPopup(startRect.translate(0f, gap.toPx()), end, sizing.safeMargin.toPx(), page)
            travel.endCenterX = settled.rect.center.x
            travel.endCenterY = settled.rect.center.y
            travel.endWidth = end.width
            frame
        },
        contentLayer = { end, _ ->
            val width = startRect.width + (end.width - startRect.width) * bounds
            val scale = if (end.width > 0f) (width / end.width).coerceAtMost(1f) else 1f
            scaleX = scale
            scaleY = scale
            alpha = contentMaterial
            val blur = GlassMotion.TRANSFORM_BLUR_PX * (1f - contentMaterial)
            renderEffect = if (blur > 0.5f) BlurEffect(blur, blur, TileMode.Decal) else null
        },
        content = content,
    )

    if (iconMaterial < 0.999f) {
        Box(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        Constraints.fixed(
                            width = iconRect.width.roundToInt().coerceAtLeast(0),
                            height = iconRect.height.roundToInt().coerceAtLeast(0),
                        ),
                    )
                    layout(constraints.maxWidth, constraints.maxHeight) {
                        placeable.place(iconRect.left.roundToInt(), iconRect.top.roundToInt())
                    }
                }
                .graphicsLayer {
                    translationX = (travel.endCenterX - startRect.center.x) * center
                    translationY = (travel.endCenterY - startRect.center.y) * center
                    val width = startRect.width + (travel.endWidth - startRect.width) * bounds
                    val growth = if (startRect.width > 0f) width / startRect.width else 1f
                    scaleX = growth
                    scaleY = growth
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                    alpha = 1f - iconMaterial
                    val blur = GlassMotion.TRANSFORM_BLUR_PX * iconMaterial
                    renderEffect = if (blur > 0.5f) BlurEffect(blur, blur, TileMode.Decal) else null
                },
            content = { anchorContent() },
        )
    }
}

/** The panel's rectangle partway from the control's own to the menu's. */
private fun transformFrame(
    anchor: Rect,
    end: Size,
    page: Size,
    margin: Float,
    gap: Float,
    sizeFraction: Float,
    positionFraction: Float,
    startRadius: Dp,
    endRadius: Dp,
): GlassPopupFrame {
    val placement = placeGlassPopup(anchor.translate(0f, gap), end, margin, page)
    val width = anchor.width + (end.width - anchor.width) * sizeFraction
    val height = anchor.height + (end.height - anchor.height) * sizeFraction
    val centerX = anchor.center.x + (placement.rect.center.x - anchor.center.x) * positionFraction
    val centerY = anchor.center.y + (placement.rect.center.y - anchor.center.y) * positionFraction
    return GlassPopupFrame(
        rect = Rect(
            left = centerX - width / 2f,
            top = centerY - height / 2f,
            right = centerX + width / 2f,
            bottom = centerY + height / 2f,
        ),
        cornerRadius = startRadius + (endRadius - startRadius) * sizeFraction,
    )
}

/**
 * How far the panel has travelled from the control, and how much bigger it is.
 *
 * Written during measurement and read at draw time by the copy of the control's contents, so the
 * two move as one. A plain holder, not snapshot state: the write happens mid-measure.
 */
private class TransformTravel {
    var endCenterX: Float = 0f
    var endCenterY: Float = 0f
    var endWidth: Float = 0f
}
