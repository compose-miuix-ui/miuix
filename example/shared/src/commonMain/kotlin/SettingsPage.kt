// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import component.CardItem
import component.groupedCardItems
import misc.VersionInfo
import navigation.Route
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.LayerBackdrop
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.nav.transition.navTransitionSource
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberBlurBackdrop

private val NavigationBarDisplayModeOptions = listOf("IconAndText", "IconOnly", "IconWithSelectedLabel")
private val SidebarStyleOptions = listOf("Original", "Glass")
private val FloatingNavigationBarStyleOptions = listOf("Original", "Glass", "iOS-like")
private val FloatingNavigationBarPositionOptions = listOf("Center", "Start", "End")
private val FloatingToolbarPositionOptions =
    listOf("TopStart", "CenterStart", "BottomStart", "TopEnd", "CenterEnd", "BottomEnd", "TopCenter", "BottomCenter")
private val FloatingToolbarOrientationOptions = listOf("Horizontal", "Vertical")
private val FabPositionOptions = listOf("Start", "Center", "End", "EndOverlay")
private val TopAppBarStyleOptions = listOf("Original", "Glass")

@Composable
fun SettingsPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val backdrop = rememberBlurBackdrop()
    val blurActive = backdrop != null
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    Scaffold(
        topBar = {
            BlurredBar(backdrop, blurActive) {
                AdaptiveTopAppBar(
                    title = "Settings",
                    showTopAppBar = appState.showTopAppBar,
                    isWideScreen = isWideScreen,
                    scrollBehavior = topAppBarScrollBehavior,
                    color = barColor,
                    subtitle = "v${VersionInfo.VERSION_NAME} (${VersionInfo.VERSION_CODE})",
                )
            }
        },
    ) { innerPadding ->
        SettingsContent(
            contentPadding = pageContentPadding(innerPadding, padding),
            topAppBarScrollBehavior = topAppBarScrollBehavior,
            backdrop = backdrop,
        )
    }
}

