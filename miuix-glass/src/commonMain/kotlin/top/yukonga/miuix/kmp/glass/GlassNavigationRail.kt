// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastRoundToInt
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.BadgeDefaults
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBarDefaults
import top.yukonga.miuix.kmp.blur.Backdrop
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

private val RailTopBarIcon: ImageVector = ImageVector.Builder(
    name = "RailTopBar",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(
        stroke = SolidColor(Color.White),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
    ) {
        moveTo(6f, 3f)
        lineTo(18f, 3f)
        curveTo(21f, 3f, 22f, 4f, 22f, 7f)
        lineTo(22f, 17f)
        curveTo(22f, 20f, 21f, 21f, 18f, 21f)
        lineTo(6f, 21f)
        curveTo(3f, 21f, 2f, 20f, 2f, 17f)
        lineTo(2f, 7f)
        curveTo(2f, 4f, 3f, 3f, 6f, 3f)
        close()
    }
    path(
        stroke = SolidColor(Color.White),
        strokeLineWidth = 2.8f,
        strokeLineCap = StrokeCap.Round,
    ) {
        moveTo(9f, 7f)
        lineTo(15f, 7f)
    }
}.build()

private val RailSideBarIcon: ImageVector = ImageVector.Builder(
    name = "RailSideBar",
    defaultWidth = 24.dp,
    defaultHeight = 24.dp,
    viewportWidth = 24f,
    viewportHeight = 24f,
).apply {
    path(
        stroke = SolidColor(Color.White),
        strokeLineWidth = 2f,
        strokeLineCap = StrokeCap.Round,
    ) {
        moveTo(6f, 3f)
        lineTo(18f, 3f)
        curveTo(21f, 3f, 22f, 4f, 22f, 7f)
        lineTo(22f, 17f)
        curveTo(22f, 20f, 21f, 21f, 18f, 21f)
        lineTo(6f, 21f)
        curveTo(3f, 21f, 2f, 20f, 2f, 17f)
        lineTo(2f, 7f)
        curveTo(2f, 4f, 3f, 3f, 6f, 3f)
        close()
    }
    path(fill = SolidColor(Color.White)) {
        moveTo(4f, 6.6f)
        curveTo(4f, 6.03995f, 4f, 5.75992f, 4.10899f, 5.54601f)
        curveTo(4.20487f, 5.35785f, 4.35785f, 5.20487f, 4.54601f, 5.10899f)
        curveTo(4.75992f, 5f, 5.03995f, 5f, 5.6f, 5f)
        lineTo(8.4f, 5f)
        curveTo(8.96005f, 5f, 9.24008f, 5f, 9.45399f, 5.10899f)
        curveTo(9.64215f, 5.20487f, 9.79513f, 5.35785f, 9.89101f, 5.54601f)
        curveTo(10f, 5.75992f, 10f, 6.03995f, 10f, 6.6f)
        lineTo(10f, 17.4f)
        curveTo(10f, 17.9601f, 10f, 18.2401f, 9.89101f, 18.454f)
        curveTo(9.79513f, 18.6422f, 9.64215f, 18.7951f, 9.45399f, 18.891f)
        curveTo(9.24008f, 19f, 8.96005f, 19f, 8.4f, 19f)
        lineTo(5.6f, 19f)
        curveTo(5.03995f, 19f, 4.75992f, 19f, 4.54601f, 18.891f)
        curveTo(4.35785f, 18.7951f, 4.20487f, 18.6422f, 4.10899f, 18.454f)
        curveTo(4f, 18.2401f, 4f, 17.9601f, 4f, 17.4f)
        close()
    }
}.build()

/** Default values for [GlassNavigationRail]. */
object GlassNavigationRailDefaults {

    /** Panel width while expanded. */
    val ExpandedWidth: Dp = 250.dp

    /** Distance between the expanded panel and the window edges. */
    val Margin: Dp = 12.dp

    /** Height of the collapsed capsule. */
    val CollapsedHeight: Dp = GlassTopAppBarDefaults.ButtonSize

