// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.basicComponentSection() {
    item(key = "basicComponent") {
        SmallTitle(text = "Basic Component")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            BasicComponent(
                title = "Title",
                summary = "Summary",
                startAction = {
                    Text(
                        text = "Start",
                    )
                },
                endActions = {
                    Text(
                        text = "End1",
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "End2",
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                    )
                },
                enabled = true,
            )
            BasicComponent(
                title = "Title",
                summary = "Summary",
                startAction = {
                    Text(
                        text = "Start",
                        color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                    )
                },
                endActions = {
                    Text(
                        text = "End1",
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "End2",
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                    )
                },
                enabled = false,
            )
        }
    }
}
