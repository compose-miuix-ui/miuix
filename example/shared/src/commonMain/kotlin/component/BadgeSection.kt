// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Badge
import top.yukonga.miuix.kmp.basic.BadgedBox
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Email
import top.yukonga.miuix.kmp.icon.extended.Favorites
import top.yukonga.miuix.kmp.icon.extended.Messages
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.badgeSection() {
    item(key = "badge") {
        SmallTitle(text = "Badge")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = "A badge overlays dynamic information, such as an unread count, on an anchor.",
                    color = MiuixTheme.colorScheme.onSurfaceContainerVariant,
                )
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
