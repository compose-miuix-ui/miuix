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
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            SuperRadioButtonDemo()
            RadioButtonPreference(
                title = "Disabled RadioButton",
                selected = true,
                enabled = false,
                onClick = {},
            )
        }
    }
}

@Composable
private fun SuperRadioButtonDemo() {
    var selectedIndex by remember { mutableIntStateOf(0) }

    RadioButtonPreference(
        title = "Option A",
        summary = "Selected: ${selectedIndex == 0}",
        selected = selectedIndex == 0,
        onClick = { selectedIndex = 0 },
    )
    RadioButtonPreference(
        title = "Option B",
        summary = "Selected: ${selectedIndex == 1}",
        selected = selectedIndex == 1,
        onClick = { selectedIndex = 1 },
    )
    RadioButtonPreference(
        title = "Option C",
        summary = "Selected: ${selectedIndex == 2}",
        selected = selectedIndex == 2,
        onClick = { selectedIndex = 2 },
    )
}
