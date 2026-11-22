// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toPixelMap
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
import androidx.compose.ui.test.onNodeWithTag
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
    fun collapsedCapsuleStaysCentredWhenTheDensityChanges() = runComposeUiTest {
        var scale by mutableFloatStateOf(1f)
        setContent {
            DialogTestHost {
                val outer = LocalDensity.current
                // The container keeps its pixel size while the density changes, as with an app-wide UI scale.
                Box(Modifier.size(800.dp, 600.dp).testTag("container")) {
                    CompositionLocalProvider(LocalDensity provides Density(outer.density * scale, outer.fontScale)) {
                        GlassNavigationRail(
                            items = items,
                            selectedIndex = 0,
                            onSelect = {},
                            backdrop = null,
                            state = remember { GlassNavigationRailState(initialExpanded = false) },
                            defaultWindowInsetsPadding = false,
                        )
                    }
                }
            }
        }
        fun capsuleCentreOffset(): Float {
            val container = onNodeWithTag("container").fetchSemanticsNode().boundsInRoot
            val toggle = onNodeWithContentDescription(GlassNavigationRailDefaults.ExpandContentDescription)
                .fetchSemanticsNode().boundsInRoot
            val last = onNodeWithContentDescription("Settings").fetchSemanticsNode().boundsInRoot
            return container.center.x - (toggle.left + last.right) / 2
        }
        waitForIdle()
        val before = capsuleCentreOffset()
        scale = 0.8f
        waitForIdle()
        // The capsule's chrome is asymmetric, but in its own dp the offset from the centre is constant.
        assertEquals(before, capsuleCentreOffset() / 0.8f, 1.5f)
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
    }

    @Test
    fun flyingLabelsNeverTruncateOnFractionalDensity() = runDesktopComposeUiTest(width = 1100, height = 825) {
        val secondary = listOf(
            GlassNavigationItem(MiuixIcons.Basic.Search, "Preferences"),
            GlassNavigationItem(MiuixIcons.Basic.Search, "Glass"),
        )
        val state = GlassNavigationRailState(initialExpanded = false)
        setContent {
            CompositionLocalProvider(LocalDensity provides Density(2.75f)) {
                DialogTestHost {
                    Box(Modifier.size(400.dp, 300.dp)) {
                        GlassNavigationRail(
                            items = items,
                            secondaryItems = secondary,
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
            repeat(30) { frame ->
                mainClock.advanceTimeByFrame()
                listOf("Home", "Library", "Settings", "Preferences", "Glass").forEach { label ->
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
    fun secondaryItemsAndDividerFollowTrailingMorphDuringExpandAndCollapse() = runDesktopComposeUiTest(width = 1067, height = 711) {
        val primary = listOf(
            GlassNavigationItem(MiuixIcons.Basic.Search, "Home"),
            GlassNavigationItem(MiuixIcons.Basic.Search, "Icon"),
            GlassNavigationItem(MiuixIcons.Basic.Search, "Color"),
            GlassNavigationItem(MiuixIcons.Basic.Search, "TextStyle"),
            GlassNavigationItem(MiuixIcons.Basic.Search, "Settings"),
        )
        val secondary = listOf(
            GlassNavigationItem(MiuixIcons.Basic.Search, "Preferences"),
            GlassNavigationItem(MiuixIcons.Basic.Search, "Glass"),
        )
        val state = GlassNavigationRailState(initialExpanded = false)
        setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f)) {
                DialogTestHost {
                    Box(Modifier.size(1067.dp, 711.dp)) {
                        GlassNavigationRail(
                            items = primary,
                            secondaryItems = secondary,
                            selectedIndex = 0,
                            onSelect = {},
                            backdrop = null,
                            state = state,
                            defaultWindowInsetsPadding = false,
                            stroke = null,
                            shadow = null,
                        )
                    }
                }
            }
        }
        waitForIdle()
        assertEquals(0, onAllNodesWithText("Preferences", useUnmergedTree = true).fetchSemanticsNodes().size)

        mainClock.autoAdvance = false
        runOnIdle { state.expanded = true }
        mainClock.advanceTimeBy(100)

        val prefId = onNodeWithText("Preferences", useUnmergedTree = true).fetchSemanticsNode().id
        val glassId = onNodeWithText("Glass", useUnmergedTree = true).fetchSemanticsNode().id
        val homeExpand100 = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        val settingsExpand100 = onNodeWithContentDescription("Settings").fetchSemanticsNode().boundsInRoot
        val prefExpand100 = onNodeWithContentDescription("Preferences").fetchSemanticsNode().boundsInRoot
        val glassExpand100 = onNodeWithContentDescription("Glass").fetchSemanticsNode().boundsInRoot
        assertTrue(
            prefExpand100.left >= settingsExpand100.left && glassExpand100.left >= prefExpand100.left,
            "Secondary items must trail Settings at 100ms expand: home=${homeExpand100.left}, settings=${settingsExpand100.left}, pref=${prefExpand100.left}, glass=${glassExpand100.left}",
        )
        val img100 = onRoot().captureToImage()
        val dividerSpan100 = findDividerHorizontalSpan(
            pixels = img100.toPixelMap(),
            yMin = settingsExpand100.center.y.toInt() + 12,
            yMax = prefExpand100.center.y.toInt() - 12,
        )
        assertTrue(
            dividerSpan100 != null && dividerSpan100.first > homeExpand100.left + 40f,
            "Divider must trail away from Home at 100ms expand: span=$dividerSpan100, homeLeft=${homeExpand100.left}",
        )
        assertTrue(
            dividerSpan100.last - dividerSpan100.first < 200,
            "Divider must expand from trailing slot width, not capsule width: span=$dividerSpan100",
        )

        mainClock.advanceTimeBy(80)
        assertEquals(prefId, onNodeWithText("Preferences", useUnmergedTree = true).fetchSemanticsNode().id)
        assertEquals(glassId, onNodeWithText("Glass", useUnmergedTree = true).fetchSemanticsNode().id)
        val settingsExpand180 = onNodeWithContentDescription("Settings").fetchSemanticsNode().boundsInRoot
        val prefExpand180 = onNodeWithContentDescription("Preferences").fetchSemanticsNode().boundsInRoot
        val glassExpand180 = onNodeWithContentDescription("Glass").fetchSemanticsNode().boundsInRoot
        assertTrue(
            prefExpand180.left >= settingsExpand180.left && glassExpand180.left >= prefExpand180.left,
            "Secondary items must keep trailing Settings at 180ms expand: settings=${settingsExpand180.left}, pref=${prefExpand180.left}, glass=${glassExpand180.left}",
        )

        mainClock.autoAdvance = true
        mainClock.advanceTimeBy(2000)
        waitForIdle()
        val homeSettled = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        val prefSettled = onNodeWithContentDescription("Preferences").fetchSemanticsNode().boundsInRoot
        assertEquals(homeSettled.left, prefSettled.left, 1f)

        mainClock.autoAdvance = false
        runOnIdle { state.expanded = false }
        mainClock.advanceTimeBy(80)
        val homeTextCollapse = onNodeWithText("Home", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val settingsTextCollapse = onNodeWithText("Settings", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val prefTextCollapse = onNodeWithText("Preferences", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        val glassTextCollapse = onNodeWithText("Glass", useUnmergedTree = true).fetchSemanticsNode().boundsInRoot
        assertTrue(
            prefTextCollapse.left > homeTextCollapse.left + 40f &&
                glassTextCollapse.left > homeTextCollapse.left + 40f &&
                prefTextCollapse.left >= settingsTextCollapse.left - 8f &&
                glassTextCollapse.left >= settingsTextCollapse.left - 8f,
            "Secondary labels must stay aligned with Settings during collapse: home=${homeTextCollapse.left}, settings=${settingsTextCollapse.left}, pref=${prefTextCollapse.left}, glass=${glassTextCollapse.left}",
        )

        mainClock.autoAdvance = true
        mainClock.advanceTimeBy(2000)
        waitForIdle()
        assertEquals(0, onAllNodesWithText("Preferences", useUnmergedTree = true).fetchSemanticsNodes().size)
        assertEquals(0, onAllNodesWithText("Glass", useUnmergedTree = true).fetchSemanticsNodes().size)
    }

    @Test
    fun secondaryItemSelectionAndCollapseSettleCleanly() = runComposeUiTest {
        val secondary = listOf(
            GlassNavigationItem(MiuixIcons.Basic.Search, "Preferences"),
            GlassNavigationItem(MiuixIcons.Basic.Search, "Glass"),
        )
        val state = GlassNavigationRailState(initialExpanded = true)
        var selected by mutableIntStateOf(0)
        setContent {
            DialogTestHost {
                Box(Modifier.size(800.dp, 600.dp)) {
                    GlassNavigationRail(
                        items = items,
                        secondaryItems = secondary,
                        selectedIndex = selected,
                        onSelect = { selected = it },
                        backdrop = null,
                        state = state,
                        defaultWindowInsetsPadding = false,
                    )
                }
            }
        }
        onNodeWithContentDescription("Preferences").performClick()
        waitForIdle()
        assertEquals(items.size, selected)
        onNodeWithContentDescription("Preferences").assertIsSelected()

        runOnIdle { state.expanded = false }
        waitForIdle()
        assertEquals(0, onAllNodesWithText("Preferences", useUnmergedTree = true).fetchSemanticsNodes().size)
        onNodeWithContentDescription("Home").assertIsSelected()
    }

    private fun findDividerHorizontalSpan(
        pixels: androidx.compose.ui.graphics.PixelMap,
        yMin: Int,
        yMax: Int,
    ): IntRange? {
        var bestSpan: IntRange? = null
        val topY = yMin.coerceAtLeast(2)
        val botY = yMax.coerceAtMost(pixels.height - 1)
        for (y in topY..botY) {
            var minX = Int.MAX_VALUE
            var maxX = Int.MIN_VALUE
            var count = 0
            var maxDiff = 0f
            for (x in 0 until pixels.width) {
                val p = pixels[x, y]
                val ref = pixels[x, y - 2]
                val diff = ref.red - p.red
                if (diff > maxDiff) maxDiff = diff
                if (diff > 0.005f && ref.green - p.green > 0.005f && ref.blue - p.blue > 0.005f) {
                    if (x < minX) minX = x
                    if (x > maxX) maxX = x
                    count++
                }
            }
            if (count > 40 && maxDiff < 0.05f && (bestSpan == null || count > bestSpan.last - bestSpan.first)) {
                bestSpan = minX..maxX
            }
        }
        return bestSpan
    }
}
