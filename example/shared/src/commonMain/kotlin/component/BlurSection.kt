// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.blend.ColorBlendToken
import component.effect.BgEffectBackground
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurBlendMode
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.isRenderEffectSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.blur_test_bg
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.miuixShape
import ui.isInDarkTheme
import kotlin.math.roundToInt
import androidx.compose.ui.graphics.BlendMode as ComposeBlendMode

fun LazyListScope.blurSection() {
    if (!isRenderEffectSupported()) return
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
    var blurRadiusX by remember { mutableFloatStateOf(100f) }
    var blurRadiusY by remember { mutableFloatStateOf(100f) }
    var noiseCoefficient by remember { mutableFloatStateOf(BlurDefaults.NoiseCoefficient) }
    var brightness by remember { mutableFloatStateOf(0f) }
    var contrast by remember { mutableFloatStateOf(1f) }
    var saturation by remember { mutableFloatStateOf(1f) }

    val isInDark = isInDarkTheme()

    val backdrop = rememberLayerBackdrop()
    val surface = MiuixTheme.colorScheme.surface.copy(alpha = 0.6f)
    val blendConfigs = remember(isInDark, surface) {
        listOf(
            "None" to emptyList(),
            // Token presets (theme-aware)
            "Info Thin" to if (isInDark) ColorBlendToken.Info_Thin_Dark else ColorBlendToken.Info_Thin_Light,
            "Info Regular" to if (isInDark) ColorBlendToken.Info_Regular_Dark else ColorBlendToken.Info_Regular_Light,
            "Colored Thin" to if (isInDark) ColorBlendToken.Colored_Thin_Dark else ColorBlendToken.Colored_Thin_Light,
            "Colored Regular" to if (isInDark) ColorBlendToken.Colored_Regular_Dark else ColorBlendToken.Colored_Regular_Light,
            "Colored Thick" to if (isInDark) ColorBlendToken.Colored_Thick_Dark else ColorBlendToken.Colored_Thick_Light,
            "Pured Regular" to if (isInDark) ColorBlendToken.Pured_Regular_Dark else ColorBlendToken.Pured_Regular_Light,
            "Pured Thick" to if (isInDark) ColorBlendToken.Pured_Thick_Dark else ColorBlendToken.Pured_Thick_Light,
            "Overlay Thin" to if (isInDark) ColorBlendToken.Overlay_Thin_Light else ColorBlendToken.Overlay_Thin_Light,
            "Overlay Thick" to if (isInDark) ColorBlendToken.Overlay_Thick_Dark else ColorBlendToken.Overlay_Thick_Light,
            // Single-mode blends
            "SrcOver" to listOf(BlendColorEntry(surface, BlurBlendMode.SrcOver)),
            "Screen" to listOf(BlendColorEntry(surface, BlurBlendMode.Screen)),
            "Multiply" to listOf(BlendColorEntry(surface, BlurBlendMode.Multiply)),
            "Overlay" to listOf(BlendColorEntry(surface, BlurBlendMode.Overlay)),
            "Soft Light" to listOf(BlendColorEntry(surface, BlurBlendMode.SoftLight)),
            "Color Dodge" to listOf(BlendColorEntry(surface, BlurBlendMode.ColorDodge)),
            "Color Burn" to listOf(BlendColorEntry(surface, BlurBlendMode.ColorBurn)),
            "Linear Light" to listOf(BlendColorEntry(surface, BlurBlendMode.LinearLight)),
            "Linear Light Grey" to listOf(BlendColorEntry(surface, BlurBlendMode.LinearLightWithGreyscale)),
            "Linear Light Lab" to listOf(BlendColorEntry(surface, BlurBlendMode.LinearLightLab)),
            "Lab Lighten" to listOf(BlendColorEntry(surface, BlurBlendMode.LabLightenWithGreyscale)),
            "Lab Darken" to listOf(BlendColorEntry(surface, BlurBlendMode.LabDarkenWithGreyscale)),
            "MI Difference" to listOf(BlendColorEntry(surface, BlurBlendMode.MiDifference)),
            "MI Color Dodge" to listOf(BlendColorEntry(surface, BlurBlendMode.MiColorDodge)),
            "MI Color Burn" to listOf(BlendColorEntry(surface, BlurBlendMode.MiColorBurn)),
            "Plus Lighter" to listOf(BlendColorEntry(surface, BlurBlendMode.PlusLighter)),
            "Plus Darker" to listOf(BlendColorEntry(surface, BlurBlendMode.PlusDarker)),
        )
    }
    var blendModeIndex by remember { mutableIntStateOf(5) }
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
                            text = "R=${blurRadiusX.toInt()} | ${currentBlend.first}",
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
                endActions = { ValueText("${(noiseCoefficient * 10000).toInt() / 10000f}") },
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
            val modeInfo = currentBlend.second.let { entries ->
                when {
                    entries.size == 1 -> " (${entries.first().mode})"
                    entries.size > 1 -> " (${entries.size} layers)"
                    else -> ""
                }
            }
            BasicComponent(
                title = "Blend Mode",
                endActions = {
                    ValueText(currentBlend.first + modeInfo)
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
    var blurRadiusX by remember { mutableFloatStateOf(200f) }
    var blurRadiusY by remember { mutableFloatStateOf(200f) }
    var noiseCoefficient by remember { mutableFloatStateOf(BlurDefaults.NoiseCoefficient) }
    var brightness by remember { mutableFloatStateOf(0f) }
    var contrast by remember { mutableFloatStateOf(1f) }
    var saturation by remember { mutableFloatStateOf(1f) }

    val isInDark = isInDarkTheme()
    val dynamicBackground = remember { mutableStateOf(true) }

    val backdrop = rememberLayerBackdrop()
    val onBackground = MiuixTheme.colorScheme.onBackground
    val logoBlend = remember(isInDark) {
        if (isInDark) {
            listOf(
                BlendColorEntry(Color(0xe6a1a1a1), BlurBlendMode.ColorDodge),
                BlendColorEntry(Color(0x4de6e6e6), BlurBlendMode.LinearLight),
                BlendColorEntry(Color(0xff1af500), BlurBlendMode.Lab),
            )
        } else {
            listOf(
                BlendColorEntry(Color(0xcc4a4a4a), BlurBlendMode.ColorBurn),
                BlendColorEntry(Color(0xff4f4f4f), BlurBlendMode.LinearLight),
                BlendColorEntry(Color(0xff1af200), BlurBlendMode.Lab),
            )
        }
    }
    val blendConfigs = remember(isInDark, onBackground) {
        listOf(
            "None" to emptyList(),
            // Multi-layer presets
            "Logo Blend" to logoBlend,
            "Colored Thin" to if (isInDark) ColorBlendToken.Colored_Thin_Dark else ColorBlendToken.Colored_Thin_Light,
            "Colored Regular" to if (isInDark) ColorBlendToken.Colored_Regular_Dark else ColorBlendToken.Colored_Regular_Light,
            "Colored Thick" to if (isInDark) ColorBlendToken.Colored_Thick_Dark else ColorBlendToken.Colored_Thick_Light,
            "Pured Regular" to if (isInDark) ColorBlendToken.Pured_Regular_Dark else ColorBlendToken.Pured_Regular_Light,
            "Overlay Thin" to if (isInDark) ColorBlendToken.Overlay_Thin_Light else ColorBlendToken.Overlay_Thin_Light,
            "Info Colored" to ColorBlendToken.Info_Colored_Regular,
            // Single-mode blends
            "SrcOver" to listOf(BlendColorEntry(onBackground, BlurBlendMode.SrcOver)),
            "Screen" to listOf(BlendColorEntry(onBackground, BlurBlendMode.Screen)),
            "Multiply" to listOf(BlendColorEntry(onBackground, BlurBlendMode.Multiply)),
            "Overlay" to listOf(BlendColorEntry(onBackground, BlurBlendMode.Overlay)),
            "Soft Light" to listOf(BlendColorEntry(onBackground, BlurBlendMode.SoftLight)),
            "Color Dodge" to listOf(BlendColorEntry(onBackground, BlurBlendMode.ColorDodge)),
            "Color Burn" to listOf(BlendColorEntry(onBackground, BlurBlendMode.ColorBurn)),
            "Linear Light" to listOf(BlendColorEntry(onBackground, BlurBlendMode.LinearLight)),
            "Linear Light Grey" to listOf(BlendColorEntry(onBackground, BlurBlendMode.LinearLightWithGreyscale)),
            "Linear Light Lab" to listOf(BlendColorEntry(onBackground, BlurBlendMode.LinearLightLab)),
            "Lab Lighten" to listOf(BlendColorEntry(onBackground, BlurBlendMode.LabLightenWithGreyscale)),
            "Lab Darken" to listOf(BlendColorEntry(onBackground, BlurBlendMode.LabDarkenWithGreyscale)),
            "MI Difference" to listOf(BlendColorEntry(onBackground, BlurBlendMode.MiDifference)),
            "MI Color Dodge" to listOf(BlendColorEntry(onBackground, BlurBlendMode.MiColorDodge)),
            "MI Color Burn" to listOf(BlendColorEntry(onBackground, BlurBlendMode.MiColorBurn)),
            "Plus Lighter" to listOf(BlendColorEntry(onBackground, BlurBlendMode.PlusLighter)),
            "Plus Darker" to listOf(BlendColorEntry(onBackground, BlurBlendMode.PlusDarker)),
        )
    }
    var blendModeIndex by remember { mutableIntStateOf(1) }
    val currentBlend = blendConfigs[blendModeIndex]

    Column(
        modifier = Modifier.padding(horizontal = 12.dp),
    ) {
        Card(
            colors = CardDefaults.defaultColors(Color.Transparent),
        ) {
            BgEffectBackground(
                dynamicBackground = dynamicBackground.value,
                isFullSize = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                bgModifier = Modifier.layerBackdrop(backdrop),
            ) {
                // Foreground blur text
                Text(
                    text = "Foreground Blur\nMiuix Demo",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .textureBlur(
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
                            contentBlendMode = ComposeBlendMode.DstIn,
                        ),
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // Controls
        Card {
            // Dynamic Background toggle
            BasicComponent(
                title = "Dynamic Background",
                endActions = {
                    Switch(
                        dynamicBackground.value,
                        { dynamicBackground.value = it },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

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
                endActions = { ValueText("${(noiseCoefficient * 10000).toInt() / 10000f}") },
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

            val modeInfo = currentBlend.second.let { entries ->
                when {
                    entries.size == 1 -> " (${entries.first().mode})"
                    entries.size > 1 -> " (${entries.size} layers)"
                    else -> ""
                }
            }
            BasicComponent(
                title = "Blend Mode",
                endActions = {
                    ValueText(currentBlend.first + modeInfo)
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
    Image(
        painter = painterResource(Res.drawable.blur_test_bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )
}
