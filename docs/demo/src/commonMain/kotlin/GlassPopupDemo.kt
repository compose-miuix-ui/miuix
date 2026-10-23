// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassPopupDemo() {
    val backdrop = rememberLayerBackdrop()
    val sortOrders = remember { listOf("Newest first", "Oldest first", "Name") }
    var show by remember { mutableStateOf(false) }
    var anchorBounds by remember { mutableStateOf(Rect.Zero) }
    var sortOrder by remember { mutableStateOf("Newest first") }

    Box(Modifier.fillMaxSize()) {
        GlassDemoBackground(backdrop) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter).padding(24.dp).widthIn(max = 600.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Sort photos", style = MiuixTheme.textStyles.title3)
                Text("A glass menu anchored to a regular button.")
                TextButton(
                    text = "Choose sort order",
                    onClick = { show = true },
                    modifier = Modifier.onGloballyPositioned { anchorBounds = it.boundsInRoot() },
                )
            }
            Text(
                text = "Sort order: $sortOrder",
                modifier = Modifier.align(Alignment.BottomCenter).padding(24.dp),
                color = MiuixTheme.colorScheme.onBackground,
            )
        }
        GlassPopup(
            show = show,
            onDismissRequest = { show = false },
            anchorBounds = anchorBounds,
            backdrop = backdrop,
        ) {
            sortOrders.forEach { label ->
                GlassPopupItem(
                    text = label,
                    selected = sortOrder == label,
                    onClick = {
                        sortOrder = label
                        show = false
                    },
                )
            }
        }
    }
}
