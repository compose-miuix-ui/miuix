// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import top.yukonga.miuix.kmp.window.internal.DesktopOS

/**
 * Window background type.
 */
enum class MiuixWindowBackgroundType {
    None,

    /**
     * Windows 11 22000+
     */
    Mica,

    /**
     * Windows 11 22621+
     */
    Acrylic,

    /**
     * Windows 11 22621+
     */
    MicaAlt,

    /**
     * Native macOS 10.14+ vibrancy backed by `NSVisualEffectView`.
     */
    Vibrancy,

    ;

    /**
     * Whether the background type is supported on the current OS.
     */
    fun isSupported(): Boolean {
        val os = DesktopOS.current
        return when (this) {
            None -> true
            Mica -> os is DesktopOS.Windows && os.buildNumber >= DesktopOS.Windows.WINDOWS_11_21H2
            Acrylic -> os is DesktopOS.Windows && os.buildNumber >= DesktopOS.Windows.WINDOWS_11_22H2
            MicaAlt -> os is DesktopOS.Windows && os.buildNumber >= DesktopOS.Windows.WINDOWS_11_22H2
            Vibrancy -> os is DesktopOS.MacOS && os.supportsVibrancy()
        }
    }
}
