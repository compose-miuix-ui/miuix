// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.Contacts
import top.yukonga.miuix.kmp.icon.symbol.Edit
import top.yukonga.miuix.kmp.icon.symbol.Rename
import top.yukonga.miuix.kmp.icon.symbol.Settings
import top.yukonga.miuix.kmp.symbol.rememberSymbolPainter
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun IconDemo() {
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
                insideMargin = PaddingValues(16.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                ) {
                    Icon(
                        painter = rememberSymbolPainter(MiuixIcons.Symbol.Contacts),
                        contentDescription = "Personal",
                        tint = MiuixTheme.colorScheme.onBackground,
                    )
                    Icon(
                        painter = rememberSymbolPainter(MiuixIcons.Symbol.Settings),
                        contentDescription = "Settings",
                        tint = MiuixTheme.colorScheme.onBackground,
                    )
                    Icon(
                        painter = rememberSymbolPainter(MiuixIcons.Symbol.Edit),
                        contentDescription = "Blue Edit",
                        tint = Color.Blue,
                    )
                    Icon(
                        painter = rememberSymbolPainter(MiuixIcons.Symbol.Rename),
                        contentDescription = "Green Rename",
                        tint = Color.Green,
                    )
                }
            }
        }
    }
}
