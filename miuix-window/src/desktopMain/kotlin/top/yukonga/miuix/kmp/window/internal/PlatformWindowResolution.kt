// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.internal

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowDecoration

@OptIn(ExperimentalComposeUiApi::class)
internal fun WindowDecoration.resolveForPlatform(): WindowDecoration {
    val requiresUndecorated = DesktopOS.isLinux() && this == WindowDecoration.SystemDefault
    return if (requiresUndecorated) WindowDecoration.Undecorated(0.dp) else this
}
