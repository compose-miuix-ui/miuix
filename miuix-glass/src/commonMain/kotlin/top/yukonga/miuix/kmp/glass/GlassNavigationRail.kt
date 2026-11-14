// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.BadgedBox
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBarDefaults
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
import kotlin.math.ceil
import kotlin.math.roundToInt

/** Default values for [GlassNavigationRail]. */
object GlassNavigationRailDefaults {

    /** Panel width while expanded. */
    val ExpandedWidth: Dp = 250.dp

    /** Distance between the expanded panel and the window edges. */
    val Margin: Dp = 12.dp

    /** Height of the collapsed capsule. */
    val CollapsedHeight: Dp = GlassTopAppBarDefaults.ButtonSize

    /**
     * Top of the collapsed capsule, relative to the safe area. It puts the capsule on the centre
     * line of a [top.yukonga.miuix.kmp.basic.TopAppBar] or [GlassTopAppBar] at the top of the same
     * area, so the capsule takes the place of the bar's collapsed title.
     */
    val CollapsedTop: Dp = (TopAppBarDefaults.SmallTopAppBarCenterHeight - CollapsedHeight) / 2 + 3.dp

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
 * State of a [GlassNavigationRail].
 *
 * @param initialExpanded Whether the rail starts expanded.
 */
@Stable
class GlassNavigationRailState(initialExpanded: Boolean) {

    /** Whether the rail is the expanded panel. Setting it starts the morph. */
    var expanded: Boolean by mutableStateOf(initialExpanded)

    internal val morph = Animatable(if (initialExpanded) 1f else 0f)

    /**
     * The morph's position, from `0` (the collapsed capsule) to `1` (the expanded panel). It
     * changes every frame of the morph, so read it in a layout or draw block, for example to move
     * the page clear of the panel without recomposing it.
     */
    val progress: Float get() = morph.value

