// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.util.fastFirstOrNull
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * Pager gesture conflict resolution modes when nested scrollables (such as LazyColumn)
 * have vertical momentum or overscroll.
 */
enum class PagerInterceptionMode(val title: String) {
    /** Native Compose behavior without intervention. */
    Native("Default"),

    /**
     * Cross-Axis Interceptor: Prioritizes horizontal swipe on the HorizontalPager level,
     * cleanly driving page transitions without interference from child list fling/overscroll.
     */
    CrossAxisInterceptor("Cross-Axis"),

    /**
     * Tap to Halt (iOS-like): The initial horizontal gesture halts vertical list inertia;
     * subsequent horizontal swipe flips pages natively.
     */
    TapToHalt("iOS-like"),
}

/**
 * Spring spec shared by pager tab navigation and snap fling.
 */
val PagerNavigationSpringSpec: SpringSpec<Float> = spring(
    stiffness = 322.2f,
    dampingRatio = 32.31f / (2f * kotlin.math.sqrt(322.2f)),
    visibilityThreshold = 0.5f,
)

/**
 * Smoothly animates [PagerState] to the specified [target] page using [PagerNavigationSpringSpec].
 * Focus requests will not interrupt tab navigation before the page settles.
 * If called before the pager layout has been measured, falls back to [PagerState.scrollToPage].
 */
suspend fun PagerState.springAnimateToPage(target: Int) {
    if (target !in 0 until pageCount) return
    val pageSize = layoutInfo.pageSize + layoutInfo.pageSpacing
    if (pageSize <= 0) {
        scrollToPage(target)
        return
    }
    scroll(MutatePriority.UserInput) {
        val distance = (target - currentPage - currentPageOffsetFraction) * pageSize.toFloat()
        var previousValue = 0f

        updateTargetPage(target)
        animate(
            initialValue = 0f,
            targetValue = distance,
            animationSpec = PagerNavigationSpringSpec,
        ) { currentValue, _ ->
            previousValue += scrollBy(currentValue - previousValue)
        }
    }
}

/**
 * Cross-Axis Gesture Interceptor (applied at HorizontalPager level):
 *
 * Exclusively drives the pager when active to avoid conflicting with HorizontalPager's
 * internal DraggableNode. Clamps target page strictly to at most 1 page from the starting page
 * (downPage) based on physical touch displacement (totalDx) to guarantee consistent directional navigation.
 */
