// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.style.TextAlign
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
import top.yukonga.miuix.kmp.basic.TabRow
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassTopAppBarDefaults
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.ExpandLess
import top.yukonga.miuix.kmp.icon.extended.ExpandMore
import top.yukonga.miuix.kmp.icon.glass.Search
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.squircle.squircleClip
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import top.yukonga.miuix.kmp.utils.springAnimateToPage
import utils.AdaptiveTopAppBar
import utils.All
import utils.BlurredBar
import utils.LocalAppPageInsets
import utils.SearchStatus
import utils.appPageSearchInsets
import utils.pageBottomPadding
import utils.pageContentPadding
import utils.pageScrollModifiers
import kotlin.time.Duration.Companion.milliseconds

private data class IconFamilyCatalog(
    val regularIcons: List<ImageVector>,
    val weightVariants: List<Pair<String, List<ImageVector>>>,
    val iconNames: List<String>,
)

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
    val pageCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }
    val searchCoordinates = remember { mutableStateOf<LayoutCoordinates?>(null) }
    val density = LocalDensity.current
    val searchTopInset = LocalAppPageInsets.current.searchTop
    val searchModifier = Modifier.appPageSearchInsets()
    val searchResultPadding = pageBottomPadding(padding)

    val iconFamilies = remember { listOf("Glass", "Original") }
    val iconCatalogs = remember {
        listOf(MiuixIcons.Glass.All, MiuixIcons.All).map { allIcons ->
            val regular = allIcons["Regular"] ?: emptyList()
            val weights = listOf("Light", "Normal", "Regular", "Medium", "Demibold").map { name ->
                name to (allIcons[name] ?: emptyList())
            }
            val names = regular.map { it.name.substringBefore(".") }
            IconFamilyCatalog(
                regularIcons = regular,
                weightVariants = weights,
                iconNames = names,
            )
        }
    }
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { iconFamilies.size })
    val currentPage by remember { derivedStateOf { pagerState.currentPage } }
    val searchIsGlass = currentPage == 0
    val searchCatalog = iconCatalogs[currentPage]
    val searchRegularIcons = searchCatalog.regularIcons
    val searchIconNames = searchCatalog.iconNames
    val surfaceColor = colorScheme.surface
    val backdrop = rememberLayerBackdrop {
        drawRect(surfaceColor)
        drawContent()
    }
    val blurActive = appState.enableBlur && isRuntimeShaderSupported()
    val barColor = if (blurActive) Color.Transparent else colorScheme.surface

    val listStates = remember { List(2) { LazyListState() } }
    val headerHeights = remember { List(2) { mutableIntStateOf(0) } }
    val activeListState = listStates[currentPage]
    val activeHeaderHeight = headerHeights[currentPage]
    val saturatedScrollDistance = with(density) {
        maxOf(
            GlassTopAppBarDefaults.RampStart + GlassTopAppBarDefaults.RampDistance,
            GlassTopAppBarDefaults.ProgressiveBlurRampDistance,
        ).toPx()
    }
    val visualContentOffset: () -> Float = remember(activeListState, activeHeaderHeight, topAppBarScrollBehavior, saturatedScrollDistance) {
        {
            // Keep progress continuous through the first icon row, then saturate.
            when (activeListState.firstVisibleItemIndex) {
                0 -> -activeListState.firstVisibleItemScrollOffset.toFloat() + topAppBarScrollBehavior.state.overscrollOffset

                1 -> {
                    val measuredHeaderHeight = activeHeaderHeight.intValue
                    if (measuredHeaderHeight > 0) {
                        -(measuredHeaderHeight + activeListState.firstVisibleItemScrollOffset.toFloat()) + topAppBarScrollBehavior.state.overscrollOffset
                    } else {
                        // Restored lists may skip measuring the header.
                        -saturatedScrollDistance
                    }
                }

                else -> -saturatedScrollDistance
            }
        }
    }
    val coroutineScope = rememberCoroutineScope()

    val searchResultItems: LazyListScope.(List<Int>) -> Unit = { filteredIndices ->
        items(
            count = filteredIndices.size,
            key = { filteredIndices[it] },
        ) { i ->
            val index = filteredIndices[i]
            Card(modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)) {
                BasicComponent(
                    title = searchIconNames[index],
                    startAction = {
                        Icon(
                            imageVector = searchRegularIcons[index],
                            contentDescription = searchIconNames[index],
                            tint = colorScheme.onBackground,
                            modifier = Modifier.size(if (searchIsGlass) 28.dp else 24.dp),
                        )
                    },
                    onClick = {
                        searchExpanded = false
                        legacySearchStatus = legacySearchStatus?.copy(current = SearchStatus.Status.COLLAPSING)
                        coroutineScope.launch {
                            delay(400.milliseconds)
                            // The header is the only list item before icon rows.
                            activeListState.animateScrollToItem(index + 1)
                        }
                    },
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
                        isContentScrolled = activeListState.canScrollBackward,
                        visualContentOffset = visualContentOffset,
                        contentModifier = if (glassTopBar) Modifier.glassSearchBarTransition(searchExpanded) { 1f } else Modifier,
                        color = barColor,
                        actions = {
                            if (glassTopBar) {
                                GlassIconButton(onClick = { searchExpanded = true }) {
                                    Icon(
                                        imageVector = MiuixIcons.Glass.Search,
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
                                    tabs = iconFamilies,
                                    selectedIndex = pagerState.targetPage,
                                    onSelect = { index ->
                                        coroutineScope.launch { pagerState.springAnimateToPage(index) }
                                    },
                                    backdrop = backdrop,
                                    surfaceAlpha = if (blurActive) 1f else 0f,
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
                                        tabs = iconFamilies,
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
                    beyondViewportPageCount = 1,
                    key = { it },
                ) { page ->
                    val isPageGlass = page == 0
                    val pageListState = listStates[page]
                    val pageHeaderHeight = headerHeights[page]
                    val scrollBarAdapter = rememberScrollBarAdapter(pageListState)
                    val catalog = iconCatalogs[page]
                    val regularIcons = catalog.regularIcons
                    val weightVariants = catalog.weightVariants
                    val iconNames = catalog.iconNames
                    // Accordion state — only one row may be expanded at a time. -1 = none.
                    var expandedIndex by remember { mutableIntStateOf(-1) }

                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            state = pageListState,
                            modifier = Modifier.pageScrollModifiers(
                                appState.enableScrollEndHaptic,
                                appState.showTopAppBar,
                                topAppBarScrollBehavior,
                            ),
                            contentPadding = contentPadding,
                        ) {
                            item(key = "iconsHeader") {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onSizeChanged { pageHeaderHeight.intValue = it.height }
                                        .padding(horizontal = 12.dp)
                                        .squircleClip(
                                            topStart = 16.dp,
                                            topEnd = 16.dp,
                                            bottomEnd = 0.dp,
                                            bottomStart = 0.dp,
                                        )
                                        .background(colorScheme.surfaceContainer)
                                        .padding(horizontal = 16.dp)
                                        .padding(top = 12.dp, bottom = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "Name",
                                        modifier = Modifier.weight(1f),
                                        style = MiuixTheme.textStyles.footnote1,
                                        color = colorScheme.onSurfaceVariantActions,
                                    )
                                    Text(
                                        text = "Tap to compare weights",
                                        style = MiuixTheme.textStyles.footnote2,
                                        color = colorScheme.onSurfaceVariantActions,
                                    )
                                }
                            }
                            items(
                                count = regularIcons.size,
                                key = { "icon_$it" },
                            ) { index ->
                                val isLast = index == regularIcons.lastIndex
                                val bottomPadding = if (isLast) 6.dp else 0.dp
                                val expanded = expandedIndex == index
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp)
                                        .squircleClip(
                                            topStart = 0.dp,
                                            topEnd = 0.dp,
                                            bottomEnd = if (isLast) 16.dp else 0.dp,
                                            bottomStart = if (isLast) 16.dp else 0.dp,
                                        )
                                        .background(colorScheme.surfaceContainer)
                                        .clickable { expandedIndex = if (expanded) -1 else index }
                                        .padding(horizontal = 16.dp)
                                        .padding(vertical = 6.dp)
                                        .padding(bottom = bottomPadding),
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = iconNames[index],
                                            modifier = Modifier.weight(1f),
                                            style = MiuixTheme.textStyles.body2,
                                            color = colorScheme.onSurface,
                                        )
                                        Icon(
                                            imageVector = regularIcons[index],
                                            contentDescription = iconNames[index],
                                            tint = colorScheme.onBackground,
                                            modifier = Modifier.size(if (isPageGlass) 28.dp else 24.dp),
                                        )
                                        Spacer(modifier = Modifier.size(8.dp))
                                        Icon(
                                            imageVector = if (expanded) MiuixIcons.ExpandLess else MiuixIcons.ExpandMore,
                                            contentDescription = if (expanded) "Collapse" else "Expand",
                                            tint = colorScheme.onSurfaceVariantActions,
                                            modifier = Modifier.size(18.dp),
                                        )
                                    }
                                    AnimatedVisibility(visible = expanded) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(top = 10.dp, bottom = 4.dp),
                                            verticalAlignment = Alignment.Top,
                                        ) {
                                            weightVariants.forEach { (label, icons) ->
                                                Column(
                                                    modifier = Modifier.weight(1f),
                                                    horizontalAlignment = Alignment.CenterHorizontally,
                                                ) {
                                                    Icon(
                                                        imageVector = icons[index],
                                                        contentDescription = "${iconNames[index]} ($label)",
                                                        tint = colorScheme.onBackground,
                                                        modifier = Modifier.size(if (isPageGlass) 28.dp * (7f / 6f) else 28.dp),
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
                            }
                            item { Spacer(modifier = Modifier.height(12.dp)) }
                        }
                        VerticalScrollBar(
                            adapter = scrollBarAdapter,
                            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                            trackPadding = contentPadding,
                        )
                    }
                }
            }
        }
        if (glassTopBar) {
            GlassSearchOverlay(
                onDismissRequest = { searchExpanded = false },
                expanded = searchExpanded,
                modifier = searchModifier,
                label = "Search icons",
            ) { query ->
                val filteredIndices = remember(query, searchIconNames) {
                    if (query.isBlank()) {
                        emptyList()
                    } else {
                        searchIconNames.indices.filter {
                            searchIconNames[it].contains(
                                query,
                                ignoreCase = true,
                            )
                        }
                    }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = searchResultPadding,
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
                    if (status.searchText.isBlank()) {
                        emptyList()
                    } else {
                        searchIconNames.indices.filter {
                            searchIconNames[it].contains(
                                status.searchText,
                                ignoreCase = true,
                            )
                        }
                    }
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
