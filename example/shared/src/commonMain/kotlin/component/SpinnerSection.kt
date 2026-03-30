// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SpinnerEntry
import top.yukonga.miuix.kmp.preference.OverlaySpinnerPreference
import top.yukonga.miuix.kmp.preference.WindowSpinnerPreference

fun LazyListScope.spinnerSection() {
    item(key = "spinner") {
        val superSpinnerOptionSelected = remember { mutableIntStateOf(0) }
        val windowSpinnerOptionSelected = remember { mutableIntStateOf(1) }
        val superSpinnerOptionSelectedDialog = remember { mutableIntStateOf(2) }
        val windowSpinnerOptionSelectedDialog = remember { mutableIntStateOf(3) }
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

        SmallTitle(text = "Spinner")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            OverlaySpinnerPreference(
                title = "SpinnerPref",
                items = spinnerOptions,
                selectedIndex = superSpinnerOptionSelected.value,
                onSelectedIndexChange = { newOption -> superSpinnerOptionSelected.value = newOption },
            )
            WindowSpinnerPreference(
                title = "WindowSpinnerPref",
                items = spinnerOptions,
                selectedIndex = windowSpinnerOptionSelected.value,
                onSelectedIndexChange = { newOption -> windowSpinnerOptionSelected.value = newOption },
            )
            OverlaySpinnerPreference(
                title = "SpinnerPref",
                summary = "As OverlayDialog",
                dialogButtonString = "Cancel",
                items = spinnerOptions,
                selectedIndex = superSpinnerOptionSelectedDialog.value,
                onSelectedIndexChange = { newOption -> superSpinnerOptionSelectedDialog.value = newOption },
            )
            WindowSpinnerPreference(
                title = "WindowSpinnerPref",
                summary = "As WindowDialog",
                dialogButtonString = "Cancel",
                items = spinnerOptions,
                selectedIndex = windowSpinnerOptionSelectedDialog.value,
                onSelectedIndexChange = { newOption -> windowSpinnerOptionSelectedDialog.value = newOption },
            )
            OverlaySpinnerPreference(
                title = "Disabled SpinnerPref",
                items = listOf(SpinnerEntry(icon = null, title = "Option 5")),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
            WindowSpinnerPreference(
                title = "Disabled WindowSpinnerPref",
                items = listOf(SpinnerEntry(icon = null, title = "Option 6")),
                selectedIndex = 0,
                onSelectedIndexChange = {},
                enabled = false,
            )
        }
    }
}

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
