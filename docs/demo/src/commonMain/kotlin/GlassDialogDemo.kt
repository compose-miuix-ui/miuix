// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDialog
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassDialogButtonRole
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassDialogDemo() {
    val backdrop = rememberLayerBackdrop()
    var showDialog by remember { mutableStateOf(false) }
    var saveCount by remember { mutableIntStateOf(0) }
    var status by remember { mutableStateOf("No changes saved yet") }

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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp),
            ) {
                Scaffold(
                    containerColor = MiuixTheme.colorScheme.surface,
                ) { paddingValues ->
                    GlassDemoBackground(backdrop) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            TextButton(
                                text = "Show a GlassDialog",
                                onClick = { showDialog = true },
                            )
                            Text(
                                text = status,
                                color = MiuixTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                    GlassDialog(
                        show = showDialog,
                        backdrop = backdrop,
                        title = "Save appearance?",
                        summary = "The normal and primary actions share the dialog's glass material.",
                        onDismissRequest = {
                            status = "Dismissed without saving"
                            showDialog = false
                        },
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            GlassDialogButton(
                                modifier = Modifier.weight(1f),
                                role = GlassDialogButtonRole.Normal,
                                onClick = {
                                    status = "Cancelled without saving"
                                    showDialog = false
                                },
                            ) {
                                Text("Cancel", style = MiuixTheme.textStyles.button)
                            }
                            GlassDialogButton(
                                modifier = Modifier.weight(1f),
                                role = GlassDialogButtonRole.Primary,
                                onClick = {
                                    saveCount++
                                    status = "Saved changes $saveCount time${if (saveCount == 1) "" else "s"}"
                                    showDialog = false
                                },
                            ) {
                                Text("Save", style = MiuixTheme.textStyles.button)
                            }
                        }
                    }
                }
            }
        }
    }
}
