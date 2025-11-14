// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.annotation.IntRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.abs

/**
 * A [Slider] component with Miuix style.
 *
 * @param value The current value of the [Slider]. If outside of [valueRange] provided, value will be coerced to this range.
 * @param onValueChange The callback to be called when the value changes.
 * @param modifier The modifier to be applied to the [Slider].
 * @param enabled Whether the [Slider] is enabled.
 * @param valueRange Range of values that this slider can take. The passed [value] will be coerced to this range.
 * @param steps If positive, specifies the amount of discrete allowable values between the endpoints of [valueRange].
 *   For example, a range from 0 to 10 with 4 [steps] allows 4 values evenly distributed between 0 and 10 (i.e., 2, 4, 6, 8).
 *   If [steps] is 0, the slider will behave continuously and allow any value from the range. Must not be negative.
 * @param onValueChangeFinished Called when value change has ended. This should not be used to update the slider value
 *   (use [onValueChange] instead), but rather to know when the user has completed selecting a new value by ending a drag or a click.
 * @param reverseDirection Controls the direction of this slider. When false (default), slider increases from left to right.
 *   When true, slider increases from right to left (useful for RTL layouts or custom direction requirements).
 * @param height The height of the [Slider].
 * @param colors The [SliderColors] of the [Slider].
 * @param effect Whether to show the effect of the [Slider].
 * @param hapticEffect The haptic effect of the [Slider].
 * @param showKeyPoints Whether to show the key points (step indicators) on the slider. Only works when [keyPoints] is not null.
 * @param keyPoints Custom key point values to display on the slider. If null, uses step positions from [steps] parameter.
 *   Values should be within [valueRange]. For example, for a range of 0f..100f, you might specify listOf(0f, 25f, 50f, 75f, 100f).
 * @param magnetThreshold The magnetic snap threshold as a fraction (0.0 to 1.0). When the slider value is within this
 *   distance from a key point, it will snap to that point. Default is 0.02 (2%). Only applies when [keyPoints] is set.
 */
@Composable
fun Slider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    reverseDirection: Boolean = false,
    height: Dp = SliderDefaults.MinHeight,
    colors: SliderColors = SliderDefaults.sliderColors(),
    effect: Boolean = false,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    showKeyPoints: Boolean = false,
    keyPoints: List<Float>? = null,
    magnetThreshold: Float = 0.02f
) {
    require(steps >= 0) { "steps should be >= 0" }
    require(valueRange.start < valueRange.endInclusive) { "valueRange start should be less than end" }

    val hapticFeedback = LocalHapticFeedback.current
    var dragOffset by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    val hapticState = remember { SliderHapticState() }
    val interactionSource = remember { MutableInteractionSource() }
    val shape = remember(height) { ContinuousRoundedRectangle(height) }

    val coercedValue = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val stepFractions = remember(steps) { stepsToTickFractions(steps) }

    val keyPointFractions = remember(keyPoints, stepFractions, valueRange, showKeyPoints) {
        computeKeyPointFractions(keyPoints, stepFractions, valueRange, showKeyPoints)
    }

    val allKeyPointFractions = remember(keyPoints, stepFractions, valueRange) {
        computeAllKeyPointFractions(keyPoints, stepFractions, valueRange)
    }

    val fractionToValue = remember(valueRange, steps, stepFractions, allKeyPointFractions, magnetThreshold) {
        { fraction: Float ->
            val f = fraction.coerceIn(0f, 1f)
            val newValue = lerp(valueRange.start, valueRange.endInclusive, f)
            when {
                steps > 0 -> snapValueToTick(newValue, stepFractions, valueRange.start, valueRange.endInclusive)
                allKeyPointFractions.isNotEmpty() -> {
                    val closestKeyPoint = allKeyPointFractions.minByOrNull { abs(it - f) }
                    if (closestKeyPoint != null && abs(f - closestKeyPoint) < magnetThreshold) {
                        lerp(valueRange.start, valueRange.endInclusive, closestKeyPoint)
                    } else newValue
                }

                else -> newValue
            }
        }
    }

    Box(
        modifier = modifier
            .then(
                if (enabled) {
                    Modifier
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures(
                                onDragStart = { offset ->
                                    isDragging = true
                                    dragOffset = offset.x

                                    val thumbRadius = size.height / 2f
                                    val availableWidth = (size.width - 2f * thumbRadius).coerceAtLeast(0f)
                                    val visualFraction =
                                        if (availableWidth == 0f) 0f else ((offset.x - thumbRadius) / availableWidth).coerceIn(
                                            0f,
                                            1f
                                        )
                                    val fractionForValue = if (reverseDirection) 1f - visualFraction else visualFraction
                                    val calculatedValue = fractionToValue(fractionForValue)
                                    onValueChange(calculatedValue)
                                    hapticState.reset(calculatedValue)
                                },
                                onHorizontalDrag = { _, dragAmount ->
                                    dragOffset = (dragOffset + dragAmount).coerceIn(0f, size.width.toFloat())

                                    val thumbRadius = size.height / 2f
                                    val availableWidth = (size.width - 2f * thumbRadius).coerceAtLeast(0f)
                                    val visualFraction =
                                        if (availableWidth == 0f) 0f else ((dragOffset - thumbRadius) / availableWidth).coerceIn(
                                            0f,
                                            1f
                                        )
                                    val fractionForValue = if (reverseDirection) 1f - visualFraction else visualFraction
                                    val calculatedValue = fractionToValue(fractionForValue)
                                    onValueChange(calculatedValue)
                                    hapticState.handleHapticFeedback(
                                        calculatedValue,
                                        valueRange,
                                        hapticEffect,
                                        hapticFeedback,
                                        allKeyPointFractions,
                                        hasCustomKeyPoints = keyPoints != null
                                    )
                                },
                                onDragEnd = {
                                    isDragging = false
                                    onValueChangeFinished?.invoke()
                                }
                            )
                        }
                        .indication(interactionSource, LocalIndication.current)
                } else Modifier
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        SliderTrack(
            modifier = Modifier.fillMaxWidth().height(height),
            shape = shape,
            backgroundColor = colors.backgroundColor(),
            foregroundColor = colors.foregroundColor(enabled),
            effect = effect,
            value = coercedValue,
            valueRange = valueRange,
            isDragging = isDragging,
            isVertical = false,
            reverseDirection = reverseDirection,
            showKeyPoints = showKeyPoints,
            stepFractions = keyPointFractions,
            keyPointColor = colors.keyPointColor(),
            keyPointForegroundColor = colors.keyPointForegroundColor()
        )
    }
}

