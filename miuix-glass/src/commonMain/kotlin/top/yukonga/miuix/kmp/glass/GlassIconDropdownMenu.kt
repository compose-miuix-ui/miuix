// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.layout.CascadingPopupDefaults

/**
 * A glass menu opened from an existing [GlassIconButton] anchor.
 *
 * Place this component in the same root [BoxScope] as the anchor. Entries with children open a
 * submenu with a back header and divider. Selection is caller-controlled; entry updates also
 * apply to an open submenu.
 * The secondary backdrop combines [secondaryBackdrop] with a recording of the primary popup,
 * excluding the secondary itself. Keep the supplied scene backdrop outside both popup layers.
 */
// The caller modifier belongs to the primary panel, not its full-page recording layer.
@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
fun BoxScope.GlassIconDropdownMenu(
    show: Boolean,
    onDismissRequest: () -> Unit,
    anchor: GlassPopupAnchor,
    anchorContent: @Composable () -> Unit,
    entries: List<DropdownEntry>,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    secondaryBackdrop: Backdrop? = backdrop,
    sizing: GlassPopupSizing = GlassPopupSizing(),
    horizontalAlignment: Alignment.Horizontal = Alignment.End,
    visuals: GlassPopupVisuals = GlassPopupDefaults.visuals(),
    collapseOnSelection: Boolean = entries.size <= 1,
) {
    var hasOpened by remember { mutableStateOf(show) }
    if (show) hasOpened = true
    if (!hasOpened) return
    var popupShow by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    var submenu by remember { mutableStateOf(false) }
    LaunchedEffect(show) {
        popupShow = show
        if (!show) submenu = false
    }
    var submenuPresent by remember { mutableStateOf(false) }
    val submenuPathState = remember { mutableStateOf<Pair<Int, Int>?>(null) }
    val submenuPath = submenuPathState.value
    var submenuAnchor by remember { mutableStateOf(Rect.Zero) }
    var primaryWidth by remember { mutableStateOf(sizing.minWidth) }
    val hostOriginState = remember { mutableStateOf(Offset.Zero) }
    val groups = remember(entries) { entries.filter { it.items.isNotEmpty() } }
    val submenuEntry = submenuPath?.let { groups.getOrNull(it.first) }
    val submenuItem = submenuPath?.let { submenuEntry?.items?.getOrNull(it.second) }
    val submenuShown = popupShow && submenu && !submenuItem?.children.isNullOrEmpty()
    val arrowTurn = CascadingPopupDefaults.arrowRotation(LocalLayoutDirection.current)
    val submenuArrow = animateFloatAsState(
        targetValue = if (submenuShown) arrowTurn else 0f,
        animationSpec = CascadingPopupDefaults.arrowSpring(submenuShown),
    )
    val menuVisibility = animateFloatAsState(
        targetValue = if (popupShow) 1f else 0f,
        animationSpec = GlassMotion.transformBounds(popupShow),
        label = "glassCascadeVisibility",
    )

    val primaryBackdrop = rememberLayerBackdrop()
    val layeredBackdrop = remember(secondaryBackdrop, primaryBackdrop) {
        secondaryBackdrop?.let { scene ->
            object : Backdrop {
                override val isCoordinatesDependent = true
                override val offsetResidualX: Float get() = scene.offsetResidualX
                override val offsetResidualY: Float get() = scene.offsetResidualY

                override fun DrawScope.drawBackdrop(
                    density: Density,
                    coordinates: LayoutCoordinates?,
                    layerBlock: (GraphicsLayerScope.() -> Unit)?,
                    downscaleFactor: Float,
                ) {
                    with(scene) { drawBackdrop(density, coordinates, layerBlock, downscaleFactor) }
                    with(primaryBackdrop) { drawBackdrop(density, coordinates, layerBlock, downscaleFactor) }
                }
            }
        }
    }
    val secondaryActive = submenuShown || submenuPresent
    // Only record the primary while a submenu is active; recording the secondary here would make it sample itself.
    Box(
        if (secondaryActive) {
            Modifier.matchParentSize().layerBackdrop(primaryBackdrop)
        } else {
            Modifier.matchParentSize()
        },
    ) {
        GlassTransformPopup(
            show = popupShow,
            onDismissRequest = {
                submenu = false
                onDismissRequest()
            },
            anchor = anchor,
            backdrop = backdrop,
            anchorContent = anchorContent,
            modifier = modifier,
            stacked = submenuShown,
            sizing = sizing,
            horizontalAlignment = horizontalAlignment,
            visuals = visuals,
            onMeasured = { primaryWidth = with(density) { it.width.toDp() } },
        ) {
            groups.forEachIndexed { groupIndex, entry ->
                entry.items.forEachIndexed { itemIndex, item ->
                    val children = item.children.orEmpty()
                    val hasChildren = children.isNotEmpty()
                    val rowBoundsState = if (hasChildren) {
                        remember(groupIndex, itemIndex) { mutableStateOf(Rect.Zero) }
                    } else {
                        null
                    }
                    val arrowRotationProvider = remember(groupIndex, itemIndex, submenuArrow, anchor) {
                        {
                            val path = submenuPathState.value
                            if (path?.first == groupIndex && path.second == itemIndex) {
                                submenuArrow.value * (1f - anchor.secondaryBackProgress)
                            } else {
                                0f
                            }
                        }
                    }
                    GlassPopupItem(
                        text = item.text,
                        summary = item.summary,
                        enabled = entry.enabled && item.enabled,
                        selected = item.selected,
                        showArrow = hasChildren,
                        arrowRotation = arrowRotationProvider,
                        modifier = if (rowBoundsState != null) {
                            Modifier.onGloballyPositioned { coordinates ->
                                if (!submenuPresent) rowBoundsState.value = coordinates.boundsInWindow()
                            }
                        } else {
                            Modifier
                        },
                        onClick = {
                            if (hasChildren) {
                                if (show && !submenuPresent) {
                                    submenuAnchor = rowBoundsState?.value ?: Rect.Zero
                                    submenuPathState.value = groupIndex to itemIndex
                                    submenuPresent = true
                                    submenu = true
                                }
                            } else {
                                item.onClick?.invoke()
                                if (collapseOnSelection) {
                                    submenu = false
                                    onDismissRequest()
                                }
                            }
                        },
                    )
                }
                if (groupIndex < groups.lastIndex) {
                    GlassMenuDivider()
                }
            }
        }
    }
    if (secondaryActive) {
        // Submenu dismissal collapses to the row; full dismissal also moves toward the toolbar anchor.
        Box(
            Modifier.matchParentSize()
                .onGloballyPositioned { hostOriginState.value = it.positionInWindow() }
                .graphicsLayer {
                    val progress = menuVisibility.value.coerceIn(0f, 1f)
                    val trigger = anchor.containerBounds
                    val closedScale = (trigger.width / primaryWidth.toPx()).coerceIn(0f, 1f)
                    val scale = closedScale + (1f - closedScale) * progress
                    // The anchor is reported in window coordinates; this layer is host-local.
                    val origin = hostOriginState.value
                    val centerX = trigger.center.x - origin.x
                    val centerY = trigger.center.y - origin.y
                    transformOrigin = TransformOrigin(
                        if (size.width > 0f) centerX / size.width else 0.5f,
                        if (size.height > 0f) centerY / size.height else 0.5f,
                    )
                    scaleX = scale
                    scaleY = scale
                    alpha = progress
                },
        ) {
            GlassSecondaryPopup(
                show = submenuShown,
                onDismissRequest = { submenu = false },
                anchorBounds = submenuAnchor,
                backdrop = layeredBackdrop,
                materialAnchor = anchor,
                sizing = sizing.copy(minWidth = primaryWidth),
                visuals = visuals,
                onDismissFinished = {
                    submenuPresent = false
                    submenuPathState.value = null
                },
            ) {
                submenuItem?.let { parent ->
                    val secondaryArrowRotation = remember(submenuArrow, anchor) {
                        { submenuArrow.value * (1f - anchor.secondaryBackProgress) }
                    }
                    GlassPopupItem(
                        text = parent.text,
                        summary = parent.summary,
                        selected = parent.selected,
                        showArrow = true,
                        arrowRotation = secondaryArrowRotation,
                        onClick = { submenu = false },
                    )
                    GlassMenuDivider()
                    parent.children.orEmpty().forEach { item ->
                        GlassPopupItem(
                            text = item.text,
                            summary = item.summary,
                            enabled = submenuEntry?.enabled == true && parent.enabled && item.enabled,
                            selected = item.selected,
                            onClick = {
                                item.onClick?.invoke()
                                if (collapseOnSelection) {
                                    submenu = false
                                    onDismissRequest()
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GlassMenuDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(
            horizontal = GlassPopupDefaults.DividerPaddingHorizontal,
            vertical = GlassPopupDefaults.DividerPaddingVertical,
        ),
        thickness = GlassPopupDefaults.DividerThickness,
    )
}
