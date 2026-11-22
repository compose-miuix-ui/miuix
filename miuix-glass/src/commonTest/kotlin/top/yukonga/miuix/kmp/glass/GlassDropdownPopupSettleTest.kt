// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertTrue

class GlassDropdownPopupSettleTest {
    @Test
    fun dropdownSizeAndPositionSettleWithoutVisibleSnapping() {
        for (entering in listOf(true, false)) {
            assertSubpixelSettle(GlassMotion.popupProgressSpring(GlassMotion.arcBounds(entering)), entering)
            assertSubpixelSettle(GlassMotion.popupProgressSpring(GlassMotion.arcPosition(entering)), entering)
        }
    }

    private fun assertSubpixelSettle(spec: SpringSpec<Float>, entering: Boolean = true) {
        val target = if (entering) 1f else 0f
        val animation = TargetBasedAnimation(
            animationSpec = spec,
            typeConverter = Float.VectorConverter,
            initialValue = if (entering) 0f else 1f,
            targetValue = target,
        )
        val beforeFinish = animation.getValueFromNanos(animation.durationNanos - 1_000_000L)
        // A 1000 px travel is typical for a tall menu on a high-density phone.
        val snapDistance = abs(target - beforeFinish) * 1000f
        assertTrue(snapDistance < 1f, "Settling must not jump $snapDistance px directly to the target")
    }
}
