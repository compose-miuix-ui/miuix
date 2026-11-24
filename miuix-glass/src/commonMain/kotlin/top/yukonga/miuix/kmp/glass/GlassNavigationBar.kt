// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
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
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
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
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import androidx.compose.ui.util.fastRoundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.BadgedBox
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
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
import top.yukonga.miuix.kmp.material.internal.rememberGraphicsResourceGeneration
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.Platform
import top.yukonga.miuix.kmp.utils.platform
import kotlin.math.abs
import kotlin.time.Duration.Companion.milliseconds

/**
 * One destination in a [GlassNavigationBar] or a [GlassNavigationRail].
 *
 * @property icon Icon for this destination.
 * @property label Optional caption below the icon.
 * @property contentDescription Accessibility description announced by a screen reader.
 * @property badge Optional badge drawn on the icon, typically a [top.yukonga.miuix.kmp.basic.Badge].
 *   It is hosted in a [top.yukonga.miuix.kmp.basic.BadgedBox]; keep the item instance stable so
 *   the badge keeps its state.
 */
@Stable
data class GlassNavigationItem(
    val icon: ImageVector,
    val label: String? = null,
    val contentDescription: String? = null,
    val badge: (@Composable () -> Unit)? = null,
)

/** Default values for [GlassNavigationBar]. */
object GlassNavigationBarDefaults {

    /** Minimum height; wrapped captions can make the bar taller. */
    val Height: Dp = 54.dp

    /** Space between the glass capsule and an optional trailing action. */
    val ActionSpacing: Dp = 12.dp

    /** Size of an icon. */
    val IconSize: Dp = 25.dp

    /** Caption size in dp. */
    val LabelSize: Dp = 11.dp

    /** Caption size used when the system font scale reaches 1.6. */
    val LargeLabelSize: Dp = 16.dp

    /** Space above and below each icon-and-caption group. */
    val ContentPaddingVertical: Dp = 6.dp

    /** Gap between an icon and its caption. */
    val LabelSpacing: Dp = 0.dp

    /** Inset between the top and bottom of the bar and the indicator. */
    val IndicatorPaddingVertical: Dp = 3.dp

    /** The bar's own horizontal inset: where the destinations are laid out from. */
    val ContentPaddingHorizontal: Dp = 8.dp

    /** How far the capsule reaches past its destination's share of the bar, on each side. */
    val IndicatorOverhang: Dp = 5.dp

    /** Opacity of the destination under the finger. */
    val PressedAlpha: Float = 0.6f

    /** The rim traced around the bar. */
    @Composable
    @ReadOnlyComposable
    fun stroke(): EdgeStroke = GlassEdgeStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /** The bar's own body. */
    @Composable
    fun material(): GlassMaterial = dynamicGlassMaterial(
        GlassMaterials.puredThinGlass(
            isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        ),
    )

    /** Resting indicator fill. */
    @Composable
    @ReadOnlyComposable
    fun indicatorColor(): Color = indicatorTint().copy(
        alpha = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) 0.12f else 0.06f,
    )

    /** Pressed indicator fill, using the same theme tint at a higher opacity. */
    @Composable
    @ReadOnlyComposable
    fun indicatorPressedColor(): Color = indicatorTint().copy(
        alpha = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) 0.26f else 0.16f,
    )

    @Composable
    @ReadOnlyComposable
    private fun indicatorTint(): Color = when {
        MiuixTheme.isDynamicColor -> MiuixTheme.colorScheme.primary
        MiuixTheme.colorScheme.background.luminance() < 0.5f -> Color.White
        else -> Color.Black
    }
}

