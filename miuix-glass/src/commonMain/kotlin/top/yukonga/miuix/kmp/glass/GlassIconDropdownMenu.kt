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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
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
    visuals: GlassPopupVisuals = GlassPopupDefaults.visuals(),
    collapseOnSelection: Boolean = entries.size <= 1,
) {
    val density = LocalDensity.current
    var submenu by remember { mutableStateOf(false) }
    var submenuPresent by remember { mutableStateOf(false) }
    var submenuPath by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var submenuAnchor by remember { mutableStateOf(Rect.Zero) }
    var primaryWidth by remember { mutableStateOf(sizing.minWidth) }
    val groups = remember(entries) { entries.filter { it.items.isNotEmpty() } }
    val submenuEntry = submenuPath?.let { groups.getOrNull(it.first) }
    val submenuItem = submenuPath?.let { submenuEntry?.items?.getOrNull(it.second) }
    val submenuShown = show && submenu && !submenuItem?.children.isNullOrEmpty()
    val arrowTurn = CascadingPopupDefaults.arrowRotation(LocalLayoutDirection.current)
    val submenuArrow = animateFloatAsState(
        targetValue = if (submenuShown) arrowTurn else 0f,
        animationSpec = CascadingPopupDefaults.arrowSpring(submenuShown),
    )
    val menuVisibility = animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = GlassMotion.transformBounds(show),
        label = "glassCascadeVisibility",
    )
    LaunchedEffect(show) {
        if (!show) submenu = false
    }

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
                    downscaleFactor: Int,
                ) {
                    with(scene) { drawBackdrop(density, coordinates, layerBlock, downscaleFactor) }
                    with(primaryBackdrop) { drawBackdrop(density, coordinates, layerBlock, downscaleFactor) }
                }
            }
        }
    }
    // Only record the primary. Recording the secondary here would make it sample itself.
    Box(Modifier.matchParentSize().layerBackdrop(primaryBackdrop)) {
        GlassTransformPopup(
            show = show,
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
            visuals = visuals,
            onMeasured = { primaryWidth = with(density) { it.width.toDp() } },
        ) {
            groups.forEachIndexed { groupIndex, entry ->
                entry.items.forEachIndexed { itemIndex, item ->
                    val children = item.children.orEmpty()
                    var rowBounds by remember(groupIndex, itemIndex) { mutableStateOf(Rect.Zero) }
                    GlassPopupItem(
                        text = item.text,
                        summary = item.summary,
                        enabled = entry.enabled && item.enabled,
                        selected = item.selected,
                        showArrow = children.isNotEmpty(),
                        arrowRotation = {
                            if (submenuPath?.first == groupIndex && submenuPath?.second == itemIndex) {
                                submenuArrow.value * (1f - anchor.secondaryBackProgress)
                            } else {
                                0f
                            }
                        },
                        modifier = if (children.isNotEmpty()) {
                            Modifier.onGloballyPositioned { coordinates ->
                                if (!submenuPresent) rowBounds = coordinates.boundsInRoot()
                            }
                        } else {
                            Modifier
                        },
                        onClick = {
                            if (children.isNotEmpty()) {
                                if (show && !submenuPresent) {
                                    submenuAnchor = rowBounds
                                    submenuPath = groupIndex to itemIndex
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
    // Submenu dismissal collapses to the row; full dismissal also moves toward the toolbar anchor.
    Box(
        Modifier.matchParentSize().graphicsLayer {
            val progress = menuVisibility.value.coerceIn(0f, 1f)
            val trigger = anchor.containerBounds
            val closedScale = (trigger.width / primaryWidth.toPx()).coerceIn(0f, 1f)
            val scale = closedScale + (1f - closedScale) * progress
            transformOrigin = TransformOrigin(
                if (size.width > 0f) trigger.center.x / size.width else 0.5f,
                if (size.height > 0f) trigger.center.y / size.height else 0.5f,
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
                submenuPath = null
            },
        ) {
            submenuItem?.let { parent ->
                GlassPopupItem(
                    text = parent.text,
                    summary = parent.summary,
                    selected = parent.selected,
                    showArrow = true,
                    arrowRotation = { submenuArrow.value * (1f - anchor.secondaryBackProgress) },
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