/**
 * A vertical [Slider] component with Miuix style.
 *
 * @param value The current value of the [Slider]. If outside of [valueRange] provided, value will be coerced to this range.
 * @param onValueChange The callback to be called when the value changes.
 * @param modifier The modifier to be applied to the [Slider].
 * @param enabled Whether the [Slider] is enabled.
 * @param valueRange Range of values that this slider can take. The passed [value] will be coerced to this range.
 * @param steps If positive, specifies the amount of discrete allowable values between the endpoints of [valueRange].
 * @param onValueChangeFinished Called when value change has ended.
 * @param reverseDirection Controls the direction of this slider. When false (default), slider increases from bottom to top.
 *   When true, slider increases from top to bottom.
 * @param width The width of the vertical [Slider].
 * @param colors The [SliderColors] of the [Slider].
 * @param effect Whether to show the effect of the [Slider].
 * @param hapticEffect The haptic effect of the [Slider].
 * @param showKeyPoints Whether to show the key points (step indicators) on the slider. Only works when [keyPoints] is not null.
 * @param keyPoints Custom key point values to display on the slider. If null, uses step positions from [steps] parameter.
 *   Values should be within [valueRange].
 */
@Composable
fun VerticalSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    reverseDirection: Boolean = false,
    width: Dp = SliderDefaults.MinHeight,
    colors: SliderColors = SliderDefaults.sliderColors(),
    effect: Boolean = false,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    showKeyPoints: Boolean = false,
    keyPoints: List<Float>? = null,
    magnetThreshold: Float = 0.02f
) {
    require(steps >= 0) { "steps should be >= 0" }
    require(valueRange.start < valueRange.endInclusive) { "valueRange start should be less than end" }

    val hapticFeedback = LocalHapticFeedback.current
    var dragOffset by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    val hapticState = remember { SliderHapticState() }
    val interactionSource = remember { MutableInteractionSource() }
    val shape = remember(width) { ContinuousRoundedRectangle(width) }

    val coercedValue = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val stepFractions = remember(steps) { stepsToTickFractions(steps) }

    val keyPointFractions = remember(keyPoints, stepFractions, valueRange, showKeyPoints) {
        computeKeyPointFractions(keyPoints, stepFractions, valueRange, showKeyPoints)
    }

    val allKeyPointFractions = remember(keyPoints, stepFractions, valueRange) {
        computeAllKeyPointFractions(keyPoints, stepFractions, valueRange)
    }

    val fractionToValueVertical = remember(valueRange, steps, stepFractions, allKeyPointFractions, magnetThreshold) {
        { fraction: Float ->
            val f = fraction.coerceIn(0f, 1f)
            val newValue = lerp(valueRange.start, valueRange.endInclusive, f)
            when {
                steps > 0 -> snapValueToTick(newValue, stepFractions, valueRange.start, valueRange.endInclusive)
                allKeyPointFractions.isNotEmpty() -> {
                    val closestKeyPoint = allKeyPointFractions.minByOrNull { abs(it - f) }
                    if (closestKeyPoint != null && abs(f - closestKeyPoint) < magnetThreshold) {
                        lerp(valueRange.start, valueRange.endInclusive, closestKeyPoint)
                    } else newValue
                }

                else -> newValue
            }
        }
    }

    Box(
        modifier = modifier
            .then(
                if (enabled) {
                    Modifier.pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onDragStart = { offset ->
                                isDragging = true
                                dragOffset = offset.y
                                val thumbRadius = size.width / 2f
                                val availableHeight = (size.height - 2f * thumbRadius).coerceAtLeast(0f)
                                val visualFraction =
                                    if (availableHeight == 0f) 0f else ((offset.y - thumbRadius) / availableHeight).coerceIn(
                                        0f,
                                        1f
                                    )
                                val fractionForValue = if (reverseDirection) visualFraction else 1f - visualFraction
                                val calculatedValue = fractionToValueVertical(fractionForValue)
                                onValueChange(calculatedValue)
                                hapticState.reset(calculatedValue)
                            },
                            onVerticalDrag = { _, dragAmount ->
                                dragOffset = (dragOffset + dragAmount).coerceIn(0f, size.height.toFloat())
                                val thumbRadius = size.width / 2f
                                val availableHeight = (size.height - 2f * thumbRadius).coerceAtLeast(0f)
                                val visualFraction =
                                    if (availableHeight == 0f) 0f else ((dragOffset - thumbRadius) / availableHeight).coerceIn(
                                        0f,
                                        1f
                                    )
                                val fractionForValue = if (reverseDirection) visualFraction else 1f - visualFraction
                                val calculatedValue = fractionToValueVertical(fractionForValue)
                                onValueChange(calculatedValue)
                                hapticState.handleHapticFeedback(
                                    calculatedValue,
                                    valueRange,
                                    hapticEffect,
                                    hapticFeedback,
                                    allKeyPointFractions,
                                    hasCustomKeyPoints = keyPoints != null
                                )
                            },
                            onDragEnd = {
                                isDragging = false
                                onValueChangeFinished?.invoke()
                            }
                        )
                    }.indication(interactionSource, LocalIndication.current)
                } else Modifier
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        SliderTrack(
            modifier = Modifier.width(width).fillMaxHeight(),
            shape = shape,
            backgroundColor = colors.backgroundColor(),
            foregroundColor = colors.foregroundColor(enabled),
            effect = effect,
            value = coercedValue,
            valueRange = valueRange,
            isDragging = isDragging,
            isVertical = true,
            reverseDirection = reverseDirection,
            showKeyPoints = showKeyPoints,
            stepFractions = keyPointFractions,
            keyPointColor = colors.keyPointColor(),
            keyPointForegroundColor = colors.keyPointForegroundColor()
        )
    }
}

