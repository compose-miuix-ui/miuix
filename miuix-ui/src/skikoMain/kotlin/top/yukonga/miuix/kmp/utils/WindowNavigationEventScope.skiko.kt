// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.runtime.Composable

@Composable
actual fun WindowNavigationEventScope(content: @Composable () -> Unit) {
    // Dialogs are layered inside the host window; there is no window boundary to rebind across.
    content()
}
