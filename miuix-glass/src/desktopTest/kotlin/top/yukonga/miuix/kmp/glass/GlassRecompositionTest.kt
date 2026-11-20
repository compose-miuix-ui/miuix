// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Search
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassRecompositionTest {

    @Test
    fun glassPressDoesNotRecomposeCallerDuringPressAnimation() = runComposeUiTest {
        mainClock.autoAdvance = false
        var callerCompositions = 0
        lateinit var interactionSource: MutableInteractionSource
        setContent {
            DialogTestHost {
                interactionSource = remember { MutableInteractionSource() }
                SideEffect { callerCompositions++ }
                Box(
                    modifier = Modifier
                        .size(120.dp, 48.dp)
                        .glassPress(interactionSource)
                        .clickable(interactionSource = interactionSource, indication = null) {},
                )
            }
        }
        mainClock.advanceTimeBy(500)
        waitForIdle()
        val beforePress = callerCompositions

        val press = PressInteraction.Press(Offset(20f, 20f))
        interactionSource.tryEmit(press)
        repeat(10) {
            mainClock.advanceTimeBy(16)
            waitForIdle()
        }
        interactionSource.tryEmit(PressInteraction.Release(press))
        repeat(10) {
            mainClock.advanceTimeBy(16)
            waitForIdle()
        }
        val afterPress = callerCompositions
        assertTrue(
            afterPress == beforePress,
            "Expected callerCompositions during press animation to stay $beforePress, but was $afterPress",
        )
    }

    @Test
    fun navigationRailUnselectedItemsSkipOnSelectionChange() = runComposeUiTest {
        var selected by mutableIntStateOf(0)
        val badgeCompositions = IntArray(3)
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                val items = remember {
                    listOf(
                        GlassNavigationItem(MiuixIcons.Basic.Search, "Home", badge = { SideEffect { badgeCompositions[0]++ } }),
                        GlassNavigationItem(MiuixIcons.Basic.Search, "Explore", badge = { SideEffect { badgeCompositions[1]++ } }),
                        GlassNavigationItem(MiuixIcons.Basic.Search, "Profile", badge = { SideEffect { badgeCompositions[2]++ } }),
                    )
                }
                GlassNavigationRail(
                    items = items,
                    selectedIndex = selected,
                    onSelect = { selected = it },
                    backdrop = backdrop,
                    stroke = null,
                    shadow = null,
                )
            }
        }
        waitForIdle()
        val beforeItem2 = badgeCompositions[2]
        selected = 1
        waitForIdle()
        val afterItem2 = badgeCompositions[2]
        assertTrue(
            afterItem2 == beforeItem2,
            "Expected unselected item 2 badge compositions to stay $beforeItem2, but was $afterItem2",
        )
    }

    @Test
    fun segmentedTabRowDoesNotRecomposeHostDuringPress() = runComposeUiTest {
        mainClock.autoAdvance = false
        var hostCompositions = 0
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                val tabs = remember { listOf("First", "Second") }
                SideEffect { hostCompositions++ }
                GlassSegmentedTabRow(
                    tabs = tabs,
                    selectedIndex = 0,
                    onSelect = {},
                    backdrop = backdrop,
                    stroke = null,
                    shadow = null,
                )
            }
        }
        mainClock.advanceTimeBy(500)
        waitForIdle()
        val beforePress = hostCompositions

        onRoot().performTouchInput { down(centerLeft + Offset(20f, 0f)) }
        repeat(8) {
            mainClock.advanceTimeBy(16)
            waitForIdle()
        }
        onRoot().performTouchInput { up() }
        repeat(8) {
            mainClock.advanceTimeBy(16)
            waitForIdle()
        }
        val afterPress = hostCompositions
        assertTrue(
            afterPress == beforePress,
            "Expected hostCompositions during SegmentedTabRow press to stay $beforePress, but was $afterPress",
        )
    }

    @Test
    fun navigationBarDoesNotRecomposeItemsDuringTrailingActionAnimation() = runComposeUiTest {
        mainClock.autoAdvance = false
        var showAction by mutableStateOf(true)
        val badgeCompositions = IntArray(3)
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                val items = remember {
                    listOf(
                        GlassNavigationItem(MiuixIcons.Basic.Search, "Home", badge = { SideEffect { badgeCompositions[0]++ } }),
                        GlassNavigationItem(MiuixIcons.Basic.Search, "Add", badge = { SideEffect { badgeCompositions[1]++ } }),
                        GlassNavigationItem(MiuixIcons.Basic.Search, "Settings", badge = { SideEffect { badgeCompositions[2]++ } }),
                    )
                }
                GlassNavigationBar(
                    items = items,
                    selectedIndex = 0,
                    onSelect = {},
                    backdrop = backdrop,
                    stroke = null,
                    shadow = null,
                    trailingAction = if (showAction) {
                        { Box(Modifier.size(48.dp)) }
                    } else {
                        null
                    },
                )
            }
        }
        mainClock.advanceTimeBy(500)
        waitForIdle()
        val beforeAnim = badgeCompositions[0]

        showAction = false
        repeat(20) {
            mainClock.advanceTimeBy(16)
            waitForIdle()
        }
        val afterAnim = badgeCompositions[0]
        assertTrue(
            afterAnim <= beforeAnim + 1,
            "Expected navigationBar item 0 badge compositions during trailingAction exit to be <= ${beforeAnim + 1}, but was $afterAnim",
        )
    }
}
