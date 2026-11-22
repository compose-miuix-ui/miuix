// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import component.GlassBottomSheetDemo
import component.GlassDemoDefaults
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassBottomSheetDefaults
import top.yukonga.miuix.kmp.glass.GlassDialog
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassDialogButtonRole
import top.yukonga.miuix.kmp.glass.GlassDialogDefaults
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationBarDefaults
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassPopupDefaults
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.GlassPopupSizing
import top.yukonga.miuix.kmp.glass.GlassSearchOverlay
import top.yukonga.miuix.kmp.glass.GlassSecondaryPopup
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRowDefaults
import top.yukonga.miuix.kmp.glass.GlassTabRow
import top.yukonga.miuix.kmp.glass.GlassTabRowDefaults
import top.yukonga.miuix.kmp.glass.GlassTextField
import top.yukonga.miuix.kmp.glass.GlassTopAppBar
import top.yukonga.miuix.kmp.glass.GlassTopAppBarDefaults
import top.yukonga.miuix.kmp.glass.GlassTransformPopup
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
import top.yukonga.miuix.kmp.glass.glassSearchBarTransition
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.glass.rememberGlassSearchBarAlpha
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.glass.Add
import top.yukonga.miuix.kmp.icon.glass.ChevronBackward
import top.yukonga.miuix.kmp.icon.glass.Close
import top.yukonga.miuix.kmp.icon.glass.Edit
import top.yukonga.miuix.kmp.icon.glass.HomeOutline
import top.yukonga.miuix.kmp.icon.glass.ImageOutline
import top.yukonga.miuix.kmp.icon.glass.Search
import top.yukonga.miuix.kmp.icon.glass.SettingsOutline
import top.yukonga.miuix.kmp.layout.CascadingPopupDefaults
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.SliderPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.blur_test
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme
import utils.LocalAppPageInsets
import utils.LocalTopAppBarCenterObstructionStart
import utils.TopLayer
import utils.appPageHorizontalInsets
import utils.appPageTopBarInsets
import utils.pageContentPadding
import utils.rememberAppSafePadding

private val NavItems: List<GlassNavigationItem> = listOf(
    GlassNavigationItem(MiuixIcons.Glass.HomeOutline, "Home"),
    GlassNavigationItem(MiuixIcons.Glass.Add, "Add"),
    GlassNavigationItem(MiuixIcons.Glass.ImageOutline, "Image"),
    GlassNavigationItem(MiuixIcons.Glass.SettingsOutline, "Settings"),
)

private val TabRowPlacements = listOf("TopAppBar", "List")
private const val TABS_IN_TOP_BAR = 0
private const val TABS_IN_LIST = 1
private const val OVERLAY_NONE = 0
private const val OVERLAY_POPUP = 1
private const val OVERLAY_DIALOG = 2
private const val OVERLAY_BOTTOM_SHEET = 3

private val LogLevels = listOf("Verbose", "Debug", "Info", "Warn", "Error")

private val SortOrders = listOf("Updated", "Created", "Title")

/**
 * Demonstrates glass components on solid or wallpaper backgrounds.
 *
 * @param padding Window padding supplied by the host scaffold.
 */
