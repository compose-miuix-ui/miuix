package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateTo
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFirst
import top.yukonga.miuix.kmp.basic.TopAppBarState.Companion.Saver
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.abs
import kotlin.math.roundToInt

/**
 * A [TopAppBar] that can collapse and expand based on the scroll position of the content below it.
 *
 * The [TopAppBar] can be configured with a title, a navigation icon, and action icons. The large
 * title will collapse when the content is scrolled up and expand when the content is scrolled down.
 *
 * @param title The title of the [TopAppBar].
 * @param modifier The modifier to be applied to the  [TopAppBar].
 * @param color The background color of the [TopAppBar].
 * @param largeTitle The large title of the [TopAppBar], If not specified, it will be the same as title.
 * @param navigationIcon The [Composable] content that represents the navigation icon.
 * @param actions The [Composable] content that represents the action icons.
 * @param scrollBehavior The [ScrollBehavior] that controls the behavior of the [TopAppBar].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding to the [TopAppBar].
 * @param horizontalPadding The horizontal padding of the [TopAppBar].
 */
@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = MiuixTheme.colorScheme.background,
    largeTitle: String? = null,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: ScrollBehavior? = null,
    defaultWindowInsetsPadding: Boolean = true,
    horizontalPadding: Dp = 26.dp
) {
    val density = LocalDensity.current
    val expandedHeightPx by rememberUpdatedState(
        with(density) { (TopAppBarExpandedHeight).toPx().coerceAtLeast(0f) }
    )
    SideEffect {
        // Sets the app bar's height offset to collapse the entire bar's height when content is
        // scrolled.
        if (scrollBehavior?.state?.heightOffsetLimit != -expandedHeightPx) {
            scrollBehavior?.state?.heightOffsetLimit = -expandedHeightPx
        }
    }

    // Wrap the given actions in a Row.
    val actionsRow =
        @Composable {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = actions
            )
        }
    // Compose a Surface with a TopAppBarLayout content.
    // The surface's background color is animated as specified above.
    // The height of the app bar is determined by subtracting the bar's height offset from the
    // app bar's defined constant height value (i.e. the ContainerHeight token).
    Surface(
        color = color,
        modifier =
            modifier
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, _ -> }
                }
                .then(
                    if (defaultWindowInsetsPadding) {
                        Modifier
                            .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal))
                            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal))
                    } else Modifier
                )
    ) {
        TopAppBarLayout(
            title = title,
            largeTitle = largeTitle ?: title,
            navigationIcon = navigationIcon,
            actions = actionsRow,
            scrolledOffset = { scrollBehavior?.state?.heightOffset ?: 0f },
            expandedHeightPx = expandedHeightPx,
            horizontalPadding = horizontalPadding,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding
        )
    }
}

@Composable
fun SmallTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = MiuixTheme.colorScheme.background,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: ScrollBehavior? = null,
    defaultWindowInsetsPadding: Boolean = true,
    horizontalPadding: Dp = 26.dp
) {
    SideEffect {
        // Sets the height offset limit of the SmallTopAppBar to 0f
        // To ensure that the content can still scroll normally even when scrollBehavior is passed.
        scrollBehavior?.state?.heightOffsetLimit = 0f
    }

    // Wrap the given actions in a Row.
    val actionsRow =
        @Composable {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = actions
            )
        }
    // Compose a Surface with a SmallTopAppBarLayout content.
    // The surface's background color is animated as specified above.
    // The height of the app bar is determined by subtracting the bar's height offset from the
    // app bar's defined constant height value (i.e. the ContainerHeight token).
    Surface(
        color = color,
        modifier =
            modifier
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, _ -> }
                }
                .then(
                    if (defaultWindowInsetsPadding) {
                        Modifier
                            .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal))
                            .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal))
                    } else Modifier
                )
    ) {
        SmallTopAppBarLayout(
            title = title,
            navigationIcon = navigationIcon,
            actions = actionsRow,
            horizontalPadding = horizontalPadding,
            defaultWindowInsetsPadding = defaultWindowInsetsPadding
        )
    }
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
@Composable
fun MiuixScrollBehavior(
    state: TopAppBarState = rememberTopAppBarState(),
    canScroll: () -> Boolean = { true },
    snapAnimationSpec: AnimationSpec<Float>? = spring(stiffness = 3000f),
    flingAnimationSpec: DecayAnimationSpec<Float>? = rememberSplineBasedDecay()
): ScrollBehavior =
    remember(state, canScroll, snapAnimationSpec, flingAnimationSpec) {
        ExitUntilCollapsedScrollBehavior(
            state = state,
            snapAnimationSpec = snapAnimationSpec,
            flingAnimationSpec = flingAnimationSpec,
            canScroll = canScroll
        )
    }

