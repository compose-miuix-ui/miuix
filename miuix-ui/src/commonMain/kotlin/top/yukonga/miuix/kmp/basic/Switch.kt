// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.toggleableState
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.fastRoundToInt
import top.yukonga.miuix.kmp.anim.folmeSpring
import top.yukonga.miuix.kmp.theme.LocalColors
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme.isDynamicColor
import kotlin.math.absoluteValue

private val ThumbStart = 4.dp
private val ThumbTravel = 21.dp
private val ThumbPositionSpring = folmeSpring(damping = 0.6f, response = 0.346f, visibilityThreshold = 0.001f)

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
    val currentChecked by rememberUpdatedState(checked)
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    val currentHapticFeedback by rememberUpdatedState(LocalHapticFeedback.current)
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val hasCallback = onCheckedChange != null
    val canDrag = enabled && hasCallback
    var isDragging by remember { mutableStateOf(false) }
    var dragPosition by remember { mutableFloatStateOf(Float.NaN) }
    var completedDrags by remember { mutableIntStateOf(0) }
    var startPosition by remember { mutableFloatStateOf(0f) }
    var rawPosition by remember { mutableFloatStateOf(0f) }
    var lastHaptic by remember { mutableStateOf<Boolean?>(null) }
    val travelPx = with(LocalDensity.current) { ThumbTravel.toPx() }

    val capsuleShape = CircleShape
    val thumbScaleSpringSpec = remember { spring<Float>(dampingRatio = 0.6f, stiffness = 987f) }

    val thumbPosition = remember { Animatable(if (checked) 1f else 0f) }
    LaunchedEffect(checked, isDragging, canDrag, completedDrags) {
        if (isDragging && canDrag) return@LaunchedEffect
        if (!dragPosition.isNaN()) {
            thumbPosition.snapTo(dragPosition)
            dragPosition = Float.NaN
        }
        thumbPosition.animateTo(if (checked) 1f else 0f, ThumbPositionSpring)
    }
    val finishDrag by rememberUpdatedState<(Boolean) -> Unit> { released ->
        if (isDragging) {
            val position = dragPosition
            isDragging = false
            completedDrags++
            if (released && canDrag) {
                val target = position >= 0.5f
                if (target != currentChecked) currentOnCheckedChange?.invoke(target)
                if (lastHaptic != target && (position - startPosition).absoluteValue >= 1f / ThumbTravel.value) {
                    currentHapticFeedback.performHapticFeedback(if (target) HapticFeedbackType.ToggleOn else HapticFeedbackType.ToggleOff)
                }
            }
        }
    }
    val dragInteractionSource = remember(interactionSource) {
        SwitchDragInteractionSource(interactionSource) { released -> finishDrag(released) }
    }
    val draggableState = rememberDraggableState { amount ->
        if (isDragging) {
            rawPosition += amount / travelPx
            val previousPosition = dragPosition
            dragPosition = rawPosition.fastCoerceIn(0f, 1f)
            if (dragPosition != previousPosition && (dragPosition == 0f || dragPosition == 1f)) {
                val reachedOn = dragPosition == 1f
                currentHapticFeedback.performHapticFeedback(if (reachedOn) HapticFeedbackType.ToggleOn else HapticFeedbackType.ToggleOff)
                lastHaptic = reachedOn
            }
        }
    }

    val thumbScaleState = animateFloatAsState(
        targetValue = if (!enabled) {
            1f
        } else if (isPressed || isDragging || isHovered) {
            1.127f
        } else {
            1f
        },
        animationSpec = thumbScaleSpringSpec,
    )

    val thumbColorState = animateColorAsState(
        if (checked) colors.checkedThumbColor(enabled) else colors.uncheckedThumbColor(enabled),
    )

    val backgroundColorState = animateColorAsState(
        if (checked) colors.checkedTrackColor(enabled) else colors.uncheckedTrackColor(enabled),
        animationSpec = spring(dampingRatio = 0.99f, stiffness = 438.6f),
    )

    val toggleableModifier = if (hasCallback) {
        remember(checked, enabled, interactionSource) {
            Modifier.toggleable(
                value = checked,
                onValueChange = { v ->
                    currentOnCheckedChange?.invoke(v)
                    currentHapticFeedback.performHapticFeedback(
                        if (v) HapticFeedbackType.ToggleOn else HapticFeedbackType.ToggleOff,
                    )
                },
                enabled = enabled,
                role = Role.Switch,
                interactionSource = interactionSource,
            )
        }
    } else {
        Modifier.semantics {
            role = Role.Switch
            toggleableState = ToggleableState(checked)
            if (!enabled) disabled()
        }
    }

    Box(
        modifier = modifier
            .wrapContentSize(Alignment.Center)
            .size(49.dp, 28.dp)
            .clip(capsuleShape)
            .drawBehind {
                drawRect(backgroundColorState.value)
            }
            .hoverable(
                interactionSource = interactionSource,
                enabled = enabled,
            )
            .then(toggleableModifier),
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterStart)
                .offset {
                    // Keep spring overshoot in the rendered position. Only finger tracking is clamped.
                    val position = if (dragPosition.isNaN()) thumbPosition.value else dragPosition
                    IntOffset((ThumbStart.toPx() + position * ThumbTravel.toPx()).fastRoundToInt(), 0)
                }
                .draggable(
                    state = draggableState,
                    orientation = Orientation.Horizontal,
                    enabled = canDrag,
                    interactionSource = dragInteractionSource,
                    reverseDirection = isRtl,
                    onDragStarted = {
                        startPosition = (if (dragPosition.isNaN()) thumbPosition.value else dragPosition).fastCoerceIn(0f, 1f)
                        rawPosition = startPosition
                        dragPosition = startPosition
                        lastHaptic = null
                        isDragging = true
                    },
                )
                .graphicsLayer {
                    scaleX = thumbScaleState.value
                    scaleY = thumbScaleState.value
                }
                .drawBehind {
                    drawCircle(color = thumbColorState.value)
                },
        )
    }
}

// Draggable calls onDragStopped for both release and cancellation. Its interactions carry the outcome.
private class SwitchDragInteractionSource(
    private val source: MutableInteractionSource,
    private val finishDrag: (Boolean) -> Unit,
) : MutableInteractionSource by source {
    override suspend fun emit(interaction: Interaction) {
        onDragInteraction(interaction)
        source.emit(interaction)
    }

    override fun tryEmit(interaction: Interaction): Boolean {
        onDragInteraction(interaction)
        return source.tryEmit(interaction)
    }

    private fun onDragInteraction(interaction: Interaction) {
        when (interaction) {
            is DragInteraction.Stop -> finishDrag(true)
            is DragInteraction.Cancel -> finishDrag(false)
        }
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
    ): SwitchColors = remember(
        checkedThumbColor,
        uncheckedThumbColor,
        disabledCheckedThumbColor,
        disabledUncheckedThumbColor,
        checkedTrackColor,
        uncheckedTrackColor,
        disabledCheckedTrackColor,
        disabledUncheckedTrackColor,
    ) {
        SwitchColors(
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
