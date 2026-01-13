// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.adaptive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.adaptive.utils.shouldShowSplitPane
import androidx.navigation3.adaptive.utils.shouldShowThreePanes
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.scene.Scene
import top.yukonga.miuix.kmp.basic.VerticalDivider
import top.yukonga.miuix.kmp.theme.MiuixTheme

internal sealed interface ThreePaneScaffoldType {
    data object ListDetail : ThreePaneScaffoldType
    data object SupportingPane : ThreePaneScaffoldType
}

internal sealed interface BackNavigationBehavior {
    data object PopLast : BackNavigationBehavior
}

internal data class ThreePaneScaffoldScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    override val entries: List<NavEntry<T>>,
    private val type: ThreePaneScaffoldType,
    private val listPaneContent: (@Composable () -> Unit)? = null,
    private val detailPaneContent: (@Composable () -> Unit)? = null,
    private val mainPaneContent: (@Composable () -> Unit)? = null,
    private val supportingPaneContent: (@Composable () -> Unit)? = null,
    private val extraPaneContent: (@Composable () -> Unit)? = null,
    private val detailPlaceholder: (@Composable () -> Unit)? = null,
    private val backBehavior: BackNavigationBehavior = BackNavigationBehavior.PopLast,
) : Scene<T> {

    override val content: @Composable () -> Unit = {
        when (type) {
            ThreePaneScaffoldType.ListDetail -> {
                ListDetailContent(
                    listContent = listPaneContent,
                    detailContent = detailPaneContent,
                    extraContent = extraPaneContent,
                    detailPlaceholder = detailPlaceholder,
                )
            }

            ThreePaneScaffoldType.SupportingPane -> {
                SupportingPaneContent(
                    mainContent = mainPaneContent,
                    supportingContent = supportingPaneContent,
                    extraContent = extraPaneContent,
                )
            }
        }
    }
}

@Composable
fun SupportingPaneScaffold(
    main: @Composable () -> Unit,
    supporting: @Composable () -> Unit,
    extra: (@Composable () -> Unit)? = null,
) {
    SupportingPaneContent(
        mainContent = main,
        supportingContent = supporting,
        extraContent = extra,
    )
}

@Composable
fun ListDetailPaneScaffold(
    list: @Composable () -> Unit,
    detail: (@Composable () -> Unit)?,
    extra: (@Composable () -> Unit)? = null,
    detailPlaceholder: (@Composable () -> Unit)? = null,
) {
    ListDetailContent(
        listContent = list.let { { it() } },
        detailContent = detail,
        extraContent = extra,
        detailPlaceholder = detailPlaceholder,
    )
}

@Composable
private fun ListDetailContent(
    listContent: (@Composable () -> Unit)?,
    detailContent: (@Composable () -> Unit)?,
    extraContent: (@Composable () -> Unit)?,
    detailPlaceholder: (@Composable () -> Unit)?,
) {
    val showThreePanes = shouldShowThreePanes()
    val showTwoPanes = shouldShowSplitPane()
    val enableThreePanes = showThreePanes && extraContent != null

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MiuixTheme.colorScheme.surface),
    ) {
        if (showTwoPanes || showThreePanes) {
            Box(modifier = Modifier.weight(0.4f)) {
                listContent?.invoke()
            }
            NavVerticalDivider()
            Box(modifier = Modifier.weight(if (enableThreePanes) 0.4f else 0.6f)) {
                (detailContent ?: detailPlaceholder)?.invoke()
            }
            if (enableThreePanes) {
                NavVerticalDivider()
                Box(modifier = Modifier.weight(0.2f)) {
                    extraContent.invoke()
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                (extraContent ?: detailContent ?: listContent ?: detailPlaceholder)?.invoke()
            }
        }
    }
}

@Composable
private fun SupportingPaneContent(
    mainContent: (@Composable () -> Unit)?,
    supportingContent: (@Composable () -> Unit)?,
    extraContent: (@Composable () -> Unit)?,
) {
    val showThreePanes = shouldShowThreePanes()
    val showTwoPanes = shouldShowSplitPane()
    val enableThreePanes = showThreePanes && extraContent != null

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MiuixTheme.colorScheme.surface),
    ) {
        if (showTwoPanes || showThreePanes) {
            Box(modifier = Modifier.weight(if (enableThreePanes) 0.25f else 0.3f)) {
                supportingContent?.invoke()
            }
            NavVerticalDivider()
            Box(modifier = Modifier.weight(if (enableThreePanes) 0.5f else 0.7f)) {
                mainContent?.invoke()
            }
            if (enableThreePanes) {
                NavVerticalDivider()
                Box(modifier = Modifier.weight(0.25f)) {
                    extraContent.invoke()
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                mainContent?.invoke()
            }
        }
    }
}

@Composable
private fun NavVerticalDivider() {
    val statusBarsPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val captionBarPadding = WindowInsets.captionBar.asPaddingValues().calculateTopPadding()
    val displayCutoutPadding = WindowInsets.displayCutout.asPaddingValues().calculateTopPadding()
    val safeTopInset =
        remember(statusBarsPadding, captionBarPadding, displayCutoutPadding) {
            maxOf(statusBarsPadding, captionBarPadding, displayCutoutPadding)
        }

    VerticalDivider(
        Modifier
            .padding(top = safeTopInset)
            .width(1.dp)
            .fillMaxHeight(),
    )
}
