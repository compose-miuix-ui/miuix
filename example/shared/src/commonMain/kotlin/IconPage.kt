// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.node.Ref
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import component.SearchBarFake
import component.SearchPager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SnackbarDuration
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.TabRow
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDialog
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassSearchOverlay
import top.yukonga.miuix.kmp.glass.GlassSearchOverlayDefaults
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.glassSearchBarTransition
import top.yukonga.miuix.kmp.glass.rememberGlassSearchBarAlpha
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.Close1
import top.yukonga.miuix.kmp.icon.symbol.Search
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.squircle.squircleClip
import top.yukonga.miuix.kmp.symbol.Symbol
import top.yukonga.miuix.kmp.symbol.SymbolWeights
import top.yukonga.miuix.kmp.symbol.VariableSymbol
import top.yukonga.miuix.kmp.symbol.toImageVector
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import top.yukonga.miuix.kmp.utils.springAnimateToPage
import utils.AdaptiveTopAppBar
import utils.AllVariableSymbols
import utils.BlurredBar
import utils.LocalAppPageInsets
import utils.SearchStatus
import utils.appPageHorizontalInsets
import utils.appPageSearchInsets
import utils.originalIconsForWeight
import utils.pageBottomPadding
import utils.pageContentPadding
import utils.pageScrollModifiers
import kotlin.concurrent.Volatile
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.milliseconds

private val SearchResultVerticalPadding = 4.dp

private data class IconSection(
    val title: String,
    val indices: List<Int>,
)

private val IconFamilies = listOf("Symbol", "Original")
private val IconWeightNames = listOf("Light", "Normal", "Regular", "Medium", "Demibold")
private val SymbolPresetWeights = listOf(
    "Light" to SymbolWeights.Light,
    "Normal" to SymbolWeights.Normal,
    "Regular" to SymbolWeights.Regular,
    "Medium" to SymbolWeights.Medium,
    "Demibold" to SymbolWeights.Demibold,
)
private val IconCellShape = RoundedCornerShape(12.dp)

private class IconFamilyCatalog(
    val regularIcons: List<ImageVector>,
    val iconNames: List<String>,
    val sections: List<IconSection>,
    private val weightLoader: (String) -> List<ImageVector>,
) {
    val weightVariants: List<Pair<String, List<ImageVector>>> by lazy {
        IconWeightNames.map { name -> name to weightLoader(name) }
    }

    @Volatile
    private var rowsCache: Pair<Int, List<Pair<String, List<List<Int>>>>>? = null

    fun sectionRowsFor(columns: Int): List<Pair<String, List<List<Int>>>> {
        val cached = rowsCache
        if (cached != null && cached.first == columns) return cached.second
        val built = sections.map { it.title to it.indices.chunked(columns) }
        rowsCache = columns to built
        return built
    }
}

private fun buildIconCatalog(
    regular: List<ImageVector>,
    weightLoader: (String) -> List<ImageVector>,
): IconFamilyCatalog {
    val names = regular.map { it.name.substringBefore(".") }
    val sections = names.indices
        .groupBy { names[it].firstOrNull()?.uppercaseChar()?.toString() ?: "#" }
        .entries
        .sortedBy { it.key }
        .map { (title, indices) ->
            IconSection(
                title = title,
                indices = indices.sortedBy { names[it] },
            )
        }
    return IconFamilyCatalog(
        regularIcons = regular,
        iconNames = names,
        sections = sections,
        weightLoader = weightLoader,
    )
}

private val OriginalIconCatalog by lazy {
    buildIconCatalog(originalIconsForWeight("Regular"), ::originalIconsForWeight)
}

private class SymbolIconCatalog(
    val symbols: List<VariableSymbol>,
    val iconNames: List<String>,
    val sections: List<IconSection>,
) {
    @Volatile
    private var rowsCache: Pair<Int, List<Pair<String, List<List<Int>>>>>? = null

    fun sectionRowsFor(columns: Int): List<Pair<String, List<List<Int>>>> {
        val cached = rowsCache
        if (cached != null && cached.first == columns) return cached.second
        val built = sections.map { it.title to it.indices.chunked(columns) }
        rowsCache = columns to built
        return built
    }
}

