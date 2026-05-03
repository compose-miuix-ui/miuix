// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.preference.OverlaySpinnerPreference
import top.yukonga.miuix.kmp.preference.WindowSpinnerPreference

fun LazyListScope.spinnerSection() {
    item(key = "spinner") {
        val superSpinnerOptionSelected = remember { mutableIntStateOf(0) }
        val windowSpinnerOptionSelected = remember { mutableIntStateOf(1) }
        val superSpinnerOptionSelectedDialog = remember { mutableIntStateOf(2) }
        val windowSpinnerOptionSelectedDialog = remember { mutableIntStateOf(3) }
        var overlayExpanded by remember { mutableStateOf(false) }
        var windowExpanded by remember { mutableStateOf(false) }
        var overlayDialogExpanded by remember { mutableStateOf(false) }
        var windowDialogExpanded by remember { mutableStateOf(false) }
        var overlayGroupedExpanded by remember { mutableStateOf(false) }
        var windowGroupedExpanded by remember { mutableStateOf(false) }
        var overlayGroupedDialogExpanded by remember { mutableStateOf(false) }
        var windowGroupedDialogExpanded by remember { mutableStateOf(false) }
        var overlayGroup1SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var overlayGroup2SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var overlayGroup3SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var windowGroup1SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var windowGroup2SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var windowGroup3SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var overlayDialogGroup1SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var overlayDialogGroup2SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var overlayDialogGroup3SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var windowDialogGroup1SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var windowDialogGroup2SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        var windowDialogGroup3SpinnerOptionSelected by remember { mutableIntStateOf(0) }
        val spinnerOptions = remember {
            listOf(
                DropdownItem(
                    icon = {
                        Icon(
                            RoundedRectanglePainter(),
                            "Icon",
                            Modifier.padding(end = 12.dp),
                            Color(0xFFFF5B29),
                        )
                    },
                    text = "Option 1",
                    summary = "Red",
                ),
                DropdownItem(
                    icon = {
                        Icon(
                            RoundedRectanglePainter(),
                            "Icon",
                            Modifier.padding(end = 12.dp),
                            Color(0xFF36D167),
                        )
                    },
                    text = "Option 2",
                    summary = "Green",
                ),
                DropdownItem(
                    icon = {
                        Icon(
                            RoundedRectanglePainter(),
                            "Icon",
                            Modifier.padding(end = 12.dp),
                            Color(0xFF3482FF),
                        )
                    },
                    text = "Option 3",
                    summary = "Blue",
                ),
                DropdownItem(
                    icon = {
                        Icon(
                            RoundedRectanglePainter(),
                            "Icon",
                            Modifier.padding(end = 12.dp),
                            Color(0xFFFFB21D),
                        )
                    },
                    text = "Option 4",
                    summary = "Yellow",
                ),
            )
        }
        val overlayGroupedSpinnerOptions = remember(
            spinnerOptions,
            overlayGroup1SpinnerOptionSelected,
            overlayGroup2SpinnerOptionSelected,
            overlayGroup3SpinnerOptionSelected,
        ) {
            groupedSpinnerOptions(
                spinnerOptions = spinnerOptions,
                group1SelectedIndex = overlayGroup1SpinnerOptionSelected,
                onGroup1SelectedIndexChange = { overlayGroup1SpinnerOptionSelected = it },
                group2SelectedIndex = overlayGroup2SpinnerOptionSelected,
                onGroup2SelectedIndexChange = { overlayGroup2SpinnerOptionSelected = it },
                group3SelectedIndex = overlayGroup3SpinnerOptionSelected,
                onGroup3SelectedIndexChange = { overlayGroup3SpinnerOptionSelected = it },
            )
        }
        val windowGroupedSpinnerOptions = remember(
            spinnerOptions,
            windowGroup1SpinnerOptionSelected,
            windowGroup2SpinnerOptionSelected,
            windowGroup3SpinnerOptionSelected,
        ) {
            groupedSpinnerOptions(
                spinnerOptions = spinnerOptions,
                group1SelectedIndex = windowGroup1SpinnerOptionSelected,
                onGroup1SelectedIndexChange = { windowGroup1SpinnerOptionSelected = it },
                group2SelectedIndex = windowGroup2SpinnerOptionSelected,
                onGroup2SelectedIndexChange = { windowGroup2SpinnerOptionSelected = it },
                group3SelectedIndex = windowGroup3SpinnerOptionSelected,
                onGroup3SelectedIndexChange = { windowGroup3SpinnerOptionSelected = it },
            )
        }
        val overlayGroupedDialogSpinnerOptions = remember(
            spinnerOptions,
            overlayDialogGroup1SpinnerOptionSelected,
            overlayDialogGroup2SpinnerOptionSelected,
            overlayDialogGroup3SpinnerOptionSelected,
        ) {
            groupedSpinnerOptions(
                spinnerOptions = spinnerOptions,
                group1SelectedIndex = overlayDialogGroup1SpinnerOptionSelected,
                onGroup1SelectedIndexChange = { overlayDialogGroup1SpinnerOptionSelected = it },
                group2SelectedIndex = overlayDialogGroup2SpinnerOptionSelected,
                onGroup2SelectedIndexChange = { overlayDialogGroup2SpinnerOptionSelected = it },
                group3SelectedIndex = overlayDialogGroup3SpinnerOptionSelected,
                onGroup3SelectedIndexChange = { overlayDialogGroup3SpinnerOptionSelected = it },
            )
        }
        val windowGroupedDialogSpinnerOptions = remember(
            spinnerOptions,
            windowDialogGroup1SpinnerOptionSelected,
            windowDialogGroup2SpinnerOptionSelected,
            windowDialogGroup3SpinnerOptionSelected,
        ) {
            groupedSpinnerOptions(
                spinnerOptions = spinnerOptions,
                group1SelectedIndex = windowDialogGroup1SpinnerOptionSelected,
                onGroup1SelectedIndexChange = { windowDialogGroup1SpinnerOptionSelected = it },
                group2SelectedIndex = windowDialogGroup2SpinnerOptionSelected,
                onGroup2SelectedIndexChange = { windowDialogGroup2SpinnerOptionSelected = it },
                group3SelectedIndex = windowDialogGroup3SpinnerOptionSelected,
                onGroup3SelectedIndexChange = { windowDialogGroup3SpinnerOptionSelected = it },
            )
        }

        SmallTitle(text = "Spinner")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            OverlaySpinnerPreference(
                title = "SpinnerPref (O)",
                summary = if (overlayExpanded) "Expanded" else "Collapsed",
                items = spinnerOptions,
                selectedIndex = superSpinnerOptionSelected.value,
                onSelectedIndexChange = { newOption -> superSpinnerOptionSelected.value = newOption },
                onExpandedChange = { overlayExpanded = it },
            )
            WindowSpinnerPreference(
                title = "SpinnerPref (W)",
                summary = if (windowExpanded) "Expanded" else "Collapsed",
                items = spinnerOptions,
                selectedIndex = windowSpinnerOptionSelected.value,
                onSelectedIndexChange = { newOption -> windowSpinnerOptionSelected.value = newOption },
                onExpandedChange = { windowExpanded = it },
            )
            OverlaySpinnerPreference(
                title = "SpinnerPref (O)",
                summary = "As Dialog (O)" + if (overlayDialogExpanded) " (Expanded)" else " (Collapsed)",
                dialogButtonString = "OK",
                items = spinnerOptions,
                selectedIndex = superSpinnerOptionSelectedDialog.value,
                onSelectedIndexChange = { newOption -> superSpinnerOptionSelectedDialog.value = newOption },
                onExpandedChange = { overlayDialogExpanded = it },
            )
            WindowSpinnerPreference(
                title = "SpinnerPref (W)",
                summary = "As Dialog (W)" + if (windowDialogExpanded) " (Expanded)" else " (Collapsed)",
                dialogButtonString = "OK",
                items = spinnerOptions,
                selectedIndex = windowSpinnerOptionSelectedDialog.value,
                onSelectedIndexChange = { newOption -> windowSpinnerOptionSelectedDialog.value = newOption },
                onExpandedChange = { windowDialogExpanded = it },
            )
            OverlaySpinnerPreference(
                title = "Grouped SpinnerPref (O)",
                summary = if (overlayGroupedExpanded) "Expanded" else "Collapsed",
                entries = overlayGroupedSpinnerOptions,
                collapseOnSelection = false,
                onExpandedChange = { overlayGroupedExpanded = it },
            )
            WindowSpinnerPreference(
                title = "Grouped SpinnerPref (W)",
                summary = if (windowGroupedExpanded) "Expanded" else "Collapsed",
                entries = windowGroupedSpinnerOptions,
                collapseOnSelection = false,
                onExpandedChange = { windowGroupedExpanded = it },
            )
            OverlaySpinnerPreference(
                title = "Grouped SpinnerPref (O)",
                summary = "As Dialog (O)" + if (overlayGroupedDialogExpanded) " (Expanded)" else " (Collapsed)",
                dialogButtonString = "OK",
                entries = overlayGroupedDialogSpinnerOptions,
                onExpandedChange = { overlayGroupedDialogExpanded = it },
            )
            WindowSpinnerPreference(
                title = "Grouped SpinnerPref (W)",
                summary = "As Dialog (W)" + if (windowGroupedDialogExpanded) " (Expanded)" else " (Collapsed)",
                dialogButtonString = "OK",
                entries = windowGroupedDialogSpinnerOptions,
                onExpandedChange = { windowGroupedDialogExpanded = it },
            )
            OverlaySpinnerPreference(
                title = "Disabled SpinnerPref (O)",
                summary = "Collapsed",
                items = listOf(DropdownItem(text = "Option 5")),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
            WindowSpinnerPreference(
                title = "Disabled SpinnerPref (W)",
                summary = "Collapsed",
                items = listOf(DropdownItem(text = "Option 6")),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
        }
    }
}

private fun groupedSpinnerOptions(
    spinnerOptions: List<DropdownItem>,
    group1SelectedIndex: Int,
    onGroup1SelectedIndexChange: (Int) -> Unit,
    group2SelectedIndex: Int,
    onGroup2SelectedIndexChange: (Int) -> Unit,
    group3SelectedIndex: Int,
    onGroup3SelectedIndexChange: (Int) -> Unit,
): List<DropdownEntry> = listOf(
    DropdownEntry(
        items = spinnerOptions.take(2).mapIndexed { index, item ->
            item.copy(
                selected = group1SelectedIndex == index,
                onClick = {
                    onGroup1SelectedIndexChange(index)
                    item.onClick?.invoke()
                },
            )
        },
    ),
    DropdownEntry(
        items = spinnerOptions.drop(2).mapIndexed { index, item ->
            item.copy(
                selected = group2SelectedIndex == index,
                onClick = {
                    onGroup2SelectedIndexChange(index)
                    item.onClick?.invoke()
                },
            )
        },
    ),
    DropdownEntry(
        items = spinnerOptions.mapIndexed { index, item ->
            item.copy(
                text = "Option ${index + 1}",
                enabled = index % 2 == 0,
                selected = group3SelectedIndex == index,
                onClick = {
                    onGroup3SelectedIndexChange(index)
                    item.onClick?.invoke()
                },
            )
        },
    ),
)

private class RoundedRectanglePainter(
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
