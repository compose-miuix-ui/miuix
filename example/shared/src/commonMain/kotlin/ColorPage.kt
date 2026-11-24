// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.layout.Box
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
import kotlin.concurrent.Volatile

private val CamelCaseRegex = Regex("([A-Z])")
private val ColorBlockCornerRadius = 12.dp

@Immutable
private data class ColorBlockData(
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
    val dynLight = platformDynamicColors(dark = false)
    val dynDark = platformDynamicColors(dark = true)

    val currentRows = remember(currentColors) { buildColorBlockRows(currentColors) }
    val dynLightRows = rememberDynamicColorRows(dynLight, isDark = false)
    val dynDarkRows = rememberDynamicColorRows(dynDark, isDark = true)
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
                    containerColor = LightColorScheme.surfaceContainer,
                    rows = LightColorRows,
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
                    containerColor = DarkColorScheme.surfaceContainer,
                    rows = DarkColorRows,
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

private class ColorSlotSpec(
    val displayName: String,
    val partnerIndex: Int,
    val extract: (Colors) -> Color,
)

private val ColorSlotSpecs: List<ColorSlotSpec> by lazy {
    val rawSlots: List<Pair<String, (Colors) -> Color>> = listOf(
        "primary" to { it.primary },
        "onPrimary" to { it.onPrimary },
        "primaryVariant" to { it.primaryVariant },
        "onPrimaryVariant" to { it.onPrimaryVariant },
        "error" to { it.error },
        "onError" to { it.onError },
        "errorContainer" to { it.errorContainer },
        "onErrorContainer" to { it.onErrorContainer },
        "disabledPrimary" to { it.disabledPrimary },
        "disabledOnPrimary" to { it.disabledOnPrimary },
        "disabledPrimaryButton" to { it.disabledPrimaryButton },
        "disabledOnPrimaryButton" to { it.disabledOnPrimaryButton },
        "disabledPrimarySlider" to { it.disabledPrimarySlider },
        "primaryContainer" to { it.primaryContainer },
        "onPrimaryContainer" to { it.onPrimaryContainer },
        "secondary" to { it.secondary },
        "onSecondary" to { it.onSecondary },
        "secondaryVariant" to { it.secondaryVariant },
        "onSecondaryVariant" to { it.onSecondaryVariant },
        "disabledSecondary" to { it.disabledSecondary },
        "disabledOnSecondary" to { it.disabledOnSecondary },
        "disabledSecondaryVariant" to { it.disabledSecondaryVariant },
        "disabledOnSecondaryVariant" to { it.disabledOnSecondaryVariant },
        "secondaryContainer" to { it.secondaryContainer },
        "onSecondaryContainer" to { it.onSecondaryContainer },
        "secondaryContainerVariant" to { it.secondaryContainerVariant },
        "onSecondaryContainerVariant" to { it.onSecondaryContainerVariant },
        "tertiaryContainer" to { it.tertiaryContainer },
        "onTertiaryContainer" to { it.onTertiaryContainer },
        "tertiaryContainerVariant" to { it.tertiaryContainerVariant },
        "background" to { it.background },
        "onBackground" to { it.onBackground },
        "onBackgroundVariant" to { it.onBackgroundVariant },
        "surface" to { it.surface },
        "onSurface" to { it.onSurface },
        "surfaceVariant" to { it.surfaceVariant },
        "onSurfaceSecondary" to { it.onSurfaceSecondary },
        "onSurfaceVariantSummary" to { it.onSurfaceVariantSummary },
        "onSurfaceVariantActions" to { it.onSurfaceVariantActions },
        "disabledOnSurface" to { it.disabledOnSurface },
        "surfaceContainer" to { it.surfaceContainer },
        "onSurfaceContainer" to { it.onSurfaceContainer },
        "onSurfaceContainerVariant" to { it.onSurfaceContainerVariant },
        "surfaceContainerHigh" to { it.surfaceContainerHigh },
        "onSurfaceContainerHigh" to { it.onSurfaceContainerHigh },
        "surfaceContainerHighest" to { it.surfaceContainerHighest },
        "onSurfaceContainerHighest" to { it.onSurfaceContainerHighest },
        "outline" to { it.outline },
        "dividerLine" to { it.dividerLine },
        "windowDimming" to { it.windowDimming },
        "sliderKeyPoint" to { it.sliderKeyPoint },
        "sliderKeyPointForeground" to { it.sliderKeyPointForeground },
    )
    val nameToIndex = rawSlots.mapIndexed { idx, (name, _) -> name to idx }.toMap()
    rawSlots.map { (name, extractor) ->
        val partnerName = when {
            name.startsWith("on") -> name.removePrefix("on").replaceFirstChar { it.lowercase() }
            else -> "on" + name.replaceFirstChar { it.uppercase() }
        }
        val partnerIndex = nameToIndex[partnerName] ?: -1
        val displayName = name.replace(CamelCaseRegex, " $1").trim().replaceFirstChar { it.uppercase() }
        ColorSlotSpec(displayName = displayName, partnerIndex = partnerIndex, extract = extractor)
    }
}

private val LightColorScheme by lazy { lightColorScheme() }
private val DarkColorScheme by lazy { darkColorScheme() }
private val LightColorRows by lazy { buildColorBlockRows(LightColorScheme) }
private val DarkColorRows by lazy { buildColorBlockRows(DarkColorScheme) }

@Volatile
private var cachedDynamicLight: Pair<Colors, List<List<ColorBlockData>>>? = null

@Volatile
private var cachedDynamicDark: Pair<Colors, List<List<ColorBlockData>>>? = null

private fun rememberDynamicColorRows(colors: Colors, isDark: Boolean): List<List<ColorBlockData>> {
    val cached = if (isDark) cachedDynamicDark else cachedDynamicLight
    if (cached != null && cached.first == colors) return cached.second
    val built = buildColorBlockRows(colors)
    val entry = colors to built
    if (isDark) {
        cachedDynamicDark = entry
    } else {
        cachedDynamicLight = entry
    }
    return built
}

private fun buildColorBlockRows(colors: Colors): List<List<ColorBlockData>> {
    val specs = ColorSlotSpecs
    val resolved = Array(specs.size) { i -> specs[i].extract(colors) }
    val rowCount = (specs.size + 1) / 2
    return List(rowCount) { row ->
        val start = row * 2
        val end = minOf(start + 2, specs.size)
        List(end - start) { offset ->
            val idx = start + offset
            val spec = specs[idx]
            val surfaceColor = resolved[idx]
            val textColor = if (spec.partnerIndex >= 0) {
                resolved[spec.partnerIndex]
            } else if (surfaceColor.luminance() > 0.5f) {
                Color.Black
            } else {
                Color.White
            }
            ColorBlockData(
                displayName = spec.displayName,
                surfaceColor = surfaceColor,
                textColor = textColor,
            )
        }
    }
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
