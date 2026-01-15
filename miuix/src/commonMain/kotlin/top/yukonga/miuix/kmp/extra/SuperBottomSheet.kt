// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.NavigationEventTransitionState
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.DialogLayout

/**
 * A bottom sheet that slides up from the bottom of the screen.
 * The height adapts to the content size, but will not cover the status bar area.
 *
 * @param show The show state of the [SuperBottomSheet].
 * @param modifier The modifier to be applied to the [SuperBottomSheet].
 * @param title Optional title to display at the top of the [SuperBottomSheet].
 * @param startAction Optional [Composable] to display on the start side of the title (e.g. a close button).
 * @param endAction Optional [Composable] to display on the end side of the title (e.g. a submit button).
 * @param backgroundColor The background color of the [SuperBottomSheet].
 * @param enableWindowDim Whether to dim the window behind the [SuperBottomSheet].
 * @param cornerRadius The corner radius of the top corners of the [SuperBottomSheet].
 * @param sheetMaxWidth The maximum width of the [SuperBottomSheet].
 * @param onDismissRequest Will called when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * @param onDismissFinished The callback when the [SuperDialog] is completely dismissed.
 * @param outsideMargin The margin outside the [SuperBottomSheet].
 * @param insideMargin The margin inside the [SuperBottomSheet].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding.
 * @param dragHandleColor The color of the drag handle at the top.
 * @param allowDismiss Whether to allow dismissing the sheet via drag or back gesture.
 * @param enableNestedScroll Whether to enable nested scrolling for the content.
 * @param content The [Composable] content of the [SuperBottomSheet].
 */
@Suppress("ktlint:compose:modifier-not-used-at-root")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SuperBottomSheet(
    show: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    title: String? = null,
    startAction: @Composable (() -> Unit)? = null,
    endAction: @Composable (() -> Unit)? = null,
    backgroundColor: Color = SuperBottomSheetDefaults.backgroundColor(),
    enableWindowDim: Boolean = true,
    cornerRadius: Dp = SuperBottomSheetDefaults.cornerRadius,
    sheetMaxWidth: Dp = SuperBottomSheetDefaults.maxWidth,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    outsideMargin: DpSize = SuperBottomSheetDefaults.outsideMargin,
    insideMargin: DpSize = SuperBottomSheetDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    dragHandleColor: Color = SuperBottomSheetDefaults.dragHandleColor(),
    allowDismiss: Boolean = true,
    enableNestedScroll: Boolean = true,
    content: @Composable () -> Unit,
) {
    if (!show.value) return

    val coroutineScope = rememberCoroutineScope()
    val sheetHeightPx = remember { mutableIntStateOf(0) }
    val dragOffsetY = remember { Animatable(0f) }
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)
    val dragSnapChannel = remember { Channel<Float>(capacity = Channel.CONFLATED) }

    val resetGesture: suspend () -> Unit = {
        dragOffsetY.animateTo(0f, animationSpec = tween(durationMillis = 150))
        animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
            dimAlpha.floatValue = value
        }
    }

    val navigationEventState = rememberNavigationEventState(currentInfo = NavigationEventInfo.None)
    NavigationBackHandler(
        state = navigationEventState,
        isBackEnabled = show.value,
        onBackCancelled = {
            coroutineScope.launch {
                resetGesture()
            }
        },
        onBackCompleted = {
            if (allowDismiss) {
                currentOnDismissRequest?.invoke()
            } else {
                coroutineScope.launch {
                    resetGesture()
                }
            }
        },
    )

    LaunchedEffect(navigationEventState.transitionState, allowDismiss) {
        val transitionState = navigationEventState.transitionState
        if (
            transitionState is NavigationEventTransitionState.InProgress &&
            transitionState.direction == NavigationEventTransitionState.TRANSITIONING_BACK
        ) {
            val maxOffset = if (sheetHeightPx.intValue > 0) {
                sheetHeightPx.intValue.toFloat()
            } else {
                500f
            }
            val offset = transitionState.latestEvent.progress * maxOffset
            val finalOffset = if (!allowDismiss) {
                offset * 0.1f
            } else {
                offset
            }
            dragSnapChannel.trySend(finalOffset)

            if (allowDismiss) {
                dimAlpha.floatValue = 1f - transitionState.latestEvent.progress
            }
        }
    }

    LaunchedEffect(dragOffsetY) {
        for (target in dragSnapChannel) {
            dragOffsetY.snapTo(target)
        }
    }

    @Composable
    fun rememberDefaultSheetEnterTransition(): EnterTransition = remember {
        slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(450, easing = DecelerateEasing(1.5f)),
        )
    }

    @Composable
    fun rememberDefaultSheetExitTransition(): ExitTransition = remember {
        slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(450, easing = DecelerateEasing(0.8f)),
        )
    }

    DialogLayout(
        visible = show,
        enterTransition = rememberDefaultSheetEnterTransition(),
        exitTransition = rememberDefaultSheetExitTransition(),
        enableWindowDim = enableWindowDim,
        enableAutoLargeScreen = false,
        dimAlpha = dimAlpha,
        onDismissFinished = onDismissFinished,
    ) {
        SuperBottomSheetContent(
            title = title,
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            sheetMaxWidth = sheetMaxWidth,
            outsideMargin = outsideMargin,
            insideMargin = insideMargin,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            dragHandleColor = dragHandleColor,
            allowDismiss = allowDismiss,
            sheetHeightPx = sheetHeightPx,
            dragOffsetY = dragOffsetY,
            dimAlpha = dimAlpha,
            dragSnapChannel = dragSnapChannel,
            onDismissRequest = currentOnDismissRequest,
            modifier = modifier,
            startAction = startAction,
            endAction = endAction,
            enableNestedScroll = enableNestedScroll,
            content = content,
        )
    }
}

