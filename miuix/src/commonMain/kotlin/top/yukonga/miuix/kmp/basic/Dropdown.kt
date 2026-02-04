// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Image(
        modifier = Modifier
            .size(10.dp, 16.dp)
            .align(Alignment.CenterVertically),
        imageVector = MiuixIcons.Basic.ArrowUpDown,
        colorFilter = ColorFilter.tint(actionColor),
        contentDescription = null,
    )
}

/**
 * The implementation of the dropdown.
 *
 * @param text The text of the current option.
 * @param optionSize The size of the options.
 * @param isSelected Whether the option is selected.
 * @param index The index of the current option in the options.
 * @param onSelectedIndexChange The callback when the index is selected.
 */
@Composable
fun DropdownImpl(
    text: String,
    optionSize: Int,
    isSelected: Boolean,
    index: Int,
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    onSelectedIndexChange: (Int) -> Unit,
) {
    val additionalTopPadding = if (index == 0) 20.dp else 12.dp
    val additionalBottomPadding = if (index == optionSize - 1) 20.dp else 12.dp

    val (textColor, backgroundColor) = if (isSelected) {
        dropdownColors.selectedContentColor to dropdownColors.selectedContainerColor
    } else {
        dropdownColors.contentColor to dropdownColors.containerColor
    }

    val checkColor = if (isSelected) {
        dropdownColors.selectedContentColor
    } else {
        Color.Transparent
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clickable { onSelectedIndexChange(index) }
            .background(backgroundColor)
            .padding(horizontal = 20.dp)
            .padding(
                top = additionalTopPadding,
                bottom = additionalBottomPadding,
            ),
    ) {
        Text(
            modifier = Modifier.widthIn(max = 200.dp),
            text = text,
            fontSize = MiuixTheme.textStyles.body1.fontSize,
            fontWeight = FontWeight.Medium,
            color = textColor,
        )

        Image(
            modifier = Modifier
                .padding(start = 12.dp)
                .size(20.dp),
            imageVector = MiuixIcons.Basic.Check,
            colorFilter = BlendModeColorFilter(checkColor, BlendMode.SrcIn),
            contentDescription = null,
        )
    }
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
    spinnerColors: SpinnerColors,
    dialogMode: Boolean = false,
    onSelectedIndexChange: (Int) -> Unit,
) {
    val additionalTopPadding = if (!dialogMode && index == 0) 20.dp else 12.dp
    val additionalBottomPadding = if (!dialogMode && index == entryCount - 1) 20.dp else 12.dp

    val (titleColor, summaryColor, backgroundColor) = if (isSelected) {
        Triple(
            spinnerColors.selectedContentColor,
            spinnerColors.selectedSummaryColor,
            spinnerColors.selectedContainerColor,
        )
    } else {
        Triple(
            spinnerColors.contentColor,
            spinnerColors.summaryColor,
            spinnerColors.containerColor,
        )
    }

    val selectColor = if (isSelected) spinnerColors.selectedIndicatorColor else Color.Transparent

    val itemModifier = Modifier
        .clickable { onSelectedIndexChange(index) }
        .background(backgroundColor)
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
        .padding(top = additionalTopPadding, bottom = additionalBottomPadding)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = itemModifier,
    ) {
        val contentRowModifier = if (dialogMode) Modifier else Modifier.widthIn(max = 216.dp)
        Row(
            modifier = contentRowModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
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
                        color = titleColor,
                    )
                }
                entry.summary?.let {
                    Text(
                        text = it,
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = summaryColor,
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

@Immutable
data class DropdownColors(
    val contentColor: Color,
    val containerColor: Color,
    val selectedContentColor: Color,
    val selectedContainerColor: Color,
)

object DropdownDefaults {

    @Composable
    fun dropdownColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        containerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        selectedContentColor: Color = MiuixTheme.colorScheme.primary,
        selectedContainerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
    ): DropdownColors = DropdownColors(
        contentColor = contentColor,
        containerColor = containerColor,
        selectedContentColor = selectedContentColor,
        selectedContainerColor = selectedContainerColor,
    )
}

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
    ): SpinnerColors = SpinnerColors(
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
    ): SpinnerColors = SpinnerColors(
        contentColor = contentColor,
        summaryColor = summaryColor,
        containerColor = containerColor,
        selectedContentColor = selectedContentColor,
        selectedSummaryColor = selectedSummaryColor,
        selectedContainerColor = selectedContainerColor,
        selectedIndicatorColor = selectedIndicatorColor,
    )
}

@Immutable
data class SpinnerColors(
    val contentColor: Color,
    val summaryColor: Color,
    val containerColor: Color,
    val selectedContentColor: Color,
    val selectedSummaryColor: Color,
    val selectedContainerColor: Color,
    val selectedIndicatorColor: Color,
)

/**
 * The spinner entry.
 */
data class SpinnerEntry(
    val icon: @Composable ((Modifier) -> Unit)? = null,
    val title: String? = null,
    val summary: String? = null,
)
