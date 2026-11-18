// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassFloatingToolbar
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.glass.Delete
import top.yukonga.miuix.kmp.icon.glass.Edit
import top.yukonga.miuix.kmp.icon.glass.More
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassFloatingToolbarDemo() {
    val backdrop = rememberLayerBackdrop()
    var lastAction by remember { mutableStateOf("None") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .layerBackdrop(backdrop)
                .background(demoBackground()),
        )
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GlassFloatingToolbar(
                backdrop = backdrop,
                cornerRadius = 20.dp,
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    IconButton(onClick = { lastAction = "Edit" }) {
                        Icon(MiuixIcons.Glass.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { lastAction = "Delete" }) {
                        Icon(MiuixIcons.Glass.Delete, contentDescription = "Delete")
                    }
                    IconButton(onClick = { lastAction = "More" }) {
                        Icon(MiuixIcons.Glass.More, contentDescription = "More")
                    }
                }
            }
            Text(
                text = "Last action: $lastAction",
                style = MiuixTheme.textStyles.body2,
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }
}
