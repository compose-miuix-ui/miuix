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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import component.GlassBottomSheetDemo
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBarDefaults
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.ProgressiveBlur
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.progressiveTextureBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDialog
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassDialogButtonRole
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationBarDefaults
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassPopupDefaults
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.GlassPopupSizing
import top.yukonga.miuix.kmp.glass.GlassSecondaryPopup
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassShadows
import top.yukonga.miuix.kmp.glass.GlassStroke
import top.yukonga.miuix.kmp.glass.GlassStrokes
import top.yukonga.miuix.kmp.glass.GlassStyle
import top.yukonga.miuix.kmp.glass.GlassStyles
import top.yukonga.miuix.kmp.glass.GlassTabRow
import top.yukonga.miuix.kmp.glass.GlassTabRowDefaults
import top.yukonga.miuix.kmp.glass.GlassTopAppBar
import top.yukonga.miuix.kmp.glass.GlassTopAppBarDefaults
import top.yukonga.miuix.kmp.glass.GlassTransformPopup
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Home
import top.yukonga.miuix.kmp.icon.glass.ChevronBackward
import top.yukonga.miuix.kmp.icon.glass.Create
import top.yukonga.miuix.kmp.icon.glass.Edit
import top.yukonga.miuix.kmp.icon.glass.Image
import top.yukonga.miuix.kmp.icon.glass.Search
import top.yukonga.miuix.kmp.icon.glass.Settings
import top.yukonga.miuix.kmp.layout.CascadingPopupDefaults
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.SliderPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.blur_test
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme
import kotlin.math.roundToInt

private val Materials: List<Pair<String, GlassStyle>> = listOf(
    "Common Small Thin" to GlassStyles.CommonSmallThin,
    "Common Small Normal" to GlassStyles.CommonSmallNormal,
    "Common Small Regular" to GlassStyles.CommonSmallRegular,
    "Common Small Semibold" to GlassStyles.CommonSmallSemibold,
    "Common Small Bold" to GlassStyles.CommonSmallBold,
    "Common Medium Thin Low" to GlassStyles.CommonMediumThinLow,
    "Common Medium Thin High" to GlassStyles.CommonMediumThinHigh,
    "Common Medium Regular Low Light" to GlassStyles.CommonMediumRegularLowLight,
    "Common Medium Regular High Light" to GlassStyles.CommonMediumRegularHighLight,
    "Common Medium Regular Dark" to GlassStyles.CommonMediumRegularDark,
    "Common Medium Bold Light" to GlassStyles.CommonMediumBoldLight,
    "Common Medium Bold Dark" to GlassStyles.CommonMediumBoldDark,
    "Common Medium Bold Low" to GlassStyles.CommonMediumBoldLow,
    "Common Large Regular" to GlassStyles.CommonLargeRegular,
    "Common Large Demibold" to GlassStyles.CommonLargeDemibold,
    "Bionic Medium Thin" to GlassStyles.BionicMediumThin,
    "Bionic Medium Normal" to GlassStyles.BionicMediumNormal,
    "Bionic Medium Regular" to GlassStyles.BionicMediumRegular,
    "Tint Purple" to GlassStyles.TintPurple,
    "Tint Orange" to GlassStyles.TintOrange,
    "Tint Blue" to GlassStyles.TintBlue,
    "Tint Yellow" to GlassStyles.TintYellow,
    "Tint Brown" to GlassStyles.TintBrown,
    "Tint Black Thin" to GlassStyles.TintBlackThin,
    "Tint Black Regular" to GlassStyles.TintBlackRegular,
)

private val Strokes: List<Pair<String, GlassStroke?>> = listOf(
    "None" to null,
    "Small" to GlassStrokes.SmallLight,
    "Middle" to GlassStrokes.MiddleLight,
    "Big" to GlassStrokes.BigLight,
)

private val StrokesDark: List<GlassStroke?> = listOf(
    null,
    GlassStrokes.SmallDark,
    GlassStrokes.MiddleDark,
    GlassStrokes.BigDark,
)

private val NavItems: List<GlassNavigationItem> = listOf(
    GlassNavigationItem(MiuixIcons.Home, "Home"),
    GlassNavigationItem(MiuixIcons.Glass.Create, "Create"),
    GlassNavigationItem(MiuixIcons.Glass.Image, "Gallery"),
    GlassNavigationItem(MiuixIcons.Glass.Settings, "Settings"),
)

private val OverlayNames = listOf("None", "Popup", "Dialog", "Bottom Sheet")

private val TabPlacements = listOf("Top bar", "List")
private const val TABS_IN_TOP_BAR = 0
private const val OVERLAY_NONE = 0
private const val OVERLAY_POPUP = 1
private const val OVERLAY_DIALOG = 2
private const val OVERLAY_BOTTOM_SHEET = 3

