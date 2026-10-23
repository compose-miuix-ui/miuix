// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassDropdownPreferenceDemo() {
    val backdrop = rememberLayerBackdrop()
    val languages = remember { listOf("English", "French", "Japanese") }
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold {
        Box(Modifier.fillMaxSize()) {
            GlassDemoBackground(backdrop) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                        .widthIn(max = 600.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Language settings",
                        style = MiuixTheme.textStyles.headline2,
                        color = MiuixTheme.colorScheme.onBackground,
                    )
                    Card(modifier = Modifier.fillMaxWidth()) {
                        GlassDropdownPreference(
                            title = "Language",
                            summary = "App language: ${languages[selectedIndex]}",
                            items = languages,
                            selectedIndex = selectedIndex,
                            onSelectedIndexChange = { selectedIndex = it },
                            backdrop = backdrop,
                        )
                    }
                    Text(
                        text = "Choose a language from the glass menu.",
                        color = MiuixTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
