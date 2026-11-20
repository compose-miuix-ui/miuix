// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.maxLengthReject
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TextField

fun LazyListScope.textFieldSection() {
    item(key = "textField") {
        val focusManager = LocalFocusManager.current

        val text1 = rememberTextFieldState(initialText = "")
        val text2 = rememberTextFieldState(initialText = "")
        val text3 = rememberTextFieldState(initialText = "")
        val text4 = rememberTextFieldState(initialText = "")

        SmallTitle(text = "TextField")
        TextField(
            state = text1,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            onKeyboardAction = { focusManager.clearFocus() },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
        TextField(
            state = text2,
            label = "With title",
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            onKeyboardAction = { focusManager.clearFocus() },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
        TextField(
            state = text3,
            label = "State-based (max 20)",
            inputTransformation = InputTransformation.maxLengthReject(20),
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            onKeyboardAction = { focusManager.clearFocus() },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
        TextField(
            state = text4,
            label = "Placeholder & SingleLine",
            useLabelAsPlaceholder = true,
            lineLimits = TextFieldLineLimits.SingleLine,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            onKeyboardAction = { focusManager.clearFocus() },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
    }
}
