// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package navigation3

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Type-safe navigation keys for Navigation3.
 * Each destination is a NavKey (data object/data class) and can be saved/restored in the back stack.
 */
@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object Main : Route

    @Serializable
    data object PullToRefresh : Route

    @Serializable
    data object About : Route

    @Serializable
    data object License : Route

    @Serializable
    data class Navigation(val id: String) : Route

    @Serializable
    data object MultiScaffold : Route

    @Serializable
    data object OverscrollLoadMore : Route
}
