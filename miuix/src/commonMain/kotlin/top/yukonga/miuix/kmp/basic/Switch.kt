// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mocharealm.gaze.capsule.ContinuousCapsule
import top.yukonga.miuix.kmp.theme.LocalColors
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme.isDynamicColor
import kotlin.math.absoluteValue

/**
 * A [Switch] component with Miuix style.
 *
 * @param checked The checked state of the [Switch].
 * @param onCheckedChange The callback to be called when the state of the [Switch] changes.
 * @param modifier The modifier to be applied to the [Switch].
 * @param colors The [SwitchColors] of the [Switch].
 * @param enabled Whether the [Switch] is enabled.
 */
@Composable
fun Switch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    colors: SwitchColors = SwitchDefaults.switchColors(),
    enabled: Boolean = true,
) {
    val currentOnCheckedChange by rememberUpdatedState(onCheckedChange)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDragged by interactionSource.collectIsDraggedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val hapticFeedback = LocalHapticFeedback.current
    var hasVibrated by remember { mutableStateOf(false) }
    var hasVibratedOnce by remember { mutableStateOf(false) }

    val springSpec = remember { spring<Dp>(dampingRatio = 0.6f, stiffness = 987f) }

    var dragOffset by remember { mutableFloatStateOf(0f) }
    val thumbOffset by animateDpAsState(
        targetValue = if (checked) {
            if (!enabled) {
                25.dp
            } else if (isPressed || isDragged || isHovered) {
                23.75.dp
            } else {
                25.dp
            }
        } else {
            if (!enabled) {
                4.dp
            } else if (isPressed || isDragged || isHovered) {
                2.75.dp
            } else {
                4.dp
            }
        } + dragOffset.dp,
        animationSpec = springSpec,
    )

    val thumbSize by animateDpAsState(
        targetValue = if (!enabled) {
            20.dp
        } else if (isPressed || isDragged || isHovered) {
            22.5.dp
        } else {
            20.dp
        },
        animationSpec = springSpec,
    )

    val thumbColor by animateColorAsState(
        if (checked) colors.checkedThumbColor(enabled) else colors.uncheckedThumbColor(enabled),
    )

    val backgroundColor by animateColorAsState(
        if (checked) colors.checkedTrackColor(enabled) else colors.uncheckedTrackColor(enabled),
        animationSpec = tween(durationMillis = 200),
    )

    val toggleableModifier = if (currentOnCheckedChange != null) {
        Modifier.toggleable(
            value = checked,
            onValueChange = {
                currentOnCheckedChange!!(it)
                hapticFeedback.performHapticFeedback(
                    if (it) HapticFeedbackType.ToggleOn else HapticFeedbackType.ToggleOff,
                )
            },
            enabled = enabled,
            role = Role.Switch,
            interactionSource = interactionSource,
            indication = null,
        )
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .size(49.dp, 28.dp)
            .clip(ContinuousCapsule)
            .drawBehind {
                drawRect(backgroundColor)
            }
            .hoverable(
                interactionSource = interactionSource,
                enabled = enabled,
            )
            .then(toggleableModifier),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = thumbOffset)
                .size(thumbSize)
                .drawBehind {
                    drawCircle(color = thumbColor)
                }
                .pointerInput(checked, enabled) {
                    if (!enabled) return@pointerInput
                    awaitPointerEventScope {
                        while (true) {
                            val down = awaitFirstDown()

                            var startedDrag = false
                            val dragInteraction = DragInteraction.Start()
                            var lastPosition = down.position
                            val pointerId = down.id

                            var accumulatedX = 0f
                            var accumulatedY = 0f
                            var rawDragOffset = 0f

                            while (true) {
                                val event = awaitPointerEvent()
                                var idx = -1
                                val total = event.changes.size
                                var j = 0
                                while (j < total) {
                                    if (event.changes[j].id == pointerId) {
                                        idx = j
                                        break
                                    }
                                    j++
                                }
                                val change = if (idx >= 0) event.changes[idx] else event.changes[0]

                                if (!change.pressed) {
                                    if (startedDrag) {
                                        if (dragOffset.absoluteValue > 21f / 2) currentOnCheckedChange?.invoke(!checked)
                                        if (!hasVibratedOnce && dragOffset.absoluteValue >= 1f) {
                                            if ((checked && dragOffset <= -11f) || (!checked && dragOffset <= 10f)) {
                                                hapticFeedback.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            } else if ((checked && dragOffset >= -10f) || (!checked && dragOffset >= 11f)) {
                                                hapticFeedback.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            }
                                        }
                                        interactionSource.tryEmit(DragInteraction.Stop(dragInteraction))
                                    }
                                    dragOffset = 0f
                                    break
                                }

                                val dx = change.position.x - lastPosition.x
                                val dy = change.position.y - lastPosition.y
                                lastPosition = change.position

                                accumulatedX += dx
                                accumulatedY += dy

                                if (!startedDrag) {
                                    val absX = accumulatedX.absoluteValue
                                    val absY = accumulatedY.absoluteValue
                                    val touchSlop = viewConfiguration.touchSlop

                                    if (absY > touchSlop && absY > absX) {
                                        dragOffset = 0f
                                        break
                                    }

                                    if (absX > touchSlop) {
                                        startedDrag = true
                                        interactionSource.tryEmit(dragInteraction)
                                        hasVibrated = true
                                        hasVibratedOnce = false
                                    }
                                }

                                if (startedDrag) {
                                    rawDragOffset += dx / 2

                                    dragOffset = if (checked) {
                                        rawDragOffset.coerceIn(-21f, 0f)
                                    } else {
                                        rawDragOffset.coerceIn(0f, 21f)
                                    }
                                    change.consume()

                                    if (dragOffset in -11f..-10f || dragOffset in 10f..11f) {
                                        hasVibratedOnce = false
                                    } else if (dragOffset in -20f..-1f || dragOffset in 1f..20f) {
                                        hasVibrated = false
                                    } else if (!hasVibrated) {
                                        if ((checked && dragOffset == -21f) || (!checked && dragOffset == 0f)) {
                                            hapticFeedback.performHapticFeedback(HapticFeedbackType.ToggleOff)
                                            hasVibrated = true
                                            hasVibratedOnce = true
                                        } else if ((checked && dragOffset == 0f) || (!checked && dragOffset == 21f)) {
                                            hapticFeedback.performHapticFeedback(HapticFeedbackType.ToggleOn)
                                            hasVibrated = true
                                            hasVibratedOnce = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
        )
    }
}

object SwitchDefaults {

    /**
     * The default colors for the [Switch].
     */
    @Composable
    fun switchColors(
        checkedThumbColor: Color = if (isDynamicColor) LocalColors.current.onPrimary else MiuixTheme.colorScheme.onPrimary,
        uncheckedThumbColor: Color = if (isDynamicColor) LocalColors.current.onSurface.copy(0.38f) else MiuixTheme.colorScheme.onSecondary,
        disabledCheckedThumbColor: Color = if (isDynamicColor) LocalColors.current.surface else MiuixTheme.colorScheme.disabledOnPrimary,
        disabledUncheckedThumbColor: Color = MiuixTheme.colorScheme.disabledOnSecondary,
        checkedTrackColor: Color = MiuixTheme.colorScheme.primary,
        uncheckedTrackColor: Color = MiuixTheme.colorScheme.secondary,
        disabledCheckedTrackColor: Color = MiuixTheme.colorScheme.disabledPrimary,
        disabledUncheckedTrackColor: Color = MiuixTheme.colorScheme.disabledSecondary,
    ): SwitchColors = SwitchColors(
        checkedThumbColor = checkedThumbColor,
        uncheckedThumbColor = uncheckedThumbColor,
        disabledCheckedThumbColor = disabledCheckedThumbColor,
        disabledUncheckedThumbColor = disabledUncheckedThumbColor,
        checkedTrackColor = checkedTrackColor,
        uncheckedTrackColor = uncheckedTrackColor,
        disabledCheckedTrackColor = disabledCheckedTrackColor,
        disabledUncheckedTrackColor = disabledUncheckedTrackColor,
    )
}

@Immutable
data class SwitchColors(
    private val checkedThumbColor: Color,
    private val uncheckedThumbColor: Color,
    private val disabledCheckedThumbColor: Color,
    private val disabledUncheckedThumbColor: Color,
    private val checkedTrackColor: Color,
    private val uncheckedTrackColor: Color,
    private val disabledCheckedTrackColor: Color,
    private val disabledUncheckedTrackColor: Color,
) {
    @Stable
    internal fun checkedThumbColor(enabled: Boolean): Color = if (enabled) checkedThumbColor else disabledCheckedThumbColor

    @Stable
    internal fun uncheckedThumbColor(enabled: Boolean): Color = if (enabled) uncheckedThumbColor else disabledUncheckedThumbColor

    @Stable
    internal fun checkedTrackColor(enabled: Boolean): Color = if (enabled) checkedTrackColor else disabledCheckedTrackColor

    @Stable
    internal fun uncheckedTrackColor(enabled: Boolean): Color = if (enabled) uncheckedTrackColor else disabledUncheckedTrackColor
}
