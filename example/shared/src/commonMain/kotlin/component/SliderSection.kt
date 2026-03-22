// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.RangeSlider
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SliderDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalSlider

fun LazyListScope.sliderSection() {
    item(key = "slider") {
        SmallTitle(text = "Slider")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            var sliderValue by remember { mutableFloatStateOf(0.3f) }
            Text(
                text = "Normal: ${(sliderValue * 100).toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp),
            )
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
            var stepsValue by remember { mutableFloatStateOf(100f) }
            Text(
                text = "Steps: ${stepsValue.toInt()}/200",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            Slider(
                value = stepsValue,
                onValueChange = { stepsValue = it },
                valueRange = 0f..200f,
                steps = 199,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
            var stepsWithKeyPointsValue by remember { mutableFloatStateOf(5f) }
            Text(
                text = "Steps with Key Points: ${stepsWithKeyPointsValue.toInt()}/8",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            Slider(
                value = stepsWithKeyPointsValue,
                onValueChange = { stepsWithKeyPointsValue = it },
                valueRange = 0f..8f,
                steps = 7,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                showKeyPoints = true,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
            var customKeyPointsValue by remember { mutableFloatStateOf(25f) }
            Text(
                text = "Custom Key Points: ${customKeyPointsValue.toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            Slider(
                value = customKeyPointsValue,
                onValueChange = { customKeyPointsValue = it },
                valueRange = 0f..100f,
                showKeyPoints = true,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                keyPoints = listOf(0f, 25f, 50f, 75f, 100f),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
            val disabledValue by remember { mutableFloatStateOf(0.7f) }
            Text(
                text = "Disabled: ${(disabledValue * 100).toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            Slider(
                value = disabledValue,
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
        }

        // RangeSlider
        SmallTitle(text = "RangeSlider")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            var rangeValue by remember { mutableStateOf(0.2f..0.8f) }
            Text(
                text = "Range: ${(rangeValue.start * 100).toInt()}% - ${(rangeValue.endInclusive * 100).toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp),
            )
            RangeSlider(
                value = rangeValue,
                onValueChange = { rangeValue = it },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
            var rangeStepsValue by remember { mutableStateOf(2f..8f) }
            Text(
                text = "Range with Key Points: ${rangeStepsValue.start.toInt()} - ${rangeStepsValue.endInclusive.toInt()}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            RangeSlider(
                value = rangeStepsValue,
                onValueChange = { rangeStepsValue = it },
                valueRange = 0f..8f,
                steps = 7,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                showKeyPoints = true,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
            var customRangeValue by remember { mutableStateOf(20f..80f) }
            Text(
                text = "Custom Range Points: ${customRangeValue.start.toInt()}% - ${customRangeValue.endInclusive.toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            RangeSlider(
                value = customRangeValue,
                onValueChange = { customRangeValue = it },
                valueRange = 0f..100f,
                showKeyPoints = true,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                keyPoints = listOf(0f, 20f, 40f, 60f, 80f, 100f),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
            var disabledRangeValue by remember { mutableStateOf(0.3f..0.7f) }
            Text(
                text = "Disabled: ${(disabledRangeValue.start * 100).toInt()}% - ${(disabledRangeValue.endInclusive * 100).toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            RangeSlider(
                value = disabledRangeValue,
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )
        }

        // VerticalSlider
        SmallTitle(text = "VerticalSlider")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                var verticalValue1 by remember { mutableFloatStateOf(0.3f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f),
                ) {
                    VerticalSlider(
                        value = verticalValue1,
                        onValueChange = { verticalValue1 = it },
                        modifier = Modifier.size(25.dp, 160.dp),
                    )
                    Text(
                        text = "Normal\n${(verticalValue1 * 100).toInt()}%",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                var verticalValue2 by remember { mutableFloatStateOf(5f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f),
                ) {
                    VerticalSlider(
                        value = verticalValue2,
                        onValueChange = { verticalValue2 = it },
                        valueRange = 0f..6f,
                        steps = 5,
                        hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                        modifier = Modifier.size(25.dp, 160.dp),
                    )
                    Text(
                        text = "Steps\n${verticalValue2.toInt()}/6",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                var verticalValue3 by remember { mutableFloatStateOf(5f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f),
                ) {
                    VerticalSlider(
                        value = verticalValue3,
                        onValueChange = { verticalValue3 = it },
                        valueRange = 0f..6f,
                        steps = 5,
                        hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                        showKeyPoints = true,
                        modifier = Modifier.size(25.dp, 160.dp),
                    )
                    Text(
                        text = "Points\n${verticalValue3.toInt()}/6",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                var verticalValue4 by remember { mutableFloatStateOf(50f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f),
                ) {
                    VerticalSlider(
                        value = verticalValue4,
                        onValueChange = { verticalValue4 = it },
                        valueRange = 0f..100f,
                        showKeyPoints = true,
                        hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                        keyPoints = listOf(0f, 25f, 50f, 75f, 100f),
                        modifier = Modifier.size(25.dp, 160.dp),
                    )
                    Text(
                        text = "Custom\n${verticalValue4.toInt()}%",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                val disabledVerticalValue by remember { mutableFloatStateOf(0.7f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f),
                ) {
                    VerticalSlider(
                        value = disabledVerticalValue,
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier.size(25.dp, 160.dp),
                    )
                    Text(
                        text = "Disabled\n${(disabledVerticalValue * 100).toInt()}%",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}
