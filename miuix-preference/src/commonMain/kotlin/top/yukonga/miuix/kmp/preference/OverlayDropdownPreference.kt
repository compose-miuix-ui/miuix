// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.preference

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.DropdownArrowEndAction
import top.yukonga.miuix.kmp.basic.DropdownColors
import top.yukonga.miuix.kmp.basic.DropdownDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownImpl
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.overlay.OverlayListPopup
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A dropdown with a title and a summary.
 *
 * @param items The options of the [OverlayDropdownPreference].
 * @param selectedIndex The index of the selected option.
 * @param title The title of the [OverlayDropdownPreference].
 * @param modifier The modifier to be applied to the [OverlayDropdownPreference].
 * @param titleColor The color of the title.
 * @param summary The summary of the [OverlayDropdownPreference].
 * @param summaryColor The color of the summary.
 * @param dropdownColors The [DropdownColors] of the [OverlayDropdownPreference].
 * @param startAction The [Composable] content that on the start side of the [OverlayDropdownPreference].
 * @param bottomAction The [Composable] content at the bottom of the [OverlayDropdownPreference].
 * @param insideMargin The margin inside the [OverlayDropdownPreference].
 * @param maxHeight The maximum height of the [OverlayListPopup].
 * @param enabled Whether the [OverlayDropdownPreference] is enabled.
 * @param showValue Whether to show the selected value of the [OverlayDropdownPreference].
 * @param renderInRootScaffold Whether to render the popup in the root (outermost) Scaffold.
 *   When true (default), the popup covers the full screen. When false, it renders within the
 *   current Scaffold's bounds with position compensation.
 * @param onExpandedChange The callback to be invoked when the expanded state of the [OverlayDropdownPreference] changes.
 * @param onSelectedIndexChange The callback when the selected index of the [OverlayDropdownPreference] is changed.
 */
@Composable
fun OverlayDropdownPreference(
    items: List<String>,
    selectedIndex: Int,
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    startAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    maxHeight: Dp? = null,
    enabled: Boolean = true,
    showValue: Boolean = true,
    renderInRootScaffold: Boolean = true,
    // collapseOnSelection: Boolean = true,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    onSelectedIndexChange: ((Int) -> Unit)? = null,
) {
    val entry = remember(
        items, selectedIndex, onSelectedIndexChange,
    ) { DropdownEntry(items, selectedIndex, onSelectedIndexChange) }
    return OverlayDropdownPreference(
        entry = entry,
        title = title,
        modifier = modifier,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        dropdownColors = dropdownColors,
        startAction = startAction,
        bottomAction = bottomAction,
        insideMargin = insideMargin,
        maxHeight = maxHeight,
        enabled = enabled,
        showValue = showValue,
        renderInRootScaffold = renderInRootScaffold,
        collapseOnSelection = true,
        onExpandedChange = onExpandedChange,
    )
}

@Composable
private fun OverlayDropdownPreferencePopup(
    items: List<String>,
    selectedIndex: Int,
    isDropdownExpanded: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    maxHeight: Dp?,
    dropdownColors: DropdownColors,
    hapticFeedback: HapticFeedback,
    renderInRootScaffold: Boolean,
    // collapseOnSelection: Boolean,
    onSelectedIndexChange: ((Int) -> Unit)?,
) {
    val entry = remember(
        items, selectedIndex, onSelectedIndexChange,
    ) { DropdownEntry(items, selectedIndex, onSelectedIndexChange) }
    return OverlayDropdownPreferencePopup(
        entry = entry,
        isDropdownExpanded = isDropdownExpanded,
        onDismiss = onDismiss,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
        dropdownColors = dropdownColors,
        hapticFeedback = hapticFeedback,
        renderInRootScaffold = renderInRootScaffold,
        collapseOnSelection = true,
    )
}

