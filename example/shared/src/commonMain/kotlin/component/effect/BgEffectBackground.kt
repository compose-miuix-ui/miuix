// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.effect

import LocalAppState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import top.yukonga.miuix.kmp.blur.BlendMode
import top.yukonga.miuix.kmp.blur.asComposeShader
import top.yukonga.miuix.kmp.theme.MiuixTheme

private fun getLayerList(isDarkMode: Boolean): List<Pair<Color, Int>> = if (isDarkMode) {
    listOf(
        Pair(Color(0xe6a1a1a1), BlendMode.COLOR_DODGE),
        Pair(Color(0x4de6e6e6), BlendMode.LINEAR_LIGHT),
        Pair(Color(0xff1af500), BlendMode.LAB),
    )
} else {
    listOf(
        Pair(Color(0xcc4a4a4a), BlendMode.COLOR_BURN),
        Pair(Color(0xff4f4f4f), BlendMode.LINEAR_LIGHT),
        Pair(Color(0xff1af200), BlendMode.LAB),
    )
}

@Composable
inline fun BgEffectBackground(
    modifier: Modifier = Modifier,
    bgModifier: Modifier = Modifier,
    bgAlpha: Float = 1f,
    crossinline content: @Composable (BoxScope.() -> Unit),
) {
    val appState = LocalAppState.current

    val painter = remember { BgEffectPainter() }

    val currentBrush: MutableState<ShaderBrush?> = remember { mutableStateOf(null) }
    val isDark = isSystemInDarkTheme()

    var targetSize by remember { mutableStateOf(IntSize.Zero) }
    val logoHeight = remember { mutableFloatStateOf(0f) }

    LaunchedEffect(targetSize, isDark, logoHeight.value) {
        if (targetSize.width > 0 && targetSize.height > 0) {
            painter.showRuntimeShader(
                logoHeight.value,
                targetSize.height.toFloat(),
                targetSize.width.toFloat(),
                isDark,
            )
            painter.updateMode(isDark)
        }
    }

    LaunchedEffect(Unit) {
        var startTime: Long? = null
        while (true) {
            withFrameNanos { frameTime ->
                if (startTime == null) {
                    startTime = frameTime
                }
                val animTime = ((frameTime - startTime) / 1_000_000_000f) % 62.831852f
                if (
                    targetSize.width > 0 &&
                    targetSize.height > 0
                ) {
                    painter.setAnimTime(animTime)
                    painter.setResolution(
                        floatArrayOf(
                            targetSize.width.toFloat(),
                            targetSize.height.toFloat(),
                        ),
                    )
                    painter.updateMaterials()
                    painter.runtimeShader?.let { shader ->
                        currentBrush.value = ShaderBrush(shader.asComposeShader())
                    }
                }
            }
        }
    }

    Box(
        modifier = modifier.onSizeChanged {
            targetSize = it
        },
    ) {
        currentBrush.value?.let { brush ->
            Canvas(
                modifier = Modifier.fillMaxSize().graphicsLayer { alpha = bgAlpha }.then(bgModifier),
            ) {
                with(drawContext.density) {
                    logoHeight.value = 410.dp.toPx()
                }
                drawRect(Color.White)
                drawRect(brush)
            }
            content()
        }
    }
}
