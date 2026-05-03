// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlendModeColorFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowUpDown
import top.yukonga.miuix.kmp.icon.basic.Check
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun RowScope.DropdownArrowEndAction(
    actionColor: Color,
) {
    val colorFilter = remember(actionColor) { ColorFilter.tint(actionColor) }
    Image(
        modifier = Modifier
            .size(10.dp, 16.dp)
            .align(Alignment.CenterVertically),
        imageVector = MiuixIcons.Basic.ArrowUpDown,
        colorFilter = colorFilter,
        contentDescription = null,
    )
}

/**
 * The implementation of the dropdown.
 *
 * @param item The item of the current option.
 * @param optionSize The size of the options.
 * @param isSelected Whether the option is selected.
 * @param index The index of the current option in the options.
 * @param dropdownColors The [DropdownColors] used to style the option row.
 * @param enabled Whether the option is clickable. Disabled rows ignore clicks and use the disabled text color.
 * @param dialogMode Whether the item is shown in dialog mode.
 * @param onSelectedIndexChange The callback invoked with [index] when the option is selected.
 */
@Composable
fun DropdownImpl(
    item: DropdownItem,
    optionSize: Int,
    isSelected: Boolean,
    index: Int,
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    enabled: Boolean = item.enabled,
    dialogMode: Boolean = false,
    onSelectedIndexChange: (Int) -> Unit,
) {
    val additionalTopPadding = if (!dialogMode && index == 0) 20.dp else 12.dp
    val additionalBottomPadding = if (!dialogMode && index == optionSize - 1) 20.dp else 12.dp

    val backgroundColor = if (isSelected) {
        dropdownColors.selectedContainerColor
    } else {
        dropdownColors.containerColor
    }

    val checkColor = when {
        !isSelected -> Color.Transparent
        !enabled -> MiuixTheme.colorScheme.disabledOnSecondaryVariant
        else -> dropdownColors.selectedIndicatorColor
    }

    val titleColor = when {
        !enabled -> MiuixTheme.colorScheme.disabledOnSecondaryVariant
        isSelected -> dropdownColors.selectedContentColor
        else -> dropdownColors.contentColor
    }

    val summaryColor = when {
        !enabled -> MiuixTheme.colorScheme.disabledOnSecondaryVariant
        isSelected -> dropdownColors.selectedSummaryColor
        else -> dropdownColors.summaryColor
    }

    val currentOnSelectedIndexChange by rememberUpdatedState(onSelectedIndexChange)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .drawBehind { drawRect(backgroundColor) }
            .clickable(enabled = enabled) { currentOnSelectedIndexChange(index) }
            .then(
                if (dialogMode) {
                    Modifier
                        .heightIn(min = 56.dp)
                        .widthIn(min = 200.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp)
                } else {
                    Modifier.padding(horizontal = 20.dp)
                },
            )
            .padding(
                top = additionalTopPadding,
                bottom = additionalBottomPadding,
            ),
    ) {
        Row(
            modifier = if (dialogMode) Modifier else Modifier.widthIn(max = 216.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            item.icon?.let {
                it(
                    Modifier
                        .sizeIn(minWidth = 26.dp, minHeight = 26.dp)
                        .padding(end = 12.dp),
                )
            }
            Column {
                Text(
                    text = item.text,
                    fontSize = MiuixTheme.textStyles.body1.fontSize,
                    fontWeight = FontWeight.Medium,
                    color = titleColor,
                )
                item.summary?.let {
                    Text(
                        text = it,
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = summaryColor,
                    )
                }
            }
        }

        val checkColorFilter = remember(checkColor) {
            BlendModeColorFilter(checkColor, BlendMode.SrcIn)
        }
        Image(
            modifier = Modifier
                .padding(start = 12.dp)
                .size(20.dp),
            imageVector = MiuixIcons.Basic.Check,
            colorFilter = checkColorFilter,
            contentDescription = null,
        )
    }
}

@Composable
fun DropdownImpl(
    text: String,
    optionSize: Int,
    isSelected: Boolean,
    index: Int,
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    enabled: Boolean = true,
    dialogMode: Boolean = false,
    onSelectedIndexChange: (Int) -> Unit,
) {
    val item = remember(text, enabled) { DropdownItem(text = text, enabled = enabled) }
    DropdownImpl(
        item = item,
        optionSize = optionSize,
        isSelected = isSelected,
        index = index,
        dropdownColors = dropdownColors,
        enabled = enabled,
        dialogMode = dialogMode,
        onSelectedIndexChange = onSelectedIndexChange,
    )
}

/**
 * The implementation of the spinner.
 *
 * @param entry the [DropdownItem] to be shown in the spinner.
 * @param entryCount the count of the entries in the spinner.
 * @param isSelected whether the entry is selected.
 * @param index the index of the entry.
 * @param spinnerColors the [DropdownColors] used to style the entry row.
 * @param dialogMode whether the spinner is in dialog mode.
 * @param onSelectedIndexChange the callback to be invoked when the selected index of the spinner is changed.
 */
@Composable
fun SpinnerItemImpl(
    entry: DropdownItem,
    entryCount: Int,
    isSelected: Boolean,
    index: Int,
    spinnerColors: DropdownColors,
    dialogMode: Boolean = false,
    onSelectedIndexChange: (Int) -> Unit,
) {
    DropdownImpl(
        item = entry,
        optionSize = entryCount,
        isSelected = isSelected,
        index = index,
        dropdownColors = spinnerColors,
        enabled = entry.enabled,
        dialogMode = dialogMode,
        onSelectedIndexChange = onSelectedIndexChange,
    )
}

