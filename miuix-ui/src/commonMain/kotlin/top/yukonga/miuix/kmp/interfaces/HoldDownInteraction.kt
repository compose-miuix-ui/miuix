// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.interfaces

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.withContext
import top.yukonga.miuix.kmp.interfaces.HoldDownInteraction.HoldDown
import top.yukonga.miuix.kmp.interfaces.HoldDownInteraction.Release

/**
 * An interaction related to hold down events.
 *
 * @see HoldDown
 * @see Release
 */
interface HoldDownInteraction : Interaction {
    /**
     * An interaction representing a hold down event on a component.
     *
     * @see Release
     */
    class HoldDown : HoldDownInteraction

    /**
     * An interaction representing a [HoldDown] event being released on a component.
     *
     * @property holdDown the source [HoldDown] interaction that is being released
     *
     * @see HoldDown
     */
    class Release(
        val holdDown: HoldDown,
    ) : HoldDownInteraction
}

/**
 * Returns whether this source has any unreleased [HoldDown] interactions.
 */
@Composable
fun InteractionSource.collectIsHeldDownAsState(): State<Boolean> {
    val isHeldDown = remember(this) { mutableStateOf(false) }
    LaunchedEffect(this) {
        val holdInteraction = mutableListOf<HoldDown>()
        interactions.collect { interaction ->
            when (interaction) {
                is HoldDown -> holdInteraction.add(interaction)
                is Release -> holdInteraction.remove(interaction.holdDown)
            }
            isHeldDown.value = holdInteraction.isNotEmpty()
        }
    }
    return isHeldDown
}

/**
 * Mirrors [holdDownState] into [interactionSource], releasing any in-flight [HoldDown] on dispose.
 */
@Composable
internal fun HoldDownObserver(
    holdDownState: Boolean,
    interactionSource: MutableInteractionSource,
) {
    if (!holdDownState) return
    LaunchedEffect(interactionSource) {
        val hold = HoldDown()
        interactionSource.emit(hold)
        try {
            awaitCancellation()
        } finally {
            withContext(NonCancellable) { interactionSource.emit(Release(hold)) }
        }
    }
}