fun Modifier.horizontalPagerSwipeOverride(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    mode: PagerInterceptionMode = PagerInterceptionMode.CrossAxisInterceptor,
    enabled: Boolean = true,
    onIntercepted: (() -> Unit)? = null,
): Modifier = if (!enabled) {
    this
} else {
    this.pointerInput(mode, enabled, pagerState, onIntercepted) {
        val touchSlop = viewConfiguration.touchSlop
        val velocityTracker = VelocityTracker()
        var animationJob: kotlinx.coroutines.Job? = null

        awaitEachGesture {
            val down = awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)

            // Cancel in-flight animation on touch down and settle pager offset to prevent
            // DefaultPagerNestedScrollConnection from pulling the pager backwards during child scrolling
            if (animationJob?.isActive == true || pagerState.isScrollInProgress) {
                animationJob?.cancel()
                val offsetFraction = pagerState.currentPageOffsetFraction
                if (abs(offsetFraction) > 1e-3f) {
                    val settled = if (offsetFraction > 0.5f) {
                        (pagerState.currentPage + 1).coerceAtMost(pagerState.pageCount - 1)
                    } else if (offsetFraction < -0.5f) {
                        (pagerState.currentPage - 1).coerceAtLeast(0)
                    } else {
                        pagerState.currentPage
                    }
                    coroutineScope.launch {
                        pagerState.scrollToPage(settled)
                    }
                }
            }

            velocityTracker.resetTracking()
            velocityTracker.addPosition(down.uptimeMillis, down.position)

            val downPage = pagerState.currentPage
            val pageCount = pagerState.pageCount
            var isDraggingPager = false
            val downPos = down.position
            var accumulatedDispatchedDx = 0f

            while (true) {
                val event = awaitPointerEvent(pass = PointerEventPass.Initial)
                val change = event.changes.fastFirstOrNull { it.id == down.id } ?: break

                val pageWidth = (pagerState.layoutInfo.pageSize + pagerState.layoutInfo.pageSpacing).coerceAtLeast(1)

                if (!change.pressed) {
                    if (isDraggingPager) {
                        change.consume()
                        val velocityX = velocityTracker.calculateVelocity().x
                        val totalDx = change.position.x - downPos.x
                        // Physical displacement: negative totalDx means finger dragged LEFT (forward)
                        val dragDistanceFraction = -totalDx / pageWidth.toFloat()

                        // A fling must strictly agree with the physical drag direction to prevent opposite-direction flips!
                        val isFlingForward = velocityX < -800f && totalDx < -touchSlop
                        val isFlingBackward = velocityX > 800f && totalDx > touchSlop
                        val isDragForward = dragDistanceFraction > 0.35f
                        val isDragBackward = dragDistanceFraction < -0.35f

                        val targetPage = when {
                            isFlingForward || isDragForward -> (downPage + 1).coerceAtMost(pageCount - 1)
                            isFlingBackward || isDragBackward -> (downPage - 1).coerceAtLeast(0)
                            else -> downPage
                        }
                        animationJob = coroutineScope.launch {
                            pagerState.springAnimateToPage(targetPage)
                        }
                    }
                    break
                }

                val currentPos = change.position
                val totalDx = currentPos.x - downPos.x
                val totalDy = currentPos.y - downPos.y

                velocityTracker.addPosition(change.uptimeMillis, change.position)

                if (!isDraggingPager) {
                    val absTotalX = abs(totalDx)
                    val absTotalY = abs(totalDy)

                    // Strict directional threshold: must be predominantly horizontal (at least 2x greater than Y)
                    if (absTotalX > touchSlop * 1.5f && absTotalX > absTotalY * 2.0f) {
                        isDraggingPager = true
                        onIntercepted?.invoke()
                        change.consume()

                        val maxDragPx = pageWidth.toFloat()
                        val minBound = if (downPage < pageCount - 1) -maxDragPx else 0f
                        val maxBound = if (downPage > 0) maxDragPx else 0f
                        val clampedTotalDx = totalDx.coerceIn(minBound, maxBound)
                        val deltaToDispatch = clampedTotalDx - accumulatedDispatchedDx
                        accumulatedDispatchedDx += deltaToDispatch
                        if (deltaToDispatch != 0f) {
                            pagerState.dispatchRawDelta(-deltaToDispatch)
                        }
                    } else if (absTotalY > touchSlop && absTotalY > absTotalX) {
                        break
                    }
                } else {
                    change.consume()
                    val maxDragPx = pageWidth.toFloat()
                    val minBound = if (downPage < pageCount - 1) -maxDragPx else 0f
                    val maxBound = if (downPage > 0) maxDragPx else 0f
                    val clampedTotalDx = totalDx.coerceIn(minBound, maxBound)
                    val deltaToDispatch = clampedTotalDx - accumulatedDispatchedDx
                    accumulatedDispatchedDx += deltaToDispatch
                    if (deltaToDispatch != 0f) {
                        pagerState.dispatchRawDelta(-deltaToDispatch)
                    }
                }
            }
        }
    }
}

/**
 * Tracks nested vertical fling momentum from child scrollables via [NestedScrollConnection].
 */
class PagerFlingTrackerConnection : NestedScrollConnection {
    var isChildFlinging by mutableStateOf(false)
        internal set

    var haltFling by mutableStateOf(false)
        internal set

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        @Suppress("DEPRECATION")
        val isFlingSource = source == NestedScrollSource.SideEffect || source == NestedScrollSource.Fling
        if (isFlingSource && abs(available.y) > 0.5f) {
            isChildFlinging = true
            if (haltFling) {
                // Consume all vertical scroll to force child fling animation to cancel immediately
                return available
            }
        }
        return Offset.Zero
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        if (haltFling) {
            haltFling = false
            isChildFlinging = false
            // Consume all fling velocity
            return available
        }
        return Velocity.Zero
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        isChildFlinging = false
        haltFling = false
        return Velocity.Zero
    }
}

/**
 * Tap to Halt (iOS-like behavior applied at HorizontalPager level):
 *
 * When vertical momentum is active on touch down, halts the motion and consumes the
 * horizontal drag of that initial gesture. Once the list is at rest, subsequent
 * horizontal swipes pass through untouched for native HorizontalPager page transitions.
 */
