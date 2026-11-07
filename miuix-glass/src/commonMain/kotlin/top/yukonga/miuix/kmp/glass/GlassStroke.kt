// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

/**
 * Defines a light used to shade a glass bloom stroke.
 *
 * @property x Horizontal position in `[0, 1]`.
 * @property y Vertical position in `[0, 1]`.
 * @property z Signed depth; negative values place the light behind the surface.
 * @property color Light colour; its alpha controls intensity.
 */
@Immutable
data class GlassStrokeLight(
    val x: Float,
    val y: Float,
    val z: Float,
    val color: Color,
)

/**
 * Defines the lit edge along a glass silhouette.
 *
 * @property width Width of the flat stroke band, in dp.
 * @property bevel Depth of the shaded bevel, in dp.
 * @property color Flat stroke colour; its alpha controls strength.
 * @property primary First light direction.
 * @property secondary Second light direction, opposite [primary].
 */
@Immutable
data class GlassStroke(
    val width: Float,
    val bevel: Float,
    val color: Color,
    val primary: GlassStrokeLight,
    val secondary: GlassStrokeLight,
)

/** Predefined glass bloom strokes. */
object GlassStrokes {

    /** The big light bloom stroke. */
    @Stable
    val BigLight: GlassStroke = GlassStroke(
        width = 0.8f,
        bevel = 1.2f,
        color = Color(1f, 1f, 1f, 0.1f),
        primary = GlassStrokeLight(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.6f)),
        secondary = GlassStrokeLight(0.5f, 0.9f, -0.5f, Color(1f, 1f, 1f, 0.05f)),
    )

    /** The middle light bloom stroke. */
    @Stable
    val MiddleLight: GlassStroke = GlassStroke(
        width = 0.8f,
        bevel = 1.2f,
        color = Color(1f, 1f, 1f, 0.1f),
        primary = GlassStrokeLight(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.5f)),
        secondary = GlassStrokeLight(0.7f, 0.8f, 0f, Color(1f, 1f, 1f, 0.3f)),
    )

    /** The small light bloom stroke. */
    @Stable
    val SmallLight: GlassStroke = GlassStroke(
        width = 0.8f,
        bevel = 1.2f,
        color = Color(1f, 1f, 1f, 0.05f),
        primary = GlassStrokeLight(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.6f)),
        secondary = GlassStrokeLight(0.5f, 0.95f, -0.5f, Color(1f, 1f, 1f, 0.35f)),
    )

    /** The big dark bloom stroke. */
    @Stable
    val BigDark: GlassStroke = GlassStroke(
        width = 0.8f,
        bevel = 1.2f,
        color = Color(1f, 1f, 1f, 0.1f),
        primary = GlassStrokeLight(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.4f)),
        secondary = GlassStrokeLight(0.5f, 0.9f, -0.5f, Color(1f, 1f, 1f, 0.01f)),
    )

    /** The middle dark bloom stroke. */
    @Stable
    val MiddleDark: GlassStroke = GlassStroke(
        width = 0.8f,
        bevel = 1.2f,
        color = Color(1f, 1f, 1f, 0.1f),
        primary = GlassStrokeLight(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.4f)),
        secondary = GlassStrokeLight(0.7f, 0.8f, 0f, Color(1f, 1f, 1f, 0.2f)),
    )

    /** The small dark bloom stroke. */
    @Stable
    val SmallDark: GlassStroke = GlassStroke(
        width = 0.8f,
        bevel = 1.2f,
        color = Color(1f, 1f, 1f, 0.05f),
        primary = GlassStrokeLight(0.2f, 0.5f, 0f, Color(1f, 1f, 1f, 0.6f)),
        secondary = GlassStrokeLight(0.5f, 0.95f, -0.36f, Color(1f, 1f, 1f, 0.25f)),
    )

    /**
     * Picks the light or the dark half of a theme pair.
     *
     * @param isDark True to return [dark].
     * @param light The stroke for a light theme.
     * @param dark The stroke for a dark theme.
     */
    @Stable
    fun forTheme(
        isDark: Boolean,
        light: GlassStroke = MiddleLight,
        dark: GlassStroke = MiddleDark,
    ): GlassStroke = if (isDark) dark else light
}
