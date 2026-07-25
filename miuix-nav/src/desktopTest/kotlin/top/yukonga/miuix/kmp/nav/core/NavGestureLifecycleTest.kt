// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection
import kotlin.test.Test
import kotlin.test.assertEquals

private data object GestureRouteA : NavKey

private data object GestureRouteB : NavKey

/**
 * End-to-end check of the gesture lifecycle cap: while an interactive swipe-back owns the float,
 * the top entry must be held at STARTED (no RESUMED<->STARTED flapping when the finger hovers
 * around the midpoint), and must return to RESUMED once the cancelled gesture settles back.
 */
@OptIn(ExperimentalTestApi::class)
class NavGestureLifecycleTest {

    @Test
    fun topEntryHeldAtStartedWhileSwipeGestureActive() = runComposeUiTest {
        val backStack = navBackStackOf(GestureRouteA, GestureRouteB)
        var topLifecycle: Lifecycle? = null
        setContent {
            NavDisplay(backStack = backStack, effects = NavDisplayEffects.None) {
                // Full-size pages so the display container (sized by its content) covers the
                // window and the injected pointer events actually hit the swipe recognizer.
                entry<GestureRouteA> { Box(Modifier.fillMaxSize()) { BasicText("page-a") } }
                entry<GestureRouteB>(swipeDismiss = NavSwipeDirection.LeftToRight) {
                    topLifecycle = LocalLifecycleOwner.current.lifecycle
                    Box(Modifier.fillMaxSize()) { BasicText("page-b") }
                }
            }
        }
        waitForIdle()
        assertEquals(Lifecycle.State.RESUMED, checkNotNull(topLifecycle).currentState, "settled top must be RESUMED")

        // Drag right past touch slop and hold the finger mid-gesture (~30% progress, below the
        // 0.5 commit threshold). The trailing stationary moves decay the tracked velocity so the
        // later release resolves as a cancel, not a velocity commit.
        onRoot().performTouchInput {
            down(Offset(width * 0.05f, centerY))
            repeat(6) { step ->
                moveTo(Offset(width * (0.05f + 0.05f * (step + 1)), centerY))
            }
            repeat(5) { moveTo(Offset(width * 0.35f, centerY), delayMillis = 100) }
        }
        waitForIdle()
        assertEquals(
            Lifecycle.State.STARTED,
            checkNotNull(topLifecycle).currentState,
            "top entry must be capped at STARTED while the gesture is active",
        )

        // Release below both commit thresholds: the gesture cancels, settles back, and the top
        // entry is promoted back to RESUMED exactly once.
        onRoot().performTouchInput { up() }
        waitForIdle()
        assertEquals(
            Lifecycle.State.RESUMED,
            checkNotNull(topLifecycle).currentState,
            "cancelled gesture must restore the settled top to RESUMED",
        )
    }
}
