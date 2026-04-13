// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.effect

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme

@Composable
fun BgEffectBackground(
    dynamicBackground: Boolean,
    modifier: Modifier = Modifier,
    bgModifier: Modifier = Modifier,
    isFullSize: Boolean = false,
    effectBackground: Boolean = true,
    isOs3Effect: Boolean = true,
    alpha: () -> Float = { 1f },
    content: @Composable (BoxScope.() -> Unit),
) {
    val shaderSupported = remember { isRuntimeShaderSupported() }
    if (!shaderSupported) {
        Box(modifier = modifier, content = content)
        return
    }
    Box(
        modifier = modifier,
    ) {
        val surface = MiuixTheme.colorScheme.surface
        val painter = remember(isOs3Effect) { BgEffectPainter(isOs3Effect) }
        val animTime = rememberFrameTimeSeconds(dynamicBackground)
        val isDark = isInDarkTheme()
        val deviceType = DeviceType.PHONE

        val preset = remember(isDark, deviceType, isOs3Effect) {
            BgEffectConfig.get(deviceType, isDark, isOs3Effect)
        }

        val colorStage = remember { Animatable(0f) }

        LaunchedEffect(dynamicBackground, preset) {
            if (!dynamicBackground) return@LaunchedEffect
            var targetStage = 1f
            while (isActive) {
                delay((preset.colorInterpPeriod * 500).toLong())
                colorStage.animateTo(
                    targetValue = targetStage,
                    animationSpec = spring(dampingRatio = 0.9f, stiffness = 35f),
                )
                targetStage += 1f
            }
        }

        key(isOs3Effect) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .then(bgModifier),
            ) {
                drawRect(surface)
                if (effectBackground) {
                    val drawHeight = if (isFullSize) size.height else size.height * 0.78f

                    val stage = colorStage.value
                    val base = stage.toInt()
                    val fraction = stage - base

                    val getColors = { index: Int ->
                        when (index % 4) {
                            0 -> preset.colors2
                            1 -> preset.colors1
                            2 -> preset.colors2
                            3 -> preset.colors3
                            else -> preset.colors2
                        }
                    }

                    val start = getColors(base)
                    val end = getColors(base + 1)
                    val currentColors = FloatArray(16) { i ->
                        start[i] + (end[i] - start[i]) * fraction
                    }

                    painter.updateResolution(size.width, size.height)
                    painter.updatePresetIfNeeded(drawHeight, size.height, size.width, isDark)
                    painter.updateColors(currentColors)
                    painter.updateAnimTime(animTime())

                    drawRect(painter.brush, alpha = alpha())
                }
            }
        }
        content()
    }
}
