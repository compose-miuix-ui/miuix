// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import LocalAppState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.LayerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.glass.GlassTopAppBar
import top.yukonga.miuix.kmp.glass.GlassTopAppBarDefaults
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

val LocalTopAppBarBackdrop = staticCompositionLocalOf<LayerBackdrop?> { null }
private val LocalTopAppBarBackgroundBlur = staticCompositionLocalOf { false }

@Composable
fun Modifier.pageScrollModifiers(
    enableScrollEndHaptic: Boolean,
    showTopAppBar: Boolean,
    topAppBarScrollBehavior: ScrollBehavior,
): Modifier {
    val state = topAppBarScrollBehavior.state
    val onOffsetChanged: (Float) -> Unit = remember(state) {
        { state.overscrollOffset = it }
    }
    return this
        .then(if (enableScrollEndHaptic) Modifier.scrollEndHaptic() else Modifier)
        .overScrollVertical(onOffsetChanged = if (showTopAppBar) onOffsetChanged else null)
        .then(if (showTopAppBar) Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection) else Modifier)
        .fillMaxHeight()
}

@Composable
fun pageContentPadding(
    innerPadding: PaddingValues,
    outerPadding: PaddingValues,
    isWideScreen: Boolean,
    extraTop: Dp = 0.dp,
    extraStart: Dp = 0.dp,
    extraEnd: Dp = 0.dp,
    extraBottom: Dp = 0.dp,
): PaddingValues {
    val topPadding = innerPadding.calculateTopPadding() + extraTop
    val bottomPadding = outerPadding.calculateBottomPadding() + extraBottom + if (isWideScreen) {
        WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + WindowInsets.captionBar.asPaddingValues()
            .calculateBottomPadding()
    } else {
        0.dp
    }

    return remember(topPadding, bottomPadding, extraStart, extraEnd, extraBottom) {
        PaddingValues(
            top = topPadding,
            start = extraStart,
            end = extraEnd,
            bottom = bottomPadding,
        )
    }
}

@Composable
fun AdaptiveTopAppBar(
    title: String,
    showTopAppBar: Boolean,
    isWideScreen: Boolean,
    scrollBehavior: ScrollBehavior,
    contentModifier: Modifier = Modifier,
    subtitle: String = "",
    color: Color = MiuixTheme.colorScheme.surface,
    titleColor: Color = MiuixTheme.colorScheme.onSurface,
    defaultWindowInsetsPadding: Boolean = !isWideScreen,
    isContentScrolled: Boolean? = null,
    visualContentOffset: (() -> Float)? = null,
    titleAlpha: () -> Float = { titleColor.alpha },
    backgroundAlpha: () -> Float = { 1f },
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
) {
    if (!showTopAppBar) return
    val appState = LocalAppState.current
    if (appState.topAppBarStyle == 1) {
        val backdrop = LocalTopAppBarBackdrop.current
        val backgroundBlur = LocalTopAppBarBackgroundBlur.current
        val shaderSupported = isRuntimeShaderSupported()
        val useBandBrush = !shaderSupported || (backdrop != null && backgroundBlur)
        val surfaceColor = MiuixTheme.colorScheme.surface
        val bandBrush = remember(useBandBrush, surfaceColor, color) {
            if (useBandBrush) GlassTopAppBarDefaults.bandBrush(surfaceColor) else SolidColor(color)
        }
        val contentScrolled by remember(scrollBehavior, isContentScrolled, visualContentOffset) {
            derivedStateOf { isContentScrolled ?: ((visualContentOffset?.invoke() ?: scrollBehavior.state.visualContentOffset) < 0f) }
        }
        GlassTopAppBar(
            title = title,
            isContentScrolled = contentScrolled,
            contentModifier = contentModifier,
            subtitle = subtitle,
            backdrop = backdrop,
            progressiveBlur = appState.glassTopBarProgressiveBlur && appState.enableBlur,
            scrollBehavior = scrollBehavior,
            visualContentOffset = visualContentOffset,
            titleAlpha = titleAlpha,
            backgroundAlpha = { if (backgroundBlur || !shaderSupported) backgroundAlpha() else 0f },
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            bandBrush = bandBrush,
            bandOverhang = if (appState.enableBlur || !shaderSupported) GlassTopAppBarDefaults.BandOverhang else 0.dp,
            navigationIcon = navigationIcon,
            actions = actions,
            bottomContent = bottomContent,
        )
    } else if (isWideScreen) {
        SmallTopAppBar(
            title = title,
            modifier = contentModifier,
            subtitle = subtitle,
            color = color,
            titleColor = titleColor,
            scrollBehavior = scrollBehavior,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            navigationIcon = navigationIcon,
            actions = actions,
            bottomContent = bottomContent,
        )
    } else {
        TopAppBar(
            title = title,
            modifier = contentModifier,
            subtitle = subtitle,
            color = color,
            titleColor = titleColor,
            scrollBehavior = scrollBehavior,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            navigationIcon = navigationIcon,
            actions = actions,
            bottomContent = bottomContent,
        )
    }
}

@Composable
fun rememberBlurBackdrop(): LayerBackdrop? {
    val appState = LocalAppState.current
    if (!appState.enableBlur || !isRuntimeShaderSupported()) return null
    val surfaceColor = MiuixTheme.colorScheme.surface
    return rememberLayerBackdrop {
        drawRect(surfaceColor)
        drawContent()
    }
}

@Composable
fun BlurredBar(
    backdrop: LayerBackdrop?,
    blurEnabled: Boolean,
    content: @Composable () -> Unit,
) {
    val glassStyle = LocalAppState.current.topAppBarStyle == 1
    val blurActive = blurEnabled && backdrop != null
    Box {
        if (blurActive && !glassStyle) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .textureBlur(
                        backdrop = backdrop,
                        shape = RectangleShape,
                        blurRadius = 25f,
                        colors = barBlurColors(),
                    ),
            )
        }
        CompositionLocalProvider(
            LocalTopAppBarBackdrop provides backdrop,
            LocalTopAppBarBackgroundBlur provides blurActive,
        ) {
            content()
        }
    }
}

@Composable
private fun barBlurColors(): BlurColors = BlurDefaults.blurColors(
    blendColors = listOf(
        BlendColorEntry(color = MiuixTheme.colorScheme.surface.copy(alpha = 0.8f)),
    ),
)
