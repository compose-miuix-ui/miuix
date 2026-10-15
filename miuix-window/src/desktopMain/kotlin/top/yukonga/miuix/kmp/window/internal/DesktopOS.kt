// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.internal

import com.sun.jna.platform.win32.Kernel32
import com.sun.jna.platform.win32.WinNT

sealed class DesktopOS {
    data class Windows(val buildNumber: Int) : DesktopOS() {
        companion object {
            const val WINDOWS_11_21H2 = 22000
            const val WINDOWS_11_22H2 = 22621
        }
    }

    data class MacOS(val version: String, val build: String = "") : DesktopOS() {
        fun supportsVibrancy(): Boolean {
            val components = version.split('.')
            val major = components.getOrNull(0)?.toIntOrNull() ?: return false
            val minor = components.getOrNull(1)?.toIntOrNull() ?: 0
            return major > 10 || (major == 10 && minor >= 14)
        }
    }

    data class Linux(val version: String, val distro: String = "") : DesktopOS()

    data object Unknown : DesktopOS()

    companion object {
        val current: DesktopOS by lazy { detect() }

        fun isWindows(): Boolean = current is Windows
        fun isMacOS(): Boolean = current is MacOS
        fun isLinux(): Boolean = current is Linux

        private fun detect(): DesktopOS {
            val osName = System.getProperty("os.name") ?: return Unknown
            return when {
                osName.startsWith("Mac") || osName == "Darwin" -> {
                    MacOS(version = System.getProperty("os.version") ?: "")
                }

                osName.startsWith("Win") -> {
                    runCatching {
                        val osVersionInfoEx = WinNT.OSVERSIONINFOEX()
                        Kernel32.INSTANCE.GetVersionEx(osVersionInfoEx)
                        Windows(osVersionInfoEx.buildNumber)
                    }.getOrElse {
                        Windows(0)
                    }
                }

                osName.startsWith("Linux") -> {
                    Linux(version = System.getProperty("os.version") ?: "")
                }

                else -> Unknown
            }
        }
    }
}
