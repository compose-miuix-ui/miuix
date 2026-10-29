// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
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
                    .height(320.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MiuixTheme.colorScheme.surface),
                ) {
                    GlassDemoBackground(backdrop) {
                        Text(
                            text = "Current view: $view",
                            style = MiuixTheme.textStyles.title3,
                            color = MiuixTheme.colorScheme.onBackground,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 24.dp),
                        )
                    }
                    GlassIconButton(
                        onClick = { show = true },
                        modifier = Modifier
                            .align(Alignment.Center)
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
                }
            }
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