@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
internal fun SuperBottomSheetContent(
    title: String?,
    backgroundColor: Color,
    cornerRadius: Dp,
    sheetMaxWidth: Dp,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean,
    dragHandleColor: Color,
    allowDismiss: Boolean,
    sheetHeightPx: MutableIntState,
    dragOffsetY: Animatable<Float, *>,
    dimAlpha: MutableFloatState,
    dragSnapChannel: Channel<Float>,
    onDismissRequest: (() -> Unit)?,
    modifier: Modifier = Modifier,
    topInset: Dp? = null,
    enableNestedScroll: Boolean = true,
    startAction: @Composable (() -> Unit)? = null,
    endAction: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val windowInfo = LocalWindowInfo.current
    val windowHeight = windowInfo.containerDpSize.height

    val currentOnDismiss by rememberUpdatedState(onDismissRequest)

    Box(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        currentOnDismiss?.invoke()
                    },
                )
            }
            .fillMaxSize(),
    ) {
        SuperBottomSheetColumn(
            title = title,
            startAction = startAction,
            endAction = endAction,
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            sheetMaxWidth = sheetMaxWidth,
            outsideMargin = outsideMargin,
            insideMargin = insideMargin,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            dragHandleColor = dragHandleColor,
            allowDismiss = allowDismiss,
            windowHeight = windowHeight,
            topInset = topInset,
            enableNestedScroll = enableNestedScroll,
            sheetHeightPx = sheetHeightPx,
            dragOffsetY = dragOffsetY,
            dimAlpha = dimAlpha,
            dragSnapChannel = dragSnapChannel,
            onDismissRequest = onDismissRequest,
            modifier = modifier,
            content = content,
        )
    }
}

