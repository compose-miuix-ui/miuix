// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.PullToRefresh
import top.yukonga.miuix.kmp.basic.PullToRefreshState
import top.yukonga.miuix.kmp.basic.RefreshState
import top.yukonga.miuix.kmp.basic.rememberPullToRefreshState
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class OverscrollContractTest {
    @Test
    fun ancestorConsumptionIsAccountedOnceDuringTouchDrag() = assertAncestorAccounting(wheel = false)

    @Test
    fun wheelPassesThroughToAncestorWithoutDuplicateConsumption() = assertAncestorAccounting(wheel = true)

    private fun assertAncestorAccounting(wheel: Boolean) = runComposeUiTest {
        var ancestorPreConsumed = 0f
        var ancestorPostConsumed = 0f
        var childPreAvailable = 0f
        var childPostAvailable = 0f
        var offset = 0f
        val ancestor = object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val consumed = available.y / 4f
                ancestorPreConsumed += consumed
                return Offset(0f, consumed)
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                val taken = available.y / 2f
                ancestorPostConsumed += taken
                return Offset(0f, taken)
            }
        }
        val child = object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                childPreAvailable += available.y
                return Offset.Zero
            }

            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                childPostAvailable += available.y
                return Offset.Zero
            }
        }
        setContent {
            val originalWindow = LocalWindowInfo.current
            val density = LocalDensity.current
            val window = remember { ResizableWindowInfo(originalWindow, density) }
            CompositionLocalProvider(
                LocalWindowInfo provides window,
                LocalOverScrollState provides remember { OverScrollState() },
            ) {
                Box(Modifier.size(300.dp).nestedScroll(ancestor).overScrollVertical(onOffsetChanged = { offset = it })) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize().testTag("drag").nestedScroll(child),
                        overscrollEffect = null,
                    ) {
                        items(if (wheel) 20 else 1) { Box(Modifier.size(40.dp)) }
                    }
                }
            }
        }
        if (wheel) {
            onNodeWithTag("drag").performMouseInput { scroll(120f) }
        } else {
            onNodeWithTag("drag").performTouchInput {
                down(center)
                repeat(3) { moveBy(Offset(0f, 40f), delayMillis = 16) }
            }
        }
        runOnIdle {
            assertTrue(abs(childPreAvailable) > 1f, "Input must reach the real scrollable")
            assertEquals(childPreAvailable / 3f, ancestorPreConsumed, 0.01f, "An ancestor takes one quarter, leaving three quarters for the child")
            if (wheel) {
                assertEquals(childPostAvailable / 2f, ancestorPostConsumed, 0.01f, "Wheel leftovers must reach the parent exactly once")
                assertEquals(0f, offset, 0f, "Wheel input must never stretch the content")
            } else {
                assertEquals(0f, ancestorPostConsumed, 0.01f, "Overscroll consumes the remainder before the ancestor post pass")
                assertEquals(damping(childPostAvailable, 800f), offset, 0.01f, "The content stretches only by the actual remaining distance")
            }
        }
        if (!wheel) onNodeWithTag("drag").performTouchInput { up() }
    }

    @Test
    fun idlePullToRefreshStartsBeforeExplicitOverscroll() = runComposeUiTest {
        lateinit var refresh: PullToRefreshState
        var overscrollOffset = 0f
        var refreshRequests = 0
        setContent {
            MiuixTheme {
                CompositionLocalProvider(LocalOverScrollState provides remember { OverScrollState() }) {
                    refresh = rememberPullToRefreshState(refreshThreshold = 1f)
                    PullToRefresh(
                        isRefreshing = false,
                        onRefresh = { refreshRequests++ },
                        modifier = Modifier.size(300.dp),
                        pullToRefreshState = refresh,
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize().testTag("refresh-list")
                                .overScrollVertical(onOffsetChanged = { overscrollOffset = it }),
                            overscrollEffect = null,
                        ) {
                            item { Box(Modifier.size(40.dp)) }
                        }
                    }
                }
            }
        }
        onNodeWithTag("refresh-list").performTouchInput {
            down(Offset(centerX, height * 0.2f))
            repeat(3) { moveBy(Offset(0f, 35f), delayMillis = 16) }
        }
        var pulledOffset = 0f
        runOnIdle {
            assertEquals(RefreshState.Pulling, refresh.refreshState)
            assertTrue(refresh.dragOffset > 1f, "The idle ancestor must receive the first downward edge delta")
            assertEquals(0f, overscrollOffset, 0f)
            pulledOffset = refresh.dragOffset
        }
        onNodeWithTag("refresh-list").performTouchInput {
            moveBy(Offset(0f, -30f), delayMillis = 16)
        }
        runOnIdle {
            assertTrue(refresh.dragOffset < pulledOffset, "The active refresh retains priority while pulling back")
            assertEquals(0f, overscrollOffset, 0f)
            assertEquals(0, refreshRequests)
        }
        onNodeWithTag("refresh-list").performTouchInput { up() }
    }

    @Test
    fun resizingSameWindowUpdatesModifierDamping() = assertResizeDamping(factory = false, vertical = true)

    @Test
    fun resizingSameWindowUpdatesFactoryVerticalDamping() = assertResizeDamping(factory = true, vertical = true)

    @Test
    fun resizingSameWindowUpdatesFactoryHorizontalDamping() = assertResizeDamping(factory = true, vertical = false)

    private fun assertResizeDamping(factory: Boolean, vertical: Boolean) = runComposeUiTest {
        lateinit var window: ResizableWindowInfo
        lateinit var effect: MiuixOverscrollEffect
        var modifierOffset = 0f
        var rawDistance = 0f
        val observer = object : NestedScrollConnection {
            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                if (source == NestedScrollSource.UserInput) rawDistance += if (vertical) available.y else available.x
                return Offset.Zero
            }
        }
        setContent {
            val originalWindow = LocalWindowInfo.current
            val density = LocalDensity.current
            window = remember { ResizableWindowInfo(originalWindow, density) }
            effect = remember { MiuixOverscrollEffect() }
            CompositionLocalProvider(
                LocalWindowInfo provides window,
                LocalOverScrollState provides remember { OverScrollState() },
            ) {
                Box(
                    Modifier.size(300.dp).then(
                        if (factory) Modifier else Modifier.overScrollOutOfBound(vertical, onOffsetChanged = { modifierOffset = it }),
                    ),
                ) {
                    val listModifier = Modifier.fillMaxSize().testTag("drag").nestedScroll(observer)
                    if (vertical) {
                        LazyColumn(modifier = listModifier, overscrollEffect = if (factory) effect else null) {
                            items(if (factory) 20 else 1) { Box(Modifier.size(40.dp)) }
                        }
                    } else {
                        LazyRow(modifier = listModifier, overscrollEffect = if (factory) effect else null) {
                            items(if (factory) 20 else 1) { Box(Modifier.size(40.dp)) }
                        }
                    }
                }
            }
        }
        var initialOffset = 0f
        repeat(2) { pass ->
            if (pass == 1) {
                runOnIdle {
                    // The provider identity and density stay unchanged; only its observable size changes.
                    window.containerSize = IntSize(800, 1600)
                    rawDistance = 0f
                }
                waitForIdle()
            }
            onNodeWithTag("drag").performTouchInput {
                down(Offset(width * 0.2f, height * 0.2f))
                repeat(3) { moveBy(if (vertical) Offset(0f, 40f) else Offset(40f, 0f), delayMillis = 16) }
            }
            runOnIdle {
                val actual = if (factory) (if (vertical) effect.offsetY else effect.offsetX) else modifierOffset
                val range = (if (vertical) 800f else 400f) * (pass + 1)
                assertTrue(rawDistance > 1f)
                assertEquals(damping(rawDistance, range), actual, 0.01f)
                if (pass == 0) initialOffset = actual else assertTrue(actual > initialOffset, "The larger window must change the visible damping")
            }
            onNodeWithTag("drag").performTouchInput { up() }
            waitForIdle()
        }
    }

    @Test
    fun changingAxisCancelsReboundAndUsesNewAxisRange() = runComposeUiTest {
        var vertical by mutableStateOf(true)
        var offset = 0f
        var rawDistance = 0f
        val observer = object : NestedScrollConnection {
            override fun onPostScroll(consumed: Offset, available: Offset, source: NestedScrollSource): Offset {
                if (source == NestedScrollSource.UserInput) rawDistance += if (vertical) available.y else available.x
                return Offset.Zero
            }
        }
        setContent {
            val originalWindow = LocalWindowInfo.current
            val density = LocalDensity.current
            val window = remember { ResizableWindowInfo(originalWindow, density) }
            CompositionLocalProvider(
                LocalWindowInfo provides window,
                LocalOverScrollState provides remember { OverScrollState() },
            ) {
                Box(Modifier.size(300.dp).overScrollOutOfBound(vertical, onOffsetChanged = { offset = it })) {
                    val listModifier = Modifier.fillMaxSize().testTag("drag").nestedScroll(observer)
                    if (vertical) {
                        LazyColumn(modifier = listModifier, overscrollEffect = null) {
                            item { Box(Modifier.size(40.dp)) }
                        }
                    } else {
                        LazyRow(modifier = listModifier, overscrollEffect = null) {
                            item { Box(Modifier.size(40.dp)) }
                        }
                    }
                }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        onNodeWithTag("drag").performTouchInput {
            down(Offset(width * 0.2f, height * 0.2f))
            repeat(3) { moveBy(Offset(0f, 40f), delayMillis = 16) }
            up()
        }
        mainClock.advanceTimeByFrame()
        runOnIdle { assertTrue(offset > 1f, "A vertical rebound must still be running") }
        runOnIdle {
            vertical = false
            rawDistance = 0f
        }
        mainClock.advanceTimeByFrame()
        runOnIdle { assertEquals(0f, offset, 0f, "Changing axis clears the previous displacement") }
        mainClock.advanceTimeBy(500)
        runOnIdle { assertEquals(0f, offset, 0f, "The cancelled vertical spring must not write into the horizontal axis") }
        onNodeWithTag("drag").performTouchInput {
            down(Offset(width * 0.2f, height * 0.2f))
            repeat(3) { moveBy(Offset(40f, 0f), delayMillis = 16) }
        }
        runOnIdle {
            assertTrue(rawDistance > 1f)
            assertEquals(damping(rawDistance, 400f), offset, 0.01f, "The new horizontal drag uses width rather than cached height")
        }
        onNodeWithTag("drag").performTouchInput { up() }
        mainClock.advanceTimeBy(2500)
    }

    private class ResizableWindowInfo(delegate: WindowInfo, private val density: Density) : WindowInfo by delegate {
        override var containerSize by mutableStateOf(IntSize(400, 800))
        override val containerDpSize: DpSize
            get() = with(density) { DpSize(containerSize.width.toDp(), containerSize.height.toDp()) }
    }

    private fun damping(distance: Float, range: Float): Float = SpringMath.obtainDampingDistance(distance / range, range)
}