@Composable
fun OverlayDropdownPreference(
    entry: DropdownEntry,
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    startAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    maxHeight: Dp? = null,
    enabled: Boolean = true,
    showValue: Boolean = true,
    renderInRootScaffold: Boolean = true,
    collapseOnSelection: Boolean = true,
    onExpandedChange: ((Boolean) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
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

    val itemsNotEmpty = entry.items.isNotEmpty()
    val actualEnabled = enabled && itemsNotEmpty

    val actionColor = if (actualEnabled) {
        MiuixTheme.colorScheme.onSurfaceVariantActions
    } else {
        MiuixTheme.colorScheme.disabledOnSecondaryVariant
    }

    val handleClick = remember(actualEnabled) {
        {
            if (actualEnabled) {
                setExpanded(!isDropdownExpanded.value)
                if (isDropdownExpanded.value) {
                    isHoldDown.value = true
                    currentHapticFeedback.performHapticFeedback(HapticFeedbackType.ContextClick)
                }
            }
        }
    }

    BasicComponent(
        modifier = modifier,
        interactionSource = interactionSource,
        insideMargin = insideMargin,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        startAction = startAction,
        endActions = {
            if (showValue && itemsNotEmpty) {
                if (entry.selectedIndex != null)
                    Text(
                        text = entry.items[entry.selectedIndex!!],
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .align(Alignment.CenterVertically)
                            .weight(1f, fill = false),
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = actionColor,
                        textAlign = TextAlign.End,
                    )
            }
            DropdownArrowEndAction(
                actionColor = actionColor,
            )
            if (itemsNotEmpty) {
                OverlayDropdownPreferencePopup(
                    entry = entry,
                    isDropdownExpanded = isDropdownExpanded.value,
                    onDismiss = { setExpanded(false) },
                    onDismissFinished = { isHoldDown.value = false },
                    maxHeight = maxHeight,
                    dropdownColors = dropdownColors,
                    hapticFeedback = hapticFeedback,
                    renderInRootScaffold = renderInRootScaffold,
                    collapseOnSelection = collapseOnSelection,
                )
            }
        },
        bottomAction = bottomAction,
        onClick = handleClick,
        holdDownState = isHoldDown.value,
        enabled = actualEnabled,
    )
}

@Composable
private fun OverlayDropdownPreferencePopup(
    entry: DropdownEntry,
    isDropdownExpanded: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    maxHeight: Dp?,
    dropdownColors: DropdownColors,
    hapticFeedback: HapticFeedback,
    renderInRootScaffold: Boolean,
    collapseOnSelection: Boolean,
) {
    val currentEntry by rememberUpdatedState(entry)
    val currentCollapseOnSelection by rememberUpdatedState(collapseOnSelection)
    val currentOnDismiss by rememberUpdatedState(onDismiss)
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)
    val onItemSelected: (Int) -> Unit = remember {
        { selectedIdx ->
            currentHapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
            currentEntry.onSelectedIndexChange?.let { it(selectedIdx) }
            if (currentCollapseOnSelection) {
                currentOnDismiss()
            }
        }
    }
    OverlayListPopup(
        show = isDropdownExpanded,
        alignment = PopupPositionProvider.Align.End,
        onDismissRequest = onDismiss,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
        renderInRootScaffold = renderInRootScaffold,
    ) {
        ListPopupColumn {
            entry.items.forEachIndexed { index, string ->
                key(index) {
                    DropdownImpl(
                        text = string,
                        optionSize = entry.items.size,
                        isSelected = entry.selectedIndex == index,
                        dropdownColors = dropdownColors,
                        onSelectedIndexChange = onItemSelected,
                        index = index,
                    )
                }
            }
        }
    }
}

