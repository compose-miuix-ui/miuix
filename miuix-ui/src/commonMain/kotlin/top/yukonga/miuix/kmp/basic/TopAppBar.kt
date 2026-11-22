// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFirst
import androidx.compose.ui.util.fastRoundToInt
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.anim.folmeSpring
import top.yukonga.miuix.kmp.basic.TopAppBarState.Companion.Saver
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.abs

/**
 * A [TopAppBar] with Miuix style that can collapse and expand based on the
 * scroll position of the content below it.
 *
 * The [TopAppBar] can be configured with a title, a navigation icon, and action icons.
 * The large title will collapse when the content is scrolled up and expand when
 * the content is scrolled down.
 *
 * @param title The title of the [TopAppBar].
 * @param modifier The modifier to be applied to the  [TopAppBar].
 * @param expandable Whether the large title can expand. `false` keeps the compact title visible.
 * @param color The background color of the [TopAppBar].
 * @param titleColor The color of the collapsed small title text.
 * @param largeTitle The large title of the [TopAppBar].
 * @param largeTitleColor The color of the expanded large title text.
 * @param subtitle The subtitle displayed below the title bar area.
 * @param subtitleColor The color of the subtitle text.
 * @param navigationIcon The [Composable] content that represents the navigation icon.
 * @param actions The [Composable] content that represents the action icons.
 * @param scrollBehavior The [ScrollBehavior] that controls the behavior of the [TopAppBar].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding to the [TopAppBar].
 * @param titlePadding The horizontal padding of the [TopAppBar]'s title & large title.
 * @param navigationIconPadding The start padding of the navigation icon.
 * @param actionIconPadding The end padding of the action icons.
 * @param titleAlpha A lambda returning the alpha of the title text.
 * @param bottomContent The [Composable] content displayed below the title bar area.
 */
@Composable
fun TopAppBar(
    title: String,
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
    centerObstructionStart: Dp = Dp.Unspecified,
    titleAlpha: () -> Float = { 1f },
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
        expandable = expandable,
        color = color,
        titleColor = titleColor,
        largeTitle = largeTitle,
        largeTitleColor = largeTitleColor,
        titleBlurRadius = 0.dp,
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
        titleAlpha = titleAlpha,
        bottomContent = bottomContent,
    )
}

/**
 * A [SmallTopAppBar] with Miuix style.
 *
 * The [SmallTopAppBar] can be configured with a title, a navigation icon, and action icons.
 *
 * @param title The title of the [SmallTopAppBar].
 * @param modifier The modifier to be applied to the  [SmallTopAppBar].
 * @param color The background color of the [SmallTopAppBar].
 * @param titleColor The color of the title text.
 * @param subtitle The subtitle displayed below the title bar area.
 * @param subtitleColor The color of the subtitle text.
 * @param navigationIcon The [Composable] content that represents the navigation icon.
 * @param actions The [Composable] content that represents the action icons.
 * @param scrollBehavior The [ScrollBehavior] that controls the behavior of the [SmallTopAppBar].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding to the [SmallTopAppBar].
 * @param titlePadding The horizontal padding of the [SmallTopAppBar]'s title.
 * @param navigationIconPadding The start padding of the navigation icon.
 * @param actionIconPadding The end padding of the action icons.
 * @param bottomContent The [Composable] content displayed below the title bar area.
 */
@Composable
fun SmallTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = MiuixTheme.colorScheme.surface,
    titleColor: Color = MiuixTheme.colorScheme.onSurface,
    subtitle: String = "",
    subtitleColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: ScrollBehavior? = null,
    defaultWindowInsetsPadding: Boolean = true,
    titlePadding: Dp = TopAppBarDefaults.TitlePadding,
    navigationIconPadding: Dp = TopAppBarDefaults.NavigationIconPadding,
    actionIconPadding: Dp = TopAppBarDefaults.ActionIconPadding,
    centerObstructionStart: Dp = Dp.Unspecified,
    bottomContent: @Composable () -> Unit = {},
) {
    SideEffect {
        // Pin the bar: clamp both the range and any offset left by a collapsible bar so nested
        // content still scrolls when they share a ScrollBehavior. Guard against redundant writes.
        scrollBehavior?.state?.let { state ->
            if (!state.pinnedBySmallTopAppBar) state.pinnedBySmallTopAppBar = true
            if (state.heightOffsetLimit != 0f) state.heightOffsetLimit = 0f
            if (state.heightOffset != 0f) state.heightOffset = 0f
        }
    }

    // Wrap the given actions in a Row.
    val actionsRow =
        @Composable {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = actions,
            )
        }

    // Compose a Surface with a SmallTopAppBarLayout content.
    // The surface's background color is animated as specified above.
    // The height of the app bar is determined by subtracting the bar's height offset from the
    // app bar's defined constant height value (i.e. the ContainerHeight token).
    SmallTopAppBarLayout(
        title = title,
        color = color,
        titleColor = titleColor,
        subtitle = subtitle,
        subtitleColor = subtitleColor,
        navigationIcon = navigationIcon,
        actions = actionsRow,
        titlePadding = titlePadding,
        navigationIconPadding = navigationIconPadding,
        actionIconPadding = actionIconPadding,
        modifier = modifier,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        centerObstructionStart = centerObstructionStart,
        bottomContent = bottomContent,
    )
}

/**
 * Returns a [ScrollBehavior] that adjusts its properties to affect the colors and
 * height of the top app bar.
 *
 * A top app bar that is set up with this [ScrollBehavior] will immediately collapse
 * when the nested content is pulled up, and will expand back the collapsed area when the
 * content is pulled all the way down.
 *
 * @param state the state object to be used to control or observe the top app bar's scroll
 *   state. See [rememberTopAppBarState] for a state that is remembered across compositions.
 * @param canScroll a callback used to determine whether scroll events are to be handled by this
 *   [ExitUntilCollapsedScrollBehavior]
 * @param snapAnimationSpec an optional [AnimationSpec] that defines how the top app bar snaps
 *   to either fully collapsed or fully extended state when a fling or a drag scrolled it into
 *   an intermediate position
 * @param flingAnimationSpec an optional [DecayAnimationSpec] that defined how to fling the top
 *   app bar when the user flings the app bar itself, or the content below it
 */
@Suppress("ComposableNaming")
@Composable
fun MiuixScrollBehavior(
    state: TopAppBarState = rememberTopAppBarState(),
    canScroll: () -> Boolean = { true },
    snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = 2500f),
    flingAnimationSpec: DecayAnimationSpec<Float>? = rememberSplineBasedDecay(),
): ScrollBehavior {
    val coroutineScope = rememberCoroutineScope()
    return remember(state, canScroll, snapAnimationSpec, flingAnimationSpec, coroutineScope) {
        ExitUntilCollapsedScrollBehavior(
            state = state,
            snapAnimationSpec = snapAnimationSpec,
            flingAnimationSpec = flingAnimationSpec,
            canScroll = canScroll,
            coroutineScope = coroutineScope,
        )
    }
}

