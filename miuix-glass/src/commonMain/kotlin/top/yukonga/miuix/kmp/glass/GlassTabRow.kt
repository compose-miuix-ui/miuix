// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
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
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
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
import top.yukonga.miuix.kmp.glass.internal.drawGlassRim
import top.yukonga.miuix.kmp.glass.internal.drawGlassStroke
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.roundToInt

/**
 * The colours one tab wears in each of its states.
 *
 * @property selectedContainerColor Fill of the selected tab.
 * @property selectedContentColor Its label.
 * @property restingContainerColor Fill of every other tab before the material fades in.
 * @property containerColor Fill of every other tab once the material is up. [Color.Transparent]
 *   leaves the glass showing.
 * @property contentColor Their labels.
 * @property pressedOverlayColor Painted over whichever tab is under a finger, selected or not.
 * @property restingStrokeAlpha How much extra rim an unselected tab wears before the material comes
 *   up. Once material is active both states take the bloom stroke instead.
 */
@Immutable
data class GlassTabColors(
    val selectedContainerColor: Color,
    val selectedContentColor: Color,
    val restingContainerColor: Color,
    val containerColor: Color,
    val contentColor: Color,
    val pressedOverlayColor: Color,
    val restingStrokeAlpha: Float,
)

/** Default values for [GlassTabRow]. */
object GlassTabRowDefaults {

    /** Height of a tab: `filter_sort_tab_view2_min_height`. */
    val Height: Dp = 40.dp

    /** The shorter height that [neutralColors] goes with. */
    val NeutralHeight: Dp = 35.dp

    /** Gap between two tabs: `filter_sort_view2_tab_gap`. */
    val TabGap: Dp = 12.dp

    /** Horizontal padding inside a tab: `filter_sort_tab_view2_padding_horizontal`. */
    val TabPaddingHorizontal: Dp = 16.dp

    /** Vertical padding inside a tab: `filter_sort_tab_view2_padding_vertical`. */
    val TabPaddingVertical: Dp = 6.dp

    /** How much white an unselected tab carries over a dark page, before the material comes up. */
    private val RestingAlpha: Float = 0.067f

    /** Each tab is a pill: a 999dp radius, which is a true semicircular cap. */
    @Composable
    fun shape(height: Dp = Height): GlassShape = GlassShape(height / 2, smoothing = 0f)

    /** `filter-sort-view2-*-glass`: 20dp mask blur with pured-thin-glass colour layers. */
    @Composable
    fun material(): GlassMaterial = GlassMaterials.puredThinGlass(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /** The middle bloom stroke declared by both selected and unselected tokens. */
    @Composable
    fun stroke(): GlassStroke = GlassStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /**
     * The accented set: the selected tab carries the theme's own colour, the same in both themes.
     * Material is applied to every tab; unselected tabs clear their background so it stays visible.
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
            restingContainerColor = if (isDark) {
                Color.White.copy(alpha = RestingAlpha).compositeOver(MiuixTheme.colorScheme.surface)
            } else {
                Color.White
            },
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
            restingStrokeAlpha = 1f,
        )
    }

    /**
     * The neutral set: the selected tab is a strong neutral rather than the theme's colour, for a
     * page whose content already owns the accent.
     *
     * It rides the same ramp as the accented set: at rest the unselected tabs are an opaque grey
     * over the page, and once the material is up they hand that body to the glass and go
     * transparent.
     */
    @Composable
    fun neutralColors(): GlassTabColors {
        val isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f
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
            restingStrokeAlpha = 0f,
        )
    }
}

