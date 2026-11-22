// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.layout

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Geometry and animated state supplied to a dialog's surface.
 *
 * Read [scrimColor] and [scale] during drawing to keep a sampled surface in step with the
 * dialog's opening, closing and predictive-back animations without recomposing every frame.
 *
 * @property cornerRadius The resolved radius, including the host's screen-dependent default.
 * @property scrimColor The current dimming color, including animation progress. Transparent when
 *   window dimming is disabled. A backdrop supplied to the surface should exclude this scrim.
 * @property scale The combined scale applied by the host. Backdrop sampling can invert it to
 *   keep the background aligned with the screen while the panel scales.
 */
@Stable
class DialogSurfaceScope internal constructor(
    val cornerRadius: Dp,
    val scrimColor: State<Color>,
    val scale: State<Float>,
)
