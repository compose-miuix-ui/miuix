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
import androidx.compose.foundation.layout.widthIn
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
import top.yukonga.miuix.kmp.glass.GlassTopAppBar
import top.yukonga.miuix.kmp.glass.GlassTopAppBarDefaults
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.glass.ChevronBackward
import top.yukonga.miuix.kmp.icon.glass.More
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun GlassTopAppBarDemo() {
    val backdrop = rememberLayerBackdrop()
    val scrollBehavior = MiuixScrollBehavior()
    val listState = rememberLazyListState()
    var showAlbums by remember { mutableStateOf(false) }
    var moreCount by remember { mutableIntStateOf(0) }
    val pageTitle = if (showAlbums) "Albums" else "Library"

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
                Scaffold(
                    containerColor = MiuixTheme.colorScheme.surface,
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    topBar = {
                        GlassTopAppBar(
                            title = pageTitle,
                            largeTitle = "Your $pageTitle",
                            subtitle = "More pressed: $moreCount",
                            backdrop = backdrop,
                            isContentScrolled = listState.canScrollBackward,
                            scrollBehavior = scrollBehavior,
                            bandBrush = GlassTopAppBarDefaults.bandBrush(MiuixTheme.colorScheme.surface),
                            defaultWindowInsetsPadding = false,
                            navigationIcon = {
                                GlassIconButton(onClick = { showAlbums = !showAlbums }) {
                                    Icon(
                                        imageVector = MiuixIcons.Glass.ChevronBackward,
                                        contentDescription = "Switch library view",
                                        tint = MiuixTheme.colorScheme.onSurface,
                                    )
                                }
                            },
                            actions = {
                                GlassIconButton(onClick = { moreCount++ }) {
                                    Icon(
                                        imageVector = MiuixIcons.Glass.More,
                                        contentDescription = "More actions",
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
                            modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
                            state = listState,
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                top = paddingValues.calculateTopPadding() + 12.dp,
                                end = 16.dp,
                                bottom = 16.dp,
                            ),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            item {
                                Text(
                                    text = "Scroll to collapse the title. Use the arrows to switch views.",
                                    color = MiuixTheme.colorScheme.onSurface,
                                )
                            }
                            items(20) { index ->
                                Card(modifier = Modifier.fillMaxWidth()) {
                                    Column(
                                        modifier = Modifier.padding(20.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                    ) {
                                        Text(
                                            text = "$pageTitle item ${index + 1}",
                                            style = MiuixTheme.textStyles.title3,
                                        )
                                        Text(
                                            text = if (showAlbums) "A collection of favorite moments" else "Photos from your recent days",
                                            color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
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
}
