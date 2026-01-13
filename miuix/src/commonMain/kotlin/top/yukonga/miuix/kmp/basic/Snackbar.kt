// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.SearchCleanup
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * Possible durations of the [Snackbar].
 */
sealed interface SnackbarDuration {
    /** Show the Snackbar for a short period of time. */
    data object Short : SnackbarDuration

    /** Show the Snackbar for a long period of time. */
    data object Long : SnackbarDuration

    /** Show the Snackbar indefinitely until dismissed. */
    data object Indefinite : SnackbarDuration

    /** Show the Snackbar for a custom period of time. */
    data class Custom(val durationMillis: kotlin.Long) : SnackbarDuration
}

/**
 * Possible results of the [Snackbar].
 */
enum class SnackbarResult {
    /** The Snackbar was dismissed. */
    Dismissed,

    /** The Snackbar's action was performed. */
    ActionPerformed,
}

/**
 * Visuals for a [Snackbar].
 *
 * @param message text to be shown in the Snackbar
 * @param actionLabel optional action label to be shown in the Snackbar
 * @param withDismissAction whether to show a dismiss action in the Snackbar
 * @param duration duration of the Snackbar
 */
@Immutable
data class SnackbarVisuals(
    val message: String,
    val actionLabel: String?,
    val withDismissAction: Boolean,
    val duration: SnackbarDuration,
)

/**
 * Interface representing the data of a [Snackbar].
 */
interface SnackbarData {
    /** Visuals of the Snackbar. */
    val visuals: SnackbarVisuals

    /** Dismiss the Snackbar. */
    suspend fun dismiss()

    /** Perform the action of the Snackbar. */
    suspend fun performAction()
}

/**
 * State of the [SnackbarHost].
 *
 * It allows to show a [Snackbar] with a message and an optional action.
 */
@Stable
class SnackbarHostState {
    private val entries = mutableStateListOf<SnackbarEntry>()
    internal val currentSnackbars: List<SnackbarEntry> get() = entries
    suspend fun newestSnackbarData(): SnackbarData? = mutex.withLock {
        entries.firstOrNull { it.visible }?.data
    }

    suspend fun oldestSnackbarData(): SnackbarData? = mutex.withLock {
        entries.lastOrNull { it.visible }?.data
    }

    private val mutex = Mutex()
    private var idCounter = 0L

    internal suspend fun removeEntry(entry: SnackbarEntry) {
        mutex.withLock {
            entries.remove(entry)
        }
    }

    /**
     * Shows a [Snackbar] with the provided [message].
     *
     * @param message text to be shown in the Snackbar
     * @param actionLabel optional action label to be shown in the Snackbar
     * @param withDismissAction whether to show a dismiss action in the Snackbar
     * @param duration duration of the Snackbar
     * @return result of the Snackbar
     */
    suspend fun showSnackbar(
        message: String,
        actionLabel: String? = null,
        withDismissAction: Boolean = false,
        duration: SnackbarDuration = SnackbarDuration.Short,
    ): SnackbarResult {
        val result = CompletableDeferred<SnackbarResult>()
        val visuals = SnackbarVisuals(message, actionLabel, withDismissAction, duration)

        val data = mutex.withLock {
            val currentId = ++idCounter
            val data = object : SnackbarData {
                override val visuals = visuals
                private val snackbarMutex = Mutex()
                private var completed = false

                override suspend fun dismiss() {
                    snackbarMutex.withLock {
                        if (completed) return
                        completed = true
                    }
                    if (!result.isCompleted) result.complete(SnackbarResult.Dismissed)
                    clear()
                }

                override suspend fun performAction() {
                    snackbarMutex.withLock {
                        if (completed) return
                        completed = true
                    }
                    if (!result.isCompleted) result.complete(SnackbarResult.ActionPerformed)
                    clear()
                }

                private suspend fun clear() {
                    this@SnackbarHostState.mutex.withLock {
                        val index = entries.indexOfFirst { it.id == currentId }
                        if (index != -1) entries[index] = entries[index].copy(visible = false)
                    }
                }
            }
            val entry = SnackbarEntry(currentId, data)
            entries.add(0, entry)
            data
        }

        val timeout = when (duration) {
            SnackbarDuration.Short -> 4000L
            SnackbarDuration.Long -> 10000L
            is SnackbarDuration.Custom -> duration.durationMillis
            SnackbarDuration.Indefinite -> Long.MAX_VALUE
        }

        return coroutineScope {
            val job = launch {
                delay(timeout)
                data.dismiss()
            }
            val r = result.await()
            job.cancel()
            r
        }
    }

    @Immutable
    internal data class SnackbarEntry(
        val id: Long,
        val data: SnackbarData,
        val visible: Boolean = true,
    )
}

