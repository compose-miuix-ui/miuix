// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import kotlin.math.roundToInt
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class GlassPopupMaterialTest {

    @Test
    fun expandedMenuSamplesThePageInsteadOfTheButtonBackdrop() = assertExpandedAppearance(
        menuColor = Color.Transparent,
        expected = Color.Red,
    )

    @Test
    fun expandedMenuUsesItsOwnMaterialAndReturnsToTheOriginalButton() = assertExpandedAppearance(
        menuColor = Color.Green,
        expected = Color.Green,
    )

    @Test
    fun secondaryMenuSharesThePrimaryMenusExpandedMaterial() = assertExpandedAppearance(
        menuColor = Color.Green,
        expected = Color.Green,
        secondary = true,
    )

    @Test
    fun materialRimFollowsTheShrinkingPanelWhileRowsKeepTheirSize() = runComposeUiTest {
        var panel = Rect.Zero
        setContent {
            DialogTestHost {
                Box(Modifier.size(500.dp)) {
                    GlassPopupSurface(
                        onDismissRequest = {},
                        backdrop = null,
                        sizing = GlassPopupSizing(),
                        visuals = GlassPopupDefaults.visuals(
                            containerColor = Color.Green,
                            stroke = GlassStrokes.SmallLight.copy(width = 4f, color = Color.White),
                            shadow = null,
                        ),
                        contentPadding = PaddingValues(0.dp),
                        panelLayer = {},
                        overlay = {},
                        frame = { _, _ ->
                            panel = Rect(80.dp.toPx(), 80.dp.toPx(), 160.dp.toPx(), 160.dp.toPx())
                            GlassPopupFrame(panel, 0.dp)
                        },
                        contentLayer = { _, _ -> },
                    ) {
                        Box(Modifier.size(160.dp, 100.dp))
                    }
                }
            }
        }
        waitForIdle()
        val pixels = onRoot().captureToImage().toPixelMap()
        assertColorNear(Color.Green, pixels[panel.center.x.roundToInt(), panel.center.y.roundToInt()])
        assertColorNear(Color.White, pixels[panel.right.roundToInt() - 2, panel.center.y.roundToInt()])
        assertColorNear(Color.White, pixels[panel.center.x.roundToInt(), panel.bottom.roundToInt() - 2])
    }

    private fun assertExpandedAppearance(menuColor: Color, expected: Color, secondary: Boolean = false) = runComposeUiTest {
        val show = mutableStateOf(false)
        val showSecondary = mutableStateOf(false)
        setContent {
            DialogTestHost {
                val buttonBackdrop = rememberLayerBackdrop()
                val pageBackdrop = rememberLayerBackdrop()
                val anchor = rememberGlassPopupAnchor()
                Box(Modifier.size(500.dp)) {
                    Box(Modifier.fillMaxSize().layerBackdrop(buttonBackdrop).background(Color.Blue))
                    Box(Modifier.fillMaxSize().layerBackdrop(pageBackdrop).background(Color.Red)) {
                        GlassIconButton(
                            onClick = { show.value = true },
                            backdrop = buttonBackdrop,
                            modifier = Modifier.offset(350.dp, 60.dp)
                                .glassPopupAnchor(anchor, 20.dp)
                                .testTag("button"),
                            stroke = null,
                            shadow = null,
                        ) { }
                    }
                    GlassTransformPopup(
                        show = show.value,
                        onDismissRequest = { show.value = false },
                        anchor = anchor,
                        backdrop = pageBackdrop,
                        anchorContent = {},
                        stacked = showSecondary.value,
                        visuals = GlassPopupDefaults.visuals(
                            material = GlassMaterial(0.dp, GlassColorLayer(menuColor, GlassColorBlendMode.SrcOver)),
                            stroke = null,
                            shadow = null,
                        ),
                    ) {
                        Box(Modifier.size(160.dp, 80.dp).testTag("primary-content"))
                    }
                    GlassSecondaryPopup(
                        show = showSecondary.value,
                        onDismissRequest = { showSecondary.value = false },
                        anchorBounds = Rect(220f, 160f, 400f, 210f),
                        backdrop = pageBackdrop,
                        materialAnchor = anchor,
                        visuals = GlassPopupDefaults.visuals(
                            material = GlassMaterial(0.dp, GlassColorLayer(Color.Magenta, GlassColorBlendMode.SrcOver)),
                            stroke = null,
                            shadow = null,
                        ),
                    ) {
                        Box(Modifier.size(160.dp, 80.dp).testTag("secondary-content"))
                    }
                }
            }
        }
        waitForIdle()
        val buttonBefore = onNodeWithTag("button").captureToImage().toPixelMap().let { it[it.width / 2, it.height / 2] }
        onNodeWithTag("button").performClick()
        waitForIdle()
        if (secondary) {
            runOnIdle { showSecondary.value = true }
            waitForIdle()
        }
        val content = if (secondary) "secondary-content" else "primary-content"
        val pixels = onNodeWithTag(content).captureToImage().toPixelMap()
        assertColorNear(expected, pixels[pixels.width / 2, pixels.height / 2])

        runOnIdle {
            showSecondary.value = false
            show.value = false
        }
        waitForIdle()
        onNodeWithTag(content).assertDoesNotExist()
        val buttonAfter = onNodeWithTag("button").captureToImage().toPixelMap()
        assertColorNear(buttonBefore, buttonAfter[buttonAfter.width / 2, buttonAfter.height / 2])
    }
}
