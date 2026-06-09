// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.runtime

import androidx.compose.animation.core.SpringSpec
import androidx.compose.runtime.Immutable

/**
 * Pure mapping from a predictive-back / edge-swipe gesture progress to the
 * `animatedTop` target value, per spec §7.2.
 *
 * The mapping is strictly linear (1:1 with the finger): a fully completed
 * gesture (`progress == 1`) drives `animatedTop` exactly one step toward the
 * previous entry, i.e. `topIndex - 1`. This linearity is the whole point of the
 * single-driver model — no easing lives on the `finger -> animatedTop` axis, so
 * no inverse-transform is ever needed (contrast nav3's SeekableTransitionState,
 * which bakes easing into its fraction).
 *
 * @param topIndex index of the current top entry in the back stack (`lastIndex`).
 * @param progress raw gesture progress; clamped to `0f..1f`.
 * @return the `animatedTop` value the gesture should snap to.
 */
internal fun fingerTarget(topIndex: Int, progress: Float): Float = topIndex - progress.coerceIn(0f, 1f)

/**
 * The single source of truth for the shared `animatedTop` spring (spec §9
 * "single shared spring"). Bundles the spring parameters, the release commit/cancel
 * thresholds, the shared [Default] instance, and the [spring] factory consumed by
 * [settleTo] — so no other phase ever redefines spring or threshold constants.
 *
 * Holds no lambdas and is never mutated, so it is safely [@Immutable]. The spring
 * fields feed [spring]/[settleTo]; the threshold fields feed [navBackCommitDecision].
 */
@Immutable
internal data class NavDriverSpec(
    val dampingRatio: Float = DAMPING_RATIO,
    val stiffness: Float = STIFFNESS,
    val visibilityThreshold: Float = VISIBILITY_THRESHOLD,
) {
    /**
     * Builds the [SpringSpec] for this driver. Single factory reused by [settleTo]
     * (and any future settle path), so the curve is identical everywhere. Calls the
     * top-level `spring` factory via its fully-qualified name to avoid colliding with
     * this same-named member function.
     */
    fun spring(): SpringSpec<Float> = androidx.compose.animation.core.spring(
        dampingRatio = dampingRatio,
        stiffness = stiffness,
        visibilityThreshold = visibilityThreshold,
    )

    companion object {
        /** Slightly under critical damping for a brisk, barely-overshooting settle. */
        const val DAMPING_RATIO: Float = 0.9f

        /** Medium-low stiffness: snappy yet not jittery on the depth scale (units = entries). */
        const val STIFFNESS: Float = 600f

        /**
         * `animatedTop` is measured in entry-index units, so it converges visually once
         * within a few hundredths of an index. Tighter than the default 0.01 for px.
         */
        const val VISIBILITY_THRESHOLD: Float = 0.0025f

        /**
         * Velocity (in progress-units per second) above which a release is treated as a
         * deliberate fling and commits/cancels by sign alone, ignoring position.
         */
        const val COMMIT_VELOCITY_THRESHOLD: Float = 1.0f

        /** Position fallback: progress at/after which a low-velocity release commits. */
        const val COMMIT_POSITION_THRESHOLD: Float = 0.5f

        /** Shared default instance to avoid per-frame allocation. */
        val Default: NavDriverSpec = NavDriverSpec()
    }
}

/**
 * Decides whether a released predictive-back / edge-swipe gesture should commit
 * (pop the top entry) or cancel (spring back), per spec §7.2 "velocity-first,
 * position-fallback".
 *
 * Velocity takes priority: a release flung hard enough in either direction wins
 * regardless of how far the finger travelled. Only when the release velocity sits
 * inside the dead zone (`|velocity| < velocityThreshold`) does position decide.
 *
 * @param progress gesture completion at release, `0f..1f` (0 = untouched, 1 = fully popped).
 * @param velocity release velocity in progress-units per second; positive points toward pop.
 * @param velocityThreshold magnitude above which velocity alone decides.
 * @param positionThreshold progress at/after which a low-velocity release commits.
 * @return `true` to commit (pop), `false` to cancel (spring back).
 */
internal fun navBackCommitDecision(
    progress: Float,
    velocity: Float,
    velocityThreshold: Float = NavDriverSpec.COMMIT_VELOCITY_THRESHOLD,
    positionThreshold: Float = NavDriverSpec.COMMIT_POSITION_THRESHOLD,
): Boolean = when {
    velocity >= velocityThreshold -> true
    velocity <= -velocityThreshold -> false
    else -> progress >= positionThreshold
}
