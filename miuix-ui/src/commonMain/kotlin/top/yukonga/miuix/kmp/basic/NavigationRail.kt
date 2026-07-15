// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastFirst
import androidx.compose.ui.util.fastRoundToInt
import androidx.compose.ui.util.lerp
import top.yukonga.miuix.kmp.anim.folmeSpring
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Sidebar
import top.yukonga.miuix.kmp.squircle.squircleBackground
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A [NavigationRail] that is suitable for wide screens.
 *
 * When a non-null [state] is provided, the rail becomes expandable: a built-in toggle button is
 * shown at the top (aligned to the start when expanded), the rail animates between [minWidth] and
 * [expandedWidth], and its items switch to a horizontal icon-and-label layout with a highlighted
 * pill behind the selected item. When [state] is null the rail keeps its classic collapsed layout
 * with no toggle button.
 *
 * @param modifier The modifier to be applied to the [NavigationRail].
 * @param state Controls the expanded/collapsed state; pass a [rememberNavigationRailState] to make
 *   the rail expandable, or null (default) for the classic non-expandable rail. Keep the same
 *   nullness across window size changes and drive it via [NavigationRailState.expand] /
 *   [NavigationRailState.collapse]; flipping between null and non-null at runtime swaps the item
 *   layout in a single frame without animation.
 * @param header The header of the [NavigationRail], usually a [FloatingActionButton] or a logo.
 * @param color The color of the [NavigationRail].
 * @param showDivider Whether to show the divider line between the [NavigationRail] and the content.
 * @param defaultWindowInsetsPadding whether to apply default window insets padding to the [NavigationRail].
 * @param minWidth The minimum width of the [NavigationRail], used for the collapsed state.
 * @param expandedWidth The width of the [NavigationRail] when [state] is expanded.
 * @param expandContentDescription The accessible description of the built-in toggle while the rail
 *   is collapsed; override it to localize the announcement.
 * @param collapseContentDescription The accessible description of the built-in toggle while the
 *   rail is expanded; override it to localize the announcement.
 * @param scrollState The [ScrollState] of the rail's scrollable content column.
 * @param content The content of the [NavigationRail], usually [NavigationRailItem]s.
 */
@Composable
fun NavigationRail(
    modifier: Modifier = Modifier,
    state: NavigationRailState? = null,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    color: Color = MiuixTheme.colorScheme.surface,
    showDivider: Boolean = true,
    defaultWindowInsetsPadding: Boolean = true,
    minWidth: Dp = NavigationRailDefaults.MinWidth,
    expandedWidth: Dp = NavigationRailDefaults.ExpandedWidth,
    expandContentDescription: String = NavigationRailDefaults.ExpandContentDescription,
    collapseContentDescription: String = NavigationRailDefaults.CollapseContentDescription,
    scrollState: ScrollState = rememberScrollState(),
    content: @Composable ColumnScope.() -> Unit,
) {
    val isExpanded = state?.isExpanded == true
    val hasState = state != null
    // Guard against a misconfigured expandedWidth narrower than the collapsed minWidth.
    val effectiveExpandedWidth = expandedWidth.coerceAtLeast(minWidth)
    // Single 0..1 timeline that both the rail width and the item morph derive from. It is read
    // only inside layout-phase blocks (and the small label leaf), so spring frames re-layout the
    // rail without recomposing it or its items.
    val expandProgress = animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0f,
        animationSpec = RailExpandSpring,
        label = "navigationRailExpandProgress",
    )
    val expandInfo = remember(expandProgress, minWidth) {
        NavigationRailExpandInfo(progress = expandProgress, collapsedWidth = minWidth)
    }
    Row(
        // Background is painted before the insets padding so the rail color extends under the
        // start cutout / side navigation bar instead of exposing the window background.
        modifier = modifier
            .fillMaxHeight()
            .background(color)
            .then(
                if (defaultWindowInsetsPadding) {
                    Modifier
                        .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Start))
                        .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Start))
                } else {
                    Modifier
                },
            ),
    ) {
        Column(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val targetWidth = if (hasState) {
                        lerp(minWidth, effectiveExpandedWidth, expandProgress.value.coerceIn(0f, 1f))
                    } else {
                        minWidth
                    }
                    val width = targetWidth.roundToPx().coerceIn(constraints.minWidth, constraints.maxWidth)
                    val placeable = measurable.measure(constraints.copy(minWidth = width, maxWidth = width))
                    layout(width, placeable.height) { placeable.placeRelative(0, 0) }
                }
                .fillMaxHeight()
                .then(
                    if (defaultWindowInsetsPadding) {
                        Modifier
                            .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Top))
                            .verticalScroll(scrollState)
                            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Bottom))
                    } else {
                        Modifier.verticalScroll(scrollState)
                    },
                )
                .selectableGroup()
                .padding(vertical = NavigationRailDefaults.VerticalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            if (state != null) {
                NavigationRailExpandToggle(
                    state = state,
                    info = expandInfo,
                    expandContentDescription = expandContentDescription,
                    collapseContentDescription = collapseContentDescription,
                )
                Spacer(modifier = Modifier.height(NavigationRailDefaults.HeaderSpacing))
            }
            if (header != null) {
                header()
                Spacer(modifier = Modifier.height(NavigationRailDefaults.HeaderSpacing))
            }
            CompositionLocalProvider(
                LocalNavigationRailExpandInfo provides (if (state != null) expandInfo else null),
            ) {
                content()
            }
        }
        if (showDivider) {
            VerticalDivider()
        }
    }
}

