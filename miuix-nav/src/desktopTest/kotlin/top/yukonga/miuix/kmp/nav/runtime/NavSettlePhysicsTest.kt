// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.runtime.MonotonicFrameClock
import kotlinx.coroutines.runBlocking
import top.yukonga.miuix.kmp.nav.transition.NavMotion
import top.yukonga.miuix.kmp.nav.transition.NavSettleSpec
import kotlin.math.min
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Deterministic 16ms-per-frame clock so Animatable can run under plain runBlocking. */
private class FixedFrameClock : MonotonicFrameClock {
    var frames: Int = 0
        private set
    private var nanos = 0L

    override suspend fun <R> withFrameNanos(onFrame: (frameTimeNanos: Long) -> R): R {
        frames++
        nanos += 16_000_000L
        return onFrame(nanos)
    }
}

/**
 * Behavior of the per-transition settle physics: the no-overshoot floor only applies while the
 * commit spec keeps overshoot clamped, the cancel settle pins the rest bound, and the
 * programmatic dispatch falls back to a spring whenever a tween cannot serve.
 */
class NavSettlePhysicsTest {

    @Test
    fun clampedCommit_neverCrossesTarget() {
        val spec = NavSettleSpec.Spring()
        val animatable = Animatable(1f)
        var minSeen = 1f
        runBlocking(FixedFrameClock()) {
            val seed = (-50f).coerceAtLeast(spec.commitVelocityFloor(1f))
            animatable.animateTo(0f, spec.toAnimationSpec(), initialVelocity = seed) {
                minSeen = min(minSeen, value)
            }
        }
        assertTrue(minSeen >= -0.01f, "clamped commit crossed the target: min $minSeen")
        assertEquals(0f, animatable.value, absoluteTolerance = 0.01f)
    }

    @Test
    fun unclampedUnderdampedCommit_overshootsAndConverges() {
        val spec = NavSettleSpec.Spring(dampingRatio = 0.8f, stiffness = 280f, clampOvershoot = false)
        assertEquals(Float.NEGATIVE_INFINITY, spec.commitVelocityFloor(1f))
        val animatable = Animatable(1f)
        var minSeen = 1f
        runBlocking(FixedFrameClock()) {
            animatable.animateTo(0f, spec.toAnimationSpec(), initialVelocity = -50f) {
                minSeen = min(minSeen, value)
            }
        }
        assertTrue(minSeen < -0.05f, "flung underdamped commit must overshoot: min $minSeen")
        assertEquals(0f, animatable.value, absoluteTolerance = 0.01f)
    }

    @Test
    fun underdampedFromRest_stillOvershoots() {
        val spec = NavSettleSpec.Spring(dampingRatio = 0.8f, stiffness = 280f, clampOvershoot = false)
        val animatable = Animatable(1f)
        var minSeen = 1f
        runBlocking(FixedFrameClock()) {
            animatable.animateTo(0f, spec.toAnimationSpec()) { minSeen = min(minSeen, value) }
        }
        assertTrue(minSeen < -0.005f, "underdamped step response must overshoot: min $minSeen")
    }

    @Test
    fun settleCancel_pinsRestBoundAndClearsIt() = runBlocking(FixedFrameClock()) {
        val animatable = Animatable(0.5f)
        animatable.settleCancel(
            target = 1f,
            spec = NavSettleSpec.Spring(dampingRatio = 0.6f, stiffness = 280f, clampOvershoot = false),
            initialVelocity = 8f,
        )
        assertEquals(1f, animatable.value)
        // Bounds must be cleared afterwards: a later snap past the old bound succeeds.
        animatable.snapTo(1.5f)
        assertEquals(1.5f, animatable.value)
    }

    @Test
    fun settleProgrammatic_fullStepUsesTween_partialFallsBackToSpring() {
        val motion = NavMotion(
            commit = NavSettleSpec.Tween(450, LinearEasing),
            programmatic = NavSettleSpec.Tween(160, LinearEasing),
        )
        // From-rest full step: linear 160ms tween finishes in ~10-12 frames.
        val fullClock = FixedFrameClock()
        runBlocking(fullClock) { Animatable(1f).settleProgrammatic(0f, motion) }
        assertTrue(fullClock.frames in 8..14, "full step should ride the 160ms tween, took ${fullClock.frames} frames")
        // Partial distance with a Tween commit: falls back to the default spring (many more frames).
        val partialClock = FixedFrameClock()
        runBlocking(partialClock) { Animatable(0.7f).settleProgrammatic(0f, motion) }
        assertTrue(partialClock.frames > 20, "partial settle must fall back to the spring, took ${partialClock.frames} frames")
    }
}
