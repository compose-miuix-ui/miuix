// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.layout.DialogDefaults
import top.yukonga.miuix.kmp.overlay.OverlayDialog

/**
 * A Scaffold-hosted glass dialog with an animated scrim and predictive-back support.
 *
 * The [backdrop] must include the page and bars but exclude the dialog host and its scrim.
 * A `null` backdrop or unavailable runtime shaders use [containerColor]. Use [GlassDialogButton]
 * for dialog actions.
 *
 * @param onDismissRequest Called on an outside tap or completed back gesture.
 * @param show Whether the dialog is shown.
 * @param backdrop Page backdrop; `null` uses an opaque fill.
 * @param modifier Modifier applied to the dialog panel.
 * @param enableWindowDim Whether to draw and sample the animated [dimColor].
 * @param defaultWindowInsetsPadding Whether the host applies default system/IME insets.
 * @param renderInRootScaffold Whether the outermost Scaffold hosts the dialog.
 * @param largeScreen Override for centered scale/fade presentation; null detects window size.
 * @param title Optional host-rendered title.
 * @param titleColor Title color.
 * @param summary Optional host-rendered summary.
 * @param summaryColor Summary color.
 * @param cornerRadius Optional radius override; null uses the host's presentation defaults.
 * @param maxWidth Maximum panel width.
 * @param outsideMargin Panel external margins.
 * @param insideMargin Panel content padding.
 * @param material Blur input and ordered color layers.
 * @param stroke Optional bloom stroke.
 * @param containerColor Opaque fallback fill.
 * @param dimColor Scrim color before the host applies animation progress.
 * @param onDismissFinished Called after the hide animation completes.
 * @param content Dialog content, drawn sharply above the material.
 */
@Composable
fun GlassDialog(
    onDismissRequest: () -> Unit,
    show: Boolean,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    enableWindowDim: Boolean = true,
    defaultWindowInsetsPadding: Boolean = true,
    renderInRootScaffold: Boolean = true,
    largeScreen: Boolean? = null,
    title: String? = null,
    titleColor: Color = DialogDefaults.titleColor(),
    summary: String? = null,
    summaryColor: Color = DialogDefaults.summaryColor(),
    cornerRadius: Dp? = null,
    maxWidth: Dp = DialogDefaults.MaxWidth,
    outsideMargin: DpSize = DialogDefaults.outsideMargin,
    insideMargin: DpSize = DialogDefaults.insideMargin,
    material: GlassMaterial = GlassDialogDefaults.material(),
    stroke: GlassStroke? = GlassDialogDefaults.stroke(),
    containerColor: Color = GlassDialogDefaults.containerColor(),
    dimColor: Color = GlassDialogDefaults.ScrimColor,
    onDismissFinished: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    OverlayDialog(
        show = show,
        modifier = modifier,
        title = title,
        titleColor = titleColor,
        summary = summary,
        summaryColor = summaryColor,
        backgroundColor = containerColor,
        enableWindowDim = enableWindowDim,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        outsideMargin = outsideMargin,
        insideMargin = insideMargin,
        defaultWindowInsetsPadding = defaultWindowInsetsPadding,
        renderInRootScaffold = renderInRootScaffold,
        maxWidth = maxWidth,
        largeScreen = largeScreen,
        cornerRadius = cornerRadius,
        dimColor = dimColor,
        surface = glassDialogSurface(backdrop, material, stroke, containerColor),
        content = content,
    )
}
