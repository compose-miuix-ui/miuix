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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.glass.Ok
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassIconButtonDemo() {
    val backdrop = rememberLayerBackdrop()
    var confirmedCount by remember { mutableIntStateOf(0) }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        GlassDemoBackground(backdrop)
        Column(
            modifier = Modifier.padding(24.dp).widthIn(max = 600.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Tap to confirm", style = MiuixTheme.textStyles.title3)
            GlassIconButton(
                onClick = { confirmedCount++ },
                backdrop = backdrop,
            ) {
                Icon(
                    imageVector = MiuixIcons.Glass.Ok,
                    contentDescription = "Confirm",
                    tint = MiuixTheme.colorScheme.onBackground,
                )
            }
            Text(
                text = "Confirmed: $confirmedCount",
                color = MiuixTheme.colorScheme.onBackground,
            )
        }
    }
}
