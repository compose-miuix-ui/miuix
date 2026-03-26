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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.shapes.RoundedRectangle
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlendMode
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.materialBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.blurSection() {
    item(key = "blur") {
        SmallTitle(text = "Material Blur")
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
            "None" to emptyList(),
            "SrcOver White" to listOf(BlendColorEntry(Color.White.copy(alpha = 0.3f), BlendMode.SRC_OVER)),
            "SrcOver Black" to listOf(BlendColorEntry(Color.Black.copy(alpha = 0.2f), BlendMode.SRC_OVER)),
            "Lab Lighten" to listOf(BlendColorEntry(Color.White.copy(alpha = 0.5f), BlendMode.LAB_LIGHTEN_WITH_GREYSCALE)),
            "Lab Darken" to listOf(BlendColorEntry(Color.Black.copy(alpha = 0.5f), BlendMode.LAB_DARKEN_WITH_GREYSCALE)),
            "Linear Light" to listOf(BlendColorEntry(Color(0xFF6750A4).copy(alpha = 0.3f), BlendMode.LINEAR_LIGHT)),
            "Plus Darker" to listOf(BlendColorEntry(Color(0xFF006D40).copy(alpha = 0.4f), BlendMode.PLUS_DARKER)),
            "Screen" to listOf(BlendColorEntry(Color(0xFF1565C0).copy(alpha = 0.3f), BlendMode.SCREEN)),
            "Multiply" to listOf(BlendColorEntry(Color(0xFFE91E63).copy(alpha = 0.3f), BlendMode.MULTIPLY)),
        )
    }
    val currentBlend = blendConfigs[blendModeIndex]

    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(bottom = 12.dp),
    ) {
        // Preview area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Color(0xFF1A1A2E), RoundedRectangle(16.dp)),
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
                    .materialBlur(
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
                        text = "Material Blur",
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
    val colors = listOf(
        Color(0xFFFF6B6B),
        Color(0xFFFFE66D),
        Color(0xFF4ECDC4),
        Color(0xFF45B7D1),
        Color(0xFF96CEB4),
        Color(0xFFDDA0DD),
    )
    Box(Modifier.fillMaxSize()) {
        val cols = 4
        val rows = 3
        for (r in 0 until rows) {
            for (c in 0 until cols) {
                Box(
                    modifier = Modifier
                        .size(80.dp, 70.dp)
                        .offset(x = (c * 90 + 12).dp, y = (r * 85 + 16).dp)
                        .background(
                            colors[(r * cols + c) % colors.size],
                            RoundedRectangle(12.dp),
                        ),
                )
            }
        }
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(text = "Background", color = Color.White, fontSize = 18.sp)
            Text(text = "Static content layer", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
        }
    }
}

@Composable
private fun DynamicBackground() {
    val transition = rememberInfiniteTransition()
    val phase by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(6000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )
    val drift by transition.animateFloat(
        initialValue = -30f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    val colors = listOf(
        Color(0xFFFF6B6B),
        Color(0xFFFFE66D),
        Color(0xFF4ECDC4),
        Color(0xFF45B7D1),
        Color(0xFF96CEB4),
        Color(0xFFDDA0DD),
        Color(0xFFFF9A76),
        Color(0xFF6C5CE7),
    )

    Box(Modifier.fillMaxSize()) {
        // Animated colored blocks
        for (i in colors.indices) {
            val angle = phase + i * 45f
            val xOff = drift * (if (i % 2 == 0) 1f else -1f)
            val yOff = drift * (if (i % 3 == 0) -0.5f else 0.7f)
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .offset(
                        x = (40 + (i % 4) * 80).dp + xOff.dp,
                        y = (20 + (i / 4) * 100).dp + yOff.dp,
                    )
                    .rotate(angle)
                    .background(
                        colors[i],
                        RoundedRectangle(16.dp),
                    ),
            )
        }
        // Overlay text
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(text = "Dynamic", color = Color.White, fontSize = 18.sp)
            Text(text = "Animated content layer", color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp)
        }
    }
}
