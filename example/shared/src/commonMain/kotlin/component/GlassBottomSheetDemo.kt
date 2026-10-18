// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.glass.GlassBottomSheetDefaults
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassDialogButtonRole
import top.yukonga.miuix.kmp.glass.glassBottomSheetSurface
import top.yukonga.miuix.kmp.overlay.OverlayBottomSheet
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** A sheet with transparent content, so the shared material remains visible while scrolling. */
@Composable
internal fun GlassBottomSheetDemo(
    onDismissRequest: () -> Unit,
    show: Boolean,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
) {
    OverlayBottomSheet(
        show = show,
        modifier = modifier,
        title = "Glass Bottom Sheet",
        dimColor = GlassBottomSheetDefaults.ScrimColor,
        surface = glassBottomSheetSurface(backdrop),
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("Drag the handle or scroll the content to try the sheet material.", style = MiuixTheme.textStyles.body2)
            repeat(24) { index ->
                Column(Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Text("Sheet item ${index + 1}", style = MiuixTheme.textStyles.main)
                    Text("Content stays sharp above the blurred page.", style = MiuixTheme.textStyles.body2)
                }
                HorizontalDivider()
            }
            GlassDialogButton(
                onClick = onDismissRequest,
                modifier = Modifier.fillMaxWidth(),
                role = GlassDialogButtonRole.Primary,
            ) {
                Text("Done", style = MiuixTheme.textStyles.button)
            }
        }
    }
}
