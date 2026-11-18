// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownArrowEndAction
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDropdownPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.glassPopupAnchorRow
import top.yukonga.miuix.kmp.glass.glassPopupAnchorValue
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassDropdownPopupDemo() {
    val backdrop = rememberLayerBackdrop()
    val anchor = rememberGlassPopupAnchor()
    val qualities = remember { listOf("Automatic", "1080p", "720p", "480p") }
    var show by remember { mutableStateOf(false) }
    var rowBounds by remember { mutableStateOf(Rect.Zero) }
    var quality by remember { mutableStateOf("Automatic") }

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
        Card(
            modifier = Modifier
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
        ) {
            BasicComponent(
                title = "Quality",
                summary = "Video playback",
                modifier = Modifier.glassPopupAnchorRow(anchor).onGloballyPositioned {
                    rowBounds = it.boundsInWindow()
                },
                onClick = { show = true },
                onClickLabel = "Choose playback quality",
                endActions = {
                    Row(
                        modifier = Modifier.glassPopupAnchorValue(anchor),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = quality,
                            modifier = Modifier.padding(end = 8.dp),
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                        DropdownArrowEndAction(
                            actionColor = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                    }
                },
            )
        }
        GlassDropdownPopup(
            show = show,
            onDismissRequest = { show = false },
            anchorBounds = rowBounds,
            anchor = anchor,
            backdrop = backdrop,
        ) {
            qualities.forEach { label ->
                GlassPopupItem(
                    text = label,
                    selected = quality == label,
                    onClick = {
                        quality = label
                        show = false
                    },
                )
            }
        }
    }
}
