// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import top.yukonga.miuix.kmp.anim.folmeSpring
import top.yukonga.miuix.kmp.basic.NavigationRailState.Companion.Saver
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
 *   the rail expandable, or null (default) for the classic non-expandable rail.
 * @param header The header of the [NavigationRail], usually a [FloatingActionButton] or a logo.
 * @param color The color of the [NavigationRail].
 * @param showDivider Whether to show the divider line between the [NavigationRail] and the content.
 * @param defaultWindowInsetsPadding whether to apply default window insets padding to the [NavigationRail].
 * @param minWidth The minimum width of the [NavigationRail], used for the collapsed state.
 * @param expandedWidth The width of the [NavigationRail] when [state] is expanded.
 * @param mode The mode for displaying items in the [NavigationRail]. It can show icons, text or both.
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
    mode: NavigationRailDisplayMode = NavigationRailDisplayMode.IconAndText,
    content: @Composable ColumnScope.() -> Unit,
) {
    val isExpanded = state?.isExpanded == true
    val animatedWidth by animateDpAsState(
        targetValue = if (isExpanded) expandedWidth else minWidth,
        animationSpec = folmeSpring(damping = 1f, response = 0.35f),
        label = "navigationRailWidth",
    )
    // Continuous 0..1 expansion fraction that items morph off; null when the rail is not expandable.
    val expandProgress = if (expandedWidth > minWidth) {
        ((animatedWidth - minWidth) / (expandedWidth - minWidth)).coerceIn(0f, 1f)
    } else if (isExpanded) {
        1f
    } else {
        0f
    }
    Row(
        modifier = modifier
            .fillMaxHeight()
            .then(
                if (defaultWindowInsetsPadding) {
                    Modifier
                        .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Start))
                        .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Start))
                } else {
                    Modifier
                },
            )
            .background(color),
    ) {
        Column(
            modifier = Modifier
                .width(animatedWidth)
                .fillMaxHeight()
                .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Vertical))
                .verticalScroll(rememberScrollState())
                .selectableGroup()
                .padding(vertical = NavigationRailDefaults.VerticalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            if (state != null) {
                NavigationRailExpandToggle(state = state)
                Spacer(modifier = Modifier.height(NavigationRailDefaults.HeaderSpacing))
            }
            if (header != null) {
                header()
                Spacer(modifier = Modifier.height(NavigationRailDefaults.HeaderSpacing))
            }
            CompositionLocalProvider(
                LocalNavigationRailDisplayMode provides mode,
                LocalNavigationRailExpandProgress provides (if (state != null) expandProgress else null),
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
 * The built-in expand/collapse toggle button at the top of an expandable [NavigationRail]. It is
 * anchored to the start and sized so its centered icon lands on the same baseline as the item icons.
 */
@Composable
private fun NavigationRailExpandToggle(
    state: NavigationRailState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = NavigationRailDefaults.ExpandedItemHorizontalMargin),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { state.toggle() },
            minWidth = NavigationRailDefaults.IconSize + NavigationRailDefaults.ExpandedItemContentHorizontalPadding * 2,
            minHeight = NavigationRailDefaults.IconSize + NavigationRailDefaults.ExpandedItemContentVerticalPadding * 2,
        ) {
            Icon(
                modifier = Modifier.size(NavigationRailDefaults.IconSize),
                imageVector = MiuixIcons.Basic.Sidebar,
                contentDescription = if (state.isExpanded) "Collapse navigation rail" else "Expand navigation rail",
                tint = MiuixTheme.colorScheme.onSurfaceContainer,
            )
        }
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
    val mode = LocalNavigationRailDisplayMode.current

    val expandProgress = LocalNavigationRailExpandProgress.current
    if (expandProgress != null) {
        NavigationRailItemExpandable(
            progress = expandProgress,
            selected = selected,
            onClick = onClick,
            icon = icon,
            label = label,
            enabled = enabled,
            tint = tint,
            fontWeight = fontWeight,
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
            .animateContentSize()
            .then(if (badge != null) Modifier.badgeBounds() else Modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (mode) {
            NavigationRailDisplayMode.IconAndText -> {
                NavigationItemIcon(badge = badge, modifier = Modifier) { iconModifier ->
                    Image(
                        modifier = iconModifier.size(NavigationRailDefaults.IconSize),
                        imageVector = icon,
                        // Decorative: the adjacent label already names the item; avoids TalkBack double-read.
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(tint),
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

            NavigationRailDisplayMode.IconWithSelectedLabel -> {
                NavigationItemIcon(badge = badge, modifier = Modifier) { iconModifier ->
                    Image(
                        modifier = iconModifier.size(NavigationRailDefaults.IconSize),
                        imageVector = icon,
                        // The label only exists in the tree when selected; name the icon otherwise to avoid double-read.
                        contentDescription = if (selected) null else label,
                        colorFilter = ColorFilter.tint(tint),
                    )
                }
                if (selected) {
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

            else -> {
                NavigationItemIcon(badge = badge, modifier = Modifier) { iconModifier ->
                    Image(
                        modifier = iconModifier.size(NavigationRailDefaults.IconSize),
                        imageVector = icon,
                        contentDescription = label,
                        colorFilter = ColorFilter.tint(tint),
                    )
                }
            }
        }
    }
}

/**
 * The morphing layout for a [NavigationRailItem] in an expandable [NavigationRail], driven by the
 * continuous [progress] fraction (0 collapsed, 1 expanded). A single [Layout] interpolates between
 * icon-above-label (collapsed) and icon-before-label (expanded); the icon keeps a fixed start
 * baseline and the selection indicator hugs the icon while collapsed, growing to the full item
 * once expanded.
 */
@Composable
private fun NavigationRailItemExpandable(
    progress: Float,
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    enabled: Boolean,
    tint: Color,
    fontWeight: FontWeight,
    interactionSource: MutableInteractionSource,
    badge: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val fraction = progress.coerceIn(0f, 1f)
    val collapsedSp = NavigationRailDefaults.LabelFontSize.value
    val expandedSp = NavigationRailDefaults.ExpandedLabelFontSize.value
    val labelFontSize = (collapsedSp + (expandedSp - collapsedSp) * fraction).sp
    val indicatorColor = MiuixTheme.colorScheme.surfaceContainerHigh
    val indication = LocalIndication.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = NavigationRailDefaults.ExpandedItemHorizontalMargin)
            .clip(RoundedCornerShape(NavigationRailDefaults.ExpandedItemCornerRadius))
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
                        .clip(RoundedCornerShape(NavigationRailDefaults.ExpandedItemCornerRadius))
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
                NavigationItemIcon(badge = badge, modifier = Modifier) { iconModifier ->
                    Image(
                        modifier = iconModifier.size(NavigationRailDefaults.IconSize),
                        imageVector = icon,
                        // Decorative: the always-present label names the item; avoids double-read.
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(tint),
                    )
                }
                Text(
                    text = label,
                    color = tint,
                    fontSize = labelFontSize,
                    fontWeight = fontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
        ) { measurables, constraints ->
            val width = constraints.maxWidth
            val iconPlaceable = measurables[1].measure(constraints.copy(minWidth = 0, minHeight = 0))
            val labelPlaceable = measurables[2].measure(
                Constraints(maxWidth = width.coerceAtLeast(0), maxHeight = constraints.maxHeight),
            )
            val iconW = iconPlaceable.width
            val iconH = iconPlaceable.height
            val labelH = labelPlaceable.height

            val topPad = NavigationRailDefaults.ItemVerticalPadding.roundToPx()
            val collapsedSpacing = NavigationRailDefaults.IconTextSpacing.roundToPx()
            val collapsedIndVPad = NavigationRailDefaults.CollapsedIndicatorVerticalPadding.roundToPx()
            val expandedVPad = NavigationRailDefaults.ExpandedItemContentVerticalPadding.roundToPx()
            val leadingInset = NavigationRailDefaults.ExpandedItemContentHorizontalPadding.roundToPx()
            val expandedSpacing = NavigationRailDefaults.ExpandedItemIconTextSpacing.roundToPx()

            val collapsedHeight = topPad + iconH + collapsedIndVPad * 2 + collapsedSpacing + labelH + topPad
            val expandedHeight = expandedVPad * 2 + maxOf(iconH, labelH)
            val height = lerp(collapsedHeight, expandedHeight, fraction)

            // Icon keeps a fixed start inset (which also centers it while collapsed); only its Y morphs.
            val iconX = leadingInset
            val iconCenterX = iconX + iconW / 2
            val iconY = lerp(topPad + collapsedIndVPad, (height - iconH) / 2, fraction)

            // Indicator hugs the icon while collapsed and grows to wrap the whole item once expanded.
            val indicatorH = lerp(iconH + collapsedIndVPad * 2, iconH + expandedVPad * 2, fraction)
            val indicatorW = lerp(iconW + leadingInset * 2, width, fraction)
            val indicatorY = iconY - (indicatorH - iconH) / 2
            val indicatorPlaceable = measurables[0].measure(Constraints.fixed(indicatorW, indicatorH))

            // Label sits centered under the icon while collapsed and at its end while expanded.
            val collapsedLabelY = topPad + iconH + collapsedIndVPad * 2 + collapsedSpacing
            val labelX = lerp(iconCenterX - labelPlaceable.width / 2, leadingInset + iconW + expandedSpacing, fraction)
            val labelY = lerp(collapsedLabelY, (height - labelH) / 2, fraction)

            layout(width, height) {
                indicatorPlaceable.place(0, indicatorY)
                iconPlaceable.place(iconX, iconY)
                labelPlaceable.place(labelX, labelY)
            }
        }
    }
}

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
}

/**
 * Defines the display mode for items in a [NavigationRail].
 *
 * This controls whether to show both icon and text, icon only,
 * or icon with text only when selected.
 */
enum class NavigationRailDisplayMode {
    /** Show both icon and text. */
    IconAndText,

    /** Show icon only. */
    IconOnly,

    /** Show icon always, show text only when selected. */
    IconWithSelectedLabel,
}

/**
 * A composition local to control the display mode for items in a [NavigationRail].
 */
val LocalNavigationRailDisplayMode = compositionLocalOf { NavigationRailDisplayMode.IconAndText }

/**
 * The hosting [NavigationRail]'s expansion fraction (0..1) used by items to morph, or null when the
 * rail is not expandable.
 */
internal val LocalNavigationRailExpandProgress = compositionLocalOf<Float?> { null }

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
            restore = { NavigationRailState(NavigationRailValue.entries[it]) },
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
): NavigationRailState = rememberSaveable(saver = Saver) {
    NavigationRailState(initialValue)
}
