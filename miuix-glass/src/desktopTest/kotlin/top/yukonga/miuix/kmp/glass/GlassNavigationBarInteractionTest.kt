// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Search
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class GlassNavigationBarInteractionTest {
    @Test
    fun draggingAfterResizeUsesOriginalHitRegions() = assertDragAfterResize(LayoutDirection.Ltr)

    @Test
    fun draggingAfterResizeUsesOriginalHitRegionsInRtl() = assertDragAfterResize(LayoutDirection.Rtl)

    private fun assertDragAfterResize(direction: LayoutDirection) = runComposeUiTest {
        val width = mutableStateOf(360.dp)
        val selected = mutableIntStateOf(0)
        val selections = mutableListOf<Int>()
        setContent {
            DialogTestHost {
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Box(Modifier.size(600.dp, 120.dp), contentAlignment = AbsoluteAlignment.TopLeft) {
                        Box(Modifier.size(width.value, 120.dp)) {
                            GlassNavigationBar(
                                items = List(3) { GlassNavigationItem(MiuixIcons.Basic.Search, "Tab $it") },
                                selectedIndex = selected.intValue,
                                onSelect = {
                                    selections += it
                                    selected.intValue = it
                                },
                                backdrop = null,
                                defaultWindowInsetsPadding = false,
                                stroke = null,
                                shadow = null,
                            )
                        }
                    }
                }
            }
        }
        val first = onNodeWithContentDescription("Tab 0").fetchSemanticsNode().boundsInRoot.center
        val second = onNodeWithContentDescription("Tab 1").fetchSemanticsNode().boundsInRoot.center
        val delta = Offset(second.x - first.x, 0f)
        onRoot().performTouchInput { down(first) }
        runOnIdle {
            assertEquals(emptyList(), selections)
            width.value = 600.dp
        }
        waitForIdle()
        onRoot().performTouchInput { moveTo(first + delta) }
        runOnIdle { assertEquals(emptyList(), selections) }
        onRoot().performTouchInput { up() }
        onNodeWithContentDescription("Tab 1").assertIsSelected()
        runOnIdle { assertEquals(listOf(1), selections) }
    }
}
