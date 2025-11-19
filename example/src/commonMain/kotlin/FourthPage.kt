// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import misc.VersionInfo
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.ListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.example.generated.resources.Res
import top.yukonga.miuix.kmp.example.generated.resources.ic_launcher
import top.yukonga.miuix.kmp.extra.DropdownImpl
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.useful.Back
import top.yukonga.miuix.kmp.icon.icons.useful.Edit
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun FourthPage(
    padding: PaddingValues,
    showFPSMonitor: Boolean,
    onShowFPSMonitorChange: (Boolean) -> Unit,
    showTopAppBar: Boolean,
    onShowTopAppBarChange: (Boolean) -> Unit,
    showNavigationBar: Boolean,
    onShowNavigationBarChange: (Boolean) -> Unit,
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
    scrollEndHaptic: Boolean,
    onScrollEndHapticChange: (Boolean) -> Unit,
    isWideScreen: Boolean,
    colorMode: MutableState<Int>,
) {
    val navController = rememberNavController()
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    NavHost(
        navController = navController,
        startDestination = "settings",
        modifier = Modifier.fillMaxHeight(),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it / 5 },
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            ) + fadeOut(
                animationSpec = tween(durationMillis = 500),
                targetAlpha = 0.5f
            )
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it / 5 },
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            ) + fadeIn(
                animationSpec = tween(durationMillis = 500),
                initialAlpha = 0.5f
            )
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
            )
        }
    ) {
        composable("settings") {
            Scaffold(
                topBar = {
                    if (showTopAppBar) {
                        if (isWideScreen) {
                            SmallTopAppBar(
                                title = "Settings",
                                scrollBehavior = topAppBarScrollBehavior,
                                defaultWindowInsetsPadding = false
                            )
                        } else {
                            TopAppBar(
                                title = "Settings",
                                scrollBehavior = topAppBarScrollBehavior
                            )
                        }
                    }
                },
                popupHost = {}
            ) { innerPadding ->
                SettingsContent(
                    padding = PaddingValues(
                        top = innerPadding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding()
                    ),
                    showFPSMonitor = showFPSMonitor,
                    onShowFPSMonitorChange = onShowFPSMonitorChange,
                    showTopAppBar = showTopAppBar,
                    onShowTopAppBarChange = onShowTopAppBarChange,
                    showNavigationBar = showNavigationBar,
                    onShowNavigationBarChange = onShowNavigationBarChange,
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
                    scrollEndHaptic = scrollEndHaptic,
                    onScrollEndHapticChange = onScrollEndHapticChange,
                    colorMode = colorMode,
                    isWideScreen = isWideScreen,
                    navToAbout = { navController.navigate("about") }
                )
            }
        }
        composable("about") {
            Scaffold(
                topBar = {
                    if (showTopAppBar) {
                        if (isWideScreen) {
                            SmallTopAppBar(
                                title = "About",
                                scrollBehavior = topAppBarScrollBehavior,
                                defaultWindowInsetsPadding = false,
                                navigationIcon = {
                                    BackNavigationIcon(
                                        modifier = Modifier.padding(start = 16.dp),
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    )
                                },
                                actions = { AboutTopBarActions() }
                            )
                        } else {
                            TopAppBar(
                                title = "About",
                                scrollBehavior = topAppBarScrollBehavior,
                                navigationIcon = {
                                    BackNavigationIcon(
                                        modifier = Modifier.padding(start = 16.dp),
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    )
                                },
                                actions = { AboutTopBarActions() }
                            )
                        }
                    }
                },
                popupHost = {}
            ) { innerPadding ->
                AboutPage(
                    padding = PaddingValues(
                        top = innerPadding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding()
                    ),
                    topAppBarScrollBehavior = topAppBarScrollBehavior,
                    scrollEndHaptic = scrollEndHaptic,
                    isWideScreen = isWideScreen
                )
            }
        }
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
    scrollEndHaptic: Boolean,
    onScrollEndHapticChange: (Boolean) -> Unit,
    colorMode: MutableState<Int>,
    isWideScreen: Boolean,
    navToAbout: () -> Unit,
) {
    val floatingNavigationBarModeOptions = remember { listOf("IconOnly", "IconAndText", "TextOnly") }
    val floatingNavigationBarPositionOptions = remember { listOf("Center", "Start", "End") }
    val floatingToolbarPositionOptions =
        remember { listOf("TopStart", "CenterStart", "BottomStart", "TopEnd", "CenterEnd", "BottomEnd", "TopCenter", "BottomCenter") }
    val floatingToolbarOrientationOptions = remember { listOf("Horizontal", "Vertical") }
    val fabPositionOptions = remember { listOf("Start", "Center", "End", "EndOverlay") }
    val colorModeOptions = remember { listOf("System", "Light", "Dark", "DynamicSystem", "DynamicLight", "DynamicDark") }

    LazyColumn(
        modifier = Modifier
            .then(if (scrollEndHaptic) Modifier.scrollEndHaptic() else Modifier)
            .overScrollVertical()
            .background(colorScheme.surface)
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
            .fillMaxHeight(),
        contentPadding = PaddingValues(
            top = padding.calculateTopPadding(),
            bottom = if (isWideScreen) {
                WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() +
                        padding.calculateBottomPadding() + 12.dp
            } else padding.calculateBottomPadding() + 12.dp
        ),
        overscrollEffect = null
    ) {
        item {
            Card(
                modifier = Modifier.padding(12.dp)
            ) {
                SuperSwitch(
                    title = "Show FPS Monitor",
                    checked = showFPSMonitor,
                    onCheckedChange = onShowFPSMonitorChange
                )
                SuperSwitch(
                    title = "Show TopAppBar",
                    checked = showTopAppBar,
                    onCheckedChange = onShowTopAppBarChange
                )
                SuperSwitch(
                    title = "Show NavigationBar",
                    checked = showNavigationBar,
                    enabled = !isWideScreen,
                    onCheckedChange = onShowNavigationBarChange
                )
                AnimatedVisibility(visible = showNavigationBar && !isWideScreen) {
                    Column {
                        SuperSwitch(
                            title = "Use FloatingNavigationBar",
                            checked = useFloatingNavigationBar,
                            onCheckedChange = onUseFloatingNavigationBarChange
                        )
                        AnimatedVisibility(visible = useFloatingNavigationBar) {
                            Column {
                                SuperDropdown(
                                    title = "FloatingNavigationBar Mode",
                                    items = floatingNavigationBarModeOptions,
                                    selectedIndex = floatingNavigationBarMode,
                                    onSelectedIndexChange = onFloatingNavigationBarModeChange
                                )
                                SuperDropdown(
                                    title = "FloatingNavigationBar Position",
                                    items = floatingNavigationBarPositionOptions,
                                    selectedIndex = floatingNavigationBarPosition,
                                    onSelectedIndexChange = onFloatingNavigationBarPositionChange
                                )
                            }
                        }
                    }
                }
                SuperSwitch(
                    title = "Show FloatingToolbar",
                    checked = showFloatingToolbar,
                    onCheckedChange = onShowFloatingToolbarChange
                )
                AnimatedVisibility(visible = showFloatingToolbar) {
                    Column {
                        SuperDropdown(
                            title = "FloatingToolbar Position",
                            items = floatingToolbarPositionOptions,
                            selectedIndex = floatingToolbarPosition,
                            onSelectedIndexChange = onFloatingToolbarPositionChange
                        )
                        SuperDropdown(
                            title = "FloatingToolbar Orientation",
                            items = floatingToolbarOrientationOptions,
                            selectedIndex = floatingToolbarOrientation,
                            onSelectedIndexChange = onFloatingToolbarOrientationChange
                        )
                    }
                }
                SuperSwitch(
                    title = "Show FloatingActionButton",
                    checked = showFloatingActionButton,
                    onCheckedChange = onShowFloatingActionButtonChange
                )
                AnimatedVisibility(visible = showFloatingActionButton) {
                    SuperDropdown(
                        title = "FloatingActionButton Position",
                        items = fabPositionOptions,
                        selectedIndex = fabPosition,
                        onSelectedIndexChange = { onFabPositionChange(it) }
                    )
                }
                SuperSwitch(
                    title = "Enable Scroll End Haptic",
                    checked = scrollEndHaptic,
                    onCheckedChange = onScrollEndHapticChange
                )
                SuperSwitch(
                    title = "Enable Page User Scroll",
                    checked = enablePageUserScroll,
                    onCheckedChange = onEnablePageUserScrollChange
                )
                SuperDropdown(
                    title = "Color Mode",
                    items = colorModeOptions,
                    selectedIndex = colorMode.value,
                    onSelectedIndexChange = { colorMode.value = it }
                )
            }
            Card(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                SuperArrow(
                    title = "About",
                    summary = "About this app",
                    onClick = navToAbout
                )
            }
        }
    }
}

