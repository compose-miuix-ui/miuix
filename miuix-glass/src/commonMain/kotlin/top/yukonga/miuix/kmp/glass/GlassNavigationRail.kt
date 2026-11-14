// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import top.yukonga.miuix.kmp.basic.BadgedBox
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Sidebar
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassEdgeStrokes
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassMaterials
import top.yukonga.miuix.kmp.material.GlassShadow
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.GlassStyle
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.roundToInt

/** Default values for [GlassNavigationRail]. */
object GlassNavigationRailDefaults {

    /** Panel width while expanded. */
    val ExpandedWidth: Dp = 250.dp

    /** Distance between the expanded panel and the window edges. */
    val Margin: Dp = 12.dp

    /** Corner radius of the expanded panel. */
    val CornerRadius: Dp = 22.dp

    /** Size of a destination icon. */
    val IconSize: Dp = 24.dp

    /** Gap between a destination's icon column and its label. */
    val IconColumnSpacing: Dp = 12.dp

    /** Width of the column that holds a destination's icon while expanded. */
    val IconColumnWidth: Dp = IconSize + IconColumnSpacing

    /** Horizontal padding inside a destination. */
    val ItemContentPadding: Dp = 14.dp

    /** Height of a destination row while collapsed. */
    val CollapsedRowHeight: Dp = 38.dp

    /** Extra height a row gains as the panel expands. */
    val ExpandedRowGrowth: Dp = 4.dp

    /** Vertical distance between two destination rows while expanded. */
    val RowPitch: Dp = 46.dp

    /** Top of a row while collapsed: the bar's own row inset. */
    val RowTop: Dp = 3.dp

    /** Top of the first row while expanded. */
    val ExpandedRowTop: Dp = RowTop + 61.dp

    /** Top of the toggle while expanded. */
    val ExpandedToggleTop: Dp = 12.dp

    /** Left edge of the toggle while collapsed, matching the bar's own leading action. */
    val CollapsedToggleStart: Dp = GlassNavigationBarDefaults.ContentPaddingHorizontal - 6.dp

    /** Left edge of the toggle while expanded. */
    val ExpandedToggleStart: Dp = 16.dp

    /** Diameter of the expand/collapse toggle while expanded. */
    val ToggleSize: Dp = 44.dp

    /** Gap between the collapsed capsule's toggle and its first destination. */
    val CollapsedToggleSpacing: Dp = 4.dp

    /** Width reserved for the collapsed capsule's toggle, outside destination hit testing. */
    val LeadingActionWidth: Dp = ToggleSize + CollapsedToggleSpacing

    /** Horizontal margin between a destination and the panel's edges. */
    val ItemHorizontalMargin: Dp = 12.dp

    /** Inset after the last destination in the collapsed capsule. */
    val CollapsedEdgePadding: Dp = 3.dp

    /**
     * Where the first destination's slot begins inside the collapsed capsule. The bar lays its
     * destinations out after its own content padding plus the leading action, so the panel's rows
     * and selection surface must collapse to that same origin or the hand-off would jump.
     */
    val CollapsedContentStart: Dp = GlassNavigationBarDefaults.ContentPaddingHorizontal +
        LeadingActionWidth

    /** Top of the rail when collapsed, relative to the safe area. */
    val CollapsedTopMargin: Dp = 6.dp

    /** The default content description of the built-in toggle while the rail is collapsed. */
    val ExpandContentDescription: String = "Expand navigation rail"

    /** The default content description of the built-in toggle while the rail is expanded. */
    val CollapseContentDescription: String = "Collapse navigation rail"

    /** The panel's rim. */
    @Composable
    fun stroke(): EdgeStroke = GlassEdgeStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /** The panel's body. */
    @Composable
    fun material(): GlassMaterial = GlassMaterials.puredThinGlass(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )
}

