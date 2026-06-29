// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import top.yukonga.miuix.kmp.nav.runtime.NavDriverSpec
import top.yukonga.miuix.kmp.nav.runtime.NavProgrammaticEasing
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertTrue

class NavMotionTest {

    @Test
    fun springDefaults_matchDriverConstants() {
        val spring = NavSettleSpec.Spring()
        assertEquals(NavDriverSpec.DAMPING_RATIO, spring.dampingRatio)
        assertEquals(NavDriverSpec.STIFFNESS, spring.stiffness)
        assertTrue(spring.clampOvershoot)
    }

    @Test
    fun underdampedSpring_requiresExplicitOvershootAcknowledgement() {
        assertFailsWith<IllegalArgumentException> { NavSettleSpec.Spring(dampingRatio = 0.8f) }
        // Explicit acknowledgement constructs fine.
        NavSettleSpec.Spring(dampingRatio = 0.8f, clampOvershoot = false)
    }

    @Test
    fun motionDefault_keepsEstablishedProgrammaticCurve() {
        val programmatic = assertIs<NavSettleSpec.Tween>(NavMotion.Default.programmatic)
        assertEquals(NavDriverSpec.PROGRAMMATIC_DURATION_MILLIS, programmatic.durationMillis)
        assertEquals(NavProgrammaticEasing, programmatic.easing)
    }
}
