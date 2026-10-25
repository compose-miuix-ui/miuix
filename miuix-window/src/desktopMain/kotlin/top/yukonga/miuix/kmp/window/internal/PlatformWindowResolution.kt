// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.internal

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowDecoration
import top.yukonga.miuix.kmp.window.platform.linux.LinuxWindowBackend

@OptIn(ExperimentalComposeUiApi::class)
internal fun WindowDecoration.resolveForPlatform(): WindowDecoration {
    val requiresUndecorated = DesktopOS.isLinux() &&
        this == WindowDecoration.SystemDefault &&
        LinuxWindowBackend.current != LinuxWindowBackend.FallbackSystem
    return if (requiresUndecorated) WindowDecoration.Undecorated(0.dp) else this
}
