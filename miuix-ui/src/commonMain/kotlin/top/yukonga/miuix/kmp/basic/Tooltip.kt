// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.BasicTooltipDefaults
import androidx.compose.foundation.BasicTooltipState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.MutatorMutex
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupPositionProvider
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import top.yukonga.miuix.kmp.squircle.squircleBackground
import top.yukonga.miuix.kmp.theme.LocalContentColor
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.jvm.JvmInline
import kotlin.math.max
import kotlin.time.Duration.Companion.milliseconds

/**
 * The preferred position of a tooltip relative to its anchor. The tooltip flips to the opposite
 * side when there is not enough room on the preferred side. [Start] / [End] resolve to [Left] /
 * [Right] following the layout direction.
 */
@JvmInline
value class TooltipAnchorPosition private constructor(private val value: Int) {
    override fun toString(): String = when (this) {
        Above -> "Above"
        Below -> "Below"
        Left -> "Left"
        Right -> "Right"
        Start -> "Start"
        End -> "End"
        else -> "Invalid"
    }

    companion object {
        val Above = TooltipAnchorPosition(0)
        val Below = TooltipAnchorPosition(1)
        val Left = TooltipAnchorPosition(2)
        val Right = TooltipAnchorPosition(3)
        val Start = TooltipAnchorPosition(4)
        val End = TooltipAnchorPosition(5)
    }
}

/**
 * Receiver scope for the [TooltipBox] tooltip content ([PlainTooltip] / [RichTooltip]). It exposes
 * the resolved anchor position and the anchor bounds so a caret can be drawn pointing at the anchor.
 */
@Stable
sealed interface TooltipScope {
    /** The preferred [TooltipAnchorPosition] of the tooltip relative to its anchor. */
    val positioning: TooltipAnchorPosition

    /** The anchor bounds in window coordinates, or null before the anchor is placed. */
    fun obtainAnchorBounds(): LayoutCoordinates?
}

private class TooltipScopeImpl(
    override val positioning: TooltipAnchorPosition,
    private val anchorBounds: () -> LayoutCoordinates?,
) : TooltipScope {
    override fun obtainAnchorBounds(): LayoutCoordinates? = anchorBounds()
}

/**
 * The state that controls the visibility of a [TooltipBox].
 *
 * Created via [rememberTooltipState]. When [isPersistent] is false the tooltip is dismissed
 * automatically after a short timeout; when true it stays visible until [dismiss] is called or the
 * user taps outside. A global [MutatorMutex] keeps only one tooltip visible at a time.
 */
@Stable
interface TooltipState {
    /** The transition driving the tooltip's enter / exit animation. */
    val transition: MutableTransitionState<Boolean>

    /** Whether the tooltip is currently visible (including while animating out). */
    val isVisible: Boolean

    /** Whether the tooltip stays visible until explicitly dismissed. */
    val isPersistent: Boolean

    /** Shows the tooltip, suspending until it is dismissed or superseded by another tooltip. */
    suspend fun show(mutatePriority: MutatePriority = MutatePriority.Default)

    /** Dismisses the tooltip. */
    fun dismiss()

    /** Cleans up the coroutine backing [show] when the tooltip leaves the composition. */
    fun onDispose()
}

