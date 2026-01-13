// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package androidx.navigation3.scene

import androidx.compose.runtime.Immutable
import androidx.navigationevent.NavigationEventHistory
import androidx.navigationevent.NavigationEventInfo

/**
 * Represents a snapshot of the visible destinations in a navigation container.
 *
 * This class provides the necessary context for building animations during navigation gestures,
 * like predictive back. It's a simple data holder that feeds into the [NavigationEventHistory].
 *
 * @property scene The scene whose state is used by the NavigationEvent
 */
@Immutable
class SceneInfo<T : Any>(val scene: Scene<T>) : NavigationEventInfo() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as SceneInfo<*>

        return scene == other.scene
    }

    override fun hashCode(): Int = scene.hashCode()

    override fun toString(): String = "SceneInfo(scene=$scene)"
}
