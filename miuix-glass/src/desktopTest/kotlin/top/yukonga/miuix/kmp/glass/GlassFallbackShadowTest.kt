// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassFallbackShadowTest {
    @Test
    fun shadowFadesWithoutFadingOrClippingContent() = runComposeUiTest {
        val opacity = mutableFloatStateOf(1f)
        setContent {
            Box(Modifier.size(240.dp).background(Color.White)) {
                Box(
                    Modifier.offset(60.dp, 60.dp).size(120.dp)
                        .fallbackGlassShadow(GlassShape(20.dp), GlassShadows.Regular) { opacity.floatValue }
                        .background(Color.Red)
                        .drawBehind {
                            drawRect(Color.Green, topLeft = Offset(-10.dp.toPx(), 50.dp.toPx()), size = Size(20.dp.toPx(), 20.dp.toPx()))
                        },
                )
            }
        }
        fun darkness(): Float {
            val pixels = onRoot().captureToImage().toPixelMap()
            var total = 0f
            // Sample all four edges, away from corners and the overflowing green rectangle.
            for (distance in 1..20) {
                total += 1f - pixels[120, 60 - distance].red
                total += 1f - pixels[120, 179 + distance].red
                total += 1f - pixels[60 - distance, 90].red
                total += 1f - pixels[179 + distance, 90].red
            }
            if (opacity.floatValue == 1f) {
                assertTrue(pixels[120, 58].red < 1f, "Shadow must cover the top edge")
                assertTrue(pixels[120, 181].red < 1f, "Shadow must cover the bottom edge")
                assertTrue(pixels[58, 90].red < 1f, "Shadow must cover the left edge")
                assertTrue(pixels[181, 90].red < 1f, "Shadow must cover the right edge")
            }
            assertEquals(Color.Red, pixels[100, 100])
            assertEquals(Color.Green, pixels[55, 115], "The shadow modifier must not clip overflowing content")
            return total
        }
        val visible = darkness()
        assertTrue(visible > 0f, "Fallback shadow must extend outside the surface")
        runOnIdle { opacity.floatValue = 0.5f }
        val partial = darkness()
        assertTrue(partial < visible, "Shadow opacity must follow its animation")
        runOnIdle { opacity.floatValue = 0f }
        assertEquals(0f, darkness(), 0.001f, "Zero opacity must remove only the shadow")
    }
}
