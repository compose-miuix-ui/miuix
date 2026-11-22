// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLengthReject
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.ColorPalette
import top.yukonga.miuix.kmp.basic.ColorPicker
import top.yukonga.miuix.kmp.basic.ColorSpace
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.round

fun LazyListScope.colorPickerSection(colorSpace: ColorSpace) {
    item(key = "colorPicker-$colorSpace") {
        SmallTitle(text = "ColorPicker ($colorSpace)")
        ColorPickerCard(colorSpace = colorSpace)
    }
}

fun LazyListScope.colorPaletteSection() {
    item(key = "colorPalette") {
        SmallTitle(text = "ColorPalette")
        val miuixColor = MiuixTheme.colorScheme.primary
        var selectedColor by remember { mutableStateOf(miuixColor) }

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
            ColorHexTextField(selectedColor) { newColor ->
                selectedColor = newColor
            }
        }
    }
}

@Composable
private fun ColorPickerCard(colorSpace: ColorSpace) {
    val miuixColor = MiuixTheme.colorScheme.primary
    var selectedColor by remember { mutableStateOf(miuixColor) }

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
            colorSpace = colorSpace,
            showPreview = false,
        )
        ColorHexTextField(selectedColor) { newColor ->
            selectedColor = newColor
        }
    }
}

@Composable
private fun ColorHexTextField(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
) {
    val currentOnColorSelected by rememberUpdatedState(onColorSelected)
    val expectedHex = selectedColor.toArgb().toHexString(HexFormat.UpperCase)
    val currentExpectedHex by rememberUpdatedState(expectedHex)
    val hexState = rememberTextFieldState(initialText = expectedHex)
    LaunchedEffect(expectedHex) {
        if (hexState.text.toString() != expectedHex) {
            hexState.setTextAndPlaceCursorAtEnd(expectedHex)
        }
    }
    LaunchedEffect(hexState) {
        snapshotFlow { hexState.text.toString() }.collect { currentHex ->
            if (currentHex.length == 8 && currentHex != currentExpectedHex) {
                runCatching { Color(currentHex.toUInt(16).toInt()) }
                    .getOrNull()
                    ?.let(currentOnColorSelected)
            }
        }
    }
    TextField(
        state = hexState,
        lineLimits = TextFieldLineLimits.SingleLine,
        inputTransformation = InputTransformation {
            val filtered = asCharSequence()
                .filter { it.isDigit() || it in 'a'..'f' || it in 'A'..'F' }
                .toString()
                .uppercase()
            if (asCharSequence().toString() != filtered) {
                replace(0, length, filtered)
            }
        }.maxLengthReject(8),
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
