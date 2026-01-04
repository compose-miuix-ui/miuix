// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Velocity
import kotlin.math.abs

private const val PRE_SCROLL_RESET_THRESHOLD = 1.0f
private const val POST_FLING_AVAILABLE_VELOCITY_THRESHOLD = 1.0f
private const val POST_FLING_CONSUMED_VELOCITY_THRESHOLD = 25.0f

/**
 * A [NestedScrollConnection] that provides haptic feedback when a scrollable container
 * is flung to its start or end boundaries.
 *
 * @param hapticFeedback The [HapticFeedback] instance to perform feedback.
 * @param hapticFeedbackType The type of haptic feedback to perform.
 */
private class ScrollEndHapticNode(
    private var hapticFeedbackType: HapticFeedbackType,
) : DelegatingNode(),
    CompositionLocalConsumerModifierNode,
    NestedScrollConnection {

    private val hapticFeedback: HapticFeedback
        get() = currentValueOf(LocalHapticFeedback)

    private enum class ScrollEndHapticState {
        /** Not scrolled to the boundary. */
        Idle,

        /** Scrolled to the top boundary. */
        TopBoundaryHit,

        /** Scrolled to the bottom boundary. */
        BottomBoundaryHit,
    }

    private var scrollEndHapticState = ScrollEndHapticState.Idle

    private fun Float.filter(tolerance: Float): Boolean = abs(this) < tolerance

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        if (!isAttached) return Offset.Zero
        // Reset state when scrolling from a boundary into content.
        if (scrollEndHapticState == ScrollEndHapticState.TopBoundaryHit && available.y < -PRE_SCROLL_RESET_THRESHOLD) {
            scrollEndHapticState = ScrollEndHapticState.Idle
        } else if (scrollEndHapticState == ScrollEndHapticState.BottomBoundaryHit && available.y > PRE_SCROLL_RESET_THRESHOLD) {
            scrollEndHapticState = ScrollEndHapticState.Idle
        }
        return Offset.Zero
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        if (!isAttached) return Velocity.Zero
        // Flinging beyond the top boundary.
        if (available.y > POST_FLING_AVAILABLE_VELOCITY_THRESHOLD && !consumed.y.filter(POST_FLING_CONSUMED_VELOCITY_THRESHOLD)) {
            if (scrollEndHapticState != ScrollEndHapticState.TopBoundaryHit) {
                hapticFeedback.performHapticFeedback(hapticFeedbackType)
                scrollEndHapticState = ScrollEndHapticState.TopBoundaryHit
            }
        }
        // Flinging beyond the bottom boundary.
        else if (available.y < -POST_FLING_AVAILABLE_VELOCITY_THRESHOLD && !consumed.y.filter(
                POST_FLING_CONSUMED_VELOCITY_THRESHOLD,
            )
        ) {
            if (scrollEndHapticState != ScrollEndHapticState.BottomBoundaryHit) {
                hapticFeedback.performHapticFeedback(hapticFeedbackType)
                scrollEndHapticState = ScrollEndHapticState.BottomBoundaryHit
            }
        }
        return Velocity.Zero
    }

    override fun onAttach() {
        delegate(nestedScrollModifierNode(this, null))
    }

    fun update(hapticFeedbackType: HapticFeedbackType) {
        this.hapticFeedbackType = hapticFeedbackType
    }
}

private data class ScrollEndHapticElement(
    private val hapticFeedbackType: HapticFeedbackType,
) : ModifierNodeElement<ScrollEndHapticNode>() {
    override fun create() = ScrollEndHapticNode(hapticFeedbackType)

    override fun update(node: ScrollEndHapticNode) {
        node.update(hapticFeedbackType)
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "scrollEndHaptic"
        properties["hapticFeedbackType"] = hapticFeedbackType
    }
}

/**
 * Applies a haptic feedback effect when a scrollable container is flung to its boundaries.
 *
 * @param hapticFeedbackType The type of haptic feedback to perform.
 */
fun Modifier.scrollEndHaptic(
    hapticFeedbackType: HapticFeedbackType = HapticFeedbackType.TextHandleMove,
): Modifier = this.then(ScrollEndHapticElement(hapticFeedbackType))
