// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import component.arrowSection
import component.basicSliderSection
import component.bottomSheetSection
import component.checkboxPreferenceSection
import component.colorPickerSection
import component.dialogSection
import component.dropdownPopupSection
import component.dropdownSection
import component.foregroundBlurSection
import component.menuSection
import component.progressiveBlurSection
import component.radioButtonSection
import component.rangeSliderSection
import component.sliderSection
import component.snackbarSection
import component.spinnerSection
import component.switchPreferenceSection
import component.textureBlurSection
import component.verticalSliderSection
import navigation.ComponentDemo
import top.yukonga.miuix.kmp.basic.ColorSpace
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberBlurBackdrop

@Composable
fun ComponentPage(
    demo: ComponentDemo,
    padding: PaddingValues,
) {
    val navigator = LocalNavigator.current
    if (demo == ComponentDemo.Snackbar) {
        val snackbarHostState = remember { SnackbarHostState() }
        ComponentPageContent(
            title = demo.title,
            padding = padding,
            onBack = { navigator.pop() },
            snackbarHost = { SnackbarHost(state = snackbarHostState) },
        ) {
            snackbarSection(snackbarHostState)
        }
    } else {
        ComponentPageContent(
            title = demo.title,
            padding = padding,
            onBack = { navigator.pop() },
            extraTop = when (demo) {
                ComponentDemo.Slider, ComponentDemo.ColorPicker, ComponentDemo.Preferences -> 0.dp
                else -> 12.dp
            },
        ) {
            when (demo) {
                ComponentDemo.Dialog -> dialogSection()

                ComponentDemo.BottomSheet -> bottomSheetSection()

                ComponentDemo.Slider -> {
                    basicSliderSection()
                    verticalSliderSection()
                }

                ComponentDemo.ColorPicker -> {
                    colorPickerSection(ColorSpace.HSV)
                    colorPickerSection(ColorSpace.OKHSV)
                    colorPickerSection(ColorSpace.OKLAB)
                    colorPickerSection(ColorSpace.OKLCH)
                }

                ComponentDemo.Preferences -> {
                    switchPreferenceSection()
                    checkboxPreferenceSection()
                    radioButtonSection()
                    sliderSection()
                    rangeSliderSection()
                    arrowSection()
                    dropdownSection()
                    spinnerSection()
                    menuSection()
                    dropdownPopupSection()
                }

                ComponentDemo.TextureBlur -> textureBlurSection()

                ComponentDemo.ForegroundBlur -> foregroundBlurSection()

                ComponentDemo.ProgressiveBlur -> progressiveBlurSection()

                ComponentDemo.Snackbar -> error("Snackbar requires its own host")
            }
        }
    }
}

@Composable
private fun ComponentPageContent(
    title: String,
    padding: PaddingValues,
    onBack: () -> Unit,
    extraTop: Dp = 12.dp,
    snackbarHost: @Composable () -> Unit = {},
    content: LazyListScope.() -> Unit,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val backdrop = rememberBlurBackdrop()
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            BlurredBar(backdrop, backdrop != null) {
                AdaptiveTopAppBar(
                    title = title,
                    showTopAppBar = appState.showTopAppBar,
                    isWideScreen = isWideScreen,
                    scrollBehavior = topAppBarScrollBehavior,
                    color = if (backdrop != null) Color.Transparent else MiuixTheme.colorScheme.surface,
                    navigationIcon = { BackNavigationIcon(onClick = onBack) },
                )
            }
        },
        snackbarHost = snackbarHost,
    ) { innerPadding ->
        val contentPadding = pageContentPadding(
            innerPadding,
            padding,
            isWideScreen,
            extraTop = extraTop,
            extraStart = if (isWideScreen) 0.dp else WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
            extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
        )
        Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.pageScrollModifiers(
                    appState.enableScrollEndHaptic,
                    appState.showTopAppBar,
                    topAppBarScrollBehavior,
                ),
                contentPadding = contentPadding,
            ) {
                content()
                item { Spacer(modifier = Modifier.height(12.dp)) }
            }
            VerticalScrollBar(
                adapter = rememberScrollBarAdapter(lazyListState),
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                trackPadding = contentPadding,
            )
        }
    }
}
