// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation3.adaptive.SupportingPaneScaffold
import androidx.navigation3.adaptive.utils.shouldShowSplitPane
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.FabPosition
import top.yukonga.miuix.kmp.basic.FloatingActionButton
import top.yukonga.miuix.kmp.basic.FloatingNavigationBar
import top.yukonga.miuix.kmp.basic.FloatingNavigationBarMode
import top.yukonga.miuix.kmp.basic.FloatingToolbar
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationItem
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.ToolbarPosition
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Create
import top.yukonga.miuix.kmp.icon.extended.Delete
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.icon.extended.HorizontalSplit
import top.yukonga.miuix.kmp.icon.extended.Image
import top.yukonga.miuix.kmp.icon.extended.Link
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.icon.extended.Sort
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic
import utils.FPSMonitor

private object UIConstants {
    const val MAIN_PAGE_INDEX = 0
    const val ICON_PAGE_INDEX = 1
    const val COLOR_PAGE_INDEX = 2
    const val DROPDOWN_PAGE_INDEX = 3
    const val PAGE_COUNT = 5
    const val GITHUB_URL = "https://github.com/compose-miuix-ui/miuix"

    val PAGE_TITLES = listOf("Home", "Icon", "Color", "Dropdown", "Settings")
}

enum class FloatingNavigationBarAlignment(val value: Int) {
    Center(0),
    Start(1),
    End(2),
    ;

    companion object {
        fun fromInt(value: Int) = entries.find { it.value == value } ?: Center
    }
}

enum class FloatingNavigationBarDisplayMode(val value: Int) {
    IconOnly(0),
    IconAndText(1),
    TextOnly(2),
    ;

    companion object {
        fun fromInt(value: Int) = entries.find { it.value == value } ?: IconOnly
    }
}

data class UIState(
    val showFPSMonitor: Boolean = false,
    val showTopAppBar: Boolean = true,
    val showNavigationBar: Boolean = true,
    val useFloatingNavigationBar: Boolean = false,
    val floatingNavigationBarMode: Int = 0,
    val floatingNavigationBarPosition: Int = 0,
    val showFloatingToolbar: Boolean = false,
    val floatingToolbarPosition: Int = 1,
    val floatingToolbarOrientation: Int = 1,
    val showFloatingActionButton: Boolean = false,
    val floatingActionButtonPosition: Int = 2,
    val enablePageUserScroll: Boolean = false,
    val enableScrollEndHaptic: Boolean = true,
    val enableOverScroll: Boolean = true,
    val isWideScreen: Boolean = false,
)

val LocalPagerState = compositionLocalOf<PagerState> { error("No pager state") }
val LocalHandlePageChange = compositionLocalOf<(Int) -> Unit> { error("No handle page change") }
val LocalBackStack = compositionLocalOf<MutableList<NavKey>> { error("No backstack") }

sealed interface Screen : NavKey {
    data object Home : Screen
    data object About : Screen
    data class NavTestPage(val id: String) : Screen
}

