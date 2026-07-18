// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import androidx.compose.animation.core.LinearEasing
import top.yukonga.miuix.kmp.nav.transition.NavSettleSpec
import kotlin.math.sqrt
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
    fun fingerTarget_fullProgress_saturatesJustAboveTopMinusOne() {
        // A finger position never reaches the fully-popped end (see MAX_FINGER_PROGRESS).
        assertEquals(2f - NavDriverSpec.MAX_FINGER_PROGRESS, fingerTarget(topIndex = 2, progress = 1f))
    }

    @Test
    fun fingerTarget_clampsProgressBelowZero() {
        assertEquals(2f, fingerTarget(topIndex = 2, progress = -0.3f))
    }

    @Test
    fun fingerTarget_clampsProgressAboveOne() {
        // Misreported progress past 1 saturates at the same sub-1 cap.
        assertEquals(2f - NavDriverSpec.MAX_FINGER_PROGRESS, fingerTarget(topIndex = 2, progress = 1.4f))
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
    fun anchoredProgress_saturatesJustBelowFullyPopped() {
        // anchor + finger > 1 pins just under the fully-popped end (d = -1 is terminal).
        assertEquals(NavDriverSpec.MAX_FINGER_PROGRESS, anchoredProgress(anchor = 0.5f, fingerProgress = 0.75f))
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
        // anchor == 0 degenerates to the legacy fingerTarget clamp (0..MAX_FINGER_PROGRESS).
        assertEquals(0f, anchoredProgress(anchor = 0f, fingerProgress = -0.5f))
        assertEquals(NavDriverSpec.MAX_FINGER_PROGRESS, anchoredProgress(anchor = 0f, fingerProgress = 1.5f))
    }

    // ---- usesProgrammaticCurve ----

    @Test
    fun programmaticCurve_fromRestFullStep() {
        // A programmatic push/pop: at rest, one whole entry of travel.
        assertEquals(true, usesProgrammaticCurve(velocity = 0f, distance = 1f))
        assertEquals(true, usesProgrammaticCurve(velocity = 0f, distance = -1f))
    }

    @Test
    fun programmaticCurve_fromRestMultiStep() {
        // MultiPop sweeps several layers within the same fixed-duration window.
        assertEquals(true, usesProgrammaticCurve(velocity = 0f, distance = -3f))
    }

    @Test
    fun spring_whenCarryingVelocity() {
        // An interrupted tween hands its instantaneous velocity to the spring continuation.
        assertEquals(false, usesProgrammaticCurve(velocity = -2f, distance = 1f))
    }

    @Test
    fun spring_forPartialDistance() {
        // Resuming from a mid-gesture position: a fixed-duration tween would mis-pace it.
        assertEquals(false, usesProgrammaticCurve(velocity = 0f, distance = 0.45f))
    }

    @Test
    fun programmaticCurve_toleratesThresholdResidue() {
        // A previous settle may leave the value within the visibility threshold of an integer.
        assertEquals(true, usesProgrammaticCurve(velocity = 0f, distance = 0.9995f))
    }

    // ---- commitVelocityFloor ----

    @Test
    fun noOvershootFloor_zeroDistance_isZero() {
        // Released exactly at the target: any toward-target velocity would overshoot.
        assertEquals(0f, NavSettleSpec.Spring().commitVelocityFloor(remainingDistance = 0f))
    }

    @Test
    fun noOvershootFloor_isOmegaTimesDistance() {
        // Exact no-overshoot bound for a critically damped spring: v0 >= -ω·x0, ω = √stiffness.
        assertEquals(-sqrt(NavDriverSpec.STIFFNESS), NavSettleSpec.Spring().commitVelocityFloor(remainingDistance = 1f), absoluteTolerance = 1e-4f)
    }

    @Test
    fun noOvershootFloor_scalesLinearlyWithDistance() {
        val one = NavSettleSpec.Spring().commitVelocityFloor(remainingDistance = 1f)
        assertEquals(2f * one, NavSettleSpec.Spring().commitVelocityFloor(remainingDistance = 2f), absoluteTolerance = 1e-4f)
    }

    @Test
    fun noOvershootFloor_negativeDistance_treatedAsZero() {
        // Defensive: a value already past the target must not produce a positive floor.
        assertEquals(0f, NavSettleSpec.Spring().commitVelocityFloor(remainingDistance = -0.5f))
    }

    @Test
    fun noOvershootFloor_absentForOptOutAndTween() {
        // clampOvershoot = false (and any tween) removes the floor entirely.
        val unclamped = NavSettleSpec.Spring(dampingRatio = 0.8f, stiffness = 280f, clampOvershoot = false)
        assertEquals(Float.NEGATIVE_INFINITY, unclamped.commitVelocityFloor(remainingDistance = 1f))
        assertEquals(Float.NEGATIVE_INFINITY, NavSettleSpec.Tween(450, LinearEasing).commitVelocityFloor(remainingDistance = 1f))
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