    /** Top offset of the collapsed capsule, centered on the collapsed top bar. */
    val CollapsedTop: Dp = (TopAppBarDefaults.CollapsedHeight - CollapsedHeight) / 2

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
    fun material(): GlassMaterial = dynamicGlassMaterial(
        GlassMaterials.puredThinGlass(
            isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        ),
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

    /** Start offset of the collapsed capsule within the rail's viewport. */
    var collapsedStart: Dp by mutableStateOf(0.dp)
        internal set

    /** Width of the collapsed capsule. */
    var collapsedWidth: Dp by mutableStateOf(0.dp)
        internal set

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
 * Expanded, the rail lists destinations as icon and label. Collapsed, it shows labels only, with
 * the expand toggle in its leading slot. Its persistent gesture owner and indicator use the same
 * drag physics as [GlassNavigationBar], including presses made during the morph.
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
 * @param secondaryItems Optional secondary destinations shown below a divider while expanded.
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
 * @param selectedColor Tint of the selected icon and label.
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
    secondaryItems: List<GlassNavigationItem> = emptyList(),
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
    val totalItems = items.size + secondaryItems.size
    val index = if (selectedIndex in 0 until totalItems) selectedIndex else -1
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
    val sourceWidths = remember(items, labelWidths) {
        labelWidths.mapIndexed { index, width ->
            width + RailSlotPadding + if (items[index].badge != null) BadgeDefaults.LargeSize * 2 else 0.dp
        }
    }

    // One clock for the surface's width, top and corner; each row and the panel's height and start
    // edge settle on their own springs, as in the reference motion.
    val secondaryRowCount = if (secondaryItems.isNotEmpty()) 1 + secondaryItems.size else 0
    val rowProgress = remember(items.size, secondaryRowCount) {
        List(items.size + secondaryRowCount) { Animatable(if (expanded) 1f else 0f) }
    }
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
        // maxWidth/maxHeight go stale when only the density changes; the pixels don't.
        val viewportWidth = with(density) { constraints.maxWidth.toDp() }
        val viewportHeight = with(density) { constraints.maxHeight.toDp() }
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
        val animatedCapsuleWidth = animateDpAsState(capsuleWidth, RailMorphDpMotion, label = "railCapsuleWidth")
        val collapsedHeight = GlassNavigationRailDefaults.CollapsedHeight
        val collapsedStart = (viewportWidth - capsuleWidth) / 2
        SideEffect {
            state.collapsedStart = collapsedStart
            state.collapsedWidth = capsuleWidth
        }
        val expandedStart = GlassNavigationRailDefaults.Margin
        val expandedTop = GlassNavigationRailDefaults.CollapsedTop
        val expandedHeight = (viewportHeight - expandedTop - GlassNavigationRailDefaults.Margin)
            .coerceAtLeast(collapsedHeight)

        val panelHeight = rememberRailMotion(
            target = if (expanded) expandedHeight else collapsedHeight,
            expanded = expanded,
        ) { expanding -> railHeightMotion(expanding, expandedHeight - collapsedHeight) }
        val panelStart = rememberRailMotion(
            target = if (expanded) expandedStart else collapsedStart,
            expanded = expanded,
            targetChangeSpec = RailMorphDpMotion,
        ) { expanding -> if (expanding) RailMorphDpMotion else RailReboundMotion }

        GlassNavigationRailPanel(
            items = items,
            selectedIndex = index,
            secondaryItems = secondaryItems,
            state = state,
            rowProgress = rowProgress,
            iconAlpha = { iconAlpha.value },
            showToggle = showToggle,
            tabWidths = tabWidths,
            labelWidths = labelWidths,
            contentStart = contentStart,
            collapsedWidth = { animatedCapsuleWidth.value },
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
            indicatorPressedColor = indicatorPressedColor,
            labelStyle = labelStyle,
            toggleContentDescription = if (expanded) collapseContentDescription else expandContentDescription,
            onSelect = onSelect,
            scrollState = scrollState,
        )
    }
}

