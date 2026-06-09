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

    // ---- navBackCommitDecision ----

    @Test
    fun commit_whenVelocityStronglyTowardPop() {
        // Fast fling toward pop, position barely moved -> commit anyway.
        assertEquals(
            true,
            navBackCommitDecision(progress = 0.1f, velocity = 5f),
        )
    }

    @Test
    fun cancel_whenVelocityStronglyBack() {
        // Fast fling back, even though position passed half -> cancel.
        assertEquals(
            false,
            navBackCommitDecision(progress = 0.8f, velocity = -5f),
        )
    }

    @Test
    fun positionFallback_commitsWhenPastHalf() {
        // Velocity inside the dead zone -> decide by position; past 0.5 -> commit.
        assertEquals(
            true,
            navBackCommitDecision(progress = 0.6f, velocity = 0f),
        )
    }

    @Test
    fun positionFallback_cancelsWhenBeforeHalf() {
        // Velocity inside the dead zone -> decide by position; before 0.5 -> cancel.
        assertEquals(
            false,
            navBackCommitDecision(progress = 0.4f, velocity = 0f),
        )
    }

    @Test
    fun positionFallback_commitsExactlyAtHalf() {
        // Boundary: progress == positionThreshold (0.5) -> commit (>=).
        assertEquals(
            true,
            navBackCommitDecision(progress = 0.5f, velocity = 0f),
        )
    }

    @Test
    fun commit_whenVelocityExactlyAtPositiveThreshold() {
        // Boundary: velocity == +velocityThreshold -> commit (>=), regardless of position.
        assertEquals(
            true,
            navBackCommitDecision(
                progress = 0.0f,
                velocity = NavDriverSpec.COMMIT_VELOCITY_THRESHOLD,
            ),
        )
    }

    @Test
    fun cancel_whenVelocityExactlyAtNegativeThreshold() {
        // Boundary: velocity == -velocityThreshold -> cancel (<=), regardless of position.
        assertEquals(
            false,
            navBackCommitDecision(
                progress = 1.0f,
                velocity = -NavDriverSpec.COMMIT_VELOCITY_THRESHOLD,
            ),
        )
    }
}