/**
 * A floating sidebar on glass for wide layouts: a panel at the start edge that, collapsing, travels
 * to the top centre and becomes the navigation bar itself.
 *
 * The two faces are the same navigation. Expanded, the rail lists destinations as icon and label.
 * Collapsed, it *is* a [GlassNavigationBar] — labels only, with the expand toggle in its leading
 * slot — so a tap, a horizontal drag and the selection indicator behave exactly as they do in the
 * bar, rather than being a second, invented navigation model.
 *
 * Between those two settled states the panel shrinks and rounds while every row flies from its
 * place in the expanded column into its slot in the bar's row. The morph's progress is read in
 * layout and draw blocks, so a morph frame moves rows without recomposing their contents.
 *
 * The rail is an overlay: it fills the space it is given and draws the surface inside it. Place it
 * over the page, outside the recorded backdrop, and reserve the expanded width in the page's own
 * layout if the content must stay clear of the panel.
 *
 * @param items The destinations, in order.
 * @param selectedIndex The index of the current destination.
 * @param onSelect Called with the destination the bar's own gesture selected.
 * @param backdrop The [Backdrop] supplying the content behind the glass. `null` fills the surface
 *   with the theme's surfaceContainer color instead of sampling.
 * @param modifier The modifier applied to the rail's overlay box.
 * @param expanded Whether the rail is expanded. Collapsing moves it to the top centre and settles
 *   it into the navigation bar.
 * @param onExpandedChange Called when the built-in toggle is activated. `null` hides the toggle and
 *   leaves [expanded] to the caller.
 * @param defaultWindowInsetsPadding Whether to keep the surface inside the system bars and the
 *   display cutout. Disable when the caller insets the rail itself.
 * @param expandedWidth Panel width while [expanded].
 * @param style The glass material style.
 * @param alpha Opacity multiplier for the material.
 * @param material The surface's own body when sampling a backdrop. `null` omits explicit material
 *   layers; it does not disable sampling. Ignored when [backdrop] is `null`.
 * @param stroke Optional bloom stroke along the rim.
 * @param shadow The shadow the floating surface casts. `null` removes it.
 * @param selectedColor Tint of the selected icon and label. Dynamic colour themes use primary;
 *   other themes use onSurface.
 * @param unselectedColor Tint of the unselected icons and labels, without additional dimming.
 * @param indicatorColor Fill behind the selected destination in both states.
 * @param indicatorPressedColor Fill behind the destination held down in the collapsed bar.
 * @param labelStyle The destinations' label style in both states. It also decides the width of a
 *   collapsed slot, so the capsule hugs its labels instead of splitting the width evenly.
 * @param expandContentDescription The accessible description of the toggle while collapsed.
 * @param collapseContentDescription The accessible description of the toggle while expanded.
 * @param scrollState The [ScrollState] of the expanded destination column.
 */
