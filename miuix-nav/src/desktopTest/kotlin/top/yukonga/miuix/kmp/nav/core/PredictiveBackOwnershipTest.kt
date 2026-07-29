// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PredictiveBackOwnershipTest {
    @Test
    fun staleLeaseCannotReleaseNewerSession() {
        val ownership = PredictiveBackOwnership()
        val sessionA = ownership.acquire()
        val sessionB = ownership.acquire()

        assertEquals(3L, ownership.generation)
        assertFalse(ownership.release(sessionA))
        assertEquals(3L, ownership.generation)
        assertTrue(ownership.release(sessionB))
        assertEquals(4L, ownership.generation)
    }
}