@Composable
fun UITest(
    colorMode: MutableState<Int>,
    seedIndex: MutableState<Int>,
    padding: PaddingValues,
    enableOverScroll: Boolean,
) {
    val pagerState = rememberPagerState(pageCount = { UIConstants.PAGE_COUNT })
    val coroutineScope = rememberCoroutineScope()

    val navigationItems = remember {
        listOf(
            NavigationItem(UIConstants.PAGE_TITLES[0], MiuixIcons.HorizontalSplit),
            NavigationItem(UIConstants.PAGE_TITLES[1], MiuixIcons.Create),
            NavigationItem(UIConstants.PAGE_TITLES[2], MiuixIcons.Image),
            NavigationItem(UIConstants.PAGE_TITLES[3], MiuixIcons.Sort),
            NavigationItem(UIConstants.PAGE_TITLES[4], MiuixIcons.Settings),
        )
    }

    var uiState by remember { mutableStateOf(UIState()) }
    val backStack = remember { mutableStateListOf<NavKey>(Screen.Home) }
    val handlePageChange: (Int) -> Unit = remember(pagerState, coroutineScope) {
        { page ->
            coroutineScope.launch {
                if (uiState.isWideScreen) {
                    pagerState.scrollToPage(page)
                } else {
                    pagerState.animateScrollToPage(page)
                }
            }
        }
    }

    CompositionLocalProvider(
        LocalPagerState provides pagerState,
        LocalHandlePageChange provides handlePageChange,
        LocalBackStack provides backStack,
    ) {
        val isWideScreen = shouldShowSplitPane()

        LaunchedEffect(isWideScreen) {
            if (uiState.isWideScreen != isWideScreen) {
                uiState = uiState.copy(isWideScreen = isWideScreen)
            }
        }
        LaunchedEffect(enableOverScroll) {
            if (uiState.enableOverScroll != enableOverScroll) {
                uiState = uiState.copy(enableOverScroll = enableOverScroll)
            }
        }

        val entryProvider = remember(backStack, uiState, colorMode, seedIndex) {
            entryProvider<NavKey> {
                entry(Screen.Home) {
                    Home(
                        uiState = uiState,
                        onUiStateChange = { uiState = it },
                        colorMode = colorMode,
                        seedIndex = seedIndex,
                        padding = padding,
                        navigationItems = navigationItems,
                        navToAbout = { backStack.add(Screen.About) },
                    )
                }
                entry(Screen.About) {
                    AboutPage(
                        padding = padding,
                        showTopAppBar = uiState.showTopAppBar,
                        isWideScreen = uiState.isWideScreen,
                        enableScrollEndHaptic = uiState.enableScrollEndHaptic,
                        enableOverScroll = uiState.enableOverScroll,
                        onBack = { backStack.removeLast() },
                    )
                }
                entry<Screen.NavTestPage> {
                    NavTestPage(
                        padding = padding,
                        showTopAppBar = uiState.showTopAppBar,
                        isWideScreen = uiState.isWideScreen,
                        enableScrollEndHaptic = uiState.enableScrollEndHaptic,
                        enableOverScroll = uiState.enableOverScroll,
                        onBack = { backStack.removeLast() },
                    )
                }
            }
        }

        NavDisplay(
            backStack = backStack,
            entryProvider = entryProvider,
            onBack = { backStack.removeLast() },
        )
    }

    AnimatedVisibility(
        visible = uiState.showFPSMonitor,
        enter = fadeIn() + expandHorizontally(),
        exit = fadeOut() + shrinkHorizontally(),
    ) {
        FPSMonitor(
            modifier = Modifier
                .statusBarsPadding()
                .captionBarPadding()
                .padding(all = 12.dp),
        )
    }
}

@Composable
private fun Home(
    uiState: UIState,
    onUiStateChange: (UIState) -> Unit,
    colorMode: MutableState<Int>,
    seedIndex: MutableState<Int>,
    padding: PaddingValues,
    navigationItems: List<NavigationItem>,
    navToAbout: () -> Unit,
) {
    val layoutDirection = LocalLayoutDirection.current
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            if (uiState.isWideScreen) {
                SnackbarHost(state = snackbarHostState)
            }
        },
    ) {
        SupportingPaneScaffold(
            main = {
                if (uiState.isWideScreen) {
                    WideScreenContent(
                        uiState = uiState,
                        onUiStateChange = onUiStateChange,
                        colorMode = colorMode,
                        seedIndex = seedIndex,
                        snackbarHostState = snackbarHostState,
                        layoutDirection = layoutDirection,
                        navToAbout = navToAbout,
                    )
                } else {
                    CompactScreenLayout(
                        navigationItems = navigationItems,
                        uiState = uiState,
                        onUiStateChange = onUiStateChange,
                        colorMode = colorMode,
                        seedIndex = seedIndex,
                        snackbarHostState = snackbarHostState,
                        padding = padding,
                        navToAbout = navToAbout,
                    )
                }
            },
            supporting = {
                val barScrollBehavior = MiuixScrollBehavior()
                WideScreenPanel(
                    barScrollBehavior = barScrollBehavior,
                    uiState = uiState,
                    layoutDirection = layoutDirection,
                )
            },
        )
    }
}

