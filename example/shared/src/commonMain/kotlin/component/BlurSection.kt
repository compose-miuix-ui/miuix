// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.shapes.RoundedRectangle
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlendMode
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.blurSection() {
    item(key = "blur") {
        SmallTitle(text = "Texture Blur")
        BlurDemo()
    }
}

@Composable
private fun BlurDemo() {
    var blurRadius by remember { mutableFloatStateOf(60f) }
    var noiseCoefficient by remember { mutableFloatStateOf(0.001f) }
    var blendModeIndex by remember { mutableIntStateOf(0) }
    var dynamicBg by remember { mutableStateOf(false) }

    val backdrop = rememberLayerBackdrop()

    val blendConfigs = remember {
        listOf(
            // No blend
            "None" to emptyList(),
            // Standard SkBlendMode (GPU hardware)
            "SrcOver White" to listOf(BlendColorEntry(Color.White.copy(alpha = 0.3f), BlendMode.SRC_OVER)),
            "SrcOver Black" to listOf(BlendColorEntry(Color.Black.copy(alpha = 0.2f), BlendMode.SRC_OVER)),
            "Screen" to listOf(BlendColorEntry(Color(0xFF1565C0).copy(alpha = 0.3f), BlendMode.SCREEN)),
            "Multiply" to listOf(BlendColorEntry(Color(0xFFE91E63).copy(alpha = 0.3f), BlendMode.MULTIPLY)),
            "Overlay" to listOf(BlendColorEntry(Color(0xFF6750A4).copy(alpha = 0.4f), BlendMode.OVERLAY)),
            "Soft Light" to listOf(BlendColorEntry(Color(0xFFFFB74D).copy(alpha = 0.4f), BlendMode.SOFT_LIGHT)),
            // Xiaomi custom modes (runtime shader)
            "Linear Light" to listOf(BlendColorEntry(Color(0xFF6750A4).copy(alpha = 0.3f), BlendMode.LINEAR_LIGHT)),
            "Linear Light Grey" to listOf(BlendColorEntry(Color(0xFF90A4AE).copy(alpha = 0.4f), BlendMode.LINEAR_LIGHT_WITH_GREYSCALE)),
            "Linear Light Lab" to listOf(BlendColorEntry(Color(0xFF5C6BC0).copy(alpha = 0.3f), BlendMode.LINEAR_LIGHT_LAB)),
            "Lab Lighten" to listOf(BlendColorEntry(Color.White.copy(alpha = 0.5f), BlendMode.LAB_LIGHTEN_WITH_GREYSCALE)),
            "Lab Darken" to listOf(BlendColorEntry(Color.Black.copy(alpha = 0.5f), BlendMode.LAB_DARKEN_WITH_GREYSCALE)),
            "MI Difference" to listOf(BlendColorEntry(Color(0xFF00BCD4).copy(alpha = 0.4f), BlendMode.MI_DIFFERENCE)),
            "MI Color Dodge" to listOf(BlendColorEntry(Color(0xFFFF7043).copy(alpha = 0.3f), BlendMode.MI_COLOR_DODGE)),
            "MI Color Burn" to listOf(BlendColorEntry(Color(0xFF8D6E63).copy(alpha = 0.3f), BlendMode.MI_COLOR_BURN)),
            "Plus Darker" to listOf(BlendColorEntry(Color(0xFF006D40).copy(alpha = 0.4f), BlendMode.PLUS_DARKER)),
            "Plus Lighter" to listOf(BlendColorEntry(Color(0xFF1A237E).copy(alpha = 0.4f), BlendMode.PLUS_LIGHTER)),
        )
    }
    val currentBlend = blendConfigs[blendModeIndex]

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        // Preview area
        Card(
            colors = CardDefaults.defaultColors(color = Color(0xFF1A1A2E)),
        ) {
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
                    if (dynamicBg) {
                        DynamicBackground()
                    } else {
                        StaticBackground()
                    }
                }

                // Blur overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(140.dp)
                        .align(Alignment.Center)
                        .textureBlur(
                            backdrop = backdrop,
                            shape = { RoundedRectangle(16.dp) },
                            blurRadius = blurRadius,
                            noiseCoefficient = noiseCoefficient,
                            colors = BlurColors(blendColors = currentBlend.second),
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
                            text = "R=${blurRadius.toInt()} | ${currentBlend.first}",
                            style = MiuixTheme.textStyles.body2,
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        // Controls
        Card {
            // Blur radius
            Text(
                text = "Blur Radius: ${blurRadius.toInt()}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp),
            )
            Slider(
                value = blurRadius / 200f,
                onValueChange = { blurRadius = it * 200f },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )

            // Noise
            Text(
                text = "Noise: ${(noiseCoefficient * 1000).toInt() / 1000f}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            Slider(
                value = noiseCoefficient / 0.1f,
                onValueChange = { noiseCoefficient = it * 0.1f },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )

            // Blend mode
            val modeId = currentBlend.second.firstOrNull()?.mode
            Text(
                text = "Blend Mode: ${currentBlend.first}" + if (modeId != null) " ($modeId)" else "",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
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

            // Dynamic background toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Dynamic Background", fontSize = 14.sp)
                Switch(
                    checked = dynamicBg,
                    onCheckedChange = { dynamicBg = it },
                )
            }
        }
    }
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

@Composable
private fun DynamicBackground() {
    val transition = rememberInfiniteTransition()

    // Multiple animation drivers at different speeds
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )
    val drift by transition.animateFloat(
        initialValue = -40f,
        targetValue = 40f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )
    val pulse by transition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    data class AnimBlock(
        val color: Color,
        val x: Int,
        val y: Int,
        val w: Int,
        val h: Int,
        val cornerRadius: Int,
    )

    val blocks = remember {
        listOf(
            AnimBlock(Color(0xFFFF6B6B), 10, 10, 100, 80, 20),
            AnimBlock(Color(0xFFFFE66D), 130, 30, 80, 100, 16),
            AnimBlock(Color(0xFF4ECDC4), 240, 5, 120, 70, 30),
            AnimBlock(Color(0xFF45B7D1), 50, 110, 90, 90, 45),
            AnimBlock(Color(0xFF96CEB4), 170, 100, 110, 80, 12),
            AnimBlock(Color(0xFFDDA0DD), 300, 80, 70, 110, 22),
            AnimBlock(Color(0xFFFF9A76), 20, 200, 130, 60, 30),
            AnimBlock(Color(0xFF6C5CE7), 180, 190, 80, 80, 40),
            AnimBlock(Color(0xFFE17055), 290, 200, 100, 70, 18),
            AnimBlock(Color(0xFF00B894), 100, 160, 60, 100, 14),
            AnimBlock(Color(0xFFFDCB6E), 250, 150, 90, 50, 25),
            AnimBlock(Color(0xFF74B9FF), 350, 30, 60, 130, 16),
        )
    }

    // Opaque gradient base
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0F2027),
                        Color(0xFF203A43),
                        Color(0xFF2C5364),
                    ),
                ),
            ),
    ) {
        for (i in blocks.indices) {
            val b = blocks[i]
            val angle = phase * (if (i % 2 == 0) 1f else -0.7f) + i * 30f
            val xOff = drift * (
                if (i % 3 == 0) {
                    1f
                } else if (i % 3 == 1) {
                    -0.6f
                } else {
                    0.3f
                }
                )
            val yOff = drift * (if (i % 2 == 0) -0.4f else 0.8f) * (if (i % 5 == 0) 1.5f else 1f)
            val s = if (i % 4 == 0) {
                pulse
            } else if (i % 4 == 2) {
                2f - pulse
            } else {
                1f
            }
            Box(
                modifier = Modifier
                    .size(b.w.dp, b.h.dp)
                    .offset(x = b.x.dp + xOff.dp, y = b.y.dp + yOff.dp)
                    .scale(s)
                    .rotate(angle)
                    .background(b.color.copy(alpha = 0.85f), RoundedRectangle(b.cornerRadius.dp)),
            )
        }
    }
}
