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
import top.yukonga.miuix.kmp.preference.CheckboxLocation
import top.yukonga.miuix.kmp.preference.CheckboxPreference

@Composable
fun CheckboxPreferenceDemo() {
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
            var rightChecked by remember { mutableStateOf(false) }
            var notificationsEnabled by remember { mutableStateOf(false) }

            Card {
                CheckboxPreference(
                    title = "Checkbox Option",
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                )
                CheckboxPreference(
                    title = "Notifications",
                    summary = "Receive push notifications from the app",
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                )
                CheckboxPreference(
                    title = "End Checkbox",
                    summary = "Checkbox is on the end side",
                    checked = rightChecked,
                    onCheckedChange = { rightChecked = it },
                    checkboxLocation = CheckboxLocation.End,
                )
                CheckboxPreference(
                    title = "Disabled Checkbox",
                    summary = "This checkbox is currently unavailable",
                    checked = true,
                    onCheckedChange = {},
                    enabled = false,
                )
            }
        }
    }
}
