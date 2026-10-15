// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows

internal enum class DwmWindowAttribute(
    val value: Int,
) {
    DWMWA_USE_IMMERSIVE_DARK_MODE(20),
    DWMWA_CAPTION_COLOR(35),
    DWMWA_SYSTEMBACKDROP_TYPE(38),
    DWMWA_MICA_EFFECT(1029),
}
