// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class LayerBackdropCaptureTest {
    @Test
    fun underlayIsDrawnOnlyInTheCapture() = runComposeUiTest {
        setContent {
            val backdrop = rememberLayerBackdrop(
                backgroundColor = Color.Green,
                onDrawUnderlay = { drawRect(Color.Blue, size = Size(12.dp.toPx(), size.height)) },
            )
            Row(Modifier.background(Color.White)) {
                Box(Modifier.size(48.dp).layerBackdrop(backdrop), contentAlignment = Alignment.Center) {
                    Box(Modifier.size(16.dp).background(Color.Red))
                }
                Box(Modifier.size(48.dp).drawBehind { drawLayer(backdrop.graphicsLayer) })
            }
        }

        val pixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.White, pixels[6, 24])
        assertEquals(Color.White, pixels[42, 24])
        assertEquals(Color.Red, pixels[24, 24])
        assertEquals(Color.Blue, pixels[54, 24])
        assertEquals(Color.Green, pixels[90, 24])
        assertEquals(Color.Red, pixels[72, 24])
    }
}
