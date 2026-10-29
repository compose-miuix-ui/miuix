// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.Backdrop

/** Default values for [GlassDropdownPopup]. */
object GlassDropdownDefaults {

    /** Expanded panel corner radius. */
    val CornerRadius: Dp = 24.dp

    /** Initial panel corner radius. */
    val StartCornerRadius: Dp = 4.dp
}

/**
 * A glass dropdown menu anchored to a settings row.
 *
 * Keep it composed until the exit animation finishes.
 *
 * @param show Whether the panel is open. Row input and accessibility actions stop immediately
 *   when this becomes false, while the exit animation finishes.
 * @param onDismissRequest Called when an outside tap or Back requests dismissal.
 * @param anchorBounds The row's bounds, in this composable's own coordinate space.
 * @param backdrop Background sampled by the panel. Exclude the popup itself. `null` uses an
 *   opaque fill while retaining the configured bloom stroke and shadow.
 * @param modifier The modifier applied to the panel.
 * @param anchor Optional row anchor. Hides the displayed value while open and aligns the panel
 *   horizontally to bounds reported by [glassPopupAnchorValue], or [anchorBounds] if unavailable.
 * @param sizing Panel size limits and window margin.
 * @param visuals Panel surface appearance.
 * @param cornerRadius Expanded panel corner radius.
 * @param contentPadding Padding around the rows.
 * @param content The rows. [GlassPopupItem] with `selected` shows a tick.
 */
@Composable
fun BoxScope.GlassDropdownPopup(
    show: Boolean,
    onDismissRequest: () -> Unit,
    anchorBounds: Rect,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    anchor: GlassPopupAnchor? = null,
    sizing: GlassPopupSizing = GlassPopupSizing(),
    visuals: GlassPopupVisuals = GlassPopupDefaults.visuals(),
    cornerRadius: Dp = GlassDropdownDefaults.CornerRadius,
    contentPadding: PaddingValues = PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical),
    content: @Composable ColumnScope.() -> Unit,
) {
    GlassDropdownPopupContent(
        show = show,
        onDismissRequest = onDismissRequest,
        anchorBounds = anchorBounds,
        backdrop = backdrop,
        modifier = modifier,
        anchor = anchor,
        sizing = sizing,
        visuals = visuals,
        cornerRadius = cornerRadius,
        contentPadding = contentPadding,
        content = content,
    )
}

/** Shared dropdown renderer; Scaffold hosts supply their origin in root coordinates. */
@Composable
internal fun BoxScope.GlassDropdownPopupContent(
    show: Boolean,
    onDismissRequest: () -> Unit,
    anchorBounds: Rect,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    anchor: GlassPopupAnchor? = null,
    sizing: GlassPopupSizing = GlassPopupSizing(),
    visuals: GlassPopupVisuals = GlassPopupDefaults.visuals(),
    cornerRadius: Dp = GlassDropdownDefaults.CornerRadius,
    contentPadding: PaddingValues = PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical),
    coordinateOrigin: Offset = Offset.Zero,
    onDismissFinished: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    // Scaffold hosts compose after opening is requested; start closed to retain the enter transition.
    val visibility = remember { MutableTransitionState(false) }
    LaunchedEffect(show) {
        visibility.targetState = show
    }
    val transition = rememberTransition(visibility, label = "glassDropdown")
    val size = transition.animateFloat(
        transitionSpec = { GlassMotion.arcBounds(targetState) },
        label = "glassDropdownSize",
    ) { if (it) 1f else 0f }
    val position by transition.animateFloat(
        transitionSpec = { GlassMotion.arcPosition(targetState) },
        label = "glassDropdownPosition",
    ) { if (it) 1f else 0f }
    val fade by transition.animateFloat(
        transitionSpec = { GlassMotion.arcFade(targetState) },
        label = "glassDropdownFade",
    ) { if (it) 1f else 0f }
    val sharpness by transition.animateFloat(
        transitionSpec = { GlassMotion.arcBlur(targetState) },
        label = "glassDropdownBlur",
    ) { if (it) 1f else 0f }

    val active by remember(show, size) {
        derivedStateOf { show || size.value > 0.0001f }
    }
    val currentOnDismissFinished by rememberUpdatedState(onDismissFinished)
    LaunchedEffect(show, active) {
        if (!show && !active) currentOnDismissFinished?.invoke()
    }
    val layoutDirection = LocalLayoutDirection.current
    val backProgress = rememberGlassPopupBackProgress(
        show = show,
        active = active,
        enabled = show,
        retainWhenInactive = true,
        resetSpec = GlassMotion.arcBounds(true),
        onDismissRequest = onDismissRequest,
    )
    DisposableEffect(anchor, backProgress) {
        anchor?.dropdownBackProgressState = backProgress
        onDispose {
            if (anchor?.dropdownBackProgressState === backProgress) {
                anchor.dropdownBackProgressState = null
            }
        }
    }
    fun geometryProgress() = popupFractionWithBack(size.value, backProgress.value)
    fun positionProgress() = popupFractionWithBack(position, backProgress.value)
    fun fadeProgress() = fade * (1f - backProgress.value.coerceIn(0f, 1f))
    fun sharpnessProgress() = sharpness * (1f - backProgress.value.coerceIn(0f, 1f))
    val standingDown = active
    DisposableEffect(anchor, standingDown) {
        anchor?.contentHidden = standingDown
        onDispose { anchor?.contentHidden = false }
    }
    if (!standingDown) return

    val startRadius = GlassDropdownDefaults.StartCornerRadius
    GlassPopupSurface(
        onDismissRequest = onDismissRequest,
        backdrop = backdrop,
        sizing = sizing,
        visuals = visuals,
        contentPadding = contentPadding,
        panelLayer = {
            alpha = if (show && geometryProgress() <= GlassMotion.ARC_VISIBLE_FRACTION) 0f else fadeProgress()
            val blur = (1f - sharpnessProgress()) * GlassMotion.ARC_EXIT_BLUR_DP * this.density
            renderEffect = if (blur > 0.5f) BlurEffect(blur, blur, TileMode.Decal) else null
        },
        overlay = {},
        frame = { end, page ->
            arcFrame(
                anchorBounds.translate(-coordinateOrigin),
                anchor?.dropdownValueBounds?.translate(-coordinateOrigin),
                end,
                page,
                sizing.safeMargin.toPx(),
                geometryProgress(),
                positionProgress(),
                startRadius,
                cornerRadius,
                layoutDirection,
            )
        },
        contentLayer = { _, _ ->
            val fraction = GlassMotion.ARC_START_WIDTH + (1f - GlassMotion.ARC_START_WIDTH) * geometryProgress()
            val scale = fraction.coerceAtMost(1f)
            scaleX = scale
            scaleY = scale
        },
        modifier = modifier,
        interactive = show,
        scrollable = true,
        content = content,
    )
}