/** The default expanded height of a [TopAppBar]. */
val TopAppBarExpandedHeight: Dp = 48.dp

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
    initialContentOffset: Float = 0f
): TopAppBarState {
    return rememberSaveable(saver = Saver) {
        TopAppBarState(initialHeightOffsetLimit, initialHeightOffset, initialContentOffset)
    }
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
    initialContentOffset: Float
) {
    /**
     * The top app bar's height offset limit in pixels, which represents the limit that a top app
     * bar is allowed to collapse to.
     *
     * Use this limit to coerce the [heightOffset] value when it's updated.
     */
    var heightOffsetLimit by mutableFloatStateOf(initialHeightOffsetLimit)

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
                        ((heightOffsetLimit - contentOffset).coerceIn(
                            minimumValue = heightOffsetLimit,
                            maximumValue = 0f,
                        ) / heightOffsetLimit)
            } else {
                0f
            }

    companion object {
        /** The default [Saver] implementation for [TopAppBarState]. */
        val Saver: Saver<TopAppBarState, *> =
            listSaver(
                save = { listOf(it.heightOffsetLimit, it.heightOffset, it.contentOffset) },
                restore = {
                    TopAppBarState(
                        initialHeightOffsetLimit = it[0],
                        initialHeightOffset = it[1],
                        initialContentOffset = it[2],
                    )
                },
            )
    }

    private var _heightOffset = mutableFloatStateOf(initialHeightOffset)
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
) : ScrollBehavior {
    override val isPinned: Boolean = false
    override var nestedScrollConnection =
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // Don't intercept if scrolling down.
                if (!canScroll() || available.y > 0f) return Offset.Zero

                val prevHeightOffset = state.heightOffset
                state.heightOffset = state.heightOffset + available.y
                return if (prevHeightOffset != state.heightOffset) {
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
                    state.heightOffset = state.heightOffset + consumed.y
                    return Offset(0f, state.heightOffset - oldHeightOffset)
                }

                if (available.y > 0f) {
                    // Adjust the height offset in case the consumed delta Y is less than what was
                    // recorded as available delta Y in the pre-scroll.
                    val oldHeightOffset = state.heightOffset
                    state.heightOffset = state.heightOffset + available.y
                    return Offset(0f, state.heightOffset - oldHeightOffset)
                }
                return Offset.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
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
 * Settles the app bar by flinging, in case the given velocity is greater than zero, and snapping
 * after the fling settles.
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
            flingAnimationSpec
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
            return Velocity(0f, velocity)
        }
    }
    return Velocity(0f, velocity - remainingVelocity)
}

/** A functional interface for providing an app-bar scroll offset. */
private fun interface ScrolledOffset {
    fun offset(): Float
}

/**
 * The base [Layout] for [TopAppBar]. This function lays out a top app bar navigation icon
 * (leading icon), a title (header), and action icons (trailing icons). Note that the navigation and
 * the actions are optional.
 *
 * @param title the top app bar title (header).
 * @param navigationIcon a navigation icon [Composable].
 * @param actions actions [Composable].
 * @param scrolledOffset a function that provides the scroll offset of the top app bar.
 * @param expandedHeightPx the expanded height of the top app bar in pixels.
 * @param horizontalPadding the horizontal padding of the [TopAppBar].
 */
