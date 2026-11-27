// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.More
import top.yukonga.miuix.kmp.icon.symbol.Ok
import top.yukonga.miuix.kmp.icon.symbol.Search
import top.yukonga.miuix.kmp.symbol.toImageVector
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassIconButtonDemo() {
    val backdrop = rememberLayerBackdrop()
    var confirmedCount by remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .layerBackdrop(backdrop)
                .background(demoBackground()),
        )
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GlassIconButton(
                    onClick = { confirmedCount++ },
                    backdrop = backdrop,
                ) {
                    Icon(
                        imageVector = MiuixIcons.Symbol.Ok.toImageVector(),
                        contentDescription = "Confirm",
                        tint = MiuixTheme.colorScheme.onBackground,
                    )
                }
                GlassIconButton(
                    onClick = { confirmedCount++ },
                    backdrop = backdrop,
                ) {
                    Icon(
                        imageVector = MiuixIcons.Symbol.More.toImageVector(),
                        contentDescription = "More",
                        tint = MiuixTheme.colorScheme.onBackground,
                    )
                }
                GlassIconButton(
                    onClick = { confirmedCount++ },
                    backdrop = backdrop,
                ) {
                    Icon(
                        imageVector = MiuixIcons.Symbol.Search.toImageVector(),
                        contentDescription = "Search",
                        tint = MiuixTheme.colorScheme.onBackground,
                    )
                }
            }
            Text(
                text = "Confirmed: $confirmedCount",
                color = MiuixTheme.colorScheme.onBackground,
            )
        }
    }
}
