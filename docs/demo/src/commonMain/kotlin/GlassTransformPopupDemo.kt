// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.GlassTransformPopup
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.glass.More
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassTransformPopupDemo() {
    val backdrop = rememberLayerBackdrop()
    val anchor = rememberGlassPopupAnchor()
    val layouts = remember { listOf("List", "Grid", "Compact") }
    var show by remember { mutableStateOf(false) }
    var view by remember { mutableStateOf("List") }

    Box(Modifier.fillMaxSize()) {
        GlassDemoBackground(backdrop) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter).padding(24.dp).widthIn(max = 600.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Library view", style = MiuixTheme.textStyles.title3)
                Text("Open the glass button to change the layout.")
            }
            Text(
                text = "Current view: $view",
                modifier = Modifier.align(Alignment.BottomCenter).padding(24.dp),
                color = MiuixTheme.colorScheme.onBackground,
            )
        }
        GlassIconButton(
            onClick = { show = true },
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 112.dp)
                .glassPopupAnchor(anchor, cornerRadius = 24.dp, floating = true),
            backdrop = backdrop,
            size = 48.dp,
        ) {
            Icon(
                imageVector = MiuixIcons.Glass.More,
                contentDescription = "Change library view",
                modifier = Modifier.size(24.dp).glassPopupAnchorContent(anchor),
                tint = MiuixTheme.colorScheme.onBackground,
            )
        }
        GlassTransformPopup(
            show = show,
            onDismissRequest = { show = false },
            anchor = anchor,
            backdrop = backdrop,
            simplified = true,
            anchorContent = {
                Icon(
                    imageVector = MiuixIcons.Glass.More,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            },
        ) {
            layouts.forEach { label ->
                GlassPopupItem(
                    text = label,
                    selected = view == label,
                    onClick = {
                        view = label
                        show = false
                    },
                )
            }
        }
    }
}
