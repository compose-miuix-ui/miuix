// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.folmeSpring
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.squircle.squircleSurface
import top.yukonga.miuix.kmp.theme.LocalDismissState
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.coroutines.cancellation.CancellationException
import kotlin.math.abs

/**
 * Internal shared layout logic for [OverlayBottomSheet] and [WindowBottomSheet].
 *
 * @param show Whether the bottom sheet is currently shown.
 * @param backgroundColor The background color of the bottom sheet.
 * @param cornerRadius The corner radius of the top corners of the bottom sheet.
 * @param sheetMaxWidth The maximum width of the bottom sheet.
 * @param outsideMargin The margin outside the bottom sheet.
 * @param insideMargin The margin inside the bottom sheet.
 * @param dragHandleColor The color of the drag handle.
 * @param popupHost A composable that provides the container (e.g., DialogLayout or Dialog).
 *   It receives the visibility state and the inner content composable.
 * @param modifier The modifier to be applied to the bottom sheet content.
 * @param title Optional title to display at the top of the bottom sheet.
 * @param startAction Optional [Composable] to display on the start side of the title.
 * @param endAction Optional [Composable] to display on the end side of the title.
 * @param enableWindowDim Whether to dim the window behind the bottom sheet.
 * @param onDismissRequest The callback when the user tries to dismiss the bottom sheet.
 * @param onDismissFinished Invoked when the hide animation completes; not invoked if the hide
 *   is cancelled mid-flight (e.g., by [show] toggling back to true).
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding.
 * @param allowDismiss Whether to allow dismissing the sheet via drag or back gesture.
 * @param enableNestedScroll Whether to enable nested scrolling for the content.
 * @param topInset Optional top inset override. If null, calculated from window insets.
 * @param content The content of the bottom sheet.
 */