/**
 * A [RangeSlider] component with Miuix style.
 *
 * Range Sliders expand upon [Slider] using the same concepts but allow the user to select 2 values.
 * The two values are still bounded by the value range but they also cannot cross each other.
 *
 * @param value Current values of the RangeSlider. If either value is outside of [valueRange] provided, it will be coerced to this range.
 * @param onValueChange Lambda in which values should be updated.
 * @param modifier The modifier to be applied to the [RangeSlider].
 * @param enabled Whether the [RangeSlider] is enabled.
 * @param valueRange Range of values that Range Slider values can take. Passed [value] will be coerced to this range.
 * @param steps If positive, specifies the amount of discrete allowable values between the endpoints of [valueRange].
 * @param onValueChangeFinished Lambda to be invoked when value change has ended.
 * @param height The height of the [RangeSlider].
 * @param colors The [SliderColors] of the [RangeSlider].
 * @param effect Whether to show the effect of the [RangeSlider].
 * @param hapticEffect The haptic effect of the [RangeSlider].
 * @param showKeyPoints Whether to show the key points (step indicators) on the slider. Only works when [keyPoints] is not null.
 * @param keyPoints Custom key point values to display on the slider. If null, uses step positions from [steps] parameter.
 *   Values should be within [valueRange].
 * @param magnetThreshold The magnetic snap threshold as a fraction (0.0 to 1.0). When the slider value is within this
 *   distance from a key point, it will snap to that point. Default is 0.02 (2%). Only applies when [keyPoints] is set.
 */
