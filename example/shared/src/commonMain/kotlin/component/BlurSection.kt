// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.blend.ColorBlendToken
import component.effect.BgEffectBackground
import component.highlight.HighlightConfig
import component.highlight.rememberContainerHighlight
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurBlendMode
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.highlight.highlight
import top.yukonga.miuix.kmp.blur.isRenderEffectSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.preference.OverlayDropdownPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.blur_test_bg
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme
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

    var tiltDriven by remember { mutableStateOf(true) }
    val containers = HighlightConfig.Container.entries
    val containerItems = remember { containers.map { it.displayName } }
    var containerIndex by remember { mutableIntStateOf(HighlightConfig.Container.Small.ordinal) }
    val currentContainer = containers[containerIndex]
    val highlight = rememberContainerHighlight(
        container = currentContainer,
        isDark = isInDark,
        tiltDriven = tiltDriven,
    )

    val blendConfigs = remember(isInDark, surface) {
        listOf(
            "None" to emptyList(),
            "Info Thin" to if (isInDark) ColorBlendToken.Info_Thin_Dark else ColorBlendToken.Info_Thin_Light,
            "Info Regular" to if (isInDark) ColorBlendToken.Info_Regular_Dark else ColorBlendToken.Info_Regular_Light,
            "Colored Thin" to if (isInDark) ColorBlendToken.Colored_Thin_Dark else ColorBlendToken.Colored_Thin_Light,
            "Colored Regular" to if (isInDark) ColorBlendToken.Colored_Regular_Dark else ColorBlendToken.Colored_Regular_Light,
            "Colored Thick" to if (isInDark) ColorBlendToken.Colored_Thick_Dark else ColorBlendToken.Colored_Thick_Light,
            "Pured Regular" to if (isInDark) ColorBlendToken.Pured_Regular_Dark else ColorBlendToken.Pured_Regular_Light,
            "Pured Thick" to if (isInDark) ColorBlendToken.Pured_Thick_Dark else ColorBlendToken.Pured_Thick_Light,
            "Overlay Thin" to if (isInDark) ColorBlendToken.Overlay_Thin_Light else ColorBlendToken.Overlay_Thin_Light,
            "Overlay Thick" to if (isInDark) ColorBlendToken.Overlay_Thick_Dark else ColorBlendToken.Overlay_Thick_Light,
        )
    }
    var blendModeIndex by remember { mutableIntStateOf(5) }
    val currentBlend = blendConfigs[blendModeIndex]
    val blendModeItems = remember(blendConfigs) { blendConfigs.map { it.first } }
    val shape = RoundedCornerShape(24.dp)

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp),
    ) {
        // Preview area
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

                // Blur overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(140.dp)
                        .align(Alignment.Center)
                        .textureBlur(
                            backdrop = backdrop,
                            shape = shape,
                            blurRadiusX = blurRadiusX,
                            blurRadiusY = blurRadiusY,
                            noiseCoefficient = noiseCoefficient,
                            colors = BlurColors(
                                blendColors = currentBlend.second,
                                brightness = brightness,
                                contrast = contrast,
                                saturation = saturation,
                            ),
                        )
                        .highlight(
                            shape = shape,
                            highlight = highlight,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Texture Blur | R=${blurRadiusX.toInt()}",
                            style = MiuixTheme.textStyles.headline2,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "${currentBlend.first} | ${containerItems[containerIndex]}",
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
            OverlayDropdownPreference(
                title = "Blend Mode",
                items = blendModeItems,
                selectedIndex = blendModeIndex,
                onSelectedIndexChange = { blendModeIndex = it },
            )

            OverlayDropdownPreference(
                title = "Highlight",
                items = containerItems,
                selectedIndex = containerIndex,
                onSelectedIndexChange = { containerIndex = it },
            )

            HorizontalDivider(Modifier.fillMaxWidth().padding(horizontal = 16.dp))

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
            "Logo Blend" to logoBlend,
            "Colored Thin" to if (isInDark) ColorBlendToken.Colored_Thin_Dark else ColorBlendToken.Colored_Thin_Light,
            "Colored Regular" to if (isInDark) ColorBlendToken.Colored_Regular_Dark else ColorBlendToken.Colored_Regular_Light,
            "Colored Thick" to if (isInDark) ColorBlendToken.Colored_Thick_Dark else ColorBlendToken.Colored_Thick_Light,
            "Pured Regular" to if (isInDark) ColorBlendToken.Pured_Regular_Dark else ColorBlendToken.Pured_Regular_Light,
            "Overlay Thin" to if (isInDark) ColorBlendToken.Overlay_Thin_Light else ColorBlendToken.Overlay_Thin_Light,
            "Info Colored" to ColorBlendToken.Info_Colored_Regular,
        )
    }
    var blendModeIndex by remember { mutableIntStateOf(1) }
    val currentBlend = blendConfigs[blendModeIndex]
    val blendModeItems = remember(blendConfigs) { blendConfigs.map { it.first } }
    var isOs3Effect by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.padding(horizontal = 12.dp),
    ) {
        Card(
            colors = CardDefaults.defaultColors(Color.Transparent),
        ) {
            BgEffectBackground(
                dynamicBackground = dynamicBackground.value,
                isFullSize = true,
                isOs3Effect = isOs3Effect,
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
            val effectVariantOptions = listOf("OS2", "OS3")
            OverlayDropdownPreference(
                title = "Effect Variant",
                items = effectVariantOptions,
                selectedIndex = if (isOs3Effect) 1 else 0,
                onSelectedIndexChange = { isOs3Effect = (it == 1) },
            )

            OverlayDropdownPreference(
                title = "Blend Mode",
                items = blendModeItems,
                selectedIndex = blendModeIndex,
                onSelectedIndexChange = { blendModeIndex = it },
            )

            SwitchPreference(
                title = "Dynamic Background",
                checked = dynamicBackground.value,
                onCheckedChange = { dynamicBackground.value = it },
            )

            HorizontalDivider(Modifier.fillMaxWidth().padding(horizontal = 16.dp))

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
