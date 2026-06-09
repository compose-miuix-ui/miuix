// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.activity.BackEventCompat
import androidx.activity.compose.PredictiveBackHandler
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import top.yukonga.miuix.kmp.nav.transition.NavSwipeEdge

/**
 * Android [PredictiveBackHandler] actual.
 *
 * Bridges the platform predictive-back stream exposed by
 * [androidx.activity.compose.PredictiveBackHandler] (a `Flow<BackEventCompat>`) into the
 * common [NavBackEvent] stream. Normal completion of the underlying suspend block marks a commit;
 * a [CancellationException] thrown while collecting marks a cancel.
 */
@Composable
actual fun PredictiveBackHandler(
    enabled: Boolean,
    onProgress: suspend (Flow<NavBackEvent>) -> Unit,
    onCommit: () -> Unit,
    onCancel: () -> Unit,
) {
    PredictiveBackHandler(enabled = enabled) { backEvents: Flow<BackEventCompat> ->
        try {
            onProgress(backEvents.map { it.toNavBackEvent() })
            // Reaching here means the gesture stream completed without cancellation: committed.
            onCommit()
        } catch (e: CancellationException) {
            // The gesture was cancelled before commit; propagate so the coroutine unwinds cleanly.
            onCancel()
            throw e
        }
    }
}

/** Maps an [androidx.activity.BackEventCompat] to the common [NavBackEvent]. */
private fun BackEventCompat.toNavBackEvent(): NavBackEvent = NavBackEvent(
    progress = progress,
    swipeEdge = when (swipeEdge) {
        BackEventCompat.EDGE_LEFT -> NavSwipeEdge.Left
        BackEventCompat.EDGE_RIGHT -> NavSwipeEdge.Right
        else -> NavSwipeEdge.None
    },
    touchY = touchY,
)