private val VariableSymbolCatalog by lazy {
    val symbols = AllVariableSymbols
    val names = symbols.map { it.name }
    val sections = names.indices
        .groupBy { names[it].firstOrNull()?.uppercaseChar()?.toString() ?: "#" }
        .entries
        .sortedBy { it.key }
        .map { (title, indices) ->
            IconSection(
                title = title,
                indices = indices.sortedBy { names[it] },
            )
        }
    SymbolIconCatalog(
        symbols = symbols,
        iconNames = names,
        sections = sections,
    )
}

@Composable
fun IconsPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val glassTopBar = appState.topAppBarStyle == 1
    val isWideScreen = LocalIsWideScreen.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val searchBarTopPadding = remember(topAppBarScrollBehavior) {
        { 12.dp * (1f - topAppBarScrollBehavior.state.collapsedFraction) }
    }
    var searchExpanded by remember { mutableStateOf(false) }
    val searchBarAlpha = rememberGlassSearchBarAlpha(searchExpanded && glassTopBar)
    var legacySearchStatus by remember { mutableStateOf<SearchStatus?>(null) }
    var legacySearchOffset by remember { mutableStateOf(0.dp) }
    val pageCoordinates = remember { Ref<LayoutCoordinates>() }
    val searchCoordinates = remember { Ref<LayoutCoordinates>() }
    val density = LocalDensity.current
    val searchTopInset = LocalAppPageInsets.current.searchTop
    val searchModifier = Modifier.appPageSearchInsets()
    val searchResultPadding = pageBottomPadding(padding)

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { IconFamilies.size })
    val currentPage by remember { computedStateOf { pagerState.currentPage } }
    val searchIsSymbol = currentPage == 0
    val searchIconNames = if (searchIsSymbol) VariableSymbolCatalog.iconNames else OriginalIconCatalog.iconNames
    val searchSections = if (searchIsSymbol) VariableSymbolCatalog.sections else OriginalIconCatalog.sections
    val backdrop = rememberLayerBackdrop(backgroundColor = colorScheme.surface)
    val blurActive = appState.enableBlur && isRuntimeShaderSupported()
    val barColor = if (blurActive) Color.Transparent else colorScheme.surface

    var symbolWeight by remember { mutableFloatStateOf(SymbolWeights.Regular) }
    val listStates = remember { List(IconFamilies.size) { LazyListState() } }
    val activeListState = listStates[currentPage]
    val pageColumns = remember { IntArray(IconFamilies.size) { 3 } }
    val contentUnderTopBar by remember(activeListState, topAppBarScrollBehavior) {
        computedStateOf { activeListState.canScrollBackward || topAppBarScrollBehavior.state.overscrollOffset < -1f }
    }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    @Suppress("DEPRECATION")
    val clipboardManager = LocalClipboardManager.current
    val hapticFeedback = LocalHapticFeedback.current
    val onCopyIconName: (String) -> Unit = remember(clipboardManager, hapticFeedback, snackbarHostState, coroutineScope) {
        { name ->
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            clipboardManager.setText(AnnotatedString(name))
            coroutineScope.launch {
                snackbarHostState.newestSnackbarData()?.dismiss()
                snackbarHostState.showSnackbar(
                    message = "Copied \"$name\"",
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }

    val searchResultCardModifier = remember {
        Modifier.padding(horizontal = 12.dp, vertical = SearchResultVerticalPadding)
    }
    val searchResultIconModifier = remember {
        Modifier.size(24.dp)
    }
    val onSearchResultClick = remember(currentPage, topAppBarScrollBehavior) {
        { index: Int ->
            searchExpanded = false
            legacySearchStatus = legacySearchStatus?.copy(current = SearchStatus.Status.COLLAPSING)
            coroutineScope.launch {
                delay(400.milliseconds)
                val headerOffset = if (currentPage == 0) 1 else 0
                val targetItemIndex = headerOffset + findIconListItemIndex(
                    sections = searchSections,
                    columns = pageColumns[currentPage],
                    targetIconIndex = index,
                )
                val scrollState = topAppBarScrollBehavior.state
                val collapseJob = if (scrollState.heightOffsetLimit < 0f && scrollState.heightOffset > scrollState.heightOffsetLimit) {
                    launch {
                        animate(
                            initialValue = scrollState.heightOffset,
                            targetValue = scrollState.heightOffsetLimit,
                            animationSpec = topAppBarScrollBehavior.snapAnimationSpec ?: spring(),
                        ) { value, _ ->
                            scrollState.heightOffset = value
                        }
                    }
                } else {
                    null
                }
                activeListState.animateScrollToItem(targetItemIndex)
                if (!activeListState.canScrollBackward) {
                    collapseJob?.cancel()
                    scrollState.heightOffset = 0f
                }
            }
            Unit
        }
    }

    val searchResultItems: LazyListScope.(List<Int>) -> Unit = { filteredIndices ->
        items(
            count = filteredIndices.size,
            contentType = { "search_result" },
        ) { i ->
            val index = filteredIndices[i]
            val name = searchIconNames[index]
            Card(modifier = searchResultCardModifier) {
                BasicComponent(
                    title = name,
                    startAction = {
                        if (searchIsSymbol) {
                            val symbol = VariableSymbolCatalog.symbols[index]
                            Symbol(
                                symbol = symbol,
                                contentDescription = name,
                                weight = symbolWeight,
                                tint = if (symbol.defaultColor != Color.Unspecified) {
                                    Color.Unspecified
                                } else {
                                    colorScheme.onBackground
                                },
                                modifier = searchResultIconModifier,
                            )
                        } else {
                            Icon(
                                imageVector = OriginalIconCatalog.regularIcons[index],
                                contentDescription = name,
                                tint = colorScheme.onBackground,
                                modifier = searchResultIconModifier,
                            )
                        }
                    },
                    onClick = { onSearchResultClick(index) },
                )
            }
        }
    }

    Box(Modifier.fillMaxSize().onGloballyPositioned { pageCoordinates.value = it }) {
        Scaffold(
            modifier = Modifier
                .graphicsLayer { alpha = searchBarAlpha.value }
                .then(if (searchExpanded || legacySearchStatus != null) Modifier.clearAndSetSemantics { } else Modifier),
            topBar = {
                BlurredBar(backdrop.takeIf { blurActive }, blurActive) {
                    AdaptiveTopAppBar(
                        title = "Icon",
                        showTopAppBar = appState.showTopAppBar,
                        isWideScreen = isWideScreen,
                        scrollBehavior = topAppBarScrollBehavior,
                        isContentScrolled = contentUnderTopBar,
                        contentModifier = if (glassTopBar) {
                            Modifier.glassSearchBarTransition(
                                searchExpanded,
                                blurEnabled = appState.enableBlur,
                            ) { 1f }
                        } else {
                            Modifier
                        },
                        color = barColor,
                        actions = {
                            if (glassTopBar) {
                                GlassIconButton(onClick = { searchExpanded = true }) {
                                    Icon(
                                        imageVector = MiuixIcons.Symbol.Search.toImageVector(),
                                        contentDescription = "Search icons",
                                        modifier = Modifier.size(28.dp),
                                        tint = colorScheme.onSurface,
                                    )
                                }
                            }
                        },
                        bottomContent = {
                            if (glassTopBar) {
                                GlassSegmentedTabRow(
                                    tabs = IconFamilies,
                                    selectedIndex = pagerState.targetPage,
                                    onSelect = { index ->
                                        coroutineScope.launch { pagerState.springAnimateToPage(index) }
                                    },
                                    backdrop = backdrop,
                                    surfaceAlpha = if (blurActive && contentUnderTopBar) 1f else 0f,
                                    modifier = Modifier.padding(horizontal = 12.dp).padding(top = 6.dp, bottom = 12.dp),
                                )
                            } else {
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .onGloballyPositioned { searchCoordinates.value = it }
                                            .graphicsLayer { alpha = if (legacySearchStatus?.isCollapsed() != false) 1f else 0f }
                                            .clickable {
                                                val offset = checkNotNull(pageCoordinates.value).localPositionOf(
                                                    checkNotNull(searchCoordinates.value),
                                                    Offset.Zero,
                                                )
                                                legacySearchOffset = with(density) { offset.y.toDp() } - searchTopInset()
                                                legacySearchStatus = SearchStatus(
                                                    label = "Search icons",
                                                )
                                            },
                                    ) {
                                        SearchBarFake(
                                            label = "Search icons",
                                            searchBarTopPadding = searchBarTopPadding,
                                        )
                                    }
                                    TabRow(
                                        tabs = IconFamilies,
                                        selectedTabIndex = pagerState.targetPage,
                                        onTabSelected = { index ->
                                            coroutineScope.launch { pagerState.springAnimateToPage(index) }
                                        },
                                        modifier = Modifier.padding(horizontal = 12.dp).padding(top = 6.dp, bottom = 12.dp),
                                    )
                                }
                            }
                        },
                    )
                }
            },
            snackbarHost = {
                val safeBottom = LocalAppPageInsets.current.safeInsets.asPaddingValues().calculateBottomPadding()
                val extraBottom = (padding.calculateBottomPadding() - safeBottom).coerceAtLeast(0.dp)
                SnackbarHost(
                    state = snackbarHostState,
                    modifier = Modifier.padding(bottom = extraBottom),
                )
            },
            contentWindowInsets = LocalAppPageInsets.current.contentInsets,
        ) { innerPadding ->
            val contentPadding = pageContentPadding(
                innerPadding,
                padding,
            )
            Box(modifier = if (blurActive) Modifier.layerBackdrop(backdrop) else Modifier) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    userScrollEnabled = false,
                    beyondViewportPageCount = 0,
                    key = { it },
                ) { page ->
                    val isPageSymbol = page == 0
                    val pageListState = listStates[page]
                    val scrollBarAdapter = rememberScrollBarAdapter(pageListState)
                    val iconNames = if (isPageSymbol) VariableSymbolCatalog.iconNames else OriginalIconCatalog.iconNames
                    val itemCount = iconNames.size
                    val tintColor = colorScheme.onBackground
                    // Dialog state for comparing weight variants of the tapped icon.
                    var showWeightDialog by remember { mutableStateOf(false) }
                    var selectedIconIndex by remember { mutableIntStateOf(0) }

                    BoxWithConstraints(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        val columns = remember(maxWidth) {
                            ((maxWidth - 24.dp) / 80.dp).toInt().coerceIn(3, 8)
                        }
                        pageColumns[page] = columns
                        val sectionRows = remember(page, columns) {
                            if (isPageSymbol) {
                                VariableSymbolCatalog.sectionRowsFor(columns)
                            } else {
                                OriginalIconCatalog.sectionRowsFor(columns)
                            }
                        }
                        LazyColumn(
                            state = pageListState,
                            cacheWindow = remember { LazyLayoutCacheWindow(aheadFraction = 0.5f, behindFraction = 0.25f) },
                            modifier = Modifier
                                .fillMaxSize()
                                .pageScrollModifiers(
                                    appState.enableScrollEndHaptic,
                                    appState.showTopAppBar,
                                    topAppBarScrollBehavior,
                                ),
                            overscrollEffect = null,
                            contentPadding = contentPadding,
                        ) {
                            if (isPageSymbol) {
                                item(key = "symbol_weight_controls", contentType = "symbol_weight_controls") {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 12.dp),
                                        insideMargin = PaddingValues(16.dp),
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                        ) {
                                            Text(
                                                text = "Variable Weight (wght)",
                                                style = MiuixTheme.textStyles.body2,
                                                modifier = Modifier.weight(1f),
                                            )
                                            Text(
                                                text = "${symbolWeight.roundToInt()}",
                                                style = MiuixTheme.textStyles.body2,
                                                color = colorScheme.onSurfaceVariantActions,
                                            )
                                        }
                                        Slider(
                                            value = symbolWeight,
                                            onValueChange = { symbolWeight = it },
                                            valueRange = SymbolWeights.Min..SymbolWeights.Max,
                                            modifier = Modifier.padding(top = 8.dp),
                                        )
                                    }
                                }
                            }
                            sectionRows.forEachIndexed { sectionIndex, (title, rows) ->
                                val isLastSection = sectionIndex == sectionRows.lastIndex
                                item(key = "section_title_$title", contentType = "section_title") {
                                    SmallTitle(
                                        text = title,
                                        modifier = Modifier,
                                    )
                                }
                                items(
                                    count = rows.size,
                                    key = { "section_${title}_row_$it" },
                                    contentType = { "icon_row" },
                                ) { rowIndex ->
                                    val isFirst = rowIndex == 0
                                    val isLast = rowIndex == rows.lastIndex
                                    val rowIndices = rows[rowIndex]
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                start = 12.dp,
                                                end = 12.dp,
                                                bottom = if (isLast && !isLastSection) 12.dp else 0.dp,
                                            )
                                            .let { mod ->
                                                if (isFirst || isLast) {
                                                    mod.squircleClip(
                                                        topStart = if (isFirst) 16.dp else 0.dp,
                                                        topEnd = if (isFirst) 16.dp else 0.dp,
                                                        bottomEnd = if (isLast) 16.dp else 0.dp,
                                                        bottomStart = if (isLast) 16.dp else 0.dp,
                                                    )
                                                } else {
                                                    mod
                                                }
                                            }
                                            .background(colorScheme.surfaceContainer)
                                            .padding(horizontal = 6.dp)
                                            .padding(
                                                top = if (isFirst) 8.dp else 2.dp,
                                                bottom = if (isLast) 8.dp else 2.dp,
                                            ),
                                    ) {
                                        for (col in 0 until columns) {
                                            if (col < rowIndices.size) {
                                                val iconIndex = rowIndices[col]
                                                Column(
                                                    modifier = Modifier
                                                        .weight(1f)
                                                        .clip(IconCellShape)
                                                        .combinedClickable(
                                                            onClick = {
                                                                selectedIconIndex = iconIndex
                                                                showWeightDialog = true
                                                            },
                                                            onLongClick = { onCopyIconName(iconNames[iconIndex]) },
                                                        )
                                                        .padding(horizontal = 4.dp, vertical = 10.dp),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                ) {
                                                    if (isPageSymbol) {
                                                        val symbol = VariableSymbolCatalog.symbols[iconIndex]
                                                        Symbol(
                                                            symbol = symbol,
                                                            contentDescription = iconNames[iconIndex],
                                                            weight = { symbolWeight },
                                                            tint = {
                                                                if (symbol.defaultColor != Color.Unspecified) {
                                                                    Color.Unspecified
                                                                } else {
                                                                    tintColor
                                                                }
                                                            },
                                                            modifier = Modifier.size(28.dp),
                                                        )
                                                    } else {
                                                        Icon(
                                                            imageVector = OriginalIconCatalog.regularIcons[iconIndex],
                                                            contentDescription = iconNames[iconIndex],
                                                            tint = tintColor,
                                                            modifier = Modifier.size(24.dp),
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.height(6.dp))
                                                    Text(
                                                        text = iconNames[iconIndex],
                                                        style = MiuixTheme.textStyles.footnote2,
                                                        color = colorScheme.onSurfaceVariantActions,
                                                        textAlign = TextAlign.Center,
                                                        maxLines = 1,
                                                        overflow = TextOverflow.Ellipsis,
                                                    )
                                                }
                                            } else {
                                                Spacer(modifier = Modifier.weight(1f))
                                            }
                                        }
                                    }
                                }
                            }
                            item(key = "bottom_spacer", contentType = "spacer") { Spacer(modifier = Modifier.height(12.dp)) }
                        }
                        VerticalScrollBar(
                            adapter = scrollBarAdapter,
                            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                            trackPadding = contentPadding,
                            topAppBarScrollBehavior = topAppBarScrollBehavior.takeIf { appState.showTopAppBar },
                        )
                    }

                    val safeIconIndex = selectedIconIndex.coerceIn(0, (itemCount - 1).coerceAtLeast(0))
                    val dialogIconName = iconNames.getOrElse(safeIconIndex) { "" }
                    GlassDialog(
                        show = showWeightDialog && itemCount > 0,
                        backdrop = if (blurActive) backdrop else null,
                        title = dialogIconName,
                        onDismissRequest = { showWeightDialog = false },
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            if (isPageSymbol) {
                                val selectedSymbol = VariableSymbolCatalog.symbols[safeIconIndex]
                                var dialogWeight by remember(safeIconIndex, showWeightDialog) {
                                    mutableFloatStateOf(symbolWeight)
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp, bottom = 12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Symbol(
                                        symbol = selectedSymbol,
                                        contentDescription = dialogIconName,
                                        weight = { dialogWeight },
                                        tint = {
                                            if (selectedSymbol.defaultColor != Color.Unspecified) {
                                                Color.Unspecified
                                            } else {
                                                tintColor
                                            }
                                        },
                                        modifier = Modifier.size(56.dp),
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "wght = ${dialogWeight.roundToInt()} (${selectedSymbol.category.name})",
                                        style = MiuixTheme.textStyles.footnote2,
                                        color = colorScheme.onSurfaceVariantActions,
                                    )
                                    Slider(
                                        value = dialogWeight,
                                        onValueChange = { dialogWeight = it },
                                        valueRange = SymbolWeights.Min..SymbolWeights.Max,
                                        modifier = Modifier.padding(top = 8.dp, bottom = 12.dp),
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.Top,
                                    ) {
                                        SymbolPresetWeights.forEach { (label, preset) ->
                                            Column(
                                                modifier = Modifier
                                                    .weight(1f)
                                                    .clip(IconCellShape)
                                                    .clickable { dialogWeight = preset }
                                                    .padding(vertical = 4.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                            ) {
                                                Symbol(
                                                    symbol = selectedSymbol,
                                                    contentDescription = "$dialogIconName ($label)",
                                                    weight = preset,
                                                    tint = if (selectedSymbol.defaultColor != Color.Unspecified) {
                                                        Color.Unspecified
                                                    } else {
                                                        tintColor
                                                    },
                                                    modifier = Modifier.size(28.dp),
                                                )
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = label,
                                                    style = MiuixTheme.textStyles.footnote2,
                                                    color = colorScheme.onSurfaceVariantActions,
                                                    textAlign = TextAlign.Center,
                                                    maxLines = 1,
                                                )
                                            }
                                        }
                                    }
                                }
                            } else {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp, bottom = 16.dp),
                                    verticalAlignment = Alignment.Top,
                                ) {
                                    OriginalIconCatalog.weightVariants.forEach { (label, icons) ->
                                        icons.getOrNull(safeIconIndex)?.let { icon ->
                                            Column(
                                                modifier = Modifier.weight(1f),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                            ) {
                                                Icon(
                                                    imageVector = icon,
                                                    contentDescription = "$dialogIconName ($label)",
                                                    tint = tintColor,
                                                    modifier = Modifier.size(28.dp),
                                                )
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(
                                                    text = label,
                                                    style = MiuixTheme.textStyles.footnote2,
                                                    color = colorScheme.onSurfaceVariantActions,
                                                    textAlign = TextAlign.Center,
                                                    maxLines = 1,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            GlassDialogButton(
                                onClick = { showWeightDialog = false },
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Text(
                                    text = "Close",
                                    style = MiuixTheme.textStyles.button,
                                )
                            }
                        }
                    }
                }
            }
        }
        if (glassTopBar) {
            val searchListState = rememberLazyListState()
            val pageInsets = LocalAppPageInsets.current
            val searchBarSpace = GlassSearchOverlayDefaults.BarHeight + GlassSearchOverlayDefaults.VerticalPadding * 2
            GlassSearchOverlay(
                onDismissRequest = { searchExpanded = false },
                expanded = searchExpanded,
                scrollMaterialThreshold = GlassSearchOverlayDefaults.VerticalPadding + SearchResultVerticalPadding,
                label = "Search icons",
                scrollOffset = {
                    when {
                        searchListState.layoutInfo.totalItemsCount == 0 -> 0f
                        searchListState.firstVisibleItemIndex > 0 -> Float.MAX_VALUE
                        else -> searchListState.firstVisibleItemScrollOffset.toFloat()
                    }
                },
                topInsets = pageInsets.safeInsets.only(WindowInsetsSides.Top),
                searchTopPadding = pageInsets.searchTop,
                blurEnabled = appState.enableBlur,
                horizontalInsetsModifier = Modifier.appPageHorizontalInsets(),
                searchIcon = MiuixIcons.Symbol.Search.toImageVector(),
                closeIcon = MiuixIcons.Symbol.Close1.toImageVector(),
            ) { query ->
                LaunchedEffect(query) {
                    if (searchListState.firstVisibleItemIndex != 0 || searchListState.firstVisibleItemScrollOffset != 0) {
                        searchListState.scrollToItem(0)
                    }
                }
                val filteredIndices = remember(query, searchIconNames) {
                    filterIconIndices(searchIconNames, query)
                }
                val safeInsetsPadding = pageInsets.safeInsets.asPaddingValues()
                val searchContentPadding = remember(safeInsetsPadding, pageInsets, searchBarSpace, searchResultPadding) {
                    object : PaddingValues {
                        override fun calculateLeftPadding(layoutDirection: LayoutDirection) = 0.dp
                        override fun calculateRightPadding(layoutDirection: LayoutDirection) = 0.dp
                        override fun calculateTopPadding() = safeInsetsPadding.calculateTopPadding() + pageInsets.searchTop() + searchBarSpace
                        override fun calculateBottomPadding() = searchResultPadding.calculateBottomPadding()
                    }
                }
                LazyColumn(
                    state = searchListState,
                    cacheWindow = remember { LazyLayoutCacheWindow(aheadFraction = 0.5f, behindFraction = 0.25f) },
                    modifier = Modifier.fillMaxSize().appPageHorizontalInsets(),
                    contentPadding = searchContentPadding,
                ) {
                    searchResultItems(filteredIndices)
                }
            }
        } else {
            legacySearchStatus?.let { status ->
                LaunchedEffect(Unit) {
                    // Let SearchPager establish its inline position before changing its target.
                    withFrameNanos { }
                    legacySearchStatus = status.copy(current = SearchStatus.Status.EXPANDING)
                }
                val filteredIndices = remember(status.searchText, searchIconNames) {
                    filterIconIndices(searchIconNames, status.searchText)
                }
                Box(
                    Modifier.fillMaxSize().then(searchModifier).onPreviewKeyEvent {
                        if (it.key == Key.Escape && it.type == KeyEventType.KeyUp) {
                            legacySearchStatus = status.copy(current = SearchStatus.Status.COLLAPSING)
                            true
                        } else {
                            false
                        }
                    },
                ) {
                    status.SearchPager(
                        onSearchStatusChange = { next ->
                            legacySearchStatus = if (next.isCollapsed()) null else next.copy(resultStatus = SearchStatus.ResultStatus.SHOW)
                        },
                        offsetY = legacySearchOffset,
                        searchBarTopPadding = searchBarTopPadding,
                        defaultResult = {},
                        result = {
                            searchResultItems(filteredIndices)
                            item { Spacer(Modifier.padding(searchResultPadding)) }
                        },
                    )
                }
            }
        }
    }
}

