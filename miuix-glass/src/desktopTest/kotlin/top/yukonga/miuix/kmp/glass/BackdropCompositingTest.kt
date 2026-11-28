// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.BackdropPipeline
import top.yukonga.miuix.kmp.blur.drawBackdrop
import top.yukonga.miuix.kmp.blur.progressiveTextureBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class BackdropCompositingTest {
    @Test
    fun textureBlurIsolatesChildClear() = assertTextureBlurIsolatesChildClear(progressive = false)

    @Test
    fun progressiveTextureBlurIsolatesChildClear() = assertTextureBlurIsolatesChildClear(progressive = true)

    private fun assertTextureBlurIsolatesChildClear(progressive: Boolean) = runComposeUiTest {
        setContent {
            val backdrop = rememberLayerBackdrop()
            val blur = if (progressive) {
                Modifier.progressiveTextureBlur(backdrop, RectangleShape, blurRadius = 0f)
            } else {
                Modifier.textureBlur(backdrop, RectangleShape, blurRadius = 0f)
            }
            Box(Modifier.size(80.dp).background(Color.Green)) {
                Box(Modifier.size(80.dp).then(blur)) {
                    Box(
                        Modifier.size(80.dp).drawWithContent {
                            drawRect(Color.Red)
                            clearStrip()
                        },
                    )
                }
            }
        }

        val pixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Green, pixels[5, 20])
        assertEquals(Color.Red, pixels[25, 20])
    }

    @Test
    fun stablePipelineCanToggleIsolationWithItsMask() = runComposeUiTest {
        val maskEnabled = mutableStateOf(false)
        val pipeline = object : BackdropPipeline {
            override val shape = RectangleShape
            override val requiresOffscreenCompositing: Boolean get() = maskEnabled.value

            override fun configureEffects(scope: BackdropEffectScope) {}

            override fun drawFront(scope: DrawScope) {
                if (maskEnabled.value) scope.clearStrip()
            }
        }
        setContent {
            Box(Modifier.size(80.dp).background(Color.Green)) {
                Box(
                    Modifier.size(80.dp)
                        .drawBackdrop(rememberLayerBackdrop(), pipeline)
                        .background(Color.Red),
                )
            }
        }

        assertEquals(Color.Red, onRoot().captureToImage().toPixelMap()[5, 20])
        runOnIdle { maskEnabled.value = true }
        val masked = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Green, masked[5, 20])
        assertEquals(Color.Red, masked[25, 20])
        runOnIdle { maskEnabled.value = false }
        assertEquals(Color.Red, onRoot().captureToImage().toPixelMap()[5, 20])
    }

    private fun DrawScope.clearStrip() {
        drawRect(Color.Black, size = Size(10.dp.toPx(), size.height), blendMode = BlendMode.Clear)
    }
}
