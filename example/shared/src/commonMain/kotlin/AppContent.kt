// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import component.liquid.IosLiquidGlassNavigationBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
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
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.LayerBackdrop
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
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
import top.yukonga.miuix.kmp.icon.glass.EditSubtitle
import top.yukonga.miuix.kmp.icon.glass.HomeOutline
import top.yukonga.miuix.kmp.icon.glass.ImageOutline
import top.yukonga.miuix.kmp.icon.glass.SettingsOutline
import top.yukonga.miuix.kmp.icon.glass.Theme
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
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.PagerGestureNestedScrollConnection
import top.yukonga.miuix.kmp.utils.PagerInterceptionMode
import top.yukonga.miuix.kmp.utils.PagerNavigationSpringSpec
import top.yukonga.miuix.kmp.utils.pagerGestureOverride
import top.yukonga.miuix.kmp.utils.springAnimateToPage
import ui.isInDarkTheme
import utils.FPSMonitor
import utils.LocalTopAppBarTitleAlpha
import utils.LocalTopLayer
import utils.LocalWideScreenContentEndInset
import utils.LocalWideScreenContentInset
import utils.LocalWideScreenSearchTopInset
import utils.TopLayerHost
import utils.TopLayerState
import utils.rememberBlurBackdrop
import utils.shouldExpandNavigationRail
import utils.shouldShowSplitPane
import kotlin.math.roundToInt

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
val LocalIsWideScreen = staticCompositionLocalOf { false }
val LocalMainPagerState = staticCompositionLocalOf<MainPagerState> { error("LocalMainPagerState not provided") }