    companion object {
        /** The default [Saver], keeping [expanded]. */
        val Saver: Saver<GlassNavigationRailState, Boolean> = Saver(
            save = { it.expanded },
            restore = { GlassNavigationRailState(it) },
        )
    }
}

/**
 * Creates and remembers a [GlassNavigationRailState], kept across configuration changes.
 *
 * @param initialExpanded Whether the rail starts expanded.
 */
@Composable
fun rememberGlassNavigationRailState(initialExpanded: Boolean = true): GlassNavigationRailState = rememberSaveable(saver = GlassNavigationRailState.Saver) {
    GlassNavigationRailState(initialExpanded)
}

/**
 * A floating sidebar on glass for wide layouts: a panel at the start edge that, collapsing, travels
 * to the top centre and becomes the navigation bar itself.
 *
 * Expanded, the rail lists destinations as icon and label. Collapsed, it *is* a
 * [GlassNavigationBar] showing labels only, with the expand toggle in its leading slot, so a tap, a
 * horizontal drag and the selection indicator behave exactly as they do in the bar.
 *
 * Between those settled states every row flies from its place in the column into its slot in the
 * bar's row. The morph is read in layout and draw blocks, so its frames do not recompose the rows.
 *
 * The rail is an overlay: it fills the space it is given and draws the surface inside it. Place it
 * over the page, outside the recorded backdrop. Move the page clear of the panel with
 * [GlassNavigationRailState.progress], and hide a top bar's title while the capsule covers it.
 *
 * @param items The destinations, in order.
 * @param selectedIndex The index of the current destination.
 * @param onSelect Called with the destination the user selected.
 * @param backdrop The [Backdrop] supplying the content behind the glass. `null` fills the surface
 *   with the theme's surfaceContainer color instead of sampling.
 * @param modifier The modifier applied to the rail's overlay box.
 * @param state The rail's expanded state and morph progress.
 * @param showToggle Whether to show the built-in expand/collapse toggle. Without it, the caller
 *   drives [GlassNavigationRailState.expanded].
 * @param defaultWindowInsetsPadding Whether to keep the surface inside the system bars, the caption
 *   bar and the display cutout. Disable when the caller insets the rail itself.
 * @param collapsedTop Top of the collapsed capsule, relative to the safe area.
 * @param expandedWidth Panel width while expanded.
 * @param style The glass material style.
 * @param alpha Opacity multiplier for the material.
 * @param material The surface's own body when sampling a backdrop. `null` omits explicit material
 *   layers; it does not disable sampling. Ignored when [backdrop] is `null`.
 * @param stroke Optional bloom stroke along the rim.
 * @param shadow The shadow the floating surface casts. `null` removes it.
 * @param selectedColor Tint of the selected icon and label. Dynamic colour themes use primary;
 *   other themes use onSurface.
 * @param unselectedColor Tint of the unselected icons and labels.
 * @param indicatorColor Fill behind the selected destination.
 * @param indicatorPressedColor Fill behind the destination held down in the collapsed capsule.
 * @param labelStyle The destinations' label style. It also decides the width of a collapsed slot,
 *   so the capsule hugs its labels.
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
    state: GlassNavigationRailState = rememberGlassNavigationRailState(),
    showToggle: Boolean = true,
    defaultWindowInsetsPadding: Boolean = true,
    collapsedTop: Dp = GlassNavigationRailDefaults.CollapsedTop,
    expandedWidth: Dp = GlassNavigationRailDefaults.ExpandedWidth,
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    material: GlassMaterial? = GlassNavigationRailDefaults.material(),
    stroke: EdgeStroke? = GlassNavigationRailDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Float,
    selectedColor: Color = if (MiuixTheme.isDynamicColor) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onSurface,
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
    val expanded = state.expanded
    val density = LocalDensity.current

    val textMeasurer = rememberTextMeasurer()
    // A collapsed slot is as wide as its own label plus the bar's indicator reach, so the capsule
    // hugs its text.
    val labelWidths = remember(items, labelStyle, density) {
        items.map { item ->
            item.label?.let { with(density) { ceil(textMeasurer.measure(it, labelStyle).size.width.toFloat()).toDp() } } ?: 0.dp
        }
    }
    val sourceWidths = remember(labelWidths) { labelWidths.map { it + RailSlotPadding } }

    // One clock for the surface's width, top and corner; each row and the panel's height and start
    // edge settle on their own springs, as in the reference motion.
    val rowProgress = remember(items.size) { List(items.size) { Animatable(if (expanded) 1f else 0f) } }
    val iconAlpha = remember { Animatable(if (expanded) 1f else 0f) }
    LaunchedEffect(expanded, rowProgress) {
        val target = if (expanded) 1f else 0f
        launch { state.morph.animateTo(target, RailMorphMotion) }
        launch { iconAlpha.animateTo(target, if (expanded) RailIconEnterMotion else RailIconExitMotion) }
        rowProgress.forEachIndexed { position, progress ->
            launch { progress.animateTo(target, railRowMotion(position, expanded)) }
        }
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .then(
                if (defaultWindowInsetsPadding) {
                    Modifier.windowInsetsPadding(
                        WindowInsets.systemBars
                            .union(WindowInsets.captionBar)
                            .union(WindowInsets.displayCutout),
                    )
                } else {
                    Modifier
                },
            ),
    ) {
        val viewportWidth = maxWidth
        val viewportHeight = maxHeight
        val totalSlots = sourceWidths.fold(0.dp) { sum, width -> sum + width }
        // The bar lays out its first destination after its content padding and the toggle's slot.
        val leadingActionWidth = if (showToggle) RailLeadingActionWidth else 0.dp
        val contentStart = GlassNavigationBarDefaults.ContentPaddingHorizontal + leadingActionWidth
        val chrome = contentStart + RailCollapsedEndPadding
        // The capsule leaves room on both sides for a top bar's navigation icon and actions, and
        // compresses its slots when the labels need more than that.
        val neededWidth = chrome + totalSlots
        val capsuleWidth = neededWidth
            .coerceAtMost((viewportWidth - RailCollapsedSideReserve * 2).coerceAtLeast(RailMinCollapsedWidth))
            .coerceAtMost(viewportWidth)
        val widthRatio = if (totalSlots > 0.dp) {
            ((capsuleWidth - chrome) / totalSlots).coerceAtLeast(0f)
        } else {
            1f
        }
        val tabWidths = remember(sourceWidths, widthRatio) { sourceWidths.map { it * widthRatio } }
        val collapsedHeight = GlassNavigationRailDefaults.CollapsedHeight
        val collapsedStart = (viewportWidth - capsuleWidth) / 2
        val expandedStart = GlassNavigationRailDefaults.Margin
        val expandedTop = GlassNavigationRailDefaults.Margin
        val expandedHeight = (viewportHeight - expandedTop - GlassNavigationRailDefaults.Margin)
            .coerceAtLeast(collapsedHeight)

        val panelHeight = rememberRailMotion(
            target = if (expanded) expandedHeight else collapsedHeight,
            expanded = expanded,
        ) { expanding -> railHeightMotion(expanding, expandedHeight - collapsedHeight) }
        val panelStart = rememberRailMotion(
            target = if (expanded) expandedStart else collapsedStart,
            expanded = expanded,
        ) { expanding -> if (expanding) RailMorphDpMotion else RailReboundMotion }

        // The bar takes over only once every channel has come to rest, so nothing is still moving
        // when the panel's geometry is swapped for the bar's.
        val settledCollapsed by remember(state, rowProgress, panelHeight, panelStart) {
            derivedStateOf {
                !state.expanded &&
                    state.morph.value == 0f &&
                    !panelHeight.isRunning &&
                    !panelStart.isRunning &&
                    rowProgress.all { it.value == 0f }
            }
        }

        if (settledCollapsed) {
            GlassNavigationBarImpl(
                items = items,
                selectedIndex = index,
                onSelect = onSelect,
                backdrop = backdrop,
                modifier = Modifier
                    .offset(x = collapsedStart, y = collapsedTop)
                    .width(capsuleWidth),
                defaultWindowInsetsPadding = false,
                style = style,
                shape = GlassShape(CornerSize(50), CornerSize(50), CornerSize(50), CornerSize(50)),
                alpha = alpha,
                visible = true,
                stroke = stroke,
                shadow = shadow,
                height = collapsedHeight,
                material = material,
                indicatorColor = indicatorColor,
                indicatorPressedColor = indicatorPressedColor,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                trailingAction = null,
                leadingAction = if (showToggle) {
                    {
                        RailToggle(
                            onClick = { state.expanded = true },
                            size = RailCollapsedRowHeight,
                            tint = unselectedColor,
                            contentDescription = expandContentDescription,
                        )
                    }
                } else {
                    null
                },
                leadingActionWidth = leadingActionWidth,
                labelsOnly = true,
                labelStyle = labelStyle,
                destinationWidths = tabWidths,
                indicatorOverhang = 0.dp,
                contentPaddingEnd = RailCollapsedEndPadding,
            )
        } else {
            GlassNavigationRailPanel(
                items = items,
                selectedIndex = index,
                state = state,
                rowProgress = rowProgress,
                iconAlpha = { iconAlpha.value },
                showToggle = showToggle,
                tabWidths = tabWidths,
                labelWidths = labelWidths,
                contentStart = contentStart,
                collapsedWidth = capsuleWidth,
                expandedWidth = expandedWidth,
                panelStart = { panelStart.value },
                collapsedTop = collapsedTop,
                expandedTop = expandedTop,
                panelHeight = { panelHeight.value },
                backdrop = backdrop,
                style = style,
                alpha = alpha,
                material = material,
                stroke = stroke,
                shadow = shadow,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                indicatorColor = indicatorColor,
                labelStyle = labelStyle,
                toggleContentDescription = if (expanded) collapseContentDescription else expandContentDescription,
                onSelect = onSelect,
                scrollState = scrollState,
            )
        }
    }
}

/** The panel side of the morph: the surface, the flying rows, their indicator and the toggle. */
@Composable
private fun GlassNavigationRailPanel(
    items: List<GlassNavigationItem>,
    selectedIndex: Int,
    state: GlassNavigationRailState,
    rowProgress: List<Animatable<Float, AnimationVector1D>>,
    iconAlpha: () -> Float,
    showToggle: Boolean,
    tabWidths: List<Dp>,
    labelWidths: List<Dp>,
    contentStart: Dp,
    collapsedWidth: Dp,
    expandedWidth: Dp,
    panelStart: () -> Dp,
    collapsedTop: Dp,
    expandedTop: Dp,
    panelHeight: () -> Dp,
    backdrop: Backdrop?,
    style: GlassStyle,
    alpha: Float,
    material: GlassMaterial?,
    stroke: EdgeStroke?,
    shadow: GlassShadow?,
    selectedColor: Color,
    unselectedColor: Color,
    indicatorColor: Color,
    labelStyle: TextStyle,
    toggleContentDescription: String,
    onSelect: (Int) -> Unit,
    scrollState: ScrollState,
) {
    val expanded = state.expanded
    // The corner eases from the capsule's half-height to the panel's radius on the morph's clock,
    // read when the outline is built rather than in composition.
    val shape = remember(state) {
        val corner = RailMorphCorner { state.progress }
        GlassShape(corner, corner, corner, corner)
    }
    val itemWidth = expandedWidth - RailItemHorizontalMargin * 2
    val contentHeight = RailExpandedRowTop + RailRowPitch * items.lastIndex + RailExpandedRowHeight +
        GlassNavigationRailDefaults.Margin
    // The column only scrolls once it has settled into the panel; while rows fly they keep their
    // own geometry.
    val scrollEnabled by remember(state, rowProgress) {
        derivedStateOf { state.expanded && state.morph.value == 1f && rowProgress.all { it.value == 1f } }
    }

    Box(
        Modifier
            .layout { measurable, _ ->
                val fraction = state.progress.coerceIn(0f, 1f)
                val width = lerp(collapsedWidth.toPx(), expandedWidth.toPx(), fraction).roundToInt()
                val height = panelHeight().roundToPx().coerceAtLeast(0)
                val placeable = measurable.measure(Constraints.fixed(width, height))
                layout(width, height) { placeable.place(0, 0) }
            }
            .offset {
                IntOffset(
                    x = panelStart().roundToPx(),
                    y = lerp(collapsedTop.toPx(), expandedTop.toPx(), state.progress).roundToInt(),
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
            .clip(shape),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                // Rows scrolled up pass under the toggle's header, not over it.
                .drawWithContent {
                    clipRect(top = RailHeaderHeight.toPx() * state.progress) {
                        this@drawWithContent.drawContent()
                    }
                }
                .verticalScroll(scrollState, enabled = scrollEnabled),
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(contentHeight)
                    .selectableGroup(),
            ) {
                NavigationRailIndicator(
                    selectedIndex = selectedIndex,
                    rowProgress = { rowProgress[selectedIndex].value },
                    scrollOffset = { scrollState.value },
                    tabWidths = tabWidths,
                    contentStart = contentStart,
                    itemWidth = itemWidth,
                    color = indicatorColor,
                )
                items.forEachIndexed { position, item ->
                    key(position, item) {
                        NavigationRailRow(
                            item = item,
                            selected = position == selectedIndex,
                            position = position,
                            rowProgress = { rowProgress[position].value },
                            iconAlpha = iconAlpha,
                            scrollOffset = { scrollState.value },
                            tabWidths = tabWidths,
                            contentStart = contentStart,
                            labelWidth = labelWidths[position],
                            itemWidth = itemWidth,
                            selectedColor = selectedColor,
                            unselectedColor = unselectedColor,
                            labelStyle = labelStyle,
                            onClick = { onSelect(position) },
                        )
                    }
                }
            }
        }
        if (showToggle) {
            Box(
                modifier = Modifier
                    .offset {
                        val fraction = state.progress
                        IntOffset(
                            x = lerp(RailCollapsedToggleBoxStart.toPx(), RailExpandedToggleStart.toPx(), fraction).roundToInt(),
                            y = lerp(0f, RailExpandedToggleTop.toPx(), fraction).roundToInt(),
                        )
                    }
                    .size(RailToggleBoxSize),
                contentAlignment = Alignment.Center,
            ) {
                RailToggle(
                    onClick = { state.expanded = !expanded },
                    size = if (expanded) RailToggleBoxSize else RailCollapsedRowHeight,
                    tint = unselectedColor,
                    contentDescription = toggleContentDescription,
                )
            }
        }
    }
}

/** The expand/collapse toggle. Its surface is clear; only the icon and the press read. */
@Composable
private fun RailToggle(
    onClick: () -> Unit,
    size: Dp,
    tint: Color,
    contentDescription: String,
) {
    GlassIconButton(
        onClick = onClick,
        inheritTopBarMaterial = false,
        size = size,
        surfaceAlpha = 0f,
        shadow = null,
    ) {
        Icon(
            imageVector = MiuixIcons.Basic.Sidebar,
            contentDescription = contentDescription,
            modifier = Modifier.size(RailIconSize),
            tint = tint,
        )
    }
}

/**
 * One destination row. Its slot is interpolated in the layout phase from the collapsed bar's
 * destination into the expanded column; only the icon's opacity changes in the draw phase.
 */
@Composable
private fun NavigationRailRow(
    item: GlassNavigationItem,
    selected: Boolean,
    position: Int,
    rowProgress: () -> Float,
    iconAlpha: () -> Float,
    scrollOffset: () -> Int,
    tabWidths: List<Dp>,
    contentStart: Dp,
    labelWidth: Dp,
    itemWidth: Dp,
    selectedColor: Color,
    unselectedColor: Color,
    labelStyle: TextStyle,
    onClick: () -> Unit,
) {
    val tint by animateColorAsState(
        targetValue = if (selected) selectedColor else unselectedColor,
        animationSpec = GlassMotion.navContent(),
        label = "glassNavigationRailTint",
    )
    // The flight starts where the bar draws this destination: the label centred in its slot, and
    // the row that slot's label widened by the padding and icon column the expanded row needs. Both
    // ends are fixed for a given layout, so only the interpolation runs per frame.
    val density = LocalDensity.current
    val geometry = remember(density, position, tabWidths, contentStart, labelWidth, itemWidth) {
        with(density) {
            // Everything is summed as the row's own padding and icon column round, and as the bar
            // lays out its whole-pixel slots and centres the label in one, so neither the label's
            // width nor the hand-off is a pixel off.
            val padding = RailItemContentPadding.roundToPx()
            val labelStart = padding + RailIconColumnWidth.roundToPx()
            val slotStart = contentStart.roundToPx() + tabWidths.take(position).sumOf { it.roundToPx() }
            val slotWidth = tabWidths[position].roundToPx()
            val labelPx = labelWidth.roundToPx()
            RailRowGeometry(
                sourceStart = (slotStart + ((slotWidth - labelPx.coerceAtMost(slotWidth)) / 2f).roundToInt() - labelStart).toFloat(),
                sourceWidth = (labelStart + labelPx + padding).toFloat(),
                expandedTop = (RailExpandedRowTop + RailRowPitch * position).toPx(),
                labelMaxWidth = (itemWidth - RailItemContentPadding * 2 - RailIconColumnWidth).roundToPx(),
            )
        }
    }
    Row(
        modifier = Modifier
            .layout { measurable, constraints ->
                val fraction = rowProgress().coerceIn(0f, 1f)
                val width = lerp(geometry.sourceWidth, itemWidth.toPx(), fraction).roundToInt().coerceAtLeast(0)
                val height = (RailCollapsedRowHeight + RailRowGrowth * fraction).roundToPx()
                val placeable = measurable.measure(Constraints.fixed(width, height))
                val x = lerp(geometry.sourceStart, RailItemHorizontalMargin.toPx(), fraction)
                // The column's scroll moves the expanded rows only: the collapsed slot stays on
                // the capsule's row wherever the column was left.
                val y = lerp(RailRowTop.toPx() + scrollOffset(), geometry.expandedTop, fraction)
                layout(constraints.maxWidth, constraints.maxHeight) {
                    placeable.placeRelative(x.roundToInt(), y.roundToInt())
                }
            }
            .clip(RoundedCornerShape(percent = 50))
            .selectable(selected = selected, role = Role.Tab, onClick = onClick)
            .padding(horizontal = RailItemContentPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .width(RailIconColumnWidth)
                .graphicsLayer { this.alpha = iconAlpha() },
        ) {
            BadgedBox(badge = { item.badge?.invoke() }) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    modifier = Modifier.size(RailIconSize),
                    tint = tint,
                )
            }
        }
        if (item.label != null) {
            Text(
                text = item.label,
                // The flying row is sized from a separate measurement of the label, which can
                // disagree with the label's own by a pixel or two. Never offer the label less than
                // the settled row will, so it only ellipsizes when it would not fit the panel.
                modifier = Modifier.layout { measurable, constraints ->
                    val placeable = measurable.measure(
                        constraints.copy(maxWidth = maxOf(constraints.maxWidth, geometry.labelMaxWidth)),
                    )
                    layout(placeable.width.coerceAtMost(constraints.maxWidth), placeable.height) {
                        placeable.placeRelative(0, 0)
                    }
                },
                color = tint,
                style = labelStyle,
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
            )
        } else {
            item.contentDescription?.let { description ->
                Spacer(Modifier.semantics { contentDescription = description })
            }
        }
    }
}