@Composable
private fun SettingsContent(
    contentPadding: PaddingValues,
    topAppBarScrollBehavior: ScrollBehavior,
    backdrop: LayerBackdrop?,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val updateAppState = LocalUpdateAppState.current
    val navigator = LocalNavigator.current
    val lazyListState = rememberLazyListState()
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
            groupedCardItems(
                keyPrefix = "settingsUi",
                outerTopPadding = 12.dp,
                outerBottomPadding = 12.dp,
                items = buildList {
                    add(
                        CardItem("fpsMonitor") {
                            SwitchPreference(
                                title = "Show FPS Monitor",
                                checked = appState.showFPSMonitor,
                                onCheckedChange = { updateAppState { state -> state.copy(showFPSMonitor = it) } },
                            )
                        },
                    )
                    add(
                        CardItem("themeSettings") {
                            ArrowPreference(
                                title = "Theme Settings",
                                onClick = { navigator.push(Route.ThemeSettings) },
                            )
                        },
                    )
                },
            )
            item(key = "settingsLayoutTitle") { SmallTitle("Layout") }
            groupedCardItems(
                keyPrefix = "settingsLayout",
                outerTopPadding = 0.dp,
                outerBottomPadding = 12.dp,
                items = buildList {
                    add(
                        CardItem("showTopAppBar") {
                            SwitchPreference(
                                title = "Show TopAppBar",
                                checked = appState.showTopAppBar,
                                onCheckedChange = { updateAppState { state -> state.copy(showTopAppBar = it) } },
                            )
                        },
                    )
                    if (appState.showTopAppBar) {
                        add(
                            CardItem("topAppBarStyle") {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "TopAppBar Style",
                                    items = TopAppBarStyleOptions,
                                    selectedIndex = appState.topAppBarStyle,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(topAppBarStyle = it) } },
                                )
                            },
                        )
                    }
                    add(
                        CardItem("showNavigationBar") {
                            SwitchPreference(
                                title = if (isWideScreen) "Show NavigationRail" else "Show NavigationBar",
                                checked = appState.showNavigationBar,
                                onCheckedChange = { updateAppState { state -> state.copy(showNavigationBar = it) } },
                            )
                        },
                    )
                    if (appState.showNavigationBar && isWideScreen) {
                        add(
                            CardItem("sidebarStyle") {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "Sidebar Style",
                                    items = SidebarStyleOptions,
                                    selectedIndex = appState.navigationRailStyle,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(navigationRailStyle = it) } },
                                )
                            },
                        )
                    }
                    if (appState.showNavigationBar) {
                        add(
                            CardItem("navigationBadge") {
                                SwitchPreference(
                                    title = "Show Navigation Badge",
                                    checked = appState.showNavigationBarBadge,
                                    onCheckedChange = { updateAppState { state -> state.copy(showNavigationBarBadge = it) } },
                                )
                            },
                        )
                    }
                    if (appState.showNavigationBar && !isWideScreen && !appState.useFloatingNavigationBar) {
                        add(
                            CardItem("navigationBarMode") {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "NavigationBar Mode",
                                    items = NavigationBarDisplayModeOptions,
                                    selectedIndex = appState.navigationBarMode,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(navigationBarMode = it) } },
                                )
                            },
                        )
                    }
                    if (appState.showNavigationBar && !isWideScreen) {
                        add(
                            CardItem("useFloatingNavigationBar") {
                                SwitchPreference(
                                    title = "Use FloatingNavigationBar",
                                    checked = appState.useFloatingNavigationBar,
                                    onCheckedChange = { updateAppState { state -> state.copy(useFloatingNavigationBar = it) } },
                                )
                            },
                        )
                        if (appState.useFloatingNavigationBar) {
                            add(
                                CardItem("floatingNavigationBarStyle") {
                                    GlassDropdownPreference(
                                        backdrop = backdrop,
                                        title = "FloatingNavigationBar Style",
                                        items = FloatingNavigationBarStyleOptions,
                                        selectedIndex = appState.floatingNavigationBarStyle,
                                        onSelectedIndexChange = {
                                            updateAppState { state -> state.copy(floatingNavigationBarStyle = it) }
                                        },
                                    )
                                },
                            )
                            if (appState.floatingNavigationBarStyle == 0) {
                                add(
                                    CardItem("floatingNavigationBarPosition") {
                                        GlassDropdownPreference(
                                            backdrop = backdrop,
                                            title = "FloatingNavigationBar Position",
                                            items = FloatingNavigationBarPositionOptions,
                                            selectedIndex = appState.floatingNavigationBarPosition,
                                            onSelectedIndexChange = {
                                                updateAppState { state -> state.copy(floatingNavigationBarPosition = it) }
                                            },
                                        )
                                    },
                                )
                            }
                        }
                    }
                    add(
                        CardItem("showFloatingToolbar") {
                            SwitchPreference(
                                title = "Show FloatingToolbar",
                                checked = appState.showFloatingToolbar,
                                onCheckedChange = { updateAppState { state -> state.copy(showFloatingToolbar = it) } },
                            )
                        },
                    )
                    if (appState.showFloatingToolbar) {
                        add(
                            CardItem("floatingToolbarPosition") {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "FloatingToolbar Position",
                                    items = FloatingToolbarPositionOptions,
                                    selectedIndex = appState.floatingToolbarPosition,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(floatingToolbarPosition = it) } },
                                )
                            },
                        )
                        add(
                            CardItem("floatingToolbarOrientation") {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "FloatingToolbar Orientation",
                                    items = FloatingToolbarOrientationOptions,
                                    selectedIndex = appState.floatingToolbarOrientation,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(floatingToolbarOrientation = it) } },
                                )
                            },
                        )
                        add(
                            CardItem("floatingToolbarStyle") {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "FloatingToolbar Style",
                                    items = TopAppBarStyleOptions,
                                    selectedIndex = appState.floatingToolbarStyle,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(floatingToolbarStyle = it) } },
                                )
                            },
                        )
                    }
                    add(
                        CardItem("showFloatingActionButton") {
                            SwitchPreference(
                                title = "Show FloatingActionButton",
                                checked = appState.showFloatingActionButton,
                                onCheckedChange = { updateAppState { state -> state.copy(showFloatingActionButton = it) } },
                            )
                        },
                    )
                    if (appState.showFloatingActionButton) {
                        add(
                            CardItem("floatingActionButtonPosition") {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "FloatingActionButton Position",
                                    items = FabPositionOptions,
                                    selectedIndex = appState.floatingActionButtonPosition,
                                    onSelectedIndexChange = {
                                        updateAppState { state -> state.copy(floatingActionButtonPosition = it) }
                                    },
                                )
                            },
                        )
                        add(
                            CardItem("floatingActionButtonStyle") {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "FloatingActionButton Style",
                                    items = TopAppBarStyleOptions,
                                    selectedIndex = appState.floatingActionButtonStyle,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(floatingActionButtonStyle = it) } },
                                )
                            },
                        )
                    }
                },
            )
            item(key = "settingsAboutTitle") { SmallTitle("Other") }
            groupedCardItems(
                keyPrefix = "settingsAbout",
                outerTopPadding = 0.dp,
                outerBottomPadding = 0.dp,
                items = listOf(
                    CardItem("about") {
                        ArrowPreference(
                            title = "About",
                            summary = "About this example App",
                            modifier = Modifier.navTransitionSource(Route.About, containerColor = MiuixTheme.colorScheme.surfaceContainer),
                            onClick = { navigator.push(Route.About) },
                        )
                    },
                ),
            )
            item(key = "settingsBottomSpacer") { Spacer(modifier = Modifier.height(12.dp)) }
        }
        VerticalScrollBar(
            adapter = rememberScrollBarAdapter(lazyListState),
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            trackPadding = contentPadding,
            topAppBarScrollBehavior = topAppBarScrollBehavior.takeIf { appState.showTopAppBar },
        )
    }
}
