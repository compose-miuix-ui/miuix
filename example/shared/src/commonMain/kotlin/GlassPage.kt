// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Button
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDialog
import top.yukonga.miuix.kmp.glass.GlassDropdownPopup
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassOverlayDefaults
import top.yukonga.miuix.kmp.glass.GlassPopupAnchor
import top.yukonga.miuix.kmp.glass.GlassPopupDefaults
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.GlassPopupSizing
import top.yukonga.miuix.kmp.glass.GlassSecondaryPopup
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassShape
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
import top.yukonga.miuix.kmp.glass.glassPopupAnchorValue
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowUpDown
import top.yukonga.miuix.kmp.icon.extended.Home
import top.yukonga.miuix.kmp.icon.glass.ChevronBackward
import top.yukonga.miuix.kmp.icon.glass.Create
import top.yukonga.miuix.kmp.icon.glass.Edit
import top.yukonga.miuix.kmp.icon.glass.Image
import top.yukonga.miuix.kmp.icon.glass.Search
import top.yukonga.miuix.kmp.icon.glass.Settings
import top.yukonga.miuix.kmp.layout.CascadingPopupDefaults
import top.yukonga.miuix.kmp.preference.SliderPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.blur_test
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme

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

private val OverlayNames = listOf("None", "Popup", "Dialog")

private val TabPlacements = listOf("Top bar", "List")
private const val TABS_IN_TOP_BAR = 0
private const val TABS_IN_LIST = 1
private const val OVERLAY_NONE = 0
private const val OVERLAY_POPUP = 1
private const val OVERLAY_DIALOG = 2
private const val DROPDOWN_NONE = 0
private const val DROPDOWN_LOG_LEVEL = 1
private const val DROPDOWN_TAB_ROW = 2
private const val DROPDOWN_MATERIAL = 3
private const val DROPDOWN_STROKE = 4
private const val DROPDOWN_OVERLAY = 5

private val LogLevels = listOf("Verbose", "Debug", "Info", "Warn", "Error")

private val SortOrders = listOf("Updated", "Created", "Title")

private const val MATERIAL_LIGHT = 7
private const val MATERIAL_DARK = 9

