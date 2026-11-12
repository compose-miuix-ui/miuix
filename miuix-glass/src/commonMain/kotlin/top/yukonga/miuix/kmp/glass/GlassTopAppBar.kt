// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BlurTopAppBar
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.TopAppBarDefaults
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.ProgressiveBlur
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.progressiveTextureBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassEdgeStrokes
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassMaterials
import top.yukonga.miuix.kmp.material.GlassShadow
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.GlassStyle
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.material.withUnderlayBlur
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.roundToInt

/** Default values for [GlassTopAppBar] and [GlassIconButton]. */
object GlassTopAppBarDefaults {

    /** Start/end inset of the glass controls, aligned with the page's cards and tabs. */
    val HorizontalPadding: Dp = 12.dp

/** Scroll threshold for the retained shadow ramp. */
    val RampStart: Dp = 0.dp

/** Scroll distance for the retained shadow ramp. Material transitions use their own timing. */
    val RampDistance: Dp = 32.dp

    /** How far the large title blurs out as it collapses. */
    val LargeTitleBlurRadius: Dp = 12.dp

    /** Diameter of the glass pill behind a bar button. */
    val ButtonSize: Dp = 44.dp

    /** Maximum radius of the progressive background band. */
    val ProgressiveBlurRadius: Dp = 80.dp

    /** Scroll distance over which the progressive band fades in. */
    val ProgressiveBlurRampDistance: Dp = 48.dp

    /** Icon opacity while an action-bar button is held. */
    val PressedContentAlpha: Float = 0.6f

    /**
     * How far a bar has collapsed, in `[0, 1]`.
     *
     * @param scrollBehavior The behavior driving the bar. With neither scroll source provided,
     * the bar remains at full strength.
     * @param visualContentOffset Optional live content position in pixels (negative when scrolled
     * upward), including any elastic displacement. Overrides the behavior's visual offset.
     */
    @Composable
    fun collapseRamp(
        scrollBehavior: ScrollBehavior?,
        visualContentOffset: (() -> Float)? = null,
    ): State<Float> {
        val density = LocalDensity.current
        val startPx = with(density) { RampStart.toPx() }
        val rampPx = with(density) { RampDistance.toPx() }
        return remember(scrollBehavior, visualContentOffset, startPx, rampPx) {
            if (scrollBehavior == null && visualContentOffset == null) {
                mutableFloatStateOf(1f)
            } else {
                derivedStateOf {
                    val offset = visualContentOffset?.invoke() ?: scrollBehavior!!.state.visualContentOffset
                    ((-offset - startPx) / rampPx).coerceIn(0f, 1f)
                }
            }
        }
    }

/** Fallback button fill when backdrop material is unavailable. */
    @Composable
    fun buttonFill(): Color = if (isDarkTheme()) Color(0xFF2C2C2C) else Color.White

/** Default material for action-bar buttons. */
    @Composable
    fun buttonMaterial(): GlassMaterial = GlassMaterials.puredThinGlass(isDarkTheme())

/** Material applied beneath child buttons and tabs. */
    @Composable
    internal fun actionBarUnderlayMaterial(): GlassMaterial = GlassMaterials.actionBarMask(isDarkTheme())

    /** The small bloom stroke paired with [buttonMaterial]. */
    @Composable
    fun buttonStroke(): EdgeStroke = GlassEdgeStrokes.forTheme(
        isDark = isDarkTheme(),
        light = GlassEdgeStrokes.SmallLight,
        dark = GlassEdgeStrokes.SmallDark,
    )

    /** The state overlay painted behind a held action-bar icon. */
    @Composable
    fun buttonPressedOverlay(): Color = if (isDarkTheme()) {
        Color.White.copy(alpha = 0.14f)
    } else {
        Color.Black.copy(alpha = 0.10f)
    }

/** Gradient that dims content passing beneath the bar.
     *
     * @param color Page background colour.
     */
    @Stable
    fun bandBrush(color: Color): Brush = TopAppBarBandBrush(color)

    /** How far the band reaches past the bottom of the bar. */
    val BandOverhang: Dp = 48.dp

    @Composable
    private fun isDarkTheme(): Boolean = MiuixTheme.colorScheme.background.luminance() < 0.5f
}

/** Starts fading inside the measured bar so its trailing edge does not form an opaque shelf. */
private class TopAppBarBandBrush(color: Color) : ShaderBrush() {
    private val colors = opacities.map { color.copy(alpha = color.alpha * it) }

    override fun createShader(size: Size): Shader = createShader(0f, size.height)

    fun createShader(fadeStart: Float, fadeHeight: Float): Shader = LinearGradientShader(
        from = Offset(0f, fadeStart),
        to = Offset(0f, fadeStart + fadeHeight.coerceAtLeast(1f)),
        colors = colors,
        colorStops = stops,
    )

