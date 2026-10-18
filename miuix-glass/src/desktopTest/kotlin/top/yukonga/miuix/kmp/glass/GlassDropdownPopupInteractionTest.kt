// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class GlassDropdownPopupInteractionTest {

    @Test
    fun closingRowsRejectTouchesAndAccessibilityActionsUntilReopened() = runComposeUiTest {
        mainClock.autoAdvance = false
        val show = mutableStateOf(true)
        var selections = 0
        setContent {
            DialogTestHost {
                Box(Modifier.fillMaxSize()) {
                    GlassDropdownPopup(
                        show = show.value,
                        onDismissRequest = { show.value = false },
                        anchorBounds = Rect(16f, 64f, 200f, 112f),
                        backdrop = null,
                        modifier = Modifier.testTag("dropdown"),
                    ) {
                        GlassPopupItem(
                            text = "Choose",
                            onClick = {
                                selections++
                                show.value = false
                            },
                        )
                    }
                }
            }
        }
        waitForIdle()
        val row = onNodeWithText("Choose").assertHasClickAction()
        val position = row.fetchSemanticsNode().boundsInRoot.center
        row.performTouchInput { click() }
        assertEquals(1, selections)
        mainClock.advanceTimeByFrame()
        waitForIdle()

        // Keep the exit animation running and tap the same row again.
        onNodeWithTag("dropdown").assertExists()
        onRoot().performTouchInput { click(position) }
        assertEquals(1, selections)
        onNodeWithText("Choose").assertDoesNotExist()

        mainClock.advanceTimeBy(1000)
        waitForIdle()
        onNodeWithTag("dropdown").assertDoesNotExist()
        runOnIdle { show.value = true }
        mainClock.advanceTimeBy(1000)
        waitForIdle()
        onNodeWithText("Choose").assertHasClickAction().performTouchInput { click() }
        assertEquals(2, selections)
    }
}
