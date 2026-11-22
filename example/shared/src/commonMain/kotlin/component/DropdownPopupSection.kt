// Copyright 2026, compose-miuix-ui contributors
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownArrowEndAction
import top.yukonga.miuix.kmp.basic.DropdownDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.popup.OverlayDropdownDialog
import top.yukonga.miuix.kmp.popup.OverlayDropdownPopup
import top.yukonga.miuix.kmp.popup.WindowDropdownDialog
import top.yukonga.miuix.kmp.popup.WindowDropdownPopup
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.dropdownPopupSection() {
    item(key = "dropdownPopup") {
        var showOverlay by remember { mutableStateOf(false) }
        var showWindow by remember { mutableStateOf(false) }
        var holdOverlay by remember { mutableStateOf(false) }
        var holdWindow by remember { mutableStateOf(false) }
        var overlaySelected by remember { mutableIntStateOf(0) }
        var windowSelected by remember { mutableIntStateOf(0) }
        val options = remember { listOf("Name", "Date", "Size") }
        val overlayEntry = remember(overlaySelected) {
            DropdownEntry(
                items = options.mapIndexed { index, text ->
                    DropdownItem(
                        text = text,
                        selected = overlaySelected == index,
                        onClick = { overlaySelected = index },
                    )
                },
            )
        }
        val windowEntry = remember(windowSelected) {
            DropdownEntry(
                items = options.mapIndexed { index, text ->
                    DropdownItem(
                        text = text,
                        selected = windowSelected == index,
                        onClick = { windowSelected = index },
                    )
                },
            )
        }
        val dropdownColors = DropdownDefaults.dropdownColors()
        val actionColor = MiuixTheme.colorScheme.onSurfaceVariantActions

        SmallTitle(text = "Dropdown Popup")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            BasicComponent(
                title = "OverlayDropdownPopup",
                summary = "Sort by ${options[overlaySelected]} · select to close",
                onClick = {
                    showOverlay = true
                    holdOverlay = true
                },
                onClickLabel = "Choose overlay sort order",
                role = Role.DropdownList,
                holdDownState = holdOverlay,
                endActions = {
                    DropdownArrowEndAction(actionColor = actionColor)
                    OverlayDropdownPopup(
                        entry = overlayEntry,
                        show = showOverlay,
                        onDismiss = { showOverlay = false },
                        onDismissFinished = { holdOverlay = false },
                        maxHeight = 300.dp,
                        dropdownColors = dropdownColors,
                        renderInRootScaffold = true,
                        collapseOnSelection = true,
                    )
                },
            )
            BasicComponent(
                title = "WindowDropdownPopup",
                summary = "Sort by ${options[windowSelected]} · select to close",
                onClick = {
                    showWindow = true
                    holdWindow = true
                },
                onClickLabel = "Choose window sort order",
                role = Role.DropdownList,
                holdDownState = holdWindow,
                endActions = {
                    DropdownArrowEndAction(actionColor = actionColor)
                    WindowDropdownPopup(
                        entry = windowEntry,
                        show = showWindow,
                        onDismiss = { showWindow = false },
                        onDismissFinished = { holdWindow = false },
                        maxHeight = 300.dp,
                        dropdownColors = dropdownColors,
                        collapseOnSelection = true,
                    )
                },
            )
        }
    }
    item(key = "dropdownDialog") {
        var showOverlay by remember { mutableStateOf(false) }
        var showWindow by remember { mutableStateOf(false) }
        var holdOverlay by remember { mutableStateOf(false) }
        var holdWindow by remember { mutableStateOf(false) }
        var overlaySelected by remember { mutableIntStateOf(0) }
        var windowSelected by remember { mutableIntStateOf(0) }
        var windowOrderSelected by remember { mutableIntStateOf(0) }
        val options = remember { listOf("Name", "Date", "Size") }
        val orderOptions = remember { listOf("Ascending", "Descending") }
        val overlayEntry = remember(overlaySelected) {
            DropdownEntry(
                items = options.mapIndexed { index, text ->
                    DropdownItem(
                        text = text,
                        selected = overlaySelected == index,
                        onClick = { overlaySelected = index },
                    )
                },
            )
        }
        val windowEntries = remember(windowSelected, windowOrderSelected) {
            listOf(
                DropdownEntry(
                    items = options.mapIndexed { index, text ->
                        DropdownItem(
                            text = text,
                            selected = windowSelected == index,
                            onClick = { windowSelected = index },
                        )
                    },
                ),
                DropdownEntry(
                    items = orderOptions.mapIndexed { index, text ->
                        DropdownItem(
                            text = text,
                            selected = windowOrderSelected == index,
                            onClick = { windowOrderSelected = index },
                        )
                    },
                ),
            )
        }
        val dropdownColors = DropdownDefaults.dropdownColors()
        val actionColor = MiuixTheme.colorScheme.onSurfaceVariantActions

        SmallTitle(text = "Dropdown Dialog")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            BasicComponent(
                title = "OverlayDropdownDialog",
                summary = "Sort by ${options[overlaySelected]} · select, then Done",
                onClick = {
                    showOverlay = true
                    holdOverlay = true
                },
                onClickLabel = "Open overlay sort dialog",
                role = Role.DropdownList,
                holdDownState = holdOverlay,
                endActions = {
                    DropdownArrowEndAction(actionColor = actionColor)
                    OverlayDropdownDialog(
                        entry = overlayEntry,
                        title = "Sort by",
                        dialogButtonString = "Done",
                        show = showOverlay,
                        onDismiss = { showOverlay = false },
                        onDismissFinished = { holdOverlay = false },
                        dropdownColors = dropdownColors,
                        renderInRootScaffold = true,
                        collapseOnSelection = false,
                    )
                },
            )
            BasicComponent(
                title = "WindowDropdownDialog",
                summary = "${options[windowSelected]} · ${orderOptions[windowOrderSelected]} · select, then Done",
                onClick = {
                    showWindow = true
                    holdWindow = true
                },
                onClickLabel = "Open grouped window sort dialog",
                role = Role.DropdownList,
                holdDownState = holdWindow,
                endActions = {
                    DropdownArrowEndAction(actionColor = actionColor)
                    WindowDropdownDialog(
                        entries = windowEntries,
                        title = "Sort and order",
                        dialogButtonString = "Done",
                        show = showWindow,
                        onDismiss = { showWindow = false },
                        onDismissFinished = { holdWindow = false },
                        dropdownColors = dropdownColors,
                        collapseOnSelection = false,
                    )
                },
            )
        }
    }
}
