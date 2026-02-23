// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import misc.VersionInfo
import navigation3.Route
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.ic_launcher
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun AboutPage(
    padding: PaddingValues,
    showTopAppBar: Boolean,
    isWideScreen: Boolean,
    enableScrollEndHaptic: Boolean,
    enableOverScroll: Boolean,
) {
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current
    Scaffold(
        topBar = {
            if (showTopAppBar) {
                if (isWideScreen) {
                    SmallTopAppBar(
                        title = "About",
                        scrollBehavior = topAppBarScrollBehavior,
                        navigationIcon = {
                            BackNavigationIcon(
                                modifier = Modifier.padding(start = 16.dp),
                                onClick = { navigator.pop() },
                            )
                        },
                    )
                } else {
                    TopAppBar(
                        title = "About",
                        scrollBehavior = topAppBarScrollBehavior,
                        navigationIcon = {
                            BackNavigationIcon(
                                modifier = Modifier.padding(start = 16.dp),
                                onClick = { navigator.pop() },
                            )
                        },
                    )
                }
            }
        },
        popupHost = {},
    ) { innerPadding ->
        AboutPage(
            padding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding(),
            ),
            topAppBarScrollBehavior = topAppBarScrollBehavior,
            showTopAppBar = showTopAppBar,
            enableScrollEndHaptic = enableScrollEndHaptic,
            enableOverScroll = enableOverScroll,
            isWideScreen = isWideScreen,
        )
    }
}

@Composable
fun AboutPage(
    padding: PaddingValues,
    topAppBarScrollBehavior: ScrollBehavior,
    showTopAppBar: Boolean,
    enableScrollEndHaptic: Boolean,
    enableOverScroll: Boolean,
    isWideScreen: Boolean,
) {
    val uriHandler = LocalUriHandler.current
    val navigator = LocalNavigator.current

    LazyColumn(
        modifier = Modifier
            .then(if (enableScrollEndHaptic) Modifier.scrollEndHaptic() else Modifier)
            .overScrollVertical(isEnabled = { enableOverScroll })
            .then(if (showTopAppBar) Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection) else Modifier)
            .fillMaxHeight(),
        contentPadding = PaddingValues(
            top = padding.calculateTopPadding(),
            start = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
            end = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
            bottom = if (isWideScreen) {
                WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + padding.calculateBottomPadding() + 12.dp
            } else {
                padding.calculateBottomPadding() + 12.dp
            },
        ),
        overscrollEffect = null,
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 72.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .background(Color.White),
                ) {
                    Image(
                        modifier = Modifier.size(80.dp),
                        painter = painterResource(Res.drawable.ic_launcher),
                        contentDescription = null,
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Miuix",
                    style = MiuixTheme.textStyles.title3,
                    color = colorScheme.onSurface,
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth().padding(bottom = 48.dp),
                text = "App Ver. " + VersionInfo.VERSION_NAME + " (" + VersionInfo.VERSION_CODE + ")" + "\nJDK Ver. " + VersionInfo.JDK_VERSION,
                textAlign = TextAlign.Center,
            )
            Card(
                modifier = Modifier.padding(horizontal = 12.dp),
            ) {
                SuperArrow(
                    title = "View Source",
                    endActions = {
                        Text(
                            text = "GitHub",
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = colorScheme.onSurfaceVariantActions,
                        )
                    },
                    onClick = { uriHandler.openUri("https://github.com/compose-miuix-ui/miuix") },
                )
                SuperArrow(
                    title = "Join Group",
                    endActions = {
                        Text(
                            text = "Telegram",
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = colorScheme.onSurfaceVariantActions,
                        )
                    },
                    onClick = { uriHandler.openUri("https://t.me/YuKongA13579") },
                )
            }
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp),
            ) {
                SuperArrow(
                    title = "License",
                    endActions = {
                        Text(
                            text = "Apache-2.0",
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = colorScheme.onSurfaceVariantActions,
                        )
                    },
                    onClick = {
                        uriHandler.openUri("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    },
                )
                SuperArrow(
                    title = "Third Party Licenses",
                    onClick = { navigator.push(Route.License) },
                )
            }
        }
    }
}