fun Modifier.iosStyleMomentumHalt(
    flingTracker: PagerFlingTrackerConnection,
    mode: PagerInterceptionMode = PagerInterceptionMode.TapToHalt,
    enabled: Boolean = true,
    onHalted: (() -> Unit)? = null,
): Modifier = if (!enabled) {
    this
} else {
    this.nestedScroll(flingTracker).pointerInput(mode, enabled, onHalted) {
        val touchSlop = viewConfiguration.touchSlop

        awaitEachGesture {
            val down = awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)
            val downPos = down.position
            val hadMomentum = flingTracker.isChildFlinging
            if (!hadMomentum) {
                // List is already at rest: do not intercept; allow native HorizontalPager swipe
                return@awaitEachGesture
            }

            // Momentum was active: actively halt the child fling animation
            flingTracker.haltFling = true
            flingTracker.isChildFlinging = false
            onHalted?.invoke()
            var isHalting = false

            while (true) {
                val event = awaitPointerEvent(pass = PointerEventPass.Initial)
                val change = event.changes.fastFirstOrNull { it.id == down.id } ?: break
                if (!change.pressed) break

                val totalDx = abs(change.position.x - downPos.x)
                val totalDy = abs(change.position.y - downPos.y)

                if (!isHalting && totalDx > touchSlop && totalDx > totalDy * 2f) {
                    isHalting = true
                }

                if (isHalting) {
                    change.consume()
                } else if (totalDy > touchSlop && totalDy > totalDx) {
                    break
                }
            }
        }
    }
}

/**
 * Convenient modifier that applies the selected [mode] resolution to [HorizontalPager].
 */
fun Modifier.pagerGestureOverride(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    mode: PagerInterceptionMode = PagerInterceptionMode.CrossAxisInterceptor,
    enabled: Boolean = true,
    flingTracker: PagerFlingTrackerConnection? = null,
    onTriggered: (() -> Unit)? = null,
): Modifier = when (mode) {
    PagerInterceptionMode.CrossAxisInterceptor -> horizontalPagerSwipeOverride(
        pagerState = pagerState,
        coroutineScope = coroutineScope,
        mode = mode,
        enabled = enabled,
        onIntercepted = onTriggered,
    )

    PagerInterceptionMode.TapToHalt -> if (flingTracker != null) {
        iosStyleMomentumHalt(
            flingTracker = flingTracker,
            mode = mode,
            enabled = enabled,
            onHalted = onTriggered,
        )
    } else {
        this
    }

    PagerInterceptionMode.Native -> this
}

/**
 * Convenient modifier overload that accepts raw integer [mode] for settings storage.
 */
fun Modifier.pagerGestureOverride(
    pagerState: PagerState,
    coroutineScope: CoroutineScope,
    mode: Int,
    enabled: Boolean = true,
    flingTracker: PagerFlingTrackerConnection? = null,
    onTriggered: (() -> Unit)? = null,
): Modifier = pagerGestureOverride(
    pagerState = pagerState,
    coroutineScope = coroutineScope,
    mode = PagerInterceptionMode.entries.getOrElse(mode) { PagerInterceptionMode.Native },
    enabled = enabled,
    flingTracker = flingTracker,
    onTriggered = onTriggered,
)

/**
 * Convenient composable modifier that applies the selected [mode] resolution to [HorizontalPager].
 */
@Composable
fun Modifier.pagerGestureOverride(
    pagerState: PagerState,
    mode: PagerInterceptionMode = PagerInterceptionMode.CrossAxisInterceptor,
    enabled: Boolean = true,
    onTriggered: (() -> Unit)? = null,
): Modifier {
    val coroutineScope = rememberCoroutineScope()
    val flingTracker = remember { PagerFlingTrackerConnection() }
    return pagerGestureOverride(
        pagerState = pagerState,
        coroutineScope = coroutineScope,
        mode = mode,
        enabled = enabled,
        flingTracker = flingTracker,
        onTriggered = onTriggered,
    )
}

/**
 * Convenient composable modifier overload that accepts raw integer [mode] for settings storage.
 */
@Composable
fun Modifier.pagerGestureOverride(
    pagerState: PagerState,
    mode: Int,
    enabled: Boolean = true,
    onTriggered: (() -> Unit)? = null,
): Modifier = pagerGestureOverride(
    pagerState = pagerState,
    mode = PagerInterceptionMode.entries.getOrElse(mode) { PagerInterceptionMode.Native },
    enabled = enabled,
    onTriggered = onTriggered,
)
