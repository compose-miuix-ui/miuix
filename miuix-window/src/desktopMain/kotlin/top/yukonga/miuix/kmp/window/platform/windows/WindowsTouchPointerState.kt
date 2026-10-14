// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows

import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.scene.ComposeScenePointer

@OptIn(InternalComposeUiApi::class)
internal class WindowsTouchPointerState {
    private val pointers = linkedMapOf<PointerId, ComposeScenePointer>()

    fun update(
        id: Int,
        position: Offset,
        eventType: PointerEventType,
    ): List<ComposeScenePointer>? {
        val pointerId = PointerId(id.toLong())
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
