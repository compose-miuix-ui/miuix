// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NavDisplayEffectsTest {

    private val effects = NavDisplayEffects()

    @Test
    fun clipCorners_onlyWhileLeadingTopTransitions() {
        assertTrue(effects.shouldClipCornersAt(-0.5f))
        assertFalse(effects.shouldClipCornersAt(0f))
        assertFalse(effects.shouldClipCornersAt(0.5f))
        assertFalse(effects.shouldClipCornersAt(-1f))
    }

    @Test
    fun clipCorners_disabledWhenSwitchOff() {
        val noClip = NavDisplayEffects(enableCornerClip = false)
        assertFalse(noClip.shouldClipCornersAt(-0.5f))
    }

    @Test
    fun blockInput_onlyOnNonTopMidTransition() {
        val blocking = NavDisplayEffects(blockInputDuringTransition = true)
        assertFalse(blocking.shouldBlockInputAt(0f))
        assertFalse(blocking.shouldBlockInputAt(1f))
        assertTrue(blocking.shouldBlockInputAt(0.5f))
        assertTrue(blocking.shouldBlockInputAt(1.5f))
    }

    @Test
    fun blockInput_disabledByDefault() {
        assertFalse(effects.shouldBlockInputAt(0.5f))
    }

    @Test
    fun companionNone_disablesEverything() {
        assertEquals(0f, NavDisplayEffects.None.dimAmount)
        assertFalse(NavDisplayEffects.None.shouldClipCornersAt(-0.5f))
        assertFalse(NavDisplayEffects.None.shouldBlockInputAt(0.5f))
    }
}
