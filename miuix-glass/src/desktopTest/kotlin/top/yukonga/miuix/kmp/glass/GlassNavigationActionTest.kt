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
import androidx.compose.ui.graphics.Color
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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassNavigationActionTest {

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
