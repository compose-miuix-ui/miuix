// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.NonRestartableComposable
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
import androidx.compose.ui.input.pointer.pointerInput
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
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A spinner component with Miuix style, rendered at window level without `Scaffold`. (Popup Mode)
 *
 * @param items The list of [SpinnerEntry] to be shown in the [WindowSpinner].
 * @param selectedIndex The index of the selected item in the [WindowSpinner].
 * @param title The title of the [WindowSpinner].
 * @param modifier The [Modifier] to be applied to the [WindowSpinner].
 * @param titleColor The color of the title of the [WindowSpinner].
 * @param summary The summary of the [WindowSpinner].
 * @param summaryColor The color of the summary of the [WindowSpinner].
 * @param spinnerColors The [SpinnerColors] of the [WindowSpinner].
 * @param startAction The [Composable] content that on the start side of the [WindowSpinner].
 * @param bottomAction The [Composable] content at the bottom of the [WindowSpinner].
 * @param insideMargin The [PaddingValues] to be applied inside the [WindowSpinner].
 * @param maxHeight The maximum height of the [WindowListPopup].
 * @param enabled Whether the [WindowSpinner] is enabled.
 * @param showValue Whether to show the value of the [WindowSpinner].
 * @param onSelectedIndexChange The callback to be invoked when the selected index of the [WindowSpinner] is changed.
 */
@Composable
@NonRestartableComposable
fun WindowSpinner(
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
    onSelectedIndexChange: ((Int) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isDropdownExpanded = rememberSaveable { mutableStateOf(false) }
    val hapticFeedback = LocalHapticFeedback.current

    val itemsNotEmpty = items.isNotEmpty()
    val actualEnabled = enabled && itemsNotEmpty

    val actionColor = if (actualEnabled) {
        MiuixTheme.colorScheme.onSurfaceVariantActions
    } else {
        MiuixTheme.colorScheme.disabledOnSecondaryVariant
    }

    val handleClick: () -> Unit = {
        if (actualEnabled) {
            isDropdownExpanded.value = !isDropdownExpanded.value
            if (isDropdownExpanded.value) {
                hapticFeedback.performHapticFeedback(HapticFeedbackType.ContextClick)
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
                WindowSpinnerPopup(
                    items = items,
                    selectedIndex = selectedIndex,
                    isDropdownExpanded = isDropdownExpanded,
                    maxHeight = maxHeight,
                    hapticFeedback = hapticFeedback,
                    spinnerColors = spinnerColors,
                    onSelectedIndexChange = onSelectedIndexChange,
                )
            }
        },
        bottomAction = bottomAction,
        onClick = handleClick,
        holdDownState = isDropdownExpanded.value,
        enabled = actualEnabled,
    )
}

@Composable
private fun WindowSpinnerPopup(
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    isDropdownExpanded: MutableState<Boolean>,
    maxHeight: Dp?,
    hapticFeedback: HapticFeedback,
    spinnerColors: SpinnerColors,
    onSelectedIndexChange: ((Int) -> Unit)?,
) {
    val onSelectState = rememberUpdatedState(onSelectedIndexChange)
    WindowListPopup(
        show = isDropdownExpanded,
        alignment = PopupPositionProvider.Align.End,
        onDismissRequest = {
            isDropdownExpanded.value = false
        },
        maxHeight = maxHeight,
    ) {
        val dismiss = LocalWindowListPopupState.current
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
                    ) { selectedIdx ->
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                        onSelectState.value?.invoke(selectedIdx)
                        dismiss()
                    }
                }
            }
        }
    }
}

/**
 * A [WindowSpinner] component with Miuix style, show Spinner as dialog, rendered at window level without `Scaffold`. (Dialog Mode)
 *
 * @param items the list of [SpinnerEntry] to be shown in the [WindowSpinner].
 * @param selectedIndex the index of the selected item in the [WindowSpinner].
 * @param title the title of the [WindowSpinner].
 * @param dialogButtonString the string of the button in the dialog.
 * @param modifier the [Modifier] to be applied to the [WindowSpinner].
 * @param popupModifier the [Modifier] to be applied to the popup of the [WindowSpinner].
 * @param titleColor the color of the title of the [WindowSpinner].
 * @param summary the summary of the [WindowSpinner].
 * @param summaryColor the color of the summary of the [WindowSpinner].
 * @param startAction the action to be shown at the start side of the [WindowSpinner].
 * @param bottomAction The [Composable] content at the bottom of the [WindowSpinner].
 * @param insideMargin the [PaddingValues] to be applied inside the [WindowSpinner].
 * @param enabled whether the [WindowSpinner] is enabled.
 * @param showValue whether to show the value of the [WindowSpinner].
 * @param onSelectedIndexChange the callback to be invoked when the selected index of the [WindowSpinner] is changed.
 */
@Composable
@NonRestartableComposable
fun WindowSpinner(
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
    onSelectedIndexChange: ((Int) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isDropdownExpanded = remember { mutableStateOf(false) }
    val hapticFeedback = LocalHapticFeedback.current

    val itemsNotEmpty = items.isNotEmpty()
    val actualEnabled = enabled && itemsNotEmpty

    val actionColor = if (actualEnabled) {
        MiuixTheme.colorScheme.onSurfaceVariantActions
    } else {
        MiuixTheme.colorScheme.disabledOnSecondaryVariant
    }

    val componentModifier = modifier.pointerInput(actualEnabled) {
        if (!actualEnabled) return@pointerInput
    }

    val handleClick: () -> Unit = {
        if (actualEnabled) {
            isDropdownExpanded.value = !isDropdownExpanded.value
        }
    }

    BasicComponent(
        modifier = componentModifier,
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
            WindowSpinnerDialog(
                items = items,
                selectedIndex = selectedIndex,
                title = title,
                dialogButtonString = dialogButtonString,
                isDropdownExpanded = isDropdownExpanded,
                hapticFeedback = hapticFeedback,
                spinnerColors = spinnerColors,
                popupModifier = popupModifier,
                onSelectedIndexChange = onSelectedIndexChange,
            )
        },
        bottomAction = bottomAction,
        onClick = handleClick,
        holdDownState = isDropdownExpanded.value,
        enabled = actualEnabled,
    )
}

@Composable
private fun WindowSpinnerDialog(
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    title: String,
    dialogButtonString: String,
    isDropdownExpanded: MutableState<Boolean>,
    hapticFeedback: HapticFeedback,
    spinnerColors: SpinnerColors,
    popupModifier: Modifier = Modifier,
    onSelectedIndexChange: ((Int) -> Unit)? = null,
) {
    val currentOnSelectedIndexChange by rememberUpdatedState(onSelectedIndexChange)
    WindowDialog(
        modifier = popupModifier,
        title = title,
        show = isDropdownExpanded,
        onDismissRequest = {
            isDropdownExpanded.value = false
        },
        insideMargin = DpSize(0.dp, 24.dp),
        content = {
            val dismiss = LocalWindowDialogState.current
            Layout(
                content = {
                    LazyColumn {
                        items(items.size) { index ->
                            SpinnerItemImpl(
                                entry = items[index],
                                entryCount = items.size,
                                isSelected = selectedIndex == index,
                                index = index,
                                spinnerColors = spinnerColors,
                                dialogMode = true,
                            ) { selectedIdx ->
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                                currentOnSelectedIndexChange?.invoke(selectedIdx)
                                dismiss.invoke()
                            }
                        }
                    }
                    TextButton(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 12.dp, end = 24.dp)
                            .fillMaxWidth(),
                        text = dialogButtonString,
                        minHeight = 50.dp,
                        onClick = { dismiss.invoke() },
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
