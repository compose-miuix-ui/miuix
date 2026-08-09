// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.gesture

import kotlin.test.Test
import kotlin.test.assertEquals

class NavSwipeArbitratorTest {
    @Test
    fun consumedChangesBelowSlopLockChildOwnership() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        assertEquals(NavSwipeArbitrationOutcome.Continue, arbiter.onPositionChange(1f, 0f, consumed = true))
        assertEquals(NavSwipeArbitrationPhase.AwaitingChildConfirmation, arbiter.phase)
        assertEquals(NavSwipeArbitrationOutcome.YieldToChild, arbiter.onPositionChange(1f, 0f, consumed = true))
        assertEquals(NavSwipeArbitrationPhase.ChildOwned, arbiter.phase)

        assertEquals(NavSwipeArbitrationOutcome.YieldToChild, arbiter.onPositionChange(100f, 0f, consumed = false))
        assertEquals(NavSwipeArbitrationPhase.ChildOwned, arbiter.phase)
    }

    @Test
    fun transientConsumptionBelowSlopCanLaterClaimNavigation() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        assertEquals(NavSwipeArbitrationOutcome.Continue, arbiter.onPositionChange(1f, 0f, consumed = true))
        assertEquals(NavSwipeArbitrationOutcome.Continue, arbiter.onPositionChange(1f, 0f, consumed = false))
        assertEquals(NavSwipeArbitrationPhase.Possible, arbiter.phase)
        assertEquals(NavSwipeArbitrationOutcome.ClaimNavigation, arbiter.onPositionChange(20f, 0f, consumed = false))
        assertEquals(12f, arbiter.initialDismissTravelPx)
    }

    @Test
    fun zeroPositionChangesDoNotResolveConfirmationWindow() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        arbiter.onPositionChange(1f, 0f, consumed = true)
        assertEquals(NavSwipeArbitrationOutcome.Continue, arbiter.onPositionChange(0f, 0f, consumed = false))
        assertEquals(NavSwipeArbitrationPhase.AwaitingChildConfirmation, arbiter.phase)
        assertEquals(NavSwipeArbitrationOutcome.YieldToChild, arbiter.onPositionChange(1f, 0f, consumed = true))
    }

    @Test
    fun consumedChangePastSlopStillWaitsForChildConfirmation() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        assertEquals(NavSwipeArbitrationOutcome.Continue, arbiter.onPositionChange(20f, 0f, consumed = true))
        assertEquals(NavSwipeArbitrationPhase.AwaitingChildConfirmation, arbiter.phase)
        assertEquals(NavSwipeArbitrationOutcome.ClaimNavigation, arbiter.onPositionChange(1f, 0f, consumed = false))
        assertEquals(11f, arbiter.initialDismissTravelPx)
    }

    @Test
    fun twoConsumedChangesPastSlopLockChildOwnership() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        arbiter.onPositionChange(20f, 0f, consumed = true)
        assertEquals(NavSwipeArbitrationOutcome.YieldToChild, arbiter.onPositionChange(1f, 0f, consumed = true))
        assertEquals(NavSwipeArbitrationOutcome.YieldToChild, arbiter.onPositionChange(100f, 0f, consumed = false))
    }

    @Test
    fun oneConsumedChangeThenEndNeverClaimsNavigation() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        arbiter.onPositionChange(20f, 0f, consumed = true)
        assertEquals(NavSwipeArbitrationOutcome.Cancelled, arbiter.onEnd())
        assertEquals(NavSwipeArbitrationPhase.Cancelled, arbiter.phase)
    }

    @Test
    fun directUnconsumedTravelPastSlopClaimsNavigation() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        assertEquals(NavSwipeArbitrationOutcome.ClaimNavigation, arbiter.onPositionChange(11f, 0f, consumed = false))
        assertEquals(1f, arbiter.initialDismissTravelPx)
    }

    @Test
    fun travelEqualToSlopDoesNotClaimNavigation() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        assertEquals(NavSwipeArbitrationOutcome.Continue, arbiter.onPositionChange(10f, 0f, consumed = false))
        assertEquals(NavSwipeArbitrationOutcome.ClaimNavigation, arbiter.onPositionChange(0.5f, 0f, consumed = false))
        assertEquals(0.5f, arbiter.initialDismissTravelPx)
    }

    @Test
    fun crossAxisTravelPastSlopLocksChildOwnership() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        assertEquals(NavSwipeArbitrationOutcome.YieldToChild, arbiter.onPositionChange(1f, 11f, consumed = false))
        assertEquals(NavSwipeArbitrationPhase.ChildOwned, arbiter.phase)
    }

    @Test
    fun oppositeTravelPastSlopLocksChildOwnership() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        assertEquals(NavSwipeArbitrationOutcome.YieldToChild, arbiter.onPositionChange(-11f, 0f, consumed = false))
        assertEquals(NavSwipeArbitrationPhase.ChildOwned, arbiter.phase)
    }

    @Test
    fun repeatedConsumedCrossAxisChangesAlsoLockChildOwnership() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        assertEquals(NavSwipeArbitrationOutcome.Continue, arbiter.onPositionChange(0f, 1f, consumed = true))
        assertEquals(NavSwipeArbitrationOutcome.YieldToChild, arbiter.onPositionChange(0f, 1f, consumed = true))
        assertEquals(NavSwipeArbitrationPhase.ChildOwned, arbiter.phase)
    }

    @Test
    fun navigationOwnershipCannotTransferBackToChild() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        arbiter.onPositionChange(11f, 0f, consumed = false)
        assertEquals(NavSwipeArbitrationOutcome.ClaimNavigation, arbiter.onPositionChange(1f, 0f, consumed = true))
        assertEquals(NavSwipeArbitrationPhase.NavigationOwned, arbiter.phase)
    }

    @Test
    fun cancellationIsTerminal() {
        val arbiter = NavSwipeArbitrator(touchSlop = 10f)

        assertEquals(NavSwipeArbitrationOutcome.Cancelled, arbiter.cancel())
        assertEquals(NavSwipeArbitrationOutcome.Cancelled, arbiter.onPositionChange(100f, 0f, consumed = false))
        assertEquals(NavSwipeArbitrationPhase.Cancelled, arbiter.phase)
    }
}
