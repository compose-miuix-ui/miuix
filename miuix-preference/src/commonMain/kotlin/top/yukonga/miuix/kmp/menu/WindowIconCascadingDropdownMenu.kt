// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.menu

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import top.yukonga.miuix.kmp.basic.DropdownColors
import top.yukonga.miuix.kmp.basic.DropdownDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.IconButtonDefaults
import top.yukonga.miuix.kmp.utils.performHapticFeedbackCompat
import top.yukonga.miuix.kmp.window.WindowCascadingListPopup

/**
 * An [IconButton] wrapper that opens a [WindowCascadingListPopup] for a single [DropdownEntry].
 *
 * Items whose [top.yukonga.miuix.kmp.basic.DropdownItem.children] is non-empty become submenu
 * triggers; cascading depth is limited to 2.
 */
@Composable
fun WindowIconCascadingDropdownMenu(
    entry: DropdownEntry,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    maxHeight: Dp? = null,
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    collapseOnSelection: Boolean = true,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    backgroundColor: Color = Color.Unspecified,
    cornerRadius: Dp = IconButtonDefaults.CornerRadius,
    minHeight: Dp = IconButtonDefaults.MinHeight,
    minWidth: Dp = IconButtonDefaults.MinWidth,
    content: @Composable () -> Unit,
) {
    val entries = remember(entry) { listOf(entry) }
    WindowIconCascadingDropdownMenu(
        entries = entries,
        modifier = modifier,
        enabled = enabled,
        maxHeight = maxHeight,
        dropdownColors = dropdownColors,
        collapseOnSelection = collapseOnSelection,
        onExpandedChange = onExpandedChange,
        backgroundColor = backgroundColor,
        cornerRadius = cornerRadius,
        minHeight = minHeight,
        minWidth = minWidth,
        content = content,
    )
}

/**
 * An [IconButton] wrapper that opens a [WindowCascadingListPopup] for one or more
 * [DropdownEntry] groups. Items whose [top.yukonga.miuix.kmp.basic.DropdownItem.children] is
 * non-empty become submenu triggers; cascading depth is limited to 2.
 */
@Composable
fun WindowIconCascadingDropdownMenu(
    entries: List<DropdownEntry>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    maxHeight: Dp? = null,
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    collapseOnSelection: Boolean = true,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    backgroundColor: Color = Color.Unspecified,
    cornerRadius: Dp = IconButtonDefaults.CornerRadius,
    minHeight: Dp = IconButtonDefaults.MinHeight,
    minWidth: Dp = IconButtonDefaults.MinWidth,
    content: @Composable () -> Unit,
) {
    val isDropdownExpanded = remember { mutableStateOf(false) }
    val isHoldDown = remember { mutableStateOf(false) }
    val hapticFeedback = LocalHapticFeedback.current
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)
    val currentOnExpandedChange = rememberUpdatedState(onExpandedChange)
    val setExpanded: (Boolean) -> Unit = remember {
        { expanded ->
            if (isDropdownExpanded.value != expanded) {
                isDropdownExpanded.value = expanded
                currentOnExpandedChange.value?.invoke(expanded)
            }
        }
    }

    val nonEmptyEntries = entries.filter { it.items.isNotEmpty() }
    val actualEnabled = enabled && nonEmptyEntries.isNotEmpty()
    val handleClick = remember(actualEnabled) {
        {
            if (actualEnabled) {
                setExpanded(!isDropdownExpanded.value)
                if (isDropdownExpanded.value) {
                    isHoldDown.value = true
                    currentHapticFeedback.performHapticFeedbackCompat(HapticFeedbackType.ContextClick)
                }
            }
        }
    }

    Box(modifier = modifier) {
        IconButton(
            onClick = handleClick,
            enabled = actualEnabled,
            holdDownState = isHoldDown.value,
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            minHeight = minHeight,
            minWidth = minWidth,
            content = content,
        )
        if (nonEmptyEntries.isNotEmpty()) {
            WindowCascadingListPopup(
                show = isDropdownExpanded.value,
                entries = nonEmptyEntries,
                onDismissRequest = { setExpanded(false) },
                onDismissFinished = { isHoldDown.value = false },
                maxHeight = maxHeight,
                dropdownColors = dropdownColors,
                collapseOnSelection = collapseOnSelection,
            )
        }
    }
}