@Composable
private fun WideScreenPanel(
    barScrollBehavior: ScrollBehavior,
    uiState: UIState,
    layoutDirection: LayoutDirection,
) {
    val page = LocalPagerState.current.targetPage
    val handlePageChange = LocalHandlePageChange.current
    Scaffold(
        modifier = Modifier
            .padding(start = 18.dp, end = 12.dp)
            .fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBars.union(
            WindowInsets.displayCutout.exclude(
                WindowInsets.displayCutout.only(WindowInsetsSides.End),
            ),
        ),
        topBar = {
            TopAppBar(
                title = "Miuix",
                horizontalPadding = 12.dp,
                scrollBehavior = barScrollBehavior,
            )
        },
        popupHost = { },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .then(
                    if (uiState.enableScrollEndHaptic) Modifier.scrollEndHaptic() else Modifier,
                )
                .padding(start = padding.calculateStartPadding(layoutDirection))
                .overScrollVertical(
                    isEnabled = { uiState.enableOverScroll },
                )
                .nestedScroll(barScrollBehavior.nestedScrollConnection)
                .fillMaxHeight(),
            contentPadding = PaddingValues(bottom = 12.dp),
        ) {
            item {
                Card(
                    modifier = Modifier
                        .padding(
                            top = 12.dp + padding.calculateTopPadding(),
                            bottom = padding.calculateBottomPadding(),
                        ),
                ) {
                    UIConstants.PAGE_TITLES.forEachIndexed { index, title ->
                        BasicComponent(
                            title = title,
                            onClick = { handlePageChange(index) },
                            holdDownState = page == index,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun WideScreenContent(
    uiState: UIState,
    onUiStateChange: (UIState) -> Unit,
    colorMode: MutableState<Int>,
    seedIndex: MutableState<Int>,
    snackbarHostState: SnackbarHostState,
    layoutDirection: LayoutDirection,
    navToAbout: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .padding(end = 6.dp)
            .fillMaxSize(),
        contentWindowInsets =
        WindowInsets.systemBars.union(
            WindowInsets.displayCutout.exclude(
                WindowInsets.displayCutout.only(WindowInsetsSides.Start),
            ),
        ),
        floatingActionButton = {
            FloatingActionButton(show = uiState.showFloatingActionButton)
        },
        floatingActionButtonPosition = uiState.floatingActionButtonPosition.toFabPosition(),
        floatingToolbar = {
            FloatingToolbar(
                showFloatingToolbar = uiState.showFloatingToolbar,
                floatingToolbarOrientation = uiState.floatingToolbarOrientation,
            )
        },
        floatingToolbarPosition = uiState.floatingToolbarPosition.toToolbarPosition(),
        popupHost = { },
    ) { padding ->
        AppPager(
            snackbarHostState = snackbarHostState,
            padding = PaddingValues(top = padding.calculateTopPadding()),
            uiState = uiState,
            onUiStateChange = onUiStateChange,
            colorMode = colorMode,
            seedIndex = seedIndex,
            modifier = Modifier
                .imePadding()
                .padding(end = padding.calculateEndPadding(layoutDirection)),
            navToAbout = navToAbout,
        )
    }
}

@Composable
private fun CompactScreenLayout(
    navigationItems: List<NavigationItem>,
    uiState: UIState,
    onUiStateChange: (UIState) -> Unit,
    colorMode: MutableState<Int>,
    seedIndex: MutableState<Int>,
    snackbarHostState: SnackbarHostState,
    padding: PaddingValues,
    navToAbout: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                uiState = uiState,
                navigationItems = navigationItems,
                modifier = Modifier.padding(bottom = padding.calculateBottomPadding()),
            )
        },
        floatingActionButton = {
            FloatingActionButton(show = uiState.showFloatingActionButton)
        },
        floatingActionButtonPosition = uiState.floatingActionButtonPosition.toFabPosition(),
        floatingToolbar = {
            FloatingToolbar(
                showFloatingToolbar = uiState.showFloatingToolbar,
                floatingToolbarOrientation = uiState.floatingToolbarOrientation,
            )
        },
        floatingToolbarPosition = uiState.floatingToolbarPosition.toToolbarPosition(),
        snackbarHost = {
            if (!uiState.isWideScreen) {
                SnackbarHost(state = snackbarHostState)
            }
        },
    ) { innerPadding ->
        AppPager(
            snackbarHostState = snackbarHostState,
            padding = innerPadding,
            uiState = uiState,
            onUiStateChange = onUiStateChange,
            colorMode = colorMode,
            seedIndex = seedIndex,
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    start = padding.calculateStartPadding(LocalLayoutDirection.current),
                    end = padding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .imePadding(),
            navToAbout = navToAbout,
        )
    }
}

@Composable
private fun NavigationBar(
    uiState: UIState,
    navigationItems: List<NavigationItem>,
    modifier: Modifier = Modifier,
) {
    val page = LocalPagerState.current.targetPage
    val handlePageChange = LocalHandlePageChange.current
    AnimatedVisibility(
        visible = uiState.showNavigationBar,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
    ) {
        AnimatedVisibility(
            visible = !uiState.useFloatingNavigationBar,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top),
        ) {
            Box(
                modifier = Modifier
                    .background(MiuixTheme.colorScheme.surface)
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent()
                                event.changes.forEach { change ->
                                    if (change.pressed) change.consume()
                                }
                            }
                        }
                    }
                    .then(modifier),
            ) {
                NavigationBar(
                    modifier = Modifier,
                    items = navigationItems,
                    selected = page,
                    onClick = handlePageChange,
                )
            }
        }
        AnimatedVisibility(
            visible = uiState.useFloatingNavigationBar,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top),
        ) {
            Box(
                modifier = modifier,
            ) {
                FloatingNavigationBar(
                    items = navigationItems,
                    selected = page,
                    mode = FloatingNavigationBarDisplayMode.fromInt(uiState.floatingNavigationBarMode).toMode(),
                    horizontalAlignment = FloatingNavigationBarAlignment.fromInt(uiState.floatingNavigationBarPosition)
                        .toAlignment(),
                    onClick = handlePageChange,
                )
            }
        }
    }
}