/**
 * A row of tabs, each one its own pill, with nothing travelling between them.
 *
 * Two sets of colours ship with it and they are not interchangeable:
 * [GlassTabRowDefaults.primaryColors] for the theme's own colour, and
 * [GlassTabRowDefaults.neutralColors] for a page whose content already owns the accent.
 *
 * @param tabs The tab labels, in order. Every tab takes an equal share of the row.
 * @param selectedIndex The index of the current tab.
 * @param onSelect Called with the index of a tapped tab.
 * @param backdrop The [Backdrop] supplying the material. Inside [GlassTopAppBar] the bar's own
 *   backdrop and effective blur take precedence. `null` outside a bar uses the resting fills.
 * @param modifier The modifier applied to the row.
 * @param style Blur style used only when [material] is null.
 * @param alpha Opacity multiplier for the material colour layers, bloom stroke and shadow.
 * @param surfaceAlpha How far the material has come up, 0 to 1. Drive this from the page-overlap
 *   state rather than the title's expand state.
 * @param stroke Optional bloom stroke along each tab's rim. The default is Middle.
 * @param shadow The shadow each tab casts. `null` removes it.
 * @param material Each tab's own body. `null` leaves the tabs at the blur selected by [style].
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
    val ramp = surfaceAlpha.coerceIn(0f, 1f)
    val topBarContext = LocalGlassTopAppBarContext.current
    val resolvedBackdrop = topBarContext?.backdrop ?: backdrop
    val resolvedMaterial = material?.let {
        topBarContext?.material?.blurRadius?.let { blurRadius -> it.copy(blurRadius = blurRadius) } ?: it
    }
    val underlayMaterial = topBarContext?.underlayMaterial

    Row(
        modifier = modifier.height(height).selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(tabGap),
    ) {
        tabs.forEachIndexed { position, label ->
            key(label) {
                val selected = position == index
                val interactionSource = remember { MutableInteractionSource() }
                val pressed by interactionSource.collectIsPressedAsState()
                val target = animateColorAsState(
                    targetValue = if (selected) colors.selectedContainerColor else colors.containerColor,
                    animationSpec = GlassMotion.navContent(),
                    label = "glassTabContainer",
                )
                val content by animateColorAsState(
                    targetValue = if (selected) colors.selectedContentColor else colors.contentColor,
                    animationSpec = GlassMotion.navContent(),
                    label = "glassTabContent",
                )
                val overlay = animateColorAsState(
                    targetValue = if (pressed) colors.pressedOverlayColor else Color.Transparent,
                    animationSpec = if (pressed) GlassMotion.navPressEnter() else GlassMotion.navPressExit(),
                    label = "glassTabOverlay",
                )
                val rim = animateFloatAsState(
                    targetValue = if (selected) 0f else colors.restingStrokeAlpha,
                    animationSpec = GlassMotion.navContentFloat(),
                    label = "glassTabRim",
                )

                fun containerColor(): Color {
                    val settled = target.value
                    return if (selected) settled else lerp(colors.restingContainerColor, settled, ramp)
                }
                val materialSurface = if (resolvedBackdrop != null && underlayMaterial != null) {
                    Modifier
                        .glassShadow(shape, shadow, alpha * ramp)
                        .glassOnActionBar(
                            backdrop = resolvedBackdrop,
                            shape = shape,
                            style = style,
                            alpha = alpha * ramp,
                            material = resolvedMaterial,
                            underlayMaterial = underlayMaterial,
                            stroke = stroke,
                        )
                } else {
                    Modifier.glassPanel(
                        backdrop = resolvedBackdrop,
                        shape = shape,
                        style = style,
                        alpha = alpha * ramp,
                        material = resolvedMaterial,
                        stroke = stroke,
                        shadow = shadow,
                        shading = false,
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .then(materialSurface)
                        .drawBehind {
                            drawRect(containerColor())
                            drawRect(overlay.value)
                        }
                        .drawWithContent {
                            drawContent()
                            val restingRim = alpha * (1f - ramp) * rim.value
                            if (restingRim > 0.001f) {
                                if (stroke != null) {
                                    drawGlassStroke(shape, layoutDirection, stroke, restingRim)
                                }
                                drawGlassRim(shape, layoutDirection, style, containerColor(), restingRim)
                            }
                        }
                        .selectable(
                            selected = selected,
                            role = Role.Tab,
                            interactionSource = interactionSource,
                            indication = null,
                        ) { onSelect(position) }
                        .padding(
                            horizontal = GlassTabRowDefaults.TabPaddingHorizontal,
                            vertical = GlassTabRowDefaults.TabPaddingVertical,
                        ),
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

    /** `filter-sort-view-glass`: 20dp mask blur with pured-thin-glass colour layers. */
    @Composable
    fun material(): GlassMaterial = GlassMaterials.puredThinGlass(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /** The middle bloom stroke declared by the track token. */
    @Composable
    fun stroke(): GlassStroke = GlassStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /**
     * Fill the track wears before the material comes up. Opaque, so nothing shows through the
     * control at rest: one layer of [IndicatorAlpha] over the page.
     */
    @Composable
    fun restingTrackColor(): Color = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) {
        Color.White.copy(alpha = IndicatorAlpha).compositeOver(MiuixTheme.colorScheme.surface)
    } else {
        Color.White
    }

    /**
     * Fill of the indicator behind the current tab: the same layer again, over the track. Both must
     * be the one figure or the indicator stops separating from the track.
     */
    @Composable
    fun indicatorColor(): Color = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) {
        Color.White.copy(alpha = IndicatorAlpha)
    } else {
        Color.Black.copy(alpha = 0.06f)
    }

    /**
     * How much of the opposite tone one layer of the track carries, on a dark page: a byte alpha of
     * `0x22`, against `0x0F` on the light side.
     */
    private val IndicatorAlpha: Float = 0.133f
}

