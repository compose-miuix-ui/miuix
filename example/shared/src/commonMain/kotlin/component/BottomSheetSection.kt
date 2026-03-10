// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperBottomSheet
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.extra.WindowBottomSheet
import top.yukonga.miuix.kmp.extra.WindowDropdown
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Close
import top.yukonga.miuix.kmp.icon.extended.Ok
import top.yukonga.miuix.kmp.theme.LocalDismissState
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

private val BottomSheetDropdownOptions = listOf("Option 1", "Option 2")

fun LazyListScope.bottomSheetSection() {
    item(key = "bottomSheet") {
        val showSuperBottomSheet = remember { mutableStateOf(false) }
        val showWindowBottomSheet = remember { mutableStateOf(false) }
        val superBottomSheetHoldDown = remember { mutableStateOf(false) }
        val windowBottomSheetHoldDown = remember { mutableStateOf(false) }
        val bottomSheetDropdownSelectedOption = remember { mutableIntStateOf(0) }
        val bottomSheetSuperSwitchState = remember { mutableStateOf(true) }

        SmallTitle(text = "BottomSheet")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            SuperArrow(
                title = "SuperBottomSheet",
                summary = "Click to show a SuperBottomSheet",
                onClick = {
                    showSuperBottomSheet.value = true
                    superBottomSheetHoldDown.value = true
                },
                holdDownState = superBottomSheetHoldDown.value,
            )
            SuperArrow(
                title = "WindowBottomSheet",
                summary = "Click to show a WindowBottomSheet",
                onClick = {
                    showWindowBottomSheet.value = true
                    windowBottomSheetHoldDown.value = true
                },
                holdDownState = windowBottomSheetHoldDown.value,
            )
        }

        SuperBottomSheetDemo(
            showSuperBottomSheet,
            bottomSheetDropdownSelectedOption,
            bottomSheetSuperSwitchState,
            onDismissFinished = { superBottomSheetHoldDown.value = false },
        )
        WindowBottomSheetDemo(
            showWindowBottomSheet,
            bottomSheetDropdownSelectedOption,
            bottomSheetSuperSwitchState,
            onDismissFinished = { windowBottomSheetHoldDown.value = false },
        )
    }
}

