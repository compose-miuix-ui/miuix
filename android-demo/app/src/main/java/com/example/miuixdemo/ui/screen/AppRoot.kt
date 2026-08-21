package com.example.miuixdemo.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.miuixdemo.ui.theme.LocalOnThemeModeChange
import com.example.miuixdemo.ui.theme.LocalThemeMode
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Layers
import top.yukonga.miuix.kmp.icon.extended.ListView
import top.yukonga.miuix.kmp.icon.extended.Messages
import top.yukonga.miuix.kmp.icon.extended.Settings

internal val LocalSnackbarHostState = compositionLocalOf { SnackbarHostState() }

private data class NavItem(
    val label: String,
    val icon: ImageVector,
)

/**
 * 应用根布局：Scaffold + TopAppBar + NavigationBar + 四个页面切换。
 */
@Composable
fun AppRoot(
    themeMode: Int,
    onThemeModeChange: (Int) -> Unit,
) {
    val navItems = remember {
        listOf(
            NavItem("Basic", MiuixIcons.Layers),
            NavItem("List", MiuixIcons.ListView),
            NavItem("Settings", MiuixIcons.Settings),
            NavItem("Dialog", MiuixIcons.Messages),
        )
    }
    var page by rememberSaveable { mutableIntStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(
        LocalThemeMode provides themeMode,
        LocalOnThemeModeChange provides onThemeModeChange,
        LocalSnackbarHostState provides snackbarHostState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = navItems[page].label,
                    subtitle = "Miuix ${getMiuixVersion()}",
                )
            },
            bottomBar = {
                NavigationBar {
                    navItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = page == index,
                            onClick = { page = index },
                            icon = item.icon,
                            label = item.label,
                        )
                    }
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                when (page) {
                    0 -> BasicScreen()
                    1 -> ListScreen()
                    2 -> SettingsScreen()
                    3 -> DialogScreen()
                }
            }
        }
    }
}

private fun getMiuixVersion(): String = "0.9.3"
