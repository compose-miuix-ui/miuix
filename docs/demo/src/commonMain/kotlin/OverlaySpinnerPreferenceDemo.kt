// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.preference.OverlaySpinnerPreference

@Composable
fun OverlaySpinnerPreferenceDemo() {
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(demoBackground()),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .widthIn(max = 600.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                var selectedIndex1 by remember { mutableIntStateOf(0) }
                val options1 = listOf(
                    DropdownItem(text = "Option 1"),
                    DropdownItem(text = "Option 2"),
                    DropdownItem(text = "Option 3"),
                )
                var selectedIndex2 by remember { mutableIntStateOf(0) }
                var selectedIndex3 by remember { mutableIntStateOf(0) }
                var firstGroupedSelectedIndex by remember { mutableIntStateOf(0) }
                var secondGroupedSelectedIndex by remember { mutableIntStateOf(0) }
                var multiSelectedItems by remember { mutableStateOf(setOf("A1", "B2")) }
                var expanded by remember { mutableStateOf(false) }

                // Create a rounded rectangle Painter
                class RoundedRectanglePainter(
                    val cornerRadius: Dp = 6.dp,
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

                val options3 = listOf(
                    DropdownItem(text = "Option A"),
                    DropdownItem(text = "Option B"),
                    DropdownItem(text = "Option C"),
                )
                val options2 = listOf(
                    DropdownItem(
                        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFFFF5B29)) },
                        text = "Red Theme",
                        summary = "Vibrant red",
                    ),
                    DropdownItem(
                        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFF3482FF)) },
                        text = "Blue Theme",
                        summary = "Calm blue",
                    ),
                    DropdownItem(
                        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFF36D167)) },
                        text = "Green Theme",
                        summary = "Fresh green",
                    ),
                    DropdownItem(
                        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFFFFB21D)) },
                        text = "Yellow Theme",
                        summary = "Bright yellow",
                    ),
                )
                val groupedOptions = listOf(
                    DropdownEntry(
                        items = listOf("Small", "Medium").mapIndexed { index, text ->
                            DropdownItem(
                                text = text,
                                selected = firstGroupedSelectedIndex == index,
                                onClick = { firstGroupedSelectedIndex = index },
                            )
                        },
                    ),
                    DropdownEntry(
                        items = listOf("Red", "Green", "Blue").mapIndexed { index, text ->
                            DropdownItem(
                                text = text,
                                selected = secondGroupedSelectedIndex == index,
                                onClick = { secondGroupedSelectedIndex = index },
                            )
                        },
                    ),
                )
                val multiSelectOptions = listOf(
                    DropdownEntry(
                        items = listOf("A1", "A2").map { text ->
                            DropdownItem(
                                text = text,
                                selected = text in multiSelectedItems,
                                onClick = {
                                    multiSelectedItems = if (text in multiSelectedItems) {
                                        multiSelectedItems - text
                                    } else {
                                        multiSelectedItems + text
                                    }
                                },
                            )
                        },
                    ),
                    DropdownEntry(
                        items = listOf("B1", "B2", "B3").map { text ->
                            DropdownItem(
                                text = text,
                                selected = text in multiSelectedItems,
                                onClick = {
                                    multiSelectedItems = if (text in multiSelectedItems) {
                                        multiSelectedItems - text
                                    } else {
                                        multiSelectedItems + text
                                    }
                                },
                            )
                        },
                    ),
                )

                Card {
                    OverlaySpinnerPreference(
                        title = "Dropdown Selector",
                        items = options1,
                        selectedIndex = selectedIndex1,
                        onSelectedIndexChange = { selectedIndex1 = it },
                    )
                    OverlaySpinnerPreference(
                        title = "Function Selection",
                        summary = if (expanded) "Expanded" else "Choose the action you want to perform",
                        items = options2,
                        selectedIndex = selectedIndex2,
                        onSelectedIndexChange = { selectedIndex2 = it },
                        onExpandedChange = { expanded = it },
                    )
                    OverlaySpinnerPreference(
                        title = "Dialog Selector",
                        items = options3,
                        selectedIndex = selectedIndex3,
                        onSelectedIndexChange = { selectedIndex3 = it },
                        dialogButtonString = "Cancel",
                    )
                    OverlaySpinnerPreference(
                        title = "Grouped Selector",
                        entries = groupedOptions,
                        collapseOnSelection = false,
                    )
                    OverlaySpinnerPreference(
                        title = "Multi Select Selector",
                        entries = multiSelectOptions,
                        collapseOnSelection = false,
                    )
                    OverlaySpinnerPreference(
                        title = "Disabled Selector",
                        summary = "This selector is currently unavailable",
                        items = listOf(DropdownItem(text = "Option 1")),
                        selectedIndex = 0,
                        onSelectedIndexChange = {},
                        enabled = false,
                    )
                }
            }
        }
    }
}
