// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import LocalAppState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import top.yukonga.miuix.kmp.basic.ScrollBehavior
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

/** Extra title opacity, for chrome that covers the bar's title, such as a collapsed glass sidebar. */
val LocalTopAppBarTitleAlpha = staticCompositionLocalOf<() -> Float> { { 1f } }

/** Start offset of centered chrome (such as a collapsed glass sidebar) obstructing the top bar center. */
val LocalTopAppBarCenterObstructionStart = staticCompositionLocalOf<() -> Dp> { { Dp.Unspecified } }

/**
 * Full-window safe areas and the remaining insets in the current page pane. A sibling rail has
 * already reserved its start space; an overlaid rail is included in [contentInsets]. Backgrounds
 * stay edge-to-edge. Animated providers are read by layout, not while publishing this contract.
 */
@Stable
class AppPageInsets(
    val windowInsets: WindowInsets,
    val safeInsets: WindowInsets,
    private val overlayStart: () -> Dp,
    val searchTop: () -> Dp,
) {
    val contentInsets: WindowInsets = object : WindowInsets {
        override fun getLeft(density: Density, layoutDirection: LayoutDirection): Int = safeInsets.getLeft(density, layoutDirection) +
            if (layoutDirection == LayoutDirection.Ltr) with(density) { overlayStart().roundToPx() } else 0

        override fun getRight(density: Density, layoutDirection: LayoutDirection): Int = safeInsets.getRight(density, layoutDirection) +
            if (layoutDirection == LayoutDirection.Rtl) with(density) { overlayStart().roundToPx() } else 0

        override fun getTop(density: Density): Int = safeInsets.getTop(density)

        override fun getBottom(density: Density): Int = safeInsets.getBottom(density)
    }

    /** Keeps the last in-view clearance while [hidden], instead of following the overlay. */
    fun whenHidden(hidden: () -> Boolean): AppPageInsets = AppPageInsets(
        windowInsets = windowInsets,
        safeInsets = safeInsets,
        overlayStart = overlayStart.frozenWhile(hidden),
        searchTop = searchTop.frozenWhile(hidden),
    )
}

/** Reads [this] while [frozen] is false; while it is true, returns the last value read instead. */
private fun (() -> Dp).frozenWhile(frozen: () -> Boolean): () -> Dp {
    val live = this
    var last = Dp.Unspecified
    return {
        val held = last
        if (frozen() && held != Dp.Unspecified) held else live().also { last = it }
    }
}

val LocalAppPageInsets = staticCompositionLocalOf {
    AppPageInsets(WindowInsets(0), WindowInsets(0), { 0.dp }, { 0.dp })
}

/** Only the app host combines platform safe areas, external host padding and navigation chrome. */
@Composable
fun rememberAppPageInsets(
    hostPadding: PaddingValues,
    startAlreadyReserved: Boolean,
    overlayStart: () -> Dp,
    searchTop: () -> Dp,
): AppPageInsets {
    val systemInsets = WindowInsets.systemBars.union(WindowInsets.captionBar).union(WindowInsets.displayCutout)
    return remember(systemInsets, hostPadding, startAlreadyReserved, overlayStart, searchTop) {
        val hostInsets = object : WindowInsets {
            override fun getLeft(density: Density, layoutDirection: LayoutDirection): Int = with(density) {
                hostPadding.calculateLeftPadding(layoutDirection).roundToPx()
            }

            override fun getRight(density: Density, layoutDirection: LayoutDirection): Int = with(density) {
                hostPadding.calculateRightPadding(layoutDirection).roundToPx()
            }

            override fun getTop(density: Density): Int = with(density) { hostPadding.calculateTopPadding().roundToPx() }

            override fun getBottom(density: Density): Int = with(density) { hostPadding.calculateBottomPadding().roundToPx() }
        }
        val windowInsets = systemInsets.union(hostInsets)
        val safeInsets = if (startAlreadyReserved) {
            windowInsets.only(WindowInsetsSides.End + WindowInsetsSides.Vertical)
        } else {
            windowInsets
        }
        AppPageInsets(windowInsets, safeInsets, overlayStart, searchTop)
    }
}

/** Apply and consume horizontal page space without moving its backdrop or vertical content. */
@Composable
fun Modifier.appPageHorizontalInsets(): Modifier = windowInsetsPadding(
    LocalAppPageInsets.current.contentInsets.only(WindowInsetsSides.Horizontal),
)

/** Top bars own the remaining top safe area; internal inset modifiers see it consumed. */
@Composable
fun Modifier.appPageTopBarInsets(): Modifier = appPageHorizontalInsets().windowInsetsPadding(
    LocalAppPageInsets.current.safeInsets.only(WindowInsetsSides.Top),
)

/** Search shares horizontal page clearance and moves below the collapsed navigation capsule. */
@Composable
fun Modifier.appPageSearchInsets(): Modifier {
    val searchTop = LocalAppPageInsets.current.searchTop
    return appPageHorizontalInsets().layout { measurable, constraints ->
        val top = searchTop().roundToPx().coerceIn(0, constraints.maxHeight)
        val placeable = measurable.measure(constraints.offset(vertical = -top))
        layout(placeable.width, placeable.height + top) {
            placeable.placeRelative(0, top)
        }
    }
}

