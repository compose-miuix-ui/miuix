// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import kotlin.test.Test
import kotlin.test.assertEquals

private data object OpacityUnder : NavKey

private data object OpacityOver : NavKey

/**
 * Entries are input-opaque within their bounds: a touch on a blank region of the top entry (no
 * in-page consumer there) must never operate the covered — invisible but still composed — entry
 * below, while the top entry's own consumers keep working.
 */
@OptIn(ExperimentalTestApi::class)
class NavInputOpacityTest {

    @Test
    fun blankRegionOfTopEntry_doesNotReachCoveredEntry() = runComposeUiTest {
        var coveredClicks = 0
        var topClicks = 0
        val backStack = navBackStackOf(OpacityUnder, OpacityOver)
        setContent {
            NavDisplay(backStack, transition = NavTransitions.None, effects = NavDisplayEffects.None) {
                entry<OpacityUnder> { Box(Modifier.fillMaxSize().clickable { coveredClicks++ }) }
                entry<OpacityOver> {
                    Box(Modifier.fillMaxSize()) {
                        Box(Modifier.size(40.dp).clickable { topClicks++ })
                    }
                }
            }
        }
        waitForIdle()

        // Blank region of the top entry (outside its corner clickable).
        onRoot().performTouchInput { click(center) }
        waitForIdle()
        assertEquals(0, coveredClicks, "a blank-region touch must not fall through to the covered entry")

        // The top entry's own consumer still works.
        onRoot().performTouchInput { click(Offset(20f, 20f)) }
        waitForIdle()
        assertEquals(1, topClicks, "the top entry's own clickable must still receive input")
        assertEquals(0, coveredClicks)
    }
}
