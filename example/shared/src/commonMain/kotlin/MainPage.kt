// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import component.CardItem
import component.badgeSection
import component.basicComponentSection
import component.breadcrumbBarSection
import component.buttonSection
import component.cardSection
import component.checkboxSection
import component.colorPaletteSection
import component.groupedCardItems
import component.numberPickerSection
import component.otherPageSection
import component.progressIndicatorSection
import component.searchBarSection
import component.switchSection
import component.tabRowSection
import component.textFieldSection
import component.tooltipSection
import navigation.ComponentDemo
import navigation.ComponentDemoGroup
import navigation.Route
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TooltipBox
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassIconDropdownMenu
import top.yukonga.miuix.kmp.glass.GlassPopupAnchor
import top.yukonga.miuix.kmp.glass.GlassTopAppBarDefaults
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.icon.extended.Tune
import top.yukonga.miuix.kmp.icon.glass.More
import top.yukonga.miuix.kmp.icon.glass.Tune
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.menu.OverlayIconCascadingDropdownMenu
import top.yukonga.miuix.kmp.menu.OverlayIconDropdownMenu
import top.yukonga.miuix.kmp.nav.transition.NavTransitionCorners
import top.yukonga.miuix.kmp.nav.transition.navTransitionSource
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.TopLayer
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberBlurBackdrop

private val DemoGroups by lazy { ComponentDemo.entries.groupBy { it.group } }

@Composable
fun MainPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val glassTopBar = appState.topAppBarStyle == 1
    val isWideScreen = LocalIsWideScreen.current
    val navigator = LocalNavigator.current
    val runtimeShaderSupported = isRuntimeShaderSupported()
    val menuState = rememberMainPageMenuState()
    val backdrop = rememberBlurBackdrop()
    val pageBackdrop = rememberBlurBackdrop()
    val blurActive = backdrop != null
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface

    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val lazyListState = rememberLazyListState()
    val contentUnderTopBar by remember(lazyListState) {
        computedStateOf { lazyListState.canScrollBackward }
    }
    Box(Modifier.fillMaxSize()) {
        Scaffold(
            modifier = if (pageBackdrop != null) Modifier.layerBackdrop(pageBackdrop) else Modifier,
            topBar = {
                BlurredBar(backdrop, blurActive) {
                    AdaptiveTopAppBar(
                        title = "Home",
                        showTopAppBar = appState.showTopAppBar,
                        isWideScreen = isWideScreen,
                        scrollBehavior = topAppBarScrollBehavior,
                        isContentScrolled = contentUnderTopBar,
                        color = barColor,
                        actions = {
                            MainPageTopBarActions(
                                glassTopBar = glassTopBar,
                                menuState = menuState,
                            )
                        },
                    )
                }
            },
        ) { innerPadding ->
            val contentPadding = pageContentPadding(innerPadding, padding)
            Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.pageScrollModifiers(
                        appState.enableScrollEndHaptic,
                        appState.showTopAppBar,
                        topAppBarScrollBehavior,
                    ),
                    overscrollEffect = null,
                    contentPadding = contentPadding,
                ) {
                    DemoGroups.forEach { (group, demos) ->
                        if (!group.requiresRuntimeShader || runtimeShaderSupported) {
                            if (group != ComponentDemoGroup.Ui) {
                                item(key = group.title, contentType = "section_title") {
                                    SmallTitle(text = group.title)
                                }
                            }
                            if (group == ComponentDemoGroup.Ui) {
                                searchBarSection()
                                basicComponentSection()
                                switchSection()
                                checkboxSection()
                                buttonSection()
                                tabRowSection()
                                breadcrumbBarSection()
                                textFieldSection()
                                progressIndicatorSection()
                                cardSection(backdrop = pageBackdrop)
                                tooltipSection()
                                badgeSection()
                                numberPickerSection()
                                colorPaletteSection()
                            }
                            groupedCardItems(
                                keyPrefix = "${group.title}-pages",
                                outerTopPadding = 0.dp,
                                outerBottomPadding = 12.dp,
                                items = buildList {
                                    if (group == ComponentDemoGroup.Ui) {
                                        add(
                                            CardItem("PullToRefresh") {
                                                ArrowPreference(
                                                    title = "PullToRefresh",
                                                    modifier = Modifier.navTransitionSource(
                                                        Route.PullToRefresh,
                                                        containerColor = MiuixTheme.colorScheme.surfaceContainer,
                                                        corners = NavTransitionCorners(CardDefaults.CornerRadius, CardDefaults.CornerRadius, 0.dp, 0.dp),
                                                        sourceKey = "pullToRefresh",
                                                    ),
                                                    onClick = { navigator.push(Route.PullToRefresh) },
                                                )
                                            },
                                        )
                                    }
                                    demos.forEachIndexed { index, demo ->
                                        val top = if (index == 0 && group != ComponentDemoGroup.Ui) CardDefaults.CornerRadius else 0.dp
                                        val bottom = if (index == demos.lastIndex) CardDefaults.CornerRadius else 0.dp
                                        add(
                                            CardItem(demo.name) {
                                                val destination = Route.Component(demo)
                                                ArrowPreference(
                                                    title = demo.title,
                                                    modifier = Modifier.navTransitionSource(
                                                        destination,
                                                        containerColor = MiuixTheme.colorScheme.surfaceContainer,
                                                        corners = NavTransitionCorners(top, top, bottom, bottom),
                                                        sourceKey = demo.name,
                                                    ),
                                                    onClick = { navigator.push(destination) },
                                                )
                                            },
                                        )
                                    }
                                },
                            )
                        }
                    }
                    item(key = "miuix-glass", contentType = "glass_section") {
                        SmallTitle(text = "miuix-glass")
                        Card(modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp)) {
                            ArrowPreference(
                                title = "Glass Component",
                                modifier = Modifier.navTransitionSource(Route.Glass, containerColor = MiuixTheme.colorScheme.surfaceContainer, sourceKey = "glass"),
                                onClick = { navigator.push(Route.Glass) },
                            )
                        }
                    }
                    otherPageSection()
                    item(key = "bottom_spacer", contentType = "spacer") { Spacer(modifier = Modifier.height(12.dp)) }
                }
                VerticalScrollBar(
                    adapter = rememberScrollBarAdapter(lazyListState),
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    trackPadding = contentPadding,
                    topAppBarScrollBehavior = topAppBarScrollBehavior.takeIf { appState.showTopAppBar },
                )
            }
        }
        if (glassTopBar) {
            // Above the floating glass sidebar, which overlays the page in wide layouts.
            TopLayer {
                MainPageGlassMenus(
                    menuState = menuState,
                    pageBackdrop = pageBackdrop,
                )
            }
        }
    }
}

