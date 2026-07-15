// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.preference.RadioButtonPreference

fun LazyListScope.radioButtonSection() {
    item(key = "radioButton") {
        SmallTitle(text = "RadioButton")
        RadioButtonCardsDemo()
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            RadioButtonPreference(
                title = "Disabled RadioButton",
                summary = "This option is unavailable",
                selected = true,
                enabled = false,
                onClick = {},
            )
        }
    }
}

@Composable
private fun RadioButtonCardsDemo() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    listOf("Option A", "Option B", "Option C").forEachIndexed { index, title ->
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            RadioButtonPreference(
                title = title,
                summary = "Selected: ${selectedIndex == index}",
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
            )
        }
    }
}
