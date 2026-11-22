// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.GlassPopupSizing
import top.yukonga.miuix.kmp.glass.GlassSecondaryPopup
import top.yukonga.miuix.kmp.glass.GlassTransformPopup
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.glass.ChevronBackward
import top.yukonga.miuix.kmp.icon.glass.More
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassSecondaryPopupDemo() {
    val backdrop = rememberLayerBackdrop()
    val anchor = rememberGlassPopupAnchor()
    val sortOrders = remember { listOf("Newest first", "Oldest first", "Name") }
    val density = LocalDensity.current
    var showPrimary by remember { mutableStateOf(false) }
    var showSecondary by remember { mutableStateOf(false) }
    var secondaryPresent by remember { mutableStateOf(false) }
    var rowBounds by remember { mutableStateOf(Rect.Zero) }
    var frozenBounds by remember { mutableStateOf(Rect.Zero) }
    var menuWidth by remember { mutableStateOf(200.dp) }
    var sortOrder by remember { mutableStateOf("Newest first") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .layerBackdrop(backdrop)
                .background(demoBackground()),
        )
        GlassIconButton(
            onClick = { showPrimary = true },
            modifier = Modifier
                .padding(top = 32.dp)
                .glassPopupAnchor(anchor, cornerRadius = 24.dp, floating = true),
            backdrop = backdrop,
            size = 48.dp,
        ) {
            Icon(
                imageVector = MiuixIcons.Glass.More,
                contentDescription = "More actions",
                modifier = Modifier.size(28.dp).glassPopupAnchorContent(anchor),
                tint = MiuixTheme.colorScheme.onBackground,
            )
        }
        GlassTransformPopup(
            show = showPrimary,
            onDismissRequest = { showPrimary = false },
            anchor = anchor,
            backdrop = backdrop,
            simplified = true,
            stacked = secondaryPresent,
            onMeasured = { menuWidth = with(density) { it.width.toDp() } },
            anchorContent = {
                Icon(
                    imageVector = MiuixIcons.Glass.More,
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            },
        ) {
            GlassPopupItem(
                text = "Sort by",
                summary = sortOrder,
                showArrow = true,
                arrowRotation = { if (secondaryPresent) 90f * (1f - anchor.secondaryBackProgress) else 0f },
                modifier = Modifier.onGloballyPositioned {
                    if (!secondaryPresent) rowBounds = it.boundsInWindow()
                },
                onClick = {
                    frozenBounds = rowBounds
                    secondaryPresent = true
                    showSecondary = true
                },
            )
            GlassPopupItem(
                text = "Close actions",
                onClick = { showPrimary = false },
            )
        }
        GlassSecondaryPopup(
            show = showSecondary,
            onDismissRequest = { showSecondary = false },
            anchorBounds = frozenBounds,
            backdrop = backdrop,
            materialAnchor = anchor,
            sizing = GlassPopupSizing(minWidth = menuWidth),
            onDismissFinished = { secondaryPresent = false },
        ) {
            GlassPopupItem(
                text = "Back to actions",
                icon = MiuixIcons.Glass.ChevronBackward,
                onClick = { showSecondary = false },
            )
            sortOrders.forEach { label ->
                GlassPopupItem(
                    text = label,
                    selected = sortOrder == label,
                    onClick = {
                        sortOrder = label
                        showSecondary = false
                    },
                )
            }
        }
    }
}
