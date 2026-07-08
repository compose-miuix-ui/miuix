// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowRight
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A single segment in a [BreadcrumbBar]. Each item carries a [path] segment used to reconstruct the
 * full path, and an optional [text] for display. When [text] is null, [path] is shown.
 *
 * @param path The path segment, used by [joinToPath] to reconstruct the full path.
 * @param text The display text. If null, [path] is used for display.
 */
@Stable
data class BreadcrumbItem(
    val path: String,
    val text: String? = null,
)

/**
 * A horizontal breadcrumb navigation bar with Miuix style. Displays a trail of path segments as
 * capsule-shaped items separated by arrow icons. When the content overflows the available width, it
 * scrolls horizontally instead of collapsing — matching the file-manager convention on mobile
 * devices.
 *
 * The [highlightIndex] is decoupled from the [items] list: the caller may show the full path while
 * highlighting any segment (e.g. the current directory or a parent the user navigated back to).
 *
 * @param items The list of [BreadcrumbItem]s to display.
 * @param onItemClick Callback invoked with the index of the clicked item.
 * @param modifier The modifier to be applied to the [BreadcrumbBar].
 * @param highlightIndex The index of the highlighted item. Defaults to the last item.
 *   Pass a negative value (e.g. `-1`) to disable highlighting and auto-scroll entirely.
 * @param enabled Whether the items are clickable. When disabled, items use the disabled color
 *   scheme but horizontal scrolling remains active.
 * @param colors The [BreadcrumbBarColors] of the [BreadcrumbBar].
 * @param insideMargin The margin inside the [BreadcrumbBar].
 * @param itemMaxWidth The maximum width of each capsule-shaped item. Text beyond this is truncated.
 * @param scrollState The [ScrollState] to be used for horizontal scrolling. If null, an internal
 *   state is created. Pass a shared state to synchronize scrolling across multiple bars.
 * @param interactionSource The [MutableInteractionSource] to be used for the items.
 * @param indication The [Indication] to be used for click interactions.
 */
@Composable
fun BreadcrumbBar(
    items: List<BreadcrumbItem>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    highlightIndex: Int = items.lastIndex,
    enabled: Boolean = true,
    colors: BreadcrumbBarColors = BreadcrumbBarDefaults.breadcrumbBarColors(),
    insideMargin: PaddingValues = BreadcrumbBarDefaults.InsideMargin,
    itemMaxWidth: Dp = BreadcrumbBarDefaults.ItemMaxWidth,
    scrollState: ScrollState? = null,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = LocalIndication.current,
) {
    val currentOnItemClick by rememberUpdatedState(onItemClick)
    val resolvedScrollState = scrollState ?: rememberScrollState()
    val hasHighlight = highlightIndex >= 0
    var highlightItemX by remember { mutableFloatStateOf(0f) }
    var highlightItemWidth by remember { mutableFloatStateOf(0f) }
    var positioned by remember { mutableStateOf(false) }
    var lastSettledHighlightIndex by remember { mutableIntStateOf(-1) }

    LaunchedEffect(highlightIndex) {
        // Wait for the newly highlighted segment to be positioned before scrolling.
        positioned = false
    }

    LaunchedEffect(positioned, highlightItemX, highlightItemWidth) {
        if (!hasHighlight) return@LaunchedEffect
        if (!positioned) return@LaunchedEffect
        if (highlightItemWidth <= 0f) return@LaunchedEffect

        val viewportWidth = resolvedScrollState.viewportSize.toFloat()
        if (viewportWidth <= 0f) return@LaunchedEffect

        val targetScroll = (highlightItemX - (viewportWidth - highlightItemWidth) / 2f)
            .coerceIn(0f, resolvedScrollState.maxValue.toFloat())
            .toInt()

        if (lastSettledHighlightIndex < 0 || lastSettledHighlightIndex == highlightIndex) {
            resolvedScrollState.scrollTo(targetScroll)
        } else {
            resolvedScrollState.animateScrollTo(targetScroll)
        }
        lastSettledHighlightIndex = highlightIndex
    }

    Row(
        modifier = modifier
            .horizontalScroll(resolvedScrollState)
            .padding(insideMargin),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, item ->
            if (index > 0) {
                BreadcrumbSeparator(
                    color = if (enabled) colors.separatorColor else colors.disabledColor,
                )
            }
            BreadcrumbSegment(
                text = item.text ?: item.path,
                highlighted = hasHighlight && index == highlightIndex,
                enabled = enabled,
                colors = colors,
                itemMaxWidth = itemMaxWidth,
                interactionSource = interactionSource,
                indication = indication,
                onPositioned = if (hasHighlight && index == highlightIndex) {
                    { x, width ->
                        highlightItemX = x + resolvedScrollState.value
                        highlightItemWidth = width
                        if (!positioned) positioned = true
                    }
                } else {
                    null
                },
                onClick = { currentOnItemClick(index) },
            )
        }
    }
}

