// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.shapes.RoundedRectangle
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlendMode
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.foregroundBlur
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.miuixShape
import kotlin.math.roundToInt

fun LazyListScope.blurSection() {
    item(key = "blur") {
        SmallTitle(text = "Texture Blur")
        BlurDemo()
    }
    item(key = "foreground_blur") {
        SmallTitle(text = "Foreground Blur")
        ForegroundBlurDemo()
    }
}

@Composable
private fun BlurDemo() {
    var separateXY by remember { mutableStateOf(false) }
    var blurRadiusX by remember { mutableFloatStateOf(60f) }
    var blurRadiusY by remember { mutableFloatStateOf(60f) }
    var noiseCoefficient by remember { mutableFloatStateOf(0.001f) }
    var brightness by remember { mutableFloatStateOf(0f) }
    var contrast by remember { mutableFloatStateOf(1f) }
    var saturation by remember { mutableFloatStateOf(1f) }

    val backdrop = rememberLayerBackdrop()
    val surface = MiuixTheme.colorScheme.surface.copy(alpha = 0.6f)
    val blendConfigs = remember(surface) {
        listOf(
            // No blend
            "None" to emptyList(),
            // Standard SkBlendMode (GPU hardware)
            "SrcOver" to listOf(BlendColorEntry(surface, BlendMode.SRC_OVER)),
            "Screen" to listOf(BlendColorEntry(surface, BlendMode.SCREEN)),
            "Multiply" to listOf(BlendColorEntry(surface, BlendMode.MULTIPLY)),
            "Overlay" to listOf(BlendColorEntry(surface, BlendMode.OVERLAY)),
            "Soft Light" to listOf(BlendColorEntry(surface, BlendMode.SOFT_LIGHT)),
            "Color Dodge" to listOf(BlendColorEntry(surface, BlendMode.COLOR_DODGE)),
            "Color Burn" to listOf(BlendColorEntry(surface, BlendMode.COLOR_BURN)),
            // Xiaomi custom modes (runtime shader)
            "Linear Light" to listOf(BlendColorEntry(surface, BlendMode.LINEAR_LIGHT)),
            "Linear Light Grey" to listOf(BlendColorEntry(surface, BlendMode.LINEAR_LIGHT_WITH_GREYSCALE)),
            "Linear Light Lab" to listOf(BlendColorEntry(surface, BlendMode.LINEAR_LIGHT_LAB)),
            "Lab Lighten" to listOf(BlendColorEntry(surface, BlendMode.LAB_LIGHTEN_WITH_GREYSCALE)),
            "Lab Darken" to listOf(BlendColorEntry(surface, BlendMode.LAB_DARKEN_WITH_GREYSCALE)),
            "MI Difference" to listOf(BlendColorEntry(surface, BlendMode.MI_DIFFERENCE)),
            "MI Color Dodge" to listOf(BlendColorEntry(surface, BlendMode.MI_COLOR_DODGE)),
            "MI Color Burn" to listOf(BlendColorEntry(surface, BlendMode.MI_COLOR_BURN)),
            "Plus Lighter" to listOf(BlendColorEntry(surface, BlendMode.PLUS_LIGHTER)),
            "Plus Darker" to listOf(BlendColorEntry(surface, BlendMode.PLUS_DARKER)),
        )
    }
    val defaultBlendIndex = 16
    var blendModeIndex by remember(defaultBlendIndex) { mutableIntStateOf(defaultBlendIndex) }
    val currentBlend = blendConfigs[blendModeIndex]
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp),
    ) {
        // Preview area
        Card {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                offsetX = 0f
                                offsetY = 0f
                            },
                        )
                    },
            ) {
                // Background layer (captured by layerBackdrop)
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .layerBackdrop(backdrop),
                ) {
                    StaticBackground()
                }

                // Blur overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(140.dp)
                        .align(Alignment.Center)
                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            }
                        }
                        .textureBlur(
                            backdrop = backdrop,
                            shape = miuixShape(16.dp),
                            blurRadiusX = blurRadiusX,
                            blurRadiusY = blurRadiusY,
                            noiseCoefficient = noiseCoefficient,
                            colors = BlurColors(
                                blendColors = currentBlend.second,
                                brightness = brightness,
                                contrast = contrast,
                                saturation = saturation,
                            ),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Texture Blur",
                            style = MiuixTheme.textStyles.headline2,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = if (separateXY) {
                                "X=${blurRadiusX.toInt()} Y=${blurRadiusY.toInt()} | ${currentBlend.first}"
                            } else {
                                "R=${blurRadiusX.toInt()} | ${currentBlend.first}"
                            },
                            style = MiuixTheme.textStyles.body2,
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Controls
        Card(
            modifier = Modifier.padding(bottom = 12.dp),
        ) {
            // Blur radius
            BasicComponent(
                title = "Blur Radius",
                endActions = { ValueText("${blurRadiusX.toInt()}") },
                bottomAction = {
                    Slider(
                        value = blurRadiusX / 200f,
                        onValueChange = {
                            blurRadiusX = it * 200f
                            blurRadiusY = it * 200f
                        },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Noise
            BasicComponent(
                title = "Noise",
                endActions = { ValueText("${(noiseCoefficient * 1000).toInt() / 1000f}") },
                bottomAction = {
                    Slider(
                        value = noiseCoefficient / 0.1f,
                        onValueChange = { noiseCoefficient = it * 0.1f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Brightness
            BasicComponent(
                title = "Brightness",
                endActions = { ValueText("${(brightness * 100).toInt() / 100f}") },
                bottomAction = {
                    Slider(
                        value = (brightness + 1f) / 2f,
                        onValueChange = { brightness = it * 2f - 1f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Contrast
            BasicComponent(
                title = "Contrast",
                endActions = { ValueText("${(contrast * 100).toInt() / 100f}") },
                bottomAction = {
                    Slider(
                        value = contrast / 3f,
                        onValueChange = { contrast = it * 3f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Saturation
            BasicComponent(
                title = "Saturation",
                endActions = { ValueText("${(saturation * 100).toInt() / 100f}") },
                bottomAction = {
                    Slider(
                        value = saturation / 3f,
                        onValueChange = { saturation = it * 3f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Blend mode
            val modeId = currentBlend.second.firstOrNull()?.mode
            BasicComponent(
                title = "Blend Mode",
                endActions = {
                    ValueText(currentBlend.first + if (modeId != null) " ($modeId)" else "")
                },
                bottomAction = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        TextButton(
                            text = "Prev",
                            onClick = {
                                blendModeIndex = (blendModeIndex - 1 + blendConfigs.size) % blendConfigs.size
                            },
                            modifier = Modifier.weight(1f),
                        )
                        TextButton(
                            text = "Next",
                            onClick = {
                                blendModeIndex = (blendModeIndex + 1) % blendConfigs.size
                            },
                            modifier = Modifier.weight(1f),
                        )
                    }
                },
            )
        }
    }
}

@Composable
private fun ForegroundBlurDemo() {
    var blurRadiusX by remember { mutableFloatStateOf(60f) }
    var blurRadiusY by remember { mutableFloatStateOf(60f) }
    var noiseCoefficient by remember { mutableFloatStateOf(0.001f) }
    var brightness by remember { mutableFloatStateOf(0f) }
    var contrast by remember { mutableFloatStateOf(1f) }
    var saturation by remember { mutableFloatStateOf(1f) }

    val backdrop = rememberLayerBackdrop()
    val onBackground = MiuixTheme.colorScheme.onBackground
    val blendConfigs = remember(onBackground) {
        listOf(
            "None" to emptyList(),
            "SrcOver" to listOf(BlendColorEntry(onBackground, BlendMode.SRC_OVER)),
            "Screen" to listOf(BlendColorEntry(onBackground, BlendMode.SCREEN)),
            "Multiply" to listOf(BlendColorEntry(onBackground, BlendMode.MULTIPLY)),
            "Overlay" to listOf(BlendColorEntry(onBackground, BlendMode.OVERLAY)),
            "Soft Light" to listOf(BlendColorEntry(onBackground, BlendMode.SOFT_LIGHT)),
            "Color Dodge" to listOf(BlendColorEntry(onBackground, BlendMode.COLOR_DODGE)),
            "Color Burn" to listOf(BlendColorEntry(onBackground, BlendMode.COLOR_BURN)),
            "Linear Light" to listOf(BlendColorEntry(onBackground, BlendMode.LINEAR_LIGHT)),
            "Linear Light Grey" to listOf(BlendColorEntry(onBackground, BlendMode.LINEAR_LIGHT_WITH_GREYSCALE)),
            "Linear Light Lab" to listOf(BlendColorEntry(onBackground, BlendMode.LINEAR_LIGHT_LAB)),
            "Lab Lighten" to listOf(BlendColorEntry(onBackground, BlendMode.LAB_LIGHTEN_WITH_GREYSCALE)),
            "Lab Darken" to listOf(BlendColorEntry(onBackground, BlendMode.LAB_DARKEN_WITH_GREYSCALE)),
            "MI Difference" to listOf(BlendColorEntry(onBackground, BlendMode.MI_DIFFERENCE)),
            "MI Color Dodge" to listOf(BlendColorEntry(onBackground, BlendMode.MI_COLOR_DODGE)),
            "MI Color Burn" to listOf(BlendColorEntry(onBackground, BlendMode.MI_COLOR_BURN)),
            "Plus Lighter" to listOf(BlendColorEntry(onBackground, BlendMode.PLUS_LIGHTER)),
            "Plus Darker" to listOf(BlendColorEntry(onBackground, BlendMode.PLUS_DARKER)),
        )
    }
    val defaultBlendIndex = 10
    var blendModeIndex by remember { mutableIntStateOf(defaultBlendIndex) }
    val currentBlend = blendConfigs[blendModeIndex]

    Column(
        modifier = Modifier.padding(horizontal = 12.dp),
    ) {
        Card {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
            ) {
                // Background layer (captured by layerBackdrop)
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .layerBackdrop(backdrop),
                ) {
                    StaticBackground()
                }

                // Foreground blur text
                Text(
                    text = "Foreground Blur\n\nTEST TEST TEST",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .foregroundBlur(
                            backdrop = backdrop,
                            shape = RoundedCornerShape(0.dp),
                            blurRadiusX = blurRadiusX,
                            blurRadiusY = blurRadiusY,
                            noiseCoefficient = noiseCoefficient,
                            colors = BlurColors(
                                blendColors = currentBlend.second,
                                brightness = brightness,
                                contrast = contrast,
                                saturation = saturation,
                            ),
                        ),
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // Controls
        Card {
            BasicComponent(
                title = "Blur Radius",
                endActions = { ValueText("${blurRadiusX.toInt()}") },
                bottomAction = {
                    Slider(
                        value = blurRadiusX / 200f,
                        onValueChange = {
                            blurRadiusX = it * 200f
                            blurRadiusY = it * 200f
                        },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            BasicComponent(
                title = "Noise",
                endActions = { ValueText("${(noiseCoefficient * 1000).toInt() / 1000f}") },
                bottomAction = {
                    Slider(
                        value = noiseCoefficient / 0.1f,
                        onValueChange = { noiseCoefficient = it * 0.1f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            BasicComponent(
                title = "Brightness",
                endActions = { ValueText("${(brightness * 100).toInt() / 100f}") },
                bottomAction = {
                    Slider(
                        value = (brightness + 1f) / 2f,
                        onValueChange = { brightness = it * 2f - 1f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            BasicComponent(
                title = "Contrast",
                endActions = { ValueText("${(contrast * 100).toInt() / 100f}") },
                bottomAction = {
                    Slider(
                        value = contrast / 3f,
                        onValueChange = { contrast = it * 3f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            BasicComponent(
                title = "Saturation",
                endActions = { ValueText("${(saturation * 100).toInt() / 100f}") },
                bottomAction = {
                    Slider(
                        value = saturation / 3f,
                        onValueChange = { saturation = it * 3f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            val modeId = currentBlend.second.firstOrNull()?.mode
            BasicComponent(
                title = "Blend Mode",
                endActions = {
                    ValueText(currentBlend.first + if (modeId != null) " ($modeId)" else "")
                },
                bottomAction = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        TextButton(
                            text = "Prev",
                            onClick = {
                                blendModeIndex = (blendModeIndex - 1 + blendConfigs.size) % blendConfigs.size
                            },
                            modifier = Modifier.weight(1f),
                        )
                        TextButton(
                            text = "Next",
                            onClick = {
                                blendModeIndex = (blendModeIndex + 1) % blendConfigs.size
                            },
                            modifier = Modifier.weight(1f),
                        )
                    }
                },
            )
        }
    }
}

@Suppress("FunctionName")
@Composable
private fun ValueText(text: String) {
    Text(
        text = text,
        fontSize = MiuixTheme.textStyles.body2.fontSize,
        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
    )
}

@Composable
private fun StaticBackground() {
    // Opaque gradient base — eliminates transparent gaps that cause dark blur halos
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF2D1B69),
                        Color(0xFF11998E),
                        Color(0xFFFC5C7D),
                        Color(0xFF6A82FB),
                    ),
                ),
            ),
    ) {
        // Overlapping shapes for rich detail
        Box(
            Modifier
                .size(120.dp, 100.dp)
                .offset(x = 10.dp, y = 20.dp)
                .background(Color(0xCCFF6B6B), RoundedRectangle(20.dp)),
        )
        Box(
            Modifier
                .size(90.dp, 130.dp)
                .offset(x = 100.dp, y = 60.dp)
                .background(Color(0xCCFFE66D), RoundedRectangle(16.dp)),
        )
        Box(
            Modifier
                .size(140.dp, 80.dp)
                .offset(x = 180.dp, y = 10.dp)
                .background(Color(0xCC4ECDC4), RoundedRectangle(50.dp)),
        )
        Box(
            Modifier
                .size(100.dp)
                .offset(x = 60.dp, y = 150.dp)
                .rotate(25f)
                .background(Color(0xCC45B7D1), RoundedRectangle(24.dp)),
        )
        Box(
            Modifier
                .size(130.dp, 70.dp)
                .offset(x = 200.dp, y = 120.dp)
                .background(Color(0xCCDDA0DD), RoundedRectangle(50.dp)),
        )
        Box(
            Modifier
                .size(80.dp, 110.dp)
                .offset(x = 280.dp, y = 50.dp)
                .rotate(-15f)
                .background(Color(0xCC96CEB4), RoundedRectangle(12.dp)),
        )
        Box(
            Modifier
                .size(110.dp, 60.dp)
                .offset(x = 150.dp, y = 200.dp)
                .background(Color(0xCCFF9A76), RoundedRectangle(30.dp)),
        )
        Box(
            Modifier
                .size(70.dp, 90.dp)
                .offset(x = 310.dp, y = 180.dp)
                .rotate(10f)
                .background(Color(0xCC6C5CE7), RoundedRectangle(18.dp)),
        )
    }
}