@Composable
fun OverlayDropdownPreference(
    entries: List<DropdownEntry>,
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    startAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    maxHeight: Dp? = null,
    enabled: Boolean = true,
    showValue: Boolean = true,
    renderInRootScaffold: Boolean = true,
    collapseOnSelection: Boolean = false,
    onExpandedChange: ((Boolean) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
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

    val entriesNotEmpty = entries.isNotEmpty() && entries.any { it.items.isNotEmpty() }
    val actualEnabled = enabled && entriesNotEmpty

    val actionColor = if (actualEnabled) {
        MiuixTheme.colorScheme.onSurfaceVariantActions
    } else {
        MiuixTheme.colorScheme.disabledOnSecondaryVariant
    }

    val handleClick = remember(actualEnabled) {
        {
            if (actualEnabled) {
                setExpanded(!isDropdownExpanded.value)
                if (isDropdownExpanded.value) {
                    isHoldDown.value = true
                    currentHapticFeedback.performHapticFeedback(HapticFeedbackType.ContextClick)
                }
            }
        }
    }

    BasicComponent(
        modifier = modifier,
        interactionSource = interactionSource,
        insideMargin = insideMargin,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        startAction = startAction,
        endActions = {
            val selectedValueText = entries.joinToString("\n") { group ->
                group.selectedIndex?.let { index -> group.items.getOrNull(index) } ?: ""
            }.ifBlank { null }
            if (showValue && entriesNotEmpty && !selectedValueText.isNullOrBlank()) {
                Text(
                    text = selectedValueText,
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = MiuixTheme.textStyles.body2.fontSize,
                    color = actionColor,
                    textAlign = TextAlign.End,
                    lineHeight = MiuixTheme.textStyles.body2.lineHeight,
                )
            }
            DropdownArrowEndAction(
                actionColor = actionColor,
            )
            if (entriesNotEmpty) {
                OverlayDropdownPreferencePopup(
                    entries = entries,
                    isDropdownExpanded = isDropdownExpanded.value,
                    onDismiss = { setExpanded(false) },
                    onDismissFinished = { isHoldDown.value = false },
                    maxHeight = maxHeight,
                    dropdownColors = dropdownColors,
                    hapticFeedback = hapticFeedback,
                    renderInRootScaffold = renderInRootScaffold,
                    collapseOnSelection = collapseOnSelection,
                )
            }
        },
        bottomAction = bottomAction,
        onClick = handleClick,
        holdDownState = isHoldDown.value,
        enabled = actualEnabled,
    )
}

@Composable
private fun OverlayDropdownPreferencePopup(
    entries: List<DropdownEntry>,
    isDropdownExpanded: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    maxHeight: Dp?,
    dropdownColors: DropdownColors,
    hapticFeedback: HapticFeedback,
    renderInRootScaffold: Boolean,
    collapseOnSelection: Boolean,
) {
    val currentEntries by rememberUpdatedState(entries)
    val currentCollapseOnSelection by rememberUpdatedState(collapseOnSelection)
    val currentOnDismiss by rememberUpdatedState(onDismiss)
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)
    val onItemSelected: (Int, Int) -> Unit = remember {
        { entryIdx, selectedIdx ->
            currentHapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
            currentEntries.getOrNull(entryIdx)?.onSelectedIndexChange?.invoke(selectedIdx)
            if (currentCollapseOnSelection) {
                currentOnDismiss()
            }
        }
    }
    OverlayListPopup(
        show = isDropdownExpanded,
        alignment = PopupPositionProvider.Align.End,
        onDismissRequest = onDismiss,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
        renderInRootScaffold = renderInRootScaffold,
    ) {
        ListPopupColumn {
            entries.forEachIndexed { entryIdx, entry ->
                entry.items.forEachIndexed { itemIdx, option ->
                    DropdownImpl(
                        text = option,
                        optionSize = entry.items.size,
                        isSelected = entry.selectedIndex == itemIdx,
                        dropdownColors = dropdownColors,
                        onSelectedIndexChange = { selectedIdx ->
                            onItemSelected(entryIdx, selectedIdx)
                        },
                        index = itemIdx,
                    )
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