@Composable
fun GlassNavigationRail(
    items: List<GlassNavigationItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    expanded: Boolean = true,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    defaultWindowInsetsPadding: Boolean = true,
    expandedWidth: Dp = GlassNavigationRailDefaults.ExpandedWidth,
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    material: GlassMaterial? = GlassNavigationRailDefaults.material(),
    stroke: EdgeStroke? = GlassNavigationRailDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Float,
    selectedColor: Color = railSelectedColor(),
    unselectedColor: Color = MiuixTheme.colorScheme.onSurface,
    indicatorColor: Color = GlassNavigationBarDefaults.indicatorColor(),
    indicatorPressedColor: Color = GlassNavigationBarDefaults.indicatorPressedColor(),
    labelStyle: TextStyle = MiuixTheme.textStyles.body1,
    expandContentDescription: String = GlassNavigationRailDefaults.ExpandContentDescription,
    collapseContentDescription: String = GlassNavigationRailDefaults.CollapseContentDescription,
    scrollState: ScrollState = rememberScrollState(),
) {
    if (items.isEmpty()) return
    val index = selectedIndex.coerceIn(0, items.lastIndex)
    val density = LocalDensity.current
    val onSelectState by rememberUpdatedState(onSelect)
    val onExpandedChangeState by rememberUpdatedState(onExpandedChange)
    val hasToggle = onExpandedChangeState != null

    // One 0..1 timeline for the shape change, the rows' flights and the labels' fade.
    val progress = animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = tween(
            durationMillis = RAIL_MORPH_DURATION_MILLIS,
            easing = RailMorphEasing,
        ),
        label = "glassNavigationRailMorph",
    )
    // The collapsed face is the bar itself, so it must not appear until the morph has settled; it
    // also has to give way the instant the caller expands it, so the panel can start moving.
    var settledCollapsed by remember { mutableStateOf(!expanded) }

    val textMeasurer = rememberTextMeasurer()
    // A slot is as wide as its own label needs, so the capsule hugs its text; the bar's own
    // indicator is reserved in each slot.
    val labelWidths = remember(items, labelStyle, density) {
        items.map { item ->
            item.label?.let { with(density) { textMeasurer.measure(it, labelStyle).size.width.toDp() } } ?: 0.dp
        }
    }
    val sourceWidths = remember(labelWidths) {
        labelWidths.map { it + glassRailDestinationPadding() }
    }
    val totalSlots = sourceWidths.fold(0.dp) { sum, width -> sum + width }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .then(
                if (defaultWindowInsetsPadding) {
                    Modifier
                        .windowInsetsPadding(
                            WindowInsets.systemBars
                                .union(WindowInsets.captionBar)
                                .only(WindowInsetsSides.Vertical),
                        )
                        .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Start))
                } else {
                    Modifier
                },
            ),
    ) {
        val viewportWidth = maxWidth
        val viewportHeight = maxHeight
        val leadingActionWidth = GlassNavigationRailDefaults.LeadingActionWidth
        val capsuleTrailingPadding = GlassNavigationBarDefaults.ContentPaddingHorizontal +
            GlassNavigationRailDefaults.CollapsedEdgePadding
        // The capsule keeps the whole row on screen: it is only clamped when the labels need more
        // width than the window can give, and every slot compresses with it.
        val neededWidth = leadingActionWidth + capsuleTrailingPadding + totalSlots
        val capsuleWidth = neededWidth.coerceAtMost(
            (viewportWidth - GlassNavigationRailDefaults.Margin * 2).coerceAtLeast(0.dp),
        )
        val widthRatio = if (totalSlots > 0.dp) {
            with(density) {
                ((capsuleWidth - leadingActionWidth - capsuleTrailingPadding) / totalSlots).toFloat()
            }
        } else {
            1f
        }
        val tabWidths = remember(sourceWidths, widthRatio) { sourceWidths.map { it * widthRatio } }
        val collapsedHeight = GlassNavigationRailDefaults.CollapsedRowHeight +
            GlassNavigationBarDefaults.IndicatorPaddingVertical * 2
        val collapsedStart = (viewportWidth - capsuleWidth) / 2
        val expandedStart = GlassNavigationRailDefaults.Margin
        val collapsedTop = GlassNavigationRailDefaults.CollapsedTopMargin
        val expandedTop = GlassNavigationRailDefaults.Margin
        val expandedHeight = (viewportHeight - expandedTop - GlassNavigationRailDefaults.Margin)
            .coerceAtLeast(collapsedHeight)
        // The surface's height keeps settling after the morph reaches its end.
        val panelHeight = remember {
            Animatable(if (expanded) expandedHeight else collapsedHeight, Dp.VectorConverter)
        }

        LaunchedEffect(expanded, expandedHeight, capsuleWidth, collapsedStart) {
            panelHeight.animateTo(
                targetValue = if (expanded) expandedHeight else collapsedHeight,
                animationSpec = railHeightMotion(expanded, expandedHeight - collapsedHeight),
            )
            settledCollapsed = !expanded
        }

        // The travel is a right angle, not a diagonal: the surface first changes size and shape in
        // place at the start edge, and only then slides to the top centre (reversed on the way
        // out, where it slides back before it grows). Both segments are read off the same morph —
        // the slide owns the later half of it, so nothing happens on two independent clocks.
        // The travel lives in the first share of the morph, so it happens while the surface still
        // holds its other state: expanding slides back to the start edge and then grows; collapsing
        // shrinks in place and then slides to the top centre. Either way the path turns a corner.

        // Only a *settled* collapse hands over to the bar; expanding hands back immediately so the
        // panel can start moving on the same frame.
        if (!expanded && settledCollapsed) {
            // At rest the collapsed rail is the navigation bar: same gestures, same indicator and
            // same semantics, so dragging the capsule switches destinations exactly as it does in
            // the bottom bar. The expand toggle takes the bar's leading slot.
            GlassNavigationBar(
                items = items,
                selectedIndex = index,
                onSelect = onSelectState,
                backdrop = backdrop,
                modifier = Modifier
                    .offset(x = collapsedStart, y = collapsedTop)
                    .width(capsuleWidth),
                defaultWindowInsetsPadding = false,
                style = style,
                alpha = alpha,
                material = material,
                stroke = stroke,
                shadow = shadow,
                height = collapsedHeight,
                labelsOnly = true,
                labelStyle = labelStyle,
                destinationWidths = tabWidths,
                indicatorOverhang = 0.dp,
                contentPaddingEnd = GlassNavigationRailDefaults.CollapsedEdgePadding,
                leadingActionWidth = leadingActionWidth,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                indicatorColor = indicatorColor,
                indicatorPressedColor = indicatorPressedColor,
                leadingAction = if (hasToggle) {
                    {
                        GlassIconButton(
                            onClick = { onExpandedChangeState?.invoke(true) },
                            size = GlassNavigationRailDefaults.CollapsedRowHeight,
                            surfaceAlpha = 0f,
                            shadow = null,
                        ) {
                            Icon(
                                imageVector = MiuixIcons.Basic.Sidebar,
                                contentDescription = expandContentDescription,
                                modifier = Modifier.size(GlassNavigationRailDefaults.IconSize),
                                tint = unselectedColor,
                            )
                        }
                    }
                } else {
                    null
                },
            )
        } else {
            GlassNavigationRailPanel(
                items = items,
                selectedIndex = index,
                expanded = expanded,
                progress = { progress.value },
                tabWidths = tabWidths,
                labelWidths = labelWidths,
                collapsedWidth = capsuleWidth,
                expandedWidth = expandedWidth,
                collapsedStart = collapsedStart,
                expandedStart = expandedStart,
                collapsedTop = collapsedTop,
                expandedTop = expandedTop,
                panelHeight = { panelHeight.value },
                capsuleHeight = collapsedHeight,
                leadingActionWidth = leadingActionWidth,
                backdrop = backdrop,
                style = style,
                alpha = alpha,
                material = material,
                stroke = stroke,
                shadow = shadow,
                hasToggle = hasToggle,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                indicatorColor = indicatorColor,
                labelStyle = labelStyle,
                collapseContentDescription = collapseContentDescription,
                onToggle = { onExpandedChangeState?.invoke(!expanded) },
                onSelect = onSelectState,
                scrollState = scrollState,
            )
        }
    }
}