@OptIn(ExperimentalFoundationApi::class)
@Stable
private class TooltipStateImpl(
    initialIsVisible: Boolean,
    override val isPersistent: Boolean,
    private val mutatorMutex: MutatorMutex,
) : TooltipState {
    override val transition: MutableTransitionState<Boolean> =
        MutableTransitionState(initialIsVisible)

    override val isVisible: Boolean
        get() = transition.currentState || transition.targetState

    /** continuation used to clean up */
    private var job: (CancellableContinuation<Unit>)? = null

    /**
     * Show the tooltip associated with the current [TooltipState]. When this method is called, all
     * of the other tooltips associated with [mutatorMutex] will be dismissed.
     *
     * @param mutatePriority [MutatePriority] to be used with [mutatorMutex].
     */
    override suspend fun show(mutatePriority: MutatePriority) {
        val cancellableShow: suspend () -> Unit = {
            suspendCancellableCoroutine { continuation ->
                transition.targetState = true
                job = continuation
            }
        }

        // Show associated tooltip for [TooltipDuration] amount of time
        // or until tooltip is explicitly dismissed depending on [isPersistent].
        mutatorMutex.mutate(mutatePriority) {
            try {
                if (isPersistent || mutatePriority == MutatePriority.UserInput) {
                    cancellableShow()
                } else {
                    withTimeout(BasicTooltipDefaults.TooltipDuration.milliseconds) { cancellableShow() }
                }
            } finally {
                if (mutatePriority != MutatePriority.PreventUserInput) {
                    // timeout or cancellation has occurred and we close out the current tooltip.
                    dismiss()
                }
            }
        }
    }

    /** Dismiss the tooltip associated with this [TooltipState] if it's currently being shown. */
    override fun dismiss() {
        transition.targetState = false
        if (isPersistent) {
            job?.cancel()
        }
    }

    /** Cleans up [mutatorMutex] when the tooltip associated with this state leaves Composition. */
    override fun onDispose() {
        job?.cancel()
    }
}

/**
 * Creates and remembers a [TooltipState].
 *
 * @param initialIsVisible whether the tooltip is initially visible.
 * @param isPersistent whether the tooltip stays visible until explicitly dismissed.
 * @param mutatorMutex the [MutatorMutex] used to ensure only one tooltip is shown at a time.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun rememberTooltipState(
    initialIsVisible: Boolean = false,
    isPersistent: Boolean = false,
    mutatorMutex: MutatorMutex = BasicTooltipDefaults.GlobalMutatorMutex,
): TooltipState = remember(isPersistent, mutatorMutex) {
    TooltipStateImpl(initialIsVisible, isPersistent, mutatorMutex)
}

/**
 * A tooltip box that anchors a [tooltip] (a [PlainTooltip] or [RichTooltip]) to its [content].
 *
 * The tooltip is shown on hover (desktop / web) or long press (touch), or programmatically via
 * [TooltipState.show]. It is built on the foundation tooltip primitives, so triggering, the global
 * single-tooltip behavior, and the auto-dismiss timeout match the platform behavior.
 *
 * @param positionProvider the [PopupPositionProvider] positioning the tooltip, usually from
 *   [TooltipDefaults.rememberTooltipPositionProvider].
 * @param tooltip the tooltip content.
 * @param state the [TooltipState] controlling visibility.
 * @param modifier the modifier applied to the anchor wrapper.
 * @param focusable whether the tooltip is focusable; true for interactive rich tooltips so an
 *   outside tap dismisses them and their actions are reachable.
 * @param enableUserInput whether hover / long press on the anchor shows the tooltip.
 * @param content the anchor content.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TooltipBox(
    positionProvider: PopupPositionProvider,
    tooltip: @Composable TooltipScope.() -> Unit,
    state: TooltipState,
    modifier: Modifier = Modifier,
    focusable: Boolean = false,
    enableUserInput: Boolean = true,
    content: @Composable () -> Unit,
) {
    var anchorBounds by remember { mutableStateOf<LayoutCoordinates?>(null) }
    val positioning = (positionProvider as? TooltipPopupPositionProvider)?.positioning ?: TooltipAnchorPosition.Below
    val scope = remember(positioning) { TooltipScopeImpl(positioning) { anchorBounds } }
    val basicState = remember(state) {
        object : BasicTooltipState {
            override val isVisible: Boolean get() = state.isVisible
            override val isPersistent: Boolean get() = state.isPersistent
            override suspend fun show(mutatePriority: MutatePriority) = state.show(mutatePriority)
            override fun dismiss() = state.dismiss()
            override fun onDispose() = state.onDispose()
        }
    }

    BasicTooltipBox(
        positionProvider = positionProvider,
        tooltip = {
            AnimatedVisibility(
                visibleState = state.transition,
                enter = fadeIn(ListPopupDefaults.AlphaEnterAnimationSpec) +
                    scaleIn(initialScale = 0.9f, animationSpec = ListPopupDefaults.FractionAnimationSpec),
                exit = fadeOut(ListPopupDefaults.AlphaExitAnimationSpec) +
                    scaleOut(targetScale = 0.9f, animationSpec = ListPopupDefaults.AlphaExitAnimationSpec),
            ) {
                scope.tooltip()
            }
        },
        state = basicState,
        modifier = modifier.onGloballyPositioned { anchorBounds = it },
        focusable = focusable,
        enableUserInput = enableUserInput,
        content = content,
    )
}

/**
 * A plain tooltip that briefly describes an anchor with a short text label, using the Miuix inverse
 * surface style.
 *
 * @param modifier the modifier applied to the tooltip.
 * @param caretShape when non-null, draws a caret pointing at the anchor; null (default) keeps the
 *   Miuix caret-less style.
 * @param maxWidth the maximum width of the tooltip.
 * @param cornerRadius the corner radius of the tooltip.
 * @param containerColor the container color of the tooltip.
 * @param contentColor the content color of the tooltip.
 * @param insideMargin the margin inside the tooltip.
 * @param content the tooltip content, usually a short [Text].
 */
