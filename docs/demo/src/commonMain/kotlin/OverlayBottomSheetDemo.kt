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
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Close
import top.yukonga.miuix.kmp.icon.extended.Ok
import top.yukonga.miuix.kmp.overlay.OverlayBottomSheet
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun OverlayBottomSheetDemo() {
    Scaffold {
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
                var showBottomSheet by remember { mutableStateOf(false) }
                var notificationsEnabled by remember { mutableStateOf(true) }
                var soundEnabled by remember { mutableStateOf(false) }

                Card {
                    TextButton(
                        text = "Show a BottomSheet",
                        onClick = { showBottomSheet = true },
                    )
                    OverlayBottomSheet(
                        title = "BottomSheet Title",
                        show = showBottomSheet,
                        startAction = {
                            IconButton(onClick = { showBottomSheet = false }) {
                                Icon(
                                    imageVector = MiuixIcons.Close,
                                    contentDescription = "Cancel",
                                    tint = MiuixTheme.colorScheme.onBackground,
                                )
                            }
                        },
                        endAction = {
                            IconButton(onClick = { showBottomSheet = false }) {
                                Icon(
                                    imageVector = MiuixIcons.Ok,
                                    contentDescription = "Confirm",
                                    tint = MiuixTheme.colorScheme.onBackground,
                                )
                            }
                        },
                        onDismissRequest = { showBottomSheet = false },
                    ) {
                        Card(modifier = Modifier.padding(bottom = 16.dp)) {
                            SwitchPreference(
                                title = "Notifications",
                                summary = "Receive push notifications",
                                checked = notificationsEnabled,
                                onCheckedChange = { notificationsEnabled = it },
                            )
                            SwitchPreference(
                                title = "Sound",
                                summary = "Play sound on notification",
                                checked = soundEnabled,
                                onCheckedChange = { soundEnabled = it },
                            )
                        }
                    }
                }
            }
        }
    }
}
