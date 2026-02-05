// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.kyant.shapes.RoundedRectangle
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.roundToInt

/**
 * A [TabRow] with Miuix style.
 *
 * @param tabs The text to be displayed in the [TabRow].
 * @param selectedTabIndex The selected tab index of the [TabRow].
 * @param onTabSelected The callback when a tab is selected.
 * @param modifier The modifier to be applied to the [TabRow].
 * @param colors The colors of the [TabRow].
 * @param minWidth The minimum width of the tab in [TabRow].
 * @param maxWidth The maximum width of the tab in [TabRow].
 * @param height The height of the [TabRow].
 * @param cornerRadius The round corner radius of the tab in [TabRow].
 * @param itemSpacing The spacing between tabs in [TabRow].
 * @param contentAlignment The content alignment of the tab in [TabRow].
 */
@Composable
@NonRestartableComposable
fun TabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    colors: TabRowColors = TabRowDefaults.tabRowColors(),
    minWidth: Dp = TabRowDefaults.TabRowMinWidth,
    maxWidth: Dp = TabRowDefaults.TabRowMaxWidth,
    height: Dp = TabRowDefaults.TabRowHeight,
    cornerRadius: Dp = TabRowDefaults.TabRowCornerRadius,
    itemSpacing: Dp = 9.dp,
    contentAlignment: Alignment = Alignment.Center,
) {
    val currentOnTabSelected by rememberUpdatedState(onTabSelected)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .height(height)
            .background(color = colors.backgroundColor(false)),
    ) {
        val config = rememberTabRowConfig(tabs, minWidth, maxWidth, cornerRadius, itemSpacing, this.maxWidth)
        val density = LocalDensity.current
        val tabWidthPx = with(density) { config.tabWidth.toPx() }
        val spacingPx = with(density) { itemSpacing.toPx() }
        val indicatorOffset = remember { Animatable(0f) }
        val availableWidth = this.maxWidth

        LaunchedEffect(selectedTabIndex, tabWidthPx, spacingPx) {
            val target = selectedTabIndex * (tabWidthPx + spacingPx)
            indicatorOffset.animateTo(target, tween(200, easing = LinearEasing))
        }

        LaunchedEffect(selectedTabIndex, availableWidth) {
            val centerOffset = (availableWidth - config.tabWidth) / 2
            val offsetPx = with(density) { -centerOffset.toPx() }.roundToInt()
            config.listState.animateScrollToItem(selectedTabIndex, offsetPx)
        }

        val scrollOffset by remember {
            derivedStateOf {
                val state = config.listState
                val firstIndex = state.firstVisibleItemIndex
                val firstOffset = state.firstVisibleItemScrollOffset
                firstIndex * (tabWidthPx + spacingPx) + firstOffset
            }
        }

        Box(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .offset { IntOffset((indicatorOffset.value - scrollOffset).roundToInt(), 0) }
                    .width(config.tabWidth)
                    .fillMaxHeight()
                    .clip(config.shape)
                    .background(colors.backgroundColor(true)),
            )
            LazyRow(
                state = config.listState,
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(itemSpacing),
                overscrollEffect = null,
            ) {
                itemsIndexed(tabs) { index, tabText ->
                    TabItem(
                        text = tabText,
                        isSelected = selectedTabIndex == index,
                        onClick = { currentOnTabSelected.invoke(index) },
                        shape = config.shape,
                        width = config.tabWidth,
                        color = colors.contentColor(selectedTabIndex == index),
                        contentAlignment = contentAlignment,
                    )
                }
            }
        }
    }
}

/**
 * A [TabRowWithContour] with Miuix style.
 *
 * @param tabs The text to be displayed in the [TabRow].
 * @param selectedTabIndex The selected tab index of the [TabRow].
 * @param onTabSelected The callback when a tab is selected.
 * @param modifier The modifier to be applied to the [TabRow].
 * @param colors The colors of the [TabRow].
 * @param minWidth The minimum width of the tab in [TabRow].
 * @param maxWidth The maximum width of the tab in [TabRow].
 * @param height The height of the [TabRow].
 * @param cornerRadius The round corner radius of the tab in [TabRow].
 * @param itemSpacing The spacing between tabs in [TabRow].
 * @param contentAlignment The content alignment of the tab in [TabRow].
 */
