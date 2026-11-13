// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.ViewConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SkikoComposeUiTest
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.ColorPalette
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class, InternalComposeUiApi::class)
class PagerGestureContractTest {
    @Test
    fun nestedPagersOnlyAncestorOwnsVerticalReboundTakeover() = assertNestedOwnership(ancestorClaimsLater = false)

    @Test
    fun ancestorTakeoverCancelsAnAlreadyDraggingInnerPager() = assertNestedOwnership(ancestorClaimsLater = true)

    private fun assertNestedOwnership(ancestorClaimsLater: Boolean) = runComposeUiTest {
        lateinit var outer: PagerState
        lateinit var inner: PagerState
        lateinit var list: LazyListState
        lateinit var effect: MiuixOverscrollEffect
        var outerClaims = 0
        var innerClaims = 0
        var completedFlings = 0
        var outerSlop = 0f
        val connection = object : NestedScrollConnection {
            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                completedFlings++
                return Velocity.Zero
            }
        }
        setContent {
            outer = rememberPagerState(initialPage = 1) { 3 }
            val configuration = LocalViewConfiguration.current
            outerSlop = configuration.touchSlop
            val innerConfiguration = remember(configuration, ancestorClaimsLater) {
                if (ancestorClaimsLater) {
                    object : ViewConfiguration by configuration {
                        override val touchSlop: Float = configuration.touchSlop / 4f
                    }
                } else {
                    configuration
                }
            }
            TestPager(outer, onTriggered = { outerClaims++ }) { outerPage ->
                val innerState = rememberPagerState(initialPage = 1) { 3 }
                if (outerPage == 1) inner = innerState
                CompositionLocalProvider(LocalViewConfiguration provides innerConfiguration) {
                    TestPager(innerState, tag = "inner-$outerPage", onTriggered = { innerClaims++ }) { innerPage ->
                        val listState = rememberLazyListState(initialFirstVisibleItemIndex = 2)
                        val overscroll = remember { MiuixOverscrollEffect() }
                        if (outerPage == 1 && innerPage == 1) {
                            list = listState
                            effect = overscroll
                        }
                        LazyColumn(
                            state = listState,
                            modifier = Modifier.fillMaxSize().testTag("list-$outerPage-$innerPage").nestedScroll(connection),
                            overscrollEffect = overscroll,
                        ) {
                            items(20) { Box(Modifier.fillMaxWidth().height(60.dp)) }
                        }
                    }
                }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        onNodeWithTag("list-1-1").performTouchInput {
            down(center)
            repeat(5) { moveBy(Offset(0f, 16f), delayMillis = 16) }
            up()
        }
        assertEquals(0f, effect.offsetY, 0f, "The boundary must be reached by inertia, not the held drag")
        advanceUntil { abs(effect.offsetY) > 1f && completedFlings > 0 && !list.isScrollInProgress }
        if (ancestorClaimsLater) {
            onNodeWithTag("pager").performTouchInput {
                down(Offset(width * 0.15f, centerY))
                moveBy(Offset(outerSlop / 2f, 0f), delayMillis = 16)
            }
            waitForIdle()
            assertTrue(abs(inner.currentPageOffsetFraction) > 0.001f)
            assertEquals(0f, outer.currentPageOffsetFraction, 0.001f)
            onNodeWithTag("pager").performTouchInput { moveBy(Offset(width * 0.12f, 0f), delayMillis = 16) }
            finishAnimations()
            assertFalse(inner.isScrollInProgress, "The inner drag must stop as soon as Initial is consumed")
            assertEquals(0f, inner.currentPageOffsetFraction, 0.001f, "The cancelled partial inner drag must snap while the outer finger is still held")
            assertTrue(outer.isScrollInProgress)
            onNodeWithTag("pager").performTouchInput {
                repeat(4) { moveBy(Offset(width * 0.12f, 0f), delayMillis = 16) }
                up()
            }
        } else {
            swipePage("pager", right = true)
        }
        finishAnimations()
        assertEquals(0, outer.currentPage)
        assertEquals(1, inner.currentPage, "The ancestor's Initial consumption must cancel the inner takeover")
        assertEquals(0f, inner.currentPageOffsetFraction, 0.001f)
        assertEquals(1, outerClaims)
        assertEquals(if (ancestorClaimsLater) 1 else 0, innerClaims)
    }

    @Test
    fun horizontalListFlingDoesNotActivateOuterTakeover() = runComposeUiTest {
        lateinit var outer: PagerState
        lateinit var row: LazyListState
        var outerClaims = 0
        setContent {
            outer = rememberPagerState(initialPage = 1) { 3 }
            TestPager(outer, onTriggered = { outerClaims++ }) { page ->
                val state = rememberLazyListState(initialFirstVisibleItemIndex = 10)
                if (page == 1) row = state
                LazyRow(state = state, modifier = Modifier.fillMaxSize().testTag("row-$page"), overscrollEffect = null) {
                    items(100) { Box(Modifier.width(60.dp).fillMaxHeight()) }
                }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        onNodeWithTag("row-1").performTouchInput {
            down(center)
            repeat(5) { moveBy(Offset(-16f, 0f), delayMillis = 16) }
            up()
        }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertTrue(row.isScrollInProgress, "The second touch must interrupt a real horizontal fling")
        val before = row.firstVisibleItemIndex * 60 + row.firstVisibleItemScrollOffset
        swipePage("row-1")
        finishAnimations()
        assertTrue(row.firstVisibleItemIndex * 60 + row.firstVisibleItemScrollOffset > before)
        assertEquals(1, outer.currentPage)
        assertEquals(0f, outer.currentPageOffsetFraction, 0.001f)
        assertEquals(0, outerClaims)
    }

    @Test
    fun innerPagerFlingDoesNotActivateOuterTakeover() = runComposeUiTest {
        lateinit var outer: PagerState
        lateinit var inner: PagerState
        var outerClaims = 0
        setContent {
            outer = rememberPagerState(initialPage = 1) { 3 }
            TestPager(outer, onTriggered = { outerClaims++ }) { page ->
                val state = rememberPagerState(initialPage = 5) { 20 }
                if (page == 1) inner = state
                HorizontalPager(
                    state = state,
                    modifier = Modifier.fillMaxSize().testTag("inner-$page"),
                    flingBehavior = PagerDefaults.flingBehavior(state, snapAnimationSpec = PagerNavigationSpringSpec),
                ) { Box(Modifier.fillMaxSize()) }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        onNodeWithTag("inner-1").performTouchInput {
            down(center)
            repeat(3) { moveBy(Offset(-width * 0.05f, 0f), delayMillis = 16) }
            up()
        }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertTrue(inner.isScrollInProgress)
        val before = inner.currentPage + inner.currentPageOffsetFraction
        swipePage("inner-1")
        finishAnimations()
        assertTrue(inner.currentPage > before)
        assertEquals(1, outer.currentPage)
        assertEquals(0f, outer.currentPageOffsetFraction, 0.001f)
        assertEquals(0, outerClaims)
    }

    @Test
    fun horizontalModifierReboundStaysWithTheRow() = assertHorizontalRebound(modifier = true, factory = false)

    @Test
    fun horizontalFactoryReboundStaysWithTheRow() = assertHorizontalRebound(modifier = false, factory = true)

    @Test
    fun horizontalCombinedReboundStaysWithTheRow() = assertHorizontalRebound(modifier = true, factory = true)

    @Test
    fun paletteGestureOnIncomingPageKeepsItsDrag() = assertIncomingPageChildGesture(palette = true)

    @Test
    fun horizontalListGestureOnIncomingPageKeepsItsDrag() = assertIncomingPageChildGesture(palette = false)

    private fun assertIncomingPageChildGesture(palette: Boolean) = runComposeUiTest {
        lateinit var pager: PagerState
        lateinit var row: LazyListState
        var claims = 0
        var paletteChanges = 0
        setContent {
            pager = rememberPagerState(initialPage = 0) { 3 }
            TestPager(pager, onTriggered = { claims++ }) { page ->
                if (page == 1 && palette) {
                    ColorPalette(
                        color = Color.Red,
                        onColorChanged = { paletteChanges++ },
                        showPreview = false,
                        modifier = Modifier.fillMaxWidth().testTag("child"),
                    )
                } else if (page == 1) {
                    val state = rememberLazyListState()
                    row = state
                    LazyRow(
                        state = state,
                        modifier = Modifier.fillMaxSize().testTag("child"),
                        overscrollEffect = null,
                    ) {
                        items(40) { Box(Modifier.width(60.dp).fillMaxHeight()) }
                    }
                } else {
                    Box(Modifier.fillMaxSize())
                }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        // A real touch swipe starts the transition to page 1; the pager settles it.
        onNodeWithTag("pager").performTouchInput {
            down(Offset(width * 0.8f, centerY))
            repeat(4) { moveBy(Offset(-width * 0.1f, 0f), delayMillis = 16) }
            up()
        }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertTrue(pager.isScrollInProgress, "The page transition must still be running")
        var frames = 0
        while ((pager.currentPage + pager.currentPageOffsetFraction) !in 0.25f..0.75f && frames++ < 120) {
            mainClock.advanceTimeByFrame()
            waitForIdle()
        }
        val transition = pager.currentPage + pager.currentPageOffsetFraction
        assertTrue(transition in 0.25f..0.75f, "The incoming page must be partly on screen, was $transition")
        // Aim at the visible part of the incoming page. The pager centers page content vertically.
        onNodeWithTag("pager").performTouchInput {
            down(Offset(width * (1f - transition / 2f), height * 0.45f))
        }
        waitForIdle()
        assertTrue(
            !palette || paletteChanges > 0,
            "The incoming page's palette must receive the down (claims=$claims)",
        )
        assertEquals(1, claims, "A down on the incoming page must not start a pager drag (palette=$paletteChanges)")
        onNodeWithTag("pager").performTouchInput {
            repeat(4) { moveBy(Offset(-width * 0.08f, 0f), delayMillis = 16) }
        }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertEquals(1, claims, "Only the gesture that started the transition may reach the pager (palette=$paletteChanges)")
        if (palette) {
            assertTrue(paletteChanges > 1, "The incoming page's palette must keep the drag (claims=$claims)")
        } else {
            assertTrue(
                row.firstVisibleItemIndex > 0 || row.firstVisibleItemScrollOffset > 0,
                "The incoming page's horizontal list must keep the drag (claims=$claims)",
            )
        }
        onNodeWithTag("pager").performTouchInput { up() }
        finishAnimations()
        assertEquals(1, pager.currentPage, "The interrupted transition must still finish on the incoming page")
        assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
    }

    private fun assertHorizontalRebound(modifier: Boolean, factory: Boolean) = runComposeUiTest {
        lateinit var outer: PagerState
        lateinit var row: LazyListState
        lateinit var effect: MiuixOverscrollEffect
        var modifierOffset = 0f
        var completedFlings = 0
        var outerClaims = 0
        // The pager consumes nested X fling remainder, so a factory-only edge spring is
        // generated by a held drag. Modifier-only and combined cases still exercise inertia.
        val heldRebound = factory && !modifier
        val connection = object : NestedScrollConnection {
            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                completedFlings++
                return Velocity.Zero
            }
        }
        setContent {
            outer = rememberPagerState(initialPage = 1) { 3 }
            TestPager(outer, onTriggered = { outerClaims++ }) { page ->
                CompositionLocalProvider(LocalOverScrollState provides remember { OverScrollState() }) {
                    val state = rememberLazyListState(initialFirstVisibleItemIndex = if (heldRebound) 0 else 2)
                    val overscroll = remember { MiuixOverscrollEffect() }
                    val callback: (Float) -> Unit = remember { { if (page == 1) modifierOffset = it } }
                    if (page == 1) {
                        row = state
                        effect = overscroll
                    }
                    LazyRow(
                        state = state,
                        modifier = Modifier.fillMaxSize().testTag("row-$page").nestedScroll(connection).then(
                            if (modifier) Modifier.overScrollHorizontal(onOffsetChanged = callback) else Modifier,
                        ),
                        overscrollEffect = if (factory) overscroll else null,
                    ) {
                        items(20) { Box(Modifier.width(60.dp).fillMaxHeight()) }
                    }
                }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        onNodeWithTag("row-1").performTouchInput {
            down(center)
            repeat(5) { moveBy(Offset(16f, 0f), delayMillis = 16) }
        }
        waitForIdle()
        if (heldRebound) {
            assertTrue(abs(effect.offsetX) > 1f, "The held edge drag must produce real factory displacement")
        } else {
            assertEquals(0f, modifierOffset, 0f)
            assertEquals(0f, effect.offsetX, 0f)
        }
        onNodeWithTag("row-1").performTouchInput { up() }
        advanceUntil {
            (abs(modifierOffset) > 1f || abs(effect.offsetX) > 1f) && completedFlings > 0 && !row.isScrollInProgress
        }
        swipePage("row-1")
        finishAnimations()
        assertTrue(row.firstVisibleItemIndex > 0 || row.firstVisibleItemScrollOffset > 0, "The row must retain the new horizontal drag")
        assertEquals(1, outer.currentPage)
        assertEquals(0f, outer.currentPageOffsetFraction, 0.001f)
        assertEquals(0, outerClaims)
    }

    @Test
    fun actionCancelSettlesPartialDragWithZeroVelocity() = runComposeUiTest {
        lateinit var pager: PagerState
        var claims = 0
        setContent {
            pager = rememberPagerState(initialPage = 1) { 5 }
            TestPager(pager, onTriggered = { claims++ })
        }
        startSettle(pager)
        beginPagerDrag()
        waitForIdle()
        assertTrue(pager.currentPageOffsetFraction in 0.01f..0.49f)
        cancelTouch()
        finishAnimations()
        assertEquals(1, pager.currentPage, "Cancellation must ignore the last fast drag velocity")
        assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
        assertFalse(pager.isScrollInProgress)
        assertEquals(2, claims, "Both gestures must reach the pager drag")
    }

    @Test
    fun cancelledDragCannotSnapOverNewerGesture() = runComposeUiTest {
        lateinit var pager: PagerState
        setContent {
            pager = rememberPagerState(initialPage = 1) { 5 }
            TestPager(pager)
        }
        startSettle(pager)
        beginPagerDrag()
        cancelTouch()
        // No animation frame is allowed between cancellation and the next user's gesture.
        swipePage("pager", right = true)
        finishAnimations()
        assertEquals(0, pager.currentPage)
        assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
        assertFalse(pager.isScrollInProgress)
    }

    @Test
    fun cancelledDragCannotSnapOverRequestedPage() = runComposeUiTest {
        lateinit var pager: PagerState
        setContent {
            pager = rememberPagerState(initialPage = 1) { 5 }
            TestPager(pager)
        }
        startSettle(pager)
        beginPagerDrag()
        runOnIdle { pager.requestScrollToPage(3) }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        cancelTouch()
        finishAnimations()
        assertEquals(3, pager.currentPage)
        assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
    }

    @Test
    fun dragTransfersToSecondFingerWithoutPrematureRelease() = runComposeUiTest {
        lateinit var pager: PagerState
        var claims = 0
        setContent {
            pager = rememberPagerState(initialPage = 1) { 5 }
            TestPager(pager, onTriggered = { claims++ })
        }
        startSettle(pager)
        beginPagerDrag()
        waitForIdle()
        val beforeHandoff = pager.currentPage + pager.currentPageOffsetFraction
        onNodeWithTag("pager").performTouchInput {
            down(1, Offset(width * 0.85f, height * 0.3f))
            up(0)
        }
        mainClock.advanceTimeBy(64)
        waitForIdle()
        assertTrue(pager.isScrollInProgress, "The remaining finger must keep the drag mutation alive")
        assertEquals(beforeHandoff, pager.currentPage + pager.currentPageOffsetFraction, 0.001f, "Handoff must neither jump nor start a snap")
        onNodeWithTag("pager").performTouchInput {
            repeat(4) { moveBy(1, Offset(-width * 0.12f, 0f), delayMillis = 16) }
            up(1)
        }
        finishAnimations()
        assertEquals(2, pager.currentPage)
        assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
        assertEquals(2, claims)
    }

    @Test
    fun stationarySecondFingerDoesNotInheritFirstFingerVelocity() = runComposeUiTest {
        lateinit var pager: PagerState
        setContent {
            pager = rememberPagerState(initialPage = 1) { 5 }
            TestPager(pager)
        }
        startSettle(pager)
        beginPagerDrag()
        onNodeWithTag("pager").performTouchInput {
            down(1, Offset(width * 0.9f, height * 0.3f))
            up(0)
            advanceEventTime(160)
            up(1)
        }
        finishAnimations()
        assertEquals(1, pager.currentPage, "A stationary replacement must snap to the nearest page, not inherit fling velocity")
        assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
    }

    @Test
    fun replacingPagerStateCancelsOldSettle() = assertStateReplacement(duringDrag = false)

    @Test
    fun replacingPagerStateCancelsOldTakeover() = runComposeUiTest {
        val replace = mutableStateOf(false)
        lateinit var old: PagerState
        lateinit var replacement: PagerState
        lateinit var list: LazyListState
        var claims = 0
        setContent {
            old = rememberPagerState(initialPage = 1) { 5 }
            replacement = rememberPagerState(initialPage = 2) { 5 }
            TestPager(if (replace.value) replacement else old, onTriggered = { claims++ }) { page ->
                if (page == 1) {
                    val state = rememberLazyListState()
                    list = state
                    LazyColumn(
                        state = state,
                        modifier = Modifier.fillMaxSize().testTag("list"),
                        overscrollEffect = null,
                    ) {
                        items(30) { Box(Modifier.fillMaxWidth().height(60.dp)) }
                    }
                } else {
                    Box(Modifier.fillMaxSize())
                }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        onNodeWithTag("list").performTouchInput {
            down(center)
            repeat(4) { moveBy(Offset(0f, -40f), delayMillis = 16) }
            up()
        }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertTrue(list.isScrollInProgress, "A child fling must be running before the takeover")
        onNodeWithTag("pager").performTouchInput {
            down(Offset(width * 0.8f, centerY))
            repeat(2) { moveBy(Offset(-width * 0.06f, 0f), delayMillis = 16) }
        }
        waitForIdle()
        assertEquals(1, claims, "A child fling must be taken over")
        assertTrue(old.isScrollInProgress)
        runOnIdle { replace.value = true }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertFalse(old.isScrollInProgress, "State replacement must cancel the old takeover")
        val abandonedPosition = old.currentPage + old.currentPageOffsetFraction
        onNodeWithTag("pager").performTouchInput { up() }
        finishAnimations()
        assertEquals(abandonedPosition, old.currentPage + old.currentPageOffsetFraction, 0.001f)
        assertEquals(2, replacement.currentPage)
        assertEquals(0f, replacement.currentPageOffsetFraction, 0.001f)
    }

    private fun assertStateReplacement(duringDrag: Boolean) = runComposeUiTest {
        val replace = mutableStateOf(false)
        lateinit var old: PagerState
        lateinit var replacement: PagerState
        setContent {
            old = rememberPagerState(initialPage = 1) { 5 }
            replacement = rememberPagerState(initialPage = 2) { 5 }
            TestPager(if (replace.value) replacement else old, reverseLayout = replace.value)
        }
        startSettle(old)
        if (duringDrag) beginPagerDrag()
        runOnIdle { replace.value = true }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertFalse(old.isScrollInProgress, "State replacement must cancel the old mutation")
        val abandonedPosition = old.currentPage + old.currentPageOffsetFraction
        if (duringDrag) cancelTouch()
        finishAnimations()
        assertEquals(abandonedPosition, old.currentPage + old.currentPageOffsetFraction, 0.001f)
        assertEquals(2, replacement.currentPage)
        assertEquals(0f, replacement.currentPageOffsetFraction, 0.001f)
        swipePage("pager", right = true)
        finishAnimations()
        assertEquals(3, replacement.currentPage, "New touches must use the replacement state's measured reverseLayout")
        assertEquals(abandonedPosition, old.currentPage + old.currentPageOffsetFraction, 0.001f)
    }

    @Test
    fun replacingPagerStateCancelsFoundationDrag() = runComposeUiTest {
        val replace = mutableStateOf(false)
        lateinit var old: PagerState
        lateinit var replacement: PagerState
        setContent {
            old = rememberPagerState(initialPage = 1) { 5 }
            replacement = rememberPagerState(initialPage = 3) { 5 }
            TestPager(if (replace.value) replacement else old)
        }
        waitForIdle()
        mainClock.autoAdvance = false
        beginPagerDrag() // With no ongoing motion, this touch is handled by the Foundation delegate.
        assertTrue(old.isScrollInProgress)
        assertTrue(old.currentPageOffsetFraction > 0f)
        runOnIdle { replace.value = true }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertFalse(old.isScrollInProgress)
        val abandonedPosition = old.currentPage + old.currentPageOffsetFraction
        onNodeWithTag("pager").performTouchInput { up() }
        finishAnimations()
        assertEquals(abandonedPosition, old.currentPage + old.currentPageOffsetFraction, 0.001f)
        assertEquals(3, replacement.currentPage)
        assertEquals(0f, replacement.currentPageOffsetFraction, 0.001f)
        swipePage("pager", right = true)
        finishAnimations()
        assertEquals(2, replacement.currentPage)
    }

    @Test
    fun detachingTakeoverStopsAllPagerMotion() = runComposeUiTest {
        val shown = mutableStateOf(true)
        lateinit var pager: PagerState
        setContent {
            pager = rememberPagerState(initialPage = 1) { 5 }
            Box(Modifier.size(400.dp).testTag("host")) {
                if (shown.value) TestPager(pager)
            }
        }
        startSettle(pager, tag = "host")
        beginPagerDrag(tag = "host")
        runOnIdle { shown.value = false }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertFalse(pager.isScrollInProgress)
        val detachedPosition = pager.currentPage + pager.currentPageOffsetFraction
        cancelTouch(tag = "host")
        finishAnimations()
        assertEquals(detachedPosition, pager.currentPage + pager.currentPageOffsetFraction, 0.001f, "A detached node must not launch a cleanup snap")
    }

    @Test
    fun touchUsesInitialReverseLayout() = assertInitialDirection(LayoutDirection.Ltr, reverseLayout = true)

    @Test
    fun touchUsesInitialRtl() = assertInitialDirection(LayoutDirection.Rtl, reverseLayout = false)

    @Test
    fun touchUsesInitialRtlWithReverseLayout() = assertInitialDirection(LayoutDirection.Rtl, reverseLayout = true)

    private fun assertInitialDirection(direction: LayoutDirection, reverseLayout: Boolean) = runComposeUiTest {
        lateinit var pager: PagerState
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides direction) {
                pager = rememberPagerState(initialPage = 2) { 5 }
                TestPager(pager, reverseLayout = reverseLayout)
            }
        }
        waitForIdle()
        swipePage("pager")
        waitForIdle()
        val reversed = (direction == LayoutDirection.Rtl) xor reverseLayout
        assertEquals(if (reversed) 1 else 3, pager.currentPage)
        assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
    }

    @Test
    fun touchRefreshesDirectionAfterRtlAndReverseLayoutChanges() = runComposeUiTest {
        val direction = mutableStateOf(LayoutDirection.Ltr)
        val reverse = mutableStateOf(false)
        lateinit var pager: PagerState
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides direction.value) {
                pager = rememberPagerState(initialPage = 2) { 5 }
                TestPager(pager, reverseLayout = reverse.value)
            }
        }
        waitForIdle()
        swipePage("pager")
        waitForIdle()
        assertEquals(3, pager.currentPage)
        runOnIdle { reverse.value = true }
        waitForIdle()
        swipePage("pager")
        waitForIdle()
        assertEquals(2, pager.currentPage)
        runOnIdle { direction.value = LayoutDirection.Rtl }
        waitForIdle()
        swipePage("pager")
        waitForIdle()
        assertEquals(3, pager.currentPage)
        runOnIdle { reverse.value = false }
        waitForIdle()
        swipePage("pager")
        waitForIdle()
        assertEquals(2, pager.currentPage)
        assertEquals(0f, pager.currentPageOffsetFraction, 0.001f)
    }

    @Composable
    private fun TestPager(
        state: PagerState,
        tag: String = "pager",
        reverseLayout: Boolean = false,
        onTriggered: (() -> Unit)? = null,
        content: @Composable (Int) -> Unit = { Box(Modifier.fillMaxSize()) },
    ) {
        val fling = PagerDefaults.flingBehavior(state, snapAnimationSpec = PagerNavigationSpringSpec)
        HorizontalPager(
            state = state,
            modifier = Modifier.size(400.dp).testTag(tag).pagerGestureOverride(state, fling, onTriggered = onTriggered),
            userScrollEnabled = false,
            reverseLayout = reverseLayout,
            flingBehavior = fling,
            pageNestedScrollConnection = PagerGestureNestedScrollConnection,
        ) { content(it) }
    }

    private fun ComposeUiTest.startSettle(pager: PagerState, tag: String = "pager") {
        waitForIdle()
        mainClock.autoAdvance = false
        onNodeWithTag(tag).performTouchInput {
            down(Offset(width * 0.75f, centerY))
            repeat(3) { moveBy(Offset(-width * 0.05f, 0f), delayMillis = 16) }
            up()
        }
        mainClock.advanceTimeByFrame()
        waitForIdle()
        assertTrue(pager.isScrollInProgress, "The second gesture must start during a real pager settle")
        assertTrue(pager.currentPageOffsetFraction > 0f)
    }

    private fun ComposeUiTest.beginPagerDrag(tag: String = "pager") {
        onNodeWithTag(tag).performTouchInput {
            down(Offset(width * 0.75f, centerY))
            repeat(2) { moveBy(Offset(-width * 0.06f, 0f), delayMillis = 16) }
        }
        waitForIdle()
    }

    private fun ComposeUiTest.swipePage(tag: String, right: Boolean = false) {
        onNodeWithTag(tag).performTouchInput {
            down(Offset(width * if (right) 0.15f else 0.85f, centerY))
            repeat(5) { moveBy(Offset(width * if (right) 0.12f else -0.12f, 0f), delayMillis = 16) }
            up()
        }
    }

    private fun ComposeUiTest.cancelTouch(tag: String = "pager") {
        // Skiko's TouchInjectionScope.cancel() only clears injector bookkeeping; it emits
        // no platform event. Cancel the real scene too, exercising onCancelPointerInput.
        onNodeWithTag(tag).performTouchInput { cancel() }
        runOnUiThread { (this as SkikoComposeUiTest).scene.cancelPointerInput() }
        waitForIdle()
    }

    private fun ComposeUiTest.finishAnimations() {
        // With a paused clock, let queued input/release jobs enter their animations first.
        waitForIdle()
        mainClock.advanceTimeBy(2500)
        waitForIdle()
    }

    private fun ComposeUiTest.advanceUntil(condition: () -> Boolean) {
        var frames = 0
        while (!condition() && frames++ < 120) {
            mainClock.advanceTimeByFrame()
            waitForIdle()
        }
        assertTrue(condition(), "A real rebound must be active before injecting the next touch")
    }
}
