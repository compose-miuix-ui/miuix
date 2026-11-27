// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.CapturedAnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import component.liquid.IosLiquidGlassNavigationBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import navigation.ComponentDemo
import navigation.CrossActivityTransition
import navigation.Navigator
import navigation.Route
import top.yukonga.miuix.kmp.basic.Badge
import top.yukonga.miuix.kmp.basic.FabPosition
import top.yukonga.miuix.kmp.basic.FloatingActionButton
import top.yukonga.miuix.kmp.basic.FloatingNavigationBar
import top.yukonga.miuix.kmp.basic.FloatingNavigationBarItem
import top.yukonga.miuix.kmp.basic.FloatingToolbar
import top.yukonga.miuix.kmp.basic.FloatingToolbarDefaults
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarDisplayMode
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.NavigationItem
import top.yukonga.miuix.kmp.basic.NavigationRail
import top.yukonga.miuix.kmp.basic.NavigationRailItem
import top.yukonga.miuix.kmp.basic.NavigationRailValue
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.ToolbarPosition
import top.yukonga.miuix.kmp.basic.rememberNavigationRailState
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.LayerBackdrop
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.glass.GlassFloatingActionButton
import top.yukonga.miuix.kmp.glass.GlassFloatingToolbar
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassNavigationRail
import top.yukonga.miuix.kmp.glass.GlassNavigationRailDefaults
import top.yukonga.miuix.kmp.glass.GlassNavigationRailState
import top.yukonga.miuix.kmp.glass.rememberGlassNavigationRailState
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Delete
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.icon.extended.Link
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.icon.symbol.Desktop
import top.yukonga.miuix.kmp.icon.symbol.DoodlePen
import top.yukonga.miuix.kmp.icon.symbol.EditSubtitle
import top.yukonga.miuix.kmp.icon.symbol.Hd
import top.yukonga.miuix.kmp.icon.symbol.Image
import top.yukonga.miuix.kmp.icon.symbol.Settings
import top.yukonga.miuix.kmp.icon.symbol.Theme
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.EdgeStrokes
import top.yukonga.miuix.kmp.material.edgeStroke
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.nav.core.NavBackStack
import top.yukonga.miuix.kmp.nav.core.NavCornerClipMode
import top.yukonga.miuix.kmp.nav.core.NavDisplay
import top.yukonga.miuix.kmp.nav.core.NavDisplayEffects
import top.yukonga.miuix.kmp.nav.core.rememberNavBackStack
import top.yukonga.miuix.kmp.nav.core.rememberNavSystemCornerRadius
import top.yukonga.miuix.kmp.nav.transition.NavMotion
import top.yukonga.miuix.kmp.nav.transition.NavSettleSpec
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition
import top.yukonga.miuix.kmp.symbol.toImageVector
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PagerGestureNestedScrollConnection
import top.yukonga.miuix.kmp.utils.PagerInterceptionMode
import top.yukonga.miuix.kmp.utils.PagerNavigationSpringSpec
import top.yukonga.miuix.kmp.utils.pagerGestureOverride
import top.yukonga.miuix.kmp.utils.springAnimateToPage
import ui.isInDarkTheme
import utils.FPSMonitor
import utils.LocalAppPageInsets
import utils.LocalTopAppBarCenterObstructionStart
import utils.LocalTopAppBarTitleAlpha
import utils.LocalTopLayer
import utils.TopLayerHost
import utils.TopLayerState
import utils.isInMultiWindowMode
import utils.rememberAppPageInsets
import utils.rememberBlurBackdrop
import utils.shouldExpandNavigationRail
import utils.shouldShowSplitPane
import kotlin.math.abs
import kotlin.time.Duration.Companion.milliseconds

private object UIConstants {
    const val MAIN_PAGE_INDEX = 0
    const val ICON_PAGE_INDEX = 1
    const val COLOR_PAGE_INDEX = 2
    const val TEXT_STYLE_PAGE_INDEX = 3
    const val PAGE_COUNT = 5
    const val GITHUB_URL = "https://github.com/compose-miuix-ui/miuix"

