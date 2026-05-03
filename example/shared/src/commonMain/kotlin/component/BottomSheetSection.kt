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
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Close
import top.yukonga.miuix.kmp.icon.extended.Ok
import top.yukonga.miuix.kmp.overlay.OverlayBottomSheet
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.OverlayDropdownPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.preference.WindowDropdownPreference
import top.yukonga.miuix.kmp.theme.LocalDismissState
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic
import top.yukonga.miuix.kmp.window.WindowBottomSheet

private val BottomSheetDropdownOptions = listOf("Option 1", "Option 2")

fun LazyListScope.bottomSheetSection() {
    item(key = "bottomSheet") {
        var showSuperBottomSheet by remember { mutableStateOf(false) }
        var showWindowBottomSheet by remember { mutableStateOf(false) }
        var superBottomSheetHoldDown by remember { mutableStateOf(false) }
        var windowBottomSheetHoldDown by remember { mutableStateOf(false) }
        var bottomSheetDropdownSelectedOption by remember { mutableIntStateOf(0) }
        var bottomSheetSuperSwitchState by remember { mutableStateOf(true) }

        SmallTitle(text = "BottomSheet")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            ArrowPreference(
                title = "BottomSheet (O)",
                summary = "Click to show an OverlayBottomSheet",
                onClick = {
                    showSuperBottomSheet = true
                    superBottomSheetHoldDown = true
                },
                holdDownState = superBottomSheetHoldDown,
            )
            ArrowPreference(
                title = "BottomSheet (W)",
                summary = "Click to show a WindowBottomSheet",
                onClick = {
                    showWindowBottomSheet = true
                    windowBottomSheetHoldDown = true
                },
                holdDownState = windowBottomSheetHoldDown,
            )
        }

        SuperBottomSheetDemo(
            show = showSuperBottomSheet,
            onDismissRequest = { showSuperBottomSheet = false },
            dropdownSelectedIndex = bottomSheetDropdownSelectedOption,
            onDropdownSelectedIndexChange = { bottomSheetDropdownSelectedOption = it },
            switchChecked = bottomSheetSuperSwitchState,
            onSwitchCheckedChange = { bottomSheetSuperSwitchState = it },
            onDismissFinished = { superBottomSheetHoldDown = false },
        )
        WindowBottomSheetDemo(
            show = showWindowBottomSheet,
            onDismissRequest = { showWindowBottomSheet = false },
            dropdownSelectedIndex = bottomSheetDropdownSelectedOption,
            onDropdownSelectedIndexChange = { bottomSheetDropdownSelectedOption = it },
            switchChecked = bottomSheetSuperSwitchState,
            onSwitchCheckedChange = { bottomSheetSuperSwitchState = it },
            onDismissFinished = { windowBottomSheetHoldDown = false },
        )
    }
}

@Composable
private fun SuperBottomSheetDemo(
    show: Boolean,
    onDismissRequest: () -> Unit,
    dropdownSelectedIndex: Int,
    onDropdownSelectedIndexChange: (Int) -> Unit,
    switchChecked: Boolean,
    onSwitchCheckedChange: (Boolean) -> Unit,
    onDismissFinished: () -> Unit,
) {
    var allowDismiss by remember { mutableStateOf(true) }
    var enableNestedScroll by remember { mutableStateOf(true) }

    OverlayBottomSheet(
        title = "BottomSheet (O)",
        show = show,
        allowDismiss = allowDismiss,
        enableNestedScroll = enableNestedScroll,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        startAction = {
            IconButton(
                onClick = onDismissRequest,
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
                onClick = onDismissRequest,
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
                SmallTitle(
                    text = "Behavior Settings",
                    insideMargin = PaddingValues(16.dp, 8.dp),
                )
                Card(
                    modifier = Modifier.padding(bottom = 12.dp),
                    colors = CardDefaults.defaultColors(
                        color = MiuixTheme.colorScheme.secondaryContainer,
                    ),
                ) {
                    SwitchPreference(
                        title = "Allow Dismiss",
                        summary = "Drag or Back to dismiss",
                        checked = allowDismiss,
                        onCheckedChange = { allowDismiss = it },
                    )
                    SwitchPreference(
                        title = "Enable NestedScroll",
                        summary = "Scroll content vs Drag sheet",
                        checked = enableNestedScroll,
                        onCheckedChange = { enableNestedScroll = it },
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
                    OverlayDropdownPreference(
                        title = "DropdownPref (O)",
                        items = BottomSheetDropdownOptions,
                        selectedIndex = dropdownSelectedIndex,
                        onSelectedIndexChange = onDropdownSelectedIndexChange,
                    )
                    SwitchPreference(
                        title = "SwitchPref",
                        checked = switchChecked,
                        onCheckedChange = onSwitchCheckedChange,
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
    show: Boolean,
    onDismissRequest: () -> Unit,
    dropdownSelectedIndex: Int,
    onDropdownSelectedIndexChange: (Int) -> Unit,
    switchChecked: Boolean,
    onSwitchCheckedChange: (Boolean) -> Unit,
    onDismissFinished: () -> Unit,
) {
    var allowDismiss by remember { mutableStateOf(true) }
    var enableNestedScroll by remember { mutableStateOf(true) }

    WindowBottomSheet(
        title = "BottomSheet (W)",
        show = show,
        allowDismiss = allowDismiss,
        enableNestedScroll = enableNestedScroll,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        startAction = {
            val dismissState = LocalDismissState.current
            IconButton(
                onClick = { dismissState?.invoke() },
            ) {
                Icon(
                    imageVector = MiuixIcons.Close,
                    contentDescription = "Cancel",
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            }
        },
        endAction = {
            val dismissState = LocalDismissState.current
            IconButton(
                onClick = { dismissState?.invoke() },
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
                SmallTitle(
                    text = "Behavior Settings",
                    insideMargin = PaddingValues(16.dp, 8.dp),
                )
                Card(
                    modifier = Modifier.padding(bottom = 12.dp),
                    colors = CardDefaults.defaultColors(
                        color = MiuixTheme.colorScheme.secondaryContainer,
                    ),
                ) {
                    SwitchPreference(
                        title = "Allow Dismiss",
                        summary = "Drag or Back to dismiss",
                        checked = allowDismiss,
                        onCheckedChange = { allowDismiss = it },
                    )
                    SwitchPreference(
                        title = "Enable NestedScroll",
                        summary = "Scroll content vs Drag sheet",
                        checked = enableNestedScroll,
                        onCheckedChange = { enableNestedScroll = it },
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
                    WindowDropdownPreference(
                        title = "DropdownPref (W)",
                        items = BottomSheetDropdownOptions,
                        selectedIndex = dropdownSelectedIndex,
                        onSelectedIndexChange = onDropdownSelectedIndexChange,
                    )
                    SwitchPreference(
                        title = "SwitchPref",
                        checked = switchChecked,
                        onCheckedChange = onSwitchCheckedChange,
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
