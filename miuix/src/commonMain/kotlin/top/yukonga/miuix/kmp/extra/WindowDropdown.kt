// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.DropdownColors
import top.yukonga.miuix.kmp.basic.DropdownDefaults
import top.yukonga.miuix.kmp.basic.DropdownImpl
import top.yukonga.miuix.kmp.basic.DropdownRightActions
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A dropdown with a title and a summary, rendered at window level without `Scaffold`.
 *
 * @param items The options of the [WindowDropdown].
 * @param selectedIndex The index of the selected option.
 * @param title The title of the [WindowDropdown].
 * @param titleColor The color of the title.
 * @param summary The summary of the [WindowDropdown].
 * @param summaryColor The color of the summary.
 * @param dropdownColors The [DropdownColors] of the [WindowDropdown].
 * @param leftAction The [Composable] content that on the left side of the [WindowDropdown].
 * @param bottomAction The [Composable] content at the bottom of the [WindowDropdown].
 * @param modifier The modifier to be applied to the [WindowDropdown].
 * @param insideMargin The margin inside the [WindowDropdown].
 * @param maxHeight The maximum height of the [WindowListPopup].
 * @param enabled Whether the [WindowDropdown] is enabled.
 * @param showValue Whether to show the selected value of the [WindowDropdown].
 * @param onSelectedIndexChange The callback when the selected index of the [WindowDropdown] is changed.
 */
@Composable
@NonRestartableComposable
fun WindowDropdown(
    items: List<String>,
    selectedIndex: Int,
    title: String,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    leftAction: @Composable (() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    modifier: Modifier = Modifier,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    maxHeight: Dp? = null,
    enabled: Boolean = true,
    showValue: Boolean = true,
    onSelectedIndexChange: ((Int) -> Unit)?,
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
        leftAction = leftAction,
        rightActions = {
            DropdownRightActions(
                showValue = showValue,
                itemsNotEmpty = itemsNotEmpty,
                items = items,
                selectedIndex = selectedIndex,
                actionColor = actionColor,
            )
            if (itemsNotEmpty) {
                WindowDropdownPopup(
                    items = items,
                    selectedIndex = selectedIndex,
                    isDropdownExpanded = isDropdownExpanded,
                    maxHeight = maxHeight,
                    dropdownColors = dropdownColors,
                    hapticFeedback = hapticFeedback,
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
private fun WindowDropdownPopup(
    items: List<String>,
    selectedIndex: Int,
    isDropdownExpanded: MutableState<Boolean>,
    maxHeight: Dp?,
    dropdownColors: DropdownColors,
    hapticFeedback: HapticFeedback,
    onSelectedIndexChange: ((Int) -> Unit)?,
) {
    val onSelectState = rememberUpdatedState(onSelectedIndexChange)
    WindowListPopup(
        show = isDropdownExpanded,
        alignment = PopupPositionProvider.Align.Right,
        onDismissRequest = {
            isDropdownExpanded.value = false
        },
        maxHeight = maxHeight,
    ) {
        val dismiss = LocalWindowListPopupState.current
        ListPopupColumn {
            items.forEachIndexed { index, string ->
                key(index) {
                    DropdownImpl(
                        text = string,
                        optionSize = items.size,
                        isSelected = selectedIndex == index,
                        dropdownColors = dropdownColors,
                        onSelectedIndexChange = { selectedIdx ->
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                            onSelectState.value?.invoke(selectedIdx)
                            dismiss()
                        },
                        index = index,
                    )
                }
            }
        }
    }
}
