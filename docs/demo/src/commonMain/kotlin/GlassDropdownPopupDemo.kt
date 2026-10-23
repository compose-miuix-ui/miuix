// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Text
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

    Box(Modifier.fillMaxSize()) {
        GlassDemoBackground(backdrop) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter).padding(24.dp).widthIn(max = 600.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                Text("Playback settings", style = MiuixTheme.textStyles.title3)
                Text("Tap the row to choose a video quality.")
                Card {
                    BasicComponent(
                        title = "Quality",
                        summary = "Video playback",
                        modifier = Modifier.glassPopupAnchorRow(anchor).onGloballyPositioned {
                            rowBounds = it.boundsInRoot()
                        },
                        onClick = { show = true },
                        onClickLabel = "Choose playback quality",
                        endActions = {
                            Text(
                                text = "$quality ⌄",
                                modifier = Modifier.glassPopupAnchorValue(anchor),
                                color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                            )
                        },
                    )
                }
            }
            Text(
                text = "Playback quality: $quality",
                modifier = Modifier.align(Alignment.BottomCenter).padding(24.dp),
                color = MiuixTheme.colorScheme.onBackground,
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
