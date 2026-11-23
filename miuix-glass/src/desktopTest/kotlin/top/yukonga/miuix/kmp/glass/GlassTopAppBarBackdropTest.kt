// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
    fun sceneButtonSamplesItsOwnBackdropWithoutTheBarUnderlay() = assertSceneSampling(isDark = false)

    @Test
    fun sceneButtonSamplesItsOwnBackdropInDarkTheme() = assertSceneSampling(isDark = true)

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

    @Test
    fun progressiveBandWithoutButtonsBlursSmoothlyWhenScrolledAndSkipsAtTop() = runComposeUiTest {
        val offset = mutableFloatStateOf(0f)
        val scrollState = TopAppBarState(-1000f, 0f, 0f)
        setContent {
            DialogTestHost {
                MiuixTheme(colors = lightColorScheme()) {
                    val backdrop = rememberLayerBackdrop()
                    Box(Modifier.size(400.dp, 300.dp)) {
                        Column(Modifier.matchParentSize().layerBackdrop(backdrop)) {
                            repeat(6) { index ->
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                        .background(if (index % 2 == 0) Color.Red else Color.Blue),
                                )
                            }
                        }
                        GlassTopAppBar(
                            title = "",
                            isContentScrolled = offset.floatValue < 0f,
                            progressiveBlur = true,
                            backdrop = backdrop,
                            scrollBehavior = MiuixScrollBehavior(scrollState),
                            defaultWindowInsetsPadding = false,
                        )
                    }
                }
            }
        }
        // At rest (offset == 0f), the progressive band is gated off and shows crisp stripes.
        val atTop = onRoot().captureToImage().toPixelMap()
        val stripe1Y = with(density) { 15.dp.roundToPx() }
        val stripe2Y = with(density) { 45.dp.roundToPx() }
        assertColorNear(Color.Red, atTop[20, stripe1Y], tolerance = 3f / 255f)
        assertColorNear(Color.Blue, atTop[20, stripe2Y], tolerance = 3f / 255f)

        // Scrolled: strong top blur mixes red + blue stripes with surface scrim, while far below stays crisp.
        runOnIdle {
            offset.floatValue = -1000f
            scrollState.contentOffset = -1000f
            scrollState.heightOffset = scrollState.heightOffsetLimit
        }
        val scrolled = onRoot().captureToImage().toPixelMap()
        val topBlurred = scrolled[20, stripe1Y]
        assertTrue(topBlurred.blue > 0.1f && topBlurred.red > 0.1f, "Top progressive blur must blend stripes: $topBlurred")
        val belowBarY = with(density) { 165.dp.roundToPx() }
        assertColorNear(Color.Blue, scrolled[20, belowBarY], tolerance = 3f / 255f)
    }
}
