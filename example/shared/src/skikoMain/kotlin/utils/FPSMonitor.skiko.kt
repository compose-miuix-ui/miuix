// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.withFrameNanos

@Composable
internal actual fun ObserveFrameStats(
    onStatsUpdate: (stats: FpsStats, refFps: Int) -> Unit,
) {
    val currentOnStatsUpdate by rememberUpdatedState(onStatsUpdate)

    LaunchedEffect(Unit) {
        val collector = FpsSampleCollector()
        var lastFrameNs = 0L
        while (true) {
            withFrameNanos { frameNs ->
                if (lastFrameNs != 0L) {
                    val delta = frameNs - lastFrameNs
                    if (delta in 1L..IDLE_THRESHOLD_NS) {
                        collector.record(delta, frameNs, currentOnStatsUpdate)
                    }
                }
                lastFrameNs = frameNs
            }
        }
    }
}
