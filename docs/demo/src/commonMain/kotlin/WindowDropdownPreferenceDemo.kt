// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.preference.WindowDropdownPreference

@Composable
fun WindowDropdownPreferenceDemo() {
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
            var selectedIndex by remember { mutableIntStateOf(0) }
            val options = listOf("Chinese", "English", "Japanese")
            var expanded by remember { mutableStateOf(false) }
            var customSelectedIndex by remember { mutableIntStateOf(0) }
            val customEntry = DropdownEntry(
                items = listOf(
                    DropdownItem(
                        text = "Custom Option 1",
                        selected = customSelectedIndex == 0,
                        onClick = { customSelectedIndex = 0 },
                    ),
                    DropdownItem(text = "Custom Option 2", enabled = false),
                    DropdownItem(
                        text = "Custom Option 3",
                        selected = customSelectedIndex == 2,
                        onClick = { customSelectedIndex = 2 },
                    ),
                ),
            )
            var sizeSelectedIndex by remember { mutableIntStateOf(0) }
            var colorSelectedIndex by remember { mutableIntStateOf(0) }
            var multiSelectedItems by remember { mutableStateOf(setOf("A1", "B2")) }
            val groupedEntries = listOf(
                DropdownEntry(
                    items = listOf("Small", "Medium").mapIndexed { index, text ->
                        DropdownItem(
                            text = text,
                            selected = sizeSelectedIndex == index,
                            onClick = { sizeSelectedIndex = index },
                        )
                    },
                ),
                DropdownEntry(
                    items = listOf("Red", "Green", "Blue").mapIndexed { index, text ->
                        DropdownItem(
                            text = text,
                            selected = colorSelectedIndex == index,
                            onClick = { colorSelectedIndex = index },
                        )
                    },
                ),
            )
            val multiSelectEntries = listOf(
                DropdownEntry(
                    items = listOf("A1", "A2").map { text ->
                        DropdownItem(
                            text = text,
                            selected = text in multiSelectedItems,
                            onClick = {
                                multiSelectedItems = if (text in multiSelectedItems) {
                                    multiSelectedItems - text
                                } else {
                                    multiSelectedItems + text
                                }
                            },
                        )
                    },
                ),
                DropdownEntry(
                    items = listOf("B1", "B2", "B3").map { text ->
                        DropdownItem(
                            text = text,
                            selected = text in multiSelectedItems,
                            onClick = {
                                multiSelectedItems = if (text in multiSelectedItems) {
                                    multiSelectedItems - text
                                } else {
                                    multiSelectedItems + text
                                }
                            },
                        )
                    },
                ),
            )

            Card {
                WindowDropdownPreference(
                    title = "Language Settings",
                    summary = if (expanded) "Expanded" else "Choose your preferred language",
                    items = options,
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = { selectedIndex = it },
                    onExpandedChange = { expanded = it },
                )
                WindowDropdownPreference(
                    title = "Custom Entry",
                    entry = customEntry,
                )
                WindowDropdownPreference(
                    title = "Grouped Dropdown",
                    entries = groupedEntries,
                    collapseOnSelection = false,
                )
                WindowDropdownPreference(
                    title = "Multi Select Dropdown",
                    entries = multiSelectEntries,
                    collapseOnSelection = false,
                )
                WindowDropdownPreference(
                    title = "Disabled Dropdown",
                    summary = "This dropdown menu is currently unavailable",
                    items = listOf("Option 1"),
                    selectedIndex = 0,
                    onSelectedIndexChange = {},
                    enabled = false,
                )
            }
        }
    }
}
