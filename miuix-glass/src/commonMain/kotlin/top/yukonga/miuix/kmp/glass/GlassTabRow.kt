// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.fastRoundToInt
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.glass.internal.LocalGlassHeaderContext
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassEdgeStrokes
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassMaterials
import top.yukonga.miuix.kmp.material.GlassShadow
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.GlassStyle
import top.yukonga.miuix.kmp.material.ShaderBrushCache
import top.yukonga.miuix.kmp.material.drawGlassEdge
import top.yukonga.miuix.kmp.material.drawGlassRim
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.material.withUnderlayBlur
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.MiuixIndication
import kotlin.math.abs

/**
 * Colours used by a tab in each interaction state.
 *
 * @property selectedContainerColor Selected-tab fill.
 * @property selectedContentColor Selected-tab label colour.
 * @property restingContainerColor Unselected-tab fill before material appears.
 * @property containerColor Unselected-tab fill after material appears. [Color.Transparent] shows
 *   the glass body.
 * @property contentColor Unselected-tab label colour.
 * @property pressedOverlayColor Overlay for the tab under a finger.
 * @property restingStrokeAlpha Additional rim alpha before material appears.
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

    /** Tab height. */
    val Height: Dp = 40.dp

    /** The shorter height that [neutralColors] goes with. */
    val NeutralHeight: Dp = 35.dp

    /** Gap between tabs. */
    val TabGap: Dp = 12.dp

    /** Horizontal padding inside a tab. */
    val TabPaddingHorizontal: Dp = 16.dp

    /** Vertical padding inside a tab. */
    val TabPaddingVertical: Dp = 6.dp

    /** Minimum width of each tab in [GlassScrollableTabRow]. */
    val MinTabWidth: Dp = 76.dp

    /** How much white an unselected tab carries over a dark page, before the material comes up. */
    private val RestingAlpha: Float = 0.067f

    /** Each tab is a pill: a 999dp radius, which is a true semicircular cap. */
    @Composable
    fun shape(height: Dp = Height): GlassShape = remember(height) {
        GlassShape(height / 2, smoothing = 0f)
    }

    /** `filter-sort-view2-*-glass`: 20dp mask blur with pured-thin-glass colour layers. */
    @Composable
    fun material(): GlassMaterial = dynamicGlassMaterial(
        GlassMaterials.puredThinGlass(
            isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        ),
    )

    /** Bloom stroke for selected and unselected tabs. */
    @Composable
    @ReadOnlyComposable
    fun stroke(): EdgeStroke = GlassEdgeStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /**
     * The accented set: the selected tab carries the theme's own colour, paired with its on-primary
     * label colour. Material is applied to every tab; unselected tabs clear their background so it
     * stays visible.
     */
    @Composable
    fun primaryColors(
        selectedContainerColor: Color = MiuixTheme.colorScheme.primary,
        containerColor: Color = Color.Transparent,
    ): GlassTabColors {
        val isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f
        val isDynamic = MiuixTheme.isDynamicColor
        val surface = MiuixTheme.colorScheme.surface
        val surfaceContainer = MiuixTheme.colorScheme.surfaceContainer
        val onPrimary = MiuixTheme.colorScheme.onPrimary
        val onSurface = MiuixTheme.colorScheme.onSurface
        val unselectedContent = MiuixTheme.colorScheme.onSurfaceVariantSummary
        return remember(
            isDark,
            isDynamic,
            selectedContainerColor,
            containerColor,
            surface,
            surfaceContainer,
            onPrimary,
            onSurface,
            unselectedContent,
        ) {
            GlassTabColors(
                selectedContainerColor = selectedContainerColor,
                selectedContentColor = if (!isDynamic && isDark && onPrimary == Color.White) {
                    onPrimary.copy(alpha = 0.8f)
                } else {
                    onPrimary
                },
                restingContainerColor = if (isDark) {
                    (if (isDynamic) onSurface else Color.White)
                        .copy(alpha = RestingAlpha)
                        .compositeOver(surface)
                } else if (isDynamic) {
                    surfaceContainer
                } else {
                    Color.White
                },
                containerColor = containerColor,
                contentColor = unselectedContent,
                pressedOverlayColor = if (isDark) {
                    Color.White.copy(alpha = 0.16f)
                } else {
                    Color.Black.copy(alpha = 0.05f)
                },
                restingStrokeAlpha = 1f,
            )
        }
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
        val isDynamic = MiuixTheme.isDynamicColor
        val page = MiuixTheme.colorScheme.surface
        val onSurface = MiuixTheme.colorScheme.onSurface
        return remember(isDark, isDynamic, page, onSurface) {
            val fill = (
                if (isDynamic) {
                    onSurface
                } else if (isDark) {
                    Color.White
                } else {
                    Color.Black
                }
                )
                .copy(alpha = 0.06f)
                .compositeOver(page)
            GlassTabColors(
                selectedContainerColor = if (isDynamic) {
                    onSurface
                } else if (isDark) {
                    Color(0xFFE6E6E6)
                } else {
                    Color(0xFF383838)
                },
                selectedContentColor = if (isDynamic) {
                    page
                } else if (isDark) {
                    Color.Black
                } else {
                    Color.White
                },
                restingContainerColor = fill,
                containerColor = Color.Transparent,
                contentColor = onSurface,
                pressedOverlayColor = if (isDark) {
                    Color.White.copy(alpha = 0.16f)
                } else {
                    Color.Black.copy(alpha = 0.05f)
                },
                restingStrokeAlpha = 0f,
            )
        }
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
    stroke: EdgeStroke? = GlassTabRowDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Float,
    material: GlassMaterial? = GlassTabRowDefaults.material(),
    height: Dp = GlassTabRowDefaults.Height,
    tabGap: Dp = GlassTabRowDefaults.TabGap,
    colors: GlassTabColors = GlassTabRowDefaults.primaryColors(),
) {
    if (tabs.isEmpty()) return
    val index = selectedIndex.coerceIn(0, tabs.lastIndex)
    val shape = GlassTabRowDefaults.shape(height)
    val headerContext = LocalGlassHeaderContext.current
    val resolvedBackdrop = (headerContext?.backdrop ?: backdrop)?.takeIf { isRuntimeShaderSupported() }
    val underlayMaterial = headerContext?.underlayMaterial
    val resolvedMaterial = remember(material, underlayMaterial) { material?.withUnderlayBlur(underlayMaterial) }
    val targetRamp = if (resolvedBackdrop != null) surfaceAlpha.coerceIn(0f, 1f) else 0f
    val rampState = animateFloatAsState(
        targetValue = targetRamp,
        animationSpec = GlassTabMaterialTransitionSpec,
        label = "glassTabRamp",
    )
    val showGlass by remember(resolvedBackdrop) {
        computedStateOf { resolvedBackdrop != null && rampState.value > 0.001f }
    }

    Row(
        modifier = modifier.height(height).selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(tabGap),
    ) {
        tabs.forEachIndexed { position, label ->
            key(label) {
                val selected = position == index
                val interactionSource = remember { MutableInteractionSource() }
                val edgeBrushCache = remember { ShaderBrushCache() }
                val target = animateColorAsState(
                    targetValue = if (selected) colors.selectedContainerColor else colors.containerColor,
                    animationSpec = GlassMotion.navContent(),
                    label = "glassTabContainer",
                )
                val contentColorState = animateColorAsState(
                    targetValue = if (selected) colors.selectedContentColor else colors.contentColor,
                    animationSpec = GlassMotion.navContent(),
                    label = "glassTabContent",
                )
                val contentColorProducer = remember { ColorProducer { contentColorState.value } }
                val rim = animateFloatAsState(
                    targetValue = if (selected) 0f else colors.restingStrokeAlpha,
                    animationSpec = GlassMotion.navContentFloat(),
                    label = "glassTabRim",
                )

                fun containerColor(): Color {
                    val settled = target.value
                    return if (selected) {
                        settled
                    } else {
                        lerp(
                            colors.restingContainerColor,
                            settled,
                            if (resolvedBackdrop != null) rampState.value else 0f,
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .glassShadow(shape, shadow) { alpha * if (resolvedBackdrop != null) rampState.value else 1f }
                        .clip(shape)
                        .drawBehind {
                            if (!selected) {
                                drawRect(colors.restingContainerColor)
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    if (resolvedBackdrop != null) {
                        val glassModifier = if (underlayMaterial != null) {
                            Modifier.glassOnActionBar(
                                backdrop = resolvedBackdrop,
                                shape = shape,
                                style = style,
                                alpha = alpha,
                                material = resolvedMaterial,
                                underlayMaterial = underlayMaterial,
                                stroke = stroke,
                                enabled = showGlass,
                            )
                        } else {
                            Modifier.glass(
                                backdrop = resolvedBackdrop,
                                shape = shape,
                                style = style,
                                alpha = alpha,
                                material = resolvedMaterial,
                                stroke = stroke,
                                enabled = showGlass,
                                shading = false,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .graphicsLayer { this.alpha = if (showGlass) rampState.value else 0f }
                                .then(glassModifier),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .drawBehind {
                                val settled = target.value
                                val currentRamp = if (resolvedBackdrop != null) rampState.value else 0f
                                if (selected) {
                                    drawRect(settled)
                                } else if (settled.alpha > 0f) {
                                    val fillAlpha = if (settled != colors.containerColor) 1f else currentRamp
                                    if (fillAlpha > 0f) {
                                        drawRect(settled.copy(alpha = settled.alpha * fillAlpha))
                                    }
                                }
                            }
                            .drawWithContent {
                                drawContent()
                                val currentRamp = if (resolvedBackdrop != null) rampState.value else 0f
                                val restingRim = alpha * (1f - currentRamp) * rim.value
                                if (restingRim > 0.001f) {
                                    if (stroke != null) {
                                        drawGlassEdge(shape, stroke, style, containerColor(), restingRim, brushCache = edgeBrushCache)
                                    } else {
                                        drawGlassRim(shape, style, containerColor(), restingRim)
                                    }
                                }
                            }
                            .selectable(
                                selected = selected,
                                role = Role.Tab,
                                interactionSource = interactionSource,
                                indication = remember(colors.pressedOverlayColor) {
                                    MiuixIndication(
                                        color = colors.pressedOverlayColor.copy(alpha = 1f),
                                        pressAlpha = colors.pressedOverlayColor.alpha,
                                    )
                                },
                            ) { onSelect(position) }
                            .padding(
                                horizontal = GlassTabRowDefaults.TabPaddingHorizontal,
                                vertical = GlassTabRowDefaults.TabPaddingVertical,
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = label,
                            color = contentColorProducer,
                            style = if (selected) {
                                MiuixTheme.textStyles.subtitle
                            } else {
                                MiuixTheme.textStyles.body2
                            },
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
private val EmptyDrawWithContent = Modifier.drawWithContent {}

/**
 * A horizontally scrollable row of pill tabs, each one its own pill, sized to its label with a
 * [minTabWidth] floor instead of dividing the row equally.
 *
 * @param tabs The tab labels, in order.
 * @param selectedIndex The index of the current tab.
 * @param onSelect Called with the index of a tapped tab.
 * @param backdrop The [Backdrop] supplying the material. Inside [GlassTopAppBar] the bar's own
 *   backdrop and effective blur take precedence. `null` outside a bar uses the resting fills.
 * @param modifier The modifier applied to the row.
 * @param contentPadding Padding around the scrollable tab list inside the scroll container.
 * @param style Blur style used only when [material] is null.
 * @param alpha Opacity multiplier for the material colour layers, bloom stroke and shadow.
 * @param surfaceAlpha How far the material has come up, 0 to 1.
 * @param stroke Optional bloom stroke along each tab's rim. The default is Middle.
 * @param shadow The shadow each tab casts. `null` removes it.
 * @param material Each tab's own body. `null` leaves the tabs at the blur selected by [style].
 * @param height Height of a tab. [GlassTabRowDefaults.NeutralHeight] goes with the neutral colours.
 * @param tabGap Gap between two tabs.
 * @param minTabWidth Minimum width of each tab pill.
 * @param colors The colours the tabs wear.
 */
@Composable
fun GlassScrollableTabRow(
    tabs: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    surfaceAlpha: Float = 1f,
    stroke: EdgeStroke? = GlassTabRowDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Float,
    material: GlassMaterial? = GlassTabRowDefaults.material(),
    height: Dp = GlassTabRowDefaults.Height,
    tabGap: Dp = GlassTabRowDefaults.TabGap,
    minTabWidth: Dp = GlassTabRowDefaults.MinTabWidth,
    colors: GlassTabColors = GlassTabRowDefaults.primaryColors(),
) {
    if (tabs.isEmpty()) return
    val index = selectedIndex.coerceIn(0, tabs.lastIndex)
    val shape = GlassTabRowDefaults.shape(height)
    val headerContext = LocalGlassHeaderContext.current
    val resolvedBackdrop = (headerContext?.backdrop ?: backdrop)?.takeIf { isRuntimeShaderSupported() }
    val underlayMaterial = headerContext?.underlayMaterial
    val resolvedMaterial = remember(material, underlayMaterial) { material?.withUnderlayBlur(underlayMaterial) }
    val targetRamp = if (resolvedBackdrop != null) surfaceAlpha.coerceIn(0f, 1f) else 0f
    val rampState = animateFloatAsState(
        targetValue = targetRamp,
        animationSpec = GlassTabMaterialTransitionSpec,
        label = "scrollableGlassTabRamp",
    )
    val showGlass by remember(resolvedBackdrop) {
        computedStateOf { resolvedBackdrop != null && rampState.value > 0.001f }
    }

    // Reserve vertical bleed inside horizontalScroll so its clipToBounds never cuts tab shadows.
    Box(
        modifier = modifier.layout { measurable, constraints ->
            val padPx = ScrollableGlassTabShadowPadding.roundToPx()
            val looseConstraints = constraints.copy(
                minHeight = 0,
                maxHeight = if (constraints.maxHeight == Constraints.Infinity) {
                    Constraints.Infinity
                } else {
                    constraints.maxHeight + padPx * 2
                },
            )
            val placeable = measurable.measure(looseConstraints)
            val layoutHeight = (placeable.height - padPx * 2).coerceAtLeast(0)
            layout(placeable.width, layoutHeight) {
                placeable.placeRelative(0, -padPx)
            }
        },
    ) {
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(vertical = ScrollableGlassTabShadowPadding)
                .padding(contentPadding)
                .height(height)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(tabGap),
        ) {
            tabs.forEachIndexed { position, label ->
                key(label) {
                    val selected = position == index
                    val interactionSource = remember { MutableInteractionSource() }
                    val edgeBrushCache = remember { ShaderBrushCache() }
                    val target = animateColorAsState(
                        targetValue = if (selected) colors.selectedContainerColor else colors.containerColor,
                        animationSpec = GlassMotion.navContent(),
                        label = "scrollableGlassTabContainer",
                    )
                    val contentColorState = animateColorAsState(
                        targetValue = if (selected) colors.selectedContentColor else colors.contentColor,
                        animationSpec = GlassMotion.navContent(),
                        label = "scrollableGlassTabContent",
                    )
                    val contentColorProducer = remember { ColorProducer { contentColorState.value } }
                    val rim = animateFloatAsState(
                        targetValue = if (selected) 0f else colors.restingStrokeAlpha,
                        animationSpec = GlassMotion.navContentFloat(),
                        label = "scrollableGlassTabRim",
                    )

                    fun containerColor(): Color {
                        val settled = target.value
                        return if (selected) {
                            settled
                        } else {
                            lerp(
                                colors.restingContainerColor,
                                settled,
                                if (resolvedBackdrop != null) rampState.value else 0f,
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .widthIn(min = minTabWidth)
                            .glassShadow(shape, shadow, alpha * if (resolvedBackdrop != null) rampState.value else 1f)
                            .clip(shape)
                            .drawBehind {
                                if (!selected) {
                                    drawRect(colors.restingContainerColor)
                                }
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        if (resolvedBackdrop != null) {
                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .graphicsLayer { this.alpha = if (showGlass) rampState.value else 0f }
                                    .glass(
                                        backdrop = resolvedBackdrop,
                                        shape = shape,
                                        style = style,
                                        alpha = alpha,
                                        material = resolvedMaterial,
                                        stroke = stroke,
                                        enabled = showGlass,
                                        shading = false,
                                    ),
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .widthIn(min = minTabWidth)
                                .drawBehind {
                                    val settled = target.value
                                    val currentRamp = if (resolvedBackdrop != null) rampState.value else 0f
                                    if (selected) {
                                        drawRect(settled)
                                    } else if (settled.alpha > 0f) {
                                        val fillAlpha = if (settled != colors.containerColor) 1f else currentRamp
                                        if (fillAlpha > 0f) {
                                            drawRect(settled.copy(alpha = settled.alpha * fillAlpha))
                                        }
                                    }
                                }
                                .drawWithContent {
                                    drawContent()
                                    val currentRamp = if (resolvedBackdrop != null) rampState.value else 0f
                                    val restingRim = alpha * (1f - currentRamp) * rim.value
                                    if (restingRim > 0.001f) {
                                        if (stroke != null) {
                                            drawGlassEdge(shape, stroke, style, containerColor(), restingRim, brushCache = edgeBrushCache)
                                        } else {
                                            drawGlassRim(shape, style, containerColor(), restingRim)
                                        }
                                    }
                                }
                                .selectable(
                                    selected = selected,
                                    onClick = { onSelect(position) },
                                    role = Role.Tab,
                                    interactionSource = interactionSource,
                                    indication = remember { MiuixIndication() },
                                )
                                .padding(
                                    horizontal = GlassTabRowDefaults.TabPaddingHorizontal,
                                    vertical = GlassTabRowDefaults.TabPaddingVertical,
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (!selected) {
                                Text(
                                    text = label,
                                    style = MiuixTheme.textStyles.subtitle,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = EmptyDrawWithContent,
                                )
                            }
                            Text(
                                text = label,
                                color = contentColorProducer,
                                style = if (selected) {
                                    MiuixTheme.textStyles.subtitle
                                } else {
                                    MiuixTheme.textStyles.body2
                                },
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
}

private val ScrollableGlassTabShadowPadding = 20.dp

private val GlassTabMaterialTransitionSpec = tween<Float>(450, easing = LinearEasing)

/** Default values for [GlassSegmentedTabRow]. */
object GlassSegmentedTabRowDefaults {

    /** Height of the track: a 34dp tab with [TrackPadding] above and below it. */
    val Height: Dp = 40.dp

    /** Inset between the track rim and its tabs. */
    val TrackPadding: Dp = 3.dp

    /** Gap between tabs. */
    val TabGap: Dp = 8.dp

    /** Horizontal padding inside a tab. */
    val TabPaddingHorizontal: Dp = 16.dp

    /** The track is a pill, and so is the indicator inside it. */
    @Composable
    fun shape(height: Dp = Height): GlassShape = remember(height) {
        GlassShape(height / 2, smoothing = 0f)
    }

    /** `filter-sort-view-glass`: 20dp mask blur with pured-thin-glass colour layers. */
    @Composable
    fun material(): GlassMaterial = dynamicGlassMaterial(
        GlassMaterials.puredThinGlass(
            isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        ),
    )

    /** Bloom stroke for the track. */
    @Composable
    @ReadOnlyComposable
    fun stroke(): EdgeStroke = GlassEdgeStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /**
     * Fill the track wears before the material comes up. Opaque, so nothing shows through the
     * control at rest.
     */
    @Composable
    @ReadOnlyComposable
    fun restingTrackColor(): Color {
        val isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f
        return when {
            isDark -> {
                val tint = if (MiuixTheme.isDynamicColor) MiuixTheme.colorScheme.onSurface else Color.White
                tint.copy(alpha = IndicatorAlpha).compositeOver(MiuixTheme.colorScheme.surface)
            }

            MiuixTheme.isDynamicColor -> MiuixTheme.colorScheme.surfaceContainer

            else -> Color.White
        }
    }

    /** Fill of the indicator behind the current tab. */
    @Composable
    @ReadOnlyComposable
    fun indicatorColor(): Color = indicatorTint().copy(
        alpha = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) IndicatorAlpha else 0.06f,
    )

    /** Pressed indicator fill, using a higher opacity for touch feedback. */
    @Composable
    @ReadOnlyComposable
    fun indicatorPressedColor(): Color = indicatorTint().copy(
        alpha = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) PressedIndicatorAlpha else 0.14f,
    )

    @Composable
    @ReadOnlyComposable
    private fun indicatorTint(): Color = when {
        MiuixTheme.isDynamicColor -> MiuixTheme.colorScheme.primary
        MiuixTheme.colorScheme.background.luminance() < 0.5f -> Color.White
        else -> Color.Black
    }

    /**
     * How much of the opposite tone one layer of the track carries, on a dark page: a byte alpha of
     * `0x22`, against `0x0F` on the light side.
     */
    private val IndicatorAlpha: Float = 0.133f
    private val PressedIndicatorAlpha: Float = 0.26f
    internal const val PRESSED_CONTENT_ALPHA: Float = 0.6f
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
    stroke: EdgeStroke? = GlassSegmentedTabRowDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Float,
    material: GlassMaterial? = GlassSegmentedTabRowDefaults.material(),
    height: Dp = GlassSegmentedTabRowDefaults.Height,
    trackColor: Color = GlassSegmentedTabRowDefaults.restingTrackColor(),
    indicatorColor: Color = GlassSegmentedTabRowDefaults.indicatorColor(),
    selectedContentColor: Color = if (MiuixTheme.isDynamicColor) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onSurface,
    contentColor: Color = MiuixTheme.colorScheme.onSurface,
) {
    if (tabs.isEmpty()) return
    val index = selectedIndex.coerceIn(0, tabs.lastIndex)
    val runtimeShaderSupported = isRuntimeShaderSupported()
    val headerContext = LocalGlassHeaderContext.current
    val resolvedBackdrop = (headerContext?.backdrop ?: backdrop).takeIf { runtimeShaderSupported }
    val underlayMaterial = headerContext?.underlayMaterial
    val resolvedMaterial = remember(material, underlayMaterial) { material?.withUnderlayBlur(underlayMaterial) }
    val targetRamp = if (resolvedBackdrop != null) surfaceAlpha.coerceIn(0f, 1f) else 0f
    val rampState = animateFloatAsState(
        targetValue = targetRamp,
        animationSpec = GlassTabMaterialTransitionSpec,
        label = "glassSegmentedTabRamp",
    )
    val showGlass by remember(resolvedBackdrop) {
        computedStateOf { resolvedBackdrop != null && rampState.value > 0.001f }
    }
    val trackShape = GlassSegmentedTabRowDefaults.shape(height)
    val innerHeight = height - GlassSegmentedTabRowDefaults.TrackPadding * 2
    val indicatorShape = GlassSegmentedTabRowDefaults.shape(innerHeight)

    val trackWidthState = remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val gap = with(density) { GlassSegmentedTabRowDefaults.TabGap.toPx() }
    val scope = rememberCoroutineScope()
    val indicatorLeft = remember { Animatable(0f) }
    val indicatorRight = remember { Animatable(0f) }
    val pressedIndexState = remember { mutableIntStateOf(-1) }
    val dragging = remember { mutableStateOf(false) }
    var settleGeneration by remember { mutableIntStateOf(0) }
    val onSelectState by rememberUpdatedState(onSelect)

    fun animateIndicator(
        newLeft: Float,
        newRight: Float,
        leftSpring: SpringSpec<Float> = GlassMotion.edgeSpring(newLeft <= indicatorLeft.value),
        rightSpring: SpringSpec<Float> = GlassMotion.edgeSpring(newLeft > indicatorLeft.value),
    ) {
        if (indicatorLeft.targetValue == newLeft && indicatorRight.targetValue == newRight) return
        scope.launch { indicatorLeft.animateTo(newLeft, leftSpring) }
        scope.launch { indicatorRight.animateTo(newRight, rightSpring) }
    }

    LaunchedEffect(index, settleGeneration, tabs.size, gap) {
        snapshotFlow { trackWidthState.intValue }.collectLatest { currentTrackWidth ->
            if (dragging.value || currentTrackWidth <= 0) return@collectLatest
            val slot = ((currentTrackWidth - gap * (tabs.size - 1)) / tabs.size).coerceAtLeast(0f)
            if (slot <= 0f) return@collectLatest
            val targetLeft = index * (slot + gap)
            if (indicatorRight.value <= indicatorLeft.value) {
                indicatorLeft.snapTo(targetLeft)
                indicatorRight.snapTo(targetLeft + slot)
            } else {
                animateIndicator(targetLeft, targetLeft + slot)
            }
        }
    }
    val indicatorPressed by remember { computedStateOf { pressedIndexState.intValue >= 0 } }
    val targetIndicatorFill = if (indicatorPressed) {
        val defaultIndicatorColor = GlassSegmentedTabRowDefaults.indicatorColor()
        if (indicatorColor == defaultIndicatorColor) {
            GlassSegmentedTabRowDefaults.indicatorPressedColor()
        } else {
            indicatorColor.copy(alpha = (indicatorColor.alpha * 1.8f).coerceAtMost(0.45f))
        }
    } else {
        indicatorColor
    }
    val animatedIndicatorColor = animateColorAsState(
        targetValue = targetIndicatorFill,
        animationSpec = if (indicatorPressed) GlassMotion.navPressEnter() else GlassMotion.navPressExit(),
        label = "glassSegmentedTabIndicatorFill",
    )
    val indicatorScale = animateFloatAsState(
        targetValue = if (indicatorPressed) {
            glassPressScale(with(density) { innerHeight.toPx() }, density.density)
        } else {
            1f
        },
        animationSpec = if (indicatorPressed) GlassMotion.pressDown() else GlassMotion.pressUp(),
        label = "glassSegmentedTabIndicatorPress",
    )
    Box(
        modifier = modifier
            .height(height)
            .glassShadow(trackShape, shadow) { alpha * if (resolvedBackdrop != null) rampState.value else 1f }
            .clip(trackShape)
            .drawBehind { drawRect(trackColor) },
    ) {
        if (resolvedBackdrop != null) {
            val materialSurface = if (underlayMaterial != null) {
                Modifier.glassOnActionBar(
                    backdrop = resolvedBackdrop,
                    shape = trackShape,
                    style = style,
                    alpha = alpha,
                    material = resolvedMaterial,
                    underlayMaterial = underlayMaterial,
                    stroke = stroke,
                    enabled = showGlass,
                )
            } else {
                Modifier.glass(
                    backdrop = resolvedBackdrop,
                    shape = trackShape,
                    style = style,
                    alpha = alpha,
                    material = resolvedMaterial,
                    stroke = stroke,
                    enabled = showGlass,
                    shading = false,
                )
            }
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { this.alpha = if (showGlass) rampState.value else 0f }
                    .then(materialSurface),
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(GlassSegmentedTabRowDefaults.TrackPadding)
                .onSizeChanged { trackWidthState.intValue = it.width }
                .pointerInput(tabs.size, gap, layoutDirection) {
                    awaitEachGesture {
                        val down = awaitFirstDown(requireUnconsumed = false)
                        val width = size.width.toFloat()
                        val currentSlot = ((width - gap * (tabs.size - 1)) / tabs.size).coerceAtLeast(0f)
                        if (width <= 0f || currentSlot <= 0f) return@awaitEachGesture
                        fun logicalX(x: Float) = if (layoutDirection == LayoutDirection.Rtl) width - x else x
                        fun indexAt(x: Float): Int = (x / (currentSlot + gap)).toInt().coerceIn(0, tabs.lastIndex)

                        val downX = logicalX(down.position.x)
                        var selectionX = downX
                        var current = indexAt(downX)
                        pressedIndexState.intValue = current
                        dragging.value = true
                        val pressedLeft = current * (currentSlot + gap)
                        animateIndicator(pressedLeft, pressedLeft + currentSlot)
                        var grabOffset = Float.NaN
                        try {
                            while (true) {
                                val event = awaitPointerEvent()
                                val change = event.changes.firstOrNull { it.id == down.id } ?: break
                                if (change.isConsumed) break
                                if (!change.pressed) {
                                    if (change.changedToUp()) {
                                        val releaseDelta = change.position.x - change.previousPosition.x
                                        selectionX += if (layoutDirection == LayoutDirection.Rtl) -releaseDelta else releaseDelta
                                        onSelectState(indexAt(selectionX))
                                        change.consume()
                                    }
                                    break
                                }
                                val activeWidth = size.width.toFloat()
                                val activeSlot = ((activeWidth - gap * (tabs.size - 1)) / tabs.size).coerceAtLeast(0f)
                                val maxLeft = (activeWidth - activeSlot).coerceAtLeast(0f)
                                val x = logicalX(change.position.x)
                                val pointerDelta = change.position.x - change.previousPosition.x
                                val delta = if (layoutDirection == LayoutDirection.Rtl) -pointerDelta else pointerDelta
                                selectionX += delta
                                if (grabOffset.isNaN()) {
                                    if (abs(selectionX - downX) < viewConfiguration.touchSlop) {
                                        pressedIndexState.intValue = indexAt(selectionX)
                                        continue
                                    }
                                    grabOffset = downX - pressedLeft
                                }
                                val chipLeft = (x - grabOffset).coerceIn(0f, maxLeft)
                                val next = indexAt(selectionX)
                                val target = navigationDragTarget(chipLeft, activeSlot, 0f, activeWidth, delta, next != current)
                                animateIndicator(target.left, target.right, target.leftSpring, target.rightSpring)
                                current = next
                                pressedIndexState.intValue = next
                                change.consume()
                            }
                        } finally {
                            pressedIndexState.intValue = -1
                            dragging.value = false
                            settleGeneration++
                        }
                    }
                },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .layout { measurable, constraints ->
                        val bounds = if (indicatorRight.value <= indicatorLeft.value && constraints.maxWidth > 0) {
                            val slot = ((constraints.maxWidth.toFloat() - gap * (tabs.size - 1)) / tabs.size).coerceAtLeast(0f)
                            val targetLeft = index * (slot + gap)
                            navigationIndicatorBounds(
                                targetLeft,
                                targetLeft + slot,
                                constraints.maxWidth.toFloat(),
                                0f,
                            )
                        } else {
                            navigationIndicatorBounds(
                                indicatorLeft.value,
                                indicatorRight.value,
                                constraints.maxWidth.toFloat(),
                                0f,
                            )
                        }
                        val start = bounds.x.fastRoundToInt()
                        val width = (bounds.y.fastRoundToInt() - start).coerceAtLeast(0)
                        val placeable = measurable.measure(constraints.copy(minWidth = width, maxWidth = width))
                        layout(constraints.maxWidth, placeable.height) {
                            placeable.placeRelative(IntOffset(start, 0))
                        }
                    }
                    .graphicsLayer {
                        val scale = indicatorScale.value
                        val insetPx = (1f - scale) * size.height
                        scaleX = if (size.width > 0f) ((size.width - insetPx) / size.width).fastCoerceIn(0f, 1f) else 1f
                        scaleY = scale
                    }
                    .clip(indicatorShape)
                    .drawBehind { drawRect(animatedIndicatorColor.value) },
            )
            Row(
                modifier = Modifier.fillMaxHeight().selectableGroup(),
                horizontalArrangement = Arrangement.spacedBy(GlassSegmentedTabRowDefaults.TabGap),
            ) {
                val pressedIndexProvider = remember { { pressedIndexState.intValue } }
                tabs.forEachIndexed { position, label ->
                    key(label) {
                        GlassSegmentedTabItem(
                            label = label,
                            position = position,
                            selected = position == index,
                            pressedIndexProvider = pressedIndexProvider,
                            selectedContentColor = selectedContentColor,
                            contentColor = contentColor,
                            indicatorShape = indicatorShape,
                            onSelect = onSelectState,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun androidx.compose.foundation.layout.RowScope.GlassSegmentedTabItem(
    label: String,
    position: Int,
    selected: Boolean,
    pressedIndexProvider: () -> Int,
    selectedContentColor: Color,
    contentColor: Color,
    indicatorShape: GlassShape,
    onSelect: (Int) -> Unit,
) {
    val contentColorState = animateColorAsState(
        targetValue = if (selected) selectedContentColor else contentColor,
        animationSpec = GlassMotion.navContent(),
        label = "glassSegmentedTabContent",
    )
    val contentColorProducer = remember { ColorProducer { contentColorState.value } }
    val isPressed by remember(position) {
        computedStateOf { pressedIndexProvider() == position }
    }
    val pressAlpha = animateFloatAsState(
        targetValue = if (isPressed) {
            GlassSegmentedTabRowDefaults.PRESSED_CONTENT_ALPHA
        } else {
            1f
        },
        animationSpec = GlassMotion.navContentFloat(),
        label = "glassSegmentedTabPressAlpha",
    )
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clip(indicatorShape)
            .semantics(mergeDescendants = true) {
                this.role = Role.Tab
                this.selected = selected
                onClick {
                    onSelect(position)
                    true
                }
            }
            .onKeyEvent { event ->
                val activationKey = event.key == Key.Enter ||
                    event.key == Key.NumPadEnter ||
                    event.key == Key.Spacebar
                if (activationKey) {
                    if (event.type == KeyEventType.KeyUp) onSelect(position)
                    true
                } else {
                    false
                }
            }
            .focusable()
            .graphicsLayer { this.alpha = pressAlpha.value }
            .padding(horizontal = GlassSegmentedTabRowDefaults.TabPaddingHorizontal),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            color = contentColorProducer,
            style = if (selected) {
                MiuixTheme.textStyles.subtitle
            } else {
                MiuixTheme.textStyles.body2
            },
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
