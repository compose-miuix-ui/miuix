// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.kyant.shapes.UnevenRoundedRectangle
import kotlinx.coroutines.delay
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.PullToRefresh
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.rememberPullToRefreshState
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.extra.WindowDropdown
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import utils.AdaptiveTopAppBar
import utils.pageContentPadding
import utils.pageScrollModifiers

private val DropdownListTopShape = UnevenRoundedRectangle(topStart = 16.dp, topEnd = 16.dp)
private val DropdownListBottomShape = UnevenRoundedRectangle(bottomStart = 16.dp, bottomEnd = 16.dp)

@Composable
fun DropdownPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    val pullToRefreshState = rememberPullToRefreshState()
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    val dropdownOptions = remember { listOf("Option 1", "Option 2", "Option 3", "Option 4") }
    var dropdownSelectedOption by remember { mutableIntStateOf(0) }
    var dropdownCount by remember { mutableIntStateOf(6) }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            delay(200)
            dropdownCount += 6
            isRefreshing = false
        }
    }

    Scaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = "Dropdown",
                showTopAppBar = appState.showTopAppBar,
                isWideScreen = isWideScreen,
                scrollBehavior = topAppBarScrollBehavior,
            )
        },
    ) { innerPadding ->
        PullToRefresh(
            isRefreshing = isRefreshing,
            onRefresh = { isRefreshing = true },
            pullToRefreshState = pullToRefreshState,
            topAppBarScrollBehavior = if (appState.showTopAppBar) topAppBarScrollBehavior else null,
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 12.dp,
                bottom = if (isWideScreen) {
                    WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                } else {
                    0.dp
                },
            ),
        ) {
            LazyColumn(
                modifier = Modifier.pageScrollModifiers(
                    appState.enableScrollEndHaptic,
                    appState.showTopAppBar,
                    topAppBarScrollBehavior,
                ),
                contentPadding = pageContentPadding(innerPadding, padding, isWideScreen, extraTop = 12.dp),
            ) {
                items(
                    count = dropdownCount,
                    key = { "dropdown_$it" },
                ) { i ->
                    val isFirst = i == 0
                    val isLast = i == dropdownCount - 1
                    val shape = when {
                        isFirst -> DropdownListTopShape
                        isLast -> DropdownListBottomShape
                        else -> RectangleShape
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .clip(shape)
                            .background(colorScheme.surfaceContainer),
                    ) {
                        if (i % 2 == 0) {
                            SuperDropdown(
                                title = "SuperDropdown ${i + 1}",
                                items = dropdownOptions,
                                selectedIndex = dropdownSelectedOption,
                                onSelectedIndexChange = { newOption ->
                                    dropdownSelectedOption = newOption
                                },
                            )
                        } else {
                            WindowDropdown(
                                title = "WindowDropdown ${i + 1}",
                                items = dropdownOptions,
                                selectedIndex = dropdownSelectedOption,
                                onSelectedIndexChange = { newOption ->
                                    dropdownSelectedOption = newOption
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}
