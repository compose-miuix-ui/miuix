// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performKeyInput
import androidx.compose.ui.test.pressKey
import androidx.compose.ui.test.v2.runComposeUiTest
import top.yukonga.miuix.kmp.utils.MiuixIndication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class BasicComponentHoldDownTest {
    @Test
    fun releasingAHeldRowPreservesItsKeyboardFocusHighlight() = runComposeUiTest {
        val held = mutableStateOf(false)
        setContent {
            CompositionLocalProvider(LocalIndication provides MiuixIndication()) {
                BasicComponent(
                    modifier = Modifier.background(Color.White).testTag("row"),
                    onClick = { held.value = !held.value },
                    holdDownState = held.value,
                ) {}
            }
        }
        val row = onNodeWithTag("row")
        fun brightness() = row.captureToImage().toPixelMap()[5, 5].red

        val idle = brightness()
        onRoot().performKeyInput { pressKey(Key.Tab) }
        row.assertIsFocused()
        val focused = brightness()
        assertTrue(focused < idle, "Keyboard focus must be visible")

        row.performKeyInput { pressKey(Key.Enter) }
        val heldBrightness = brightness()
        assertTrue(heldBrightness < focused, "A held row must show feedback")
        row.performKeyInput { pressKey(Key.Enter) }
        row.assertIsFocused()
        assertEquals(focused, brightness(), 1f / 255f, "Releasing the hold must preserve the existing focus highlight")
    }

    @Test
    fun aReadOnlyRowShowsAndReleasesHoldFeedbackWithoutAClickAction() = runComposeUiTest {
        val held = mutableStateOf(false)
        setContent {
            CompositionLocalProvider(LocalIndication provides MiuixIndication()) {
                BasicComponent(
                    modifier = Modifier.background(Color.White).testTag("row"),
                    holdDownState = held.value,
                ) {}
            }
        }
        val row = onNodeWithTag("row")
        fun brightness() = row.captureToImage().toPixelMap()[5, 5].red

        val idle = brightness()
        runOnIdle { held.value = true }
        row.assertHasNoClickAction()
        assertTrue(brightness() < idle, "A read-only row must still show hold feedback")
        runOnIdle { held.value = false }
        assertEquals(idle, brightness(), 1f / 255f)
    }
}
