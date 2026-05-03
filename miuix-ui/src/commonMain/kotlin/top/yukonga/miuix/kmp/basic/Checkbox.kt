// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.triStateToggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.SinkFeedback
import top.yukonga.miuix.kmp.utils.pressable

/**
 * A [Checkbox] component with Miuix style, supporting three states: On, Off, and Indeterminate.
 *
 * @param state The current [ToggleableState] of the [Checkbox].
 * @param onClick The callback to be called when the [Checkbox] is clicked. The caller is
 *   responsible for updating the state. If `null`, the [Checkbox] is not interactive.
 * @param modifier The modifier to be applied to the [Checkbox].
 * @param colors The [CheckboxColors] of the [Checkbox].
 * @param enabled Whether the [Checkbox] is enabled.
 */
@Composable
fun Checkbox(
    state: ToggleableState,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    colors: CheckboxColors = CheckboxDefaults.checkboxColors(),
    enabled: Boolean = true,
) {
    val currentOnClickState = rememberUpdatedState(onClick)
    val hapticFeedback = LocalHapticFeedback.current
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)

    val transition = updateTransition(state, label = "CheckboxTransition")

    val backgroundColorState = transition.animateColor(
        transitionSpec = { tween(durationMillis = 300, easing = FastOutSlowInEasing) },
        label = "BackgroundColor",
    ) {
        if (it != ToggleableState.Off) colors.checkedBackgroundColor(enabled) else colors.uncheckedBackgroundColor(enabled)
    }

    val foregroundColorState = transition.animateColor(
        transitionSpec = { tween(durationMillis = 300, easing = FastOutSlowInEasing) },
        label = "ForegroundColor",
    ) {
        if (it != ToggleableState.Off) colors.checkedForegroundColor(enabled) else colors.uncheckedForegroundColor(enabled)
    }

    val checkAlphaState = transition.animateFloat(
        transitionSpec = {
            if (targetState != ToggleableState.Off) {
                tween(durationMillis = 10, easing = FastOutSlowInEasing)
            } else {
                tween(durationMillis = 150, easing = FastOutSlowInEasing)
            }
        },
        label = "CheckAlpha",
    ) { if (it != ToggleableState.Off) 1f else 0f }

    val checkStartTrimState = transition.animateFloat(
        transitionSpec = {
            if (targetState != ToggleableState.Off) {
                tween(durationMillis = 200, easing = FastOutSlowInEasing)
            } else {
                keyframes {
                    durationMillis = 300
                    0.1f at 300
                }
            }
        },
        label = "CheckStartTrim",
    ) { if (it != ToggleableState.Off) 0.186f else 0.1f }

    val checkEndTrimState = transition.animateFloat(
        transitionSpec = {
            if (targetState != ToggleableState.Off) {
                keyframes {
                    durationMillis = 300
                    0.85f at 200 using FastOutSlowInEasing
                    0.803f at 300 using FastOutSlowInEasing
                }
            } else {
                keyframes {
                    durationMillis = 300
                    0.1f at 300
                }
            }
        },
        label = "CheckEndTrim",
    ) { if (it != ToggleableState.Off) 0.803f else 0.1f }

    val crossCenterGravitationState = transition.animateFloat(
        transitionSpec = {
            if (targetState == ToggleableState.Indeterminate) {
                tween(durationMillis = 200, easing = FastOutSlowInEasing)
            } else {
                tween(durationMillis = 150, easing = FastOutSlowInEasing)
            }
        },
        label = "CrossCenterGravitation",
    ) { if (it == ToggleableState.Indeterminate) 1f else 0f }

    val capsuleShape = CircleShape
    val checkPath = remember { Path() }
    val sinkFeedback = remember { SinkFeedback(sinkAmount = 0.85f, animationSpec = spring(0.99f, 986.96f)) }

    val finalModifier = if (onClick != null) {
        Modifier.triStateToggleable(
            state = state,
            onClick = {
                currentOnClickState.value?.invoke()
                currentHapticFeedback.performHapticFeedback(
                    when (state) {
                        ToggleableState.Off -> HapticFeedbackType.ToggleOn
                        ToggleableState.On -> HapticFeedbackType.ToggleOff
                        ToggleableState.Indeterminate -> HapticFeedbackType.SegmentTick
                    },
                )
            },
            enabled = enabled,
            role = Role.Checkbox,
            interactionSource = null,
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .requiredSize(26.dp)
            .pressable(
                interactionSource = remember { MutableInteractionSource() },
                indication = sinkFeedback,
                enabled = enabled,
                delay = null,
            )
            .clip(capsuleShape)
            .drawWithCache {
                val viewportSize = 23f
                val strokeWidth = size.width * 0.09f
                val centerX = size.width / 2
                val centerY = size.height / 2
                val viewportCenterX = viewportSize / 2
                val viewportCenterY = viewportSize / 2

                val startPoint = Offset(
                    centerX + ((5f - viewportCenterX) / viewportSize * size.width),
                    centerY + ((9.4f - viewportCenterY) / viewportSize * size.height),
                )
                val middlePoint = Offset(
                    centerX + ((10.3f - viewportCenterX) / viewportSize * size.width),
                    centerY + ((14.9f - viewportCenterY) / viewportSize * size.height),
                )
                val endPoint = Offset(
                    centerX + ((17.9f - viewportCenterX) / viewportSize * size.width),
                    centerY + ((5.1f - viewportCenterY) / viewportSize * size.height),
                )

                val cache = CheckmarkCache(
                    startPoint,
                    middlePoint,
                    endPoint,
                    centerX,
                    centerY,
                    strokeWidth,
                )

                val stroke = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    miter = 10.0f,
                )

                onDrawBehind {
                    drawCircle(backgroundColorState.value)
                    drawTrimmedCheckmark(
                        color = foregroundColorState.value,
                        alpha = checkAlphaState.value,
                        trimStart = checkStartTrimState.value,
                        trimEnd = checkEndTrimState.value,
                        crossCenterGravitation = crossCenterGravitationState.value,
                        path = checkPath,
                        cache = cache,
                        stroke = stroke,
                    )
                }
            }
            .then(finalModifier),
    ) {}
}

