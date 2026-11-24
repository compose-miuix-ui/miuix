// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import androidx.navigationevent.DirectNavigationEventInput
import androidx.navigationevent.NavigationEvent
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventDispatcherOwner
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.overlay.OverlayDialog
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class DialogPredictiveBackTest {
    @Test
    fun predictiveBackMidOpenDoesNotLeaveOffsetOnNextOpen() = runComposeUiTest {
        val dispatcher = NavigationEventDispatcher()
        val owner = object : NavigationEventDispatcherOwner {
            override val navigationEventDispatcher = dispatcher
        }
        val input = DirectNavigationEventInput()
        dispatcher.addInput(input)

        var show by mutableStateOf(false)
        var dialogY = Float.NaN

        setContent {
            CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides owner) {
                MiuixTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) {
                        OverlayDialog(
                            show = show,
                            largeScreen = false,
                            onDismissRequest = { show = false },
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(120.dp)
                                    .onGloballyPositioned { coordinates ->
                                        dialogY = coordinates.positionInWindow().y
                                    },
                            )
                        }
                    }
                }
            }
        }

        // Baseline: clean open to rest.
        show = true
        waitForIdle()
        val settledY = dialogY

        // Close cleanly.
        show = false
        waitForIdle()

        // Re-open, advance partway, trigger predictive back and complete it.
        mainClock.autoAdvance = false
        show = true
        mainClock.advanceTimeByFrame()
        mainClock.advanceTimeBy(48)

        input.backStarted(NavigationEvent(progress = 0f))
        mainClock.advanceTimeByFrame()
        input.backProgressed(NavigationEvent(progress = 0.45f))
        mainClock.advanceTimeByFrame()
        input.backCompleted()

        mainClock.autoAdvance = true
        waitForIdle()

        // Open again and verify the panel settles at the exact same resting Y.
        show = true
        waitForIdle()
        assertEquals(settledY, dialogY, absoluteTolerance = 1f)
    }
}