/**
 * The selection surface: the bar's slot growing into the expanded row. A new selection moves it at
 * once, landing with the row's own press feedback rather than trailing behind it.
 */
@Composable
private fun NavigationRailIndicator(
    selectedIndex: Int,
    rowProgress: () -> Float,
    scrollOffset: () -> Int,
    tabWidths: List<Dp>,
    contentStart: Dp,
    itemWidth: Dp,
    color: Color,
) {
    // The collapsed slot is the bar's own indicator: its start and width, in the bar's float metric.
    val collapsedSlot = remember(selectedIndex, tabWidths, contentStart) {
        contentStart + tabWidths.take(selectedIndex).fold(0.dp) { sum, width -> sum + width } to tabWidths[selectedIndex]
    }
    GlassNavigationIndicator(
        pressed = false,
        scaleOnPress = false,
        color = color,
        pressedColor = color,
        modifier = Modifier.layout { measurable, constraints ->
            val fraction = rowProgress().coerceIn(0f, 1f)
            val (collapsedStartDp, collapsedWidthDp) = collapsedSlot
            val collapsedStart = collapsedStartDp.toPx()
            val width = lerp(collapsedWidthDp.toPx(), itemWidth.toPx(), fraction).roundToInt().coerceAtLeast(0)
            val height = (RailCollapsedRowHeight + RailRowGrowth * fraction).roundToPx()
            val placeable = measurable.measure(Constraints.fixed(width, height))
            val x = lerp(collapsedStart, RailItemHorizontalMargin.toPx(), fraction)
            val y = lerp(
                RailRowTop.toPx() + scrollOffset(),
                RailExpandedRowTop.toPx() + RailRowPitch.toPx() * selectedIndex,
                fraction,
            )
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(x.roundToInt(), y.roundToInt())
            }
        },
    )
}