@Composable
@NonRestartableComposable
fun TabRowWithContour(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    colors: TabRowColors = TabRowDefaults.tabRowColors(),
    minWidth: Dp = TabRowDefaults.TabRowWithContourMinWidth,
    maxWidth: Dp = TabRowDefaults.TabRowWithContourMaxWidth,
    height: Dp = TabRowDefaults.TabRowWithContourHeight,
    cornerRadius: Dp = TabRowDefaults.TabRowWithContourCornerRadius,
    itemSpacing: Dp = 5.dp,
    contentAlignment: Alignment = Alignment.Center,
) {
    val currentOnTabSelected by rememberUpdatedState(onTabSelected)
    val contourPadding = 5.dp

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .height(height),
    ) {
        val lazyRowAvailableWidth = this.maxWidth - (contourPadding * 2)
        val config = rememberTabRowConfig(tabs, minWidth, maxWidth, cornerRadius, itemSpacing, lazyRowAvailableWidth)
        val density = LocalDensity.current
        val tabWidthPx = with(density) { config.tabWidth.toPx() }
        val spacingPx = with(density) { itemSpacing.toPx() }
        val indicatorOffset = remember { Animatable(0f) }
        val availableWidth = this.maxWidth

        LaunchedEffect(selectedTabIndex, tabWidthPx, spacingPx) {
            val target = selectedTabIndex * (tabWidthPx + spacingPx)
            indicatorOffset.animateTo(target, tween(200, easing = LinearEasing))
        }

        LaunchedEffect(selectedTabIndex, availableWidth) {
            val centerOffset = (availableWidth - (contourPadding * 2) - config.tabWidth) / 2
            val offsetPx = with(density) { -centerOffset.toPx() }.roundToInt()
            config.listState.animateScrollToItem(selectedTabIndex, offsetPx)
        }

        val scrollOffset by remember {
            derivedStateOf {
                val state = config.listState
                val firstIndex = state.firstVisibleItemIndex
                val firstOffset = state.firstVisibleItemScrollOffset
                firstIndex * (tabWidthPx + spacingPx) + firstOffset
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedRectangle(cornerRadius + contourPadding))
                .background(color = colors.backgroundColor(false))
                .padding(contourPadding),
        ) {
            Box(
                Modifier
                    .offset { IntOffset((indicatorOffset.value - scrollOffset).roundToInt(), 0) }
                    .width(config.tabWidth)
                    .fillMaxHeight()
                    .clip(config.shape)
                    .background(colors.backgroundColor(true)),
            )
            LazyRow(
                state = config.listState,
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(itemSpacing),
                overscrollEffect = null,
            ) {
                itemsIndexed(tabs) { index, tabText ->
                    TabItemWithContour(
                        text = tabText,
                        isSelected = selectedTabIndex == index,
                        onClick = { currentOnTabSelected.invoke(index) },
                        shape = config.shape,
                        width = config.tabWidth,
                        color = colors.contentColor(selectedTabIndex == index),
                        contentAlignment = contentAlignment,
                    )
                }
            }
        }
    }
}

