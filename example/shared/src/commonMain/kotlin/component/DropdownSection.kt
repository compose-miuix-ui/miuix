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
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.preference.OverlayDropdownPreference
import top.yukonga.miuix.kmp.preference.WindowDropdownPreference

fun LazyListScope.dropdownSection() {
    item(key = "dropdown") {
        val superDropdownOptionSelected = remember { mutableIntStateOf(0) }
        val windowDropdownOptionSelected = remember { mutableIntStateOf(0) }
        var overlayExpanded by remember { mutableStateOf(false) }
        var windowExpanded by remember { mutableStateOf(false) }
        var disabledOverlayExpanded by remember { mutableStateOf(false) }
        var disabledWindowExpanded by remember { mutableStateOf(false) }
        val dropdownOptions = remember { listOf("Option 1", "Option 2", "Option 3", "Option 4") }
        val dropdownLongOptions =
            remember {
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
                selectedIndex = superDropdownOptionSelected.value,
                onSelectedIndexChange = { newOption -> superDropdownOptionSelected.value = newOption },
                onExpandedChange = { overlayExpanded = it },
            )
            WindowDropdownPreference(
                title = "WindowDropdownPref",
                summary = if (windowExpanded) "Expanded" else "Collapsed",
                items = dropdownLongOptions,
                selectedIndex = windowDropdownOptionSelected.value,
                onSelectedIndexChange = { newOption -> windowDropdownOptionSelected.value = newOption },
                onExpandedChange = { windowExpanded = it },
            )
            OverlayDropdownPreference(
                title = "Disabled DropdownPref",
                summary = if (disabledOverlayExpanded) "Expanded" else "Collapsed",
                items = listOf("Option 1"),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
                onExpandedChange = { disabledOverlayExpanded = it },
            )
            WindowDropdownPreference(
                title = "Disabled WindowDropdownPref",
                summary = if (disabledWindowExpanded) "Expanded" else "Collapsed",
                items = listOf("Option 1"),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
                onExpandedChange = { disabledWindowExpanded = it },
            )
        }
    }
}
