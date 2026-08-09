// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChangeIgnoreConsumed
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

private fun Modifier.consumeFirstPositionMoves(
    count: Int,
    onConsumed: () -> Unit = {},
): Modifier = pointerInput(count, onConsumed) {
    awaitEachGesture {
        val down = awaitFirstDown(requireUnconsumed = false)
        var consumedMoves = 0
        while (true) {
            val event = awaitPointerEvent(PointerEventPass.Main)
            val change = event.changes.firstOrNull { it.id == down.id } ?: return@awaitEachGesture
            if (!change.pressed) break
            val delta = change.positionChangeIgnoreConsumed()
            if ((delta.x != 0f || delta.y != 0f) && consumedMoves < count) {
                consumedMoves++
                onConsumed()
                change.consume()
            }
        }
    }
}

/**
 * Pins the documented arbitration contract between the swipe-dismiss recognizer and scrollable
 * entry content (the two-phase engagement in `Modifier.navSwipeDismiss`):
 * - a clearly cross-axis-dominant drag is never claimed, so the page's own scrolling keeps working;
 * - two consecutive consumed dismiss-axis moves confirm that a child owns the whole sequence, so
 *   sliders and same-axis scrollables are not stolen even if they later stop consuming;
 * - a clickable's one-time consumption while cancelling its press enters a one-move confirmation
 *   window but does not reserve the sequence;
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
    fun transientConsumptionPreservesPreClaimDistance() = runComposeUiTest {
        var animatedTop: Animatable<Float, AnimationVector1D>? = null
        setContent {
            val animation = remember { Animatable(1f) }
            animatedTop = animation
            Box(
                Modifier
                    .fillMaxSize()
                    .navSwipeDismissImpl(
                        enabled = true,
                        direction = NavSwipeDirection.LeftToRight,
                        animatedTop = animation,
                        topIndex = 1,
                        motion = NavMotion.Default,
                        settleSink = null,
                        externalGestureOwnership = { 0L },
                        onCommit = {},
                        onCancel = {},
                        onGesture = {},
                    ),
            ) {
                Box(Modifier.fillMaxSize().consumeFirstPositionMoves(1))
            }
        }
        waitForIdle()

        onRoot().performTouchInput {
            down(Offset(width * 0.1f, centerY))
            moveTo(Offset(width * 0.3f, centerY))
            moveTo(Offset(width * 0.6f, centerY))
        }
        waitForIdle()

        assertTrue(
            checkNotNull(animatedTop).value < 0.8f,
            "navigation must catch up to the full recognized drag instead of restarting at claim",
        )

        onRoot().performTouchInput { up() }
        waitForIdle()
    }

    @Test
    fun secondPointerBeforeClaimLeavesSequenceToContent() = runComposeUiTest {
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
                        externalGestureOwnership = { 0L },
                        onCommit = { commits++ },
                        onCancel = {},
                        onGesture = {},
                    ),
            )
        }
        waitForIdle()

        onRoot().performTouchInput {
            down(pointerId = 0, position = Offset(width * 0.1f, centerY))
            down(pointerId = 1, position = Offset(width * 0.7f, centerY))
            moveTo(pointerId = 0, position = Offset(width * 0.9f, centerY))
            up(pointerId = 1)
            up(pointerId = 0)
        }
        waitForIdle()

        assertEquals(0, commits, "navigation must not claim a sequence that became multi-touch")
    }

    @Test
    fun secondPointerAfterClaimCancelsInsteadOfCommitting() = runComposeUiTest {
        var commits = 0
        var cancels = 0
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
                        externalGestureOwnership = { 0L },
                        onCommit = { commits++ },
                        onCancel = { cancels++ },
                        onGesture = {},
                    ),
            )
        }
        waitForIdle()

        onRoot().performTouchInput {
            down(pointerId = 0, position = Offset(width * 0.05f, centerY))
            moveTo(pointerId = 0, position = Offset(width * 0.4f, centerY))
            down(pointerId = 1, position = Offset(width * 0.7f, centerY))
            moveTo(pointerId = 0, position = Offset(width * 0.95f, centerY))
            up(pointerId = 1)
            up(pointerId = 0)
        }
        waitForIdle()

        assertEquals(0, commits, "a multi-touch interruption must never commit the pop")
        assertEquals(1, cancels, "a claimed sequence must settle back after multi-touch interruption")
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
    fun clickableContentDoesNotBlockDismissDirectionDrag() = runComposeUiTest {
        val backStack = navBackStackOf(ConflictBase, HorizontalPage)
        var clicks = 0
        val interactions = mutableListOf<PressInteraction>()
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<ConflictBase> { Box(Modifier.fillMaxSize()) { BasicText("base") } }
                entry<HorizontalPage>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    val interactionSource = remember { MutableInteractionSource() }
                    val indication = LocalIndication.current
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect { interaction ->
                            if (interaction is PressInteraction) interactions += interaction
                        }
                    }
                    Box(
                        Modifier
                            .fillMaxSize()
                            .clickable(
                                interactionSource = interactionSource,
                                indication = indication,
                            ) { clicks++ },
                    ) {
                        BasicText("clickable-page")
                    }
                }
            }
        }
        waitForIdle()

        onRoot().performTouchInput { down(Offset(width * 0.1f, centerY)) }
        waitForIdle()

        assertTrue(
            interactions.any { it is PressInteraction.Press },
            "clickable must receive press first so its indication can show before navigation claims",
        )

        onRoot().performTouchInput {
            moveTo(Offset(width * 0.4f, centerY))
            moveTo(Offset(width * 0.9f, centerY))
            up()
        }
        waitForIdle()

        assertEquals(1, backStack.size, "click cancellation must not reserve the sequence from navigation")
        assertEquals(0, clicks, "the dismiss drag must cancel the click")
        assertTrue(
            interactions.any { it is PressInteraction.Cancel },
            "navigation takeover must cancel the clickable press and its indication",
        )
        onNodeWithText("base").assertExists()
    }

    @Test
    fun oneConsumedMoveThenUnconsumedMoveLetsNavigationClaim() = runComposeUiTest {
        val backStack = navBackStackOf(ConflictBase, HorizontalPage)
        var consumedMoves = 0
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<ConflictBase> { Box(Modifier.fillMaxSize()) { BasicText("base") } }
                entry<HorizontalPage>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .consumeFirstPositionMoves(1) { consumedMoves++ },
                    ) {
                        BasicText("transient-consumer")
                    }
                }
            }
        }
        waitForIdle()

        onRoot().performTouchInput {
            down(Offset(width * 0.1f, centerY))
            moveTo(Offset(width * 0.3f, centerY))
            moveTo(Offset(width * 0.6f, centerY))
            moveTo(Offset(width * 0.9f, centerY))
            up()
        }
        waitForIdle()

        assertEquals(1, consumedMoves, "the child must consume only the ambiguous first move")
        assertEquals(1, backStack.size, "an unconsumed confirmation move must let navigation pop")
        onNodeWithText("base").assertExists()
    }

    @Test
    fun twoConsumedMovesLockOwnershipToChild() = runComposeUiTest {
        val backStack = navBackStackOf(ConflictBase, HorizontalPage)
        var consumedMoves = 0
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<ConflictBase> { Box(Modifier.fillMaxSize()) { BasicText("base") } }
                entry<HorizontalPage>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .consumeFirstPositionMoves(2) { consumedMoves++ },
                    ) {
                        BasicText("confirmed-consumer")
                    }
                }
            }
        }
        waitForIdle()

        onRoot().performTouchInput {
            down(Offset(width * 0.1f, centerY))
            moveTo(Offset(width * 0.3f, centerY))
            moveTo(Offset(width * 0.5f, centerY))
            moveTo(Offset(width * 0.9f, centerY))
            up()
        }
        waitForIdle()

        assertEquals(2, consumedMoves, "two consumed moves must confirm child ownership")
        assertEquals(2, backStack.size, "navigation must not steal after child ownership is confirmed")
        onNodeWithText("confirmed-consumer").assertExists()
    }

    @Test
    fun oneConsumedMoveThenUpDoesNotNavigate() = runComposeUiTest {
        val backStack = navBackStackOf(ConflictBase, HorizontalPage)
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                entry<ConflictBase> { Box(Modifier.fillMaxSize()) { BasicText("base") } }
                entry<HorizontalPage>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .consumeFirstPositionMoves(1),
                    ) {
                        BasicText("single-move-consumer")
                    }
                }
            }
        }
        waitForIdle()

        onRoot().performTouchInput {
            down(Offset(width * 0.1f, centerY))
            moveTo(Offset(width * 0.8f, centerY))
            up()
        }
        waitForIdle()

        assertEquals(2, backStack.size, "an unresolved consumed move followed by up must not pop")
        onNodeWithText("single-move-consumer").assertExists()
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