private fun findIconListItemIndex(
    sections: List<IconSection>,
    columns: Int,
    targetIconIndex: Int,
): Int {
    var listIndex = 0
    for ((_, indices) in sections) {
        listIndex += 1
        val pos = indices.indexOf(targetIconIndex)
        if (pos >= 0) {
            return listIndex + pos / columns
        }
        listIndex += (indices.size + columns - 1) / columns
    }
    return 0
}

private fun filterIconIndices(
    iconNames: List<String>,
    query: String,
): List<Int> {
    val trimmed = query.trim()
    if (trimmed.isEmpty()) return emptyList()
    var exactMatches: MutableList<Int>? = null
    var prefixMatches: MutableList<Int>? = null
    var wordMatches: MutableList<Int>? = null
    var substringMatches: MutableList<Int>? = null
    for (i in iconNames.indices) {
        val name = iconNames[i]
        val matchIndex = name.indexOf(trimmed, ignoreCase = true)
        if (matchIndex < 0) continue
        when {
            name.length == trimmed.length -> (exactMatches ?: mutableListOf<Int>().also { exactMatches = it }).add(i)

            matchIndex == 0 -> (prefixMatches ?: mutableListOf<Int>().also { prefixMatches = it }).add(i)

            isWordBoundaryMatch(name, trimmed, matchIndex) ->
                (wordMatches ?: mutableListOf<Int>().also { wordMatches = it }).add(i)

            else -> (substringMatches ?: mutableListOf<Int>().also { substringMatches = it }).add(i)
        }
    }
    val nonEmptyCount =
        (if (exactMatches != null) 1 else 0) +
            (if (prefixMatches != null) 1 else 0) +
            (if (wordMatches != null) 1 else 0) +
            (if (substringMatches != null) 1 else 0)
    if (nonEmptyCount == 0) return emptyList()
    if (nonEmptyCount == 1) {
        return exactMatches ?: prefixMatches ?: wordMatches ?: substringMatches!!
    }
    val totalSize =
        (exactMatches?.size ?: 0) +
            (prefixMatches?.size ?: 0) +
            (wordMatches?.size ?: 0) +
            (substringMatches?.size ?: 0)
    return buildList(totalSize) {
        exactMatches?.let(::addAll)
        prefixMatches?.let(::addAll)
        wordMatches?.let(::addAll)
        substringMatches?.let(::addAll)
    }
}

private fun isWordBoundaryMatch(
    name: String,
    query: String,
    firstMatchIndex: Int,
): Boolean {
    var idx = firstMatchIndex
    while (idx > 0) {
        if (name[idx].isUpperCase() || !name[idx - 1].isLetterOrDigit()) {
            return true
        }
        idx = name.indexOf(query, startIndex = idx + 1, ignoreCase = true)
    }
    return false
}