/**
 * Creates a [TopAppBarState] that is remembered across compositions.
 *
 * @param initialHeightOffsetLimit the initial value for [TopAppBarState.heightOffsetLimit], which
 *   represents the pixel limit that a top app bar is allowed to collapse when the scrollable
 *   content is scrolled
 * @param initialHeightOffset the initial value for [TopAppBarState.heightOffset]. The initial
 *   offset height offset should be between zero and [initialHeightOffsetLimit].
 * @param initialContentOffset the initial value for [TopAppBarState.contentOffset]
 */
@Composable
fun rememberTopAppBarState(
    initialHeightOffsetLimit: Float = -Float.MAX_VALUE,
    initialHeightOffset: Float = 0f,
    initialContentOffset: Float = 0f,
): TopAppBarState = rememberSaveable(saver = Saver) {
    TopAppBarState(initialHeightOffsetLimit, initialHeightOffset, initialContentOffset)
}

/**
 * A state object that can be hoisted to control and observe the top app bar state. The state is
 * read and updated by a [ScrollBehavior] implementation.
 *
 * In most cases, this state will be created via [rememberTopAppBarState].
 *
 * @param initialHeightOffsetLimit the initial value for [TopAppBarState.heightOffsetLimit]
 * @param initialHeightOffset the initial value for [TopAppBarState.heightOffset]
 * @param initialContentOffset the initial value for [TopAppBarState.contentOffset]
 */
@Stable
class TopAppBarState(
    initialHeightOffsetLimit: Float,
    initialHeightOffset: Float,
    initialContentOffset: Float,
) {

    /**
     * The top app bar's height offset limit in pixels, which represents the limit that a top app
     * bar is allowed to collapse to.
     *
     * Use this limit to coerce the [heightOffset] value when it's updated.
     */
    var heightOffsetLimit = initialHeightOffsetLimit

    /**
     * The top app bar's current height offset in pixels. This height offset is applied to the fixed
     * height of the app bar to control the displayed height when content is being scrolled.
     *
     * Updates to the [heightOffset] value are coerced between zero and [heightOffsetLimit].
     */
    var heightOffset: Float
        get() = _heightOffset.floatValue
        set(newOffset) {
            _heightOffset.floatValue =
                newOffset.coerceIn(minimumValue = heightOffsetLimit, maximumValue = 0f)
        }

    /**
     * The total offset of the content scrolled under the top app bar.
     *
     * The content offset is used to compute the [overlappedFraction], which can later be read by an
     * implementation.
     *
     * This value is updated by a [ScrollBehavior] whenever a nested scroll connection
     * consumes scroll events. A common implementation would update the value to be the sum of all
     * [NestedScrollConnection.onPostScroll] `consumed.y` values.
     */
    var contentOffset by mutableFloatStateOf(initialContentOffset)

    /**
     * Temporary, damped vertical overscroll displacement in pixels. Update this from
     * `overScrollVertical(onOffsetChanged = ...)`, including spring frames and reset to zero.
     * It affects visual treatments, not title collapse or saved scroll position.
     */
    var overscrollOffset by mutableFloatStateOf(0f)

    /** Content position used by scroll-dependent visual treatments, including elastic motion. */
    val visualContentOffset: Float
        get() = contentOffset + overscrollOffset

    internal var pinnedBySmallTopAppBar by mutableStateOf(false)

    /**
     * A value that represents the collapsed height percentage of the app bar.
     *
     * A `0.0` represents a fully expanded bar, and `1.0` represents a fully collapsed bar (computed
     * as [heightOffset] / [heightOffsetLimit]).
     */
    val collapsedFraction: Float
        get() =
            if (heightOffsetLimit != 0f) {
                heightOffset / heightOffsetLimit
            } else {
                0f
            }

    /**
     * A value that represents the percentage of the app bar area that is overlapping with the
     * content scrolled behind it.
     *
     * A `0.0` indicates that the app bar does not overlap any content, while `1.0` indicates that
     * the entire visible app bar area overlaps the scrolled content.
     */
    val overlappedFraction: Float
        get() =
            if (heightOffsetLimit != 0f) {
                1 -
                    (
                        (heightOffsetLimit - contentOffset).coerceIn(
                            minimumValue = heightOffsetLimit,
                            maximumValue = 0f,
                        ) / heightOffsetLimit
                        )
            } else {
                0f
            }

    companion object {
        /** The default [Saver] implementation for [TopAppBarState]. */
        val Saver: Saver<TopAppBarState, *> =
            listSaver(
                save = {
                    listOf(
                        it.heightOffsetLimit,
                        it.heightOffset,
                        it.contentOffset,
                        it.pinnedBySmallTopAppBar,
                    )
                },
                restore = {
                    TopAppBarState(
                        initialHeightOffsetLimit = it[0] as Float,
                        initialHeightOffset = it[1] as Float,
                        initialContentOffset = it[2] as Float,
                    ).apply {
                        pinnedBySmallTopAppBar = it.getOrNull(3) as? Boolean ?: false
                    }
                },
            )
    }

    private var _heightOffset = mutableFloatStateOf(initialHeightOffset)
}

/** Contains default values used by [TopAppBar] and [SmallTopAppBar]. */
object TopAppBarDefaults {
    /** The default horizontal padding of the title and large title. */
    val TitlePadding = 26.dp

    /** The spacing between the title and adjacent controls. */
    val TitleControlSpacing = 12.dp

    /** The default start padding of the navigation icon. */
    val NavigationIconPadding = 16.dp

    /** The default end padding of the action icons. */
    val ActionIconPadding = 16.dp

    /** The default collapsed height of the [TopAppBar]. */
    val CollapsedHeight = 52.dp

    /** The vertical center height used for [SmallTopAppBar] layout. */
    val SmallTopAppBarCenterHeight = 50.dp

    /** The bottom padding below the large title when no subtitle is present. */
    val LargeTitleBottomPadding = 4.dp

    /** The bottom padding below the subtitle (both large and small). */
    val SubtitleBottomPadding = 8.dp
}

@Stable
interface ScrollBehavior {

    /**
     * A [TopAppBarState] that is attached to this behavior and is read and updated when scrolling
     * happens.
     */
    val state: TopAppBarState

    /**
     * Indicates whether the top app bar is pinned.
     *
     * A pinned app bar will stay fixed in place when content is scrolled and will not react to any
     * drag gestures.
     */
    val isPinned: Boolean

    /**
     * An optional [AnimationSpec] that defines how the top app bar snaps to either fully collapsed
     * or fully extended state when a fling or a drag scrolled it into an intermediate position.
     */
    val snapAnimationSpec: AnimationSpec<Float>?

