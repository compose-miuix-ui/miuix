// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
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
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.isRenderEffectSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.LocalDismissState
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import top.yukonga.miuix.kmp.window.WindowListPopup
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import kotlin.random.Random

private val TopBarPopupItems = listOf("Window 1", "Window 2", "Window 3")

@Composable
fun NavTestPage(
    index: Int,
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val blurSupported = isRenderEffectSupported()
    val surfaceColor = MiuixTheme.colorScheme.surface
    val backdrop = if (blurSupported) {
        rememberLayerBackdrop {
            drawRect(surfaceColor)
            drawContent()
        }
    } else {
        null
    }
    val blurActive = appState.enableBlur && blurSupported
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current

    Scaffold(
        topBar = {
            BlurredBar(backdrop, blurActive) {
                AdaptiveTopAppBar(
                    title = "Navigate Test $index",
                    showTopAppBar = appState.showTopAppBar,
                    isWideScreen = isWideScreen,
                    scrollBehavior = topAppBarScrollBehavior,
                    color = barColor,
                    navigationIcon = {
                        BackNavigationIcon(
                            onClick = { navigator.pop() },
                        )
                    },
                    actions = {
                        TopBarActions()
                    },
                )
            }
        },
    ) { innerPadding ->
        val lazyListState = rememberLazyListState()
        val contentPadding = pageContentPadding(
            innerPadding,
            padding,
            isWideScreen,
            extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
            extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
        )
        Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.pageScrollModifiers(
                    appState.enableScrollEndHaptic,
                    appState.showTopAppBar,
                    topAppBarScrollBehavior,
                ),
                contentPadding = contentPadding,
            ) {
                item(key = "nav_push") {
                    Card(
                        modifier = Modifier
                            .padding(all = 12.dp),
                    ) {
                        val navigator = LocalNavigator.current
                        ArrowPreference(
                            title = "Push another Navigate Test Page",
                            onClick = { navigator.push(Route.NavTest(Random.nextLong().toString())) },
                        )
                    }
                }
                item(key = "nav_layout") {
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                    ) {
                        ArrowPreference(
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
                        ArrowPreference(
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
                        ArrowPreference(
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
                        ArrowPreference(
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
                        ArrowPreference(
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
                        ArrowPreference(
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
                        ArrowPreference(
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
                        ArrowPreference(
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
                        ArrowPreference(
                            title = "Long Title Long Title Long Title Long Title Long Title Long Title Long Title Long Title",
                            summary = "Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary Long Summary",
                            endActions = {
                                Text(text = "Long End Long End Long End Long End", textAlign = TextAlign.End)
                            },
                            enabled = true,
                        )
                        ArrowPreference(
                            title = "Long Title Long Title Long Title Long Title",
                            summary = "Summary",
                            endActions = {
                                Text(text = "Long End Long End Long End Long End", textAlign = TextAlign.End)
                            },
                            enabled = true,
                        )
                        ArrowPreference(
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
            VerticalScrollBar(
                adapter = rememberScrollBarAdapter(lazyListState),
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                trackPadding = contentPadding,
            )
        }
    }
}

@Composable
fun TopBarActions() {
    val showTopPopup = remember { mutableStateOf(false) }
    val topPopupHoldDown = remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val hapticFeedback = LocalHapticFeedback.current
    IconButton(
        onClick = {
            showTopPopup.value = true
            topPopupHoldDown.value = true
        },
        holdDownState = topPopupHoldDown.value,
    ) {
        Icon(
            imageVector = MiuixIcons.Edit,
            contentDescription = "WindowListPopup",
            tint = colorScheme.onBackground,
        )
    }
    WindowListPopup(
        show = showTopPopup.value,
        popupPositionProvider = ListPopupDefaults.ContextMenuPositionProvider,
        alignment = PopupPositionProvider.Align.TopEnd,
        onDismissRequest = {
            showTopPopup.value = false
        },
        onDismissFinished = {
            topPopupHoldDown.value = false
        },
        content = {
            val state = LocalDismissState.current
            ListPopupColumn {
                TopBarPopupItems.forEachIndexed { index, string ->
                    key(index) {
                        DropdownImpl(
                            text = string,
                            optionSize = TopBarPopupItems.size,
                            isSelected = selectedIndex == index,
                            index = index,
                            onSelectedIndexChange = { selectedIdx ->
                                hapticFeedback.performHapticFeedback(HapticFeedbackType.Confirm)
                                selectedIndex = selectedIdx
                                state?.invoke()
                            },
                        )
                    }
                }
            }
        },
    )
}
