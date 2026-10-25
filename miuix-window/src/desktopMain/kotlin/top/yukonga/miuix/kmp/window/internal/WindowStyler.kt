// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.internal

import top.yukonga.miuix.kmp.window.MiuixWindowBackgroundType

internal interface WindowStyler {
    fun updateIsResizable(value: Boolean)
    fun updateBackground(type: MiuixWindowBackgroundType, isDarkTheme: Boolean)
}
