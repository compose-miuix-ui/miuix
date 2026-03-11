// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kyant.shapes.UnevenRoundedRectangle
import component.SearchBox
import component.SearchPager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import utils.AdaptiveTopAppBar
import utils.All
import utils.SearchStatus
import utils.pageContentPadding
import utils.pageScrollModifiers

private val IconListTopShape = UnevenRoundedRectangle(topStart = 16.dp, topEnd = 16.dp)
private val IconListBottomShape = UnevenRoundedRectangle(bottomStart = 16.dp, bottomEnd = 16.dp)

@Composable
fun IconsPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val dynamicTopPadding by remember(isWideScreen) {
        derivedStateOf { if (isWideScreen) 0.dp else 12.dp * (1f - topAppBarScrollBehavior.state.collapsedFraction) }
    }

    // Search state
    var searchStatus by remember { mutableStateOf(SearchStatus(label = "Search icons")) }
    val updateSearchStatus: (SearchStatus) -> Unit = { searchStatus = it }
    var searchOffsetY by remember { mutableStateOf(0.dp) }

    // Icon data
    val allIcons = remember { MiuixIcons.All }
    val lightIcons = remember(allIcons) { allIcons["Light"] ?: emptyList() }
    val regularIcons = remember(allIcons) { allIcons["Regular"] ?: emptyList() }
    val heavyIcons = remember(allIcons) { allIcons["Heavy"] ?: emptyList() }
    val iconNames = remember(lightIcons) { lightIcons.map { it.name.substringBefore(".") } }

    // Search filtering
    val filteredIndices = remember(searchStatus.searchText, iconNames) {
        if (searchStatus.searchText.isBlank()) {
            emptyList()
        } else {
            iconNames.indices.filter {
                iconNames[it].contains(searchStatus.searchText, ignoreCase = true)
            }
        }
    }
    val searchResultStatus = remember(searchStatus.searchText, filteredIndices) {
        when {
            searchStatus.searchText.isBlank() -> SearchStatus.ResultStatus.DEFAULT
            filteredIndices.isEmpty() -> SearchStatus.ResultStatus.EMPTY
            else -> SearchStatus.ResultStatus.SHOW
        }
    }
    LaunchedEffect(searchResultStatus) {
        if (searchStatus.resultStatus != searchResultStatus) {
            searchStatus = searchStatus.copy(resultStatus = searchResultStatus)
        }
    }

    // Scroll state
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            searchStatus.TopAppBarAnim {
                AdaptiveTopAppBar(
                    title = "Icon",
                    showTopAppBar = appState.showTopAppBar,
                    isWideScreen = isWideScreen,
                    scrollBehavior = topAppBarScrollBehavior,
                )
            }
        },
        popupHost = {
            searchStatus.SearchPager(
                onSearchStatusChange = updateSearchStatus,
                offsetY = searchOffsetY,
                defaultResult = {},
                searchBarTopPadding = dynamicTopPadding,
            ) {
                items(
                    count = filteredIndices.size,
                    key = { filteredIndices[it] },
                ) { i ->
                    val index = filteredIndices[i]
                    Card(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    ) {
                        BasicComponent(
                            title = iconNames[index],
                            startAction = {
                                Icon(
                                    imageVector = regularIcons[index],
                                    contentDescription = iconNames[index],
                                    tint = colorScheme.onBackground,
                                    modifier = Modifier.size(24.dp),
                                )
                            },
                            onClick = {
                                searchStatus = searchStatus.copy(
                                    searchText = "",
                                    current = SearchStatus.Status.COLLAPSING,
                                )
                                coroutineScope.launch {
                                    delay(350L)
                                    // item 0 = header, icon rows start at item 1
                                    lazyListState.animateScrollToItem(index + 1)
                                }
                            },
                        )
                    }
                }
                item {
                    Spacer(Modifier.height(padding.calculateBottomPadding()))
                }
            }
        },
    ) { innerPadding ->
        searchStatus.SearchBox(
            onSearchStatusChange = updateSearchStatus,
            onOffsetYChange = { searchOffsetY = it },
            searchBarTopPadding = dynamicTopPadding,
            contentPadding = PaddingValues(top = innerPadding.calculateTopPadding()),
        ) { boxHeight ->
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.pageScrollModifiers(
                    appState.enableScrollEndHaptic,
                    appState.showTopAppBar,
                    topAppBarScrollBehavior,
                ),
                contentPadding = pageContentPadding(
                    innerPadding,
                    padding,
                    isWideScreen,
                    extraTop = boxHeight.value,
                ),
            ) {
                item(key = "iconsHeader") {
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .clip(IconListTopShape)
                            .background(colorScheme.surfaceContainer)
                            .padding(horizontal = 16.dp)
                            .padding(top = 12.dp, bottom = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Name",
                            modifier = Modifier.weight(2f),
                            style = MiuixTheme.textStyles.footnote1,
                            color = colorScheme.onSurfaceVariantActions,
                        )
                        Text(
                            text = "Light",
                            modifier = Modifier.weight(1f),
                            style = MiuixTheme.textStyles.footnote1,
                            color = colorScheme.onSurfaceVariantActions,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Regular",
                            modifier = Modifier.weight(1f),
                            style = MiuixTheme.textStyles.footnote1,
                            color = colorScheme.onSurfaceVariantActions,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Heavy",
                            modifier = Modifier.weight(1f),
                            style = MiuixTheme.textStyles.footnote1,
                            color = colorScheme.onSurfaceVariantActions,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                items(
                    count = lightIcons.size,
                    key = { "icon_$it" },
                ) { index ->
                    val isLast = index == lightIcons.lastIndex
                    val shape = if (isLast) IconListBottomShape else RectangleShape
                    val bottomPadding = if (isLast) 6.dp else 0.dp
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .clip(shape)
                            .background(colorScheme.surfaceContainer)
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 6.dp)
                            .padding(bottom = bottomPadding),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = iconNames[index],
                            modifier = Modifier.weight(2f),
                            style = MiuixTheme.textStyles.body2,
                            color = colorScheme.onSurface,
                        )
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = lightIcons[index],
                                contentDescription = lightIcons[index].name,
                                tint = colorScheme.onBackground,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = regularIcons[index],
                                contentDescription = regularIcons[index].name,
                                tint = colorScheme.onBackground,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = heavyIcons[index],
                                contentDescription = heavyIcons[index].name,
                                tint = colorScheme.onBackground,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
