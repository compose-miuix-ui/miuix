// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.material.GlassShape
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class OversizedGlassTest {
    @Test
    fun tallGlassKeepsChildrenVisibleAcrossTileBoundary() = runComposeUiTest {
        val scroll = ScrollState(1950)
        setContent {
            Box(Modifier.size(100.dp, 180.dp).background(Color.White).verticalScroll(scroll)) {
                Box(
                    Modifier.size(100.dp, 2300.dp)
                        .glass(rememberLayerBackdrop(), GlassShape(16.dp), noiseCoefficient = 0f, shading = false)
                        .background(Color.Red),
                )
            }
        }

        val pixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Red, pixels[50, 97])
        assertEquals(Color.Red, pixels[50, 98])
    }
}
