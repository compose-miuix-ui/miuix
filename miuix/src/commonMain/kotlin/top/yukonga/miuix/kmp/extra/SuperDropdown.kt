// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

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
import top.yukonga.miuix.kmp.basic.DropdownImpl
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A dropdown with a title and a summary.
 *
 * @param items The options of the [SuperDropdown].
 * @param selectedIndex The index of the selected option.
 * @param title The title of the [SuperDropdown].
 * @param modifier The modifier to be applied to the [SuperDropdown].
 * @param titleColor The color of the title.
 * @param summary The summary of the [SuperDropdown].
 * @param summaryColor The color of the summary.
 * @param dropdownColors The [DropdownColors] of the [SuperDropdown].
 * @param startAction The [Composable] content that on the start side of the [SuperDropdown].
 * @param bottomAction The [Composable] content at the bottom of the [SuperDropdown].
 * @param insideMargin The margin inside the [SuperDropdown].
 * @param maxHeight The maximum height of the [SuperListPopup].
 * @param enabled Whether the [SuperDropdown] is enabled.
 * @param showValue Whether to show the selected value of the [SuperDropdown].
 * @param renderInRootScaffold Whether to render the popup in the root (outermost) Scaffold.
 *   When true (default), the popup covers the full screen. When false, it renders within the
 *   current Scaffold's bounds with position compensation.
 * @param onSelectedIndexChange The callback when the selected index of the [SuperDropdown] is changed.
 */
@Composable
fun SuperDropdown(
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
    onSelectedIndexChange: ((Int) -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isDropdownExpanded = remember { mutableStateOf(false) }
    val isHoldDown = remember { mutableStateOf(false) }
    val hapticFeedback = LocalHapticFeedback.current
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)

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
                isDropdownExpanded.value = !isDropdownExpanded.value
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
                    text = items[selectedIndex],
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
                SuperDropdownPopup(
                    items = items,
                    selectedIndex = selectedIndex,
                    isDropdownExpanded = isDropdownExpanded.value,
                    onDismiss = { isDropdownExpanded.value = false },
                    onDismissFinished = { isHoldDown.value = false },
                    maxHeight = maxHeight,
                    dropdownColors = dropdownColors,
                    hapticFeedback = hapticFeedback,
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
private fun SuperDropdownPopup(
    items: List<String>,
    selectedIndex: Int,
    isDropdownExpanded: Boolean,
    onDismiss: () -> Unit,
    onDismissFinished: () -> Unit,
    maxHeight: Dp?,
    dropdownColors: DropdownColors,
    hapticFeedback: HapticFeedback,
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
    SuperListPopup(
        show = isDropdownExpanded,
        alignment = PopupPositionProvider.Align.End,
        onDismissRequest = onDismiss,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
        renderInRootScaffold = renderInRootScaffold,
    ) {
        ListPopupColumn {
            items.forEachIndexed { index, string ->
                key(index) {
                    DropdownImpl(
                        text = string,
                        optionSize = items.size,
                        isSelected = selectedIndex == index,
                        dropdownColors = dropdownColors,
                        onSelectedIndexChange = onItemSelected,
                        index = index,
                    )
                }
            }
        }
    }
}
