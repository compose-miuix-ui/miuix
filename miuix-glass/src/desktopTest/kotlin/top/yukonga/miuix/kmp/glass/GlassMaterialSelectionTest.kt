// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassMaterialSelectionTest {
    @Test
    fun topBarButtonUpdatesItsMaterialAndInheritedStroke() = assertButtonUpdates(inheritTopBarMaterial = true)

    @Test
    fun sceneButtonUpdatesItsMaterialAndInheritedStroke() = assertButtonUpdates(inheritTopBarMaterial = false)

    @Test
    fun topBarTabsRespectChangesToTheirOwnBlurRadius() = assertTabBlurUpdates(segmented = false)

    @Test
    fun topBarSegmentedTabsRespectChangesToTheirOwnBlurRadius() = assertTabBlurUpdates(segmented = true)

    @Test
    fun standaloneButtonKeepsBlurWhenMaterialAlphaIsReduced() = runComposeUiTest {
        val alpha = mutableFloatStateOf(1f)
        setContent {
            DialogTestHost {
                MiuixTheme(colors = lightColorScheme()) {
                    val backdrop = rememberLayerBackdrop()
                    val material = GlassMaterial(30.dp, GlassColorLayer(Color.Red, GlassColorBlendMode.SrcOver))
                    Box(Modifier.size(200.dp, 250.dp)) {
                        Canvas(Modifier.matchParentSize().layerBackdrop(backdrop)) {
                            drawRect(Color.Black)
                            drawRect(Color.White, Offset(55.dp.toPx(), 0f), Size(10.dp.toPx(), size.height))
                        }
                        GlassIconButton(
                            onClick = {},
                            backdrop = backdrop,
                            modifier = Modifier.offset(40.dp, 40.dp).testTag("button"),
                            surfaceAlpha = alpha.value,
                            size = 40.dp,
                            material = material,
                            stroke = null,
                            shadow = null,
                        ) { }
                        Box(
                            Modifier.offset(40.dp, 140.dp).size(40.dp)
                                .glass(
                                    backdrop = backdrop,
                                    shape = GlassShape(20.dp),
                                    alpha = alpha.value,
                                    material = material,
                                    shading = false,
                                ).testTag("reference"),
                        )
                    }
                }
            }
        }
        fun center(tag: String): Color {
            val pixels = onNodeWithTag(tag).captureToImage().toPixelMap()
            return pixels[pixels.width / 2, pixels.height / 2]
        }
        assertColorNear(Color.Red, center("button"))
        for (value in listOf(0.25f, 0f, 1f)) {
            runOnIdle { alpha.value = value }
            assertColorNear(center("reference"), center("button"))
        }
    }

    private fun assertButtonUpdates(inheritTopBarMaterial: Boolean) = runComposeUiTest {
        val material = mutableStateOf<GlassMaterial?>(null)
        val stroke = mutableStateOf<GlassStroke?>(null)
        setContent {
            DialogTestHost {
                MiuixTheme(colors = lightColorScheme()) {
                    val backdrop = rememberLayerBackdrop()
                    Box(Modifier.size(400.dp, 200.dp)) {
                        Box(Modifier.matchParentSize().layerBackdrop(backdrop).background(Color.Blue))
                        GlassTopAppBar(
                            title = "",
                            isContentScrolled = true,
                            backdrop = backdrop,
                            bandBrush = SolidColor(Color.Transparent),
                            buttonShadow = null,
                            stroke = stroke.value,
                            defaultWindowInsetsPadding = false,
                            navigationIcon = {
                                GlassIconButton(
                                    onClick = {},
                                    modifier = Modifier.testTag("button"),
                                    inheritTopBarMaterial = inheritTopBarMaterial,
                                    backdrop = backdrop,
                                    material = material.value,
                                ) { }
                            },
                        )
                    }
                }
            }
        }
        fun centerColor(): Color {
            val pixels = onNodeWithTag("button").captureToImage().toPixelMap()
            return pixels[pixels.width / 2, pixels.height / 2]
        }
        val initial = centerColor()
        runOnIdle { material.value = GlassMaterial(0.dp, GlassColorLayer(Color.Red, GlassColorBlendMode.SrcOver)) }
        assertColorNear(Color.Red, centerColor())
        runOnIdle { material.value = GlassMaterial(60.dp, GlassColorLayer(Color.Green, GlassColorBlendMode.SrcOver)) }
        assertColorNear(Color.Green, centerColor())
        runOnIdle { stroke.value = GlassStrokes.SmallLight.copy(width = 6f, color = Color.Magenta) }
        val rim = onNodeWithTag("button").captureToImage().toPixelMap()
        assertTrue(rim[2, rim.height / 2].blue > 0.5f, "Changing the bar stroke must update the button rim")
        runOnIdle {
            material.value = null
            stroke.value = null
        }
        assertColorNear(initial, centerColor())
    }

    private fun assertTabBlurUpdates(segmented: Boolean) = runComposeUiTest {
        val radius = mutableStateOf(0.dp)
        setContent {
            DialogTestHost {
                MiuixTheme(colors = lightColorScheme()) {
                    val backdrop = rememberLayerBackdrop()
                    val material = GlassMaterial(radius.value, GlassColorLayer(Color.Transparent, GlassColorBlendMode.SrcOver))
                    Box(Modifier.size(400.dp, 300.dp)) {
                        Canvas(Modifier.matchParentSize().layerBackdrop(backdrop)) {
                            drawRect(Color.Black)
                            drawRect(
                                Color.White,
                                topLeft = Offset(size.width * 0.75f - 10.dp.toPx(), 0f),
                                size = Size(20.dp.toPx(), size.height),
                            )
                        }
                        GlassTopAppBar(
                            title = "",
                            isContentScrolled = true,
                            backdrop = backdrop,
                            bandBrush = SolidColor(Color.Transparent),
                            defaultWindowInsetsPadding = false,
                            bottomContent = {
                                if (segmented) {
                                    GlassSegmentedTabRow(
                                        tabs = listOf("First", "Second"),
                                        selectedIndex = 0,
                                        onSelect = {},
                                        backdrop = backdrop,
                                        modifier = Modifier.testTag("tabs"),
                                        material = material,
                                        stroke = null,
                                        shadow = null,
                                    )
                                } else {
                                    GlassTabRow(
                                        tabs = listOf("First", "Second"),
                                        selectedIndex = 0,
                                        onSelect = {},
                                        backdrop = backdrop,
                                        modifier = Modifier.testTag("tabs"),
                                        material = material,
                                        stroke = null,
                                        shadow = null,
                                    )
                                }
                            },
                        )
                    }
                }
            }
        }
        fun stripeColor(): Color {
            val bounds = onNodeWithTag("tabs").fetchSemanticsNode().boundsInRoot
            val pixels = onRoot().captureToImage().toPixelMap()
            return pixels[(bounds.left + bounds.width * 0.75f).roundToInt(), (bounds.top + bounds.height * 0.2f).roundToInt()]
        }
        val before = stripeColor()
        runOnIdle { radius.value = 80.dp }
        val after = stripeColor()
        assertTrue(before.red > after.red + 0.02f, "Increasing blur must soften the bright stripe under a top-bar tab: $before -> $after")
    }
}
