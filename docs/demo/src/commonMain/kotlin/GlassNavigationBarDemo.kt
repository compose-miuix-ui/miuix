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
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.glass.Contacts
import top.yukonga.miuix.kmp.icon.glass.Search
import top.yukonga.miuix.kmp.icon.glass.Settings
import top.yukonga.miuix.kmp.icon.glass.VerticalSplit
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassNavigationBarDemo() {
    val backdrop = rememberLayerBackdrop()
    val items = remember {
        listOf(
            GlassNavigationItem(MiuixIcons.Glass.VerticalSplit, "Home", "Home"),
            GlassNavigationItem(MiuixIcons.Glass.Contacts, "Contacts", "Contacts"),
            GlassNavigationItem(MiuixIcons.Glass.Settings, "Settings", "Settings"),
        )
    }
    val descriptions = remember {
        listOf("Your latest updates", "Your favorite people", "Your personal preferences")
    }
    var selectedIndex by remember { mutableIntStateOf(0) }
    var searchCount by remember { mutableIntStateOf(0) }

    Box(Modifier.fillMaxSize()) {
        GlassDemoBackground(backdrop) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 24.dp, end = 24.dp, bottom = 72.dp)
                    .widthIn(max = 600.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Current: ${items[selectedIndex].label}",
                    style = MiuixTheme.textStyles.title2,
                    color = MiuixTheme.colorScheme.onBackground,
                )
                Text(descriptions[selectedIndex], color = MiuixTheme.colorScheme.onBackground)
                Text("Search pressed: $searchCount", color = MiuixTheme.colorScheme.onBackground)
            }
        }
        GlassNavigationBar(
            items = items,
            selectedIndex = selectedIndex,
            onSelect = { selectedIndex = it },
            backdrop = backdrop,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            trailingAction = {
                GlassIconButton(
                    onClick = { searchCount++ },
                    backdrop = backdrop,
                ) {
                    Icon(
                        imageVector = MiuixIcons.Glass.Search,
                        contentDescription = "Search current page",
                        tint = MiuixTheme.colorScheme.onBackground,
                    )
                }
            },
        )
    }
}