/**
 * The built-in expand/collapse toggle button at the top of an expandable [NavigationRail]. Its
 * horizontal position morphs between the collapsed rail's center and the expanded start margin
 * (coincident at the default dimensions), keeping its centered icon on the same baseline as the
 * item icons at every fraction. The progress read happens in the layout phase, so spring frames
 * re-place the button without recomposing it.
 */
@Composable
private fun NavigationRailExpandToggle(
    state: NavigationRailState,
    info: NavigationRailExpandInfo,
    expandContentDescription: String,
    collapseContentDescription: String,
) {
    IconButton(
        onClick = { state.toggle() },
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints.copy(minWidth = 0))
            val fraction = info.progress.value.coerceIn(0f, 1f)
            val collapsedX = (info.collapsedWidth.toPx() - placeable.width) / 2f
            val expandedX = NavigationRailDefaults.ExpandedItemHorizontalMargin.toPx()
            val x = lerp(collapsedX, expandedX, fraction).fastRoundToInt()
            layout(constraints.maxWidth, placeable.height) {
                placeable.placeRelative(x, 0)
            }
        },
        minWidth = NavigationRailDefaults.IconSize + NavigationRailDefaults.ExpandedItemContentHorizontalPadding * 2,
        minHeight = NavigationRailDefaults.IconSize + NavigationRailDefaults.ExpandedItemContentVerticalPadding * 2,
    ) {
        Icon(
            modifier = Modifier.size(NavigationRailDefaults.IconSize),
            imageVector = MiuixIcons.Basic.Sidebar,
            contentDescription = if (state.isExpanded) collapseContentDescription else expandContentDescription,
            tint = MiuixTheme.colorScheme.onSurfaceContainer,
        )
    }
}

/**
 * A [NavigationRailItem] that is suitable for [NavigationRail].
 *
 * @param selected Whether the item is selected.
 * @param onClick The callback when the item is clicked.
 * @param icon The icon of the item.
 * @param label The label of the item.
 * @param modifier The modifier to be applied to the [NavigationRailItem].
 * @param enabled Whether the item is enabled.
 * @param badge The optional badge shown on the item's icon, typically a [Badge].
 */
@Composable
fun NavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    badge: (@Composable () -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val tint = MiuixTheme.colorScheme.onSurfaceContainer
    val fontWeight = FontWeight.Medium
    val iconColorFilter = remember(tint) { ColorFilter.tint(tint) }

    val expandInfo = LocalNavigationRailExpandInfo.current
    if (expandInfo != null) {
        NavigationRailItemExpandable(
            info = expandInfo,
            selected = selected,
            onClick = onClick,
            icon = icon,
            label = label,
            enabled = enabled,
            tint = tint,
            fontWeight = fontWeight,
            iconColorFilter = iconColorFilter,
            interactionSource = interactionSource,
            badge = badge,
            modifier = modifier,
        )
        return
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = LocalIndication.current,
            )
            .padding(vertical = NavigationRailDefaults.ItemVerticalPadding)
            .then(if (badge != null) Modifier.badgeBounds() else Modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        NavigationItemIcon(badge = badge, modifier = Modifier) { iconModifier ->
            Image(
                modifier = iconModifier.size(NavigationRailDefaults.IconSize),
                imageVector = icon,
                // Decorative: the adjacent label already names the item; avoids TalkBack double-read.
                contentDescription = null,
                colorFilter = iconColorFilter,
            )
        }
        Spacer(modifier = Modifier.height(NavigationRailDefaults.IconTextSpacing))
        Text(
            text = label,
            color = tint,
            textAlign = TextAlign.Center,
            fontSize = NavigationRailDefaults.LabelFontSize,
            fontWeight = fontWeight,
        )
    }
}

