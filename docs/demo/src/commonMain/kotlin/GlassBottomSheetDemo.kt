// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassBottomSheet
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassBottomSheetDemo() {
    val backdrop = rememberLayerBackdrop()
    var showSheet by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var soundEnabled by remember { mutableStateOf(false) }
    var previewsEnabled by remember { mutableStateOf(true) }

    Scaffold {
        Box(Modifier.fillMaxSize()) {
            GlassDemoBackground(backdrop) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(24.dp)
                        .widthIn(max = 600.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Glass bottom sheet",
                        style = MiuixTheme.textStyles.headline2,
                        color = MiuixTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = "Notifications: ${if (notificationsEnabled) "On" else "Off"}\n" +
                            "Sound: ${if (soundEnabled) "On" else "Off"}\n" +
                            "Previews: ${if (previewsEnabled) "On" else "Off"}",
                        color = MiuixTheme.colorScheme.onBackground,
                        textAlign = TextAlign.Center,
                    )
                    TextButton(
                        text = "Open notification options",
                        onClick = { showSheet = true },
                    )
                }
            }
            GlassBottomSheet(
                show = showSheet,
                backdrop = backdrop,
                title = "Notifications",
                onDismissRequest = { showSheet = false },
                endAction = {
                    TextButton(
                        text = "Done",
                        onClick = { showSheet = false },
                    )
                },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 260.dp)
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 24.dp),
                ) {
                    Text(
                        text = "Changes apply immediately. Scroll to see every option.",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = MiuixTheme.colorScheme.onBackground,
                    )
                    SwitchPreference(
                        title = "Notifications",
                        summary = if (notificationsEnabled) "Push notifications are on" else "Push notifications are off",
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it },
                    )
                    SwitchPreference(
                        title = "Sound",
                        summary = if (soundEnabled) "Notification sound is on" else "Notification sound is off",
                        checked = soundEnabled,
                        onCheckedChange = { soundEnabled = it },
                    )
                    SwitchPreference(
                        title = "Message previews",
                        summary = if (previewsEnabled) "Show message content" else "Hide message content",
                        checked = previewsEnabled,
                        onCheckedChange = { previewsEnabled = it },
                    )
                }
            }
        }
    }
}
