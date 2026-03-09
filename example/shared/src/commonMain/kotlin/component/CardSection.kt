// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType

fun LazyListScope.cardSection() {
    item(key = "card") {
        SmallTitle(text = "Card")
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            colors = CardDefaults.defaultColors(
                color = MiuixTheme.colorScheme.primaryVariant,
            ),
            insideMargin = PaddingValues(16.dp),
            pressFeedbackType = PressFeedbackType.None,
            showIndication = true,
        ) {
            Text(
                color = MiuixTheme.colorScheme.onPrimaryVariant,
                text = "Card",
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                color = MiuixTheme.colorScheme.onPrimaryVariant,
                text = "ShowIndication: true",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Card(
                modifier = Modifier.weight(1f),
                insideMargin = PaddingValues(16.dp),
                pressFeedbackType = PressFeedbackType.Sink,
                onClick = { println("Card click") },
                content = {
                    Text(
                        color = MiuixTheme.colorScheme.onSurface,
                        text = "Card",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                        text = "PressFeedback\nType: Sink",
                        style = MiuixTheme.textStyles.paragraph,
                    )
                },
            )
            Card(
                modifier = Modifier.weight(1f),
                insideMargin = PaddingValues(16.dp),
                pressFeedbackType = PressFeedbackType.Tilt,
                onLongPress = { println("Card long press") },
                content = {
                    Text(
                        color = MiuixTheme.colorScheme.onSurface,
                        text = "Card",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                    )
                    Text(
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                        text = "PressFeedback\nType: Tilt",
                        style = MiuixTheme.textStyles.paragraph,
                    )
                },
            )
        }
    }
}
