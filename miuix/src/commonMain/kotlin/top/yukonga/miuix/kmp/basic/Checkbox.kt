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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
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
import androidx.compose.ui.unit.dp
import com.kyant.shapes.Capsule
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.SinkFeedback
import top.yukonga.miuix.kmp.utils.pressable

/**
 * A [Checkbox] component with Miuix style.
 *
 * @param checked The current state of the [Checkbox].
 * @param onCheckedChange The callback to be called when the state of the [Checkbox] changes.
 * @param modifier The modifier to be applied to the [Checkbox].
 * @param colors The [CheckboxColors] of the [Checkbox].
 * @param enabled Whether the [Checkbox] is enabled.
 */
@Composable
fun Checkbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    colors: CheckboxColors = CheckboxDefaults.checkboxColors(),
    enabled: Boolean = true,
) {
    val currentOnCheckedChange by rememberUpdatedState(onCheckedChange)
    val hapticFeedback = LocalHapticFeedback.current

    val transition = updateTransition(checked, label = "CheckboxTransition")

    val backgroundColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = 300, easing = FastOutSlowInEasing) },
        label = "BackgroundColor",
    ) {
        if (it) colors.checkedBackgroundColor(enabled) else colors.uncheckedBackgroundColor(enabled)
    }

    val foregroundColor by transition.animateColor(
        transitionSpec = { tween(durationMillis = 300, easing = FastOutSlowInEasing) },
        label = "ForegroundColor",
    ) {
        if (it) colors.checkedForegroundColor(enabled) else colors.uncheckedForegroundColor(enabled)
    }

    val checkAlpha by transition.animateFloat(
        transitionSpec = {
            if (targetState) {
                tween(durationMillis = 10, easing = FastOutSlowInEasing)
            } else {
                tween(durationMillis = 150, easing = FastOutSlowInEasing)
            }
        },
        label = "CheckAlpha",
    ) { if (it) 1f else 0f }

    val checkStartTrim by transition.animateFloat(
        transitionSpec = {
            if (targetState) {
                tween(durationMillis = 200, easing = FastOutSlowInEasing)
            } else {
                keyframes {
                    durationMillis = 300
                    0.1f at 300
                }
            }
        },
        label = "CheckStartTrim",
    ) { if (it) 0.186f else 0.1f }

    val checkEndTrim by transition.animateFloat(
        transitionSpec = {
            if (targetState) {
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
    ) { if (it) 0.803f else 0.1f }

    val checkPath = remember { Path() }

    val finalModifier = if (currentOnCheckedChange != null) {
        Modifier.toggleable(
            value = checked,
            onValueChange = {
                currentOnCheckedChange!!(it)
                hapticFeedback.performHapticFeedback(
                    if (it) HapticFeedbackType.ToggleOn else HapticFeedbackType.ToggleOff,
                )
            },
            enabled = enabled,
            role = Role.Checkbox,
            indication = null,
            interactionSource = null,
        )
    } else {
        modifier
    }

    Box(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .requiredSize(26.dp)
            .pressable(
                interactionSource = remember { MutableInteractionSource() },
                indication = SinkFeedback(
                    sinkAmount = 0.85f,
                    animationSpec = spring(0.99f, 986.96f),
                ),
                enabled = enabled,
                delay = null,
            )
            .clip(Capsule())
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

                val firstSegmentLength = (middlePoint - startPoint).getDistance()
                val secondSegmentLength = (endPoint - middlePoint).getDistance()

                val cache = CheckmarkCache(
                    startPoint,
                    middlePoint,
                    endPoint,
                    firstSegmentLength,
                    secondSegmentLength,
                    strokeWidth,
                )

                onDrawBehind {
                    drawCircle(backgroundColor)
                    drawTrimmedCheckmark(
                        color = foregroundColor,
                        alpha = checkAlpha,
                        trimStart = checkStartTrim,
                        trimEnd = checkEndTrim,
                        path = checkPath,
                        cache = cache,
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
    val firstSegmentLength: Float,
    val secondSegmentLength: Float,
    val strokeWidth: Float,
)

private fun DrawScope.drawTrimmedCheckmark(
    color: Color,
    alpha: Float = 1f,
    trimStart: Float,
    trimEnd: Float,
    path: Path,
    cache: CheckmarkCache,
) {
    path.rewind()

    val totalLength = cache.firstSegmentLength + cache.secondSegmentLength

    val startDistance = totalLength * trimStart
    val endDistance = totalLength * trimEnd

    if (startDistance < cache.firstSegmentLength && endDistance > 0) {
        val segStartRatio = (startDistance / cache.firstSegmentLength).coerceIn(0f, 1f)
        val segEndRatio = (endDistance / cache.firstSegmentLength).coerceIn(0f, 1f)

        val startX = cache.startPoint.x + (cache.middlePoint.x - cache.startPoint.x) * segStartRatio
        val startY = cache.startPoint.y + (cache.middlePoint.y - cache.startPoint.y) * segStartRatio
        val endX = cache.startPoint.x + (cache.middlePoint.x - cache.startPoint.x) * segEndRatio
        val endY = cache.startPoint.y + (cache.middlePoint.y - cache.startPoint.y) * segEndRatio

        path.moveTo(startX, startY)
        path.lineTo(endX, endY)
    }

    if (endDistance > cache.firstSegmentLength) {
        val segStartRatio = ((startDistance - cache.firstSegmentLength) / cache.secondSegmentLength).coerceIn(0f, 1f)
        val segEndRatio = ((endDistance - cache.firstSegmentLength) / cache.secondSegmentLength).coerceIn(0f, 1f)

        val startX = cache.middlePoint.x + (cache.endPoint.x - cache.middlePoint.x) * segStartRatio
        val startY = cache.middlePoint.y + (cache.endPoint.y - cache.middlePoint.y) * segStartRatio
        val endX = cache.middlePoint.x + (cache.endPoint.x - cache.middlePoint.x) * segEndRatio
        val endY = cache.middlePoint.y + (cache.endPoint.y - cache.middlePoint.y) * segEndRatio

        if (startDistance < cache.firstSegmentLength) {
            path.lineTo(endX, endY)
        } else {
            path.moveTo(startX, startY)
            path.lineTo(endX, endY)
        }
        path.lineTo(endX, endY)
    }

    drawPath(
        path = path,
        color = color,
        alpha = alpha,
        style = Stroke(
            width = cache.strokeWidth,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round,
            miter = 10.0f,
        ),
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
    ): CheckboxColors = CheckboxColors(
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
    @Stable
    internal fun checkedForegroundColor(enabled: Boolean): Color = if (enabled) checkedForegroundColor else disabledCheckedForegroundColor

    @Stable
    internal fun uncheckedForegroundColor(enabled: Boolean): Color = if (enabled) uncheckedForegroundColor else disabledUncheckedForegroundColor

    @Stable
    internal fun checkedBackgroundColor(enabled: Boolean): Color = if (enabled) checkedBackgroundColor else disabledCheckedBackgroundColor

    @Stable
    internal fun uncheckedBackgroundColor(enabled: Boolean): Color = if (enabled) uncheckedBackgroundColor else disabledUncheckedBackgroundColor
}
