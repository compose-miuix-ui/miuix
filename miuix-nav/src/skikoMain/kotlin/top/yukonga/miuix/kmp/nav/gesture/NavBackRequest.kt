// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

/**
 * Aggregates discrete "go back" triggers (Desktop ESC key, Web programmatic back) into a single
 * hot [SharedFlow] consumed by the skiko [PredictiveBackHandler] actual.
 *
 * This is the skiko substitute for a system predictive-back dispatcher: there is no per-event
 * progress, only a fire-and-commit signal.
 *
 * Stable, not immutable: the identity is fixed but the backing hot flow's state changes on each
 * [request] emission (those changes are observed via [events], not Compose snapshot state).
 */
@Stable
class NavBackRequest {
    private val _events = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    /** Hot stream of discrete back requests. Each emission triggers one commit. */
    val events: SharedFlow<Unit> = _events

    /** Programmatically request a back navigation (used by Web custom triggers). */
    fun request() {
        _events.tryEmit(Unit)
    }
}

/**
 * Provides the active [NavBackRequest] to the skiko [PredictiveBackHandler] actual. Defaults to a
 * detached instance so reads never crash; the navigation host installs the real one.
 */
val LocalNavBackRequest = staticCompositionLocalOf { NavBackRequest() }

/**
 * Installs an ESC-key listener that feeds [request] on Desktop/Web. Attach this to the navigation
 * host's root node (which must be focusable) so a top-level ESC press maps to a back navigation.
 *
 * On iOS/macOS without a hardware keyboard this is inert; continuous edge swipes are handled by
 * `Modifier.navSwipeDismiss` instead.
 */
fun Modifier.navBackKeyModifier(enabled: Boolean, request: NavBackRequest): Modifier = if (!enabled) {
    this
} else {
    this.onPreviewKeyEvent { event ->
        if (event.type == KeyEventType.KeyDown && event.key == Key.Escape) {
            request.request()
            true
        } else {
            false
        }
    }
}