/** The default selected tint: primary in dynamic colour themes, onSurface elsewhere. */
@Composable
private fun railSelectedColor(): Color = if (MiuixTheme.isDynamicColor) {
    MiuixTheme.colorScheme.primary
} else {
    MiuixTheme.colorScheme.onSurface
}

/**
 * The panel side of the morph. Every row carries its own progress: it starts life as the row of the
 * collapsed bar and flies into the expanded column, so the hand-off at either end is invisible.
 */
@Composable
private fun GlassNavigationRailPanel(
    items: List<GlassNavigationItem>,
    selectedIndex: Int,
    expanded: Boolean,
    progress: () -> Float,
    tabWidths: List<Dp>,
    labelWidths: List<Dp>,
    collapsedWidth: Dp,
    expandedWidth: Dp,
    collapsedStart: Dp,
    expandedStart: Dp,
    collapsedTop: Dp,
    expandedTop: Dp,
    panelHeight: () -> Dp,
    capsuleHeight: Dp,
    leadingActionWidth: Dp,
    backdrop: Backdrop?,
    style: GlassStyle,
    alpha: Float,
    material: GlassMaterial?,
    stroke: EdgeStroke?,
    shadow: GlassShadow?,
    hasToggle: Boolean,
    selectedColor: Color,
    unselectedColor: Color,
    indicatorColor: Color,
    labelStyle: TextStyle,
    collapseContentDescription: String,
    onToggle: () -> Unit,
    onSelect: (Int) -> Unit,
    scrollState: ScrollState,
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    // The capsule's half-height and the panel's corner radius are the same value, so the shape does
    // not depend on the morph and the surface never has to recompose while it moves.
    val shape = GlassShape(GlassNavigationRailDefaults.CornerRadius)
    val rowShape = RoundedCornerShape(percent = 50)
    val itemHorizontalMargin = GlassNavigationRailDefaults.ItemHorizontalMargin
    val itemContentPadding = GlassNavigationRailDefaults.ItemContentPadding
    val iconColumnWidth = GlassNavigationRailDefaults.IconColumnWidth
    val collapsedWidthPx = with(density) { collapsedWidth.toPx() }
    val expandedWidthPx = with(density) { expandedWidth.toPx() }
    val itemHorizontalMarginPx = with(density) { itemHorizontalMargin.toPx() }
    val collapsedStartPx = with(density) { collapsedStart.toPx() }
    val expandedStartPx = with(density) { expandedStart.toPx() }
    val collapsedTopPx = with(density) { collapsedTop.toPx() }
    val expandedTopPx = with(density) { expandedTop.toPx() }
    val itemWidthPx = expandedWidthPx - itemHorizontalMarginPx * 2
    // The icon fades out as the rail collapses: the capsule is text navigation.
    val iconAlpha = animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = if (expanded) {
            tween(durationMillis = 200, easing = RailMorphEasing)
        } else {
            tween(durationMillis = 120, easing = RailMorphEasing)
        },
        label = "glassNavigationRailIconAlpha",
    )

    // The selection surface follows its own row's flight, so the chip lands with the row it belongs
    // to instead of sliding on a separate timeline.
    val selectionProgress = animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = railRowMotion(selectedIndex, expanded),
        label = "glassNavigationRailSelection",
    )

    Box(
        Modifier
            // The surface's rectangle is the morph: width from the capsule to the panel, height on
            // its own settle, and a two-segment travel that holds the start edge until the size
            // change is done. All of it is read here, so the geometry on the last frame of the
            // morph is exactly the geometry the settled bar takes over.
            .layout { measurable, constraints ->
                val fraction = progress().coerceIn(0f, 1f)
                val width = lerp(collapsedWidthPx, expandedWidthPx, fraction).roundToInt()
                val height = panelHeight().roundToPx().coerceAtLeast(0)
                val placeable = measurable.measure(Constraints.fixed(width, height))
                layout(width, height) { placeable.place(0, 0) }
            }
            .offset {
                val fraction = progress().coerceIn(0f, 1f)
                val slide = railSlideFraction(fraction)
                IntOffset(
                    x = lerp(expandedStartPx, collapsedStartPx, slide).roundToInt(),
                    y = lerp(expandedTopPx, collapsedTopPx, slide).roundToInt(),
                )
            }
            .glassPanel(
                backdrop = backdrop,
                shape = shape,
                style = style,
                alpha = alpha,
                material = material,
                stroke = stroke,
                shadow = shadow,
                shading = false,
                fallback = Modifier
                    .clip(shape)
                    .background(MiuixTheme.colorScheme.surfaceContainer),
            )
            .clip(shape)
            .selectableGroup(),
    ) {
        NavigationRailIndicator(
            selectedIndex = selectedIndex,
            rowProgress = {
                selectionProgress.value
            },
            tabWidths = tabWidths,
            itemWidthPx = itemWidthPx,
            itemHorizontalMargin = itemHorizontalMargin,
            color = indicatorColor,
        )
        items.forEachIndexed { position, item ->
            key(position, item) {
                val rowProgress = animateFloatAsState(
                    targetValue = if (expanded) 1f else 0f,
                    animationSpec = railRowMotion(position, expanded),
                    label = "glassNavigationRailRow$position",
                )
                NavigationRailRow(
                    item = item,
                    selected = position == selectedIndex,
                    position = position,
                    rowProgress = { rowProgress.value },
                    iconAlpha = { iconAlpha.value },
                    tabWidths = tabWidths,
                    labelWidths = labelWidths,
                    itemWidthPx = itemWidthPx,
                    itemHorizontalMargin = itemHorizontalMargin,
                    itemContentPadding = itemContentPadding,
                    iconColumnWidth = iconColumnWidth,
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor,
                    labelStyle = labelStyle,
                    rowShape = rowShape,
                    layoutDirection = layoutDirection,
                    onClick = { onSelect(position) },
                )
            }
        }
        if (hasToggle) {
            GlassIconButton(
                onClick = onToggle,
                modifier = Modifier
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) { placeable.place(0, 0) }
                    }
                    .offset {
                        val fraction = progress().coerceIn(0f, 1f)
                        IntOffset(
                            x = with(density) {
                                lerp(
                                    GlassNavigationRailDefaults.CollapsedToggleStart.toPx(),
                                    GlassNavigationRailDefaults.ExpandedToggleStart.toPx(),
                                    fraction,
                                ).roundToInt()
                            },
                            y = with(density) {
                                androidx.compose.ui.unit.lerp(
                                    (capsuleHeight - GlassNavigationRailDefaults.CollapsedRowHeight) / 2,
                                    GlassNavigationRailDefaults.ExpandedToggleTop,
                                    fraction,
                                ).roundToPx()
                            },
                        )
                    },
                size = GlassNavigationRailDefaults.CollapsedRowHeight,
                surfaceAlpha = 0f,
                shadow = null,
            ) {
                Icon(
                    imageVector = MiuixIcons.Basic.Sidebar,
                    contentDescription = collapseContentDescription,
                    modifier = Modifier.size(GlassNavigationRailDefaults.IconSize),
                    tint = unselectedColor,
                )
            }
        }
    }
}

