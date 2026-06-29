// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import navigation.Route
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import kotlin.random.Random

@Composable
fun NavTestPage(
    index: Int,
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val blurSupported = isRuntimeShaderSupported()
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
                )
            }
        },
    ) { innerPadding ->
        val lazyListState = rememberLazyListState()
        val contentPadding = pageContentPadding(
            innerPadding,
            padding,
            true,
            extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
            extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
            extraBottom = 12.dp,
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
                item(key = "nav_continuous") {
                    val navigator = LocalNavigator.current
                    Column {
                        SmallTitle(text = "Continuous depth")
                        Card(modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp)) {
                            ArrowPreference(
                                title = "Push another Navigation page",
                                summary = "Single push (animatedTop N -> N+1)",
                                onClick = { navigator.push(Route.Navigation(Random.nextLong().toString())) },
                            )
                            ArrowPreference(
                                title = "Push three pages at once",
                                summary = "Continuous multi-push (N -> N+3, one shared spring)",
                                onClick = {
                                    navigator.push(Route.Navigation(Random.nextLong().toString()))
                                    navigator.push(Route.Navigation(Random.nextLong().toString()))
                                    navigator.push(Route.Navigation(Random.nextLong().toString()))
                                },
                            )
                            ArrowPreference(
                                title = "Pop all Navigation pages",
                                summary = "Continuous multi-pop back to the entry",
                                onClick = { navigator.popUntil { it !is Route.Navigation } },
                            )
                        }
                    }
                }
                item(key = "nav_gesture") {
                    Column {
                        SmallTitle(text = "Gesture back")
                        Card(modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp)) {
                            BasicComponent(
                                title = "Swipe to go back",
                                summary = "Turn on \"Enable Swipe Back\" in Settings, then swipe a pushed page to " +
                                    "pop it. Stack several pages with continuous push above and drag back through " +
                                    "them; the gesture drives the same animatedTop spring.",
                            )
                        }
                    }
                }
                item(key = "nav_layout") {
                    SmallTitle(text = "Layout test")
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp),
                    ) {
                        ArrowPreference(
                            title = "Long Long Long Long Long Title",
                            summary = "Summary",
                            startAction = {
                                Text(text = "Start")
                            },
                            endActions = {
                                Text(text = "End1", textAlign = TextAlign.End)
                                Spacer(Modifier.width(8.dp))
                                Text(text = "End2", textAlign = TextAlign.End)
                            },
                            onClick = { /* Do nothing */ },
                            enabled = true,
                        )
                        ArrowPreference(
                            title = "Title",
                            summary = "Long Long Long Long Long Summary",
                            startAction = {
                                Text(text = "Start")
                            },
                            endActions = {
                                Text(text = "End1", textAlign = TextAlign.End)
                                Spacer(Modifier.width(8.dp))
                                Text(text = "End2", textAlign = TextAlign.End)
                            },
                            onClick = { /* Do nothing */ },
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
                                    text = "Long Long Long Long Long End",
                                    textAlign = TextAlign.End,
                                )
                            },
                            onClick = { /* Do nothing */ },
                            enabled = true,
                        )
                        ArrowPreference(
                            title = "Long Long Long Long Long Title",
                            summary = "Summary",
                            startAction = {
                                Text(text = "Start")
                            },
                            endActions = {
                                Text(
                                    text = "Long Long Long Long Long End",
                                    textAlign = TextAlign.End,
                                )
                            },
                            onClick = { /* Do nothing */ },
                            enabled = true,
                        )
                        ArrowPreference(
                            title = "Title",
                            summary = "Long Long Long Long Long Summary",
                            endActions = {
                                Text(
                                    text = "Long Long Long Long Long End",
                                    textAlign = TextAlign.End,
                                )
                            },
                            onClick = { /* Do nothing */ },
                            enabled = true,
                        )
                        ArrowPreference(
                            title = "Long Long Long Long Long Title",
                            summary = "Summary",
                            endActions = {
                                Text(text = "Long Long Long Long Long End", textAlign = TextAlign.End)
                            },
                            onClick = { /* Do nothing */ },
                            enabled = true,
                        )
                        ArrowPreference(
                            title = "Title",
                            summary = "Long Long Long Long Long Summary",
                            endActions = {
                                Text(text = "Long Long Long Long Long End", textAlign = TextAlign.End)
                            },
                            onClick = { /* Do nothing */ },
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
