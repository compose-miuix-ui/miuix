// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.animation.core.Animatable
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import top.yukonga.miuix.kmp.nav.transition.NavSwipeEdge
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Value-level tests for the anchored predictive-back drive (grab-anchor model, invariant 6).
 * Lives in desktopTest because driving a suspend collect needs a JVM runBlocking; the pure
 * mapping math is covered platform-agnostically in NavDriverTest.
 */
class NavPredictiveBackDriverTest {
    private fun event(progress: Float): NavBackEvent = NavBackEvent(progress = progress, swipeEdge = NavSwipeEdge.None, touchY = 0f)

    @Test
    fun firstEvent_zeroProgress_causesNoJump_midPush() = runBlocking {
        // Push toward topIndex=1 interrupted at 25%: the first 0-progress event must be a no-op.
        val animatedTop = Animatable(0.25f)
        drivePredictiveBack(events = flowOf(event(0f)), animatedTop = animatedTop, topIndex = 1)
        assertEquals(0.25f, animatedTop.value)
    }

    @Test
    fun progress_continuesFromAnchor() = runBlocking {
        // anchor = 1 - 0.25 = 0.75; progress 0.125 -> total 0.875 -> animatedTop = 0.125.
        val animatedTop = Animatable(0.25f)
        drivePredictiveBack(events = flowOf(event(0f), event(0.125f)), animatedTop = animatedTop, topIndex = 1)
        assertEquals(0.125f, animatedTop.value)
    }

    @Test
    fun progress_saturatesAtFullyPopped() = runBlocking {
        // anchor 0.75 + progress 0.5 clamps at 1 -> animatedTop pins at topIndex - 1.
        val animatedTop = Animatable(0.25f)
        drivePredictiveBack(events = flowOf(event(0f), event(0.5f)), animatedTop = animatedTop, topIndex = 1)
        assertEquals(0f, animatedTop.value)
    }

    @Test
    fun negativeAnchor_reelsLeavingEntryFirst() = runBlocking {
        // Pop-settle toward topIndex=1 interrupted at animatedTop=1.25 (a leaving entry above):
        // anchor = -0.25; progress 0.5 -> total 0.25 -> animatedTop = 0.75. No jump at event 0.
        val animatedTop = Animatable(1.25f)
        drivePredictiveBack(events = flowOf(event(0f), event(0.5f)), animatedTop = animatedTop, topIndex = 1)
        assertEquals(0.75f, animatedTop.value)
    }

    @Test
    fun restState_behavesAsBefore() = runBlocking {
        // anchor = 0: legacy behavior, progress maps absolutely.
        val animatedTop = Animatable(2f)
        drivePredictiveBack(events = flowOf(event(0.5f)), animatedTop = animatedTop, topIndex = 2)
        assertEquals(1.5f, animatedTop.value)
    }
}
