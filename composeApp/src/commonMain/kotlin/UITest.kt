import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.FloatingActionButton
import top.yukonga.miuix.kmp.basic.HorizontalPager
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.ListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationItem
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.extra.DropdownImpl
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.GitHub
import top.yukonga.miuix.kmp.icon.icons.ImmersionMore
import top.yukonga.miuix.kmp.icon.icons.Info
import top.yukonga.miuix.kmp.icon.icons.More
import top.yukonga.miuix.kmp.icon.icons.NavigatorSwitch
import top.yukonga.miuix.kmp.icon.icons.Settings
import top.yukonga.miuix.kmp.utils.MiuixPopupUtil.Companion.dismissPopup
import utils.FPSMonitor

@OptIn(FlowPreview::class)
@Composable
fun UITest(
    colorMode: MutableState<Int>,
) {
    val topAppBarScrollBehavior0 = MiuixScrollBehavior(rememberTopAppBarState())
    val topAppBarScrollBehavior1 = MiuixScrollBehavior(rememberTopAppBarState())
    val topAppBarScrollBehavior2 = MiuixScrollBehavior(rememberTopAppBarState())

    val topAppBarScrollBehaviorList = listOf(
        topAppBarScrollBehavior0, topAppBarScrollBehavior1, topAppBarScrollBehavior2
    )

    val pagerState = rememberPagerState(pageCount = { 3 })
    var targetPage by remember { mutableStateOf(pagerState.currentPage) }
    val coroutineScope = rememberCoroutineScope()

    val currentScrollBehavior = when (pagerState.currentPage) {
        0 -> topAppBarScrollBehaviorList[0]
        1 -> topAppBarScrollBehaviorList[1]
        else -> topAppBarScrollBehaviorList[2]
    }

    val items = listOf(
        NavigationItem("HomePage", MiuixIcons.NavigatorSwitch),
        NavigationItem("DropDown", MiuixIcons.Info),
        NavigationItem("Settings", MiuixIcons.Settings),
        NavigationItem("More", MiuixIcons.More)
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.debounce(150).collectLatest {
            targetPage = pagerState.currentPage
        }
    }

    val showFPSMonitor = remember { mutableStateOf(true) }
    val showTopAppBar = remember { mutableStateOf(true) }
    val showBottomBar = remember { mutableStateOf(true) }
    val showFloatingActionButton = remember { mutableStateOf(true) }
    val enablePageUserScroll = remember { mutableStateOf(false) }

    val isTopPopupExpanded = remember { mutableStateOf(false) }
    val showTopPopup = remember { mutableStateOf(false) }
    val isBottomPopupExpanded = remember { mutableStateOf(false) }
    val showBottomPopup = remember { mutableStateOf(false) }

    val uriHandler = LocalUriHandler.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AnimatedVisibility(
                visible = showTopAppBar.value,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                BoxWithConstraints {
                    if (maxWidth > 840.dp) {
                        SmallTopAppBar(
                            title = "Miuix",
                            scrollBehavior = currentScrollBehavior,
                            actions = {
                                if (isTopPopupExpanded.value) {
                                    ListPopup(
                                        show = showTopPopup,
                                        popupPositionProvider = ListPopupDefaults.ContextMenuPositionProvider,
                                        alignment = PopupPositionProvider.Align.TopRight,
                                        onDismissRequest = {
                                            isTopPopupExpanded.value = false
                                        }
                                    ) {
                                        ListPopupColumn {
                                            items.take(3).forEachIndexed { index, navigationItem ->
                                                DropdownImpl(
                                                    text = navigationItem.label,
                                                    optionSize = items.take(3).size,
                                                    isSelected = items[index] == items[targetPage],
                                                    onSelectedIndexChange = {
                                                        targetPage = index
                                                        coroutineScope.launch {
                                                            pagerState.animateScrollToPage(index)
                                                        }
                                                        dismissPopup(showTopPopup)
                                                        isTopPopupExpanded.value = false
                                                    },
                                                    index = index
                                                )
                                            }
                                        }
                                    }
                                    showTopPopup.value = true
                                }
                                IconButton(
                                    modifier = Modifier.padding(end = 21.dp).size(40.dp),
                                    onClick = {
                                        isTopPopupExpanded.value = true
                                    }
                                ) {
                                    Icon(
                                        imageVector = MiuixIcons.ImmersionMore,
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        )
                    } else {
                        TopAppBar(
                            title = "Miuix",
                            scrollBehavior = currentScrollBehavior,
                            actions = {
                                if (isTopPopupExpanded.value) {
                                    ListPopup(
                                        show = showTopPopup,
                                        popupPositionProvider = ListPopupDefaults.ContextMenuPositionProvider,
                                        alignment = PopupPositionProvider.Align.TopRight,
                                        onDismissRequest = {
                                            isTopPopupExpanded.value = false
                                        }
                                    ) {
                                        ListPopupColumn {
                                            items.take(3).forEachIndexed { index, navigationItem ->
                                                DropdownImpl(
                                                    text = navigationItem.label,
                                                    optionSize = items.take(3).size,
                                                    isSelected = items[index] == items[targetPage],
                                                    onSelectedIndexChange = {
                                                        targetPage = index
                                                        coroutineScope.launch {
                                                            pagerState.animateScrollToPage(index)
                                                        }
                                                        dismissPopup(showTopPopup)
                                                        isTopPopupExpanded.value = false
                                                    },
                                                    index = index
                                                )
                                            }
                                        }
                                    }
                                    showTopPopup.value = true
                                }
                                IconButton(
                                    modifier = Modifier.padding(end = 21.dp).size(40.dp),
                                    onClick = {
                                        isTopPopupExpanded.value = true
                                    }
                                ) {
                                    Icon(
                                        imageVector = MiuixIcons.ImmersionMore,
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        )
                    }
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar.value,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                if (isBottomPopupExpanded.value) {
                    ListPopup(
                        show = showBottomPopup,
                        popupPositionProvider = ListPopupDefaults.ContextMenuPositionProvider,
                        alignment = PopupPositionProvider.Align.BottomRight,
                        onDismissRequest = {
                            isBottomPopupExpanded.value = false
                        }
                    ) {
                        ListPopupColumn {
                            items.take(3).forEachIndexed { index, navigationItem ->
                                DropdownImpl(
                                    text = navigationItem.label,
                                    optionSize = items.take(3).size,
                                    isSelected = items[index] == items[targetPage],
                                    onSelectedIndexChange = {
                                        targetPage = index
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                        dismissPopup(showBottomPopup)
                                        isBottomPopupExpanded.value = false
                                    },
                                    index = index
                                )
                            }
                        }
                    }
                    showBottomPopup.value = true
                }
                NavigationBar(
                    items = items,
                    selected = targetPage,
                    onClick = { index ->
                        if (index in 0..2) {
                            targetPage = index
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        } else {
                            isBottomPopupExpanded.value = true
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            if (showFloatingActionButton.value) {
                FloatingActionButton(
                    onClick = {
                        uriHandler.openUri("https://github.com/miuix-kotlin-multiplatform/miuix")
                    }
                ) {
                    Icon(
                        imageVector = MiuixIcons.GitHub,
                        tint = Color.White,
                        contentDescription = "GitHub"
                    )
                }
            }
        }
    ) { padding ->
        AppHorizontalPager(
            modifier = Modifier.imePadding(),
            pagerState = pagerState,
            topAppBarScrollBehaviorList = topAppBarScrollBehaviorList,
            padding = padding,
            showFPSMonitor = showFPSMonitor.value,
            onShowFPSMonitorChange = { showFPSMonitor.value = it },
            showTopAppBar = showTopAppBar.value,
            onShowTopAppBarChange = { showTopAppBar.value = it },
            showBottomBar = showBottomBar.value,
            onShowBottomBarChange = { showBottomBar.value = it },
            showFloatingActionButton = showFloatingActionButton.value,
            onShowFloatingActionButtonChange = { showFloatingActionButton.value = it },
            enablePageUserScroll = enablePageUserScroll.value,
            onEnablePageUserScrollChange = { enablePageUserScroll.value = it },
            colorMode = colorMode,
        )
    }

    AnimatedVisibility(
        visible = showFPSMonitor.value,
        enter = fadeIn() + expandHorizontally(),
        exit = fadeOut() + shrinkHorizontally()
    ) {
        FPSMonitor(
            modifier = Modifier
                .statusBarsPadding()
                .captionBarPadding()
                .padding(horizontal = 4.dp)
        )
    }
}

@Composable
fun AppHorizontalPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    topAppBarScrollBehaviorList: List<ScrollBehavior>,
    padding: PaddingValues,
    showFPSMonitor: Boolean,
    onShowFPSMonitorChange: (Boolean) -> Unit,
    showTopAppBar: Boolean,
    onShowTopAppBarChange: (Boolean) -> Unit,
    showBottomBar: Boolean,
    onShowBottomBarChange: (Boolean) -> Unit,
    showFloatingActionButton: Boolean,
    onShowFloatingActionButtonChange: (Boolean) -> Unit,
    enablePageUserScroll: Boolean,
    onEnablePageUserScrollChange: (Boolean) -> Unit,
    colorMode: MutableState<Int>
) {
    HorizontalPager(
        modifier = modifier,
        pagerState = pagerState,
        userScrollEnabled = enablePageUserScroll,
        pageContent = { page ->
            when (page) {
                0 -> MainPage(
                    topAppBarScrollBehavior = topAppBarScrollBehaviorList[0],
                    padding = padding
                )

                1 -> SecondPage(
                    topAppBarScrollBehavior = topAppBarScrollBehaviorList[1],
                    padding = padding
                )

                else -> ThirdPage(
                    topAppBarScrollBehavior = topAppBarScrollBehaviorList[2],
                    padding = padding,
                    showFPSMonitor = showFPSMonitor,
                    onShowFPSMonitorChange = onShowFPSMonitorChange,
                    showTopAppBar = showTopAppBar,
                    onShowTopAppBarChange = onShowTopAppBarChange,
                    showBottomBar = showBottomBar,
                    onShowBottomBarChange = onShowBottomBarChange,
                    showFloatingActionButton = showFloatingActionButton,
                    onShowFloatingActionButtonChange = onShowFloatingActionButtonChange,
                    enablePageUserScroll = enablePageUserScroll,
                    onEnablePageUserScrollChange = onEnablePageUserScrollChange,
                    colorMode = colorMode
                )
            }
        }
    )
}