@Composable
private fun FloatingActionButton(
    show: Boolean,
) {
    if (show) {
        val uriHandler = LocalUriHandler.current
        FloatingActionButton(
            onClick = {
                uriHandler.openUri(UIConstants.GITHUB_URL)
            },
        ) {
            Icon(
                imageVector = MiuixIcons.Link,
                tint = MiuixTheme.colorScheme.onPrimary,
                contentDescription = "GitHub",
            )
        }
    }
}

@Composable
private fun FloatingToolbar(
    showFloatingToolbar: Boolean,
    floatingToolbarOrientation: Int,
) {
    AnimatedVisibility(
        visible = showFloatingToolbar,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        FloatingToolbar(
            color = MiuixTheme.colorScheme.primary,
            cornerRadius = 20.dp,
        ) {
            AnimatedContent(
                targetState = floatingToolbarOrientation,
            ) { orientation ->
                val iconTint = MiuixTheme.colorScheme.onPrimary
                val content = @Composable {
                    IconButton(onClick = { /* Action 1 */ }) {
                        Icon(
                            MiuixIcons.Edit,
                            contentDescription = "Edit",
                            tint = iconTint,
                        )
                    }
                    IconButton(onClick = { /* Action 2 */ }) {
                        Icon(
                            MiuixIcons.Delete,
                            contentDescription = "Delete",
                            tint = iconTint,
                        )
                    }
                    IconButton(onClick = { /* Action 3 */ }) {
                        Icon(
                            MiuixIcons.More,
                            contentDescription = "More",
                            tint = iconTint,
                        )
                    }
                }
                when (orientation) {
                    0 -> Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) { content() }

                    else -> Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) { content() }
                }
            }
        }
    }
}

private fun Int.toFabPosition(): FabPosition = when (this) {
    0 -> FabPosition.Start
    1 -> FabPosition.Center
    2 -> FabPosition.End
    else -> FabPosition.EndOverlay
}

private fun Int.toToolbarPosition(): ToolbarPosition = when (this) {
    0 -> ToolbarPosition.TopStart
    1 -> ToolbarPosition.CenterStart
    2 -> ToolbarPosition.BottomStart
    3 -> ToolbarPosition.TopEnd
    4 -> ToolbarPosition.CenterEnd
    5 -> ToolbarPosition.BottomEnd
    6 -> ToolbarPosition.TopCenter
    else -> ToolbarPosition.BottomCenter
}

private fun FloatingNavigationBarDisplayMode.toMode(): FloatingNavigationBarMode = when (this) {
    FloatingNavigationBarDisplayMode.IconOnly -> FloatingNavigationBarMode.IconOnly
    FloatingNavigationBarDisplayMode.IconAndText -> FloatingNavigationBarMode.IconAndText
    FloatingNavigationBarDisplayMode.TextOnly -> FloatingNavigationBarMode.TextOnly
}

private fun FloatingNavigationBarAlignment.toAlignment(): Alignment.Horizontal = when (this) {
    FloatingNavigationBarAlignment.Center -> CenterHorizontally
    FloatingNavigationBarAlignment.Start -> Alignment.Start
    FloatingNavigationBarAlignment.End -> Alignment.End
}

