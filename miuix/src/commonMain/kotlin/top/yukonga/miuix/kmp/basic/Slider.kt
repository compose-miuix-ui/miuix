// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.annotation.IntRange
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.progressBarRangeInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.setProgress
import androidx.compose.ui.semantics.stateDescription
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
 * @param hapticEffect The haptic effect of the [Slider].
 * @param showKeyPoints Whether to show the key points (step indicators) on the slider. Only works when [keyPoints] is not null.
 * @param keyPoints Custom key point values to display on the slider. If null, uses step positions from [steps] parameter.
 *   Values should be within [valueRange]. For example, for a range of 0f..100f, you might specify listOf(0f, 25f, 50f, 75f, 100f).
 * @param magnetThreshold The magnetic snap threshold as a fraction (0.0 to 1.0). When the slider value is within this
 *   distance from a key point, it will snap to that point. Default is 0.02 (2%). Only applies when [keyPoints] is set.
 */
@Composable
@NonRestartableComposable
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
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    showKeyPoints: Boolean = false,
    keyPoints: List<Float>? = null,
    magnetThreshold: Float = 0.02f
) {
    require(steps >= 0) { "steps should be >= 0" }
    require(valueRange.start < valueRange.endInclusive) { "valueRange start should be less than end" }

    val hapticFeedback = LocalHapticFeedback.current
    val onValueChangeState by rememberUpdatedState(onValueChange)
    val onValueChangeFinishedState by rememberUpdatedState(onValueChangeFinished)
    var dragOffset by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    val hapticState = remember { SliderHapticState() }
    val interactionSource = remember { MutableInteractionSource() }
    val shape = remember(height) { ContinuousRoundedRectangle(height) }

    val coercedValue = value.coerceIn(valueRange.start, valueRange.endInclusive)

    val progressAnimationSpec = if (isDragging) {
        spring<Float>(dampingRatio = 0.9f, stiffness = 1755f)
    } else {
        spring<Float>(dampingRatio = 0.96f, stiffness = 322f)
    }
    val thumbScaleAnimationSpec = spring<Float>(dampingRatio = 0.6f, stiffness = 987f)

    val animatedValue by animateFloatAsState(coercedValue, progressAnimationSpec)
    val thumbScale by animateFloatAsState(if (isDragging) 1.127f else 1f, thumbScaleAnimationSpec)

    val stepFractions = remember(steps) { stepsToTickFractions(steps) }

    val keyPointFractions = remember(keyPoints, stepFractions, valueRange, showKeyPoints) {
        computeKeyPointFractions(keyPoints, stepFractions, valueRange, showKeyPoints)
    }

    val allKeyPointFractions = remember(keyPoints, stepFractions, valueRange) {
        computeAllKeyPointFractions(keyPoints, stepFractions, valueRange)
    }

    val fractionToValue = remember(valueRange, steps, stepFractions, allKeyPointFractions, magnetThreshold) {
        { fraction: Float ->
            resolveValueFromFraction(
                fraction = fraction,
                valueRange = valueRange,
                steps = steps,
                stepFractions = stepFractions,
                allKeyPointFractions = allKeyPointFractions,
                magnetThreshold = magnetThreshold
            )
        }
    }

    Box(
        modifier = modifier
            .then(
                if (enabled) {
                    Modifier
                        .pointerInput(
                            enabled,
                            reverseDirection,
                            valueRange,
                            steps,
                            magnetThreshold,
                            keyPoints,
                            hapticEffect
                        ) {
                            detectHorizontalDragGestures(
                                onDragStart = { offset ->
                                    isDragging = true
                                    dragOffset = offset.x

                                    val visualFraction = horizontalVisualFraction(offset.x, size.width, size.height)
                                    val fractionForValue = if (reverseDirection) 1f - visualFraction else visualFraction
                                    val calculatedValue = fractionToValue(fractionForValue)
                                    onValueChangeState(calculatedValue)
                                    hapticState.reset(calculatedValue)
                                },
                                onHorizontalDrag = { change, dragAmount ->
                                    change.consume()
                                    dragOffset += dragAmount

                                    val visualFraction = horizontalVisualFraction(dragOffset, size.width, size.height)
                                    val fractionForValue = if (reverseDirection) 1f - visualFraction else visualFraction
                                    val calculatedValue = fractionToValue(fractionForValue)
                                    onValueChangeState(calculatedValue)
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
                                    onValueChangeFinishedState?.invoke()
                                }
                            )
                        }
                        .indication(interactionSource, LocalIndication.current)
                } else Modifier
            )
            .semantics {
                progressBarRangeInfo = ProgressBarRangeInfo(
                    coercedValue,
                    valueRange.start..valueRange.endInclusive,
                    if (steps > 0) steps else 0
                )
                setProgress { target ->
                    val clamped = target.coerceIn(valueRange.start, valueRange.endInclusive)
                    onValueChangeState(clamped)
                    true
                }
            },
        contentAlignment = Alignment.CenterStart
    ) {
        SliderTrack(
            modifier = Modifier.fillMaxWidth().height(height),
            shape = shape,
            backgroundColor = colors.backgroundColor(enabled),
            foregroundColor = colors.foregroundColor(enabled),
            thumbColor = colors.thumbColor(enabled),
            keyPointColor = colors.keyPointColor(),
            keyPointForegroundColor = colors.keyPointForegroundColor(),
            value = animatedValue,
            thumbScale = thumbScale,
            valueRange = valueRange,
            isDragging = isDragging,
            isVertical = false,
            reverseDirection = reverseDirection,
            showKeyPoints = showKeyPoints,
            stepFractions = keyPointFractions,
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
@NonRestartableComposable
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
    val onValueChangeState by rememberUpdatedState(onValueChange)
    val onValueChangeFinishedState by rememberUpdatedState(onValueChangeFinished)
    var dragOffset by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    val hapticState = remember { SliderHapticState() }
    val interactionSource = remember { MutableInteractionSource() }
    val shape = remember(width) { ContinuousRoundedRectangle(width) }

    val coercedValue = value.coerceIn(valueRange.start, valueRange.endInclusive)

    val progressAnimationSpec = if (isDragging) {
        spring<Float>(dampingRatio = 0.9f, stiffness = 1755f)
    } else {
        spring<Float>(dampingRatio = 0.96f, stiffness = 322f)
    }
    val thumbScaleAnimationSpec = spring<Float>(dampingRatio = 0.6f, stiffness = 987f)

    val animatedValue by animateFloatAsState(coercedValue, progressAnimationSpec)
    val thumbScale by animateFloatAsState(if (isDragging) 1.127f else 1f, thumbScaleAnimationSpec)

    val stepFractions = remember(steps) { stepsToTickFractions(steps) }

    val keyPointFractions = remember(keyPoints, stepFractions, valueRange, showKeyPoints) {
        computeKeyPointFractions(keyPoints, stepFractions, valueRange, showKeyPoints)
    }

    val allKeyPointFractions = remember(keyPoints, stepFractions, valueRange) {
        computeAllKeyPointFractions(keyPoints, stepFractions, valueRange)
    }

    val fractionToValueVertical = remember(valueRange, steps, stepFractions, allKeyPointFractions, magnetThreshold) {
        { fraction: Float ->
            resolveValueFromFraction(
                fraction = fraction,
                valueRange = valueRange,
                steps = steps,
                stepFractions = stepFractions,
                allKeyPointFractions = allKeyPointFractions,
                magnetThreshold = magnetThreshold
            )
        }
    }

    Box(
        modifier = modifier
            .then(
                if (enabled) {
                    Modifier.pointerInput(
                        enabled,
                        reverseDirection,
                        valueRange,
                        steps,
                        magnetThreshold,
                        keyPoints,
                        hapticEffect
                    ) {
                        detectVerticalDragGestures(
                            onDragStart = { offset ->
                                isDragging = true
                                dragOffset = offset.y
                                val visualFraction = verticalVisualFraction(offset.y, size.height, size.width)
                                val fractionForValue = if (reverseDirection) visualFraction else 1f - visualFraction
                                val calculatedValue = fractionToValueVertical(fractionForValue)
                                onValueChangeState(calculatedValue)
                                hapticState.reset(calculatedValue)
                            },
                            onVerticalDrag = { change, dragAmount ->
                                change.consume()
                                dragOffset += dragAmount
                                val visualFraction = verticalVisualFraction(dragOffset, size.height, size.width)
                                val fractionForValue = if (reverseDirection) visualFraction else 1f - visualFraction
                                val calculatedValue = fractionToValueVertical(fractionForValue)
                                onValueChangeState(calculatedValue)
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
                                onValueChangeFinishedState?.invoke()
                            }
                        )
                    }.indication(interactionSource, LocalIndication.current)
                } else Modifier
            )
            .semantics {
                progressBarRangeInfo = ProgressBarRangeInfo(
                    coercedValue,
                    valueRange.start..valueRange.endInclusive,
                    if (steps > 0) steps else 0
                )
                setProgress { target ->
                    val clamped = target.coerceIn(valueRange.start, valueRange.endInclusive)
                    onValueChangeState(clamped)
                    true
                }
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        SliderTrack(
            modifier = Modifier.width(width).fillMaxHeight(),
            shape = shape,
            backgroundColor = colors.backgroundColor(enabled),
            foregroundColor = colors.foregroundColor(enabled),
            thumbColor = colors.thumbColor(enabled),
            keyPointColor = colors.keyPointColor(),
            keyPointForegroundColor = colors.keyPointForegroundColor(),
            value = animatedValue,
            thumbScale = thumbScale,
            valueRange = valueRange,
            isDragging = isDragging,
            isVertical = true,
            reverseDirection = reverseDirection,
            showKeyPoints = showKeyPoints,
            stepFractions = keyPointFractions,
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
 * @param hapticEffect The haptic effect of the [RangeSlider].
 * @param showKeyPoints Whether to show the key points (step indicators) on the slider. Only works when [keyPoints] is not null.
 * @param keyPoints Custom key point values to display on the slider. If null, uses step positions from [steps] parameter.
 *   Values should be within [valueRange].
 * @param magnetThreshold The magnetic snap threshold as a fraction (0.0 to 1.0). When the slider value is within this
 *   distance from a key point, it will snap to that point. Default is 0.02 (2%). Only applies when [keyPoints] is set.
 */
@Composable
@NonRestartableComposable
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
    hapticEffect: SliderDefaults.SliderHapticEffect = SliderDefaults.DefaultHapticEffect,
    showKeyPoints: Boolean = false,
    keyPoints: List<Float>? = null,
    magnetThreshold: Float = 0.02f
) {
    require(steps >= 0) { "steps should be >= 0" }
    require(valueRange.start < valueRange.endInclusive) { "valueRange start should be less than end" }

    val hapticFeedback = LocalHapticFeedback.current
    val onValueChangeState by rememberUpdatedState(onValueChange)
    val onValueChangeFinishedState by rememberUpdatedState(onValueChangeFinished)
    var startDragOffset by remember { mutableFloatStateOf(0f) }
    var endDragOffset by remember { mutableFloatStateOf(0f) }
    var isDraggingStart by remember { mutableStateOf(false) }
    var isDraggingEnd by remember { mutableStateOf(false) }
    val isDragging = isDraggingStart || isDraggingEnd
    val hapticState = remember { RangeSliderHapticState() }
    val interactionSource = remember { MutableInteractionSource() }
    val shape = remember(height) { ContinuousRoundedRectangle(height) }
    var lastDraggedIsStart by remember { mutableStateOf(true) }

    var currentStartValue by remember { mutableFloatStateOf(value.start) }
    var currentEndValue by remember { mutableFloatStateOf(value.endInclusive) }

    if (!isDragging) {
        currentStartValue = value.start
        currentEndValue = value.endInclusive
    }

    val coercedStart = currentStartValue.coerceIn(valueRange.start, valueRange.endInclusive)
    val coercedEnd = currentEndValue.coerceIn(valueRange.start, valueRange.endInclusive)

    val progressAnimationSpec = if (isDragging) {
        spring<Float>(dampingRatio = 0.9f, stiffness = 1755f)
    } else {
        spring<Float>(dampingRatio = 0.96f, stiffness = 322f)
    }
    val thumbScaleAnimationSpec = spring<Float>(dampingRatio = 0.6f, stiffness = 987f)

    val animatedStartValue by animateFloatAsState(coercedStart, progressAnimationSpec)
    val animatedEndValue by animateFloatAsState(coercedEnd, progressAnimationSpec)
    val startThumbScale by animateFloatAsState(if (isDraggingStart) 1.127f else 1f, thumbScaleAnimationSpec)
    val endThumbScale by animateFloatAsState(if (isDraggingEnd) 1.127f else 1f, thumbScaleAnimationSpec)

    val stepFractions = remember(steps) { stepsToTickFractions(steps) }

    val keyPointFractions = remember(keyPoints, stepFractions, valueRange, showKeyPoints) {
        computeKeyPointFractions(keyPoints, stepFractions, valueRange, showKeyPoints)
    }

    val allKeyPointFractions = remember(keyPoints, stepFractions, valueRange) {
        computeAllKeyPointFractions(keyPoints, stepFractions, valueRange)
    }

    val fractionToValueRange = remember(valueRange, steps, stepFractions, allKeyPointFractions, magnetThreshold) {
        { fraction: Float ->
            resolveValueFromFraction(
                fraction = fraction,
                valueRange = valueRange,
                steps = steps,
                stepFractions = stepFractions,
                allKeyPointFractions = allKeyPointFractions,
                magnetThreshold = magnetThreshold
            )
        }
    }

    Box(
        modifier = modifier
            .then(
                if (enabled) {
                    Modifier
                        .pointerInput(
                            enabled,
                            valueRange,
                            steps,
                            magnetThreshold,
                            keyPoints,
                            hapticEffect
                        ) {
                            detectHorizontalDragGestures(
                                onDragStart = { offset ->
                                    val thumbRadius = size.height / 2f
                                    val availableWidth = (size.width - 2f * thumbRadius).coerceAtLeast(0f)
                                    val startFraction =
                                        (currentStartValue - valueRange.start) / (valueRange.endInclusive - valueRange.start)
                                    val endFraction =
                                        (currentEndValue - valueRange.start) / (valueRange.endInclusive - valueRange.start)
                                    val startPos = thumbRadius + startFraction * availableWidth
                                    val endPos = thumbRadius + endFraction * availableWidth

                                    val knobRadius = thumbRadius * 0.72f
                                    val hitRadius = knobRadius + (thumbRadius * 0.5f)
                                    val isOnStartThumb = abs(offset.x - startPos) <= hitRadius
                                    val isOnEndThumb = abs(offset.x - endPos) <= hitRadius

                                    when {
                                        isOnStartThumb && !isOnEndThumb -> {
                                            isDraggingStart = true
                                            startDragOffset = offset.x
                                            hapticState.resetStart(coercedStart)
                                        }

                                        !isOnStartThumb && isOnEndThumb -> {
                                            isDraggingEnd = true
                                            endDragOffset = offset.x
                                            hapticState.resetEnd(coercedEnd)
                                        }

                                        isOnStartThumb && isOnEndThumb -> {
                                            if (lastDraggedIsStart) {
                                                isDraggingStart = true
                                                startDragOffset = offset.x
                                                hapticState.resetStart(coercedStart)
                                            } else {
                                                isDraggingEnd = true
                                                endDragOffset = offset.x
                                                hapticState.resetEnd(coercedEnd)
                                            }
                                        }

                                        else -> {
                                            val diffStart = abs(offset.x - startPos)
                                            val diffEnd = abs(offset.x - endPos)
                                            if (diffStart <= diffEnd) {
                                                isDraggingStart = true
                                                startDragOffset = offset.x
                                                hapticState.resetStart(coercedStart)
                                            } else {
                                                isDraggingEnd = true
                                                endDragOffset = offset.x
                                                hapticState.resetEnd(coercedEnd)
                                            }
                                        }
                                    }
                                },
                                onHorizontalDrag = { change, dragAmount ->
                                    change.consume()
                                    if (isDraggingStart) {
                                        lastDraggedIsStart = true
                                        val tentativeStartOffset = startDragOffset + dragAmount
                                        val visualFractionStart = horizontalVisualFraction(tentativeStartOffset, size.width, size.height)
                                        val newStart = fractionToValueRange(visualFractionStart).coerceAtMost(currentEndValue)

                                        if (newStart >= currentEndValue && dragAmount > 0f && currentStartValue == currentEndValue) {
                                            isDraggingStart = false
                                            isDraggingEnd = true

                                            endDragOffset = tentativeStartOffset
                                            hapticState.resetEnd(currentEndValue)
                                            hapticState.inheritEndKeyPoint()

                                            val visualFractionEnd = horizontalVisualFraction(endDragOffset, size.width, size.height)
                                            val newEnd = fractionToValueRange(visualFractionEnd).coerceAtLeast(currentStartValue)
                                            currentEndValue = newEnd
                                            onValueChangeState(currentStartValue..newEnd)
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
                                            onValueChangeState(newStart..currentEndValue)
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
                                        lastDraggedIsStart = false
                                        val tentativeEndOffset = endDragOffset + dragAmount
                                        val visualFractionEnd = horizontalVisualFraction(tentativeEndOffset, size.width, size.height)
                                        val newEnd = fractionToValueRange(visualFractionEnd).coerceAtLeast(currentStartValue)

                                        if (newEnd <= currentStartValue && dragAmount < 0f && currentStartValue == currentEndValue) {
                                            isDraggingEnd = false
                                            isDraggingStart = true
                                            startDragOffset = tentativeEndOffset
                                            hapticState.resetStart(currentStartValue)
                                            hapticState.inheritStartKeyPoint()

                                            val visualFractionStart = horizontalVisualFraction(startDragOffset, size.width, size.height)
                                            val newStart = fractionToValueRange(visualFractionStart).coerceAtMost(currentEndValue)
                                            currentStartValue = newStart
                                            onValueChangeState(newStart..currentEndValue)
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
                                            onValueChangeState(currentStartValue..newEnd)
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
                                    onValueChangeFinishedState?.invoke()
                                }
                            )
                        }
                        .indication(interactionSource, LocalIndication.current)
                } else Modifier
            )
            .semantics {
                stateDescription = "${coercedStart}-${coercedEnd}"
            },
        contentAlignment = Alignment.CenterStart
    ) {
        RangeSliderTrack(
            modifier = Modifier.fillMaxWidth().height(height),
            shape = shape,
            backgroundColor = colors.backgroundColor(enabled),
            foregroundColor = colors.foregroundColor(enabled),
            thumbColor = colors.thumbColor(enabled),
            keyPointColor = colors.keyPointColor(),
            keyPointForegroundColor = colors.keyPointForegroundColor(),
            valueStart = animatedStartValue,
            valueEnd = animatedEndValue,
            startThumbScale = startThumbScale,
            endThumbScale = endThumbScale,
            valueRange = valueRange,
            isDragging = isDragging,
            showKeyPoints = showKeyPoints,
            stepFractions = keyPointFractions,
        )
    }
}

/**
 * Internal slider track renderer
 */
@Composable
private fun SliderTrack(
    modifier: Modifier,
    shape: Shape,
    backgroundColor: Color,
    foregroundColor: Color,
    thumbColor: Color,
    keyPointColor: Color,
    keyPointForegroundColor: Color,
    value: Float,
    thumbScale: Float = 1f,
    valueRange: ClosedFloatingPointRange<Float>,
    isDragging: Boolean,
    isVertical: Boolean,
    reverseDirection: Boolean = false,
    showKeyPoints: Boolean,
    stepFractions: FloatArray,
) {
    val backgroundAlpha by animateFloatAsState(
        targetValue = if (isDragging) 0.044f else 0f,
        animationSpec = tween(150),
        label = "SliderTrackAlpha"
    )

    Canvas(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .drawBehind {
                val overlay = Color.Black.copy(alpha = backgroundAlpha)
                drawRect(overlay)
            }
    ) {
        val barHeight = size.height
        val barWidth = size.width
        val fraction = (value - valueRange.start) / (valueRange.endInclusive - valueRange.start)

        if (isVertical) {
            val thumbRadius = barWidth / 2f
            val availableHeight = (barHeight - 2f * thumbRadius).coerceAtLeast(0f)
            val effectiveFraction = if (reverseDirection) fraction else (1f - fraction)
            val centerY = thumbRadius + effectiveFraction * availableHeight

            drawLine(
                color = foregroundColor,
                start = Offset(barWidth / 2f, barHeight),
                end = Offset(barWidth / 2f, centerY),
                strokeWidth = barWidth,
                cap = StrokeCap.Round
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
                        center = Offset(barWidth / 2f, y),
                    )
                }
            }
            drawCircle(
                color = thumbColor,
                radius = thumbRadius * 0.72f * thumbScale,
                center = Offset(barWidth / 2f, centerY)
            )
        } else {
            val thumbRadius = barHeight / 2f
            val availableWidth = (barWidth - 2f * thumbRadius).coerceAtLeast(0f)
            val effectiveFraction = if (reverseDirection) 1f - fraction else fraction
            val centerX = thumbRadius + effectiveFraction * availableWidth

            drawLine(
                color = foregroundColor,
                start = Offset(0f, barHeight / 2f),
                end = Offset(centerX, barHeight / 2f),
                strokeWidth = barHeight,
                cap = StrokeCap.Round
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
                        center = Offset(x, barHeight / 2f),
                    )
                }
            }
            drawCircle(
                color = thumbColor,
                radius = thumbRadius * 0.72f * thumbScale,
                center = Offset(centerX, barHeight / 2f),
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
    shape: Shape,
    backgroundColor: Color,
    foregroundColor: Color,
    thumbColor: Color,
    keyPointColor: Color,
    keyPointForegroundColor: Color,
    valueStart: Float,
    valueEnd: Float,
    startThumbScale: Float = 1f,
    endThumbScale: Float = 1f,
    valueRange: ClosedFloatingPointRange<Float>,
    isDragging: Boolean,
    showKeyPoints: Boolean,
    stepFractions: FloatArray,
) {
    val backgroundAlpha by animateFloatAsState(
        targetValue = if (isDragging) 0.044f else 0f,
        animationSpec = tween(150),
        label = "RangeSliderTrackAlpha"
    )

    Canvas(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .drawBehind {
                val overlay = Color.Black.copy(alpha = backgroundAlpha)
                drawRect(overlay)
            }
    ) {
        val barHeight = size.height
        val barWidth = size.width
        val startFraction = (valueStart - valueRange.start) / (valueRange.endInclusive - valueRange.start)
        val endFraction = (valueEnd - valueRange.start) / (valueRange.endInclusive - valueRange.start)
        val thumbRadius = barHeight / 2f
        val availableWidth = (barWidth - 2f * thumbRadius).coerceAtLeast(0f)
        val startX = thumbRadius + startFraction * availableWidth
        val endX = thumbRadius + endFraction * availableWidth

        val centerY = barHeight / 2f

        drawLine(
            color = foregroundColor,
            start = Offset(startX, centerY),
            end = Offset(endX, centerY),
            strokeWidth = barHeight,
            cap = StrokeCap.Round
        )

        if (showKeyPoints && stepFractions.isNotEmpty()) {
            val keyPointRadius = SliderDefaults.KeyPointRadius.toPx()
            stepFractions.forEach { stepFraction ->
                val x = thumbRadius + stepFraction * availableWidth
                val kpColor = if (x in startX..endX) keyPointForegroundColor else keyPointColor
                drawCircle(
                    color = kpColor,
                    radius = keyPointRadius,
                    center = Offset(x, barHeight / 2f),
                )
            }
        }

        drawCircle(
            color = thumbColor,
            radius = thumbRadius * 0.72f * startThumbScale,
            center = Offset(startX, centerY)
        )
        drawCircle(
            color = thumbColor,
            radius = thumbRadius * 0.72f * endThumbScale,
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

    fun inheritStartKeyPoint() {
        startIsAtKeyPoint = endIsAtKeyPoint
    }

    fun inheritEndKeyPoint() {
        endIsAtKeyPoint = startIsAtKeyPoint
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

private fun resolveValueFromFraction(
    fraction: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    stepFractions: FloatArray,
    allKeyPointFractions: FloatArray,
    magnetThreshold: Float
): Float {
    val f = fraction.coerceIn(0f, 1f)
    val base = lerp(valueRange.start, valueRange.endInclusive, f)
    return when {
        steps > 0 -> snapValueToTick(base, stepFractions, valueRange.start, valueRange.endInclusive)
        allKeyPointFractions.isNotEmpty() -> {
            val closest = allKeyPointFractions.minByOrNull { abs(it - f) }
            if (closest != null && abs(f - closest) < magnetThreshold) {
                lerp(valueRange.start, valueRange.endInclusive, closest)
            } else base
        }

        else -> base
    }
}

private fun horizontalVisualFraction(offsetX: Float, sizeWidth: Int, sizeHeight: Int): Float {
    val thumbRadius = sizeHeight / 2f
    val availableWidth = (sizeWidth.toFloat() - 2f * thumbRadius).coerceAtLeast(0f)
    return if (availableWidth == 0f) 0f else ((offsetX - thumbRadius) / availableWidth).coerceIn(0f, 1f)
}

private fun verticalVisualFraction(offsetY: Float, sizeHeight: Int, sizeWidth: Int): Float {
    val thumbRadius = sizeWidth / 2f
    val availableHeight = (sizeHeight.toFloat() - 2f * thumbRadius).coerceAtLeast(0f)
    return if (availableHeight == 0f) 0f else ((offsetY - thumbRadius) / availableHeight).coerceIn(0f, 1f)
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
    val MinHeight = 28.dp

    /**
     * The radius of the key points on the [Slider] and [RangeSlider].
     */
    val KeyPointRadius = 3.855.dp

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
        backgroundColor: Color = MiuixTheme.colorScheme.sliderBackground,
        disabledBackgroundColor: Color = MiuixTheme.colorScheme.disabledSecondary,
        thumbColor: Color = MiuixTheme.colorScheme.onPrimary,
        disabledThumbColor: Color = MiuixTheme.colorScheme.disabledOnPrimary,
        keyPointColor: Color = MiuixTheme.colorScheme.sliderKeyPoint,
        keyPointForegroundColor: Color = MiuixTheme.colorScheme.sliderKeyPointForeground,
    ): SliderColors = SliderColors(
        foregroundColor = foregroundColor,
        disabledForegroundColor = disabledForegroundColor,
        backgroundColor = backgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
        thumbColor = thumbColor,
        disabledThumbColor = disabledThumbColor,
        keyPointColor = keyPointColor,
        keyPointForegroundColor = keyPointForegroundColor
    )
}

@Immutable
class SliderColors(
    private val foregroundColor: Color,
    private val disabledForegroundColor: Color,
    private val backgroundColor: Color,
    private val disabledBackgroundColor: Color,
    private val thumbColor: Color,
    private val disabledThumbColor: Color,
    private val keyPointColor: Color,
    private val keyPointForegroundColor: Color
) {
    @Stable
    internal fun foregroundColor(enabled: Boolean): Color =
        if (enabled) foregroundColor else disabledForegroundColor

    @Stable
    internal fun backgroundColor(enabled: Boolean): Color =
        if (enabled) backgroundColor else disabledBackgroundColor

    @Stable
    internal fun thumbColor(enabled: Boolean): Color =
        if (enabled) thumbColor else disabledThumbColor

    @Stable
    internal fun keyPointColor(): Color = keyPointColor

    @Stable
    internal fun keyPointForegroundColor(): Color = keyPointForegroundColor
}
