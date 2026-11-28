// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
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
import androidx.compose.ui.util.fastCoerceAtLeast
import androidx.compose.ui.util.fastCoerceIn
import androidx.compose.ui.util.fastRoundToInt
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.TopAppBarDefaults
import top.yukonga.miuix.kmp.basic.TopAppBarLayout
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.LayerBackdrop
import top.yukonga.miuix.kmp.blur.ProgressiveBlur
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.progressiveTextureBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.internal.GlassHeaderContext
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
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.material.withUnderlayBlur
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Default values for [GlassTopAppBar] and [GlassIconButton]. */
object GlassTopAppBarDefaults {

    /** Start/end inset of the glass controls, aligned with the page's cards and tabs. */
    val HorizontalPadding: Dp = 12.dp

    /** Scroll threshold for the retained shadow ramp. */
    val RampStart: Dp = 0.dp

    /** Scroll distance for the retained shadow ramp. Material transitions use their own timing. */
    val RampDistance: Dp = 32.dp

    /** Maximum blur radius applied to titles during collapse/expand transitions. */
    val TitleBlurRadius: Dp = 8.dp

    /** Diameter of the glass pill behind a bar button. */
    val ButtonSize: Dp = 44.dp

    /** Maximum radius of the progressive background band. */
    val ProgressiveBlurRadius: Dp = 32.dp

    /** Scroll distance over which the progressive band fades in. */
    val ProgressiveBlurRampDistance: Dp = 48.dp

    /** Icon opacity while an action-bar button is held. */
    val PressedContentAlpha: Float = 0.6f

    /**
     * Visual scroll progress in `[0, 1]`, including elastic displacement.
     * [visualContentOffset] overrides the behavior's visual offset; no source means full strength.
     */
    @Composable
    fun collapseRamp(
        scrollBehavior: ScrollBehavior?,
        visualContentOffset: (() -> Float)? = null,
    ): State<Float> {
        val density = LocalDensity.current
        val startPx = with(density) { RampStart.toPx() }
        val rampPx = with(density) { RampDistance.toPx() }
        val currentOffset by rememberUpdatedState(visualContentOffset)
        return remember(scrollBehavior, startPx, rampPx) {
            computedStateOf {
                val offset = currentOffset?.invoke() ?: scrollBehavior?.state?.visualContentOffset
                if (offset == null) 1f else ((-offset - startPx) / rampPx).fastCoerceIn(0f, 1f)
            }
        }
    }

    /** Fallback button fill when backdrop material is unavailable. */
    @Composable
    @ReadOnlyComposable
    fun buttonFill(): Color = when {
        MiuixTheme.isDynamicColor -> MiuixTheme.colorScheme.surfaceContainerHigh
        isDarkTheme() -> Color(0xFF2C2C2C)
        else -> Color.White
    }

    /** Default material for action-bar buttons. */
    @Composable
    fun buttonMaterial(): GlassMaterial = dynamicGlassMaterial(GlassMaterials.puredThinGlass(isDarkTheme()))

    /** Material applied beneath child buttons and tabs. */
    @Composable
    @ReadOnlyComposable
    internal fun actionBarUnderlayMaterial(): GlassMaterial = GlassMaterials.actionBarMask(isDarkTheme())

    /** The small bloom stroke paired with [buttonMaterial]. */
    @Composable
    @ReadOnlyComposable
    fun buttonStroke(): EdgeStroke = GlassEdgeStrokes.forTheme(
        isDark = isDarkTheme(),
        light = GlassEdgeStrokes.SmallLight,
        dark = GlassEdgeStrokes.SmallDark,
    )

    /** The state overlay painted behind a held action-bar icon. */
    @Composable
    @ReadOnlyComposable
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
    val BandOverhang: Dp = 10.dp

    @Composable
    @ReadOnlyComposable
    private fun isDarkTheme(): Boolean = MiuixTheme.colorScheme.background.luminance() < 0.5f
}

/** Starts fading inside the collapsed bar so the title area stays legible while avoiding an opaque shelf. */
private data class TopAppBarBandBrush(val color: Color) : ShaderBrush() {
    val colors = opacities.map { color.copy(alpha = color.alpha * it) }

    override fun createShader(size: Size): Shader = LinearGradientShader(
        from = Offset.Zero,
        to = Offset(0f, size.height.fastCoerceAtLeast(1f)),
        colors = colors,
        colorStops = stops,
    )

    companion object {
        const val MAX_ALPHA = 0.90f
        val stops = List(17) { it / 16f }
        private val opacities = stops.map { t ->
            val fade = t * t * t * (t * (t * 6f - 15f) + 10f)
            MAX_ALPHA * (1f - fade)
        }
    }
}

