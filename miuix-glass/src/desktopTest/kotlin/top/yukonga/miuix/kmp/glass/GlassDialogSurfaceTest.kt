// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigationevent.DirectNavigationEventInput
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventDispatcherOwner
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.internal.ScrimBackdrop
import top.yukonga.miuix.kmp.layout.DialogSurfaceScope
import top.yukonga.miuix.kmp.overlay.OverlayDialog
import top.yukonga.miuix.kmp.theme.MiuixTheme
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassDialogSurfaceTest {

    @Test
    fun blurredBackgroundChangesContinuouslyDuringSmallOpeningRebounds() = runComposeUiTest {
        val translation = mutableFloatStateOf(0f)
        setContent {
            val backdrop = rememberLayerBackdrop()
            Box(Modifier.size(500.dp)) {
                Box(
                    Modifier.fillMaxSize().layerBackdrop(backdrop).drawBehind {
                        drawRect(Color.Black)
                        for (y in 0..500 step 64) {
                            drawRect(Color.Blue, Offset(0f, y.toFloat()), Size(size.width, 32f))
                        }
                    },
                )
                val scale = remember { mutableFloatStateOf(1f) }
                val scrim = remember { mutableStateOf(Color.Transparent) }
                val dimmedBackdrop = remember { ScrimBackdrop(backdrop, scrim, scale) }
                Box(
                    Modifier.offset(100.dp, 200.dp).size(300.dp, 180.dp)
                        .graphicsLayer { translationY = translation.floatValue }
                        .glassPanel(
                            backdrop = dimmedBackdrop,
                            shape = GlassShape(28.dp),
                            material = GlassMaterial(60.dp, GlassColorLayer(Color.Transparent, GlassColorBlendMode.SrcOver)),
                            stroke = null,
                            shadow = null,
                            shading = false,
                        ),
                )
            }
        }
        val samples = listOf(0f, -1f, -2f, -3f, -2f, -1f, 0f, 1f, 0f).map { offset ->
            runOnIdle { translation.floatValue = offset }
            mainClock.advanceTimeByFrame()
            onRoot().captureToImage().toPixelMap()[250, 220].blue
        }
        // Small phase/interpolation differences are expected while the sampling window moves;
        // a one-pixel rebound must not jump an entire downsample-grid cell.
        val largestStep = samples.zipWithNext { before, after -> kotlin.math.abs(after - before) }.max()
        assertTrue(largestStep < 0.015f, "One-pixel rebounds must not flash: $samples")
    }

    @Test
    fun fallbackReplacesTheHostFillAndRetainsRadiusAndDismissal() = runComposeUiTest {
        val show = mutableStateOf(true)
        val owner = TestNavigationOwner()
        var radius: Dp? = null
        var finished = 0
        val fill = Color(0xFF326498)
        setContent {
            DialogTestHost(owner) {
                val surface = glassDialogSurface(backdrop = null, stroke = null, containerColor = fill)
                OverlayDialog(
                    show = show.value,
                    modifier = Modifier.width(300.dp).testTag("dialog"),
                    backgroundColor = Color.Red,
                    cornerRadius = 44.dp,
                    largeScreen = true,
                    surface = {
                        radius = cornerRadius
                        surface(this)
                    },
                    onDismissRequest = { show.value = false },
                    onDismissFinished = { finished++ },
                ) {
                    TextButton(text = "Close", onClick = { show.value = false })
                }
            }
        }
        waitForIdle()
        val pixels = onNodeWithTag("dialog").captureToImage().toPixelMap()
        assertEquals(44.dp, radius)
        assertColorNear(fill, pixels[8, pixels.height / 2])
        // A replacement surface must still clip its corners.
        assertTrue(pixels[1, 1] != fill)
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
    fun lightMaterialBlendsTheDimmedScene() = assertMaterial(isDark = false, expectedChannel = 206)

    @Test
    fun darkMaterialBlendsTheDimmedScene() = assertMaterial(isDark = true, expectedChannel = 61)

    @Test
    fun dialogActionsTintTheSharedBlurredSurface() = runComposeUiTest {
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(Modifier.fillMaxSize().layerBackdrop(backdrop).drawBehind { drawRect(Color(0xFF808080)) })
                OverlayDialog(
                    show = true,
                    modifier = Modifier.width(340.dp).testTag("dialog"),
                    title = "Glass Dialog",
                    summary = "The actions share the dialog material.",
                    dimColor = GlassDialogDefaults.ScrimColor,
                    largeScreen = true,
                    surface = glassDialogSurface(backdrop = backdrop, stroke = null),
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        GlassDialogButton(onClick = {}, modifier = Modifier.weight(1f)) {
                            Text("Cancel", style = MiuixTheme.textStyles.button)
                        }
                        GlassDialogButton(
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            role = GlassDialogButtonRole.Primary,
                        ) {
                            Text("Confirm", style = MiuixTheme.textStyles.button)
                        }
                    }
                }
            }
        }
        waitForIdle()
        val cancel = onNodeWithText("Cancel").captureToImage().toPixelMap()
        val parentChannel = 206f / 255f
        val expectedTonal = Color(0x0F000000).compositeOver(Color(parentChannel, parentChannel, parentChannel))
        assertColorNear(expectedTonal, cancel[8, cancel.height / 2], tolerance = 4f / 255f)
        val confirm = onNodeWithText("Confirm").captureToImage().toPixelMap()
        assertColorNear(Color(0xFF3482FF), confirm[8, confirm.height / 2])
        saveImage("dialog-with-actions", onRoot().captureToImage())
    }

    private fun assertMaterial(isDark: Boolean, expectedChannel: Int) = runComposeUiTest {
        val dimEnabled = mutableStateOf(true)
        var surfaceState: DialogSurfaceScope? = null
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                val surface = glassDialogSurface(
                    backdrop = backdrop,
                    material = GlassMaterials.dialogGlass(isDark),
                    stroke = null,
                )
                // Match GlassPage: record a nested scaffold while its dialog uses the outer host.
                Scaffold(
                    modifier = Modifier.fillMaxSize().layerBackdrop(backdrop),
                    containerColor = Color(0xFF808080),
                    contentWindowInsets = WindowInsets(0),
                ) {
                    OverlayDialog(
                        show = true,
                        modifier = Modifier.width(300.dp).testTag("dialog"),
                        enableWindowDim = dimEnabled.value,
                        dimColor = GlassDialogDefaults.ScrimColor,
                        largeScreen = true,
                        surface = {
                            surfaceState = this
                            surface(this)
                        },
                    ) {
                        Box(Modifier.height(80.dp))
                    }
                }
            }
        }
        waitForIdle()
        val pixels = onNodeWithTag("dialog").captureToImage().toPixelMap()
        val center = pixels[pixels.width / 2, pixels.height / 2]
        val expected = expectedChannel / 255f
        assertColorNear(Color(expected, expected, expected), center, tolerance = 4f / 255f)
        // The pre-blur scrim must also cover the kernel's padding, all the way to the side.
        assertColorNear(center, pixels[8, pixels.height / 2], tolerance = 4f / 255f)
        saveImage(if (isDark) "dialog-dark" else "dialog-light", onRoot().captureToImage())

        runOnIdle { dimEnabled.value = false }
        waitForIdle()
        val undimmed = onNodeWithTag("dialog").captureToImage().toPixelMap()
        assertTrue(undimmed[undimmed.width / 2, undimmed.height / 2].red > center.red + 0.01f)
        assertEquals(Color.Transparent, surfaceState?.scrimColor?.value)
    }

    @Test
    fun backgroundStaysScreenAlignedDuringTheOpeningScale() = runComposeUiTest {
        mainClock.autoAdvance = false
        val show = mutableStateOf(false)
        var surfaceState: DialogSurfaceScope? = null
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(
                    Modifier
                        .fillMaxSize()
                        .layerBackdrop(backdrop)
                        .drawBehind {
                            drawRect(Color.Red)
                            drawRect(Color.Blue, topLeft = Offset(size.width / 2, 0f), size = Size(size.width / 2, size.height))
                        },
                )
                val surface = glassDialogSurface(
                    backdrop = backdrop,
                    material = GlassMaterial(0.dp, GlassColorLayer(Color.Transparent, GlassColorBlendMode.SrcOver)),
                    stroke = null,
                )
                OverlayDialog(
                    show = show.value,
                    modifier = Modifier.width(300.dp).testTag("dialog"),
                    enableWindowDim = false,
                    largeScreen = true,
                    surface = {
                        surfaceState = this
                        surface(this)
                    },
                ) {
                    Box(
                        Modifier.height(80.dp).drawBehind {
                            drawRect(Color.White, size = Size(16.dp.toPx(), 16.dp.toPx()))
                        },
                    )
                }
            }
        }
        runOnIdle { show.value = true }
        mainClock.advanceTimeBy(64)
        waitForIdle()
        val scale = checkNotNull(surfaceState).scale.value
        assertTrue(scale in 0.8f..0.97f, "Check the surface during its scale animation: $scale")
        val image = onRoot().captureToImage()
        val pixels = image.toPixelMap()
        val left = pixels[pixels.width / 2 - 6, pixels.height / 2]
        val right = pixels[pixels.width / 2 + 6, pixels.height / 2]
        assertTrue(left.red > left.blue, "The scene's red half must remain left of the screen center")
        assertTrue(right.blue > right.red, "The scene's blue half must remain right of the screen center")
        saveImage("dialog-opening", image)
    }
}