private data class CheckmarkCache(
    val startPoint: Offset,
    val middlePoint: Offset,
    val endPoint: Offset,
    val centerX: Float,
    val centerY: Float,
    val strokeWidth: Float,
)

private fun DrawScope.drawTrimmedCheckmark(
    color: Color,
    alpha: Float = 1f,
    trimStart: Float,
    trimEnd: Float,
    crossCenterGravitation: Float,
    path: Path,
    cache: CheckmarkCache,
    stroke: Stroke,
) {
    path.rewind()

    val gravitatedStart = Offset(
        cache.startPoint.x,
        lerp(cache.startPoint.y, cache.centerY, crossCenterGravitation),
    )
    val gravitatedMiddle = Offset(
        lerp(cache.middlePoint.x, cache.centerX, crossCenterGravitation),
        lerp(cache.middlePoint.y, cache.centerY, crossCenterGravitation),
    )
    val gravitatedEnd = Offset(
        cache.endPoint.x,
        lerp(cache.endPoint.y, cache.centerY, crossCenterGravitation),
    )

    val firstSegmentLength = (gravitatedMiddle - gravitatedStart).getDistance()
    val secondSegmentLength = (gravitatedEnd - gravitatedMiddle).getDistance()
    val totalLength = firstSegmentLength + secondSegmentLength

    val startDistance = totalLength * trimStart
    val endDistance = totalLength * trimEnd

    if (startDistance < firstSegmentLength && endDistance > 0) {
        val segStartRatio = (startDistance / firstSegmentLength).coerceIn(0f, 1f)
        val segEndRatio = (endDistance / firstSegmentLength).coerceIn(0f, 1f)

        val startX = gravitatedStart.x + (gravitatedMiddle.x - gravitatedStart.x) * segStartRatio
        val startY = gravitatedStart.y + (gravitatedMiddle.y - gravitatedStart.y) * segStartRatio
        val endX = gravitatedStart.x + (gravitatedMiddle.x - gravitatedStart.x) * segEndRatio
        val endY = gravitatedStart.y + (gravitatedMiddle.y - gravitatedStart.y) * segEndRatio

        path.moveTo(startX, startY)
        path.lineTo(endX, endY)
    }

    if (endDistance > firstSegmentLength) {
        val segStartRatio = ((startDistance - firstSegmentLength) / secondSegmentLength).coerceIn(0f, 1f)
        val segEndRatio = ((endDistance - firstSegmentLength) / secondSegmentLength).coerceIn(0f, 1f)

        val startX = gravitatedMiddle.x + (gravitatedEnd.x - gravitatedMiddle.x) * segStartRatio
        val startY = gravitatedMiddle.y + (gravitatedEnd.y - gravitatedMiddle.y) * segStartRatio
        val endX = gravitatedMiddle.x + (gravitatedEnd.x - gravitatedMiddle.x) * segEndRatio
        val endY = gravitatedMiddle.y + (gravitatedEnd.y - gravitatedMiddle.y) * segEndRatio

        if (startDistance < firstSegmentLength) {
            path.lineTo(endX, endY)
        } else {
            path.moveTo(startX, startY)
            path.lineTo(endX, endY)
        }
    }

    drawPath(
        path = path,
        color = color,
        alpha = alpha,
        style = stroke,
    )
}

