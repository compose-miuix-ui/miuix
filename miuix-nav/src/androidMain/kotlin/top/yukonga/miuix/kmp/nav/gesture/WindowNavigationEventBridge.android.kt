// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.navigationevent.DirectNavigationEventInput
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import androidx.navigationevent.findViewTreeNavigationEventDispatcherOwner

@Composable
actual fun WindowNavigationEventBridge() {
    val inheritedDispatcher =
        LocalNavigationEventDispatcherOwner.current?.navigationEventDispatcher ?: return
    val view = LocalView.current
    val windowDispatcher = remember(view) {
        view.findViewTreeNavigationEventDispatcherOwner()?.navigationEventDispatcher
    } ?: return
    if (windowDispatcher === inheritedDispatcher) return

    val forwardingInput = remember(inheritedDispatcher) { DirectNavigationEventInput() }
    val forwardingHandler = remember(windowDispatcher, forwardingInput) {
        ForwardingNavigationEventHandler(forwardingInput)
    }

    DisposableEffect(inheritedDispatcher, forwardingInput) {
        inheritedDispatcher.addInput(forwardingInput)
        onDispose {
            try {
                inheritedDispatcher.removeInput(forwardingInput)
            } catch (_: IllegalStateException) {
                // The owning nav hierarchy already disposed this descendant dispatcher.
            }
        }
    }
    DisposableEffect(windowDispatcher, forwardingHandler) {
        windowDispatcher.addHandler(forwardingHandler)
        onDispose { forwardingHandler.remove() }
    }
}
