// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperDialog
import top.yukonga.miuix.kmp.extra.WindowDialog
import top.yukonga.miuix.kmp.theme.LocalDismissState

fun LazyListScope.dialogSection() {
    item(key = "dialog") {
        val showSuperDialog = remember { mutableStateOf(false) }
        val showWindowDialog = remember { mutableStateOf(false) }
        val superDialogHoldDown = remember { mutableStateOf(false) }
        val windowDialogHoldDown = remember { mutableStateOf(false) }

        SmallTitle(text = "Dialog")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            SuperArrow(
                title = "SuperDialog",
                summary = "Click to show a SuperDialog",
                onClick = {
                    showSuperDialog.value = true
                    superDialogHoldDown.value = true
                },
                holdDownState = superDialogHoldDown.value,
            )
            SuperArrow(
                title = "WindowDialog",
                summary = "Click to show a WindowDialog",
                onClick = {
                    showWindowDialog.value = true
                    windowDialogHoldDown.value = true
                },
                holdDownState = windowDialogHoldDown.value,
            )
        }

        SuperDialogDemo(showSuperDialog, onDismissFinished = { superDialogHoldDown.value = false })
        WindowDialogDemo(showWindowDialog, onDismissFinished = { windowDialogHoldDown.value = false })
    }
}

@Composable
private fun SuperDialogDemo(
    showDialog: MutableState<Boolean>,
    onDismissFinished: () -> Unit,
) {
    SuperDialog(
        show = showDialog.value,
        title = "SuperDialog",
        summary = "A dialog component inside MiuixPopupHost.",
        onDismissRequest = {
            showDialog.value = false
        },
        onDismissFinished = onDismissFinished,
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(
                    text = "Cancel",
                    onClick = {
                        showDialog.value = false
                    },
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(20.dp))
                TextButton(
                    text = "Confirm",
                    onClick = {
                        showDialog.value = false
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.textButtonColorsPrimary(),
                )
            }
        },
    )
}

@Composable
private fun WindowDialogDemo(
    showDialog: MutableState<Boolean>,
    onDismissFinished: () -> Unit,
) {
    WindowDialog(
        show = showDialog.value,
        title = "WindowDialog",
        summary = "A window-level dialog, no MiuixPopupHost required.",
        onDismissRequest = {
            showDialog.value = false
        },
        onDismissFinished = onDismissFinished,
        content = {
            val state = LocalDismissState.current
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(
                    text = "Cancel",
                    onClick = {
                        state?.invoke()
                    },
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(20.dp))
                TextButton(
                    text = "Confirm",
                    onClick = {
                        state?.invoke()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.textButtonColorsPrimary(),
                )
            }
        },
    )
}