/** Interpolates size and center independently for the dropdown's curved motion. */
private fun arcFrame(
    anchor: Rect,
    valueAnchor: Rect?,
    end: Size,
    page: Size,
    margin: Float,
    sizeFraction: Float,
    positionFraction: Float,
    startRadius: Dp,
    endRadius: Dp,
    layoutDirection: LayoutDirection,
): GlassPopupFrame {
    val placement = placeGlassDropdownPopup(anchor, valueAnchor, end, margin, page, layoutDirection)
    val startWidth = end.width * GlassMotion.ARC_START_WIDTH
    val startHeight = startWidth * GlassMotion.ARC_START_RATIO
    val width = startWidth + (end.width - startWidth) * sizeFraction
    val endRatio = if (end.width > 0f) end.height / end.width else 1f
    val ratio = GlassMotion.ARC_START_RATIO + (endRatio - GlassMotion.ARC_START_RATIO) * sizeFraction
    val height = ratio * width

    val alignLeft = valueAnchor?.let { it.center.x <= page.width / 2f } ?: (layoutDirection == LayoutDirection.Rtl)
    val startCenterX = if (alignLeft) {
        placement.rect.left + startWidth / 2f
    } else {
        placement.rect.right - startWidth / 2f
    }
    val startCenterY = if (placement.alignTop) {
        placement.rect.top + startHeight / 2f
    } else {
        placement.rect.bottom - startHeight / 2f
    }
    val centerX = startCenterX + (placement.rect.center.x - startCenterX) * positionFraction
    val centerY = startCenterY + (placement.rect.center.y - startCenterY) * positionFraction
    val radius = startRadius + (endRadius - startRadius) * sizeFraction
    return GlassPopupFrame(
        rect = Rect(
            left = centerX - width / 2f,
            top = centerY - height / 2f,
            right = centerX + width / 2f,
            bottom = centerY + height / 2f,
        ),
        cornerRadius = radius,
    )
}

internal fun placeGlassDropdownPopup(
    row: Rect,
    valueAnchor: Rect?,
    size: Size,
    margin: Float,
    page: Size,
    direction: LayoutDirection = LayoutDirection.Ltr,
): GlassPopupPlacement {
    if (valueAnchor == null) return placeGlassPopup(row, size, margin, page, direction)
    // Align to the value's nearest horizontal edge of the container.
    val wantedLeft = if (valueAnchor.center.x <= page.width / 2f) {
        valueAnchor.left
    } else {
        valueAnchor.right - size.width
    }
    val left = wantedLeft.coerceIn(margin, (page.width - size.width - margin).coerceAtLeast(margin))
    val verticalPlacement = placeGlassPopup(row, size, margin, page, LayoutDirection.Ltr)
    return GlassPopupPlacement(
        rect = Rect(left, verticalPlacement.rect.top, left + size.width, verticalPlacement.rect.bottom),
        alignTop = verticalPlacement.alignTop,
    )
}
