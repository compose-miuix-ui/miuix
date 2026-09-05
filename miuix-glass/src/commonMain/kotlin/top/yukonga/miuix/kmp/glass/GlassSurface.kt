// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import top.yukonga.miuix.kmp.blur.Backdrop

/**
 * A sheet of glass, in the form the library's own components ask for their surface in.
 *
 * The components already know how to lay a menu out, stack a second one over it, dim the one behind
 * and morph between them. What they do not know is what their panel is made of, so they ask for it.
 * Handing them this is the whole of what makes them glass — there is no second implementation.
 *
 * ```
 * OverlayIconCascadingDropdownMenu(
 *     entries = entries,
 *     surface = glassSurface(backdrop, GlassPopupDefaults.visuals()),
 * ) { Icon(…) }
 * ```
 *
 * @param backdrop The [Backdrop] supplying the content behind the glass. `null` falls back to the
 *   flat fill in [visuals], which is what a device without runtime shaders gets.
 * @param visuals What the surface is made of.
 * @param cornerRadius Corner radius of the panel. It has to match the silhouette the component
 *   clips itself to, which is why it is named here rather than read off the shape handed in: that
 *   shape is a plain outline and carries no smoothing.
 */
fun glassSurface(
    backdrop: Backdrop?,
    visuals: GlassPopupVisuals,
    cornerRadius: Dp = GlassPopupDefaults.CornerRadius,
): @Composable (Shape) -> Modifier = {
    Modifier.glassPanel(
        backdrop = backdrop,
        shape = GlassShape(cornerRadius),
        style = visuals.style,
        alpha = visuals.alpha,
        material = visuals.material,
        stroke = visuals.stroke,
        shadow = visuals.shadow,
        shading = false,
        fallback = Modifier.background(visuals.containerColor),
    )
}