object CheckboxDefaults {
    @Composable
    fun checkboxColors(
        checkedForegroundColor: Color = MiuixTheme.colorScheme.onPrimary,
        uncheckedForegroundColor: Color = MiuixTheme.colorScheme.secondary,
        disabledCheckedForegroundColor: Color = MiuixTheme.colorScheme.disabledOnPrimary,
        disabledUncheckedForegroundColor: Color = MiuixTheme.colorScheme.disabledOnPrimary,
        checkedBackgroundColor: Color = MiuixTheme.colorScheme.primary,
        uncheckedBackgroundColor: Color = MiuixTheme.colorScheme.secondary,
        disabledCheckedBackgroundColor: Color = MiuixTheme.colorScheme.disabledPrimary,
        disabledUncheckedBackgroundColor: Color = MiuixTheme.colorScheme.disabledSecondary,
    ): CheckboxColors = remember(
        checkedForegroundColor,
        uncheckedForegroundColor,
        disabledCheckedForegroundColor,
        disabledUncheckedForegroundColor,
        checkedBackgroundColor,
        uncheckedBackgroundColor,
        disabledCheckedBackgroundColor,
        disabledUncheckedBackgroundColor,
    ) {
        CheckboxColors(
            checkedForegroundColor = checkedForegroundColor,
            uncheckedForegroundColor = uncheckedForegroundColor,
            disabledCheckedForegroundColor = disabledCheckedForegroundColor,
            disabledUncheckedForegroundColor = disabledUncheckedForegroundColor,
            checkedBackgroundColor = checkedBackgroundColor,
            uncheckedBackgroundColor = uncheckedBackgroundColor,
            disabledCheckedBackgroundColor = disabledCheckedBackgroundColor,
            disabledUncheckedBackgroundColor = disabledUncheckedBackgroundColor,
        )
    }
}

@Immutable
data class CheckboxColors(
    private val checkedForegroundColor: Color,
    private val uncheckedForegroundColor: Color,
    private val disabledCheckedForegroundColor: Color,
    private val disabledUncheckedForegroundColor: Color,
    private val checkedBackgroundColor: Color,
    private val uncheckedBackgroundColor: Color,
    private val disabledCheckedBackgroundColor: Color,
    private val disabledUncheckedBackgroundColor: Color,
) {
    internal fun checkedForegroundColor(enabled: Boolean): Color = if (enabled) checkedForegroundColor else disabledCheckedForegroundColor

    internal fun uncheckedForegroundColor(enabled: Boolean): Color = if (enabled) uncheckedForegroundColor else disabledUncheckedForegroundColor

    internal fun checkedBackgroundColor(enabled: Boolean): Color = if (enabled) checkedBackgroundColor else disabledCheckedBackgroundColor

    internal fun uncheckedBackgroundColor(enabled: Boolean): Color = if (enabled) uncheckedBackgroundColor else disabledUncheckedBackgroundColor
}