@Composable
private fun TabItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    shape: RoundedRectangle,
    width: Dp,
    color: Color = Color.Unspecified,
    contentAlignment: Alignment = Alignment.Center,
) {
    val currentOnClick by rememberUpdatedState(onClick)
    Surface(
        shape = shape,
        onClick = {
            currentOnClick()
        },
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxHeight()
            .width(width)
            .semantics { role = Role.Tab },
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp),
            contentAlignment = contentAlignment,
        ) {
            Text(
                text = text,
                color = color,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                fontSize = MiuixTheme.textStyles.body1.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun TabItemWithContour(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    shape: RoundedRectangle,
    width: Dp,
    color: Color = Color.Unspecified,
    contentAlignment: Alignment = Alignment.Center,
) {
    val currentOnClick by rememberUpdatedState(onClick)
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(width)
            .clip(shape)
            .clickable {
                currentOnClick()
            }
            .semantics { role = Role.Tab },
        contentAlignment = contentAlignment,
    ) {
        Text(
            text = text,
            color = color,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            fontSize = MiuixTheme.textStyles.body2.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Base configuration for TabRow implementations.
 */
private data class TabRowConfig(
    val tabWidth: Dp,
    val shape: RoundedRectangle,
    val listState: androidx.compose.foundation.lazy.LazyListState,
)

/**
 * Prepare common TabRow configuration.
 * @param lazyRowAvailableWidth The actual width available for the LazyRow's content (tabs + inter-tab spacing).
 */
@Composable
private fun rememberTabRowConfig(
    tabs: List<String>,
    minWidth: Dp,
    maxWidth: Dp,
    cornerRadius: Dp,
    spacing: Dp,
    lazyRowAvailableWidth: Dp,
): TabRowConfig {
    val listState = rememberLazyListState()
    val tabWidth = remember(tabs.size, minWidth, maxWidth, lazyRowAvailableWidth, spacing) {
        calculateTabWidth(tabs.size, minWidth, maxWidth, spacing, lazyRowAvailableWidth)
    }
    val shape = remember(cornerRadius) { RoundedRectangle(cornerRadius) }

    return TabRowConfig(tabWidth, shape, listState)
}

private fun calculateTabWidth(
    tabCount: Int,
    minWidth: Dp,
    maxWidth: Dp,
    spacing: Dp,
    availableWidth: Dp,
): Dp {
    if (tabCount == 0) return minWidth

    val totalSpacing = if (tabCount > 1) (tabCount - 1) * spacing else 0.dp
    val contentWidth = availableWidth - totalSpacing

    return if (contentWidth <= 0.dp) {
        minWidth
    } else {
        val idealWidth = contentWidth / tabCount
        when {
            idealWidth < minWidth -> minWidth

            idealWidth > maxWidth -> {
                val totalMaxWidth = maxWidth * tabCount + totalSpacing
                if (totalMaxWidth < availableWidth) {
                    idealWidth
                } else {
                    maxWidth
                }
            }

            else -> idealWidth
        }
    }
}

object TabRowDefaults {

    /**
     * The default height of the [TabRow].
     */
    val TabRowHeight = 42.dp

    /**
     * The default height of the [TabRowWithContour].
     */
    val TabRowWithContourHeight = 45.dp

    /**
     * The default corner radius of the [TabRow].
     */
    val TabRowCornerRadius = 12.dp

    /**
     * The default corner radius of the [TabRowWithContour].
     */
    val TabRowWithContourCornerRadius = 8.dp

    /**
     * The default minimum width of the [TabRow].
     */
    val TabRowMinWidth = 76.dp

    /**
     * The default minimum width of the [TabRowWithContour].
     */
    val TabRowWithContourMinWidth = 62.dp

    /**
     * The default maximum width of the tab in [TabRow].
     */
    val TabRowMaxWidth = 98.dp

    /**
     * The default minimum width of the tab in [TabRowWithContour].
     */
    val TabRowWithContourMaxWidth = 84.dp

    /**
     * The default colors for the [TabRow] and [TabRowWithContour].
     */
    @Composable
    fun tabRowColors(
        backgroundColor: Color = MiuixTheme.colorScheme.surface,
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        selectedBackgroundColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        selectedContentColor: Color = MiuixTheme.colorScheme.onBackground,
    ): TabRowColors = TabRowColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        selectedBackgroundColor = selectedBackgroundColor,
        selectedContentColor = selectedContentColor,
    )
}

@Immutable
data class TabRowColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val selectedBackgroundColor: Color,
    private val selectedContentColor: Color,
) {
    @Stable
    internal fun backgroundColor(selected: Boolean): Color = if (selected) selectedBackgroundColor else backgroundColor

    @Stable
    internal fun contentColor(selected: Boolean): Color = if (selected) selectedContentColor else contentColor
}