/**
 * The glass components on a flat page.
 *
 * The page is flat by default. A busy photograph flatters a refractive material and hides a rim
 * that is a pixel too wide, and a flat page is what the system's own settings pages actually are.
 * The wallpaper switch puts a photograph back for the opposite reason: over a flat page the blur
 * and the refraction have nothing to act on, so neither can be judged at all.
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
    var activeDropdown by remember { mutableIntStateOf(DROPDOWN_NONE) }
    var tabPlacement by remember { mutableIntStateOf(TABS_IN_TOP_BAR) }
    var navIndex by remember { mutableIntStateOf(0) }
    var primaryTab by remember { mutableIntStateOf(0) }
    var neutralTab by remember { mutableIntStateOf(0) }
    var joinedTab by remember { mutableIntStateOf(0) }
    var visible by remember { mutableStateOf(false) }
    var wallpaper by remember { mutableStateOf(false) }
    val menuAnchor = rememberGlassPopupAnchor()
    var dropdownAnchor by remember { mutableStateOf(Rect.Zero) }
    val dropdownRow = rememberGlassPopupAnchor()
    var tabRowAnchor by remember { mutableStateOf(Rect.Zero) }
    val tabRowDropdown = rememberGlassPopupAnchor()
    var materialAnchor by remember { mutableStateOf(Rect.Zero) }
    val materialDropdown = rememberGlassPopupAnchor()
    var strokeAnchor by remember { mutableStateOf(Rect.Zero) }
    val strokeDropdown = rememberGlassPopupAnchor()
    var overlayAnchor by remember { mutableStateOf(Rect.Zero) }
    val overlayDropdown = rememberGlassPopupAnchor()
    var logLevel by remember { mutableIntStateOf(2) }

    val style = Materials[materialIndex].second
    val stroke = if (isInDark) StrokesDark[strokeIndex] else Strokes[strokeIndex].second
    val popupVisuals = GlassPopupDefaults.visuals(style = style, alpha = alpha, stroke = stroke)
    var searchExpanded by remember { mutableStateOf(false) }
    var submenu by remember { mutableStateOf(false) }
    var submenuPresent by remember { mutableStateOf(false) }
    var submenuAnchor by remember { mutableStateOf(Rect.Zero) }
    var menuWidth by remember { mutableStateOf(0.dp) }
    var sortOrder by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val arrowTurn = CascadingPopupDefaults.arrowRotation(LocalLayoutDirection.current)
    val submenuArrow by animateFloatAsState(
        targetValue = if (submenu) arrowTurn else 0f,
        animationSpec = CascadingPopupDefaults.arrowSpring(submenu),
    )
    val shape = GlassShape(cornerRadius.dp, smoothing)
    val tabsInTopBar = tabPlacement == TABS_IN_TOP_BAR
    // A tab row placed in the list sits inside the layer the bars sample, so it samples the page
    // background instead — sampling its own layer would close the loop and take the renderer down.
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

    Box(modifier = Modifier.fillMaxSize()) {
        // Record the page and primary menu, including its scale and dimming mask.
        // The secondary must stay outside this layer so it never samples itself.
        Box(modifier = Modifier.fillMaxSize().layerBackdrop(secondaryBackdrop)) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .then(if (searchExpanded) Modifier.clearAndSetSemantics { } else Modifier),
                bottomBar = {
                    Column(
                        modifier = Modifier
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
                            modifier = Modifier.layout { measurable, constraints ->
                                // BottomNavigator's four/five-item rule accounts for overlapping items
                                // before deciding whether to use the compact 344dp wide-screen bar.
                                val available = constraints.maxWidth + (10.dp * (NavItems.size - 1) - 6.dp).roundToPx()
                                val width = if (constraints.hasBoundedWidth && available > 400.dp.roundToPx()) {
                                    constraints.constrainWidth(344.dp.roundToPx())
                                } else {
                                    constraints.maxWidth
                                }
                                val placeable = measurable.measure(constraints.copy(minWidth = width, maxWidth = width))
                                layout(placeable.width, placeable.height) { placeable.placeRelative(0, 0) }
                            },
                            style = style,
                            alpha = alpha,
                            stroke = stroke,
                        )
                    }
                },
                topBar = {
                    GlassTopAppBar(
                        title = "Glass",
                        contentModifier = Modifier.glassSearchBarTransition(searchExpanded),
                        titleAlpha = rememberGlassSearchTitleAlpha(searchExpanded, scrollBehavior.state.heightOffset == scrollBehavior.state.heightOffsetLimit),
                        isContentScrolled = contentUnderTopBar,
                        backdrop = backdrop,
                        scrollBehavior = scrollBehavior,
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
                                    onClick = {
                                        submenu = false
                                        overlayIndex = OVERLAY_NONE
                                        activeDropdown = DROPDOWN_NONE
                                        searchExpanded = true
                                    },
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
                                        activeDropdown = DROPDOWN_NONE
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
                },
            ) { innerPadding ->
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
                                    GlassDropdownRow(
                                        title = "Bluetooth stack log",
                                        value = LogLevels[logLevel],
                                        anchor = dropdownRow,
                                        onClick = { activeDropdown = DROPDOWN_LOG_LEVEL },
                                        onBounds = { dropdownAnchor = it },
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
                                    GlassDropdownRow(
                                        title = "Tab row",
                                        value = TabPlacements[tabPlacement],
                                        anchor = tabRowDropdown,
                                        onClick = { activeDropdown = DROPDOWN_TAB_ROW },
                                        onBounds = { tabRowAnchor = it },
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
                                    GlassDropdownRow(
                                        title = "Material",
                                        value = Materials[materialIndex].first,
                                        anchor = materialDropdown,
                                        onClick = { activeDropdown = DROPDOWN_MATERIAL },
                                        onBounds = { materialAnchor = it },
                                    )
                                    GlassDropdownRow(
                                        title = "Bloom Stroke",
                                        value = Strokes[strokeIndex].first,
                                        anchor = strokeDropdown,
                                        onClick = { activeDropdown = DROPDOWN_STROKE },
                                        onBounds = { strokeAnchor = it },
                                    )
                                    GlassDropdownRow(
                                        title = "Overlay",
                                        value = OverlayNames[overlayIndex],
                                        anchor = overlayDropdown,
                                        onClick = { activeDropdown = DROPDOWN_OVERLAY },
                                        onBounds = { overlayAnchor = it },
                                    )
                                    SwitchPreference(
                                        title = "Wallpaper",
                                        summary = "A photograph behind the glass, to judge the refraction",
                                        checked = wallpaper,
                                        onCheckedChange = { wallpaper = it },
                                    )
                                    SwitchPreference(
                                        title = "Show Dialog",
                                        checked = visible,
                                        onCheckedChange = {
                                            visible = it
                                            overlayIndex = if (it) OVERLAY_DIALOG else OVERLAY_NONE
                                            activeDropdown = DROPDOWN_NONE
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

            GlassDialog(
                visible = overlayIndex == OVERLAY_DIALOG,
                onDismissRequest = {
                    overlayIndex = OVERLAY_NONE
                    visible = false
                },
                backdrop = backdrop,
                scrimAlpha = if (isInDark) {
                    GlassOverlayDefaults.ScrimAlphaDark
                } else {
                    GlassOverlayDefaults.ScrimAlphaLight
                },
                style = style,
                alpha = alpha,
                stroke = stroke,
            ) {
                Text(text = "Glass Dialog", style = MiuixTheme.textStyles.title4)
                Text(
                    text = "The dialog wears the material the page is set to.",
                    style = MiuixTheme.textStyles.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp, bottom = 18.dp),
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    TextButton(
                        text = "Cancel",
                        onClick = {
                            overlayIndex = OVERLAY_NONE
                            visible = false
                        },
                        modifier = Modifier.weight(1f),
                    )
                    Button(
                        onClick = {
                            overlayIndex = OVERLAY_NONE
                            visible = false
                        },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(text = "Confirm", style = MiuixTheme.textStyles.button)
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
                backdrop = backdrop,
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

        GlassDropdownPopup(
            show = activeDropdown == DROPDOWN_LOG_LEVEL,
            onDismissRequest = { activeDropdown = DROPDOWN_NONE },
            anchorBounds = dropdownAnchor,
            anchor = dropdownRow,
            backdrop = backdrop,
            visuals = popupVisuals,
        ) {
            LogLevels.forEachIndexed { position, label ->
                GlassPopupItem(
                    text = label,
                    onClick = {
                        logLevel = position
                        activeDropdown = DROPDOWN_NONE
                    },
                    selected = position == logLevel,
                )
            }
        }
        GlassDropdownPopup(
            show = activeDropdown == DROPDOWN_TAB_ROW,
            onDismissRequest = { activeDropdown = DROPDOWN_NONE },
            anchorBounds = tabRowAnchor,
            anchor = tabRowDropdown,
            backdrop = backdrop,
            visuals = popupVisuals,
        ) {
            TabPlacements.forEachIndexed { position, label ->
                GlassPopupItem(
                    text = label,
                    onClick = {
                        tabPlacement = position
                        activeDropdown = DROPDOWN_NONE
                    },
                    selected = position == tabPlacement,
                )
            }
        }

        GlassDropdownPopup(
            show = activeDropdown == DROPDOWN_MATERIAL,
            onDismissRequest = { activeDropdown = DROPDOWN_NONE },
            anchorBounds = materialAnchor,
            anchor = materialDropdown,
            backdrop = backdrop,
            visuals = popupVisuals,
        ) {
            Materials.forEachIndexed { position, material ->
                GlassPopupItem(
                    text = material.first,
                    onClick = {
                        materialIndex = position
                        activeDropdown = DROPDOWN_NONE
                    },
                    selected = position == materialIndex,
                )
            }
        }
        GlassDropdownPopup(
            show = activeDropdown == DROPDOWN_STROKE,
            onDismissRequest = { activeDropdown = DROPDOWN_NONE },
            anchorBounds = strokeAnchor,
            anchor = strokeDropdown,
            backdrop = backdrop,
            visuals = popupVisuals,
        ) {
            Strokes.forEachIndexed { position, stroke ->
                GlassPopupItem(
                    text = stroke.first,
                    onClick = {
                        strokeIndex = position
                        activeDropdown = DROPDOWN_NONE
                    },
                    selected = position == strokeIndex,
                )
            }
        }
        GlassDropdownPopup(
            show = activeDropdown == DROPDOWN_OVERLAY,
            onDismissRequest = { activeDropdown = DROPDOWN_NONE },
            anchorBounds = overlayAnchor,
            anchor = overlayDropdown,
            backdrop = backdrop,
            visuals = popupVisuals,
        ) {
            OverlayNames.forEachIndexed { position, label ->
                GlassPopupItem(
                    text = label,
                    onClick = {
                        overlayIndex = position
                        visible = position == OVERLAY_DIALOG
                        activeDropdown = DROPDOWN_NONE
                    },
                    selected = position == overlayIndex,
                )
            }
        }
        GlassSearchOverlay(
            expanded = searchExpanded,
            onDismissRequest = { searchExpanded = false },
        )
    }
}

/** One settings row whose value opens a [GlassDropdownPopup]. */
@Composable
private fun GlassDropdownRow(
    title: String,
    value: String,
    anchor: GlassPopupAnchor,
    onClick: () -> Unit,
    onBounds: (Rect) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { onBounds(it.boundsInRoot()) }
            .clickable(interactionSource = null, indication = null, onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MiuixTheme.textStyles.body1,
            color = MiuixTheme.colorScheme.onSurface,
        )
        Row(
            modifier = Modifier.glassPopupAnchorValue(anchor),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = value,
                style = MiuixTheme.textStyles.body2,
                color = MiuixTheme.colorScheme.onSurfaceVariantActions,
            )
            Icon(
                imageVector = MiuixIcons.Basic.ArrowUpDown,
                contentDescription = null,
                modifier = Modifier.padding(start = 8.dp).size(14.dp),
                tint = MiuixTheme.colorScheme.onSurfaceVariantActions,
            )
        }
    }
}
