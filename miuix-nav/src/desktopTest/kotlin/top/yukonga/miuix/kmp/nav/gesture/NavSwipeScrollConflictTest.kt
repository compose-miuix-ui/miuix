// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.foundation.ScrollState
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
 * - a dismiss-direction drag past slop is claimed parent-first on the Initial pass, so the nav
 *   gesture wins over a same-axis scrollable unconditionally (full-width engagement, no edge
 *   region) — by design, mitigated by swipe being per-route opt-in;
 * - travel opposite the dismiss direction never engages, so a same-axis scrollable still receives
 *   reverse scrolling.
 */
@OptIn(ExperimentalTestApi::class)
class NavSwipeScrollConflictTest {

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
    fun dismissDirectionDragWinsOverSameAxisScrollable() = runComposeUiTest {
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

        assertEquals(1, backStack.size, "same-axis dismiss-direction fling must commit the pop")
        assertEquals(200, checkNotNull(scroll).value, "nav-wins precedence: the scrollable must not move")
        onNodeWithText("base").assertExists()
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