@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
internal fun BottomSheetContentLayout(
    show: Boolean,
    backgroundColor: Color,
    cornerRadius: Dp,
    sheetMaxWidth: Dp,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    dragHandleColor: Color,
    popupHost: @Composable (visible: Boolean, content: @Composable () -> Unit) -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    startAction: @Composable (() -> Unit)? = null,
    endAction: @Composable (() -> Unit)? = null,
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    defaultWindowInsetsPadding: Boolean = true,
    allowDismiss: Boolean = true,
    enableNestedScroll: Boolean = true,
    topInset: Dp? = null,
    content: @Composable () -> Unit,
) {
    val animationProgress = remember { Animatable(0f, visibilityThreshold = 0.0001f) }
    val dragOffsetY = remember { Animatable(0f) }
    val currentOnDismissFinished by rememberUpdatedState(onDismissFinished)
    val internalVisible = remember { mutableStateOf(false) }

    LaunchedEffect(show) {
        if (show) {
            internalVisible.value = true
            dragOffsetY.snapTo(0f)
            animationProgress.animateTo(
                targetValue = 1f,
                animationSpec = folmeSpring(damping = 0.9f, response = 0.38f),
            )
        } else {
            if (!internalVisible.value) return@LaunchedEffect
            if (dragOffsetY.value > 0f) {
                // Sheet already dragged off-screen — snap immediately
                animationProgress.snapTo(0f)
            } else {
                // Button/back dismiss — animate normally
                animationProgress.animateTo(
                    targetValue = 0f,
                    animationSpec = folmeSpring(damping = 0.9f, response = 0.38f),
                )
            }
            internalVisible.value = false
            currentOnDismissFinished?.invoke()
        }
    }

    if (!show && !internalVisible.value) return

    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current
    val coroutineScope = rememberCoroutineScope()
    val sheetHeightPx = remember { mutableIntStateOf(0) }
    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val dragSnapChannel = remember { Channel<Float>(capacity = Channel.CONFLATED) }
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)

    val requestDismiss: () -> Unit = remember {
        { currentOnDismissRequest?.invoke() }
    }

    val resetGesture: suspend () -> Unit = {
        dragOffsetY.animateTo(0f, animationSpec = tween(durationMillis = 150))
        animate(dimAlpha.floatValue, 1f, animationSpec = tween(durationMillis = 150)) { value, _ ->
            dimAlpha.floatValue = value
        }
    }

    LaunchedEffect(dragOffsetY) {
        for (target in dragSnapChannel) dragOffsetY.snapTo(target)
    }

    popupHost(internalVisible.value) {
        val navigationEventState = rememberNavigationEventState(currentInfo = NavigationEventInfo.None)
        NavigationBackHandler(
            state = navigationEventState,
            isBackEnabled = show,
            onBackCancelled = { coroutineScope.launch { resetGesture() } },
            onBackCompleted = {
                if (allowDismiss) {
                    coroutineScope.launch {
                        val windowHeightPx = with(density) { windowInfo.containerDpSize.height.toPx() }
                        animateDismissOffScreen(
                            dragOffsetY = dragOffsetY,
                            sheetHeightPx = sheetHeightPx.intValue,
                            windowHeightPx = windowHeightPx,
                            dimAlpha = dimAlpha,
                        ) {
                            requestDismiss()
                        }
                    }
                } else {
                    coroutineScope.launch { resetGesture() }
                }
            },
        )

        LaunchedEffect(allowDismiss) {
            // Collect inside a single coroutine so the per-frame `transitionState` ticks during a
            // back gesture do not cancel/relaunch the LaunchedEffect on every progress update.
            snapshotFlow { navigationEventState.transitionState }
                .collect { transitionState ->
                    if (
                        transitionState is NavigationEventTransitionState.InProgress &&
                        transitionState.direction == NavigationEventTransitionState.TRANSITIONING_BACK
                    ) {
                        val maxOffset = if (sheetHeightPx.intValue > 0) sheetHeightPx.intValue.toFloat() else 500f
                        val offset = transitionState.latestEvent.progress * maxOffset
                        val finalOffset = if (!allowDismiss) offset * 0.1f else offset
                        dragSnapChannel.trySend(finalOffset)
                        if (allowDismiss) dimAlpha.floatValue = 1f - transitionState.latestEvent.progress
                    }
                }
        }

        if (enableWindowDim) {
            val baseColor = MiuixTheme.colorScheme.windowDimming
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawRect(baseColor.copy(alpha = baseColor.alpha * dimAlpha.floatValue * animationProgress.value))
                    },
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                // Key on `allowDismiss` so the gesture coroutine restarts with the latest closure
                // when the consumer toggles it; otherwise the in-flight detector keeps the stale value.
                .pointerInput(allowDismiss) {
                    detectTapGestures(
                        onTap = {
                            if (allowDismiss) {
                                requestDismiss()
                            }
                        },
                    )
                }
                .semantics {
                    if (allowDismiss) {
                        onClick(label = "Dismiss") {
                            requestDismiss()
                            true
                        }
                    }
                },
            contentAlignment = Alignment.BottomCenter,
        ) {
            val sheetModifier = modifier.graphicsLayer {
                val progress = animationProgress.value
                val currentHeight = sheetHeightPx.intValue.toFloat()
                val windowHeightPx = with(density) { windowInfo.containerDpSize.height.toPx() }
                val baseOffset = if (currentHeight > 0) currentHeight else windowHeightPx
                translationY = baseOffset * (1f - progress) + dragOffsetY.value
            }

            BottomSheetContent(
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
                onDismissRequest = {
                    if (allowDismiss) {
                        requestDismiss()
                    }
                },
                modifier = sheetModifier,
                topInset = topInset,
                enableNestedScroll = enableNestedScroll,
                startAction = startAction?.let { action ->
                    { CompositionLocalProvider(LocalDismissState provides requestDismiss) { action() } }
                },
                endAction = endAction?.let { action ->
                    { CompositionLocalProvider(LocalDismissState provides requestDismiss) { action() } }
                },
                content = {
                    CompositionLocalProvider(LocalDismissState provides requestDismiss) {
                        content()
                    }
                },
            )
        }
    }
}

