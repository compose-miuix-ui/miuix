// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassPopupMotionTest {
    @Test
    fun directionPopupContentAndPanelRetainOpeningRebound() = assertOpeningRebound(transform = false)

    @Test
    fun transformPopupContentFollowsPanelOpeningRebound() = assertOpeningRebound(transform = true)

    @Test
    fun contentKeepsFractionalPositionAcrossRoundedLayoutBounds() = runComposeUiTest {
        val position = mutableStateOf(Offset(80.25f, 80.375f))
        setContent {
            DialogTestHost {
                Box(Modifier.size(600.dp)) {
                    GlassPopupSurface(
                        onDismissRequest = {},
                        backdrop = null,
                        sizing = GlassPopupSizing(minWidth = 200.dp, maxWidth = 200.dp),
                        visuals = GlassPopupDefaults.visuals(stroke = null, shadow = null),
                        contentPadding = PaddingValues(20.dp),
                        panelLayer = {},
                        overlay = {},
                        frame = { end, _ -> GlassPopupFrame(Rect(position.value, end), 0.dp) },
                        contentLayer = { _, _ -> },
                    ) {
                        Box(Modifier.fillMaxWidth().height(120.dp).testTag("content"))
                    }
                }
            }
        }
        val before = onNodeWithTag("content").fetchSemanticsNode().boundsInRoot
        val inset = with(density) { 20.dp.toPx() }
        assertEquals(position.value.x + inset, before.left, 0.01f)
        assertEquals(position.value.y + inset, before.top, 0.01f)

        // Cross the integer rounding boundary without losing the fractional motion.
        runOnIdle { position.value = Offset(80.625f, 80.75f) }
        val after = onNodeWithTag("content").fetchSemanticsNode().boundsInRoot
        assertEquals(position.value.x + inset, after.left, 0.01f)
        assertEquals(position.value.y + inset, after.top, 0.01f)
    }

    private fun assertOpeningRebound(transform: Boolean) = runComposeUiTest {
        mainClock.autoAdvance = false
        val show = mutableStateOf(false)
        setContent {
            DialogTestHost {
                val anchor = rememberGlassPopupAnchor()
                val sizing = GlassPopupSizing(minWidth = 200.dp, maxWidth = 200.dp)
                val visuals = GlassPopupDefaults.visuals(stroke = null, shadow = null)
                Box(Modifier.size(600.dp)) {
                    Box(
                        Modifier.offset(340.dp, 60.dp).size(40.dp)
                            .glassPopupAnchor(anchor, cornerRadius = 20.dp),
                    )
                    if (transform) {
                        GlassTransformPopup(
                            show = show.value,
                            onDismissRequest = { show.value = false },
                            anchor = anchor,
                            backdrop = null,
                            anchorContent = {},
                            sizing = sizing,
                            visuals = visuals,
                            contentPadding = PaddingValues(0.dp),
                        ) {
                            Box(Modifier.fillMaxWidth().height(120.dp).testTag("content"))
                        }
                    } else {
                        GlassPopup(
                            show = show.value,
                            onDismissRequest = { show.value = false },
                            anchorBounds = anchor.containerBounds,
                            backdrop = null,
                            sizing = sizing,
                            visuals = visuals,
                            contentPadding = PaddingValues(0.dp),
                        ) {
                            Box(Modifier.fillMaxWidth().height(120.dp).testTag("content"))
                        }
                    }
                }
            }
        }
        runOnIdle { show.value = true }
        mainClock.advanceTimeBy(336)
        val rebound = onNodeWithTag("content").fetchSemanticsNode().boundsInRoot
        mainClock.advanceTimeBy(1000)
        val settled = onNodeWithTag("content").fetchSemanticsNode().boundsInRoot
        assertTrue(rebound.width > settled.width + 0.5f, "Content must follow width overshoot: $rebound vs $settled")
        assertTrue(rebound.height > settled.height + 0.2f, "Panel must not clip height overshoot: $rebound vs $settled")
        assertEquals(with(density) { 200.dp.toPx() }, settled.width, 0.01f)
    }
}
