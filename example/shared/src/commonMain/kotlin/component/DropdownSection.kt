// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.preference.OverlayDropdownPreference
import top.yukonga.miuix.kmp.preference.WindowDropdownPreference

fun LazyListScope.dropdownSection() {
    item(key = "dropdown") {
        var overlayDropdownOptionSelected by remember { mutableIntStateOf(0) }
        var windowDropdownOptionSelected by remember { mutableIntStateOf(0) }
        var overlayExpanded by remember { mutableStateOf(false) }
        var windowExpanded by remember { mutableStateOf(false) }
        val dropdownOptions = remember { listOf("Option 1", "Option 2", "Option 3", "Option 4") }
        val dropdownLongOptions = remember {
            listOf(
                "Option 1",
                "Long Option 2",
                "Long Long Option 3",
                "Long Long Long Option 4",
                "Long Long Long Long Option 5",
                "Long Long Long Long Long Option 6",
                "Long Long Long Long Long Long Option 7",
                "Long Long Long Long Long Long Long Option 8",
                "Long Long Long Long Long Long Long Long Option 9",
                "Long Long Long Long Long Long Long Long Long Option 10",
                "Long Long Long Long Long Long Long Long Long Long Option 11",
                "Long Long Long Long Long Long Long Long Long Long Long Option 12",
            )
        }

        var overlayGroupedExpanded by remember { mutableStateOf(false) }
        var overlayGroup1DropdownOptionSelected by remember { mutableIntStateOf(0) }
        var overlayGroup2DropdownOptionSelected by remember { mutableIntStateOf(0) }
        var overlayGroup3DropdownOptionSelected by remember { mutableIntStateOf(0) }
        val overlayMultiGroupOptions = remember(
            overlayGroup1DropdownOptionSelected,
            overlayGroup2DropdownOptionSelected,
            overlayGroup3DropdownOptionSelected,
        ) {
            listOf(
                DropdownEntry(
                    items = listOf("Option A-1", "Option A-2")
                        .map { DropdownItem(it) },
                    selectedIndex = overlayGroup1DropdownOptionSelected,
                    onSelectedIndexChange = { overlayGroup1DropdownOptionSelected = it },
                ),
                DropdownEntry(
                    items = listOf("Option B-1", "Option B-2", "Option B-3")
                        .map { DropdownItem(it) },
                    selectedIndex = overlayGroup2DropdownOptionSelected,
                    onSelectedIndexChange = { overlayGroup2DropdownOptionSelected = it },
                ),
                DropdownEntry(
                    items = listOf("Option C-1", "Option C-2", "Option C-3", "Option C-4")
                        .mapIndexed { index, string ->
                            DropdownItem(text = string, enabled = index % 2 == 0)
                        },
                    selectedIndex = overlayGroup3DropdownOptionSelected,
                    onSelectedIndexChange = { overlayGroup3DropdownOptionSelected = it },
                ),
            )
        }

        var windowGroupedExpanded by remember { mutableStateOf(false) }
        var windowGroup1DropdownOptionSelected by remember { mutableIntStateOf(0) }
        var windowGroup2DropdownOptionSelected by remember { mutableIntStateOf(0) }
        var windowGroup3DropdownOptionSelected by remember { mutableIntStateOf(0) }
        val windowMultiGroupOptions = remember(
            windowGroup1DropdownOptionSelected,
            windowGroup2DropdownOptionSelected,
            windowGroup3DropdownOptionSelected,
        ) {
            listOf(
                DropdownEntry(
                    items = listOf("Option A-1", "Option A-2")
                        .map { DropdownItem(it) },
                    selectedIndex = windowGroup1DropdownOptionSelected,
                    onSelectedIndexChange = { windowGroup1DropdownOptionSelected = it },
                ),
                DropdownEntry(
                    items = listOf("Option B-1", "Option B-2", "Option B-3")
                        .map { DropdownItem(it) },
                    selectedIndex = windowGroup2DropdownOptionSelected,
                    onSelectedIndexChange = { windowGroup2DropdownOptionSelected = it },
                ),
                DropdownEntry(
                    items = listOf("Option C-1", "Option C-2", "Option C-3", "Option C-4")
                        .mapIndexed { index, string ->
                            DropdownItem(text = string, enabled = index % 2 == 0)
                        },
                    selectedIndex = windowGroup3DropdownOptionSelected,
                    onSelectedIndexChange = { windowGroup3DropdownOptionSelected = it },
                ),
            )
        }

        SmallTitle(text = "Dropdown")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            OverlayDropdownPreference(
                title = "DropdownPref",
                summary = if (overlayExpanded) "Expanded" else "Collapsed",
                items = dropdownOptions,
                selectedIndex = overlayDropdownOptionSelected,
                onSelectedIndexChange = { newOption ->
                    overlayDropdownOptionSelected = newOption
                },
                onExpandedChange = { overlayExpanded = it },
            )
            WindowDropdownPreference(
                title = "WindowDropdownPref",
                summary = if (windowExpanded) "Expanded" else "Collapsed",
                items = dropdownLongOptions,
                selectedIndex = windowDropdownOptionSelected,
                onSelectedIndexChange = { newOption ->
                    windowDropdownOptionSelected = newOption
                },
                onExpandedChange = { windowExpanded = it },
            )
            OverlayDropdownPreference(
                title = "Disabled DropdownPref",
                items = listOf("Option 1"),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
            WindowDropdownPreference(
                title = "Disabled WindowDropdownPref",
                items = listOf("Option 1"),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
            OverlayDropdownPreference(
                title = "GroupedDropdownPref",
                summary = if (overlayGroupedExpanded) "Expanded" else "Collapsed",
                entries = overlayMultiGroupOptions,
                collapseOnSelection = false,
                onExpandedChange = { overlayGroupedExpanded = it },
            )
            WindowDropdownPreference(
                title = "GroupedDropdownPref",
                summary = if (windowGroupedExpanded) "Expanded" else "Collapsed",
                entries = windowMultiGroupOptions,
                collapseOnSelection = false,
                onExpandedChange = { windowGroupedExpanded = it },
            )
        }
    }
}
