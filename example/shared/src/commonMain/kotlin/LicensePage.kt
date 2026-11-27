// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.layout.LazyLayoutCacheWindow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.Library
import utils.LocalAboutLibrariesJsonLoader
import utils.SimpleJsonParser
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberAboutLibrariesJsonLoader

@Composable
fun LicensePage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val blurSupported = isRuntimeShaderSupported()
    val backdrop = if (blurSupported) {
        rememberLayerBackdrop(backgroundColor = MiuixTheme.colorScheme.surface)
    } else {
        null
    }
    val blurActive = appState.enableBlur && blurSupported
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current

    val fallbackLoader = rememberAboutLibrariesJsonLoader()
    val loadAboutLibrariesJson = LocalAboutLibrariesJsonLoader.current ?: fallbackLoader
    val libraries by produceState<List<Library>?>(initialValue = null, loadAboutLibrariesJson) {
        try {
            val jsonString = loadAboutLibrariesJson()
            val libs = withContext(Dispatchers.Default) { SimpleJsonParser(jsonString).parseLibs() }
            value = libs.libraries
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Scaffold(
        topBar = {
            BlurredBar(backdrop, blurActive) {
                AdaptiveTopAppBar(
                    title = "Third Party Licenses",
                    showTopAppBar = appState.showTopAppBar,
                    isWideScreen = isWideScreen,
                    scrollBehavior = topAppBarScrollBehavior,
                    color = barColor,
                    navigationIcon = {
                        BackNavigationIcon(
                            onClick = { navigator.pop() },
                        )
                    },
                )
            }
        },
    ) { innerPadding ->
        val uriHandler = LocalUriHandler.current
        val lazyListState = rememberLazyListState()
        val contentPadding = pageContentPadding(
            innerPadding,
            padding,
        )
        Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
            LazyColumn(
                state = lazyListState,
                cacheWindow = remember { LazyLayoutCacheWindow(aheadFraction = 0.5f, behindFraction = 0.25f) },
                modifier = Modifier.pageScrollModifiers(
                    appState.enableScrollEndHaptic,
                    appState.showTopAppBar,
                    topAppBarScrollBehavior,
                ),
                overscrollEffect = null,
                contentPadding = contentPadding,
            ) {
                libraries?.let { libs ->
                    items(libs, key = { it.uniqueId }, contentType = { "license_item" }) { library ->
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .padding(top = 12.dp),
                        ) {
                            ArrowPreference(
                                title = library.name,
                                summary = "${library.artifactVersion}, ${library.licenses.firstOrNull()}",
                                onClick = {
                                    library.website?.let {
                                        uriHandler.openUri(library.website)
                                    }
                                },
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            VerticalScrollBar(
                adapter = rememberScrollBarAdapter(lazyListState),
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                trackPadding = contentPadding,
                topAppBarScrollBehavior = topAppBarScrollBehavior.takeIf { appState.showTopAppBar },
            )
        }
    }
}