private val LogLevels = listOf("Verbose", "Debug", "Info", "Warn", "Error")

private val SortOrders = listOf("Updated", "Created", "Title")

private const val MATERIAL_LIGHT = 7
private const val MATERIAL_DARK = 9

/**
 * Demonstrates glass components on solid or wallpaper backgrounds.
 *
 * @param padding Window padding supplied by the host scaffold.
 */
@Composable
fun GlassPage(padding: PaddingValues) {
    val navigator = LocalNavigator.current
    val horizontalPadding = GlassTopAppBarDefaults.HorizontalPadding
    val isInDark = isInDarkTheme()
    val navigationBarInset = with(LocalDensity.current) { WindowInsets.navigationBars.getBottom(this).toDp() }
    val bottomBarMargin = maxOf(24.dp, navigationBarInset + 8.dp, padding.calculateBottomPadding() + 8.dp)
    val backdrop = rememberLayerBackdrop()
    val secondaryBackdrop = rememberLayerBackdrop()
    val wallpaperBackdrop = rememberLayerBackdrop()
    val pageBackdrop = rememberLayerBackdrop()
    val scrollBehavior = MiuixScrollBehavior()
    val listState = rememberLazyListState()
    val collapseRamp = GlassTopAppBarDefaults.collapseRamp(scrollBehavior).value
    val contentUnderTopBar by remember {
        derivedStateOf { listState.canScrollBackward }
    }
    val tabSurfaceAlpha = if (contentUnderTopBar) 1f else 0f

    var materialIndex by remember(isInDark) {
        mutableIntStateOf(if (isInDark) MATERIAL_DARK else MATERIAL_LIGHT)
    }
    var strokeIndex by remember { mutableIntStateOf(2) }
    var cornerRadius by remember { mutableFloatStateOf(26f) }
    var smoothing by remember { mutableFloatStateOf(1f) }
    var alpha by remember { mutableFloatStateOf(1f) }
    var overlayIndex by remember { mutableIntStateOf(OVERLAY_NONE) }
    var tabPlacement by remember { mutableIntStateOf(TABS_IN_TOP_BAR) }
    var progressiveTopBarBlur by remember { mutableStateOf(false) }
    var navigationSearchButton by remember { mutableStateOf(true) }
    val topBarWindowInsets = WindowInsets.statusBars
    var navIndex by remember { mutableIntStateOf(0) }
    var primaryTab by remember { mutableIntStateOf(0) }
    var neutralTab by remember { mutableIntStateOf(0) }
    var joinedTab by remember { mutableIntStateOf(0) }
    var wallpaper by remember { mutableStateOf(false) }
    val menuAnchor = rememberGlassPopupAnchor()
    var logLevel by remember { mutableIntStateOf(2) }

    val style = Materials[materialIndex].second
    val stroke = if (isInDark) StrokesDark[strokeIndex] else Strokes[strokeIndex].second
    val popupVisuals = GlassPopupDefaults.visuals(style = style, alpha = alpha, stroke = stroke)
    var searchExpanded by remember { mutableStateOf(false) }
    val searchBarAlpha = rememberGlassSearchBarAlpha(searchExpanded)
    var submenu by remember { mutableStateOf(false) }
    var submenuPresent by remember { mutableStateOf(false) }
    var submenuAnchor by remember { mutableStateOf(Rect.Zero) }
    var menuWidth by remember { mutableStateOf(0.dp) }
    var sortOrder by remember { mutableIntStateOf(0) }
    val openSearch: () -> Unit = {
        submenu = false
        overlayIndex = OVERLAY_NONE
        searchExpanded = true
    }
    val density = LocalDensity.current
    val arrowTurn = CascadingPopupDefaults.arrowRotation(LocalLayoutDirection.current)
    val submenuArrow by animateFloatAsState(
        targetValue = if (submenu) arrowTurn else 0f,
        animationSpec = CascadingPopupDefaults.arrowSpring(submenu),
    )
    val tabsInTopBar = tabPlacement == TABS_IN_TOP_BAR
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
                surfaceAlpha = 1f,
                stroke = stroke,
            )
            GlassTabRow(
                tabs = listOf("Home", "Widget", "Theme", "Paper"),
                selectedIndex = neutralTab,
                onSelect = { neutralTab = it },
                backdrop = tabBackdrop,
                style = style,
                alpha = alpha,
                surfaceAlpha = if (tabsInTopBar) tabSurfaceAlpha else 1f,
                stroke = stroke,
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
                surfaceAlpha = if (tabsInTopBar) tabSurfaceAlpha else 1f,
                stroke = stroke,
            )
        }
    }

    if (!isRuntimeShaderSupported()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "This device has no runtime shader support.")
        }
        return
    }

    GlassPageHost(modifier = Modifier.fillMaxSize()) {
        // Record the page and primary menu; keep the secondary outside its sampled layer.
        Box(modifier = Modifier.fillMaxSize().layerBackdrop(secondaryBackdrop)) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    // Include page bars, excluding overlays and the route scrim.
                    .layerBackdrop(pageBackdrop)
                    .then(if (searchExpanded) Modifier.clearAndSetSemantics { } else Modifier),
                bottomBar = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = bottomBarMargin)
                            .graphicsLayer { this.alpha = if (searchExpanded) 0f else 1f },
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
                                // Account for item overlap before selecting the compact wide-screen width.
                                val actionWidth = if (navigationSearchButton) {
                                    (GlassNavigationBarDefaults.Height + GlassNavigationBarDefaults.ActionSpacing).roundToPx()
                                } else {
                                    0
                                }
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
                            trailingAction = if (navigationSearchButton) {
                                {
                                    GlassIconButton(
                                        onClick = openSearch,
                                        backdrop = backdrop,
                                        size = GlassNavigationBarDefaults.Height,
                                        style = style,
                                        surfaceAlpha = alpha,
                                        stroke = stroke,
                                        shadow = GlassShadows.Float,
                                    ) {
                                        Icon(
                                            imageVector = MiuixIcons.Glass.Search,
                                            contentDescription = "Search",
                                            modifier = Modifier.size(24.dp),
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
                    Box {
                        if (progressiveTopBarBlur) {
                            val blurColors = BlurDefaults.blurColors(
                                blendColors = listOf(
                                    BlendColorEntry(color = MiuixTheme.colorScheme.surface.copy(alpha = 0.3f)),
                                ),
                            )
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .layout { measurable, constraints ->
                                        // Demo tab rows have their own material; keep the band at toolbar height.
                                        val barHeight = topBarWindowInsets.getTop(this) +
                                            TopAppBarDefaults.CollapsedHeight.toPx()
                                        val blurHeight = ((barHeight + 20.dp.toPx()) * 1.2f).roundToInt()
                                        val placeable = measurable.measure(
                                            constraints.copy(minHeight = blurHeight, maxHeight = blurHeight),
                                        )
                                        // Let the blur overhang without changing Scaffold's content inset.
                                        layout(constraints.maxWidth, constraints.maxHeight) {
                                            placeable.placeRelative(0, 0)
                                        }
                                    }
                                    .graphicsLayer {
                                        this.alpha = (-scrollBehavior.state.contentOffset / 48.dp.toPx()).coerceIn(0f, 1f) * searchBarAlpha.value
                                    }
                                    .progressiveTextureBlur(
                                        backdrop = backdrop,
                                        shape = RectangleShape,
                                        gradient = ProgressiveBlur.Top.copy(curve = 0.5f),
                                        blurRadius = 80f,
                                        colors = blurColors,
                                    ),
                            )
                        }
                        GlassTopAppBar(
                            title = "Glass",
                            contentModifier = Modifier.glassSearchBarTransition(searchExpanded, barAlpha = { searchBarAlpha.value }),
                            titleAlpha = rememberGlassSearchTitleAlpha(searchExpanded, scrollBehavior.state.heightOffset == scrollBehavior.state.heightOffsetLimit),
                            isContentScrolled = contentUnderTopBar,
                            backdrop = backdrop,
                            scrollBehavior = scrollBehavior,
                            bandBrush = if (progressiveTopBarBlur) {
                                SolidColor(Color.Transparent)
                            } else {
                                GlassTopAppBarDefaults.bandBrush(MiuixTheme.colorScheme.surface)
                            },
                            style = style,
                            alpha = alpha,
                            navigationIcon = {
                                GlassIconButton(onClick = { navigator.pop() }) {
                                    Icon(
                                        imageVector = MiuixIcons.Glass.ChevronBackward,
                                        contentDescription = "Back",
                                        modifier = Modifier.size(24.dp),
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
                                            modifier = Modifier.size(24.dp),
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
                                            imageVector = MiuixIcons.Glass.Settings,
                                            contentDescription = "Sort",
                                            modifier = Modifier.size(24.dp).glassPopupAnchorContent(menuAnchor),
                                            tint = MiuixTheme.colorScheme.onSurface,
                                        )
                                    }
                                }
                            },
                            bottomContent = {
                                if (tabsInTopBar) {
                                    tabRows(
                                        Modifier
                                            .padding(horizontal = horizontalPadding).padding(bottom = 6.dp)
                                            .graphicsLayer { this.alpha = if (searchExpanded) 0f else 1f },
                                    )
                                }
                            },
                        )
                    }
                },
            ) { innerPadding ->
                GlassDialog(
                    show = overlayIndex == OVERLAY_DIALOG,
                    backdrop = pageBackdrop,
                    title = "Glass Dialog",
                    summary = "Blurred background with the dialog's own three-layer material.",
                    onDismissRequest = { overlayIndex = OVERLAY_NONE },
                ) {
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
                )

                Box(modifier = Modifier.fillMaxSize().graphicsLayer { this.alpha = if (searchExpanded) 0f else 1f }) {
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
                            contentPadding = PaddingValues(
                                top = innerPadding.calculateTopPadding(),
                                bottom = innerPadding.calculateBottomPadding(),
                            ),
                        ) {
                            item(key = "glass-tabs") {
                                if (!tabsInTopBar) {
                                    tabRows(Modifier.padding(horizontal = horizontalPadding).padding(top = 12.dp))
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
                                    SwitchPreference(
                                        title = "Top bar progressive blur",
                                        checked = progressiveTopBarBlur,
                                        onCheckedChange = { progressiveTopBarBlur = it },
                                    )
                                    SwitchPreference(
                                        title = "Navigation search button",
                                        checked = navigationSearchButton,
                                        onCheckedChange = { navigationSearchButton = it },
                                    )
                                    GlassDropdownPreference(
                                        title = "Tab row",
                                        items = TabPlacements,
                                        selectedIndex = tabPlacement,
                                        onSelectedIndexChange = { tabPlacement = it },
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
                                        items = Materials.map { it.first },
                                        selectedIndex = materialIndex,
                                        onSelectedIndexChange = { materialIndex = it },
                                        backdrop = backdrop,
                                        visuals = popupVisuals,
                                    )
                                    GlassDropdownPreference(
                                        title = "Bloom Stroke",
                                        items = Strokes.map { it.first },
                                        selectedIndex = strokeIndex,
                                        onSelectedIndexChange = { strokeIndex = it },
                                        backdrop = backdrop,
                                        visuals = popupVisuals,
                                    )
                                    GlassDropdownPreference(
                                        title = "Overlay",
                                        items = OverlayNames,
                                        selectedIndex = overlayIndex,
                                        onSelectedIndexChange = { overlayIndex = it },
                                        backdrop = backdrop,
                                        visuals = popupVisuals,
                                    )
                                    SwitchPreference(
                                        title = "Wallpaper",
                                        summary = "A photograph behind the glass, to judge the refraction",
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
                                        title = "Show Bottom Sheet",
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
                            item(key = "content-title") { SmallTitle(text = "Content") }
                            item(key = "content") {
                                Card(
                                    modifier = Modifier
                                        .padding(horizontal = horizontalPadding)
                                        .padding(bottom = 12.dp),
                                ) {
                                    repeat(8) { index ->
                                        BasicComponent(
                                            title = "Content ${index + 1}",
                                            summary = "Scrolls under the top bar",
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

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
                        imageVector = MiuixIcons.Glass.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MiuixTheme.colorScheme.onSurface,
                    )
                },
                stacked = submenu,
                visuals = popupVisuals,
                anchorAlpha = collapseRamp,
                gap = 0.dp,
                onMeasured = { menuWidth = with(density) { it.width.toDp() } },
            ) {
                GlassPopupItem(
                    text = "View",
                    onClick = { overlayIndex = OVERLAY_NONE },
                    icon = MiuixIcons.Glass.Image,
                    summary = "List view",
                    showArrow = true,
                )
                GlassPopupItem(
                    text = "Sort by",
                    onClick = {
                        if (overlayIndex == OVERLAY_POPUP) {
                            submenuPresent = true
                            submenu = true
                        }
                    },
                    modifier = Modifier.onGloballyPositioned {
                        if (!submenuPresent) submenuAnchor = it.boundsInRoot()
                    },
                    icon = MiuixIcons.Glass.Edit,
                    summary = SortOrders[sortOrder],
                    showArrow = true,
                    arrowRotation = { submenuArrow * (1f - menuAnchor.secondaryBackProgress) },
                )
                GlassPopupItem(
                    text = "Settings",
                    onClick = { overlayIndex = OVERLAY_NONE },
                    icon = MiuixIcons.Glass.Settings,
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
                arrowRotation = { submenuArrow * (1f - menuAnchor.secondaryBackProgress) },
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

        GlassSearchOverlay(
            expanded = searchExpanded,
            onDismissRequest = { searchExpanded = false },
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
