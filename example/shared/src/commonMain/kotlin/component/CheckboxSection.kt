// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Checkbox
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.extra.CheckboxLocation
import top.yukonga.miuix.kmp.extra.SuperCheckbox
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.checkboxSection() {
    item(key = "checkbox") {
        SmallTitle(text = "Checkbox")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            CheckboxRow()
            SuperEndCheckboxDemo()
            SuperCheckboxDemo()
            SuperCheckbox(
                title = "Disabled Checkbox",
                checked = true,
                enabled = false,
                onCheckedChange = {},
            )
        }
    }
}

@Composable
private fun CheckboxRow() {
    var checkbox by remember { mutableStateOf(false) }
    var checkboxTrue by remember { mutableStateOf(true) }
    var checkboxIndeterminate by remember { mutableStateOf(ToggleableState.Indeterminate) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Checkbox(
            state = ToggleableState(checkbox),
            onClick = { checkbox = !checkbox },
        )
        Checkbox(
            state = ToggleableState(checkboxTrue),
            onClick = { checkboxTrue = !checkboxTrue },
            modifier = Modifier.padding(start = 8.dp),
        )
        Checkbox(
            state = checkboxIndeterminate,
            onClick = {
                checkboxIndeterminate = when (checkboxIndeterminate) {
                    ToggleableState.Off -> ToggleableState.Indeterminate
                    ToggleableState.Indeterminate -> ToggleableState.On
                    ToggleableState.On -> ToggleableState.Off
                }
            },
            modifier = Modifier.padding(start = 8.dp),
        )
        Checkbox(
            state = ToggleableState.Off,
            onClick = null,
            modifier = Modifier.padding(start = 8.dp),
            enabled = false,
        )
        Checkbox(
            state = ToggleableState.On,
            onClick = null,
            modifier = Modifier.padding(start = 8.dp),
            enabled = false,
        )
        Checkbox(
            state = ToggleableState.Indeterminate,
            onClick = null,
            modifier = Modifier.padding(start = 8.dp),
            enabled = false,
        )
    }
}

@Composable
private fun SuperEndCheckboxDemo() {
    var checked by remember { mutableStateOf(false) }

    SuperCheckbox(
        checkboxLocation = CheckboxLocation.End,
        title = "Checkbox",
        checked = checked,
        endActions = {
            Text(
                text = "$checked",
                fontSize = MiuixTheme.textStyles.body2.fontSize,
                color = MiuixTheme.colorScheme.onSurfaceVariantActions,
            )
        },
        onCheckedChange = { checked = it },
    )
}

@Composable
private fun SuperCheckboxDemo() {
    var checked by remember { mutableStateOf(false) }

    SuperCheckbox(
        title = "Checkbox",
        summary = "State: $checked",
        checked = checked,
        onCheckedChange = { checked = it },
    )
}