@Stable
private class MainPageMenuState(
    val optionsAnchor: GlassPopupAnchor,
    val moreAnchor: GlassPopupAnchor,
) {
    var showOptionsMenu by mutableStateOf(false)
    var showMoreMenu by mutableStateOf(false)
    var selectedIndex1 by mutableIntStateOf(0)
    var cascadingSortIndex by mutableIntStateOf(0)
    var cascadingViewIndex by mutableIntStateOf(0)
    var cascadingFilterIndex by mutableIntStateOf(0)
    var cascadingCollapseOnSelection by mutableStateOf(true)
    var selectedMultiOptions by mutableStateOf(setOf("Multi selection 2", "Multi selection 3"))
}

@Composable
private fun rememberMainPageMenuState(): MainPageMenuState {
    val optionsAnchor = rememberGlassPopupAnchor()
    val moreAnchor = rememberGlassPopupAnchor()
    return remember(optionsAnchor, moreAnchor) {
        MainPageMenuState(optionsAnchor = optionsAnchor, moreAnchor = moreAnchor)
    }
}

@Composable
private fun MainPageTopBarActions(
    glassTopBar: Boolean,
    menuState: MainPageMenuState,
) {
    TooltipBox(text = "Options") {
        if (glassTopBar) {
            GlassIconButton(
                onClick = { menuState.showOptionsMenu = !menuState.showOptionsMenu },
                modifier = Modifier.glassPopupAnchor(
                    anchor = menuState.optionsAnchor,
                    cornerRadius = GlassTopAppBarDefaults.ButtonSize / 2,
                    floating = true,
                ),
            ) {
                Icon(
                    imageVector = MiuixIcons.Glass.Tune,
                    contentDescription = "Tune",
                    modifier = Modifier.size(28.dp).glassPopupAnchorContent(menuState.optionsAnchor),
                )
            }
        } else {
            val cascadingEntries = rememberMainPageCascadingEntries(menuState)
            OverlayIconCascadingDropdownMenu(
                entries = cascadingEntries,
                collapseOnSelection = menuState.cascadingCollapseOnSelection,
            ) {
                Icon(
                    imageVector = MiuixIcons.Tune,
                    contentDescription = "Tune",
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
    Spacer(Modifier.width(8.dp))
    TooltipBox(text = "More") {
        if (glassTopBar) {
            GlassIconButton(
                onClick = { menuState.showMoreMenu = !menuState.showMoreMenu },
                modifier = Modifier.glassPopupAnchor(
                    anchor = menuState.moreAnchor,
                    cornerRadius = GlassTopAppBarDefaults.ButtonSize / 2,
                    floating = true,
                ),
            ) {
                Icon(
                    imageVector = MiuixIcons.Glass.More,
                    contentDescription = "More",
                    modifier = Modifier.size(28.dp).glassPopupAnchorContent(menuState.moreAnchor),
                )
            }
        } else {
            val moreItems = rememberMainPageMoreItems(menuState)
            OverlayIconDropdownMenu(
                entries = moreItems,
                collapseOnSelection = false,
            ) {
                Icon(
                    imageVector = MiuixIcons.More,
                    contentDescription = "More",
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}

@Composable
private fun BoxScope.MainPageGlassMenus(
    menuState: MainPageMenuState,
    pageBackdrop: top.yukonga.miuix.kmp.blur.LayerBackdrop?,
) {
    val cascadingEntries = rememberMainPageCascadingEntries(menuState)
    val moreItems = rememberMainPageMoreItems(menuState)
    GlassIconDropdownMenu(
        show = menuState.showOptionsMenu,
        onDismissRequest = { menuState.showOptionsMenu = false },
        anchor = menuState.optionsAnchor,
        anchorContent = { Icon(MiuixIcons.Glass.Tune, contentDescription = null, modifier = Modifier.size(28.dp)) },
        entries = cascadingEntries,
        backdrop = pageBackdrop,
        collapseOnSelection = menuState.cascadingCollapseOnSelection,
    )
    GlassIconDropdownMenu(
        show = menuState.showMoreMenu,
        onDismissRequest = { menuState.showMoreMenu = false },
        anchor = menuState.moreAnchor,
        anchorContent = { Icon(MiuixIcons.Glass.More, contentDescription = null, modifier = Modifier.size(28.dp)) },
        entries = moreItems,
        backdrop = pageBackdrop,
        collapseOnSelection = false,
    )
}

@Composable
private fun rememberMainPageCascadingEntries(menuState: MainPageMenuState): List<DropdownEntry> = remember(
    menuState.cascadingSortIndex,
    menuState.cascadingViewIndex,
    menuState.cascadingFilterIndex,
    menuState.cascadingCollapseOnSelection,
) {
    val sortLabels = listOf("Sort by capture date", "Sort by date added")
    val viewLabels = listOf("Group by date", "Compact")
    val filterLabels = listOf("All items", "Camera album")
    listOf(
        DropdownEntry(
            items = sortLabels.mapIndexed { idx, label ->
                DropdownItem(
                    text = label,
                    selected = menuState.cascadingSortIndex == idx,
                    onClick = { menuState.cascadingSortIndex = idx },
                )
            },
        ),
        DropdownEntry(
            items = listOf(
                DropdownItem(
                    text = "Collapse On Selection",
                    selected = menuState.cascadingCollapseOnSelection,
                    onClick = {
                        menuState.cascadingCollapseOnSelection = !menuState.cascadingCollapseOnSelection
                    },
                ),
                DropdownItem(
                    text = "View mode",
                    children = viewLabels.mapIndexed { idx, label ->
                        DropdownItem(
                            text = label,
                            selected = menuState.cascadingViewIndex == idx,
                            onClick = { menuState.cascadingViewIndex = idx },
                        )
                    },
                ),
                DropdownItem(
                    text = "Filter",
                    children = filterLabels.mapIndexed { idx, label ->
                        DropdownItem(
                            text = label,
                            selected = menuState.cascadingFilterIndex == idx,
                            onClick = { menuState.cascadingFilterIndex = idx },
                        )
                    },
                ),
            ),
        ),
    )
}

@Composable
private fun rememberMainPageMoreItems(menuState: MainPageMenuState): List<DropdownEntry> {
    val optionItems = remember(menuState.selectedIndex1) {
        listOf(
            DropdownEntry(
                items = listOf("Selection 1", "Selection 2").mapIndexed { index, text ->
                    DropdownItem(
                        text = text,
                        selected = menuState.selectedIndex1 == index,
                        onClick = { menuState.selectedIndex1 = index },
                    )
                },
            ),
        )
    }
    val multiSelectItems = remember(menuState.selectedMultiOptions) {
        val items = MainPageMultiSelectOptions.map { option ->
            DropdownItem(
                text = option,
                selected = option in menuState.selectedMultiOptions,
                onClick = {
                    menuState.selectedMultiOptions = if (option in menuState.selectedMultiOptions) {
                        menuState.selectedMultiOptions - option
                    } else {
                        menuState.selectedMultiOptions + option
                    }
                },
            )
        }
        listOf(DropdownEntry(items = items))
    }
    return remember(optionItems, multiSelectItems) { optionItems + multiSelectItems }
}

private val MainPageMultiSelectOptions = listOf("Multi selection 1", "Multi selection 2", "Multi selection 3")
