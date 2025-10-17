// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.CircularProgressIndicator
import top.yukonga.miuix.kmp.basic.ColorPalette
import top.yukonga.miuix.kmp.basic.ColorPicker
import top.yukonga.miuix.kmp.basic.ColorSpace
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.InfiniteProgressIndicator
import top.yukonga.miuix.kmp.basic.LinearProgressIndicator
import top.yukonga.miuix.kmp.basic.RangeSlider
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SliderDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TabRow
import top.yukonga.miuix.kmp.basic.TabRowWithContour
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.basic.VerticalSlider
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.useful.Like
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType
import top.yukonga.miuix.kmp.utils.toHsv
import top.yukonga.miuix.kmp.utils.toOkLab
import kotlin.math.round

fun LazyListScope.otherComponent(
    miuixIcons: List<ImageVector>,
    focusManager: FocusManager,
    padding: PaddingValues
) {
    item(key = "button") {
        var buttonText by remember { mutableStateOf("Cancel") }
        var submitButtonText by remember { mutableStateOf("Submit") }
        var clickCount by remember { mutableStateOf(0) }
        var submitClickCount by remember { mutableStateOf(0) }

        SmallTitle(text = "Button")
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                text = buttonText,
                onClick = {
                    clickCount++
                    buttonText = "Click: $clickCount"
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(12.dp))
            TextButton(
                text = submitButtonText,
                onClick = {
                    submitClickCount++
                    submitButtonText = "Click: $submitClickCount"
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                text = "Disabled",
                onClick = {},
                modifier = Modifier.weight(1f),
                enabled = false
            )
            Spacer(Modifier.width(12.dp))
            TextButton(
                text = "Disabled",
                onClick = {},
                enabled = false,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
    }

    item(key = "progressIndicator") {
        SmallTitle(text = "ProgressIndicator")
        val progressValues = listOf(0.0f, 0.25f, 0.5f, 0.75f, 1.0f, null)
        val animatedProgressValue by rememberInfiniteTransition().animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse
            )
        )

        LinearProgressIndicator(
            progress = animatedProgressValue,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 12.dp)
        )
        progressValues.forEach { progressValue ->
            LinearProgressIndicator(
                progress = progressValue,
                modifier = Modifier
                    .padding(horizontal = 15.dp) // Increased from 12.dp.
                    .padding(bottom = 12.dp)
            )
        }
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CircularProgressIndicator(
                progress = animatedProgressValue
            )
            progressValues.forEach { progressValue ->
                CircularProgressIndicator(
                    progress = progressValue
                )
            }
            InfiniteProgressIndicator(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
            )
        }
    }

    item(key = "textField") {
        var text1 by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf(TextFieldValue("")) }
        val text3 = rememberTextFieldState(initialText = "")
        var text4 by remember { mutableStateOf("") }

        SmallTitle(text = "TextField")
        TextField(
            value = text1,
            onValueChange = { text1 = it },
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        TextField(
            value = text2,
            onValueChange = { text2 = it },
            label = "With title",
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        TextField(
            state = text3,
            label = "State-based",
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            onKeyboardAction = { focusManager.clearFocus() },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
        TextField(
            value = text4,
            onValueChange = { text4 = it },
            label = "Placeholder & SingleLine",
            useLabelAsPlaceholder = true,
            singleLine = true,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
    }

    item(key = "slider") {
        SmallTitle(text = "Slider")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp)
        ) {
            var sliderValue by remember { mutableStateOf(0.3f) }
            Text(
                text = "Normal: ${(sliderValue * 100).toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp)
            )
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
            )
            var stepsValue by remember { mutableStateOf(5f) }
            Text(
                text = "Steps: ${stepsValue.toInt()}/8",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp)
            )
            Slider(
                value = stepsValue,
                onValueChange = { stepsValue = it },
                valueRange = 0f..8f,
                steps = 7,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
            )
            var stepsWithKeyPointsValue by remember { mutableStateOf(5f) }
            Text(
                text = "Steps with Key Points: ${stepsWithKeyPointsValue.toInt()}/8",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp)
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
                    .padding(bottom = 12.dp)
            )
            var customKeyPointsValue by remember { mutableStateOf(25f) }
            Text(
                text = "Custom Key Points: ${customKeyPointsValue.toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp)
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
                    .padding(bottom = 12.dp)
            )
            val disabledValue by remember { mutableStateOf(0.7f) }
            Text(
                text = "Disabled: ${(disabledValue * 100).toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp)
            )
            Slider(
                value = disabledValue,
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
            )
        }

        // RangeSlider
        SmallTitle(text = "RangeSlider")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp)
        ) {
            var rangeValue by remember { mutableStateOf(0.2f..0.8f) }
            Text(
                text = "Range: ${(rangeValue.start * 100).toInt()}% - ${(rangeValue.endInclusive * 100).toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp)
            )
            RangeSlider(
                value = rangeValue,
                onValueChange = { rangeValue = it },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
            )
            var rangeStepsValue by remember { mutableStateOf(2f..8f) }
            Text(
                text = "Range with Key Points: ${rangeStepsValue.start.toInt()} - ${rangeStepsValue.endInclusive.toInt()}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp)
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
                    .padding(bottom = 12.dp)
            )
            var customRangeValue by remember { mutableStateOf(20f..80f) }
            Text(
                text = "Custom Range Points: ${customRangeValue.start.toInt()}% - ${customRangeValue.endInclusive.toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp)
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
                    .padding(bottom = 12.dp)
            )
            var disabledRangeValue by remember { mutableStateOf(0.3f..0.7f) }
            Text(
                text = "Disabled: ${(disabledRangeValue.start * 100).toInt()}% - ${(disabledRangeValue.endInclusive * 100).toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp)
            )
            RangeSlider(
                value = disabledRangeValue,
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp)
            )
        }

        // VerticalSlider
        SmallTitle(text = "VerticalSlider")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var verticalValue1 by remember { mutableStateOf(0.3f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    VerticalSlider(
                        value = verticalValue1,
                        onValueChange = { verticalValue1 = it },
                        modifier = Modifier.size(25.dp, 160.dp)
                    )
                    Text(
                        text = "Normal\n${(verticalValue1 * 100).toInt()}%",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                var verticalValue2 by remember { mutableStateOf(5f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    VerticalSlider(
                        value = verticalValue2,
                        onValueChange = { verticalValue2 = it },
                        valueRange = 0f..8f,
                        steps = 7,
                        hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                        modifier = Modifier.size(25.dp, 160.dp)
                    )
                    Text(
                        text = "Steps\n${verticalValue2.toInt()}/8",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                var verticalValue3 by remember { mutableStateOf(5f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    VerticalSlider(
                        value = verticalValue3,
                        onValueChange = { verticalValue3 = it },
                        valueRange = 0f..8f,
                        steps = 7,
                        hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                        showKeyPoints = true,
                        modifier = Modifier.size(25.dp, 160.dp)
                    )
                    Text(
                        text = "Points\n${verticalValue3.toInt()}/8",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                var verticalValue4 by remember { mutableStateOf(50f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    VerticalSlider(
                        value = verticalValue4,
                        onValueChange = { verticalValue4 = it },
                        valueRange = 0f..100f,
                        showKeyPoints = true,
                        hapticEffect = SliderDefaults.SliderHapticEffect.Step,
                        keyPoints = listOf(0f, 25f, 50f, 75f, 100f),
                        modifier = Modifier.size(25.dp, 160.dp)
                    )
                    Text(
                        text = "Custom\n${verticalValue4.toInt()}%",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                val disabledVerticalValue by remember { mutableStateOf(0.7f) }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    VerticalSlider(
                        value = disabledVerticalValue,
                        onValueChange = {},
                        enabled = false,
                        modifier = Modifier.size(25.dp, 160.dp)
                    )
                    Text(
                        text = "Disabled\n${(disabledVerticalValue * 100).toInt()}%",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

    item(key = "tabRow") {
        SmallTitle(text = "TabRow")
        val tabTexts = remember { listOf("Tab 1", "Tab 2", "Tab 3") }
        val tabTexts1 = remember { listOf("Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5", "Tab 6") }
        var selectedTabIndex by remember { mutableStateOf(0) }
        var selectedTabIndex1 by remember { mutableStateOf(0) }
        TabRow(
            tabs = tabTexts,
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp)
        ) {
            selectedTabIndex = it
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),
            insideMargin = PaddingValues(16.dp)
        ) {
            TabRowWithContour(
                tabs = tabTexts1,
                selectedTabIndex = selectedTabIndex1,
            ) {
                selectedTabIndex1 = it
            }
            val selectedTabText by remember(selectedTabIndex1) {
                derivedStateOf { tabTexts1[selectedTabIndex1] }
            }
            Text(
                text = "Selected Tab: $selectedTabText",
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }

    item(key = "icon") {
        SmallTitle(text = "Icon")
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),
            insideMargin = PaddingValues(16.dp)
        ) {
            FlowRow {
                miuixIcons.forEach { icon ->
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (icon != MiuixIcons.Useful.Like) MiuixTheme.colorScheme.onBackground else Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }

    item(key = "colorPicker-hsv") {
        SmallTitle(text = "ColorPicker (HSV)")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),
            insideMargin = PaddingValues(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val hsv = selectedColor.toHsv()
                Text(
                    text = "HEX: #${selectedColor.toArgb().toHexString(HexFormat.UpperCase)}" +
                            "\nRGBA: ${(selectedColor.red * 255).toInt()}, " +
                            "${(selectedColor.green * 255).toInt()}, " +
                            "${(selectedColor.blue * 255).toInt()}, " +
                            "${(round(selectedColor.alpha * 100) / 100.0)}" +
                            "\nHSVA: ${(hsv.h).toInt()}, " +
                            "${(hsv.s).toInt()}%, " +
                            "${(hsv.v).toInt()}%, " +
                            "${(round(selectedColor.alpha * 100) / 100.0)}",
                    modifier = Modifier.weight(1f)
                )
            }
            ColorPicker(
                initialColor = selectedColor,
                onColorChanged = { selectedColor = it },
                showPreview = false
            )
        }
    }

    item(key = "colorPicker-okHsv") {
        SmallTitle(text = "ColorPicker (OKHSV)")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),

            insideMargin = PaddingValues(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val hsv = selectedColor.toHsv()
                Text(
                    text = "HEX: #${selectedColor.toArgb().toHexString(HexFormat.UpperCase)}" +
                            "\nRGBA: ${(selectedColor.red * 255).toInt()}, " +
                            "${(selectedColor.green * 255).toInt()}, " +
                            "${(selectedColor.blue * 255).toInt()}, " +
                            "${(round(selectedColor.alpha * 100) / 100.0)}" +
                            "\nHSVA: ${(hsv.h).toInt()}, " +
                            "${(hsv.s).toInt()}%, " +
                            "${(hsv.v).toInt()}%, " +
                            "${(round(selectedColor.alpha * 100) / 100.0)}",
                    modifier = Modifier.weight(1f)
                )
            }
            ColorPicker(
                initialColor = selectedColor,
                onColorChanged = { selectedColor = it },
                colorSpace = ColorSpace.OKHSV,
                showPreview = false
            )
        }
    }

    item(key = "colorPicker-okLab") {
        SmallTitle(text = "ColorPicker (OKLAB)")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),
            insideMargin = PaddingValues(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val ok = selectedColor.toOkLab()
                Text(
                    text = "HEX: #${selectedColor.toArgb().toHexString(HexFormat.UpperCase)}" +
                            "\nRGBA: ${(selectedColor.red * 255).toInt()}, " +
                            "${(selectedColor.green * 255).toInt()}, " +
                            "${(selectedColor.blue * 255).toInt()}, " +
                            "${(round(selectedColor.alpha * 100) / 100.0)}" +
                            "\nOkLab: ${((ok.l * 1000).toInt() / 1000.0)}, " +
                            "${((ok.a * 1000).toInt() / 1000.0)}, " +
                            "${((ok.b * 1000).toInt() / 1000.0)} / " +
                            "${(round(selectedColor.alpha * 100) / 100.0)}",
                    modifier = Modifier.weight(1f)
                )
            }
            ColorPicker(
                initialColor = selectedColor,
                onColorChanged = { selectedColor = it },
                colorSpace = ColorSpace.OKLAB,
                showPreview = false
            )
        }
    }

    item(key = "colorPalette") {
        SmallTitle(text = "ColorPalette")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 6.dp),
            insideMargin = PaddingValues(16.dp)
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val hsv = selectedColor.toHsv()
                Text(
                    text = "HEX: #${selectedColor.toArgb().toHexString(HexFormat.UpperCase)}" +
                            "\nRGBA: ${(selectedColor.red * 255).toInt()}, " +
                            "${(selectedColor.green * 255).toInt()}, " +
                            "${(selectedColor.blue * 255).toInt()}, " +
                            "${(round(selectedColor.alpha * 100) / 100.0)}" +
                            "\nHSVA: ${(hsv.h).toInt()}, " +
                            "${(hsv.s).toInt()}%, " +
                            "${(hsv.v).toInt()}%, " +
                            "${(round(selectedColor.alpha * 100) / 100.0)}",
                    modifier = Modifier.weight(1f)
                )
            }
            ColorPalette(
                initialColor = selectedColor,
                onColorChanged = { selectedColor = it },
                showPreview = false
            )
        }
    }

    item(key = "card") {
        SmallTitle(text = "Card")
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            colors = CardDefaults.defaultColors(
                color = MiuixTheme.colorScheme.primaryVariant
            ),
            insideMargin = PaddingValues(16.dp),
            pressFeedbackType = PressFeedbackType.None,
            showIndication = true
        ) {
            Text(
                color = MiuixTheme.colorScheme.onPrimary,
                text = "Card",
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                color = MiuixTheme.colorScheme.onPrimaryVariant,
                text = "ShowIndication: true",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp + padding.calculateBottomPadding()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier.weight(1f),
                insideMargin = PaddingValues(16.dp),
                pressFeedbackType = PressFeedbackType.Sink,
                showIndication = true,
                onClick = { println("Card click") },
                content = {
                    Text(
                        color = MiuixTheme.colorScheme.onSurface,
                        text = "Card",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                        text = "PressFeedback\nType: Sink",
                        style = MiuixTheme.textStyles.paragraph
                    )
                }
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
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                        text = "PressFeedback\nType: Tilt",
                        style = MiuixTheme.textStyles.paragraph
                    )
                }
            )
        }
    }
}