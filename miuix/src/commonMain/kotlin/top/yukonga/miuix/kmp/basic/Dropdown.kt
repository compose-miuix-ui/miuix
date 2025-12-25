// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowUpDown
import top.yukonga.miuix.kmp.icon.basic.Check
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun RowScope.DropdownRightActions(
    showValue: Boolean,
    itemsNotEmpty: Boolean,
    items: List<String>,
    selectedIndex: Int,
    actionColor: Color
) {
    if (showValue && itemsNotEmpty) {
        Text(
            modifier = Modifier.widthIn(max = 130.dp),
            text = items[selectedIndex],
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
        imageVector = MiuixIcons.Basic.ArrowUpDown,
        colorFilter = ColorFilter.tint(actionColor),
        contentDescription = null
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
    onSelectedIndexChange: (Int) -> Unit
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
                bottom = additionalBottomPadding
            )
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

@Immutable
data class DropdownColors(
    val contentColor: Color,
    val containerColor: Color,
    val selectedContentColor: Color,
    val selectedContainerColor: Color
)

object DropdownDefaults {

    @Composable
    fun dropdownColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        containerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        selectedContentColor: Color = MiuixTheme.colorScheme.primary,
        selectedContainerColor: Color = MiuixTheme.colorScheme.surfaceContainer
    ): DropdownColors {
        return DropdownColors(
            contentColor = contentColor,
            containerColor = containerColor,
            selectedContentColor = selectedContentColor,
            selectedContainerColor = selectedContainerColor
        )
    }
}
