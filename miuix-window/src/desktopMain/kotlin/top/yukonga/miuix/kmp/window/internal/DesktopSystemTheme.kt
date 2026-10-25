// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import org.jetbrains.skiko.currentSystemTheme
import org.jetbrains.skiko.SystemTheme as SkikoSystemTheme

@Composable
internal fun rememberDesktopSystemDarkTheme(): Boolean {
    var isDark by remember {
        mutableStateOf(currentSystemTheme == SkikoSystemTheme.DARK)
    }
    LaunchedEffect(Unit) {
        while (isActive) {
            val current = currentSystemTheme == SkikoSystemTheme.DARK
            if (isDark != current) {
                isDark = current
            }
            delay(500)
        }
    }
    return isDark
}
