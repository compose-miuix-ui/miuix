// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.test.v2.runDesktopComposeUiTest
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Search
import kotlin.math.ceil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassNavigationRailTest {

    private val items = listOf(
        GlassNavigationItem(MiuixIcons.Basic.Search, "Home"),
        GlassNavigationItem(MiuixIcons.Basic.Search, "Library"),
        GlassNavigationItem(MiuixIcons.Basic.Search, "Settings"),
    )

    @Test
    fun collapsedCapsuleShowsLabelsInOneCentredRow() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        setContent {
            DialogTestHost {
                Box(Modifier.size(800.dp, 600.dp)) {
                    GlassNavigationRail(
                        items = items,
                        selectedIndex = 0,
                        onSelect = {},
                        backdrop = null,
                        modifier = Modifier.testTag("rail"),
                        state = state,
                        defaultWindowInsetsPadding = false,
                    )
                }
            }
        }
        val expandedHome = onNodeWithText("Home").getUnclippedBoundsInRoot()

        onNodeWithContentDescription(GlassNavigationRailDefaults.CollapseContentDescription)
            .performClick()
        waitForIdle()

        // Collapsed, the capsule is text navigation: every label is still shown, in one row beside
        // the toggle, and the row has travelled up and inward from its expanded column.
        val labels = listOf("Home", "Library", "Settings").map {
            onNodeWithText(it).getUnclippedBoundsInRoot()
        }
        assertTrue(labels.all { it.bottom > it.top })
        assertEquals(1, labels.map { it.top }.distinct().size)
        assertTrue(labels[0].right <= labels[1].left && labels[1].right <= labels[2].left)
        assertTrue(labels[0].top < expandedHome.top)
        assertTrue(labels[0].left > expandedHome.left)

        // The row is centred on the container, allowing for the toggle block at its leading edge.
        val rowCentre = (labels[0].left + labels[2].right) / 2
        assertTrue(rowCentre > 380.dp && rowCentre < 440.dp, "row centre was $rowCentre")
    }

    @Test
    fun theMorphMovesRowsWithoutRecomposingThem() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        setContent {
            DialogTestHost {
                Box(Modifier.size(800.dp, 600.dp)) {
                    GlassNavigationRail(
                        items = items,
                        selectedIndex = 1,
                        onSelect = {},
                        backdrop = null,
                        state = state,
                        defaultWindowInsetsPadding = false,
                    )
                }
            }
        }
        // A row keeps its identity for the whole flight: the morph only changes its layout and its
        // icon's draw-phase alpha, so the destination is never recreated on the way to the capsule.
        val before = onNodeWithText("Library").fetchSemanticsNode().id
        mainClock.autoAdvance = false
        runOnIdle { onNodeWithContentDescription(GlassNavigationRailDefaults.CollapseContentDescription).performClick() }
        repeat(6) {
            mainClock.advanceTimeBy(50)
            assertEquals(before, onNodeWithText("Library").fetchSemanticsNode().id)
        }
        mainClock.autoAdvance = true
        mainClock.advanceTimeBy(2000)
        waitForIdle()

        // The destination retains its tab semantics when the morph reaches the capsule.
        onNodeWithContentDescription("Library").assertIsSelected()
    }

    @Test
    fun draggingTheCollapsedCapsuleFollowsThePointer() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        var testDensity = Density(1f)
        var selected by mutableIntStateOf(0)
        val selections = mutableListOf<Int>()
        setContent {
            testDensity = LocalDensity.current
            DialogTestHost {
                Box(Modifier.size(800.dp, 600.dp)) {
                    GlassNavigationRail(
                        items = items,
                        selectedIndex = selected,
                        onSelect = {
                            selected = it
                            selections += it
                        },
                        backdrop = null,
                        state = state,
                        defaultWindowInsetsPadding = false,
                    )
                }
            }
        }
        onNodeWithContentDescription(GlassNavigationRailDefaults.CollapseContentDescription)
            .performClick()
        waitForIdle()

        val home = onNodeWithText("Home").getUnclippedBoundsInRoot()
        val settings = onNodeWithText("Settings").getUnclippedBoundsInRoot()
        val homeCentre = with(testDensity) {
            Offset((home.left + home.right).toPx() / 2f, (home.top + home.bottom).toPx() / 2f)
        }
        val settingsCentre = with(testDensity) {
            Offset((settings.left + settings.right).toPx() / 2f, (settings.top + settings.bottom).toPx() / 2f)
        }
        onRoot().performTouchInput {
            down(homeCentre)
            moveTo(settingsCentre)
            up()
        }
        waitForIdle()

        // A horizontal drag commits the destination under the pointer only on release.
        assertEquals(listOf(2), selections)
        assertEquals(2, selected)
    }

    @Test
    fun theExpandedColumnScrollsToADestinationBelowThePanel() = runComposeUiTest {
        val many = List(14) { GlassNavigationItem(MiuixIcons.Basic.Search, "Item $it") }
        setContent {
            DialogTestHost {
                Box(Modifier.size(800.dp, 360.dp)) {
                    GlassNavigationRail(
                        items = many,
                        selectedIndex = 0,
                        onSelect = {},
                        backdrop = null,
                        defaultWindowInsetsPadding = false,
                    )
                }
            }
        }
        val last = onNodeWithText("Item 13")
        assertTrue(last.getUnclippedBoundsInRoot().top > 360.dp)

        last.performScrollTo()
        waitForIdle()

        val bounds = last.getUnclippedBoundsInRoot()
        assertTrue(bounds.bottom <= 360.dp, "the last destination ended at ${bounds.bottom}")
    }

    @Test
    fun compressedCollapsedLabelsKeepTextLayoutWithinTheirSlots() = runComposeUiTest {
        val longItems = List(3) { GlassNavigationItem(MiuixIcons.Basic.Search, "Destination number $it") }
        val state = GlassNavigationRailState(initialExpanded = false)
        setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f)) {
                DialogTestHost {
                    Box(Modifier.size(600.dp, 360.dp)) {
                        GlassNavigationRail(
                            items = longItems,
                            selectedIndex = 0,
                            onSelect = {},
                            backdrop = null,
                            state = state,
                            defaultWindowInsetsPadding = false,
                            labelStyle = TextStyle(fontFamily = FontFamily.Monospace, fontSize = 16.sp),
                        )
                    }
                }
            }
        }
        for (expanded in listOf(false, true, false)) {
            runOnIdle { state.expanded = expanded }
            waitForIdle()
            saveImage("rail-compressed-labels-$expanded", onRoot().captureToImage())
            longItems.forEach { item ->
                val label = checkNotNull(item.label)
                val textNode = onNodeWithText(label, useUnmergedTree = true).fetchSemanticsNode()
                val layouts = mutableListOf<TextLayoutResult>()
                checkNotNull(textNode.config.getOrNull(SemanticsActions.GetTextLayoutResult)).action?.invoke(layouts)
                val textLayout = layouts.single()
                val slot = onNodeWithContentDescription(label).fetchSemanticsNode().boundsInRoot
                assertTrue(
                    textLayout.multiParagraph.intrinsics.maxIntrinsicWidth > textLayout.size.width,
                    "$label must exercise overflow in its available text width",
                )
                assertTrue(
                    textLayout.size.width <= slot.width,
                    "$label was laid out beyond its visible slot: ${textLayout.size.width} > ${slot.width}",
                )
            }
        }
        saveImage("rail-compressed-labels", onRoot().captureToImage())
    }

    @Test
    fun aFlyingLabelIsNeverNarrowerThanItsTextOnAFractionalDensity() = runDesktopComposeUiTest(width = 2800, height = 1700) {
        val state = GlassNavigationRailState(initialExpanded = false)
        setContent {
            CompositionLocalProvider(LocalDensity provides Density(2.75f)) {
                DialogTestHost {
                    Box(Modifier.size(1000.dp, 600.dp)) {
                        GlassNavigationRail(
                            items = items,
                            selectedIndex = 0,
                            onSelect = {},
                            backdrop = null,
                            state = state,
                            defaultWindowInsetsPadding = false,
                        )
                    }
                }
            }
        }
        waitForIdle()
        mainClock.autoAdvance = false
        // The row's padding and icon column round to whole pixels; if its starting width did not,
        // the label would start a pixel short and show an ellipsis until the row had widened.
        for (expand in listOf(true, false)) {
            runOnIdle { state.expanded = expand }
            repeat(90) { frame ->
                mainClock.advanceTimeByFrame()
                listOf("Home", "Library", "Settings").forEach { label ->
                    onAllNodesWithText(label, useUnmergedTree = true).fetchSemanticsNodes().forEach { node ->
                        val layouts = mutableListOf<TextLayoutResult>()
                        node.config.getOrNull(SemanticsActions.GetTextLayoutResult)?.action?.invoke(layouts)
                        val layout = layouts.firstOrNull() ?: return@forEach
                        val needed = ceil(layout.multiParagraph.intrinsics.maxIntrinsicWidth).toInt()
                        assertTrue(
                            layout.layoutInput.constraints.maxWidth >= needed,
                            "$label had ${layout.layoutInput.constraints.maxWidth}px of $needed on frame $frame",
                        )
                    }
                }
            }
        }
    }
}
