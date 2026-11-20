// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.glass
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassPopupDemo() {
    val backdrop = rememberLayerBackdrop()
    val density = LocalDensity.current
    val sortOrders = remember { listOf("Newest first", "Oldest first", "Name") }
    var show by remember { mutableStateOf(false) }
    var anchorBounds by remember { mutableStateOf(Rect.Zero) }
    var sortOrder by remember { mutableStateOf("Newest first") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .layerBackdrop(backdrop)
                .background(demoBackground()),
        )
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .glass(backdrop = backdrop, shape = GlassShape(24.dp))
                .clip(CircleShape)
                .clickable { show = true }
                .onGloballyPositioned {
                    val b = it.boundsInWindow()
                    val gapPx = with(density) { 6.dp.toPx() }
                    anchorBounds = Rect(b.left, b.bottom + gapPx, b.right, b.bottom + gapPx)
                },
        ) {
            Text(
                text = sortOrder,
                style = MiuixTheme.textStyles.button,
                color = MiuixTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
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
