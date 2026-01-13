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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.CircularProgressIndicator
import top.yukonga.miuix.kmp.basic.ColorPalette
import top.yukonga.miuix.kmp.basic.ColorPicker
import top.yukonga.miuix.kmp.basic.ColorSpace
import top.yukonga.miuix.kmp.basic.InfiniteProgressIndicator
import top.yukonga.miuix.kmp.basic.LinearProgressIndicator
import top.yukonga.miuix.kmp.basic.RangeSlider
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SliderDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SnackbarDuration
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.SnackbarResult
import top.yukonga.miuix.kmp.basic.TabRow
import top.yukonga.miuix.kmp.basic.TabRowWithContour
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.basic.VerticalSlider
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PressFeedbackType
import kotlin.math.round

fun LazyListScope.otherComponent(hostState: SnackbarHostState) {
    item(key = "button") {
        var buttonText by remember { mutableStateOf("Cancel") }
        var submitButtonText by remember { mutableStateOf("Submit") }
        var clickCount by remember { mutableIntStateOf(0) }
        var submitClickCount by remember { mutableIntStateOf(0) }

        SmallTitle(text = "Button")
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextButton(
                text = buttonText,
                onClick = {
                    clickCount++
                    buttonText = "Click: $clickCount"
                },
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(12.dp))
            TextButton(
                text = submitButtonText,
                onClick = {
                    submitClickCount++
                    submitButtonText = "Click: $submitClickCount"
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary(),
            )
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextButton(
                text = "Disabled",
                onClick = {},
                modifier = Modifier.weight(1f),
                enabled = false,
            )
            Spacer(Modifier.width(12.dp))
            TextButton(
                text = "Disabled",
                onClick = {},
                enabled = false,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary(),
            )
        }
    }

    item(key = "snackbar") {
        SmallTitle(text = "Snackbar")
        val scope = rememberCoroutineScope()
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextButton(
                        text = "Short",
                        onClick = {
                            scope.launch {
                                hostState.showSnackbar("This is a short message")
                            }
                        },
                        modifier = Modifier.weight(1f),
                    )
                    TextButton(
                        text = "Long",
                        onClick = {
                            scope.launch {
                                hostState.showSnackbar(
                                    message = "This is a long message to display more text content",
                                    duration = SnackbarDuration.Long,
                                )
                            }
                        },
                        modifier = Modifier.weight(1f),
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextButton(
                        text = "Custom (2s)",
                        onClick = {
                            scope.launch {
                                hostState.showSnackbar(
                                    message = "This message will last for 2 seconds",
                                    duration = SnackbarDuration.Custom(2000L),
                                )
                            }
                        },
                        modifier = Modifier.weight(1f),
                    )
                    var text by remember { mutableStateOf("Action") }
                    TextButton(
                        text = text,
                        onClick = {
                            scope.launch {
                                text = "Action: Alive"
                                val result = hostState.showSnackbar(
                                    message = "This message has an action",
                                    actionLabel = "Undo",
                                    duration = SnackbarDuration.Short,
                                )
                                text = when (result) {
                                    SnackbarResult.ActionPerformed -> "Action: Undo"
                                    else -> "Action: Expired"
                                }
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.textButtonColorsPrimary(),
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextButton(
                        text = "Dismissible",
                        onClick = {
                            scope.launch {
                                hostState.showSnackbar(
                                    message = "This message can be removed via the close button",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Long,
                                )
                            }
                        },
                        modifier = Modifier.weight(1f),
                    )
                    TextButton(
                        text = "Indefinite",
                        onClick = {
                            scope.launch {
                                hostState.showSnackbar(
                                    message = "Indefinite message, dismiss manually",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Indefinite,
                                )
                            }
                        },
                        modifier = Modifier.weight(1f),
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextButton(
                        text = "Dismiss",
                        onClick = {
                            scope.launch {
                                hostState.oldestSnackbarData()?.dismiss()
                            }
                        },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
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
                repeatMode = RepeatMode.Reverse,
            ),
        )

        LinearProgressIndicator(
            progress = animatedProgressValue,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 12.dp),
        )
        progressValues.forEach { progressValue ->
            LinearProgressIndicator(
                progress = progressValue,
                modifier = Modifier
                    .padding(horizontal = 15.dp) // Increased from 12.dp.
                    .padding(bottom = 12.dp),
            )
        }
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CircularProgressIndicator(
                progress = animatedProgressValue,
            )
            progressValues.forEach { progressValue ->
                CircularProgressIndicator(
                    progress = progressValue,
                )
            }
            InfiniteProgressIndicator(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically),
            )
        }
    }

    item(key = "textField") {
        val focusManager = LocalFocusManager.current

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
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
        TextField(
            value = text2,
            onValueChange = { text2 = it },
            label = "With title",
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
                .padding(bottom = 12.dp),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
    }

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
            var stepsValue by remember { mutableFloatStateOf(5f) }
            Text(
                text = "Steps: ${stepsValue.toInt()}/8",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            Slider(
                value = stepsValue,
                onValueChange = { stepsValue = it },
                valueRange = 0f..8f,
                steps = 7,
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

    item(key = "tabRow") {
        SmallTitle(text = "TabRow")
        val tabTexts = remember { listOf("Tab 1", "Tab 2", "Tab 3") }
        val tabTexts1 = remember { listOf("Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5", "Tab 6") }
        var selectedTabIndex by remember { mutableIntStateOf(0) }
        TabRow(
            tabs = tabTexts,
            selectedTabIndex = selectedTabIndex,
            onTabSelected = {
                selectedTabIndex = it
            },
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            insideMargin = PaddingValues(16.dp),
        ) {
            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState(pageCount = { tabTexts1.size })
            TabRowWithContour(
                tabs = tabTexts1,
                selectedTabIndex = pagerState.currentPage,
                onTabSelected = {
                    scope.launch {
                        pagerState.animateScrollToPage(it)
                    }
                },
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                userScrollEnabled = true,
                key = { it },
                pageContent = { page ->
                    Text(
                        text = "Content of ${tabTexts1[page]}",
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                },
            )
        }
    }

    item(key = "colorPicker-HSV") {
        SmallTitle(text = "ColorPicker (HSV)")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }
        var colorHex by remember(selectedColor) {
            mutableStateOf(
                selectedColor.toArgb().toHexString(HexFormat.UpperCase),
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            insideMargin = PaddingValues(16.dp),
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "RGBA: ${(selectedColor.red * 255).toInt()}, " +
                        "${(selectedColor.green * 255).toInt()}, " +
                        "${(selectedColor.blue * 255).toInt()}, " +
                        "${(round(selectedColor.alpha * 100) / 100.0)}",
                    modifier = Modifier.weight(1f),
                )
            }
            ColorPicker(
                color = selectedColor,
                onColorChanged = { selectedColor = it },
                showPreview = false,
            )
            TextField(
                value = colorHex,
                onValueChange = { newHex ->
                    if (newHex.length <= 8 && newHex.all { it.isDigit() || it in 'a'..'f' || it in 'A'..'F' }) {
                        colorHex = newHex.uppercase()
                        if (newHex.length == 8) {
                            val newColor = Color(colorHex.toUInt(16).toInt())
                            selectedColor = newColor
                        }
                    }
                },
                leadingIcon = {
                    Text(
                        "HEX: #",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                },
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }

    item(key = "colorPicker-OKHSV") {
        SmallTitle(text = "ColorPicker (OKHSV)")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }
        var colorHex by remember(selectedColor) {
            mutableStateOf(
                selectedColor.toArgb().toHexString(HexFormat.UpperCase),
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),

            insideMargin = PaddingValues(16.dp),
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "RGBA: ${(selectedColor.red * 255).toInt()}, " +
                        "${(selectedColor.green * 255).toInt()}, " +
                        "${(selectedColor.blue * 255).toInt()}, " +
                        "${(round(selectedColor.alpha * 100) / 100.0)}",
                    modifier = Modifier.weight(1f),
                )
            }
            ColorPicker(
                color = selectedColor,
                onColorChanged = { selectedColor = it },
                colorSpace = ColorSpace.OKHSV,
                showPreview = false,
            )
            TextField(
                value = colorHex,
                onValueChange = { newHex ->
                    if (newHex.length <= 8 && newHex.all { it.isDigit() || it in 'a'..'f' || it in 'A'..'F' }) {
                        colorHex = newHex.uppercase()
                        if (newHex.length == 8) {
                            val newColor = Color(colorHex.toUInt(16).toInt())
                            selectedColor = newColor
                        }
                    }
                },
                leadingIcon = {
                    Text(
                        "HEX: #",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                },
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }

    item(key = "colorPicker-OKLAB") {
        SmallTitle(text = "ColorPicker (OKLAB)")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }
        var colorHex by remember(selectedColor) {
            mutableStateOf(
                selectedColor.toArgb().toHexString(HexFormat.UpperCase),
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            insideMargin = PaddingValues(16.dp),
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "RGBA: ${(selectedColor.red * 255).toInt()}, " +
                        "${(selectedColor.green * 255).toInt()}, " +
                        "${(selectedColor.blue * 255).toInt()}, " +
                        "${(round(selectedColor.alpha * 100) / 100.0)}",
                    modifier = Modifier.weight(1f),
                )
            }
            ColorPicker(
                color = selectedColor,
                onColorChanged = { selectedColor = it },
                colorSpace = ColorSpace.OKLAB,
                showPreview = false,
            )
            TextField(
                value = colorHex,
                onValueChange = { newHex ->
                    if (newHex.length <= 8 && newHex.all { it.isDigit() || it in 'a'..'f' || it in 'A'..'F' }) {
                        colorHex = newHex.uppercase()
                        if (newHex.length == 8) {
                            val newColor = Color(colorHex.toUInt(16).toInt())
                            selectedColor = newColor
                        }
                    }
                },
                leadingIcon = {
                    Text(
                        "HEX: #",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                },
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }

    item(key = "colorPicker-OKLCH") {
        SmallTitle(text = "ColorPicker (OKLCH)")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }
        var colorHex by remember(selectedColor) {
            mutableStateOf(
                selectedColor.toArgb().toHexString(HexFormat.UpperCase),
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),

            insideMargin = PaddingValues(16.dp),
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "RGBA: ${(selectedColor.red * 255).toInt()}, " +
                        "${(selectedColor.green * 255).toInt()}, " +
                        "${(selectedColor.blue * 255).toInt()}, " +
                        "${(round(selectedColor.alpha * 100) / 100.0)}",
                    modifier = Modifier.weight(1f),
                )
            }
            ColorPicker(
                color = selectedColor,
                onColorChanged = { selectedColor = it },
                colorSpace = ColorSpace.OKLCH,
                showPreview = false,
            )
            TextField(
                value = colorHex,
                onValueChange = { newHex ->
                    if (newHex.length <= 8 && newHex.all { it.isDigit() || it in 'a'..'f' || it in 'A'..'F' }) {
                        colorHex = newHex.uppercase()
                        if (newHex.length == 8) {
                            val newColor = Color(colorHex.toUInt(16).toInt())
                            selectedColor = newColor
                        }
                    }
                },
                leadingIcon = {
                    Text(
                        "HEX: #",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                },
                modifier = Modifier.padding(top = 12.dp),
            )
        }
    }

    item(key = "colorPalette") {
        SmallTitle(text = "ColorPalette")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }
        var colorHex by remember(selectedColor) {
            mutableStateOf(
                selectedColor.toArgb().toHexString(HexFormat.UpperCase),
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            insideMargin = PaddingValues(16.dp),
        ) {
            Row(
                modifier = Modifier.padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "RGBA: ${(selectedColor.red * 255).toInt()}, " +
                        "${(selectedColor.green * 255).toInt()}, " +
                        "${(selectedColor.blue * 255).toInt()}, " +
                        "${(round(selectedColor.alpha * 100) / 100.0)}",
                    modifier = Modifier.weight(1f),
                )
            }
            ColorPalette(
                color = selectedColor,
                onColorChanged = { selectedColor = it },
                showPreview = false,
            )
            TextField(
                value = colorHex,
                onValueChange = { newHex ->
                    if (newHex.length <= 8 && newHex.all { it.isDigit() || it in 'a'..'f' || it in 'A'..'F' }) {
                        colorHex = newHex.uppercase()
                        if (newHex.length == 8) {
                            val newColor = Color(colorHex.toUInt(16).toInt())
                            selectedColor = newColor
                        }
                    }
                },
                leadingIcon = {
                    Text(
                        "HEX: #",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp),
                    )
                },
                modifier = Modifier.padding(top = 12.dp),
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
                showIndication = true,
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