/**
 * Colors used by dropdown option rows.
 *
 * @param contentColor The text color of an unselected option.
 * @param summaryColor The summary text color of an unselected option.
 * @param containerColor The background color of an unselected option.
 * @param selectedContentColor The text color of the selected option.
 * @param selectedSummaryColor The summary text color of the selected option.
 * @param selectedContainerColor The background color of the selected option.
 * @param selectedIndicatorColor The color of the selected indicator icon.
 */
@Immutable
data class DropdownColors(
    val contentColor: Color,
    val summaryColor: Color,
    val containerColor: Color,
    val selectedContentColor: Color,
    val selectedSummaryColor: Color,
    val selectedContainerColor: Color,
    val selectedIndicatorColor: Color,
)

/**
 * A group of dropdown items.
 *
 * A [DropdownEntry] represents one visual group in a dropdown menu. Group titles are intentionally
 * reserved for future use because the original MIUI dropdown style currently has no matching
 * group-title presentation.
 *
 * @param items Items shown in this dropdown group.
 * @param enabled Whether this group is enabled. When false, all items in this group are disabled;
 * when true, each item's [DropdownItem.enabled] value is still respected.
 */
@Stable
data class DropdownEntry(
    val items: List<DropdownItem>,
    val enabled: Boolean = true,
)

/**
 * An item shown inside a dropdown, spinner, or dropdown menu.
 *
 * @param text Text shown for the item.
 * @param enabled Whether the item can be clicked.
 * @param selected Whether the item is selected.
 * @param onClick Callback invoked when the item is clicked.
 * @param icon Optional icon shown before [text].
 * @param summary Optional summary shown below [text].
 */
@Stable
data class DropdownItem(
    val text: String,
    val enabled: Boolean = true,
    val selected: Boolean = false,
    val onClick: (() -> Unit)? = null,
    val icon: @Composable ((Modifier) -> Unit)? = null,
    val summary: String? = null,
) {
    /**
     * [SpinnerEntry] compatibility
     */
    constructor(
        icon: @Composable ((Modifier) -> Unit)? = null,
        title: String? = null,
        summary: String? = null,
    ) : this(
        text = title.orEmpty(),
        icon = icon,
        summary = summary,
    )
}

object DropdownDefaults {

    @Composable
    fun dropdownColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        summaryColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        containerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        selectedContentColor: Color = MiuixTheme.colorScheme.primary,
        selectedSummaryColor: Color = MiuixTheme.colorScheme.primary,
        selectedContainerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        selectedIndicatorColor: Color = MiuixTheme.colorScheme.primary,
    ): DropdownColors = remember(
        contentColor,
        summaryColor,
        containerColor,
        selectedContentColor,
        selectedSummaryColor,
        selectedContainerColor,
        selectedIndicatorColor,
    ) {
        DropdownColors(
            contentColor = contentColor,
            summaryColor = summaryColor,
            containerColor = containerColor,
            selectedContentColor = selectedContentColor,
            selectedSummaryColor = selectedSummaryColor,
            selectedContainerColor = selectedContainerColor,
            selectedIndicatorColor = selectedIndicatorColor,
        )
    }

    @Composable
    fun dialogDropdownColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        summaryColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        containerColor: Color = Color.Transparent,
        selectedContentColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedSummaryColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedContainerColor: Color = MiuixTheme.colorScheme.tertiaryContainer,
        selectedIndicatorColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
    ): DropdownColors = dropdownColors(
        contentColor = contentColor,
        summaryColor = summaryColor,
        containerColor = containerColor,
        selectedContentColor = selectedContentColor,
        selectedSummaryColor = selectedSummaryColor,
        selectedContainerColor = selectedContainerColor,
        selectedIndicatorColor = selectedIndicatorColor,
    )
}

@Deprecated(
    message = "Use DropdownDefaults instead.",
    replaceWith = ReplaceWith("DropdownDefaults"),
)
object SpinnerDefaults {
    @Composable
    fun spinnerColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        summaryColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        containerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        selectedContentColor: Color = MiuixTheme.colorScheme.primary,
        selectedSummaryColor: Color = MiuixTheme.colorScheme.primary,
        selectedContainerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        selectedIndicatorColor: Color = MiuixTheme.colorScheme.primary,
    ): DropdownColors = DropdownDefaults.dropdownColors(
        contentColor = contentColor,
        summaryColor = summaryColor,
        containerColor = containerColor,
        selectedContentColor = selectedContentColor,
        selectedSummaryColor = selectedSummaryColor,
        selectedContainerColor = selectedContainerColor,
        selectedIndicatorColor = selectedIndicatorColor,
    )

    @Composable
    fun dialogSpinnerColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        summaryColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        containerColor: Color = Color.Transparent,
        selectedContentColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedSummaryColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedContainerColor: Color = MiuixTheme.colorScheme.tertiaryContainer,
        selectedIndicatorColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
    ): DropdownColors = DropdownDefaults.dialogDropdownColors(
        contentColor = contentColor,
        summaryColor = summaryColor,
        containerColor = containerColor,
        selectedContentColor = selectedContentColor,
        selectedSummaryColor = selectedSummaryColor,
        selectedContainerColor = selectedContainerColor,
        selectedIndicatorColor = selectedIndicatorColor,
    )
}

@Deprecated(
    message = "Use DropdownColors instead.",
    replaceWith = ReplaceWith("DropdownColors"),
)
typealias SpinnerColors = DropdownColors

@Deprecated(
    message = "Use DropdownItem instead.",
    replaceWith = ReplaceWith("DropdownItem"),
)
typealias SpinnerEntry = DropdownItem
