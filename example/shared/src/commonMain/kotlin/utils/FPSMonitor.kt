// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.Text

/**
 * A simple FPS monitor that displays the current frames per second.
 */
@Composable
fun FPSMonitor(modifier: Modifier = Modifier) {
    var fps by remember { mutableIntStateOf(0) }
    var maxFps by remember { mutableIntStateOf(0) }
    var lastFrameTime by remember { mutableLongStateOf(0L) }
    var frameCount by remember { mutableIntStateOf(0) }
    var totalFrameTime by remember { mutableLongStateOf(0L) }

    val color by remember {
        derivedStateOf {
            when {
                fps >= maxFps - 2 -> Color.Green
                fps >= maxFps - 6 -> Color.Blue
                fps >= maxFps - 15 -> Color(0xFFFFD700)
                else -> Color.Red
            }
        }
    }

    Text(
        modifier = modifier,
        text = "FPS: $fps",
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        color = color,
    )

    LaunchedEffect(Unit) {
        while (true) {
            withFrameMillis { frameTimeMillis ->
                if (lastFrameTime != 0L) {
                    val frameDuration = frameTimeMillis - lastFrameTime
                    totalFrameTime += frameDuration
                    frameCount++
                    if (totalFrameTime >= 1000L) {
                        fps = frameCount
                        if (frameCount > maxFps) maxFps = frameCount
                        frameCount = 0
                        totalFrameTime = 0L
                    }
                }
                lastFrameTime = frameTimeMillis
            }
        }
    }
}
