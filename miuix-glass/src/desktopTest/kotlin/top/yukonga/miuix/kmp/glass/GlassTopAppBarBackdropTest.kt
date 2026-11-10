// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
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
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.TopAppBarState
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.material.GlassColorBlendMode
import top.yukonga.miuix.kmp.material.GlassColorLayer
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.darkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassTopAppBarBackdropTest {
    @Test
    fun explicitOverlapKeepsTheButtonMaterialOverAnUnscrolledHero() = runComposeUiTest {
        val overlapping = mutableStateOf(true)
        val scrollState = TopAppBarState(-1000f, 0f, 0f)
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                Box(Modifier.size(400.dp, 200.dp)) {
                    Box(Modifier.matchParentSize().layerBackdrop(backdrop).background(Color.Blue))
                    GlassTopAppBar(
                        title = "",
                        isContentScrolled = overlapping.value,
                        progressiveBlur = true,
                        backdrop = backdrop,
                        scrollBehavior = MiuixScrollBehavior(scrollState),
                        material = GlassMaterial(0.dp, GlassColorLayer(Color.Green, GlassColorBlendMode.SrcOver)),
                        backgroundAlpha = { 0f },
                        buttonShadow = null,
                        stroke = null,
                        defaultWindowInsetsPadding = false,
                        navigationIcon = {
                            GlassIconButton(onClick = {}, modifier = Modifier.testTag("hero-button")) { }
                        },
                    )
                }
            }
        }
        fun buttonColor(): Color {
            val image = onNodeWithTag("hero-button").captureToImage().toPixelMap()
            return image[image.width / 2, image.height / 2]
        }
        assertColorNear(Color.Green, buttonColor())
        runOnIdle { overlapping.value = false }
        assertColorNear(Color.Blue, buttonColor())
    }

    @Test
    fun progressiveButtonsSampleTheRenderedBandAndFollowItsOpacity() = assertProgressiveSampling(isDark = false)

    @Test
    fun progressiveButtonsSampleTheRenderedBandInDarkTheme() = assertProgressiveSampling(isDark = true)

    @Test
    fun sceneButtonSamplesItsOwnBackdropWithoutTheBarUnderlay() = assertSceneSampling(isDark = false)

    @Test
    fun sceneButtonSamplesItsOwnBackdropInDarkTheme() = assertSceneSampling(isDark = true)

    private fun assertProgressiveSampling(isDark: Boolean) = runComposeUiTest {
        val progressive = mutableStateOf(true)
        val bandAlpha = mutableFloatStateOf(1f)
        setContent {
            DialogTestHost {
                MiuixTheme(colors = if (isDark) darkColorScheme() else lightColorScheme()) {
                    val backdrop = rememberLayerBackdrop()
                    Box(Modifier.size(500.dp)) {
                        Box(Modifier.matchParentSize().layerBackdrop(backdrop).background(Color.Blue))
                        GlassTopAppBar(
                            title = "",
                            isContentScrolled = true,
                            progressiveBlur = progressive.value,
                            backdrop = backdrop,
                            bandBrush = SolidColor(Color.Transparent),
                            material = GlassMaterial(0.dp, GlassColorLayer(Color.Transparent, GlassColorBlendMode.SrcOver)),
                            backgroundAlpha = { bandAlpha.floatValue },
                            buttonShadow = null,
                            stroke = null,
                            defaultWindowInsetsPadding = false,
                            navigationIcon = {
                                GlassIconButton(onClick = {}, modifier = Modifier.testTag("progressive-button")) { }
                            },
                            bottomContent = {
                                GlassTabRow(
                                    tabs = listOf("First", "Second"),
                                    selectedIndex = 0,
                                    onSelect = {},
                                    backdrop = backdrop,
                                    modifier = Modifier.testTag("tabs"),
                                    stroke = null,
                                    shadow = null,
                                )
                            },
                        )
                    }
                }
            }
        }
        fun buttonColor(): Color {
            val center = onNodeWithTag("progressive-button").fetchSemanticsNode().boundsInRoot.center
            return onRoot().captureToImage().toPixelMap()[center.x.roundToInt(), center.y.roundToInt()]
        }
        fun tabColor(): Color {
            val bounds = onNodeWithTag("tabs").fetchSemanticsNode().boundsInRoot
            return onRoot().captureToImage().toPixelMap()[(bounds.left + bounds.width * 0.75f).roundToInt(), (bounds.top + bounds.height * 0.2f).roundToInt()]
        }
        val button = onNodeWithTag("progressive-button").fetchSemanticsNode().boundsInRoot
        val pixels = onRoot().captureToImage().toPixelMap()
        val band = pixels[(button.right + button.width).roundToInt(), button.center.y.roundToInt()]
        assertColorNear(band, buttonColor(), tolerance = 3f / 255f)
        assertTrue(abs(band.blue - Color.Blue.blue) > 0.1f || band.red > 0.1f, "The visible band must differ from the raw blue page")
        val tabBefore = tabColor()
        runOnIdle { bandAlpha.floatValue = 0f }
        assertColorNear(Color.Blue, buttonColor(), tolerance = 3f / 255f)
        runOnIdle { progressive.value = false }
        assertColorNear(tabBefore, tabColor())
    }

    private fun assertSceneSampling(isDark: Boolean) = runComposeUiTest {
        setContent {
            DialogTestHost {
                MiuixTheme(colors = if (isDark) darkColorScheme() else lightColorScheme()) {
                    val page = rememberLayerBackdrop()
                    val scene = rememberLayerBackdrop()
                    Box(Modifier.size(500.dp)) {
                        Box(Modifier.matchParentSize().layerBackdrop(page).background(Color.Red))
                        Box(Modifier.matchParentSize().layerBackdrop(scene).background(Color.Blue))
                        GlassTopAppBar(
                            title = "Title",
                            isContentScrolled = true,
                            backdrop = page,
                            bandBrush = SolidColor(Color.Transparent),
                            buttonShadow = null,
                            stroke = null,
                            defaultWindowInsetsPadding = false,
                            navigationIcon = {
                                GlassIconButton(
                                    onClick = {},
                                    modifier = Modifier.testTag("scene-button"),
                                    inheritTopBarMaterial = false,
                                    backdrop = scene,
                                ) { }
                            },
                            actions = {
                                GlassIconButton(
                                    onClick = {},
                                    modifier = Modifier.testTag("inherited-button"),
                                    backdrop = scene,
                                ) { }
                            },
                        )
                        GlassIconButton(
                            onClick = {},
                            modifier = Modifier.offset(20.dp, 200.dp).testTag("reference-button"),
                            backdrop = scene,
                            stroke = null,
                            shadow = null,
                        ) { }
                    }
                }
            }
        }
        val image = onRoot().captureToImage().toPixelMap()
        fun centerColor(tag: String): Color {
            val center = onNodeWithTag(tag).fetchSemanticsNode().boundsInRoot.center
            return image[center.x.roundToInt(), center.y.roundToInt()]
        }
        val actual = centerColor("scene-button")
        val reference = centerColor("reference-button")
        assertColorNear(reference, actual)
        assertTrue(actual.blue > actual.red + 0.05f, "Scene buttons must sample blue, not the original red page: $actual")
        val inherited = centerColor("inherited-button")
        assertTrue(inherited.red > inherited.blue + 0.05f, "Default buttons must retain the bar's red backdrop and material: $inherited")
    }
}
