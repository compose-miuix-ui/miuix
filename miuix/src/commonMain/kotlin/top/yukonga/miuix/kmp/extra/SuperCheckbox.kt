// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.Checkbox
import top.yukonga.miuix.kmp.basic.CheckboxColors
import top.yukonga.miuix.kmp.basic.CheckboxDefaults

/**
 * A checkbox with a title and a summary.
 *
 * @param title The title of the [SuperCheckbox].
 * @param checked The checked state of the [SuperCheckbox].
 * @param onCheckedChange The callback when the checked state of the [SuperCheckbox] is changed.
 * @param modifier The modifier to be applied to the [SuperCheckbox].
 * @param titleColor The color of the title.
 * @param summary The summary of the [SuperCheckbox].
 * @param summaryColor The color of the summary.
 * @param checkboxColors The [CheckboxColors] of the [SuperCheckbox].
 * @param endActions The [Composable] content that on the end side of the [SuperCheckbox].
 * @param checkboxLocation The location of checkbox, [CheckboxLocation.Start] or [CheckboxLocation.End].
 * @param bottomAction The [Composable] content at the bottom of the [SuperCheckbox].
 * @param insideMargin The margin inside the [SuperCheckbox].
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [SuperCheckbox] is clickable.
 */
@Composable
@NonRestartableComposable
fun SuperCheckbox(
    title: String,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    checkboxColors: CheckboxColors = CheckboxDefaults.checkboxColors(),
    endActions: @Composable RowScope.() -> Unit = {},
    checkboxLocation: CheckboxLocation = CheckboxLocation.Start,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
) {
    val currentOnCheckedChange by rememberUpdatedState(onCheckedChange)
    val startActionComposable = if (checkboxLocation == CheckboxLocation.Start) {
        @Composable {
            SuperCheckboxStartAction(
                checked = checked,
                onCheckedChange = currentOnCheckedChange,
                enabled = enabled,
                checkboxColors = checkboxColors,
                insideMargin = insideMargin,
            )
        }
    } else {
        null
    }

    BasicComponent(
        modifier = modifier,
        insideMargin = insideMargin,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        leftAction = startActionComposable,
        rightActions = {
            SuperCheckboxRightActions(
                endActions = endActions,
                checkboxLocation = checkboxLocation,
                checked = checked,
                onCheckedChange = currentOnCheckedChange,
                enabled = enabled,
                checkboxColors = checkboxColors,
            )
        },
        bottomAction = bottomAction,
        onClick = {
            currentOnCheckedChange.takeIf { enabled }?.invoke(!checked)
        },
        holdDownState = holdDownState,
        enabled = enabled,
    )
}

@Composable
private fun SuperCheckboxStartAction(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean,
    checkboxColors: CheckboxColors,
    insideMargin: PaddingValues,
) {
    Checkbox(
        modifier = Modifier.padding(end = insideMargin.calculateLeftPadding(LayoutDirection.Ltr)),
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        colors = checkboxColors,
    )
}

@Composable
private fun RowScope.SuperCheckboxRightActions(
    endActions: @Composable RowScope.() -> Unit,
    checkboxLocation: CheckboxLocation,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean,
    checkboxColors: CheckboxColors,
) {
    endActions()
    if (checkboxLocation == CheckboxLocation.End) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = checkboxColors,
        )
    }
}

enum class CheckboxLocation {
    Start,
    End,
}
