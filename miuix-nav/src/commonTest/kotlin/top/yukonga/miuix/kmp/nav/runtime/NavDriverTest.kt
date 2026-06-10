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

    // ---- anchoredProgress ----

    @Test
    fun anchoredProgress_zeroFinger_returnsAnchor_positiveAnchor() {
        // Grab mid-push: first frame must map exactly back to the sampled anchor (zero jump).
        assertEquals(0.5f, anchoredProgress(anchor = 0.5f, fingerProgress = 0f))
    }

    @Test
    fun anchoredProgress_zeroFinger_returnsAnchor_negativeAnchor() {
        // Grab mid-pop-settle: anchor below zero is preserved as-is on the first frame.
        assertEquals(-0.25f, anchoredProgress(anchor = -0.25f, fingerProgress = 0f))
    }

    @Test
    fun anchoredProgress_addsLinearly() {
        // Slope stays exactly 1 (1:1 with the finger).
        assertEquals(0.75f, anchoredProgress(anchor = 0.5f, fingerProgress = 0.25f))
    }

    @Test
    fun anchoredProgress_saturatesAtOne() {
        // anchor + finger > 1 pins at the fully-popped end.
        assertEquals(1f, anchoredProgress(anchor = 0.5f, fingerProgress = 0.75f))
    }

    @Test
    fun anchoredProgress_positiveAnchor_floorsAtZero() {
        // Reverse drag from a mid-push grab can at most push the page back to rest, never past it.
        assertEquals(0f, anchoredProgress(anchor = 0.5f, fingerProgress = -0.75f))
    }

    @Test
    fun anchoredProgress_negativeAnchor_floorsAtAnchor() {
        // Reverse drag during a pop-settle grab freezes the leaving entry at the grab point;
        // it never re-reveals an already-popped page.
        assertEquals(-0.25f, anchoredProgress(anchor = -0.25f, fingerProgress = -0.5f))
    }

    @Test
    fun anchoredProgress_negativeAnchor_reelsThroughZero() {
        // Forward drag reels the leaving entry out, then starts peeling the current top.
        assertEquals(0.25f, anchoredProgress(anchor = -0.25f, fingerProgress = 0.5f))
    }

    @Test
    fun anchoredProgress_deepNegativeAnchor_multiPopReel() {
        // MultiPop interrupted mid-flight: anchors below -1 reel multiple leaving layers 1:1.
        assertEquals(-1.75f, anchoredProgress(anchor = -2.25f, fingerProgress = 0.5f))
    }

    @Test
    fun anchoredProgress_zeroAnchor_matchesLegacyClamp() {
        // anchor == 0 degenerates to the legacy fingerTarget clamp (0..1).
        assertEquals(0f, anchoredProgress(anchor = 0f, fingerProgress = -0.5f))
        assertEquals(1f, anchoredProgress(anchor = 0f, fingerProgress = 1.5f))
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
