// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.G2RoundedCornerShape
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.DialogLayout
import top.yukonga.miuix.kmp.utils.PredictiveBackHandler
import top.yukonga.miuix.kmp.utils.getWindowSize

/**
 * A bottom sheet that slides up from the bottom of the screen.
 * The height adapts to the content size, but will not cover the status bar area.
 *
 * @param show The show state of the [SuperBottomSheet].
 * @param modifier The modifier to be applied to the [SuperBottomSheet].
 * @param title Optional title to display at the top of the [SuperBottomSheet].
 * @param leftAction Optional composable to display on the left side of the title (e.g. a close button).
 * @param rightAction Optional composable to display on the right side of the title (e.g. a submit button).
 * @param backgroundColor The background color of the [SuperBottomSheet].
 * @param enableWindowDim Whether to dim the window behind the [SuperBottomSheet].
 * @param cornerRadius The corner radius of the top corners of the [SuperBottomSheet].
 * @param sheetMaxWidth The maximum width of the [SuperBottomSheet].
 * @param onDismissRequest The callback when the [SuperBottomSheet] is dismissed.
 * @param outsideMargin The margin outside the [SuperBottomSheet].
 * @param insideMargin The margin inside the [SuperBottomSheet].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding.
 * @param dragHandleColor The color of the drag handle at the top.
 * @param content The [Composable] content of the [SuperBottomSheet].
 */
@Composable
fun SuperBottomSheet(
    show: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    title: String? = null,
    leftAction: @Composable (() -> Unit?)? = null,
    rightAction: @Composable (() -> Unit?)? = null,
    backgroundColor: Color = SuperBottomSheetDefaults.backgroundColor(),
    enableWindowDim: Boolean = true,
    cornerRadius: Dp = SuperBottomSheetDefaults.cornerRadius,
    sheetMaxWidth: Dp = SuperBottomSheetDefaults.maxWidth,
    onDismissRequest: (() -> Unit)? = null,
    outsideMargin: DpSize = SuperBottomSheetDefaults.outsideMargin,
    insideMargin: DpSize = SuperBottomSheetDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    dragHandleColor: Color = SuperBottomSheetDefaults.dragHandleColor(),
    content: @Composable () -> Unit
) {
    if (!show.value) return

    val dimAlpha = remember { mutableFloatStateOf(1f) }
    val currentOnDismissRequest by rememberUpdatedState(onDismissRequest)
    val sheetHeightPx = remember { mutableIntStateOf(0) }
    val dragOffsetY = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    DialogLayout(
        visible = show,
        enableWindowDim = enableWindowDim,
        enableAutoLargeScreen = false,
        dimAlpha = dimAlpha
    ) {
        SuperBottomSheetContent(
            modifier = modifier,
            title = title,
            leftAction = leftAction,
            rightAction = rightAction,
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            sheetMaxWidth = sheetMaxWidth,
            outsideMargin = outsideMargin,
            insideMargin = insideMargin,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            dragHandleColor = dragHandleColor,
            dimAlpha = dimAlpha,
            sheetHeightPx = sheetHeightPx,
            dragOffsetY = dragOffsetY,
            onDismissRequest = currentOnDismissRequest,
            content = content
        )
    }

    PredictiveBackHandler(
        enabled = show.value,
        onBackProgressed = { event ->
            coroutineScope.launch {
                // Calculate offset based on back progress
                val maxOffset = if (sheetHeightPx.value > 0) {
                    sheetHeightPx.value.toFloat()
                } else {
                    500f
                }
                val offset = event.progress * maxOffset
                dragOffsetY.snapTo(offset)

                // Update dim alpha
                dimAlpha.value = 1f - event.progress
            }
        },
        onBackCancelled = {
            coroutineScope.launch {
                // Reset to original position
                dragOffsetY.animateTo(0f, animationSpec = tween(durationMillis = 150))
                dimAlpha.value = 1f
            }
        },
        onBack = {
            currentOnDismissRequest?.invoke()
        }
    )
}

@Composable
private fun SuperBottomSheetContent(
    modifier: Modifier,
    title: String?,
    leftAction: @Composable (() -> Unit?)? = null,
    rightAction: @Composable (() -> Unit?)? = null,
    backgroundColor: Color,
    cornerRadius: Dp,
    sheetMaxWidth: Dp,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean,
    dragHandleColor: Color,
    dimAlpha: MutableState<Float>,
    sheetHeightPx: MutableState<Int>,
    dragOffsetY: Animatable<Float, *>,
    onDismissRequest: (() -> Unit)?,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val windowSize by rememberUpdatedState(getWindowSize())
    val windowHeight by remember(windowSize, density) {
        derivedStateOf { windowSize.height.dp / density.density }
    }

    val statusBarHeight = with(density) {
        WindowInsets.statusBars.getTop(density).toDp()
    }

    val rootBoxModifier = remember(onDismissRequest) {
        Modifier
            .pointerInput(onDismissRequest) {
                detectTapGestures(
                    onTap = { onDismissRequest?.invoke() }
                )
            }
            .fillMaxSize()
    }

    Box(modifier = rootBoxModifier) {
        SuperBottomSheetColumn(
            modifier = modifier,
            title = title,
            leftAction = leftAction,
            rightAction = rightAction,
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            sheetMaxWidth = sheetMaxWidth,
            outsideMargin = outsideMargin,
            insideMargin = insideMargin,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            dragHandleColor = dragHandleColor,
            windowHeight = windowHeight,
            statusBarHeight = statusBarHeight,
            sheetHeightPx = sheetHeightPx,
            dragOffsetY = dragOffsetY,
            dimAlpha = dimAlpha,
            density = density,
            onDismissRequest = onDismissRequest,
            content = content
        )
    }
}