@Composable
fun AppPager(
    snackbarHostState: SnackbarHostState,
    padding: PaddingValues,
    uiState: UIState,
    onUiStateChange: (UIState) -> Unit,
    colorMode: MutableState<Int>,
    seedIndex: MutableState<Int>,
    modifier: Modifier = Modifier,
    navToAbout: () -> Unit,
) {
    HorizontalPager(
        state = LocalPagerState.current,
        modifier = modifier,
        userScrollEnabled = uiState.enablePageUserScroll,
        verticalAlignment = Alignment.Top,
        beyondViewportPageCount = 1,
        overscrollEffect = null,
        pageContent = { page ->
            when (page) {
                UIConstants.MAIN_PAGE_INDEX -> MainPage(
                    snackbarHostState = snackbarHostState,
                    padding = padding,
                    enableScrollEndHaptic = uiState.enableScrollEndHaptic,
                    enableOverScroll = uiState.enableOverScroll,
                    isWideScreen = uiState.isWideScreen,
                    showTopAppBar = uiState.showTopAppBar,
                )

                UIConstants.ICON_PAGE_INDEX -> IconsPage(
                    padding = padding,
                    enableScrollEndHaptic = uiState.enableScrollEndHaptic,
                    enableOverScroll = uiState.enableOverScroll,
                    isWideScreen = uiState.isWideScreen,
                    showTopAppBar = uiState.showTopAppBar,
                )

                UIConstants.COLOR_PAGE_INDEX -> ColorPage(
                    padding = padding,
                    enableScrollEndHaptic = uiState.enableScrollEndHaptic,
                    enableOverScroll = uiState.enableOverScroll,
                    isWideScreen = uiState.isWideScreen,
                    showTopAppBar = uiState.showTopAppBar,
                )

                UIConstants.DROPDOWN_PAGE_INDEX -> DropdownPage(
                    padding = padding,
                    enableScrollEndHaptic = uiState.enableScrollEndHaptic,
                    enableOverScroll = uiState.enableOverScroll,
                    isWideScreen = uiState.isWideScreen,
                    showTopAppBar = uiState.showTopAppBar,
                )

                else -> SettingsPage(
                    padding = padding,
                    showFPSMonitor = uiState.showFPSMonitor,
                    onShowFPSMonitorChange = { onUiStateChange(uiState.copy(showFPSMonitor = it)) },
                    showTopAppBar = uiState.showTopAppBar,
                    onShowTopAppBarChange = { onUiStateChange(uiState.copy(showTopAppBar = it)) },
                    showNavigationBar = uiState.showNavigationBar,
                    onShowNavigationBarChange = { onUiStateChange(uiState.copy(showNavigationBar = it)) },
                    showFloatingToolbar = uiState.showFloatingToolbar,
                    onShowFloatingToolbarChange = { onUiStateChange(uiState.copy(showFloatingToolbar = it)) },
                    useFloatingNavigationBar = uiState.useFloatingNavigationBar,
                    onUseFloatingNavigationBarChange = { onUiStateChange(uiState.copy(useFloatingNavigationBar = it)) },
                    floatingNavigationBarMode = uiState.floatingNavigationBarMode,
                    onFloatingNavigationBarModeChange = { onUiStateChange(uiState.copy(floatingNavigationBarMode = it)) },
                    floatingNavigationBarPosition = uiState.floatingNavigationBarPosition,
                    onFloatingNavigationBarPositionChange = { onUiStateChange(uiState.copy(floatingNavigationBarPosition = it)) },
                    floatingToolbarPosition = uiState.floatingToolbarPosition,
                    onFloatingToolbarPositionChange = { onUiStateChange(uiState.copy(floatingToolbarPosition = it)) },
                    floatingToolbarOrientation = uiState.floatingToolbarOrientation,
                    onFloatingToolbarOrientationChange = { onUiStateChange(uiState.copy(floatingToolbarOrientation = it)) },
                    showFloatingActionButton = uiState.showFloatingActionButton,
                    onShowFloatingActionButtonChange = { onUiStateChange(uiState.copy(showFloatingActionButton = it)) },
                    fabPosition = uiState.floatingActionButtonPosition,
                    onFabPositionChange = { onUiStateChange(uiState.copy(floatingActionButtonPosition = it)) },
                    enablePageUserScroll = uiState.enablePageUserScroll,
                    onEnablePageUserScrollChange = { onUiStateChange(uiState.copy(enablePageUserScroll = it)) },
                    enableScrollEndHaptic = uiState.enableScrollEndHaptic,
                    onScrollEndHapticChange = { onUiStateChange(uiState.copy(enableScrollEndHaptic = it)) },
                    enableOverScroll = uiState.enableOverScroll,
                    isWideScreen = uiState.isWideScreen,
                    colorMode = colorMode,
                    seedIndex = seedIndex,
                    navToAbout = navToAbout,
                )
            }
        },
    )
}