    val PAGE_TITLES = listOf("Home", "Icon", "Color", "TextStyle", "Settings")
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

val LocalNavigator = staticCompositionLocalOf<Navigator> { error("No navigator found!") }
val LocalIsWideScreen = compositionLocalOf { false }
val LocalMainPagerState = staticCompositionLocalOf<MainPagerState> { error("LocalMainPagerState not provided") }
private val DefaultTopAppBarTitleAlpha: () -> Float = { 1f }
private val DefaultTopAppBarCenterObstructionStart: () -> Dp = { Dp.Unspecified }

@Composable
fun AppContent(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current

    val pagerState = rememberPagerState(pageCount = { UIConstants.PAGE_COUNT })
    val mainPagerState = rememberMainPagerState(pagerState)
    LaunchedEffect(mainPagerState) {
        snapshotFlow { mainPagerState.pagerState.currentPage }
            .collect { mainPagerState.syncPage() }
    }

    // Explicit Route type preserves the sealed hierarchy for save/restore.
    val backStack = rememberNavBackStack<Route>(Route.Main)
    val navigator = remember { Navigator(backStack) }

    val navigationItems = remember {
        listOf(
            NavigationItem(UIConstants.PAGE_TITLES[0], MiuixIcons.Symbol.Desktop.toImageVector()),
            NavigationItem(UIConstants.PAGE_TITLES[1], MiuixIcons.Symbol.Image.toImageVector()),
            NavigationItem(UIConstants.PAGE_TITLES[2], MiuixIcons.Symbol.Theme.toImageVector()),
            NavigationItem(UIConstants.PAGE_TITLES[3], MiuixIcons.Symbol.EditSubtitle.toImageVector()),
            NavigationItem(UIConstants.PAGE_TITLES[4], MiuixIcons.Symbol.Settings.toImageVector()),
        )
    }

    MainScreenBackHandler(mainPagerState, navigator)

    val isWideScreen = shouldShowSplitPane()
    val hasNavigationRail = isWideScreen && appState.showNavigationBar
    // Plain rails occupy a column; glass rails overlay the page.
    val useGlassSidebar = hasNavigationRail && appState.navigationRailStyle == 1
    val expandSidebar = shouldExpandNavigationRail()
    val sidebarState = rememberGlassNavigationRailState(initialExpanded = expandSidebar)
    LaunchedEffect(expandSidebar) { sidebarState.expanded = expandSidebar }
    val primaryTitleAlpha: () -> Float = remember(useGlassSidebar, sidebarState) {
        if (useGlassSidebar) {
            { sidebarState.progress }
        } else {
            { 1f }
        }
    }
    val topAppBarCenterObstructionStart: () -> Dp = remember(useGlassSidebar, sidebarState) {
        if (useGlassSidebar) {
            {
                if (!sidebarState.expanded && sidebarState.collapsedWidth > 0.dp) {
                    sidebarState.collapsedStart
                } else {
                    Dp.Unspecified
                }
            }
        } else {
            DefaultTopAppBarCenterObstructionStart
        }
    }
    LaunchedEffect(useGlassSidebar, sidebarState.expanded) {
        mainPagerState.snapTransitions = useGlassSidebar && sidebarState.expanded
    }
    val sidebarBackdrop = if (useGlassSidebar) rememberBlurBackdrop() else null
    // Blur needs a recorded page layer; otherwise use the surface color.
    val pageInsets = rememberAppPageInsets(
        hostPadding = padding,
        startAlreadyReserved = hasNavigationRail && !useGlassSidebar,
        overlayStart = {
            if (useGlassSidebar) {
                (GlassNavigationRailDefaults.Margin + GlassNavigationRailDefaults.ExpandedWidth) * sidebarState.progress
            } else {
                0.dp
            }
        },
        searchTop = {
            if (useGlassSidebar) {
                (GlassNavigationRailDefaults.CollapsedTop + GlassNavigationRailDefaults.CollapsedHeight + 4.dp) * (1f - sidebarState.progress)
            } else {
                0.dp
            }
        },
    )
    val topLayer = remember { TopLayerState() }

    CompositionLocalProvider(
        LocalNavigator provides navigator,
        LocalMainPagerState provides mainPagerState,
        LocalIsWideScreen provides isWideScreen,
        LocalTopAppBarTitleAlpha provides DefaultTopAppBarTitleAlpha,
        LocalTopAppBarCenterObstructionStart provides topAppBarCenterObstructionStart,
        LocalAppPageInsets provides pageInsets,
    ) {
        // Match platform screen corners; multi-window has no outer screen corner.
        val navCornerRadius = if (isInMultiWindowMode()) 0.dp else rememberNavSystemCornerRadius()
        val isCrossActivityStyle = appState.navTransitionStyle == 1
        val backdropColor = MiuixTheme.colorScheme.surface
        val isDarkTheme = isInDarkTheme()
        val effects = remember(
            appState.enableCornerClip,
            navCornerRadius,
            appState.enableDim,
            appState.blockInputDuringTransition,
            isCrossActivityStyle,
            backdropColor,
            isDarkTheme,
        ) {
            NavDisplayEffects(
                enableCornerClip = appState.enableCornerClip,
                // Fallback radius for platforms without screen corners.
                cornerClipRadius = if (isCrossActivityStyle && navCornerRadius <= 0.dp) 32.dp else navCornerRadius,
                cornerClipMode = if (isCrossActivityStyle) NavCornerClipMode.All else NavCornerClipMode.Leading,
                // Card rounds all corners; slide rounds the leading edge.
                // Card uses platform scrim amounts; slide uses 0.5.
                dimAmount = when {
                    !appState.enableDim -> 0f
                    isCrossActivityStyle -> if (isDarkTheme) 0.8f else 0.2f
                    else -> 0.5f
                },
                blockInputDuringTransition = appState.blockInputDuringTransition,
                backdropColor = backdropColor,
            )
        }

        // Swipe direction is physical; mirror it for RTL.
        val swipeBackDirection = when {
            !appState.enableSwipeBack -> NavSwipeDirection.None
            LocalLayoutDirection.current == LayoutDirection.Rtl -> NavSwipeDirection.RightToLeft
            else -> NavSwipeDirection.LeftToRight
        }

        var suppressNavTransition by remember { mutableStateOf(false) }
        LaunchedEffect(navigator.backStack.size, navigator.backStack.lastOrNull()) {
            if (suppressNavTransition) {
                delay(50.milliseconds)
                suppressNavTransition = false
            }
        }
        val navTransition = when {
            suppressNavTransition -> InstantNavTransition
            appState.navTransitionStyle == 2 -> NavTransitions.Expansion
            isCrossActivityStyle -> CrossActivityTransition
            else -> NavTransitions.MiuixDefault
        }

        // Keep overlays above page content and the glass rail.
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0),
        ) {
            CompositionLocalProvider(LocalTopLayer provides topLayer) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.fillMaxSize()) {
                        if (hasNavigationRail && !useGlassSidebar) {
                            PersistentNavigationRail(
                                navigationItems = navigationItems,
                                mainPagerState = mainPagerState,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                // Plain rails consume the inset; glass rails reserve it in page content.
                                .then(
                                    if (hasNavigationRail && !useGlassSidebar) {
                                        Modifier.consumeWindowInsets(
                                            pageInsets.windowInsets.only(WindowInsetsSides.Start),
                                        )
                                    } else {
                                        Modifier
                                    },
                                )
                                .then(
                                    if (useGlassSidebar) {
                                        Modifier.background(MiuixTheme.colorScheme.surface)
                                    } else {
                                        Modifier
                                    },
                                )
                                .then(if (isWideScreen) Modifier.clipToBounds() else Modifier),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .then(
                                        if (sidebarBackdrop != null) {
                                            Modifier.layerBackdrop(sidebarBackdrop)
                                        } else {
                                            Modifier
                                        },
                                    ),
                            ) {
                                AppNavHost(
                                    modifier = Modifier.fillMaxSize(),
                                    backStack = backStack,
                                    navigator = navigator,
                                    transition = navTransition,
                                    effects = effects,
                                    swipeBackDirection = swipeBackDirection,
                                    padding = pageInsets.safeInsets.asPaddingValues(),
                                    navigationItems = navigationItems,
                                    mainPagerState = mainPagerState,
                                    primaryTitleAlpha = primaryTitleAlpha,
                                )
                            }
                            if (useGlassSidebar) {
                                PersistentGlassNavigationRail(
                                    navigationItems = navigationItems,
                                    mainPagerState = mainPagerState,
                                    backdrop = sidebarBackdrop,
                                    state = sidebarState,
                                    onBeforeNavigate = { suppressNavTransition = true },
                                )
                            }
                        }
                    }
                    TopLayerHost(topLayer)
                }
            }
        }
    }

    CapturedAnimatedVisibility(
        visible = appState.showFPSMonitor,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        FPSMonitor(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .captionBarPadding(),
        )
    }
}

