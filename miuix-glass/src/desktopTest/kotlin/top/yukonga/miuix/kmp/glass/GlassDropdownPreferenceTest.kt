// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertHasNoClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.BasicComponent
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassDropdownPreferenceTest {

    @Test
    fun firstOpenAndReopenAnimateFromCollapsedBounds() = runComposeUiTest {
        mainClock.autoAdvance = false
        val owner = TestNavigationOwner()
        setContent {
            DialogTestHost(owner) {
                GlassDropdownPreference(
                    onSelectedIndexChange = {},
                    items = listOf("Debug", "Warn"),
                    selectedIndex = 0,
                    title = "Log level",
                    backdrop = null,
                )
            }
        }
        repeat(2) {
            onNodeWithText("Log level").performClick()
            mainClock.advanceTimeBy(64)
            waitForIdle()
            val opening = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot
            mainClock.advanceTimeBy(1000)
            waitForIdle()
            val expanded = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot
            assertTrue(opening.width < expanded.width, "The dropdown must grow during opening")
            runOnIdle { owner.input.backCompleted() }
            mainClock.advanceTimeBy(1000)
            waitForIdle()
            onNodeWithText("Warn").assertDoesNotExist()
        }
    }

    @Test
    fun openingContentFollowsReboundAndContinuesSettlingPastTheOldCutoff() = runComposeUiTest {
        mainClock.autoAdvance = false
        setContent {
            DialogTestHost {
                GlassDropdownPreference(
                    onSelectedIndexChange = {},
                    items = listOf("Debug", "Warn", "Error"),
                    selectedIndex = 0,
                    title = "Log level",
                    backdrop = null,
                    sizing = GlassPopupSizing(minWidth = 200.dp, maxWidth = 200.dp),
                    visuals = GlassPopupDefaults.visuals(stroke = null, shadow = null),
                )
            }
        }
        onNodeWithText("Log level").performClick()
        // Allow the Scaffold popup host to mount before sampling the spring's rebound.
        mainClock.advanceTimeBy(64)
        onNodeWithText("Warn").assertExists()
        mainClock.advanceTimeBy(320)
        val rebound = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot
        mainClock.advanceTimeBy(80)
        val settling = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot
        mainClock.advanceTimeBy(1000)
        val settled = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot
        assertTrue(rebound.width > settled.width + 0.5f, "Rows must keep following the panel past 100%: $rebound vs $settled")
        assertTrue(settling.width > settled.width + 0.1f, "The tail must not snap to its target at the old 356ms cutoff: $settling vs $settled")
        assertTrue(settling.width < rebound.width, "The panel must return smoothly from its widest point")
    }

    @Test
    fun titleMatchesStandardPreferenceAndPopupUsesMediumWeight() = runComposeUiTest {
        setContent {
            DialogTestHost {
                Column {
                    BasicComponent(title = "Standard preference")
                    GlassDropdownPreference(
                        onSelectedIndexChange = {},
                        items = listOf("Debug", "Warn"),
                        selectedIndex = 0,
                        title = "Bluetooth stack log",
                        backdrop = null,
                    )
                }
            }
        }
        val standard = mutableListOf<TextLayoutResult>()
        val glass = mutableListOf<TextLayoutResult>()
        onNodeWithText("Standard preference", useUnmergedTree = true)
            .performSemanticsAction(SemanticsActions.GetTextLayoutResult) { it(standard) }
        onNodeWithText("Bluetooth stack log", useUnmergedTree = true)
            .performSemanticsAction(SemanticsActions.GetTextLayoutResult) { it(glass) }
        assertEquals(standard.single().layoutInput.style, glass.single().layoutInput.style)
        onNodeWithText("Bluetooth stack log").performClick()
        val option = mutableListOf<TextLayoutResult>()
        onNodeWithText("Warn", useUnmergedTree = true)
            .performSemanticsAction(SemanticsActions.GetTextLayoutResult) { it(option) }
        assertEquals(FontWeight.Medium, option.single().layoutInput.style.fontWeight)
        assertEquals(16.sp, option.single().layoutInput.style.fontSize)
    }

    @Test
    fun selectionUpdatesValueAndBackDismissalAllowsReopening() = runComposeUiTest {
        val selected = mutableIntStateOf(0)
        val owner = TestNavigationOwner()
        val expansions = mutableListOf<Boolean>()
        setContent {
            DialogTestHost(owner) {
                GlassDropdownPreference(
                    onSelectedIndexChange = { selected.intValue = it },
                    items = listOf("Debug", "Warn"),
                    selectedIndex = selected.intValue,
                    title = "Log level",
                    backdrop = null,
                    onExpandedChange = { expansions += it },
                )
            }
        }
        onNodeWithText("Log level").performClick()
        onNodeWithText("Warn").performClick()
        waitForIdle()
        assertEquals(1, selected.intValue)
        assertEquals(listOf(true, false), expansions)
        onNodeWithText("Warn").assertIsDisplayed()
        onNodeWithText("Log level").performClick()
        runOnIdle { owner.input.backCompleted() }
        waitForIdle()
        assertEquals(listOf(true, false, true, false), expansions)
        onNodeWithText("Debug").assertDoesNotExist()
        onNodeWithText("Warn").assertIsDisplayed()
        onNodeWithText("Log level").performClick()
        onNodeWithText("Debug").assertIsDisplayed().performClick()
        waitForIdle()
        assertEquals(0, selected.intValue)
    }

    @Test
    fun emptyOrDisabledOptionsCannotOpenAndDisablingClosesThePopup() = runComposeUiTest {
        val items = mutableStateOf(emptyList<String>())
        val enabled = mutableStateOf(true)
        val expansions = mutableListOf<Boolean>()
        setContent {
            DialogTestHost {
                GlassDropdownPreference(
                    onSelectedIndexChange = {},
                    items = items.value,
                    selectedIndex = 0,
                    title = "Options",
                    backdrop = null,
                    enabled = enabled.value,
                    onExpandedChange = { expansions += it },
                )
            }
        }
        onNodeWithText("Options").assertHasNoClickAction()
        runOnIdle { items.value = listOf("One", "Two") }
        onNodeWithText("Options").performClick()
        onNodeWithText("Two").assertIsDisplayed()
        runOnIdle { enabled.value = false }
        waitForIdle()
        onNodeWithText("Two").assertDoesNotExist()
        onNodeWithText("Options").assertHasNoClickAction()
        assertEquals(listOf(true, false), expansions)
    }
}
