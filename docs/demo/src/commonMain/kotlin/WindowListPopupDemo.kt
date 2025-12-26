// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.DropdownImpl
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.extra.LocalWindowListPopupState
import top.yukonga.miuix.kmp.extra.WindowListPopup

@Composable
fun WindowListPopupDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Color(0xfff77062), Color(0xfffe5196)))),
        contentAlignment = Alignment.Center,
    ) {
        val showPopup = remember { mutableStateOf(false) }
        var selectedIndex by remember { mutableIntStateOf(0) }
        val items = listOf("Option 1", "Option 2", "Option 3")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            Box {
                TextButton(
                    text = "Show WindowListPopup",
                    onClick = { showPopup.value = true },
                    modifier = Modifier.padding(top = 16.dp),
                )
                WindowListPopup(
                    show = showPopup,
                    alignment = PopupPositionProvider.Align.TopLeft,
                    onDismissRequest = { showPopup.value = false },
                ) {
                    val dismiss = LocalWindowListPopupState.current
                    ListPopupColumn {
                        items.forEachIndexed { index, string ->
                            DropdownImpl(
                                text = string,
                                optionSize = items.size,
                                isSelected = selectedIndex == index,
                                onSelectedIndexChange = { selectedIdx ->
                                    selectedIndex = selectedIdx
                                    dismiss()
                                },
                                index = index,
                            )
                        }
                    }
                }
            }
        }
    }
}
