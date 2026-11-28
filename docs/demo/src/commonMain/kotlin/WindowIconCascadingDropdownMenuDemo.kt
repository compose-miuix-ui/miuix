// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.MoreCircle
import top.yukonga.miuix.kmp.icon.symbol.Tune
import top.yukonga.miuix.kmp.menu.WindowIconCascadingDropdownMenu
import top.yukonga.miuix.kmp.symbol.rememberSymbolPainter
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun WindowIconCascadingDropdownMenuDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(demoBackground()),
        contentAlignment = Alignment.Center,
    ) {
        var sortIndex by remember { mutableIntStateOf(0) }
        var viewIndex by remember { mutableIntStateOf(0) }
        var filterIndex by remember { mutableIntStateOf(0) }
        val sortLabels = listOf("Sort by capture date", "Sort by date added")
        val viewLabels = listOf("Group by date", "Compact")
        val filterLabels = listOf("All items", "Camera album")

        val entries = remember(sortIndex, viewIndex, filterIndex) {
            listOf(
                DropdownEntry(
                    items = sortLabels.mapIndexed { idx, label ->
                        DropdownItem(
                            text = label,
                            selected = sortIndex == idx,
                            onClick = { sortIndex = idx },
                        )
                    },
                ),
                DropdownEntry(
                    items = listOf(
                        DropdownItem(
                            text = "View mode",
                            children = viewLabels.mapIndexed { idx, label ->
                                DropdownItem(
                                    text = label,
                                    selected = viewIndex == idx,
                                    onClick = { viewIndex = idx },
                                )
                            },
                        ),
                        DropdownItem(
                            text = "Filter",
                            children = filterLabels.mapIndexed { idx, label ->
                                DropdownItem(
                                    text = label,
                                    selected = filterIndex == idx,
                                    onClick = { filterIndex = idx },
                                )
                            },
                        ),
                    ),
                ),
            )
        }
        val emptyEntry = DropdownEntry(items = listOf(DropdownItem(text = "Option 1")))

        Card(modifier = Modifier.padding(16.dp).widthIn(max = 600.dp).fillMaxWidth()) {
            Scaffold(
                topBar = {
                    SmallTopAppBar(
                        title = "Library",
                        actions = {
                            WindowIconCascadingDropdownMenu(entries = entries) {
                                Icon(
                                    painter = rememberSymbolPainter(MiuixIcons.Symbol.Tune),
                                    tint = MiuixTheme.colorScheme.onBackground,
                                    contentDescription = "Adjust",
                                )
                            }
                            WindowIconCascadingDropdownMenu(
                                entry = emptyEntry,
                                enabled = false,
                            ) {
                                Icon(
                                    painter = rememberSymbolPainter(MiuixIcons.Symbol.MoreCircle),
                                    tint = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                                    contentDescription = "More",
                                )
                            }
                        },
                    )
                },
            ) { padding ->
                LazyColumn(
                    contentPadding = PaddingValues(top = padding.calculateTopPadding()),
                ) {
                    item {
                        Spacer(Modifier.height(8.dp))
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(text = "Sort by: ${sortLabels[sortIndex]}")
                            Text(text = "View mode: ${viewLabels[viewIndex]}")
                            Text(text = "Filter: ${filterLabels[filterIndex]}")
                        }
                    }
                }
            }
        }
    }
}