@Composable
fun RangeSlider(
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    @IntRange(from = 0) steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    height: Dp = SliderDefaults.MinHeight,
    colors: SliderColors = SliderDefaults.sliderColors(),
    effect: Boolean = false,
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    showKeyPoints: Boolean = false,
    keyPoints: List<Float>? = null,
    magnetThreshold: Float = 0.02f
) {
    require(steps >= 0) { "steps should be >= 0" }
    require(valueRange.start < valueRange.endInclusive) { "valueRange start should be less than end" }

    val hapticFeedback = LocalHapticFeedback.current
    var startDragOffset by remember { mutableFloatStateOf(0f) }
    var endDragOffset by remember { mutableFloatStateOf(0f) }
    var isDraggingStart by remember { mutableStateOf(false) }
    var isDraggingEnd by remember { mutableStateOf(false) }
    val isDragging = isDraggingStart || isDraggingEnd
    val hapticState = remember { RangeSliderHapticState() }
    val interactionSource = remember { MutableInteractionSource() }
    val shape = remember(height) { ContinuousRoundedRectangle(height) }

    var currentStartValue by remember { mutableFloatStateOf(value.start) }
    var currentEndValue by remember { mutableFloatStateOf(value.endInclusive) }

    if (!isDragging) {
        currentStartValue = value.start
        currentEndValue = value.endInclusive
    }

    val coercedStart = currentStartValue.coerceIn(valueRange.start, valueRange.endInclusive)
    val coercedEnd = currentEndValue.coerceIn(valueRange.start, valueRange.endInclusive)
    val stepFractions = remember(steps) { stepsToTickFractions(steps) }

    val keyPointFractions = remember(keyPoints, stepFractions, valueRange, showKeyPoints) {
        computeKeyPointFractions(keyPoints, stepFractions, valueRange, showKeyPoints)
    }

    val allKeyPointFractions = remember(keyPoints, stepFractions, valueRange) {
        computeAllKeyPointFractions(keyPoints, stepFractions, valueRange)
    }

    val fractionToValueRange = remember(valueRange, steps, stepFractions, allKeyPointFractions, magnetThreshold) {
        { fraction: Float ->
            val f = fraction.coerceIn(0f, 1f)
            val newValue = lerp(valueRange.start, valueRange.endInclusive, f)
            when {
                steps > 0 -> snapValueToTick(newValue, stepFractions, valueRange.start, valueRange.endInclusive)
                allKeyPointFractions.isNotEmpty() -> {
                    val closestKeyPoint = allKeyPointFractions.minByOrNull { abs(it - f) }
                    if (closestKeyPoint != null && abs(f - closestKeyPoint) < magnetThreshold) {
                        lerp(valueRange.start, valueRange.endInclusive, closestKeyPoint)
                    } else newValue
                }

                else -> newValue
            }
        }
    }

    Box(
        modifier = modifier
            .then(
                if (enabled) {
                    Modifier
                        .pointerInput(Unit) {
                            detectHorizontalDragGestures(
                                onDragStart = { offset ->
                                    val thumbRadius = size.height / 2f
                                    val availableWidth = (size.width - 2f * thumbRadius).coerceAtLeast(0f)
                                    val startFraction =
                                        (coercedStart - valueRange.start) / (valueRange.endInclusive - valueRange.start)
                                    val endFraction =
                                        (coercedEnd - valueRange.start) / (valueRange.endInclusive - valueRange.start)
                                    val startPos = thumbRadius + startFraction * availableWidth
                                    val endPos = thumbRadius + endFraction * availableWidth
                                    val diffStart = abs(offset.x - startPos)
                                    val diffEnd = abs(offset.x - endPos)

                                    if (diffStart < diffEnd) {
                                        isDraggingStart = true
                                        startDragOffset = offset.x
                                        hapticState.resetStart(coercedStart)
                                    } else if (diffEnd < diffStart) {
                                        isDraggingEnd = true
                                        endDragOffset = offset.x
                                        hapticState.resetEnd(coercedEnd)
                                    } else {
                                        if (offset.x <= startPos) {
                                            isDraggingStart = true
                                            startDragOffset = offset.x
                                            hapticState.resetStart(coercedStart)
                                        } else {
                                            isDraggingEnd = true
                                            endDragOffset = offset.x
                                            hapticState.resetEnd(coercedEnd)
                                        }
                                    }
                                },
                                onHorizontalDrag = { _, dragAmount ->
                                    if (isDraggingStart) {
                                        val tentativeStartOffset =
                                            (startDragOffset + dragAmount).coerceIn(0f, size.width.toFloat())
                                        val thumbRadius = size.height / 2f
                                        val availableWidth = (size.width - 2f * thumbRadius).coerceAtLeast(0f)
                                        val visualFractionStart =
                                            if (availableWidth == 0f) 0f else ((tentativeStartOffset - thumbRadius) / availableWidth).coerceIn(
                                                0f,
                                                1f
                                            )
                                        val newStart = fractionToValueRange(visualFractionStart).coerceAtMost(currentEndValue)

                                        if (newStart >= currentEndValue && dragAmount > 0f && currentStartValue == currentEndValue) {
                                            isDraggingStart = false
                                            isDraggingEnd = true

                                            endDragOffset = tentativeStartOffset
                                            hapticState.resetEnd(currentEndValue)

                                            val visualFractionEnd =
                                                if (availableWidth == 0f) 0f else ((endDragOffset - thumbRadius) / availableWidth).coerceIn(
                                                    0f,
                                                    1f
                                                )
                                            val newEnd = fractionToValueRange(visualFractionEnd).coerceAtLeast(currentStartValue)
                                            currentEndValue = newEnd
                                            onValueChange(currentStartValue..newEnd)
                                            hapticState.handleEndHapticFeedback(
                                                newEnd,
                                                valueRange,
                                                hapticEffect,
                                                hapticFeedback,
                                                allKeyPointFractions,
                                                hasCustomKeyPoints = keyPoints != null
                                            )
                                        } else {
                                            startDragOffset = tentativeStartOffset
                                            currentStartValue = newStart
                                            onValueChange(newStart..currentEndValue)
                                            hapticState.handleStartHapticFeedback(
                                                newStart,
                                                valueRange,
                                                hapticEffect,
                                                hapticFeedback,
                                                allKeyPointFractions,
                                                hasCustomKeyPoints = keyPoints != null
                                            )
                                        }

                                    } else if (isDraggingEnd) {
                                        val tentativeEndOffset = (endDragOffset + dragAmount).coerceIn(0f, size.width.toFloat())
                                        val thumbRadius = size.height / 2f
                                        val availableWidth = (size.width - 2f * thumbRadius).coerceAtLeast(0f)
                                        val visualFractionEnd =
                                            if (availableWidth == 0f) 0f else ((tentativeEndOffset - thumbRadius) / availableWidth).coerceIn(
                                                0f,
                                                1f
                                            )
                                        val newEnd = fractionToValueRange(visualFractionEnd).coerceAtLeast(currentStartValue)

                                        if (newEnd <= currentStartValue && dragAmount < 0f && currentStartValue == currentEndValue) {
                                            isDraggingEnd = false
                                            isDraggingStart = true
                                            startDragOffset = tentativeEndOffset
                                            hapticState.resetStart(currentStartValue)

                                            val visualFractionStart =
                                                if (availableWidth == 0f) 0f else ((startDragOffset - thumbRadius) / availableWidth).coerceIn(
                                                    0f,
                                                    1f
                                                )
                                            val newStart = fractionToValueRange(visualFractionStart).coerceAtMost(currentEndValue)
                                            currentStartValue = newStart
                                            onValueChange(newStart..currentEndValue)
                                            hapticState.handleStartHapticFeedback(
                                                newStart,
                                                valueRange,
                                                hapticEffect,
                                                hapticFeedback,
                                                allKeyPointFractions,
                                                hasCustomKeyPoints = keyPoints != null
                                            )
                                        } else {
                                            endDragOffset = tentativeEndOffset
                                            currentEndValue = newEnd
                                            onValueChange(currentStartValue..newEnd)
                                            hapticState.handleEndHapticFeedback(
                                                newEnd,
                                                valueRange,
                                                hapticEffect,
                                                hapticFeedback,
                                                allKeyPointFractions,
                                                hasCustomKeyPoints = keyPoints != null
                                            )
                                        }
                                    }
                                },
                                onDragEnd = {
                                    isDraggingStart = false
                                    isDraggingEnd = false
                                    onValueChangeFinished?.invoke()
                                }
                            )
                        }
                        .indication(interactionSource, LocalIndication.current)
                } else Modifier
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        RangeSliderTrack(
            modifier = Modifier.fillMaxWidth().height(height),
            shape = shape,
            backgroundColor = colors.backgroundColor(),
            foregroundColor = colors.foregroundColor(enabled),
            effect = effect,
            valueStart = coercedStart,
            valueEnd = coercedEnd,
            valueRange = valueRange,
            isDragging = isDragging,
            showKeyPoints = showKeyPoints,
            stepFractions = keyPointFractions,
            keyPointColor = colors.keyPointColor(),
            keyPointForegroundColor = colors.keyPointForegroundColor()
        )
    }
}

/**
 * Internal slider track renderer
 */
@Composable
private fun SliderTrack(
    modifier: Modifier,
    shape: ContinuousRoundedRectangle,
    backgroundColor: Color,
    foregroundColor: Color,
    effect: Boolean,
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    isDragging: Boolean,
    isVertical: Boolean,
    reverseDirection: Boolean = false,
    showKeyPoints: Boolean,
    stepFractions: FloatArray,
    keyPointColor: Color,
    keyPointForegroundColor: Color
) {
    val backgroundAlpha by animateFloatAsState(
        targetValue = if (isDragging) 0.044f else 0f,
        animationSpec = tween(150)
    )

    Canvas(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .drawBehind { drawRect(Color.Black.copy(alpha = backgroundAlpha)) }
    ) {
        val barHeight = size.height
        val barWidth = size.width
        val fraction = (value - valueRange.start) / (valueRange.endInclusive - valueRange.start)
        val cornerRadius = if (effect) {
            if (isVertical) CornerRadius(barWidth / 2) else CornerRadius(barHeight / 2)
        } else {
            CornerRadius.Zero
        }

        if (isVertical) {
            val thumbRadius = barWidth / 2f
            val availableHeight = (barHeight - 2f * thumbRadius).coerceAtLeast(0f)
            val effectiveFraction = if (reverseDirection) fraction else (1f - fraction)
            val centerY = thumbRadius + effectiveFraction * availableHeight
            val fillHeight = (barHeight - centerY).coerceAtLeast(0f)

            drawRoundRect(
                color = foregroundColor,
                size = Size(barWidth, fillHeight),
                topLeft = Offset(0f, centerY),
                cornerRadius = cornerRadius
            )

            if (showKeyPoints && stepFractions.isNotEmpty()) {
                val keyPointRadius = barWidth / 7.5f
                stepFractions.forEach { stepFraction ->
                    val effectiveStep = if (reverseDirection) stepFraction else (1f - stepFraction)
                    val y = thumbRadius + effectiveStep * availableHeight
                    val kpColor = if (y >= centerY) keyPointForegroundColor else keyPointColor
                    drawCircle(
                        color = kpColor,
                        radius = keyPointRadius,
                        center = Offset(barWidth / 2f, y)
                    )
                }
            }
            drawCircle(
                color = foregroundColor,
                radius = thumbRadius,
                center = Offset(barWidth / 2f, centerY)
            )
            drawCircle(
                color = Color.White,
                radius = thumbRadius * 0.72f,
                center = Offset(barWidth / 2f, centerY)
            )
        } else {
            val thumbRadius = barHeight / 2f
            val availableWidth = (barWidth - 2f * thumbRadius).coerceAtLeast(0f)
            val effectiveFraction = if (reverseDirection) 1f - fraction else fraction
            val centerX = thumbRadius + effectiveFraction * availableWidth

            drawRoundRect(
                color = foregroundColor,
                size = Size(centerX, barHeight),
                topLeft = Offset(0f, 0f),
                cornerRadius = cornerRadius
            )

            if (showKeyPoints && stepFractions.isNotEmpty()) {
                val keyPointRadius = barHeight / 7.5f
                stepFractions.forEach { stepFraction ->
                    val effectiveStep = if (reverseDirection) 1f - stepFraction else stepFraction
                    val x = thumbRadius + effectiveStep * availableWidth
                    val kpColor = if (x <= centerX) keyPointForegroundColor else keyPointColor
                    drawCircle(
                        color = kpColor,
                        radius = keyPointRadius,
                        center = Offset(x, barHeight / 2f)
                    )
                }
            }
            drawCircle(
                color = foregroundColor,
                radius = thumbRadius,
                center = Offset(centerX, barHeight / 2f)
            )
            drawCircle(
                color = Color.White,
                radius = thumbRadius * 0.72f,
                center = Offset(centerX, barHeight / 2f)
            )
        }
    }
}

/**
 * Internal range slider track renderer
 */
@Composable
private fun RangeSliderTrack(
    modifier: Modifier,
    shape: ContinuousRoundedRectangle,
    backgroundColor: Color,
    foregroundColor: Color,
    effect: Boolean,
    valueStart: Float,
    valueEnd: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    isDragging: Boolean,
    showKeyPoints: Boolean,
    stepFractions: FloatArray,
    keyPointColor: Color,
    keyPointForegroundColor: Color
) {
    val backgroundAlpha by animateFloatAsState(
        targetValue = if (isDragging) 0.044f else 0f,
        animationSpec = tween(150)
    )

    Canvas(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .drawBehind { drawRect(Color.Black.copy(alpha = backgroundAlpha)) }
    ) {
        val barHeight = size.height
        val barWidth = size.width
        val startFraction = (valueStart - valueRange.start) / (valueRange.endInclusive - valueRange.start)
        val endFraction = (valueEnd - valueRange.start) / (valueRange.endInclusive - valueRange.start)
        val thumbRadius = barHeight / 2f
        val availableWidth = (barWidth - 2f * thumbRadius).coerceAtLeast(0f)
        val startX = thumbRadius + startFraction * availableWidth
        val endX = thumbRadius + endFraction * availableWidth
        val cornerRadius = if (effect) CornerRadius(barHeight / 2) else CornerRadius.Zero

        drawRoundRect(
            color = foregroundColor,
            size = Size((endX - startX).coerceAtLeast(0f), barHeight),
            topLeft = Offset(startX, 0f),
            cornerRadius = cornerRadius
        )

        if (showKeyPoints && stepFractions.isNotEmpty()) {
            val keyPointRadius = barHeight / 7.5f
            stepFractions.forEach { stepFraction ->
                val x = thumbRadius + stepFraction * availableWidth
                val kpColor = if (x in startX..endX) keyPointForegroundColor else keyPointColor
                drawCircle(
                    color = kpColor,
                    radius = keyPointRadius,
                    center = Offset(x, barHeight / 2f)
                )
            }
        }

        val centerY = barHeight / 2f

        drawCircle(
            color = foregroundColor,
            radius = thumbRadius,
            center = Offset(startX, centerY)
        )
        drawCircle(
            color = Color.White,
            radius = thumbRadius * 0.72f,
            center = Offset(startX, centerY)
        )
        drawCircle(
            color = foregroundColor,
            radius = thumbRadius,
            center = Offset(endX, centerY)
        )
        drawCircle(
            color = Color.White,
            radius = thumbRadius * 0.72f,
            center = Offset(endX, centerY)
        )
    }
}

/**
 * Manages haptic feedback state for the slider.
 */
internal class SliderHapticState {
    private var edgeFeedbackTriggered: Boolean = false
    private var lastStep: Float = 0f
    private var isAtKeyPoint: Boolean = false

    fun reset(currentValue: Float) {
        edgeFeedbackTriggered = false
        lastStep = currentValue
        isAtKeyPoint = false
    }

    fun handleHapticFeedback(
        currentValue: Float,
        valueRange: ClosedFloatingPointRange<Float>,
        hapticEffect: SliderDefaults.SliderHapticEffect,
        hapticFeedback: HapticFeedback,
        keyPointFractions: FloatArray = floatArrayOf(),
        hasCustomKeyPoints: Boolean = false
    ) {
        if (hapticEffect == SliderDefaults.SliderHapticEffect.None) return

        handleEdgeHaptic(currentValue, valueRange, hapticFeedback)

        if (hapticEffect == SliderDefaults.SliderHapticEffect.Step) {
            handleStepHaptic(currentValue, valueRange, hapticFeedback, keyPointFractions, hasCustomKeyPoints)
        }
    }

    private fun handleEdgeHaptic(
        currentValue: Float,
        valueRange: ClosedFloatingPointRange<Float>,
        hapticFeedback: HapticFeedback
    ) {
        val isAtEdge = currentValue == valueRange.start || currentValue == valueRange.endInclusive
        if (isAtEdge && !edgeFeedbackTriggered) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.GestureThresholdActivate)
            edgeFeedbackTriggered = true
        } else if (!isAtEdge) {
            edgeFeedbackTriggered = false
        }
    }

    private fun handleStepHaptic(
        currentValue: Float,
        valueRange: ClosedFloatingPointRange<Float>,
        hapticFeedback: HapticFeedback,
        keyPointFractions: FloatArray,
        hasCustomKeyPoints: Boolean
    ) {
        val isNotAtEdge = currentValue != valueRange.start && currentValue != valueRange.endInclusive

        if (hasCustomKeyPoints && keyPointFractions.isNotEmpty()) {
            handleKeyPointHaptic(currentValue, valueRange, hapticFeedback, keyPointFractions, isNotAtEdge)
        } else if (currentValue != lastStep && isNotAtEdge) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            lastStep = currentValue
        }
    }

    private fun handleKeyPointHaptic(
        currentValue: Float,
        valueRange: ClosedFloatingPointRange<Float>,
        hapticFeedback: HapticFeedback,
        keyPointFractions: FloatArray,
        isNotAtEdge: Boolean
    ) {
        val fraction = (currentValue - valueRange.start) / (valueRange.endInclusive - valueRange.start)
        val threshold = 0.005f

        val nearestKeyPoint = keyPointFractions.minByOrNull { abs(it - fraction) }
        val currentlyAtKeyPoint = nearestKeyPoint != null && abs(fraction - nearestKeyPoint) < threshold

        if (currentlyAtKeyPoint && !isAtKeyPoint && isNotAtEdge) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
        }

        isAtKeyPoint = currentlyAtKeyPoint
    }
}

