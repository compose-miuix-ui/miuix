// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class PagerReboundTakeoverTest {
    private enum class OverscrollMode { Modifier, Factory, Both }

    @Test
    fun swipeRightDuringTopInertialRebound() = assertTakeover(atTop = true, mode = OverscrollMode.Modifier)

    @Test
    fun swipeLeftDuringBottomInertialRebound() = assertTakeover(atTop = false, mode = OverscrollMode.Modifier)

    @Test
    fun swipeRightDuringFactoryTopRebound() = assertTakeover(atTop = true, mode = OverscrollMode.Factory)

    @Test
    fun swipeLeftDuringFactoryBottomRebound() = assertTakeover(atTop = false, mode = OverscrollMode.Factory)

    @Test
    fun swipeRightDuringCombinedTopRebound() = assertTakeover(atTop = true, mode = OverscrollMode.Both)

    @Test
    fun swipeLeftDuringCombinedBottomRebound() = assertTakeover(atTop = false, mode = OverscrollMode.Both)

    @Test
    fun verticalDragDuringReboundStaysInThePage() = assertTakeover(atTop = true, mode = OverscrollMode.Both, horizontal = false)

    private fun assertTakeover(atTop: Boolean, mode: OverscrollMode, horizontal: Boolean = true) = runComposeUiTest {
        lateinit var pager: PagerState
        lateinit var list: LazyListState
        lateinit var effect: MiuixOverscrollEffect
        var modifierOffset = 0f
        var finishedFlings = 0
        var interceptions = 0
        val connection = object : NestedScrollConnection {
            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                finishedFlings++
                return Velocity.Zero
            }
        }
        setContent {
            pager = rememberPagerState(initialPage = 1) { 3 }
            val fling = PagerDefaults.flingBehavior(pager, snapAnimationSpec = PagerNavigationSpringSpec)
            HorizontalPager(
                state = pager,
                modifier = Modifier.size(400.dp).testTag("pager").nestedScroll(connection)
                    .pagerGestureOverride(pager, fling, onTriggered = { interceptions++ }),
                userScrollEnabled = false,
                flingBehavior = fling,
                pageNestedScrollConnection = PagerGestureNestedScrollConnection,
            ) { page ->
                // Each page owns its overscroll state; the pager must not rely on a global flag.
                CompositionLocalProvider(LocalOverScrollState provides remember { OverScrollState() }) {
                    val listState = rememberLazyListState(initialFirstVisibleItemIndex = if (atTop) 2 else 11)
                    val overscroll = remember { MiuixOverscrollEffect() }
                    val callback: (Float) -> Unit = remember { { if (page == 1) modifierOffset = it } }
                    if (page == 1) {
                        list = listState
                        effect = overscroll
                    }
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize().testTag("list-$page").then(
                            if (mode != OverscrollMode.Factory) Modifier.overScrollVertical(onOffsetChanged = callback) else Modifier,
                        ),
                        overscrollEffect = if (mode != OverscrollMode.Modifier) overscroll else null,
                    ) {
                        items(20) { Box(Modifier.fillMaxWidth().height(60.dp)) }
                    }
                }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        val direction = if (atTop) 1f else -1f
        onNodeWithTag("list-1").performTouchInput {
            down(center)
            repeat(5) { moveBy(Offset(0f, direction * 16f), delayMillis = 16) }
            up()
        }
        assertEquals(0f, modifierOffset, 0f, "The drag must release before hitting the boundary")
        assertEquals(0f, effect.offsetY, 0f, "Overscroll must be caused by inertia, not the held drag")
        fun isRebounding() = abs(modifierOffset) > 1f || abs(effect.offsetY) > 1f
        var frames = 0
        while ((!isRebounding() || finishedFlings == 0 || list.isScrollInProgress) && frames++ < 120) {
            mainClock.advanceTimeByFrame()
            waitForIdle()
        }
        assertTrue(isRebounding(), "The list must reach an inertial edge rebound")
        assertTrue(finishedFlings > 0, "The nested fling must have returned before the new gesture")
        assertTrue(!list.isScrollInProgress, "The remaining movement must be the independent rebound")
        assertEquals(1, pager.currentPage)
        val offsetBefore = list.firstVisibleItemIndex * 60 + list.firstVisibleItemScrollOffset
        onNodeWithTag("pager").performTouchInput {
            if (horizontal) {
                val startX = if (atTop) width * 0.2f else width * 0.8f
                val deltaX = if (atTop) width * 0.12f else -width * 0.12f
                down(Offset(startX, centerY))
                repeat(5) { moveBy(Offset(deltaX, 0f), delayMillis = 16) }
            } else {
                down(Offset(centerX, height * 0.7f))
                repeat(5) { moveBy(Offset(0f, -height * 0.08f), delayMillis = 16) }
            }
            up()
        }
        mainClock.advanceTimeBy(2500)
        waitForIdle()
        if (horizontal) {
            assertEquals(if (atTop) 0 else 2, pager.currentPage, "Horizontal input must switch pages during the rebound")
            assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
            assertEquals(1, interceptions, "The gesture must be claimed once")
        } else {
            assertEquals(1, pager.currentPage, "Vertical input must stay with the list")
            assertEquals(0, interceptions)
            assertTrue(list.firstVisibleItemIndex * 60 + list.firstVisibleItemScrollOffset > offsetBefore)
        }
    }
}
