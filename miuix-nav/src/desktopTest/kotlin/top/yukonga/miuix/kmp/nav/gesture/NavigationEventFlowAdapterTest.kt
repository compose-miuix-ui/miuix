// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.navigationevent.NavigationEvent
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking
import top.yukonga.miuix.kmp.nav.runtime.NavDriverSpec
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Ordering-semantics tests for [NavigationEventFlowAdapter]: the dispatcher's synchronous
 * callback protocol must map onto the suspend-Flow contract with commit strictly after the
 * collector has drained every progress event, and cancel never reported as commit.
 */
class NavigationEventFlowAdapterTest {
    @Test
    fun gesture_streamsEventsThenCommitsAfterDrain() = runBlocking {
        val order = mutableListOf<String>()
        val adapter = NavigationEventFlowAdapter(this)
        adapter.currentOnProgress = { events -> events.collect { order += "progress:${it.progress}" } }
        adapter.currentOnCommit = { order += "commit" }

        adapter.handleBackStarted(NavigationEvent(progress = 0f))
        adapter.handleBackProgressed(NavigationEvent(progress = 0.5f))
        adapter.handleBackCompleted()
        coroutineContext.job.children.forEach { it.join() }

        // Every progress event is delivered, and the commit lands strictly after the drain.
        assertContentEquals(listOf("progress:0.0", "progress:0.5", "commit"), order)
    }

    @Test
    fun cancelledGesture_invokesOnCancelNotCommit() = runBlocking {
        var committed = false
        var cancelled = false
        val adapter = NavigationEventFlowAdapter(this)
        adapter.currentOnProgress = { events -> events.collect { } }
        adapter.currentOnCommit = { committed = true }
        adapter.currentOnCancel = { cancelled = true }

        adapter.handleBackStarted(NavigationEvent(progress = 0f))
        adapter.handleBackProgressed(NavigationEvent(progress = 0.25f))
        adapter.handleBackCancelled()
        coroutineContext.job.children.forEach { it.join() }

        assertFalse(committed)
        assertTrue(cancelled)
    }

    @Test
    fun discreteTrigger_commitsDirectlyWithoutSession() = runBlocking {
        var progressSessions = 0
        var committed = false
        val adapter = NavigationEventFlowAdapter(this)
        adapter.currentOnProgress = { progressSessions++ }
        adapter.currentOnCommit = { committed = true }

        // A discrete trigger (e.g. Desktop ESC) reaches the handler as a bare completed callback.
        adapter.handleBackCompleted()

        assertTrue(committed)
        assertEquals(0, progressSessions)
    }

    @Test
    fun overdrivenProgress_saturatesAtFingerCap() = runBlocking {
        // Progress misreported past 1 saturates at the driver cap (see MAX_FINGER_PROGRESS).
        val received = mutableListOf<Float>()
        val adapter = NavigationEventFlowAdapter(this)
        adapter.currentOnProgress = { events -> events.collect { received += it.progress } }

        adapter.handleBackStarted(NavigationEvent(progress = 0f))
        adapter.handleBackProgressed(NavigationEvent(progress = 1.3f))
        adapter.handleBackCancelled()
        coroutineContext.job.children.forEach { it.join() }

        assertContentEquals(listOf(0f, NavDriverSpec.MAX_FINGER_PROGRESS), received)
    }

    @Test
    fun secondGesture_afterCommit_streamsIndependently() = runBlocking {
        val received = mutableListOf<Float>()
        var commits = 0
        val adapter = NavigationEventFlowAdapter(this)
        adapter.currentOnProgress = { events -> events.collect { received += it.progress } }
        adapter.currentOnCommit = { commits++ }

        adapter.handleBackStarted(NavigationEvent(progress = 0f))
        adapter.handleBackCompleted()
        adapter.handleBackStarted(NavigationEvent(progress = 0f))
        adapter.handleBackProgressed(NavigationEvent(progress = 0.75f))
        adapter.handleBackCompleted()
        coroutineContext.job.children.forEach { it.join() }

        assertContentEquals(listOf(0f, 0f, 0.75f), received)
        assertEquals(2, commits)
    }
}