/** One permanent surface, gesture owner, set of rows and toggle for both ends of the morph. */
@Composable
private fun GlassNavigationRailPanel(
    items: List<GlassNavigationItem>,
    secondaryItems: List<GlassNavigationItem>,
    selectedIndex: Int,
    state: GlassNavigationRailState,
    rowProgress: List<Animatable<Float, AnimationVector1D>>,
    iconAlpha: () -> Float,
    showToggle: Boolean,
    tabWidths: List<Dp>,
    labelWidths: List<Dp>,
    contentStart: Dp,
    collapsedWidth: () -> Dp,
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
    indicatorPressedColor: Color,
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
    val secondaryTop = RailExpandedRowTop + RailRowPitch * items.size + RailDividerSectionHeight
    val contentHeight = if (secondaryItems.isEmpty()) {
        RailExpandedRowTop + RailRowPitch * items.lastIndex + RailExpandedRowHeight +
            GlassNavigationRailDefaults.Margin
    } else {
        secondaryTop + RailRowPitch * secondaryItems.lastIndex + RailExpandedRowHeight +
            GlassNavigationRailDefaults.Margin
    }
    // The column only scrolls once it has settled into the panel; while rows fly they keep their
    // own geometry.
    val scrollEnabled by remember(state, rowProgress) {
        computedStateOf { state.expanded && state.morph.value == 1f && rowProgress.all { it.value == 1f } }
    }
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val scope = rememberCoroutineScope()
    val widths = remember(tabWidths, density) { with(density) { tabWidths.map { it.roundToPx().toFloat() } } }
    val startPx = with(density) { contentStart.roundToPx().toFloat() }
    val endPx = with(density) { RailCollapsedEndPadding.toPx() }
    val headerHeightPx = with(density) { RailHeaderHeight.toPx() }
    val primarySelectedIndex = if (selectedIndex in items.indices) selectedIndex else -1
    var lastPrimaryIndex by remember { mutableIntStateOf(primarySelectedIndex.coerceAtLeast(0)) }
    LaunchedEffect(primarySelectedIndex, items.size) {
        if (primarySelectedIndex >= 0) {
            lastPrimaryIndex = primarySelectedIndex
        } else if (lastPrimaryIndex !in items.indices) {
            lastPrimaryIndex = 0
        }
    }
    val hasSecondarySelection = selectedIndex >= items.size && selectedIndex < items.size + secondaryItems.size
    val collapsedTargetIndex = if (primarySelectedIndex >= 0) primarySelectedIndex else lastPrimaryIndex
    val lastSlotGeometry = remember(density, tabWidths, contentStart) {
        with(density) {
            var slotStart = contentStart.roundToPx()
            for (index in 0 until tabWidths.lastIndex) slotStart += tabWidths[index].roundToPx()
            val slotWidth = tabWidths.last().roundToPx()
            slotStart.toFloat() to slotWidth.toFloat()
        }
    }
    val lastSlotStart = animateFloatAsState(lastSlotGeometry.first, RailMorphMotion, label = "railLastSlotStart")
    val lastSlotWidth = animateFloatAsState(lastSlotGeometry.second, RailMorphMotion, label = "railLastSlotWidth")
    val interaction = remember(widths.size, startPx, endPx, scope) {
        NavigationRailInteraction(widths, startPx, endPx, collapsedTargetIndex, scope)
    }
    val currentOnSelect = rememberUpdatedState(onSelect)
    val currentExpanded = rememberUpdatedState(expanded)
    val currentHeaderBottom = rememberUpdatedState { scrollState.value + headerHeightPx * state.progress }
    val pressedIndex = interaction.pressedIndex
    val pressed = pressedIndex >= 0
    val indicatorSlotIndex = if (pressed) pressedIndex else collapsedTargetIndex
    val showIndicator = pressed || primarySelectedIndex >= 0 || hasSecondarySelection
    LaunchedEffect(interaction, widths, collapsedTargetIndex, pressed, interaction.releaseGeneration) {
        interaction.updateWidths(widths)
        if (!pressed && collapsedTargetIndex in items.indices) interaction.settle(collapsedTargetIndex)
    }
    Box(
        Modifier
            .layout { measurable, _ ->
                val fraction = state.progress.coerceIn(0f, 1f)
                val width = lerp(collapsedWidth().toPx(), expandedWidth.toPx(), fraction).roundToInt()
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
                    .selectableGroup()
                    .navigationRailGestures(
                        interaction = interaction,
                        layoutDirection = layoutDirection,
                        expanded = currentExpanded,
                        headerBottom = currentHeaderBottom,
                        onSelect = currentOnSelect,
                    ),
            ) {
                val scrollOffsetProvider = remember(scrollState) { { scrollState.value } }
                val showSecondary by remember(state, rowProgress, items.size) {
                    computedStateOf {
                        if (state.progress > 0f) return@computedStateOf true
                        for (i in items.size until rowProgress.size) {
                            if (rowProgress[i].value > 0f) return@computedStateOf true
                        }
                        false
                    }
                }
                if (showIndicator && indicatorSlotIndex in items.indices) {
                    val indicatorProgressIndex = if (!pressed && hasSecondarySelection) {
                        items.size + 1 + (selectedIndex - items.size)
                    } else {
                        indicatorSlotIndex
                    }
                    val indicatorRowProgress = remember(rowProgress, indicatorProgressIndex) {
                        { rowProgress[indicatorProgressIndex].value }
                    }
                    val targetExpandedY = with(density) {
                        if (!pressed && hasSecondarySelection) {
                            (secondaryTop + RailRowPitch * (selectedIndex - items.size)).toPx()
                        } else {
                            (RailExpandedRowTop + RailRowPitch * indicatorSlotIndex).toPx()
                        }
                    }
                    val expandedYProvider = remember(targetExpandedY) { { targetExpandedY } }
                    NavigationRailIndicator(
                        rowProgress = indicatorRowProgress,
                        expandedY = expandedYProvider,
                        scrollOffset = scrollOffsetProvider,
                        interaction = interaction,
                        itemWidth = itemWidth,
                        color = indicatorColor,
                        pressed = pressed,
                        pressedColor = indicatorPressedColor,
                    )
                }
                items.forEachIndexed { position, item ->
                    key(position) {
                        val rowSelected = position == selectedIndex ||
                            (!expanded && hasSecondarySelection && position == lastPrimaryIndex)
                        NavigationRailRow(
                            item = item,
                            selected = rowSelected,
                            position = position,
                            slot = interaction.slots[position],
                            interactionSource = interaction.rowInteractions[position],
                            expanded = expanded,
                            pressed = pressedIndex == position,
                            rowProgress = { rowProgress[position].value },
                            iconAlpha = iconAlpha,
                            scrollOffset = scrollOffsetProvider,
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
                if (secondaryItems.isNotEmpty() && showSecondary) {
                    val dividerProgress = remember(rowProgress, items.size) { { rowProgress[items.size].value } }
                    val lastSlotStartProvider = remember(lastSlotStart) { { lastSlotStart.value } }
                    val lastSlotWidthProvider = remember(lastSlotWidth) { { lastSlotWidth.value } }
                    HorizontalDivider(
                        modifier = Modifier
                            .layout { measurable, constraints ->
                                val fraction = dividerProgress().coerceIn(0f, 1f)
                                val paddingPx = RailItemContentPadding.toPx()
                                val collapsedDividerWidth = (lastSlotWidthProvider() - paddingPx * 2).coerceAtLeast(0f)
                                val expandedDividerWidth = (itemWidth - RailItemContentPadding * 2).toPx()
                                val width = lerp(collapsedDividerWidth, expandedDividerWidth, fraction)
                                    .roundToInt()
                                    .coerceAtLeast(0)
                                val x = lerp(
                                    lastSlotStartProvider() + paddingPx,
                                    (RailItemHorizontalMargin + RailItemContentPadding).toPx(),
                                    fraction,
                                ).roundToInt()
                                val expandedDividerY =
                                    (RailExpandedRowTop + RailRowPitch * items.size + (RailDividerSectionHeight - 0.5.dp) / 2)
                                        .toPx()
                                val y = if (fraction == 1f) {
                                    expandedDividerY.roundToInt()
                                } else {
                                    lerp(
                                        RailRowTop.toPx() + scrollOffsetProvider() + RailCollapsedRowHeight.toPx() / 2f,
                                        expandedDividerY,
                                        fraction,
                                    ).roundToInt()
                                }
                                val placeable = measurable.measure(
                                    constraints.copy(minWidth = width, maxWidth = width),
                                )
                                layout(constraints.maxWidth, constraints.maxHeight) {
                                    placeable.placeRelative(x, y)
                                }
                            }
                            .graphicsLayer {
                                val fraction = dividerProgress().coerceIn(0f, 1f)
                                this.alpha = ((fraction - 0.35f) / 0.65f).coerceIn(0f, 1f)
                            },
                        thickness = 0.5.dp,
                    )
                    secondaryItems.forEachIndexed { secondaryPosition, item ->
                        key(secondaryPosition) {
                            val globalIndex = items.size + secondaryPosition
                            val secondaryProgress = remember(rowProgress, items.size, secondaryPosition) {
                                { rowProgress[items.size + 1 + secondaryPosition].value }
                            }
                            val onSecondaryClick = remember(globalIndex) {
                                { currentOnSelect.value(globalIndex) }
                            }
                            SecondaryNavigationRailRow(
                                item = item,
                                selected = globalIndex == selectedIndex,
                                secondaryPosition = secondaryPosition,
                                expanded = expanded,
                                rowProgress = secondaryProgress,
                                scrollOffset = scrollOffsetProvider,
                                lastSlotStart = lastSlotStartProvider,
                                lastSlotWidth = lastSlotWidthProvider,
                                itemWidth = itemWidth,
                                secondaryTop = secondaryTop,
                                selectedColor = selectedColor,
                                unselectedColor = unselectedColor,
                                labelStyle = labelStyle,
                                onClick = onSecondaryClick,
                            )
                        }
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
                            x = lerp(RailCollapsedToggleBoxStart.toPx(), RailExpandedToggleStart.toPx(), fraction).fastRoundToInt(),
                            y = lerp(0f, RailExpandedToggleTop.toPx(), fraction).fastRoundToInt(),
                        )
                    }
                    .size(RailToggleBoxSize),
                contentAlignment = Alignment.Center,
            ) {
                RailToggle(
                    onClick = { state.expanded = !expanded },
                    expanded = expanded,
                    size = if (expanded) RailToggleBoxSize else RailCollapsedRowHeight,
                    tint = unselectedColor,
                    contentDescription = toggleContentDescription,
                )
            }
        }
    }
}

/** The expand/collapse toggle with its embedded top-bar or sidebar glyph. */
@Composable
private fun RailToggle(
    onClick: () -> Unit,
    expanded: Boolean,
    size: Dp,
    tint: Color,
    contentDescription: String,
) {
    val collapsedInteractionSource = remember { MutableInteractionSource() }
    val collapsedPressed by collapsedInteractionSource.collectIsPressedAsState()
    val collapsedContentAlpha = animateFloatAsState(
        targetValue = if (collapsedPressed) GlassTopAppBarDefaults.PressedContentAlpha else 1f,
        animationSpec = if (collapsedPressed) GlassMotion.pressDown() else GlassMotion.pressUp(),
        label = "railCollapsedToggleContentAlpha",
    )
    val topBarAlpha = animateFloatAsState(
        targetValue = if (expanded) 1f else 0f,
        animationSpec = GlassMotion.navShowHide(),
        label = "railTopBarIconAlpha",
    )
    val sideBarAlpha = animateFloatAsState(
        targetValue = if (expanded) 0f else 1f,
        animationSpec = GlassMotion.navShowHide(),
        label = "railSideBarIconAlpha",
    )
    if (expanded) {
        GlassIconButton(
            onClick = onClick,
            inheritTopBarMaterial = false,
            size = size,
            surfaceAlpha = 0f,
            shadow = null,
        ) {
            RailToggleGlyph(
                tint = tint,
                topBarAlpha = { topBarAlpha.value },
                sideBarAlpha = { sideBarAlpha.value },
                contentAlpha = { 1f },
                contentDescription = contentDescription,
            )
        }
    } else {
        Box(
            modifier = Modifier
                .size(size)
                .clickable(
                    interactionSource = collapsedInteractionSource,
                    indication = null,
                    onClick = onClick,
                )
                .semantics { this.contentDescription = contentDescription },
            contentAlignment = Alignment.Center,
        ) {
            RailToggleGlyph(
                tint = tint,
                topBarAlpha = { topBarAlpha.value },
                sideBarAlpha = { sideBarAlpha.value },
                contentAlpha = { collapsedContentAlpha.value },
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun RailToggleGlyph(
    tint: Color,
    topBarAlpha: () -> Float,
    sideBarAlpha: () -> Float,
    contentAlpha: () -> Float,
    contentDescription: String?,
) {
    Box(contentAlignment = Alignment.Center) {
        Icon(
            imageVector = RailTopBarIcon,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(RailIconSize)
                .graphicsLayer { alpha = topBarAlpha() * contentAlpha() },
            tint = tint,
        )
        Icon(
            imageVector = RailSideBarIcon,
            contentDescription = null,
            modifier = Modifier
                .size(RailIconSize)
                .graphicsLayer { alpha = sideBarAlpha() * contentAlpha() },
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
    slot: NavigationRailSlot,
    interactionSource: MutableInteractionSource,
    expanded: Boolean,
    pressed: Boolean,
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
    val badgeVisibility = animateFloatAsState(
        targetValue = if (item.badge != null) 1f else 0f,
        animationSpec = RailMorphMotion,
        label = "railBadgeVisibility",
    )
    val pressAlpha = animateFloatAsState(
        targetValue = if (pressed) GlassNavigationBarDefaults.PressedAlpha else 1f,
        animationSpec = GlassMotion.navContentFloat(),
        label = "glassNavigationRailPress",
    )
    val density = LocalDensity.current
    val geometry = remember(density, position, tabWidths, contentStart, labelWidth, itemWidth) {
        with(density) {
            val padding = RailItemContentPadding.roundToPx()
            val labelStart = padding + RailIconColumnWidth.roundToPx()
            var slotStart = contentStart.roundToPx()
            for (index in 0 until position) slotStart += tabWidths[index].roundToPx()
            val slotWidth = tabWidths[position].roundToPx()
            val labelPx = labelWidth.roundToPx().coerceAtMost(slotWidth)
            val expandedLabelWidth = (itemWidth - RailItemContentPadding * 2 - RailIconColumnWidth).roundToPx()
            RailRowGeometry(
                slotStart = slotStart.toFloat(),
                slotWidth = slotWidth.toFloat(),
                sourceStart = (slotStart + ((slotWidth - labelPx) / 2f).roundToInt() - labelStart).toFloat(),
                sourceWidth = (labelStart + labelPx + padding).toFloat(),
                expandedTop = (RailExpandedRowTop + RailRowPitch * position).toPx(),
                labelMinWidth = if (labelWidth.roundToPx() > slotWidth) minOf(labelPx, expandedLabelWidth) else expandedLabelWidth,
            )
        }
    }
    val slotStart = animateFloatAsState(geometry.slotStart, RailMorphMotion, label = "railSlotStart")
    val slotWidth = animateFloatAsState(geometry.slotWidth, RailMorphMotion, label = "railSlotWidth")
    val sourceStart = animateFloatAsState(geometry.sourceStart, RailMorphMotion, label = "railSourceStart")
    val sourceWidth = animateFloatAsState(geometry.sourceWidth, RailMorphMotion, label = "railSourceWidth")
    // This box is the actual slot, not the wider row retaining its invisible icon column.
    Box(
        modifier = Modifier
            .layout { measurable, constraints ->
                val fraction = rowProgress().coerceIn(0f, 1f)
                val width = lerp(slotWidth.value, itemWidth.toPx(), fraction).roundToInt().coerceAtLeast(0)
                val height = (RailCollapsedRowHeight + RailRowGrowth * fraction).roundToPx()
                val x = lerp(slotStart.value, RailItemHorizontalMargin.toPx(), fraction).roundToInt()
                // The scrolling parent places settled rows; only the morph needs scroll compensation.
                val y = if (fraction == 1f) {
                    geometry.expandedTop.roundToInt()
                } else {
                    lerp(RailRowTop.toPx() + scrollOffset(), geometry.expandedTop, fraction).roundToInt()
                }
                slot.left = x.toFloat()
                slot.top = y.toFloat()
                slot.width = width.toFloat()
                slot.height = height.toFloat()
                val placeable = measurable.measure(Constraints.fixed(width, height))
                layout(constraints.maxWidth, constraints.maxHeight) { placeable.placeRelative(x, y) }
            }
            .clip(CircleShape)
            .indication(interactionSource, LocalIndication.current)
            .hoverable(interactionSource, enabled = expanded)
            .semantics(mergeDescendants = true) {
                role = Role.Tab
                this.selected = selected
                (item.contentDescription ?: item.label)?.let { contentDescription = it }
                onClick {
                    onClick()
                    true
                }
            }
            .onKeyEvent { event ->
                if (event.key == Key.Enter || event.key == Key.NumPadEnter || event.key == Key.Spacebar) {
                    if (event.type == KeyEventType.KeyUp) onClick()
                    true
                } else {
                    false
                }
            }
            .focusable(interactionSource = interactionSource)
            .graphicsLayer { alpha = pressAlpha.value },
    ) {
        Layout(
            modifier = Modifier
                .layout { measurable, constraints ->
                    val fraction = rowProgress().coerceIn(0f, 1f)
                    val width = lerp(sourceWidth.value, itemWidth.toPx(), fraction).roundToInt().coerceAtLeast(0)
                    val placeable = measurable.measure(Constraints.fixed(width, constraints.maxHeight))
                    val x = lerp(sourceStart.value - slotStart.value, 0f, fraction).roundToInt()
                    layout(constraints.maxWidth, constraints.maxHeight) { placeable.placeRelative(x, 0) }
                }
                .padding(horizontal = RailItemContentPadding),
            content = {
                Box(
                    modifier = Modifier
                        .width(RailIconColumnWidth)
                        .graphicsLayer { this.alpha = iconAlpha() },
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        modifier = Modifier.size(RailSideIconSize),
                        tint = tint,
                    )
                }
                if (item.label != null) {
                    Text(
                        text = item.label,
                        modifier = Modifier.layout { measurable, constraints ->
                            val placeable = measurable.measure(
                                // Keep rounding from truncating a fitting label, without exceeding either endpoint.
                                constraints.copy(maxWidth = maxOf(constraints.maxWidth, geometry.labelMinWidth)),
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
                }
                AnimatedContent(
                    targetState = item.badge,
                    contentKey = { it != null },
                    contentAlignment = Alignment.Center,
                    transitionSpec = {
                        (fadeIn(RailMorphMotion) togetherWith fadeOut(RailMorphMotion)).using(null)
                    },
                    label = "railBadge",
                ) { badge ->
                    Box { badge?.invoke() }
                }
            },
        ) { measurables, constraints ->
            val icon = measurables[0].measure(constraints.copy(minWidth = 0, minHeight = 0))
            val label = if (item.label != null) {
                measurables[1].measure(
                    constraints.copy(
                        minWidth = 0,
                        minHeight = 0,
                        maxWidth = (constraints.maxWidth - icon.width).coerceAtLeast(0),
                    ),
                )
            } else {
                null
            }
            val badge = measurables.last().measure(constraints.copy(minWidth = 0, minHeight = 0))
            layout(constraints.maxWidth, constraints.maxHeight) {
                val fraction = rowProgress().coerceIn(0f, 1f)
                icon.placeRelative(0, (constraints.maxHeight - icon.height) / 2)
                val visibility = badgeVisibility.value
                val badgeGap = RailBadgeSpacing.toPx() * visibility
                val badgeWidth = badge.width * visibility
                val badgeSpace = badgeWidth + badgeGap
                val labelShift = -badgeSpace / 2 * (1f - fraction)
                label?.placeRelative((icon.width + labelShift).roundToInt(), (constraints.maxHeight - label.height) / 2)
                val hasContent = badge.width > BadgeDefaults.Size.roundToPx()
                val horizontalOverlap = if (hasContent) 12.dp.roundToPx() else 6.dp.roundToPx()
                val verticalOverlap = if (hasContent) 14.dp.roundToPx() else 6.dp.roundToPx()
                val anchorEnd = RailSideIconSize.toPx()
                val anchorHeight = icon.height.toFloat()
                // Clamp to the actual animated slot, not the wider row retaining its icon column.
                val rowOffset = lerp(sourceStart.value - slotStart.value, 0f, fraction) + RailItemContentPadding.toPx()
                val currentSlotWidth = lerp(slotWidth.value, itemWidth.toPx(), fraction)
                val end = (currentSlotWidth - rowOffset - badge.width - RailBadgeSpacing.toPx()).roundToInt()
                val start = (RailBadgeSpacing.toPx() - rowOffset).roundToInt()
                val collapsedX = icon.width + (label?.width ?: 0) + labelShift + badgeGap - (badge.width - badgeWidth) / 2
                val expandedX = anchorEnd - horizontalOverlap
                val x = lerp(collapsedX, expandedX, fraction).roundToInt().coerceIn(start, maxOf(start, end))
                val collapsedY = (constraints.maxHeight - badge.height) / 2f
                val expandedY = (constraints.maxHeight - anchorHeight) / 2 - badge.height + verticalOverlap
                val y = lerp(collapsedY, expandedY, fraction).roundToInt().coerceAtLeast(0)
                badge.placeRelative(x, y)
            }
        }
    }
}

@Composable
private fun SecondaryNavigationRailRow(
    item: GlassNavigationItem,
    selected: Boolean,
    secondaryPosition: Int,
    expanded: Boolean,
    rowProgress: () -> Float,
    scrollOffset: () -> Int,
    lastSlotStart: () -> Float,
    lastSlotWidth: () -> Float,
    itemWidth: Dp,
    secondaryTop: Dp,
    selectedColor: Color,
    unselectedColor: Color,
    labelStyle: TextStyle,
    onClick: () -> Unit,
) {
    val tint by animateColorAsState(
        targetValue = if (selected) selectedColor else unselectedColor,
        animationSpec = GlassMotion.navContent(),
        label = "glassNavigationRailSecondaryTint",
    )
    Box(
        modifier = Modifier
            .layout { measurable, constraints ->
                val fraction = rowProgress().coerceIn(0f, 1f)
                val width = lerp(lastSlotWidth(), itemWidth.toPx(), fraction)
                    .roundToInt()
                    .coerceAtLeast(0)
                val height = (RailCollapsedRowHeight + RailRowGrowth * fraction).roundToPx()
                val x = lerp(
                    lastSlotStart(),
                    RailItemHorizontalMargin.toPx(),
                    fraction,
                ).roundToInt()
                val expandedRowY = (secondaryTop + RailRowPitch * secondaryPosition).toPx()
                val y = if (fraction == 1f) {
                    expandedRowY.roundToInt()
                } else {
                    lerp(
                        RailRowTop.toPx() + scrollOffset(),
                        expandedRowY,
                        fraction,
                    ).roundToInt()
                }
                val placeable = measurable.measure(Constraints.fixed(width, height))
                layout(constraints.maxWidth, constraints.maxHeight) {
                    placeable.placeRelative(x, y)
                }
            }
            .graphicsLayer {
                val fraction = rowProgress().coerceIn(0f, 1f)
                this.alpha = ((fraction - 0.35f) / 0.65f).coerceIn(0f, 1f)
            }
            .clip(CircleShape)
            .clickable(
                enabled = expanded,
                role = Role.Tab,
                onClick = onClick,
            )
            .semantics(mergeDescendants = true) {
                this.selected = selected
                (item.contentDescription ?: item.label)?.let { contentDescription = it }
            }
            .padding(horizontal = RailItemContentPadding),
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier.layout { measurable, constraints ->
                val expandedInnerWidth = (itemWidth - RailItemContentPadding * 2).roundToPx().coerceAtLeast(0)
                val placeable = measurable.measure(
                    constraints.copy(minWidth = 0, maxWidth = maxOf(constraints.maxWidth, expandedInnerWidth)),
                )
                layout(placeable.width.coerceAtMost(constraints.maxWidth), placeable.height) {
                    placeable.placeRelative(0, 0)
                }
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(RailIconColumnWidth - RailSideIconSize),
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier = Modifier.size(RailSideIconSize),
                tint = tint,
            )
            item.label?.let { label ->
                Text(
                    text = label,
                    style = labelStyle,
                    color = tint,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

/** The same pressed indicator follows the current slot through the entire morph. */
@Composable
private fun NavigationRailIndicator(
    rowProgress: () -> Float,
    expandedY: () -> Float,
    scrollOffset: () -> Int,
    interaction: NavigationRailInteraction,
    itemWidth: Dp,
    color: Color,
    pressed: Boolean,
    pressedColor: Color,
) {
    GlassNavigationIndicator(
        pressed = pressed,
        height = RailCollapsedRowHeight,
        color = color,
        pressedColor = pressedColor,
        modifier = Modifier.layout { measurable, constraints ->
            val fraction = rowProgress().coerceIn(0f, 1f)
            val width: Int
            val x: Float
            val y: Float
            val targetY = expandedY()
            if (fraction == 1f) {
                // Collapsed springs and scroll compensation cannot affect the expanded indicator.
                width = itemWidth.roundToPx().coerceAtLeast(0)
                x = RailItemHorizontalMargin.toPx()
                y = targetY
            } else {
                val bounds = navigationIndicatorBounds(
                    interaction.left.value,
                    interaction.right.value,
                    interaction.width,
                    interaction.contentStart,
                    interaction.endPadding,
                )
                width = lerp(bounds.y - bounds.x, itemWidth.toPx(), fraction).roundToInt().coerceAtLeast(0)
                x = lerp(bounds.x, RailItemHorizontalMargin.toPx(), fraction)
                y = lerp(RailRowTop.toPx() + scrollOffset(), targetY, fraction)
            }
            val height = (RailCollapsedRowHeight + RailRowGrowth * fraction).roundToPx()
            val placeable = measurable.measure(Constraints.fixed(width, height))
            layout(constraints.maxWidth, constraints.maxHeight) {
                placeable.placeRelative(x.roundToInt(), y.roundToInt())
            }
        },
    )
}

/** A row's fixed flight ends, in pixels. */
private class RailRowGeometry(
    val slotStart: Float,
    val slotWidth: Float,
    val sourceStart: Float,
    val sourceWidth: Float,
    val expandedTop: Float,
    val labelMinWidth: Int,
)

/** A corner that eases from the capsule's half-height to the panel's radius as [progress] rises. */
@Stable
private class RailMorphCorner(private val progress: () -> Float) : CornerSize {
    override fun toPx(shapeSize: Size, density: Density): Float = with(density) {
        lerp(RailCollapsedCornerRadius.toPx(), RailExpandedCornerRadius.toPx(), progress().coerceIn(0f, 1f))
    }
}

/** Animates expand/collapse; other geometry changes snap unless [targetChangeSpec] is supplied. */
@Composable
private fun rememberRailMotion(
    target: Dp,
    expanded: Boolean,
    targetChangeSpec: AnimationSpec<Dp>? = null,
    spec: (expanding: Boolean) -> AnimationSpec<Dp>,
): Animatable<Dp, AnimationVector1D> {
    val animatable = remember { Animatable(target, Dp.VectorConverter) }
    val currentTarget by rememberUpdatedState(target)
    val currentExpanded by rememberUpdatedState(expanded)
    val currentSpec by rememberUpdatedState(spec)
    val currentTargetChangeSpec by rememberUpdatedState(targetChangeSpec)
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
                val targetSpec = currentTargetChangeSpec
                if (targetSpec != null) animatable.animateTo(value, targetSpec) else animatable.snapTo(value)
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
private val RailSideIconSize = RailIconSize + 4.dp
private val RailIconColumnWidth = RailIconSize + 12.dp
private val RailBadgeSpacing = 4.dp
private val RailItemContentPadding = 12.dp
private val RailItemHorizontalMargin = 12.dp
private val RailCollapsedRowHeight = GlassNavigationRailDefaults.CollapsedHeight -
    GlassNavigationBarDefaults.IndicatorPaddingVertical * 2
private val RailRowGrowth = 4.dp
private val RailDividerSectionHeight = 17.dp
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
private val RailCollapsedToggleBoxStart = GlassNavigationBarDefaults.ContentPaddingHorizontal - 2.dp
private val RailExpandedToggleStart = 16.dp
private val RailExpandedToggleTop = 12.dp
private val RailCollapsedEndPadding = 3.dp

/** A collapsed slot's padding around its label, the bar's indicator reach on each side. */
private val RailSlotPadding = RailIconSize + GlassNavigationBarDefaults.IndicatorOverhang * 2
private val RailCollapsedSideReserve = 81.dp
private val RailMinCollapsedWidth = 192.dp
