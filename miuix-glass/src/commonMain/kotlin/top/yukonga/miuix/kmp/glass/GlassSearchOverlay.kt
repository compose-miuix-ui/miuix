// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.offset
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.ProgressiveBlur
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.progressiveTextureBlur
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.Close
import top.yukonga.miuix.kmp.icon.basic.Search
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassShadow
import top.yukonga.miuix.kmp.material.GlassShadows
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.GlassStyle
import top.yukonga.miuix.kmp.material.drawGlassShadow
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin
import kotlin.math.sqrt

private const val SEARCH_TOP_BAR_BLUR_RADIUS = 50f

// Map one second of spring motion onto the 400ms search transition.
private val SearchModeEasing: Easing = run {
    val omega = 2.0 * PI / 0.75f
    val stiffness = (omega * omega).toFloat()
    val damping = (0.98f * 2.0 * omega).toFloat()
    val decay = -damping / 2f
    val frequency = sqrt(-(damping.toDouble() * damping - stiffness.toDouble() * 4)).toFloat() / 2f
    val sineCoefficient = decay / frequency
    Easing { fraction ->
        (
            1.0 + exp((decay * fraction).toDouble()) *
                (-cos((frequency * fraction).toDouble()) + sineCoefficient * sin((frequency * fraction).toDouble()))
            ).toFloat()
    }
}

/** Default values for [GlassSearchOverlay]. */
object GlassSearchOverlayDefaults {
    val BarHeight = 44.dp
    val HorizontalPadding = GlassTopAppBarDefaults.HorizontalPadding
    val VerticalPadding = 4.dp
    val ProgressiveBlurOverhang = 24.dp

    @Composable
    fun material(): GlassMaterial = GlassNavigationRailDefaults.material()

    @Composable
    fun stroke(): EdgeStroke = GlassNavigationRailDefaults.stroke()

    fun shadow(): GlassShadow = GlassShadows.Float
}

/**
 * A full-screen glass search overlay with a progressive blur header band and
 * glassmorphic search bar + cancel button.
 */
@Composable
@NonRestartableComposable
fun GlassSearchOverlay(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    horizontalInsetsModifier: Modifier = Modifier,
    label: String = "Search",
    scrollOffset: () -> Float = { 0f },
    topInsets: WindowInsets = WindowInsets.statusBars,
    searchTopPadding: () -> Dp = { 0.dp },
    blurEnabled: Boolean = true,
    style: GlassStyle = GlassDefaults.Style,
    material: GlassMaterial = GlassSearchOverlayDefaults.material(),
    stroke: EdgeStroke? = GlassSearchOverlayDefaults.stroke(),
    shadow: GlassShadow? = GlassSearchOverlayDefaults.shadow(),
    searchIcon: ImageVector = MiuixIcons.Basic.Search,
    closeIcon: ImageVector = MiuixIcons.Basic.Close,
    content: @Composable ColumnScope.(String) -> Unit = {},
) = GlassSearchOverlay(
    expanded = expanded,
    onDismissRequest = onDismissRequest,
    scrollMaterialThreshold = Dp.Unspecified,
    modifier = modifier,
    horizontalInsetsModifier = horizontalInsetsModifier,
    label = label,
    scrollOffset = scrollOffset,
    topInsets = topInsets,
    searchTopPadding = searchTopPadding,
    blurEnabled = blurEnabled,
    style = style,
    material = material,
    stroke = stroke,
    shadow = shadow,
    searchIcon = searchIcon,
    closeIcon = closeIcon,
    content = content,
)

/**
 * A full-screen search overlay that transitions the input and cancel button from a solid fill to glass
 * as results scroll under it.
 *
 * @param scrollMaterialThreshold The result-list scroll distance before the input and cancel button gain glass.
 *   [Dp.Unspecified] keeps both glass at all scroll positions.
 * @param scrollOffset The result-list scroll offset in pixels, or zero when there are no results.
 */
