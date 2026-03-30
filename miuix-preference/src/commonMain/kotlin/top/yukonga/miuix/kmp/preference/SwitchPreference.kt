// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.preference

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.SwitchColors
import top.yukonga.miuix.kmp.basic.SwitchDefaults

/**
 * A switch with a title and a summary.
 *
 * @param checked The checked state of the [SwitchPreference].
 * @param onCheckedChange The callback when the checked state of the [SwitchPreference] is changed.
 * @param title The title of the [SwitchPreference].
 * @param modifier The modifier to be applied to the [SwitchPreference].
 * @param titleColor The color of the title.
 * @param summary The summary of the [SwitchPreference].
 * @param summaryColor The color of the summary.
 * @param startAction The [Composable] content that on the start side of the [SwitchPreference].
 * @param endActions The [Composable] content on the end side of the [SwitchPreference].
 * @param bottomAction The [Composable] content at the bottom of the [SwitchPreference].
 * @param switchColors The [SwitchColors] of the [SwitchPreference].
 * @param insideMargin The margin inside the [SwitchPreference].
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [SwitchPreference] is clickable.
 */
@Composable
@NonRestartableComposable
fun SwitchPreference(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    title: String,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    startAction: @Composable (() -> Unit)? = null,
    endActions: @Composable RowScope.() -> Unit = {},
    bottomAction: (@Composable () -> Unit)? = null,
    switchColors: SwitchColors = SwitchDefaults.switchColors(),
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
        startAction = startAction,
        endActions = {
            Row(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1f, fill = false),
            ) {
                endActions()
            }
            SwitchPreferenceEndActions(
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
private fun SwitchPreferenceEndActions(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean,
    switchColors: SwitchColors,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        colors = switchColors,
    )
}
