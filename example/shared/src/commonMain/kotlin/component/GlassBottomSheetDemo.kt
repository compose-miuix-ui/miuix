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
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.glass.GlassBottomSheet
import top.yukonga.miuix.kmp.glass.GlassBottomSheetDefaults
import top.yukonga.miuix.kmp.glass.GlassCard
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassDialogButtonRole
import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** A sheet with transparent content, so the shared material remains visible while scrolling. */
@Composable
internal fun GlassBottomSheetDemo(
    onDismissRequest: () -> Unit,
    show: Boolean,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    material: GlassMaterial = GlassBottomSheetDefaults.material(),
    stroke: EdgeStroke? = GlassBottomSheetDefaults.stroke(),
    onDismissFinished: (() -> Unit)? = null,
) {
    GlassBottomSheet(
        show = show,
        modifier = modifier,
        title = "Glass BottomSheet",
        backdrop = backdrop,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        material = material,
        stroke = stroke,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                BasicComponent(
                    title = "Drag or scroll",
                    summary = "Drag the handle or scroll the content to try the sheet material.",
                    onClick = {},
                )
            }
            repeat(4) { group ->
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    repeat(4) { index ->
                        BasicComponent(
                            title = "Sheet item ${group * 4 + index + 1}",
                            summary = "Content stays sharp above the blurred page.",
                            onClick = {},
                        )
                    }
                }
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

/** A half-height sheet, sized like the launcher's settings sheet, to compare the opening motion. */
@Composable
internal fun GlassHalfBottomSheetDemo(
    onDismissRequest: () -> Unit,
    show: Boolean,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    material: GlassMaterial = GlassBottomSheetDefaults.material(),
    stroke: EdgeStroke? = GlassBottomSheetDefaults.stroke(),
    onDismissFinished: (() -> Unit)? = null,
) {
    GlassBottomSheet(
        show = show,
        modifier = modifier,
        title = "Half BottomSheet",
        backdrop = backdrop,
        onDismissRequest = onDismissRequest,
        onDismissFinished = onDismissFinished,
        material = material,
        stroke = stroke,
    ) {
        Column(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                repeat(5) { index ->
                    BasicComponent(
                        title = "Setting ${index + 1}",
                        onClick = {},
                    )
                }
            }
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                BasicComponent(
                    title = "More settings",
                    onClick = onDismissRequest,
                )
            }
        }
    }
}
