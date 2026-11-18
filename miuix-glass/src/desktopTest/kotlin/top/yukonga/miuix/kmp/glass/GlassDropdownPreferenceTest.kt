// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
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
    fun firstOpenAndReopenAfterScrollingAnimateFromCurrentValueBounds() = runComposeUiTest {
        mainClock.autoAdvance = false
        val owner = TestNavigationOwner()
        val scroll = ScrollState(0)
        setContent {
            DialogTestHost(owner) {
                Column(Modifier.height(240.dp).verticalScroll(scroll)) {
                    Spacer(Modifier.height(128.dp))
                    GlassDropdownPreference(
                        onSelectedIndexChange = {},
                        items = listOf("Warn", "Debug"),
                        selectedIndex = 1,
                        title = "Log level",
                        backdrop = null,
                    )
                    Spacer(Modifier.height(240.dp))
                }
            }
        }
        repeat(2) {
            val delta = with(density) { 48.dp.toPx() }
            runOnIdle { assertEquals(delta, scroll.dispatchRawDelta(delta)) }
            mainClock.advanceTimeByFrame()
            val valueTop = onNodeWithText("Debug", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot.top
            onNodeWithText("Log level").performClick()
            mainClock.advanceTimeBy(64)
            waitForIdle()
            val opening = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot
            mainClock.advanceTimeBy(1000)
            waitForIdle()
            val expanded = onNodeWithText("Warn").fetchSemanticsNode().boundsInRoot
            assertTrue(opening.width < expanded.width, "The dropdown must grow during opening")
            val inset = with(density) { GlassPopupDefaults.ContentPaddingVertical.toPx() }
            assertEquals(valueTop + inset, expanded.top, 1f)
            assertTrue(
                opening.top in (valueTop - inset)..(valueTop + 2 * inset),
                "Opening must start at the scrolled value: $opening vs $valueTop",
            )
            runOnIdle { owner.input.backCompleted() }
            mainClock.advanceTimeBy(1000)
            waitForIdle()
            onNodeWithText("Warn").assertDoesNotExist()
        }
    }

    @Test
    fun onlyMountedValuesTrackScrollWhilePublicAnchorsAlwaysReport() = runComposeUiTest {
        val scroll = ScrollState(0)
        val mounted = mutableStateOf(false)
        val preference = GlassPopupAnchor()
        val external = GlassPopupAnchor()
        setContent {
            Column(Modifier.height(160.dp).verticalScroll(scroll)) {
                Spacer(Modifier.height(100.dp))
                Box {
                    Box(Modifier.size(16.dp).glassPopupAnchorValue(preference, trackBounds = mounted.value))
                    Box(Modifier.size(16.dp).glassPopupAnchorValue(external))
                }
                Spacer(Modifier.height(160.dp))
            }
        }
        repeat(2) {
            val closedBounds = runOnIdle { preference.dropdownValueBounds }
            val previousTop = runOnIdle { checkNotNull(external.dropdownValueBounds).top }
            runOnIdle { scroll.dispatchRawDelta(16f) }
            runOnIdle {
                assertEquals(previousTop - 16f, checkNotNull(external.dropdownValueBounds).top)
                assertEquals(closedBounds, preference.dropdownValueBounds)
                mounted.value = true
            }
            runOnIdle {
                assertEquals(external.dropdownValueBounds, preference.dropdownValueBounds)
                mounted.value = false
            }
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
        assertTrue(
            settling.width > settled.width + 0.1f,
            "The tail must not snap to its target at the old 356ms cutoff: $settling vs $settled",
        )
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
