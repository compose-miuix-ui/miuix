// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import navigation3.Route
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.theme.ThemeColorSpec
import top.yukonga.miuix.kmp.theme.ThemePaletteStyle
import utils.AdaptiveTopAppBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import kotlin.random.Random

@Composable
fun SettingsPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    Scaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = "Settings",
                showTopAppBar = appState.showTopAppBar,
                isWideScreen = isWideScreen,
                scrollBehavior = topAppBarScrollBehavior,
            )
        },
    ) { innerPadding ->
        SettingsContent(
            padding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding(),
            ),
            topAppBarScrollBehavior = topAppBarScrollBehavior,
        )
    }
}

@Composable
private fun SettingsContent(
    padding: PaddingValues,
    topAppBarScrollBehavior: ScrollBehavior,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val updateAppState = LocalUpdateAppState.current
    val navigator = LocalNavigator.current
    val navigationBarDisplayModeOptions = remember { listOf("IconAndText", "IconOnly", "TextOnly", "IconWithSelectedLabel") }
    val navigationRailDisplayModeOptions = remember { listOf("IconAndText", "IconOnly", "TextOnly", "IconWithSelectedLabel") }
    val floatingNavigationBarDisplayModeOptions = remember { listOf("IconAndText", "IconOnly", "TextOnly") }
    val floatingNavigationBarPositionOptions = remember { listOf("Center", "Start", "End") }
    val floatingToolbarPositionOptions =
        remember { listOf("TopStart", "CenterStart", "BottomStart", "TopEnd", "CenterEnd", "BottomEnd", "TopCenter", "BottomCenter") }
    val floatingToolbarOrientationOptions = remember { listOf("Horizontal", "Vertical") }
    val fabPositionOptions = remember { listOf("Start", "Center", "End", "EndOverlay") }
    val colorModeOptions = remember { listOf("System", "Light", "Dark", "MonetSystem", "MonetLight", "MonetDark") }
    val paletteStyleOptions = remember { ThemePaletteStyle.entries.map { it.name } }
    val colorSpecOptions = remember { ThemeColorSpec.entries.map { it.name } }
    val keyColorOptions = remember { listOf("Default") + ui.KeyColors.map { it.first } }

    LazyColumn(
        modifier = Modifier.pageScrollModifiers(appState.enableScrollEndHaptic, appState.enableOverScroll, appState.showTopAppBar, topAppBarScrollBehavior),
        contentPadding = pageContentPadding(padding, padding, isWideScreen),
        overscrollEffect = null,
    ) {
        item(key = "settings") {
            Card(
                modifier = Modifier.padding(12.dp),
            ) {
                SuperSwitch(
                    title = "Show FPS Monitor",
                    checked = appState.showFPSMonitor,
                    onCheckedChange = { updateAppState { state -> state.copy(showFPSMonitor = it) } },
                )
                SuperSwitch(
                    title = "Show TopAppBar",
                    checked = appState.showTopAppBar,
                    onCheckedChange = { updateAppState { state -> state.copy(showTopAppBar = it) } },
                )
                SuperSwitch(
                    title = if (isWideScreen) "Show NavigationRail" else "Show NavigationBar",
                    checked = appState.showNavigationBar,
                    onCheckedChange = { updateAppState { state -> state.copy(showNavigationBar = it) } },
                )
                AnimatedVisibility(visible = appState.showNavigationBar && !isWideScreen && !appState.useFloatingNavigationBar) {
                    SuperDropdown(
                        title = "NavigationBar Mode",
                        items = navigationBarDisplayModeOptions,
                        selectedIndex = appState.navigationBarMode,
                        onSelectedIndexChange = { updateAppState { state -> state.copy(navigationBarMode = it) } },
                    )
                }
                AnimatedVisibility(visible = appState.showNavigationBar && isWideScreen) {
                    SuperDropdown(
                        title = "NavigationRail Mode",
                        items = navigationRailDisplayModeOptions,
                        selectedIndex = appState.navigationRailMode,
                        onSelectedIndexChange = { updateAppState { state -> state.copy(navigationRailMode = it) } },
                    )
                }
                AnimatedVisibility(visible = appState.showNavigationBar && !isWideScreen) {
                    Column {
                        SuperSwitch(
                            title = "Use FloatingNavigationBar",
                            checked = appState.useFloatingNavigationBar,
                            onCheckedChange = { updateAppState { state -> state.copy(useFloatingNavigationBar = it) } },
                        )
                        AnimatedVisibility(visible = appState.useFloatingNavigationBar) {
                            Column {
                                SuperDropdown(
                                    title = "FloatingNavigationBar Mode",
                                    items = floatingNavigationBarDisplayModeOptions,
                                    selectedIndex = appState.floatingNavigationBarMode,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(floatingNavigationBarMode = it) } },
                                )
                                SuperDropdown(
                                    title = "FloatingNavigationBar Position",
                                    items = floatingNavigationBarPositionOptions,
                                    selectedIndex = appState.floatingNavigationBarPosition,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(floatingNavigationBarPosition = it) } },
                                )
                            }
                        }
                    }
                }
                SuperSwitch(
                    title = "Show FloatingToolbar",
                    checked = appState.showFloatingToolbar,
                    onCheckedChange = { updateAppState { state -> state.copy(showFloatingToolbar = it) } },
                )
                AnimatedVisibility(visible = appState.showFloatingToolbar) {
                    Column {
                        SuperDropdown(
                            title = "FloatingToolbar Position",
                            items = floatingToolbarPositionOptions,
                            selectedIndex = appState.floatingToolbarPosition,
                            onSelectedIndexChange = { updateAppState { state -> state.copy(floatingToolbarPosition = it) } },
                        )
                        SuperDropdown(
                            title = "FloatingToolbar Orientation",
                            items = floatingToolbarOrientationOptions,
                            selectedIndex = appState.floatingToolbarOrientation,
                            onSelectedIndexChange = { updateAppState { state -> state.copy(floatingToolbarOrientation = it) } },
                        )
                    }
                }
                SuperSwitch(
                    title = "Show FloatingActionButton",
                    checked = appState.showFloatingActionButton,
                    onCheckedChange = { updateAppState { state -> state.copy(showFloatingActionButton = it) } },
                )
                AnimatedVisibility(visible = appState.showFloatingActionButton) {
                    SuperDropdown(
                        title = "FloatingActionButton Position",
                        items = fabPositionOptions,
                        selectedIndex = appState.floatingActionButtonPosition,
                        onSelectedIndexChange = { updateAppState { state -> state.copy(floatingActionButtonPosition = it) } },
                    )
                }
                SuperSwitch(
                    title = "Enable Scroll End Haptic",
                    checked = appState.enableScrollEndHaptic,
                    onCheckedChange = { updateAppState { state -> state.copy(enableScrollEndHaptic = it) } },
                )
                SuperSwitch(
                    title = "Enable Page User Scroll",
                    checked = appState.enablePageUserScroll,
                    onCheckedChange = { updateAppState { state -> state.copy(enablePageUserScroll = it) } },
                )
                SuperDropdown(
                    title = "Color Mode",
                    items = colorModeOptions,
                    selectedIndex = appState.colorMode,
                    onSelectedIndexChange = { updateAppState { state -> state.copy(colorMode = it) } },
                )
                AnimatedVisibility(visible = appState.colorMode in 3..5) {
                    SuperDropdown(
                        title = "Key Color",
                        items = keyColorOptions,
                        selectedIndex = appState.seedIndex,
                        onSelectedIndexChange = { updateAppState { state -> state.copy(seedIndex = it) } },
                    )
                }
                AnimatedVisibility(visible = appState.colorMode in 3..5 && appState.seedIndex > 0) {
                    Column {
                        SuperDropdown(
                            title = "Palette Style",
                            items = paletteStyleOptions,
                            selectedIndex = appState.paletteStyle,
                            onSelectedIndexChange = { updateAppState { state -> state.copy(paletteStyle = it) } },
                        )
                        SuperDropdown(
                            title = "Color Spec",
                            items = colorSpecOptions,
                            selectedIndex = appState.colorSpec,
                            onSelectedIndexChange = { updateAppState { state -> state.copy(colorSpec = it) } },
                        )
                    }
                }
            }
            Card(
                modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp),
            ) {
                SuperSwitch(
                    title = "Enable Corner Clip",
                    summary = "Clip the top scene with rounded corners during transitions",
                    checked = appState.enableCornerClip,
                    onCheckedChange = { updateAppState { state -> state.copy(enableCornerClip = it) } },
                )
                SuperSwitch(
                    title = "Enable Dim",
                    summary = "Dim the scene behind during transitions",
                    checked = appState.enableDim,
                    onCheckedChange = { updateAppState { state -> state.copy(enableDim = it) } },
                )
                SuperSwitch(
                    title = "Block Input During Transition",
                    summary = "Block touch input on the non-target scene",
                    checked = appState.blockInputDuringTransition,
                    onCheckedChange = { updateAppState { state -> state.copy(blockInputDuringTransition = it) } },
                )
                SuperSwitch(
                    title = "Pop Follows Swipe Edge",
                    summary = "Pop animation direction follows the finger swipe edge",
                    checked = appState.popDirectionFollowsSwipeEdge,
                    onCheckedChange = { updateAppState { state -> state.copy(popDirectionFollowsSwipeEdge = it) } },
                )
            }
            Card(
                modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp),
            ) {
                SuperArrow(
                    title = "NavTest",
                    summary = "Navigate to a NavTest Page",
                    onClick = { navigator.push(Route.NavTest(Random.nextLong().toString())) },
                )
                SuperArrow(
                    title = "Multi-Scaffold Test",
                    summary = "Test popup positioning with side-by-side Scaffolds",
                    onClick = { navigator.push(Route.MultiScaffoldTest) },
                )
            }
            Card(
                modifier = Modifier.padding(horizontal = 12.dp),
            ) {
                SuperArrow(
                    title = "About",
                    summary = "About this example App",
                    onClick = { navigator.push(Route.About) },
                )
            }
        }
    }
}