@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
private fun SuperBottomSheetColumn(
    title: String?,
    startAction: @Composable (() -> Unit?)?,
    endAction: @Composable (() -> Unit?)?,
    backgroundColor: Color,
    cornerRadius: Dp,
    sheetMaxWidth: Dp,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean,
    dragHandleColor: Color,
    allowDismiss: Boolean,
    windowHeight: Dp,
    topInset: Dp?,
    enableNestedScroll: Boolean,
    sheetHeightPx: MutableIntState,
    dragOffsetY: Animatable<Float, *>,
    dimAlpha: MutableFloatState,
    dragSnapChannel: Channel<Float>,
    onDismissRequest: (() -> Unit)?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    val isSettling = remember { mutableStateOf(false) }

    // Calculate inset top padding
    val calculatedTopInset = if (topInset != null) {
        topInset
    } else {
        val statusBars = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
        val captionBar = WindowInsets.captionBar.asPaddingValues().calculateTopPadding()
        val displayCutout = WindowInsets.displayCutout.asPaddingValues().calculateTopPadding()
        maxOf(statusBars, captionBar, displayCutout)
    }

    // Calculate damping effect
    val calculateNewOffset = remember(allowDismiss) {
        { current: Float, delta: Float ->
            val newOffset = current + delta
            if (newOffset < 0) {
                // Damping for upward drag
                val dampingFactor = 0.1f
                (current + delta * dampingFactor).coerceAtMost(0f)
            } else if (newOffset >= 0 && !allowDismiss) {
                // Damping for downward drag if not dismissible
                val dampingFactor = 0.1f
                val dampedAmount = if (delta > 0) delta * dampingFactor else delta
                (current + dampedAmount).coerceAtLeast(0f)
            } else {
                newOffset
            }
        }
    }

    val updateDimAlpha = remember(allowDismiss) {
        { offset: Float ->
            val thresholdPx = if (sheetHeightPx.intValue > 0) sheetHeightPx.intValue.toFloat() else 500f
            val alpha = if (offset >= 0 && allowDismiss) {
                1f - (offset / thresholdPx).coerceIn(0f, 1f)
            } else {
                1f
            }
            dimAlpha.floatValue = alpha
        }
    }

    // Settlement logic
    val performSettle: suspend (Float) -> Unit = remember(allowDismiss, density, windowHeight) {
        { velocity ->
            if (!isSettling.value) {
                isSettling.value = true
                val currentOffset = dragOffsetY.value
                val dismissThresholdPx = with(density) { 150.dp.toPx() }
                val velocityThresholdPx = with(density) { 1000.dp.toPx() }
                val windowHeightPx = with(density) { windowHeight.toPx() }

                val shouldDismiss = allowDismiss && (
                    (velocity > velocityThresholdPx) ||
                        (currentOffset > dismissThresholdPx && velocity > -velocityThresholdPx)
                    )

                if (shouldDismiss) {
                    if (currentOffset >= windowHeightPx) {
                        onDismissRequest?.invoke()
                    } else {
                        try {
                            dragOffsetY.animateTo(
                                targetValue = windowHeightPx,
                                animationSpec = tween(durationMillis = 300, easing = DecelerateEasing(1f)),
                                initialVelocity = velocity,
                            )
                            onDismissRequest?.invoke()
                        } catch (_: Exception) {
                            // ignore
                        }
                    }
                } else {
                    // Restore position
                    // Ignore downward velocity if dismissal is disabled to prevent the sheet from being
                    // pushed down, while preserving upward velocity for a quicker snap back.
                    val effectiveVelocity = if (!allowDismiss && velocity > 0) 0f else velocity

                    dragOffsetY.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 300, easing = DecelerateEasing(1f)),
                        initialVelocity = effectiveVelocity,
                    )
                    dimAlpha.floatValue = 1f
                    isSettling.value = false
                }
            }
        }
    }

    // Nested scroll logic
    val nestedScrollConnection = remember(enableNestedScroll, allowDismiss, windowHeight) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (!enableNestedScroll || isSettling.value) return Offset.Zero

                val delta = available.y
                // If the sheet is offset, prioritize restoring its position
                if (delta < 0 && dragOffsetY.value > 0) {
                    val newOffset = calculateNewOffset(dragOffsetY.value, delta).coerceAtLeast(0f)
                    val consumedY = dragOffsetY.value - newOffset
                    if (consumedY != 0f) {
                        dragSnapChannel.trySend(newOffset)
                        updateDimAlpha(newOffset)
                        return Offset(0f, consumedY * -1f)
                    }
                }
                return Offset.Zero
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                if (!enableNestedScroll || isSettling.value) return Offset.Zero

                val delta = available.y
                if (delta > 0) {
                    // If dismissal is disabled, return zero to trigger content overscroll
                    if (!allowDismiss) return Offset.Zero

                    val newOffset = calculateNewOffset(dragOffsetY.value, delta)
                    dragSnapChannel.trySend(newOffset)
                    updateDimAlpha(newOffset)

                    // Dismiss immediately if dragged beyond window height
                    val windowHeightPx = with(density) { windowHeight.toPx() }
                    if (newOffset > windowHeightPx) {
                        isSettling.value = true
                        onDismissRequest?.invoke()
                        return available
                    }

                    return available
                }
                return Offset.Zero
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                if (!enableNestedScroll || isSettling.value) return Velocity.Zero

                // Take over fling if the sheet is offset
                if (dragOffsetY.value > 0) {
                    performSettle(available.y)
                    return available
                }
                return Velocity.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (!enableNestedScroll || isSettling.value) return Velocity.Zero

                if (dragOffsetY.value > 0) {
                    performSettle(available.y)
                    return available
                }
                return super.onPostFling(consumed, available)
            }
        }
    }

    // Calculate the overscroll offset for background fill
    val dragOffsetYValue by remember { derivedStateOf { dragOffsetY.value } }
    val overscrollOffsetPx by remember {
        derivedStateOf {
            (-dragOffsetYValue).coerceAtLeast(0f)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        // Background fill for the area revealed when dragging up (overscroll effect)
        if (overscrollOffsetPx > 0f) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .widthIn(max = sheetMaxWidth)
                    .fillMaxWidth()
                    .height(with(density) { overscrollOffsetPx.toDp() } + 1.dp)
                    .padding(horizontal = outsideMargin.width)
                    .background(backgroundColor),
            )
        }

        Column(
            modifier = modifier
                .pointerInput(Unit) {
                    detectTapGestures { /* Consume click */ }
                }
                .then(if (enableNestedScroll) Modifier.nestedScroll(nestedScrollConnection) else Modifier)
                .widthIn(max = sheetMaxWidth)
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(max = windowHeight - calculatedTopInset)
                .onGloballyPositioned { coordinates ->
                    sheetHeightPx.intValue = coordinates.size.height
                }
                .graphicsLayer {
                    translationY = dragOffsetY.value
                }
                .then(
                    if (defaultWindowInsetsPadding) {
                        Modifier.imePadding()
                    } else {
                        Modifier
                    },
                )
                .padding(horizontal = outsideMargin.width)
                .clip(ContinuousRoundedRectangle(topStart = cornerRadius, topEnd = cornerRadius))
                .background(backgroundColor)
                .padding(horizontal = insideMargin.width)
                .padding(bottom = insideMargin.height),
        ) {
            // Drag handle area
            DragHandleArea(
                dragHandleColor = dragHandleColor,
                allowDismiss = allowDismiss,
                dragOffsetY = dragOffsetY,
                coroutineScope = coroutineScope,
                dragSnapChannel = dragSnapChannel,
                onSettle = performSettle,
                onUpdateAlpha = updateDimAlpha,
            )

            // Title and actions
            TitleAndActionsRow(
                title = title,
                startAction = startAction,
                endAction = endAction,
            )

            // Content
            content()
        }
    }
}

