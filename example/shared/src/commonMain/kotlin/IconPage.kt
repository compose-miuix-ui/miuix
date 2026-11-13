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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import utils.AdaptiveTopAppBar
import utils.All
import utils.BlurredBar
import utils.SearchStatus
import utils.pageContentPadding
import utils.pageScrollModifiers
import kotlin.time.Duration.Companion.milliseconds

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

    val iconFamilies = remember { listOf("Original", "Glass") }
    var glass by remember { mutableStateOf(false) }
    val allIcons = remember(glass) { if (glass) MiuixIcons.Glass.All else MiuixIcons.All }
    val regularIcons = remember(allIcons) { allIcons["Regular"] ?: emptyList() }
    val weightVariants: List<Pair<String, List<ImageVector>>> = remember(allIcons) {
        listOf("Light", "Normal", "Regular", "Medium", "Demibold").map { name ->
            name to (allIcons[name] ?: emptyList())
        }
    }
    val iconNames = remember(regularIcons) { regularIcons.map { it.name.substringBefore(".") } }

    // Accordion state — only one row may be expanded at a time. -1 = none.
    var expandedIndex by remember { mutableIntStateOf(-1) }

    val surfaceColor = colorScheme.surface
    val backdrop = rememberLayerBackdrop {
        drawRect(surfaceColor)
        drawContent()
    }
    val blurActive = appState.enableBlur && isRuntimeShaderSupported()
    val barColor = if (blurActive) Color.Transparent else colorScheme.surface

    val lazyListState = rememberLazyListState()
    val scrollBarAdapter = rememberScrollBarAdapter(lazyListState)
    val headerHeight = remember { mutableIntStateOf(0) }
    val saturatedScrollDistance = with(density) {
        maxOf(
            GlassTopAppBarDefaults.RampStart + GlassTopAppBarDefaults.RampDistance,
            GlassTopAppBarDefaults.ProgressiveBlurRampDistance,
        ).toPx()
    }
    val visualContentOffset: () -> Float = remember(lazyListState, headerHeight, topAppBarScrollBehavior, saturatedScrollDistance) {
        {
            // Keep progress continuous through the first icon row, then saturate.
            when (lazyListState.firstVisibleItemIndex) {
                0 -> -lazyListState.firstVisibleItemScrollOffset.toFloat() + topAppBarScrollBehavior.state.overscrollOffset

                1 -> {
                    val measuredHeaderHeight = headerHeight.intValue
                    if (measuredHeaderHeight > 0) {
                        -(measuredHeaderHeight + lazyListState.firstVisibleItemScrollOffset.toFloat()) + topAppBarScrollBehavior.state.overscrollOffset
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
                    title = iconNames[index],
                    startAction = {
                        Icon(
                            imageVector = regularIcons[index],
                            contentDescription = iconNames[index],
                            tint = colorScheme.onBackground,
                            modifier = Modifier.size(if (glass) 28.dp else 24.dp),
                        )
                    },
                    onClick = {
                        searchExpanded = false
                        legacySearchStatus = legacySearchStatus?.copy(current = SearchStatus.Status.COLLAPSING)
                        coroutineScope.launch {
                            delay(400.milliseconds)
                            // The header is the only list item before icon rows.
                            lazyListState.animateScrollToItem(index + 1)
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
                        isContentScrolled = lazyListState.canScrollBackward,
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
                                    selectedIndex = if (glass) 1 else 0,
                                    onSelect = { index ->
                                        glass = index == 1
                                        expandedIndex = -1
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
                                                val offset = checkNotNull(pageCoordinates.value).localPositionOf(checkNotNull(searchCoordinates.value), Offset.Zero)
                                                legacySearchOffset = with(density) { offset.y.toDp() }
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
                                        selectedTabIndex = if (glass) 1 else 0,
                                        onTabSelected = { index ->
                                            glass = index == 1
                                            expandedIndex = -1
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
                isWideScreen,
            )
            Box(modifier = if (blurActive) Modifier.layerBackdrop(backdrop) else Modifier) {
                LazyColumn(
                    state = lazyListState,
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
                                .onSizeChanged { headerHeight.intValue = it.height }
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
                                    modifier = Modifier.size(if (glass) 28.dp else 24.dp),
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
                                                modifier = Modifier.size(if (glass) 28.dp * (7f / 6f) else 28.dp),
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
        if (glassTopBar) {
            GlassSearchOverlay(
                onDismissRequest = { searchExpanded = false },
                expanded = searchExpanded,
                label = "Search icons",
            ) { query ->
                val filteredIndices = remember(query, iconNames) {
                    if (query.isBlank()) emptyList() else iconNames.indices.filter { iconNames[it].contains(query, ignoreCase = true) }
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = padding.calculateBottomPadding()),
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
                val filteredIndices = remember(status.searchText, iconNames) {
                    if (status.searchText.isBlank()) emptyList() else iconNames.indices.filter { iconNames[it].contains(status.searchText, ignoreCase = true) }
                }
                Box(
                    Modifier.fillMaxSize().onPreviewKeyEvent {
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
                            item { Spacer(Modifier.height(padding.calculateBottomPadding())) }
                        },
                    )
                }
            }
        }
    }
}