/**
 * Manages haptic feedback state for the range slider.
 */
internal class RangeSliderHapticState {
    private var startEdgeFeedbackTriggered: Boolean = false
    private var endEdgeFeedbackTriggered: Boolean = false
    private var startLastStep: Float = 0f
    private var endLastStep: Float = 0f
    private var startIsAtKeyPoint: Boolean = false
    private var endIsAtKeyPoint: Boolean = false

    fun resetStart(currentValue: Float) {
        startEdgeFeedbackTriggered = false
        startLastStep = currentValue
        startIsAtKeyPoint = false
    }

    fun resetEnd(currentValue: Float) {
        endEdgeFeedbackTriggered = false
        endLastStep = currentValue
        endIsAtKeyPoint = false
    }

    fun handleStartHapticFeedback(
        currentValue: Float,
        valueRange: ClosedFloatingPointRange<Float>,
        hapticEffect: SliderDefaults.SliderHapticEffect,
        hapticFeedback: HapticFeedback,
        keyPointFractions: FloatArray = floatArrayOf(),
        hasCustomKeyPoints: Boolean = false
    ) {
        handleHapticFeedbackInternal(
            currentValue = currentValue,
            valueRange = valueRange,
            hapticEffect = hapticEffect,
            hapticFeedback = hapticFeedback,
            keyPointFractions = keyPointFractions,
            edgeFeedbackTriggered = startEdgeFeedbackTriggered,
            lastStep = startLastStep,
            isAtKeyPoint = startIsAtKeyPoint,
            isStartEdge = true,
            hasCustomKeyPoints = hasCustomKeyPoints,
            onEdgeFeedbackUpdate = { startEdgeFeedbackTriggered = it },
            onLastStepUpdate = { startLastStep = it },
            onKeyPointUpdate = { startIsAtKeyPoint = it }
        )
    }