/** A row's fixed flight ends, in pixels. */
private class RailRowGeometry(
    val sourceStart: Float,
    val sourceWidth: Float,
    val expandedTop: Float,
    val labelMaxWidth: Int,
)

/** A corner that eases from the capsule's half-height to the panel's radius as [progress] rises. */
@Stable
private class RailMorphCorner(private val progress: () -> Float) : CornerSize {
    override fun toPx(shapeSize: Size, density: Density): Float = with(density) {
        lerp(RailCollapsedCornerRadius.toPx(), RailExpandedCornerRadius.toPx(), progress().coerceIn(0f, 1f))
    }
}

/**
 * Keeps a geometry value on [target]. Flipping [expanded] animates to it with [spec]; a target that
 * moves on its own, as a window resize does, is followed at once unless a flip is still settling.
 */
@Composable
private fun rememberRailMotion(
    target: Dp,
    expanded: Boolean,
    spec: (expanding: Boolean) -> AnimationSpec<Dp>,
): Animatable<Dp, AnimationVector1D> {
    val animatable = remember { Animatable(target, Dp.VectorConverter) }
    val currentTarget by rememberUpdatedState(target)
    val currentExpanded by rememberUpdatedState(expanded)
    val currentSpec by rememberUpdatedState(spec)
    LaunchedEffect(animatable) {
        var settledExpanded = currentExpanded
        var animating = false
        snapshotFlow { currentExpanded to currentTarget }.collectLatest { (expanding, value) ->
            if (expanding != settledExpanded || animating) {
                settledExpanded = expanding
                animating = true
                animatable.animateTo(value, currentSpec(expanding))
                animating = false
            } else {
                animatable.snapTo(value)
            }
        }
    }
    return animatable
}