/**
 * One destination row. Its slot is interpolated from the collapsed bar's own destination into the
 * expanded column in the layout phase, and only the icon's opacity is animated in the draw phase.
 */
@Composable
private fun NavigationRailRow(
    item: GlassNavigationItem,
    selected: Boolean,
    position: Int,
    rowProgress: () -> Float,
    iconAlpha: () -> Float,
    tabWidths: List<Dp>,
    labelWidths: List<Dp>,
    itemWidthPx: Float,
    itemHorizontalMargin: Dp,
    itemContentPadding: Dp,
    iconColumnWidth: Dp,
    selectedColor: Color,
    unselectedColor: Color,
    labelStyle: TextStyle,
    rowShape: RoundedCornerShape,
    layoutDirection: LayoutDirection,
    onClick: () -> Unit,
) {
    val density = LocalDensity.current
    val tint by androidx.compose.animation.animateColorAsState(
        targetValue = if (selected) selectedColor else unselectedColor,
        animationSpec = GlassMotion.navContent(),
        label = "glassNavigationRailTint",
    )
    // The collapsed slot is the row's own source position, so the flight begins where the bar's
    // destination sits: the label is centred in its slot, and the row is that slot widened by the
    // content padding and icon column the expanded row will need.
    val slotWidth = tabWidths.getOrElse(position) { 0.dp }
    val labelWidth = labelWidths.getOrElse(position) { 0.dp }
    val slotStart = GlassNavigationRailDefaults.CollapsedContentStart +
        tabWidths.take(position).fold(0.dp) { sum, width -> sum + width }
    val collapsedTextInset = ((slotWidth - labelWidth) / 2).coerceAtLeast(0.dp)
    val rowLeftPx = with(density) { itemHorizontalMargin.toPx() }
    val rowTopPx = with(density) { GlassNavigationRailDefaults.RowTop.toPx() }
    val expandedTopPx = with(density) {
        (GlassNavigationRailDefaults.ExpandedRowTop + GlassNavigationRailDefaults.RowPitch * position).toPx()
    }
    val sourceWidthPx = with(density) {
        (labelWidth + itemContentPadding * 2 + iconColumnWidth).toPx()
    }
    val sourceLeftPx = with(density) {
        (slotStart + collapsedTextInset).toPx() - with(density) {
            (itemContentPadding + iconColumnWidth).toPx()
        }
    }
    Row(
        modifier = Modifier
            .layout { measurable, constraints ->
                val fraction = rowProgress().coerceIn(0f, 1f)
                val width = lerp(sourceWidthPx, itemWidthPx, fraction).roundToInt().coerceAtLeast(0)
                val height = with(density) {
                    (
                        GlassNavigationRailDefaults.CollapsedRowHeight +
                            GlassNavigationRailDefaults.ExpandedRowGrowth * fraction
                        ).toPx()
                }.roundToInt()
                val placeable = measurable.measure(Constraints.fixed(width, height))
                val x = lerp(sourceLeftPx, rowLeftPx, fraction)
                val y = lerp(rowTopPx, expandedTopPx, fraction)
                layout(constraints.maxWidth, constraints.maxHeight) {
                    if (layoutDirection == LayoutDirection.Rtl) {
                        placeable.placeRelative(constraints.maxWidth - x.roundToInt() - width, y.roundToInt())
                    } else {
                        placeable.placeRelative(x.roundToInt(), y.roundToInt())
                    }
                }
            }
            .clip(rowShape)
            .selectable(selected = selected, role = Role.Tab, onClick = onClick)
            .padding(horizontal = itemContentPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .width(iconColumnWidth)
                .graphicsLayer { this.alpha = iconAlpha() },
        ) {
            BadgedBox(badge = { item.badge?.invoke() }) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    modifier = Modifier.size(GlassNavigationRailDefaults.IconSize),
                    tint = tint,
                )
            }
        }
        Text(
            text = item.label.orEmpty(),
            color = tint,
            style = labelStyle,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
        )
        if (item.label == null) {
            item.contentDescription?.let { description ->
                Spacer(
                    Modifier.semantics { contentDescription = description },
                )
            }
        }
    }
}