    /**
     * An optional [DecayAnimationSpec] that defined how to fling the top app bar when the user
     * flings the app bar itself, or the content below it.
     */
    val flingAnimationSpec: DecayAnimationSpec<Float>?

    /**
     * A [NestedScrollConnection] that should be attached to a [Modifier.nestedScroll] in order to
     * keep track of the scroll events.
     */
    val nestedScrollConnection: NestedScrollConnection
}

/**
 * A [ScrollBehavior] that adjusts its properties to affect the colors and height of a top
 * app bar.
 *
 * A top app bar that is set up with this [ScrollBehavior] will immediately collapse when
 * the nested content is pulled up, and will expand back the collapsed area when the content is
 * pulled all the way down.
 *
 * @param state a [TopAppBarState]
 * @param snapAnimationSpec an optional [AnimationSpec] that defines how the top app bar snaps to
 *   either fully collapsed or fully extended state when a fling or a drag scrolled it into an
 *   intermediate position
 * @param flingAnimationSpec an optional [DecayAnimationSpec] that defined how to fling the top app
 *   bar when the user flings the app bar itself, or the content below it
 * @param canScroll a callback used to determine whether scroll events are to be handled by this
 *   [ExitUntilCollapsedScrollBehavior]
 */
private class ExitUntilCollapsedScrollBehavior(
    override val state: TopAppBarState,
    override val snapAnimationSpec: AnimationSpec<Float>?,
    override val flingAnimationSpec: DecayAnimationSpec<Float>?,
    val canScroll: () -> Boolean = { true },
    private val coroutineScope: CoroutineScope? = null,
) : ScrollBehavior {
    override val isPinned: Boolean = false
    private var idleSettleJob: Job? = null
    internal var inputTracker: TopAppBarInputTracker? = null

    private fun scheduleIdleSettle() {
        if (inputTracker?.allowIdleSettle != true) {
            idleSettleJob?.cancel()
            return
        }
        if (snapAnimationSpec == null || coroutineScope == null) return
        if (state.collapsedFraction < 0.01f || state.collapsedFraction > 0.99f) {
            idleSettleJob?.cancel()
            return
        }
        idleSettleJob?.cancel()
        idleSettleJob = coroutineScope.launch {
            delay(180L)
            settleAppBar(state, 0f, flingAnimationSpec, snapAnimationSpec)
        }
    }

    override var nestedScrollConnection =
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // Don't intercept if scrolling down.
                if (!canScroll() || available.y > 0) return Offset.Zero
                val prevHeightOffset = state.heightOffset
                state.heightOffset += available.y
                return if (prevHeightOffset != state.heightOffset) {
                    scheduleIdleSettle()
                    // We're in the middle of top app bar collapse or expand.
                    // Consume only the scroll on the Y axis.
                    available.copy(x = 0f)
                } else {
                    Offset.Zero
                }
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource,
            ): Offset {
                if (!canScroll()) return Offset.Zero
                state.contentOffset += consumed.y

                if (available.y < 0f || consumed.y < 0f) {
                    // When scrolling up, just update the state's height offset.
                    val oldHeightOffset = state.heightOffset
                    state.heightOffset += consumed.y
                    if (oldHeightOffset != state.heightOffset) {
                        scheduleIdleSettle()
                    }
                    return Offset(0f, state.heightOffset - oldHeightOffset)
                }

                if (available.y > 0f) {
                    // Adjust the height offset in case the consumed delta Y is less than what was
                    // recorded as available delta Y in the pre-scroll.
                    val oldHeightOffset = state.heightOffset
                    state.heightOffset += available.y
                    if (oldHeightOffset != state.heightOffset) {
                        scheduleIdleSettle()
                    }
                    return Offset(0f, state.heightOffset - oldHeightOffset)
                }
                return Offset.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                idleSettleJob?.cancel()
                if (available.y > 0) {
                    // Reset the total content offset to zero when scrolling all the way down. This
                    // will eliminate some float precision inaccuracies.
                    state.contentOffset = 0f
                }
                val superConsumed = super.onPostFling(consumed, available)
                return superConsumed +
                    settleAppBar(state, available.y, flingAnimationSpec, snapAnimationSpec)
            }
        }
}

/**
 * Settles the app bar to a stable state (fully expanded or collapsed) by animating
 * its height offset.
 *
 * This function is invoked after a drag or fling gesture, using the provided velocity
 * to drive a decay animation, followed by a snap animation if the bar is left in an
 * intermediate state.
 *
 * @param state The [TopAppBarState] that holds the current and target height offsets.
 * @param velocity The velocity from the fling gesture to be consumed.
 * @param flingAnimationSpec The [DecayAnimationSpec] for the fling animation.
 * @param snapAnimationSpec The [AnimationSpec] for the final snap to a stable state.
 * @return The [Velocity] that was actually consumed by the fling decay animation. This
 * ensures accurate reporting within the nested scroll system, allowing any unconsumed
 * velocity to be propagated to parent consumers.
 */
private suspend fun settleAppBar(
    state: TopAppBarState,
    velocity: Float,
    flingAnimationSpec: DecayAnimationSpec<Float>?,
    snapAnimationSpec: AnimationSpec<Float>?,
): Velocity {
    // Check if the app bar is completely collapsed/expanded. If so, no need to settle the app bar,
    // and just return Zero Velocity.
    // Note that we don't check for 0f due to float precision with the collapsedFraction
    // calculation.
    if (state.collapsedFraction < 0.01f || state.collapsedFraction == 1f) {
        return Velocity.Zero
    }
    var remainingVelocity = velocity
    // In case there is an initial velocity that was left after a previous user fling, animate to
    // continue the motion to expand or collapse the app bar.
    if (flingAnimationSpec != null && abs(velocity) > 1f) {
        var lastValue = 0f
        AnimationState(initialValue = 0f, initialVelocity = velocity).animateDecay(
            flingAnimationSpec,
        ) {
            val delta = value - lastValue
            val initialHeightOffset = state.heightOffset
            state.heightOffset = initialHeightOffset + delta
            val consumed = abs(initialHeightOffset - state.heightOffset)
            lastValue = value
            remainingVelocity = this.velocity
            // avoid rounding errors and stop if anything is unconsumed
            if (abs(delta - consumed) > 0.5f) this.cancelAnimation()
        }
    }
    // Snap if animation specs were provided.
    if (snapAnimationSpec != null) {
        if (state.heightOffset < 0 && state.heightOffset > state.heightOffsetLimit) {
            AnimationState(initialValue = state.heightOffset).animateTo(
                if (state.collapsedFraction < 0.5f) {
                    0f
                } else {
                    state.heightOffsetLimit
                },
                animationSpec = snapAnimationSpec,
            ) {
                state.heightOffset = value
            }
        }
    }
    return Velocity(0f, velocity - remainingVelocity)
}

