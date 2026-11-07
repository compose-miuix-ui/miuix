// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BlurTopAppBar
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.TopAppBarState
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassTopAppBarShadowTest {

    @Test
    fun collapsedButtonsCastShadowsBelowTheBar() = assertShadowOutsideBar(withTabs = false)

    @Test
    fun bottomTabsCastShadowsBelowTheBar() = assertShadowOutsideBar(withTabs = true)

    @Test
    fun collapsingTitleBlurStaysInsideTheBar() = assertTitleClip(LayoutDirection.Ltr)

    @Test
    fun collapsingTitleBlurStaysInsideTheBarInRtl() = assertTitleClip(LayoutDirection.Rtl)

    private fun assertTitleClip(direction: LayoutDirection) = runComposeUiTest {
        val scrollState = TopAppBarState(-1000f, 0f, 0f)
        setContent {
            DialogTestHost {
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Box(Modifier.size(400.dp, 300.dp).background(Color.White)) {
                        BlurTopAppBar(
                            title = "",
                            largeTitle = "MMMMMMMM",
                            largeTitleColor = Color.Magenta,
                            largeTitleBlurRadius = 40.dp,
                            color = Color.Transparent,
                            modifier = Modifier.testTag("bar"),
                            scrollBehavior = MiuixScrollBehavior(scrollState),
                            defaultWindowInsetsPadding = false,
                        )
                    }
                }
            }
        }
        waitForIdle()
        runOnIdle { scrollState.heightOffset = scrollState.heightOffsetLimit * 0.2f }
        waitForIdle()
        val bar = onNodeWithTag("bar").fetchSemanticsNode().boundsInRoot
        val pixels = onRoot().captureToImage().toPixelMap()
        val columns = bar.left.roundToInt() until bar.right.roundToInt()
        val hasTitle = (bar.top.roundToInt() until bar.bottom.roundToInt()).any { y ->
            columns.any { x -> pixels[x, y].red > pixels[x, y].green + 0.01f }
        }
        assertTrue(hasTitle, "The partially collapsed title must still be visible")
        val hasOverflow = (bar.bottom.roundToInt() until bar.bottom.roundToInt() + 30).any { y ->
            columns.any { x -> pixels[x, y].green < 0.995f }
        }
        assertTrue(!hasOverflow, "Title blur must retain the original bar clip in $direction")
    }

    private fun assertShadowOutsideBar(withTabs: Boolean) = runComposeUiTest {
        val scrollState = TopAppBarState(-1000f, -1000f, -1000f)
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(
                    Modifier
                        .size(400.dp, 300.dp)
                        .background(Color.White)
                        .testTag("scene"),
                ) {
                    Box(
                        Modifier
                            .matchParentSize()
                            .layerBackdrop(backdrop)
                            .background(Color.White),
                    )
                    GlassTopAppBar(
                        title = "Title",
                        isContentScrolled = true,
                        modifier = Modifier.testTag("bar"),
                        scrollBehavior = MiuixScrollBehavior(scrollState),
                        defaultWindowInsetsPadding = false,
                        bandBrush = SolidColor(Color.Transparent),
                        navigationIcon = {
                            GlassIconButton(
                                onClick = {},
                                modifier = Modifier.testTag("button"),
                                fill = Color.White,
                                stroke = null,
                            ) { }
                        },
                        bottomContent = {
                            if (withTabs) {
                                GlassSegmentedTabRow(
                                    tabs = listOf("First", "Second"),
                                    selectedIndex = 0,
                                    onSelect = {},
                                    backdrop = backdrop,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp).testTag("tabs"),
                                    shadow = GlassShadows.Regular,
                                )
                            }
                        },
                    )
                }
            }
        }
        waitForIdle()
        val bar = onNodeWithTag("bar").fetchSemanticsNode().boundsInRoot
        val control = onNodeWithTag(if (withTabs) "tabs" else "button").fetchSemanticsNode().boundsInRoot
        val density = onNodeWithTag("scene").fetchSemanticsNode().boundsInRoot.width / 400f
        assertEquals(56f * density + if (withTabs) control.height + 4f * density else 0f, bar.height, 0.5f)
        val pixels = onRoot().captureToImage().toPixelMap()
        val x = control.center.x.roundToInt()
        val y = bar.bottom.roundToInt() + 2
        assertTrue(pixels[x, y].red < 0.998f, "The shadow must continue below the bar: ${pixels[x, y]}")
        assertTrue(pixels[x, y + 30].red > pixels[x, y].red, "The shadow must fade naturally")
    }
}
