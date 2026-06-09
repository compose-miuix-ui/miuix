// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Unit tests for the pure driver logic in NavDriver.kt:
 * - fingerTarget (gesture progress -> animatedTop target mapping)
 * - navBackCommitDecision (release commit/cancel decision)
 */
class NavDriverTest {
    // ---- fingerTarget ----

    @Test
    fun fingerTarget_zeroProgress_isTopIndex() {
        assertEquals(2f, fingerTarget(topIndex = 2, progress = 0f))
    }

    @Test
    fun fingerTarget_halfProgress_isTopMinusHalf() {
        assertEquals(1.5f, fingerTarget(topIndex = 2, progress = 0.5f))
    }

    @Test
    fun fingerTarget_fullProgress_isTopMinusOne() {
        assertEquals(1f, fingerTarget(topIndex = 2, progress = 1f))
    }

    @Test
    fun fingerTarget_clampsProgressBelowZero() {
        assertEquals(2f, fingerTarget(topIndex = 2, progress = -0.3f))
    }

    @Test
    fun fingerTarget_clampsProgressAboveOne() {
        assertEquals(1f, fingerTarget(topIndex = 2, progress = 1.4f))
    }
}