@Composable
private fun AppNavHost(
    backStack: NavBackStack,
    navigator: Navigator,
    transition: NavTransition,
    effects: NavDisplayEffects,
    swipeBackDirection: NavSwipeDirection,
    padding: PaddingValues,
    navigationItems: List<NavigationItem>,
    mainPagerState: MainPagerState,
    primaryTitleAlpha: () -> Float,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { navigator.pop() },
        transition = transition,
        effects = effects,
    ) {
        entry<Route.Main>(swipeDismiss = swipeBackDirection) {
            CompositionLocalProvider(
                LocalTopAppBarTitleAlpha provides primaryTitleAlpha,
                LocalTopAppBarCenterObstructionStart provides DefaultTopAppBarCenterObstructionStart,
            ) {
                Home(
                    navigationItems = navigationItems,
                    mainPagerState = mainPagerState,
                )
            }
        }
        entry<Route.Component>(swipeDismiss = swipeBackDirection) { route ->
            ComponentPage(demo = route.demo, padding = padding)
        }
        entry<Route.PullToRefresh>(swipeDismiss = swipeBackDirection) {
            PullToRefreshPage(padding = padding)
        }
        entry<Route.About>(swipeDismiss = swipeBackDirection) {
            AboutPage(padding = padding)
        }
        entry<Route.ThemeSettings>(swipeDismiss = swipeBackDirection) {
            ThemeSettingsPage(padding = padding)
        }
        entry<Route.License>(swipeDismiss = swipeBackDirection) {
            LicensePage(padding = padding)
        }
        entry<Route.Navigation>(swipeDismiss = swipeBackDirection) { route ->
            val index = remember(route) {
                backStack.filter { it is Route.Navigation || it is Route.ExpansionDetail }.indexOf(route) + 1
            }
            NavTestPage(
                index = index,
                padding = padding,
                route = route,
            )
        }
        entry<Route.MultiScaffold>(swipeDismiss = swipeBackDirection) {
            MultiScaffoldTestPage(padding = padding)
        }
        entry<Route.NestedNav>(swipeDismiss = swipeBackDirection) {
            NestedNavTestPage(padding = padding)
        }
        entry<Route.ExpansionDetail>(transition = NavTransitions.Expansion, swipeDismiss = swipeBackDirection) { route ->
            val index = remember(route) {
                backStack.filter { it is Route.Navigation || it is Route.ExpansionDetail }.indexOf(route) + 1
            }
            NavTestPage(index = index, padding = padding, route = route)
        }
        entry<Route.OverscrollLoadMore>(swipeDismiss = swipeBackDirection) {
            OverscrollLoadMorePage(padding = padding)
        }
        entry<Route.Glass>(swipeDismiss = swipeBackDirection) {
            GlassPage(padding = padding)
        }
    }
}