private class TopAppBarFixedFadeBrush(
    val band: TopAppBarBandBrush,
    private val fadeHeight: Float,
) : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val fadeStart = (size.height - fadeHeight).fastCoerceAtLeast(0f)
        return LinearGradientShader(
            from = Offset(0f, fadeStart),
            to = Offset(0f, fadeStart + fadeHeight.fastCoerceAtLeast(1f)),
            colors = band.colors,
            colorStops = TopAppBarBandBrush.stops,
        )
    }
}

/**
 * Top app bar whose material visibility follows actual content scrolling in [ScrollBehavior].
 * Use the overload with [isContentScrolled] when the page can report its exact overlap state.
 *
 * @param title Compact title, centered when the bar collapses.
 * @param backdrop Background sampled by the buttons. `null` uses [fill].
 * @param modifier Modifier applied to the bar.
 * @param largeTitle Large title shown before content scrolls. Defaults to [title].
 * @param expandable Whether the large title can expand. `false` keeps the compact title visible.
 * @param subtitle Optional subtitle below the title.
 * @param titleBlurRadius Maximum blur radius applied to titles during collapse/expand transitions.
 * @param scrollBehavior Behavior driving collapse and material visibility from actual content scroll.
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
    expandable: Boolean = true,
    progressiveBlur: Boolean = false,
    backdrop: Backdrop? = null,
    largeTitle: String = title,
    subtitle: String = "",
    scrollBehavior: ScrollBehavior? = null,
    bandBrush: Brush = MiuixTheme.colorScheme.surface.let { surface ->
        remember(surface) { GlassTopAppBarDefaults.bandBrush(surface) }
    },
    bandOverhang: Dp = GlassTopAppBarDefaults.BandOverhang,
    titleBlurRadius: Dp = GlassTopAppBarDefaults.TitleBlurRadius,
    style: GlassStyle = GlassDefaults.Style,
    material: GlassMaterial = GlassTopAppBarDefaults.buttonMaterial(),
    alpha: Float = 1f,
    buttonSize: Dp = GlassTopAppBarDefaults.ButtonSize,
    buttonShape: GlassShape = remember(buttonSize) { GlassShape(buttonSize / 2) },
    fill: Color = GlassTopAppBarDefaults.buttonFill(),
    stroke: EdgeStroke? = GlassTopAppBarDefaults.buttonStroke(),
    buttonShadow: GlassShadow? = GlassShadows.Regular,
    defaultWindowInsetsPadding: Boolean = true,
    titleAlpha: () -> Float = { 1f },
    backgroundAlpha: () -> Float = { 1f },
    centerObstructionStart: Dp = Dp.Unspecified,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
) {
    val isContentScrolled by remember(scrollBehavior) {
        computedStateOf {
            scrollBehavior?.state?.let { it.contentOffset < -1f || it.overscrollOffset < -1f } ?: true
        }
    }
    GlassTopAppBar(
        title = title,
        isContentScrolled = isContentScrolled,
        modifier = modifier,
        expandable = expandable,
        progressiveBlur = progressiveBlur,
        backdrop = backdrop,
        largeTitle = largeTitle,
        subtitle = subtitle,
        scrollBehavior = scrollBehavior,
        bandBrush = bandBrush,
        bandOverhang = bandOverhang,
        titleBlurRadius = titleBlurRadius,
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
        centerObstructionStart = centerObstructionStart,
        navigationIcon = navigationIcon,
        actions = actions,
        bottomContent = bottomContent,
    )
}

/**
 * A glass top bar with an explicit page-overlap state.
 *
 * @param title Compact title.
 * @param isContentScrolled Button floating state; use `listState.canScrollBackward`.
 *   Elastic overscroll can also raise material progress.
 */
