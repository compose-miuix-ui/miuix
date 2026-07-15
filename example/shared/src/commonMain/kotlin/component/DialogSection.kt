// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.VerticalDivider
import top.yukonga.miuix.kmp.layout.DialogDefaults
import top.yukonga.miuix.kmp.overlay.OverlayDialog
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.LocalDismissState
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.window.WindowDialog

fun LazyListScope.dialogSection() {
    item(key = "dialog") {
        var showOverlayDialog by remember { mutableStateOf(false) }
        var showWindowDialog by remember { mutableStateOf(false) }
        var overlayDialogHoldDown by remember { mutableStateOf(false) }
        var windowDialogHoldDown by remember { mutableStateOf(false) }
        var showWideSuperDialog by remember { mutableStateOf(false) }
        var showWideWindowDialog by remember { mutableStateOf(false) }
        var wideSuperDialogHoldDown by remember { mutableStateOf(false) }
        var wideWindowDialogHoldDown by remember { mutableStateOf(false) }
        var showCenteredDialog by remember { mutableStateOf(false) }
        var centeredDialogHoldDown by remember { mutableStateOf(false) }

        SmallTitle(text = "Dialog")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        ) {
            ArrowPreference(
                title = "Dialog (O)",
                summary = "Click to show an OverlayDialog",
                onClick = {
                    showOverlayDialog = true
                    overlayDialogHoldDown = true
                },
                holdDownState = overlayDialogHoldDown,
            )
            ArrowPreference(
                title = "Dialog (W)",
                summary = "Click to show a WindowDialog",
                onClick = {
                    showWindowDialog = true
                    windowDialogHoldDown = true
                },
                holdDownState = windowDialogHoldDown,
            )
            ArrowPreference(
                title = "Wide Dialog (O)",
                summary = "Portrait shows a regular dialog; landscape shows a two-column dialog",
                onClick = {
                    showWideSuperDialog = true
                    wideSuperDialogHoldDown = true
                },
                holdDownState = wideSuperDialogHoldDown,
            )
            ArrowPreference(
                title = "Wide Dialog (W)",
                summary = "Portrait shows a regular dialog; landscape shows a two-column dialog",
                onClick = {
                    showWideWindowDialog = true
                    wideWindowDialogHoldDown = true
                },
                holdDownState = wideWindowDialogHoldDown,
            )
            ArrowPreference(
                title = "Centered Dialog (O)",
                summary = "Force the large-screen presentation with largeScreen = true",
                onClick = {
                    showCenteredDialog = true
                    centeredDialogHoldDown = true
                },
                holdDownState = centeredDialogHoldDown,
            )
        }

        OverlayDialogDemo(
            show = showOverlayDialog,
            onDismissRequest = { showOverlayDialog = false },
            onDismissFinished = { overlayDialogHoldDown = false },
        )
        WindowDialogDemo(
            show = showWindowDialog,
            onDismissRequest = { showWindowDialog = false },
            onDismissFinished = { windowDialogHoldDown = false },
        )
        WideSuperDialogDemo(
            show = showWideSuperDialog,
            onDismissRequest = { showWideSuperDialog = false },
            onDismissFinished = { wideSuperDialogHoldDown = false },
        )
        WideWindowDialogDemo(
            show = showWideWindowDialog,
            onDismissRequest = { showWideWindowDialog = false },
            onDismissFinished = { wideWindowDialogHoldDown = false },
        )
        CenteredOverlayDialogDemo(
            show = showCenteredDialog,
            onDismissRequest = { showCenteredDialog = false },
            onDismissFinished = { centeredDialogHoldDown = false },
        )
    }
}

@Composable
private fun OverlayDialogDemo(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onDismissFinished: () -> Unit,
) {
    OverlayDialog(
        show = show,
        title = "Dialog (O)",
        summary = "A dialog component inside MiuixPopupHost.",
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(
                    text = "Cancel",
                    onClick = onDismissRequest,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(20.dp))
                TextButton(
                    text = "Confirm",
                    onClick = onDismissRequest,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.textButtonColorsPrimary(),
                )
            }
        },
    )
}

@Composable
private fun WindowDialogDemo(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onDismissFinished: () -> Unit,
) {
    WindowDialog(
        show = show,
        title = "Dialog (W)",
        summary = "A window-level dialog, no MiuixPopupHost required.",
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        content = {
            val dismissState = LocalDismissState.current
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(
                    text = "Cancel",
                    onClick = { dismissState?.invoke() },
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(20.dp))
                TextButton(
                    text = "Confirm",
                    onClick = { dismissState?.invoke() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.textButtonColorsPrimary(),
                )
            }
        },
    )
}

@Composable
private fun CenteredOverlayDialogDemo(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onDismissFinished: () -> Unit,
) {
    OverlayDialog(
        show = show,
        title = "Centered Dialog",
        summary = "largeScreen = true forces the centered presentation on any window size.",
        largeScreen = true,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(
                    text = "Cancel",
                    onClick = onDismissRequest,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(20.dp))
                TextButton(
                    text = "Confirm",
                    onClick = onDismissRequest,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.textButtonColorsPrimary(),
                )
            }
        },
    )
}

@Composable
private fun WideSuperDialogDemo(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onDismissFinished: () -> Unit,
) {
    val windowSize = LocalWindowInfo.current.containerDpSize
    val isLandscape = windowSize.width > windowSize.height

    OverlayDialog(
        show = show,
        title = if (isLandscape) null else "Wide Dialog",
        summary = if (isLandscape) null else "Rotate to landscape to see the effect.",
        maxWidth = if (isLandscape) 560.dp else DialogDefaults.MaxWidth,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        content = {
            WideDialogContent(isLandscape = isLandscape)
        },
    )
}

@Composable
private fun WideWindowDialogDemo(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onDismissFinished: () -> Unit,
) {
    val windowSize = LocalWindowInfo.current.containerDpSize
    val isLandscape = windowSize.width > windowSize.height

    WindowDialog(
        show = show,
        title = if (isLandscape) null else "Wide Dialog",
        summary = if (isLandscape) null else "Rotate to landscape to see the effect.",
        maxWidth = if (isLandscape) 560.dp else DialogDefaults.MaxWidth,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        content = {
            WideDialogContent(isLandscape = isLandscape)
        },
    )
}

@Composable
private fun WideDialogContent(
    isLandscape: Boolean,
) {
    val dismissState = LocalDismissState.current

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Wide Dialog",
                    style = MiuixTheme.textStyles.title4,
                    color = MiuixTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Rotate to landscape to see the effect.",
                    style = MiuixTheme.textStyles.body1,
                    color = MiuixTheme.colorScheme.onSurfaceSecondary,
                    textAlign = TextAlign.Center,
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp),
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(
                    space = 12.dp,
                    alignment = Alignment.CenterVertically,
                ),
            ) {
                TextButton(
                    text = "Allow Once",
                    onClick = { dismissState?.invoke() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColorsPrimary(),
                )
                TextButton(
                    text = "Always Allow",
                    onClick = { dismissState?.invoke() },
                    modifier = Modifier.fillMaxWidth(),
                )
                TextButton(
                    text = "Deny",
                    onClick = { dismissState?.invoke() },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            TextButton(
                text = "Allow Once",
                onClick = { dismissState?.invoke() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColorsPrimary(),
            )
            TextButton(
                text = "Always Allow",
                onClick = { dismissState?.invoke() },
                modifier = Modifier.fillMaxWidth(),
            )
            TextButton(
                text = "Deny",
                onClick = { dismissState?.invoke() },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