/** One row's flight. Later rows respond slightly slower, so the column follows its leader. */
private fun railRowMotion(position: Int, expanded: Boolean): SpringSpec<Float> {
    val frequency = (if (expanded) 26.75f else 21.5f) / (1f + 0.03f * position)
    return spring(stiffness = frequency * frequency, dampingRatio = 0.97f, visibilityThreshold = 0.001f)
}

/** The panel's height settle: a short rebound when expanding, crisp when collapsing. */
private fun railHeightMotion(expanded: Boolean, distance: Dp): SpringSpec<Dp> {
    val (stiffness, dampingRatio) = if (expanded) 220f to 0.83f else 462.25f to 0.97f
    return spring(
        stiffness = stiffness,
        dampingRatio = dampingRatio,
        visibilityThreshold = (distance * 0.001f).coerceAtLeast(0.1.dp),
    )
}

private val RailMorphEasing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)
private val RailMorphMotion = tween<Float>(durationMillis = 300, easing = RailMorphEasing)
private val RailMorphDpMotion = tween<Dp>(durationMillis = 300, easing = RailMorphEasing)
private val RailReboundMotion = spring<Dp>(stiffness = 200f, dampingRatio = 0.8f)
private val RailIconEnterMotion = tween<Float>(durationMillis = 200, easing = RailMorphEasing)
private val RailIconExitMotion = tween<Float>(durationMillis = 120, easing = RailMorphEasing)

