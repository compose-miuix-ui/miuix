// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.layout.ListPopupLayout

/**
 * A popup with a list of items, rendered at window level without `Scaffold`.
 *
 * Use [LocalDismissState] inside `content` to request dismissal from inner composables.
 *
 * @param show Whether the [WindowListPopup] is shown.
 * @param popupModifier The modifier to be applied to the [WindowListPopup].
 * @param popupPositionProvider The [PopupPositionProvider] of the [WindowListPopup].
 * @param alignment The alignment of the [WindowListPopup].
 * @param enableWindowDim Whether to enable window dimming when the [WindowListPopup] is shown.
 * @param onDismissRequest The callback when the [WindowListPopup] is dismissed.
 * @param onDismissFinished The callback when the [WindowListPopup] is completely dismissed (after exit animation).
 * @param maxHeight The maximum height of the [WindowListPopup]. If null, the height will be calculated automatically.
 * @param minWidth The minimum width of the [WindowListPopup].
 * @param content The [Composable] content of the [WindowListPopup]. You should use the [ListPopupColumn] in general.
 */
@Composable
fun WindowListPopup(
    show: Boolean,
    popupModifier: Modifier = Modifier,
    popupPositionProvider: PopupPositionProvider = ListPopupDefaults.DropdownPositionProvider,
    alignment: PopupPositionProvider.Align = PopupPositionProvider.Align.Start,
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    maxHeight: Dp? = null,
    minWidth: Dp = ListPopupDefaults.MinWidth,
    content: @Composable () -> Unit,
) {
    val currentOnDismissRequest = rememberUpdatedState(onDismissRequest)

    ListPopupLayout(
        show = show,
        popupHost = { visible, hostContent ->
            if (visible) {
                WindowComponentHost(
                    onDismissRequest = { currentOnDismissRequest.value?.invoke() },
                    content = hostContent,
                )
            }
        },
        useWindowComponentBounds = true,
        constrainHeightToViewport = true,
        popupModifier = popupModifier,
        popupPositionProvider = popupPositionProvider,
        alignment = alignment,
        enableWindowDim = enableWindowDim,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
        minWidth = minWidth,
        content = content,
    )
}
