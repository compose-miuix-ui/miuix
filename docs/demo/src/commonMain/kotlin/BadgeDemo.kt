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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Badge
import top.yukonga.miuix.kmp.basic.BadgedBox
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Email
import top.yukonga.miuix.kmp.icon.extended.Favorites
import top.yukonga.miuix.kmp.icon.extended.Messages
import top.yukonga.miuix.kmp.icon.extended.Settings

@Composable
fun BadgeDemo() {
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
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        BadgedBox(badge = { Badge() }) {
                            Icon(
                                imageVector = MiuixIcons.Messages,
                                contentDescription = "Messages",
                                modifier = Modifier.size(28.dp),
                            )
                        }
                        BadgedBox(badge = { Badge { Text("8") } }) {
                            Icon(
                                imageVector = MiuixIcons.Email,
                                contentDescription = "Email",
                                modifier = Modifier.size(28.dp),
                            )
                        }
                        BadgedBox(badge = { Badge { Text("99+") } }) {
                            Icon(
                                imageVector = MiuixIcons.Settings,
                                contentDescription = "Settings",
                                modifier = Modifier.size(28.dp),
                            )
                        }
                        BadgedBox(badge = { Badge { Text("5") } }) {
                            Icon(
                                imageVector = MiuixIcons.Favorites,
                                contentDescription = "Favorites",
                                modifier = Modifier.size(28.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
