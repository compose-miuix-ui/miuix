// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.skiaCanvas
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.test.v2.runSkikoComposeUiTest
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.BackdropPipeline
import top.yukonga.miuix.kmp.blur.drawBackdrop
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.runtimeShaderEffect
import top.yukonga.miuix.kmp.material.GlassColorBlendMode
import top.yukonga.miuix.kmp.material.GlassColorLayer
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassShape
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class OversizedGlassTest {
    @Test
    fun wideGlassTintSamplesBeyondTheFirstEffectTile() = runSkikoComposeUiTest(Size(4500f, 180f), Density(3f)) {
        setContent {
            val backdrop = rememberLayerBackdrop()
            Box(Modifier.size(1500.dp, 60.dp)) {
                Box(
                    Modifier.matchParentSize().layerBackdrop(backdrop).drawBehind {
                        drawRect(Color.Black)
                        drawRect(Color.White, topLeft = Offset(2286f, 0f), size = Size(size.width - 2286f, size.height))
                    },
                )
                Box(
                    Modifier.matchParentSize().glass(
                        backdrop,
                        GlassShape(0.dp),
                        material = GlassMaterial(0.dp, GlassColorLayer(Color.Transparent, GlassColorBlendMode.SrcOver)),
                        noiseCoefficient = 0f,
                    ),
                )
            }
        }

        val pixels = onRoot().captureToImage().toPixelMap()
        val left = pixels[2047, 90].red
        val right = pixels[2048, 90].red
        assertTrue(abs(left - right) <= 1f / 255f, "Wide tint changed across the tile boundary: $left to $right")
        assertTrue(left > pixels[100, 90].red + 1f / 255f, "The tint should pick up the distant white backdrop")
    }

    @Test
    fun tallBackdropFiltersCapturedPixelsInSurfaceCoordinatesAcrossTiles() = runComposeUiTest {
        val scroll = ScrollState(3998)
        val recordingClipHeights = mutableListOf<Float>()
        val effects: BackdropEffectScope.() -> Unit = {
            runtimeShaderEffect(
                "OversizedBackdropCoordinates",
                """
                uniform shader child;
                half4 main(float2 coord) {
                    half4 color = child.eval(coord);
                    return coord.y < 4096.0 ? half4(color.gbr, color.a) : half4(color.brg, color.a);
                }
                """.trimIndent(),
                "child",
            ) {}
        }
        setContent {
            val backdrop = rememberLayerBackdrop()
            Box(Modifier.size(100.dp, 180.dp)) {
                Box(Modifier.matchParentSize().layerBackdrop(backdrop).background(Color.Red))
                Box(Modifier.matchParentSize().verticalScroll(scroll)) {
                    Box(
                        Modifier.size(100.dp, 4350.dp).drawBackdrop(
                            backdrop = backdrop,
                            shape = { RectangleShape },
                            effects = effects,
                            onDrawBackdrop = { drawBackdrop ->
                                val clip = drawContext.canvas.skiaCanvas.localClipBounds
                                recordingClipHeights.add(clip.bottom - clip.top)
                                drawBackdrop()
                            },
                            onDrawFront = {},
                        ),
                    )
                }
            }
        }

        val pixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Blue, pixels[50, 97])
        assertEquals(Color.Green, pixels[50, 98])
        // Skia's clip bounds include a one-pixel outset on each edge.
        assertTrue(recordingClipHeights.isNotEmpty())
        assertTrue(recordingClipHeights.all { it <= 4098f }, "Recording clips were $recordingClipHeights")
    }

    @Test
    fun tallGlassKeepsChildrenVisibleAcrossTileBoundary() = runComposeUiTest {
        val scroll = ScrollState(3998)
        setContent {
            Box(Modifier.size(100.dp, 180.dp).background(Color.White).verticalScroll(scroll)) {
                Box(
                    Modifier.size(100.dp, 4350.dp)
                        .glass(rememberLayerBackdrop(), GlassShape(16.dp), noiseCoefficient = 0f, shading = false)
                        .background(Color.Red),
                )
            }
        }

        val pixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Red, pixels[50, 97])
        assertEquals(Color.Red, pixels[50, 98])
    }

    @Test
    fun tallBackdropPipelineClearPreservesParentPixels() = runComposeUiTest {
        val scroll = ScrollState(3998)
        val pipeline = object : BackdropPipeline {
            override val shape = RectangleShape

            override fun configureEffects(scope: BackdropEffectScope) {}

            override fun drawFront(scope: DrawScope) = with(scope) {
                drawRect(Color.Black, size = Size(10.dp.toPx(), size.height), blendMode = BlendMode.Clear)
            }
        }
        setContent {
            Box(Modifier.size(100.dp, 180.dp).background(Color.Green).verticalScroll(scroll)) {
                Box(
                    Modifier.size(100.dp, 4350.dp)
                        .drawBackdrop(rememberLayerBackdrop(), pipeline)
                        .background(Color.Red),
                )
            }
        }

        val pixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.Green, pixels[5, 97])
        assertEquals(Color.Green, pixels[5, 98])
        assertEquals(Color.Red, pixels[25, 97])
        assertEquals(Color.Red, pixels[25, 98])
    }

    @Test
    fun tallBackdropMasksTheSurfaceAcrossTileBoundary() = runComposeUiTest {
        val scroll = ScrollState(3998)
        setContent {
            Box(Modifier.size(100.dp, 180.dp).background(Color.White).verticalScroll(scroll)) {
                Box(
                    Modifier.size(100.dp, 4350.dp).drawBackdrop(
                        backdrop = rememberLayerBackdrop(),
                        shape = { RectangleShape },
                        effects = {},
                        onDrawSurface = { drawRect(Color.Red) },
                        onDrawFront = { drawRect(Color.Black, size = Size(10.dp.toPx(), size.height), blendMode = BlendMode.Clear) },
                        contentBlendMode = BlendMode.DstIn,
                    ),
                ) {
                    Box(Modifier.width(50.dp).fillMaxHeight().background(Color.Black))
                }
            }
        }

        val pixels = onRoot().captureToImage().toPixelMap()
        assertEquals(Color.White, pixels[5, 97])
        assertEquals(Color.White, pixels[5, 98])
        assertEquals(Color.Red, pixels[25, 97])
        assertEquals(Color.Red, pixels[25, 98])
        assertEquals(Color.White, pixels[75, 97])
        assertEquals(Color.White, pixels[75, 98])
    }
}
