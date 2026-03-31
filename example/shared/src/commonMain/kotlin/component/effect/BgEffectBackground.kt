// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.effect

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import top.yukonga.miuix.kmp.blur.asBrush
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme

@Composable
inline fun BgEffectBackground(
    dynamicBackground: Boolean,
    modifier: Modifier = Modifier,
    bgModifier: Modifier = Modifier,
    isFullSize: Boolean = false,
    effectBackground: Boolean = true,
    crossinline alpha: () -> Float = { 1f },
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
        val painter = remember { BgEffectPainter() }
        val animTime = rememberFrameTimeSeconds(dynamicBackground)
        val isDark = isInDarkTheme()

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .then(bgModifier),
        ) {
            drawRect(surface)
            if (effectBackground) {
                val drawHeight = if (isFullSize) size.height else size.height * 0.78f
                painter.updateResolution(
                    size.width,
                    size.height,
                )
                painter.updatePresetIfNeeded(
                    drawHeight,
                    size.height,
                    size.width,
                    isDark,
                )
                painter.updateAnimTime(animTime())
                drawRect(painter.runtimeShader.asBrush(), alpha = alpha())
            }
        }
        content()
    }
}
