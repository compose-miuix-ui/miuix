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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.preference.SwitchPreference

@Composable
fun SwitchPreferenceDemo() {
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
            var isChecked by remember { mutableStateOf(false) }
            var wifiEnabled by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier.weight(0.5f),
            ) {
                SwitchPreference(
                    title = "Switch Option",
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                )
                SwitchPreference(
                    title = "WiFi",
                    summary = "Turn on to connect to wireless networks",
                    checked = wifiEnabled,
                    onCheckedChange = { wifiEnabled = it },
                )
                SwitchPreference(
                    title = "Disabled Switch",
                    summary = "This switch is currently unavailable",
                    checked = true,
                    onCheckedChange = {},
                    enabled = false,
                )
            }
        }
    }
}