/** Persistent rail for wide layouts. */
@Composable
private fun PersistentNavigationRail(
    navigationItems: List<NavigationItem>,
    mainPagerState: MainPagerState,
) {
    val appState = LocalAppState.current
    val navigator = LocalNavigator.current
    val page = mainPagerState.selectedPage
    val expandRail = shouldExpandNavigationRail()
    val railState = rememberNavigationRailState(
        initialValue = if (expandRail) NavigationRailValue.Expanded else NavigationRailValue.Collapsed,
    )
    LaunchedEffect(expandRail) {
        if (expandRail) railState.expand() else railState.collapse()
    }
    NavigationRail(
        modifier = Modifier
            .background(MiuixTheme.colorScheme.surface)
            .windowInsetsPadding(LocalAppPageInsets.current.windowInsets.only(WindowInsetsSides.Start + WindowInsetsSides.Vertical)),
        defaultWindowInsetsPadding = false,
        state = railState,
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationRailItem(
                selected = page == index,
                onClick = {
                    navigator.popUntil { it is Route.Main }
                    mainPagerState.animateToPage(index)
                },
                icon = item.icon,
                label = item.label,
                badge = navigationItemBadge(index, appState.showNavigationBarBadge),
            )
        }
    }
}

/** Floating glass rail over the page's start edge. */
@Composable
private fun PersistentGlassNavigationRail(
    navigationItems: List<NavigationItem>,
    mainPagerState: MainPagerState,
    backdrop: LayerBackdrop?,
    state: GlassNavigationRailState,
    onBeforeNavigate: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val appState = LocalAppState.current
    val navigator = LocalNavigator.current
    val currentRoute = navigator.backStack.lastOrNull()
    val page = mainPagerState.selectedPage
    val items = remember(navigationItems, appState.showNavigationBarBadge) {
        navigationItems.mapIndexed { index, item ->
            GlassNavigationItem(
                icon = item.icon,
                label = item.label,
                badge = navigationItemBadge(index, appState.showNavigationBarBadge),
            )
        }
    }
    val secondaryRoutes = remember {
        listOf(
            Route.Component(ComponentDemo.Preferences),
            Route.Glass,
        )
    }
    val secondaryItems = remember {
        listOf(
            GlassNavigationItem(icon = MiuixIcons.Symbol.DoodlePen.toImageVector(), label = "Preferences"),
            GlassNavigationItem(icon = MiuixIcons.Symbol.Hd.toImageVector(), label = "Glass"),
        )
    }
    val selectedIndex = when (currentRoute) {
        is Route.Main -> page

        else -> {
            val secondaryIndex = secondaryRoutes.indexOf(currentRoute)
            if (secondaryIndex >= 0) items.size + secondaryIndex else -1
        }
    }
    GlassNavigationRail(
        items = items,
        selectedIndex = selectedIndex,
        onSelect = { index ->
            if (index < items.size) {
                if (currentRoute !is Route.Main) {
                    onBeforeNavigate()
                    navigator.popUntil { route -> route is Route.Main }
                }
                mainPagerState.animateToPage(index)
            } else {
                val targetRoute = secondaryRoutes[index - items.size]
                if (currentRoute != targetRoute) {
                    onBeforeNavigate()
                    navigator.popUntil { route -> route is Route.Main }
                    navigator.push(targetRoute)
                }
            }
        },
        backdrop = backdrop,
        modifier = modifier.windowInsetsPadding(LocalAppPageInsets.current.windowInsets),
        state = state,
        secondaryItems = secondaryItems,
        defaultWindowInsetsPadding = false,
    )
}