/**
 * Host for [Snackbar]s to be shown.
 *
 * @param state state of the [SnackbarHost]
 * @param modifier modifier to be applied to the [SnackbarHost]
 * @param content content of the [SnackbarHost]
 */
@Composable
fun SnackbarHost(
    state: SnackbarHostState,
    modifier: Modifier = Modifier,
    content: @Composable (SnackbarData) -> Unit = { Snackbar(it) },
) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        LazyColumn(
            reverseLayout = true,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 12.dp),
        ) {
            itemsIndexed(state.currentSnackbars, key = { _, entry -> entry.id }) { index, entry ->
                val visibleState = remember { MutableTransitionState(false) }
                visibleState.targetState = entry.visible

                if (!visibleState.targetState && visibleState.isIdle) {
                    LaunchedEffect(entry) { state.removeEntry(entry) }
                }

                AnimatedVisibility(
                    visibleState = visibleState,
                    enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it }) + fadeOut() + shrinkVertically(
                        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                        shrinkTowards = Alignment.Bottom,
                    ),
                    modifier = Modifier
                        .zIndex((state.currentSnackbars.size - index).toFloat())
                        .then(if (entry.visible) Modifier.animateItem() else Modifier),
                ) {
                    content(entry.data)
                }
            }
        }
    }
}

/**
 * A Snackbar is a temporary message that appears at the bottom of the screen.
 *
 * @param data data of the [Snackbar]
 * @param modifier modifier to be applied to the [Snackbar]
 * @param cornerRadius corner radius of the [Snackbar]
 * @param colors colors of the [Snackbar]
 * @param insideMargin margin inside the [Snackbar]
 */
@Composable
fun Snackbar(
    data: SnackbarData,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp,
    colors: SnackbarColors = SnackbarDefaults.snackbarColors(),
    insideMargin: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
) {
    val shape = remember(cornerRadius) { ContinuousRoundedRectangle(cornerRadius) }
    val visuals = data.visuals
    val scope = rememberCoroutineScope()

    Surface(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 2.dp)
            .dropShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 1.dp,
                    spread = 0.6.dp,
                    color = MiuixTheme.colorScheme.dividerLine,
                    offset = DpOffset(x = 0.dp, y = 1.dp),
                ),
            )
            .pointerInput(Unit) {
                detectTapGestures { /* Consume click */ }
            },
        shape = shape,
        color = colors.containerColor,
        contentColor = colors.contentColor,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .defaultMinSize(minHeight = 44.dp)
                .padding(insideMargin),
        ) {
            Text(
                text = visuals.message,
                color = colors.contentColor,
                style = MiuixTheme.textStyles.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )

            if (!visuals.actionLabel.isNullOrEmpty()) {
                val onAction by rememberUpdatedState(data::performAction)
                TextButton(
                    text = visuals.actionLabel,
                    onClick = { scope.launch { onAction() } },
                    colors = ButtonDefaults.textButtonColors(
                        color = Color.Transparent,
                        disabledColor = Color.Transparent,
                        textColor = colors.actionContentColor,
                        disabledTextColor = colors.actionContentColor,
                    ),
                    insideMargin = PaddingValues(horizontal = 8.dp, vertical = 6.dp),
                )
            }

            if (visuals.withDismissAction) {
                val onDismiss by rememberUpdatedState(data::dismiss)
                Icon(
                    imageVector = MiuixIcons.Basic.SearchCleanup,
                    contentDescription = null,
                    tint = colors.dismissActionContentColor,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .clickable { scope.launch { onDismiss() } },
                )
            }
        }
    }
}

/**
 * Colors for [Snackbar].
 *
 * @param containerColor container color of the Snackbar
 * @param contentColor content color of the Snackbar
 * @param actionContentColor action content color of the Snackbar
 * @param dismissActionContentColor dismiss action content color of the Snackbar
 */
@Immutable
data class SnackbarColors(
    val containerColor: Color,
    val contentColor: Color,
    val actionContentColor: Color,
    val dismissActionContentColor: Color,
)

/**
 * Defaults for [Snackbar].
 */
object SnackbarDefaults {
    @Composable
    fun snackbarColors(
        containerColor: Color = MiuixTheme.colorScheme.surfaceContainerHighest,
        contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
        actionContentColor: Color = MiuixTheme.colorScheme.onSurfaceContainerHighest,
        dismissActionContentColor: Color = MiuixTheme.colorScheme.onSurfaceContainerHighest,
    ): SnackbarColors = SnackbarColors(
        containerColor = containerColor,
        contentColor = contentColor,
        actionContentColor = actionContentColor,
        dismissActionContentColor = dismissActionContentColor,
    )
}
