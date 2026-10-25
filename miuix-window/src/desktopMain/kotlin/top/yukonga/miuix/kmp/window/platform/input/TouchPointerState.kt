// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.input

import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.scene.ComposeScenePointer

/**
 * EDT-confined contact state. A release includes the lifted finger once, with pressed=false.
 * After cancellation, only a new press may introduce a contact again.
 */
@OptIn(InternalComposeUiApi::class)
internal class TouchPointerState {
    private val pointers = linkedMapOf<PointerId, ComposeScenePointer>()

    val isEmpty: Boolean
        get() = pointers.isEmpty()

    fun update(
        id: Long,
        position: Offset,
        eventType: PointerEventType,
    ): List<ComposeScenePointer>? {
        val pointerId = PointerId(id)
        if (eventType != PointerEventType.Press && pointerId !in pointers) return null
        pointers[pointerId] = ComposeScenePointer(
            id = pointerId,
            position = position,
            pressed = eventType != PointerEventType.Release,
            type = PointerType.Touch,
        )
        val snapshot = pointers.values.toList()
        if (eventType == PointerEventType.Release) pointers.remove(pointerId)
        return snapshot
    }

    fun clear(): Boolean {
        val hadPointers = pointers.isNotEmpty()
        pointers.clear()
        return hadPointers
    }
}
