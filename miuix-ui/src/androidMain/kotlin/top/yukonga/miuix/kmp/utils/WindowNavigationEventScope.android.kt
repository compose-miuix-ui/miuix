// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import androidx.navigationevent.findViewTreeNavigationEventDispatcherOwner

@Composable
actual fun WindowNavigationEventScope(content: @Composable () -> Unit) {
    // Only this window's own dispatcher (resolved via its view tree) receives system back while
    // the window is focused; an inherited explicit provide must not shadow it.
    val view = LocalView.current
    val owner = remember(view) { view.findViewTreeNavigationEventDispatcherOwner() }
    if (owner != null) {
        CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides owner, content = content)
    } else {
        content()
    }
}
