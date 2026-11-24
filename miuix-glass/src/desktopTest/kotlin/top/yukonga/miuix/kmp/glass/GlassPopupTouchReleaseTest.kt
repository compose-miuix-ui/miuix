// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class GlassPopupTouchReleaseTest {

    @Test
    fun glassTransformPopupUnmountsOnceMorphReturnsInsteadOfWaitingForSpringTail() = runComposeUiTest {
        mainClock.autoAdvance = false
        val show = mutableStateOf(false)
        var backgroundClicks = 0

        setContent {
            DialogTestHost {
                val anchor = rememberGlassPopupAnchor()
                Box(Modifier.size(600.dp)) {
                    Box(
                        Modifier
                            .offset(16.dp, 16.dp)
                            .size(44.dp)
                            .glassPopupAnchor(anchor, cornerRadius = 22.dp),
                    )
                    Box(
                        Modifier
                            .align(Alignment.BottomEnd)
                            .size(80.dp)
                            .testTag("under")
                            .clickable { backgroundClicks++ },
                    )
                    GlassTransformPopup(
                        show = show.value,
                        onDismissRequest = { show.value = false },
                        anchor = anchor,
                        backdrop = null,
                        anchorContent = {},
                        modifier = Modifier.testTag("popup"),
                    ) {
                        GlassPopupItem(text = "Item", onClick = { show.value = false })
                    }
                }
            }
        }

        runOnIdle { show.value = true }
        mainClock.advanceTimeBy(1000)
        waitForIdle()
        val target = onNodeWithTag("under").fetchSemanticsNode().boundsInRoot.center

        runOnIdle { show.value = false }
        mainClock.advanceTimeBy(64)
        waitForIdle()
        onNodeWithTag("popup").assertExists()
        onRoot().performTouchInput { click(target) }
        assertEquals(0, backgroundClicks)

        // Bounds/center cross TRANSFORM_VISIBILITY_THRESHOLD around ~252ms, well before ~524ms spring tail.
        mainClock.advanceTimeBy(240)
        waitForIdle()
        onNodeWithTag("popup").assertDoesNotExist()
        onRoot().performTouchInput { click(target) }
        assertEquals(1, backgroundClicks)
    }
}