@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
internal fun BottomSheetContent(
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
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current
    val windowHeight = windowInfo.containerDpSize.height
    // Do NOT use windowHeight as a remember key — IME resize would recreate handlers and trigger dismiss.
    val currentWindowHeight by rememberUpdatedState(windowHeight)
    val coroutineScope = rememberCoroutineScope()

    val settlingJob = remember { mutableStateOf<Job?>(null) }
    val isSettling = remember { mutableStateOf(false) }
    val calculatedTopInset = if (topInset != null) {
        topInset
    } else {
        val statusBars = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
        val captionBar = WindowInsets.captionBar.asPaddingValues().calculateTopPadding()
        val displayCutout = WindowInsets.displayCutout.asPaddingValues().calculateTopPadding()
        maxOf(statusBars, captionBar, displayCutout)
    }

    val calculateNewOffset = remember(allowDismiss) {
        { current: Float, delta: Float ->
            val newOffset = current + delta
            if (newOffset < 0) {
                val dampingFactor = 0.1f
                (current + delta * dampingFactor).coerceAtMost(0f)
            } else if (newOffset >= 0 && !allowDismiss) {
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
    val performSettle: (Float) -> Unit = remember(allowDismiss, density) {
        { velocity ->
            settlingJob.value?.cancel()
            isSettling.value = true
            settlingJob.value = coroutineScope.launch {
                val currentOffset = dragOffsetY.value
                val dismissThresholdPx = with(density) { 150.dp.toPx() }
                // Velocity comes from gesture pipeline as px/s; the threshold is intentionally
                // expressed as a dp value converted to px so the trigger feels equivalent across
                // densities (higher-density screens both demand and produce proportionally larger
                // px/s values, which roughly cancels out).
                val velocityThresholdPx = with(density) { 800.dp.toPx() }
                val windowHeightPx = with(density) { currentWindowHeight.toPx() }

                val shouldDismiss = allowDismiss && (
                    (velocity > velocityThresholdPx) ||
                        (currentOffset > dismissThresholdPx && velocity > -velocityThresholdPx)
                    )

                try {
                    if (shouldDismiss) {
                        if (currentOffset >= windowHeightPx) {
                            onDismissRequest?.invoke()
                        } else {
                            animateDismissOffScreen(
                                dragOffsetY = dragOffsetY,
                                sheetHeightPx = sheetHeightPx.intValue,
                                windowHeightPx = windowHeightPx,
                                dimAlpha = dimAlpha,
                                velocity = velocity,
                            ) {
                                onDismissRequest?.invoke()
                            }
                        }
                    } else {
                        val effectiveVelocity = if (!allowDismiss && velocity > 0) 0f else velocity
                        dragOffsetY.animateTo(
                            targetValue = 0f,
                            animationSpec = folmeSpring(damping = 0.85f, response = 0.4f),
                            initialVelocity = effectiveVelocity,
                        ) {
                            updateDimAlpha(value)
                        }
                        dimAlpha.floatValue = 1f
                    }
                } catch (_: CancellationException) {
                    // Animation is interrupted
                } finally {
                    // Reset state after animation completes
                    isSettling.value = false
                }
            }
        }
    }

    val isNestedScrollGestureStarted = remember { mutableStateOf(false) }
    val canDragSheetFromNestedScroll = remember { mutableStateOf(false) }

    fun resetNestedScrollGesture() {
        isNestedScrollGestureStarted.value = false
        canDragSheetFromNestedScroll.value = false
    }

    // Nested scroll logic
    val nestedScrollConnection = remember(enableNestedScroll, allowDismiss, density) {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (!enableNestedScroll || source != NestedScrollSource.UserInput) return Offset.Zero

                // Allow interruption whenever settling
                if (isSettling.value) {
                    settlingJob.value?.cancel()
                    isSettling.value = false
                }

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
                if (!enableNestedScroll || source != NestedScrollSource.UserInput) return Offset.Zero

                val delta = available.y
                if (!isNestedScrollGestureStarted.value) {
                    isNestedScrollGestureStarted.value = true
                    canDragSheetFromNestedScroll.value = delta > 0 && abs(consumed.y) < 0.5f
                }

                if (delta > 0 && canDragSheetFromNestedScroll.value) {
                    if (!allowDismiss) return Offset.Zero

                    if (isSettling.value) {
                        settlingJob.value?.cancel()
                        isSettling.value = false
                    }

                    val newOffset = calculateNewOffset(dragOffsetY.value, delta)
                    dragSnapChannel.trySend(newOffset)
                    updateDimAlpha(newOffset)

                    // Dismiss immediately if dragged beyond window height
                    val windowHeightPx = with(density) { currentWindowHeight.toPx() }
                    if (newOffset > windowHeightPx) {
                        performSettle(0f)
                        return available
                    }

                    return available
                }
                return Offset.Zero
            }

            override suspend fun onPreFling(available: Velocity): Velocity {
                try {
                    if (!enableNestedScroll || isSettling.value) return Velocity.Zero

                    // Take over fling if the sheet is offset.
                    if (dragOffsetY.value > 0) {
                        performSettle(available.y)
                        return available
                    }
                    return Velocity.Zero
                } finally {
                    resetNestedScrollGesture()
                }
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                try {
                    if (!enableNestedScroll || isSettling.value) return Velocity.Zero

                    if (dragOffsetY.value > 0) {
                        performSettle(available.y)
                        return available
                    }
                    return super.onPostFling(consumed, available)
                } finally {
                    resetNestedScrollGesture()
                }
            }
        }
    }

    BottomSheetColumn(
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
        topInset = calculatedTopInset,
        enableNestedScroll = enableNestedScroll,
        sheetHeightPx = sheetHeightPx,
        dragOffsetY = dragOffsetY,
        nestedScrollConnection = nestedScrollConnection,
        coroutineScope = coroutineScope,
        dragSnapChannel = dragSnapChannel,
        onSettle = performSettle,
        onUpdateAlpha = updateDimAlpha,
        modifier = modifier,
        content = content,
    )
}

@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
private fun BottomSheetColumn(
    title: String?,
    startAction: (@Composable () -> Unit)?,
    endAction: (@Composable () -> Unit)?,
    backgroundColor: Color,
    cornerRadius: Dp,
    sheetMaxWidth: Dp,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean,
    dragHandleColor: Color,
    allowDismiss: Boolean,
    windowHeight: Dp,
    topInset: Dp,
    enableNestedScroll: Boolean,
    sheetHeightPx: MutableIntState,
    dragOffsetY: Animatable<Float, *>,
    nestedScrollConnection: NestedScrollConnection,
    coroutineScope: CoroutineScope,
    dragSnapChannel: Channel<Float>,
    onSettle: (velocity: Float) -> Unit,
    onUpdateAlpha: (Float) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val density = LocalDensity.current
    val imeInsets = WindowInsets.ime

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        OverscrollBackground(
            dragOffsetY = dragOffsetY,
            sheetMaxWidth = sheetMaxWidth,
            outsideMargin = outsideMargin,
            backgroundColor = backgroundColor,
        )

        Column(
            modifier = modifier
                .pointerInput(Unit) { detectTapGestures { /* Consume click */ } }
                .then(if (enableNestedScroll) Modifier.nestedScroll(nestedScrollConnection) else Modifier)
                .widthIn(max = sheetMaxWidth)
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(max = windowHeight - topInset)
                .onGloballyPositioned { coordinates ->
                    // Only capture the natural sheet height while the IME is closed; otherwise the
                    // bottom inset added by imePadding would inflate sheetHeightPx, shifting the
                    // dim threshold and causing translationY jumps mid-animation.
                    if (imeInsets.getBottom(density) == 0) {
                        sheetHeightPx.intValue = coordinates.size.height
                    }
                }
                .then(if (defaultWindowInsetsPadding) Modifier.imePadding() else Modifier)
                .padding(horizontal = outsideMargin.width)
                .squircleSurface(
                    color = backgroundColor,
                    topStart = cornerRadius,
                    topEnd = cornerRadius,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp,
                )
                .padding(horizontal = insideMargin.width)
                .padding(bottom = insideMargin.height),
        ) {
            DragHandleArea(
                dragHandleColor = dragHandleColor,
                allowDismiss = allowDismiss,
                dragOffsetY = dragOffsetY,
                coroutineScope = coroutineScope,
                dragSnapChannel = dragSnapChannel,
                onSettle = onSettle,
                onUpdateAlpha = onUpdateAlpha,
            )

            TitleAndActionsRow(
                title = title,
                startAction = startAction,
                endAction = endAction,
            )

            content()
        }
    }
}