/**
 * A row of tabs joined into one track, with an indicator that slides inside it.
 *
 * @param tabs The tab labels, in order. Every tab takes an equal share of the track.
 * @param selectedIndex The index of the current tab.
 * @param onSelect Called with the index of a tapped tab.
 * @param backdrop The [Backdrop] supplying the material. Inside [GlassTopAppBar] the bar's own
 *   backdrop and effective blur take precedence.
 * @param modifier The modifier applied to the track.
 * @param style Blur style used only when [material] is null.
 * @param alpha Opacity multiplier for the material colour layers, bloom stroke and shadow.
 * @param surfaceAlpha Material strength from 0 to 1. At 0 the track restores [trackColor]; at 1 the
 *   material is fully visible. The indicator and labels remain visible throughout.
 * @param stroke Optional bloom stroke along the track's rim. The default is Middle.
 * @param shadow The shadow the track casts. `null` removes it.
 * @param material The track's own body. `null` leaves it at the blur selected by [style].
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
    val topBarContext = LocalGlassTopAppBarContext.current
    val resolvedBackdrop = topBarContext?.backdrop ?: backdrop
    val resolvedMaterial = material?.let {
        topBarContext?.material?.blurRadius?.let { blurRadius -> it.copy(blurRadius = blurRadius) } ?: it
    }
    val underlayMaterial = topBarContext?.underlayMaterial
    val trackShape = GlassSegmentedTabRowDefaults.shape(height)
    val innerHeight = height - GlassSegmentedTabRowDefaults.TrackPadding * 2
    val indicatorShape = GlassSegmentedTabRowDefaults.shape(innerHeight)

    var trackWidth by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val gap = with(density) { GlassSegmentedTabRowDefaults.TabGap.toPx() }
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

    val materialSurface = if (underlayMaterial != null) {
        Modifier.glassOnActionBar(
            backdrop = resolvedBackdrop,
            shape = trackShape,
            style = style,
            alpha = alpha * ramp,
            material = resolvedMaterial,
            underlayMaterial = underlayMaterial,
            stroke = stroke,
            enabled = ramp > 0f,
        )
    } else {
        Modifier.glass(
            backdrop = resolvedBackdrop,
            shape = trackShape,
            style = style,
            alpha = alpha * ramp,
            material = resolvedMaterial,
            stroke = stroke,
            enabled = ramp > 0f,
            shading = false,
        )
    }

    Box(
        modifier = modifier
            .height(height)
            .glassShadow(trackShape, shadow, alpha * ramp)
            .clip(trackShape)
            .then(materialSurface)
            .background(trackColor.copy(alpha = trackColor.alpha * (1f - ramp)))
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
                key(label) {
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
}