/**
 * A floating bottom bar on glass.
 *
 * The indicator follows the pointer while dragging and settles from its current position and
 * velocity on release, without ever overshooting past the outermost destinations.
 *
 * @param items The destinations, in order.
 * @param selectedIndex The index of the current destination.
 * @param onSelect Called once with the final index on pointer release, or on keyboard/accessibility activation. Cancelled gestures do not select.
 * @param backdrop The [Backdrop] supplying the content behind the glass. `null` disables sampling
 *   and fills the capsule with the theme's surfaceContainer color.
 * @param modifier The modifier applied to the bar.
 * @param defaultWindowInsetsPadding Whether to apply the default floating bottom margin and
 *   navigation/caption-bar insets to the whole group. Disable when the caller handles them.
 * @param style The glass material.
 * @param shape The bar's silhouette. Defaults to a capsule of [height].
 * @param alpha Opacity multiplier for the material.
 * @param visible Whether the bar is on screen. Turning it off does not simply hide it: the bar
 *   shrinks, fades and blurs itself away together, and comes back after a short delay.
 * @param stroke Optional bloom stroke along the rim.
 * @param shadow The shadow the floating capsule casts. `null` removes it.
 * @param height Minimum bar height. Content can grow for two-line or large-font captions.
 * @param material The bar's own body when sampling a backdrop. `null` omits explicit material
 *   layers; it does not disable sampling. Ignored when [backdrop] is `null`.
 * @param indicatorPressedColor Fill of the capsule while a finger is on it.
 * @param indicatorColor Fill of the capsule behind the selected destination.
 * @param selectedColor Tint of the selected icon and label.
 * @param unselectedColor Tint of the unselected icons and labels, without additional dimming.
 * @param trailingAction Optional independent action outside the glass capsule. It owns its size
 *   and material, and shares the bar's show/hide transition. Adding or removing it animates its
 *   size, opacity and reserved space. Use [GlassIconButton] for a round action.
 */
