// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.extra

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.ListPopupColumn
import top.yukonga.miuix.kmp.basic.ListPopupDefaults
import top.yukonga.miuix.kmp.basic.PopupPositionProvider
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.PopupLayout

/**
 * A popup with a list of items.
 *
 * @param show Whether the [SuperListPopup] is shown.
 * @param popupModifier The modifier to be applied to the [SuperListPopup].
 * @param popupPositionProvider The [PopupPositionProvider] of the [SuperListPopup].
 * @param alignment The alignment of the [SuperListPopup].
 * @param enableWindowDim Whether to enable window dimming when the [SuperListPopup] is shown.
 * @param onDismissRequest The callback when the [SuperListPopup] is dismissed.
 * @param onDismissFinished The callback when the [SuperListPopup] is completely dismissed (after exit animation).
 * @param maxHeight The maximum height of the [SuperListPopup]. If null, the height will be calculated automatically.
 * @param minWidth The minimum width of the [SuperListPopup].
 * @param renderInRootScaffold Whether to render the popup in the root (outermost) Scaffold.
 *   When true (default), the popup covers the full screen. When false, it renders within the
 *   current Scaffold's bounds with position compensation.
 * @param content The [Composable] content of the [SuperListPopup]. You should use the [ListPopupColumn] in general.
 */
@Composable
fun SuperListPopup(
    show: Boolean,
    popupModifier: Modifier = Modifier,
    popupPositionProvider: PopupPositionProvider = ListPopupDefaults.DropdownPositionProvider,
    alignment: PopupPositionProvider.Align = PopupPositionProvider.Align.Start,
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    maxHeight: Dp? = null,
    minWidth: Dp = 200.dp,
    renderInRootScaffold: Boolean = true,
    content: @Composable () -> Unit,
) {
    ListPopupLayout(
        show = show,
        popupHost = { visible, hostContent ->
            val visibleState = remember { mutableStateOf(false) }
            visibleState.value = visible
            PopupLayout(
                visible = visibleState,
                enableWindowDim = false,
                enableBackHandler = false,
                enterTransition = EnterTransition.None,
                exitTransition = ExitTransition.None,
                renderInRootScaffold = renderInRootScaffold,
            ) {
                hostContent()
            }
        },
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

/**
 * A popup with a list of items.
 */
@Deprecated(
    message = "Use SuperListPopup with show: Boolean parameter instead for unidirectional data flow.",
    replaceWith = ReplaceWith(
        "SuperListPopup(show = show.value, popupModifier = popupModifier, popupPositionProvider = popupPositionProvider, alignment = alignment, enableWindowDim = enableWindowDim, onDismissRequest = onDismissRequest, maxHeight = maxHeight, minWidth = minWidth, renderInRootScaffold = renderInRootScaffold, content = content)",
    ),
)
@Composable
fun SuperListPopup(
    show: MutableState<Boolean>,
    popupModifier: Modifier = Modifier,
    popupPositionProvider: PopupPositionProvider = ListPopupDefaults.DropdownPositionProvider,
    alignment: PopupPositionProvider.Align = PopupPositionProvider.Align.Start,
    enableWindowDim: Boolean = true,
    onDismissRequest: (() -> Unit)? = null,
    onDismissFinished: (() -> Unit)? = null,
    maxHeight: Dp? = null,
    minWidth: Dp = 200.dp,
    renderInRootScaffold: Boolean = true,
    content: @Composable () -> Unit,
) {
    SuperListPopup(
        show = show.value,
        popupModifier = popupModifier,
        popupPositionProvider = popupPositionProvider,
        alignment = alignment,
        enableWindowDim = enableWindowDim,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        maxHeight = maxHeight,
        minWidth = minWidth,
        renderInRootScaffold = renderInRootScaffold,
        content = content,
    )
}
