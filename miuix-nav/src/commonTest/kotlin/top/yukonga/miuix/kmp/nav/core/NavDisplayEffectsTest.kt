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
    fun dimAlpha_isZeroAtSettledTop() {
        assertEquals(0f, effects.dimAlphaAt(0f))
    }

    @Test
    fun dimAlpha_isZeroAboveTop() {
        assertEquals(0f, effects.dimAlphaAt(-0.5f))
    }

    @Test
    fun dimAlpha_rampsLinearlyAcrossFirstCoveredLayer() {
        assertEquals(0.25f, effects.dimAlphaAt(0.5f))
    }

    @Test
    fun dimAlpha_clampsAtFullDepth() {
        assertEquals(0.5f, effects.dimAlphaAt(1f))
        assertEquals(0.5f, effects.dimAlphaAt(2f))
    }

    @Test
    fun dimAlpha_isZeroWhenDimDisabled() {
        val noDim = NavDisplayEffects(dimAmount = 0f)
        assertEquals(0f, noDim.dimAlphaAt(0.5f))
    }

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
        assertFalse(effects.shouldBlockInputAt(0f))
        assertFalse(effects.shouldBlockInputAt(1f))
        assertTrue(effects.shouldBlockInputAt(0.5f))
        assertTrue(effects.shouldBlockInputAt(1.5f))
    }

    @Test
    fun blockInput_disabledWhenSwitchOff() {
        val noBlock = NavDisplayEffects(blockInputDuringTransition = false)
        assertFalse(noBlock.shouldBlockInputAt(0.5f))
    }

    @Test
    fun companionNone_disablesEverything() {
        assertEquals(0f, NavDisplayEffects.None.dimAlphaAt(0.5f))
        assertFalse(NavDisplayEffects.None.shouldClipCornersAt(-0.5f))
        assertFalse(NavDisplayEffects.None.shouldBlockInputAt(0.5f))
    }
}
