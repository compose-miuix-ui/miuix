// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Geometry and animated state supplied to a bottom sheet's surface.
 *
 * Read [scrimColor] during drawing. Backdrop coordinates follow the host's slide, drag and back
 * gesture translation; the supplied backdrop should exclude the sheet and its scrim.
 *
 * @property cornerRadius The top-corner radius. The bottom corners are square.
 * @property scrimColor Current dimming color, including opening, closing and drag progress.
 *   Transparent when window dimming is disabled.
 * @property isOverscroll Whether this surface fills the exposed bottom edge during an upward
 *   overscroll. This extra surface has zero corner radius and should not draw an edge stroke.
 */
@Stable
class BottomSheetSurfaceScope internal constructor(
    val cornerRadius: Dp,
    val scrimColor: State<Color>,
    val isOverscroll: Boolean,
)
