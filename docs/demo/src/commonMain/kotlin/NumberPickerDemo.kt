// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.NumberPicker
import top.yukonga.miuix.kmp.basic.Text

@Composable
fun NumberPickerDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(demoBackground()),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var hour by remember { mutableIntStateOf(16) }
            var minute by remember { mutableIntStateOf(30) }
            NumberPicker(
                value = hour,
                onValueChange = { hour = it },
                range = 0..23,
                label = { it.toString().padStart(2, '0') },
                wrapAround = true,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = ":",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            NumberPicker(
                value = minute,
                onValueChange = { minute = it },
                range = 0..59,
                label = { it.toString().padStart(2, '0') },
                wrapAround = true,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
