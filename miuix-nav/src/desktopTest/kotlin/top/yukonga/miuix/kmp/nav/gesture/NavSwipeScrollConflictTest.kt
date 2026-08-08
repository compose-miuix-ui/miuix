// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.nav.core.NavDisplay
import top.yukonga.miuix.kmp.nav.core.NavDisplayEffects
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.core.navBackStackOf
import top.yukonga.miuix.kmp.nav.transition.NavMotion
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

private data object ConflictBase : NavKey

private data object VerticalPage : NavKey

private data object HorizontalPage : NavKey

/**
 * Pins the documented arbitration contract between the swipe-dismiss recognizer and scrollable
 * entry content (the two-phase engagement in `Modifier.navSwipeDismiss`):
 * - a clearly cross-axis-dominant drag is never claimed, so the page's own scrolling keeps working;
 * - a child that consumes dismiss-axis movement on the Main pass owns the sequence, so sliders and
 *   same-axis scrollables are not stolen by navigation;
 * - otherwise, a dismiss-direction drag past slop is claimed across the full display;
 * - travel opposite the dismiss direction never engages, so a same-axis scrollable still receives
 *   reverse scrolling.
 */
@OptIn(ExperimentalTestApi::class)
class NavSwipeScrollConflictTest {

    @Test
    fun predictiveBackOwnershipBlocksPointerDismissCommit() = runComposeUiTest {
        var commits = 0
        setContent {
            Box(
                Modifier
                    .fillMaxSize()
                    .navSwipeDismissImpl(
                        enabled = true,
                        direction = NavSwipeDirection.LeftToRight,
                        animatedTop = remember { Animatable(1f) },
                        topIndex = 1,
                        motion = NavMotion.Default,
                        settleSink = null,
                        externalGestureOwnership = { 1L },
                        onCommit = { commits++ },
                        onCancel = {},
                        onGesture = {},
                    ),
            )
        }
        waitForIdle()

        onRoot().performTouchInput { swipeRight(startX = width * 0.05f, endX = width * 0.95f) }
        waitForIdle()

        assertEquals(0, commits, "system predictive back ownership must suppress the pointer recognizer")
    }

    @Test
    fun completedPredictiveBackOwnershipCycleCancelsClaimedPointerWork() = runComposeUiTest {
        var ownership by mutableLongStateOf(0L)
        var commits = 0
        var pointerGestureUpdates = 0
        setContent {
            Box(
                Modifier
                    .fillMaxSize()
                    .navSwipeDismissImpl(
                        enabled = true,
                        direction = NavSwipeDirection.LeftToRight,
                        animatedTop = remember { Animatable(1f) },
                        topIndex = 1,
                        motion = NavMotion.Default,
                        settleSink = null,
                        externalGestureOwnership = { ownership },
                        onCommit = { commits++ },
                        onCancel = {},
                        onGesture = { if (it != null) pointerGestureUpdates++ },
                    ),
            )
        }
        waitForIdle()

        // Claim the pointer recognizer and allow its first update to run.
        onRoot().performTouchInput {
            down(Offset(width * 0.05f, centerY))
            moveTo(Offset(width * 0.25f, centerY))
            moveTo(Offset(width * 0.35f, centerY))
        }
        waitForIdle()
        val updatesBeforeOwnershipChange = pointerGestureUpdates
        assertTrue(updatesBeforeOwnershipChange > 0, "the pointer sequence must be claimed before takeover")

        // An even generation means predictive back has already started and finished. The pointer
        // sequence must still observe that ownership changed while it was suspended.
        runOnIdle { ownership = 2L }
        onRoot().performTouchInput {
            moveTo(Offset(width * 0.8f, centerY))
            up()
        }
        waitForIdle()

        assertEquals(0, commits, "a stale claimed pointer sequence must not commit")
        assertEquals(
            updatesBeforeOwnershipChange,
            pointerGestureUpdates,
            "queued pointer work must not publish after predictive-back ownership changes",
        )
    }

