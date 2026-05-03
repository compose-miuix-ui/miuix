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
import top.yukonga.miuix.kmp.overlay.OverlayDialog
import top.yukonga.miuix.kmp.overlay.OverlayListPopup

/**
 * Overlay dropdown popup for a single [DropdownEntry].
 *
 * Item clicks call [top.yukonga.miuix.kmp.basic.DropdownItem.onClick], and [collapseOnSelection]
 * controls whether the popup is dismissed after a click.
 */
@Composable
fun OverlayDropdownPopup(
    entry: DropdownEntry,
    show: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    maxHeight: Dp?,
    dropdownColors: DropdownColors,
    renderInRootScaffold: Boolean,
    collapseOnSelection: Boolean = true,
) {
    val entries = remember(entry) { listOf(entry) }
    OverlayDropdownPopup(
        entries = entries,
        show = show,
        onDismiss = onDismiss,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
        dropdownColors = dropdownColors,
        renderInRootScaffold = renderInRootScaffold,
        collapseOnSelection = collapseOnSelection,
    )
}

/**
 * Overlay dropdown popup for one or more [DropdownEntry] groups.
 *
 * Groups are separated by dividers. Entries without selection state can be used as action menus.
 */
@Composable
fun OverlayDropdownPopup(
    entries: List<DropdownEntry>,
    show: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    maxHeight: Dp?,
    dropdownColors: DropdownColors,
    renderInRootScaffold: Boolean,
    collapseOnSelection: Boolean = entries.size <= 1,
) {
    val hapticFeedback = LocalHapticFeedback.current
    val currentEntries by rememberUpdatedState(entries)
    val currentCollapseOnSelection by rememberUpdatedState(collapseOnSelection)
    val currentOnDismiss by rememberUpdatedState(onDismiss)
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)
    val onItemClicked: (Int, Int) -> Unit = remember {
        { entryIdx, itemIdx ->
            currentHapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
            currentEntries.getOrNull(entryIdx)?.let { entry ->
                entry.items.getOrNull(itemIdx)?.onClick?.invoke()
            }
            if (currentCollapseOnSelection) {
                currentOnDismiss()
            }
        }
    }
    OverlayListPopup(
        show = show,
        alignment = PopupPositionProvider.Align.End,
        onDismissRequest = onDismiss,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
        renderInRootScaffold = renderInRootScaffold,
    ) {
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
 * Overlay dialog for a [DropdownEntry].
 *
 * Item clicks call [top.yukonga.miuix.kmp.basic.DropdownItem.onClick], and [collapseOnSelection]
 * controls whether the dialog is dismissed after a click.
 */
@Composable
fun OverlayDropdownDialog(
    entry: DropdownEntry,
    title: String,
    dialogButtonString: String,
    show: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    dropdownColors: DropdownColors,
    popupModifier: Modifier = Modifier,
    renderInRootScaffold: Boolean = true,
    collapseOnSelection: Boolean = true,
) {
    val entries = remember(entry) { listOf(entry) }
    OverlayDropdownDialog(
        entries = entries,
        title = title,
        dialogButtonString = dialogButtonString,
        show = show,
        onDismiss = onDismiss,
        onDismissFinished = onDismissFinished,
        dropdownColors = dropdownColors,
        popupModifier = popupModifier,
        renderInRootScaffold = renderInRootScaffold,
        collapseOnSelection = collapseOnSelection,
    )
}

/**
 * Overlay dialog for one or more [DropdownEntry] groups.
 *
 * Groups are separated by dividers. Item clicks call [top.yukonga.miuix.kmp.basic.DropdownItem.onClick], and [collapseOnSelection]
 * controls whether the dialog is dismissed after a click.
 */
@Composable
fun OverlayDropdownDialog(
    entries: List<DropdownEntry>,
    title: String,
    dialogButtonString: String,
    show: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    dropdownColors: DropdownColors,
    popupModifier: Modifier = Modifier,
    renderInRootScaffold: Boolean = true,
    collapseOnSelection: Boolean = entries.size <= 1,
) {
    val hapticFeedback = LocalHapticFeedback.current
    val currentEntries by rememberUpdatedState(entries)
    val currentCollapseOnSelection by rememberUpdatedState(collapseOnSelection)
    val currentOnDismiss by rememberUpdatedState(onDismiss)
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)
    val onItemClicked: (Int, Int) -> Unit = remember {
        { entryIdx, itemIdx ->
            currentHapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
            currentEntries.getOrNull(entryIdx)?.let { entry ->
                entry.items.getOrNull(itemIdx)?.onClick?.invoke()
            }
            if (currentCollapseOnSelection) {
                currentOnDismiss()
            }
        }
    }
    OverlayDialog(
        show = show,
        modifier = popupModifier,
        title = title,
        onDismissRequest = onDismiss,
        onDismissFinished = onDismissFinished,
        insideMargin = DpSize(0.dp, 24.dp),
        renderInRootScaffold = renderInRootScaffold,
        content = {
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
                        onClick = onDismiss,
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
