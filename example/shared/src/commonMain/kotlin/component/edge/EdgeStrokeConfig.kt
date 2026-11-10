// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component.edge

import top.yukonga.miuix.kmp.material.EdgeStroke
import top.yukonga.miuix.kmp.material.EdgeStrokes

internal object EdgeStrokeConfig {

    enum class Container(val displayName: String) {
        Disabled("Disabled"),
        Large("Large Container"),
        Medium("Medium Container"),
        Small("Small Container"),
    }

    fun get(container: Container, isDark: Boolean): EdgeStroke? = when (container) {
        Container.Disabled -> null
        Container.Large -> if (!isDark) EdgeStrokes.BigLight else EdgeStrokes.BigDark
        Container.Medium -> if (!isDark) EdgeStrokes.MiddleLight else EdgeStrokes.MiddleDark
        Container.Small -> if (!isDark) EdgeStrokes.SmallLight else EdgeStrokes.SmallDark
    }
}