@Composable
fun GlassPage(padding: PaddingValues) {
    val navigator = LocalNavigator.current
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val horizontalPadding = GlassTopAppBarDefaults.HorizontalPadding
    val isInDark = isInDarkTheme()
    val safePadding = rememberAppSafePadding()
    val backdrop = rememberLayerBackdrop()
    val primaryPopupBackdrop = rememberLayerBackdrop()
    val wallpaperBackdrop = rememberLayerBackdrop()
    val pageBackdrop = rememberLayerBackdrop()
    val secondaryBackdrop = remember(pageBackdrop, primaryPopupBackdrop) {
        object : Backdrop {
            override val isCoordinatesDependent = true
            override val offsetResidualX: Float get() = pageBackdrop.offsetResidualX
            override val offsetResidualY: Float get() = pageBackdrop.offsetResidualY

            override fun DrawScope.drawBackdrop(
                density: Density,
                coordinates: LayoutCoordinates?,
                layerBlock: (GraphicsLayerScope.() -> Unit)?,
                downscaleFactor: Float,
            ) {
                with(pageBackdrop) { drawBackdrop(density, coordinates, layerBlock, downscaleFactor) }
                with(primaryPopupBackdrop) { drawBackdrop(density, coordinates, layerBlock, downscaleFactor) }
            }
        }
    }
    val scrollBehavior = MiuixScrollBehavior()
    val listState = rememberLazyListState()
    val contentUnderTopBar by remember(listState, scrollBehavior) {
        computedStateOf { listState.canScrollBackward || scrollBehavior.state.overscrollOffset < -1f }
    }
    val tabSurfaceAlpha = if (contentUnderTopBar) 1f else 0f

    var materialIndex by remember { mutableIntStateOf(0) }
    var strokeIndex by remember { mutableIntStateOf(2) }
    var cornerRadius by remember { mutableFloatStateOf(26f) }
    var smoothing by remember { mutableFloatStateOf(1f) }
    var alpha by remember { mutableFloatStateOf(1f) }
    var overlayIndex by remember { mutableIntStateOf(OVERLAY_NONE) }
    var tabRowPlacement by remember { mutableIntStateOf(TABS_IN_LIST) }
    var topAppBarProgressiveBlur by remember { mutableStateOf(true) }
    var navIndex by remember { mutableIntStateOf(0) }
    var navigationBarSearchButton by remember(navIndex) { mutableStateOf(navIndex == 0 || navIndex == 2) }
    var primaryTab by remember { mutableIntStateOf(0) }
    var neutralTab by remember { mutableIntStateOf(0) }
    var joinedTab by remember { mutableIntStateOf(0) }
    var wallpaper by remember { mutableStateOf(false) }
    val menuAnchor = rememberGlassPopupAnchor()
    var logLevel by remember { mutableIntStateOf(2) }

    val material = GlassDemoDefaults.material(materialIndex)
    val style = GlassDefaults.style(isInDark)
    val stroke = GlassDemoDefaults.stroke(strokeIndex, isInDark)
    val popupVisuals = GlassPopupDefaults.visuals(
        style = style,
        alpha = alpha,
        stroke = stroke,
        material = material ?: GlassPopupDefaults.material(),
    )
    var searchExpanded by remember { mutableStateOf(false) }
    val searchBarAlpha = rememberGlassSearchBarAlpha(searchExpanded)
    var submenu by remember { mutableStateOf(false) }
    var submenuPresent by remember { mutableStateOf(false) }
    var submenuAnchor by remember { mutableStateOf(Rect.Zero) }
    val latestSubmenuAnchor = remember {
        object {
            var rect = Rect.Zero
        }
    }
    var menuWidth by remember { mutableStateOf(0.dp) }
    var sortOrder by remember { mutableIntStateOf(0) }
    val openSearch: () -> Unit = {
        submenu = false
        overlayIndex = OVERLAY_NONE
        searchExpanded = true
    }
    val density = LocalDensity.current
    val arrowTurn = CascadingPopupDefaults.arrowRotation(LocalLayoutDirection.current)
    val submenuArrowState = animateFloatAsState(
        targetValue = if (submenu) arrowTurn else 0f,
        animationSpec = CascadingPopupDefaults.arrowSpring(submenu),
    )
    val tabsInTopBar = tabRowPlacement == TABS_IN_TOP_BAR
    var previousTabsInTopBar by remember { mutableStateOf(tabsInTopBar) }
    LaunchedEffect(tabsInTopBar) {
        if (previousTabsInTopBar == tabsInTopBar) return@LaunchedEffect
        previousTabsInTopBar = tabsInTopBar
        listState.scrollToItem(0)
        scrollBehavior.state.heightOffset = 0f
        scrollBehavior.state.contentOffset = 0f
    }
    // List tabs sample the wallpaper to avoid sampling their own recording layer.
    val tabBackdrop = if (tabsInTopBar) backdrop else wallpaperBackdrop
    val tabRows: @Composable (Modifier) -> Unit = { tabModifier ->
        Column(modifier = tabModifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            GlassTabRow(
                tabs = listOf("Sound", "Haptics"),
                selectedIndex = primaryTab,
                onSelect = { primaryTab = it },
                backdrop = tabBackdrop,
                style = style,
                alpha = alpha,
                surfaceAlpha = if (tabsInTopBar) tabSurfaceAlpha else 0f,
                stroke = stroke,
                material = material ?: GlassTabRowDefaults.material(),
            )
            GlassTabRow(
                tabs = listOf("Home", "Widget", "Theme", "Paper"),
                selectedIndex = neutralTab,
                onSelect = { neutralTab = it },
                backdrop = tabBackdrop,
                style = style,
                alpha = alpha,
                surfaceAlpha = if (tabsInTopBar) tabSurfaceAlpha else 0f,
                stroke = stroke,
                material = material ?: GlassTabRowDefaults.material(),
                height = GlassTabRowDefaults.NeutralHeight,
                colors = GlassTabRowDefaults.neutralColors(),
            )
            GlassSegmentedTabRow(
                tabs = listOf("Privacy", "Security"),
                selectedIndex = joinedTab,
                onSelect = { joinedTab = it },
                backdrop = tabBackdrop,
                style = style,
                alpha = alpha,
                surfaceAlpha = if (tabsInTopBar) tabSurfaceAlpha else 0f,
                stroke = stroke,
                material = material ?: GlassSegmentedTabRowDefaults.material(),
            )
        }
    }

    GlassPageHost(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { this.alpha = searchBarAlpha.value }
                // Include page bars, excluding overlays and the route scrim.
                .layerBackdrop(pageBackdrop)
                .then(if (searchExpanded) Modifier.clearAndSetSemantics { } else Modifier),
            bottomBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .appPageHorizontalInsets()
                        .padding(start = 24.dp, end = 24.dp, top = 20.dp)
                        .layout { measurable, constraints ->
                            val bottom = maxOf(24.dp, safePadding.calculateBottomPadding() + 8.dp, padding.calculateBottomPadding() + 8.dp)
                                .roundToPx().coerceIn(0, constraints.maxHeight)
                            val placeable = measurable.measure(constraints.offset(vertical = -bottom))
                            layout(placeable.width, placeable.height + bottom) {
                                placeable.placeRelative(0, 0)
                            }
                        },
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    GlassNavigationBar(
                        items = NavItems,
                        selectedIndex = navIndex,
                        onSelect = { navIndex = it },
                        backdrop = backdrop,
                        defaultWindowInsetsPadding = false,
                        modifier = Modifier.layout { measurable, constraints ->
                            val actionWidth = (GlassNavigationBarDefaults.Height + GlassNavigationBarDefaults.ActionSpacing).roundToPx()
                            val available = constraints.maxWidth - actionWidth + (10.dp * (NavItems.size - 1) - 6.dp).roundToPx()
                            val width = if (constraints.hasBoundedWidth && available > 400.dp.roundToPx()) {
                                constraints.constrainWidth(344.dp.roundToPx() + actionWidth)
                            } else {
                                constraints.maxWidth
                            }
                            val placeable = measurable.measure(constraints.copy(minWidth = width, maxWidth = width))
                            layout(placeable.width, placeable.height) { placeable.placeRelative(0, 0) }
                        },
                        style = style,
                        alpha = alpha,
                        stroke = stroke,
                        material = material ?: GlassNavigationBarDefaults.material(),
                        trailingAction = if (navigationBarSearchButton) {
                            {
                                GlassIconButton(
                                    onClick = openSearch,
                                    backdrop = backdrop,
                                    size = GlassNavigationBarDefaults.Height,
                                    style = style,
                                    material = material,
                                    surfaceAlpha = alpha,
                                    stroke = stroke,
                                    shadow = GlassShadows.Float,
                                ) {
                                    Icon(
                                        imageVector = MiuixIcons.Glass.Search,
                                        contentDescription = "Search",
                                        modifier = Modifier.size(28.dp),
                                        tint = MiuixTheme.colorScheme.onSurface,
                                    )
                                }
                            }
                        } else {
                            null
                        },
                    )
                }
            },
            topBar = {
                GlassTopAppBar(
                    title = "Glass Component",
                    contentModifier = Modifier.glassSearchBarTransition(searchExpanded, appState.enableBlur) { 1f }
                        .appPageTopBarInsets(),
                    expandable = !isWideScreen,
                    defaultWindowInsetsPadding = false,
                    isContentScrolled = contentUnderTopBar,
                    centerObstructionStart = LocalTopAppBarCenterObstructionStart.current,
                    backdrop = backdrop,
                    scrollBehavior = scrollBehavior,
                    progressiveBlur = topAppBarProgressiveBlur,
                    material = material ?: GlassTopAppBarDefaults.buttonMaterial(),
                    style = style,
                    alpha = alpha,
                    stroke = stroke,
                    navigationIcon = {
                        GlassIconButton(
                            onClick = { navigator.pop() },
                        ) {
                            Icon(
                                imageVector = MiuixIcons.Glass.ChevronBackward,
                                contentDescription = "Back",
                                modifier = Modifier.size(28.dp),
                                tint = MiuixTheme.colorScheme.onSurface,
                            )
                        }
                    },
                    actions = {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            GlassIconButton(
                                onClick = openSearch,
                            ) {
                                Icon(
                                    imageVector = MiuixIcons.Glass.Search,
                                    contentDescription = "Search",
                                    modifier = Modifier.size(28.dp),
                                    tint = MiuixTheme.colorScheme.onSurface,
                                )
                            }
                            GlassIconButton(
                                onClick = {
                                    submenu = false
                                    overlayIndex = OVERLAY_POPUP
                                },
                                modifier = Modifier.glassPopupAnchor(
                                    anchor = menuAnchor,
                                    cornerRadius = GlassTopAppBarDefaults.ButtonSize / 2,
                                    floating = contentUnderTopBar,
                                ),
                            ) {
                                Icon(
                                    imageVector = MiuixIcons.Glass.SettingsOutline,
                                    contentDescription = "Sort",
                                    modifier = Modifier.size(28.dp).glassPopupAnchorContent(menuAnchor),
                                    tint = MiuixTheme.colorScheme.onSurface,
                                )
                            }
                        }
                    },
                    bottomContent = {
                        if (tabsInTopBar) {
                            tabRows(
                                Modifier
                                    .padding(horizontal = horizontalPadding).padding(top = 6.dp, bottom = 12.dp),
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            GlassDialog(
                show = overlayIndex == OVERLAY_DIALOG,
                backdrop = pageBackdrop,
                title = "Glass Dialog",
                summary = "Blurred background with the dialog's own three-layer material.",
                onDismissRequest = { overlayIndex = OVERLAY_NONE },
                material = material ?: GlassDialogDefaults.material(),
                stroke = stroke,
            ) {
                val dialogTextState = rememberTextFieldState()
                GlassTextField(
                    state = dialogTextState,
                    label = "Input something...",
                    useLabelAsPlaceholder = true,
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    GlassDialogButton(
                        onClick = { overlayIndex = OVERLAY_NONE },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(text = "Cancel", style = MiuixTheme.textStyles.button)
                    }
                    GlassDialogButton(
                        onClick = { overlayIndex = OVERLAY_NONE },
                        modifier = Modifier.weight(1f),
                        role = GlassDialogButtonRole.Primary,
                    ) {
                        Text(text = "Confirm", style = MiuixTheme.textStyles.button)
                    }
                }
            }

            GlassBottomSheetDemo(
                onDismissRequest = { overlayIndex = OVERLAY_NONE },
                show = overlayIndex == OVERLAY_BOTTOM_SHEET,
                backdrop = pageBackdrop,
                material = material ?: GlassBottomSheetDefaults.material(),
                stroke = stroke,
            )

            Box(modifier = Modifier.fillMaxSize().layerBackdrop(backdrop)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .layerBackdrop(wallpaperBackdrop)
                        .background(MiuixTheme.colorScheme.surface),
                ) {
                    if (wallpaper) {
                        Image(
                            painter = painterResource(Res.drawable.blur_test),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    contentPadding = pageContentPadding(innerPadding, padding),
                ) {
                    if (!tabsInTopBar) {
                        item(key = "glass-tabs-title") { SmallTitle(text = "TabRow") }
                        item(key = "glass-tabs") {
                            tabRows(Modifier.padding(horizontal = horizontalPadding).padding(bottom = 12.dp))
                        }
                    }
                    item(key = "glass-dropdown-title") { SmallTitle(text = "Dropdown") }
                    item(key = "glass-dropdown") {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = horizontalPadding)
                                .padding(bottom = 12.dp),
                        ) {
                            GlassDropdownPreference(
                                title = "Bluetooth stack log",
                                items = LogLevels,
                                selectedIndex = logLevel,
                                onSelectedIndexChange = { logLevel = it },
                                backdrop = backdrop,
                                visuals = popupVisuals,
                            )
                        }
                    }
                    item(key = "layout-title") { SmallTitle(text = "Layout") }
                    item(key = "layout") {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = horizontalPadding)
                                .padding(bottom = 12.dp),
                        ) {
                            if (isRuntimeShaderSupported()) {
                                SwitchPreference(
                                    title = "TopAppBar progressive blur",
                                    checked = topAppBarProgressiveBlur,
                                    onCheckedChange = { topAppBarProgressiveBlur = it },
                                )
                            }
                            SwitchPreference(
                                title = "NavigationBar search button",
                                checked = navigationBarSearchButton,
                                onCheckedChange = { navigationBarSearchButton = it },
                            )
                            GlassDropdownPreference(
                                title = "TabRow placement",
                                items = TabRowPlacements,
                                selectedIndex = tabRowPlacement,
                                onSelectedIndexChange = { tabRowPlacement = it },
                                backdrop = backdrop,
                                visuals = popupVisuals,
                            )
                        }
                    }
                    item(key = "controls-title") { SmallTitle(text = "Material") }
                    item(key = "controls") {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = horizontalPadding)
                                .padding(bottom = 12.dp),
                        ) {
                            GlassDropdownPreference(
                                title = "Material",
                                items = GlassDemoDefaults.MaterialNames,
                                selectedIndex = materialIndex,
                                onSelectedIndexChange = { materialIndex = it },
                                backdrop = backdrop,
                                visuals = popupVisuals,
                            )
                            GlassDropdownPreference(
                                title = "Bloom Stroke",
                                items = GlassDemoDefaults.StrokeNames,
                                selectedIndex = strokeIndex,
                                onSelectedIndexChange = { strokeIndex = it },
                                backdrop = backdrop,
                                visuals = popupVisuals,
                            )
                            SwitchPreference(
                                title = "Wallpaper",
                                checked = wallpaper,
                                onCheckedChange = { wallpaper = it },
                            )
                            ArrowPreference(
                                title = "Show Dialog",
                                onClick = {
                                    overlayIndex = OVERLAY_DIALOG
                                },
                            )
                            ArrowPreference(
                                title = "Show BottomSheet",
                                onClick = {
                                    overlayIndex = OVERLAY_BOTTOM_SHEET
                                },
                            )
                            HorizontalDivider(Modifier.fillMaxWidth().padding(horizontal = 16.dp))
                            SliderPreference(
                                title = "Corner Radius",
                                valueText = "${cornerRadius.toInt()}",
                                value = cornerRadius / 64f,
                                onValueChange = { cornerRadius = it * 64f },
                                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
                            )
                            SliderPreference(
                                title = "Corner Smoothing",
                                valueText = "${(smoothing * 100).toInt() / 100f}",
                                value = smoothing,
                                onValueChange = { smoothing = it },
                                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
                            )
                            SliderPreference(
                                title = "Alpha",
                                valueText = "${(alpha * 100).toInt() / 100f}",
                                value = alpha,
                                onValueChange = { alpha = it },
                            )
                        }
                    }
                }
            }
        }

        // Above the floating glass sidebar, which overlays the page in wide layouts.
        TopLayer {
            // Record the primary menu outside the page layer so the secondary samples both.
            Box(modifier = Modifier.matchParentSize().layerBackdrop(primaryPopupBackdrop)) {
                GlassTransformPopup(
                    show = overlayIndex == OVERLAY_POPUP,
                    onDismissRequest = {
                        submenu = false
                        overlayIndex = OVERLAY_NONE
                    },
                    anchor = menuAnchor,
                    backdrop = pageBackdrop,
                    anchorContent = {
                        Icon(
                            imageVector = MiuixIcons.Glass.SettingsOutline,
                            contentDescription = null,
                            modifier = Modifier.size(28.dp),
                            tint = MiuixTheme.colorScheme.onSurface,
                        )
                    },
                    stacked = submenu,
                    visuals = popupVisuals,
                    gap = 0.dp,
                    onMeasured = { menuWidth = with(density) { it.width.toDp() } },
                ) {
                    GlassPopupItem(
                        text = "View",
                        onClick = { overlayIndex = OVERLAY_NONE },
                        icon = MiuixIcons.Glass.ImageOutline,
                        summary = "List view",
                        showArrow = true,
                    )
                    GlassPopupItem(
                        text = "Sort by",
                        onClick = {
                            if (overlayIndex == OVERLAY_POPUP) {
                                submenuAnchor = latestSubmenuAnchor.rect
                                submenuPresent = true
                                submenu = true
                            }
                        },
                        modifier = Modifier.onGloballyPositioned {
                            if (!submenuPresent) latestSubmenuAnchor.rect = it.boundsInWindow()
                        },
                        icon = MiuixIcons.Glass.Edit,
                        summary = SortOrders[sortOrder],
                        showArrow = true,
                        arrowRotation = { submenuArrowState.value * (1f - menuAnchor.secondaryBackProgress) },
                    )
                    GlassPopupItem(
                        text = "Settings",
                        onClick = { overlayIndex = OVERLAY_NONE },
                        icon = MiuixIcons.Glass.SettingsOutline,
                    )
                }
            }

            GlassSecondaryPopup(
                show = overlayIndex == OVERLAY_POPUP && submenu,
                onDismissRequest = { submenu = false },
                anchorBounds = submenuAnchor,
                backdrop = secondaryBackdrop,
                materialAnchor = menuAnchor,
                onDismissFinished = { submenuPresent = false },
                sizing = GlassPopupSizing(minWidth = menuWidth),
                visuals = popupVisuals,
            ) {
                GlassPopupItem(
                    text = "Sort by",
                    onClick = { submenu = false },
                    icon = MiuixIcons.Glass.Edit,
                    summary = SortOrders[sortOrder],
                    showArrow = true,
                    arrowRotation = { submenuArrowState.value * (1f - menuAnchor.secondaryBackProgress) },
                )
                HorizontalDivider(
                    modifier = Modifier.padding(
                        horizontal = GlassPopupDefaults.DividerPaddingHorizontal,
                        vertical = GlassPopupDefaults.DividerPaddingVertical,
                    ),
                    thickness = GlassPopupDefaults.DividerThickness,
                )
                SortOrders.forEachIndexed { position, label ->
                    GlassPopupItem(
                        text = label,
                        onClick = {
                            sortOrder = position
                            submenu = false
                        },
                        selected = position == sortOrder,
                    )
                }
            }
        }
        val pageInsets = LocalAppPageInsets.current
        GlassSearchOverlay(
            expanded = searchExpanded,
            onDismissRequest = { searchExpanded = false },
            scrollMaterialThreshold = 0.dp,
            topInsets = pageInsets.safeInsets.only(WindowInsetsSides.Top),
            searchTopPadding = pageInsets.searchTop,
            blurEnabled = appState.enableBlur,
            horizontalInsetsModifier = Modifier.appPageHorizontalInsets(),
            searchIcon = MiuixIcons.Glass.Search,
            closeIcon = MiuixIcons.Glass.Close,
        )
    }
}

/** Keeps the root popup host outside the page recording used by the dialog. */
@Composable
private fun GlassPageHost(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Scaffold(modifier = modifier, contentWindowInsets = WindowInsets(0)) {
        Box(modifier = Modifier.fillMaxSize(), content = content)
    }
}
