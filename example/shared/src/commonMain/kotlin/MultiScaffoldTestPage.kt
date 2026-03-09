// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic
import utils.AdaptiveTopAppBar

@Composable
fun MultiScaffoldTestPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current

    Scaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = "Multi-Scaffold Test",
                showTopAppBar = appState.showTopAppBar,
                isWideScreen = isWideScreen,
                scrollBehavior = topAppBarScrollBehavior,
                navigationIcon = {
                    BackNavigationIcon(
                        modifier = Modifier.padding(start = 16.dp),
                        onClick = { navigator.pop() },
                    )
                },
            )
        },
    ) { innerPadding ->
        val dropdownOptions = remember { listOf("A", "B", "C") }
        val topLeftSelected = remember { mutableIntStateOf(0) }
        val topRightSelected = remember { mutableIntStateOf(0) }
        val bottomLeftSelected = remember { mutableIntStateOf(0) }
        val bottomRightSelected = remember { mutableIntStateOf(0) }

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(if (appState.enableScrollEndHaptic) Modifier.scrollEndHaptic() else Modifier)
                .overScrollVertical(isEnabled = { appState.enableOverScroll })
                .then(if (appState.showTopAppBar) Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection) else Modifier)
                .verticalScroll(scrollState)
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
                    end = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
                    bottom = if (isWideScreen) {
                        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + padding.calculateBottomPadding() + 12.dp
                    } else {
                        padding.calculateBottomPadding() + 12.dp
                    },
                ),
        ) {
            Row(
                modifier = Modifier
                    .weight(1f),
            ) {
                Scaffold(
                    modifier = Modifier
                        .weight(1f)
                        .background(MiuixTheme.colorScheme.surfaceVariant),
                ) {
                    Column {
                        SmallTitle(text = "Top Left")
                        Card(
                            modifier = Modifier.padding(horizontal = 12.dp),
                        ) {
                            SuperDropdown(
                                title = "Dropdown",
                                items = dropdownOptions,
                                selectedIndex = topLeftSelected.intValue,
                                onSelectedIndexChange = { index -> topLeftSelected.intValue = index },
                                renderInRootScaffold = false,
                            )
                        }
                    }
                }
                Scaffold(
                    modifier = Modifier
                        .weight(1f)
                        .background(MiuixTheme.colorScheme.surfaceVariant),
                ) {
                    Column {
                        SmallTitle(text = "Top Right")
                        Card(
                            modifier = Modifier.padding(horizontal = 12.dp),
                        ) {
                            SuperDropdown(
                                title = "Dropdown",
                                items = dropdownOptions,
                                selectedIndex = topRightSelected.intValue,
                                onSelectedIndexChange = { index -> topRightSelected.intValue = index },
                                renderInRootScaffold = false,
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .weight(1f),
            ) {
                Scaffold(
                    modifier = Modifier
                        .weight(1f)
                        .background(MiuixTheme.colorScheme.surfaceVariant),
                ) {
                    Column {
                        SmallTitle(text = "Bottom Left")
                        Card(
                            modifier = Modifier.padding(horizontal = 12.dp),
                        ) {
                            SuperDropdown(
                                title = "Dropdown",
                                items = dropdownOptions,
                                selectedIndex = bottomLeftSelected.intValue,
                                onSelectedIndexChange = { index -> bottomLeftSelected.intValue = index },
                                renderInRootScaffold = false,
                            )
                        }
                    }
                }
                Scaffold(
                    modifier = Modifier
                        .weight(1f)
                        .background(MiuixTheme.colorScheme.surfaceVariant),
                ) {
                    Column {
                        SmallTitle(text = "Bottom Right")
                        Card(
                            modifier = Modifier.padding(horizontal = 12.dp),
                        ) {
                            SuperDropdown(
                                title = "Dropdown",
                                items = dropdownOptions,
                                selectedIndex = bottomRightSelected.intValue,
                                onSelectedIndexChange = { index -> bottomRightSelected.intValue = index },
                                renderInRootScaffold = false,
                            )
                        }
                    }
                }
            }
        }
    }
}
