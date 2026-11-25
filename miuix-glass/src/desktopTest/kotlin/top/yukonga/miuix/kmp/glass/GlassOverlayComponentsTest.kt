// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeUp
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.darkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import kotlin.math.roundToInt
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
                    ) { innerPadding ->
                        Column(Modifier.padding(innerPadding).navigationBarsPadding().padding(bottom = 16.dp)) {
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
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .testTag("scroll-content")
                            .verticalScroll(scroll)
                            .padding(innerPadding)
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

    @Test
    fun sheetTitleAppliesProgressiveBlurWhenContentScrollsUnderHeader() = runComposeUiTest {
        val scroll = ScrollState(0)
        setContent {
            DialogTestHost {
                MiuixTheme(colors = lightColorScheme()) {
                    val backdrop = rememberLayerBackdrop()
                    Box(Modifier.fillMaxSize().layerBackdrop(backdrop).drawBehind { drawRect(Color(0xFF808080)) })
                    GlassBottomSheet(
                        onDismissRequest = {},
                        show = true,
                        backdrop = backdrop,
                        modifier = Modifier.testTag("sheet"),
                        title = "Glass BottomSheet",
                        allowDismiss = false,
                        stroke = null,
                        progressiveBlur = true,
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .testTag("scroll-content")
                                .verticalScroll(scroll)
                                .padding(innerPadding)
                                .padding(bottom = 16.dp),
                        ) {
                            Box(
                                modifier = Modifier
                                    .testTag("first-item")
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .background(Color.White),
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .background(Color.Red),
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(600.dp)
                                    .background(Color.Blue),
                            )
                        }
                    }
                }
            }
        }
        waitForIdle()

        val titleBounds = onNodeWithText("Glass BottomSheet").fetchSemanticsNode().boundsInRoot
        val firstItemBounds = onNodeWithTag("first-item").fetchSemanticsNode().boundsInRoot
        assertTrue(firstItemBounds.top >= titleBounds.bottom, "Initial content must start below the title: item=${firstItemBounds.top}, title=${titleBounds.bottom}")

        val sheetBounds = onNodeWithTag("sheet").fetchSemanticsNode().boundsInRoot
        val sampleX = (sheetBounds.left + 16f).roundToInt()
        val headerY = (sheetBounds.top + with(density) { 12.dp.toPx() }).roundToInt()
        val atTop = onRoot().captureToImage().toPixelMap()
        val headerAtRest = atTop[sampleX, headerY]
        assertTrue(headerAtRest.red - headerAtRest.blue < 0.05f, "Unscrolled header must stay uniform: $headerAtRest")

        // Scroll until the red/blue boundary sits near the top of the sheet behind the header.
        onNodeWithTag("scroll-content").performTouchInput {
            swipeUp(startY = bottom * 0.8f, endY = bottom * 0.35f, durationMillis = 300)
        }
        waitForIdle()
        runOnIdle { assertTrue(scroll.value > 0) }

        val scrolled = onRoot().captureToImage().toPixelMap()
        val headerScrolled = scrolled[sampleX, headerY]
        assertTrue(
            headerScrolled.red > headerAtRest.red + 0.05f || headerScrolled.blue > headerAtRest.blue + 0.05f,
            "Scrolled content must be visible and blurred in the header: atRest=$headerAtRest scrolled=$headerScrolled",
        )

        onNodeWithTag("scroll-content").performTouchInput {
            swipeDown(startY = bottom * 0.35f, endY = bottom * 0.9f, durationMillis = 300)
        }
        waitForIdle()
        runOnIdle { assertEquals(0, scroll.value) }

        val returned = onRoot().captureToImage().toPixelMap()
        assertColorNear(headerAtRest, returned[sampleX, headerY], tolerance = 4f / 255f)
    }

    @Test
    fun sheetHeaderClearsBodyWithoutApplyingInnerPaddingByDefault() = runComposeUiTest {
        setContent {
            DialogTestHost {
                GlassBottomSheet(
                    onDismissRequest = {},
                    show = true,
                    backdrop = null,
                    modifier = Modifier.testTag("sheet"),
                    title = "Default Sheet",
                    allowDismiss = false,
                ) { _ ->
                    Box(
                        modifier = Modifier
                            .testTag("body")
                            .fillMaxWidth()
                            .height(80.dp),
                    )
                }
            }
        }
        waitForIdle()
        val titleBottom = onNodeWithText("Default Sheet").fetchSemanticsNode().boundsInRoot.bottom
        val bodyTop = onNodeWithTag("body").fetchSemanticsNode().boundsInRoot.top
        assertTrue(bodyTop >= titleBottom)
    }
}