    private companion object {
        val stops = List(17) { it / 16f }
        val opacities = stops.map { 1f - it * it * (3f - 2f * it) }
    }
}

internal data class GlassTopAppBarContext(
    val backdrop: Backdrop?,
    val buttonBackdrop: Backdrop?,
    val material: GlassMaterial,
    val underlayMaterial: GlassMaterial?,
    val buttonUnderlayMaterial: GlassMaterial?,
    val floating: Boolean,
    val alpha: Float,
    val ramp: State<Float>,
    val materialProgress: State<Float>,
    val keepMaterial: Boolean,
    val style: GlassStyle,
    val size: Dp,
    val shape: GlassShape,
    val fill: Color,
    val stroke: EdgeStroke?,
    val shadow: GlassShadow?,
)

internal val LocalGlassTopAppBarContext = staticCompositionLocalOf<GlassTopAppBarContext?> { null }

/**
 * Top app bar whose material visibility follows [ScrollBehavior].
 * Use the overload with [isContentScrolled] when the page can report its exact overlap state.
 *
 * @param title Compact title, centered when the bar collapses.
 * @param backdrop Background sampled by the buttons. `null` uses [fill].
 * @param modifier Modifier applied to the bar.
 * @param largeTitle Large title shown before content scrolls. Defaults to [title].
 * @param subtitle Optional subtitle below the title.
 * @param largeTitleBlurRadius Blur applied as the large title collapses.
 * @param scrollBehavior Behavior driving collapse. Without one, the bar remains at full strength.
 * @param visualContentOffset Optional live content position in pixels, negative for upward scroll.
 *   Use a position source when scrolling can be programmatic; include elastic displacement if needed.
 *   Drives material, shadows, and progressive blur without writing the title's scroll behavior.
 * @param bandBrush Gradient that dims content passing beneath the bar.
 * @param bandOverhang Distance the gradient extends beyond the bar.
 * @param style Glass style forwarded to the renderer.
 * @param alpha Opacity multiplier for button colour layers and stroke.
 * @param buttonShape Button-pill silhouette.
 * @param buttonSize Button-pill diameter.
 * @param buttonShadow Shadow beneath button pills; `null` disables it.
 * @param fill Fallback button fill when glass rendering support is unavailable.
 * @param stroke Optional bloom stroke on button pills.
 * @param defaultWindowInsetsPadding Whether to apply horizontal display-cutout and navigation-bar insets.
 * @param contentModifier Modifier for the foreground bar; the background band remains stationary.
 * @param progressiveBlur Draw a progressive background and let buttons sample that rendered scene.
 *   Requires a supported, non-null [backdrop]. The background excludes buttons and other bar content.
 * @param material Button material, applied over the rendered progressive band when enabled.
 * @param backgroundAlpha Draw-phase opacity multiplier for the background band.
 * @param titleAlpha Draw-phase opacity for title containers.
 * @param navigationIcon Leading control slot. Use [GlassIconButton] for a glass button.
 * @param actions Trailing control slot. Use [GlassIconButton] for glass buttons.
 * @param bottomContent Content pinned below the title inside the bar.
 */
@Composable
fun GlassTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    progressiveBlur: Boolean = false,
    backdrop: Backdrop? = null,
    largeTitle: String = title,
    subtitle: String = "",
    scrollBehavior: ScrollBehavior? = null,
    visualContentOffset: (() -> Float)? = null,
    bandBrush: Brush = GlassTopAppBarDefaults.bandBrush(MiuixTheme.colorScheme.surface),
    bandOverhang: Dp = GlassTopAppBarDefaults.BandOverhang,
    largeTitleBlurRadius: Dp = GlassTopAppBarDefaults.LargeTitleBlurRadius,
    style: GlassStyle = GlassDefaults.Style,
    material: GlassMaterial = GlassTopAppBarDefaults.buttonMaterial(),
    alpha: Float = 1f,
    buttonSize: Dp = GlassTopAppBarDefaults.ButtonSize,
    buttonShape: GlassShape = GlassShape(buttonSize / 2),
    fill: Color = GlassTopAppBarDefaults.buttonFill(),
    stroke: EdgeStroke? = GlassTopAppBarDefaults.buttonStroke(),
    buttonShadow: GlassShadow? = GlassShadows.Regular,
    defaultWindowInsetsPadding: Boolean = true,
    titleAlpha: () -> Float = { 1f },
    backgroundAlpha: () -> Float = { 1f },
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
) {
    val isContentScrolled by remember(scrollBehavior, visualContentOffset) {
        derivedStateOf {
            val offset = visualContentOffset?.invoke() ?: scrollBehavior?.state?.visualContentOffset
            offset?.let { it < 0f } ?: true
        }
    }
    GlassTopAppBar(
        title = title,
        isContentScrolled = isContentScrolled,
        modifier = modifier,
        progressiveBlur = progressiveBlur,
        backdrop = backdrop,
        largeTitle = largeTitle,
        subtitle = subtitle,
        scrollBehavior = scrollBehavior,
        visualContentOffset = visualContentOffset,
        bandBrush = bandBrush,
        bandOverhang = bandOverhang,
        largeTitleBlurRadius = largeTitleBlurRadius,
        style = style,
        material = material,
        alpha = alpha,
        buttonSize = buttonSize,
        buttonShape = buttonShape,
        fill = fill,
        stroke = stroke,
        buttonShadow = buttonShadow,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        contentModifier = contentModifier,
        titleAlpha = titleAlpha,
        backgroundAlpha = backgroundAlpha,
        navigationIcon = navigationIcon,
        actions = actions,
        bottomContent = bottomContent,
    )
}

