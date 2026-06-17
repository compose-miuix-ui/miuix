// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TextField

fun LazyListScope.textFieldSection() {
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
}
