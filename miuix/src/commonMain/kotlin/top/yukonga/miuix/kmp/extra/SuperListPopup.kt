// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.NavigationEventTransitionState
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.DecelerateEasing
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupContent
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.rememberListPopupLayoutInfo
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.PopupLayout

/**
 * A popup with a list of items.
 *
 * @param show The show state of the [SuperListPopup].
 * @param popupModifier The modifier to be applied to the [SuperListPopup].
 * @param popupPositionProvider The [PopupPositionProvider] of the [SuperListPopup].
 * @param alignment The alignment of the [SuperListPopup].
 * @param enableWindowDim Whether to enable window dimming when the [SuperListPopup] is shown.
 * @param onDismissRequest The callback when the [SuperListPopup] is dismissed.
 * @param maxHeight The maximum height of the [SuperListPopup]. If null, the height will be calculated automatically.
 * @param minWidth The minimum width of the [SuperListPopup].
 * @param content The [Composable] content of the [SuperListPopup]. You should use the [ListPopupColumn] in general.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SuperListPopup(
    show: MutableState<Boolean>,
    popupModifier: Modifier = Modifier,
    popupPositionProvider: PopupPositionProvider = ListPopupDefaults.DropdownPositionProvider,
    alignment: PopupPositionProvider.Align = PopupPositionProvider.Align.Right,
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    maxHeight: Dp? = null,
    minWidth: Dp = 200.dp,
    content: @Composable () -> Unit,
) {
    val animationProgress = remember { Animatable(0f) }
    val currentOnDismiss by rememberUpdatedState(onDismissRequest)
    val coroutineScope = rememberCoroutineScope()
    val internalPopupState = remember { mutableStateOf(show.value) }

    LaunchedEffect(show.value) {
        if (show.value) {
            internalPopupState.value = true
            animationProgress.animateTo(
                targetValue = 1f,
                animationSpec = spring(dampingRatio = 0.82f, stiffness = 362.5f, visibilityThreshold = 0.001f),
            )
        } else {
            animationProgress.animateTo(
                targetValue = 0f,
                animationSpec = tween(300, easing = DecelerateEasing(1.5f)),
            )
            internalPopupState.value = false
        }
    }

    val resetGesture: suspend () -> Unit = {
        animationProgress.animateTo(1f, animationSpec = tween(300, easing = DecelerateEasing(1.5f)))
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
            currentOnDismiss?.invoke()
        },
    )

    LaunchedEffect(navigationEventState.transitionState) {
        val transitionState = navigationEventState.transitionState
        if (
            transitionState is NavigationEventTransitionState.InProgress &&
            transitionState.direction == NavigationEventTransitionState.TRANSITIONING_BACK
        ) {
            val progress = transitionState.latestEvent.progress
            animationProgress.snapTo(1f - progress)
        }
    }

    if (!show.value && !internalPopupState.value) return

    var parentBounds by remember { mutableStateOf(IntRect.Zero) }

    Spacer(
        modifier = Modifier
            .onGloballyPositioned { childCoordinates ->
                childCoordinates.parentLayoutCoordinates?.let { parentLayoutCoordinates ->
                    val positionInWindow = parentLayoutCoordinates.positionInWindow()
                    parentBounds = IntRect(
                        left = positionInWindow.x.toInt(),
                        top = positionInWindow.y.toInt(),
                        right = positionInWindow.x.toInt() + parentLayoutCoordinates.size.width,
                        bottom = positionInWindow.y.toInt() + parentLayoutCoordinates.size.height,
                    )
                }
            },
    )

    if (parentBounds == IntRect.Zero) return

    var popupContentSize by remember { mutableStateOf(IntSize.Zero) }
    val layoutInfo = rememberListPopupLayoutInfo(
        alignment = alignment,
        popupPositionProvider = popupPositionProvider,
        parentBounds = parentBounds,
        popupContentSize = popupContentSize,
    )

    PopupLayout(
        visible = internalPopupState,
        enableWindowDim = false,
        enableBackHandler = false,
        enterTransition = EnterTransition.None,
        exitTransition = ExitTransition.None,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            if (enableWindowDim) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            alpha = animationProgress.value
                        }
                        .background(MiuixTheme.colorScheme.windowDimming),
                )
            }
            Box(
                modifier = popupModifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { currentOnDismiss?.invoke() },
                        )
                    }
                    .layout { measurable, constraints ->
                        val minWidthPx = minWidth.roundToPx().coerceAtMost(constraints.maxWidth)
                        val classicMinHeightPx = ((16.dp.roundToPx() * 2) + 50.dp.roundToPx()) * 2
                        val safeWindowMaxHeightPx = 416.dp.roundToPx()
                        val availableBelow = layoutInfo.windowBounds.bottom - parentBounds.bottom - layoutInfo.popupMargin.bottom
                        val availableAbove = parentBounds.top - layoutInfo.windowBounds.top - layoutInfo.popupMargin.top
                        val sideConstrainedMax = maxOf(availableBelow, availableAbove).coerceAtLeast(0)
                        val finalMaxHeightPx = listOf(
                            maxHeight?.roundToPx() ?: Int.MAX_VALUE,
                            safeWindowMaxHeightPx,
                            sideConstrainedMax,
                            constraints.maxHeight,
                        ).min().coerceAtLeast(classicMinHeightPx.coerceAtMost(constraints.maxHeight))
                        val placeable = measurable.measure(
                            constraints.copy(
                                minWidth = minWidthPx,
                                minHeight = classicMinHeightPx.coerceAtMost(constraints.maxHeight),
                                maxHeight = finalMaxHeightPx,
                                maxWidth = constraints.maxWidth,
                            ),
                        )
                        val measuredSize = IntSize(placeable.width, placeable.height)

                        val calculatedOffset = popupPositionProvider.calculatePosition(
                            parentBounds,
                            layoutInfo.windowBounds,
                            layoutDirection,
                            measuredSize,
                            layoutInfo.popupMargin,
                            alignment,
                        )

                        layout(constraints.maxWidth, constraints.maxHeight) {
                            placeable.place(calculatedOffset)
                        }
                    },
            ) {
                ListPopupContent(
                    popupContentSize = popupContentSize,
                    onPopupContentSizeChange = { popupContentSize = it },
                    animationProgress = { animationProgress.value },
                    popupLayoutInfo = layoutInfo.popupLayoutInfo,
                    localTransformOrigin = layoutInfo.localTransformOrigin,
                    content = content,
                )
            }
        }
    }
}
