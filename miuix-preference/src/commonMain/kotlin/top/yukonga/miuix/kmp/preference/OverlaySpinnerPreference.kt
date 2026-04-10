// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.preference

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.DropdownArrowEndAction
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.SpinnerColors
import top.yukonga.miuix.kmp.basic.SpinnerDefaults
import top.yukonga.miuix.kmp.basic.SpinnerEntry
import top.yukonga.miuix.kmp.basic.SpinnerItemImpl
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.overlay.OverlayDialog
import top.yukonga.miuix.kmp.overlay.OverlayListPopup
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A spinner component with Miuix style. (Popup Mode)
 *
 * @param items The list of [SpinnerEntry] to be shown in the [OverlaySpinnerPreference].
 * @param selectedIndex The index of the selected item in the [OverlaySpinnerPreference].
 * @param title The title of the [OverlaySpinnerPreference].
 * @param modifier The [Modifier] to be applied to the [OverlaySpinnerPreference].
 * @param titleColor The color of the title of the [OverlaySpinnerPreference].
 * @param summary The summary of the [OverlaySpinnerPreference].
 * @param summaryColor The color of the summary of the [OverlaySpinnerPreference].
 * @param spinnerColors The [SpinnerColors] of the [OverlaySpinnerPreference].
 * @param startAction The [Composable] content that on the start side of the [OverlaySpinnerPreference].
 * @param bottomAction The [Composable] content at the bottom of the [OverlaySpinnerPreference].
 * @param insideMargin The [PaddingValues] to be applied inside the [OverlaySpinnerPreference].
 * @param maxHeight The maximum height of the [OverlayListPopup].
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
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: SpinnerColors = SpinnerDefaults.spinnerColors(),
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

    val itemsNotEmpty = items.isNotEmpty()
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
                Text(
                    text = items[selectedIndex].title ?: "",
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
                OverlaySpinnerPreferencePopup(
                    items = items,
                    selectedIndex = selectedIndex,
                    isDropdownExpanded = isDropdownExpanded.value,
                    onDismiss = { setExpanded(false) },
                    onDismissFinished = { isHoldDown.value = false },
                    maxHeight = maxHeight,
                    hapticFeedback = hapticFeedback,
                    spinnerColors = spinnerColors,
                    renderInRootScaffold = renderInRootScaffold,
                    onSelectedIndexChange = onSelectedIndexChange,
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
private fun OverlaySpinnerPreferencePopup(
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    isDropdownExpanded: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    maxHeight: Dp?,
    hapticFeedback: HapticFeedback,
    spinnerColors: SpinnerColors,
    renderInRootScaffold: Boolean,
    onSelectedIndexChange: ((Int) -> Unit)?,
) {
    val onSelectState = rememberUpdatedState(onSelectedIndexChange)
    val currentOnDismiss by rememberUpdatedState(onDismiss)
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)
    val onItemSelected: (Int) -> Unit = remember {
        { selectedIdx ->
            currentHapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
            onSelectState.value?.invoke(selectedIdx)
            currentOnDismiss()
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
            items.forEachIndexed { index, spinnerEntry ->
                key(index) {
                    SpinnerItemImpl(
                        entry = spinnerEntry,
                        entryCount = items.size,
                        isSelected = selectedIndex == index,
                        index = index,
                        spinnerColors = spinnerColors,
                        dialogMode = false,
                        onSelectedIndexChange = onItemSelected,
                    )
                }
            }
        }
    }
}

/**
 * A [OverlaySpinnerPreference] component with Miuix style, show Spinner as dialog. (Dialog Mode)
 *
 * @param items the list of [SpinnerEntry] to be shown in the [OverlaySpinnerPreference].
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
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    title: String,
    dialogButtonString: String,
    modifier: Modifier = Modifier,
    popupModifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: SpinnerColors = SpinnerDefaults.dialogSpinnerColors(),
    startAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    enabled: Boolean = true,
    showValue: Boolean = true,
    renderInRootScaffold: Boolean = true,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    onSelectedIndexChange: ((Int) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isDropdownExpanded = remember { mutableStateOf(false) }
    val isHoldDown = remember { mutableStateOf(false) }
    val hapticFeedback = LocalHapticFeedback.current
    val currentOnExpandedChange = rememberUpdatedState(onExpandedChange)
    val setExpanded: (Boolean) -> Unit = remember {
        { expanded ->
            if (isDropdownExpanded.value != expanded) {
                isDropdownExpanded.value = expanded
                currentOnExpandedChange.value?.invoke(expanded)
            }
        }
    }

    val itemsNotEmpty = items.isNotEmpty()
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
                Text(
                    text = items[selectedIndex].title ?: "",
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
            OverlaySpinnerPreferenceDialog(
                items = items,
                selectedIndex = selectedIndex,
                title = title,
                dialogButtonString = dialogButtonString,
                isDropdownExpanded = isDropdownExpanded.value,
                onDismiss = { setExpanded(false) },
                onDismissFinished = { isHoldDown.value = false },
                hapticFeedback = hapticFeedback,
                spinnerColors = spinnerColors,
                popupModifier = popupModifier,
                renderInRootScaffold = renderInRootScaffold,
                onSelectedIndexChange = onSelectedIndexChange,
            )
        },
        bottomAction = bottomAction,
        onClick = handleClick,
        holdDownState = isHoldDown.value,
        enabled = actualEnabled,
    )
}

@Composable
private fun OverlaySpinnerPreferenceDialog(
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    title: String,
    dialogButtonString: String,
    isDropdownExpanded: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    hapticFeedback: HapticFeedback,
    spinnerColors: SpinnerColors,
    popupModifier: Modifier = Modifier,
    renderInRootScaffold: Boolean = true,
    onSelectedIndexChange: ((Int) -> Unit)? = null,
) {
    val currentOnSelectedIndexChange by rememberUpdatedState(onSelectedIndexChange)
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)
    val currentOnDismiss by rememberUpdatedState(onDismiss)
    val onItemSelected: (Int) -> Unit = remember {
        { selectedIdx ->
            currentHapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
            currentOnSelectedIndexChange?.invoke(selectedIdx)
            currentOnDismiss()
        }
    }
    val showState = remember { mutableStateOf(false) }
    showState.value = isDropdownExpanded
    OverlayDialog(
        show = showState.value,
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
                        items(items.size, key = { it }) { index ->
                            SpinnerItemImpl(
                                entry = items[index],
                                entryCount = items.size,
                                isSelected = selectedIndex == index,
                                index = index,
                                spinnerColors = spinnerColors,
                                dialogMode = true,
                                onSelectedIndexChange = onItemSelected,
                            )
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
