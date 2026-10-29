// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/** How a [GlassMaterial] colour layer is composited with the backdrop. */
enum class GlassColorBlendMode {

    /** The layer replaces the backdrop, in proportion to its own alpha. */
    SrcOver,

    /** Subtractive darkening: `backdrop - layerAlpha * (1 - layer)`, clamped at black. */
    PlusDarker,

    /** Additive lightening: `backdrop + layerAlpha * layer`, clamped at white. */
    PlusLighter,

/** Applies a soft-light dodge-and-burn blend without reaching pure black or white. */
    SoftLight,

    /** [Overlay] with the layer and the backdrop swapped. The layer decides the contrast. */
    HardLight,

    /** Multiplies dark backdrops and screens light ones. The backdrop decides the contrast. */
    Overlay,

    /** Keeps the backdrop's hue and saturation, and takes the layer's luminance. */
    Luminosity,

    /** Brightens the backdrop toward white, driven by the layer. */
    ColorDodge,

    /** Darkens the backdrop toward black, driven by the layer. */
    ColorBurn,
}

/**
 * A colour layer composited over the blurred backdrop.
 *
 * @property color Layer colour; its alpha controls blend strength.
 * @property mode Blend operation with content already below the layer.
 */
@Immutable
data class GlassColorLayer(
    val color: Color,
    val mode: GlassColorBlendMode,
)

/**
 * Backdrop blur and the ordered colour layers composited over it.
 *
 * @property blurRadius Backdrop blur radius. It overrides the blur requested by [GlassStyle].
 * @property first Layer nearest the backdrop. Always present.
 * @property second Layer over [first]. `null` ends the layer stack.
 * @property third Layer over [second]. `null` ends the layer stack.
 */
@Immutable
data class GlassMaterial(
    val blurRadius: Dp,
    val first: GlassColorLayer,
    val second: GlassColorLayer? = null,
    val third: GlassColorLayer? = null,
) {
    init {
        require(second != null || third == null) {
            "A GlassMaterial cannot carry a third colour layer without a second"
        }
    }
}
