// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.utils.Platform
import top.yukonga.miuix.kmp.utils.platform
import ui.AppTheme
import ui.keyColorFor

@Composable
fun App(
    colorMode: MutableState<Int> = remember { mutableIntStateOf(0) },
    seedIndex: MutableState<Int> = remember { mutableIntStateOf(0) },
    paletteStyle: MutableState<Int> = remember { mutableIntStateOf(0) },
    colorSpec: MutableState<Int> = remember { mutableIntStateOf(0) },
    padding: PaddingValues = PaddingValues(0.dp),
    enableOverScroll: Boolean = platform() == Platform.Android || platform() == Platform.IOS,
) {
    val keyColor = keyColorFor(seedIndex.value)

    AppTheme(
        colorMode = colorMode.value,
        keyColor = keyColor,
        paletteStyle = paletteStyle.value,
        colorSpec = colorSpec.value,
    ) {
        UITest(
            colorMode = colorMode,
            seedIndex = seedIndex,
            paletteStyle = paletteStyle,
            colorSpec = colorSpec,
            padding = padding,
            enableOverScroll = enableOverScroll,
        )
    }
}