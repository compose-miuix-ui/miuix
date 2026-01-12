// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Checkbox
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.extra.CheckboxLocation
import top.yukonga.miuix.kmp.extra.LocalWindowBottomSheetState
import top.yukonga.miuix.kmp.extra.LocalWindowDialogState
import top.yukonga.miuix.kmp.extra.SpinnerEntry
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperBottomSheet
import top.yukonga.miuix.kmp.extra.SuperCheckbox
import top.yukonga.miuix.kmp.extra.SuperDialog
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.extra.SuperSpinner
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.extra.WindowBottomSheet
import top.yukonga.miuix.kmp.extra.WindowDialog
import top.yukonga.miuix.kmp.extra.WindowDropdown
import top.yukonga.miuix.kmp.extra.WindowSpinner
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Close
import top.yukonga.miuix.kmp.icon.extended.Contacts
import top.yukonga.miuix.kmp.icon.extended.Ok
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun TextComponent(
    showSuperDialog: MutableState<Boolean>,
    showWindowDialog: MutableState<Boolean>,
    showSuperBottomSheet: MutableState<Boolean>,
    showWindowBottomSheet: MutableState<Boolean>,
    bottomSheetDropdownSelectedOption: MutableState<Int>,
    bottomSheetSuperSwitchState: MutableState<Boolean>,
    checkbox: MutableState<Boolean>,
    checkboxTrue: MutableState<Boolean>,
    switch: MutableState<Boolean>,
    switchTrue: MutableState<Boolean>,
    superDropdownOptionSelected: MutableState<Int>,
    windowDropdownOptionSelected: MutableState<Int>,
    superSpinnerOptionSelected: MutableState<Int>,
    windowSpinnerOptionSelected: MutableState<Int>,
    superSpinnerOptionSelectedDialog: MutableState<Int>,
    windowSpinnerOptionSelectedDialog: MutableState<Int>,
    superCheckbox: MutableState<String>,
    superCheckboxState: MutableState<Boolean>,
    superEndCheckbox: MutableState<String>,
    superEndCheckboxState: MutableState<Boolean>,
    superSwitch: MutableState<String>,
    superSwitchState: MutableState<Boolean>,
    superSwitchAnimState: MutableState<Boolean>,
) {
    Column {
        val dropdownOptions = remember { listOf("Option 1", "Option 2", "Option 3", "Option 4") }
        val dropdownLongOptions =
            remember {
                listOf(
                    "Option 1",
                    "Option 2",
                    "Option 3",
                    "Option 4",
                    "Option 5",
                    "Option 6",
                    "Option 7",
                    "Option 8",
                    "Option 9",
                    "Option 10",
                    "Option 11",
                    "Option 12",
                )
            }
        val spinnerOptions = remember {
            listOf(
                SpinnerEntry(
                    icon = {
                        Icon(
                            RoundedRectanglePainter(),
                            "Icon",
                            Modifier.padding(end = 12.dp),
                            Color(0xFFFF5B29),
                        )
                    },
                    "Option 1",
                    "Red",
                ),
                SpinnerEntry(
                    icon = {
                        Icon(
                            RoundedRectanglePainter(),
                            "Icon",
                            Modifier.padding(end = 12.dp),
                            Color(0xFF36D167),
                        )
                    },
                    "Option 2",
                    "Green",
                ),
                SpinnerEntry(
                    icon = {
                        Icon(
                            RoundedRectanglePainter(),
                            "Icon",
                            Modifier.padding(end = 12.dp),
                            Color(0xFF3482FF),
                        )
                    },
                    "Option 3",
                    "Blue",
                ),
                SpinnerEntry(
                    icon = {
                        Icon(
                            RoundedRectanglePainter(),
                            "Icon",
                            Modifier.padding(end = 12.dp),
                            Color(0xFFFFB21D),
                        )
                    },
                    "Option 4",
                    "Yellow",
                ),
            )
        }

        var volume by remember { mutableFloatStateOf(0.5f) }
        val showVolumeDialog = remember { mutableStateOf(false) }

        SmallTitle(text = "Basic Component")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            BasicComponent(
                title = "Title",
                summary = "Summary",
                startAction = {
                    Text(
                        text = "Start",
                        modifier = Modifier.padding(end = 8.dp),
                    )
                },
                endActions = {
                    Text(text = "End1")
                    Spacer(Modifier.width(8.dp))
                    Text(text = "End2")
                },
                enabled = true,
            )
            BasicComponent(
                title = "Title",
                summary = "Summary",
                startAction = {
                    Text(
                        text = "Start",
                        modifier = Modifier.padding(end = 8.dp),
                        color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                    )
                },
                endActions = {
                    Text(
                        text = "End1",
                        color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "End2",
                        color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                    )
                },
                enabled = false,
            )
        }

        SmallTitle(text = "Checkbox")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Checkbox(
                    checked = checkbox.value,
                    onCheckedChange = { checkbox.value = it },
                )
                Checkbox(
                    checked = checkboxTrue.value,
                    onCheckedChange = { checkboxTrue.value = it },
                    modifier = Modifier.padding(start = 8.dp),
                )
                Checkbox(
                    checked = false,
                    onCheckedChange = { },
                    modifier = Modifier.padding(start = 8.dp),
                    enabled = false,
                )
                Checkbox(
                    checked = true,
                    onCheckedChange = { },
                    modifier = Modifier.padding(start = 8.dp),
                    enabled = false,
                )
            }
            SuperCheckbox(
                checkboxLocation = CheckboxLocation.End,
                title = "Checkbox",
                checked = superEndCheckboxState.value,
                endActions = {
                    Text(
                        modifier = Modifier.padding(end = 6.dp),
                        text = superEndCheckbox.value,
                        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                    )
                },
                onCheckedChange = {
                    superEndCheckboxState.value = it
                    superEndCheckbox.value = "$it"
                },
            )
            SuperCheckbox(
                title = "Checkbox",
                summary = superCheckbox.value,
                checked = superCheckboxState.value,
                onCheckedChange = {
                    superCheckboxState.value = it
                    superCheckbox.value = "State: $it"
                },
            )
            SuperCheckbox(
                title = "Disabled Checkbox",
                checked = true,
                enabled = false,
                onCheckedChange = {},
            )
        }

        SmallTitle(text = "Switch")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Switch(
                    checked = switch.value,
                    onCheckedChange = { switch.value = it },
                )
                Switch(
                    checked = switchTrue.value,
                    onCheckedChange = { switchTrue.value = it },
                    modifier = Modifier.padding(start = 6.dp),
                )
                Switch(
                    checked = false,
                    onCheckedChange = { },
                    modifier = Modifier.padding(start = 6.dp),
                    enabled = false,
                )
                Switch(
                    checked = true,
                    onCheckedChange = { },
                    modifier = Modifier.padding(start = 6.dp),
                    enabled = false,
                )
            }
            SuperSwitch(
                title = "Switch",
                summary = "Click to expand a Switch",
                checked = superSwitchAnimState.value,
                onCheckedChange = {
                    superSwitchAnimState.value = it
                },
            )

            AnimatedVisibility(
                visible = superSwitchAnimState.value,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                SuperSwitch(
                    title = "Switch",
                    checked = superSwitchState.value,
                    endActions = {
                        Text(
                            modifier = Modifier.padding(end = 6.dp),
                            text = superSwitch.value,
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                    },
                    onCheckedChange = {
                        superSwitchState.value = it
                        superSwitch.value = "$it"
                    },
                )
            }
            SuperSwitch(
                title = "Disabled Switch",
                checked = true,
                enabled = false,
                onCheckedChange = {},
            )
        }

        SmallTitle(text = "Arrow")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            SuperArrow(
                title = "Arrow",
                startAction = {
                    Box(
                        modifier = Modifier.padding(end = 8.dp),
                    ) {
                        Icon(
                            imageVector = MiuixIcons.Contacts,
                            contentDescription = "Personal",
                            tint = MiuixTheme.colorScheme.onBackground,
                        )
                    }
                },
                endActions = {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = "End",
                        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                    )
                },
                onClick = {},
            )
            SuperArrow(
                title = "Arrow + Slider + Dialog",
                endActions = {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = "${(volume * 100).toInt()}%",
                        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                    )
                },
                onClick = { showVolumeDialog.value = true },
                holdDownState = showVolumeDialog.value,
                bottomAction = {
                    Slider(
                        value = volume,
                        onValueChange = { volume = it },
                    )
                },
            )
            SuperArrow(
                title = "Disabled Arrow",
                endActions = {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        text = "End",
                        color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
                    )
                },
                enabled = false,
            )
        }

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
                },
                holdDownState = showSuperDialog.value,
            )
            SuperArrow(
                title = "WindowDialog",
                summary = "Click to show a WindowDialog",
                onClick = {
                    showWindowDialog.value = true
                },
                holdDownState = showWindowDialog.value,
            )
        }

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
                },
                holdDownState = showSuperBottomSheet.value,
            )
            SuperArrow(
                title = "WindowBottomSheet",
                summary = "Click to show a WindowBottomSheet",
                onClick = {
                    showWindowBottomSheet.value = true
                },
                holdDownState = showWindowBottomSheet.value,
            )
        }

        SmallTitle(text = "Dropdown")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            SuperDropdown(
                title = "SuperDropdown",
                items = dropdownOptions,
                selectedIndex = superDropdownOptionSelected.value,
                onSelectedIndexChange = { newOption -> superDropdownOptionSelected.value = newOption },
            )
            WindowDropdown(
                title = "WindowDropdown",
                items = dropdownLongOptions,
                selectedIndex = windowDropdownOptionSelected.value,
                onSelectedIndexChange = { newOption -> windowDropdownOptionSelected.value = newOption },
            )
            SuperDropdown(
                title = "Disabled SuperDropdown",
                items = listOf("Option 3"),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
            WindowDropdown(
                title = "Disabled WindowDropdown",
                items = listOf("Option 4"),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
        }

        SmallTitle(text = "Spinner")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            SuperSpinner(
                title = "SuperSpinner",
                items = spinnerOptions,
                selectedIndex = superSpinnerOptionSelected.value,
                onSelectedIndexChange = { newOption -> superSpinnerOptionSelected.value = newOption },
            )
            WindowSpinner(
                title = "WindowSpinner",
                items = spinnerOptions,
                selectedIndex = windowSpinnerOptionSelected.value,
                onSelectedIndexChange = { newOption -> windowSpinnerOptionSelected.value = newOption },
            )
            SuperSpinner(
                title = "SuperSpinner",
                summary = "As SuperDialog",
                dialogButtonString = "Cancel",
                items = spinnerOptions,
                selectedIndex = superSpinnerOptionSelectedDialog.value,
                onSelectedIndexChange = { newOption -> superSpinnerOptionSelectedDialog.value = newOption },
            )
            WindowSpinner(
                title = "WindowSpinner",
                summary = "As WindowDialog",
                dialogButtonString = "Cancel",
                items = spinnerOptions,
                selectedIndex = windowSpinnerOptionSelectedDialog.value,
                onSelectedIndexChange = { newOption -> windowSpinnerOptionSelectedDialog.value = newOption },
            )
            SuperSpinner(
                title = "Disabled SuperSpinner",
                items = listOf(SpinnerEntry(icon = null, title = "Option 5")),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
            WindowSpinner(
                title = "Disabled WindowSpinner",
                items = listOf(SpinnerEntry(icon = null, title = "Option 6")),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
        }

        SuperDialog(showSuperDialog)
        WindowDialog(showWindowDialog)
        SliderDialog(showVolumeDialog, volumeState = { volume }, onVolumeChange = { volume = it })
        SuperBottomSheet(showSuperBottomSheet, bottomSheetDropdownSelectedOption, bottomSheetSuperSwitchState)
        WindowBottomSheet(showWindowBottomSheet, bottomSheetDropdownSelectedOption, bottomSheetSuperSwitchState)
    }
}