@Composable
fun TooltipScope.PlainTooltip(
    modifier: Modifier = Modifier,
    caretShape: Shape? = null,
    maxWidth: Dp = TooltipDefaults.PlainTooltipMaxWidth,
    cornerRadius: Dp = TooltipDefaults.PlainTooltipCornerRadius,
    containerColor: Color = TooltipDefaults.plainTooltipContainerColor,
    contentColor: Color = TooltipDefaults.plainTooltipContentColor,
    insideMargin: PaddingValues = TooltipDefaults.PlainTooltipInsideMargin,
    content: @Composable () -> Unit,
) {
    TooltipSurface(
        caretShape = caretShape,
        maxWidth = maxWidth,
        cornerRadius = cornerRadius,
        containerColor = containerColor,
        insideMargin = insideMargin,
        modifier = modifier.semantics(mergeDescendants = false) {
            isTraversalGroup = true
            liveRegion = LiveRegionMode.Polite
        },
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor) {
            content()
        }
    }
}

/**
 * A rich tooltip that explains an anchor with an optional [title], body [text], and an optional
 * [action], using the Miuix surface-container card style.
 *
 * @param modifier the modifier applied to the tooltip.
 * @param title the optional title shown above the body.
 * @param action the optional action shown below the body.
 * @param caretShape when non-null, draws a caret pointing at the anchor; null (default) keeps the
 *   Miuix caret-less style.
 * @param maxWidth the maximum width of the tooltip.
 * @param cornerRadius the corner radius of the tooltip.
 * @param colors the [RichTooltipColors] of the tooltip.
 * @param insideMargin the margin inside the tooltip.
 * @param text the body content of the tooltip.
 */
@Composable
fun TooltipScope.RichTooltip(
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
    caretShape: Shape? = null,
    maxWidth: Dp = TooltipDefaults.RichTooltipMaxWidth,
    cornerRadius: Dp = TooltipDefaults.RichTooltipCornerRadius,
    colors: RichTooltipColors = TooltipDefaults.richTooltipColors(),
    insideMargin: PaddingValues = TooltipDefaults.RichTooltipInsideMargin,
    text: @Composable () -> Unit,
) {
    TooltipSurface(
        caretShape = caretShape,
        maxWidth = maxWidth,
        cornerRadius = cornerRadius,
        containerColor = colors.containerColor,
        insideMargin = insideMargin,
        modifier = modifier.semantics(mergeDescendants = false) {
            isTraversalGroup = true
        },
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            if (title != null) {
                CompositionLocalProvider(LocalContentColor provides colors.titleContentColor) {
                    title()
                }
            }
            CompositionLocalProvider(LocalContentColor provides colors.contentColor) {
                text()
            }
            if (action != null) {
                Box(modifier = Modifier.align(Alignment.End)) {
                    CompositionLocalProvider(LocalContentColor provides colors.actionContentColor) {
                        action()
                    }
                }
            }
        }
    }
}

