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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.RadioButton
import top.yukonga.miuix.kmp.basic.RadioButtonColors
import top.yukonga.miuix.kmp.basic.RadioButtonDefaults

/**
 * A radio button with a title and a summary.
 *
 * @param title The title of the [RadioButtonPreference].
 * @param selected The selected state of the [RadioButtonPreference].
 * @param onClick The callback when the [RadioButtonPreference] is clicked.
 * @param modifier The modifier to be applied to the [RadioButtonPreference].
 * @param titleColor The color of the title.
 * @param summary The summary of the [RadioButtonPreference].
 * @param summaryColor The color of the summary.
 * @param radioButtonColors The [RadioButtonColors] of the [RadioButtonPreference].
 * @param endActions The [Composable] content that on the end side of the [RadioButtonPreference].
 * @param radioButtonLocation The location of radio button, [RadioButtonLocation.Start] or [RadioButtonLocation.End].
 * @param bottomAction The [Composable] content at the bottom of the [RadioButtonPreference].
 * @param insideMargin The margin inside the [RadioButtonPreference].
 * @param holdDownState Used to determine whether it is in the pressed state.
 * @param enabled Whether the [RadioButtonPreference] is clickable.
 */
@Composable
@NonRestartableComposable
fun RadioButtonPreference(
    title: String,
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.radioButtonColors(),
    endActions: @Composable RowScope.() -> Unit = {},
    radioButtonLocation: RadioButtonLocation = RadioButtonLocation.Start,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
) {
    val currentOnClick by rememberUpdatedState(onClick)
    val startAction = if (radioButtonLocation == RadioButtonLocation.Start) {
        @Composable {
            RadioButtonPreferenceStartAction(
                selected = selected,
                onClick = currentOnClick,
                enabled = enabled,
                radioButtonColors = radioButtonColors,
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
            if (radioButtonLocation == RadioButtonLocation.End) {
                RadioButtonPreferenceEndAction(
                    selected = selected,
                    onClick = currentOnClick,
                    enabled = enabled,
                    radioButtonColors = radioButtonColors,
                )
            }
        },
        bottomAction = bottomAction,
        onClick = {
            currentOnClick.takeIf { enabled }?.invoke()
        },
        holdDownState = holdDownState,
        enabled = enabled,
    )
}

@Composable
private fun RadioButtonPreferenceStartAction(
    selected: Boolean,
    onClick: (() -> Unit)?,
    enabled: Boolean,
    radioButtonColors: RadioButtonColors,
) {
    val currentOnClick by rememberUpdatedState(onClick)
    val wrappedOnClick = remember(onClick != null) {
        if (onClick != null) {
            { currentOnClick?.invoke() ?: Unit }
        } else {
            null
        }
    }
    RadioButton(
        modifier = Modifier
            .padding(end = 8.dp),
        selected = selected,
        onClick = wrappedOnClick,
        enabled = enabled,
        colors = radioButtonColors,
    )
}

@Composable
private fun RadioButtonPreferenceEndAction(
    selected: Boolean,
    onClick: (() -> Unit)?,
    enabled: Boolean,
    radioButtonColors: RadioButtonColors,
) {
    val currentOnClick by rememberUpdatedState(onClick)
    val wrappedOnClick = remember(onClick != null) {
        if (onClick != null) {
            { currentOnClick?.invoke() ?: Unit }
        } else {
            null
        }
    }
    RadioButton(
        selected = selected,
        onClick = wrappedOnClick,
        enabled = enabled,
        colors = radioButtonColors,
    )
}

enum class RadioButtonLocation {
    Start,
    End,
}
