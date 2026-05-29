// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.preference

import androidx.annotation.IntRange
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.BasicComponentColors
import top.yukonga.miuix.kmp.basic.BasicComponentDefaults
import top.yukonga.miuix.kmp.basic.RangeSlider
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SliderColors
import top.yukonga.miuix.kmp.basic.SliderDefaults
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowRight
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A slider preference with a title and a summary.
 *
 * The [Slider] is placed in the [BasicComponent]'s bottom action area, displaying below the title and summary text.
 * This component is typically used in settings screens for value adjustment scenarios such as volume, brightness,
 * or font size controls.
 *
 * @param value The current value of the [Slider]. If outside of [valueRange] provided, value will be coerced to this range.
 * @param onValueChange The callback to be called when the value changes.
 * @param modifier The modifier to be applied to the [SliderPreference].
 * @param title The title of the [SliderPreference].
 * @param titleColor The color of the title.
 * @param summary The summary of the [SliderPreference].
 * @param summaryColor The color of the summary.
 * @param startAction The [Composable] content on the start side of the [SliderPreference].
 * @param valueText A nullable [String] representing the current slider value, displayed in the end area with summary-style formatting.
 *   If null, no value text is shown. The text is rendered inside the existing [Row] layout structure with [Alignment.CenterVertically]
 *   and [RowScope.weight] applied, consistent with the summary text style.
 * @param endActions The [Composable] content on the end side of the [SliderPreference], following the [valueText] within the same [Row].
 * @param bottomAction The [Composable] content at the top of the bottom area, above the [Slider].
 * @param onClick The callback triggered when the [SliderPreference] is clicked. When non-null, an arrow icon is displayed in the end area.
 * @param holdDownState Used to determine whether the component is in the pressed state.
 * @param enabled Whether the [SliderPreference] is enabled.
 * @param valueRange Range of values that this slider can take. The passed [value] will be coerced to this range.
 * @param steps If positive, specifies the amount of discrete allowable values between the endpoints of [valueRange].
 *   For example, a range from 0 to 10 with 4 [steps] allows 4 values evenly distributed between 0 and 10 (i.e., 2, 4, 6, 8).
 *   If [steps] is 0, the slider will behave continuously and allow any value from the range. Must not be negative.
 * @param onValueChangeFinished Called when value change has ended. This should not be used to update the slider value
 *   (use [onValueChange] instead), but rather to know when the user has completed selecting a new value by ending a drag or a click.
 * @param reverseDirection Controls the direction of this slider. When false (default), slider increases from left to right.
 *   When true, slider increases from right to left (useful for RTL layouts or custom direction requirements).
 * @param sliderHeight The height of the [Slider].
 * @param sliderColors The [SliderColors] of the [Slider].
 * @param hapticEffect The haptic effect of the [Slider].
 * @param showKeyPoints Whether to show the key points (step indicators) on the slider. Only works when [keyPoints] is not null.
 * @param keyPoints Custom key point values to display on the slider. If null, uses step positions from [steps] parameter.
 *   Values should be within [valueRange]. For example, for a range of 0f..100f, you might specify listOf(0f, 25f, 50f, 75f, 100f).
 * @param magnetThreshold The magnetic snap threshold as a fraction (0.0 to 1.0). When the slider value is within this
 *   distance from a key point, it will snap to that point. Default is 0.02 (2%). Only applies when [keyPoints] is set.
 * @param insideMargin The margin inside the [SliderPreference].
 */
