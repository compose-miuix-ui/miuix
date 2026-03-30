// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.overlay

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import top.yukonga.miuix.kmp.layout.BottomSheetContentLayout
import top.yukonga.miuix.kmp.layout.BottomSheetDefaults
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.DialogLayout

/**
 * A bottom sheet that slides up from the bottom of the screen.
 * The height adapts to the content size, but will not cover the status bar area.
 *
 * @param show Whether the [OverlayBottomSheet] is shown.
 * @param modifier The modifier to be applied to the [OverlayBottomSheet].
 * @param title Optional title to display at the top of the [OverlayBottomSheet].
 * @param startAction Optional [Composable] to display on the start side of the title (e.g. a close button).
 * @param endAction Optional [Composable] to display on the end side of the title (e.g. a submit button).
 * @param backgroundColor The background color of the [OverlayBottomSheet].
 * @param enableWindowDim Whether to dim the window behind the [OverlayBottomSheet].
 * @param cornerRadius The corner radius of the top corners of the [OverlayBottomSheet].
 * @param sheetMaxWidth The maximum width of the [OverlayBottomSheet].
 * @param onDismissRequest Will called when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * @param onDismissFinished The callback when the [OverlayBottomSheet] is completely dismissed.
 * @param outsideMargin The margin outside the [OverlayBottomSheet].
 * @param insideMargin The margin inside the [OverlayBottomSheet].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding.
 * @param dragHandleColor The color of the drag handle at the top.
 * @param allowDismiss Whether to allow dismissing the sheet via drag or back gesture.
 * @param enableNestedScroll Whether to enable nested scrolling for the content.
 * @param renderInRootScaffold Whether to render the bottom sheet in the root (outermost) Scaffold.
 *   When true (default), the bottom sheet covers the full screen. When false, it renders within the
 *   current Scaffold's bounds.
 * @param content The [Composable] content of the [OverlayBottomSheet].
 */
@Composable
fun OverlayBottomSheet(
    show: Boolean,
    modifier: Modifier = Modifier,
    title: String? = null,
    startAction: @Composable (() -> Unit)? = null,
    endAction: @Composable (() -> Unit)? = null,
    backgroundColor: Color = BottomSheetDefaults.backgroundColor(),
    enableWindowDim: Boolean = true,
    cornerRadius: Dp = BottomSheetDefaults.cornerRadius,
    sheetMaxWidth: Dp = BottomSheetDefaults.maxWidth,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    outsideMargin: DpSize = BottomSheetDefaults.outsideMargin,
    insideMargin: DpSize = BottomSheetDefaults.insideMargin,
    defaultWindowInsetsPadding: Boolean = true,
    dragHandleColor: Color = BottomSheetDefaults.dragHandleColor(),
    allowDismiss: Boolean = true,
    enableNestedScroll: Boolean = true,
    renderInRootScaffold: Boolean = true,
    content: @Composable () -> Unit,
) {
    BottomSheetContentLayout(
        show = show,
        backgroundColor = backgroundColor,
        cornerRadius = cornerRadius,
        sheetMaxWidth = sheetMaxWidth,
        outsideMargin = outsideMargin,
        insideMargin = insideMargin,
        dragHandleColor = dragHandleColor,
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
        startAction = startAction,
        endAction = endAction,
        enableWindowDim = enableWindowDim,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        allowDismiss = allowDismiss,
        enableNestedScroll = enableNestedScroll,
        content = content,
    )
}
