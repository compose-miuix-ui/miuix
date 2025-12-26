// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.SwitchColors
import top.yukonga.miuix.kmp.basic.SwitchDefaults

/**
 * A switch with a title and a summary.
 *
 * @param checked The checked state of the [SuperSwitch].
 * @param onCheckedChange The callback when the checked state of the [SuperSwitch] is changed.
 * @param title The title of the [SuperSwitch].
 * @param titleColor The color of the title.
 * @param summary The summary of the [SuperSwitch].
 * @param summaryColor The color of the summary.
 * @param leftAction The [Composable] content that on the left side of the [SuperSwitch].
 * @param rightActions The [Composable] content on the right side of the [SuperSwitch].
 * @param bottomAction The [Composable] content at the bottom of the [SuperSwitch].
 * @param switchColors The [SwitchColors] of the [SuperSwitch].
 * @param modifier The modifier to be applied to the [SuperSwitch].
 * @param insideMargin The margin inside the [SuperSwitch].
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [SuperSwitch] is clickable.
 */
@Composable
@NonRestartableComposable
fun SuperSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    title: String,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    leftAction: @Composable (() -> Unit)? = null,
    rightActions: @Composable RowScope.() -> Unit = {},
    bottomAction: (@Composable () -> Unit)? = null,
    switchColors: SwitchColors = SwitchDefaults.switchColors(),
    modifier: Modifier = Modifier,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
) {
    val currentOnCheckedChange by rememberUpdatedState(onCheckedChange)
    BasicComponent(
        modifier = modifier,
        insideMargin = insideMargin,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        leftAction = leftAction,
        rightActions = {
            SuperSwitchRightActions(
                rightActions = rightActions,
                checked = checked,
                onCheckedChange = currentOnCheckedChange,
                enabled = enabled,
                switchColors = switchColors,
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
private fun RowScope.SuperSwitchRightActions(
    rightActions: @Composable RowScope.() -> Unit,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean,
    switchColors: SwitchColors,
) {
    rightActions()
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        colors = switchColors,
    )
}