/**
 * The base [Layout] for [TopAppBar]. This function lays out a [TopAppBar] navigation icon
 * (leading icon), a title (header), and action icons (trailing icons). Note that the navigation and
 * the actions are optional.
 *
 * @param title the [TopAppBar] title (header).
 * @param color the background color of the [TopAppBar].
 * @param titleColor the color of the collapsed small title text.
 * @param largeTitleColor the color of the expanded large title text.
 * @param subtitle the subtitle text displayed below the title bar area.
 * @param subtitleColor the color of the subtitle text.
 * @param navigationIcon a navigation icon [Composable].
 * @param actions actions [Composable].
 * @param titlePadding the horizontal padding of the [TopAppBar]'s title & large title.
 * @param navigationIconPadding the start padding of the navigation icon.
 * @param actionIconPadding the end padding of the action icons.
 * @param scrollBehavior the [ScrollBehavior] that drives the collapse/expand state. The layout
 *   reads its `heightOffset` and `collapsedFraction` directly inside layout/draw-phase lambdas,
 *   and writes the matching `heightOffsetLimit` from the large title's measured height.
 * @param modifier the [Modifier] to be applied to this layout.
 * @param largeTitle the large title of the [TopAppBar], if not specified, it will be the same as title.
 * @param titleBlurRadius maximum blur radius applied during title collapse/expand transitions.
 * @param defaultWindowInsetsPadding whether to apply default window insets padding to the [TopAppBar].
 * @param bottomContent the composable content displayed below the title bar area.
 */
