// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import navigation3.Route
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.DropdownImpl
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.extra.LocalWindowListPopupState
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.WindowListPopup
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic
import kotlin.random.Random

@Composable
fun NavTestPage(
    index: Int,
    padding: PaddingValues,
    showTopAppBar: Boolean,
    isWideScreen: Boolean,
    enableScrollEndHaptic: Boolean,
    enableOverScroll: Boolean,
) {
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current
    val rememberIndex by remember { mutableIntStateOf(index) }
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        topBar = {
            if (showTopAppBar) {
                if (isWideScreen) {
                    SmallTopAppBar(
                        title = "NavTest $rememberIndex",
                        scrollBehavior = topAppBarScrollBehavior,
                        navigationIcon = {
                            BackNavigationIcon(
                                modifier = Modifier.padding(start = 16.dp),
                                onClick = { navigator.pop() },
                            )
                        },
                        actions = {
                            TopBarActions()
                        },
                    )
                } else {
                    TopAppBar(
                        title = "NavTest $rememberIndex",
                        scrollBehavior = topAppBarScrollBehavior,
                        navigationIcon = {
                            BackNavigationIcon(
                                modifier = Modifier.padding(start = 16.dp),
                                onClick = { navigator.pop() },
                            )
                        },
                        actions = {
                            TopBarActions()
                        },
                    )
                }
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .then(if (enableScrollEndHaptic) Modifier.scrollEndHaptic() else Modifier)
                .overScrollVertical(isEnabled = { enableOverScroll })
                .then(if (showTopAppBar) Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection) else Modifier)
                .fillMaxHeight(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                start = innerPadding.calculateStartPadding(layoutDirection) + padding.calculateStartPadding(layoutDirection),
                end = innerPadding.calculateEndPadding(layoutDirection) + padding.calculateEndPadding(layoutDirection),
                bottom = if (isWideScreen) {
                    WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + padding.calculateBottomPadding() + 12.dp
                } else {
                    padding.calculateBottomPadding() + 12.dp
                },
            ),
        ) {
            item {
                Card(
                    modifier = Modifier
                        .padding(all = 12.dp),
                ) {
                    val navigator = LocalNavigator.current
                    SuperArrow(
                        title = "Push another NavTest Page",
                        onClick = { navigator.push(Route.NavTest(Random.nextLong().toString())) },
                    )
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                ) {
                    SuperArrow(
                        title = "Long Title Long Title Long Title Long Title Long Title Long Title Long Title Long Title",
                        summary = "Summary",
                        startAction = {
                            Text(text = "Start")
                        },
                        endActions = {
                            Text(text = "End1", textAlign = TextAlign.End)
                            Spacer(Modifier.width(8.dp))
                            Text(text = "End2", textAlign = TextAlign.End)
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Title",
                        summary = "Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary",
                        startAction = {
                            Text(text = "Start")
                        },
                        endActions = {
                            Text(text = "End1", textAlign = TextAlign.End)
                            Spacer(Modifier.width(8.dp))
                            Text(text = "End2", textAlign = TextAlign.End)
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Title",
                        summary = "Summary",
                        startAction = {
                            Text(text = "Long Start Long Start Long Start Long Start Long Start Long Start Long Start Long Start")
                        },
                        endActions = {
                            Text(text = "End1", textAlign = TextAlign.End)
                            Spacer(Modifier.width(8.dp))
                            Text(text = "End2", textAlign = TextAlign.End)
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Title",
                        summary = "Summary",
                        startAction = {
                            Text(text = "Start")
                        },
                        endActions = {
                            Text(
                                text = "Long End Long End Long End Long End Long End Long End Long End Long End",
                                textAlign = TextAlign.End,
                            )
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Long Title Long Title Long Title Long Title Long Title Long Title Long Title Long Title",
                        summary = "Summary",
                        startAction = {
                            Text(text = "Long Start Long Start Long Start Long Start Long Start Long Start Long Start Long Start")
                        },
                        endActions = {
                            Text(text = "End", textAlign = TextAlign.End)
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Long Title Long Title Long Title Long Title Long Title Long Title Long Title Long Title",
                        summary = "Summary",
                        startAction = {
                            Text(text = "Start")
                        },
                        endActions = {
                            Text(
                                text = "Long End Long End Long End Long End Long End Long End Long End Long End",
                                textAlign = TextAlign.End,
                            )
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Title",
                        summary = "Summary",
                        startAction = {
                            Text(text = "Long Start Long Start Long Start Long Start Long Start Long Start Long Start Long Start")
                        },
                        endActions = {
                            Text(
                                text = "Long End Long End Long End Long End Long End Long End Long End Long End",
                                textAlign = TextAlign.End,
                            )
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Title",
                        summary = "Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary",
                        endActions = {
                            Text(
                                text = "Long End Long End Long End Long End Long End Long End Long End Long End",
                                textAlign = TextAlign.End,
                            )
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Long Title Long Title Long Title Long Title Long Title Long Title Long Title Long Title",
                        summary = "Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary",
                        endActions = {
                            Text(text = "Long End Long End Long End Long End", textAlign = TextAlign.End)
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Long Title Long Title Long Title Long Title",
                        summary = "Summary",
                        endActions = {
                            Text(text = "Long End Long End Long End Long End", textAlign = TextAlign.End)
                        },
                        enabled = true,
                    )
                    SuperArrow(
                        title = "Title",
                        summary = "Long Summary Long Summary Long Summary Long Summary",
                        endActions = {
                            Text(text = "Long End Long End", textAlign = TextAlign.End)
                        },
                        enabled = true,
                    )
                }
            }
        }
    }
}

@Composable
fun TopBarActions() {
    val showTopPopup = remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val hapticFeedback = LocalHapticFeedback.current
    IconButton(
        modifier = Modifier.padding(end = 16.dp),
        onClick = { showTopPopup.value = true },
        holdDownState = showTopPopup.value,
    ) {
        Icon(
            imageVector = MiuixIcons.Edit,
            contentDescription = "WindowListPopup",
            tint = colorScheme.onBackground,
        )
    }
    WindowListPopup(
        show = showTopPopup,
        popupPositionProvider = ListPopupDefaults.ContextMenuPositionProvider,
        alignment = PopupPositionProvider.Align.TopEnd,
        onDismissRequest = {
            showTopPopup.value = false
        },
    ) {
        val state = LocalWindowListPopupState.current
        val items = listOf("Window 1", "Window 2", "Window 3")
        ListPopupColumn {
            items.forEachIndexed { index, string ->
                key(index) {
                    DropdownImpl(
                        text = string,
                        optionSize = items.size,
                        isSelected = selectedIndex == index,
                        onSelectedIndexChange = { selectedIdx ->
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                            selectedIndex = selectedIdx
                            state.invoke()
                        },
                        index = index,
                    )
                }
            }
        }
    }
}
