// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.captionBar
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Dialog
import top.yukonga.miuix.kmp.utils.RemovePlatformDialogDefaultEffects
import top.yukonga.miuix.kmp.utils.platformDialogProperties

/**
 * A dialog with a title, a summary, and other contents, rendered at window level without `Scaffold`.
 *
 * Use [LocalWindowDialogState] inside `content` to request dismissal from inner composables.
 *
 * @param show Whether the [WindowDialog] is shown.
 * @param modifier The modifier to be applied to the [WindowDialog].
 * @param title The title of the [WindowDialog].
 * @param titleColor The color of the title.
 * @param summary The summary of the [WindowDialog].
 * @param summaryColor The color of the summary.
 * @param backgroundColor The background color of the [WindowDialog].
 * @param enableWindowDim Whether to enable window dimming when the [WindowDialog] is shown.
 * @param onDismissRequest Will called when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * @param onDismissFinished The callback when the [WindowDialog] is completely dismissed.
 * @param outsideMargin The margin outside the [WindowDialog].
 * @param insideMargin The margin inside the [WindowDialog].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding to the [WindowDialog].
 * @param content The [Composable] content of the [WindowDialog].
 */
@Composable
fun WindowDialog(
    show: Boolean,
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: Color = DialogDefaults.titleColor(),
    summary: String? = null,
    summaryColor: Color = DialogDefaults.summaryColor(),
    backgroundColor: Color = DialogDefaults.backgroundColor(),
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    outsideMargin: DpSize = DialogDefaults.outsideMargin,
    insideMargin: DpSize = DialogDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    content: @Composable () -> Unit,
) {
    val statusBarsPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val captionBarPadding = WindowInsets.captionBar.asPaddingValues().calculateTopPadding()
    val displayCutoutPadding = WindowInsets.displayCutout.asPaddingValues().calculateTopPadding()
    val safeTopInset = remember(statusBarsPadding, captionBarPadding, displayCutoutPadding) {
        maxOf(statusBarsPadding, captionBarPadding, displayCutoutPadding)
    }

    val currentOnDismissRequest = rememberUpdatedState(onDismissRequest)

    DialogContentLayout(
        show = show,
        modifier = modifier,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        backgroundColor = backgroundColor,
        enableWindowDim = enableWindowDim,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        outsideMargin = outsideMargin,
        insideMargin = insideMargin,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        topInset = safeTopInset,
        popupHost = { visible, hostContent ->
            if (visible) {
                Dialog(
                    onDismissRequest = { currentOnDismissRequest.value?.invoke() },
                    properties = platformDialogProperties(),
                ) {
                    RemovePlatformDialogDefaultEffects()
                    hostContent()
                }
            }
        },
        content = {
            CompositionLocalProvider(
                LocalWindowDialogState provides { currentOnDismissRequest.value?.invoke() },
            ) {
                content()
            }
        },
    )
}

/**
 * A dialog with a title, a summary, and other contents, rendered at window level without `Scaffold`.
 */
@Deprecated(
    message = "Use WindowDialog with show: Boolean parameter instead for unidirectional data flow.",
    replaceWith = ReplaceWith(
        "WindowDialog(show = show.value, modifier = modifier, title = title, titleColor = titleColor, summary = summary, summaryColor = summaryColor, backgroundColor = backgroundColor, enableWindowDim = enableWindowDim, onDismissRequest = onDismissRequest, onDismissFinished = onDismissFinished, outsideMargin = outsideMargin, insideMargin = insideMargin, defaultWindowInsetsPadding = defaultWindowInsetsPadding, content = content)",
    ),
)
@Composable
fun WindowDialog(
    show: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    title: String? = null,
    titleColor: Color = DialogDefaults.titleColor(),
    summary: String? = null,
    summaryColor: Color = DialogDefaults.summaryColor(),
    backgroundColor: Color = DialogDefaults.backgroundColor(),
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    outsideMargin: DpSize = DialogDefaults.outsideMargin,
    insideMargin: DpSize = DialogDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    content: @Composable () -> Unit,
) {
    WindowDialog(
        show = show.value,
        modifier = modifier,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        backgroundColor = backgroundColor,
        enableWindowDim = enableWindowDim,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        outsideMargin = outsideMargin,
        insideMargin = insideMargin,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        content = content,
    )
}

@Deprecated("Use DialogDefaults instead", ReplaceWith("DialogDefaults"))
object WindowDialogDefaults {
    @Composable
    internal fun isLargeScreen() = DialogDefaults.isLargeScreen()

    @Composable
    fun titleColor() = DialogDefaults.titleColor()

    @Composable
    fun summaryColor() = DialogDefaults.summaryColor()

    @Composable
    fun backgroundColor() = DialogDefaults.backgroundColor()

    val outsideMargin get() = DialogDefaults.outsideMargin

    val insideMargin get() = DialogDefaults.insideMargin
}

@Deprecated(
    "Use LocalDismissState instead, which is provided by all overlay components.",
    ReplaceWith("LocalDismissState", "top.yukonga.miuix.kmp.extra.LocalDismissState"),
)
val LocalWindowDialogState = staticCompositionLocalOf<() -> Unit> {
    error("LocalWindowDialogState not provided")
}
