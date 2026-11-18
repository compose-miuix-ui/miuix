// Copyright 2026, compose-miuix-ui contributors
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
import top.yukonga.miuix.kmp.glass.GlassDialogDefaults
import top.yukonga.miuix.kmp.glass.GlassTextField
import top.yukonga.miuix.kmp.glass.glassPanel
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape

@Composable
fun GlassTextFieldDemo() {
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }

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
                    .height(200.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    GlassDemoBackground(backdrop) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column(
                                modifier = Modifier
                                    .widthIn(max = 400.dp)
                                    .fillMaxWidth()
                                    .glassPanel(
                                        backdrop = backdrop,
                                        shape = GlassShape(24.dp),
                                        material = GlassDialogDefaults.material(),
                                        shadow = GlassShadows.Float,
                                    )
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                            ) {
                                GlassTextField(
                                    value = text,
                                    onValueChange = { text = it },
                                    label = "Input something...",
                                    useLabelAsPlaceholder = true,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
