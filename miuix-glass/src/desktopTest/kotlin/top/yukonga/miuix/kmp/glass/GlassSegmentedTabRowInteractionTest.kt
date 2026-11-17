// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassSegmentedTabRowInteractionTest {
    @Test
    fun draggingSwitchesTabAndDoesNotScrollParentPager() = assertDrag(LayoutDirection.Ltr)

    @Test
    fun draggingSwitchesTabInRtl() = assertDrag(LayoutDirection.Rtl)

    @Test
    fun tapSwitchesTab() = runComposeUiTest {
        val selected = mutableIntStateOf(0)
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop { drawRect(Color.Black) }
                Box(Modifier.size(320.dp, 80.dp), contentAlignment = Alignment.Center) {
                    GlassSegmentedTabRow(
                        tabs = listOf("Privacy", "Security"),
                        selectedIndex = selected.intValue,
                        onSelect = { selected.intValue = it },
                        backdrop = backdrop,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                        stroke = null,
                        shadow = null,
                    )
                }
            }
        }
        onNodeWithText("Security").performClick()
        onNodeWithText("Security").assertIsSelected()
        runOnIdle { assertEquals(1, selected.intValue) }
    }

    private fun assertDrag(direction: LayoutDirection) = runComposeUiTest {
        val selected = mutableIntStateOf(0)
        var pagerPage = 0
        setContent {
            DialogTestHost {
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    val backdrop = rememberLayerBackdrop { drawRect(Color.Black) }
                    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
                    pagerPage = pagerState.currentPage
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxSize().testTag("pager"),
                    ) { page ->
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            if (page == 0) {
                                GlassSegmentedTabRow(
                                    tabs = listOf("Privacy", "Security"),
                                    selectedIndex = selected.intValue,
                                    onSelect = { selected.intValue = it },
                                    backdrop = backdrop,
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp).testTag("tabs"),
                                    stroke = null,
                                    shadow = null,
                                )
                            }
                        }
                    }
                }
            }
        }
        val first = onNodeWithText("Privacy").fetchSemanticsNode().boundsInRoot.center
        val second = onNodeWithText("Security").fetchSemanticsNode().boundsInRoot.center
        onRoot().performTouchInput {
            down(first)
            moveBy(Offset((second.x - first.x) * 0.4f, 0f))
            moveBy(Offset((second.x - first.x) * 0.6f, 0f))
        }
        mainClock.advanceTimeBy(64)
        onRoot().performTouchInput { up() }
        onNodeWithText("Security").assertIsSelected()
        runOnIdle {
            assertEquals(1, selected.intValue)
            assertEquals(0, pagerPage, "Dragging GlassSegmentedTabRow must not scroll parent HorizontalPager")
        }

        // Also test a fast swipe from Security back to Privacy.
        onRoot().performTouchInput {
            if (direction == LayoutDirection.Ltr) {
                swipeLeft(startX = second.x, endX = first.x)
            } else {
                swipeRight(startX = second.x, endX = first.x)
            }
        }
        onNodeWithText("Privacy").assertIsSelected()
        runOnIdle {
            assertEquals(0, selected.intValue)
            assertEquals(0, pagerPage)
        }
        assertTrue(true)
    }
}
