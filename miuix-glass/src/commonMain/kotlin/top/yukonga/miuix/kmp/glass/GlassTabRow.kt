// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.roundToInt

/**
 * The colours one tab wears in each of its states.
 *
 * @property selectedContainerColor Fill of the selected tab.
 * @property selectedContentColor Its label.
 * @property restingContainerColor Fill of every other tab before the material fades in. The source
 *   control carries two sets of colours and swaps between them when its parent starts to blur: an
 *   opaque one for a page at rest, and the glass one below once content is passing underneath.
 * @property containerColor Fill of every other tab once the material is up. [Color.Transparent]
 *   leaves the glass showing, which is what the source system's own bars do.
 * @property contentColor Their labels.
 * @property pressedOverlayColor Painted over whichever tab is under a finger, whether or not it is
 *   the selected one.
 */
@Immutable
data class GlassTabColors(
    val selectedContainerColor: Color,
    val selectedContentColor: Color,
    val restingContainerColor: Color,
    val containerColor: Color,
    val contentColor: Color,
    val pressedOverlayColor: Color,
)

/** Default values for [GlassTabRow]. */
object GlassTabRowDefaults {

    /** Height of a tab: `filter_sort_tab_view2_min_height`. */
    val Height: Dp = 40.dp

    /**
     * Height the source system's own content apps give the row instead.
     *
     * Shorter than [Height]. Pair it with [neutralColors] — the two go together on the pages that
     * use them.
     */
    val NeutralHeight: Dp = 35.dp

    /** Gap between two tabs: `filter_sort_view2_tab_gap`. */
    val TabGap: Dp = 12.dp

    /** Horizontal padding inside a tab: `filter_sort_tab_view2_padding_horizontal`. */
    val TabPaddingHorizontal: Dp = 16.dp

    /** Vertical padding inside a tab: `filter_sort_tab_view2_padding_vertical`. */
    val TabPaddingVertical: Dp = 6.dp

    /** Each tab is a pill. The source asks for a 999dp radius, which is a true semicircular cap. */
    @Composable
    fun shape(height: Dp = Height): GlassShape = GlassShape(height / 2, smoothing = 0f)