@Composable
fun AppContent(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current

    val pagerState = rememberPagerState(pageCount = { UIConstants.PAGE_COUNT })
    val mainPagerState = rememberMainPagerState(pagerState)
    LaunchedEffect(mainPagerState.pagerState.currentPage) {
        mainPagerState.syncPage()
    }

    // Explicit Route type preserves the sealed hierarchy for save/restore.
    val backStack = rememberNavBackStack<Route>(Route.Main)
    val navigator = remember { Navigator(backStack) }

    val navigationItems = remember {
        listOf(
            NavigationItem(UIConstants.PAGE_TITLES[0], MiuixIcons.Glass.HomeOutline),
            NavigationItem(UIConstants.PAGE_TITLES[1], MiuixIcons.Glass.ImageOutline),
            NavigationItem(UIConstants.PAGE_TITLES[2], MiuixIcons.Glass.Theme),
            NavigationItem(UIConstants.PAGE_TITLES[3], MiuixIcons.Glass.EditSubtitle),
            NavigationItem(UIConstants.PAGE_TITLES[4], MiuixIcons.Glass.SettingsOutline),
        )
    }

    MainScreenBackHandler(mainPagerState, navigator)

    val isWideScreen = shouldShowSplitPane()
    val hasNavigationRail = isWideScreen && appState.showNavigationBar
    // Plain rails occupy a column; glass rails overlay the page.
    val useGlassSidebar = hasNavigationRail && appState.navigationRailStyle == 1
    val expandSidebar = shouldExpandNavigationRail()
    val layoutDirection = LocalLayoutDirection.current
    val displayCutout = WindowInsets.displayCutout
    val systemBars = WindowInsets.systemBars
    val captionBar = WindowInsets.captionBar
    val railInsets = remember(systemBars, captionBar, displayCutout) {
        systemBars.union(captionBar).union(displayCutout)
    }
    val sidebarState = rememberGlassNavigationRailState(initialExpanded = expandSidebar)
    LaunchedEffect(expandSidebar) { sidebarState.expanded = expandSidebar }
    // Reserve the cutout; the expanded rail width is added after it.
    val sidebarInset: Density.() -> Int = if (useGlassSidebar) {
        {
            railInsets.getLeft(this, layoutDirection) +
                ((GlassNavigationRailDefaults.Margin + GlassNavigationRailDefaults.ExpandedWidth).toPx() * sidebarState.progress)
                    .roundToInt()
        }
    } else {
        { 0 }
    }
    val sidebarEndInset: Density.() -> Int = if (useGlassSidebar) {
        { railInsets.getRight(this, layoutDirection) }
    } else {
        { 0 }
    }
    val topAppBarTitleAlpha: () -> Float = remember(useGlassSidebar, sidebarState) {
        if (useGlassSidebar) {
            { sidebarState.progress }
        } else {
            { 1f }
        }
    }
    val sidebarBackdrop = if (useGlassSidebar) rememberBlurBackdrop() else null
    // Blur needs a recorded page layer; otherwise use the surface color.
    val density = LocalDensity.current
    val wideScreenContentInset = remember(useGlassSidebar, density, railInsets, layoutDirection) {
        if (useGlassSidebar) {
            { with(density) { sidebarInset().toDp() } }
        } else {
            { 0.dp }
        }
    }
    val wideScreenContentEndInset = remember(useGlassSidebar, density, railInsets, layoutDirection) {
        if (useGlassSidebar) {
            { with(density) { sidebarEndInset().toDp() } }
        } else {
            { 0.dp }
        }
    }
    val wideScreenSearchTopInset = remember(useGlassSidebar, sidebarState) {
        if (useGlassSidebar) {
            { (GlassNavigationRailDefaults.CollapsedTop + GlassNavigationRailDefaults.CollapsedHeight + 4.dp) * (1f - sidebarState.progress) }
        } else {
            { 0.dp }
        }
    }
    val topLayer = remember { TopLayerState() }

    CompositionLocalProvider(
        LocalNavigator provides navigator,
        LocalMainPagerState provides mainPagerState,
        LocalIsWideScreen provides isWideScreen,
        LocalTopAppBarTitleAlpha provides topAppBarTitleAlpha,
        LocalWideScreenContentInset provides wideScreenContentInset,
        LocalWideScreenContentEndInset provides wideScreenContentEndInset,
        LocalWideScreenSearchTopInset provides wideScreenSearchTopInset,
    ) {
        // Match platform screen corners; split panes have no outer corner.
        val navCornerRadius = if (isWideScreen) 0.dp else rememberNavSystemCornerRadius()
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

        // Select the configured transition.
        val navTransition = if (isCrossActivityStyle) CrossActivityTransition else NavTransitions.MiuixDefault

        // Keep overlays above page content and the glass rail.
        Scaffold(
            containerColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0),
        ) {
            CompositionLocalProvider(LocalTopLayer provides topLayer) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Keep NavDisplay at one site across chrome changes.
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
                                            WindowInsets.displayCutout
                                                .union(WindowInsets.navigationBars)
                                                .only(WindowInsetsSides.Start),
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
                                    padding = padding,
                                    navigationItems = navigationItems,
                                    mainPagerState = mainPagerState,
                                )
                            }
                            if (useGlassSidebar) {
                                PersistentGlassNavigationRail(
                                    navigationItems = navigationItems,
                                    mainPagerState = mainPagerState,
                                    backdrop = sidebarBackdrop,
                                    state = sidebarState,
                                )
                            }
                        }
                    }
                    TopLayerHost(topLayer)
                }
            }
        }
    }

    AnimatedVisibility(
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
            Home(
                padding = padding,
                navigationItems = navigationItems,
                mainPagerState = mainPagerState,
            )
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
        entry<Route.License>(swipeDismiss = swipeBackDirection) {
            LicensePage(padding = padding)
        }
        entry<Route.Navigation>(swipeDismiss = swipeBackDirection) { route ->
            val index = backStack.filterIsInstance<Route.Navigation>().indexOf(route) + 1
            NavTestPage(
                index = index,
                padding = padding,
            )
        }
        entry<Route.MultiScaffold>(swipeDismiss = swipeBackDirection) {
            MultiScaffoldTestPage(padding = padding)
        }
        entry<Route.NestedNav>(swipeDismiss = swipeBackDirection) {
            NestedNavTestPage(padding = padding)
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
    modifier: Modifier = Modifier,
) {
    val appState = LocalAppState.current
    val navigator = LocalNavigator.current
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
    GlassNavigationRail(
        items = items,
        selectedIndex = page,
        onSelect = {
            // Return to the main route before changing the page.
            navigator.popUntil { route -> route is Route.Main }
            mainPagerState.animateToPage(it)
        },
        backdrop = backdrop,
        modifier = modifier,
        state = state,
    )
}

@Composable
private fun Home(
    padding: PaddingValues,
    navigationItems: List<NavigationItem>,
    mainPagerState: MainPagerState,
) {
    val isWideScreen = LocalIsWideScreen.current
    val layoutDirection = LocalLayoutDirection.current
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
                layoutDirection = layoutDirection,
                pagerContent = pagerContent,
            )
        } else {
            CompactScreenLayout(
                navigationItems = navigationItems,
                padding = padding,
                mainPagerState = mainPagerState,
                pagerContent = pagerContent,
            )
        }
    }
}

