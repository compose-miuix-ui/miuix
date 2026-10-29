// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.layout.BottomSheetDefaults
import top.yukonga.miuix.kmp.overlay.OverlayBottomSheet

/**
 * A Scaffold-hosted glass bottom sheet with an animated scrim, drag dismissal and predictive Back.
 *
 * The [backdrop] must include the page and bars but exclude the sheet host and its scrim.
 * A `null` backdrop or unavailable runtime shaders use [containerColor]. The glass extends to the
 * screen edge and fills upward overscroll. Apply navigation-bar padding inside scrollable content
 * when that spacing should scroll with the content.
 *
 * @param onDismissRequest Called on an allowed outside tap, drag dismissal or back gesture.
 * @param show Whether the sheet is shown.
 * @param backdrop Page backdrop; `null` uses an opaque fill.
 * @param modifier Modifier applied to the sheet panel.
 * @param enableWindowDim Whether to draw and sample the animated [dimColor].
 * @param defaultWindowInsetsPadding Whether to apply the host's default window insets, including IME padding.
 * @param allowDismiss Whether outside taps, drags and back gestures may dismiss the sheet.
 * @param enableNestedScroll Whether child scrolling can transfer motion to the sheet.
 * @param renderInRootScaffold Whether the outermost Scaffold hosts the sheet.
 * @param title Optional title above the body.
 * @param startAction Optional leading header action.
 * @param endAction Optional trailing header action.
 * @param cornerRadius Top-corner radius; bottom corners remain square.
 * @param sheetMaxWidth Maximum panel width.
 * @param outsideMargin Panel external margins.
 * @param insideMargin Panel content padding.
 * @param dragHandleColor Handle color before its press opacity is applied.
 * @param material Blur input and ordered color layers.
 * @param stroke Optional bloom stroke, omitted on the overscroll extension.
 * @param containerColor Opaque fallback fill.
 * @param dimColor Scrim color before the host applies animation progress.
 * @param onDismissFinished Called after the hide animation completes.
 * @param content Sheet content. Add scrolling, navigation-bar padding and extra spacing as needed.
 */
@Composable
fun GlassBottomSheet(
    onDismissRequest: () -> Unit,
    show: Boolean,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    enableWindowDim: Boolean = true,
    defaultWindowInsetsPadding: Boolean = true,
    allowDismiss: Boolean = true,
    enableNestedScroll: Boolean = true,
    renderInRootScaffold: Boolean = true,
    title: String? = null,
    startAction: @Composable (() -> Unit)? = null,
    endAction: @Composable (() -> Unit)? = null,
    cornerRadius: Dp = BottomSheetDefaults.cornerRadius,
    sheetMaxWidth: Dp = BottomSheetDefaults.maxWidth,
    outsideMargin: DpSize = BottomSheetDefaults.outsideMargin,
    insideMargin: DpSize = BottomSheetDefaults.insideMargin,
    dragHandleColor: Color = BottomSheetDefaults.dragHandleColor(),
    material: GlassMaterial = GlassBottomSheetDefaults.material(),
    stroke: GlassStroke? = GlassBottomSheetDefaults.stroke(),
    containerColor: Color = GlassBottomSheetDefaults.containerColor(),
    dimColor: Color = GlassBottomSheetDefaults.ScrimColor,
    onDismissFinished: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    OverlayBottomSheet(
        show = show,
        modifier = modifier,
        title = title,
        startAction = startAction,
        endAction = endAction,
        backgroundColor = containerColor,
        enableWindowDim = enableWindowDim,
        cornerRadius = cornerRadius,
        sheetMaxWidth = sheetMaxWidth,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        outsideMargin = outsideMargin,
        insideMargin = insideMargin,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        dragHandleColor = dragHandleColor,
        allowDismiss = allowDismiss,
        enableNestedScroll = enableNestedScroll,
        renderInRootScaffold = renderInRootScaffold,
        dimColor = dimColor,
        surface = glassBottomSheetSurface(backdrop, material, stroke, containerColor),
        content = content,
    )
}
