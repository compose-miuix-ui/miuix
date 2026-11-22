// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.foundation.MutatePriority
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.ScrollWheel
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performMouseInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class PagerNonTouchContractTest {
    @Test
    fun equalPriorityCancellationReleasesVerticalWheelAndAllowsAnotherPageScroll() = assertWheelOwnershipReleased(MutatePriority.UserInput)

    @Test
    fun higherPriorityCancellationReleasesVerticalWheelAndAllowsAnotherPageScroll() = assertWheelOwnershipReleased(MutatePriority.PreventUserInput)

    @Test
    fun replacingPagerStateCancelsOldWheelAndAcceptsNewInput() = assertWheelOwnershipReleased(MutatePriority.UserInput, replaceState = true)

    private fun assertWheelOwnershipReleased(priority: MutatePriority, replaceState: Boolean = false) = runComposeUiTest {
        lateinit var pager: PagerState
        lateinit var scope: CoroutineScope
        val lists = arrayOfNulls<LazyListState>(3)
        val useReplacement = mutableStateOf(false)
        lateinit var replacedPager: PagerState
        setContent {
            val original = rememberPagerState(initialPage = 1) { 3 }
            val replacement = rememberPagerState(initialPage = 1) { 3 }
            pager = if (useReplacement.value) replacement else original
            scope = rememberCoroutineScope()
            val fling = PagerDefaults.flingBehavior(pager, snapAnimationSpec = PagerNavigationSpringSpec)
            HorizontalPager(
                state = pager,
                modifier = Modifier.size(400.dp).testTag("pager").pagerGestureOverride(pager, fling),
                userScrollEnabled = false,
                flingBehavior = fling,
                pageNestedScrollConnection = PagerGestureNestedScrollConnection,
            ) { page ->
                val list = rememberLazyListState()
                lists[page] = list
                LazyColumn(
                    state = list,
                    modifier = Modifier.fillMaxSize(),
                    overscrollEffect = null,
                ) {
                    items(40) { Box(Modifier.fillMaxWidth().height(60.dp)) }
                }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        onNodeWithTag("pager").performMouseInput {
            moveTo(center)
            scroll(20f, ScrollWheel.Horizontal)
        }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        runOnIdle {
            assertTrue(pager.isScrollInProgress, "Cancel an active wheel mutation, not an already completed snap")
            if (replaceState) {
                replacedPager = pager
                useReplacement.value = true
            } else {
                scope.launch(start = CoroutineStart.UNDISPATCHED) {
                    with(pager) {
                        scroll(priority) {
                            updateCurrentPage(1)
                        }
                    }
                }
            }
        }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertEquals(1, pager.currentPage)
        assertEquals(0f, pager.currentPageOffsetFraction)
        val previousPosition = if (replaceState) replacedPager.currentPage + replacedPager.currentPageOffsetFraction else 0f
        onNodeWithTag("pager").performMouseInput {
            scroll(10f, ScrollWheel.Vertical)
        }
        mainClock.advanceTimeBy(300)
        waitForIdle()
        val list = requireNotNull(lists[1])
        assertTrue(
            list.firstVisibleItemIndex > 0 || list.firstVisibleItemScrollOffset > 0,
            "Cancelled pager input must not swallow the child's vertical wheel",
        )
        assertEquals(1, pager.currentPage)
        if (replaceState) {
            assertTrue(!replacedPager.isScrollInProgress, "Replacing state must release the old wheel mutation")
            assertEquals(previousPosition, replacedPager.currentPage + replacedPager.currentPageOffsetFraction)
        }
        onNodeWithTag("pager").performMouseInput {
            scroll(30f, ScrollWheel.Horizontal)
        }
        mainClock.advanceTimeBy(2500)
        waitForIdle()
        assertEquals(2, pager.currentPage, "A new horizontal wheel gesture must still be accepted")
        assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
    }
}