@Composable
private fun TopAppBarLayout(
    title: String,
    largeTitle: String,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable () -> Unit,
    scrolledOffset: ScrolledOffset,
    expandedHeightPx: Float,
    horizontalPadding: Dp,
    defaultWindowInsetsPadding: Boolean
) {
    val extOffset = abs(scrolledOffset.offset()) / expandedHeightPx * 2
    val alpha by animateFloatAsState(
        targetValue = if (1 - extOffset.coerceIn(0f, 1f) == 0f) 1f else 0f,
        animationSpec = tween(durationMillis = 250)
    )
    val translationY by animateFloatAsState(
        targetValue = if (extOffset > 1f) 0f else 10f,
        animationSpec = tween(durationMillis = 250)
    )
    // Subtract the scrolledOffset from the maxHeight. The scrolledOffset is expected to be
    // equal or smaller than zero.
    val scrolledOffsetValue = scrolledOffset.offset()
    val heightOffset = if (scrolledOffsetValue.isNaN()) 0 else scrolledOffsetValue.roundToInt()

    Layout(
        {
            Box(
                Modifier
                    .layoutId("navigationIcon")
            ) {
                navigationIcon()
            }
            Box(
                Modifier
                    .layoutId("title")
                    .padding(horizontal = horizontalPadding)
                    .graphicsLayer(
                        translationY = translationY,
                        alpha = alpha
                    )
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontSize = MiuixTheme.textStyles.title3.fontSize,
                    fontWeight = FontWeight.Medium
                )
            }
            Box(
                Modifier
                    .layoutId("actionIcons")
            ) {
                actions()
            }
            Box(
                Modifier
                    .layoutId("largeTitle")
                    .fillMaxWidth()
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 56.dp)
                        .padding(horizontal = horizontalPadding)
                        .graphicsLayer(alpha = 1f - (abs(scrolledOffset.offset()) / expandedHeightPx * 2).coerceIn(0f, 1f))
                        .clipToBounds()
                ) {
                    Text(
                        modifier = Modifier.offset { IntOffset(0, heightOffset) },
                        text = largeTitle,
                        maxLines = 1,
                        fontSize = MiuixTheme.textStyles.title1.fontSize,
                        fontWeight = FontWeight.Normal
                    )
                }


            }
        },
        modifier = Modifier
            .then(
                if (defaultWindowInsetsPadding) {
                    Modifier
                        .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Top))
                        .windowInsetsPadding(WindowInsets.captionBar.only(WindowInsetsSides.Top))
                } else Modifier
            )
            .heightIn(max = 56.dp + TopAppBarExpandedHeight)
            .clipToBounds()
    ) { measurables, constraints ->
        val navigationIconPlaceable =
            measurables
                .fastFirst { it.layoutId == "navigationIcon" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))

        val actionIconsPlaceable =
            measurables
                .fastFirst { it.layoutId == "actionIcons" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))

        val maxTitleWidth =
            if (constraints.maxWidth == Constraints.Infinity) {
                constraints.maxWidth
            } else {
                (constraints.maxWidth - navigationIconPlaceable.width - actionIconsPlaceable.width)
                    .coerceAtLeast(0)
            }

        val titlePlaceable =
            measurables
                .fastFirst { it.layoutId == "title" }
                .measure(constraints.copy(minWidth = 0, maxWidth = maxTitleWidth, minHeight = 0))


        val layoutHeight =
            (if (constraints.maxHeight == Constraints.Infinity) {
                constraints.maxHeight
            } else {
                constraints.maxHeight + heightOffset
            }).coerceAtLeast(0)

        val largeTitlePlaceable =
            measurables
                .fastFirst { it.layoutId == "largeTitle" }
                .measure(
                    constraints.copy(minWidth = 0, minHeight = 0)
                )

        layout(constraints.maxWidth, layoutHeight) {
            val verticalCenter = 60.dp.roundToPx() / 2

            // Navigation icon
            navigationIconPlaceable.placeRelative(
                x = 0,
                y = verticalCenter - navigationIconPlaceable.height / 2
            )

            // Title
            var baseX = (constraints.maxWidth - titlePlaceable.width) / 2
            if (baseX < navigationIconPlaceable.width) {
                baseX += (navigationIconPlaceable.width - baseX)
            } else if (baseX + titlePlaceable.width > constraints.maxWidth - actionIconsPlaceable.width) {
                baseX += ((constraints.maxWidth - actionIconsPlaceable.width) - (baseX + titlePlaceable.width))
            }
            titlePlaceable.placeRelative(
                x = baseX,
                y = verticalCenter - titlePlaceable.height / 2
            )

            // Action icons
            actionIconsPlaceable.placeRelative(
                x = constraints.maxWidth - actionIconsPlaceable.width,
                y = verticalCenter - actionIconsPlaceable.height / 2
            )

            // Large title
            largeTitlePlaceable.placeRelative(
                x = 0,
                y = 0
            )
        }
    }
}