@Composable
internal fun DialogTestHost(
    owner: NavigationEventDispatcherOwner = remember { TestNavigationOwner() },
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides owner) {
        MiuixTheme {
            Scaffold(contentWindowInsets = WindowInsets(0)) { content() }
        }
    }
}

internal class TestNavigationOwner : NavigationEventDispatcherOwner {
    override val navigationEventDispatcher = NavigationEventDispatcher()
    val input = DirectNavigationEventInput().also { navigationEventDispatcher.addInput(it) }
}

internal fun assertColorNear(expected: Color, actual: Color, tolerance: Float = 2f / 255f) {
    assertEquals(expected.red, actual.red, tolerance, "red")
    assertEquals(expected.green, actual.green, tolerance, "green")
    assertEquals(expected.blue, actual.blue, tolerance, "blue")
    assertEquals(expected.alpha, actual.alpha, tolerance, "alpha")
}

internal fun saveImage(name: String, bitmap: ImageBitmap) {
    val output = File("build/reports/dialog-material/$name.png")
    output.parentFile.mkdirs()
    Image.makeFromBitmap(bitmap.asSkiaBitmap()).use { image ->
        checkNotNull(image.encodeToData(EncodedImageFormat.PNG)).use { output.writeBytes(it.bytes) }
    }
}