@Composable
private fun TooltipScope.TooltipSurface(
    caretShape: Shape?,
    maxWidth: Dp,
    cornerRadius: Dp,
    containerColor: Color,
    insideMargin: PaddingValues,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val drawCaret = caretShape != null &&
        (positioning == TooltipAnchorPosition.Above || positioning == TooltipAnchorPosition.Below)
    val caretOnTop = positioning == TooltipAnchorPosition.Below
    val caretSize = TooltipDefaults.caretSize
    var selfBounds by remember { mutableStateOf<LayoutCoordinates?>(null) }

    Box(
        modifier = modifier
            .widthIn(max = maxWidth)
            .onGloballyPositioned { selfBounds = it }
            .then(
                if (drawCaret) {
                    Modifier.drawBehind {
                        val anchor = obtainAnchorBounds() ?: return@drawBehind
                        val self = selfBounds ?: return@drawBehind
                        if (!anchor.isAttached || !self.isAttached) return@drawBehind
                        val anchorCenterX = anchor.positionInWindow().x + anchor.size.width / 2f
                        val selfX = self.positionInWindow().x
                        val caretHalf = caretSize.width.toPx() / 2f
                        val cr = cornerRadius.toPx()
                        val centerX = (anchorCenterX - selfX)
                            .coerceIn(cr + caretHalf, size.width - cr - caretHalf)
                        val caretHeight = caretSize.height.toPx()
                        val path = Path()
                        if (caretOnTop) {
                            path.moveTo(centerX, 0f)
                            path.lineTo(centerX - caretHalf, caretHeight)
                            path.lineTo(centerX + caretHalf, caretHeight)
                        } else {
                            path.moveTo(centerX, size.height)
                            path.lineTo(centerX - caretHalf, size.height - caretHeight)
                            path.lineTo(centerX + caretHalf, size.height - caretHeight)
                        }
                        path.close()
                        drawPath(path, color = containerColor)
                    }
                } else {
                    Modifier
                },
            ),
    ) {
        Box(
            modifier = Modifier
                .padding(
                    top = if (drawCaret && caretOnTop) caretSize.height else 0.dp,
                    bottom = if (drawCaret && !caretOnTop) caretSize.height else 0.dp,
                )
                .dropShadow(
                    shape = RoundedCornerShape(cornerRadius),
                    shadow = Shadow(radius = 10.dp, color = Color.Black, alpha = 0.1f),
                )
                .squircleBackground(color = containerColor, cornerRadius = cornerRadius)
                .padding(insideMargin),
        ) {
            content()
        }
    }
}

/**
 * A plain tooltip convenience that wraps [TooltipBox] + [PlainTooltip] for a short text label.
 *
 * @param text the text label of the tooltip.
 * @param modifier the modifier applied to the anchor wrapper.
 * @param state the [TooltipState] controlling visibility.
 * @param enabled whether hover / long press on the anchor shows the tooltip.
 * @param positioning the preferred [TooltipAnchorPosition] of the tooltip.
 * @param containerColor the container color of the tooltip.
 * @param contentColor the content color of the tooltip.
 * @param content the anchor content.
 */
