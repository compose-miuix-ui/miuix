// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import top.yukonga.miuix.kmp.window.WindowResizeEdge
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxTouchTapRecognizer
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.CaptionHit
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Command
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Hit
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Input
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Phase
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Pointer
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Position
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LinuxWindowGestureStateTest {
    @Test
    fun mouseCaptionReleaseDuringTouchHoldFinishesMoveAndAllowsNextTouch() {
        val state = configuredState()
        state.onInput(mouse(Phase.Press), Hit(caption = CaptionHit.Caption))
        state.onInput(mouse(Phase.Move, 160))
        state.onInput(touch(1, Phase.Press))
        assertEquals(
            listOf(Command.Move(Position(170, 100))),
            state.onInput(mouse(Phase.Move, 170)).commands,
        )

        val released = state.onInput(mouse(Phase.Release, 180))
        assertTrue(released.consumed)
        assertTrue(Command.EndMove in released.commands, "Mouse release must finish its move")
        assertFalse(released.commands.any { it is Command.ShowEdge }, "No edge was ever indicated")
        assertTouchQuarantineEndsWithLastContact(state)
    }

    @Test
    fun mouseEdgeResizeReportsEachIndicatorTransitionOnce() {
        val state = configuredState()
        val right = Hit(edge = WindowResizeEdge.Right)
        val decisions = listOf(
            state.onInput(mouse(Phase.Enter), right),
            state.onInput(mouse(Phase.Press), right),
            state.onInput(mouse(Phase.Move, 160)),
            // The pointer is still over the edge after release.
            state.onInput(mouse(Phase.Release, 160), right),
            state.onInput(mouse(Phase.Exit)),
        )
        assertEquals(
            listOf(Command.ShowEdge(WindowResizeEdge.Right), Command.ShowEdge(WindowResizeEdge.None)),
            decisions.flatMap { it.commands }.filterIsInstance<Command.ShowEdge>(),
            "Starting and finishing a resize must not report edge -> None -> edge",
        )
        assertTrue(Command.EndResize(applyPending = true) in decisions[3].commands)
    }

    @Test
    fun mouseResizeReleaseDuringTouchHoldAppliesPendingBoundsAndAllowsNextTouch() {
        val state = configuredState()
        state.onInput(mouse(Phase.Press), Hit(edge = WindowResizeEdge.Right))
        state.onInput(mouse(Phase.Move, 160))
        state.onInput(touch(1, Phase.Press))
        assertEquals(
            listOf(Command.Resize(Position(170, 100))),
            state.onInput(mouse(Phase.Move, 170)).commands,
        )

        val released = state.onInput(mouse(Phase.Release, 180))
        assertTrue(released.consumed)
        assertTrue(
            Command.EndResize(applyPending = true) in released.commands,
            "The owning mouse release must commit its last resize position",
        )
        assertTrue(Command.ShowEdge(WindowResizeEdge.None) in released.commands)
        assertTouchQuarantineEndsWithLastContact(state)
    }

    @Test
    fun mouseReleaseCannotFinishATouchOwnedResize() {
        val state = configuredState()
        state.onInput(touch(1, Phase.Press), Hit(edge = WindowResizeEdge.Right))
        val mouseRelease = state.onInput(mouse(Phase.Release))
        assertTrue(mouseRelease.consumed)
        assertFalse(mouseRelease.commands.any { it is Command.EndResize })
        val touchRelease = state.onInput(touch(1, Phase.Release))
        assertTrue(Command.EndResize(applyPending = true) in touchRelease.commands)
    }

    @Test
    fun touchHoldRejectsNewMousePressAndConsumesItsClickWithoutRetainingSuppression() {
        val state = configuredState()
        state.onInput(mouse(Phase.Press), Hit(caption = CaptionHit.Caption))
        state.onInput(touch(1, Phase.Press))
        assertTrue(state.onInput(mouse(Phase.Press), Hit(edge = WindowResizeEdge.Right)).commands.isEmpty())
        val secondaryRelease = state.onInput(mouse(Phase.Release).copy(primary = false))
        assertTrue(secondaryRelease.consumed)
        assertTrue(secondaryRelease.commands.isEmpty())
        assertTrue(Command.EndMove in state.onInput(mouse(Phase.Release)).commands)

        val click = state.onInput(mouse(Phase.Click).copy(clickCount = 2))
        assertTrue(click.consumed)
        assertFalse(Command.ToggleMaximize in click.commands)
        assertTrue(state.onInput(mouse(Phase.Press), Hit(edge = WindowResizeEdge.Right)).commands.isEmpty())
        state.onInput(touch(1, Phase.Release))
        assertFalse(state.onInput(mouse(Phase.Click).copy(clickCount = 2)).consumed)
        assertEquals(listOf(Command.DeliverTouch), state.onInput(touch(4, Phase.Press)).commands)
        state.onInput(touch(4, Phase.Release))
    }

    @Test
    fun releasedCaptionQueryCannotResolveOrInvalidateTheNextQuery() {
        val state = configuredState()
        val oldRequest = requestCaption(state, 1)
        state.onInput(touch(1, Phase.Release))
        val currentRequest = requestCaption(state, 2)

        assertTrue(state.resolveCaption(oldRequest, true).commands.isEmpty())
        assertFalse(state.isPendingTouchMove(1))
        assertFalse(state.isPendingTouchMove(2))
        state.resolveCaption(currentRequest, true)
        assertTrue(state.isPendingTouchMove(2), "The fresh query must still be resolvable")
        state.resolveCaption(oldRequest, false)
        assertTrue(state.isPendingTouchMove(2), "A late miss must not cancel the new owner")
    }

    @Test
    fun cancelledCaptionQueryCannotRecaptureTheHeldContact() {
        val state = configuredState()
        val request = requestCaption(state, 1)
        state.cancel()
        assertTrue(state.resolveCaption(request, true).commands.isEmpty())
        assertFalse(state.isPendingTouchMove(1))
        assertFalse(Command.DeliverTouch in state.onInput(touch(1, Phase.Move)).commands)
        state.onInput(touch(1, Phase.Release))
        state.onInput(touch(2, Phase.Press), Hit(caption = CaptionHit.Caption))
        assertTrue(state.isPendingTouchMove(2), "Cancellation must not poison the next press")
    }

    @Test
    fun resetDropsOldContactsAndPendingQueryWithoutNeedingTheirRelease() {
        val state = configuredState()
        val oldRequest = requestCaption(state, 1)
        state.resetTouchInput()
        val newRequest = requestCaption(state, 2)
        assertTrue(state.resolveCaption(oldRequest, true).commands.isEmpty())
        state.resolveCaption(newRequest, true)
        assertTrue(state.isPendingTouchMove(2))
        state.onInput(touch(1, Phase.Release))
        assertTrue(state.isPendingTouchMove(2), "A stale release cannot finish the new gesture")
    }

    @Test
    fun disablingAndReenablingDragDoesNotReviveAnOldCaptionQuery() {
        val state = configuredState()
        val request = requestCaption(state, 1)
        state.configure(configuration().copy(canDrag = false))
        state.configure(configuration())
        assertTrue(state.resolveCaption(request, true).commands.isEmpty())
        assertFalse(state.isPendingTouchMove(1))
        state.onInput(touch(1, Phase.Release))
        state.onInput(touch(2, Phase.Press), Hit(caption = CaptionHit.Caption))
        assertTrue(state.isPendingTouchMove(2))
    }

    @Test
    fun resettingTouchDuringMouseDragPreservesMouseOwnership() {
        val state = configuredState()
        state.onInput(mouse(Phase.Press), Hit(caption = CaptionHit.Caption))
        state.onInput(mouse(Phase.Move, 160))
        state.onInput(touch(1, Phase.Press))
        assertFalse(Command.EndMove in state.resetTouchInput().commands)
        assertTrue(Command.Move(Position(180, 100)) in state.onInput(mouse(Phase.Move, 180)).commands)
        assertTrue(Command.EndMove in state.onInput(mouse(Phase.Release, 180)).commands)
        assertTrue(Command.DeliverTouch in state.onInput(touch(2, Phase.Press)).commands)
    }

    private fun requestCaption(
        state: LinuxWindowGestureState,
        id: Long,
    ): Long = state.onInput(touch(id, Phase.Press), Hit(caption = CaptionHit.ResolveInCompose))
        .commands.filterIsInstance<Command.ResolveCaption>().single().request

    private fun assertTouchQuarantineEndsWithLastContact(
        state: LinuxWindowGestureState,
    ) {
        // A second contact during the existing hold remains captured even after mouse release.
        assertFalse(Command.DeliverTouch in state.onInput(touch(2, Phase.Press)).commands)
        state.onInput(touch(1, Phase.Release))
        assertFalse(Command.DeliverTouch in state.onInput(touch(2, Phase.Move)).commands)
        state.onInput(touch(2, Phase.Release))
        assertEquals(listOf(Command.DeliverTouch), state.onInput(touch(3, Phase.Press)).commands)
        state.onInput(touch(3, Phase.Release))
    }

    private fun configuredState() = LinuxWindowGestureState().apply {
        configure(configuration())
    }

    private fun configuration() = LinuxWindowGestureState.Configuration(
        moveable = true,
        resizable = true,
        canDrag = true,
        canMaximize = true,
        taps = LinuxTouchTapRecognizer.Configuration(
            touchSlop = 8f,
            longPressTimeoutMillis = 500,
            doubleTapMinTimeMillis = 40,
            doubleTapTimeoutMillis = 300,
        ),
    )

    private fun mouse(
        phase: Phase,
        x: Int = 100,
    ) = Input(
        pointer = Pointer.Mouse,
        phase = phase,
        position = Position(x, 100),
    )

    private fun touch(
        id: Long,
        phase: Phase,
    ) = Input(
        pointer = Pointer.Touch(id),
        phase = phase,
        position = Position(300, 200),
    )
}
