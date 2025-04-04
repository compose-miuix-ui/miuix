package top.yukonga.miuix.kmp.extra

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlendModeColorFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.ListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.basic.ArrowUpDownIntegrated
import top.yukonga.miuix.kmp.icon.icons.basic.Check
import top.yukonga.miuix.kmp.interfaces.HoldDownInteraction
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.dismissPopup

/**
 * A dropdown with a title and a summary.
 *
 * @param title The title of the [SuperDropdown].
 * @param items The options of the [SuperDropdown].
 * @param selectedIndex The index of the selected option.
 * @param modifier The modifier to be applied to the [SuperDropdown].
 * @param titleColor The color of the title.
 * @param summary The summary of the [SuperDropdown].
 * @param summaryColor The color of the summary.
 * @param mode The dropdown show mode of the [SuperDropdown].
 * @param insideMargin The margin inside the [SuperDropdown].
 * @param maxHeight The maximum height of the [ListPopup].
 * @param enabled Whether the [SuperDropdown] is enabled.
 * @param showValue Whether to show the selected value of the [SuperDropdown].
 * @param onClick The callback when the [SuperDropdown] is clicked.
 * @param onSelectedIndexChange The callback when the selected index of the [SuperDropdown] is changed.
 */
@Composable
fun SuperDropdown(
    title: String,
    items: List<String>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    mode: DropDownMode = DropDownMode.Normal,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    maxHeight: Dp? = null,
    enabled: Boolean = true,
    showValue: Boolean = true,
    dropdownColors: DropdownColors = DropdownDefaults.dropdownColors(),
    onClick: (() -> Unit)? = null,
    onSelectedIndexChange: ((Int) -> Unit)?,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isDropdownExpanded = remember { mutableStateOf(false) }
    val showPopup = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val isHoldDown = remember { mutableStateOf<HoldDownInteraction.HoldDown?>(null) }
    val hapticFeedback = LocalHapticFeedback.current
    val actionColor =
        if (enabled) MiuixTheme.colorScheme.onSurfaceVariantActions else MiuixTheme.colorScheme.disabledOnSecondaryVariant

    var alignLeft by rememberSaveable { mutableStateOf(true) }

    DisposableEffect(Unit) {
        onDispose {
            dismissPopup(showPopup)
            isDropdownExpanded.value = false
        }
    }

    if (!isDropdownExpanded.value) {
        isHoldDown.value?.let { oldValue ->
            coroutineScope.launch {
                interactionSource.emit(HoldDownInteraction.Release(oldValue))
            }
            isHoldDown.value = null
        }
    }

    BasicComponent(
        modifier = modifier
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (enabled) {
                        val event = awaitPointerEvent()
                        if (event.type != PointerEventType.Move) {
                            val eventChange = event.changes.first()
                            if (eventChange.pressed) {
                                alignLeft = eventChange.position.x < (size.width / 2)
                            }
                        }
                    }
                }
            },
        interactionSource = interactionSource,
        insideMargin = insideMargin,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        leftAction = {
            if (isDropdownExpanded.value) {
                ListPopup(
                    show = showPopup,
                    alignment = if ((mode == DropDownMode.AlwaysOnRight || !alignLeft))
                        PopupPositionProvider.Align.Right
                    else
                        PopupPositionProvider.Align.Left,
                    onDismissRequest = {
                        isDropdownExpanded.value = false
                    },
                    maxHeight = maxHeight
                ) {
                    ListPopupColumn {
                        items.forEachIndexed { index, string ->
                            DropdownImpl(
                                text = string,
                                optionSize = items.size,
                                isSelected = selectedIndex == index,
                                dropdownColors = dropdownColors,
                                onSelectedIndexChange = {
                                    hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                                    onSelectedIndexChange?.let { it1 -> it1(it) }
                                    dismissPopup(showPopup)
                                    isDropdownExpanded.value = false
                                },
                                index = index
                            )
                        }
                    }
                }
                showPopup.value = true
            }
        },
        rightActions = {
            if (showValue) {
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
                imageVector = MiuixIcons.Basic.ArrowUpDownIntegrated,
                colorFilter = ColorFilter.tint(actionColor),
                contentDescription = null
            )
        },
        onClick = {
            if (enabled) {
                onClick?.invoke()
                isDropdownExpanded.value = enabled
                hapticFeedback.performHapticFeedback(HapticFeedbackType.ContextClick)
                coroutineScope.launch {
                    interactionSource.emit(HoldDownInteraction.HoldDown().also {
                        isHoldDown.value = it
                    })
                }
            }
        },
        enabled = enabled
    )
}

@Immutable
class DropdownColors(
    val contentColor: Color,
    val containerColor: Color,
    val selectedContentColor: Color,
    val selectedContainerColor: Color
)

object DropdownDefaults {

    @Composable
    fun dropdownColors(
        contentColor: Color = MiuixTheme.colorScheme.onSurface,
        containerColor: Color = MiuixTheme.colorScheme.surface,
        selectedContentColor: Color = MiuixTheme.colorScheme.onTertiaryContainer,
        selectedContainerColor: Color = MiuixTheme.colorScheme.tertiaryContainer
    ): DropdownColors {
        return DropdownColors(
            contentColor = contentColor,
            containerColor = containerColor,
            selectedContentColor = selectedContentColor,
            selectedContainerColor = selectedContainerColor
        )
    }
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
    val additionalTopPadding = if (index == 0) 20f.dp else 12f.dp
    val additionalBottomPadding = if (index == optionSize - 1) 20f.dp else 12f.dp
    val textColor = if (isSelected) {
        dropdownColors.selectedContentColor
    } else {
        dropdownColors.contentColor
    }
    val backgroundColor = if (isSelected) {
        dropdownColors.selectedContainerColor
    } else {
        dropdownColors.containerColor
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .clickable {
                onSelectedIndexChange(index)
            }
            .background(backgroundColor)
            .padding(horizontal = 20.dp)
            .padding(top = additionalTopPadding, bottom = additionalBottomPadding)
    ) {
        Text(
            modifier = Modifier.widthIn(max = 216.dp),
            text = text,
            fontSize = MiuixTheme.textStyles.body1.fontSize,
            fontWeight = FontWeight.Medium,
            color = textColor,
        )
        if (isSelected) {
            Image(
                modifier = Modifier.padding(start = 12.dp).size(20.dp),
                imageVector = MiuixIcons.Basic.Check,
                colorFilter = BlendModeColorFilter(
                    dropdownColors.selectedContentColor,
                    BlendMode.SrcIn
                ),
                contentDescription = null,
            )
        }
    }
}

/**
 * The dropdown show mode.
 */
enum class DropDownMode {
    Normal,
    AlwaysOnRight
}