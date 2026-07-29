// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.runtime.Composable
import androidx.navigationevent.DirectNavigationEventInput
import androidx.navigationevent.NavigationEvent
import androidx.navigationevent.NavigationEventHandler
import androidx.navigationevent.NavigationEventInfo

/**
 * Forwards this platform window's back events to an explicitly inherited navigation dispatcher.
 *
 * This is an interoperability fallback for separate-window components that inherit a provided
 * `LocalNavigationEventDispatcherOwner` but register their own back handler before caller content
 * can rebind the dispatcher to the new window. On Android, call this once from the component's
 * window content. The complete predictive-back sequence is forwarded only when the inherited
 * dispatcher differs from the dispatcher attached to the current window.
 *
 * The bridge must be composed **inside** the separate window's content so it can resolve that
 * window's dispatcher. For example, with a third-party modal bottom sheet:
 *
 * ```kotlin
 * ModalBottomSheet(onDismissRequest = onDismissRequest) {
 *     WindowNavigationEventBridge()
 *     SheetContent()
 * }
 * ```
 *
 * Add only one bridge per window. It automatically unregisters when the window content leaves the
 * composition. Calling it outside the separate window does not bridge that window's events.
 *
 * Components that control their window composition root should instead provide that window's own
 * dispatcher there. Miuix Window* components already do this and do not need the bridge. On Skiko
 * platforms this is a no-op because dialogs share the host window.
 */
@Composable
expect fun WindowNavigationEventBridge()

internal class ForwardingNavigationEventHandler(
    private val input: DirectNavigationEventInput,
) : NavigationEventHandler<NavigationEventInfo>(
    initialInfo = NavigationEventInfo.None,
    isBackEnabled = true,
    isForwardEnabled = false,
) {
    override fun onBackStarted(event: NavigationEvent) {
        input.backStarted(event)
    }

    override fun onBackProgressed(event: NavigationEvent) {
        input.backProgressed(event)
    }

    override fun onBackCancelled() {
        input.backCancelled()
    }

    override fun onBackCompleted() {
        input.backCompleted()
    }
}
