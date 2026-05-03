// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.preference

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.DropdownArrowEndAction
import top.yukonga.miuix.kmp.basic.DropdownColors
import top.yukonga.miuix.kmp.basic.DropdownDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.popup.OverlayDropdownDialog
import top.yukonga.miuix.kmp.popup.OverlayDropdownPopup
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A spinner component with Miuix style. (Popup Mode)
 *
 * @param items The list of [DropdownItem] to be shown in the [OverlaySpinnerPreference].
 * @param selectedIndex The index of the selected item in the [OverlaySpinnerPreference].
 * @param title The title of the [OverlaySpinnerPreference].
 * @param modifier The [Modifier] to be applied to the [OverlaySpinnerPreference].
 * @param titleColor The color of the title of the [OverlaySpinnerPreference].
 * @param summary The summary of the [OverlaySpinnerPreference].
 * @param summaryColor The color of the summary of the [OverlaySpinnerPreference].
 * @param spinnerColors The [DropdownColors] of the [OverlaySpinnerPreference].
 * @param startAction The [Composable] content that on the start side of the [OverlaySpinnerPreference].
 * @param bottomAction The [Composable] content at the bottom of the [OverlaySpinnerPreference].
 * @param insideMargin The [PaddingValues] to be applied inside the [OverlaySpinnerPreference].
 * @param maxHeight The maximum height of the dropdown popup.
 * @param enabled Whether the [OverlaySpinnerPreference] is enabled.
 * @param showValue Whether to show the value of the [OverlaySpinnerPreference].
 * @param renderInRootScaffold Whether to render the popup in the root (outermost) Scaffold.
 *   When true (default), the popup covers the full screen. When false, it renders within the
 *   current Scaffold's bounds with position compensation.
 * @param onExpandedChange The callback to be invoked when the expanded state of the [OverlaySpinnerPreference] changes.
 * @param onSelectedIndexChange The callback to be invoked when the selected index of the [OverlaySpinnerPreference] is changed.
 */
