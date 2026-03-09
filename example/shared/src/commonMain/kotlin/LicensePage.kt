// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import utils.AdaptiveTopAppBar
import utils.Library
import utils.SimpleJsonParser
import utils.pageContentPadding
import utils.pageScrollModifiers

@Composable
fun LicensePage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current

    val libraries by produceState<List<Library>?>(initialValue = null) {
        try {
            val jsonString = Res.readBytes("files/aboutlibraries.json").decodeToString()
            val libs = SimpleJsonParser(jsonString).parseLibs()
            value = libs.libraries
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Scaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = "Third Party Licenses",
                showTopAppBar = appState.showTopAppBar,
                isWideScreen = isWideScreen,
                scrollBehavior = topAppBarScrollBehavior,
                navigationIcon = {
                    BackNavigationIcon(
                        modifier = Modifier.padding(start = 16.dp),
                        onClick = { navigator.pop() },
                    )
                },
            )
        },
    ) { innerPadding ->
        val uriHandler = LocalUriHandler.current
        LazyColumn(
            modifier = Modifier.pageScrollModifiers(appState.enableScrollEndHaptic, appState.enableOverScroll, appState.showTopAppBar, topAppBarScrollBehavior),
            contentPadding = pageContentPadding(
                innerPadding,
                padding,
                isWideScreen,
                extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
                extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
            ),
        ) {
            libraries?.let { libs ->
                items(libs) { library ->
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(top = 12.dp),
                    ) {
                        SuperArrow(
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
    }
}
