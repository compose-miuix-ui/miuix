// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.menu.OverlayDropdownMenu
import top.yukonga.miuix.kmp.menu.OverlayIconCascadingDropdownMenu
import top.yukonga.miuix.kmp.menu.OverlayIconDropdownMenu
import top.yukonga.miuix.kmp.menu.WindowDropdownMenu
import top.yukonga.miuix.kmp.menu.WindowIconCascadingDropdownMenu
import top.yukonga.miuix.kmp.menu.WindowIconDropdownMenu
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.menuSection() {
    item(key = "textMenus") {
        var overlaySelection by remember { mutableStateOf("Name") }
        var windowSelection by remember { mutableStateOf("Name") }
        val overlayEntry = remember(overlaySelection) {
            menuSelectionEntry(overlaySelection) { overlaySelection = it }
        }
        val windowEntry = remember(windowSelection) {
            menuSelectionEntry(windowSelection) { windowSelection = it }
        }

        SmallTitle(text = "Text menus")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            OverlayDropdownMenu(
                entry = overlayEntry,
                title = "OverlayDropdownMenu",
                summary = "Selected: $overlaySelection",
            )
            WindowDropdownMenu(
                entry = windowEntry,
                title = "WindowDropdownMenu",
                summary = "Selected: $windowSelection",
            )
        }
    }

    item(key = "iconMenus") {
        var overlaySelection by remember { mutableStateOf("Name") }
        var windowSelection by remember { mutableStateOf("Name") }
        val overlayEntry = remember(overlaySelection) {
            menuSelectionEntry(overlaySelection) { overlaySelection = it }
        }
        val windowEntry = remember(windowSelection) {
            menuSelectionEntry(windowSelection) { windowSelection = it }
        }

        SmallTitle(text = "Icon menus")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            BasicComponent(
                title = "Overlay icon menu",
                summary = "OverlayIconDropdownMenu\nSelected: $overlaySelection",
                endActions = {
                    OverlayIconDropdownMenu(entry = overlayEntry) {
                        MenuMoreIcon(contentDescription = "Open overlay icon menu")
                    }
                },
            )
            BasicComponent(
                title = "Window icon menu",
                summary = "WindowIconDropdownMenu\nSelected: $windowSelection",
                endActions = {
                    WindowIconDropdownMenu(entry = windowEntry) {
                        MenuMoreIcon(contentDescription = "Open window icon menu")
                    }
                },
            )
        }
    }

    item(key = "cascadingMenus") {
        var overlaySelection by remember { mutableStateOf("Name") }
        var windowSelection by remember { mutableStateOf("Name") }
        val overlayEntry = remember(overlaySelection) {
            cascadingMenuEntry(overlaySelection) { overlaySelection = it }
        }
        val windowEntry = remember(windowSelection) {
            cascadingMenuEntry(windowSelection) { windowSelection = it }
        }

        SmallTitle(text = "Cascading menus")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            BasicComponent(
                title = "Overlay cascading menu",
                summary = "OverlayIconCascadingDropdownMenu\nSelected: $overlaySelection",
                endActions = {
                    OverlayIconCascadingDropdownMenu(entry = overlayEntry) {
                        MenuMoreIcon(contentDescription = "Open overlay cascading menu")
                    }
                },
            )
            BasicComponent(
                title = "Window cascading menu",
                summary = "WindowIconCascadingDropdownMenu\nSelected: $windowSelection",
                endActions = {
                    WindowIconCascadingDropdownMenu(entry = windowEntry) {
                        MenuMoreIcon(contentDescription = "Open window cascading menu")
                    }
                },
            )
        }
    }
}

private fun menuSelectionEntry(
    selected: String,
    onSelect: (String) -> Unit,
): DropdownEntry = DropdownEntry(
    items = listOf(
        DropdownItem(
            text = "Name",
            selected = selected == "Name",
            onClick = { onSelect("Name") },
        ),
        DropdownItem(
            text = "Newest",
            selected = selected == "Newest",
            onClick = { onSelect("Newest") },
        ),
        DropdownItem(
            text = "Size (disabled)",
            enabled = false,
        ),
    ),
)

private fun cascadingMenuEntry(
    selected: String,
    onSelect: (String) -> Unit,
): DropdownEntry = DropdownEntry(
    items = listOf(
        DropdownItem(
            text = "Sort by",
            summary = "Open sorting options",
            children = menuSelectionEntry(selected, onSelect).items,
        ),
        DropdownItem(
            text = "Favorites",
            selected = selected == "Favorites",
            onClick = { onSelect("Favorites") },
        ),
        DropdownItem(
            text = "Archive (disabled)",
            enabled = false,
        ),
    ),
)

@Composable
private fun MenuMoreIcon(contentDescription: String) {
    Icon(
        imageVector = MiuixIcons.More,
        contentDescription = contentDescription,
        modifier = Modifier.size(24.dp),
        tint = MiuixTheme.colorScheme.onSurfaceVariantActions,
    )
}