/**
 * A glass top bar with an explicit page-overlap state.
 *
 * @param title Compact title.
 * @param isContentScrolled Whether page content has left its resting position. For a lazy list,
 *   pass `listState.canScrollBackward`.
 */
@Composable
fun GlassTopAppBar(
    title: String,
    isContentScrolled: Boolean,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    progressiveBlur: Boolean = false,
    backdrop: Backdrop? = null,
    largeTitle: String = title,
    subtitle: String = "",
    scrollBehavior: ScrollBehavior? = null,
    visualContentOffset: (() -> Float)? = null,
    bandBrush: Brush = GlassTopAppBarDefaults.bandBrush(MiuixTheme.colorScheme.surface),
    bandOverhang: Dp = GlassTopAppBarDefaults.BandOverhang,
    largeTitleBlurRadius: Dp = GlassTopAppBarDefaults.LargeTitleBlurRadius,
    style: GlassStyle = GlassDefaults.Style,
    material: GlassMaterial = GlassTopAppBarDefaults.buttonMaterial(),
    alpha: Float = 1f,
    buttonSize: Dp = GlassTopAppBarDefaults.ButtonSize,
    buttonShape: GlassShape = GlassShape(buttonSize / 2),
    fill: Color = GlassTopAppBarDefaults.buttonFill(),
    stroke: EdgeStroke? = GlassTopAppBarDefaults.buttonStroke(),
    buttonShadow: GlassShadow? = GlassShadows.Regular,
    defaultWindowInsetsPadding: Boolean = true,
    titleAlpha: () -> Float = { 1f },
    backgroundAlpha: () -> Float = { 1f },
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
) {
    val ramp = GlassTopAppBarDefaults.collapseRamp(scrollBehavior, visualContentOffset)
    val floating = isContentScrolled
    val transition = updateTransition(floating, label = "glassTopBarFloat")
    val maskProgress = transition.animateFloat(
        transitionSpec = { GlassMotion.topBarMask() },
        label = "glassTopBarMask",
    ) { if (it) 1f else 0f }
    val materialProgress = transition.animateFloat(
        transitionSpec = { GlassMotion.topBarButtonFloat() },
        label = "glassTopBarMaterial",
    ) { if (it) 1f else 0f }
    val keepMaterial by remember(floating, materialProgress) {
        derivedStateOf { floating || materialProgress.value > 0f }
    }
    val rawBackdrop = backdrop?.takeIf { isRuntimeShaderSupported() }
    val underlayMaterial = GlassTopAppBarDefaults.actionBarUnderlayMaterial().takeIf { keepMaterial && rawBackdrop != null }
    var backgroundCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }
    val progressiveBackdrop = if (progressiveBlur && rawBackdrop != null) {
        rememberLayerBackdrop {
            val coordinates = backgroundCoordinates?.takeIf { it.isAttached }
            with(rawBackdrop) { drawBackdrop(this@rememberLayerBackdrop, coordinates) }
            drawContent()
        }
    } else {
        null
    }
    val buttonUnderlay = underlayMaterial.takeIf { progressiveBackdrop == null }
    val resolvedMaterial = material.withUnderlayBlur(buttonUnderlay)

    val measurements = remember { GlassTopAppBarMeasurements() }
    GlassTopAppBarLayout(
        modifier = modifier,
        progressiveBlur = progressiveBackdrop != null && rawBackdrop != null,
        measurements = measurements,
        gradientOverhang = if (bandBrush is TopAppBarBandBrush) bandOverhang else 0.dp,
    ) {
        if (progressiveBackdrop != null && rawBackdrop != null) {
            val blurColors = BlurDefaults.blurColors(
                blendColors = listOf(BlendColorEntry(MiuixTheme.colorScheme.surface.copy(alpha = 0.3f))),
            )
            Box(
                modifier = Modifier
                    .onGloballyPositioned { backgroundCoordinates = it }
                    .layerBackdrop(progressiveBackdrop)
                    .graphicsLayer {
                        val offset = visualContentOffset?.invoke() ?: scrollBehavior?.state?.visualContentOffset
                        val ramp = offset?.let {
                            (-it / GlassTopAppBarDefaults.ProgressiveBlurRampDistance.toPx()).coerceIn(0f, 1f)
                        } ?: maskProgress.value
                        this.alpha = ramp * backgroundAlpha().coerceIn(0f, 1f)
                    }
                    .progressiveTextureBlur(
                        backdrop = rawBackdrop,
                        shape = RectangleShape,
                        gradient = ProgressiveBlur.Top.copy(curve = 0.5f),
                        blurRadius = GlassTopAppBarDefaults.ProgressiveBlurRadius.value,
                        colors = blurColors,
                    ),
            )
        } else {
            val overhangPx = with(LocalDensity.current) { bandOverhang.roundToPx().coerceAtLeast(0).toFloat() }
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        val progress = if (visualContentOffset != null || scrollBehavior != null) ramp.value else maskProgress.value
                        this.alpha = progress * backgroundAlpha().coerceIn(0f, 1f)
                        transformOrigin = TransformOrigin(0.5f, 0f)
                        scaleY = if (bandBrush !is TopAppBarBandBrush && size.height > 0f) (size.height + overhangPx) / size.height else 1f
                    }
                    .then(
                        if (bandBrush is TopAppBarBandBrush) {
                            Modifier.drawWithCache {
                                val fadeStart = (size.height - overhangPx - 12.dp.toPx()).coerceAtLeast(0f)
                                val brush = ShaderBrush(bandBrush.createShader(fadeStart, size.height - fadeStart))
                                onDrawBehind { drawRect(brush) }
                            }
                        } else {
                            Modifier.background(bandBrush)
                        },
                    ),
            )
        }
        CompositionLocalProvider(
            LocalGlassTopAppBarContext provides GlassTopAppBarContext(
                backdrop = rawBackdrop,
                buttonBackdrop = progressiveBackdrop ?: rawBackdrop,
                material = resolvedMaterial,
                underlayMaterial = underlayMaterial,
                buttonUnderlayMaterial = buttonUnderlay,
                floating = floating,
                alpha = alpha,
                ramp = ramp,
                materialProgress = materialProgress,
                keepMaterial = keepMaterial,
                style = style,
                size = buttonSize,
                shape = buttonShape,
                fill = fill,
                stroke = stroke,
                shadow = buttonShadow,
            ),
        ) {
            BlurTopAppBar(
                title = title,
                modifier = contentModifier,
                titleAlpha = titleAlpha,
                largeTitle = largeTitle,
                largeTitleBlurRadius = largeTitleBlurRadius,
                subtitle = subtitle,
                color = Color.Transparent,
                scrollBehavior = scrollBehavior,
                defaultWindowInsetsPadding = defaultWindowInsetsPadding,
                navigationIconPadding = GlassTopAppBarDefaults.HorizontalPadding,
                actionIconPadding = GlassTopAppBarDefaults.HorizontalPadding,
                navigationIcon = navigationIcon,
                actions = actions,
                bottomContent = {
                    Box(
                        modifier = Modifier.layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            measurements.bottomContentHeight = placeable.height
                            layout(placeable.width, placeable.height) { placeable.placeRelative(0, 0) }
                        },
                    ) {
                        bottomContent()
                    }
                },
            )
        }
    }
}

