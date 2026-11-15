// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import LocalAppState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BlurTopAppBar
import top.yukonga.miuix.kmp.basic.ScrollBehavior
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

/** Extra title opacity, for chrome that covers the bar's title, such as a collapsed glass sidebar. */
val LocalTopAppBarTitleAlpha = staticCompositionLocalOf<() -> Float> { { 1f } }
val LocalWideScreenContentInset = staticCompositionLocalOf<() -> Dp> { { 0.dp } }
val LocalWideScreenContentEndInset = staticCompositionLocalOf<() -> Dp> { { 0.dp } }

private class DynamicStartPaddingValues(
    private val base: PaddingValues,
    private val inset: () -> Dp,
) : PaddingValues {
    override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp = base.calculateLeftPadding(layoutDirection) + if (layoutDirection == LayoutDirection.Ltr) inset() else 0.dp

    override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp = base.calculateRightPadding(layoutDirection) + if (layoutDirection == LayoutDirection.Rtl) inset() else 0.dp

    override fun calculateTopPadding(): Dp = base.calculateTopPadding()

    override fun calculateBottomPadding(): Dp = base.calculateBottomPadding()
}
private fun Modifier.wideScreenContentInset(
    startInset: () -> Dp,
    endInset: () -> Dp,
): Modifier = layout { measurable, constraints ->
    val startPx = startInset().roundToPx().coerceAtLeast(0)
    val endPx = endInset().roundToPx().coerceAtLeast(0)
    val availableWidth = (constraints.maxWidth - startPx - endPx).coerceAtLeast(0)
    val placeable = measurable.measure(
        constraints.copy(
            minWidth = 0,
            maxWidth = availableWidth,
        ),
    )
    layout(constraints.maxWidth, placeable.height) {
        placeable.placeRelative(startPx, 0)
    }
}

@Composable
fun Modifier.wideScreenContentInset(): Modifier = wideScreenContentInset(
    startInset = LocalWideScreenContentInset.current,
    endInset = LocalWideScreenContentEndInset.current,
)

@Composable
fun wideScreenContentPadding(padding: PaddingValues, enabled: Boolean = true): PaddingValues = if (enabled) DynamicStartPaddingValues(padding, LocalWideScreenContentInset.current) else padding

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

    val contentInset = if (isWideScreen) {
        LocalWideScreenContentInset.current
    } else {
        { 0.dp }
    }
    return remember(topPadding, bottomPadding, extraStart, extraEnd, extraBottom, isWideScreen, contentInset) {
        DynamicStartPaddingValues(
            base = PaddingValues(
                top = topPadding,
                start = extraStart,
                end = extraEnd,
                bottom = bottomPadding,
            ),
            inset = contentInset,
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
    val contentInsetModifier = Modifier.wideScreenContentInset()
    if (!showTopAppBar) return
    val appState = LocalAppState.current
    val chromeTitleAlpha = LocalTopAppBarTitleAlpha.current
    if (appState.topAppBarStyle == 1) {
        val backdrop = LocalTopAppBarBackdrop.current
        val backgroundBlur = LocalTopAppBarBackgroundBlur.current
        val shaderSupported = isRuntimeShaderSupported()
        val useBandBrush = !shaderSupported || (backdrop != null && backgroundBlur)
        val surfaceColor = MiuixTheme.colorScheme.surface
        val bandBrush = remember(useBandBrush, surfaceColor, color) {
            if (useBandBrush) GlassTopAppBarDefaults.bandBrush(surfaceColor) else SolidColor(color)
        }
        val contentScrolled by remember(scrollBehavior, isContentScrolled) {
            derivedStateOf { isContentScrolled ?: (scrollBehavior.state.contentOffset < 0f) }
        }
        GlassTopAppBar(
            title = title,
            isContentScrolled = contentScrolled,
            contentModifier = contentModifier.then(contentInsetModifier),
            expandable = !isWideScreen,
            subtitle = subtitle,
            backdrop = backdrop,
            progressiveBlur = appState.glassTopBarProgressiveBlur && appState.enableBlur,
            scrollBehavior = scrollBehavior,
            visualContentOffset = visualContentOffset,
            titleAlpha = { titleAlpha() * chromeTitleAlpha() },
            backgroundAlpha = { if (backgroundBlur || !shaderSupported) backgroundAlpha() else 0f },
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            bandBrush = bandBrush,
            bandOverhang = if (appState.enableBlur || !shaderSupported) GlassTopAppBarDefaults.BandOverhang else 0.dp,
            navigationIcon = navigationIcon,
            actions = actions,
            bottomContent = bottomContent,
        )
    } else {
        BlurTopAppBar(
            title = title,
            largeTitleBlurRadius = 0.dp,
            modifier = contentInsetModifier,
            expandable = !isWideScreen,
            subtitle = subtitle,
            color = color,
            titleColor = titleColor,
            scrollBehavior = scrollBehavior,
            titleAlpha = { chromeTitleAlpha() },
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