@Composable
private fun Home(
    navigationItems: List<NavigationItem>,
    mainPagerState: MainPagerState,
) {
    val isWideScreen = LocalIsWideScreen.current
    val pagerContent = remember(mainPagerState.pagerState) {
        movableContentOf<PaddingValues, Modifier> { pagerPadding, pagerModifier ->
            AppPager(
                padding = pagerPadding,
                pagerState = mainPagerState.pagerState,
                modifier = pagerModifier,
            )
        }
    }
    Scaffold {
        if (isWideScreen) {
            WideScreenContent(
                pagerContent = pagerContent,
            )
        } else {
            CompactScreenLayout(
                navigationItems = navigationItems,
                mainPagerState = mainPagerState,
                pagerContent = pagerContent,
            )
        }
    }
}

@Composable
private fun WideScreenContent(
    pagerContent: @Composable (PaddingValues, Modifier) -> Unit,
) {
    val appState = LocalAppState.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentWindowInsets = LocalAppPageInsets.current.contentInsets,
        floatingActionButton = {
            FloatingActionButton(
                show = appState.showFloatingActionButton,
                style = appState.floatingActionButtonStyle,
            )
        },
        floatingActionButtonPosition = appState.floatingActionButtonPosition.toFabPosition(),
        floatingToolbar = {
            FloatingToolbar(
                showFloatingToolbar = appState.showFloatingToolbar,
                floatingToolbarOrientation = appState.floatingToolbarOrientation,
                style = appState.floatingToolbarStyle,
            )
        },
        floatingToolbarPosition = appState.floatingToolbarPosition.toToolbarPosition(),
    ) { padding ->
        val pagePadding by remember(padding) {
            derivedStateOf {
                PaddingValues(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding(),
                )
            }
        }
        // Pages own horizontal safe areas; the pager stays full width for backdrop recording.
        pagerContent(
            pagePadding,
            Modifier.imePadding(),
        )
    }
}