private class GlassTopAppBarMeasurements {
    var bottomContentHeight: Int = 0
}

@Composable
private fun GlassTopAppBarLayout(
    progressiveBlur: Boolean,
    measurements: GlassTopAppBarMeasurements,
    gradientOverhang: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val topInsets = WindowInsets.statusBars
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        // Measure the foreground first: its bottom slot and the band share this measure pass.
        val foreground = measurables[1].measure(constraints.copy(minWidth = 0, minHeight = 0))
        val width = constraints.constrainWidth(foreground.width)
        val height = constraints.constrainHeight(foreground.height)
        val bandHeight = if (progressiveBlur) {
            val coveredHeight = if (measurements.bottomContentHeight > 0) {
                foreground.height.toFloat()
            } else {
                topInsets.getTop(this) + TopAppBarDefaults.CollapsedHeight.toPx()
            }
            ((coveredHeight + 20.dp.toPx()) * 1.2f).roundToInt()
        } else {
            // Keep the entire fade in the alpha layer at every scroll position.
            height + gradientOverhang.roundToPx().coerceAtLeast(0)
        }
        val background = measurables[0].measure(Constraints.fixed(width, bandHeight))
        layout(width, height) {
            background.placeRelative(0, 0)
            foreground.placeRelative(0, 0)
        }
    }
}
