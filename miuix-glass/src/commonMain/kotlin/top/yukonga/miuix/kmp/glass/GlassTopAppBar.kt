// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.glass.internal.drawGlassMask
import top.yukonga.miuix.kmp.glass.internal.drawGlassRim
import top.yukonga.miuix.kmp.glass.internal.drawGlassStroke
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Default values for [GlassTopAppBar] and [GlassIconButton]. */
object GlassTopAppBarDefaults {

    /** How far the content scrolls before anything of the bar appears at all. */
    val RampStart: Dp = 0.dp

    /**
     * How far the content scrolls, past [RampStart], before the band and the buttons reach full
     * strength.
     */
    val RampDistance: Dp = 32.dp

    /** How far the large title blurs out as it collapses. */
    val LargeTitleBlurRadius: Dp = 12.dp

    /** Diameter of the glass pill behind a bar button. */
    val ButtonSize: Dp = 44.dp

    /**
     * How far a bar has collapsed, in `[0, 1]`.
     *
     * @param scrollBehavior The behavior driving the bar. `null` reports a fully collapsed bar,
     *   which is what a bar over content that does not scroll wants.
     */
    @Composable
    fun collapseRamp(scrollBehavior: ScrollBehavior?): Float {
        val density = LocalDensity.current
        val startPx = with(density) { RampStart.toPx() }
        val rampPx = with(density) { RampDistance.toPx() }
        return scrollBehavior?.state?.let {
            ((-it.contentOffset - startPx) / rampPx).coerceIn(0f, 1f)
        } ?: 1f
    }

    /** The fill behind a bar button. */
    @Composable
    fun buttonFill(): Color {
        val page = MiuixTheme.colorScheme.surface
        val lift = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) 0.10f else 0.55f
        return Color.White.copy(alpha = lift).compositeOver(page)
    }

    /**
     * The band that dims the content as it passes under the bar: the page's own colour, laid back
     * over it, solid at the top edge and gone by the bottom.
     *
     * @param color The page's background colour.
     */
    @Stable
    fun bandBrush(color: Color): Brush = Brush.verticalGradient(
        0.00f to color,
        0.45f to color,
        0.58f to color.copy(alpha = 0.82f),
        0.72f to color.copy(alpha = 0.58f),
        0.86f to color.copy(alpha = 0.34f),
        1.00f to color.copy(alpha = 0f),
    )

    /** How far the band reaches past the bottom of the bar. */
    val BandOverhang: Dp = 28.dp
}

/**
 * A top bar the content scrolls *under*.
 *
 * @param title The compact title, centred once the bar has collapsed.
 * @param backdrop Optional [Backdrop] for the button pills. `null`, the default, gives them the
 *   bar's own fill instead, which is what the source system does.
 * @param modifier The modifier applied to the bar.
 * @param largeTitle The title shown large before the content scrolls. Defaults to [title].
 * @param subtitle Optional line under the title.
 * @param largeTitleBlurRadius How far the large title blurs out as it collapses.
 * @param scrollBehavior The behavior driving the collapse. Without one the bar stays at full
 *   strength, which is what a bar over non-scrolling content wants.
 * @param bandBrush The band that dims the content passing under the bar.
 * @param bandOverhang How far the band reaches past the bottom of the bar.
 * @param style The glass material of the button pills.
 * @param alpha Opacity multiplier for the button pills, on top of the collapse ramp.
 * @param buttonShape Silhouette of the button pills. Defaults to a circle of [buttonSize].
 * @param buttonSize Diameter of the button pills.
 * @param buttonShadow The shadow under the button pills. `null` removes it.
 * @param fill Fill behind the button pills, used when [backdrop] is null.
 * @param stroke Optional bloom stroke on the button pills.
 * @param defaultWindowInsetsPadding Whether to inset the bar for the status bar.
 * @param navigationIcon The leading control. The bar puts a glass pill behind it as it collapses;
 *   the icon keeps its own click handling.
 * @param actions Trailing controls. Wrap them in [GlassIconButton] to give them the same pill.
 * @param bottomContent Content pinned under the title, inside the bar. A tab row goes here: the
 *   band reaches over it, so the list passing underneath is dimmed before it meets the tabs.
 */