@Composable
private fun CompactScreenLayout(
    navigationItems: List<NavigationItem>,
    mainPagerState: MainPagerState,
    pagerContent: @Composable (PaddingValues, Modifier) -> Unit,
) {
    val appState = LocalAppState.current
    val backdrop = rememberBlurBackdrop()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = LocalAppPageInsets.current.safeInsets,
        bottomBar = {
            NavigationBar(
                navigationItems = navigationItems,
                mainPagerState = mainPagerState,
                backdrop = backdrop,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                show = appState.showFloatingActionButton,
                style = appState.floatingActionButtonStyle,
                backdrop = backdrop,
            )
        },
        floatingActionButtonPosition = appState.floatingActionButtonPosition.toFabPosition(),
        floatingToolbar = {
            FloatingToolbar(
                showFloatingToolbar = appState.showFloatingToolbar,
                floatingToolbarOrientation = appState.floatingToolbarOrientation,
                style = appState.floatingToolbarStyle,
                backdrop = backdrop,
            )
        },
        floatingToolbarPosition = appState.floatingToolbarPosition.toToolbarPosition(),
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().then(if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier)) {
            pagerContent(
                innerPadding,
                Modifier.imePadding(),
            )
        }
    }
}

@Composable
private fun NavigationBar(
    navigationItems: List<NavigationItem>,
    mainPagerState: MainPagerState,
    backdrop: LayerBackdrop?,
) {
    val appState = LocalAppState.current
    val blurActive = appState.enableBlur && backdrop != null && isRuntimeShaderSupported()
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface
    val page = mainPagerState.selectedPage

    AnimatedVisibility(
        visible = appState.showNavigationBar,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
    ) {
        AnimatedVisibility(
            visible = !appState.useFloatingNavigationBar,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Top),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top),
        ) {
            Box(
                modifier = Modifier
                    .then(
                        if (blurActive) {
                            Modifier.textureBlur(
                                backdrop = backdrop,
                                shape = RectangleShape,
                                blurRadius = 25f,
                                colors = BlurDefaults.blurColors(
                                    blendColors = listOf(
                                        BlendColorEntry(color = MiuixTheme.colorScheme.surface.copy(0.8f)),
                                    ),
                                ),
                            )
                        } else {
                            Modifier
                        },
                    )
                    .background(barColor)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {},
                    ),
            ) {
                NavigationBar(
                    modifier = Modifier,
                    color = barColor,
                    mode = NavigationBarDisplayMode.entries[appState.navigationBarMode],
                ) {
                    navigationItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = page == index,
                            onClick = { mainPagerState.animateToPage(index) },
                            icon = item.icon,
                            label = item.label,
                            badge = navigationItemBadge(index, appState.showNavigationBarBadge),
                        )
                    }
                }
            }
        }
        if (appState.useFloatingNavigationBar) {
            val floatingBarColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surfaceContainer
            val floatingBarShape = RoundedCornerShape(FloatingToolbarDefaults.CornerRadius)
            val isDark = isInDarkTheme()
            val floatingStroke: EdgeStroke = remember(isDark) {
                if (isDark) EdgeStrokes.MiddleDark else EdgeStrokes.MiddleLight
            }
            Box {
                when (appState.floatingNavigationBarStyle) {
                    1 -> {
                        val glassItems = remember(navigationItems, appState.showNavigationBarBadge) {
                            navigationItems.mapIndexed { index, item ->
                                GlassNavigationItem(
                                    icon = item.icon,
                                    label = item.label,
                                    badge = navigationItemBadge(index, appState.showNavigationBarBadge),
                                )
                            }
                        }
                        GlassNavigationBar(
                            items = glassItems,
                            selectedIndex = page,
                            onSelect = { mainPagerState.animateToPage(it) },
                            backdrop = if (blurActive) backdrop else null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                        )
                    }

                    2 -> {
                        IosLiquidGlassNavigationBar(
                            items = navigationItems,
                            selectedIndex = page,
                            onItemClick = { mainPagerState.animateToPage(it) },
                            backdrop = if (blurActive) backdrop else null,
                            isBlurActive = blurActive,
                            badge = { navigationItemBadge(it, appState.showNavigationBarBadge) },
                        )
                    }

                    else -> {
                        FloatingNavigationBar(
                            modifier = if (blurActive) {
                                Modifier
                                    .textureBlur(
                                        backdrop = backdrop,
                                        shape = floatingBarShape,
                                        blurRadius = 25f,
                                        colors = BlurDefaults.blurColors(
                                            blendColors = listOf(
                                                BlendColorEntry(color = MiuixTheme.colorScheme.surfaceContainer.copy(0.6f)),
                                            ),
                                        ),
                                    )
                                    .edgeStroke(
                                        shape = floatingBarShape,
                                        stroke = floatingStroke,
                                    )
                            } else {
                                Modifier
                            },
                            color = floatingBarColor,
                            horizontalAlignment = FloatingNavigationBarAlignment.fromInt(appState.floatingNavigationBarPosition)
                                .toAlignment(),
                        ) {
                            navigationItems.forEachIndexed { index, item ->
                                FloatingNavigationBarItem(
                                    selected = page == index,
                                    onClick = { mainPagerState.animateToPage(index) },
                                    icon = item.icon,
                                    label = item.label,
                                    badge = navigationItemBadge(index, appState.showNavigationBarBadge),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FloatingActionButton(
    show: Boolean,
    style: Int,
    backdrop: Backdrop? = null,
) {
    if (show) {
        val uriHandler = LocalUriHandler.current
        if (style == 1) {
            GlassFloatingActionButton(
                onClick = {
                    uriHandler.openUri(UIConstants.GITHUB_URL)
                },
                backdrop = backdrop,
            ) {
                Icon(
                    imageVector = MiuixIcons.Link,
                    tint = MiuixTheme.colorScheme.onPrimary,
                    contentDescription = "GitHub",
                )
            }
        } else {
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
}

@Composable
private fun FloatingToolbar(
    showFloatingToolbar: Boolean,
    floatingToolbarOrientation: Int,
    style: Int,
    backdrop: Backdrop? = null,
) {
    CapturedAnimatedVisibility(
        visible = showFloatingToolbar,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        val toolbarContent: @Composable () -> Unit = {
            AnimatedContent(
                targetState = floatingToolbarOrientation,
            ) { orientation ->
                val iconTint = if (style == 1) {
                    MiuixTheme.colorScheme.onSurface
                } else {
                    MiuixTheme.colorScheme.onPrimary
                }
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
        if (style == 1) {
            GlassFloatingToolbar(
                backdrop = backdrop,
                cornerRadius = 20.dp,
            ) {
                toolbarContent()
            }
        } else {
            FloatingToolbar(
                color = MiuixTheme.colorScheme.primary,
                cornerRadius = 20.dp,
            ) {
                toolbarContent()
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

private fun FloatingNavigationBarAlignment.toAlignment(): Alignment.Horizontal = when (this) {
    FloatingNavigationBarAlignment.Center -> CenterHorizontally
    FloatingNavigationBarAlignment.Start -> Alignment.Start
    FloatingNavigationBarAlignment.End -> Alignment.End
}

/** Optional demo badge for a navigation item. */
private fun navigationItemBadge(index: Int, show: Boolean): (@Composable () -> Unit)? = when {
    !show -> null
    index == 1 -> ({ Badge { Text("8") } })
    index == 3 -> ({ Badge() })
    else -> null
}

@Composable
fun AppPager(
    padding: PaddingValues,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val appState = LocalAppState.current
    val flingBehavior = PagerDefaults.flingBehavior(
        state = pagerState,
        snapAnimationSpec = PagerNavigationSpringSpec,
    )
    val nativeConnection = PagerDefaults.pageNestedScrollConnection(pagerState, Orientation.Horizontal)
    HorizontalPager(
        state = pagerState,
        modifier = modifier.pagerGestureOverride(
            pagerState = pagerState,
            flingBehavior = flingBehavior,
            mode = appState.pagerInterceptionMode,
            enabled = appState.enablePageUserScroll,
        ),
        userScrollEnabled = appState.enablePageUserScroll &&
            appState.pagerInterceptionMode != PagerInterceptionMode.CrossAxis.ordinal,
        pageNestedScrollConnection = if (
            appState.pagerInterceptionMode == PagerInterceptionMode.CrossAxis.ordinal
        ) {
            PagerGestureNestedScrollConnection
        } else {
            nativeConnection
        },
        verticalAlignment = Alignment.Top,
        flingBehavior = flingBehavior,
        beyondViewportPageCount = 1,
        pageContent = { page ->
            // Pages out of view don't follow a sidebar's morph.
            val hidden = remember(pagerState, page) {
                computedStateOf {
                    abs(page - pagerState.currentPage - pagerState.currentPageOffsetFraction) >= 1f
                }
            }
            val insets = LocalAppPageInsets.current
            val pageInsets = remember(insets, hidden) { insets.whenHidden { hidden.value } }
            val frozenConstraints = remember(hidden) { Modifier.keepConstraintsWhile { hidden.value } }
            CompositionLocalProvider(LocalAppPageInsets provides pageInsets) {
                Box(modifier = frozenConstraints) {
                    AppPage(page = page, padding = padding)
                }
            }
        },
    )
}

/** Measures with the last constraints seen while [frozen] was false. */
private fun Modifier.keepConstraintsWhile(frozen: () -> Boolean): Modifier {
    var last: Constraints? = null
    return layout { measurable, constraints ->
        val held = last
        val used = if (held != null && frozen()) held else constraints.also { last = it }
        val placeable = measurable.measure(used)
        layout(constraints.constrainWidth(placeable.width), constraints.constrainHeight(placeable.height)) {
            placeable.place(0, 0)
        }
    }
}

@Composable
private fun AppPage(
    page: Int,
    padding: PaddingValues,
) {
    when (page) {
        UIConstants.MAIN_PAGE_INDEX -> MainPage(padding = padding)
        UIConstants.ICON_PAGE_INDEX -> IconsPage(padding = padding)
        UIConstants.COLOR_PAGE_INDEX -> ColorPage(padding = padding)
        UIConstants.TEXT_STYLE_PAGE_INDEX -> TextStylePage(padding = padding)
        else -> SettingsPage(padding = padding)
    }
}

@Composable
private fun MainScreenBackHandler(
    mainState: MainPagerState,
    navigator: Navigator,
) {
    val isPagerBackHandlerEnabled by remember {
        computedStateOf {
            navigator.current() is Route.Main && navigator.backStackSize() == 1 && mainState.selectedPage != 0
        }
    }

    val navEventState = rememberNavigationEventState(NavigationEventInfo.None)

    NavigationBackHandler(
        state = navEventState,
        isBackEnabled = isPagerBackHandlerEnabled,
        onBackCompleted = {
            mainState.animateToPage(0)
        },
    )
}

@Stable
class MainPagerState(
    val pagerState: PagerState,
    private val coroutineScope: CoroutineScope,
) {
    var selectedPage by mutableIntStateOf(pagerState.currentPage)
        private set

    var isNavigating by mutableStateOf(false)
        private set
    var snapTransitions by mutableStateOf(false)

    private var navJob: Job? = null

    fun animateToPage(targetIndex: Int) {
        if (targetIndex == selectedPage) return

        navJob?.cancel()

        selectedPage = targetIndex
        isNavigating = true

        navJob = coroutineScope.launch {
            val myJob = coroutineContext.job
            try {
                if (snapTransitions) {
                    pagerState.scrollToPage(targetIndex)
                } else {
                    pagerState.springAnimateToPage(targetIndex)
                }
            } finally {
                if (navJob == myJob) {
                    isNavigating = false
                    if (pagerState.currentPage != targetIndex) {
                        selectedPage = pagerState.currentPage
                    }
                }
            }
        }
    }

    fun syncPage() {
        if (!isNavigating && selectedPage != pagerState.currentPage) {
            selectedPage = pagerState.currentPage
        }
    }
}

@Composable
fun rememberMainPagerState(
    pagerState: PagerState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): MainPagerState = remember(pagerState, coroutineScope) {
    MainPagerState(pagerState, coroutineScope)
}

private val InstantNavTransition: NavTransition = navGraphicsTransition(
    opaqueDepth = 1f,
    dismissDirection = NavSwipeDirection.None,
    motion = NavMotion(
        programmatic = NavSettleSpec.Tween(durationMillis = 0, easing = LinearEasing),
    ),
) {}
