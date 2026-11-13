// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.TopAppBarState
import top.yukonga.miuix.kmp.utils.overScrollVertical
import kotlin.math.abs
import kotlin.math.round
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassTopAppBarOverscrollTest {
    @Test
    fun shortContentRestoresGlassRampAfterRelease() = assertRebound(itemCount = 1)

    @Test
    fun scrollableContentRetainsRealScrollAfterRebound() = assertRebound(itemCount = 4)

    @Test
    fun callbackCoalescesSubpixelUpdatesAndReportsExactSettle() = runComposeUiTest {
        val reports = mutableListOf<Float>()
        setContent {
            LazyColumn(
                modifier = Modifier.size(300.dp, 400.dp).testTag("list")
                    .overScrollVertical(onOffsetChanged = { reports += it }),
                overscrollEffect = null,
            ) {
                item { Box(Modifier.height(150.dp)) }
            }
        }
        mainClock.autoAdvance = false
        onNodeWithTag("list").performTouchInput {
            down(Offset(centerX, height - 20f))
            moveBy(Offset(0f, -100f), delayMillis = 120)
        }
        runOnIdle { reports.clear() }
        onNodeWithTag("list").performTouchInput {
            repeat(12) { moveBy(Offset(0f, -0.75f), delayMillis = 16) }
        }
        runOnIdle {
            assertTrue(reports.isNotEmpty())
            assertTrue(reports.size < 12, "Subpixel motion must be pixel-coalesced")
            assertTrue(reports.any { it != round(it) }, "Callbacks must retain the latest raw Float")
            assertTrue(
                reports.zipWithNext().all { (previous, current) -> round(previous) != round(current) },
                "Each callback must cross a rounded pixel boundary",
            )
        }
        onNodeWithTag("list").performTouchInput { up() }
        mainClock.advanceTimeBy(2000)
        waitForIdle()
        assertEquals(0f, reports.last(), "Settling must report exact zero")
    }

    @Test
    fun removingOverscrollClearsItsVisualDisplacement() = runComposeUiTest {
        val state = TopAppBarState(0f, 0f, 0f)
        val enabled = mutableStateOf(true)
        val reports = mutableListOf<Float>()
        setContent {
            val behavior = MiuixScrollBehavior(state)
            val reportOffset: (Float) -> Unit = remember {
                { value ->
                    reports += value
                    state.overscrollOffset = value
                }
            }
            LazyColumn(
                modifier = Modifier.size(300.dp, 400.dp).testTag("list")
                    .overScrollVertical(onOffsetChanged = reportOffset, isEnabled = { enabled.value })
                    .nestedScroll(behavior.nestedScrollConnection),
                overscrollEffect = null,
            ) {
                item { Box(Modifier.height(150.dp)) }
            }
        }
        onNodeWithTag("list").performTouchInput {
            down(Offset(centerX, height - 20f))
            moveBy(Offset(0f, -200f), delayMillis = 120)
        }
        runOnIdle {
            assertTrue(state.overscrollOffset < 0f)
            enabled.value = false
        }
        waitForIdle()
        runOnIdle {
            assertEquals(0f, state.overscrollOffset)
            assertEquals(0f, state.contentOffset)
            assertEquals(0f, reports.last(), "Reset must report exact zero")
            assertEquals(1, reports.count { it == 0f }, "Reset must not write a repeated zero tail")
        }
        onNodeWithTag("list").performTouchInput { up() }
    }

    private fun assertRebound(itemCount: Int) = runComposeUiTest {
        val state = TopAppBarState(0f, 0f, 0f)
        lateinit var ramp: State<Float>
        setContent {
            val behavior = MiuixScrollBehavior(state)
            ramp = GlassTopAppBarDefaults.collapseRamp(behavior)
            val reportOffset: (Float) -> Unit = remember { { state.overscrollOffset = it } }
            LazyColumn(
                modifier = Modifier.size(300.dp, 400.dp).testTag("list")
                    .overScrollVertical(onOffsetChanged = reportOffset)
                    .nestedScroll(behavior.nestedScrollConnection),
                overscrollEffect = null,
            ) {
                items(itemCount) { Box(Modifier.height(150.dp)) }
            }
        }
        mainClock.autoAdvance = false
        onNodeWithTag("list").performTouchInput {
            down(Offset(centerX, height - 20f))
            repeat(6) { moveBy(Offset(0f, -50f), delayMillis = 40) }
        }
        waitForIdle()
        val actualScroll = state.contentOffset
        val heldOffset = state.overscrollOffset
        assertTrue(heldOffset < 0f, "The gesture must reach elastic overscroll")
        assertTrue(ramp.value > 0f, "Glass effects must activate while held")
        if (itemCount == 1) {
            assertEquals(0f, actualScroll, "Elastic motion must not become real scroll")
        } else {
            assertTrue(actualScroll < 0f, "The list must consume actual scroll before overscroll")
        }
        onNodeWithTag("list").performTouchInput {
            advanceEventTime(200)
            up()
        }
        mainClock.advanceTimeBy(96)
        waitForIdle()
        assertTrue(abs(state.overscrollOffset) < abs(heldOffset), "Spring frames must update the visual displacement")
        mainClock.advanceTimeBy(2000)
        waitForIdle()
        assertEquals(0f, state.overscrollOffset, "Rebound must clear the transient displacement")
        assertEquals(actualScroll, state.contentOffset, 0.1f, "Rebound must not erase actual list scrolling")
        if (itemCount == 1) {
            assertEquals(0f, ramp.value, 0f, "The glass ramp must return to its unscrolled state")
        } else {
            assertTrue(ramp.value > 0f, "Scrolled content must retain its glass treatment")
        }
    }
}