/**
 * Renders the elastic background fill that follows the sheet upward when the user
 * drags past the top of its contents (negative `dragOffsetY`). Lives in its own
 * composable so the only state it reads — `dragOffsetY.value` — invalidates this
 * subtree alone, leaving the surrounding [BottomSheetColumn] free of per-frame
 * recomposition during a drag.
 */
@Composable
private fun BoxScope.OverscrollBackground(
    dragOffsetY: Animatable<Float, *>,
    sheetMaxWidth: Dp,
    outsideMargin: DpSize,
    backgroundColor: Color,
) {
    val density = LocalDensity.current
    val overscrollOffsetPx by remember { derivedStateOf { (-dragOffsetY.value).coerceAtLeast(0f) } }
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
}

private fun CoroutineScope.animateHandlePressDown(
    pressScale: Animatable<Float, *>,
    pressWidth: Animatable<Float, *>,
) {
    launch { pressScale.animateTo(targetValue = 1.15f, animationSpec = tween(durationMillis = 100)) }
    launch { pressWidth.animateTo(targetValue = 55f, animationSpec = tween(durationMillis = 100)) }
}

private fun CoroutineScope.animateHandlePressRelease(
    pressScale: Animatable<Float, *>,
    pressWidth: Animatable<Float, *>,
) {
    launch { pressScale.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 150)) }
    launch { pressWidth.animateTo(targetValue = 45f, animationSpec = tween(durationMillis = 150)) }
}

