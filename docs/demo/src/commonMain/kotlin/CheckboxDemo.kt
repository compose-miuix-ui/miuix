// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Checkbox

@Composable
fun CheckboxDemo() {
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
            var checkbox1 by remember { mutableStateOf(ToggleableState.Off) }
            var checkbox2 by remember { mutableStateOf(ToggleableState.On) }
            var checkbox3 by remember { mutableStateOf(ToggleableState.Indeterminate) }
            Row(
                horizontalArrangement = Arrangement.spacedBy(32.dp),
            ) {
                Checkbox(
                    state = checkbox1,
                    onClick = { checkbox1 = if (checkbox1 == ToggleableState.On) ToggleableState.Off else ToggleableState.On },
                )
                Checkbox(
                    state = checkbox2,
                    onClick = { checkbox2 = if (checkbox2 == ToggleableState.On) ToggleableState.Off else ToggleableState.On },
                )
                Checkbox(
                    state = checkbox3,
                    onClick = {
                        checkbox3 = when (checkbox3) {
                            ToggleableState.Off -> ToggleableState.Indeterminate
                            ToggleableState.Indeterminate -> ToggleableState.On
                            ToggleableState.On -> ToggleableState.Off
                        }
                    },
                )
                Checkbox(
                    state = ToggleableState.Off,
                    onClick = null,
                    enabled = false,
                )
                Checkbox(
                    state = ToggleableState.On,
                    onClick = null,
                    enabled = false,
                )
                Checkbox(
                    state = ToggleableState.Indeterminate,
                    onClick = null,
                    enabled = false,
                )
            }
        }
    }
}
