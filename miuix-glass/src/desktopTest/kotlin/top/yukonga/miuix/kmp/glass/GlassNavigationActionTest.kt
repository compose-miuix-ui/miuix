// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Search
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassNavigationActionTest {

    @Test
    fun glassBarSwitchAnimatesCapsuleWidth() = runComposeUiTest {
        val showSearch = mutableStateOf(true)
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(Modifier.size(410.dp, 150.dp)) {
                    Box(Modifier.fillMaxSize().layerBackdrop(backdrop).background(Color.White))
                    GlassNavigationBar(
                        items = listOf(
                            GlassNavigationItem(MiuixIcons.Basic.Search, "Home"),
                            GlassNavigationItem(MiuixIcons.Basic.Search, "Gallery"),
                        ),
                        selectedIndex = 0,
                        onSelect = {},
                        backdrop = backdrop,
                        defaultWindowInsetsPadding = false,
                        trailingAction = if (showSearch.value) {
                            {
                                GlassIconButton(
                                    onClick = {},
                                    backdrop = backdrop,
                                    size = GlassNavigationBarDefaults.Height,
                                ) { Icon(MiuixIcons.Basic.Search, contentDescription = "Search") }
                            }
                        } else {
                            null
                        },
                    )
                }
            }
        }
        fun width() = onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.SelectableGroup))
            .fetchSemanticsNode().boundsInRoot.width
        val initial = width()
        mainClock.autoAdvance = false
        runOnIdle { showSearch.value = false }
        mainClock.advanceTimeByFrame()
        mainClock.advanceTimeBy(80)
        val middle = width()
        mainClock.advanceTimeBy(1500)
        val final = width()
        assertTrue(initial < middle && middle < final, "The capsule must expand through an intermediate width")
    }

    @Test
    fun actionRemovalAnimatesReservedWidthAndCanReverse() = runComposeUiTest {
        val showAction = mutableStateOf(true)
        setContent {
            GlassNavigationBarLayout(
                modifier = Modifier.width(360.dp),
                trailingAction = if (showAction.value) {
                    { Box(Modifier.size(54.dp).testTag("animated-action")) }
                } else {
                    null
                },
            ) {
                Box(Modifier.size(360.dp, 54.dp).testTag("animated-capsule"))
            }
        }
        fun capsuleWidth() = onNodeWithTag("animated-capsule").fetchSemanticsNode().boundsInRoot.width
        val initial = capsuleWidth()
        mainClock.autoAdvance = false
        runOnIdle { showAction.value = false }
        mainClock.advanceTimeByFrame()
        mainClock.advanceTimeBy(80)
        val middle = capsuleWidth()
        assertTrue(middle > initial, "The capsule must expand during action exit")
        onNodeWithTag("animated-action").assertDoesNotExist()
        runOnIdle { showAction.value = true }
        mainClock.advanceTimeBy(1500)
        assertEquals(initial, capsuleWidth(), 1f)
        runOnIdle { showAction.value = false }
        mainClock.advanceTimeBy(1500)
        assertTrue(capsuleWidth() > middle, "The capsule must finish expanding after exit")
        onNodeWithTag("animated-action").assertDoesNotExist()
    }

    @Test
    fun windowInsetPaddingMovesTheWholeGroupWithoutStretchingItsSurfaces() = runComposeUiTest {
        val applyInsets = mutableStateOf(true)
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(Modifier.size(410.dp, 180.dp).testTag("scene")) {
                    Box(Modifier.fillMaxSize().layerBackdrop(backdrop).background(Color.White))
                    GlassNavigationBar(
                        items = listOf(GlassNavigationItem(MiuixIcons.Basic.Search, "Home")),
                        selectedIndex = 0,
                        onSelect = {},
                        backdrop = backdrop,
                        modifier = Modifier.align(Alignment.BottomCenter),
                        defaultWindowInsetsPadding = applyInsets.value,
                        trailingAction = {
                            GlassIconButton(
                                onClick = {},
                                backdrop = backdrop,
                                size = GlassNavigationBarDefaults.Height,
                                modifier = Modifier.testTag("action"),
                            ) { Icon(MiuixIcons.Basic.Search, contentDescription = "Search") }
                        },
                    )
                }
            }
        }
        val scene = onNodeWithTag("scene").fetchSemanticsNode().boundsInRoot
        val insetBar = onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.SelectableGroup))
            .fetchSemanticsNode().boundsInRoot
        val insetAction = onNodeWithTag("action").fetchSemanticsNode().boundsInRoot
        assertEquals(insetBar.center.y, insetAction.center.y, 1f)
        saveImage("navigation-insets-enabled", onRoot().captureToImage())

        runOnIdle { applyInsets.value = false }
        val bareBar = onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.SelectableGroup))
            .fetchSemanticsNode().boundsInRoot
        val bareAction = onNodeWithTag("action").fetchSemanticsNode().boundsInRoot
        assertTrue(insetAction.bottom < bareAction.bottom, "Default padding must lift the group above the bottom edge")
        assertEquals(scene.bottom, bareAction.bottom, 1f)
        assertEquals(bareBar.center.y, bareAction.center.y, 1f)
        assertEquals(insetBar.size, bareBar.size)
        assertEquals(insetAction.size, bareAction.size)
        saveImage("navigation-insets-disabled", onRoot().captureToImage())
    }

    @Test
    fun glassBarAndActionAreCenteredTogether() = assertGlassLayout(LayoutDirection.Ltr)

    @Test
    fun glassBarAndActionAreCenteredTogetherInRtl() = assertGlassLayout(LayoutDirection.Rtl)

    private fun assertGlassLayout(direction: LayoutDirection) = runComposeUiTest {
        var searches = 0
        setContent {
            DialogTestHost {
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    val backdrop = rememberLayerBackdrop()
                    Box(Modifier.size(800.dp, 180.dp).testTag("scene")) {
                        Box(Modifier.fillMaxSize().layerBackdrop(backdrop).background(Color.White))
                        GlassNavigationBar(
                            items = List(3) { GlassNavigationItem(MiuixIcons.Basic.Search, "Tab $it") },
                            selectedIndex = 0,
                            onSelect = {},
                            backdrop = backdrop,
                            modifier = Modifier.width(410.dp).align(Alignment.Center),
                            defaultWindowInsetsPadding = false,
                            trailingAction = {
                                GlassIconButton(
                                    onClick = { searches++ },
                                    backdrop = backdrop,
                                    size = GlassNavigationBarDefaults.Height,
                                    modifier = Modifier.testTag("action"),
                                ) { Icon(MiuixIcons.Basic.Search, contentDescription = "Search") }
                            },
                        )
                    }
                }
            }
        }
        val scene = onNodeWithTag("scene").fetchSemanticsNode().boundsInRoot
        val bar = onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.SelectableGroup))
            .fetchSemanticsNode().boundsInRoot
        val action = onNodeWithTag("action").fetchSemanticsNode().boundsInRoot
        val gap = with(density) { 12.dp.toPx() }
        assertEquals(scene.center.x, (minOf(bar.left, action.left) + maxOf(bar.right, action.right)) / 2f, 1f)
        assertEquals(bar.center.y, action.center.y, 1f)
        if (direction == LayoutDirection.Ltr) {
            assertEquals(gap, action.left - bar.right, 1f)
        } else {
            assertEquals(gap, bar.left - action.right, 1f)
        }
        onNodeWithTag("action").performTouchInput { click() }
        assertEquals(1, searches)
    }

    @Test
    fun glassActionIsIndependentAndCanBeRemovedAtRuntime() = runComposeUiTest {
        val showAction = mutableStateOf(true)
        val selected = mutableIntStateOf(1)
        var searches = 0
        var selections = 0
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(Modifier.size(410.dp, 150.dp).background(Color.White)) {
                    Box(Modifier.fillMaxSize().layerBackdrop(backdrop).background(Color.White))
                    GlassNavigationBar(
                        items = listOf(
                            GlassNavigationItem(MiuixIcons.Basic.Search, "Home"),
                            GlassNavigationItem(MiuixIcons.Basic.Search, "Files"),
                        ),
                        selectedIndex = selected.intValue,
                        onSelect = {
                            selected.intValue = it
                            selections++
                        },
                        backdrop = backdrop,
                        modifier = Modifier.testTag("group"),
                        defaultWindowInsetsPadding = false,
                        trailingAction = if (showAction.value) {
                            {
                                GlassIconButton(
                                    onClick = { searches++ },
                                    backdrop = backdrop,
                                    size = GlassNavigationBarDefaults.Height,
                                    modifier = Modifier.testTag("action"),
                                ) { Icon(MiuixIcons.Basic.Search, contentDescription = "Search") }
                            }
                        } else {
                            null
                        },
                    )
                }
            }
        }
        waitForIdle()
        val before = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        onNodeWithTag("action").performTouchInput { click() }
        assertEquals(1, searches)
        assertEquals(0, selections, "The action must be outside the destination gesture detector")
        onNodeWithContentDescription("Files").assertIsSelected()
        saveImage("navigation-action", onRoot().captureToImage())

        runOnIdle { showAction.value = false }
        onNodeWithTag("action").assertDoesNotExist()
        onNodeWithContentDescription("Files").assertIsSelected()
        val after = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot
        assertTrue(after.width > before.width, "Removing the action must return its width and gap to the navigation")
        onNodeWithContentDescription("Home").performTouchInput { click() }
        assertEquals(0, selected.intValue)

        runOnIdle { showAction.value = true }
        onNodeWithContentDescription("Home").assertIsSelected()
        onNodeWithTag("action").performTouchInput { click() }
        assertEquals(2, searches)
    }

    @Test
    fun draggedIndicatorSettlesAfterProfileAddsAnAction() = assertDragResizes(LayoutDirection.Ltr, 0, 2)

    @Test
    fun draggedIndicatorSettlesAfterLeavingProfileRemovesAnAction() = assertDragResizes(LayoutDirection.Ltr, 2, 0)

    @Test
    fun draggedIndicatorSettlesAfterProfileAddsAnActionInRtl() = assertDragResizes(LayoutDirection.Rtl, 0, 2)

    @Test
    fun draggedIndicatorSettlesAfterLeavingProfileRemovesAnActionInRtl() = assertDragResizes(LayoutDirection.Rtl, 2, 0)

    private fun assertDragResizes(direction: LayoutDirection, from: Int, to: Int) = runComposeUiTest {
        val selected = mutableIntStateOf(from)
        val labels = listOf("Home", "Files", "Profile")
        setContent {
            DialogTestHost {
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Box(Modifier.size(410.dp, 150.dp).background(Color.White)) {
                        GlassNavigationBar(
                            items = labels.map { GlassNavigationItem(MiuixIcons.Basic.Search, it) },
                            selectedIndex = selected.intValue,
                            onSelect = { selected.intValue = it },
                            backdrop = null,
                            defaultWindowInsetsPadding = false,
                            indicatorColor = Color.Magenta,
                            indicatorPressedColor = Color.Magenta,
                            stroke = null,
                            shadow = null,
                            trailingAction = if (selected.intValue == 2) {
                                { Box(Modifier.size(GlassNavigationBarDefaults.Height).background(Color.Green)) }
                            } else {
                                null
                            },
                        )
                    }
                }
            }
        }
        val start = onNodeWithContentDescription(labels[from]).fetchSemanticsNode().boundsInRoot.center
        val middle = onNodeWithContentDescription(labels[1]).fetchSemanticsNode().boundsInRoot.center
        val end = onNodeWithContentDescription(labels[to]).fetchSemanticsNode().boundsInRoot.center
        onRoot().performTouchInput {
            down(start)
            moveTo(middle)
            moveTo(end)
        }
        waitForIdle()
        assertEquals(from, selected.intValue, "Dragging must not navigate before release")
        // Release commits selection; the indicator must settle after the resulting resize.
        onRoot().performTouchInput { up() }
        waitForIdle()
        assertEquals(to, selected.intValue)
        val destination = onNodeWithContentDescription(labels[to]).fetchSemanticsNode().boundsInRoot
        val pixels = onRoot().captureToImage().toPixelMap()
        val row = destination.center.y.roundToInt()
        val indicatorPixels = (0 until pixels.width).filter {
            val color = pixels[it, row]
            color.red > 0.9f && color.blue > 0.9f && color.green < 0.1f
        }
        assertTrue(indicatorPixels.isNotEmpty(), "The selection indicator must remain visible")
        val center = (indicatorPixels.first() + indicatorPixels.last()) / 2f
        assertEquals(destination.center.x, center, 2f, "The released indicator must align with the resized destination")
    }

    @Test
    fun profileBoundaryStaysStableWhenDraggingFromHistory() = assertBoundaryStable(LayoutDirection.Ltr, 4, 3)

    @Test
    fun historyBoundaryStaysStableWhenDraggingFromProfile() = assertBoundaryStable(LayoutDirection.Ltr, 3, 4)

    @Test
    fun profileBoundaryStaysStableWhenDraggingFromHistoryInRtl() = assertBoundaryStable(LayoutDirection.Rtl, 4, 3)

    @Test
    fun historyBoundaryStaysStableWhenDraggingFromProfileInRtl() = assertBoundaryStable(LayoutDirection.Rtl, 3, 4)

    private fun assertBoundaryStable(direction: LayoutDirection, from: Int, to: Int) = runComposeUiTest {
        val selected = mutableIntStateOf(from)
        val selections = mutableListOf<Int>()
        val labels = listOf("Home", "Files", "Search", "Profile", "History")
        setContent {
            DialogTestHost {
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Box(Modifier.size(410.dp, 150.dp)) {
                        GlassNavigationBar(
                            items = labels.map { GlassNavigationItem(MiuixIcons.Basic.Search, it) },
                            selectedIndex = selected.intValue,
                            onSelect = {
                                selected.intValue = it
                                selections.add(it)
                            },
                            backdrop = null,
                            defaultWindowInsetsPadding = false,
                            trailingAction = if (selected.intValue == 3) {
                                { Box(Modifier.size(GlassNavigationBarDefaults.Height)) }
                            } else {
                                null
                            },
                        )
                    }
                }
            }
        }
        val bar = onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.SelectableGroup)).fetchSemanticsNode().boundsInRoot
        val start = onNodeWithContentDescription(labels[from]).fetchSemanticsNode().boundsInRoot.center
        val inset = with(density) { GlassNavigationBarDefaults.ContentPaddingHorizontal.toPx() }
        val actionSpace = with(density) { (GlassNavigationBarDefaults.Height + GlassNavigationBarDefaults.ActionSpacing).toPx() }
        val wide = bar.width + if (from == 3) actionSpace else 0f
        val narrow = wide - actionSpace
        // Between the wide and narrow Profile/History boundaries: resizing alone used to
        // switch destinations on every subsequent pointer event, repeatedly toggling the action.
        val logicalX = inset + ((wide + narrow) / 2f - inset * 2f) * 4f / 5f
        val boundary = Offset(if (direction == LayoutDirection.Ltr) bar.left + logicalX else bar.right - logicalX, start.y)
        onRoot().performTouchInput {
            down(start)
            moveTo(boundary)
        }
        waitForIdle()
        assertEquals(from, selected.intValue)
        repeat(8) { step ->
            onRoot().performTouchInput { moveTo(boundary + Offset(if (step % 2 == 0) 0.5f else -0.5f, 0f)) }
            waitForIdle()
            assertEquals(from, selected.intValue, "Pointer movement must not commit selection")
        }
        assertTrue(selections.isEmpty(), "No destination callbacks are allowed while held")
        onRoot().performTouchInput { up() }
        assertEquals(listOf(to), selections, "Release must commit exactly one destination")
        onNodeWithContentDescription(labels[to]).assertIsSelected()
    }

    @Test
    fun cancelledDragDoesNotNavigate() = runComposeUiTest {
        val selections = mutableListOf<Int>()
        setContent {
            DialogTestHost {
                Box(Modifier.size(400.dp, 100.dp)) {
                    GlassNavigationBar(
                        items = listOf("Home", "Files", "Profile").map { GlassNavigationItem(MiuixIcons.Basic.Search, it) },
                        selectedIndex = 0,
                        onSelect = { selections.add(it) },
                        backdrop = null,
                        defaultWindowInsetsPadding = false,
                    )
                }
            }
        }
        val start = onNodeWithContentDescription("Home").fetchSemanticsNode().boundsInRoot.center
        val end = onNodeWithContentDescription("Profile").fetchSemanticsNode().boundsInRoot.center
        onRoot().performTouchInput {
            down(start)
            moveTo(end)
        }
        assertTrue(selections.isEmpty())
        onRoot().performTouchInput { cancel() }
        assertTrue(selections.isEmpty(), "Cancelling must not commit the previewed destination")
        onNodeWithContentDescription("Home").assertIsSelected()
    }

    @Test
    fun narrowGlassBarKeepsActionAndWrappedLabelsInsideTheGroup() = runComposeUiTest {
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(Modifier.size(280.dp, 180.dp).background(Color.White)) {
                    Box(Modifier.fillMaxSize().layerBackdrop(backdrop).background(Color.White))
                    GlassNavigationBar(
                        items = List(4) { GlassNavigationItem(MiuixIcons.Basic.Search, "Long destination $it") },
                        selectedIndex = 0,
                        onSelect = {},
                        backdrop = backdrop,
                        modifier = Modifier.testTag("group"),
                        defaultWindowInsetsPadding = false,
                        trailingAction = {
                            GlassIconButton(
                                onClick = {},
                                backdrop = backdrop,
                                size = GlassNavigationBarDefaults.Height,
                                modifier = Modifier.testTag("action"),
                            ) { Icon(MiuixIcons.Basic.Search, contentDescription = "Search") }
                        },
                    )
                }
            }
        }
        val group = onNodeWithTag("group").fetchSemanticsNode().boundsInRoot
        val action = onNodeWithTag("action").fetchSemanticsNode().boundsInRoot
        val lastTab = onNodeWithContentDescription("Long destination 3").fetchSemanticsNode().boundsInRoot
        assertTrue(lastTab.right <= action.left - with(density) { 12.dp.toPx() })
        assertTrue(action.right <= group.right)
        assertEquals(group.center.y, action.center.y, 1f)
        saveImage("navigation-action-narrow", onRoot().captureToImage())
    }
}
