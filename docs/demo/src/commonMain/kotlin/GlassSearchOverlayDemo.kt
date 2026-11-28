// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassSearchOverlay
import top.yukonga.miuix.kmp.glass.GlassSearchOverlayDefaults
import top.yukonga.miuix.kmp.glass.GlassTopAppBar
import top.yukonga.miuix.kmp.glass.glassSearchBarTransition
import top.yukonga.miuix.kmp.glass.rememberGlassSearchBarAlpha
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.Close1
import top.yukonga.miuix.kmp.icon.symbol.Search
import top.yukonga.miuix.kmp.symbol.toImageVector
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassSearchOverlayDemo() {
    val backdrop = rememberLayerBackdrop()
    val scrollBehavior = MiuixScrollBehavior()
    val pageListState = rememberLazyListState()
    val searchListState = rememberLazyListState()
    var expanded by remember { mutableStateOf(false) }
    val searchBarAlpha = rememberGlassSearchBarAlpha(expanded)
    val topInset = 36.dp
    val topInsets = remember(topInset) { WindowInsets(top = topInset) }
    val searchResultTopPadding = 12.dp
    val searchBarPadding = GlassSearchOverlayDefaults.VerticalPadding
    val sampleItems = remember {
        listOf(
            "AccessControl",
            "Add",
            "AddBookmark",
            "AddCircle",
            "AddFolder",
            "Address",
            "Alarm",
            "Audio",
            "Back",
            "Backup",
            "Calendar",
            "Camera",
            "Contacts",
            "Settings",
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(demoBackground()),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer { alpha = searchBarAlpha.value },
                        containerColor = MiuixTheme.colorScheme.surface,
                        contentWindowInsets = WindowInsets(0, 0, 0, 0),
                        topBar = {
                            GlassTopAppBar(
                                title = "GlassSearchOverlay",
                                contentModifier = Modifier
                                    .glassSearchBarTransition(expanded) { 1f }
                                    .windowInsetsPadding(topInsets),
                                defaultWindowInsetsPadding = false,
                                isContentScrolled = pageListState.canScrollBackward,
                                backdrop = backdrop,
                                scrollBehavior = scrollBehavior,
                                progressiveBlur = true,
                                actions = {
                                    GlassIconButton(
                                        onClick = { expanded = true },
                                    ) {
                                        Icon(
                                            imageVector = MiuixIcons.Symbol.Search.toImageVector(),
                                            contentDescription = "Search",
                                            modifier = Modifier.size(28.dp),
                                            tint = MiuixTheme.colorScheme.onSurface,
                                        )
                                    }
                                },
                            )
                        },
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .layerBackdrop(backdrop)
                                .background(MiuixTheme.colorScheme.surface),
                        ) {
                            LazyColumn(
                                state = pageListState,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                                contentPadding = PaddingValues(
                                    start = 16.dp,
                                    top = paddingValues.calculateTopPadding() + 12.dp,
                                    end = 16.dp,
                                    bottom = 16.dp,
                                ),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                items(sampleItems) { item ->
                                    Card(modifier = Modifier.fillMaxWidth()) {
                                        Text(
                                            text = item,
                                            modifier = Modifier.padding(16.dp),
                                            color = MiuixTheme.colorScheme.onSurface,
                                        )
                                    }
                                }
                            }
                        }
                    }

                    GlassSearchOverlay(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        scrollMaterialThreshold = searchBarPadding + searchResultTopPadding,
                        label = "Search items",
                        topInsets = topInsets,
                        searchIcon = MiuixIcons.Symbol.Search.toImageVector(),
                        closeIcon = MiuixIcons.Symbol.Close1.toImageVector(),
                        scrollOffset = {
                            when {
                                searchListState.layoutInfo.totalItemsCount == 0 -> 0f
                                searchListState.firstVisibleItemIndex > 0 -> Float.MAX_VALUE
                                else -> searchListState.firstVisibleItemScrollOffset.toFloat()
                            }
                        },
                    ) { query ->
                        val filtered = remember(query) {
                            if (query.isBlank()) emptyList() else sampleItems.filter { it.contains(query, ignoreCase = true) }
                        }
                        val searchContentTop = topInset + GlassSearchOverlayDefaults.BarHeight + searchBarPadding * 2 + searchResultTopPadding
                        LazyColumn(
                            state = searchListState,
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                top = searchContentTop,
                                end = 16.dp,
                                bottom = 16.dp,
                            ),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(filtered) { item ->
                                Card(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = item,
                                        modifier = Modifier.padding(16.dp),
                                        color = MiuixTheme.colorScheme.onSurface,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