/**
 * Drag handle strip at the top of the sheet. Animates a press scale/width when the user
 * touches or drags the handle, and forwards drag amounts into [dragSnapChannel].
 *
 * Press/release scaling is delegated to [animateHandlePressDown] / [animateHandlePressRelease]
 * so the same launch-pair is not rewritten in each of the four entry points
 * (tap-press, tap-release, drag-start, drag-stop).
 */
@Composable
private fun DragHandleArea(
    dragHandleColor: Color,
    allowDismiss: Boolean,
    dragOffsetY: Animatable<Float, *>,
    coroutineScope: CoroutineScope,
    dragSnapChannel: Channel<Float>,
    onSettle: (velocity: Float) -> Unit,
    onUpdateAlpha: (Float) -> Unit,
) {
    val isPressing = remember { mutableFloatStateOf(0f) }
    val pressScale = remember { Animatable(1f) }
    val pressWidth = remember { Animatable(45f) }
    val handleShape = remember { RoundedCornerShape(2.dp) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .pointerHoverIcon(PointerIcon.Hand)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressing.floatValue = 1f
                        coroutineScope.animateHandlePressDown(pressScale, pressWidth)
                        val released = tryAwaitRelease()
                        if (released) {
                            isPressing.floatValue = 0f
                            coroutineScope.animateHandlePressRelease(pressScale, pressWidth)
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
                    coroutineScope.animateHandlePressDown(pressScale, pressWidth)
                },
                onDragStopped = { velocity ->
                    isPressing.floatValue = 0f
                    coroutineScope.animateHandlePressRelease(pressScale, pressWidth)
                    // Delegate the settle logic to the shared function
                    onSettle(velocity)
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .width(pressWidth.value.dp)
                .height(4.dp)
                .graphicsLayer {
                    scaleY = pressScale.value
                }
                .clip(handleShape)
                .drawBehind {
                    val handleAlpha = lerp(0.2f, 0.35f, isPressing.floatValue)
                    drawRect(dragHandleColor.copy(alpha = handleAlpha))
                },
        )
    }
}

@Composable
private fun TitleAndActionsRow(
    title: String?,
    startAction: (@Composable () -> Unit)?,
    endAction: (@Composable () -> Unit)?,
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

/**
 * Animate the sheet off-screen, updating dim alpha along the way.
 * Dismisses as soon as the sheet leaves the viewport (offset >= sheetHeight).
 */
private suspend fun CoroutineScope.animateDismissOffScreen(
    dragOffsetY: Animatable<Float, *>,
    sheetHeightPx: Int,
    windowHeightPx: Float,
    dimAlpha: MutableFloatState,
    velocity: Float = 0f,
    onDismiss: () -> Unit,
) {
    val sheetHeight = sheetHeightPx.toFloat()
    val thresholdPx = if (sheetHeight > 0) sheetHeight else 500f
    val settleJob = launch {
        dragOffsetY.animateTo(
            targetValue = windowHeightPx,
            animationSpec = folmeSpring(damping = 0.85f, response = 0.4f),
            initialVelocity = velocity,
        ) {
            dimAlpha.floatValue = (1f - (value / thresholdPx).coerceIn(0f, 1f))
        }
    }
    snapshotFlow { dragOffsetY.value }
        .first { sheetHeight > 0 && it >= sheetHeight }
    settleJob.cancel()
    onDismiss()
}

object BottomSheetDefaults {

    /**
     * The default background color of the bottom sheet.
     */
    @Composable
    fun backgroundColor() = MiuixTheme.colorScheme.background

    /**
     * The default color of the drag handle.
     */
    @Composable
    fun dragHandleColor() = MiuixTheme.colorScheme.onSurfaceVariantSummary.copy(alpha = 0.2f)

    /**
     * The default corner radius of the bottom sheet.
     */
    val cornerRadius = 28.dp

    /**
     * The default maximum width of the bottom sheet.
     */
    val maxWidth = 640.dp

    /**
     * The default margin outside the bottom sheet.
     */
    val outsideMargin = DpSize(0.dp, 0.dp)

    /**
     * The default margin inside the bottom sheet.
     */
    val insideMargin = DpSize(24.dp, 0.dp)
}
