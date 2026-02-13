// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import navigation3.Route
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic
import kotlin.random.Random

@Composable
fun SettingsPage(
    padding: PaddingValues,
    showFPSMonitor: Boolean,
    onShowFPSMonitorChange: (Boolean) -> Unit,
    showTopAppBar: Boolean,
    onShowTopAppBarChange: (Boolean) -> Unit,
    showNavigationBar: Boolean,
    onShowNavigationBarChange: (Boolean) -> Unit,
    navigationBarMode: Int,
    onNavigationBarModeChange: (Int) -> Unit,
    navigationRailMode: Int,
    onNavigationRailModeChange: (Int) -> Unit,
    useFloatingNavigationBar: Boolean,
    onUseFloatingNavigationBarChange: (Boolean) -> Unit,
    floatingNavigationBarMode: Int,
    onFloatingNavigationBarModeChange: (Int) -> Unit,
    floatingNavigationBarPosition: Int,
    onFloatingNavigationBarPositionChange: (Int) -> Unit,
    showFloatingToolbar: Boolean,
    onShowFloatingToolbarChange: (Boolean) -> Unit,
    floatingToolbarPosition: Int,
    onFloatingToolbarPositionChange: (Int) -> Unit,
    floatingToolbarOrientation: Int,
    onFloatingToolbarOrientationChange: (Int) -> Unit,
    showFloatingActionButton: Boolean,
    onShowFloatingActionButtonChange: (Boolean) -> Unit,
    fabPosition: Int,
    onFabPositionChange: (Int) -> Unit,
    enablePageUserScroll: Boolean,
    onEnablePageUserScrollChange: (Boolean) -> Unit,
    enableScrollEndHaptic: Boolean,
    onScrollEndHapticChange: (Boolean) -> Unit,
    enableOverScroll: Boolean,
    isWideScreen: Boolean,
    colorMode: MutableState<Int>,
    seedIndex: MutableState<Int>,
) {
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    Scaffold(
        topBar = {
            if (showTopAppBar) {
                if (isWideScreen) {
                    SmallTopAppBar(
                        title = "Settings",
                        scrollBehavior = topAppBarScrollBehavior,
                        defaultWindowInsetsPadding = false,
                    )
                } else {
                    TopAppBar(
                        title = "Settings",
                        scrollBehavior = topAppBarScrollBehavior,
                    )
                }
            }
        },
        popupHost = {},
    ) { innerPadding ->
        SettingsContent(
            padding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding(),
            ),
            showFPSMonitor = showFPSMonitor,
            onShowFPSMonitorChange = onShowFPSMonitorChange,
            showTopAppBar = showTopAppBar,
            onShowTopAppBarChange = onShowTopAppBarChange,
            showNavigationBar = showNavigationBar,
            onShowNavigationBarChange = onShowNavigationBarChange,
            navigationBarMode = navigationBarMode,
            onNavigationBarModeChange = onNavigationBarModeChange,
            navigationRailMode = navigationRailMode,
            onNavigationRailModeChange = onNavigationRailModeChange,
            useFloatingNavigationBar = useFloatingNavigationBar,
            onUseFloatingNavigationBarChange = onUseFloatingNavigationBarChange,
            floatingNavigationBarMode = floatingNavigationBarMode,
            onFloatingNavigationBarModeChange = onFloatingNavigationBarModeChange,
            floatingNavigationBarPosition = floatingNavigationBarPosition,
            onFloatingNavigationBarPositionChange = onFloatingNavigationBarPositionChange,
            showFloatingToolbar = showFloatingToolbar,
            onShowFloatingToolbarChange = onShowFloatingToolbarChange,
            floatingToolbarPosition = floatingToolbarPosition,
            onFloatingToolbarPositionChange = onFloatingToolbarPositionChange,
            floatingToolbarOrientation = floatingToolbarOrientation,
            onFloatingToolbarOrientationChange = onFloatingToolbarOrientationChange,
            showFloatingActionButton = showFloatingActionButton,
            onShowFloatingActionButtonChange = onShowFloatingActionButtonChange,
            fabPosition = fabPosition,
            onFabPositionChange = onFabPositionChange,
            enablePageUserScroll = enablePageUserScroll,
            onEnablePageUserScrollChange = onEnablePageUserScrollChange,
            topAppBarScrollBehavior = topAppBarScrollBehavior,
            enableScrollEndHaptic = enableScrollEndHaptic,
            onScrollEndHapticChange = onScrollEndHapticChange,
            enableOverScroll = enableOverScroll,
            colorMode = colorMode,
            seedIndex = seedIndex,
            isWideScreen = isWideScreen,
        )
    }
}

