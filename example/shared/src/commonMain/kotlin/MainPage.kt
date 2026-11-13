// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import component.badgeSection
import component.basicComponentSection
import component.breadcrumbBarSection
import component.buttonSection
import component.cardSection
import component.checkboxSection
import component.colorPaletteSection
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
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberBlurBackdrop

@Composable
fun MainPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val glassTopBar = appState.topAppBarStyle == 1
    val isWideScreen = LocalIsWideScreen.current
    val navigator = LocalNavigator.current
    val runtimeShaderSupported = isRuntimeShaderSupported()
    val demoGroups = remember { ComponentDemo.entries.groupBy { it.group } }
    var showOptionsMenu by remember { mutableStateOf(false) }
    var showMoreMenu by remember { mutableStateOf(false) }
    val optionsAnchor = rememberGlassPopupAnchor()
    val moreAnchor = rememberGlassPopupAnchor()

    val backdrop = rememberBlurBackdrop()
    val pageBackdrop = rememberBlurBackdrop()
    val blurActive = backdrop != null
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface

    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val lazyListState = rememberLazyListState()

    var selectedIndex1 by remember { mutableIntStateOf(0) }
    val optionItems = remember(selectedIndex1) {
        listOf(
            DropdownEntry(
                items = listOf("Selection 1", "Selection 2")
                    .mapIndexed { index, text ->
                        DropdownItem(
                            text = text,
                            selected = selectedIndex1 == index,
                            onClick = { selectedIndex1 = index },
                        )
                    },
            ),
        )
    }
    var cascadingSortIndex by remember { mutableIntStateOf(0) }
    var cascadingViewIndex by remember { mutableIntStateOf(0) }
    var cascadingFilterIndex by remember { mutableIntStateOf(0) }
    var cascadingCollapseOnSelection by remember { mutableStateOf(true) }
    val cascadingEntries = remember(
        cascadingSortIndex,
        cascadingViewIndex,
        cascadingFilterIndex,
        cascadingCollapseOnSelection,
    ) {
        val sortLabels = listOf("Sort by capture date", "Sort by date added")
        val viewLabels = listOf("Group by date", "Compact")
        val filterLabels = listOf("All items", "Camera album")
        listOf(
            DropdownEntry(
                items = sortLabels.mapIndexed { idx, label ->
                    DropdownItem(
                        text = label,
                        selected = cascadingSortIndex == idx,
                        onClick = { cascadingSortIndex = idx },
                    )
                },
            ),
            DropdownEntry(
                items = listOf(
                    DropdownItem(
                        text = "Collapse On Selection",
                        selected = cascadingCollapseOnSelection,
                        onClick = {
                            cascadingCollapseOnSelection = !cascadingCollapseOnSelection
                        },
                    ),
                    DropdownItem(
                        text = "View mode",
                        children = viewLabels.mapIndexed { idx, label ->
                            DropdownItem(
                                text = label,
                                selected = cascadingViewIndex == idx,
                                onClick = { cascadingViewIndex = idx },
                            )
                        },
                    ),
                    DropdownItem(
                        text = "Filter",
                        children = filterLabels.mapIndexed { idx, label ->
                            DropdownItem(
                                text = label,
                                selected = cascadingFilterIndex == idx,
                                onClick = { cascadingFilterIndex = idx },
                            )
                        },
                    ),
                ),
            ),
        )
    }
    val multiSelectOptions = remember {
        listOf("Multi selection 1", "Multi selection 2", "Multi selection 3")
    }
    var selectedMultiOptions by remember {
        mutableStateOf(setOf("Multi selection 2", "Multi selection 3"))
    }
    val multiSelectItems = remember(multiSelectOptions, selectedMultiOptions) {
        val items = multiSelectOptions.map { option ->
            DropdownItem(
                text = option,
                selected = option in selectedMultiOptions,
                onClick = {
                    selectedMultiOptions = if (option in selectedMultiOptions) {
                        selectedMultiOptions - option
                    } else {
                        selectedMultiOptions + option
                    }
                },
            )
        }
        listOf(DropdownEntry(items = items))
    }

    val moreItems = remember(optionItems, multiSelectItems) {
        optionItems + multiSelectItems
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
                        color = barColor,
                        actions = {
                            TooltipBox(text = "Options") {
                                if (glassTopBar) {
                                    GlassIconButton(
                                        onClick = { showOptionsMenu = !showOptionsMenu },
                                        modifier = Modifier.glassPopupAnchor(
                                            anchor = optionsAnchor,
                                            cornerRadius = GlassTopAppBarDefaults.ButtonSize / 2,
                                            floating = true,
                                        ),
                                    ) {
                                        Icon(
                                            imageVector = MiuixIcons.Glass.Tune,
                                            contentDescription = "Tune",
                                            modifier = Modifier.size(28.dp).glassPopupAnchorContent(optionsAnchor),
                                        )
                                    }
                                } else {
                                    OverlayIconCascadingDropdownMenu(
                                        entries = cascadingEntries,
                                        collapseOnSelection = cascadingCollapseOnSelection,
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
                                        onClick = { showMoreMenu = !showMoreMenu },
                                        modifier = Modifier.glassPopupAnchor(
                                            anchor = moreAnchor,
                                            cornerRadius = GlassTopAppBarDefaults.ButtonSize / 2,
                                            floating = true,
                                        ),
                                    ) {
                                        Icon(
                                            imageVector = MiuixIcons.Glass.More,
                                            contentDescription = "More",
                                            modifier = Modifier.size(28.dp).glassPopupAnchorContent(moreAnchor),
                                        )
                                    }
                                } else {
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
                        },
                    )
                }
            },
        ) { innerPadding ->
            val contentPadding = pageContentPadding(innerPadding, padding, isWideScreen)
            Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
                LazyColumn(
                    state = lazyListState,
                    modifier = Modifier.pageScrollModifiers(
                        appState.enableScrollEndHaptic,
                        appState.showTopAppBar,
                        topAppBarScrollBehavior,
                    ),
                    contentPadding = contentPadding,
                ) {
                    demoGroups.forEach { (group, demos) ->
                        if (!group.requiresRuntimeShader || runtimeShaderSupported) {
                            if (group != ComponentDemoGroup.Ui) {
                                item(key = group.title) {
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
                                cardSection()
                                tooltipSection()
                                badgeSection()
                                numberPickerSection()
                                colorPaletteSection()
                            }
                            item(key = "${group.title}-pages") {
                                Card(modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp)) {
                                    if (group == ComponentDemoGroup.Ui) {
                                        ArrowPreference(
                                            title = "PullToRefresh",
                                            onClick = { navigator.push(Route.PullToRefresh) },
                                        )
                                    }
                                    demos.forEach { demo ->
                                        ArrowPreference(
                                            title = demo.title,
                                            onClick = { navigator.push(Route.Component(demo)) },
                                        )
                                    }
                                }
                            }
                        }
                    }
                    item(key = "miuix-glass") {
                        SmallTitle(text = "miuix-glass")
                        Card(modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp)) {
                            ArrowPreference(
                                title = "Glass Component",
                                onClick = { navigator.push(Route.Glass) },
                            )
                        }
                    }
                    otherPageSection()
                    item { Spacer(modifier = Modifier.height(12.dp)) }
                }
                VerticalScrollBar(
                    adapter = rememberScrollBarAdapter(lazyListState),
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    trackPadding = contentPadding,
                )
            }
        }
        if (glassTopBar) {
            GlassIconDropdownMenu(
                show = showOptionsMenu,
                onDismissRequest = { showOptionsMenu = false },
                anchor = optionsAnchor,
                anchorContent = { Icon(MiuixIcons.Glass.Tune, contentDescription = null, modifier = Modifier.size(28.dp)) },
                entries = cascadingEntries,
                backdrop = pageBackdrop,
                collapseOnSelection = cascadingCollapseOnSelection,
            )
            GlassIconDropdownMenu(
                show = showMoreMenu,
                onDismissRequest = { showMoreMenu = false },
                anchor = moreAnchor,
                anchorContent = { Icon(MiuixIcons.Glass.More, contentDescription = null, modifier = Modifier.size(28.dp)) },
                entries = moreItems,
                backdrop = pageBackdrop,
                collapseOnSelection = false,
            )
        }
    }
}