@Composable
private fun BreadcrumbSegment(
    text: String,
    highlighted: Boolean,
    enabled: Boolean,
    colors: BreadcrumbBarColors,
    itemMaxWidth: Dp,
    interactionSource: MutableInteractionSource?,
    indication: Indication?,
    onPositioned: ((x: Float, width: Float) -> Unit)?,
    onClick: () -> Unit,
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val backgroundColor: Color
    val textColor: Color
    if (!enabled) {
        backgroundColor = colors.disabledBackgroundColor
        textColor = colors.disabledColor
    } else if (highlighted) {
        backgroundColor = colors.highlightBackgroundColor
        textColor = colors.highlightColor
    } else {
        backgroundColor = colors.backgroundColor
        textColor = colors.color
    }
    Box(
        modifier = Modifier
            .height(BreadcrumbBarDefaults.ItemHeight)
            .widthIn(max = itemMaxWidth)
            .then(
                if (onPositioned != null) {
                    Modifier.onGloballyPositioned { coordinates ->
                        val pos = coordinates.positionInParent()
                        onPositioned(pos.x, coordinates.size.width.toFloat())
                    }
                } else {
                    Modifier
                },
            )
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = interactionSource,
                indication = indication,
                enabled = enabled,
                onClickLabel = null,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(horizontal = BreadcrumbBarDefaults.ItemHorizontalPadding),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = MiuixTheme.textStyles.body2.fontSize,
            fontWeight = if (highlighted) FontWeight.Medium else FontWeight.Normal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun RowScope.BreadcrumbSeparator(
    color: Color,
) {
    val layoutDirection = LocalLayoutDirection.current
    Image(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(width = 10.dp, height = 16.dp)
            .graphicsLayer {
                scaleX = if (layoutDirection == LayoutDirection.Rtl) -1f else 1f
            }
            .align(Alignment.CenterVertically),
        imageVector = MiuixIcons.Basic.ArrowRight,
        contentDescription = null,
        colorFilter = ColorFilter.tint(color),
    )
}

object BreadcrumbBarDefaults {

    /**
     * The default margin inside the [BreadcrumbBar].
     */
    val InsideMargin = PaddingValues(horizontal = 12.dp, vertical = 8.dp)

    /**
     * The height of each capsule-shaped item.
     */
    val ItemHeight = 32.dp

    /**
     * The horizontal padding of each capsule-shaped item. Equals the corner radius so that the left
     * and right ends form complete semicircles.
     */
    val ItemHorizontalPadding = 10.dp

    /**
     * The default maximum width of each capsule-shaped item. Prevents a single segment with a long
     * name from stretching the bar excessively; text beyond this width is truncated with ellipsis.
     */
    val ItemMaxWidth = 160.dp

    /**
     * The default [BreadcrumbBarColors] for all breadcrumb bars.
     *
     * The background colors are derived from their text colors via alpha, so they automatically
     * adapt to light/dark themes without hardcoding theme-specific values.
     */
    @Composable
    fun breadcrumbBarColors(
        color: Color = MiuixTheme.colorScheme.onBackground.copy(alpha = 0.55f),
        highlightColor: Color = MiuixTheme.colorScheme.primary,
        disabledColor: Color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
        separatorColor: Color = MiuixTheme.colorScheme.onSurfaceVariantActions,
        backgroundColor: Color = MiuixTheme.colorScheme.onBackground.copy(alpha = 0.1f),
        highlightBackgroundColor: Color = MiuixTheme.colorScheme.primary.copy(alpha = 0.2f),
        disabledBackgroundColor: Color = MiuixTheme.colorScheme.disabledSecondaryVariant,
    ): BreadcrumbBarColors = remember(
        color,
        highlightColor,
        disabledColor,
        separatorColor,
        backgroundColor,
        highlightBackgroundColor,
        disabledBackgroundColor,
    ) {
        BreadcrumbBarColors(
            color = color,
            highlightColor = highlightColor,
            disabledColor = disabledColor,
            separatorColor = separatorColor,
            backgroundColor = backgroundColor,
            highlightBackgroundColor = highlightBackgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
        )
    }
}

@Immutable
data class BreadcrumbBarColors(
    val color: Color,
    val highlightColor: Color,
    val disabledColor: Color,
    val separatorColor: Color,
    val backgroundColor: Color,
    val highlightBackgroundColor: Color,
    val disabledBackgroundColor: Color,
)

/**
 * Joins the [path] segments of all [BreadcrumbItem]s in this list into a single path string using
 * the given [separator].
 *
 * @param separator The separator placed between path segments. Defaults to `"/"`.
 * @return The full path string.
 */
fun List<BreadcrumbItem>.joinToPath(separator: String = "/"): String = joinToString(separator = separator) { it.path }
