// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.popup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.DropdownColors
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownImpl
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.theme.LocalDismissState
import top.yukonga.miuix.kmp.window.WindowDialog
import top.yukonga.miuix.kmp.window.WindowListPopup

/**
 * Window-layer dropdown popup for a single [DropdownEntry].
 *
 * Item clicks call [top.yukonga.miuix.kmp.basic.DropdownItem.onClick], and [collapseOnSelection]
 * controls whether the popup is dismissed after a click.
 */
@Composable
fun WindowDropdownPopup(
    entry: DropdownEntry,
    show: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    maxHeight: Dp?,
    dropdownColors: DropdownColors,
    collapseOnSelection: Boolean = true,
) {
    val entries = remember(entry) { listOf(entry) }
    WindowDropdownPopup(
        entries = entries,
        show = show,
        onDismiss = onDismiss,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
        dropdownColors = dropdownColors,
        collapseOnSelection = collapseOnSelection,
    )
}

/**
 * Window-layer dropdown popup for one or more [DropdownEntry] groups.
 *
 * Groups are separated by dividers. Entries without selection state can be used as action menus.
 */
@Composable
fun WindowDropdownPopup(
    entries: List<DropdownEntry>,
    show: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    maxHeight: Dp?,
    dropdownColors: DropdownColors,
    collapseOnSelection: Boolean = entries.size <= 1,
) {
    val hapticFeedback = LocalHapticFeedback.current
    val currentEntries by rememberUpdatedState(entries)
    val currentCollapseOnSelection by rememberUpdatedState(collapseOnSelection)
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)
    WindowListPopup(
        show = show,
        alignment = PopupPositionProvider.Align.End,
        onDismissRequest = onDismiss,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
    ) {
        val dismiss = LocalDismissState.current
        val currentDismiss by rememberUpdatedState(dismiss)
        val onItemClicked: (Int, Int) -> Unit = remember {
            { entryIdx, itemIdx ->
                currentHapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                currentEntries.getOrNull(entryIdx)?.let { entry ->
                    entry.items.getOrNull(itemIdx)?.onClick?.invoke()
                }
                if (currentCollapseOnSelection) {
                    currentDismiss?.invoke()
                }
            }
        }
        ListPopupColumn {
            entries.forEachIndexed { entryIdx, entry ->
                entry.items.forEachIndexed { itemIdx, option ->
                    key(entryIdx, itemIdx) {
                        DropdownImpl(
                            item = option,
                            optionSize = entry.items.size,
                            isSelected = option.selected,
                            index = itemIdx,
                            dropdownColors = dropdownColors,
                            enabled = entry.enabled && option.enabled,
                            onSelectedIndexChange = { index ->
                                onItemClicked(entryIdx, index)
                            },
                        )
                    }
                }
                if (entryIdx != entries.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        thickness = 1.dp,
                    )
                }
            }
        }
    }
}

/**
 * Window-layer dialog for a [DropdownEntry].
 *
 * Item clicks call [top.yukonga.miuix.kmp.basic.DropdownItem.onClick], and [collapseOnSelection]
 * controls whether the dialog is dismissed after a click.
 */
@Composable
fun WindowDropdownDialog(
    entry: DropdownEntry,
    title: String,
    dialogButtonString: String,
    show: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    dropdownColors: DropdownColors,
    popupModifier: Modifier = Modifier,
    collapseOnSelection: Boolean = true,
) {
    val entries = remember(entry) { listOf(entry) }
    WindowDropdownDialog(
        entries = entries,
        title = title,
        dialogButtonString = dialogButtonString,
        show = show,
        onDismiss = onDismiss,
        onDismissFinished = onDismissFinished,
        dropdownColors = dropdownColors,
        popupModifier = popupModifier,
        collapseOnSelection = collapseOnSelection,
    )
}

/**
 * Window-layer dialog for one or more [DropdownEntry] groups.
 *
 * Groups are separated by dividers. Item clicks call [top.yukonga.miuix.kmp.basic.DropdownItem.onClick], and [collapseOnSelection]
 * controls whether the dialog is dismissed after a click.
 */
@Composable
fun WindowDropdownDialog(
    entries: List<DropdownEntry>,
    title: String,
    dialogButtonString: String,
    show: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    dropdownColors: DropdownColors,
    popupModifier: Modifier = Modifier,
    collapseOnSelection: Boolean = entries.size <= 1,
) {
    val hapticFeedback = LocalHapticFeedback.current
    val currentEntries by rememberUpdatedState(entries)
    val currentCollapseOnSelection by rememberUpdatedState(collapseOnSelection)
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)
    WindowDialog(
        show = show,
        modifier = popupModifier,
        title = title,
        onDismissRequest = onDismiss,
        onDismissFinished = onDismissFinished,
        insideMargin = DpSize(0.dp, 24.dp),
        content = {
            val dismiss = LocalDismissState.current
            val currentDismiss by rememberUpdatedState(dismiss)
            val onItemClicked: (Int, Int) -> Unit = remember {
                { entryIdx, itemIdx ->
                    currentHapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                    currentEntries.getOrNull(entryIdx)?.let { entry ->
                        entry.items.getOrNull(itemIdx)?.onClick?.invoke()
                    }
                    if (currentCollapseOnSelection) {
                        currentDismiss?.invoke()
                    }
                }
            }
            Layout(
                content = {
                    LazyColumn {
                        entries.forEachIndexed { entryIdx, entry ->
                            items(
                                entry.items.size,
                                key = { itemIdx -> "$entryIdx-$itemIdx" },
                            ) { itemIdx ->
                                val item = entry.items[itemIdx]
                                DropdownImpl(
                                    item = item,
                                    optionSize = entry.items.size,
                                    isSelected = item.selected,
                                    index = itemIdx,
                                    dropdownColors = dropdownColors,
                                    enabled = entry.enabled && item.enabled,
                                    dialogMode = true,
                                    onSelectedIndexChange = { index ->
                                        onItemClicked(entryIdx, index)
                                    },
                                )
                            }
                            if (entryIdx != entries.lastIndex) {
                                item(key = "divider-$entryIdx") {
                                    HorizontalDivider(
                                        modifier = Modifier.padding(
                                            horizontal = 28.dp,
                                            vertical = 6.dp,
                                        ),
                                        thickness = 1.dp,
                                    )
                                }
                            }
                        }
                    }
                    TextButton(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 12.dp, end = 24.dp)
                            .fillMaxWidth(),
                        text = dialogButtonString,
                        minHeight = 50.dp,
                        onClick = { dismiss?.invoke() },
                    )
                },
            ) { measurables, constraints ->
                if (measurables.size != 2) {
                    layout(0, 0) { }
                } else {
                    val button = measurables[1].measure(constraints)
                    val lazyList = measurables[0].measure(
                        constraints.copy(
                            maxHeight = constraints.maxHeight - button.height,
                        ),
                    )
                    layout(constraints.maxWidth, lazyList.height + button.height) {
                        lazyList.place(0, 0)
                        button.place(0, lazyList.height)
                    }
                }
            }
        },
    )
}
