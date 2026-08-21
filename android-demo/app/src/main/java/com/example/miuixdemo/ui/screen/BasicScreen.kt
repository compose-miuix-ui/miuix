package com.example.miuixdemo.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Button
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Checkbox
import top.yukonga.miuix.kmp.basic.CircularProgressIndicator
import top.yukonga.miuix.kmp.basic.LinearProgressIndicator
import top.yukonga.miuix.kmp.basic.RadioButton
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * 基础组件演示页：按钮、开关、复选、单选、滑块、输入框、进度条。
 */
@Composable
fun BasicScreen() {
    var buttonClickCount by remember { mutableIntStateOf(0) }
    var switchChecked by remember { mutableStateOf(true) }
    var checkboxState by remember { mutableStateOf(ToggleableState.On) }
    var radioSelected by remember { mutableIntStateOf(0) }
    var sliderValue by remember { mutableFloatStateOf(0.5f) }
    var textInput by remember { mutableStateOf("") }
    var progress by remember { mutableFloatStateOf(0.6f) }

    LazyColumn {
        item {
            SmallTitle("Button")
            Card(Modifier.padding(horizontal = 12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(
                        onClick = { buttonClickCount++ },
                        colors = ButtonDefaults.buttonColorsPrimary(),
                    ) {
                        Text("Primary")
                    }
                    Spacer(Modifier.width(12.dp))
                    Button(
                        onClick = { buttonClickCount++ },
                    ) {
                        Text("Secondary")
                    }
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Click: $buttonClickCount",
                    color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextButton(text = "TextButton", onClick = {})
                    TextButton(
                        text = "Disabled",
                        onClick = {},
                        enabled = false,
                    )
                }
            }
        }

        item {
            SmallTitle("Switch")
            Card(Modifier.padding(horizontal = 12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("Enabled")
                    Switch(checked = switchChecked, onCheckedChange = { switchChecked = it })
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("Disabled")
                    Switch(checked = true, onCheckedChange = null, enabled = false)
                }
            }
        }

        item {
            SmallTitle("Checkbox & RadioButton")
            Card(Modifier.padding(horizontal = 12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("Checkbox")
                    Checkbox(
                        state = checkboxState,
                        onClick = {
                            checkboxState = when (checkboxState) {
                                ToggleableState.On -> ToggleableState.Off
                                ToggleableState.Off -> ToggleableState.Indeterminate
                                ToggleableState.Indeterminate -> ToggleableState.On
                            }
                        },
                    )
                }
                Spacer(Modifier.height(4.dp))
                Column {
                    listOf("Option A", "Option B", "Option C").forEachIndexed { index, option ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(option)
                            RadioButton(
                                selected = radioSelected == index,
                                onClick = { radioSelected = index },
                            )
                        }
                    }
                }
            }
        }

        item {
            SmallTitle("Slider")
            Card(Modifier.padding(horizontal = 12.dp)) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text("Progress")
                        Text(
                            text = "${(sliderValue * 100).toInt()}%",
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                    }
                    Slider(
                        value = sliderValue,
                        onValueChange = { sliderValue = it },
                    )
                }
            }
        }

        item {
            SmallTitle("TextField")
            Card(Modifier.padding(horizontal = 12.dp)) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = textInput,
                    onValueChange = { textInput = it },
                    label = "Enter something",
                )
            }
        }

        item {
            SmallTitle("ProgressIndicator")
            Card(Modifier.padding(horizontal = 12.dp)) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    LinearProgressIndicator(progress = progress)
                    CircularProgressIndicator(progress = progress)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text("Infinite")
                        CircularProgressIndicator(progress = null)
                    }
                }
            }
        }
        item { Spacer(Modifier.height(24.dp)) }
    }
}