    /** The material each tab is made of. */
    @Composable
    fun material(): GlassMaterial = GlassMaterials.puredThinGlass(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /** The rim traced around a tab. */
    @Composable
    fun stroke(): GlassStroke = GlassStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /**
     * The accented set: the selected tab carries the theme's own colour.
     *
     * The source keeps the *same* accent in both themes rather than lightening it for the dark one,
     * and leaves every other tab's fill transparent so the glass under it shows through. That is
     * why this set needs a material — without one the unselected tabs have no body at all.
     */
    @Composable
    fun primaryColors(
        selectedContainerColor: Color = MiuixTheme.colorScheme.primary,
        containerColor: Color = Color.Transparent,
    ): GlassTabColors {
        val isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f
        return GlassTabColors(
            selectedContainerColor = selectedContainerColor,
            selectedContentColor = if (isDark) Color.White.copy(alpha = 0.8f) else Color.White,
            restingContainerColor = if (isDark) Color(0xFF4A4A4A) else Color.White,
            containerColor = containerColor,
            contentColor = if (isDark) {
                Color.White.copy(alpha = 0.5f)
            } else {
                Color.Black.copy(alpha = 0.6f)
            },
            pressedOverlayColor = if (isDark) {
                Color.White.copy(alpha = 0.16f)
            } else {
                Color.Black.copy(alpha = 0.05f)
            },
        )
    }

    /**
     * The neutral set: the selected tab is a strong neutral rather than the theme's colour.
     *
     * Measured off a content app rather than taken from a token: the segmented control the source
     * system declares is the accented one, and its content apps restyle it. It leaves the accent
     * free for the content below, which on a page of artwork or wallpaper is the whole point.
     *
     * It rides the same ramp as the accented set. At rest the unselected tabs are an opaque grey
     * composited onto the page, and once the material is up they hand that body over to the glass
     * and go transparent. The source flips between the two on one frame; going across on the ramp
     * reads better and costs nothing.
     */
    @Composable
    fun neutralColors(): GlassTabColors {
        val isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f
        // The resting fill is composited onto the page rather than left translucent. At rest the
        // row has content passing under it and a six-percent wash would let that read through;
        // once the material is up the glass takes the job and the fill goes out altogether.
        val page = MiuixTheme.colorScheme.surface
        val fill = (if (isDark) Color.White else Color.Black).copy(alpha = 0.06f).compositeOver(page)
        return GlassTabColors(
            selectedContainerColor = if (isDark) Color(0xFFE6E6E6) else Color(0xFF383838),
            selectedContentColor = if (isDark) Color.Black else Color.White,
            restingContainerColor = fill,
            containerColor = Color.Transparent,
            contentColor = MiuixTheme.colorScheme.onSurface,
            pressedOverlayColor = if (isDark) {
                Color.White.copy(alpha = 0.16f)
            } else {
                Color.Black.copy(alpha = 0.05f)
            },
        )
    }
}

/**
 * A row of tabs, each one its own pill.
 *
 * Not a track with an indicator sliding along it. The source system's segmented control puts every
 * tab on its own surface with a gap between them, and marks the current one by filling it. Nothing
 * travels between tabs at all.
 *
 * Two sets of colours come with it and they are not interchangeable.
 * [GlassTabRowDefaults.primaryColors] fills the selected tab with the theme's own colour and leaves
 * the rest transparent over the glass. [GlassTabRowDefaults.neutralColors] fills it with a strong
 * neutral instead, for a page whose content already owns the accent.
 *
 * @param tabs The tab labels, in order. Every tab takes an equal share of the row.
 * @param selectedIndex The index of the current tab.
 * @param onSelect Called with the index of a tapped tab.
 *  * @param backdrop The [Backdrop] supplying the content behind the glass. `null` makes the row a
 *   plain surface: no blur, no refraction, no rim. The neutral colours want that — the control they
 *   come from is not a glass one, and a material under a six-percent fill washes it out.
 * @param modifier The modifier applied to the row.
 * @param style The glass material.
 * @param alpha Opacity multiplier for the material.
 * @param surfaceAlpha How far the material has come up, 0 to 1. At 0 the tabs wear their resting
 *   fill and carry no glass at all; at 1 they are glass with the content passing under them. Drive
 *   it off the scroll, the same way a bar button's background comes in.
 * @param stroke Optional bloom stroke along each tab's rim.
 * @param shadow The shadow each tab casts. `null` removes it.
 * @param material Each tab's own body. `null` leaves the tabs at whatever the blurred backdrop is.
 * @param height Height of a tab. [GlassTabRowDefaults.NeutralHeight] goes with the neutral colours.
 * @param tabGap Gap between two tabs.
 * @param colors The colours the tabs wear.
 */
@Composable
fun GlassTabRow(
    tabs: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    surfaceAlpha: Float = 1f,
    stroke: GlassStroke? = GlassTabRowDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Float,
    material: GlassMaterial? = GlassTabRowDefaults.material(),
    height: Dp = GlassTabRowDefaults.Height,
    tabGap: Dp = GlassTabRowDefaults.TabGap,
    colors: GlassTabColors = GlassTabRowDefaults.primaryColors(),
) {
    if (tabs.isEmpty()) return
    val index = selectedIndex.coerceIn(0, tabs.lastIndex)
    val shape = GlassTabRowDefaults.shape(height)
    // The material and the resting fill are two ends of one ramp: as the glass comes up the fill
    // goes out from under it, so the tab never carries both at once.
    val ramp = surfaceAlpha.coerceIn(0f, 1f)

    Row(
        modifier = modifier.height(height),
        horizontalArrangement = Arrangement.spacedBy(tabGap),
    ) {
        tabs.forEachIndexed { position, label ->
            val selected = position == index
            val interactionSource = remember { MutableInteractionSource() }
            val pressed by interactionSource.collectIsPressedAsState()
            val target by animateColorAsState(
                targetValue = if (selected) colors.selectedContainerColor else colors.containerColor,
                animationSpec = GlassMotion.navContent(),
                label = "glassTabContainer",
            )
            // The selected tab keeps its fill through the whole ramp — the source never lets the
            // current tab go glass. Only the others hand their body over to the material.
            val container = if (selected) target else lerp(colors.restingContainerColor, target, ramp)
            val content by animateColorAsState(
                targetValue = if (selected) colors.selectedContentColor else colors.contentColor,
                animationSpec = GlassMotion.navContent(),
                label = "glassTabContent",
            )
            val overlay by animateColorAsState(
                targetValue = if (pressed) colors.pressedOverlayColor else Color.Transparent,
                animationSpec = if (pressed) GlassMotion.navPressEnter() else GlassMotion.navPressExit(),
                label = "glassTabOverlay",
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    // A Row lets its children keep their own height. Without this the tab is only
                    // as tall as its label plus the padding, and the row height goes unused.
                    .fillMaxHeight()
                    .then(
                        if (backdrop == null) {
                            Modifier.clip(shape)
                        } else {
                            Modifier
                                .glassShadow(shape, shadow, alpha * ramp)
                                .glass(
                                    backdrop = backdrop,
                                    shape = shape,
                                    style = style,
                                    alpha = alpha * ramp,
                                    material = material,
                                    stroke = stroke,
                                )
                        },
                    )
                    // Both fills go on inside the material's own clip, so a pill keeps its shape
                    // and a press reads on the selected tab as well as on the others.
                    .background(container)
                    .background(overlay)
                    .clickable(interactionSource = interactionSource, indication = null) {
                        onSelect(position)
                    }
                    .padding(
                        horizontal = GlassTabRowDefaults.TabPaddingHorizontal,
                        vertical = GlassTabRowDefaults.TabPaddingVertical,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = label,
                    // 14sp, as `filter_sort_tab_view2_text_size` asks. The current tab is set in
                    // the bold cut of the same size, not in a larger one.
                    style = if (selected) {
                        MiuixTheme.textStyles.subtitle
                    } else {
                        MiuixTheme.textStyles.body2
                    },
                    color = content,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

/** Default values for [GlassSegmentedTabRow]. */
object GlassSegmentedTabRowDefaults {

    /** Height of the track: a 34dp tab with [TrackPadding] above and below it. */
    val Height: Dp = 40.dp

    /** Inset between the track's rim and the tabs: `filter_sort_view_padding`. */
    val TrackPadding: Dp = 3.dp

    /** Gap between two tabs: `filter_sort_padding_between_tabs`. */
    val TabGap: Dp = 8.dp

    /** Horizontal padding inside a tab: `filter_sort_tab_view_padding_horizontal`. */
    val TabPaddingHorizontal: Dp = 16.dp

    /** The track is a pill, and so is the indicator inside it. */
    @Composable
    fun shape(height: Dp = Height): GlassShape = GlassShape(height / 2, smoothing = 0f)

    /** The track's body: `filter-sort-view-glass`, the token this control declares for itself. */
    @Composable
    fun material(): GlassMaterial = GlassMaterials.puredThinGlass(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /** The rim traced around the track. */
    @Composable
    fun stroke(): GlassStroke = GlassStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /**
     * Fill the track wears before the material comes up.
     *
     * Opaque, so that at rest nothing shows through the control at all. It is the same colour the
     * source gives a tab of the newer control in its non-blurred state.
     */
    @Composable
    fun restingTrackColor(): Color = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) {
        Color(0xFF4A4A4A)
    } else {
        Color.White
    }

    /**
     * Fill of the indicator behind the current tab.
     *
     * Six percent of the opposite tone, the same figure the bottom bar's capsule rests at. Over a
     * white track that lands on 240, which is what the source control measures.
     */
    @Composable
    fun indicatorColor(): Color = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) {
        Color.White.copy(alpha = 0.06f)
    } else {
        Color.Black.copy(alpha = 0.06f)
    }
}

/**
 * A row of tabs joined into one track.
 *
 * The older of the source system's two segmented controls, and the one its settings-style pages
 * use. The whole track is a single glass surface — one material, one rim, one shadow — and the
 * current tab is marked by an indicator that slides inside it. [GlassTabRow] is the newer control,
 * where each tab is its own surface with a gap between them and nothing travels.
 *
 * @param tabs The tab labels, in order. Every tab takes an equal share of the track.
 * @param selectedIndex The index of the current tab.
 * @param onSelect Called with the index of a tapped tab.
 * @param backdrop The [Backdrop] supplying the content behind the glass.
 * @param modifier The modifier applied to the track.
 * @param style The glass material.
 * @param alpha Opacity multiplier for the material.
 * @param surfaceAlpha How far the material has come up, 0 to 1. At 0 the track wears [trackColor]
 *   and carries no glass; at 1 it is glass and the content passes under it.
 * @param stroke Optional bloom stroke along the track's rim.
 * @param shadow The shadow the track casts. `null` removes it.
 * @param material The track's own body. `null` leaves it at whatever the blurred backdrop is.
 * @param height Height of the track.
 * @param trackColor Fill the track wears before the material comes up.
 * @param indicatorColor Fill of the indicator behind the current tab.
 * @param selectedContentColor Colour of the current tab's label.
 * @param contentColor Colour of the others.
 */
@Composable
fun GlassSegmentedTabRow(
    tabs: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    backdrop: Backdrop,
    modifier: Modifier = Modifier,
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    surfaceAlpha: Float = 1f,
    stroke: GlassStroke? = GlassSegmentedTabRowDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Float,
    material: GlassMaterial? = GlassSegmentedTabRowDefaults.material(),
    height: Dp = GlassSegmentedTabRowDefaults.Height,
    trackColor: Color = GlassSegmentedTabRowDefaults.restingTrackColor(),
    indicatorColor: Color = GlassSegmentedTabRowDefaults.indicatorColor(),
    selectedContentColor: Color = MiuixTheme.colorScheme.onSurface,
    contentColor: Color = MiuixTheme.colorScheme.onSurface,
) {
    if (tabs.isEmpty()) return
    val index = selectedIndex.coerceIn(0, tabs.lastIndex)
    val ramp = surfaceAlpha.coerceIn(0f, 1f)
    val trackShape = GlassSegmentedTabRowDefaults.shape(height)
    val innerHeight = height - GlassSegmentedTabRowDefaults.TrackPadding * 2
    val indicatorShape = GlassSegmentedTabRowDefaults.shape(innerHeight)

    var trackWidth by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val gap = with(density) { GlassSegmentedTabRowDefaults.TabGap.toPx() }
    // The source animates the indicator's X and its width together, on the default spring.
    val slot = if (trackWidth <= 0) 0f else (trackWidth - gap * (tabs.size - 1)) / tabs.size
    val targetLeft = index * (slot + gap)
    val offset = remember { Animatable(0f) }
    val span = remember { Animatable(0f) }

    LaunchedEffect(targetLeft, slot) {
        if (slot <= 0f) return@LaunchedEffect
        if (span.value <= 0f) {
            offset.snapTo(targetLeft)
            span.snapTo(slot)
            return@LaunchedEffect
        }
        launch { offset.animateTo(targetLeft, GlassMotion.default()) }
        launch { span.animateTo(slot, GlassMotion.default()) }
    }

    Box(
        modifier = modifier
            .height(height)
            .glassShadow(trackShape, shadow, alpha * ramp)
            .clip(trackShape)
            // Under the material, not over it: the track hands its body to the glass as the ramp
            // comes up, the same way each tab of a [GlassTabRow] does.
            .background(trackColor.copy(alpha = trackColor.alpha * (1f - ramp)))
            .glass(
                backdrop = backdrop,
                shape = trackShape,
                style = style,
                alpha = alpha * ramp,
                material = material,
                stroke = stroke,
            )
            .padding(GlassSegmentedTabRowDefaults.TrackPadding)
            .onSizeChanged { trackWidth = it.width },
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .layout { measurable, constraints ->
                    val width = span.value.roundToInt().coerceAtLeast(0)
                    val placeable = measurable.measure(constraints.copy(minWidth = width, maxWidth = width))
                    layout(constraints.maxWidth, placeable.height) {
                        placeable.placeRelative(IntOffset(offset.value.roundToInt(), 0))
                    }
                }
                .clip(indicatorShape)
                .background(indicatorColor),
        )
        Row(
            modifier = Modifier.fillMaxHeight().selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(GlassSegmentedTabRowDefaults.TabGap),
        ) {
            tabs.forEachIndexed { position, label ->
                val selected = position == index
                val content by animateColorAsState(
                    targetValue = if (selected) selectedContentColor else contentColor,
                    animationSpec = GlassMotion.navContent(),
                    label = "glassSegmentedTabContent",
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(indicatorShape)
                        .selectable(
                            selected = selected,
                            role = Role.Tab,
                            interactionSource = null,
                            indication = null,
                        ) { onSelect(position) }
                        .padding(horizontal = GlassSegmentedTabRowDefaults.TabPaddingHorizontal),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = label,
                        style = if (selected) {
                            MiuixTheme.textStyles.subtitle
                        } else {
                            MiuixTheme.textStyles.body2
                        },
                        color = content,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