@Composable
private fun SuperBottomSheetDemo(
    showBottomSheet: MutableState<Boolean>,
    bottomSheetDropdownSelectedOption: MutableState<Int>,
    bottomSheetSuperSwitchState: MutableState<Boolean>,
    onDismissFinished: () -> Unit,
) {
    val allowDismiss = remember { mutableStateOf(true) }
    val enableNestedScroll = remember { mutableStateOf(true) }

    SuperBottomSheet(
        title = "SuperBottomSheet",
        show = showBottomSheet.value,
        allowDismiss = allowDismiss.value,
        enableNestedScroll = enableNestedScroll.value,
        onDismissRequest = {
            showBottomSheet.value = false
        },
        onDismissFinished = onDismissFinished,
        startAction = {
            IconButton(
                onClick = { showBottomSheet.value = false },
            ) {
                Icon(
                    imageVector = MiuixIcons.Close,
                    contentDescription = "Cancel",
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            }
        },
        endAction = {
            IconButton(
                onClick = { showBottomSheet.value = false },
            ) {
                Icon(
                    imageVector = MiuixIcons.Ok,
                    contentDescription = "Confirm",
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .scrollEndHaptic()
                .overScrollVertical(),
        ) {
            item {
                SmallTitle(text = "Behavior Settings", insideMargin = PaddingValues(16.dp, 8.dp))
                Card(
                    modifier = Modifier.padding(bottom = 12.dp),
                    colors = CardDefaults.defaultColors(
                        color = MiuixTheme.colorScheme.secondaryContainer,
                    ),
                ) {
                    SuperSwitch(
                        title = "Allow Dismiss",
                        summary = "Drag or Back to dismiss",
                        checked = allowDismiss.value,
                        onCheckedChange = { allowDismiss.value = it },
                    )
                    SuperSwitch(
                        title = "Enable NestedScroll",
                        summary = "Scroll content vs Drag sheet",
                        checked = enableNestedScroll.value,
                        onCheckedChange = { enableNestedScroll.value = it },
                    )
                }
            }
            item {
                var sliderValue by remember { mutableFloatStateOf(0.5f) }
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    modifier = Modifier.padding(bottom = 12.dp),
                )
                var textFieldValue by remember { mutableStateOf("") }
                TextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = "TextField",
                    modifier = Modifier.padding(bottom = 12.dp),
                )
                Card(
                    modifier = Modifier.padding(bottom = 12.dp),
                    colors = CardDefaults.defaultColors(
                        color = MiuixTheme.colorScheme.secondaryContainer,
                    ),
                ) {
                    SuperDropdown(
                        title = "SuperDropdown",
                        items = BottomSheetDropdownOptions,
                        selectedIndex = bottomSheetDropdownSelectedOption.value,
                        onSelectedIndexChange = { newOption -> bottomSheetDropdownSelectedOption.value = newOption },
                    )
                    SuperSwitch(
                        title = "SuperSwitch",
                        checked = bottomSheetSuperSwitchState.value,
                        onCheckedChange = {
                            bottomSheetSuperSwitchState.value = it
                        },
                    )
                }
                Spacer(
                    Modifier.padding(
                        bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() +
                            WindowInsets.captionBar.asPaddingValues().calculateBottomPadding(),
                    ),
                )
            }
        }
    }
}

@Composable
private fun WindowBottomSheetDemo(
    showBottomSheet: MutableState<Boolean>,
    bottomSheetDropdownSelectedOption: MutableState<Int>,
    bottomSheetSuperSwitchState: MutableState<Boolean>,
    onDismissFinished: () -> Unit,
) {
    val allowDismiss = remember { mutableStateOf(true) }
    val enableNestedScroll = remember { mutableStateOf(true) }

    var state: (() -> Unit)? = null
    WindowBottomSheet(
        title = "WindowBottomSheet",
        show = showBottomSheet.value,
        allowDismiss = allowDismiss.value,
        enableNestedScroll = enableNestedScroll.value,
        onDismissRequest = {
            showBottomSheet.value = false
        },
        onDismissFinished = onDismissFinished,
        startAction = {
            IconButton(
                onClick = { state?.invoke() },
            ) {
                Icon(
                    imageVector = MiuixIcons.Close,
                    contentDescription = "Cancel",
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            }
        },
        endAction = {
            IconButton(
                onClick = { state?.invoke() },
            ) {
                Icon(
                    imageVector = MiuixIcons.Ok,
                    contentDescription = "Confirm",
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            }
        },
    ) {
        state = LocalDismissState.current
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .scrollEndHaptic()
                .overScrollVertical(),
        ) {
            item {
                SmallTitle(text = "Behavior Settings", insideMargin = PaddingValues(16.dp, 8.dp))
                Card(
                    modifier = Modifier.padding(bottom = 12.dp),
                    colors = CardDefaults.defaultColors(
                        color = MiuixTheme.colorScheme.secondaryContainer,
                    ),
                ) {
                    SuperSwitch(
                        title = "Allow Dismiss",
                        summary = "Drag or Back to dismiss",
                        checked = allowDismiss.value,
                        onCheckedChange = { allowDismiss.value = it },
                    )
                    SuperSwitch(
                        title = "Enable NestedScroll",
                        summary = "Scroll content vs Drag sheet",
                        checked = enableNestedScroll.value,
                        onCheckedChange = { enableNestedScroll.value = it },
                    )
                }
            }
            item {
                var sliderValue by remember { mutableFloatStateOf(0.5f) }
                Slider(
                    value = sliderValue,
                    onValueChange = { sliderValue = it },
                    modifier = Modifier.padding(bottom = 12.dp),
                )
                var textFieldValue by remember { mutableStateOf("") }
                TextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    label = "TextField",
                    modifier = Modifier.padding(bottom = 12.dp),
                )
                Card(
                    modifier = Modifier.padding(bottom = 12.dp),
                    colors = CardDefaults.defaultColors(
                        color = MiuixTheme.colorScheme.secondaryContainer,
                    ),
                ) {
                    WindowDropdown(
                        title = "WindowDropdown",
                        items = BottomSheetDropdownOptions,
                        selectedIndex = bottomSheetDropdownSelectedOption.value,
                        onSelectedIndexChange = { newOption -> bottomSheetDropdownSelectedOption.value = newOption },
                    )
                    SuperSwitch(
                        title = "SuperSwitch",
                        checked = bottomSheetSuperSwitchState.value,
                        onCheckedChange = {
                            bottomSheetSuperSwitchState.value = it
                        },
                    )
                }
                Spacer(
                    Modifier.padding(
                        bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() +
                            WindowInsets.captionBar.asPaddingValues().calculateBottomPadding(),
                    ),
                )
            }
        }
    }
}