@Composable
fun GlassTopAppBar(
    title: String,
    isContentScrolled: Boolean,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    expandable: Boolean = true,
    progressiveBlur: Boolean = false,
    backdrop: Backdrop? = null,
    largeTitle: String = title,
    subtitle: String = "",
    scrollBehavior: ScrollBehavior? = null,
    bandBrush: Brush = MiuixTheme.colorScheme.surface.let { surface ->
        remember(surface) { GlassTopAppBarDefaults.bandBrush(surface) }
    },
    bandOverhang: Dp = GlassTopAppBarDefaults.BandOverhang,
    titleBlurRadius: Dp = GlassTopAppBarDefaults.TitleBlurRadius,
    style: GlassStyle = GlassDefaults.Style,
    material: GlassMaterial = GlassTopAppBarDefaults.buttonMaterial(),
    alpha: Float = 1f,
    buttonSize: Dp = GlassTopAppBarDefaults.ButtonSize,
    buttonShape: GlassShape = remember(buttonSize) { GlassShape(buttonSize / 2) },
    fill: Color = GlassTopAppBarDefaults.buttonFill(),
    stroke: EdgeStroke? = GlassTopAppBarDefaults.buttonStroke(),
    buttonShadow: GlassShadow? = GlassShadows.Regular,
    defaultWindowInsetsPadding: Boolean = true,
    titleAlpha: () -> Float = { 1f },
    backgroundAlpha: () -> Float = { 1f },
    centerObstructionStart: Dp = Dp.Unspecified,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
) {
    val contentScrolled by remember(isContentScrolled, scrollBehavior) {
        computedStateOf {
            isContentScrolled || (scrollBehavior?.state?.overscrollOffset ?: 0f) < -1f
        }
    }
    val transition = updateTransition(contentScrolled, label = "glassTopBarFloat")
    val maskProgress = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 100, easing = LinearEasing) },
        label = "glassTopBarMask",
    ) { if (it) 1f else 0f }
    val useCollapseProgress = expandable && scrollBehavior != null
    val bandProgress: () -> Float = remember(useCollapseProgress, scrollBehavior, maskProgress) {
        if (useCollapseProgress) {
            { scrollBehavior.state.collapsedFraction.fastCoerceIn(0f, 1f) }
        } else {
            { maskProgress.value }
        }
    }
    val materialProgress = transition.animateFloat(
        transitionSpec = { GlassMotion.topBarButtonFloat() },
        label = "glassTopBarMaterial",
    ) { if (it) 1f else 0f }
    val keepMaterial by remember(contentScrolled, materialProgress) {
        computedStateOf { contentScrolled || materialProgress.value > 0f }
    }
    val rawBackdrop = backdrop?.takeIf { isRuntimeShaderSupported() }
    val underlayMaterial = GlassTopAppBarDefaults.actionBarUnderlayMaterial().takeIf { rawBackdrop != null }
    var backgroundCoordinates by remember { mutableStateOf<LayoutCoordinates?>(null) }
    var buttonBackdropConsumers by remember { mutableIntStateOf(0) }
    val hasButtonBackdropConsumers = buttonBackdropConsumers > 0
    val onButtonBackdropConsumerChange: (Int) -> Unit = remember {
        { delta -> buttonBackdropConsumers = (buttonBackdropConsumers + delta).fastCoerceAtLeast(0) }
    }
    val progressiveBackdrop = if (progressiveBlur && rawBackdrop != null) {
        rememberLayerBackdrop(
            onDrawUnderlay = {
                val coordinates = backgroundCoordinates?.takeIf { it.isAttached }
                with(rawBackdrop) { drawBackdrop(this@rememberLayerBackdrop, coordinates) }
            },
        )
    } else {
        null
    }
    val buttonUnderlay = underlayMaterial.takeIf { progressiveBackdrop == null }
    val resolvedMaterial = remember(material, buttonUnderlay) { material.withUnderlayBlur(buttonUnderlay) }
    val measurements = remember { GlassTopAppBarMeasurements() }
    GlassTopAppBarLayout(
        modifier = modifier,
        progressiveBlur = progressiveBackdrop != null && rawBackdrop != null,
        useCollapseProgress = useCollapseProgress,
        scrollBehavior = scrollBehavior,
        measurements = measurements,
        gradientOverhang = if (bandBrush is TopAppBarBandBrush) bandOverhang else 0.dp,
    ) {
        if (progressiveBackdrop != null && rawBackdrop != null) {
            ProgressiveTopAppBarBand(
                rawBackdrop = rawBackdrop,
                progressiveBackdrop = progressiveBackdrop,
                bandProgress = bandProgress,
                backgroundAlpha = backgroundAlpha,
                hasButtonBackdropConsumers = hasButtonBackdropConsumers,
                keepMaterial = keepMaterial,
                measurements = measurements,
                onBackgroundCoordinates = { backgroundCoordinates = it },
            )
        } else {
            val density = LocalDensity.current
            val overhangPx = with(density) { bandOverhang.roundToPx().fastCoerceAtLeast(0).toFloat() }
            val fadeHeight = overhangPx + with(density) { 36.dp.toPx() }
            val fadeBrush = remember(bandBrush, fadeHeight) {
                if (bandBrush is TopAppBarBandBrush) TopAppBarFixedFadeBrush(bandBrush, fadeHeight) else null
            }
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        this.alpha = bandProgress() * backgroundAlpha().fastCoerceIn(0f, 1f)
                        transformOrigin = TransformOrigin(0.5f, 0f)
                        scaleY = if (bandBrush !is TopAppBarBandBrush && size.height > 0f) (size.height + overhangPx) / size.height else 1f
                    }
                    .then(
                        if (fadeBrush != null) {
                            Modifier.drawBehind {
                                drawRect(brush = fadeBrush)
                            }
                        } else {
                            Modifier.background(bandBrush)
                        },
                    ),
            )
        }
        val consumerChangeCallback = if (progressiveBackdrop != null) onButtonBackdropConsumerChange else null
        val topBarContext = remember(
            rawBackdrop,
            progressiveBackdrop,
            resolvedMaterial,
            underlayMaterial,
            buttonUnderlay,
            contentScrolled,
            alpha,
            materialProgress,
            keepMaterial,
            style,
            buttonSize,
            buttonShape,
            fill,
            stroke,
            buttonShadow,
            consumerChangeCallback,
        ) {
            GlassHeaderContext(
                backdrop = rawBackdrop,
                buttonBackdrop = progressiveBackdrop ?: rawBackdrop,
                material = resolvedMaterial,
                underlayMaterial = underlayMaterial,
                buttonUnderlayMaterial = buttonUnderlay,
                floating = contentScrolled,
                alpha = alpha,
                materialProgress = materialProgress,
                keepMaterial = keepMaterial,
                style = style,
                size = buttonSize,
                shape = buttonShape,
                fill = fill,
                stroke = stroke,
                shadow = buttonShadow,
                onButtonBackdropConsumerChange = consumerChangeCallback,
            )
        }
        CompositionLocalProvider(
            LocalGlassHeaderContext provides topBarContext,
        ) {
            BlurTopAppBar(
                title = title,
                modifier = contentModifier,
                titleAlpha = titleAlpha,
                expandable = expandable,
                largeTitle = largeTitle,
                titleBlurRadius = titleBlurRadius,
                subtitle = subtitle,
                color = Color.Transparent,
                scrollBehavior = scrollBehavior,
                defaultWindowInsetsPadding = defaultWindowInsetsPadding,
                navigationIconPadding = GlassTopAppBarDefaults.HorizontalPadding,
                actionIconPadding = GlassTopAppBarDefaults.HorizontalPadding,
                centerObstructionStart = centerObstructionStart,
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

@Composable
private fun ProgressiveTopAppBarBand(
    rawBackdrop: Backdrop,
    progressiveBackdrop: LayerBackdrop,
    bandProgress: () -> Float,
    backgroundAlpha: () -> Float,
    hasButtonBackdropConsumers: Boolean,
    keepMaterial: Boolean,
    measurements: GlassTopAppBarMeasurements,
    onBackgroundCoordinates: (LayoutCoordinates) -> Unit,
) {
    val surfaceColor = MiuixTheme.colorScheme.surface
    val startFraction = measurements.progressiveStartFraction
    val progressiveGradient = remember(startFraction) {
        ProgressiveBlur.Top.copy(startFraction = startFraction, curve = 0.6f)
    }
    val scrimBrush = remember(surfaceColor) {
        Brush.verticalGradient(
            0f to surfaceColor.copy(alpha = 0.56f),
            0.25f to surfaceColor.copy(alpha = 0.48f),
            0.5f to surfaceColor.copy(alpha = 0.34f),
            0.75f to surfaceColor.copy(alpha = 0.16f),
            0.9f to surfaceColor.copy(alpha = 0.05f),
            1f to Color.Transparent,
        )
    }
    val currentBgAlpha by rememberUpdatedState(backgroundAlpha)
    val bandActive by remember(bandProgress) {
        computedStateOf {
            bandProgress() * currentBgAlpha().fastCoerceIn(0f, 1f) > 0.001f
        }
    }
    val backdropCaptureModifier = if (hasButtonBackdropConsumers && (keepMaterial || bandActive)) {
        Modifier
            .onGloballyPositioned(onBackgroundCoordinates)
            .layerBackdrop(progressiveBackdrop)
    } else {
        Modifier
    }
    Box(
        modifier = backdropCaptureModifier
            .graphicsLayer {
                this.alpha = bandProgress() * backgroundAlpha().fastCoerceIn(0f, 1f)
            }
            .progressiveTextureBlur(
                backdrop = rawBackdrop,
                shape = RectangleShape,
                gradient = progressiveGradient,
                blurRadius = GlassTopAppBarDefaults.ProgressiveBlurRadius.value,
                enabled = bandActive,
            )
            .drawWithContent {
                drawContent()
                if (bandActive) {
                    drawRect(brush = scrimBrush)
                }
            },
    )
}

private class GlassTopAppBarMeasurements {
    var bottomContentHeight: Int = 0
    var progressiveStartFraction: Float by mutableFloatStateOf(0f)
}

@Composable
private fun GlassTopAppBarLayout(
    progressiveBlur: Boolean,
    useCollapseProgress: Boolean,
    scrollBehavior: ScrollBehavior?,
    measurements: GlassTopAppBarMeasurements,
    gradientOverhang: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val topInsets = WindowInsets.systemBars
    Layout(content = content, modifier = modifier) { measurables, constraints ->
        // Measure the foreground first: its bottom slot and the band share this measure pass.
        val foreground = measurables[1].measure(constraints.copy(minWidth = 0, minHeight = 0))
        val width = constraints.constrainWidth(foreground.width)
        val height = constraints.constrainHeight(foreground.height)
        val bandHeight = if (progressiveBlur) {
            val coveredHeight = if (useCollapseProgress && scrollBehavior != null) {
                val state = scrollBehavior.state
                val limit = state.heightOffsetLimit
                if (limit <= 0f && limit >= -height.toFloat()) {
                    val offset = state.heightOffset
                    val boundedOffset = if (offset.isNaN()) 0f else offset.fastCoerceIn(limit, 0f)
                    (height.toFloat() + limit - boundedOffset).fastCoerceAtLeast(
                        TopAppBarDefaults.CollapsedHeight.toPx() + measurements.bottomContentHeight,
                    )
                } else {
                    maxOf(
                        height.toFloat(),
                        topInsets.getTop(this) + TopAppBarDefaults.CollapsedHeight.toPx() + measurements.bottomContentHeight,
                    )
                }
            } else {
                maxOf(
                    height.toFloat(),
                    topInsets.getTop(this) + TopAppBarDefaults.CollapsedHeight.toPx() + measurements.bottomContentHeight,
                )
            }
            (coveredHeight + gradientOverhang.toPx().fastCoerceAtLeast(0f)).fastRoundToInt()
        } else {
            height + gradientOverhang.roundToPx().coerceAtLeast(0)
        }
        val nextStartFraction = if (progressiveBlur && bandHeight > 0 && measurements.bottomContentHeight > 0) {
            (measurements.bottomContentHeight.toFloat() / bandHeight.toFloat()).fastCoerceIn(0f, 0.95f)
        } else {
            0f
        }
        if (measurements.progressiveStartFraction != nextStartFraction) {
            measurements.progressiveStartFraction = nextStartFraction
        }
        val background = measurables[0].measure(Constraints.fixed(width, bandHeight))
        layout(width, height) {
            background.placeRelative(0, 0)
            foreground.placeRelative(0, 0)
        }
    }
}

@Composable
internal fun BlurTopAppBar(
    title: String,
    titleBlurRadius: Dp,
    modifier: Modifier = Modifier,
    expandable: Boolean = true,
    color: Color = MiuixTheme.colorScheme.surface,
    titleColor: Color = MiuixTheme.colorScheme.onSurface,
    largeTitle: String = title,
    largeTitleColor: Color = MiuixTheme.colorScheme.onSurface,
    subtitle: String = "",
    subtitleColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: ScrollBehavior? = null,
    defaultWindowInsetsPadding: Boolean = true,
    titlePadding: Dp = TopAppBarDefaults.TitlePadding,
    navigationIconPadding: Dp = TopAppBarDefaults.NavigationIconPadding,
    actionIconPadding: Dp = TopAppBarDefaults.ActionIconPadding,
    titleAlpha: () -> Float = { 1f },
    centerObstructionStart: Dp = Dp.Unspecified,
    bottomContent: @Composable () -> Unit = {},
) {
    val actionsRow =
        @Composable {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = actions,
            )
        }

    TopAppBarLayout(
        title = title,
        titleAlpha = titleAlpha,
        expandable = expandable,
        color = color,
        titleColor = titleColor,
        largeTitle = largeTitle,
        largeTitleColor = largeTitleColor,
        titleBlurRadius = titleBlurRadius,
        subtitle = subtitle,
        subtitleColor = subtitleColor,
        navigationIcon = navigationIcon,
        actions = actionsRow,
        titlePadding = titlePadding,
        navigationIconPadding = navigationIconPadding,
        actionIconPadding = actionIconPadding,
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        centerObstructionStart = centerObstructionStart,
        bottomContent = bottomContent,
    )
}
