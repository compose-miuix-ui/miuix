// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.NonRestartableComposable
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.CallOutMenu
import top.yukonga.miuix.kmp.icon.symbol.Next
import top.yukonga.miuix.kmp.icon.symbol.Ok
import top.yukonga.miuix.kmp.symbol.SymbolWeights
import top.yukonga.miuix.kmp.symbol.rememberSymbolPainter
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
@NonRestartableComposable
fun RowScope.DropdownArrowEndAction(
    actionColor: Color,
    modifier: Modifier = Modifier,
) {
    val colorFilter = remember(actionColor) { ColorFilter.tint(actionColor) }
    Image(
        modifier = modifier
            .size(width = DropdownDefaults.ArrowSize.width, height = DropdownDefaults.ArrowSize.height)
            .align(Alignment.CenterVertically)
            .wrapContentSize(unbounded = true)
            .size(width = 14.dp, height = 32.dp),
        painter = rememberSymbolPainter(MiuixIcons.Symbol.CallOutMenu),
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
 * @param hasSubmenu When true, this row acts as a submenu trigger: a trailing chevron is shown
 *   instead of the selection check, and the row's accessibility role becomes [Role.Button].
 * @param isFirst Whether this row is the first row of the entire popup. Controls the larger top
 *   padding in popup mode. Defaults to `index == 0`; multi-entry callers should pass the
 *   popup-global flag so only the actual first row gets extra padding.
 * @param isLast Whether this row is the last row of the entire popup. Controls the larger bottom
 *   padding in popup mode. Defaults to `index == optionSize - 1`; multi-entry callers should pass
 *   the popup-global flag.
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
    hasSubmenu: Boolean = false,
    isFirst: Boolean = index == 0,
    isLast: Boolean = index == optionSize - 1,
    onSelectedIndexChange: (Int) -> Unit,
) {
    val additionalTopPadding =
        if (!dialogMode && isFirst) {
            DropdownDefaults.FirstLastVerticalPadding
        } else {
            DropdownDefaults.MiddleVerticalPadding
        }
    val additionalBottomPadding =
        if (!dialogMode && isLast) {
            DropdownDefaults.FirstLastVerticalPadding
        } else {
            DropdownDefaults.MiddleVerticalPadding
        }

    val backgroundColor = if (isSelected) {
        dropdownColors.selectedContainerColor
    } else {
        dropdownColors.containerColor
    }
    val backgroundColorState = rememberUpdatedState(backgroundColor)

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

    val containerModifier = remember(dialogMode, additionalTopPadding, additionalBottomPadding) {
        val sized = if (dialogMode) {
            Modifier
                .heightIn(min = DropdownDefaults.MinHeight)
                .widthIn(min = DropdownDefaults.MinWidth)
                .fillMaxWidth()
                .padding(horizontal = DropdownDefaults.DialogHorizontalPadding)
        } else {
            Modifier.padding(horizontal = DropdownDefaults.InsideHorizontalPadding)
        }
        sized.padding(top = additionalTopPadding, bottom = additionalBottomPadding)
    }
    val innerRowModifier = remember(dialogMode) {
        if (dialogMode) Modifier else Modifier.widthIn(max = DropdownDefaults.MaxItemTextWidth)
    }

    val currentOnSelectedIndexChange by rememberUpdatedState(onSelectedIndexChange)
    val role = if (hasSubmenu) Role.Button else Role.RadioButton
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .drawBehind { drawRect(backgroundColorState.value) }
            .selectable(
                selected = isSelected,
                enabled = enabled,
                role = role,
                onClick = { currentOnSelectedIndexChange(index) },
            )
            .then(containerModifier),
    ) {
        Row(
            modifier = innerRowModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            item.icon?.let { it(IconCellModifier) }
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

        if (hasSubmenu) {
            // Chevron uses the softer summaryColor so the trigger row doesn't visually
            // compete with selected/leaf items. The chevron is intentionally static —
            // only the cloned header in the secondary popup rotates during expansion.
            val chevronColor = when {
                !enabled -> MiuixTheme.colorScheme.disabledOnSecondaryVariant
                isSelected -> dropdownColors.selectedContentColor
                else -> dropdownColors.summaryColor
            }
            val chevronColorFilter = remember(chevronColor) {
                BlendModeColorFilter(chevronColor, BlendMode.SrcIn)
            }
            Image(
                modifier = ChevronIconBaseModifier,
                painter = rememberSymbolPainter(MiuixIcons.Symbol.Next),
                colorFilter = chevronColorFilter,
                contentDescription = null,
            )
        } else {
            val checkColorFilter = remember(checkColor) {
                BlendModeColorFilter(checkColor, BlendMode.SrcIn)
            }
            Image(
                modifier = CheckIconBaseModifier,
                painter = rememberSymbolPainter(MiuixIcons.Symbol.Ok, weight = SymbolWeights.Max),
                colorFilter = checkColorFilter,
                contentDescription = null,
            )
        }
    }
}

@Composable
@NonRestartableComposable
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
@Deprecated(
    message = "Use DropdownImpl instead. SpinnerItemImpl is a thin alias kept for compatibility.",
    replaceWith = ReplaceWith(
        "DropdownImpl(item = entry, optionSize = entryCount, isSelected = isSelected, " +
            "index = index, dropdownColors = spinnerColors, enabled = entry.enabled, " +
            "dialogMode = dialogMode, onSelectedIndexChange = onSelectedIndexChange)",
    ),
    level = DeprecationLevel.WARNING,
)
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
class DropdownEntry(
    items: List<DropdownItem>,
    val enabled: Boolean = true,
) {
    val items: List<DropdownItem> = if (items.isEmpty()) emptyList() else items.toList()

    fun copy(
        items: List<DropdownItem> = this.items,
        enabled: Boolean = this.enabled,
    ): DropdownEntry = DropdownEntry(
        items = items,
        enabled = enabled,
    )

    operator fun component1(): List<DropdownItem> = items
    operator fun component2(): Boolean = enabled

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DropdownEntry) return false
        return items == other.items && enabled == other.enabled
    }

    override fun hashCode(): Int {
        var result = items.hashCode()
        result = 31 * result + enabled.hashCode()
        return result
    }

    override fun toString(): String = "DropdownEntry(items=$items, enabled=$enabled)"
}

