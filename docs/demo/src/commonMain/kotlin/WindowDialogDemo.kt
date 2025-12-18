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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.extra.LocalWindowDialogState
import top.yukonga.miuix.kmp.extra.WindowDialog

@Composable
fun WindowDialogDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xfff77062), Color(0xfffe5196)))),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val showDialog = remember { mutableStateOf(false) }
            Card {
                TextButton(
                    text = "Show a WindowDialog",
                    onClick = { showDialog.value = true }
                )
                WindowDialog(
                    title = "WindowDialog Title",
                    summary = "This is a window-level dialog that does not require MiuixPopupHost.",
                    show = showDialog,
                    onDismissRequest = { showDialog.value = false }
                ) {
                    val dismiss = LocalWindowDialogState.current
                    TextButton(
                        text = "Confirm",
                        onClick = { dismiss?.invoke() },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