@Composable
private fun DragHandleArea(
    dragHandleColor: Color,
    allowDismiss: Boolean,
    dragOffsetY: Animatable<Float, *>,
    coroutineScope: CoroutineScope,
    dragSnapChannel: Channel<Float>,
    onSettle: suspend (velocity: Float) -> Unit,
    onUpdateAlpha: (Float) -> Unit,
) {
    val isPressing = remember { mutableFloatStateOf(0f) }
    val pressScale = remember { Animatable(1f) }
    val pressWidth = remember { Animatable(45f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressing.floatValue = 1f
                        coroutineScope.launch {
                            pressScale.animateTo(
                                targetValue = 1.15f,
                                animationSpec = tween(durationMillis = 100),
                            )
                        }
                        coroutineScope.launch {
                            pressWidth.animateTo(
                                targetValue = 55f,
                                animationSpec = tween(durationMillis = 100),
                            )
                        }

                        val released = tryAwaitRelease()
                        if (released) {
                            isPressing.floatValue = 0f
                            coroutineScope.launch {
                                pressScale.animateTo(
                                    targetValue = 1f,
                                    animationSpec = tween(durationMillis = 150),
                                )
                            }
                            coroutineScope.launch {
                                pressWidth.animateTo(
                                    targetValue = 45f,
                                    animationSpec = tween(durationMillis = 150),
                                )
                            }
                        }
                    },
                )
            }
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { dragAmount ->
                    val newOffset = dragOffsetY.value + dragAmount
                    val finalOffset = if (newOffset < 0) {
                        // Damping up
                        (dragOffsetY.value + dragAmount * 0.1f).coerceAtMost(0f)
                    } else if (newOffset >= 0 && !allowDismiss) {
                        // Damping down if not dismissible
                        val dampedAmount = if (dragAmount > 0) dragAmount * 0.1f else dragAmount
                        (dragOffsetY.value + dampedAmount).coerceAtLeast(0f)
                    } else {
                        newOffset
                    }

                    dragSnapChannel.trySend(finalOffset)
                    onUpdateAlpha(finalOffset)
                },
                onDragStarted = {
                    isPressing.floatValue = 1f
                    coroutineScope.launch {
                        pressScale.animateTo(1.15f, animationSpec = tween(durationMillis = 100))
                    }
                    coroutineScope.launch {
                        pressWidth.animateTo(55f, animationSpec = tween(durationMillis = 100))
                    }
                },
                onDragStopped = { velocity ->
                    isPressing.floatValue = 0f
                    coroutineScope.launch {
                        pressScale.animateTo(1f, animationSpec = tween(durationMillis = 150))
                    }
                    coroutineScope.launch {
                        pressWidth.animateTo(45f, animationSpec = tween(durationMillis = 150))
                    }

                    // Delegate the settle logic to the shared function
                    coroutineScope.launch {
                        onSettle(velocity)
                    }
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        val handleAlpha = lerp(0.2f, 0.35f, isPressing.floatValue)

        Box(
            modifier = Modifier
                .width(pressWidth.value.dp)
                .height(4.dp)
                .graphicsLayer {
                    scaleY = pressScale.value
                }
                .clip(ContinuousRoundedRectangle(2.dp))
                .background(dragHandleColor.copy(alpha = handleAlpha)),
        )
    }
}

