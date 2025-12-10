// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.PullToRefresh
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberPullToRefreshState
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.useful.Scan
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun SecondPage(
    padding: PaddingValues,
    enableScrollEndHaptic: Boolean,
    enableOverScroll: Boolean,
    isWideScreen: Boolean,
    showTopAppBar: Boolean,
) {
    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    val dropdownOptions = remember { listOf("Option 1", "Option 2", "Option 3", "Option 4") }
    var dropdownSelectedOption by remember { mutableStateOf(0) }
    var ii by remember { mutableStateOf(6) }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(1000)
            ii += 6
            isRefreshing = false
        }
    }

    Scaffold(
        topBar = {
            if (showTopAppBar) {
                if (isWideScreen) {
                    SmallTopAppBar(
                        title = "Dropdowns",
                        scrollBehavior = topAppBarScrollBehavior,
                        defaultWindowInsetsPadding = false
                    )
                } else {
                    TopAppBar(
                        title = "Dropdowns",
                        scrollBehavior = topAppBarScrollBehavior
                    )
                }
            }
        },
        popupHost = {}
    ) { innerPadding ->
        PullToRefresh(
            isRefreshing = isRefreshing,
            onRefresh = { isRefreshing = true },
            pullToRefreshState = pullToRefreshState,
            topAppBarScrollBehavior = if (showTopAppBar) topAppBarScrollBehavior else null,
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 12.dp,
                bottom = if (isWideScreen) {
                    WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                } else 0.dp
            )
        ) {
            LazyColumn(
                modifier = Modifier
                    .then(
                        if (enableScrollEndHaptic) Modifier.scrollEndHaptic() else Modifier
                    )
                    .overScrollVertical(
                        isEnabled = { enableOverScroll }
                    )
                    .fillMaxHeight(),
                contentPadding = PaddingValues(
                    top = innerPadding.calculateTopPadding() + 12.dp,
                    bottom = if (isWideScreen) {
                        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() +
                                padding.calculateBottomPadding() + 12.dp
                    } else padding.calculateBottomPadding() + 12.dp
                ),
                overscrollEffect = null
            ) {
                item {
                    Card(
                        modifier = Modifier.padding(horizontal = 12.dp),
                    ) {
                        for (i in 0 until ii) {
                            key(i) {
                                SuperDropdown(
                                    title = "Dropdown ${i + 1}",
                                    items = dropdownOptions,
                                    selectedIndex = dropdownSelectedOption,
                                    onSelectedIndexChange = { newOption ->
                                        dropdownSelectedOption = newOption
                                    },
                                    leftAction = {
                                        Box(
                                            modifier = Modifier.padding(end = 8.dp)
                                        ) {
                                            Icon(
                                                imageVector = MiuixIcons.Useful.Scan,
                                                contentDescription = "Share",
                                                tint = MiuixTheme.colorScheme.onBackground
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}