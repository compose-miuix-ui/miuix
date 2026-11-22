// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class TopAppBarTitleAlignmentTest {

    @Test
    fun shortTitleAndSubtitleStayCenteredInCollapsedTopAppBar() = runComposeUiTest {
        val state = TopAppBarState(-1000f, -1000f, -1000f)
        setContent {
            MiuixTheme {
                TopAppBar(
                    title = "Hi",
                    subtitle = "Sub",
                    modifier = Modifier.width(400.dp).testTag("bar"),
                    scrollBehavior = MiuixScrollBehavior(state),
                    defaultWindowInsetsPadding = false,
                    navigationIcon = { Box(Modifier.size(40.dp).testTag("nav")) },
                    actions = { Box(Modifier.size(140.dp, 40.dp).testTag("actions")) },
                )
            }
        }
        waitForIdle()
        val bar = onNodeWithTag("bar").fetchSemanticsNode().boundsInRoot
        val titleBounds = onAllNodesWithText("Hi")[0].fetchSemanticsNode().boundsInRoot
        val subtitleBounds = onAllNodesWithText("Sub")[0].fetchSemanticsNode().boundsInRoot
        assertEquals(bar.center.x, titleBounds.center.x, 1.5f)
        assertEquals(bar.center.x, subtitleBounds.center.x, 1.5f)
    }

    @Test
    fun overflowingTitleSwitchesToStartAlignedInCollapsedTopAppBar() = runComposeUiTest {
        val state = TopAppBarState(-1000f, -1000f, -1000f)
        setContent {
            MiuixTheme {
                TopAppBar(
                    title = "Very Long Title Here",
                    subtitle = "Short",
                    modifier = Modifier.width(400.dp).testTag("bar"),
                    scrollBehavior = MiuixScrollBehavior(state),
                    defaultWindowInsetsPadding = false,
                    navigationIcon = { Box(Modifier.size(40.dp).testTag("nav")) },
                    actions = { Box(Modifier.size(140.dp, 40.dp).testTag("actions")) },
                )
            }
        }
        waitForIdle()
        val navBounds = onNodeWithTag("nav").fetchSemanticsNode().boundsInRoot
        val titleBounds = onAllNodesWithText("Very Long Title Here")[0].fetchSemanticsNode().boundsInRoot
        val subtitleBounds = onAllNodesWithText("Short")[0].fetchSemanticsNode().boundsInRoot
        val density = navBounds.width / 40f
        val expectedTextLeft = navBounds.right + 12f * density
        assertEquals(expectedTextLeft, titleBounds.left, 1.5f)
        assertEquals(titleBounds.left, subtitleBounds.left, 1.5f)
    }

    @Test
    fun overflowingSubtitleSwitchesGroupToStartAlignedInSmallTopAppBar() = runComposeUiTest {
        setContent {
            MiuixTheme {
                SmallTopAppBar(
                    title = "Hi",
                    subtitle = "Long Subtitle Text",
                    modifier = Modifier.width(400.dp).testTag("bar"),
                    defaultWindowInsetsPadding = false,
                    navigationIcon = { Box(Modifier.size(40.dp).testTag("nav")) },
                    actions = { Box(Modifier.size(140.dp, 40.dp).testTag("actions")) },
                )
            }
        }
        waitForIdle()
        val navBounds = onNodeWithTag("nav").fetchSemanticsNode().boundsInRoot
        val actionsBounds = onNodeWithTag("actions").fetchSemanticsNode().boundsInRoot
        val titleBounds = onNodeWithText("Hi").fetchSemanticsNode().boundsInRoot
        val subtitleBounds = onNodeWithText("Long Subtitle Text").fetchSemanticsNode().boundsInRoot
        val density = navBounds.width / 40f
        val expectedTextLeft = navBounds.right + 12f * density
        assertEquals(expectedTextLeft, titleBounds.left, 1.5f)
        assertEquals(titleBounds.left, subtitleBounds.left, 1.5f)
        assertTrue(subtitleBounds.right < actionsBounds.left)
    }

    @Test
    fun overflowingTitleSwitchesToStartAlignedInRtlSmallTopAppBar() = runComposeUiTest {
        setContent {
            MiuixTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    SmallTopAppBar(
                        title = "Very Long Title Here",
                        subtitle = "Short",
                        modifier = Modifier.width(400.dp).testTag("bar"),
                        defaultWindowInsetsPadding = false,
                        navigationIcon = { Box(Modifier.size(40.dp).testTag("nav")) },
                        actions = { Box(Modifier.size(140.dp, 40.dp).testTag("actions")) },
                    )
                }
            }
        }
        waitForIdle()
        val navBounds = onNodeWithTag("nav").fetchSemanticsNode().boundsInRoot
        val titleBounds = onNodeWithText("Very Long Title Here").fetchSemanticsNode().boundsInRoot
        val subtitleBounds = onNodeWithText("Short").fetchSemanticsNode().boundsInRoot
        val density = navBounds.width / 40f
        val expectedTextRight = navBounds.left - 12f * density
        assertEquals(expectedTextRight, titleBounds.right, 1.5f)
        assertEquals(titleBounds.right, subtitleBounds.right, 1.5f)
    }

    @Test
    fun centerObstructionStartAlignsTitleLeftBetweenNavAndObstruction() = runComposeUiTest {
        setContent {
            MiuixTheme {
                SmallTopAppBar(
                    title = "Secondary Page Title",
                    subtitle = "Sub",
                    modifier = Modifier.width(600.dp).testTag("bar"),
                    defaultWindowInsetsPadding = false,
                    centerObstructionStart = 240.dp,
                    navigationIcon = { Box(Modifier.size(40.dp).testTag("nav")) },
                )
            }
        }
        waitForIdle()
        val navBounds = onNodeWithTag("nav").fetchSemanticsNode().boundsInRoot
        val titleBounds = onNodeWithText("Secondary Page Title").fetchSemanticsNode().boundsInRoot
        val subtitleBounds = onNodeWithText("Sub").fetchSemanticsNode().boundsInRoot
        val density = navBounds.width / 40f
        assertEquals(navBounds.right + 12f * density, titleBounds.left, 1.5f)
        assertEquals(titleBounds.left, subtitleBounds.left, 1.5f)
        assertTrue(titleBounds.right <= 240f * density - 12f * density + 1.5f)
    }

    @Test
    fun centerObstructionHidesTitleWhenSpaceIsInsufficient() = runComposeUiTest {
        setContent {
            MiuixTheme {
                SmallTopAppBar(
                    title = "Secondary Page Title",
                    modifier = Modifier.width(600.dp).testTag("bar"),
                    defaultWindowInsetsPadding = false,
                    centerObstructionStart = 90.dp,
                    navigationIcon = { Box(Modifier.size(40.dp).testTag("nav")) },
                )
            }
        }
        waitForIdle()
        onNodeWithText("Secondary Page Title").assertIsNotDisplayed()
    }

    @Test
    fun togglingCenterObstructionSmoothlyAnimatesTitleAlignment() = runComposeUiTest {
        var obstruction by mutableStateOf(240.dp)
        setContent {
            MiuixTheme {
                SmallTopAppBar(
                    title = "Details",
                    modifier = Modifier.width(600.dp).testTag("bar"),
                    defaultWindowInsetsPadding = false,
                    centerObstructionStart = obstruction,
                    navigationIcon = { Box(Modifier.size(40.dp).testTag("nav")) },
                )
            }
        }
        waitForIdle()
        val bar = onNodeWithTag("bar").fetchSemanticsNode().boundsInRoot
        val startLeft = onNodeWithText("Details").fetchSemanticsNode().boundsInRoot.left
        val navBounds = onNodeWithTag("nav").fetchSemanticsNode().boundsInRoot
        val density = navBounds.width / 40f
        assertEquals(navBounds.right + 12f * density, startLeft, 1.5f)

        mainClock.autoAdvance = false
        obstruction = Dp.Unspecified
        mainClock.advanceTimeByFrame()
        mainClock.advanceTimeBy(80L)
        val midCenter = onNodeWithText("Details").fetchSemanticsNode().boundsInRoot.center.x
        assertTrue(midCenter > startLeft && midCenter < bar.center.x)

        mainClock.autoAdvance = true
        waitForIdle()
        val settledCenter = onNodeWithText("Details").fetchSemanticsNode().boundsInRoot.center.x
        assertEquals(bar.center.x, settledCenter, 1.5f)
    }

    @Test
    fun togglingObstructionWithTruncatedTitleTransitionsSmoothlyInTopAppBarLayout() = runComposeUiTest {
        var obstruction by mutableStateOf(160.dp)
        val state = TopAppBarState(0f, 0f, 0f)
        setContent {
            MiuixTheme {
                TopAppBarLayout(
                    title = "Very Long Secondary Title",
                    color = Color.Transparent,
                    titleColor = Color.Black,
                    largeTitleColor = Color.Black,
                    subtitle = "",
                    subtitleColor = Color.Gray,
                    navigationIcon = { Box(Modifier.size(40.dp).testTag("nav")) },
                    actions = { Row(horizontalArrangement = Arrangement.End) {} },
                    titlePadding = TopAppBarDefaults.TitlePadding,
                    navigationIconPadding = TopAppBarDefaults.NavigationIconPadding,
                    actionIconPadding = TopAppBarDefaults.ActionIconPadding,
                    scrollBehavior = MiuixScrollBehavior(state),
                    modifier = Modifier.width(600.dp).testTag("bar"),
                    expandable = false,
                    titleBlurRadius = 8.dp,
                    defaultWindowInsetsPadding = false,
                    centerObstructionStart = obstruction,
                )
            }
        }
        waitForIdle()
        val bar = onNodeWithTag("bar").fetchSemanticsNode().boundsInRoot
        val collapsedBounds = onNodeWithText("Very Long Secondary Title").fetchSemanticsNode().boundsInRoot
        assertTrue(collapsedBounds.right < bar.center.x)

        obstruction = Dp.Unspecified
        waitForIdle()
        val expandedBounds = onNodeWithText("Very Long Secondary Title").fetchSemanticsNode().boundsInRoot
        assertEquals(bar.center.x, expandedBounds.center.x, 1.5f)
        assertTrue(expandedBounds.width > collapsedBounds.width)
    }
}
