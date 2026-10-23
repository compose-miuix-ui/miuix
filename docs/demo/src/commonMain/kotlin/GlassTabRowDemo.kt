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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassTabRow
import top.yukonga.miuix.kmp.glass.GlassTabRowDefaults
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassTabRowDemo() {
    val backdrop = rememberLayerBackdrop()
    val tabs = remember { listOf("All", "Photos", "Videos") }
    var primaryIndex by remember { mutableIntStateOf(0) }
    var neutralIndex by remember { mutableIntStateOf(0) }
    var segmentedIndex by remember { mutableIntStateOf(0) }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GlassDemoBackground(backdrop)
        Column(
            modifier = Modifier.padding(24.dp).widthIn(max = 600.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Primary: ${tabs[primaryIndex]}",
                    style = MiuixTheme.textStyles.title3,
                    color = MiuixTheme.colorScheme.onBackground,
                )
                GlassTabRow(
                    tabs = tabs,
                    selectedIndex = primaryIndex,
                    onSelect = { primaryIndex = it },
                    backdrop = backdrop,
                    modifier = Modifier.fillMaxWidth(),
                    colors = GlassTabRowDefaults.primaryColors(),
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Neutral: ${tabs[neutralIndex]}",
                    style = MiuixTheme.textStyles.title3,
                    color = MiuixTheme.colorScheme.onBackground,
                )
                GlassTabRow(
                    tabs = tabs,
                    selectedIndex = neutralIndex,
                    onSelect = { neutralIndex = it },
                    backdrop = backdrop,
                    modifier = Modifier.fillMaxWidth(),
                    height = GlassTabRowDefaults.NeutralHeight,
                    colors = GlassTabRowDefaults.neutralColors(),
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Segmented: ${tabs[segmentedIndex]}",
                    style = MiuixTheme.textStyles.title3,
                    color = MiuixTheme.colorScheme.onBackground,
                )
                GlassSegmentedTabRow(
                    tabs = tabs,
                    selectedIndex = segmentedIndex,
                    onSelect = { segmentedIndex = it },
                    backdrop = backdrop,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