@Composable
fun TopAppBarLayout(
    title: String,
    color: Color,
    titleColor: Color,
    largeTitleColor: Color,
    subtitle: String,
    subtitleColor: Color,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable () -> Unit,
    titlePadding: Dp,
    navigationIconPadding: Dp,
    actionIconPadding: Dp,
    scrollBehavior: ScrollBehavior?,
    modifier: Modifier = Modifier,
    expandable: Boolean = true,
    largeTitle: String = title,
    titleBlurRadius: Dp = 0.dp,
    defaultWindowInsetsPadding: Boolean = true,
    titleAlpha: () -> Float = { 1f },
    centerObstructionStart: Dp = Dp.Unspecified,
    bottomContent: @Composable () -> Unit = {},
) {
    val titleClip = remember { TopAppBarTitleClip() }
    val inputTracker = LocalTopAppBarInputTracker.current
    SideEffect {
        (scrollBehavior as? ExitUntilCollapsedScrollBehavior)?.inputTracker = inputTracker
    }
    // Producer lambdas — reads stay in layout/draw phases so scroll never recomposes this subtree.
    val scrolledOffset = remember(scrollBehavior) {
        { scrollBehavior?.state?.heightOffset ?: 0f }
    }
    // The title does not merely fade out — it goes out of focus on the way, the way the source
    // system dissolves it. `progress` is shared by both so the blur peaks exactly as the last of
    // the title goes, instead of stopping short of it or outliving it.
    val largeTitleFade = remember(scrollBehavior) {
        {
            val frac = scrollBehavior?.state?.collapsedFraction ?: 0f
            (frac * 3f).coerceIn(0f, 1f)
        }
    }
    val largeTitleAlpha = remember(largeTitleFade) {
        {
            1f - largeTitleFade()
        }
    }
    val updateHeightOffsetLimit = remember(scrollBehavior, expandable) {
        { height: Int ->
            scrollBehavior?.state?.let { state ->
                if (!expandable) {
                    state.pinnedBySmallTopAppBar = true
                    state.heightOffsetLimit = 0f
                    state.heightOffset = 0f
                } else {
                    val limit = -height.toFloat()
                    if (state.heightOffsetLimit != limit) {
                        val restoringFromSmallTopAppBar = state.pinnedBySmallTopAppBar
                        state.heightOffsetLimit = limit
                        // A pinned small bar retains contentOffset while its own height stays fixed.
                        if (restoringFromSmallTopAppBar) {
                            state.heightOffset = if (state.contentOffset < 0f) limit else 0f
                            state.pinnedBySmallTopAppBar = false
                        }
                    }
                }
            }
            Unit
        }
    }

    // Boolean computedStateOf invalidates only on flip → the spring fires once per crossing, not per frame.
    val smallTitleVisible by remember(scrollBehavior, expandable) {
        computedStateOf {
            !expandable || scrollBehavior?.state?.let { state ->
                if (state.pinnedBySmallTopAppBar) {
                    state.contentOffset < 0f
                } else {
                    state.collapsedFraction * 3f >= 1f
                }
            } ?: false
        }
    }
    val smallTitleAlpha = remember { Animatable(if (smallTitleVisible) 1f else 0f) }
    val smallTitleTranslationY = remember { Animatable(if (smallTitleVisible) 0f else 20f) }

    LaunchedEffect(smallTitleVisible) {
        if (smallTitleVisible) {
            val showSpec = folmeSpring<Float>(damping = 1.0f, response = 0.3f)
            launch { smallTitleAlpha.animateTo(1f, showSpec) }
            launch { smallTitleTranslationY.animateTo(0f, showSpec) }
        } else {
            val hideSpec = folmeSpring<Float>(damping = 1.0f, response = 0.15f)
            launch { smallTitleAlpha.animateTo(0f, hideSpec) }
            launch { smallTitleTranslationY.animateTo(20f, hideSpec) }
        }
    }
    val alignmentState = rememberTitleAlignmentState()
    val obstructionState = rememberTitleObstructionTransitionState()

    val animatedTitleColor by animateColorAsState(
        targetValue = titleColor,
        animationSpec = tween(durationMillis = 50),
    )
    val animatedLargeTitleColor by animateColorAsState(
        targetValue = largeTitleColor,
        animationSpec = tween(durationMillis = 50),
    )
    val animatedSubtitleColor by animateColorAsState(
        targetValue = subtitleColor,
        animationSpec = tween(durationMillis = 50),
    )

    Layout(
        {
            Box(
                Modifier
                    .layoutId("navigationIcon")
                    .padding(start = navigationIconPadding),
            ) {
                navigationIcon()
            }
            Box(
                Modifier
                    .layoutId("title")
                    .clipTopAppBarTitle(titleClip) { titleClip.titleOrigin }
                    .graphicsLayer {
                        val currentAlpha = smallTitleAlpha.value
                        val obstructionAlpha = obstructionState.visibilityAlpha.value
                        alpha = currentAlpha * obstructionAlpha * titleAlpha()
                        translationY = smallTitleTranslationY.value
                        val blurAmount = maxOf(
                            (1f - currentAlpha).coerceIn(0f, 1f),
                            obstructionState.blurFraction.value.coerceIn(0f, 1f),
                        )
                        val radius = if (titleBlurRadius > 0.dp) {
                            titleBlurRadius.toPx() * 0.5f * blurAmount
                        } else {
                            0f
                        }
                        renderEffect = if (radius > 0.1f) {
                            BlurEffect(radius, radius, TileMode.Decal)
                        } else {
                            null
                        }
                    },
            ) {
                Text(
                    text = title,
                    color = animatedTitleColor,
                    fontSize = MiuixTheme.textStyles.title3.fontSize,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false,
                    onTextLayout = { obstructionState.hasOverflow = it.hasVisualOverflow },
                )
            }
            Box(
                Modifier
                    .layoutId("actionIcons")
                    .padding(end = actionIconPadding),
            ) {
                actions()
            }
            Box(
                Modifier
                    .layoutId("largeTitle")
                    .clipTopAppBarTitle(titleClip) { titleClip.largeTitleOrigin }
                    .padding(top = TopAppBarDefaults.CollapsedHeight)
                    .padding(horizontal = titlePadding)
                    .graphicsLayer {
                        alpha = largeTitleAlpha() * titleAlpha()
                        // Assigned on every path, including the disabled one: the layer keeps
                        // whatever it was last given, so a radius that drops back to zero would
                        // otherwise leave the last blur on the title for good.
                        val radius = if (titleBlurRadius > 0.dp) {
                            titleBlurRadius.toPx() * largeTitleFade()
                        } else {
                            0f
                        }
                        renderEffect = if (radius > 0.1f) {
                            BlurEffect(radius, radius, TileMode.Decal)
                        } else {
                            null
                        }
                    },
            ) {
                Column(
                    modifier = Modifier
                        .offset {
                            val v = scrolledOffset()
                            IntOffset(0, v.fastRoundToInt())
                        }
                        .onSizeChanged { updateHeightOffsetLimit(it.height) },
                ) {
                    if (expandable) {
                        Text(
                            text = largeTitle,
                            color = animatedLargeTitleColor,
                            fontSize = MiuixTheme.textStyles.title1.fontSize,
                            fontWeight = FontWeight.Normal,
                        )
                        if (subtitle.isNotEmpty()) {
                            Text(
                                text = subtitle,
                                color = animatedSubtitleColor,
                                style = MiuixTheme.textStyles.body2,
                            )
                        }
                    }
                }
            }
            if (subtitle.isNotEmpty()) {
                Box(
                    Modifier
                        .layoutId("smallSubtitle")
                        .clipTopAppBarTitle(titleClip) { titleClip.subtitleOrigin }
                        .graphicsLayer {
                            val currentAlpha = smallTitleAlpha.value
                            val obstructionAlpha = obstructionState.visibilityAlpha.value
                            alpha = currentAlpha * obstructionAlpha * titleAlpha()
                            translationY = smallTitleTranslationY.value
                            val blurAmount = maxOf(
                                (1f - currentAlpha).coerceIn(0f, 1f),
                                obstructionState.blurFraction.value.coerceIn(0f, 1f),
                            )
                            val radius = if (titleBlurRadius > 0.dp) {
                                titleBlurRadius.toPx() * 0.5f * blurAmount
                            } else {
                                0f
                            }
                            renderEffect = if (radius > 0.1f) {
                                BlurEffect(radius, radius, TileMode.Decal)
                            } else {
                                null
                            }
                        },
                ) {
                    Text(
                        text = subtitle,
                        color = animatedSubtitleColor,
                        style = MiuixTheme.textStyles.body2,
                    )
                }
            }
            Box(Modifier.layoutId("bottomContent")) {
                bottomContent()
            }
        },
        modifier = modifier
            .background(color)
            .then(
                if (defaultWindowInsetsPadding) {
                    Modifier
                        .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal))
                        .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal))
                } else {
                    Modifier
                },
            )
            // Titles retain the viewport clip; controls can cast shadows beyond the bar.
            .onSizeChanged { titleClip.viewportSize = it }
            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Top))
            .pointerInput(Unit) {
                detectTapGestures { /* Consume click */ }
            },
    ) { measurables, constraints ->
        val navigationIconPlaceable =
            measurables
                .fastFirst { it.layoutId == "navigationIcon" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))

        val actionIconsPlaceable =
            measurables
                .fastFirst { it.layoutId == "actionIcons" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))
        val titlePaddingPx = titlePadding.roundToPx()
        val controlTitleSpacingPx = minOf(titlePaddingPx, TopAppBarDefaults.TitleControlSpacing.roundToPx())
        val startInset = if (navigationIconPlaceable.width > 0) controlTitleSpacingPx else titlePaddingPx
        val endInset = if (actionIconsPlaceable.width > 0) controlTitleSpacingPx else titlePaddingPx
        val hasCenterObstruction = centerObstructionStart != Dp.Unspecified && constraints.maxWidth != Constraints.Infinity
        val normalTitleMaxWidth =
            if (constraints.maxWidth == Constraints.Infinity) {
                constraints.maxWidth
            } else {
                (constraints.maxWidth - navigationIconPlaceable.width - actionIconsPlaceable.width - startInset - endInset)
                    .coerceAtLeast(0)
            }
        val obstructedWidth =
            if (hasCenterObstruction) {
                val obstructionLimit = centerObstructionStart.roundToPx() - controlTitleSpacingPx
                val startEdge = navigationIconPlaceable.width + startInset
                val available = (obstructionLimit - startEdge).coerceAtLeast(0)
                if (available < MinTitleWidth.roundToPx()) 0 else available
            } else {
                Int.MAX_VALUE
            }
        val titleMaxWidth = obstructionState.resolveMeasureMaxWidth(
            hasCenterObstruction = hasCenterObstruction,
            normalMaxWidth = normalTitleMaxWidth,
            obstructedWidth = obstructedWidth,
        )
        val titlePlaceable =
            measurables
                .fastFirst { it.layoutId == "title" }
                .measure(constraints.copy(minWidth = 0, maxWidth = titleMaxWidth, minHeight = 0))
        val largeTitlePlaceable =
            measurables
                .fastFirst { it.layoutId == "largeTitle" }
                .measure(
                    constraints.copy(
                        minWidth = 0,
                        minHeight = 0,
                        maxHeight = Constraints.Infinity,
                    ),
                )

        val smallSubtitlePlaceable =
            measurables
                .firstOrNull { it.layoutId == "smallSubtitle" }
                ?.measure(constraints.copy(minWidth = 0, maxWidth = titleMaxWidth, minHeight = 0))
        obstructionState.currentContentWidth = maxOf(titlePlaceable.width, smallSubtitlePlaceable?.width ?: 0)
        val bottomContentPlaceable =
            measurables
                .fastFirst { it.layoutId == "bottomContent" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))

        val collapsedHeight = TopAppBarDefaults.CollapsedHeight.roundToPx()
        // largeTitle Box has top padding = collapsedHeight, so subtract it back for the pure expansion.
        val expansion = (largeTitlePlaceable.height - collapsedHeight).coerceAtLeast(0)
        val barHeight = if (expansion > 0) {
            val offset = scrolledOffset()
            val collapseFraction = if (offset.isNaN()) {
                0f
            } else {
                (abs(offset) / expansion.toFloat()).coerceIn(0f, 1f)
            }
            lerp(
                start = collapsedHeight,
                stop = collapsedHeight + expansion,
                fraction = 1f - collapseFraction,
            )
        } else {
            collapsedHeight
        }

        val verticalCenter = collapsedHeight / 2
        val smallSubtitleHeight = smallSubtitlePlaceable?.height ?: 0
        val smallTitleGroupHeight = titlePlaceable.height + smallSubtitleHeight
        val smallTitleGroupTop =
            verticalCenter - if (smallSubtitlePlaceable != null) smallTitleGroupHeight / 2 else titlePlaceable.height / 2
        val smallSubtitleBottom = smallTitleGroupTop + smallTitleGroupHeight
        val expandedBottomPadding = if (smallSubtitlePlaceable != null) {
            TopAppBarDefaults.SubtitleBottomPadding.roundToPx()
        } else {
            TopAppBarDefaults.LargeTitleBottomPadding.roundToPx()
        }
        val contentTop = maxOf(barHeight + expandedBottomPadding, smallSubtitleBottom + expandedBottomPadding)
        val layoutHeight = contentTop + bottomContentPlaceable.height
        titleClip.contentHeight = layoutHeight

        layout(constraints.maxWidth, layoutHeight) {
            // Navigation icon
            navigationIconPlaceable.placeRelative(
                x = 0,
                y = verticalCenter - navigationIconPlaceable.height / 2,
            )

            // Center title/subtitle between side controls, or start-align if it overflows.
            val titleGroupWidth = maxOf(titlePlaceable.width, smallSubtitlePlaceable?.width ?: 0)
            val leadingControlBounds = if (layoutDirection == LayoutDirection.Ltr) {
                navigationIconPlaceable.width + startInset
            } else {
                actionIconsPlaceable.width + endInset
            }
            val trailingControlBounds = if (layoutDirection == LayoutDirection.Ltr) {
                actionIconsPlaceable.width + endInset
            } else {
                navigationIconPlaceable.width + startInset
            }
            val centeredGroupX = (constraints.maxWidth - titleGroupWidth) / 2
            val canCenter = !hasCenterObstruction &&
                centeredGroupX >= leadingControlBounds &&
                centeredGroupX + titleGroupWidth <= constraints.maxWidth - trailingControlBounds
            val alignmentFraction = alignmentState.resolveFraction(canCenter)
            val centeredTitleX = centeredGroupX + (titleGroupWidth - titlePlaceable.width) / 2
            val centeredSubtitleX = centeredGroupX + (titleGroupWidth - (smallSubtitlePlaceable?.width ?: 0)) / 2
            val startTitleX = if (layoutDirection == LayoutDirection.Ltr) {
                leadingControlBounds
            } else {
                constraints.maxWidth - trailingControlBounds - titlePlaceable.width
            }
            val startSubtitleX = if (layoutDirection == LayoutDirection.Ltr) {
                leadingControlBounds
            } else {
                constraints.maxWidth - trailingControlBounds - (smallSubtitlePlaceable?.width ?: 0)
            }
            val titleX = lerp(startTitleX, centeredTitleX, alignmentFraction)
            val subtitleX = lerp(startSubtitleX, centeredSubtitleX, alignmentFraction)
            if (titleMaxWidth > 0 && obstructionState.visibilityAlpha.value > 0.001f) {
                titleClip.titleOrigin = IntOffset(titleX, smallTitleGroupTop)
                titlePlaceable.place(x = titleX, y = smallTitleGroupTop)

                smallSubtitlePlaceable?.let { subtitle ->
                    val subtitleY = smallTitleGroupTop + titlePlaceable.height
                    titleClip.subtitleOrigin = IntOffset(subtitleX, subtitleY)
                    subtitle.place(x = subtitleX, y = subtitleY)
                }
            }

            // Action icons
            actionIconsPlaceable.placeRelative(
                x = constraints.maxWidth - actionIconsPlaceable.width,
                y = verticalCenter - actionIconsPlaceable.height / 2,
            )

            // Large title (includes large subtitle in a Column)
            titleClip.largeTitleOrigin = IntOffset(
                if (layoutDirection == LayoutDirection.Ltr) 0 else constraints.maxWidth - largeTitlePlaceable.width,
                0,
            )
            largeTitlePlaceable.placeRelative(x = 0, y = 0)

            // Bottom content (pinned, below bar and subtitle)
            bottomContentPlaceable.placeRelative(x = 0, y = contentTop)
        }
    }
}