/** System/host safe areas only, independent of the sidebar and collapsed search capsule. */
@Composable
fun rememberAppSafePadding(): PaddingValues = LocalAppPageInsets.current.safeInsets.asPaddingValues()

/** Bottom-only clearance for search results and floating page controls. */
@Composable
fun pageBottomPadding(
    outerPadding: PaddingValues = PaddingValues(0.dp),
    extraBottom: Dp = 0.dp,
): PaddingValues {
    val safePadding = rememberAppSafePadding()
    return remember(outerPadding, safePadding, extraBottom) {
        object : PaddingValues {
            override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp = 0.dp

            override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp = 0.dp

            override fun calculateTopPadding(): Dp = 0.dp

            override fun calculateBottomPadding(): Dp = maxOf(outerPadding.calculateBottomPadding(), safePadding.calculateBottomPadding()) + extraBottom
        }
    }
}

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
    outerPadding: PaddingValues = PaddingValues(0.dp),
    extraTop: Dp = 0.dp,
    extraStart: Dp = 0.dp,
    extraEnd: Dp = 0.dp,
    extraBottom: Dp = 0.dp,
): PaddingValues {
    val pagePadding = LocalAppPageInsets.current.contentInsets.asPaddingValues()
    return remember(innerPadding, outerPadding, pagePadding, extraTop, extraStart, extraEnd, extraBottom) {
        object : PaddingValues {
            override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp = maxOf(
                innerPadding.calculateLeftPadding(layoutDirection),
                outerPadding.calculateLeftPadding(layoutDirection),
                pagePadding.calculateLeftPadding(layoutDirection),
            ) + if (layoutDirection == LayoutDirection.Ltr) extraStart else extraEnd

            override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp = maxOf(
                innerPadding.calculateRightPadding(layoutDirection),
                outerPadding.calculateRightPadding(layoutDirection),
                pagePadding.calculateRightPadding(layoutDirection),
            ) + if (layoutDirection == LayoutDirection.Rtl) extraStart else extraEnd

            override fun calculateTopPadding(): Dp = maxOf(
                innerPadding.calculateTopPadding(),
                outerPadding.calculateTopPadding(),
                pagePadding.calculateTopPadding(),
            ) + extraTop

            override fun calculateBottomPadding(): Dp = maxOf(
                innerPadding.calculateBottomPadding(),
                outerPadding.calculateBottomPadding(),
                pagePadding.calculateBottomPadding(),
            ) + extraBottom
        }
    }
}

@Composable
fun AdaptiveTopAppBar(
    title: String,
    showTopAppBar: Boolean,
    isWideScreen: Boolean,
    scrollBehavior: ScrollBehavior,
    contentModifier: Modifier = Modifier,
    expandable: Boolean = !isWideScreen,
    subtitle: String = "",
    color: Color = MiuixTheme.colorScheme.surface,
    titleColor: Color = MiuixTheme.colorScheme.onSurface,
    isContentScrolled: Boolean? = null,
    titleAlpha: () -> Float = { titleColor.alpha },
    backgroundAlpha: () -> Float = { 1f },
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
) {
    val contentInsetModifier = Modifier.appPageTopBarInsets()
    if (!showTopAppBar) return
    val appState = LocalAppState.current
    val chromeTitleAlpha = LocalTopAppBarTitleAlpha.current
    val centerObstructionStart = LocalTopAppBarCenterObstructionStart.current()
    if (appState.topAppBarStyle == 1) {
        val backdrop = LocalTopAppBarBackdrop.current
        val surfaceColor = MiuixTheme.colorScheme.surface
        val bandBrush = remember(surfaceColor) { GlassTopAppBarDefaults.bandBrush(surfaceColor) }
        val contentScrolled by remember(scrollBehavior, isContentScrolled) {
            computedStateOf {
                isContentScrolled ?: (scrollBehavior.state.contentOffset < -1f || scrollBehavior.state.overscrollOffset < -1f)
            }
        }
        GlassTopAppBar(
            title = title,
            isContentScrolled = contentScrolled,
            contentModifier = contentModifier.then(contentInsetModifier),
            expandable = expandable,
            subtitle = subtitle,
            backdrop = backdrop,
            progressiveBlur = appState.enableBlur,
            scrollBehavior = scrollBehavior,
            titleAlpha = { titleAlpha() * chromeTitleAlpha() },
            backgroundAlpha = backgroundAlpha,
            centerObstructionStart = centerObstructionStart,
            defaultWindowInsetsPadding = false,
            bandBrush = bandBrush,
            bandOverhang = GlassTopAppBarDefaults.BandOverhang,
            navigationIcon = navigationIcon,
            actions = actions,
            bottomContent = bottomContent,
        )
    } else {
        TopAppBar(
            title = title,
            modifier = Modifier
                .background(color)
                .then(contentModifier)
                .then(contentInsetModifier),
            expandable = expandable,
            subtitle = subtitle,
            color = Color.Transparent,
            titleColor = titleColor,
            scrollBehavior = scrollBehavior,
            defaultWindowInsetsPadding = false,
            centerObstructionStart = centerObstructionStart,
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
