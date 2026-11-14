// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.onAllNodesWithContentDescription
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
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Search
import kotlin.math.ceil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
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

        // Settled, the capsule is the navigation bar itself, so the destination is reachable by the
        // bar's own semantics: a tab role with its name as the description.
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

        // The capsule is the bar, so dragging across it commits the destination under the pointer
        // on release, exactly as the bar's own contract says.
        assertEquals(listOf(2), selections)
        assertEquals(2, selected)
    }

    @Test
    fun theCollapsedCapsuleLandsWhereTheMorphLeavesIt() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        setContent {
            DialogTestHost {
                Box(Modifier.size(800.dp, 600.dp)) {
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
        // Sample the label through the whole collapse: the panel hands the rail over to the bar at
        // the very end, so any disagreement between their two geometries would leave the capsule
        // slightly off where the morph put it.
        val samples = mutableListOf<DpRect>()
        mainClock.autoAdvance = false
        runOnIdle { state.expanded = false }
        repeat(100) {
            mainClock.advanceTimeBy(16)
            samples += onNodeWithText("Home", useUnmergedTree = true).getUnclippedBoundsInRoot()
        }
        mainClock.autoAdvance = true
        mainClock.advanceTimeBy(2000)
        waitForIdle()
        val settled = onNodeWithText("Home", useUnmergedTree = true).getUnclippedBoundsInRoot()

        // Everything the morph settled on must survive the hand-off unchanged.
        val lastMorphFrame = samples.last()
        val dx = (lastMorphFrame.left - settled.left).value
        val dy = (lastMorphFrame.top - settled.top).value
        assertTrue(
            (if (dx < 0) -dx else dx) < 0.5f && (if (dy < 0) -dy else dy) < 0.5f,
            "the hand-off moved the capsule from $lastMorphFrame to $settled",
        )
    }

    @Test
    fun theToggleLandsWhereTheMorphLeavesIt() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        setContent {
            DialogTestHost {
                Box(Modifier.size(800.dp, 600.dp)) {
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
        // Collapsing, the panel's toggle already describes the next action, so the same node is
        // found before and after the bar takes over.
        val samples = mutableListOf<DpRect>()
        mainClock.autoAdvance = false
        runOnIdle { state.expanded = false }
        repeat(100) {
            mainClock.advanceTimeBy(16)
            samples += onNodeWithContentDescription(GlassNavigationRailDefaults.ExpandContentDescription)
                .getUnclippedBoundsInRoot()
        }
        mainClock.autoAdvance = true
        mainClock.advanceTimeBy(2000)
        waitForIdle()
        val settled = onNodeWithContentDescription(GlassNavigationRailDefaults.ExpandContentDescription)
            .getUnclippedBoundsInRoot()

        val lastMorphFrame = samples.last()
        val dx = (lastMorphFrame.left - settled.left).value
        val dy = (lastMorphFrame.top - settled.top).value
        assertTrue(
            (if (dx < 0) -dx else dx) < 0.5f && (if (dy < 0) -dy else dy) < 0.5f,
            "the hand-off moved the toggle from $lastMorphFrame to $settled",
        )
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

    @Test
    fun theBarDrawsTheIndicatorOnTheFrameItTakesOver() = runComposeUiTest {
        val state = GlassNavigationRailState(initialExpanded = true)
        setContent {
            DialogTestHost {
                Box(Modifier.size(800.dp, 500.dp)) {
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
        waitForIdle()
        mainClock.autoAdvance = false
        runOnIdle { state.expanded = false }
        // The settled bar exposes its destinations by description; the panel's rows do not. The
        // first frame one appears is the hand-off.
        var handOff: ImageBitmap? = null
        repeat(240) {
            if (handOff != null) return@repeat
            mainClock.advanceTimeByFrame()
            if (onAllNodesWithContentDescription("Library").fetchSemanticsNodes().isNotEmpty()) {
                handOff = onRoot().captureToImage()
            }
        }
        val first = assertNotNull(handOff, "the bar never took over").toPixelMap()
        mainClock.autoAdvance = true
        mainClock.advanceTimeBy(2000)
        waitForIdle()
        val settled = onRoot().captureToImage().toPixelMap()

        // Just inside the selected slot, beside its label: the indicator's fill, not the capsule's.
        val label = onNodeWithText("Library", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val x = (label.left - 4f).toInt()
        val y = label.center.y.toInt()
        assertEquals(settled[x, y], first[x, y], "the indicator was missing on the hand-off frame")
    }
}