@Composable
fun GlassTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    backdrop: Backdrop? = null,
    largeTitle: String = title,
    subtitle: String = "",
    scrollBehavior: ScrollBehavior? = null,
    bandBrush: Brush = GlassTopAppBarDefaults.bandBrush(MiuixTheme.colorScheme.surface),
    bandOverhang: Dp = GlassTopAppBarDefaults.BandOverhang,
    largeTitleBlurRadius: Dp = GlassTopAppBarDefaults.LargeTitleBlurRadius,
    style: GlassStyle = GlassDefaults.Style,
    alpha: Float = 1f,
    buttonSize: Dp = GlassTopAppBarDefaults.ButtonSize,
    buttonShape: GlassShape = GlassShape(buttonSize / 2),
    fill: Color = GlassTopAppBarDefaults.buttonFill(),
    stroke: GlassStroke? = null,
    buttonShadow: GlassShadow? = GlassShadows.Regular,
    defaultWindowInsetsPadding: Boolean = true,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
) {
    val ramp = GlassTopAppBarDefaults.collapseRamp(scrollBehavior)

    Box(modifier = modifier) {
        val overhangPx = with(LocalDensity.current) { bandOverhang.toPx() }
        Box(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    this.alpha = ramp
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    scaleY = if (size.height > 0f) (size.height + overhangPx) / size.height else 1f
                }
                .background(bandBrush),
        )
        TopAppBar(
            title = title,
            largeTitle = largeTitle,
            largeTitleBlurRadius = largeTitleBlurRadius,
            subtitle = subtitle,
            color = Color.Transparent,
            scrollBehavior = scrollBehavior,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding,
            navigationIcon = {
                GlassButtonSurface(
                    backdrop = backdrop,
                    surfaceAlpha = ramp * alpha,
                    style = style,
                    shape = buttonShape,
                    size = buttonSize,
                    fill = fill,
                    stroke = stroke,
                    shadow = buttonShadow,
                    content = navigationIcon,
                )
            },
            actions = actions,
            bottomContent = bottomContent,
        )
    }
}

/**
 * A round glass button, the shape the source system gives a bar control once its bar has collapsed.
 *
 * @param onClick Called when the button is tapped.
 * @param backdrop Optional [Backdrop] behind the glass. `null` gives a flat [fill] instead.
 * @param modifier The modifier applied to the button.
 * @param surfaceAlpha Opacity of the pill. Drive it from a bar's collapse so the pill arrives with
 *   the rest of the bar; below about 1% the material is skipped entirely.
 * @param style The glass material.
 * @param size Diameter of the button.
 * @param shape The button's silhouette. Defaults to a circle of [size].
 * @param shadow The shadow under the button. `null` removes it.
 * @param stroke Optional bloom stroke along the rim.
 * @param content The icon inside.
 */
@Composable
fun GlassIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backdrop: Backdrop? = null,
    surfaceAlpha: Float = 1f,
    style: GlassStyle = GlassDefaults.Style,
    size: Dp = GlassTopAppBarDefaults.ButtonSize,
    shape: GlassShape = GlassShape(size / 2),
    fill: Color = GlassTopAppBarDefaults.buttonFill(),
    stroke: GlassStroke? = null,
    shadow: GlassShadow? = GlassShadows.Regular,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    GlassButtonSurface(
        backdrop = backdrop,
        surfaceAlpha = surfaceAlpha,
        style = style,
        shape = shape,
        size = size,
        fill = fill,
        stroke = stroke,
        shadow = shadow,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .glassPress(interactionSource),
        content = content,
    )
}

/** The pill itself, shared by [GlassTopAppBar]'s navigation icon and [GlassIconButton]. */
@Composable
private fun GlassButtonSurface(
    backdrop: Backdrop?,
    surfaceAlpha: Float,
    style: GlassStyle,
    fill: Color,
    shape: GlassShape,
    size: Dp,
    stroke: GlassStroke?,
    shadow: GlassShadow?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .size(size)
            .glassShadow(shape, shadow, surfaceAlpha)
            .then(
                if (backdrop != null) {
                    Modifier.glass(
                        backdrop = backdrop,
                        shape = shape,
                        style = style,
                        alpha = surfaceAlpha,
                        stroke = stroke,
                        enabled = surfaceAlpha > 0.01f,
                    )
                } else {
                    Modifier
                        .then(
                            if (isRuntimeShaderSupported()) {
                                Modifier.graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
                            } else {
                                Modifier.clip(shape)
                            },
                        )
                        .background(fill.copy(alpha = surfaceAlpha.coerceIn(0f, 1f)))
                        .drawWithContent {
                            drawContent()
                            if (stroke != null) {
                                drawGlassStroke(shape, layoutDirection, stroke, surfaceAlpha)
                            }
                            drawGlassMask(shape, layoutDirection)
                            drawGlassRim(shape, layoutDirection, style, fill, surfaceAlpha)
                        }
                },
            ),
        contentAlignment = Alignment.Center,
        content = { content() },
    )
}