    fun handleEndHapticFeedback(
        currentValue: Float,
        valueRange: ClosedFloatingPointRange<Float>,
        hapticEffect: SliderDefaults.SliderHapticEffect,
        hapticFeedback: HapticFeedback,
        keyPointFractions: FloatArray = floatArrayOf(),
        hasCustomKeyPoints: Boolean = false
    ) {
        handleHapticFeedbackInternal(
            currentValue = currentValue,
            valueRange = valueRange,
            hapticEffect = hapticEffect,
            hapticFeedback = hapticFeedback,
            keyPointFractions = keyPointFractions,
            edgeFeedbackTriggered = endEdgeFeedbackTriggered,
            lastStep = endLastStep,
            isAtKeyPoint = endIsAtKeyPoint,
            isStartEdge = false,
            hasCustomKeyPoints = hasCustomKeyPoints,
            onEdgeFeedbackUpdate = { endEdgeFeedbackTriggered = it },
            onLastStepUpdate = { endLastStep = it },
            onKeyPointUpdate = { endIsAtKeyPoint = it }
        )
    }

    private fun handleHapticFeedbackInternal(
        currentValue: Float,
        valueRange: ClosedFloatingPointRange<Float>,
        hapticEffect: SliderDefaults.SliderHapticEffect,
        hapticFeedback: HapticFeedback,
        keyPointFractions: FloatArray,
        edgeFeedbackTriggered: Boolean,
        lastStep: Float,
        isAtKeyPoint: Boolean,
        isStartEdge: Boolean,
        hasCustomKeyPoints: Boolean,
        onEdgeFeedbackUpdate: (Boolean) -> Unit,
        onLastStepUpdate: (Float) -> Unit,
        onKeyPointUpdate: (Boolean) -> Unit
    ) {
        if (hapticEffect == SliderDefaults.SliderHapticEffect.None) return

        val targetEdge = if (isStartEdge) valueRange.start else valueRange.endInclusive
        val isAtEdge = currentValue == targetEdge

        if (isAtEdge && !edgeFeedbackTriggered) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.GestureThresholdActivate)
            onEdgeFeedbackUpdate(true)
        } else if (!isAtEdge) {
            onEdgeFeedbackUpdate(false)
        }

        if (hapticEffect == SliderDefaults.SliderHapticEffect.Step) {
            val isNotAtEdge = currentValue != targetEdge

            if (hasCustomKeyPoints && keyPointFractions.isNotEmpty()) {
                val fraction = (currentValue - valueRange.start) / (valueRange.endInclusive - valueRange.start)
                val threshold = 0.005f

                val nearestKeyPoint = keyPointFractions.minByOrNull { abs(it - fraction) }
                val currentlyAtKeyPoint = nearestKeyPoint != null && abs(fraction - nearestKeyPoint) < threshold

                if (currentlyAtKeyPoint && !isAtKeyPoint && isNotAtEdge) {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                }

                onKeyPointUpdate(currentlyAtKeyPoint)
            } else if (currentValue != lastStep && isNotAtEdge) {
                hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                onLastStepUpdate(currentValue)
            }
        }
    }
}

