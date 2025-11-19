// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.utils.Platform
import top.yukonga.miuix.kmp.utils.platform
import ui.AppTheme

@Composable
fun App(
    colorMode: MutableState<Int> = remember { mutableStateOf(0) },
    padding: PaddingValues = PaddingValues(0.dp),
    enableOverScroll: Boolean = platform() == Platform.Android || platform() == Platform.IOS
) {
    AppTheme(colorMode = colorMode.value) {
            UITest(
                colorMode = colorMode,
                padding = padding,
                enableOverScroll = enableOverScroll
            )
    }
}