@Composable
fun TooltipBox(
    text: String,
    modifier: Modifier = Modifier,
    state: TooltipState = rememberTooltipState(isPersistent = false),
    enabled: Boolean = true,
    positioning: TooltipAnchorPosition = TooltipAnchorPosition.Below,
    containerColor: Color = TooltipDefaults.plainTooltipContainerColor,
    contentColor: Color = TooltipDefaults.plainTooltipContentColor,
    content: @Composable () -> Unit,
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(positioning),
        tooltip = {
            PlainTooltip(containerColor = containerColor, contentColor = contentColor) {
                Text(
                    text = text,
                    color = contentColor,
                    style = MiuixTheme.textStyles.body2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        },
        state = state,
        modifier = modifier,
        focusable = false,
        enableUserInput = enabled,
        content = content,
    )
}

/**
 * A rich tooltip convenience that wraps [TooltipBox] + [RichTooltip] with an optional title and a
 * single text action. The action invokes [onActionClick] then dismisses the tooltip.
 *
 * The tooltip is persistent: trigger it via long press / hover, or hoist [state] and call
 * [TooltipState.show] from the anchor's own click. It is dismissed by an outside tap, a back press,
 * or the action.
 *
 * @param text the body text of the tooltip.
 * @param modifier the modifier applied to the anchor wrapper.
 * @param state the [TooltipState] controlling visibility.
 * @param title the optional title shown above the body.
 * @param actionText the optional action label.
 * @param onActionClick called when the action is clicked, before the tooltip is dismissed.
 * @param enabled whether hover / long press on the anchor shows the tooltip.
 * @param positioning the preferred [TooltipAnchorPosition] of the tooltip.
 * @param colors the [RichTooltipColors] of the tooltip.
 * @param content the anchor content.
 */
@Composable
fun RichTooltipBox(
    text: String,
    modifier: Modifier = Modifier,
    state: TooltipState = rememberTooltipState(isPersistent = true),
    title: String? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    positioning: TooltipAnchorPosition = TooltipAnchorPosition.Below,
    colors: RichTooltipColors = TooltipDefaults.richTooltipColors(),
    content: @Composable () -> Unit,
) {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(positioning),
        tooltip = {
            RichTooltip(
                title = title?.let {
                    {
                        Text(
                            text = it,
                            color = colors.titleContentColor,
                            style = MiuixTheme.textStyles.subtitle,
                        )
                    }
                },
                action = actionText?.let {
                    {
                        TextButton(
                            text = it,
                            onClick = {
                                onActionClick?.invoke()
                                state.dismiss()
                            },
                            minWidth = 0.dp,
                            minHeight = 0.dp,
                            cornerRadius = TooltipDefaults.RichTooltipActionCornerRadius,
                            colors = ButtonDefaults.textButtonColors(
                                color = Color.Transparent,
                                textColor = colors.actionContentColor,
                            ),
                            insideMargin = TooltipDefaults.RichTooltipActionInsideMargin,
                        )
                    }
                },
                colors = colors,
            ) {
                Text(
                    text = text,
                    color = colors.contentColor,
                    style = MiuixTheme.textStyles.body2,
                )
            }
        },
        state = state,
        modifier = modifier,
        focusable = true,
        enableUserInput = enabled,
        content = content,
    )
}

/**
 * Colors for [RichTooltip].
 *
 * @param containerColor container color of the rich tooltip.
 * @param contentColor color of the rich tooltip body text.
 * @param titleContentColor color of the rich tooltip title.
 * @param actionContentColor color of the rich tooltip action label.
 */
@Immutable
data class RichTooltipColors(
    val containerColor: Color,
    val contentColor: Color,
    val titleContentColor: Color,
    val actionContentColor: Color,
)

/**
 * Positions a tooltip on the preferred side of its anchor, centered on the cross axis, flipping to
 * the opposite side when there is no room and clamped to the window bounds.
 */
@Immutable
private class TooltipPopupPositionProvider(
    val positioning: TooltipAnchorPosition,
    private val spacing: Int,
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize,
    ): IntOffset {
        val resolved = positioning.resolve(layoutDirection)
        val centerX = anchorBounds.left + (anchorBounds.width - popupContentSize.width) / 2
        val centerY = anchorBounds.top + (anchorBounds.height - popupContentSize.height) / 2

        var x = centerX
        var y = centerY
        when (resolved) {
            TooltipAnchorPosition.Below -> {
                val below = anchorBounds.bottom + spacing
                val above = anchorBounds.top - popupContentSize.height - spacing
                y = if (below + popupContentSize.height <= windowSize.height || above < 0) below else above
            }

            TooltipAnchorPosition.Above -> {
                val above = anchorBounds.top - popupContentSize.height - spacing
                val below = anchorBounds.bottom + spacing
                y = if (above >= 0 || below + popupContentSize.height > windowSize.height) above else below
            }

            TooltipAnchorPosition.Left -> {
                val left = anchorBounds.left - popupContentSize.width - spacing
                val right = anchorBounds.right + spacing
                x = if (left >= 0 || right + popupContentSize.width > windowSize.width) left else right
            }

            TooltipAnchorPosition.Right -> {
                val right = anchorBounds.right + spacing
                val left = anchorBounds.left - popupContentSize.width - spacing
                x = if (right + popupContentSize.width <= windowSize.width || left < 0) right else left
            }

            else -> {
                y = anchorBounds.bottom + spacing
            }
        }

        x = x.coerceIn(0, max(0, windowSize.width - popupContentSize.width))
        y = y.coerceIn(0, max(0, windowSize.height - popupContentSize.height))
        return IntOffset(x, y)
    }
}