    @Test
    fun crossAxisDragScrollsThePageWithoutEngagingDismiss() = runComposeUiTest {
        val backStack = navBackStackOf(ConflictBase, VerticalPage)
        var scroll: ScrollState? = null
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<ConflictBase> { Box(Modifier.fillMaxSize()) { BasicText("base") } }
                entry<VerticalPage>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    val state = rememberScrollState()
                    scroll = state
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(state),
                    ) {
                        BasicText("vertical-page")
                        Box(Modifier.height(4000.dp).fillMaxSize())
                    }
                }
            }
        }
        waitForIdle()

        onRoot().performTouchInput {
            down(center)
            repeat(8) { step -> moveTo(Offset(centerX, centerY - 40f * (step + 1))) }
            up()
        }
        waitForIdle()

        assertTrue(checkNotNull(scroll).value > 0, "vertical drag must reach the page's own scroll")
        assertEquals(2, backStack.size, "cross-axis drag must not pop")
        onNodeWithText("vertical-page").assertExists()
    }

    @Test
    fun dismissDirectionDragClaimsOverVerticalScrollContent() = runComposeUiTest {
        val backStack = navBackStackOf(ConflictBase, VerticalPage)
        var scroll: ScrollState? = null
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<ConflictBase> { Box(Modifier.fillMaxSize()) { BasicText("base") } }
                entry<VerticalPage>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    val state = rememberScrollState()
                    scroll = state
                    Column(
                        Modifier
                            .fillMaxSize()
                            .verticalScroll(state),
                    ) {
                        BasicText("vertical-page")
                        Box(Modifier.height(4000.dp).fillMaxSize())
                    }
                }
            }
        }
        waitForIdle()

        onRoot().performTouchInput { swipeRight(startX = width * 0.1f, endX = width * 0.9f) }
        waitForIdle()

        assertEquals(1, backStack.size, "dismiss-direction fling must commit the pop")
        assertEquals(0, checkNotNull(scroll).value, "the claimed gesture must never reach the scroll")
        onNodeWithText("base").assertExists()
    }

    @Test
    fun sameAxisScrollableClaimsDismissDirectionDrag() = runComposeUiTest {
        val backStack = navBackStackOf(ConflictBase, HorizontalPage)
        var scroll: ScrollState? = null
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<ConflictBase> { Box(Modifier.fillMaxSize()) { BasicText("base") } }
                entry<HorizontalPage>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    // Pre-scrolled so a left-to-right drag COULD scroll back toward 0 if the
                    // scrollable ever received it.
                    val state = rememberScrollState(initial = 200)
                    scroll = state
                    Row(
                        Modifier
                            .fillMaxSize()
                            .horizontalScroll(state),
                    ) {
                        BasicText("horizontal-page")
                        Box(Modifier.width(4000.dp).fillMaxHeight())
                    }
                }
            }
        }
        waitForIdle()

        onRoot().performTouchInput { swipeRight(startX = width * 0.1f, endX = width * 0.9f) }
        waitForIdle()

        assertEquals(2, backStack.size, "a consuming same-axis child must prevent the pop")
        assertTrue(checkNotNull(scroll).value < 200, "the same-axis scrollable must receive the drag")
        onNodeWithText("horizontal-page").assertExists()
    }

    @Test
    fun sameAxisDraggableClaimsDismissDirectionDrag() = runComposeUiTest {
        val backStack = navBackStackOf(ConflictBase, HorizontalPage)
        var dragDistance = 0f
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<ConflictBase> { Box(Modifier.fillMaxSize()) { BasicText("base") } }
                entry<HorizontalPage>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .draggable(
                                state = rememberDraggableState { dragDistance += it },
                                orientation = Orientation.Horizontal,
                            ),
                    ) {
                        BasicText("draggable-page")
                    }
                }
            }
        }
        waitForIdle()

        onRoot().performTouchInput { swipeRight(startX = width * 0.1f, endX = width * 0.9f) }
        waitForIdle()

        assertEquals(2, backStack.size, "a same-axis draggable such as Slider must prevent the pop")
        assertTrue(dragDistance > 0f, "the same-axis draggable must receive the drag")
        onNodeWithText("draggable-page").assertExists()
    }

    @Test
    fun oppositeDirectionDragScrollsSameAxisContentWithoutEngaging() = runComposeUiTest {
        val backStack = navBackStackOf(ConflictBase, HorizontalPage)
        var scroll: ScrollState? = null
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<ConflictBase> { Box(Modifier.fillMaxSize()) { BasicText("base") } }
                entry<HorizontalPage>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    val state = rememberScrollState()
                    scroll = state
                    Row(
                        Modifier
                            .fillMaxSize()
                            .horizontalScroll(state),
                    ) {
                        BasicText("horizontal-page")
                        Box(Modifier.width(4000.dp).fillMaxHeight())
                    }
                }
            }
        }
        waitForIdle()

        onRoot().performTouchInput {
            down(center)
            repeat(8) { step -> moveTo(Offset(centerX - 40f * (step + 1), centerY)) }
            up()
        }
        waitForIdle()

        assertTrue(checkNotNull(scroll).value > 0, "opposite-direction drag must reach the scrollable")
        assertEquals(2, backStack.size, "opposite-direction travel must never engage the dismiss")
        onNodeWithText("horizontal-page").assertExists()
    }
}
