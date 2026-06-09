// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.Flow
import top.yukonga.miuix.kmp.nav.transition.NavSwipeEdge

/**
 * Payload emitted by the [PredictiveBackHandler] progress [Flow].
 *
 * Mirrors the fields of [top.yukonga.miuix.kmp.nav.transition.NavGesture] so the host can map a
 * back-gesture stream onto the single `animatedTop` driver, typically via Phase 3's
 * `animatedTop.snapToFinger(topIndex, event.progress)`.
 *
 * @property progress Gesture completion in the range `0f..1f`; `0f` at touch-down, approaching
 *   `1f` as the user commits the back gesture.
 * @property swipeEdge The screen edge the gesture originated from. Non-edge sources (Desktop ESC,
 *   Web custom triggers) report [NavSwipeEdge.None].
 * @property touchY The vertical touch position in pixels, used by transitions that pivot or
 *   anchor the outgoing layer around the finger.
 */
@Stable
class NavBackEvent(
    val progress: Float,
    val swipeEdge: NavSwipeEdge,
    val touchY: Float,
)

/**
 * Platform back-gesture bridge.
 *
 * Surfaces the platform's predictive-back stream as a coroutine [Flow] of [NavBackEvent] so the
 * navigation host can drive `animatedTop` continuously and decide commit/cancel at release.
 *
 * Lifecycle of a single gesture:
 * 1. The platform starts a back gesture and invokes [onProgress] with a cold [Flow]; the host
 *    collects it and snaps `animatedTop` to `topIndex - event.progress` for each emission.
 * 2. If the gesture is committed, the [Flow] completes normally and [onCommit] is invoked (pop the
 *    top entry and let the convergence spring settle to `topIndex - 1`).
 * 3. If the gesture is cancelled, the in-flight [onProgress] collection is cancelled and
 *    [onCancel] is invoked (spring `animatedTop` back to `topIndex`).
 *
 * Implementations MUST guarantee exactly one of [onCommit] / [onCancel] fires per started gesture.
 *
 * @param enabled Whether the handler is active. When `false`, the platform back action falls
 *   through to the next handler (e.g. exits the app on Android, no-ops elsewhere).
 * @param onProgress Suspend block receiving the cold per-gesture [Flow] of [NavBackEvent]. Collect
 *   it to track finger progress; the block returns when the gesture is committed.
 * @param onCommit Invoked once after [onProgress] completes normally (gesture committed).
 * @param onCancel Invoked once when the gesture is cancelled before commit.
 */
@Composable
expect fun PredictiveBackHandler(
    enabled: Boolean,
    onProgress: suspend (Flow<NavBackEvent>) -> Unit,
    onCommit: () -> Unit,
    onCancel: () -> Unit,
)
