// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import top.yukonga.miuix.kmp.nav.transition.NavSettle
import top.yukonga.miuix.kmp.nav.transition.NavSettlePhase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertSame
import kotlin.test.assertTrue

private class TestSink : NavSettleSink {
    override var settle: NavSettle? = null
}

class NavSettleTrackingTest {

    @Test
    fun trackSettle_publishesDuringBody_clearsAfter() = runBlocking {
        val sink = TestSink()
        sink.trackSettle(NavSettlePhase.Programmatic, releaseVelocity = 0f) { _ ->
            val s = sink.settle
            assertEquals(NavSettlePhase.Programmatic, s?.phase)
            assertEquals(0f, s?.releaseVelocity)
        }
        assertNull(sink.settle)
    }

    @Test
    fun trackSettle_onFrameStampsElapsed_monotonic() = runBlocking {
        val sink = TestSink()
        sink.trackSettle(NavSettlePhase.Commit, releaseVelocity = 2.5f) { onFrame ->
            assertEquals(2.5f, sink.settle?.releaseVelocity)
            onFrame()
            val first = sink.settle!!.elapsedMillis
            assertTrue(first >= 0f)
            delay(20)
            onFrame()
            assertTrue(sink.settle!!.elapsedMillis >= first)
        }
    }

    @Test
    fun trackSettle_identityGuard_doesNotClearNewerSettle() = runBlocking {
        val sink = TestSink()
        var newer: NavSettle? = null
        sink.trackSettle(NavSettlePhase.Cancel, releaseVelocity = 0f) { _ ->
            // Simulate an interrupting settle replacing the context before our finally runs.
            newer = NavSettleState(NavSettlePhase.Programmatic, 0f)
            sink.settle = newer
        }
        assertSame(newer, sink.settle, "finally must not clear a newer settle's context")
    }
}