/** Viewport geometry published by layout and read only while drawing the title layers. */
private class TopAppBarTitleClip {
    var viewportSize: IntSize by mutableStateOf(IntSize.Zero)
    var contentHeight: Int by mutableIntStateOf(0)
    var titleOrigin: IntOffset by mutableStateOf(IntOffset.Zero)
    var largeTitleOrigin: IntOffset by mutableStateOf(IntOffset.Zero)
    var subtitleOrigin: IntOffset by mutableStateOf(IntOffset.Zero)
}

/** Keep the original bar clip around transformed titles without clipping control shadows. */
private fun Modifier.clipTopAppBarTitle(
    clip: TopAppBarTitleClip,
    origin: () -> IntOffset,
): Modifier = drawWithContent {
    val position = origin()
    val topInset = clip.viewportSize.height - clip.contentHeight
    clipRect(
        left = -position.x.toFloat(),
        top = -position.y.toFloat() - topInset,
        right = clip.viewportSize.width - position.x.toFloat(),
        bottom = clip.contentHeight - position.y.toFloat(),
    ) {
        this@drawWithContent.drawContent()
    }
}

/**
 * The base [Layout] for [SmallTopAppBar]. This function lays out a [SmallTopAppBar] navigation icon
 * (leading icon), a title (header), and action icons (trailing icons). Note that the navigation and
 * the actions are optional.
 *
 * @param title the [SmallTopAppBar] title (header).
 * @param color the background color of the [SmallTopAppBar].
 * @param titleColor the color of the title text.
 * @param subtitle the subtitle text displayed below the title bar area.
 * @param subtitleColor the color of the subtitle text.
 * @param navigationIcon a navigation icon [Composable].
 * @param actions actions [Composable].
 * @param titlePadding the horizontal padding of the [SmallTopAppBar]'s title.
 * @param navigationIconPadding the start padding of the navigation icon.
 * @param actionIconPadding the end padding of the action icons.
 * @param modifier the [Modifier] to be applied to this layout.
 * @param defaultWindowInsetsPadding whether to apply default window insets padding to the [SmallTopAppBar].
 * @param bottomContent the composable content displayed below the title bar area.
 */
