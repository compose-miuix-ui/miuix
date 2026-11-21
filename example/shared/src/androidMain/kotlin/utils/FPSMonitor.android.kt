// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.metrics.performance.FrameDataApi31
import androidx.metrics.performance.JankStats

@Composable
internal actual fun ObserveFrameStats(
    onStatsUpdate: (stats: FpsStats, refFps: Int) -> Unit,
) {
    val currentOnStatsUpdate by rememberUpdatedState(onStatsUpdate)
    val view = LocalView.current
    val window = remember(view) { findWindow(view) }

    if (window != null) {
        DisposableEffect(window) {
            val collector = FpsSampleCollector()
            var lastRefreshRate = view.display?.refreshRate?.takeIf { it > 0f } ?: 0f
            var framePeriodNs = (NS_PER_SECOND / (if (lastRefreshRate > 0f) lastRefreshRate else 60f)).toLong()

            val jankStats = JankStats.createAndTrack(window) { frameData ->
                val rawDurationNs = (frameData as? FrameDataApi31)?.frameDurationTotalNanos
                    ?: frameData.frameDurationUiNanos
                if (rawDurationNs > 0L) {
                    val currentRefreshRate = view.display?.refreshRate?.takeIf { it > 0f }
                    if (currentRefreshRate != null && currentRefreshRate != lastRefreshRate) {
                        lastRefreshRate = currentRefreshRate
                        framePeriodNs = (NS_PER_SECOND / currentRefreshRate).toLong()
                    }
                    collector.record(
                        durationNs = maxOf(rawDurationNs, framePeriodNs),
                        timestampNs = frameData.frameStartNanos,
                        onStatsUpdate = currentOnStatsUpdate,
                    )
                }
            }

            onDispose {
                jankStats.isTrackingEnabled = false
            }
        }
    }
}

private fun findWindow(view: android.view.View): Window? {
    var parent = view.parent
    while (parent != null) {
        if (parent is DialogWindowProvider) {
            return parent.window
        }
        parent = parent.parent
    }
    return view.context.findActivity()?.window
}

private tailrec fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
