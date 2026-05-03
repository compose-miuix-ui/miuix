// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.preference.RadioButtonPreference

@Composable
fun RadioButtonPreferenceDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(demoBackground()),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var selectedIndex by remember { mutableIntStateOf(0) }

            Card {
                RadioButtonPreference(
                    title = "Option A",
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 },
                )
                RadioButtonPreference(
                    title = "Option B",
                    summary = "With summary description",
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 },
                )
                RadioButtonPreference(
                    title = "Option C",
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 },
                )
                RadioButtonPreference(
                    title = "Disabled RadioButton",
                    summary = "This radio button is currently unavailable",
                    selected = true,
                    onClick = {},
                    enabled = false,
                )
            }
        }
    }
}
