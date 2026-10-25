// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerType
import com.sun.jna.Native
import top.yukonga.miuix.kmp.window.platform.input.TouchPointerState
import top.yukonga.miuix.kmp.window.platform.windows.structure.POINTER_INFO
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(InternalComposeUiApi::class)
class TouchPointerStateTest {
    @Test
    fun liftingOneFingerKeepsTheOtherPressedAndItsIdStable() {
        val state = TouchPointerState()
        val first = state.update(17, Offset(10f, 20f), PointerEventType.Press)!!.single()
        val both = state.update(42, Offset(30f, 40f), PointerEventType.Press)!!
        assertEquals(2, both.size)
        assertEquals(first.id, both.first().id)
        assertTrue(both.all { it.pressed && it.type == PointerType.Touch })

        val released = state.update(17, Offset(15f, 25f), PointerEventType.Release)!!
        assertEquals(2, released.size)
        assertFalse(released.first().pressed)
        assertEquals(Offset(15f, 25f), released.first().position)
        assertTrue(released.last().pressed)

        val remaining = state.update(42, Offset(50f, 60f), PointerEventType.Move)!!.single()
        assertEquals(both.last().id, remaining.id)
        assertEquals(Offset(50f, 60f), remaining.position)
        assertTrue(remaining.pressed)
        state.update(42, remaining.position, PointerEventType.Release)
        assertFalse(state.clear())
    }

    @Test
    fun cancellationDropsStaleUpdatesUntilANewPress() {
        val state = TouchPointerState()
        state.update(1, Offset.Zero, PointerEventType.Press)
        state.update(2, Offset.Zero, PointerEventType.Press)
        assertTrue(state.clear())
        assertFalse(state.clear())
        assertNull(state.update(1, Offset.Zero, PointerEventType.Move))
        assertNull(state.update(2, Offset.Zero, PointerEventType.Release))

        val next = state.update(1, Offset(3f, 4f), PointerEventType.Press)!!
        assertEquals(1, next.size)
        assertEquals(Offset(3f, 4f), next.single().position)
    }

    @Test
    fun alreadyQueuedSnapshotsDoNotChangeWithLaterInput() {
        val state = TouchPointerState()
        val down = state.update(1, Offset.Zero, PointerEventType.Press)!!
        state.update(1, Offset(2f, 3f), PointerEventType.Move)
        state.update(2, Offset.Zero, PointerEventType.Press)
        state.clear()
        assertEquals(1, down.size)
        assertEquals(Offset.Zero, down.single().position)
        assertTrue(down.single().pressed)
    }

    @Test
    fun pointerInfoMatchesTheWindowsAbi() {
        val info = POINTER_INFO()
        assertEquals(if (Native.POINTER_SIZE == 8) 96 else 88, info.size())
        info.pointer.setInt(0, 2)
        info.pointer.setInt(4, 65535)
        val positionOffset = 16L + 2 * Native.POINTER_SIZE
        info.pointer.setInt(positionOffset, -1280)
        info.pointer.setInt(positionOffset + 4, 720)
        info.read()
        assertEquals(2, info.pointerType)
        assertEquals(65535, info.pointerId)
        assertEquals(-1280, info.ptPixelLocation.x)
        assertEquals(720, info.ptPixelLocation.y)
    }
}