private fun TooltipAnchorPosition.resolve(layoutDirection: LayoutDirection): TooltipAnchorPosition = when (this) {
    TooltipAnchorPosition.Start ->
        if (layoutDirection == LayoutDirection.Ltr) TooltipAnchorPosition.Left else TooltipAnchorPosition.Right

    TooltipAnchorPosition.End ->
        if (layoutDirection == LayoutDirection.Ltr) TooltipAnchorPosition.Right else TooltipAnchorPosition.Left

    else -> this
}

/**
 * Defaults for [TooltipBox], [PlainTooltip] and [RichTooltip].
 */
object TooltipDefaults {
    /** The default spacing between a tooltip and its anchor. */
    val SpacingBetweenTooltipAndAnchor = 8.dp

    /** The default size of the caret. */
    val caretSize = DpSize(16.dp, 8.dp)

    /** The default maximum width of a plain tooltip. */
    val PlainTooltipMaxWidth = 200.dp

    /** The default corner radius of a plain tooltip. */
    val PlainTooltipCornerRadius = 12.dp

    /** The default margin inside a plain tooltip. */
    val PlainTooltipInsideMargin = PaddingValues(horizontal = 12.dp, vertical = 8.dp)

    /** The default container color of a plain tooltip (inverse surface). */
    val plainTooltipContainerColor: Color
        @Composable get() = MiuixTheme.colorScheme.onSecondaryVariant

    /** The default content color of a plain tooltip. */
    val plainTooltipContentColor: Color
        @Composable get() = MiuixTheme.colorScheme.secondaryVariant

    /** The default maximum width of a rich tooltip. */
    val RichTooltipMaxWidth = 320.dp

    /** The default corner radius of a rich tooltip. */
    val RichTooltipCornerRadius = 16.dp

    /** The default margin inside a rich tooltip. */
    val RichTooltipInsideMargin = PaddingValues(all = 16.dp)

    /** The default corner radius of a rich tooltip action button. */
    val RichTooltipActionCornerRadius = 8.dp

    /** The default margin inside a rich tooltip action button. */
    val RichTooltipActionInsideMargin = PaddingValues(horizontal = 12.dp, vertical = 6.dp)

    /** Returns a [Shape] that, when passed as `caretShape`, enables the Miuix tooltip caret. */
    fun caretShape(): Shape = TooltipCaretShape

    @Composable
    fun richTooltipColors(
        containerColor: Color = MiuixTheme.colorScheme.surfaceContainer,
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainerVariant,
        titleContentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        actionContentColor: Color = MiuixTheme.colorScheme.primary,
    ): RichTooltipColors = remember(containerColor, contentColor, titleContentColor, actionContentColor) {
        RichTooltipColors(
            containerColor = containerColor,
            contentColor = contentColor,
            titleContentColor = titleContentColor,
            actionContentColor = actionContentColor,
        )
    }

    /**
     * Returns a [PopupPositionProvider] that positions a tooltip on the [positioning] side of its
     * anchor.
     *
     * @param positioning the preferred [TooltipAnchorPosition].
     * @param spacingBetweenTooltipAndAnchor the spacing between the tooltip and the anchor.
     */
    @Composable
    fun rememberTooltipPositionProvider(
        positioning: TooltipAnchorPosition = TooltipAnchorPosition.Below,
        spacingBetweenTooltipAndAnchor: Dp = SpacingBetweenTooltipAndAnchor,
    ): PopupPositionProvider {
        val spacingPx = with(LocalDensity.current) { spacingBetweenTooltipAndAnchor.roundToPx() }
        return remember(positioning, spacingPx) {
            TooltipPopupPositionProvider(positioning, spacingPx)
        }
    }
}

private object TooltipCaretShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path().apply {
            moveTo(size.width / 2f, 0f)
            lineTo(0f, size.height)
            lineTo(size.width, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}
