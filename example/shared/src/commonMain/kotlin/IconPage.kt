// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import utils.AdaptiveTopAppBar
import utils.All
import utils.pageContentPadding
import utils.pageScrollModifiers

@Composable
fun IconsPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    Scaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = "Icon",
                showTopAppBar = appState.showTopAppBar,
                isWideScreen = isWideScreen,
                scrollBehavior = topAppBarScrollBehavior,
            )
        },
    ) { innerPadding ->
        val allIcons = remember { MiuixIcons.All }
        LazyColumn(
            modifier = Modifier.pageScrollModifiers(appState.enableScrollEndHaptic, appState.enableOverScroll, appState.showTopAppBar, topAppBarScrollBehavior),
            contentPadding = pageContentPadding(innerPadding, padding, isWideScreen),
            overscrollEffect = null,
        ) {
            val iconCategories = listOf("Light" to "Light Icons", "Regular" to "Regular Icons", "Heavy" to "Heavy Icons")
            iconCategories.forEachIndexed { idx, (key, title) ->
                item(key = key.lowercase()) {
                    SmallTitle(text = title)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .then(if (idx < iconCategories.lastIndex) Modifier.padding(bottom = 12.dp) else Modifier),
                        cornerRadius = 16.dp,
                        colors = CardDefaults.defaultColors(color = MiuixTheme.colorScheme.surfaceContainer),
                        insideMargin = PaddingValues(16.dp),
                    ) {
                        FlowRow {
                            allIcons[key]?.forEach { icon ->
                                Icon(
                                    imageVector = icon,
                                    contentDescription = icon.name,
                                    tint = MiuixTheme.colorScheme.onBackground,
                                    modifier = Modifier.size(24.dp),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
