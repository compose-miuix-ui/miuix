// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlendModeColorFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.ListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.basic.ArrowUpDownIntegrated
import top.yukonga.miuix.kmp.icon.icons.basic.Check
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A spinner component with Miuix style. (Popup Mode)
 *
 * @param items The list of [SpinnerEntry] to be shown in the [SuperSpinner].
 * @param selectedIndex The index of the selected item in the [SuperSpinner].
 * @param title The title of the [SuperSpinner].
 * @param titleColor The color of the title of the [SuperSpinner].
 * @param summary The summary of the [SuperSpinner].
 * @param summaryColor The color of the summary of the [SuperSpinner].
 * @param spinnerColors The [SpinnerColors] of the [SuperSpinner].
 * @param leftAction The [Composable] content that on the left side of the [SuperSpinner].
 * @param modifier The [Modifier] to be applied to the [SuperSpinner].
 * @param insideMargin The [PaddingValues] to be applied inside the [SuperSpinner].
 * @param maxHeight The maximum height of the [ListPopup].
 * @param enabled Whether the [SuperSpinner] is enabled.
 * @param showValue Whether to show the value of the [SuperSpinner].
 * @param onClick The callback when the [SuperSpinner] is clicked.
 * @param onSelectedIndexChange The callback to be invoked when the selected index of the [SuperSpinner] is changed.
 */
@Composable
fun SuperSpinner(
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    title: String,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: SpinnerColors = SpinnerDefaults.spinnerColors(),
    leftAction: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    maxHeight: Dp? = null,
    enabled: Boolean = true,
    showValue: Boolean = true,
    onClick: (() -> Unit)? = null,
    onSelectedIndexChange: ((Int) -> Unit)?,
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
            onClick?.invoke()
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
            SuperSpinnerRightActions(
                showValue = showValue,
                itemsNotEmpty = itemsNotEmpty,
                items = items,
                selectedIndex = selectedIndex,
                actionColor = actionColor
            )
            if (itemsNotEmpty) {
                SuperSpinnerPopup(
                    items = items,
                    selectedIndex = selectedIndex,
                    isDropdownExpanded = isDropdownExpanded,
                    maxHeight = maxHeight,
                    hapticFeedback = hapticFeedback,
                    spinnerColors = spinnerColors,
                    onSelectedIndexChange = onSelectedIndexChange
                )
            }
        },
        onClick = handleClick,
        holdDownState = isDropdownExpanded.value,
        enabled = actualEnabled
    )
}

