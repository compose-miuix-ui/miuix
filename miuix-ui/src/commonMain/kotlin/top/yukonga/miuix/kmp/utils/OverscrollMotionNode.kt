// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.ui.node.TraversableNode

/** Exposes a live rebound to an ancestor pager without sharing motion across pages. */
internal interface OverscrollMotionNode : TraversableNode {
    val isVerticallyRebounding: Boolean
    val isHorizontallyRebounding: Boolean

    override val traverseKey: Any get() = Key

    companion object {
        val Key = Any()
    }
}