private fun stepsToTickFractions(steps: Int): FloatArray {
    return if (steps == 0) floatArrayOf() else FloatArray(steps + 2) { it.toFloat() / (steps + 1) }
}


private fun snapValueToTick(
    current: Float,
    tickFractions: FloatArray,
    minPx: Float,
    maxPx: Float
): Float {
    return tickFractions
        .minByOrNull { abs(lerp(minPx, maxPx, it) - current) }
        ?.run { lerp(minPx, maxPx, this) }
        ?: current
}

/**
 * Converts point values to normalized fractions within the value range.
 */
private fun pointsToFractions(
    points: List<Float>,
    valueRange: ClosedFloatingPointRange<Float>
): FloatArray {
    return points.map { point ->
        ((point - valueRange.start) / (valueRange.endInclusive - valueRange.start))
            .coerceIn(0f, 1f)
    }.toFloatArray()
}

/**
 * Computes key point fractions for slider display.
 * Filters out points too close to edges.
 */
private fun computeKeyPointFractions(
    keyPoints: List<Float>?,
    stepFractions: FloatArray,
    valueRange: ClosedFloatingPointRange<Float>,
    showKeyPoints: Boolean
): FloatArray {
    return when {
        keyPoints != null -> pointsToFractions(keyPoints, valueRange)
        showKeyPoints -> stepFractions
        else -> floatArrayOf()
    }
}

