// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import component.CardSegment
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.squircle.squircleBackground
import top.yukonga.miuix.kmp.squircle.squircleBorder
import top.yukonga.miuix.kmp.theme.Colors
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.darkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import top.yukonga.miuix.kmp.theme.platformDynamicColors
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberBlurBackdrop

private val CamelCaseRegex = Regex("([A-Z])")
private val ColorBlockCornerRadius = 12.dp

@Immutable
private data class ColorBlockData(
    val name: String,
    val displayName: String,
    val surfaceColor: Color,
    val textColor: Color,
)

@Composable
fun ColorPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    val backdrop = rememberBlurBackdrop()
    val blurActive = backdrop != null
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface

    val currentColors = MiuixTheme.colorScheme
    val lightColors = remember { lightColorScheme() }
    val darkColors = remember { darkColorScheme() }
    val dynLight = platformDynamicColors(dark = false)
    val dynDark = platformDynamicColors(dark = true)

    val currentRows = remember(currentColors) { buildColorBlockRows(currentColors) }
    val lightRows = remember(lightColors) { buildColorBlockRows(lightColors) }
    val dynLightRows = remember(dynLight) { buildColorBlockRows(dynLight) }
    val darkRows = remember(darkColors) { buildColorBlockRows(darkColors) }
    val dynDarkRows = remember(dynDark) { buildColorBlockRows(dynDark) }
    Scaffold(
        topBar = {
            BlurredBar(backdrop, blurActive) {
                AdaptiveTopAppBar(
                    title = "Color",
                    showTopAppBar = appState.showTopAppBar,
                    isWideScreen = isWideScreen,
                    scrollBehavior = topAppBarScrollBehavior,
                    color = barColor,
                )
            }
        },
    ) { innerPadding ->
        val lazyListState = rememberLazyListState()
        val contentPadding = pageContentPadding(innerPadding, padding)
        Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.pageScrollModifiers(
                    appState.enableScrollEndHaptic,
                    appState.showTopAppBar,
                    topAppBarScrollBehavior,
                ),
                overscrollEffect = null,
                contentPadding = contentPadding,
            ) {
                colorPaletteSection(
                    keyPrefix = "current",
                    title = "Current Theme Colors",
                    containerColor = currentColors.surfaceContainer,
                    rows = currentRows,
                )
                colorPaletteSection(
                    keyPrefix = "light",
                    title = "Light Theme Colors",
                    containerColor = lightColors.surfaceContainer,
                    rows = lightRows,
                )
                colorPaletteSection(
                    keyPrefix = "dynamic_light",
                    title = "Dynamic Light Colors",
                    containerColor = dynLight.surfaceContainer,
                    rows = dynLightRows,
                )
                colorPaletteSection(
                    keyPrefix = "dark",
                    title = "Dark Theme Colors",
                    containerColor = darkColors.surfaceContainer,
                    rows = darkRows,
                )
                colorPaletteSection(
                    keyPrefix = "dynamic_dark",
                    title = "Dynamic Dark Colors",
                    containerColor = dynDark.surfaceContainer,
                    rows = dynDarkRows,
                    bottomPadding = false,
                )
                item(key = "bottom_spacer", contentType = "spacer") { Spacer(modifier = Modifier.height(12.dp)) }
            }
            VerticalScrollBar(
                adapter = rememberScrollBarAdapter(lazyListState),
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                trackPadding = contentPadding,
                topAppBarScrollBehavior = topAppBarScrollBehavior.takeIf { appState.showTopAppBar },
            )
        }
    }
}

private val PaletteHorizontalPadding = PaddingValues(horizontal = 16.dp)

private fun LazyListScope.colorPaletteSection(
    keyPrefix: String,
    title: String,
    containerColor: Color,
    rows: List<List<ColorBlockData>>,
    bottomPadding: Boolean = true,
) {
    item(key = "${keyPrefix}_title", contentType = "palette_title") {
        SmallTitle(title)
    }
    val lastRowIdx = rows.lastIndex
    items(
        count = rows.size,
        key = { "${keyPrefix}_$it" },
        contentType = { "color_row" },
    ) { rowIdx ->
        val rowColors = rows[rowIdx]
        val isFirst = rowIdx == 0
        val isLast = rowIdx == lastRowIdx
        CardSegment(
            isFirst = isFirst,
            isLast = isLast,
            color = containerColor,
            outerBottomPadding = if (isLast && bottomPadding) 12.dp else 0.dp,
            insidePadding = PaletteHorizontalPadding,
        ) {
            Row(
                modifier = Modifier.padding(
                    top = if (isFirst) 16.dp else 0.dp,
                    bottom = 16.dp,
                ),
            ) {
                rowColors.forEachIndexed { idx, block ->
                    ColorBlock(
                        displayName = block.displayName,
                        surfaceColor = block.surfaceColor,
                        textColor = block.textColor,
                        modifier = Modifier
                            .weight(1f)
                            .then(if (idx < rowColors.lastIndex) Modifier.padding(end = 16.dp) else Modifier),
                    )
                }
            }
        }
    }
}