@Composable
private fun SuperSpinnerPopup(
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    isDropdownExpanded: MutableState<Boolean>,
    maxHeight: Dp?,
    hapticFeedback: HapticFeedback,
    spinnerColors: SpinnerColors,
    onSelectedIndexChange: ((Int) -> Unit)?
) {
    val onSelectState = rememberUpdatedState(onSelectedIndexChange)
    ListPopup(
        show = isDropdownExpanded,
        alignment = PopupPositionProvider.Align.Right,
        onDismissRequest = {
            isDropdownExpanded.value = false
        },
        maxHeight = maxHeight
    ) {
        ListPopupColumn {
            items.forEachIndexed { index, spinnerEntry ->
                key(index) {
                    SpinnerItemImpl(
                        entry = spinnerEntry,
                        entryCount = items.size,
                        isSelected = selectedIndex == index,
                        index = index,
                        dialogMode = false,
                        spinnerColors = spinnerColors
                    ) { selectedIdx ->
                        hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                        onSelectState.value?.invoke(selectedIdx)
                        isDropdownExpanded.value = false
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.SuperSpinnerRightActions(
    showValue: Boolean,
    itemsNotEmpty: Boolean,
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    actionColor: Color
) {
    if (showValue && itemsNotEmpty) {
        Text(
            modifier = Modifier.widthIn(max = 130.dp),
            text = items[selectedIndex].title ?: "",
            fontSize = MiuixTheme.textStyles.body2.fontSize,
            color = actionColor,
            textAlign = TextAlign.End,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
    Image(
        modifier = Modifier
            .padding(start = 8.dp)
            .size(10.dp, 16.dp)
            .align(Alignment.CenterVertically),
        imageVector = MiuixIcons.Basic.ArrowUpDownIntegrated,
        colorFilter = ColorFilter.tint(actionColor),
        contentDescription = null
    )
}

/**
 * A [SuperSpinner] component with Miuix style, show Spinner as dialog. (Dialog Mode)
 *
 * @param items the list of [SpinnerEntry] to be shown in the [SuperSpinner].
 * @param selectedIndex the index of the selected item in the [SuperSpinner].
 * @param title the title of the [SuperSpinner].
 * @param titleColor the color of the title of the [SuperSpinner].
 * @param summary the summary of the [SuperSpinner].
 * @param summaryColor the color of the summary of the [SuperSpinner].
 * @param leftAction the action to be shown at the left side of the [SuperSpinner].
 * @param dialogButtonString the string of the button in the dialog.
 * @param popupModifier the [Modifier] to be applied to the popup of the [SuperSpinner].
 * @param modifier the [Modifier] to be applied to the [SuperSpinner].
 * @param insideMargin the [PaddingValues] to be applied inside the [SuperSpinner].
 * @param enabled whether the [SuperSpinner] is enabled.
 * @param showValue whether to show the value of the [SuperSpinner].
 * @param onClick the callback when the [SuperSpinner] is clicked.
 * @param onSelectedIndexChange the callback to be invoked when the selected index of the [SuperSpinner] is changed.
 */
@Composable
fun SuperSpinner(
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    title: String,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    spinnerColors: SpinnerColors = SpinnerDefaults.dialogSpinnerColors(),
    leftAction: @Composable (() -> Unit)? = null,
    dialogButtonString: String,
    popupModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    enabled: Boolean = true,
    showValue: Boolean = true,
    onClick: (() -> Unit)? = null,
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

    val componentModifier = modifier.pointerInput(actualEnabled) {
        if (!actualEnabled) return@pointerInput
    }

    val handleClick: () -> Unit = {
        if (actualEnabled) {
            onClick?.invoke()
            isDropdownExpanded.value = true
            hapticFeedback.performHapticFeedback(HapticFeedbackType.ContextClick)
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
        leftAction = leftAction,
        rightActions = {
            SuperSpinnerRightActions(
                showValue = showValue,
                itemsNotEmpty = itemsNotEmpty,
                items = items,
                selectedIndex = selectedIndex,
                actionColor = actionColor
            )
        },
        onClick = handleClick,
        holdDownState = isDropdownExpanded.value,
        enabled = actualEnabled,
    )

    SuperSpinnerDialog(
        items = items,
        selectedIndex = selectedIndex,
        title = title,
        dialogButtonString = dialogButtonString,
        popupModifier = popupModifier,
        isDropdownExpanded = isDropdownExpanded,
        hapticFeedback = hapticFeedback,
        spinnerColors = spinnerColors,
        onSelectedIndexChange = onSelectedIndexChange
    )
}

@Composable
private fun SuperSpinnerDialog(
    items: List<SpinnerEntry>,
    selectedIndex: Int,
    title: String,
    dialogButtonString: String,
    popupModifier: Modifier,
    isDropdownExpanded: MutableState<Boolean>,
    hapticFeedback: HapticFeedback,
    spinnerColors: SpinnerColors,
    onSelectedIndexChange: ((Int) -> Unit)?
) {
    SuperDialog(
        modifier = popupModifier,
        title = title,
        show = isDropdownExpanded,
        onDismissRequest = {
            isDropdownExpanded.value = false
        },
        insideMargin = DpSize(0.dp, 24.dp),
        content = {
            Layout(
                content = {
                    LazyColumn {
                        items(items.size) { index ->
                            SpinnerItemImpl(
                                entry = items[index],
                                entryCount = items.size,
                                isSelected = selectedIndex == index,
                                index = index,
                                dialogMode = true,
                                spinnerColors = spinnerColors
                            ) { selectedIdx ->
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                                onSelectedIndexChange?.invoke(selectedIdx)
                                isDropdownExpanded.value = false
                            }
                        }
                    }
                    TextButton(
                        modifier = Modifier
                            .padding(start = 24.dp, top = 12.dp, end = 24.dp)
                            .fillMaxWidth(),
                        text = dialogButtonString,
                        minHeight = 50.dp,
                        onClick = {
                            isDropdownExpanded.value = false
                        }
                    )
                }
            ) { measurables, constraints ->
                if (measurables.size != 2) {
                    layout(0, 0) { }
                } else {
                    val button = measurables[1].measure(constraints)
                    val lazyList = measurables[0].measure(
                        constraints.copy(
                            maxHeight = constraints.maxHeight - button.height
                        )
                    )
                    layout(constraints.maxWidth, lazyList.height + button.height) {
                        lazyList.place(0, 0)
                        button.place(0, lazyList.height)
                    }
                }
            }
        }
    )
}

/**
 * The implementation of the spinner.
 *
 * @param entry the [SpinnerEntry] to be shown in the spinner.
 * @param entryCount the count of the entries in the spinner.
 * @param isSelected whether the entry is selected.
 * @param index the index of the entry.
 * @param dialogMode whether the spinner is in dialog mode.
 * @param onSelectedIndexChange the callback to be invoked when the selected index of the spinner is changed.
 */
@Composable
fun SpinnerItemImpl(
    entry: SpinnerEntry,
    entryCount: Int,
    isSelected: Boolean,
    index: Int,
    dialogMode: Boolean = false,
    spinnerColors: SpinnerColors,
    onSelectedIndexChange: (Int) -> Unit,
) {
    val additionalTopPadding = if (!dialogMode && index == 0) 20.dp else 12.dp
    val additionalBottomPadding = if (!dialogMode && index == entryCount - 1) 20.dp else 12.dp

    val (titleColor, summaryColor, backgroundColor) = if (isSelected) {
        Triple(
            spinnerColors.selectedContentColor,
            spinnerColors.selectedSummaryColor,
            spinnerColors.selectedContainerColor
        )
    } else {
        Triple(
            spinnerColors.contentColor,
            spinnerColors.summaryColor,
            spinnerColors.containerColor
        )
    }

    val selectColor = if (isSelected) spinnerColors.selectedIndicatorColor else Color.Transparent

    val itemModifier = Modifier
        .clickable { onSelectedIndexChange(index) }
        .background(backgroundColor)
        .then(
            if (dialogMode) Modifier
                .heightIn(min = 56.dp)
                .widthIn(min = 200.dp)
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
            else Modifier.padding(horizontal = 20.dp)
        )
        .padding(top = additionalTopPadding, bottom = additionalBottomPadding)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = itemModifier
    ) {
        val contentRowModifier = if (dialogMode) Modifier else Modifier.widthIn(max = 216.dp)
        Row(
            modifier = contentRowModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            entry.icon?.let {
                it(Modifier.sizeIn(minWidth = 26.dp, minHeight = 26.dp).padding(end = 12.dp))
            }
            Column {
                entry.title?.let {
                    Text(
                        text = it,
                        fontSize = MiuixTheme.textStyles.body1.fontSize,
                        fontWeight = FontWeight.Medium,
                        color = titleColor
                    )
                }
                entry.summary?.let {
                    Text(
                        text = it,
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = summaryColor
                    )
                }
            }
        }
        Image(
            modifier = Modifier
                .padding(start = 12.dp)
                .size(20.dp),
            imageVector = MiuixIcons.Basic.Check,
            colorFilter = BlendModeColorFilter(selectColor, BlendMode.SrcIn),
            contentDescription = null,
        )
    }
}

/**
 * The spinner entry.
 */
data class SpinnerEntry(
    val icon: @Composable ((Modifier) -> Unit)? = null,
    val title: String? = null,
    val summary: String? = null
)

@Immutable
class SpinnerColors(
    val contentColor: Color,
    val summaryColor: Color,
    val containerColor: Color,
    val selectedContentColor: Color,
    val selectedSummaryColor: Color,
    val selectedContainerColor: Color,
    val selectedIndicatorColor: Color
)

object SpinnerDefaults {

    @Composable
    fun spinnerColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurface,
        summaryColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        containerColor: Color = MiuixTheme.colorScheme.surfaceVariant,
        selectedContentColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedSummaryColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedContainerColor: Color = MiuixTheme.colorScheme.surfaceVariant,
        selectedIndicatorColor: Color = MiuixTheme.colorScheme.onTertiaryContainer
    ): SpinnerColors {
        return SpinnerColors(
            contentColor = contentColor,
            summaryColor = summaryColor,
            containerColor = containerColor,
            selectedContentColor = selectedContentColor,
            selectedSummaryColor = selectedSummaryColor,
            selectedContainerColor = selectedContainerColor,
            selectedIndicatorColor = selectedIndicatorColor
        )
    }

    @Composable
    fun dialogSpinnerColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurface,
        summaryColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        containerColor: Color = MiuixTheme.colorScheme.surfaceVariant,
        selectedContentColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedSummaryColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedContainerColor: Color = MiuixTheme.colorScheme.tertiaryContainer,
        selectedIndicatorColor: Color = MiuixTheme.colorScheme.onTertiaryContainer
    ): SpinnerColors {
        return SpinnerColors(
            contentColor = contentColor,
            summaryColor = summaryColor,
            containerColor = containerColor,
            selectedContentColor = selectedContentColor,
            selectedSummaryColor = selectedSummaryColor,
            selectedContainerColor = selectedContainerColor,
            selectedIndicatorColor = selectedIndicatorColor
        )
    }
}
