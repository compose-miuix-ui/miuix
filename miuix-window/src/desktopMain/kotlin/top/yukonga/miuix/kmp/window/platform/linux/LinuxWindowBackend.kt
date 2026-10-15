// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import com.sun.jna.platform.unix.X11
import java.awt.Toolkit

sealed interface LinuxWindowBackend {
    data object JbrWayland : LinuxWindowBackend
    data object X11OrXWayland : LinuxWindowBackend
    data object FallbackSystem : LinuxWindowBackend

    companion object {
        val current: LinuxWindowBackend by lazy { detect() }

        private fun detect(): LinuxWindowBackend {
            val toolkitName = Toolkit.getDefaultToolkit().javaClass.name
            if (toolkitName.contains("WLToolkit")) {
                return JbrWayland
            }
            val x11Display = runCatching { X11.INSTANCE.XOpenDisplay(null) }.getOrNull()
            if (x11Display != null) {
                runCatching { X11.INSTANCE.XCloseDisplay(x11Display) }
                return X11OrXWayland
            }
            return FallbackSystem
        }
    }
}
