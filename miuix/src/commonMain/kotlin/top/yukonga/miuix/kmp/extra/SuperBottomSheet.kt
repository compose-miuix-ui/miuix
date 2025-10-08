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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.BackHandler
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.DialogLayout
import top.yukonga.miuix.kmp.utils.getWindowSize
import kotlin.math.roundToInt

/**
 * A bottom sheet that slides up from the bottom of the screen.
 * The height adapts to the content size, but will not cover the status bar area.
 *
 * @param show The show state of the [SuperBottomSheet].
 * @param modifier The modifier to be applied to the [SuperBottomSheet].
 * @param title Optional title to display at the top of the bottom sheet.
 * @param backgroundColor The background color of the [SuperBottomSheet].
 * @param enableWindowDim Whether to dim the window behind the [SuperBottomSheet].
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
    onDismissRequest: (() -> Unit)? = null,
    outsideMargin: DpSize = SuperBottomSheetDefaults.outsideMargin,
    insideMargin: DpSize = SuperBottomSheetDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    dragHandleColor: Color = SuperBottomSheetDefaults.dragHandleColor(),
    content: @Composable () -> Unit
) {
    if (!show.value) return

    val dimAlpha = remember { mutableFloatStateOf(1f) }

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
            outsideMargin = outsideMargin,
            insideMargin = insideMargin,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            dragHandleColor = dragHandleColor,
            dimAlpha = dimAlpha,
            onDismissRequest = onDismissRequest,
            content = content
        )
    }

    BackHandler(enabled = show.value) {
        onDismissRequest?.invoke()
    }
}

@Composable
private fun SuperBottomSheetContent(
    modifier: Modifier,
    title: String?,
    leftAction: @Composable (() -> Unit?)? = null,
    rightAction: @Composable (() -> Unit?)? = null,
    backgroundColor: Color,
    outsideMargin: DpSize,
    insideMargin: DpSize,
    defaultWindowInsetsPadding: Boolean,
    dragHandleColor: Color,
    dimAlpha: MutableState<Float>,
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

    val sheetHeightPx = remember { mutableStateOf(0) }

    val dragOffsetY = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    val rootBoxModifier = Modifier
        .pointerInput(onDismissRequest) {
            detectTapGestures(
                onTap = { onDismissRequest?.invoke() }
            )
        }
        .fillMaxSize()

    Box(modifier = rootBoxModifier) {
        Column(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .pointerInput(Unit) {
                    detectTapGestures { /* Consume click to prevent dismissal */ }
                }
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(max = windowHeight - statusBarHeight)
                .onGloballyPositioned { coordinates ->
                    sheetHeightPx.value = coordinates.size.height
                }
                .offset { IntOffset(0, dragOffsetY.value.roundToInt()) }
                .then(
                    if (defaultWindowInsetsPadding)
                        Modifier.imePadding()
                    else
                        Modifier
                )
                .padding(horizontal = outsideMargin.width)
                .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                .background(backgroundColor)
                .padding(horizontal = insideMargin.width)
                .padding(bottom = insideMargin.height)
        ) {
            // Drag handle area
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
                        .clip(RoundedCornerShape(2.dp))
                        .background(dragHandleColor)
                )
            }

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

            // Content
            content()
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
     * The default margin outside the [SuperBottomSheet].
     */
    val outsideMargin = DpSize(0.dp, 0.dp)

    /**
     * The default margin inside the [SuperBottomSheet].
     */
    val insideMargin = DpSize(24.dp, 24.dp)
}