@Composable
fun SettingsContent(
    padding: PaddingValues,
    showFPSMonitor: Boolean,
    onShowFPSMonitorChange: (Boolean) -> Unit,
    showTopAppBar: Boolean,
    onShowTopAppBarChange: (Boolean) -> Unit,
    showNavigationBar: Boolean,
    onShowNavigationBarChange: (Boolean) -> Unit,
    navigationBarMode: Int,
    onNavigationBarModeChange: (Int) -> Unit,
    navigationRailMode: Int,
    onNavigationRailModeChange: (Int) -> Unit,
    useFloatingNavigationBar: Boolean,
    onUseFloatingNavigationBarChange: (Boolean) -> Unit,
    floatingNavigationBarMode: Int,
    onFloatingNavigationBarModeChange: (Int) -> Unit,
    floatingNavigationBarPosition: Int,
    onFloatingNavigationBarPositionChange: (Int) -> Unit,
    showFloatingToolbar: Boolean,
    onShowFloatingToolbarChange: (Boolean) -> Unit,
    floatingToolbarPosition: Int,
    onFloatingToolbarPositionChange: (Int) -> Unit,
    floatingToolbarOrientation: Int,
    onFloatingToolbarOrientationChange: (Int) -> Unit,
    showFloatingActionButton: Boolean,
    onShowFloatingActionButtonChange: (Boolean) -> Unit,
    fabPosition: Int,
    onFabPositionChange: (Int) -> Unit,
    enablePageUserScroll: Boolean,
    onEnablePageUserScrollChange: (Boolean) -> Unit,
    topAppBarScrollBehavior: ScrollBehavior,
    enableScrollEndHaptic: Boolean,
    onScrollEndHapticChange: (Boolean) -> Unit,
    enableOverScroll: Boolean,
    colorMode: MutableState<Int>,
    seedIndex: MutableState<Int>,
    isWideScreen: Boolean,
) {
    val navigator = LocalNavigator.current
    val navigationDisplayModeOptions = remember { listOf("IconAndText", "IconOnly", "TextOnly", "IconWithSelectedLabel") }
    val floatingNavigationBarPositionOptions = remember { listOf("Center", "Start", "End") }
    val floatingToolbarPositionOptions =
        remember { listOf("TopStart", "CenterStart", "BottomStart", "TopEnd", "CenterEnd", "BottomEnd", "TopCenter", "BottomCenter") }
    val floatingToolbarOrientationOptions = remember { listOf("Horizontal", "Vertical") }
    val fabPositionOptions = remember { listOf("Start", "Center", "End", "EndOverlay") }
    val colorModeOptions = remember { listOf("System", "Light", "Dark", "MonetSystem", "MonetLight", "MonetDark") }
    val keyColorOptions = remember { listOf("Default") + ui.KeyColors.map { it.first } }

    LazyColumn(
        modifier = Modifier
            .then(if (enableScrollEndHaptic) Modifier.scrollEndHaptic() else Modifier)
            .overScrollVertical(isEnabled = { enableOverScroll })
            .then(if (showTopAppBar) Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection) else Modifier)
            .fillMaxHeight(),
        contentPadding = PaddingValues(
            top = padding.calculateTopPadding(),
            bottom = if (isWideScreen) {
                WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + padding.calculateBottomPadding() + 12.dp
            } else {
                padding.calculateBottomPadding() + 12.dp
            },
        ),
        overscrollEffect = null,
    ) {
        item {
            Card(
                modifier = Modifier.padding(12.dp),
            ) {
                SuperSwitch(
                    title = "Show FPS Monitor",
                    checked = showFPSMonitor,
                    onCheckedChange = onShowFPSMonitorChange,
                )
                SuperSwitch(
                    title = "Show TopAppBar",
                    checked = showTopAppBar,
                    onCheckedChange = onShowTopAppBarChange,
                )
                SuperSwitch(
                    title = if (isWideScreen) "Show NavigationRail" else "Show NavigationBar",
                    checked = showNavigationBar,
                    onCheckedChange = onShowNavigationBarChange,
                )
                AnimatedVisibility(visible = showNavigationBar && !isWideScreen && !useFloatingNavigationBar) {
                    SuperDropdown(
                        title = "NavigationBar Mode",
                        items = navigationDisplayModeOptions,
                        selectedIndex = navigationBarMode,
                        onSelectedIndexChange = onNavigationBarModeChange,
                    )
                }
                AnimatedVisibility(visible = showNavigationBar && isWideScreen) {
                    SuperDropdown(
                        title = "NavigationRail Mode",
                        items = navigationDisplayModeOptions,
                        selectedIndex = navigationRailMode,
                        onSelectedIndexChange = onNavigationRailModeChange,
                    )
                }
                AnimatedVisibility(visible = showNavigationBar && !isWideScreen) {
                    Column {
                        SuperSwitch(
                            title = "Use FloatingNavigationBar",
                            checked = useFloatingNavigationBar,
                            onCheckedChange = onUseFloatingNavigationBarChange,
                        )
                        AnimatedVisibility(visible = useFloatingNavigationBar) {
                            Column {
                                SuperDropdown(
                                    title = "FloatingNavigationBar Mode",
                                    items = navigationDisplayModeOptions,
                                    selectedIndex = floatingNavigationBarMode,
                                    onSelectedIndexChange = onFloatingNavigationBarModeChange,
                                )
                                SuperDropdown(
                                    title = "FloatingNavigationBar Position",
                                    items = floatingNavigationBarPositionOptions,
                                    selectedIndex = floatingNavigationBarPosition,
                                    onSelectedIndexChange = onFloatingNavigationBarPositionChange,
                                )
                            }
                        }
                    }
                }
                SuperSwitch(
                    title = "Show FloatingToolbar",
                    checked = showFloatingToolbar,
                    onCheckedChange = onShowFloatingToolbarChange,
                )
                AnimatedVisibility(visible = showFloatingToolbar) {
                    Column {
                        SuperDropdown(
                            title = "FloatingToolbar Position",
                            items = floatingToolbarPositionOptions,
                            selectedIndex = floatingToolbarPosition,
                            onSelectedIndexChange = onFloatingToolbarPositionChange,
                        )
                        SuperDropdown(
                            title = "FloatingToolbar Orientation",
                            items = floatingToolbarOrientationOptions,
                            selectedIndex = floatingToolbarOrientation,
                            onSelectedIndexChange = onFloatingToolbarOrientationChange,
                        )
                    }
                }
                SuperSwitch(
                    title = "Show FloatingActionButton",
                    checked = showFloatingActionButton,
                    onCheckedChange = onShowFloatingActionButtonChange,
                )
                AnimatedVisibility(visible = showFloatingActionButton) {
                    SuperDropdown(
                        title = "FloatingActionButton Position",
                        items = fabPositionOptions,
                        selectedIndex = fabPosition,
                        onSelectedIndexChange = { onFabPositionChange(it) },
                    )
                }
                SuperSwitch(
                    title = "Enable Scroll End Haptic",
                    checked = enableScrollEndHaptic,
                    onCheckedChange = onScrollEndHapticChange,
                )
                SuperSwitch(
                    title = "Enable Page User Scroll",
                    checked = enablePageUserScroll,
                    onCheckedChange = onEnablePageUserScrollChange,
                )
                SuperDropdown(
                    title = "Color Mode",
                    items = colorModeOptions,
                    selectedIndex = colorMode.value,
                    onSelectedIndexChange = { colorMode.value = it },
                )
                AnimatedVisibility(visible = colorMode.value in listOf(3, 4, 5)) {
                    SuperDropdown(
                        title = "Key Color",
                        items = keyColorOptions,
                        selectedIndex = seedIndex.value,
                        onSelectedIndexChange = { seedIndex.value = it },
                    )
                }
            }
            Card(
                modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp),
            ) {
                SuperArrow(
                    title = "NavTest",
                    summary = "Navigate to a NavTest Page",
                    onClick = { navigator.push(Route.NavTest(Random.nextLong().toString())) },
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
