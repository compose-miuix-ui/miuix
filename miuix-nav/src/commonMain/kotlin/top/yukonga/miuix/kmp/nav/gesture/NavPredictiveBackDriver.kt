// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import kotlinx.coroutines.flow.Flow
import top.yukonga.miuix.kmp.nav.runtime.snapToFinger

/**
 * Drives `animatedTop` from a predictive-back [NavBackEvent] stream, implementing the finger-
 * following half of the §7.1 double-mode contract:
 *
 * - **During the gesture** each event maps to `animatedTop.snapToFinger(topIndex, progress)` —
 *   linear, no interpolation, strictly 1:1 with the finger (Phase 3 driver).
 * - The function returns when the stream completes. Convergence (commit -> `topIndex - 1`, cancel ->
 *   `topIndex`) is **not** done here: the caller decides commit/cancel via
 *   [top.yukonga.miuix.kmp.nav.runtime.navBackCommitDecision] and settles with the single shared
 *   spring via [top.yukonga.miuix.kmp.nav.runtime.settleTo], handing off the release velocity so the
 *   snap -> spring boundary is velocity-continuous.
 *
 * Keeping settle/decision out of this function is deliberate: `PredictiveBackHandler`'s commit/cancel
 * signal comes from the outer try/catch around the stream, not from inside it (Task 8.9).
 *
 * @param events Per-gesture cold flow of [NavBackEvent].
 * @param animatedTop The single shared depth driver.
 * @param topIndex The current top entry index the gesture is peeling away from.
 */
internal suspend fun drivePredictiveBack(
    events: Flow<NavBackEvent>,
    animatedTop: Animatable<Float, AnimationVector1D>,
    topIndex: Int,
) {
    events.collect { event ->
        // Linear, finger-following: animatedTop = topIndex - progress (clamped inside snapToFinger).
        animatedTop.snapToFinger(topIndex = topIndex, progress = event.progress)
    }
}
