// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
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
    var message by remember { mutableStateOf("Open More, then Sort by.") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(demoBackground()),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MiuixTheme.colorScheme.surface),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .layerBackdrop(backdrop)
                            .background(MiuixTheme.colorScheme.surface),
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "Nested photo actions",
                                    style = MiuixTheme.textStyles.title2,
                                    color = MiuixTheme.colorScheme.onSurface,
                                )
                                Text(
                                    text = "Sort order: $sortOrder",
                                    color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                                )
                            }
                            GlassIconButton(
                                onClick = { showPrimary = true },
                                modifier = Modifier
                                    .glassPopupAnchor(anchor, cornerRadius = 24.dp, floating = true),
                                backdrop = backdrop,
                                size = 48.dp,
                            ) {
                                Icon(
                                    imageVector = MiuixIcons.Glass.More,
                                    contentDescription = "More photo actions",
                                    modifier = Modifier.size(24.dp).glassPopupAnchorContent(anchor),
                                    tint = MiuixTheme.colorScheme.onBackground,
                                )
                            }
                        }
                        Text(
                            text = message,
                            color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                        )
                    }
                }
            }
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
                    modifier = Modifier.size(24.dp),
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
                    if (!secondaryPresent) rowBounds = it.boundsInRoot()
                },
                onClick = {
                    frozenBounds = rowBounds
                    secondaryPresent = true
                    showSecondary = true
                },
            )
            GlassPopupItem(
                text = "Close actions",
                onClick = {
                    message = "Actions closed. Reopen More to try again."
                    showPrimary = false
                },
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
                        message = "Sorted by: $label"
                        showSecondary = false
                    },
                )
            }
        }
    }
}