/**
 * An item shown inside a dropdown, spinner, or dropdown menu.
 *
 * @param text Text shown for the item.
 * @param enabled Whether the item can be clicked.
 * @param selected Whether the item is selected.
 * @param onClick Callback invoked when the item is clicked. Ignored when [children] is non-null
 *   and non-empty (the click is consumed by the cascading layer to expand the submenu).
 * @param icon Optional icon shown before [text].
 * @param summary Optional summary shown below [text].
 * @param children Optional submenu items. When non-null and non-empty, this item becomes a
 *   submenu trigger: cascading dropdown popups render a chevron and open a child popup on click,
 *   recursively. Stabilize this list (e.g. via `remember`) to avoid unnecessary recomposition.
 */
@Stable
class DropdownItem(
    val text: String,
    val enabled: Boolean = true,
    val selected: Boolean = false,
    val onClick: (() -> Unit)? = null,
    val icon: @Composable ((Modifier) -> Unit)? = null,
    val summary: String? = null,
    children: List<DropdownItem>? = null,
) {
    val children: List<DropdownItem>? = children?.let { if (it.isEmpty()) emptyList() else it.toList() }

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

    fun copy(
        text: String = this.text,
        enabled: Boolean = this.enabled,
        selected: Boolean = this.selected,
        onClick: (() -> Unit)? = this.onClick,
        icon: @Composable ((Modifier) -> Unit)? = this.icon,
        summary: String? = this.summary,
        children: List<DropdownItem>? = this.children,
    ): DropdownItem = DropdownItem(
        text = text,
        enabled = enabled,
        selected = selected,
        onClick = onClick,
        icon = icon,
        summary = summary,
        children = children,
    )

    operator fun component1(): String = text
    operator fun component2(): Boolean = enabled
    operator fun component3(): Boolean = selected
    operator fun component4(): (() -> Unit)? = onClick
    operator fun component5(): @Composable ((Modifier) -> Unit)? = icon
    operator fun component6(): String? = summary
    operator fun component7(): List<DropdownItem>? = children

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is DropdownItem) return false
        return text == other.text &&
            enabled == other.enabled &&
            selected == other.selected &&
            onClick == other.onClick &&
            icon == other.icon &&
            summary == other.summary &&
            children == other.children
    }

    override fun hashCode(): Int {
        var result = text.hashCode()
        result = 31 * result + enabled.hashCode()
        result = 31 * result + selected.hashCode()
        result = 31 * result + (onClick?.hashCode() ?: 0)
        result = 31 * result + (icon?.hashCode() ?: 0)
        result = 31 * result + (summary?.hashCode() ?: 0)
        result = 31 * result + (children?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "DropdownItem(text=$text, enabled=$enabled, selected=$selected, onClick=$onClick, icon=$icon, summary=$summary, children=$children)"
}

object DropdownDefaults {
    /** Minimum row height when the dropdown is shown in dialog mode. */
    val MinHeight: Dp = 56.dp

    /** Minimum row width when the dropdown is shown in dialog mode. */
    val MinWidth: Dp = 200.dp

    /** Size of the trailing check icon shown on the selected option. */
    val CheckIconSize: Dp = 16.dp

    /** Size of the up-down arrow rendered by [DropdownArrowEndAction]. */
    val ArrowSize: DpSize = DpSize(width = 10.dp, height = 16.dp)

    /** Size of the trailing chevron shown on rows that own a submenu. */
    val ChevronSize: DpSize = DpSize(width = 16.dp, height = 32.dp)

    /** Minimum size of the leading icon cell. */
    val IconMinSize: Dp = 26.dp

    /** Maximum width of the inner text/icon row when the dropdown is shown in popup mode. */
    val MaxItemTextWidth: Dp = 216.dp

    /** Horizontal padding of each row in popup mode. */
    val InsideHorizontalPadding: Dp = 20.dp

    /** Horizontal padding of each row in dialog mode. */
    val DialogHorizontalPadding: Dp = 28.dp

    /** Top/bottom padding applied to the first/last row in popup mode. */
    val FirstLastVerticalPadding: Dp = 20.dp

    /** Top/bottom padding applied to middle rows in popup mode and to all rows in dialog mode. */
    val MiddleVerticalPadding: Dp = 12.dp

    /** Padding between the leading icon cell and the title text. */
    val IconEndPadding: Dp = 12.dp

    /** Padding between the title/summary block and the trailing check icon. */
    val CheckIconStartPadding: Dp = 12.dp

    @Composable
    fun dropdownColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        summaryColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        containerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        selectedContentColor: Color = MiuixTheme.colorScheme.primary,
        selectedSummaryColor: Color = MiuixTheme.colorScheme.primary,
        selectedContainerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        selectedIndicatorColor: Color = MiuixTheme.colorScheme.primary,
    ): DropdownColors = rememberDropdownColorsImpl(
        contentColor = contentColor,
        summaryColor = summaryColor,
        containerColor = containerColor,
        selectedContentColor = selectedContentColor,
        selectedSummaryColor = selectedSummaryColor,
        selectedContainerColor = selectedContainerColor,
        selectedIndicatorColor = selectedIndicatorColor,
    )

    @Composable
    fun dialogDropdownColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        summaryColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        containerColor: Color = Color.Transparent,
        selectedContentColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedSummaryColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedContainerColor: Color = MiuixTheme.colorScheme.tertiaryContainer,
        selectedIndicatorColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
    ): DropdownColors = rememberDropdownColorsImpl(
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
private fun rememberDropdownColorsImpl(
    contentColor: Color,
    summaryColor: Color,
    containerColor: Color,
    selectedContentColor: Color,
    selectedSummaryColor: Color,
    selectedContainerColor: Color,
    selectedIndicatorColor: Color,
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

private val CheckIconBaseModifier = Modifier
    .padding(start = DropdownDefaults.CheckIconStartPadding)
    .size(DropdownDefaults.CheckIconSize)

private val ChevronIconBaseModifier = Modifier
    .padding(start = DropdownDefaults.CheckIconStartPadding)
    .size(width = DropdownDefaults.ChevronSize.width, height = DropdownDefaults.ChevronSize.height)

private val IconCellModifier = Modifier
    .sizeIn(minWidth = DropdownDefaults.IconMinSize, minHeight = DropdownDefaults.IconMinSize)
    .padding(end = DropdownDefaults.IconEndPadding)

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
