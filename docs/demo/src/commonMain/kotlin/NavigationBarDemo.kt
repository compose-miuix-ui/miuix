// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.FloatingNavigationBar
import top.yukonga.miuix.kmp.basic.FloatingNavigationBarItem
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.NavigationDisplayMode
import top.yukonga.miuix.kmp.basic.NavigationItem
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Contacts
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.icon.extended.VerticalSplit
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun NavigationBarDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xfff77062), Color(0xfffe5196)))),
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                val pages = listOf("Home", "Profile", "Settings")
                val items = listOf(
                    NavigationItem("Home", MiuixIcons.VerticalSplit),
                    NavigationItem("Profile", MiuixIcons.Contacts),
                    NavigationItem("Settings", MiuixIcons.Settings),
                )
                var selectedIndex1 by remember { mutableIntStateOf(0) }
                var selectedIndex2 by remember { mutableIntStateOf(0) }
                Card(
                    modifier = Modifier.weight(0.5f),
                ) {
                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedIndex1 == index,
                                        onClick = { selectedIndex1 = index },
                                        icon = item.icon,
                                        label = item.label,
                                    )
                                }
                            }
                        },
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "Current: ${pages[selectedIndex1]}",
                                style = MiuixTheme.textStyles.title1,
                            )
                        }
                    }
                }
                Card(
                    modifier = Modifier.weight(0.5f),
                ) {
                    Scaffold(
                        bottomBar = {
                            FloatingNavigationBar(
                                mode = NavigationDisplayMode.IconOnly, // Show icons only
                            ) {
                                items.forEachIndexed { index, item ->
                                    FloatingNavigationBarItem(
                                        selected = selectedIndex2 == index,
                                        onClick = { selectedIndex2 = index },
                                        icon = item.icon,
                                        label = item.label,
                                    )
                                }
                            }
                        },
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "Current: ${pages[selectedIndex2]}",
                                style = MiuixTheme.textStyles.title1,
                            )
                        }
                    }
                }
            }
        }
    }
}
