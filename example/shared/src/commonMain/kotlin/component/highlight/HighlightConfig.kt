// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.highlight

import top.yukonga.miuix.kmp.blur.highlight.Highlight

internal object HighlightConfig {

    enum class Container(val displayName: String) {
        Disabled("Disabled"),
        Large("Large Container"),
        Medium("Medium Container"),
        Small("Small Container"),
    }

    fun get(container: Container, isDark: Boolean): Highlight? = when (container) {
        Container.Disabled -> null
        Container.Large -> if (!isDark) Highlight.GlassStrokeBigLight else Highlight.GlassStrokeBigDark
        Container.Medium -> if (!isDark) Highlight.GlassStrokeMiddleLight else Highlight.GlassStrokeMiddleDark
        Container.Small -> if (!isDark) Highlight.GlassStrokeSmallLight else Highlight.GlassStrokeSmallDark
    }
}
