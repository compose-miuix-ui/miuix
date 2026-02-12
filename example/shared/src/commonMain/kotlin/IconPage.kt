// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic
import utils.All

@Composable
fun IconsPage(
    padding: PaddingValues,
    enableScrollEndHaptic: Boolean,
    enableOverScroll: Boolean,
    isWideScreen: Boolean,
    showTopAppBar: Boolean,
) {
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        topBar = {
            if (showTopAppBar) {
                if (isWideScreen) {
                    SmallTopAppBar(
                        title = "Icon",
                        scrollBehavior = topAppBarScrollBehavior,
                        defaultWindowInsetsPadding = false,
                    )
                } else {
                    TopAppBar(
                        title = "Icon",
                        scrollBehavior = topAppBarScrollBehavior,
                    )
                }
            }
        },
        popupHost = {},
    ) { innerPadding ->
        val allIcons = remember { MiuixIcons.All }
        LazyColumn(
            modifier = Modifier
                .then(if (enableScrollEndHaptic) Modifier.scrollEndHaptic() else Modifier)
                .overScrollVertical(isEnabled = { enableOverScroll })
                .then(if (showTopAppBar) Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection) else Modifier)
                .fillMaxHeight(),
            contentPadding = PaddingValues(
                start = innerPadding.calculateStartPadding(layoutDirection) + padding.calculateStartPadding(layoutDirection),
                end = innerPadding.calculateEndPadding(layoutDirection) + padding.calculateEndPadding(layoutDirection),
                top = innerPadding.calculateTopPadding(),
                bottom = if (isWideScreen) {
                    WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + padding.calculateBottomPadding() + 12.dp
                } else {
                    padding.calculateBottomPadding() + 12.dp
                },
            ),
            overscrollEffect = null,
        ) {
            item(key = "light") {
                SmallTitle(text = "Light Icons")
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    cornerRadius = 16.dp,
                    colors = CardDefaults.defaultColors(color = MiuixTheme.colorScheme.surfaceContainer),
                    insideMargin = PaddingValues(16.dp),
                ) {
                    FlowRow {
                        allIcons["Light"]?.forEach { icon ->
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
            item(key = "regular") {
                SmallTitle(text = "Regular Icons")
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                    cornerRadius = 16.dp,
                    colors = CardDefaults.defaultColors(color = MiuixTheme.colorScheme.surfaceContainer),
                    insideMargin = PaddingValues(16.dp),
                ) {
                    FlowRow {
                        allIcons["Regular"]?.forEach { icon ->
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
            item(key = "heavy") {
                SmallTitle(text = "Heavy Icons")
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    cornerRadius = 16.dp,
                    colors = CardDefaults.defaultColors(color = MiuixTheme.colorScheme.surfaceContainer),
                    insideMargin = PaddingValues(16.dp),
                ) {
                    FlowRow {
                        allIcons["Heavy"]?.forEach { icon ->
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