@Composable
private fun SmallTopAppBarLayout(
    title: String,
    color: Color,
    titleColor: Color,
    subtitle: String,
    subtitleColor: Color,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable () -> Unit,
    titlePadding: Dp,
    navigationIconPadding: Dp,
    actionIconPadding: Dp,
    modifier: Modifier = Modifier,
    defaultWindowInsetsPadding: Boolean = true,
    bottomContent: @Composable () -> Unit = {},
    centerObstructionStart: Dp = Dp.Unspecified,
) {
    val animatedTitleColor by animateColorAsState(
        targetValue = titleColor,
        animationSpec = tween(durationMillis = 50),
    )
    val animatedSubtitleColor by animateColorAsState(
        targetValue = subtitleColor,
        animationSpec = tween(durationMillis = 50),
    )
    val alignmentState = rememberTitleAlignmentState()

    Layout(
        {
            Box(
                Modifier
                    .layoutId("navigationIcon")
                    .padding(start = navigationIconPadding),
            ) {
                navigationIcon()
            }
            Box(Modifier.layoutId("title")) {
                Text(
                    text = title,
                    color = animatedTitleColor,
                    maxLines = 1,
                    fontSize = MiuixTheme.textStyles.title3.fontSize,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false,
                )
            }
            Box(
                Modifier
                    .layoutId("actionIcons")
                    .padding(end = actionIconPadding),
            ) {
                actions()
            }
            if (subtitle.isNotEmpty()) {
                Box(Modifier.layoutId("subtitle")) {
                    Text(
                        text = subtitle,
                        color = animatedSubtitleColor,
                        style = MiuixTheme.textStyles.body2,
                    )
                }
            }
            Box(Modifier.layoutId("bottomContent")) {
                bottomContent()
            }
        },
        modifier = modifier
            .background(color)
            .then(
                if (defaultWindowInsetsPadding) {
                    Modifier
                        .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal))
                        .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal))
                } else {
                    Modifier
                },
            )
            .clipToBounds()
            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Top))
            .pointerInput(Unit) {
                detectTapGestures { /* Consume click */ }
            },
    ) { measurables, constraints ->
        val navigationIconPlaceable =
            measurables
                .fastFirst { it.layoutId == "navigationIcon" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))

        val actionIconsPlaceable =
            measurables
                .fastFirst { it.layoutId == "actionIcons" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))

        val titlePaddingPx = titlePadding.roundToPx()
        val controlTitleSpacingPx = minOf(titlePaddingPx, TopAppBarDefaults.TitleControlSpacing.roundToPx())
        val startInset = if (navigationIconPlaceable.width > 0) controlTitleSpacingPx else titlePaddingPx
        val endInset = if (actionIconsPlaceable.width > 0) controlTitleSpacingPx else titlePaddingPx
        val hasCenterObstruction = centerObstructionStart != Dp.Unspecified && constraints.maxWidth != Constraints.Infinity
        val normalTitleMaxWidth =
            if (constraints.maxWidth == Constraints.Infinity) {
                constraints.maxWidth
            } else {
                (constraints.maxWidth - navigationIconPlaceable.width - actionIconsPlaceable.width - startInset - endInset)
                    .coerceAtLeast(0)
            }
        val titleMaxWidth =
            if (hasCenterObstruction) {
                val obstructionLimit = centerObstructionStart.roundToPx() - controlTitleSpacingPx
                val startEdge = navigationIconPlaceable.width + startInset
                val available = (obstructionLimit - startEdge).coerceAtLeast(0)
                if (available < MinTitleWidth.roundToPx()) 0 else minOf(normalTitleMaxWidth, available)
            } else {
                normalTitleMaxWidth
            }
        val titlePlaceable =
            measurables
                .fastFirst { it.layoutId == "title" }
                .measure(constraints.copy(minWidth = 0, maxWidth = titleMaxWidth, minHeight = 0))

        val subtitlePlaceable =
            measurables
                .firstOrNull { it.layoutId == "subtitle" }
                ?.measure(constraints.copy(minWidth = 0, maxWidth = titleMaxWidth, minHeight = 0))
        val bottomContentPlaceable =
            measurables
                .fastFirst { it.layoutId == "bottomContent" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))

        val subtitleHeight = subtitlePlaceable?.height ?: 0
        val collapsedHeight = TopAppBarDefaults.CollapsedHeight.roundToPx()
        val verticalCenter = TopAppBarDefaults.SmallTopAppBarCenterHeight.roundToPx() / 2
        val titleGroupHeight = titlePlaceable.height + subtitleHeight
        val titleGroupTop = verticalCenter - if (subtitlePlaceable != null) titleGroupHeight / 2 else titlePlaceable.height / 2
        val subtitleY = titleGroupTop + titlePlaceable.height
        val subtitleBottomPadding = if (subtitlePlaceable != null) TopAppBarDefaults.SubtitleBottomPadding.roundToPx() else 0
        val contentTop = maxOf(collapsedHeight, subtitleY + subtitleHeight + subtitleBottomPadding)
        val layoutHeight = contentTop + bottomContentPlaceable.height

        layout(constraints.maxWidth, layoutHeight) {
            // Navigation icon
            navigationIconPlaceable.placeRelative(
                x = 0,
                y = verticalCenter - navigationIconPlaceable.height / 2,
            )

            // Center title/subtitle between side controls, or start-align if it overflows.
            val titleGroupWidth = maxOf(titlePlaceable.width, subtitlePlaceable?.width ?: 0)
            val leadingControlBounds = if (layoutDirection == LayoutDirection.Ltr) {
                navigationIconPlaceable.width + startInset
            } else {
                actionIconsPlaceable.width + endInset
            }
            val trailingControlBounds = if (layoutDirection == LayoutDirection.Ltr) {
                actionIconsPlaceable.width + endInset
            } else {
                navigationIconPlaceable.width + startInset
            }
            val centeredGroupX = (constraints.maxWidth - titleGroupWidth) / 2
            val canCenter = !hasCenterObstruction &&
                centeredGroupX >= leadingControlBounds &&
                centeredGroupX + titleGroupWidth <= constraints.maxWidth - trailingControlBounds
            val alignmentFraction = alignmentState.resolveFraction(canCenter)
            val centeredTitleX = centeredGroupX + (titleGroupWidth - titlePlaceable.width) / 2
            val centeredSubtitleX = centeredGroupX + (titleGroupWidth - (subtitlePlaceable?.width ?: 0)) / 2
            val startTitleX = if (layoutDirection == LayoutDirection.Ltr) {
                leadingControlBounds
            } else {
                constraints.maxWidth - trailingControlBounds - titlePlaceable.width
            }
            val startSubtitleX = if (layoutDirection == LayoutDirection.Ltr) {
                leadingControlBounds
            } else {
                constraints.maxWidth - trailingControlBounds - (subtitlePlaceable?.width ?: 0)
            }
            val titleX = lerp(startTitleX, centeredTitleX, alignmentFraction)
            val subtitleX = lerp(startSubtitleX, centeredSubtitleX, alignmentFraction)
            if (!hasCenterObstruction || titleMaxWidth > 0) {
                titlePlaceable.place(
                    x = titleX,
                    y = titleGroupTop,
                )

                subtitlePlaceable?.place(
                    x = subtitleX,
                    y = subtitleY,
                )
            }

            // Action icons
            actionIconsPlaceable.placeRelative(
                x = constraints.maxWidth - actionIconsPlaceable.width,
                y = verticalCenter - actionIconsPlaceable.height / 2,
            )

            // Bottom content (below subtitle)
            bottomContentPlaceable.placeRelative(x = 0, y = contentTop)
        }
    }
}

