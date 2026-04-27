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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.preference.WindowDropdownPreference

@Composable
fun WindowDropdownDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xff667eea), Color(0xff764ba2)))),
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
            var selectedIndex1 by remember { mutableIntStateOf(0) }
            val options1 = listOf("Option 1", "Option 2", "Option 3")
            var selectedIndex2 by remember { mutableIntStateOf(0) }
            val options2 = listOf("Chinese", "English", "Japanese")
            var expanded by remember { mutableStateOf(false) }
            var customSelectedIndex by remember { mutableIntStateOf(0) }
            val customEntry = DropdownEntry(
                items = listOf(
                    DropdownItem(text = "Custom Option 1"),
                    DropdownItem(text = "Custom Option 2", enabled = false),
                    DropdownItem(text = "Custom Option 3"),
                ),
                selectedIndex = customSelectedIndex,
                onSelectedIndexChange = { customSelectedIndex = it },
            )
            var sizeSelectedIndex by remember { mutableIntStateOf(0) }
            var colorSelectedIndex by remember { mutableIntStateOf(0) }
            val groupedEntries = listOf(
                DropdownEntry(
                    items = listOf("Small", "Medium").map { DropdownItem(text = it) },
                    selectedIndex = sizeSelectedIndex,
                    onSelectedIndexChange = { sizeSelectedIndex = it },
                ),
                DropdownEntry(
                    items = listOf("Red", "Green", "Blue").map { DropdownItem(text = it) },
                    selectedIndex = colorSelectedIndex,
                    onSelectedIndexChange = { colorSelectedIndex = it },
                ),
            )

            Card {
                WindowDropdownPreference(
                    title = "Dropdown Menu",
                    items = options1,
                    selectedIndex = selectedIndex1,
                    onSelectedIndexChange = { selectedIndex1 = it },
                )
                WindowDropdownPreference(
                    title = "Language Settings",
                    summary = if (expanded) "Expanded" else "Choose your preferred language",
                    items = options2,
                    selectedIndex = selectedIndex2,
                    onSelectedIndexChange = { selectedIndex2 = it },
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
