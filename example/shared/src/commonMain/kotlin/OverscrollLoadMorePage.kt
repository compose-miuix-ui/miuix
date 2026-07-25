// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.InfiniteProgressIndicator
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.squircle.squircleClip
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.rememberBlurBackdrop
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun OverscrollLoadMorePage(
    padding: PaddingValues,
) {
    val navigator = LocalNavigator.current
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    val backdrop = rememberBlurBackdrop()
    val blurActive = backdrop != null
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface

    var itemCount by rememberSaveable { mutableIntStateOf(30) }
    var loadedPages by rememberSaveable { mutableIntStateOf(0) }
    var loadingMore by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState) {
        snapshotFlow {
            val layoutInfo = lazyListState.layoutInfo
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
            lastVisible >= 0 && lastVisible >= layoutInfo.totalItemsCount - 3
        }
            .distinctUntilChanged()
            .collect { nearEnd ->
                if (nearEnd && !loadingMore) {
                    loadingMore = true
                    // Long enough for the bounce-back to still be in flight when items arrive.
                    delay(800.milliseconds)
                    itemCount += 20
                    loadedPages++
                    loadingMore = false
                }
            }
    }

    Scaffold(
        topBar = {
            BlurredBar(backdrop, blurActive, topAppBarScrollBehavior) {
                AdaptiveTopAppBar(
                    title = "Overscroll + Load More",
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
        val contentPadding = pageContentPadding(
            innerPadding,
            padding,
            true,
            extraTop = 12.dp,
            extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
            extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
            extraBottom = 12.dp,
        )
        Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
            // No overScrollVertical: overscroll comes from LocalOverscrollFactory (MiuixOverscrollEffect).
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxHeight()
                    .then(
                        if (appState.showTopAppBar) {
                            Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
                        } else {
                            Modifier
                        },
                    ),
                contentPadding = contentPadding,
            ) {
                item(key = "hint") {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        cornerRadius = 16.dp,
                        insideMargin = PaddingValues(16.dp),
                        colors = CardDefaults.defaultColors(
                            color = MiuixTheme.colorScheme.surfaceContainer,
                        ),
                    ) {
                        Text(
                            text = "Fling to the bottom fast: new items load while the bounce-back plays. " +
                                "Fling again — inertial scrolling must keep working.",
                            style = MiuixTheme.textStyles.body2,
                            color = MiuixTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = "Items: $itemCount · Pages loaded: $loadedPages",
                            modifier = Modifier.padding(top = 8.dp),
                            style = MiuixTheme.textStyles.body2,
                            color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                        )
                    }
                }
                items(
                    count = itemCount,
                    key = { "row_$it" },
                ) { i ->
                    val isFirst = i == 0
                    val isLast = i == itemCount - 1 && !loadingMore
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .squircleClip(
                                topStart = if (isFirst) 16.dp else 0.dp,
                                topEnd = if (isFirst) 16.dp else 0.dp,
                                bottomEnd = if (isLast) 16.dp else 0.dp,
                                bottomStart = if (isLast) 16.dp else 0.dp,
                            )
                            .background(MiuixTheme.colorScheme.surfaceContainer),
                    ) {
                        Text(
                            text = "Item ${i + 1}",
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                            style = MiuixTheme.textStyles.body1,
                            color = MiuixTheme.colorScheme.onSurface,
                        )
                    }
                }
                if (loadingMore) {
                    item(key = "footer") {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp)
                                .squircleClip(
                                    topStart = 0.dp,
                                    topEnd = 0.dp,
                                    bottomEnd = 16.dp,
                                    bottomStart = 16.dp,
                                )
                                .background(MiuixTheme.colorScheme.surfaceContainer)
                                .padding(vertical = 14.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            InfiniteProgressIndicator(size = 20.dp)
                            Text(
                                text = "Loading more…",
                                modifier = Modifier.padding(start = 8.dp),
                                style = MiuixTheme.textStyles.body2,
                                color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                            )
                        }
                    }
                }
            }
        }
    }
}