private val MinTitleWidth = 48.dp

private class TitleAlignmentState {
    var initialized = false
    var isCentered by mutableStateOf(false)
    var hasAnimatedOnce = false
    val progress = Animatable(0f, visibilityThreshold = 0.0005f)

    fun resolveFraction(canCenter: Boolean): Float = if (!initialized) {
        initialized = true
        isCentered = canCenter
        if (canCenter) 1f else 0f
    } else {
        if (isCentered != canCenter) {
            isCentered = canCenter
        }
        if (hasAnimatedOnce) {
            progress.value
        } else if (canCenter) {
            1f
        } else {
            0f
        }
    }
}

@Composable
private fun rememberTitleAlignmentState(): TitleAlignmentState {
    val state = remember { TitleAlignmentState() }
    LaunchedEffect(state.isCentered) {
        val target = if (state.isCentered) 1f else 0f
        if (!state.hasAnimatedOnce) {
            state.hasAnimatedOnce = true
            state.progress.snapTo(target)
        } else {
            state.progress.animateTo(
                target,
                folmeSpring(damping = 1.0f, response = 0.35f, visibilityThreshold = 0.0005f),
            )
        }
    }
    return state
}

private class TitleObstructionTransitionState {
    var initialized = false
    var hasAnimatedOnce = false
    var activeObstructed = false
    var targetObstructed by mutableStateOf(false)
    var targetObstructedWidth by mutableIntStateOf(Int.MAX_VALUE)
    var activeLimit by mutableIntStateOf(Int.MAX_VALUE)
    var lastVisibleLimit = Int.MAX_VALUE
    var currentNormalMaxWidth = 0
    var currentContentWidth = 0
    var hasOverflow = false
    val blurFraction = Animatable(0f)
    val visibilityAlpha = Animatable(1f)

    fun resolveMeasureMaxWidth(
        hasCenterObstruction: Boolean,
        normalMaxWidth: Int,
        obstructedWidth: Int,
    ): Int {
        currentNormalMaxWidth = normalMaxWidth
        if (!initialized) {
            initialized = true
            activeObstructed = hasCenterObstruction
            targetObstructed = hasCenterObstruction
            targetObstructedWidth = obstructedWidth
            activeLimit = obstructedWidth
            if (obstructedWidth > 0) lastVisibleLimit = obstructedWidth
            return if (obstructedWidth == 0) 0 else minOf(normalMaxWidth, obstructedWidth)
        }
        if (!hasCenterObstruction && !activeObstructed && targetObstructedWidth == activeLimit) {
            return normalMaxWidth
        }
        if (targetObstructed != hasCenterObstruction || targetObstructedWidth != obstructedWidth) {
            targetObstructed = hasCenterObstruction
            targetObstructedWidth = obstructedWidth
        }
        val effectiveLimit = if (activeLimit > 0) activeLimit else lastVisibleLimit
        return minOf(normalMaxWidth, effectiveLimit)
    }
}

@Composable
private fun rememberTitleObstructionTransitionState(): TitleObstructionTransitionState {
    val state = remember { TitleObstructionTransitionState() }
    LaunchedEffect(state.targetObstructed, state.targetObstructedWidth) {
        if (!state.hasAnimatedOnce) {
            state.hasAnimatedOnce = true
            state.visibilityAlpha.snapTo(if (state.targetObstructedWidth > 0) 1f else 0f)
            state.blurFraction.snapTo(0f)
            return@LaunchedEffect
        }
        val nextLimit = state.targetObstructedWidth
        if (nextLimit == 0) {
            val hideSpec = folmeSpring<Float>(damping = 1.0f, response = 0.15f)
            coroutineScope {
                launch { state.blurFraction.animateTo(1f, hideSpec) }
                launch { state.visibilityAlpha.animateTo(0f, hideSpec) }
            }
            state.activeObstructed = state.targetObstructed
            state.activeLimit = 0
        } else if (state.activeLimit == 0) {
            state.activeObstructed = state.targetObstructed
            state.activeLimit = nextLimit
            state.lastVisibleLimit = nextLimit
            val showSpec = folmeSpring<Float>(damping = 1.0f, response = 0.3f)
            coroutineScope {
                launch { state.visibilityAlpha.animateTo(1f, showSpec) }
                launch { state.blurFraction.animateTo(0f, showSpec) }
            }
        } else {
            val nextMaxWidth = minOf(state.currentNormalMaxWidth, nextLimit)
            if (state.hasOverflow || state.currentContentWidth > nextMaxWidth) {
                state.blurFraction.snapTo(1f)
                state.visibilityAlpha.snapTo(0.25f)
                state.activeObstructed = state.targetObstructed
                state.activeLimit = nextLimit
                state.lastVisibleLimit = nextLimit
                val restoreSpec = folmeSpring<Float>(damping = 1.0f, response = 0.35f)
                coroutineScope {
                    launch { state.blurFraction.animateTo(0f, restoreSpec) }
                    launch { state.visibilityAlpha.animateTo(1f, restoreSpec) }
                }
            } else {
                state.activeObstructed = state.targetObstructed
                state.activeLimit = nextLimit
                state.lastVisibleLimit = nextLimit
                state.blurFraction.snapTo(0f)
                state.visibilityAlpha.snapTo(1f)
            }
        }
    }
    return state
}

internal class TopAppBarInputTracker {
    var allowIdleSettle: Boolean = false
}

internal val LocalTopAppBarInputTracker = staticCompositionLocalOf<TopAppBarInputTracker?> { null }
