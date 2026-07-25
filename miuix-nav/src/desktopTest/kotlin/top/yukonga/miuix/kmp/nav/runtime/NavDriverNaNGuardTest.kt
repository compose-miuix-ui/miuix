// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import androidx.compose.animation.core.Animatable
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

/**
 * A snap arriving before the grab anchor is sampled (anchor still NaN — possible on
 * desktop/web where Animatable.stop() suspends across dispatcher slices while a mid-flight
 * settle unwinds) must be a no-op: NaN written into the driving float is unrecoverable —
 * every later sample, release decision, and settle integrates from it.
 */
class NavDriverNaNGuardTest {

    @Test
    fun snapToFingerWithNaNAnchorIsNoOp() = runBlocking {
        val animatedTop = Animatable(1f)
        animatedTop.snapToFinger(topIndex = 1, progress = 0.3f, anchor = Float.NaN)
        assertFalse(animatedTop.value.isNaN(), "a pre-anchor snap must not poison the driving float")
        assertEquals(1f, animatedTop.value, "the value must stay where it was")
    }

    @Test
    fun snapToFingerWithFiniteAnchorStillSnaps() = runBlocking {
        val animatedTop = Animatable(1f)
        animatedTop.snapToFinger(topIndex = 1, progress = 0.3f, anchor = 0f)
        assertEquals(1f - 0.3f, animatedTop.value, absoluteTolerance = 1e-6f)
    }
}
