// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.preference

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.RadioButton
import top.yukonga.miuix.kmp.basic.RadioButtonColors
import top.yukonga.miuix.kmp.basic.RadioButtonDefaults
import top.yukonga.miuix.kmp.preference.internal.StartActionSlot
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A radio button with a title and a summary.
 *
 * @param title The title of the [RadioButtonPreference].
 * @param selected The selected state of the [RadioButtonPreference].
 * @param onClick The callback when the [RadioButtonPreference] is clicked.
 * @param modifier The modifier to be applied to the [RadioButtonPreference].
 * @param summary The summary of the [RadioButtonPreference].
 * @param colors The [RadioButtonPreferenceColors] of the title and summary.
 * @param radioButtonColors The [RadioButtonColors] of the [RadioButtonPreference].
 * @param startAction The [Composable] content on the start side of the [RadioButtonPreference].
 * @param endActions The [Composable] content on the end side of the [RadioButtonPreference].
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
    summary: String? = null,
    colors: RadioButtonPreferenceColors = RadioButtonPreferenceDefaults.radioButtonPreferenceColors(),
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.radioButtonColors(),
    startAction: @Composable (() -> Unit)? = null,
    endActions: @Composable (RowScope.() -> Unit)? = null,
    radioButtonLocation: RadioButtonLocation = RadioButtonLocation.Start,
    bottomAction: (@Composable () -> Unit)? = null,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
) {
    val currentOnClick by rememberUpdatedState(onClick)
    val hapticFeedback = LocalHapticFeedback.current
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)

    BasicComponent(
        modifier = modifier,
        insideMargin = insideMargin,
        title = title,
        titleColor = colors.titleColor(selected),
        summary = summary,
        summaryColor = colors.summaryColor(selected),
        startAction = if (radioButtonLocation == RadioButtonLocation.Start || startAction != null) {
            {
                Row {
                    if (radioButtonLocation == RadioButtonLocation.Start) {
                        RadioButton(
                            modifier = Modifier.padding(end = 5.dp),
                            selected = selected,
                            onClick = null,
                            enabled = enabled,
                            colors = radioButtonColors,
                        )
                    }

                    startAction?.let {
                        StartActionSlot(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f, fill = false),
                            endSpacing = 5.dp,
                            content = it,
                        )
                    }
                }
            }
        } else {
            null
        },
        endActions = {
            endActions?.let {
                Row(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterVertically)
                        .weight(1f, fill = false),
                ) {
                    it()
                }
            }

            if (radioButtonLocation == RadioButtonLocation.End) {
                RadioButton(
                    selected = selected,
                    onClick = null,
                    enabled = enabled,
                    colors = radioButtonColors,
                )
            }
        },
        bottomAction = bottomAction,
        onClick = {
            currentOnClick.takeIf { enabled }?.let { onClick ->
                onClick()
                currentHapticFeedback.performHapticFeedback(
                    if (selected) HapticFeedbackType.ToggleOff else HapticFeedbackType.ToggleOn,
                )
            }
        },
        role = Role.RadioButton,
        holdDownState = holdDownState,
        enabled = enabled,
    )
}

object RadioButtonPreferenceDefaults {
    /**
     * The default colors of the title and summary.
     */
    @Composable
    fun radioButtonPreferenceColors(
        titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
        selectedTitleColor: BasicComponentColors = BasicComponentDefaults.titleColor(color = MiuixTheme.colorScheme.primary),
        summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
        selectedSummaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(color = MiuixTheme.colorScheme.primary),
    ): RadioButtonPreferenceColors = remember(titleColor, selectedTitleColor, summaryColor, selectedSummaryColor) {
        RadioButtonPreferenceColors(
            titleColor = titleColor,
            selectedTitleColor = selectedTitleColor,
            summaryColor = summaryColor,
            selectedSummaryColor = selectedSummaryColor,
        )
    }
}

@Immutable
data class RadioButtonPreferenceColors(
    private val titleColor: BasicComponentColors,
    private val selectedTitleColor: BasicComponentColors,
    private val summaryColor: BasicComponentColors,
    private val selectedSummaryColor: BasicComponentColors,
) {
    @Stable
    internal fun titleColor(selected: Boolean): BasicComponentColors = if (selected) selectedTitleColor else titleColor

    @Stable
    internal fun summaryColor(selected: Boolean): BasicComponentColors = if (selected) selectedSummaryColor else summaryColor
}

enum class RadioButtonLocation {
    Start,
    End,
}