/** The selection surface: the bar's slot growing into the expanded row. */
@Composable
private fun NavigationRailIndicator(
    selectedIndex: Int,
    rowProgress: () -> Float,
    tabWidths: List<Dp>,
    itemWidthPx: Float,
    itemHorizontalMargin: Dp,
    color: Color,
) {
    val density = LocalDensity.current
    val slotWidth = tabWidths.getOrElse(selectedIndex) { 0.dp }
    val collapsedWidthPx = with(density) { slotWidth.toPx() }
    GlassNavigationIndicator(
        pressed = false,
        scaleOnPress = false,
        color = color,
        pressedColor = color,
        modifier = Modifier.layout { measurable, constraints ->
            val fraction = rowProgress().coerceIn(0f, 1f)
            val width = lerp(collapsedWidthPx, itemWidthPx, fraction).roundToInt().coerceAtLeast(0)
            val height = with(density) {
                (
                    GlassNavigationRailDefaults.CollapsedRowHeight +
                        GlassNavigationRailDefaults.ExpandedRowGrowth * fraction
                    ).toPx()
            }.roundToInt()
            val placeable = measurable.measure(Constraints.fixed(width, height))
            val x = with(density) { itemHorizontalMargin.toPx() } * fraction +
                (
                    with(density) { GlassNavigationRailDefaults.LeadingActionWidth.toPx() } +
                        with(density) { tabWidths.take(selectedIndex).fold(0.dp) { sum, width -> sum + width }.toPx() }
                    ) *
                (1f - fraction)
            val y = with(density) {
                lerp(
                    GlassNavigationRailDefaults.RowTop.toPx(),
                    (
                        GlassNavigationRailDefaults.ExpandedRowTop +
                            GlassNavigationRailDefaults.RowPitch * selectedIndex
                        ).toPx(),
                    fraction,
                )
            }
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(x.roundToInt(), y.roundToInt())
            }
        },
    )
}