@Composable
fun GlassNavigationBar(
    items: List<GlassNavigationItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    defaultWindowInsetsPadding: Boolean = true,
    style: GlassStyle = GlassDefaults.Style,
    shape: GlassShape = GlassShape(CornerSize(50), CornerSize(50), CornerSize(50), CornerSize(50)),
    alpha: Float = 1f,
    visible: Boolean = true,
    stroke: EdgeStroke? = GlassNavigationBarDefaults.stroke(),
    shadow: GlassShadow? = GlassShadows.Float,
    height: Dp = GlassNavigationBarDefaults.Height,
    material: GlassMaterial? = GlassNavigationBarDefaults.material(),
    indicatorColor: Color = GlassNavigationBarDefaults.indicatorColor(),
    indicatorPressedColor: Color = GlassNavigationBarDefaults.indicatorPressedColor(),
    selectedColor: Color = if (MiuixTheme.isDynamicColor) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onBackground,
    unselectedColor: Color = MiuixTheme.colorScheme.onBackground,
    trailingAction: (@Composable () -> Unit)? = null,
) {
    if (items.isEmpty()) return
    val index = selectedIndex.coerceIn(0, items.lastIndex)
    val iconGeneration = rememberGraphicsResourceGeneration()
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val labelSize = with(density) {
        if (fontScale >= 1.6f) GlassNavigationBarDefaults.LargeLabelSize.toSp() else GlassNavigationBarDefaults.LabelSize.toSp()
    }
    val contentStart = with(density) {
        GlassNavigationBarDefaults.ContentPaddingHorizontal.toPx()
    }
    val contentInset = with(density) { GlassNavigationBarDefaults.ContentPaddingHorizontal.toPx() }
    val overhang = with(density) { GlassNavigationBarDefaults.IndicatorOverhang.toPx() }

    val barWidthState = remember { mutableIntStateOf(0) }
    val pressedIndexState = remember { mutableIntStateOf(-1) }
    val dragging = remember { mutableStateOf(false) }
    var settleGeneration by remember { mutableIntStateOf(0) }

    val showScale = remember { Animatable(if (visible) 1f else GlassMotion.NAV_HIDE_SCALE) }
    var interactionEnabled by remember { mutableStateOf(visible) }
    LaunchedEffect(visible) {
        if (visible) {
            interactionEnabled = true
            delay(GlassMotion.NAV_SHOW_DELAY_MS.milliseconds)
            showScale.animateTo(1f, GlassMotion.navShowHide())
        } else {
            showScale.animateTo(GlassMotion.NAV_HIDE_SCALE, GlassMotion.navShowHide())
            interactionEnabled = false
        }
    }

    val scope = rememberCoroutineScope()
    val left = remember { Animatable(0f) }
    val right = remember { Animatable(0f) }
    fun animateIndicator(
        newLeft: Float,
        newRight: Float,
        leftSpring: SpringSpec<Float> = GlassMotion.edgeSpring(newLeft <= left.value),
        rightSpring: SpringSpec<Float> = GlassMotion.edgeSpring(newLeft > left.value),
    ) {
        if (left.targetValue == newLeft && right.targetValue == newRight) return
        // Shared springs preserve velocity when the target changes.
        scope.launch { left.animateTo(newLeft, leftSpring) }
        scope.launch { right.animateTo(newRight, rightSpring) }
    }

    // Settle after composition commits the selection returned by onSelect. The even split waits for
    // the bar's size, so its first "no slot yet" result is revisited once it is known.
    LaunchedEffect(index, settleGeneration, items.size, contentStart, contentInset, overhang) {
        snapshotFlow { barWidthState.intValue }.collectLatest { currentBarWidth ->
            if (dragging.value) return@collectLatest
            if (currentBarWidth <= contentStart + contentInset) return@collectLatest
            val slotWidth = ((currentBarWidth - contentStart - contentInset) / items.size).coerceAtLeast(0f)
            val targetLeft = contentStart - overhang + index * slotWidth
            val targetRight = contentStart + overhang + (index + 1) * slotWidth
            if (right.value <= left.value) {
                left.snapTo(targetLeft)
                right.snapTo(targetRight)
            } else {
                animateIndicator(targetLeft, targetRight)
            }
        }
    }
    val chipPressed by remember { computedStateOf { pressedIndexState.intValue >= 0 } }

    val onSelectState by rememberUpdatedState(onSelect)
    val bottomPaddingProvider: (() -> Dp)? = if (defaultWindowInsetsPadding) {
        val navigationBarBottom = with(density) { WindowInsets.navigationBars.getBottom(this).toDp() }
        val captionBarBottom = with(density) { WindowInsets.captionBar.getBottom(this).toDp() }
        val animatedCaptionBarHeight = animateDpAsState(
            targetValue = captionBarBottom.coerceAtLeast(0.dp),
            animationSpec = tween(durationMillis = 300),
            label = "glassNavigationCaptionBarHeight",
        )
        remember(navigationBarBottom, animatedCaptionBarHeight) {
            {
                val systemBottom = maxOf(navigationBarBottom, animatedCaptionBarHeight.value)
                when {
                    platform() == Platform.IOS -> 24.dp
                    systemBottom <= 16.dp -> 20.dp
                    systemBottom >= 28.dp -> systemBottom
                    else -> 12.dp + systemBottom
                }
            }
        }
    } else {
        null
    }
    val horizontalInsetsModifier = if (defaultWindowInsetsPadding) {
        val cutout = WindowInsets.displayCutout
        val navBars = WindowInsets.navigationBars
        val horizontalInsets = remember(cutout, navBars) {
            cutout.union(navBars).only(WindowInsetsSides.Horizontal)
        }
        Modifier.windowInsetsPadding(horizontalInsets)
    } else {
        Modifier
    }
    val wrappedTrailingAction: (@Composable () -> Unit)? = remember(trailingAction, interactionEnabled) {
        trailingAction?.let { action ->
            {
                Box(
                    modifier = Modifier
                        .then(if (interactionEnabled) Modifier else Modifier.clearAndSetSemantics { })
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            layout(placeable.width, placeable.height) {
                                if (interactionEnabled) placeable.placeRelative(0, 0)
                            }
                        },
                ) { action() }
            }
        }
    }
    GlassNavigationBarLayout(
        modifier = Modifier
            .then(horizontalInsetsModifier)
            .then(modifier)
            .fillMaxWidth()
            .then(
                if (bottomPaddingProvider != null) {
                    Modifier.layout { measurable, constraints ->
                        val bottomPx = bottomPaddingProvider().roundToPx().coerceAtLeast(0)
                        val placeable = measurable.measure(constraints.offset(vertical = -bottomPx))
                        layout(
                            constraints.constrainWidth(placeable.width),
                            constraints.constrainHeight(placeable.height + bottomPx),
                        ) {
                            placeable.placeRelative(0, 0)
                        }
                    }
                } else {
                    Modifier
                },
            )
            .graphicsLayer {
                val show = showScale.value
                val visibility = ((show - GlassMotion.NAV_HIDE_SCALE) / (1f - GlassMotion.NAV_HIDE_SCALE))
                    .coerceIn(0f, 1f)
                scaleX = show
                scaleY = show
                this.alpha = visibility
                val blurPx = ((1f - visibility) * GlassMotion.NAV_HIDE_BLUR_DP).dp.toPx()
                renderEffect = if (blurPx > 0.5f) BlurEffect(blurPx, blurPx, TileMode.Decal) else null
            },
        trailingAction = wrappedTrailingAction,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = height)
                .glassPanel(
                    backdrop = backdrop,
                    shape = shape,
                    style = style,
                    alpha = alpha,
                    material = material,
                    stroke = stroke,
                    shadow = shadow,
                    shading = false,
                    fallback = if (backdrop == null || !isRuntimeShaderSupported()) {
                        Modifier
                            .clip(shape)
                            .background(MiuixTheme.colorScheme.surfaceContainer)
                    } else {
                        null
                    },
                )
                .onSizeChanged { barWidthState.intValue = it.width }
                .then(
                    if (interactionEnabled) {
                        Modifier.pointerInput(
                            items.size,
                            contentStart,
                            contentInset,
                            overhang,
                            layoutDirection,
                        ) {
                            awaitEachGesture {
                                val down = awaitFirstDown(requireUnconsumed = false)
                                val selectionWidth = size.width.toFloat()
                                if (selectionWidth <= contentStart + contentInset) return@awaitEachGesture
                                // Freeze hit regions until release; only the indicator previews selection.
                                val selectionSlotWidth = (selectionWidth - contentStart - contentInset) / items.size
                                fun logicalX(x: Float) = if (layoutDirection == LayoutDirection.Rtl) size.width - x else x
                                fun indexAt(x: Float): Int = ((x - contentStart) / selectionSlotWidth)
                                    .toInt()
                                    .coerceIn(0, items.lastIndex)

                                val minLeft = contentStart - overhang
                                val downX = logicalX(down.position.x)
                                var selectionX = downX
                                var current = indexAt(downX)
                                pressedIndexState.intValue = current
                                dragging.value = true
                                val pressedLeft = minLeft + current * selectionSlotWidth
                                animateIndicator(pressedLeft, pressedLeft + selectionSlotWidth + overhang * 2f)
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
                                        // The layout can still resize externally while a pointer is held.
                                        val width = size.width.toFloat()
                                        val chipW = ((width - contentStart - contentInset) / items.size)
                                            .coerceAtLeast(0f) + overhang * 2f
                                        // Overshoot the destinations slightly so the rendered edge squeezes.
                                        val travelStart = contentStart - contentInset
                                        val maxLeft = (width - chipW).coerceAtLeast(travelStart)
                                        val x = logicalX(change.position.x)
                                        // Both positions use the current local coordinates, so their
                                        // delta excludes movement caused by the capsule's placement.
                                        val pointerDelta = change.position.x - change.previousPosition.x
                                        val delta = if (layoutDirection == LayoutDirection.Rtl) -pointerDelta else pointerDelta
                                        selectionX += delta
                                        if (grabOffset.isNaN()) {
                                            if (abs(selectionX - downX) < viewConfiguration.touchSlop) {
                                                pressedIndexState.intValue = indexAt(selectionX)
                                                continue
                                            }
                                            // Preserve the press anchor, not the unfinished animation's lag.
                                            grabOffset = downX - pressedLeft
                                        }
                                        val chipLeft = (x - grabOffset).coerceIn(travelStart, maxLeft)
                                        val next = indexAt(selectionX)
                                        val target = navigationDragTarget(chipLeft, chipW, travelStart, width, delta, next != current)
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
                        }
                    } else {
                        Modifier
                    },
                ),
            contentAlignment = Alignment.Center,
        ) {
            GlassNavigationIndicator(
                pressed = chipPressed,
                height = height - GlassNavigationBarDefaults.IndicatorPaddingVertical * 2,
                color = indicatorColor,
                pressedColor = indicatorPressedColor,
                modifier = Modifier
                    .matchParentSize()
                    .padding(vertical = GlassNavigationBarDefaults.IndicatorPaddingVertical)
                    .layout { measurable, constraints ->
                        // Both edges are clamped on every frame, not just at the target. Keep the
                        // spring alive so the other edge still rebounds.
                        val bounds = if (right.value <= left.value && constraints.maxWidth > contentStart + contentInset) {
                            val slotWidth = ((constraints.maxWidth - contentStart - contentInset) / items.size).coerceAtLeast(0f)
                            val targetLeft = contentStart - overhang + index * slotWidth
                            val targetRight = contentStart + overhang + (index + 1) * slotWidth
                            navigationIndicatorBounds(
                                targetLeft,
                                targetRight,
                                constraints.maxWidth.toFloat(),
                                contentStart - overhang,
                                contentInset - overhang,
                            )
                        } else {
                            navigationIndicatorBounds(
                                left.value,
                                right.value,
                                constraints.maxWidth.toFloat(),
                                contentStart - overhang,
                                contentInset - overhang,
                            )
                        }
                        val start = bounds.x.fastRoundToInt()
                        val width = (bounds.y.fastRoundToInt() - start).coerceAtLeast(0)
                        val placeable = measurable.measure(
                            constraints.copy(minWidth = width, maxWidth = width),
                        )
                        layout(constraints.maxWidth, placeable.height) {
                            placeable.placeRelative(IntOffset(start, 0))
                        }
                    },
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectableGroup()
                    .padding(horizontal = GlassNavigationBarDefaults.ContentPaddingHorizontal),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val pressedIndexProvider = remember { { pressedIndexState.intValue } }
                items.forEachIndexed { position, item ->
                    key(item) {
                        GlassNavigationBarItemContent(
                            item = item,
                            position = position,
                            selected = position == index,
                            pressedIndexProvider = pressedIndexProvider,
                            selectedColor = selectedColor,
                            unselectedColor = unselectedColor,
                            labelSize = labelSize,
                            height = height,
                            interactionEnabled = interactionEnabled,
                            onSelect = onSelectState,
                            iconGeneration = iconGeneration,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun androidx.compose.foundation.layout.RowScope.GlassNavigationBarItemContent(
    item: GlassNavigationItem,
    position: Int,
    selected: Boolean,
    pressedIndexProvider: () -> Int,
    selectedColor: Color,
    unselectedColor: Color,
    labelSize: androidx.compose.ui.unit.TextUnit,
    height: Dp,
    interactionEnabled: Boolean,
    onSelect: (Int) -> Unit,
    iconGeneration: Any?,
) {
    val tintState = animateColorAsState(
        targetValue = if (selected) selectedColor else unselectedColor,
        animationSpec = GlassMotion.navContent(),
        label = "glassNavigationTint",
    )
    val tintProducer = remember { ColorProducer { tintState.value } }
    val isPressed by remember(position) {
        computedStateOf { pressedIndexProvider() == position }
    }
    val pressAlpha = animateFloatAsState(
        targetValue = if (isPressed) {
            GlassNavigationBarDefaults.PressedAlpha
        } else {
            1f
        },
        animationSpec = GlassMotion.navContentFloat(),
        label = "glassNavigationPress",
    )
    Box(
        modifier = Modifier
            .weight(1f)
            .heightIn(min = height)
            .graphicsLayer { this.alpha = pressAlpha.value }
            .padding(
                horizontal = GlassNavigationBarDefaults.IndicatorOverhang,
                vertical = GlassNavigationBarDefaults.ContentPaddingVertical,
            )
            .then(
                if (interactionEnabled) {
                    Modifier
                        .semantics(mergeDescendants = true) {
                            this.role = Role.Tab
                            this.selected = selected
                            (item.contentDescription ?: item.label)?.let {
                                this.contentDescription = it
                            }
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
                } else {
                    Modifier.clearAndSetSemantics { }
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        androidx.compose.foundation.layout.Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(GlassNavigationBarDefaults.LabelSpacing),
        ) {
            BadgedBox(badge = { item.badge?.invoke() }) {
                val painter = rememberVectorPainter(item.icon)
                // Recreate the icon's draw cache and its tint layer after resume.
                key(iconGeneration) {
                    Icon(
                        painter = painter,
                        tint = tintProducer,
                        contentDescription = null,
                        modifier = Modifier.size(GlassNavigationBarDefaults.IconSize),
                    )
                }
            }
            if (item.label != null) {
                val baseLabelStyle = MiuixTheme.textStyles.footnote2
                val labelTextStyle = remember(baseLabelStyle, labelSize) {
                    baseLabelStyle.copy(fontSize = labelSize)
                }
                Text(
                    text = item.label,
                    color = tintProducer,
                    style = labelTextStyle,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
