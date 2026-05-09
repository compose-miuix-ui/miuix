// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.effect.highlight

import top.yukonga.miuix.kmp.blur.highlight.Highlight

/**
 * Configuration mapping for container-size-based highlight selection.
 *
 * Provides a simple enum-driven API for choosing the appropriate [Highlight] preset
 * based on the visual size category of a container and the current theme. This removes
 * the need for callers to manually map between size categories and the six built-in
 * highlight tokens ([Highlight.GlassStrokeBigLight], etc.).
 *
 * ### Usage
 *
 * ```kotlin
 * val highlight = HighlightConfig.get(
 *     container = HighlightConfig.Container.Medium,
 *     isDark = isSystemInDarkTheme(),
 * )
 * ```
 */
object HighlightConfig {

    /**
     * Container size categories for highlight selection.
     *
     * Each value maps to a specific [Highlight] preset optimized for that
     * container's visual weight and edge curvature.
     *
     * @property displayName Human-readable name for UI display (e.g., in dropdown menus).
     */
    enum class Container(val displayName: String) {
        /** Highlight disabled — returns `null` from [HighlightConfig.get]. */
        Disabled("Disabled"),

        /** Large container (e.g., full-width cards, dialogs). Uses the thickest, softest halo. */
        Large("Large Container"),

        /** Medium container (e.g., standard cards, sheets). Uses a balanced halo. */
        Medium("Medium Container"),

        /** Small container (e.g., chips, compact buttons). Uses the sharpest halo. */
        Small("Small Container"),
    }

    /**
     * Returns the appropriate [Highlight] for the given [container] size and theme.
     *
     * @param container The size category of the target container.
     * @param isDark `true` for dark theme, `false` for light theme.
     * @return A [Highlight] preset, or `null` when [container] is [Container.Disabled].
     */
    fun get(container: Container, isDark: Boolean): Highlight? = when (container) {
        Container.Disabled -> null
        Container.Large -> if (!isDark) Highlight.GlassStrokeBigLight else Highlight.GlassStrokeBigDark
        Container.Medium -> if (!isDark) Highlight.GlassStrokeMiddleLight else Highlight.GlassStrokeMiddleDark
        Container.Small -> if (!isDark) Highlight.GlassStrokeSmallLight else Highlight.GlassStrokeSmallDark
    }
}
