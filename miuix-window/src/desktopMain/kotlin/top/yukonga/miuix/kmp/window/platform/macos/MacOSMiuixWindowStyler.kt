// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.macos

import top.yukonga.miuix.kmp.window.MiuixWindowBackgroundType
import top.yukonga.miuix.kmp.window.internal.WindowStyler
import top.yukonga.miuix.kmp.window.internal.findSkiaLayer
import java.awt.Window

internal class MacOSMiuixWindowStyler(
    window: Window,
) : WindowStyler {
    private val skiaLayer = window.findSkiaLayer()
    private val vibrancy = MacOSWindowVibrancy(window)
    private val titleBar = MacOSWindowTitleBar(window)

    fun updateTitleBar(captionBarHeight: Float) {
        titleBar.update(captionBarHeight)
    }

    override fun updateIsResizable(value: Boolean) {}

    override fun updateBackground(type: MiuixWindowBackgroundType, isDarkTheme: Boolean) {
        val isVibrancyActive = vibrancy.update(
            enabled = type == MiuixWindowBackgroundType.Vibrancy,
            isDarkTheme = isDarkTheme,
        )
        if (isVibrancyActive) {
            skiaLayer?.transparency = true
        }
    }

    fun performWindowDrag(): Boolean = titleBar.performWindowDrag()

    fun dispose() {
        titleBar.dispose()
        vibrancy.dispose()
    }
}
