// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.overlay

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import top.yukonga.miuix.kmp.layout.DialogContentLayout
import top.yukonga.miuix.kmp.layout.DialogDefaults
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.DialogLayout

/**
 * A dialog with a title, a summary, and other contents.
 *
 * @param show Whether the [OverlayDialog] is shown.
 * @param modifier The modifier to be applied to the [OverlayDialog].
 * @param title The title of the [OverlayDialog].
 * @param titleColor The color of the title.
 * @param summary The summary of the [OverlayDialog].
 * @param summaryColor The color of the summary.
 * @param backgroundColor The background color of the [OverlayDialog].
 * @param enableWindowDim Whether to enable window dimming when the [OverlayDialog] is shown.
 * @param onDismissRequest Will called when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * @param onDismissFinished The callback when the [OverlayDialog] is completely dismissed.
 * @param outsideMargin The margin outside the [OverlayDialog].
 * @param insideMargin The margin inside the [OverlayDialog].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding to the [OverlayDialog].
 * @param renderInRootScaffold Whether to render the dialog in the root (outermost) Scaffold.
 *   When true (default), the dialog covers the full screen. When false, it renders within the
 *   current Scaffold's bounds.
 * @param content The [Composable] content of the [OverlayDialog].
 */
@Composable
fun OverlayDialog(
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
    renderInRootScaffold: Boolean = true,
    content: @Composable () -> Unit,
) {
    DialogContentLayout(
        show = show,
        titleColor = titleColor,
        summaryColor = summaryColor,
        backgroundColor = backgroundColor,
        outsideMargin = outsideMargin,
        insideMargin = insideMargin,
        popupHost = { visible, hostContent ->
            val visibleState = remember { mutableStateOf(false) }
            visibleState.value = visible
            DialogLayout(
                visible = visibleState,
                enableWindowDim = false,
                enterTransition = EnterTransition.None,
                exitTransition = ExitTransition.None,
                enableAutoLargeScreen = false,
                renderInRootScaffold = renderInRootScaffold,
            ) {
                hostContent()
            }
        },
        modifier = modifier,
        title = title,
        summary = summary,
        enableWindowDim = enableWindowDim,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        content = content,
    )
}

/**
 * A dialog with a title, a summary, and other contents.
 */
@Deprecated(
    message = "Use OverlayDialog with show: Boolean parameter instead for unidirectional data flow.",
    replaceWith = ReplaceWith(
        "OverlayDialog(show = show.value, modifier = modifier, title = title, titleColor = titleColor, summary = summary, summaryColor = summaryColor, backgroundColor = backgroundColor, enableWindowDim = enableWindowDim, onDismissRequest = onDismissRequest, onDismissFinished = onDismissFinished, outsideMargin = outsideMargin, insideMargin = insideMargin, defaultWindowInsetsPadding = defaultWindowInsetsPadding, renderInRootScaffold = renderInRootScaffold, content = content)",
    ),
)
@Composable
fun OverlayDialog(
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
    renderInRootScaffold: Boolean = true,
    content: @Composable () -> Unit,
) {
    OverlayDialog(
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
        renderInRootScaffold = renderInRootScaffold,
        content = content,
    )
}
