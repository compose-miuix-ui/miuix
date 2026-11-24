// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import top.yukonga.miuix.kmp.layout.BottomSheetContentLayout
import top.yukonga.miuix.kmp.layout.BottomSheetDefaults
import top.yukonga.miuix.kmp.layout.SheetMotion

/**
 * A bottom sheet that slides up from the bottom of the screen, rendered at window level without `Scaffold`.
 *
 * Use [LocalDismissState] inside `content` to request dismissal from inner composables.
 *
 * @param show Whether the [WindowBottomSheet] is shown.
 * @param modifier The modifier to be applied to the [WindowBottomSheet].
 * @param title Optional title. The header takes up space if any of [title], [startAction], or
 *   [endAction] is non-null (including `title = ""`). The drag handle is unaffected.
 * @param startAction Optional [Composable] to display on the start side of the title (e.g. a close button).
 * @param endAction Optional [Composable] to display on the end side of the title (e.g. a submit button).
 * @param backgroundColor The background color of the [WindowBottomSheet].
 * @param enableWindowDim Whether to dim the window behind the [WindowBottomSheet].
 * @param cornerRadius The corner radius of the top corners of the [WindowBottomSheet].
 * @param sheetMaxWidth The maximum width of the [WindowBottomSheet].
 * @param onDismissRequest Will called when the user tries to dismiss the Dialog by clicking outside or pressing the back button.
 * @param onDismissFinished The callback when the [WindowBottomSheet] is completely dismissed.
 * @param outsideMargin The margin outside the [WindowBottomSheet].
 * @param insideMargin The margin inside the [WindowBottomSheet].
 * @param defaultWindowInsetsPadding Whether to apply default window insets padding.
 * @param dragHandleColor The color of the drag handle at the top.
 * @param allowDismiss Whether to allow dismissing the sheet via drag or back gesture.
 * @param enableNestedScroll Whether to enable nested scrolling for the content.
 * @param motion The enter, exit and settle motion. See [SheetMotion].
 * @param content The [Composable] content of the [WindowBottomSheet].
 */
@Composable
fun WindowBottomSheet(
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
    motion: SheetMotion = SheetMotion.Default,
    content: @Composable () -> Unit,
) {
    val currentOnDismissRequest = rememberUpdatedState(onDismissRequest)

    BottomSheetContentLayout(
        show = show,
        backgroundColor = backgroundColor,
        cornerRadius = cornerRadius,
        sheetMaxWidth = sheetMaxWidth,
        outsideMargin = outsideMargin,
        insideMargin = insideMargin,
        dragHandleColor = dragHandleColor,
        popupHost = { visible, hostContent ->
            if (visible) {
                WindowComponentHost(
                    onDismissRequest = {
                        if (allowDismiss) {
                            currentOnDismissRequest.value?.invoke()
                        }
                    },
                    content = hostContent,
                )
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
        motion = motion,
        content = content,
    )
}
