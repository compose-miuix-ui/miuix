// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.click
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Search
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class GlassNavigationBlurTest {
    @Test
    fun disablingSamplingUsesSolidThemeFillAndKeepsSelection() = runComposeUiTest {
        val blurEnabled = mutableStateOf(true)
        val selected = mutableIntStateOf(0)
        var fallback = Color.Unspecified
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                fallback = MiuixTheme.colorScheme.surfaceContainer
                Box(Modifier.size(360.dp, 120.dp)) {
                    Box(Modifier.fillMaxSize().layerBackdrop(backdrop).background(Color.Red))
                    GlassNavigationBar(
                        items = listOf(
                            GlassNavigationItem(MiuixIcons.Basic.Search, "Home"),
                            GlassNavigationItem(MiuixIcons.Basic.Search, "Settings"),
                        ),
                        selectedIndex = selected.intValue,
                        onSelect = { selected.intValue = it },
                        backdrop = if (blurEnabled.value) backdrop else null,
                        modifier = Modifier.testTag("bar"),
                        defaultWindowInsetsPadding = false,
                        stroke = null,
                        shadow = null,
                        material = null,
                        indicatorColor = Color.Transparent,
                        indicatorPressedColor = Color.Transparent,
                    )
                }
            }
        }
        val before = onNodeWithTag("bar").fetchSemanticsNode().boundsInRoot
        val sampled = onNodeWithTag("bar").captureToImage().toPixelMap()
        assertColorNear(Color.Red, sampled[sampled.width / 2, sampled.height / 2])
        runOnIdle { blurEnabled.value = false }
        val solid = onNodeWithTag("bar").captureToImage().toPixelMap()
        assertColorNear(fallback, solid[solid.width / 2, solid.height / 2])
        assertEquals(before, onNodeWithTag("bar").fetchSemanticsNode().boundsInRoot)
        onNodeWithContentDescription("Settings").performTouchInput { click() }
        onNodeWithContentDescription("Settings").assertIsSelected()
        saveImage("navigation-blur-disabled", onRoot().captureToImage())
        runOnIdle { blurEnabled.value = true }
        onNodeWithContentDescription("Settings").assertIsSelected()
        val restored = onNodeWithTag("bar").captureToImage().toPixelMap()
        assertColorNear(Color.Red, restored[restored.width / 2, restored.height / 2])
        assertEquals(1, selected.intValue)
    }
}
