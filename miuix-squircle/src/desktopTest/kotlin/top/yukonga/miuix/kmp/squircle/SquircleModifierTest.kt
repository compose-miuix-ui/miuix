// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.squircle

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class SquircleModifierTest {

    @Test
    fun ultraTallSquircleSurfaceCardRemainsClickableBeforeAndAfterGrowth() = runComposeUiTest {
        val tall = mutableStateOf(false)
        var topClicks = 0
        var bottomClicks = 0

        setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f)) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .size(width = 300.dp, height = 600.dp)
                        .verticalScroll(scrollState),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .squircleSurface(color = Color.Blue, cornerRadius = 24.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .testTag("top_item")
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable { topClicks++ },
                        )
                        Spacer(Modifier.height(if (tall.value) 5200.dp else 100.dp))
                        Box(
                            modifier = Modifier
                                .testTag("bottom_item")
                                .fillMaxWidth()
                                .height(60.dp)
                                .clickable { bottomClicks++ },
                        )
                    }
                }
            }
        }

        // Normal height (<= MAX_OFFSCREEN_PX): both items clickable inside Offscreen layer.
        onNodeWithTag("top_item").performClick()
        onNodeWithTag("bottom_item").performClick()
        assertEquals(1, topClicks)
        assertEquals(1, bottomClicks)

        // Expand past MAX_OFFSCREEN_PX (height > 5300 px > 4096 px) and verify bottom item clickability.
        runOnIdle { tall.value = true }
        onNodeWithTag("top_item").performClick()
        onNodeWithTag("bottom_item").performScrollTo().performClick()
        assertEquals(2, topClicks)
        assertEquals(2, bottomClicks)
    }

    @Test
    fun rtlFlipsRelativeSquircleCornersButPreservesAbsoluteCorners() = runComposeUiTest {
        val useAbsolute = mutableStateOf(false)

        setContent {
            CompositionLocalProvider(
                LocalDensity provides Density(1f),
                LocalLayoutDirection provides LayoutDirection.Rtl,
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White)
                        .then(
                            if (useAbsolute.value) {
                                Modifier.absoluteSquircleSurface(
                                    color = Color.Red,
                                    topLeft = 28.dp,
                                    topRight = 0.dp,
                                    bottomRight = 0.dp,
                                    bottomLeft = 0.dp,
                                )
                            } else {
                                Modifier.squircleSurface(
                                    color = Color.Red,
                                    topStart = 28.dp,
                                    topEnd = 0.dp,
                                    bottomEnd = 0.dp,
                                    bottomStart = 0.dp,
                                )
                            },
                        ),
                )
            }
        }

        // Relative topStart in RTL maps to physical top-right (carved to White); physical top-left stays sharp Red.
        val rtlPixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Red, rtlPixels[2, 2], "Physical top-left (topEnd in RTL) should be sharp")
        assertEquals(Color.White, rtlPixels[77, 2], "Physical top-right (topStart in RTL) should be carved")

        // Absolute topLeft stays anchored to physical top-left regardless of RTL.
        runOnIdle { useAbsolute.value = true }
        val absPixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.White, absPixels[2, 2], "Physical top-left should be carved for absoluteSquircleSurface")
        assertEquals(Color.Red, absPixels[77, 2], "Physical top-right should stay sharp for absoluteSquircleSurface")
    }

    @Test
    fun capsuleFastPathClipsChildOverflowAndBorderUpdatesColor() = runComposeUiTest {
        val borderColor = mutableStateOf(Color.Green)

        setContent {
            CompositionLocalProvider(LocalDensity provides Density(1f)) {
                Box(
                    modifier = Modifier
                        .size(width = 120.dp, height = 40.dp)
                        .background(Color.White),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .squircleSurface(color = Color.Blue, cornerRadius = 20.dp)
                            .squircleBorder(width = 4.dp, color = borderColor.value, cornerRadius = 20.dp),
                    ) {
                        // Top-left overflow box: covers (1, 1) to test capsule clipping while leaving x=60 border unobstructed.
                        Box(Modifier.size(24.dp).background(Color.Red))
                    }
                }
            }
        }

        val pixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.White, pixels[1, 1], "Capsule fast-path must clip child overflow at outer corner")
        assertEquals(Color.Red, pixels[14, 14], "Inside of child box within capsule must render Red")
        assertEquals(Color.Green, pixels[60, 1], "Unobstructed top border stroke must initially render Green")
        assertEquals(Color.Blue, pixels[60, 20], "Center of capsule must render surface fill")

        runOnIdle { borderColor.value = Color.Black }
        val updated = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Black, updated[60, 1], "Border color update must invalidate draw and render Black")
    }

    @Test
    fun wideNearCapsuleEndCapsAndNonFiniteParametersRenderCleanly() = runComposeUiTest {
        val deferredColor = mutableStateOf(Color.Green)
        val deferredWidth = mutableStateOf(4.dp)
        val squircleEnabled = mutableStateOf(true)

        setContent {
            CompositionLocalProvider(
                LocalDensity provides Density(1f),
                LocalSquircleEnabled provides squircleEnabled.value,
            ) {
                Column(Modifier.background(Color.White)) {
                    // 18 * 1.1 = 19.8 < 20 (halfMin): exercises topH (20) >= bottomBandTop (20) left/right end-cap split.
                    Box(
                        modifier = Modifier
                            .size(width = 200.dp, height = 40.dp)
                            .squircleSurface(color = Color.Blue, cornerRadius = 18.dp, extension = Float.NaN),
                    )
                    // Unspecified / negative Dp and property-reference lambdas on deferred squircleBorder.
                    Box(
                        modifier = Modifier
                            .size(width = 100.dp, height = 40.dp)
                            .squircleBackground(color = Color.Red, cornerRadius = Dp.Unspecified, extension = Float.NaN)
                            .squircleClip(cornerRadius = (-8).dp, extension = Float.NaN)
                            .squircleBorder(
                                width = deferredWidth::value,
                                color = deferredColor::value,
                                cornerRadius = Dp.Unspecified,
                                extension = Float.NaN,
                            ),
                    )
                    // 80x80 circle with Dp.Infinity radius: verifies circle-blended border aligns inside CircleShape clip
                    // and Dp.Infinity does not crash RoundedCornerShape when LocalSquircleEnabled = false.
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .squircleSurface(color = Color.Blue, cornerRadius = Dp.Infinity)
                            .squircleBorder(width = 6.dp, color = Color.Green, cornerRadius = Dp.Infinity),
                    )
                }
            }
        }

        val initial = onRoot().captureToImage().toPixelMap()
        // Wide near-capsule row (y = 0..39): corners carved to White, top/center middle untouched Blue.
        assertEquals(Color.White, initial[1, 1])
        assertEquals(Color.White, initial[198, 1])
        assertEquals(Color.White, initial[1, 38])
        assertEquals(Color.White, initial[198, 38])
        assertEquals(Color.Blue, initial[100, 1])
        assertEquals(Color.Blue, initial[100, 20])

        // Second row (y = 40..79): Dp.Unspecified radius degrades to sharp rectangle with Green 4px border over Red fill.
        assertEquals(Color.Green, initial[50, 41])
        assertEquals(Color.Red, initial[50, 60])

        // Third row (y = 80..159): 80x80 circle centered at (40, 120); (14, 94) is r ≈ 36.8 px (inside 6px Green stroke [34, 40]).
        assertEquals(Color.White, initial[2, 82])
        assertEquals(Color.Green, initial[14, 94], "45-degree diagonal of circular border must align inside CircleShape clip")
        assertEquals(Color.Blue, initial[40, 120])

        // Deferred border updates via bound property reference + LocalSquircleEnabled=false fallback with Dp.Infinity.
        runOnIdle {
            deferredColor.value = Color.Black
            deferredWidth.value = Dp.Unspecified
            squircleEnabled.value = false
        }
        val afterDisabledAndUnspecified = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Red, afterDisabledAndUnspecified[50, 41], "Dp.Unspecified deferred width must skip drawing border")
        assertEquals(Color.White, afterDisabledAndUnspecified[2, 82], "Dp.Infinity RoundedCornerShape fallback must clip cleanly")
        assertEquals(Color.Green, afterDisabledAndUnspecified[14, 94])

        runOnIdle {
            deferredWidth.value = 4.dp
            squircleEnabled.value = true
        }
        val afterRestoredWidth = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Black, afterRestoredWidth[50, 41], "Restoring finite deferred width must draw Black border")
    }
}