private fun buildColorBlockRows(colors: Colors): List<List<ColorBlockData>> {
    val colorList = listOf(
        "primary" to colors.primary,
        "onPrimary" to colors.onPrimary,
        "primaryVariant" to colors.primaryVariant,
        "onPrimaryVariant" to colors.onPrimaryVariant,
        "error" to colors.error,
        "onError" to colors.onError,
        "errorContainer" to colors.errorContainer,
        "onErrorContainer" to colors.onErrorContainer,
        "disabledPrimary" to colors.disabledPrimary,
        "disabledOnPrimary" to colors.disabledOnPrimary,
        "disabledPrimaryButton" to colors.disabledPrimaryButton,
        "disabledOnPrimaryButton" to colors.disabledOnPrimaryButton,
        "disabledPrimarySlider" to colors.disabledPrimarySlider,
        "primaryContainer" to colors.primaryContainer,
        "onPrimaryContainer" to colors.onPrimaryContainer,
        "secondary" to colors.secondary,
        "onSecondary" to colors.onSecondary,
        "secondaryVariant" to colors.secondaryVariant,
        "onSecondaryVariant" to colors.onSecondaryVariant,
        "disabledSecondary" to colors.disabledSecondary,
        "disabledOnSecondary" to colors.disabledOnSecondary,
        "disabledSecondaryVariant" to colors.disabledSecondaryVariant,
        "disabledOnSecondaryVariant" to colors.disabledOnSecondaryVariant,
        "secondaryContainer" to colors.secondaryContainer,
        "onSecondaryContainer" to colors.onSecondaryContainer,
        "secondaryContainerVariant" to colors.secondaryContainerVariant,
        "onSecondaryContainerVariant" to colors.onSecondaryContainerVariant,
        "tertiaryContainer" to colors.tertiaryContainer,
        "onTertiaryContainer" to colors.onTertiaryContainer,
        "tertiaryContainerVariant" to colors.tertiaryContainerVariant,
        "background" to colors.background,
        "onBackground" to colors.onBackground,
        "onBackgroundVariant" to colors.onBackgroundVariant,
        "surface" to colors.surface,
        "onSurface" to colors.onSurface,
        "surfaceVariant" to colors.surfaceVariant,
        "onSurfaceSecondary" to colors.onSurfaceSecondary,
        "onSurfaceVariantSummary" to colors.onSurfaceVariantSummary,
        "onSurfaceVariantActions" to colors.onSurfaceVariantActions,
        "disabledOnSurface" to colors.disabledOnSurface,
        "surfaceContainer" to colors.surfaceContainer,
        "onSurfaceContainer" to colors.onSurfaceContainer,
        "onSurfaceContainerVariant" to colors.onSurfaceContainerVariant,
        "surfaceContainerHigh" to colors.surfaceContainerHigh,
        "onSurfaceContainerHigh" to colors.onSurfaceContainerHigh,
        "surfaceContainerHighest" to colors.surfaceContainerHighest,
        "onSurfaceContainerHighest" to colors.onSurfaceContainerHighest,
        "outline" to colors.outline,
        "dividerLine" to colors.dividerLine,
        "windowDimming" to colors.windowDimming,
        "sliderKeyPoint" to colors.sliderKeyPoint,
        "sliderKeyPointForeground" to colors.sliderKeyPointForeground,
    )
    val colorMap = colorList.toMap()
    return colorList.map { (name, color) ->
        val (surfaceColor, textColor) = when {
            name.startsWith("on") && colorMap.containsKey(name.removePrefix("on").replaceFirstChar { it.lowercase() }) -> {
                color to colorMap[name.removePrefix("on").replaceFirstChar { it.lowercase() }]!!
            }

            !name.startsWith("on") && colorMap.containsKey("on" + name.replaceFirstChar { it.uppercase() }) -> {
                color to colorMap["on" + name.replaceFirstChar { it.uppercase() }]!!
            }

            else -> color to (if (color.luminance() > 0.5) Color.Black else Color.White)
        }
        val displayName = name.replace(CamelCaseRegex, " $1").trim().replaceFirstChar { it.uppercase() }
        ColorBlockData(name, displayName, surfaceColor, textColor)
    }.chunked(2)
}

@Composable
private fun ColorBlock(displayName: String, surfaceColor: Color, textColor: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .squircleBackground(surfaceColor, ColorBlockCornerRadius)
            .squircleBorder(1.dp, textColor, ColorBlockCornerRadius),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = displayName,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp),
        )
    }
}