private val RailIconSize = 24.dp
private val RailIconColumnWidth = RailIconSize + 12.dp
private val RailItemContentPadding = 14.dp
private val RailItemHorizontalMargin = 12.dp
private val RailCollapsedRowHeight = GlassNavigationRailDefaults.CollapsedHeight -
    GlassNavigationBarDefaults.IndicatorPaddingVertical * 2
private val RailRowGrowth = 4.dp
private val RailExpandedRowHeight = RailCollapsedRowHeight + RailRowGrowth
private val RailRowPitch = RailExpandedRowHeight + 4.dp
private val RailRowTop = GlassNavigationBarDefaults.IndicatorPaddingVertical
private val RailExpandedRowTop = 64.dp
private val RailHeaderHeight = RailExpandedRowTop - (RailRowPitch - RailExpandedRowHeight)
private val RailCollapsedCornerRadius = GlassNavigationRailDefaults.CollapsedHeight / 2
private val RailExpandedCornerRadius = 20.dp

/** The toggle's box: the bar's leading slot while collapsed, the header corner while expanded. */
private val RailToggleBoxSize = GlassTopAppBarDefaults.ButtonSize
private val RailLeadingActionWidth = RailToggleBoxSize + 4.dp
private val RailCollapsedToggleBoxStart = GlassNavigationBarDefaults.ContentPaddingHorizontal +
    (RailLeadingActionWidth - RailToggleBoxSize) / 2
private val RailExpandedToggleStart = 16.dp
private val RailExpandedToggleTop = 12.dp
private val RailCollapsedEndPadding = 3.dp

/** A collapsed slot's padding around its label, the bar's indicator reach on each side. */
private val RailSlotPadding = RailIconSize + GlassNavigationBarDefaults.IndicatorOverhang * 2
private val RailCollapsedSideReserve = 81.dp
private val RailMinCollapsedWidth = 192.dp
