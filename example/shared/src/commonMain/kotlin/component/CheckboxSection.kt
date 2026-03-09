// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        val checkbox = remember { mutableStateOf(false) }
        val checkboxTrue = remember { mutableStateOf(true) }
        val checkboxIndeterminate = remember { mutableStateOf(ToggleableState.Indeterminate) }
        val superCheckbox = remember { mutableStateOf("State: false") }
        val superCheckboxState = remember { mutableStateOf(false) }
        val superEndCheckbox = remember { mutableStateOf("false") }
        val superEndCheckboxState = remember { mutableStateOf(false) }

        SmallTitle(text = "Checkbox")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Checkbox(
                    state = ToggleableState(checkbox.value),
                    onClick = { checkbox.value = !checkbox.value },
                )
                Checkbox(
                    state = ToggleableState(checkboxTrue.value),
                    onClick = { checkboxTrue.value = !checkboxTrue.value },
                    modifier = Modifier.padding(start = 8.dp),
                )
                Checkbox(
                    state = checkboxIndeterminate.value,
                    onClick = {
                        checkboxIndeterminate.value = when (checkboxIndeterminate.value) {
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
            SuperCheckbox(
                checkboxLocation = CheckboxLocation.End,
                title = "Checkbox",
                checked = superEndCheckboxState.value,
                endActions = {
                    Text(
                        text = superEndCheckbox.value,
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                    )
                },
                onCheckedChange = {
                    superEndCheckboxState.value = it
                    superEndCheckbox.value = "$it"
                },
            )
            SuperCheckbox(
                title = "Checkbox",
                summary = superCheckbox.value,
                checked = superCheckboxState.value,
                onCheckedChange = {
                    superCheckboxState.value = it
                    superCheckbox.value = "State: $it"
                },
            )
            SuperCheckbox(
                title = "Disabled Checkbox",
                checked = true,
                enabled = false,
                onCheckedChange = {},
            )
        }
    }
}