@Composable
fun SuperDialog(
    showDialog: MutableState<Boolean>,
) {
    SuperDialog(
        title = "SuperDialog",
        summary = "A dialog component inside MiuixPopupHost.",
        show = showDialog,
        onDismissRequest = {
            showDialog.value = false
        },
    ) {
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
    }
}

@Composable
fun WindowDialog(
    showDialog: MutableState<Boolean>,
) {
    WindowDialog(
        title = "WindowDialog",
        summary = "A window-level dialog, no MiuixPopupHost required.",
        show = showDialog,
        onDismissRequest = {
            showDialog.value = false
        },
    ) {
        val state = LocalWindowDialogState.current
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextButton(
                text = "Cancel",
                onClick = {
                    state.invoke()
                },
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "Confirm",
                onClick = {
                    state.invoke()
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary(),
            )
        }
    }
}

@Composable
fun SliderDialog(
    showDialog: MutableState<Boolean>,
    volumeState: () -> Float,
    onVolumeChange: (Float) -> Unit,
) {
    SuperDialog(
        title = "Adjust Volume",
        summary = "Enter 0-100",
        show = showDialog,
        onDismissRequest = { showDialog.value = false },
    ) {
        var text by remember { mutableStateOf(((volumeState() * 100).toInt()).toString()) }
        TextField(
            modifier = Modifier.padding(bottom = 16.dp),
            value = text,
            maxLines = 1,
            onValueChange = { newValue ->
                val digits = newValue.filter { it.isDigit() }
                if (digits.isEmpty()) {
                    text = ""
                } else {
                    val limited = digits.take(3)
                    val num = limited.toIntOrNull() ?: 0
                    val clamped = num.coerceIn(0, 100)
                    text = clamped.toString()
                }
            },
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            TextButton(
                text = "Cancel",
                onClick = { showDialog.value = false },
                modifier = Modifier.weight(1f),
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "Confirm",
                onClick = {
                    val parsed = text.toIntOrNull()
                    val clamped = parsed?.coerceIn(0, 100) ?: ((volumeState() * 100).toInt())
                    onVolumeChange(clamped / 100f)
                    showDialog.value = false
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary(),
            )
        }
    }
}

@Composable
fun SuperBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    bottomSheetDropdownSelectedOption: MutableState<Int>,
    bottomSheetSuperSwitchState: MutableState<Boolean>,
) {
    val allowDismiss = remember { mutableStateOf(true) }
    val enableNestedScroll = remember { mutableStateOf(true) }

    val dropdownOptions = listOf("Option 1", "Option 2")
    SuperBottomSheet(
        title = "BottomSheet",
        show = showBottomSheet,
        allowDismiss = allowDismiss.value,
        enableNestedScroll = enableNestedScroll.value,
        onDismissRequest = {
            showBottomSheet.value = false
        },
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
            overscrollEffect = null,
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
                Card(
                    modifier = Modifier.padding(bottom = 12.dp),
                    colors = CardDefaults.defaultColors(
                        color = MiuixTheme.colorScheme.secondaryContainer,
                    ),
                ) {
                    SuperDropdown(
                        title = "SuperDropdown",
                        items = dropdownOptions,
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
                        bottom = WindowInsets.navigationBars.asPaddingValues()
                            .calculateBottomPadding() + WindowInsets.captionBar.asPaddingValues().calculateBottomPadding(),
                    ),
                )
            }
        }
    }
}

