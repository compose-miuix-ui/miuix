// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.ui.graphics.Color
import kotlin.test.Test
import kotlin.test.assertEquals

class NavigationBarItemColorsTest {
    @Test
    fun transparentContentStaysTransparentInEveryState() {
        val colors = NavigationBarItemColors(Color.Transparent, Color.Transparent)

        for (selected in listOf(false, true)) {
            for (pressed in listOf(false, true)) {
                assertEquals(Color.Transparent, colors.contentColor(selected, pressed))
            }
        }
    }

    @Test
    fun translucentContentRetainsItsAlphaAndHue() {
        val unselected = Color(0x804080C0)
        val selected = Color(0x4080C040)
        val colors = NavigationBarItemColors(unselected, selected)

        assertEquals(selected, colors.contentColor(selected = true, isPressed = false))
        assertEquals(
            selected.copy(alpha = selected.alpha * 0.5f),
            colors.contentColor(selected = true, isPressed = true),
        )
        assertEquals(
            unselected.copy(alpha = unselected.alpha * 0.4f),
            colors.contentColor(selected = false, isPressed = false),
        )
        assertEquals(
            unselected.copy(alpha = unselected.alpha * 0.6f),
            colors.contentColor(selected = false, isPressed = true),
        )
    }

    @Test
    fun opaqueContentPreservesOriginalStateOpacity() {
        val color = Color(0xFF4080C0)
        val colors = NavigationBarItemColors(color, color)

        assertEquals(color, colors.contentColor(selected = true, isPressed = false))
        assertEquals(color.copy(alpha = 0.5f), colors.contentColor(selected = true, isPressed = true))
        assertEquals(color.copy(alpha = 0.4f), colors.contentColor(selected = false, isPressed = false))
        assertEquals(color.copy(alpha = 0.6f), colors.contentColor(selected = false, isPressed = true))
    }
}
