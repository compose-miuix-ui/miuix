// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.extra.LocalWindowBottomSheetState
import top.yukonga.miuix.kmp.extra.WindowBottomSheet

@Composable
fun WindowBottomSheetDemo() {
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
            val showBottomSheet = remember { mutableStateOf(false) }
            Card {
                TextButton(
                    text = "Show a WindowBottomSheet",
                    onClick = { showBottomSheet.value = true }
                )
                WindowBottomSheet(
                    show = showBottomSheet,
                    title = "WindowBottomSheet Title",
                    onDismissRequest = { showBottomSheet.value = false }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("This is a window-level bottom sheet that does not require MiuixPopupHost.")
                        Box(modifier = Modifier.height(16.dp))
                        val dismiss = LocalWindowBottomSheetState.current
                        TextButton(
                            text = "Close",
                            onClick = { dismiss?.invoke() },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
