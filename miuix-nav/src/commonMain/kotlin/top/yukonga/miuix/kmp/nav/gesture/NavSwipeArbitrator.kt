// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import kotlin.math.abs

/** Terminal ownership and the single-move child confirmation phase for one pointer sequence. */
internal enum class NavSwipeArbitrationPhase {
    Possible,
    AwaitingChildConfirmation,
    ChildOwned,
    NavigationOwned,
    Cancelled,
}

/**
 * The action the pointer adapter should take after one arbitration input.
 *
 * Kept as an enum because [onPositionChange] runs on the pointer hot path. Claim metadata is exposed
 * through [initialDismissTravelPx], avoiding an allocation for every observed movement.
 */
internal enum class NavSwipeArbitrationOutcome {
    Continue,
    YieldToChild,
    ClaimNavigation,
    Cancelled,
}

/**
 * Pure child-first ownership arbiter for swipe dismissal engagement.
 *
 * Child ownership evidence and navigation intent are deliberately independent:
 *
 * - any first consumed, non-zero position change starts a one-position confirmation window;
 * - another consumed position change confirms [NavSwipeArbitrationPhase.ChildOwned], regardless of
 *   navigation touch slop;
 * - an unconsumed confirmation change releases that provisional evidence, after which navigation
 *   may claim only if cumulative dismiss-direction travel has crossed [touchSlop] and dominates the
 *   cross axis.
 *
 * Compose consumption is attached to the whole pointer change rather than an individual axis, so
 * repeated consumption on either axis is conservatively treated as a child recognizer taking
 * ownership. Once an owner is terminal, later inputs can never transfer it.
 *
 * @param touchSlop Navigation's activation dead zone in pixels. It never gates child ownership.
 */
internal class NavSwipeArbitrator(
    private val touchSlop: Float,
) {
    init {
        require(touchSlop >= 0f && touchSlop.isFinite()) { "touchSlop must be finite and non-negative" }
    }

    var phase: NavSwipeArbitrationPhase = NavSwipeArbitrationPhase.Possible
        private set

    var initialDismissTravelPx: Float = 0f
        private set

    private var towardTravelPx = 0f
    private var crossTravelPx = 0f

    /**
     * Observes one non-platform position sample.
     *
     * [towardDeltaPx] is already projected so positive values point toward dismissal.
     * [crossDeltaPx] remains signed so it represents displacement from DOWN rather than path length.
     */
    fun onPositionChange(
        towardDeltaPx: Float,
        crossDeltaPx: Float,
        consumed: Boolean,
    ): NavSwipeArbitrationOutcome {
        terminalOutcome()?.let { return it }

        if (towardDeltaPx == 0f && crossDeltaPx == 0f) {
            return NavSwipeArbitrationOutcome.Continue
        }

        towardTravelPx += towardDeltaPx
        crossTravelPx += crossDeltaPx

        if (isContentDirection()) return lockChildOwnership()

        return when (phase) {
            NavSwipeArbitrationPhase.Possible -> {
                if (consumed) {
                    phase = NavSwipeArbitrationPhase.AwaitingChildConfirmation
                    NavSwipeArbitrationOutcome.Continue
                } else {
                    claimNavigationIfEligible()
                }
            }

            NavSwipeArbitrationPhase.AwaitingChildConfirmation -> {
                if (consumed) {
                    lockChildOwnership()
                } else {
                    phase = NavSwipeArbitrationPhase.Possible
                    claimNavigationIfEligible()
                }
            }

            NavSwipeArbitrationPhase.ChildOwned,
            NavSwipeArbitrationPhase.NavigationOwned,
            NavSwipeArbitrationPhase.Cancelled,
            -> error("terminal phases return before movement processing")
        }
    }

    /** Ends an unresolved pointer sequence without granting navigation ownership. */
    fun onEnd(): NavSwipeArbitrationOutcome {
        terminalOutcome()?.let { return it }
        phase = NavSwipeArbitrationPhase.Cancelled
        return NavSwipeArbitrationOutcome.Cancelled
    }

    /** Cancels an unresolved candidate, for example when another pointer joins before engagement. */
    fun cancel(): NavSwipeArbitrationOutcome {
        terminalOutcome()?.let { return it }
        phase = NavSwipeArbitrationPhase.Cancelled
        return NavSwipeArbitrationOutcome.Cancelled
    }

    private fun isContentDirection(): Boolean = towardTravelPx < -touchSlop ||
        (abs(crossTravelPx) > touchSlop && abs(crossTravelPx) > abs(towardTravelPx))

    private fun claimNavigationIfEligible(): NavSwipeArbitrationOutcome {
        if (towardTravelPx <= touchSlop || towardTravelPx < abs(crossTravelPx)) {
            return NavSwipeArbitrationOutcome.Continue
        }
        initialDismissTravelPx = (towardTravelPx - touchSlop).coerceAtLeast(0f)
        phase = NavSwipeArbitrationPhase.NavigationOwned
        return NavSwipeArbitrationOutcome.ClaimNavigation
    }

    private fun lockChildOwnership(): NavSwipeArbitrationOutcome {
        phase = NavSwipeArbitrationPhase.ChildOwned
        return NavSwipeArbitrationOutcome.YieldToChild
    }

    private fun terminalOutcome(): NavSwipeArbitrationOutcome? = when (phase) {
        NavSwipeArbitrationPhase.ChildOwned -> NavSwipeArbitrationOutcome.YieldToChild

        NavSwipeArbitrationPhase.NavigationOwned -> NavSwipeArbitrationOutcome.ClaimNavigation

        NavSwipeArbitrationPhase.Cancelled -> NavSwipeArbitrationOutcome.Cancelled

        NavSwipeArbitrationPhase.Possible,
        NavSwipeArbitrationPhase.AwaitingChildConfirmation,
        -> null
    }
}
