// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.squircle.squircleBackground
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.roundToInt

/**
 * Frame timing overlay that shows the average FPS and the 1% low FPS over a
 * rolling 5-second window. The 1% low is the average FPS of the slowest 1% of
 * frames in the window, which surfaces stutter spikes that the average hides.
 *
 * The reference FPS used for color thresholds tracks the highest avg seen in
 * the last [REF_HISTORY_NS] window, so the indicator adapts to display-rate
 * changes (e.g. variable refresh, sustained throttling) instead of being
 * pinned to a peak observed at startup.
 */
@Composable
fun FPSMonitor(modifier: Modifier = Modifier) {
    var stats by remember { mutableStateOf(FpsStats.Empty) }
    var refFps by remember { mutableIntStateOf(0) }
    var parentSize by remember { mutableStateOf(IntSize.Zero) }
    var pillSize by remember { mutableStateOf(IntSize.Zero) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val avgTargetColor by remember { derivedStateOf { healthColor(stats.avg, refFps) } }
    val lowTargetColor by remember { derivedStateOf { healthColor(stats.low1, refFps) } }
    val avgColor by animateColorAsState(avgTargetColor)
    val lowColor by animateColorAsState(lowTargetColor)

    fun clampOffset(raw: Offset): Offset {
        val xRange = ((parentSize.width - pillSize.width) / 2f).coerceAtLeast(0f)
        val yMax = (parentSize.height - pillSize.height).toFloat().coerceAtLeast(0f)
        return Offset(
            x = raw.x.coerceIn(-xRange, xRange),
            y = raw.y.coerceIn(0f, yMax),
        )
    }

    Box(
        modifier = modifier.onSizeChanged {
            parentSize = it
            offset = clampOffset(offset)
        },
        contentAlignment = Alignment.TopCenter,
    ) {
        Row(
            modifier = Modifier
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                .onSizeChanged {
                    pillSize = it
                    offset = clampOffset(offset)
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, drag ->
                        change.consume()
                        offset = clampOffset(offset + drag)
                    }
                }
                .squircleBackground(
                    color = MiuixTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.5f),
                    cornerRadius = PillCorner,
                )
                .padding(horizontal = 10.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            if (stats.avg == 0) {
                Text(
                    text = "--",
                    style = MiuixTheme.textStyles.body2,
                    color = MiuixTheme.colorScheme.onSurfaceSecondary,
                )
            } else {
                val secondary = MiuixTheme.colorScheme.onSurfaceSecondary
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = secondary)) { append("AVG ") }
                        withStyle(SpanStyle(color = avgColor)) { append(stats.avg.toString()) }
                    },
                    style = MiuixTheme.textStyles.body2,
                )
                Text(
                    text = "·",
                    style = MiuixTheme.textStyles.body2,
                    color = secondary,
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = secondary)) { append("LOW ") }
                        withStyle(SpanStyle(color = lowColor)) { append(stats.low1.toString()) }
                    },
                    style = MiuixTheme.textStyles.body2,
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        val samples = ArrayDeque<Long>(WINDOW_FRAME_CAP)
        val avgHistory = ArrayDeque<AvgTick>(REF_HISTORY_CAP)
        var sumNs = 0L
        var lastFrameNs = 0L
        var nextRefreshNs = 0L
        while (true) {
            withFrameNanos { frameNs ->
                if (lastFrameNs != 0L) {
                    val delta = frameNs - lastFrameNs
                    if (delta in 1L..IDLE_THRESHOLD_NS) {
                        samples.addLast(delta)
                        sumNs += delta
                        while (sumNs > WINDOW_NS && samples.size > MIN_SAMPLES) {
                            sumNs -= samples.removeFirst()
                        }
                        while (samples.size > WINDOW_FRAME_CAP) {
                            sumNs -= samples.removeFirst()
                        }
                        if (frameNs >= nextRefreshNs && samples.size >= MIN_SAMPLES) {
                            val newStats = computeFpsStats(samples, sumNs)
                            stats = newStats
                            avgHistory.addLast(AvgTick(frameNs, newStats.avg))
                            while (avgHistory.size > 1 && frameNs - avgHistory.first().timeNs > REF_HISTORY_NS) {
                                avgHistory.removeFirst()
                            }
                            while (avgHistory.size > REF_HISTORY_CAP) {
                                avgHistory.removeFirst()
                            }
                            refFps = avgHistory.maxOf { it.avg }
                            nextRefreshNs = frameNs + REFRESH_INTERVAL_NS
                        }
                    }
                }
                lastFrameNs = frameNs
            }
        }
    }
}

@Immutable
private data class FpsStats(val avg: Int, val low1: Int) {
    companion object {
        val Empty = FpsStats(0, 0)
    }
}

private data class AvgTick(val timeNs: Long, val avg: Int)

private fun computeFpsStats(samples: ArrayDeque<Long>, sumNs: Long): FpsStats {
    val size = samples.size
    val avgNs = sumNs.toDouble() / size
    val sorted = samples.toLongArray().also { it.sort() }
    val low1Count = (size / 100).coerceAtLeast(1)
    var low1SumNs = 0L
    for (i in size - low1Count until size) low1SumNs += sorted[i]
    val low1AvgNs = low1SumNs.toDouble() / low1Count
    return FpsStats(
        avg = (NS_PER_SECOND / avgNs).toInt(),
        low1 = (NS_PER_SECOND / low1AvgNs).toInt(),
    )
}

private fun healthColor(value: Int, ref: Int): Color {
    if (ref == 0 || value == 0) return Color.Gray
    val pct = (value.toFloat() / ref).coerceIn(0f, 1f)
    return when {
        pct >= 0.75f -> lerp(HealthYellow, HealthGreen, (pct - 0.75f) / 0.25f)
        pct >= 0.50f -> lerp(HealthRed, HealthYellow, (pct - 0.50f) / 0.25f)
        else -> HealthRed
    }
}

private val HealthGreen = Color(0xFF36D167)
private val HealthYellow = Color(0xFFFFB21D)
private val HealthRed = Color(0xFFFF5B29)

private val PillCorner = 12.dp

private const val NS_PER_SECOND = 1_000_000_000.0
private const val WINDOW_NS = 5L * 1_000_000_000L // 5 seconds
private const val WINDOW_FRAME_CAP = 1_200 // upper bound for ~240Hz × 5s
private const val MIN_SAMPLES = 30 // need at least ~0.5s of data before reporting
private const val REFRESH_INTERVAL_NS = 500_000_000L // refresh stats twice per second
private const val IDLE_THRESHOLD_NS = 500_000_000L // ignore gaps > 500ms (idle frames)
private const val REF_HISTORY_NS = 30L * 1_000_000_000L // refFps reflects best avg in last 30s
private const val REF_HISTORY_CAP = 120 // hard cap on stored ticks (2Hz × 60s headroom)
