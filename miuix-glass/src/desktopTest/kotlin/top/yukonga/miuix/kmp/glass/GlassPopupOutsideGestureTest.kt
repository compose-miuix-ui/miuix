// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassPopupOutsideGestureTest {
    @Test
    fun outsideTapClosesOnRelease() = checkOutsideGesture(Orientation.Horizontal, drag = false)

    @Test
    fun outsideHorizontalDragClosesOnReleaseWithoutScrollingParent() = checkOutsideGesture(Orientation.Horizontal, drag = true)

    @Test
    fun outsideVerticalDragClosesOnReleaseWithoutScrollingParent() = checkOutsideGesture(Orientation.Vertical, drag = true)

    @Test
    fun cancelledOutsideDragDoesNotDismiss() = checkOutsideGesture(Orientation.Horizontal, drag = true, cancelled = true)

    @Test
    fun consumedOutsideReleaseDoesNotDismiss() = checkOutsideGesture(Orientation.Horizontal, drag = true, consumedRelease = true)

    private fun checkOutsideGesture(orientation: Orientation, drag: Boolean, cancelled: Boolean = false, consumedRelease: Boolean = false) = runComposeUiTest {
        val show = mutableStateOf(true)
        var dismissals = 0
        var scrollDistance = 0f
        setContent {
            DialogTestHost {
                val scroll = rememberScrollableState { delta ->
                    scrollDistance += delta
                    delta
                }
                Box(
                    Modifier.size(500.dp)
                        .pointerInput(consumedRelease) {
                            if (consumedRelease) {
                                awaitPointerEventScope {
                                    while (true) {
                                        val event = awaitPointerEvent(PointerEventPass.Initial)
                                        // Android represents cancellation as an already-consumed up event.
                                        event.changes.forEach { if (!it.pressed) it.consume() }
                                    }
                                }
                            }
                        }
                        .scrollable(scroll, orientation)
                        .testTag("scene"),
                ) {
                    GlassPopup(
                        show = show.value,
                        onDismissRequest = {
                            dismissals++
                            show.value = false
                        },
                        anchorBounds = Rect(20f, 20f, 60f, 60f),
                        backdrop = null,
                    ) {
                        GlassPopupItem(text = "Menu item", onClick = {})
                    }
                }
            }
        }
        onNodeWithText("Menu item").assertIsDisplayed()
        onNodeWithTag("scene").performTouchInput {
            down(Offset(width * 0.85f, height * 0.85f))
            if (drag) {
                repeat(10) { step ->
                    val fraction = 0.85f - (step + 1) * 0.04f
                    val position = if (orientation == Orientation.Horizontal) {
                        Offset(width * fraction, height * 0.85f)
                    } else {
                        Offset(width * 0.85f, height * fraction)
                    }
                    moveTo(position, delayMillis = 16)
                }
            }
        }
        runOnIdle {
            assertTrue(show.value, "Outside gestures must not dismiss before release")
            assertEquals(0, dismissals)
            assertEquals(0f, scrollDistance, "The parent must not take over the drag")
        }
        onNodeWithTag("scene").performTouchInput {
            if (cancelled) cancel() else up()
        }
        val keepOpen = cancelled || consumedRelease
        runOnIdle {
            if (keepOpen) assertTrue(show.value) else assertFalse(show.value)
            assertEquals(if (keepOpen) 0 else 1, dismissals)
            assertEquals(0f, scrollDistance)
        }
        if (keepOpen) {
            onNodeWithText("Menu item").assertIsDisplayed()
        } else {
            onNodeWithText("Menu item").assertDoesNotExist()
        }
    }
}