@Composable
fun OverlaySpinnerPreference(
    items: List<DropdownItem>,
    selectedIndex: Int,
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: DropdownColors = DropdownDefaults.dropdownColors(),
    startAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    maxHeight: Dp? = null,
    enabled: Boolean = true,
    showValue: Boolean = true,
    renderInRootScaffold: Boolean = true,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    onSelectedIndexChange: ((Int) -> Unit)? = null,
) {
    val entries = remember(items, selectedIndex, onSelectedIndexChange) {
        listOf(
            DropdownEntry(
                items = items.mapIndexed { index, item ->
                    item.copy(
                        selected = index == selectedIndex,
                        onClick = {
                            onSelectedIndexChange?.invoke(index)
                            item.onClick?.invoke()
                        },
                    )
                },
            ),
        )
    }
    OverlaySpinnerPreference(
        entries = entries,
        title = title,
        modifier = modifier,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        spinnerColors = spinnerColors,
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
fun OverlaySpinnerPreference(
    entry: DropdownEntry,
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: DropdownColors = DropdownDefaults.dropdownColors(),
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
    val entries = remember(entry) { listOf(entry) }
    OverlaySpinnerPreference(
        entries = entries,
        title = title,
        modifier = modifier,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        spinnerColors = spinnerColors,
        startAction = startAction,
        bottomAction = bottomAction,
        insideMargin = insideMargin,
        maxHeight = maxHeight,
        enabled = enabled,
        showValue = showValue,
        renderInRootScaffold = renderInRootScaffold,
        collapseOnSelection = collapseOnSelection,
        onExpandedChange = onExpandedChange,
    )
}

@Composable
fun OverlaySpinnerPreference(
    entries: List<DropdownEntry>,
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: DropdownColors = DropdownDefaults.dropdownColors(),
    startAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    maxHeight: Dp? = null,
    enabled: Boolean = true,
    showValue: Boolean = true,
    renderInRootScaffold: Boolean = true,
    collapseOnSelection: Boolean = entries.size <= 1,
    onExpandedChange: ((Boolean) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isDropdownExpanded = rememberSaveable { mutableStateOf(false) }
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
    val hasEntries = nonEmptyEntries.isNotEmpty()
    val actualEnabled = enabled && hasEntries

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
            val selectedValueText = nonEmptyEntries
                .asSequence()
                .flatMap { group -> group.items }
                .filter { it.selected }
                .map { it.text }
                .filter { it.isNotBlank() }
                .joinToString("\n")
                .ifBlank { null }
            if (showValue && hasEntries && !selectedValueText.isNullOrBlank()) {
                Text(
                    text = selectedValueText,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f, fill = false),
                    fontSize = MiuixTheme.textStyles.body2.fontSize,
                    color = actionColor,
                    textAlign = TextAlign.End,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            DropdownArrowEndAction(
                actionColor = actionColor,
            )
            if (hasEntries) {
                OverlayDropdownPopup(
                    entries = nonEmptyEntries,
                    show = isDropdownExpanded.value,
                    onDismiss = { setExpanded(false) },
                    onDismissFinished = { isHoldDown.value = false },
                    maxHeight = maxHeight,
                    dropdownColors = spinnerColors,
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

/**
 * A [OverlaySpinnerPreference] component with Miuix style, show Spinner as dialog. (Dialog Mode)
 *
 * @param items the list of [DropdownItem] to be shown in the [OverlaySpinnerPreference].
 * @param selectedIndex the index of the selected item in the [OverlaySpinnerPreference].
 * @param title the title of the [OverlaySpinnerPreference].
 * @param dialogButtonString the string of the button in the dialog.
 * @param modifier the [Modifier] to be applied to the [OverlaySpinnerPreference].
 * @param popupModifier the [Modifier] to be applied to the popup of the [OverlaySpinnerPreference].
 * @param titleColor the color of the title of the [OverlaySpinnerPreference].
 * @param summary the summary of the [OverlaySpinnerPreference].
 * @param summaryColor the color of the summary of the [OverlaySpinnerPreference].
 * @param startAction the action to be shown at the start side of the [OverlaySpinnerPreference].
 * @param bottomAction the action to be shown at the bottom of the [OverlaySpinnerPreference].
 * @param insideMargin the [PaddingValues] to be applied inside the [OverlaySpinnerPreference].
 * @param enabled whether the [OverlaySpinnerPreference] is enabled.
 * @param showValue whether to show the value of the [OverlaySpinnerPreference].
 * @param renderInRootScaffold Whether to render the dialog in the root (outermost) Scaffold.
 *   When true (default), the dialog covers the full screen. When false, it renders within the
 *   current Scaffold's bounds.
 * @param onExpandedChange the callback to be invoked when the expanded state of the [OverlaySpinnerPreference] changes.
 * @param onSelectedIndexChange the callback to be invoked when the selected index of the [OverlaySpinnerPreference] is changed.
 */
@Composable
fun OverlaySpinnerPreference(
    items: List<DropdownItem>,
    selectedIndex: Int,
    title: String,
    dialogButtonString: String,
    modifier: Modifier = Modifier,
    popupModifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: DropdownColors = DropdownDefaults.dialogDropdownColors(),
    startAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    enabled: Boolean = true,
    showValue: Boolean = true,
    renderInRootScaffold: Boolean = true,
    collapseOnSelection: Boolean = true,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    onSelectedIndexChange: ((Int) -> Unit)? = null,
) {
    val entry = remember(items, selectedIndex, onSelectedIndexChange) {
        DropdownEntry(
            items = items.mapIndexed { index, item ->
                item.copy(
                    selected = index == selectedIndex,
                    onClick = {
                        onSelectedIndexChange?.invoke(index)
                        item.onClick?.invoke()
                    },
                )
            },
        )
    }
    OverlaySpinnerPreference(
        entry = entry,
        title = title,
        dialogButtonString = dialogButtonString,
        modifier = modifier,
        popupModifier = popupModifier,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        spinnerColors = spinnerColors,
        startAction = startAction,
        bottomAction = bottomAction,
        insideMargin = insideMargin,
        enabled = enabled,
        showValue = showValue,
        renderInRootScaffold = renderInRootScaffold,
        collapseOnSelection = collapseOnSelection,
        onExpandedChange = onExpandedChange,
    )
}

@Composable
fun OverlaySpinnerPreference(
    entry: DropdownEntry,
    title: String,
    dialogButtonString: String,
    modifier: Modifier = Modifier,
    popupModifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: DropdownColors = DropdownDefaults.dialogDropdownColors(),
    startAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    enabled: Boolean = true,
    showValue: Boolean = true,
    renderInRootScaffold: Boolean = true,
    collapseOnSelection: Boolean = true,
    onExpandedChange: ((Boolean) -> Unit)? = null,
) {
    val entries = remember(entry) { listOf(entry) }
    OverlaySpinnerPreference(
        entries = entries,
        title = title,
        dialogButtonString = dialogButtonString,
        modifier = modifier,
        popupModifier = popupModifier,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        spinnerColors = spinnerColors,
        startAction = startAction,
        bottomAction = bottomAction,
        insideMargin = insideMargin,
        enabled = enabled,
        showValue = showValue,
        renderInRootScaffold = renderInRootScaffold,
        collapseOnSelection = collapseOnSelection,
        onExpandedChange = onExpandedChange,
    )
}

@Composable
fun OverlaySpinnerPreference(
    entries: List<DropdownEntry>,
    title: String,
    dialogButtonString: String,
    modifier: Modifier = Modifier,
    popupModifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: DropdownColors = DropdownDefaults.dialogDropdownColors(),
    startAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    enabled: Boolean = true,
    showValue: Boolean = true,
    renderInRootScaffold: Boolean = true,
    collapseOnSelection: Boolean = entries.size <= 1,
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

    val nonEmptyEntries = entries.filter { it.items.isNotEmpty() }
    val hasEntries = nonEmptyEntries.isNotEmpty()
    val actualEnabled = enabled && hasEntries

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
            val selectedValueText = nonEmptyEntries
                .asSequence()
                .flatMap { group -> group.items }
                .filter { it.selected }
                .map { it.text }
                .filter { it.isNotBlank() }
                .joinToString("\n")
                .ifBlank { null }
            if (showValue && hasEntries && !selectedValueText.isNullOrBlank()) {
                Text(
                    text = selectedValueText,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f, fill = false),
                    fontSize = MiuixTheme.textStyles.body2.fontSize,
                    color = actionColor,
                    textAlign = TextAlign.End,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            DropdownArrowEndAction(
                actionColor = actionColor,
            )
            if (hasEntries) {
                OverlayDropdownDialog(
                    entries = nonEmptyEntries,
                    title = title,
                    dialogButtonString = dialogButtonString,
                    show = isDropdownExpanded.value,
                    onDismiss = { setExpanded(false) },
                    onDismissFinished = { isHoldDown.value = false },
                    dropdownColors = spinnerColors,
                    popupModifier = popupModifier,
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
