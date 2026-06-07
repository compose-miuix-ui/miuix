// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.RichTooltipBox
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.TooltipBox
import top.yukonga.miuix.kmp.basic.rememberTooltipState
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Copy
import top.yukonga.miuix.kmp.icon.extended.Delete
import top.yukonga.miuix.kmp.icon.extended.Download
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.icon.extended.Favorites
import top.yukonga.miuix.kmp.icon.extended.Share
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun TooltipDemo() {
    val richState = rememberTooltipState(isPersistent = true)
    val scope = rememberCoroutineScope()
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
            Card {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = "Hover (mouse) or long press (touch) an icon to reveal its label.",
                        color = MiuixTheme.colorScheme.onSurfaceContainerVariant,
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        TooltipBox(text = "Edit") {
                            IconButton(onClick = {}) {
                                Icon(imageVector = MiuixIcons.Edit, contentDescription = "Edit")
                            }
                        }
                        TooltipBox(text = "Copy") {
                            IconButton(onClick = {}) {
                                Icon(imageVector = MiuixIcons.Copy, contentDescription = "Copy")
                            }
                        }
                        TooltipBox(text = "Share") {
                            IconButton(onClick = {}) {
                                Icon(imageVector = MiuixIcons.Share, contentDescription = "Share")
                            }
                        }
                        TooltipBox(text = "Download") {
                            IconButton(onClick = {}) {
                                Icon(imageVector = MiuixIcons.Download, contentDescription = "Download")
                            }
                        }
                        TooltipBox(text = "Favorite") {
                            IconButton(onClick = {}) {
                                Icon(imageVector = MiuixIcons.Favorites, contentDescription = "Favorite")
                            }
                        }
                        TooltipBox(text = "Delete") {
                            IconButton(onClick = {}) {
                                Icon(imageVector = MiuixIcons.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                    RichTooltipBox(
                        title = "Rich tooltip",
                        text = "Rich tooltips show a title, supporting text, and an optional action. " +
                            "Tap outside or the action to dismiss.",
                        actionText = "Got it",
                        onActionClick = {},
                        state = richState,
                    ) {
                        TextButton(
                            text = "Show rich tooltip",
                            onClick = { scope.launch { richState.show() } },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.textButtonColorsPrimary(),
                        )
                    }
                }
            }
        }
    }
}
