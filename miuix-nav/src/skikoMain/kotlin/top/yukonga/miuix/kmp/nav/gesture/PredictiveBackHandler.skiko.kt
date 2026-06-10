// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import top.yukonga.miuix.kmp.nav.transition.NavSwipeEdge

/**
 * Non-Android (skiko) [PredictiveBackHandler] actual.
 *
 * The skiko targets (iOS, macOS, Desktop, Web) lack a unified system predictive-back stream, so
 * this actual handles only *discrete* back triggers (Desktop ESC, Web programmatic back). Each
 * trigger emits a minimal synthetic two-point [NavBackEvent] flow (`progress` 0f -> 1f) and then
 * commits, satisfying the common contract that [onProgress] is always collected and exactly one of
 * [onCommit] / [onCancel] fires.
 *
 * Continuous, finger-following edge swipes on iOS are provided separately by
 * `Modifier.navSwipeDismiss`, which drives `animatedTop` directly; this actual deliberately does
 * not attempt to synthesise a gesture stream for them.
 *
 * Keyboard wiring (ESC) is installed at the navigation host via [navBackKeyModifier]; this
 * composable itself holds no key listener so that the host owns focus placement.
 */
@Composable
actual fun PredictiveBackHandler(
    enabled: Boolean,
    onProgress: suspend (Flow<NavBackEvent>) -> Unit,
    onCommit: () -> Unit,
    onCancel: () -> Unit,
) {
    val request = LocalNavBackRequest.current
    val currentOnProgress = rememberUpdatedState(onProgress)
    val currentOnCommit = rememberUpdatedState(onCommit)

    LaunchedEffect(enabled, request) {
        if (!enabled) return@LaunchedEffect
        request.events.collect {
            currentOnProgress.value(syntheticBackFlow())
            currentOnCommit.value()
        }
    }
}

/**
 * A minimal two-point synthetic back stream used when the platform offers no continuous progress
 * source (e.g. a Desktop ESC press). Emits `progress` 0f then 1f from [NavSwipeEdge.None].
 */
private fun syntheticBackFlow(): Flow<NavBackEvent> = flow {
    emit(NavBackEvent(progress = 0f, swipeEdge = NavSwipeEdge.None, touchY = 0f))
    emit(NavBackEvent(progress = 1f, swipeEdge = NavSwipeEdge.None, touchY = 0f))
}