/**
 * The morphing layout for a [NavigationRailItem] in an expandable [NavigationRail]. A single
 * [Layout] interpolates between icon-above-label (collapsed) and icon-before-label (expanded),
 * driven by the continuous fraction in [info], which is read only inside the measure pass (and by
 * the small label leaf), so spring frames re-layout the item without recomposing it. The icon
 * morphs between the collapsed rail's horizontal center and the expanded pill's start inset
 * (coincident at the default dimensions), and the selection indicator hugs the icon while
 * collapsed, growing to the full pill once expanded.
 */
@Composable
private fun NavigationRailItemExpandable(
    info: NavigationRailExpandInfo,
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    enabled: Boolean,
    tint: Color,
    fontWeight: FontWeight,
    iconColorFilter: ColorFilter,
    interactionSource: MutableInteractionSource,
    badge: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val indicatorColor = MiuixTheme.colorScheme.surfaceContainerHigh
    val indication = LocalIndication.current
    val progress = info.progress
    Box(
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null,
            )
            .then(if (badge != null) Modifier.badgeBounds() else Modifier),
    ) {
        Layout(
            modifier = Modifier.fillMaxWidth(),
            content = {
                // Indicator carries the selection fill and press overlay, so feedback tracks the
                // icon (not the label) while collapsed.
                Box(
                    modifier = Modifier
                        .layoutId("indicator")
                        .clip(ExpandedItemShape)
                        .then(
                            if (selected) {
                                Modifier.squircleBackground(
                                    color = indicatorColor,
                                    cornerRadius = NavigationRailDefaults.ExpandedItemCornerRadius,
                                )
                            } else {
                                Modifier
                            },
                        )
                        .indication(interactionSource, indication),
                )
                NavigationItemIcon(badge = badge, modifier = Modifier.layoutId("icon")) { iconModifier ->
                    Image(
                        modifier = iconModifier.size(NavigationRailDefaults.IconSize),
                        imageVector = icon,
                        // Decorative: the always-present label names the item; avoids double-read.
                        contentDescription = null,
                        colorFilter = iconColorFilter,
                    )
                }
                NavigationRailItemLabel(
                    info = info,
                    label = label,
                    tint = tint,
                    fontWeight = fontWeight,
                    modifier = Modifier.layoutId("label"),
                )
            },
        ) { measurables, constraints ->
            // Reading the fraction here (not in composition) makes spring frames measure-only.
            val fraction = progress.value.coerceIn(0f, 1f)
            val width = constraints.maxWidth
            val marginPx = NavigationRailDefaults.ExpandedItemHorizontalMargin.roundToPx()
            val leadingInset = NavigationRailDefaults.ExpandedItemContentHorizontalPadding.roundToPx()
            val expandedSpacing = NavigationRailDefaults.ExpandedItemIconTextSpacing.roundToPx()

            val iconPlaceable = measurables.fastFirst { it.layoutId == "icon" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))
            val iconW = iconPlaceable.width
            val iconH = iconPlaceable.height

            // Collapsed, the label may use the full item width (matching the classic rail);
            // expanded, it is capped to the pill's content area with a trailing inset symmetric to
            // the leading one, so long labels ellipsize instead of hard-clipping.
            val expandedLabelMax = (width - 2 * marginPx - 2 * leadingInset - iconW - expandedSpacing).coerceAtLeast(0)
            val labelPlaceable = measurables.fastFirst { it.layoutId == "label" }.measure(
                Constraints(maxWidth = lerp(width, expandedLabelMax, fraction), maxHeight = constraints.maxHeight),
            )
            val labelW = labelPlaceable.width
            val labelH = labelPlaceable.height

            val topPad = NavigationRailDefaults.ItemVerticalPadding.roundToPx()
            val collapsedSpacing = NavigationRailDefaults.IconTextSpacing.roundToPx()
            val collapsedIndVPad = NavigationRailDefaults.CollapsedIndicatorVerticalPadding.roundToPx()
            val expandedVPad = NavigationRailDefaults.ExpandedItemContentVerticalPadding.roundToPx()

            // Geometry is interpolated in float and rounded exactly once per coordinate to avoid
            // per-frame parity wobble between independently rounded values.
            val collapsedHeight = topPad + iconH + collapsedIndVPad * 2 + collapsedSpacing + labelH + topPad
            val expandedHeight = expandedVPad * 2 + maxOf(iconH, labelH)
            val height = lerp(collapsedHeight.toFloat(), expandedHeight.toFloat(), fraction).fastRoundToInt()

            // Icon morphs from the collapsed rail's center to the pill's start inset (coincident
            // at the default dimensions, so the start baseline stays fixed there).
            val collapsedIconX = (info.collapsedWidth.toPx() - iconW) / 2f
            val expandedIconX = (marginPx + leadingInset).toFloat()
            val iconX = lerp(collapsedIconX, expandedIconX, fraction).fastRoundToInt()
            val iconY = lerp((topPad + collapsedIndVPad).toFloat(), (height - iconH) / 2f, fraction).fastRoundToInt()

            // Indicator is built symmetrically around the icon's resolved rect so their centers
            // stay locked at every fraction; it hugs the icon while collapsed and grows to the
            // full pill once expanded.
            val indicatorVPad = lerp(collapsedIndVPad.toFloat(), expandedVPad.toFloat(), fraction).fastRoundToInt()
            val indicatorH = iconH + indicatorVPad * 2
            val indicatorY = iconY - indicatorVPad
            val indicatorX = lerp(collapsedIconX - leadingInset, marginPx.toFloat(), fraction).fastRoundToInt()
            val indicatorW = lerp((iconW + leadingInset * 2).toFloat(), (width - marginPx * 2).toFloat(), fraction)
                .fastRoundToInt()
                .coerceAtLeast(0)
            val indicatorPlaceable = measurables.fastFirst { it.layoutId == "indicator" }
                .measure(Constraints.fixed(indicatorW, indicatorH))

            // Label sits centered under the icon while collapsed and at its end while expanded.
            val collapsedLabelY = topPad + iconH + collapsedIndVPad * 2 + collapsedSpacing
            val collapsedLabelX = collapsedIconX + iconW / 2f - labelW / 2f
            val expandedLabelX = (marginPx + leadingInset + iconW + expandedSpacing).toFloat()
            val labelX = lerp(collapsedLabelX, expandedLabelX, fraction).fastRoundToInt().coerceAtLeast(0)
            val labelY = lerp(collapsedLabelY.toFloat(), (height - labelH) / 2f, fraction).fastRoundToInt()

            layout(width, height) {
                indicatorPlaceable.placeRelative(indicatorX, indicatorY)
                iconPlaceable.placeRelative(iconX, iconY)
                labelPlaceable.placeRelative(labelX, labelY)
            }
        }
    }
}

