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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SnackbarDuration
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.overlay.OverlayBottomSheet
import top.yukonga.miuix.kmp.overlay.OverlayDialog
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * 弹层与反馈演示页：Dialog、BottomSheet、Snackbar。
 */
@Composable
fun DialogScreen() {
    val snackbarHostState = LocalSnackbarHostState.current
    val scope = rememberCoroutineScope()
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var bottomSheetSwitch by remember { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableIntStateOf(0) }
    val options = listOf("Standard", "Silent", "Vibrate")

    LazyColumn {
        item {
            SmallTitle("Dialog")
            Card(Modifier.padding(horizontal = 12.dp)) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextButton(
                        text = "Show Dialog",
                        onClick = { showDialog = true },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    TextButton(
                        text = "Show Snackbar",
                        onClick = {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "This is a Miuix Snackbar.",
                                    duration = SnackbarDuration.Long,
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }

        item {
            SmallTitle("BottomSheet")
            Card(Modifier.padding(horizontal = 12.dp)) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextButton(
                        text = "Show BottomSheet",
                        onClick = { showBottomSheet = true },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }

        item {
            SmallTitle("Dropdown")
            Card(Modifier.padding(horizontal = 12.dp)) {
                options.forEachIndexed { index, option ->
                    TextButton(
                        text = if (selectedOption == index) "$option ✓" else option,
                        onClick = {
                            selectedOption = index
                            scope.launch {
                                snackbarHostState.showSnackbar("Selected: $option")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = if (selectedOption == index) {
                            ButtonDefaults.textButtonColorsPrimary()
                        } else {
                            ButtonDefaults.textButtonColors()
                        },
                    )
                }
            }
        }
        item { Spacer(Modifier.height(24.dp)) }
    }

    OverlayDialog(
        show = showDialog,
        title = "Miuix Dialog",
        summary = "A dialog with Miuix style. Tap the buttons below to interact.",
        onDismissRequest = { showDialog = false },
        content = {
            Column {
                Text(
                    text = "Content of the dialog.",
                    color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TextButton(
                        text = "Cancel",
                        onClick = { showDialog = false },
                        modifier = Modifier.weight(1f),
                    )
                    Spacer(Modifier.width(20.dp))
                    TextButton(
                        text = "Confirm",
                        onClick = {
                            showDialog = false
                            scope.launch { snackbarHostState.showSnackbar("Confirmed!") }
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.textButtonColorsPrimary(),
                    )
                }
            }
        },
    )

    OverlayBottomSheet(
        show = showBottomSheet,
        title = "Options",
        onDismissRequest = { showBottomSheet = false },
        content = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text("Enable extra feature")
                    Switch(
                        checked = bottomSheetSwitch,
                        onCheckedChange = { bottomSheetSwitch = it },
                    )
                }
                Spacer(Modifier.height(16.dp))
                TextButton(
                    text = "Close",
                    onClick = { showBottomSheet = false },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
    )
}