@Composable
private fun WideScreenContent(
    layoutDirection: LayoutDirection,
    pagerContent: @Composable (PaddingValues, Modifier) -> Unit,
) {
    val appState = LocalAppState.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        contentWindowInsets =
        WindowInsets.systemBars.union(
            // Plain rails consume the start cutout; glass rails leave it for the page pane.
            if (appState.showNavigationBar && appState.navigationRailStyle == 0) {
                WindowInsets.displayCutout.exclude(
                    WindowInsets.displayCutout.only(WindowInsetsSides.Start),
                )
            } else {
                WindowInsets.displayCutout
            },
        ),
        floatingActionButton = {
            FloatingActionButton(show = appState.showFloatingActionButton)
        },
        floatingActionButtonPosition = appState.floatingActionButtonPosition.toFabPosition(),
        floatingToolbar = {
            FloatingToolbar(
                showFloatingToolbar = appState.showFloatingToolbar,
                floatingToolbarOrientation = appState.floatingToolbarOrientation,
            )
        },
        floatingToolbarPosition = appState.floatingToolbarPosition.toToolbarPosition(),
    ) { padding ->
        pagerContent(
            PaddingValues(top = padding.calculateTopPadding()),
            Modifier
                .imePadding()
                .padding(end = padding.calculateEndPadding(layoutDirection)),
        )
    }
}

@Composable
private fun CompactScreenLayout(
    navigationItems: List<NavigationItem>,
    padding: PaddingValues,
    mainPagerState: MainPagerState,
    pagerContent: @Composable (PaddingValues, Modifier) -> Unit,
) {
    val surfaceColor = MiuixTheme.colorScheme.surface
    val appState = LocalAppState.current
    val backdrop = rememberLayerBackdrop {
        drawRect(surfaceColor)
        drawContent()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                navigationItems = navigationItems,
                mainPagerState = mainPagerState,
                backdrop = backdrop,
                modifier = Modifier.padding(bottom = padding.calculateBottomPadding()),
            )
        },
        floatingActionButton = {
            FloatingActionButton(show = appState.showFloatingActionButton)
        },
        floatingActionButtonPosition = appState.floatingActionButtonPosition.toFabPosition(),
        floatingToolbar = {
            FloatingToolbar(
                showFloatingToolbar = appState.showFloatingToolbar,
                floatingToolbarOrientation = appState.floatingToolbarOrientation,
            )
        },
        floatingToolbarPosition = appState.floatingToolbarPosition.toToolbarPosition(),
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().layerBackdrop(backdrop)) {
            pagerContent(
                innerPadding,
                Modifier
                    .padding(
                        top = padding.calculateTopPadding(),
                        start = padding.calculateStartPadding(LocalLayoutDirection.current),
                        end = padding.calculateEndPadding(LocalLayoutDirection.current),
                    )
                    .imePadding(),
            )
        }
    }
}

@Composable
private fun NavigationBar(
    navigationItems: List<NavigationItem>,
    mainPagerState: MainPagerState,
    backdrop: LayerBackdrop?,
    modifier: Modifier = Modifier,
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
                    )
                    .then(modifier),
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
            Box(modifier = modifier) {
                when (appState.floatingNavigationBarStyle) {
                    1 -> {
                        val glassItems = remember(navigationItems) {
                            navigationItems.map { GlassNavigationItem(icon = it.icon, label = it.label) }
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
        beyondViewportPageCount = 4,
        pageContent = { page ->
            when (page) {
                UIConstants.MAIN_PAGE_INDEX -> MainPage(padding = padding)
                UIConstants.ICON_PAGE_INDEX -> IconsPage(padding = padding)
                UIConstants.COLOR_PAGE_INDEX -> ColorPage(padding = padding)
                UIConstants.TEXT_STYLE_PAGE_INDEX -> TextStylePage(padding = padding)
                else -> SettingsPage(padding = padding)
            }
        },
    )
}

@Composable
private fun MainScreenBackHandler(
    mainState: MainPagerState,
    navigator: Navigator,
) {
    val isPagerBackHandlerEnabled by remember {
        derivedStateOf {
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

    private var navJob: Job? = null

    fun animateToPage(targetIndex: Int) {
        if (targetIndex == selectedPage) return

        navJob?.cancel()

        selectedPage = targetIndex
        isNavigating = true

        navJob = coroutineScope.launch {
            val myJob = coroutineContext.job
            try {
                pagerState.springAnimateToPage(targetIndex)
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
