// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.glass.GlassBottomSheet
import top.yukonga.miuix.kmp.glass.GlassBottomSheetDefaults
import top.yukonga.miuix.kmp.glass.GlassCard
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassDialogButtonRole
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassTextField
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.Close1
import top.yukonga.miuix.kmp.icon.symbol.Ok
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.symbol.toImageVector
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** A sheet with transparent content, so the shared material remains visible while scrolling. */
@Composable
internal fun GlassBottomSheetDemo(
    onDismissRequest: () -> Unit,
    show: Boolean,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    material: GlassMaterial = GlassBottomSheetDefaults.material(),
    stroke: EdgeStroke? = GlassBottomSheetDefaults.stroke(),
    onDismissFinished: (() -> Unit)? = null,
) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val coroutineScope = rememberCoroutineScope()
    var selectedTab by remember { mutableIntStateOf(0) }
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.targetPage }.collect { selectedTab = it }
    }
    val featuredScrollState = rememberScrollState()
    val shortcutsScrollState = rememberScrollState()
    val scrollStates = remember(featuredScrollState, shortcutsScrollState) {
        listOf(featuredScrollState, shortcutsScrollState)
    }
    val isScrolled by remember(pagerState, scrollStates) {
        derivedStateOf { scrollStates[pagerState.currentPage].value > 0 }
    }
    val tabTitles = remember { listOf("Featured", "Shortcuts") }
    GlassBottomSheet(
        show = show,
        insideMargin = DpSize(0.dp, 0.dp),
        modifier = modifier,
        progressiveBlur = true,
        scrollOffset = { scrollStates[pagerState.currentPage].value.toFloat() },
        title = "Glass BottomSheet",
        backdrop = backdrop,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        material = material,
        stroke = stroke,
        startAction = {
            GlassIconButton(
                onClick = onDismissRequest,
                modifier = Modifier.padding(start = 12.dp),
            ) {
                Icon(
                    imageVector = MiuixIcons.Symbol.Close1.toImageVector(),
                    contentDescription = "Close",
                    tint = MiuixTheme.colorScheme.onSurface,
                )
            }
        },
        endAction = {
            GlassIconButton(
                onClick = onDismissRequest,
                modifier = Modifier.padding(end = 12.dp),
            ) {
                Icon(
                    imageVector = MiuixIcons.Symbol.Ok.toImageVector(),
                    contentDescription = "Confirm",
                    tint = MiuixTheme.colorScheme.onSurface,
                )
            }
        },
        headerBottomContent = {
            if (backdrop != null) {
                val tabSurfaceAlpha = if (isScrolled) 1f else 0f
                GlassSegmentedTabRow(
                    tabs = tabTitles,
                    selectedIndex = selectedTab,
                    onSelect = { index ->
                        selectedTab = index
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    },
                    backdrop = backdrop,
                    surfaceAlpha = tabSurfaceAlpha,
                    trackColor = MiuixTheme.colorScheme.onSurface.copy(alpha = 0.06f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .padding(bottom = 12.dp),
                )
            }
        },
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollStates[page])
                    .padding(innerPadding)
                    .padding(horizontal = 12.dp)
                    .navigationBarsPadding()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                if (page == 0) {
                    GlassCard(modifier = Modifier.fillMaxWidth()) {
                        BasicComponent(
                            title = "Drag or scroll",
                            summary = "Drag the handle or scroll the content to try the sheet material.",
                            onClick = {},
                        )
                    }
                    repeat(4) { group ->
                        GlassCard(modifier = Modifier.fillMaxWidth()) {
                            repeat(4) { index ->
                                BasicComponent(
                                    title = "Sheet item ${group * 4 + index + 1}",
                                    summary = "Content stays sharp above the blurred page.",
                                    onClick = {},
                                )
                            }
                        }
                    }
                    GlassDialogButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.fillMaxWidth(),
                        role = GlassDialogButtonRole.Primary,
                    ) {
                        Text("Done", style = MiuixTheme.textStyles.button)
                    }
                } else {
                    repeat(4) { group ->
                        GlassCard(modifier = Modifier.fillMaxWidth()) {
                            repeat(4) { index ->
                                BasicComponent(
                                    title = "Shortcut ${group * 4 + index + 1}",
                                    summary = "Swipe horizontally or tap tabs to switch pages.",
                                    onClick = {},
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/** A half-height sheet, sized like the launcher's settings sheet, to compare the opening motion. */
@Composable
internal fun GlassHalfBottomSheetDemo(
    onDismissRequest: () -> Unit,
    show: Boolean,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    material: GlassMaterial = GlassBottomSheetDefaults.material(),
    stroke: EdgeStroke? = GlassBottomSheetDefaults.stroke(),
    onDismissFinished: (() -> Unit)? = null,
) {
    val textFieldState = rememberTextFieldState()
    GlassBottomSheet(
        show = show,
        modifier = modifier,
        title = "Half BottomSheet",
        backdrop = backdrop,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        material = material,
        stroke = stroke,
        startAction = {
            GlassIconButton(onClick = onDismissRequest) {
                Icon(
                    imageVector = MiuixIcons.Symbol.Close1.toImageVector(),
                    contentDescription = "Close",
                    tint = MiuixTheme.colorScheme.onSurface,
                )
            }
        },
        endAction = {
            GlassIconButton(onClick = onDismissRequest) {
                Icon(
                    imageVector = MiuixIcons.Symbol.Ok.toImageVector(),
                    contentDescription = "Confirm",
                    tint = MiuixTheme.colorScheme.onSurface,
                )
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            GlassTextField(
                state = textFieldState,
                modifier = Modifier.fillMaxWidth(),
                label = "Text field",
                useLabelAsPlaceholder = true,
            )
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                repeat(5) { index ->
                    BasicComponent(
                        title = "Setting ${index + 1}",
                        onClick = {},
                    )
                }
            }
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                BasicComponent(
                    title = "More settings",
                    onClick = onDismissRequest,
                )
            }
        }
    }
}