@Composable
private fun TitleAndActionsRow(
    title: String?,
    startAction: @Composable (() -> Unit?)?,
    endAction: @Composable (() -> Unit?)?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, bottom = 12.dp),
    ) {
        // Start action (e.g. close button)
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            startAction?.invoke()
        }

        // Title text
        title?.let {
            Text(
                text = it,
                modifier = Modifier.align(Alignment.Center),
                fontSize = MiuixTheme.textStyles.title4.fontSize,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = MiuixTheme.colorScheme.onSurface,
            )
        }

        // End action (e.g. submit button)
        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            endAction?.invoke()
        }
    }
}

object SuperBottomSheetDefaults {

    /**
     * The default background color of the [SuperBottomSheet].
     */
    @Composable
    fun backgroundColor() = MiuixTheme.colorScheme.background

    /**
     * The default color of the drag handle.
     */
    @Composable
    fun dragHandleColor() = MiuixTheme.colorScheme.onSurfaceVariantSummary.copy(alpha = 0.2f)

    /**
     * The default corner radius of the [SuperBottomSheet].
     */
    val cornerRadius = 28.dp

    /**
     * The default maximum width of the [SuperBottomSheet].
     */
    val maxWidth = 640.dp

    /**
     * The default margin outside the [SuperBottomSheet].
     */
    val outsideMargin = DpSize(0.dp, 0.dp)

    /**
     * The default margin inside the [SuperBottomSheet].
     */
    val insideMargin = DpSize(24.dp, 0.dp)
}
