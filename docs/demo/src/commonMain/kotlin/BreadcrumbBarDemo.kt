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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BreadcrumbBar
import top.yukonga.miuix.kmp.basic.BreadcrumbItem
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.joinToPath
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun BreadcrumbBarDemo() {
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
            val items = remember {
                listOf(
                    BreadcrumbItem(path = "/storage/emulated/0", text = "Internal storage"),
                    BreadcrumbItem(path = "DataBackup"),
                    BreadcrumbItem(path = "apps"),
                    BreadcrumbItem(path = "com.tencent.mobileqq"),
                    BreadcrumbItem(path = "user_0"),
                )
            }
            var highlightIndex by remember { mutableIntStateOf(items.size - 1) }

            Card {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    BreadcrumbBar(
                        items = items,
                        onItemClick = { index -> highlightIndex = index },
                        highlightIndex = highlightIndex,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Text(
                        text = "Full path: ${items.joinToPath()}",
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                    )
                    Text(
                        text = "Current: ${items.subList(0, highlightIndex + 1).joinToPath()}",
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                    )
                    BreadcrumbBar(
                        items = items,
                        onItemClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        enabled = false,
                    )
                }
            }
        }
    }
}