/**
 * The morphing label of an expandable [NavigationRailItem]. Font size is a composition-phase
 * input, so the per-frame progress read is confined to this small leaf: only it and the
 * underlying [Text] restart while the rail animates.
 */
@Composable
private fun NavigationRailItemLabel(
    info: NavigationRailExpandInfo,
    label: String,
    tint: Color,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier,
) {
    val fraction = info.progress.value.coerceIn(0f, 1f)
    val collapsedSp = NavigationRailDefaults.LabelFontSize.value
    val expandedSp = NavigationRailDefaults.ExpandedLabelFontSize.value
    Text(
        text = label,
        modifier = modifier,
        color = tint,
        fontSize = lerp(collapsedSp, expandedSp, fraction).sp,
        fontWeight = fontWeight,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

/** Spring for the expand/collapse progress; the threshold trims the invisible settle tail. */
private val RailExpandSpring: SpringSpec<Float> =
    folmeSpring(damping = 1f, response = 0.35f, visibilityThreshold = 0.001f)

/** Shared clip shape of the expanded item pill and its selection indicator. */
private val ExpandedItemShape = RoundedCornerShape(NavigationRailDefaults.ExpandedItemCornerRadius)

/** Contains default values used by [NavigationRail] and [NavigationRailItem]. */
object NavigationRailDefaults {
    /** The default minimum width of the [NavigationRail], used for the collapsed state. */
    val MinWidth = 80.dp

    /** The default width of the [NavigationRail] when expanded. */
    val ExpandedWidth = 240.dp

    /** The default vertical padding of the [NavigationRail] content. */
    val VerticalPadding = 24.dp

    /** The default spacing after the header. */
    val HeaderSpacing = 24.dp

    /** The default icon size. */
    val IconSize = 28.dp

    /** The default spacing between icon and text. */
    val IconTextSpacing = 4.dp

    /** The default vertical padding for each item. */
    val ItemVerticalPadding = 12.dp

    /** The default label font size. */
    val LabelFontSize = 12.sp

    /** The label font size for items when the [NavigationRail] is expanded. */
    val ExpandedLabelFontSize = 16.sp

    /** The horizontal margin between an expanded item's pill and the rail edges. */
    val ExpandedItemHorizontalMargin = 12.dp

    /** The corner radius of the highlighted pill behind a selected expanded item. */
    val ExpandedItemCornerRadius = 16.dp

    /** The vertical padding of a collapsed item's selection indicator around its icon. */
    val CollapsedIndicatorVerticalPadding = 4.dp

    /** The horizontal content padding inside an expanded item's pill; keeps the icon on the collapsed baseline. */
    val ExpandedItemContentHorizontalPadding = 14.dp

    /** The vertical content padding inside an expanded item's pill. */
    val ExpandedItemContentVerticalPadding = 14.dp

    /** The spacing between the icon and label of an expanded item. */
    val ExpandedItemIconTextSpacing = 16.dp

    /** The default content description of the built-in toggle while the rail is collapsed. */
    val ExpandContentDescription = "Expand navigation rail"

    /** The default content description of the built-in toggle while the rail is expanded. */
    val CollapseContentDescription = "Collapse navigation rail"
}

/**
 * Expansion parameters a hosting expandable [NavigationRail] shares with its items: the continuous
 * 0..1 [progress] (read inside layout-phase blocks so spring frames never recompose readers) and
 * the rail's [collapsedWidth] used to center collapsed item icons.
 */
@Stable
internal class NavigationRailExpandInfo(
    val progress: State<Float>,
    val collapsedWidth: Dp,
)

/**
 * The hosting [NavigationRail]'s expansion parameters used by items to morph, or null when the
 * rail is not expandable. The provided instance is identity-stable across animation frames.
 */
internal val LocalNavigationRailExpandInfo = compositionLocalOf<NavigationRailExpandInfo?> { null }

/**
 * The possible expansion states of a [NavigationRail].
 */
enum class NavigationRailValue {
    /** The rail is collapsed to [NavigationRailDefaults.MinWidth]. */
    Collapsed,

    /** The rail is expanded to [NavigationRailDefaults.ExpandedWidth]. */
    Expanded,
}

/**
 * A state holder that controls whether a [NavigationRail] is collapsed or expanded.
 *
 * Create it with [rememberNavigationRailState] and pass it to [NavigationRail] to make the rail
 * expandable. The width and item-layout transitions are driven declaratively by the rail from
 * [currentValue], so mutating the state via [expand], [collapse] or [toggle] is enough to animate.
 *
 * @param initialValue The initial [NavigationRailValue].
 */
@Stable
class NavigationRailState(initialValue: NavigationRailValue = NavigationRailValue.Collapsed) {
    /** The current expansion value. */
    var currentValue: NavigationRailValue by mutableStateOf(initialValue)
        private set

    /** Whether the rail is currently expanded. */
    val isExpanded: Boolean get() = currentValue == NavigationRailValue.Expanded

    /** Expands the rail. */
    fun expand() {
        currentValue = NavigationRailValue.Expanded
    }

    /** Collapses the rail. */
    fun collapse() {
        currentValue = NavigationRailValue.Collapsed
    }

    /** Toggles the rail between collapsed and expanded. */
    fun toggle() {
        currentValue = if (isExpanded) NavigationRailValue.Collapsed else NavigationRailValue.Expanded
    }

    companion object {
        /** The default [Saver] for [NavigationRailState]. */
        val Saver: Saver<NavigationRailState, Int> = Saver(
            save = { it.currentValue.ordinal },
            restore = { NavigationRailState(NavigationRailValue.entries.getOrNull(it) ?: NavigationRailValue.Collapsed) },
        )
    }
}

/**
 * Creates and remembers a [NavigationRailState] that survives configuration changes and process
 * death.
 *
 * @param initialValue The initial [NavigationRailValue]. Defaults to [NavigationRailValue.Collapsed].
 */
@Composable
fun rememberNavigationRailState(
    initialValue: NavigationRailValue = NavigationRailValue.Collapsed,
): NavigationRailState = rememberSaveable(saver = NavigationRailState.Saver) {
    NavigationRailState(initialValue)
}