@Composable
fun GlassSearchOverlay(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    scrollMaterialThreshold: Dp,
    modifier: Modifier = Modifier,
    horizontalInsetsModifier: Modifier = Modifier,
    label: String = "Search",
    scrollOffset: () -> Float = { 0f },
    topInsets: WindowInsets = WindowInsets.statusBars,
    searchTopPadding: () -> Dp = { 0.dp },
    blurEnabled: Boolean = true,
    style: GlassStyle = GlassDefaults.Style,
    material: GlassMaterial = GlassSearchOverlayDefaults.material(),
    stroke: EdgeStroke? = GlassSearchOverlayDefaults.stroke(),
    shadow: GlassShadow? = GlassSearchOverlayDefaults.shadow(),
    searchIcon: ImageVector = MiuixIcons.Basic.Search,
    closeIcon: ImageVector = MiuixIcons.Basic.Close,
    content: @Composable ColumnScope.(String) -> Unit = {},
) {
    val transition = updateTransition(expanded, label = "glassSearch")
    val progress = transition.animateFloat(
        transitionSpec = { tween(400, easing = SearchModeEasing) },
        label = "glassSearchGeometry",
    ) { if (it) 1f else 0f }
    val cancelAlpha = transition.animateFloat(
        transitionSpec = { tween(150, delayMillis = if (targetState) 100 else 0, easing = LinearEasing) },
        label = "glassSearchCancelMaterial",
    ) { if (it) 1f else 0f }
    val queryState = rememberTextFieldState()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    val direction = LocalLayoutDirection.current
    val active = expanded || transition.currentState || transition.isRunning
    LaunchedEffect(expanded) {
        queryState.clearText()
        if (expanded) {
            focusRequester.requestFocus()
            keyboard?.show()
        } else if (active) {
            focusManager.clearFocus()
            keyboard?.hide()
        }
    }
    if (!active) return
    NavigationBackHandler(
        state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
        isBackEnabled = expanded,
        onBackCompleted = onDismissRequest,
    )
    val colors = MiuixTheme.colorScheme
    val mutedColor = if (MiuixTheme.isDynamicColor) colors.onSurfaceVariantSummary else colors.disabledOnSurface
    Box(
        modifier = modifier
            .fillMaxSize()
            .onPreviewKeyEvent {
                if (it.key == Key.Escape && it.type == KeyEventType.KeyUp) {
                    onDismissRequest()
                    true
                } else {
                    false
                }
            },
    ) {
        // Block input through the faded bar and its padding until exit completes.
        Box(
            modifier = Modifier
                .matchParentSize()
                .drawBehind { drawRect(colors.surface.copy(alpha = progress.value.coerceIn(0f, 1f))) }
                .clickable(interactionSource = null, indication = null) { keyboard?.hide() },
        )
        val blurActive = blurEnabled && isRuntimeShaderSupported()
        val contentBackdrop = if (blurActive) rememberLayerBackdrop() else null
        val currentScrollOffset by rememberUpdatedState(scrollOffset)
        val pillMaterialProgress = if (contentBackdrop != null && scrollMaterialThreshold.isSpecified) {
            val thresholdPx = with(LocalDensity.current) { scrollMaterialThreshold.toPx() }
            val hasOverlap by remember(thresholdPx) { derivedStateOf { currentScrollOffset() > thresholdPx } }
            animateFloatAsState(
                targetValue = if (expanded && hasOverlap) 1f else 0f,
                animationSpec = tween(450, easing = LinearEasing),
                label = "glassSearchPillMaterial",
            )
        } else {
            null
        }
        val baseBandHeight = GlassSearchOverlayDefaults.BarHeight +
            GlassSearchOverlayDefaults.VerticalPadding * 2 + GlassSearchOverlayDefaults.ProgressiveBlurOverhang
        val bandLayoutModifier = Modifier.layout { measurable, constraints ->
            val bandHeightPx = (topInsets.getTop(this) + (searchTopPadding() + baseBandHeight).roundToPx())
                .coerceIn(0, constraints.maxHeight)
            val placeable = measurable.measure(constraints.copy(minHeight = bandHeightPx, maxHeight = bandHeightPx))
            layout(placeable.width, bandHeightPx) { placeable.placeRelative(0, 0) }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = progress.value }
                .imePadding(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .then(if (contentBackdrop != null) Modifier.layerBackdrop(contentBackdrop) else Modifier)
                    .background(colors.surface),
            ) {
                content(queryState.text.toString())
            }
            if (contentBackdrop != null) {
                val blendColors = remember(colors.surface) {
                    listOf(BlendColorEntry(colors.surface.copy(alpha = 0.3f)))
                }
                val blurColors = BlurDefaults.blurColors(blendColors = blendColors)
                val rampDistancePx = with(LocalDensity.current) { GlassTopAppBarDefaults.ProgressiveBlurRampDistance.toPx() }
                val bandActive by remember(rampDistancePx) {
                    derivedStateOf { (currentScrollOffset() / rampDistancePx).coerceIn(0f, 1f) > 0.001f }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(bandLayoutModifier)
                        .graphicsLayer {
                            alpha = (scrollOffset() / GlassTopAppBarDefaults.ProgressiveBlurRampDistance.toPx()).coerceIn(0f, 1f)
                        }
                        .progressiveTextureBlur(
                            backdrop = contentBackdrop,
                            shape = RectangleShape,
                            gradient = ProgressiveBlur.Top.copy(curve = 0.5f),
                            blurRadius = GlassTopAppBarDefaults.ProgressiveBlurRadius.value,
                            colors = blurColors,
                            enabled = bandActive,
                        ),
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(bandLayoutModifier)
                        .graphicsLayer {
                            alpha = (scrollOffset() / GlassTopAppBarDefaults.ProgressiveBlurRampDistance.toPx()).coerceIn(0f, 1f)
                        }
                        .drawWithCache {
                            val insetFraction = (topInsets.getTop(this).toFloat() / size.height.coerceAtLeast(1f)).coerceIn(0f, 0.8f)
                            val fadeBrush = Brush.verticalGradient(
                                0f to colors.surface,
                                insetFraction to colors.surface,
                                0.82f to colors.surface.copy(alpha = 0.85f),
                                1f to Color.Transparent,
                            )
                            onDrawBehind { drawRect(fadeBrush) }
                        },
                )
            }
            val pillShape = remember { GlassShape(GlassSearchOverlayDefaults.BarHeight / 2) }
            val glassPillModifier = if (contentBackdrop != null) {
                Modifier
                    .then(
                        if (shadow != null) {
                            Modifier.drawBehind { drawGlassShadow(pillShape, shadow, 1f) }
                        } else {
                            Modifier
                        },
                    )
                    .glass(
                        backdrop = contentBackdrop,
                        shape = pillShape,
                        style = style,
                        material = material,
                        stroke = stroke,
                        shading = false,
                    )
            } else {
                Modifier.background(colors.surfaceContainerHigh, CircleShape)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(horizontalInsetsModifier)
                    .windowInsetsPadding(topInsets)
                    .layout { measurable, constraints ->
                        val top = searchTopPadding().roundToPx().coerceIn(0, constraints.maxHeight)
                        val placeable = measurable.measure(constraints.offset(vertical = -top))
                        layout(placeable.width, placeable.height + top) {
                            placeable.placeRelative(0, top)
                        }
                    }
                    .padding(
                        horizontal = GlassSearchOverlayDefaults.HorizontalPadding,
                        vertical = GlassSearchOverlayDefaults.VerticalPadding,
                    )
                    .graphicsLayer { translationY = -topInsets.getTop(this) * (1f - progress.value) },
            ) {
                BasicTextField(
                    state = queryState,
                    enabled = expanded,
                    lineLimits = TextFieldLineLimits.SingleLine,
                    textStyle = MiuixTheme.textStyles.body1.copy(color = colors.onSurface),
                    cursorBrush = SolidColor(colors.primary),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    onKeyboardAction = { keyboard?.hide() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .layout { measurable, constraints ->
                            val endSpace = (54.dp.toPx() * progress.value).toInt()
                            val width = (constraints.maxWidth - endSpace).coerceAtLeast(0)
                            val placeable = measurable.measure(constraints.copy(minWidth = width, maxWidth = width))
                            layout(constraints.maxWidth, placeable.height) {
                                placeable.placeRelative(0, 0)
                            }
                        }
                        .height(GlassSearchOverlayDefaults.BarHeight)
                        .focusRequester(focusRequester),
                    decorator = { field ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .then(if (pillMaterialProgress == null) glassPillModifier else Modifier),
                        ) {
                            if (pillMaterialProgress != null && contentBackdrop != null) {
                                ScrollSearchPillBackground(
                                    backdrop = contentBackdrop,
                                    shape = pillShape,
                                    fill = colors.surfaceContainerHigh,
                                    style = style,
                                    material = material,
                                    stroke = stroke,
                                    shadow = shadow,
                                    progress = { pillMaterialProgress.value },
                                    modifier = Modifier.matchParentSize(),
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                Icon(
                                    searchIcon,
                                    contentDescription = null,
                                    modifier = Modifier.size(28.dp),
                                    tint = mutedColor,
                                )
                                Box(Modifier.weight(1f)) {
                                    if (queryState.text.isEmpty()) Text(label, color = mutedColor, style = MiuixTheme.textStyles.body1)
                                    field()
                                }
                                if (queryState.text.isNotEmpty()) {
                                    IconButton(
                                        onClick = {
                                            queryState.clearText()
                                            focusRequester.requestFocus()
                                        },
                                        modifier = Modifier.size(24.dp),
                                    ) {
                                        Icon(
                                            closeIcon,
                                            contentDescription = "Clear",
                                            modifier = Modifier.size(16.dp * (7f / 6f)),
                                            tint = mutedColor,
                                        )
                                    }
                                }
                            }
                        }
                    },
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .graphicsLayer {
                            val fraction = progress.value
                            translationX = 66.dp.toPx() * (1f - fraction) * if (direction == LayoutDirection.Ltr) 1f else -1f
                            scaleX = fraction
                            scaleY = fraction
                            alpha = cancelAlpha.value
                            val blur = 50f * (1f - cancelAlpha.value)
                            renderEffect = if (blurActive && blur > 0.5f) BlurEffect(blur, blur, TileMode.Decal) else null
                        },
                ) {
                    if (pillMaterialProgress != null && contentBackdrop != null) {
                        ScrollSearchPillBackground(
                            backdrop = contentBackdrop,
                            shape = pillShape,
                            fill = colors.surfaceContainerHigh,
                            style = style,
                            material = material,
                            stroke = stroke,
                            shadow = shadow,
                            progress = { pillMaterialProgress.value },
                            modifier = Modifier.matchParentSize(),
                        )
                    }
                    IconButton(
                        onClick = onDismissRequest,
                        enabled = expanded,
                        modifier = Modifier
                            .size(GlassSearchOverlayDefaults.BarHeight)
                            .then(if (pillMaterialProgress == null) glassPillModifier else Modifier),
                    ) {
                        Icon(
                            closeIcon,
                            contentDescription = "Cancel",
                            modifier = Modifier.size(28.dp),
                            tint = colors.onSurface,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ScrollSearchPillBackground(
    backdrop: Backdrop,
    shape: GlassShape,
    fill: Color,
    style: GlassStyle,
    material: GlassMaterial,
    stroke: EdgeStroke?,
    shadow: GlassShadow?,
    progress: () -> Float,
    modifier: Modifier = Modifier,
) {
    val currentProgress by rememberUpdatedState(progress)
    val showGlass by remember { derivedStateOf { currentProgress() > 0.001f } }
    Box(
        modifier = modifier
            .then(if (shadow != null) Modifier.drawBehind { drawGlassShadow(shape, shadow, currentProgress()) } else Modifier)
            .background(fill, shape),
    ) {
        if (showGlass) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer { alpha = currentProgress() }
                    .glass(
                        backdrop = backdrop,
                        shape = shape,
                        style = style,
                        material = material,
                        stroke = stroke,
                        shading = false,
                    ),
            )
        }
    }
}

/** Shared page opacity keeps content and its sampled top-bar background in sync. */
@Composable
fun rememberGlassSearchBarAlpha(expanded: Boolean): State<Float> {
    val transition = updateTransition(expanded, label = "glassSearchTopBarOpacity")
    return transition.animateFloat(
        transitionSpec = {
            tween(150, easing = if (targetState) CubicBezierEasing(0f, 0f, 0.67f, 1f) else CubicBezierEasing(0.26f, 0f, 0.6f, 0.2f))
        },
        label = "glassSearchTopBarAlpha",
    ) { if (it) 0f else 1f }
}

/** ActionBarView fades, shrinks to 0.9, and blurs while entering search mode. */
@Composable
fun Modifier.glassSearchBarTransition(expanded: Boolean, blurEnabled: Boolean = true, barAlpha: () -> Float): Modifier {
    val density = LocalDensity.current
    val layerOutset = remember(density) {
        val shadow = GlassShadows.Regular
        val shadowOutset = ((shadow.radius + maxOf(abs(shadow.offsetX), abs(shadow.offsetY))) / GlassDefaults.SourceDensity).dp
        // Keep a fixed halo for the shadows and maximum blur throughout the offscreen fade.
        with(density) { shadowOutset.toPx() } + SEARCH_TOP_BAR_BLUR_RADIUS * 3f
    }
    val transition = updateTransition(expanded, label = "glassSearchTopBar")
    val scale = transition.animateFloat(
        transitionSpec = { tween(400, easing = SearchModeEasing) },
        label = "glassSearchTopBarScale",
    ) { if (it) 0.9f else 1f }
    val blurActive = blurEnabled && isRuntimeShaderSupported()
    val blur = transition.animateFloat(
        transitionSpec = { tween(if (targetState) 150 else 120, easing = LinearEasing) },
        label = "glassSearchTopBarBlur",
    ) { if (it && blurActive) SEARCH_TOP_BAR_BLUR_RADIUS else 0f }
    return drawWithCache {
        val layer = obtainGraphicsLayer()
        val halo = ceil(layerOutset).toInt()
        val layerSize = IntSize(size.width.toInt() + halo * 2, size.height.toInt() + halo * 2)
        onDrawWithContent {
            val opacity = barAlpha().coerceIn(0f, 1f)
            val currentScale = scale.value
            val currentBlur = blur.value
            if (opacity > 0f) {
                if (opacity == 1f && currentScale == 1f && currentBlur == 0f) {
                    drawContent()
                } else {
                    // Keep the halo in the recorded bounds: Skiko can replay nested layers with
                    // LayerOutsets without applying their opacity or render effect.
                    layer.record(density = this@drawWithCache, size = layerSize) {
                        translate(halo.toFloat(), halo.toFloat()) { this@record.drawContent() }
                    }
                    layer.alpha = opacity
                    layer.scaleX = currentScale
                    layer.scaleY = currentScale
                    layer.renderEffect = if (blurActive && currentBlur > 0.5f) BlurEffect(currentBlur, currentBlur, TileMode.Decal) else null
                    translate(-halo.toFloat(), -halo.toFloat()) { drawLayer(layer) }
                }
            }
        }
    }
}