@Composable
fun AboutPage(
    padding: PaddingValues,
    topAppBarScrollBehavior: ScrollBehavior,
    scrollEndHaptic: Boolean,
    isWideScreen: Boolean
) {
    val uriHandler = LocalUriHandler.current
    LazyColumn(
        modifier = Modifier
            .then(if (scrollEndHaptic) Modifier.scrollEndHaptic() else Modifier)
            .overScrollVertical()
            .background(colorScheme.surface)
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
            .fillMaxHeight(),
        contentPadding = PaddingValues(
            top = padding.calculateTopPadding(),
            bottom = if (isWideScreen) {
                WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() +
                        padding.calculateBottomPadding() + 12.dp
            } else padding.calculateBottomPadding() + 12.dp
        ),
        overscrollEffect = null
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 72.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_launcher),
                        contentDescription = "App Logo",
                    )
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth().padding(bottom = 48.dp),
                text = "JDK Ver. " + VersionInfo.JDK_VERSION +
                        "\nAPP Ver. " + VersionInfo.VERSION_NAME + " (" + VersionInfo.VERSION_CODE + ")",
                textAlign = TextAlign.Center,
            )
            Card(
                modifier = Modifier.padding(horizontal = 12.dp)
            ) {
                SuperArrow(
                    title = "View Source",
                    rightActions = {
                        Text(
                            modifier = Modifier.padding(end = 8.dp),
                            text = "GitHub",
                            color = colorScheme.onSurfaceVariantActions
                        )
                    },
                    onClick = { uriHandler.openUri("https://github.com/compose-miuix-ui/miuix") }
                )
                SuperArrow(
                    title = "Join Group",
                    rightActions = {
                        Text(
                            modifier = Modifier.padding(end = 8.dp),
                            text = "Telegram",
                            color = colorScheme.onSurfaceVariantActions
                        )
                    },
                    onClick = { uriHandler.openUri("https://t.me/YuKongA13579") }
                )
            }
            Card(
                modifier = Modifier.padding(horizontal = 12.dp).padding(top = 12.dp)
            ) {
                val list = listOf("Apache-2.0", "Apache-2.0", "Apache-2.0")
                var selectedIndex by remember { mutableStateOf(0) }
                SuperDropdown(
                    title = "License",
                    items = list,
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = {
                        selectedIndex = it
                    },
                )
            }
        }
    }
}

@Composable
fun BackNavigationIcon(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = MiuixIcons.Useful.Back,
            contentDescription = null,
            tint = colorScheme.onBackground
        )
    }
}

@Composable
fun AboutTopBarActions() {
    val showTopPopup = remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }
    val hapticFeedback = LocalHapticFeedback.current
    IconButton(
        modifier = Modifier.padding(end = 16.dp),
        onClick = { showTopPopup.value = true },
        holdDownState = showTopPopup.value
    ) {
        Icon(
            imageVector = MiuixIcons.Useful.Edit,
            contentDescription = "Test 1",
            tint = colorScheme.onBackground
        )
    }
    ListPopup(
        show = showTopPopup,
        popupPositionProvider = ListPopupDefaults.ContextMenuPositionProvider,
        alignment = PopupPositionProvider.Align.TopRight,
        onDismissRequest = {
            showTopPopup.value = false
        }
    ) {
        val items = listOf("Option 1", "Option 2", "Option 3")
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
                            showTopPopup.value = false
                        },
                        index = index
                    )
                }
            }
        }
    }
}
