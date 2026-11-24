// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassBottomSheet
import top.yukonga.miuix.kmp.glass.GlassCard
import top.yukonga.miuix.kmp.glass.glass
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

@Composable
fun GlassCardDemo() {
    val backdrop = rememberLayerBackdrop()
    var showSheet by remember { mutableStateOf(false) }
    var previewsEnabled by remember { mutableStateOf(true) }

    Scaffold(
        containerColor = Color.Transparent,
    ) { _ ->
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
            Box(
                modifier = Modifier
                    .glass(backdrop = backdrop, shape = GlassShape(24.dp))
                    .clip(CircleShape)
                    .clickable { showSheet = true },
            ) {
                Text(
                    text = "Show GlassCards in a sheet",
                    style = MiuixTheme.textStyles.button,
                    color = MiuixTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                )
            }
            GlassBottomSheet(
                show = showSheet,
                backdrop = backdrop,
                title = "Wallpaper",
                onDismissRequest = { showSheet = false },
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 320.dp)
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                        .padding(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    GlassCard(
                        modifier = Modifier.fillMaxWidth(),
                        insideMargin = PaddingValues(horizontal = 16.dp, vertical = 18.dp),
                        pressFeedbackType = PressFeedbackType.Sink,
                        onClick = {},
                    ) {
                        Text("Choose from gallery", style = MiuixTheme.textStyles.main)
                    }
                    GlassCard(modifier = Modifier.fillMaxWidth()) {
                        ArrowPreference(title = "Recent wallpapers", onClick = {})
                        SwitchPreference(
                            title = "Show previews",
                            checked = previewsEnabled,
                            onCheckedChange = { previewsEnabled = it },
                        )
                    }
                }
            }
        }
    }
}
