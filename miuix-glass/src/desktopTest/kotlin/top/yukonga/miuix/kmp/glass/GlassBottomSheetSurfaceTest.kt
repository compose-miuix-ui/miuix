// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.layout.BottomSheetSurfaceScope
import top.yukonga.miuix.kmp.overlay.OverlayBottomSheet
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.darkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassBottomSheetSurfaceTest {

    @Test
    fun fallbackReplacesTheFillRoundsOnlyTheTopAndRetainsDismissal() = runComposeUiTest {
        val show = mutableStateOf(true)
        val allowDismiss = mutableStateOf(false)
        val owner = TestNavigationOwner()
        var finished = 0
        var scope: BottomSheetSurfaceScope? = null
        val fill = Color(0xFF326498)
        setContent {
            DialogTestHost(owner) {
                val surface = glassBottomSheetSurface(null, stroke = null, containerColor = fill)
                OverlayBottomSheet(
                    show = show.value,
                    modifier = Modifier.testTag("sheet"),
                    sheetMaxWidth = 340.dp,
                    outsideMargin = DpSize(0.dp, 0.dp),
                    insideMargin = DpSize(24.dp, 0.dp),
                    cornerRadius = 44.dp,
                    backgroundColor = Color.Red,
                    allowDismiss = allowDismiss.value,
                    onDismissRequest = { show.value = false },
                    onDismissFinished = { finished++ },
                    surface = {
                        scope = this
                        surface(this)
                    },
                ) {
                    Box(Modifier.height(80.dp))
                    TextButton("Close", onClick = { show.value = false })
                }
            }
        }
        waitForIdle()
        val pixels = onNodeWithTag("sheet").captureToImage().toPixelMap()
        assertEquals(44.dp, scope?.cornerRadius)
        assertEquals(false, scope?.isOverscroll)
        assertColorNear(fill, pixels[8, pixels.height / 2])
        assertTrue(pixels[1, 1] != fill)
        assertColorNear(fill, pixels[1, pixels.height - 2])
        runOnIdle { owner.input.backCompleted() }
        waitForIdle()
        onNodeWithText("Close").assertExists()
        assertEquals(0, finished)
        onNodeWithText("Close").performClick()
        waitForIdle()
        assertEquals(1, finished)
        runOnIdle {
            allowDismiss.value = true
            show.value = true
        }
        waitForIdle()
        runOnIdle { owner.input.backCompleted() }
        waitForIdle()
        onNodeWithText("Close").assertDoesNotExist()
        assertEquals(2, finished)
    }

    @Test
    fun lightSheetUsesTheSharedDialogMaterial() = assertMaterial(isDark = false, expectedChannel = 206)

    @Test
    fun darkSheetUsesTheSharedDialogMaterial() = assertMaterial(isDark = true, expectedChannel = 61)

    private fun assertMaterial(isDark: Boolean, expectedChannel: Int) = runComposeUiTest {
        val dim = mutableStateOf(true)
        var scope: BottomSheetSurfaceScope? = null
        setContent {
            DialogTestHost {
                MiuixTheme(colors = if (isDark) darkColorScheme() else lightColorScheme()) {
                    val backdrop = rememberLayerBackdrop()
                    Box(Modifier.fillMaxSize().layerBackdrop(backdrop).drawBehind { drawRect(Color(0xFF808080)) })
                    val surface = glassBottomSheetSurface(backdrop, stroke = null)
                    OverlayBottomSheet(
                        show = true,
                        modifier = Modifier.testTag("sheet"),
                        title = "Glass Bottom Sheet",
                        sheetMaxWidth = 340.dp,
                        outsideMargin = DpSize(0.dp, 0.dp),
                        enableWindowDim = dim.value,
                        dimColor = GlassBottomSheetDefaults.ScrimColor,
                        surface = {
                            scope = this
                            surface(this)
                        },
                    ) {
                        Box(Modifier.fillMaxWidth().height(160.dp))
                        GlassDialogButton(onClick = {}, modifier = Modifier.fillMaxWidth(), role = GlassDialogButtonRole.Primary) {
                            Text("Done", style = MiuixTheme.textStyles.button)
                        }
                    }
                }
            }
        }
        waitForIdle()
        val pixels = onNodeWithTag("sheet").captureToImage().toPixelMap()
        val expected = expectedChannel / 255f
        val center = pixels[pixels.width / 2, pixels.height / 2]
        assertColorNear(Color(expected, expected, expected), center, tolerance = 4f / 255f)
        assertColorNear(center, pixels[8, pixels.height / 2], tolerance = 4f / 255f)
        assertEquals(GlassBottomSheetDefaults.ScrimColor, scope?.scrimColor?.value)
        saveImage(if (isDark) "sheet-dark" else "sheet-light", onRoot().captureToImage())
        runOnIdle { dim.value = false }
        waitForIdle()
        val undimmed = onNodeWithTag("sheet").captureToImage().toPixelMap()
        assertTrue(undimmed[undimmed.width / 2, undimmed.height / 2].red > center.red + 0.01f)
        assertEquals(Color.Transparent, scope?.scrimColor?.value)
    }

    @Test
    fun sceneStaysScreenAlignedAsTheSheetSlidesIn() = runComposeUiTest {
        mainClock.autoAdvance = false
        val show = mutableStateOf(false)
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(
                    Modifier.fillMaxSize().layerBackdrop(backdrop).drawBehind {
                        drawRect(Brush.verticalGradient(listOf(Color.Red, Color.Blue)))
                    },
                )
                OverlayBottomSheet(
                    show = show.value,
                    modifier = Modifier.testTag("sheet"),
                    sheetMaxWidth = 340.dp,
                    outsideMargin = DpSize(0.dp, 0.dp),
                    enableWindowDim = false,
                    surface = glassBottomSheetSurface(
                        backdrop,
                        material = GlassMaterial(0.dp, GlassColorLayer(Color.Transparent, GlassColorBlendMode.SrcOver)),
                        stroke = null,
                    ),
                ) {
                    Box(Modifier.height(280.dp))
                }
            }
        }
        waitForIdle()
        val clean = onRoot().captureToImage().toPixelMap()
        val x = clean.width / 2
        val y = clean.height - 40
        runOnIdle { show.value = true }
        mainClock.advanceTimeBy(96)
        waitForIdle()
        val openingTop = onNodeWithTag("sheet").fetchSemanticsNode().boundsInRoot.top
        val opening = onRoot().captureToImage().toPixelMap()
        assertColorNear(clean[x, y], opening[x, y], tolerance = 4f / 255f)
        saveImage("sheet-opening", onRoot().captureToImage())
        mainClock.advanceTimeBy(1000)
        waitForIdle()
        val restingTop = onNodeWithTag("sheet").fetchSemanticsNode().boundsInRoot.top
        assertTrue(openingTop > restingTop + 10f, "Sample the moving sheet, not its resting frame")
        val resting = onRoot().captureToImage().toPixelMap()
        assertColorNear(clean[x, y], resting[x, y], tolerance = 4f / 255f)
    }

    @Test
    fun upwardOverscrollUsesTheMaterialAndDownwardDragStillDismisses() = runComposeUiTest {
        val show = mutableStateOf(true)
        var finished = 0
        var overscroll: BottomSheetSurfaceScope? = null
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(Modifier.fillMaxSize().layerBackdrop(backdrop).drawBehind { drawRect(Color(0xFF808080)) })
                val surface = glassBottomSheetSurface(backdrop, stroke = null)
                OverlayBottomSheet(
                    show = show.value,
                    modifier = Modifier.testTag("sheet"),
                    sheetMaxWidth = 340.dp,
                    outsideMargin = DpSize(0.dp, 0.dp),
                    backgroundColor = Color.Red,
                    dimColor = GlassBottomSheetDefaults.ScrimColor,
                    onDismissRequest = { show.value = false },
                    onDismissFinished = { finished++ },
                    surface = {
                        if (isOverscroll) overscroll = this
                        surface(this)
                    },
                ) {
                    Box(Modifier.height(240.dp))
                }
            }
        }
        waitForIdle()
        val sheet = onNodeWithTag("sheet")
        // Exercise the actual drag/elastic-fill pipeline, rather than an interaction indication.
        sheet.performTouchInput {
            down(Offset(centerX, 12f))
            moveBy(Offset(0f, -180f), delayMillis = 120)
        }
        waitForIdle()
        assertEquals(true, overscroll?.isOverscroll)
        assertEquals(0.dp, overscroll?.cornerRadius)
        val pixels = onRoot().captureToImage().toPixelMap()
        val expected = 206f / 255f
        assertColorNear(Color(expected, expected, expected), pixels[pixels.width / 2, pixels.height - 2], tolerance = 4f / 255f)
        saveImage("sheet-overscroll", onRoot().captureToImage())
        sheet.performTouchInput { up() }
        waitForIdle()
        assertEquals(0, finished)
        sheet.performTouchInput {
            swipe(start = Offset(centerX, 12f), end = Offset(centerX, height - 2f), durationMillis = 300)
        }
        waitForIdle()
        sheet.assertDoesNotExist()
        assertEquals(1, finished)
    }
}