/** Padding a collapsed slot carries around its label, in the bar's own metric. */
private fun glassRailDestinationPadding(): Dp = GlassNavigationRailDefaults.IconSize +
    GlassNavigationBarDefaults.IndicatorOverhang * 2

/** Duration of the rail's expand/collapse morph. */
private const val RAIL_MORPH_DURATION_MILLIS: Int = 300

/** Easing of the rail's expand/collapse morph. */
private val RailMorphEasing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)

/**
 * The share of the morph the horizontal travel owns. The rest belongs to the size and shape
 * change, so the surface turns a corner instead of drifting diagonally.
 */
private const val RAIL_SLIDE_PORTION = 0.45f

/** Eases the travel segment of the morph, clamped so it holds still outside its share. */
private fun railSlideProgress(fraction: Float): Float {
    val t = fraction.coerceIn(0f, 1f)
    return t * t * (3f - 2f * t)
}

/** The morph's travel share: 1 while the surface still sits at the collapsed position. */
private fun railSlideFraction(fraction: Float): Float = railSlideProgress((RAIL_SLIDE_PORTION - fraction) / RAIL_SLIDE_PORTION)

/**
 * One row's flight. The frequency falls slightly with the row's index so a column of rows reads as
 * following its leader rather than moving as one rigid block.
 */
private fun railRowMotion(position: Int, expanded: Boolean): SpringSpec<Float> {
    val frequency = (if (expanded) 26.75f else 21.5f) / (1f + 0.03f * position)
    return spring(stiffness = frequency * frequency, dampingRatio = 0.97f, visibilityThreshold = 0.001f)
}

/**
 * Height settle of the surface. Expanding and collapsing use different springs, so the panel lands
 * with a short rebound while the capsule's collapse stays crisp.
 */
private fun railHeightMotion(expanded: Boolean, distance: Dp): SpringSpec<Dp> {
    val (stiffness, dampingRatio) = if (expanded) 220f to 0.83f else 462.25f to 0.97f
    return spring(
        stiffness = stiffness,
        dampingRatio = dampingRatio,
        visibilityThreshold = (distance * 0.001f).coerceAtLeast(0.1.dp),
    )
}
