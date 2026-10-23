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
import androidx.compose.runtime.mutableIntStateOf
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
fun GlassPopupItemDemo() {
    val backdrop = rememberLayerBackdrop()
    var show by remember { mutableStateOf(false) }
    var anchorBounds by remember { mutableStateOf(Rect.Zero) }
    var layout by remember { mutableStateOf("Standard") }
    var previewCount by remember { mutableIntStateOf(0) }
    var message by remember { mutableStateOf("Choose a row to see its result.") }

    Box(Modifier.fillMaxSize()) {
        GlassDemoBackground(backdrop) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter).padding(24.dp).widthIn(max = 600.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("Menu item variants", style = MiuixTheme.textStyles.title3)
                TextButton(
                    text = "Open item examples",
                    onClick = { show = true },
                    modifier = Modifier.onGloballyPositioned { anchorBounds = it.boundsInRoot() },
                )
            }
            Column(
                modifier = Modifier.align(Alignment.BottomCenter).padding(24.dp).widthIn(max = 600.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text("Layout: $layout · Previews: $previewCount")
                Text(message, color = MiuixTheme.colorScheme.onBackground)
            }
        }
        GlassPopup(
            show = show,
            onDismissRequest = { show = false },
            anchorBounds = anchorBounds,
            backdrop = backdrop,
        ) {
            GlassPopupItem(
                text = "Standard",
                selected = layout == "Standard",
                onClick = {
                    layout = "Standard"
                    message = "Standard layout selected."
                    show = false
                },
            )
            GlassPopupItem(
                text = "Compact",
                selected = layout == "Compact",
                onClick = {
                    layout = "Compact"
                    message = "Compact layout selected."
                    show = false
                },
            )
            GlassPopupItem(
                text = "Preview layout",
                summary = "Shows a summary below the label",
                onClick = {
                    previewCount++
                    message = "Previewed the $layout layout."
                    show = false
                },
            )
            GlassPopupItem(
                text = "Unavailable action",
                enabled = false,
                onClick = {
                    message = "Unavailable action activated."
                    show = false
                },
            )
            GlassPopupItem(
                text = "Close menu",
                onClick = {
                    message = "Menu closed. Open it again to explore."
                    show = false
                },
            )
        }
    }
}
