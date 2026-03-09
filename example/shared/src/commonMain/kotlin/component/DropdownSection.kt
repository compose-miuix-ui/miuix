// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.extra.WindowDropdown

fun LazyListScope.dropdownSection() {
    item(key = "dropdown") {
        val superDropdownOptionSelected = remember { mutableIntStateOf(0) }
        val windowDropdownOptionSelected = remember { mutableIntStateOf(0) }
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
            SuperDropdown(
                title = "SuperDropdown",
                items = dropdownOptions,
                selectedIndex = superDropdownOptionSelected.value,
                onSelectedIndexChange = { newOption -> superDropdownOptionSelected.value = newOption },
            )
            WindowDropdown(
                title = "WindowDropdown",
                items = dropdownLongOptions,
                selectedIndex = windowDropdownOptionSelected.value,
                onSelectedIndexChange = { newOption -> windowDropdownOptionSelected.value = newOption },
            )
            SuperDropdown(
                title = "Disabled SuperDropdown",
                items = listOf("Option 1"),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
            WindowDropdown(
                title = "Disabled WindowDropdown",
                items = listOf("Option 1"),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
        }
    }
}