@Composable
private fun SuperBottomSheetColumn(
    modifier: Modifier,
    title: String?,
    leftAction: @Composable (() -> Unit?)?,
    rightAction: @Composable (() -> Unit?)?,
    backgroundColor: Color,
    cornerRadius: Dp,
    sheetMaxWidth: Dp,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean,
    dragHandleColor: Color,
    windowHeight: Dp,
    statusBarHeight: Dp,
    sheetHeightPx: MutableState<Int>,
    dragOffsetY: Animatable<Float, *>,
    dimAlpha: MutableState<Float>,
    density: androidx.compose.ui.unit.Density,
    onDismissRequest: (() -> Unit)?,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = modifier
                .pointerInput(Unit) {
                    detectTapGestures { /* Consume click to prevent dismissal */ }
                }
                .widthIn(max = sheetMaxWidth)
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(max = windowHeight - statusBarHeight)
                .onGloballyPositioned { coordinates ->
                    sheetHeightPx.value = coordinates.size.height
                }
                .graphicsLayer {
                    translationY = dragOffsetY.value
                }
                .then(
                    if (defaultWindowInsetsPadding)
                        Modifier.imePadding()
                    else
                        Modifier
                )
                .padding(horizontal = outsideMargin.width)
                .clip(G2RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius))
                .background(backgroundColor)
                .padding(horizontal = insideMargin.width)
                .padding(bottom = insideMargin.height)
        ) {
            // Drag handle area
            DragHandleArea(
                dragHandleColor = dragHandleColor,
                windowHeight = windowHeight,
                sheetHeightPx = sheetHeightPx,
                dragOffsetY = dragOffsetY,
                dimAlpha = dimAlpha,
                density = density,
                coroutineScope = coroutineScope,
                onDismissRequest = onDismissRequest
            )

            // Title and actions
            TitleAndActionsRow(
                title = title,
                leftAction = leftAction,
                rightAction = rightAction
            )

            // Content
            content()
        }
    }
}

@Composable
private fun DragHandleArea(
    dragHandleColor: Color,
    windowHeight: Dp,
    sheetHeightPx: MutableState<Int>,
    dragOffsetY: Animatable<Float, *>,
    dimAlpha: MutableState<Float>,
    density: androidx.compose.ui.unit.Density,
    coroutineScope: kotlinx.coroutines.CoroutineScope,
    onDismissRequest: (() -> Unit)?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragStart = {
                        coroutineScope.launch {
                            dragOffsetY.snapTo(dragOffsetY.value)
                        }
                    },
                    onDragEnd = {
                        coroutineScope.launch {
                            when {
                                // Dragged down significantly -> dismiss with animation
                                dragOffsetY.value > 150f -> {
                                    // Animate to bottom of screen
                                    onDismissRequest?.invoke()
                                    dragOffsetY.animateTo(
                                        targetValue = windowHeight.value * density.density,
                                        animationSpec = tween(durationMillis = 250)
                                    )
                                }
                                // Reset position if no action triggered
                                else -> {
                                    dragOffsetY.animateTo(0f, animationSpec = tween(durationMillis = 150))
                                    // Reset dim alpha
                                    dimAlpha.value = 1f
                                }
                            }
                        }
                    },
                    onVerticalDrag = { _, dragAmount ->
                        coroutineScope.launch {
                            // Only allow dragging down (positive offset)
                            val newOffset = (dragOffsetY.value + dragAmount).coerceAtLeast(0f)
                            dragOffsetY.snapTo(newOffset)

                            // Update dim alpha based on sheet height
                            val thresholdPx = if (sheetHeightPx.value > 0) sheetHeightPx.value.toFloat() else 500f
                            val alpha = 1f - (newOffset / thresholdPx).coerceIn(0f, 1f)
                            dimAlpha.value = alpha
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        // Drag handle indicator
        Box(
            modifier = Modifier
                .width(45.dp)
                .height(4.dp)
                .clip(G2RoundedCornerShape(2.dp))
                .background(dragHandleColor)
        )
    }
}

@Composable
private fun TitleAndActionsRow(
    title: String?,
    leftAction: @Composable (() -> Unit?)?,
    rightAction: @Composable (() -> Unit?)?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp, bottom = 12.dp)
    ) {
        // left action (e.g. close button)
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            leftAction?.invoke()
        }

        // title text
        title?.let {
            Text(
                text = it,
                modifier = Modifier.align(Alignment.Center),
                fontSize = MiuixTheme.textStyles.title4.fontSize,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = MiuixTheme.colorScheme.onSurface
            )
        }

        // right action (e.g. submit button)
        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            rightAction?.invoke()
        }
    }
}

object SuperBottomSheetDefaults {

    /**
     * The default background color of the [SuperBottomSheet].
     */
    @Composable
    fun backgroundColor() = MiuixTheme.colorScheme.surface

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
    val insideMargin = DpSize(24.dp, 24.dp)
}
