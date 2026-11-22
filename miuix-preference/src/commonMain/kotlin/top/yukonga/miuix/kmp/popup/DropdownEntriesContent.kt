// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.popup

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.DropdownColors
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownImpl
import top.yukonga.miuix.kmp.basic.HorizontalDivider

/**
 * Renders a list of [DropdownEntry] groups inside a popup container. Computes popup-global
 * first/last per row internally so only the very first and very last rows of the entire popup
 * receive the larger first/last padding; group boundaries fall back to the middle padding.
 */
@Composable
internal fun DropdownEntriesPopupContent(
    entries: List<DropdownEntry>,
    dropdownColors: DropdownColors,
    onItemClick: (entryIdx: Int, itemIdx: Int) -> Unit,
) {
    val lastEntryIdx = entries.lastIndex
    entries.forEachIndexed { entryIdx, entry ->
        val lastItemIdx = entry.items.lastIndex
        val isFirstEntry = entryIdx == 0
        val isLastEntry = entryIdx == lastEntryIdx
        val onEntryClick = remember(entryIdx, onItemClick) {
            { idx: Int -> onItemClick(entryIdx, idx) }
        }
        entry.items.forEachIndexed { itemIdx, option ->
            key(entryIdx, itemIdx) {
                DropdownImpl(
                    item = option,
                    optionSize = entry.items.size,
                    isSelected = option.selected,
                    index = itemIdx,
                    dropdownColors = dropdownColors,
                    enabled = entry.enabled && option.enabled,
                    isFirst = isFirstEntry && itemIdx == 0,
                    isLast = isLastEntry && itemIdx == lastItemIdx,
                    onSelectedIndexChange = onEntryClick,
                )
            }
        }
        if (entryIdx != lastEntryIdx) {
            HorizontalDivider(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 4.dp),
                thickness = 1.5.dp,
            )
        }
    }
}

/**
 * Adds [DropdownEntry] groups to a [LazyListScope] for use inside a dialog. Dialog mode uses
 * uniform vertical padding regardless of position, so popup-global first/last is intentionally
 * not propagated.
 */
internal fun LazyListScope.dropdownEntriesDialogItems(
    entries: List<DropdownEntry>,
    dropdownColors: DropdownColors,
    onItemClick: (entryIdx: Int, itemIdx: Int) -> Unit,
) {
    val lastEntryIdx = entries.lastIndex
    entries.forEachIndexed { entryIdx, entry ->
        items(
            count = entry.items.size,
            key = { itemIdx -> "$entryIdx-$itemIdx" },
            contentType = { "item" },
        ) { itemIdx ->
            val item = entry.items[itemIdx]
            val onEntryClick = remember(entryIdx, onItemClick) {
                { idx: Int -> onItemClick(entryIdx, idx) }
            }
            DropdownImpl(
                item = item,
                optionSize = entry.items.size,
                isSelected = item.selected,
                index = itemIdx,
                dropdownColors = dropdownColors,
                enabled = entry.enabled && item.enabled,
                dialogMode = true,
                onSelectedIndexChange = onEntryClick,
            )
        }
        if (entryIdx != lastEntryIdx) {
            item(
                key = "divider-$entryIdx",
                contentType = "divider",
            ) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 4.dp),
                    thickness = 1.5.dp,
                )
            }
        }
    }
}
