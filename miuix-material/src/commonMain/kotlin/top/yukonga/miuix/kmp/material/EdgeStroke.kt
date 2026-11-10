// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Directional light contributing to an [EdgeStroke].
 *
 * The shader normalizes `(x - 0.5, y - 0.7, z)` into the light direction, so `(0.5, 0.7)` is the
 * reference origin and contributes nothing; a negative [z] places the light behind the surface
 * plane and lights the inward-facing edge.
 *
 * @property color Its alpha scales the contribution.
 * @property intensity Multiplied with [color] alpha.
 */
@Immutable
data class EdgeLightSource(
    val x: Float,
    val y: Float,
    val z: Float,
    val color: Color = Color.White,
    @param:FloatRange(from = 0.0) val intensity: Float = 1f,
)

/**
 * How each light's rim contribution is gated — the term multiplied into the Lambert term before it
 * is squared, deciding which arc of the silhouette a light can reach.
 */
enum class EdgePeak {

    /** Fixed vertical gate: the primary owns the upper half of the rim, the secondary the lower. */
    Vertical,

    /** Gate follows each light's own XY direction, so the lit arc moves with the light. */
    Axial,

    /** `dot(n.xy, L.xy)²`: one light produces two peaks 180° apart. */
    Dual,
}

/**
 * Configures the lit edge drawn along a shape's silhouette: a flat stroke band plus a hemispheric
 * rim normal lit by two directional [EdgeLightSource]s.
 *
 * @property width Width of the flat stroke band hugging the outer edge.
 * @property bevel Inward halo depth, the reach of the rim normal.
 * @property color The flat stroke band; its alpha controls strength.
 * @property primary Light gated to the first arc of the rim; which arc depends on [peak].
 * @property secondary Light gated to the opposite arc of the rim.
 * @property blendMode How the stroke composites onto what is already drawn.
 * @property peak Which arc each light can reach.
 */
@Immutable
data class EdgeStroke(
    val width: Dp,
    val bevel: Dp,
    val color: Color,
    val primary: EdgeLightSource,
    val secondary: EdgeLightSource,
    val blendMode: BlendMode = BlendMode.Plus,
    val peak: EdgePeak = EdgePeak.Vertical,
)

/**
 * Edge strokes tuned for the bloom-style (OS3) material.
 *
 * Names read `{Big,Middle,Small}{Light,Dark}`: the size picks the halo depth, the suffix the theme.
 */
object EdgeStrokes {

    @Stable
    val BigLight: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 3.5.dp,
        color = Color.White.copy(alpha = 0.05f),
        primary = EdgeLightSource(0.5f, 0.5f, -0.5f, intensity = 0.3f),
        secondary = EdgeLightSource(0.5f, 0.6f, -0.5f, intensity = 0.2f),
        peak = EdgePeak.Axial,
    )

    @Stable
    val MiddleLight: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 2.8.dp,
        color = Color.White.copy(alpha = 0.05f),
        primary = EdgeLightSource(0.5f, 0.5f, -0.5f, intensity = 0.4f),
        secondary = EdgeLightSource(0.5f, 0.8f, -0.5f, intensity = 0.25f),
        peak = EdgePeak.Axial,
    )

    @Stable
    val SmallLight: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 2.6.dp,
        color = Color.White.copy(alpha = 0.05f),
        primary = EdgeLightSource(0.5f, 0.5f, -0.5f, intensity = 0.6f),
        secondary = EdgeLightSource(0.5f, 0.95f, -0.5f, intensity = 0.35f),
        peak = EdgePeak.Axial,
    )

    @Stable
    val BigDark: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 1.7.dp,
        color = Color.White.copy(alpha = 0.05f),
        primary = EdgeLightSource(0.5f, 0.5f, -0.5f, intensity = 0.4f),
        secondary = EdgeLightSource(0.5f, 0.6f, -0.5f, intensity = 0.25f),
        peak = EdgePeak.Axial,
    )

    @Stable
    val MiddleDark: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 2f.dp,
        color = Color.White.copy(alpha = 0.06f),
        primary = EdgeLightSource(0.5f, 0.5f, -0.5f, intensity = 0.5f),
        secondary = EdgeLightSource(0.5f, 0.8f, -0.5f, intensity = 0.25f),
        peak = EdgePeak.Axial,
    )

    @Stable
    val SmallDark: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 2.3.dp,
        color = Color.White.copy(alpha = 0.08f),
        primary = EdgeLightSource(0.5f, 0.5f, -0.5f, intensity = 0.6f),
        secondary = EdgeLightSource(0.5f, 0.95f, -0.36f, intensity = 0.25f),
        peak = EdgePeak.Axial,
    )

    /** Aliases [MiddleLight]. */
    @Stable
    val Default: EdgeStroke = MiddleLight
}

/** Edge strokes tuned for the glass material; same naming scheme as [EdgeStrokes]. */
object GlassEdgeStrokes {

    @Stable
    val BigLight: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 1.2.dp,
        color = Color(1f, 1f, 1f, 0.1f),
        primary = EdgeLightSource(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.6f)),
        secondary = EdgeLightSource(0.5f, 0.9f, -0.5f, Color(1f, 1f, 1f, 0.05f)),
    )

    @Stable
    val MiddleLight: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 1.2.dp,
        color = Color(1f, 1f, 1f, 0.1f),
        primary = EdgeLightSource(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.5f)),
        secondary = EdgeLightSource(0.7f, 0.8f, 0f, Color(1f, 1f, 1f, 0.3f)),
    )

    @Stable
    val SmallLight: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 1.2.dp,
        color = Color(1f, 1f, 1f, 0.05f),
        primary = EdgeLightSource(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.6f)),
        secondary = EdgeLightSource(0.5f, 0.95f, -0.5f, Color(1f, 1f, 1f, 0.35f)),
    )

    @Stable
    val BigDark: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 1.2.dp,
        color = Color(1f, 1f, 1f, 0.1f),
        primary = EdgeLightSource(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.4f)),
        secondary = EdgeLightSource(0.5f, 0.9f, -0.5f, Color(1f, 1f, 1f, 0.01f)),
    )

    @Stable
    val MiddleDark: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 1.2.dp,
        color = Color(1f, 1f, 1f, 0.1f),
        primary = EdgeLightSource(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.4f)),
        secondary = EdgeLightSource(0.7f, 0.8f, 0f, Color(1f, 1f, 1f, 0.2f)),
    )

    @Stable
    val SmallDark: EdgeStroke = EdgeStroke(
        width = 0.8.dp,
        bevel = 1.2.dp,
        color = Color(1f, 1f, 1f, 0.05f),
        primary = EdgeLightSource(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.6f)),
        secondary = EdgeLightSource(0.5f, 0.95f, -0.36f, Color(1f, 1f, 1f, 0.25f)),
    )

    /** Picks [light] or [dark] for the theme. */
    @Stable
    fun forTheme(
        isDark: Boolean,
        light: EdgeStroke = MiddleLight,
        dark: EdgeStroke = MiddleDark,
    ): EdgeStroke = if (isDark) dark else light
}
