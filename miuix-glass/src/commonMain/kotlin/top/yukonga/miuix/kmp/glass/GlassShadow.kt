// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * Defines the shadow cast by a glass surface.
 *
 * @property color Shadow colour; its alpha is the strength at full opacity.
 * @property offsetX Horizontal displacement.
 * @property offsetY Vertical displacement. Positive values place the shadow below the surface.
 * @property radius Distance the shadow extends beyond the silhouette.
 * @property dispersion Falloff distribution. Values below 0.5 keep the shadow near the edge;
 *   higher values spread it farther and reduce its density.
 */
@Immutable
data class GlassShadow(
    val color: Color,
    val offsetX: Float,
    val offsetY: Float,
    val radius: Float,
    val dispersion: Float,
)

/** Predefined glass shadows. */
object GlassShadows {

    private val Black = Color(0f, 0f, 0f, 0.05f)

/** Low-elevation surface shadow. */
    @Stable
    val Low: GlassShadow = GlassShadow(Black, 0f, 0f, 44f, 0.5f)

/** Standard panel shadow. */
    @Stable
    val Regular: GlassShadow = GlassShadow(Black, 0f, 2f, 64f, 0.5f)

/** Elevated surface shadow. */
    @Stable
    val High: GlassShadow = GlassShadow(Black, 0f, 4f, 80f, 0.5f)

/** Dialog shadow over a dimmed page. */
    @Stable
    val ExtraHigh: GlassShadow = GlassShadow(Black, 0f, 70f, 96f, 0.5f)

/** Floating control shadow. */
    @Stable
    val Float: GlassShadow = GlassShadow(Black, 0f, 0f, 24f, 0.5f)
}
