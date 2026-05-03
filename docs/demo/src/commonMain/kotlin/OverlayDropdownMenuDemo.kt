// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.menu.OverlayDropdownMenu

@Composable
fun OverlayDropdownMenuDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(demoBackground()),
        contentAlignment = Alignment.Center,
    ) {
        var basicSelectedIndex by remember { mutableIntStateOf(0) }
        val basicEntry = DropdownEntry(
            items = listOf("Option 1", "Option 2", "Option 3").mapIndexed { index, text ->
                DropdownItem(
                    text = text,
                    selected = basicSelectedIndex == index,
                    onClick = { basicSelectedIndex = index },
                )
            },
        )
        var sizeIndex by remember { mutableIntStateOf(0) }
        var colorIndex by remember { mutableIntStateOf(0) }
        val groupedEntries = listOf(
            DropdownEntry(
                items = listOf("Small", "Medium").mapIndexed { index, text ->
                    DropdownItem(
                        text = text,
                        selected = sizeIndex == index,
                        onClick = { sizeIndex = index },
                    )
                },
            ),
            DropdownEntry(
                items = listOf("Red", "Green", "Blue").mapIndexed { index, text ->
                    DropdownItem(
                        text = text,
                        selected = colorIndex == index,
                        onClick = { colorIndex = index },
                    )
                },
            ),
        )
        var multiSelected by remember { mutableStateOf(setOf("A1", "B2")) }
        val multiSelectEntries = listOf(
            DropdownEntry(
                items = listOf("A1", "A2").map { text ->
                    DropdownItem(
                        text = text,
                        selected = text in multiSelected,
                        onClick = {
                            multiSelected = if (text in multiSelected) {
                                multiSelected - text
                            } else {
                                multiSelected + text
                            }
                        },
                    )
                },
            ),
            DropdownEntry(
                items = listOf("B1", "B2", "B3").map { text ->
                    DropdownItem(
                        text = text,
                        selected = text in multiSelected,
                        onClick = {
                            multiSelected = if (text in multiSelected) {
                                multiSelected - text
                            } else {
                                multiSelected + text
                            }
                        },
                    )
                },
            ),
        )
        var expanded by remember { mutableStateOf(false) }
        val expandedEntry = DropdownEntry(
            items = listOf("Option 1", "Option 2", "Option 3").map { DropdownItem(text = it) },
        )
        val disabledEntry = DropdownEntry(items = listOf(DropdownItem(text = "Option 1")))

        Card(modifier = Modifier.padding(16.dp).widthIn(max = 600.dp).fillMaxWidth()) {
            Scaffold(
                topBar = { SmallTopAppBar(title = "Settings") },
            ) { padding ->
                LazyColumn(
                    contentPadding = PaddingValues(top = padding.calculateTopPadding()),
                ) {
                    item {
                        Spacer(Modifier.height(8.dp))
                        Card(modifier = Modifier.padding(horizontal = 16.dp)) {
                            OverlayDropdownMenu(
                                title = "Dropdown Menu",
                                entry = basicEntry,
                            )
                            OverlayDropdownMenu(
                                title = "Grouped Menu",
                                entries = groupedEntries,
                                collapseOnSelection = false,
                            )
                            OverlayDropdownMenu(
                                title = "Multi Select Menu",
                                entries = multiSelectEntries,
                                collapseOnSelection = false,
                            )
                            OverlayDropdownMenu(
                                title = "Observe Expanded",
                                summary = if (expanded) "Expanded" else "Collapsed",
                                entry = expandedEntry,
                                onExpandedChange = { expanded = it },
                            )
                            OverlayDropdownMenu(
                                title = "Disabled Menu",
                                summary = "This menu is currently unavailable",
                                entry = disabledEntry,
                                enabled = false,
                            )
                        }
                    }
                }
            }
        }
    }
}
