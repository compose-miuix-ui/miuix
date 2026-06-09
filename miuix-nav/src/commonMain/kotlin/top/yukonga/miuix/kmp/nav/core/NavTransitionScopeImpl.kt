// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import top.yukonga.miuix.kmp.nav.runtime.NavChange
import top.yukonga.miuix.kmp.nav.transition.NavGesture
import top.yukonga.miuix.kmp.nav.transition.NavRole
import top.yukonga.miuix.kmp.nav.transition.NavTransitionScope

/**
 * Concrete render-time carrier of [NavTransitionScope].
 *
 * The renderer constructs one of these per presented entry and passes it into the governing
 * transition's `Modifier.transformEntry(scope)`. [relativeDepth] is read inside a deferred
 * `graphicsLayer { }` block so that re-reading the driving float does not trigger recomposition.
 *
 * All fields are plain values; the type is [Stable] (it is a one-shot snapshot whose identity the
 * renderer recreates each layout pass, and the transition reads it synchronously).
 */
@Stable
internal class NavTransitionScopeImpl(
    override val relativeDepth: Float,
    override val role: NavRole,
    override val change: NavChange,
    override val gesture: NavGesture?,
    override val layoutSize: IntSize,
    override val layoutDirection: LayoutDirection,
    override val density: Density,
) : NavTransitionScope
