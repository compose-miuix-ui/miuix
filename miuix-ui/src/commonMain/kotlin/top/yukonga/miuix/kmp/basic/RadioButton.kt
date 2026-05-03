// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.SinkFeedback
import top.yukonga.miuix.kmp.utils.pressable

/**
 * A [RadioButton] component with Miuix style.
 *
 * Displays a checkmark indicator when selected, matching the miuix-classic SingleChoicePreference
 * visual style. When unselected, no indicator is shown.
 *
 * @param selected Whether the [RadioButton] is currently selected.
 * @param onClick The callback to be called when the [RadioButton] is clicked. The caller is
 *   responsible for updating the state. If `null`, the [RadioButton] is not interactive.
 * @param modifier The modifier to be applied to the [RadioButton].
 * @param colors The [RadioButtonColors] of the [RadioButton].
 * @param enabled Whether the [RadioButton] is enabled.
 */
@Composable
fun RadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    colors: RadioButtonColors = RadioButtonDefaults.radioButtonColors(),
    enabled: Boolean = true,
) {
    val currentOnClickState = rememberUpdatedState(onClick)
    val hapticFeedback = LocalHapticFeedback.current
    val currentHapticFeedback by rememberUpdatedState(hapticFeedback)

    val transition = updateTransition(selected, label = "RadioButtonTransition")

    val color = colors.color(enabled)

    val checkAlphaState = transition.animateFloat(
        transitionSpec = {
            if (targetState) {
                tween(durationMillis = 10, easing = FastOutSlowInEasing)
            } else {
                tween(durationMillis = 150, easing = FastOutSlowInEasing)
            }
        },
        label = "CheckAlpha",
    ) { if (it) 1f else 0f }

    val trimEndState = transition.animateFloat(
        transitionSpec = {
            tween(durationMillis = 300, easing = FastOutSlowInEasing)
        },
        label = "TrimEnd",
    ) { if (it) 1f else 0f }

    val capsuleShape = CircleShape
    val checkPath = remember { Path() }
    val sinkFeedback = remember { SinkFeedback(sinkAmount = 0.85f, animationSpec = spring(0.99f, 986.96f)) }

    val finalModifier = if (onClick != null) {
        Modifier.selectable(
            selected = selected,
            onClick = {
                currentOnClickState.value?.invoke()
                currentHapticFeedback.performHapticFeedback(
                    if (selected) HapticFeedbackType.ToggleOff else HapticFeedbackType.ToggleOn,
                )
            },
            enabled = enabled,
            role = Role.RadioButton,
            interactionSource = null,
            indication = null,
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
                // Path coordinates from miuix-classic SingleChoicePreference
                // Original viewport: 56x56, path: M 10.9 29 L 23.1 40.8 L 44 16
                val viewportSize = 56f
                val strokeWidth = 7.0f / viewportSize * size.width
                val centerX = size.width / 2
                val centerY = size.height / 2
                val viewportCenter = viewportSize / 2

                val startPoint = Offset(
                    centerX + ((10.9f - viewportCenter) / viewportSize * size.width),
                    centerY + ((29f - viewportCenter) / viewportSize * size.height),
                )
                val middlePoint = Offset(
                    centerX + ((23.1f - viewportCenter) / viewportSize * size.width),
                    centerY + ((40.8f - viewportCenter) / viewportSize * size.height),
                )
                val endPoint = Offset(
                    centerX + ((44f - viewportCenter) / viewportSize * size.width),
                    centerY + ((16f - viewportCenter) / viewportSize * size.height),
                )

                val cache = CheckCache(startPoint, middlePoint, endPoint)

                val stroke = Stroke(
                    width = strokeWidth,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    miter = 10.0f,
                )

                onDrawBehind {
                    drawTrimmedCheck(
                        color = color,
                        alpha = checkAlphaState.value,
                        trimEnd = trimEndState.value,
                        path = checkPath,
                        cache = cache,
                        stroke = stroke,
                    )
                }
            }
            .then(finalModifier),
    ) {}
}

private data class CheckCache(
    val startPoint: Offset,
    val middlePoint: Offset,
    val endPoint: Offset,
)

private fun DrawScope.drawTrimmedCheck(
    color: Color,
    alpha: Float,
    trimEnd: Float,
    path: Path,
    cache: CheckCache,
    stroke: Stroke,
) {
    if (alpha <= 0f || trimEnd <= 0f) return

    path.rewind()

    val firstSegmentLength = (cache.middlePoint - cache.startPoint).getDistance()
    val secondSegmentLength = (cache.endPoint - cache.middlePoint).getDistance()
    val totalLength = firstSegmentLength + secondSegmentLength
    val endDistance = totalLength * trimEnd

    path.moveTo(cache.startPoint.x, cache.startPoint.y)

    if (endDistance <= firstSegmentLength) {
        val ratio = endDistance / firstSegmentLength
        path.lineTo(
            cache.startPoint.x + (cache.middlePoint.x - cache.startPoint.x) * ratio,
            cache.startPoint.y + (cache.middlePoint.y - cache.startPoint.y) * ratio,
        )
    } else {
        path.lineTo(cache.middlePoint.x, cache.middlePoint.y)
        val ratio = ((endDistance - firstSegmentLength) / secondSegmentLength).coerceAtMost(1f)
        path.lineTo(
            cache.middlePoint.x + (cache.endPoint.x - cache.middlePoint.x) * ratio,
            cache.middlePoint.y + (cache.endPoint.y - cache.middlePoint.y) * ratio,
        )
    }

    drawPath(
        path = path,
        color = color,
        alpha = alpha,
        style = stroke,
    )
}

object RadioButtonDefaults {
    @Composable
    fun radioButtonColors(
        selectedColor: Color = MiuixTheme.colorScheme.primary,
        disabledSelectedColor: Color = MiuixTheme.colorScheme.disabledPrimary,
    ): RadioButtonColors = remember(
        selectedColor,
        disabledSelectedColor,
    ) {
        RadioButtonColors(
            selectedColor = selectedColor,
            disabledSelectedColor = disabledSelectedColor,
        )
    }
}

@Immutable
data class RadioButtonColors(
    private val selectedColor: Color,
    private val disabledSelectedColor: Color,
) {
    internal fun color(enabled: Boolean): Color = if (enabled) selectedColor else disabledSelectedColor
}
