// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import top.yukonga.miuix.kmp.basic.SliderDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalSlider
import top.yukonga.miuix.kmp.preference.RangeSliderPreference
import top.yukonga.miuix.kmp.preference.SliderPreference

fun LazyListScope.sliderSection() {
    item(key = "slider") {
        SmallTitle(text = "Slider")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            var sliderValue by remember { mutableFloatStateOf(0.3f) }
            SliderPreference(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                title = "Normal",
                valueText = "${(sliderValue * 100).toInt()}%",
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            var stepsValue by remember { mutableFloatStateOf(100f) }
            SliderPreference(
                value = stepsValue,
                onValueChange = { stepsValue = it },
                title = "Steps",
                valueText = "${stepsValue.toInt()}/200",
                valueRange = 0f..200f,
                steps = 199,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            var stepsWithKeyPointsValue by remember { mutableFloatStateOf(5f) }
            SliderPreference(
                value = stepsWithKeyPointsValue,
                onValueChange = { stepsWithKeyPointsValue = it },
                title = "Steps with Key Points",
                valueText = "${stepsWithKeyPointsValue.toInt()}/8",
                valueRange = 0f..8f,
                steps = 7,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                showKeyPoints = true,
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            var customKeyPointsValue by remember { mutableFloatStateOf(25f) }
            SliderPreference(
                value = customKeyPointsValue,
                onValueChange = { customKeyPointsValue = it },
                title = "Custom Key Points",
                valueText = "${customKeyPointsValue.toInt()}%",
                valueRange = 0f..100f,
                showKeyPoints = true,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                keyPoints = listOf(0f, 25f, 50f, 75f, 100f),
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            val disabledValue by remember { mutableFloatStateOf(0.7f) }
            SliderPreference(
                value = disabledValue,
                onValueChange = {},
                title = "Disabled",
                valueText = "${(disabledValue * 100).toInt()}%",
                enabled = false,
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
            RangeSliderPreference(
                value = rangeValue,
                onValueChange = { rangeValue = it },
                title = "Range",
                valueText = "${(rangeValue.start * 100).toInt()}% - ${(rangeValue.endInclusive * 100).toInt()}%",
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            var rangeStepsValue by remember { mutableStateOf(2f..8f) }
            RangeSliderPreference(
                value = rangeStepsValue,
                onValueChange = { rangeStepsValue = it },
                title = "Range with Key Points",
                valueText = "${rangeStepsValue.start.toInt()} - ${rangeStepsValue.endInclusive.toInt()}",
                valueRange = 0f..8f,
                steps = 7,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                showKeyPoints = true,
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            var customRangeValue by remember { mutableStateOf(20f..80f) }
            RangeSliderPreference(
                value = customRangeValue,
                onValueChange = { customRangeValue = it },
                title = "Custom Range Points",
                valueText = "${customRangeValue.start.toInt()}% - ${customRangeValue.endInclusive.toInt()}%",
                valueRange = 0f..100f,
                showKeyPoints = true,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                keyPoints = listOf(0f, 20f, 40f, 60f, 80f, 100f),
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            var disabledRangeValue by remember { mutableStateOf(0.3f..0.7f) }
            RangeSliderPreference(
                value = disabledRangeValue,
                onValueChange = {},
                title = "Disabled",
                valueText = "${(disabledRangeValue.start * 100).toInt()}% - ${(disabledRangeValue.endInclusive * 100).toInt()}%",
                enabled = false,
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