/**
 * The base [Layout] for [SmallTopAppBar]. This function lays out a top app bar navigation icon
 * (leading icon), a title (header), and action icons (trailing icons). Note that the navigation and
 * the actions are optional.
 *
 * @param title the top app bar title (header).
 * @param navigationIcon a navigation icon [Composable].
 * @param actions actions [Composable].
 * @param horizontalPadding the horizontal padding of the [SmallTopAppBar].
 */
@Composable
private fun SmallTopAppBarLayout(
    title: String,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable () -> Unit,
    horizontalPadding: Dp,
    defaultWindowInsetsPadding: Boolean
) {
    Layout(
        {
            Box(
                Modifier
                    .layoutId("navigationIcon")
            ) {
                navigationIcon()
            }
            Box(
                Modifier
                    .layoutId("title")
                    .padding(horizontal = horizontalPadding)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontSize = MiuixTheme.textStyles.title3.fontSize,
                    fontWeight = FontWeight.Medium
                )
            }
            Box(
                Modifier
                    .layoutId("actionIcons")
            ) {
                actions()
            }
        },
        modifier =
            Modifier
                .then(
                    if (defaultWindowInsetsPadding) {
                        Modifier
                            .windowInsetsPadding(WindowInsets.statusBars.only(WindowInsetsSides.Top))
                            .windowInsetsPadding(WindowInsets.captionBar.only(WindowInsetsSides.Top))
                    } else Modifier
                )
                .heightIn(max = 56.dp)
    ) { measurables, constraints ->
        val navigationIconPlaceable =
            measurables
                .fastFirst { it.layoutId == "navigationIcon" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))

        val actionIconsPlaceable =
            measurables
                .fastFirst { it.layoutId == "actionIcons" }
                .measure(constraints.copy(minWidth = 0, minHeight = 0))

        val maxTitleWidth =
            if (constraints.maxWidth == Constraints.Infinity) {
                constraints.maxWidth
            } else {
                (constraints.maxWidth - navigationIconPlaceable.width - actionIconsPlaceable.width)
                    .coerceAtLeast(0)
            }

        val titlePlaceable =
            measurables
                .fastFirst { it.layoutId == "title" }
                .measure(constraints.copy(minWidth = 0, maxWidth = maxTitleWidth, minHeight = 0))


        val layoutHeight =
            if (constraints.maxHeight == Constraints.Infinity) {
                constraints.maxHeight
            } else {
                constraints.maxHeight
            }

        layout(constraints.maxWidth, layoutHeight) {
            val verticalCenter = 60.dp.roundToPx() / 2

            // Navigation icon
            navigationIconPlaceable.placeRelative(
                x = 0,
                y = verticalCenter - navigationIconPlaceable.height / 2
            )

            // Title
            var baseX = (constraints.maxWidth - titlePlaceable.width) / 2
            if (baseX < navigationIconPlaceable.width) {
                baseX += (navigationIconPlaceable.width - baseX)
            } else if (baseX + titlePlaceable.width > constraints.maxWidth - actionIconsPlaceable.width) {
                baseX += ((constraints.maxWidth - actionIconsPlaceable.width) - (baseX + titlePlaceable.width))
            }
            titlePlaceable.placeRelative(
                x = baseX,
                y = verticalCenter - titlePlaceable.height / 2
            )

            // Action icons
            actionIconsPlaceable.placeRelative(
                x = constraints.maxWidth - actionIconsPlaceable.width,
                y = verticalCenter - actionIconsPlaceable.height / 2
            )
        }
    }
}