@Composable
@NonRestartableComposable
fun SliderPreference(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    startAction: @Composable (() -> Unit)? = null,
    valueText: String? = null,
    endActions: @Composable (RowScope.() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    reverseDirection: Boolean = false,
    sliderHeight: Dp = SliderDefaults.MinHeight,
    sliderColors: SliderColors = SliderDefaults.sliderColors(),
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    showKeyPoints: Boolean = false,
    keyPoints: List<Float>? = null,
    magnetThreshold: Float = 0.02f,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
) {
    val currentOnValueChange by rememberUpdatedState(onValueChange)
    val currentOnValueChangeFinished by rememberUpdatedState(onValueChangeFinished)
    val showArrow = onClick != null
    val showEndArea = valueText != null || endActions != null || showArrow

    BasicComponent(
        modifier = modifier,
        insideMargin = insideMargin,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        startAction = startAction,
        endActions = if (showEndArea) {
            {
                Row(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterVertically)
                        .weight(1f, fill = false),
                ) {
                    if (valueText != null) {
                        Text(
                            text = valueText,
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = if (enabled) MiuixTheme.colorScheme.onSurfaceVariantActions else MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                        )
                    }
                    endActions?.invoke(this)
                }
                if (showArrow) {
                    SliderPreferenceArrowIcon(enabled = enabled)
                }
            }
        } else {
            null
        },
        bottomAction = {
            Column {
                bottomAction?.invoke()
                Slider(
                    value = value,
                    onValueChange = { currentOnValueChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    valueRange = valueRange,
                    steps = steps,
                    onValueChangeFinished = currentOnValueChangeFinished,
                    reverseDirection = reverseDirection,
                    height = sliderHeight,
                    colors = sliderColors,
                    hapticEffect = hapticEffect,
                    showKeyPoints = showKeyPoints,
                    keyPoints = keyPoints,
                    magnetThreshold = magnetThreshold,
                )
            }
        },
        onClick = onClick,
        holdDownState = holdDownState,
        enabled = enabled,
    )
}

/**
 * A range slider preference with a title and a summary.
 *
 * The [RangeSlider] is placed in the [BasicComponent]'s bottom action area, displaying below the title and summary text.
 * This component is typically used in settings screens for range selection scenarios such as price filters,
 * frequency bands, or dual-threshold controls.
 *
 * @param value Current values of the [RangeSlider]. If either value is outside of [valueRange] provided, it will be coerced to this range.
 * @param onValueChange Lambda in which values should be updated.
 * @param modifier The modifier to be applied to the [RangeSliderPreference].
 * @param title The title of the [RangeSliderPreference].
 * @param titleColor The color of the title.
 * @param summary The summary of the [RangeSliderPreference].
 * @param summaryColor The color of the summary.
 * @param startAction The [Composable] content on the start side of the [RangeSliderPreference].
 * @param valueText A nullable [String] representing the current slider value, displayed in the end area with summary-style formatting.
 *   If null, no value text is shown. The text is rendered inside the existing [Row] layout structure with [Alignment.CenterVertically]
 *   and [RowScope.weight] applied, consistent with the summary text style.
 * @param endActions The [Composable] content on the end side of the [RangeSliderPreference], following the [valueText] within the same [Row].
 * @param bottomAction The [Composable] content at the top of the bottom area, above the [RangeSlider].
 * @param onClick The callback triggered when the [RangeSliderPreference] is clicked. When non-null, an arrow icon is displayed in the end area.
 * @param holdDownState Used to determine whether the component is in the pressed state.
 * @param enabled Whether the [RangeSliderPreference] is enabled.
 * @param valueRange Range of values that [RangeSlider] values can take. Passed [value] will be coerced to this range.
 * @param steps If positive, specifies the amount of discrete allowable values between the endpoints of [valueRange].
 * @param onValueChangeFinished Lambda to be invoked when value change has ended.
 * @param sliderHeight The height of the [RangeSlider].
 * @param sliderColors The [SliderColors] of the [RangeSlider].
 * @param hapticEffect The haptic effect of the [RangeSlider].
 * @param showKeyPoints Whether to show the key points (step indicators) on the slider. Only works when [keyPoints] is not null.
 * @param keyPoints Custom key point values to display on the slider. If null, uses step positions from [steps] parameter.
 *   Values should be within [valueRange].
 * @param magnetThreshold The magnetic snap threshold as a fraction (0.0 to 1.0). When the slider value is within this
 *   distance from a key point, it will snap to that point. Default is 0.02 (2%). Only applies when [keyPoints] is set.
 * @param insideMargin The margin inside the [RangeSliderPreference].
 */
@Composable
@NonRestartableComposable
fun RangeSliderPreference(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: BasicComponentColors = BasicComponentDefaults.titleColor(),
    summary: String? = null,
    summaryColor: BasicComponentColors = BasicComponentDefaults.summaryColor(),
    startAction: @Composable (() -> Unit)? = null,
    valueText: String? = null,
    endActions: @Composable (RowScope.() -> Unit)? = null,
    bottomAction: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
    holdDownState: Boolean = false,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    sliderHeight: Dp = SliderDefaults.MinHeight,
    sliderColors: SliderColors = SliderDefaults.sliderColors(),
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    showKeyPoints: Boolean = false,
    keyPoints: List<Float>? = null,
    magnetThreshold: Float = 0.02f,
    insideMargin: PaddingValues = BasicComponentDefaults.InsideMargin,
) {
    val currentOnValueChange by rememberUpdatedState(onValueChange)
    val currentOnValueChangeFinished by rememberUpdatedState(onValueChangeFinished)
    val showArrow = onClick != null
    val showEndArea = valueText != null || endActions != null || showArrow

    BasicComponent(
        modifier = modifier,
        insideMargin = insideMargin,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        startAction = startAction,
        endActions = if (showEndArea) {
            {
                Row(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterVertically)
                        .weight(1f, fill = false),
                ) {
                    if (valueText != null) {
                        Text(
                            text = valueText,
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = if (enabled) MiuixTheme.colorScheme.onSurfaceVariantActions else MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                        )
                    }
                    endActions?.invoke(this)
                }
                if (showArrow) {
                    SliderPreferenceArrowIcon(enabled = enabled)
                }
            }
        } else {
            null
        },
        bottomAction = {
            Column {
                bottomAction?.invoke()
                RangeSlider(
                    value = value,
                    onValueChange = { currentOnValueChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    valueRange = valueRange,
                    steps = steps,
                    onValueChangeFinished = currentOnValueChangeFinished,
                    height = sliderHeight,
                    colors = sliderColors,
                    hapticEffect = hapticEffect,
                    showKeyPoints = showKeyPoints,
                    keyPoints = keyPoints,
                    magnetThreshold = magnetThreshold,
                )
            }
        },
        onClick = onClick,
        holdDownState = holdDownState,
        enabled = enabled,
    )
}

@Composable
private fun RowScope.SliderPreferenceArrowIcon(
    enabled: Boolean,
) {
    val actionColors = ArrowPreferenceDefaults.endActionColors()
    val tintFilter = remember(enabled, actionColors) {
        ColorFilter.tint(actionColors.color(enabled = enabled))
    }
    val layoutDirection = LocalLayoutDirection.current
    Image(
        modifier = Modifier
            .size(width = 10.dp, height = 16.dp)
            .graphicsLayer {
                scaleX = if (layoutDirection == LayoutDirection.Rtl) -1f else 1f
            }
            .align(Alignment.CenterVertically),
        imageVector = MiuixIcons.Basic.ArrowRight,
        contentDescription = null,
        colorFilter = tintFilter,
    )
}