/**
 * Computes all key point fractions including edge points.
 * Used for haptic feedback and magnetic snapping.
 */
private fun computeAllKeyPointFractions(
    keyPoints: List<Float>?,
    stepFractions: FloatArray,
    valueRange: ClosedFloatingPointRange<Float>
): FloatArray {
    return when {
        keyPoints != null -> pointsToFractions(keyPoints, valueRange)
        stepFractions.isNotEmpty() -> stepFractions
        else -> floatArrayOf()
    }
}

object SliderDefaults {
    /**
     * The minimum height of the [Slider] and [RangeSlider].
     */
    val MinHeight = 30.dp

    /**
     * The type of haptic feedback to be used for the slider.
     */
    enum class SliderHapticEffect {
        /** No haptic feedback. */
        None,

        /** Haptic feedback at 0% and 100%. */
        Edge,

        /** Haptic feedback at steps. */
        Step
    }

    /**
     * The default haptic effect of the [Slider] and [RangeSlider].
     */
    val DefaultHapticEffect = SliderHapticEffect.Edge

    @Composable
    fun sliderColors(
        foregroundColor: Color = MiuixTheme.colorScheme.primary,
        disabledForegroundColor: Color = MiuixTheme.colorScheme.disabledPrimarySlider,
        backgroundColor: Color = MiuixTheme.colorScheme.secondaryVariant,
        keyPointColor: Color = MiuixTheme.colorScheme.sliderKeyPoint,
        keyPointForegroundColor: Color = MiuixTheme.colorScheme.sliderKeyPointForeground,
    ): SliderColors = SliderColors(
        foregroundColor = foregroundColor,
        disabledForegroundColor = disabledForegroundColor,
        backgroundColor = backgroundColor,
        keyPointColor = keyPointColor,
        keyPointForegroundColor = keyPointForegroundColor
    )
}

@Immutable
class SliderColors(
    private val foregroundColor: Color,
    private val disabledForegroundColor: Color,
    private val backgroundColor: Color,
    private val keyPointColor: Color,
    private val keyPointForegroundColor: Color
) {
    @Stable
    internal fun foregroundColor(enabled: Boolean): Color =
        if (enabled) foregroundColor else disabledForegroundColor

    @Stable
    internal fun backgroundColor(): Color = backgroundColor

    @Stable
    internal fun keyPointColor(): Color = keyPointColor

    @Stable
    internal fun keyPointForegroundColor(): Color = keyPointForegroundColor
}
