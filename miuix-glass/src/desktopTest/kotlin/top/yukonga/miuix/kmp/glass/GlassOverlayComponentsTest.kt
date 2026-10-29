// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.darkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassOverlayComponentsTest {

    @Test
    fun dialogDefaultsApplyTheMaterialAndRetainHostDismissal() = runComposeUiTest {
        val show = mutableStateOf(true)
        val owner = TestNavigationOwner()
        var finished = 0
        setContent {
            DialogTestHost(owner) {
                MiuixTheme(colors = lightColorScheme()) {
                    val backdrop = rememberLayerBackdrop()
                    Box(Modifier.fillMaxSize().layerBackdrop(backdrop).drawBehind { drawRect(Color(0xFF808080)) })
                    GlassDialog(
                        onDismissRequest = { show.value = false },
                        show = show.value,
                        backdrop = backdrop,
                        modifier = Modifier.width(300.dp).testTag("dialog"),
                        largeScreen = true,
                        title = "Glass dialog",
                        summary = "Shared material",
                        onDismissFinished = { finished++ },
                    ) {
                        GlassDialogButton(onClick = { show.value = false }) { Text("Close") }
                    }
                }
            }
        }
        waitForIdle()
        onNodeWithText("Glass dialog").assertIsDisplayed()
        onNodeWithText("Shared material").assertIsDisplayed()
        val pixels = onNodeWithTag("dialog").captureToImage().toPixelMap()
        assertColorNear(Color(0xFFCECECE), pixels[8, pixels.height / 2], tolerance = 4f / 255f)
        onNodeWithText("Close").performClick()
        waitForIdle()
        onNodeWithText("Close").assertDoesNotExist()
        assertEquals(1, finished)
        runOnIdle { show.value = true }
        waitForIdle()
        runOnIdle { owner.input.backCompleted() }
        waitForIdle()
        onNodeWithText("Close").assertDoesNotExist()
        assertEquals(2, finished)
    }

    @Test
    fun sheetUsesDarkMaterialAndRetainsCallerSpacingAndDismissal() = runComposeUiTest {
        val show = mutableStateOf(true)
        val allowDismiss = mutableStateOf(false)
        val owner = TestNavigationOwner()
        var finished = 0
        setContent {
            DialogTestHost(owner) {
                MiuixTheme(colors = darkColorScheme()) {
                    val backdrop = rememberLayerBackdrop()
                    Box(Modifier.fillMaxSize().layerBackdrop(backdrop).drawBehind { drawRect(Color(0xFF808080)) })
                    GlassBottomSheet(
                        onDismissRequest = { show.value = false },
                        show = show.value,
                        backdrop = backdrop,
                        modifier = Modifier.testTag("sheet"),
                        title = "Glass sheet",
                        allowDismiss = allowDismiss.value,
                        onDismissFinished = { finished++ },
                    ) {
                        Column(Modifier.navigationBarsPadding().padding(bottom = 16.dp)) {
                            Box(Modifier.height(100.dp))
                            GlassDialogButton(
                                onClick = { show.value = false },
                                modifier = Modifier.fillMaxWidth().testTag("done"),
                                role = GlassDialogButtonRole.Primary,
                            ) {
                                Text("Done")
                            }
                        }
                    }
                }
            }
        }
        waitForIdle()
        val sheet = onNodeWithTag("sheet")
        val pixels = sheet.captureToImage().toPixelMap()
        assertColorNear(Color(0xFF3D3D3D), pixels[8, pixels.height / 2], tolerance = 4f / 255f)
        val sheetBottom = sheet.fetchSemanticsNode().boundsInRoot.bottom
        val doneBottom = onNodeWithTag("done").fetchSemanticsNode().boundsInRoot.bottom
        assertEquals(with(density) { 16.dp.toPx() }, sheetBottom - doneBottom, absoluteTolerance = 1f)
        runOnIdle { owner.input.backCompleted() }
        waitForIdle()
        onNodeWithText("Done").assertIsDisplayed()
        assertEquals(0, finished)
        runOnIdle { allowDismiss.value = true }
        runOnIdle { owner.input.backCompleted() }
        waitForIdle()
        onNodeWithText("Done").assertDoesNotExist()
        assertEquals(1, finished)
    }

    @Test
    fun sheetBottomSpacingScrollsWithContentAndDoneRemainsReachable() = runComposeUiTest {
        val show = mutableStateOf(true)
        val scroll = ScrollState(0)
        setContent {
            DialogTestHost {
                GlassBottomSheet(
                    onDismissRequest = { show.value = false },
                    show = show.value,
                    backdrop = null,
                    modifier = Modifier.testTag("sheet"),
                    containerColor = Color(0xFF326498),
                ) {
                    Column(
                        modifier = Modifier
                            .testTag("scroll-content")
                            .verticalScroll(scroll)
                            .navigationBarsPadding()
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        repeat(24) { index ->
                            Text("Sheet item $index", modifier = Modifier.height(48.dp))
                        }
                        GlassDialogButton(onClick = { show.value = false }, modifier = Modifier.testTag("done")) {
                            Text("Done")
                        }
                    }
                }
            }
        }
        waitForIdle()
        val pixels = onNodeWithTag("sheet").captureToImage().toPixelMap()
        assertColorNear(Color(0xFF326498), pixels[8, pixels.height / 2])
        val sheetBottom = onNodeWithTag("sheet").fetchSemanticsNode().boundsInRoot.bottom
        val viewportBottom = onNodeWithTag("scroll-content").fetchSemanticsNode().boundsInRoot.bottom
        assertEquals(sheetBottom, viewportBottom, absoluteTolerance = 1f)
        onNodeWithTag("done").performScrollTo().assertIsDisplayed()
        onNodeWithTag("scroll-content").performTouchInput { swipeUp() }
        waitForIdle()
        runOnIdle { assertTrue(scroll.value > 0) }
        val doneBottom = onNodeWithTag("done").fetchSemanticsNode().boundsInRoot.bottom
        assertEquals(with(density) { 16.dp.toPx() }, sheetBottom - doneBottom, absoluteTolerance = 1f)
        onNodeWithTag("done").performClick()
        waitForIdle()
        onNodeWithTag("sheet").assertDoesNotExist()
    }
}