@Composable
fun WindowBottomSheet(
    showBottomSheet: MutableState<Boolean>,
    bottomSheetDropdownSelectedOption: MutableState<Int>,
    bottomSheetSuperSwitchState: MutableState<Boolean>,
) {
    val allowDismiss = remember { mutableStateOf(true) }
    val enableNestedScroll = remember { mutableStateOf(true) }

    val dropdownOptions = listOf("Option 1", "Option 2")
    var state: (() -> Unit)? = null
    WindowBottomSheet(
        title = "WindowBottomSheet",
        show = showBottomSheet,
        allowDismiss = allowDismiss.value,
        enableNestedScroll = enableNestedScroll.value,
        onDismissRequest = {
            showBottomSheet.value = false
        },
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
        state = LocalWindowBottomSheetState.current
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .scrollEndHaptic()
                .overScrollVertical(),
            overscrollEffect = null,
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
                Card(
                    modifier = Modifier.padding(bottom = 12.dp),
                    colors = CardDefaults.defaultColors(
                        color = MiuixTheme.colorScheme.secondaryContainer,
                    ),
                ) {
                    WindowDropdown(
                        title = "WindowDropdown",
                        items = dropdownOptions,
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
                        bottom = WindowInsets.navigationBars.asPaddingValues()
                            .calculateBottomPadding() + WindowInsets.captionBar.asPaddingValues().calculateBottomPadding(),
                    ),
                )
            }
        }
    }
}

class RoundedRectanglePainter(
    private val cornerRadius: Dp = 6.dp,
) : Painter() {
    override val intrinsicSize = Size.Unspecified

    override fun DrawScope.onDraw() {
        drawRoundRect(
            color = Color.White,
            size = Size(size.width, size.height),
            cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
        )
    }
}
