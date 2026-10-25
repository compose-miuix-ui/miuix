// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.input

import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers
import androidx.compose.ui.unit.IntOffset
import top.yukonga.miuix.kmp.window.platform.input.DesktopTouchScene
import top.yukonga.miuix.kmp.window.platform.input.TouchPointerState
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.WindowGestureEffects
import top.yukonga.miuix.kmp.window.platform.linux.x11.LinuxXInputSession
import java.awt.Component
import kotlin.math.roundToInt

/**
 * EDT-confined adaptation from native contact samples to Compose and window gestures.
 * Window eligibility, native connections and mouse routing belong to LinuxWindowInput.
 */
@OptIn(InternalComposeUiApi::class)
internal class LinuxTouchInput(
    private val canvas: Component,
    private val target: DesktopTouchScene,
    private val gestures: LinuxWindowGestures,
    private val focus: WindowGestureEffects.Focus,
) {
    private val pointers = TouchPointerState()

    fun dispatch(
        sample: LinuxXInputSession.Touch,
        canMoveNatively: Boolean,
    ): LinuxWindowGestures.TouchResult? {
        if (!sample.directTouch) return null
        if (target.isDisposed) {
            cancel()
            return null
        }
        val type = when (sample.phase) {
            LinuxXInputSession.TouchPhase.Down -> PointerEventType.Press
            LinuxXInputSession.TouchPhase.Move -> PointerEventType.Move
            LinuxXInputSession.TouchPhase.Up -> PointerEventType.Release
        }
        val position = Offset(sample.x.toFloat(), sample.y.toFloat())
        val transform = canvas.graphicsConfiguration.defaultTransform
        val touchEvent = LinuxTouchEvent(
            id = sample.id,
            type = type,
            screenPosition = IntOffset(
                (sample.rootX / transform.scaleX).roundToInt(),
                (sample.rootY / transform.scaleY).roundToInt(),
            ),
            contentPosition = position,
            timeMillis = sample.timeMillis,
        )
        if (type == PointerEventType.Press) {
            focus.onPress(sample.x11Timestamp)
        }
        return gestures.dispatchTouch(
            event = touchEvent,
            dispatchToCompose = {
                // XI coordinates include system scale; extraDisplayScale only affects density.
                pointers.update(
                    id = sample.id,
                    position = target.positionInScene(canvas, position),
                    eventType = type,
                )?.let { snapshot ->
                    target.scene.sendPointerEvent(
                        eventType = type,
                        pointers = snapshot,
                        keyboardModifiers = PointerKeyboardModifiers(
                            isShiftPressed = sample.modifiers and 1 != 0,
                            isCtrlPressed = sample.modifiers and 4 != 0,
                            isAltPressed = sample.modifiers and 8 != 0,
                            isMetaPressed = sample.modifiers and 64 != 0,
                        ),
                        timeMillis = sample.timeMillis,
                        nativeEvent = touchEvent,
                    )
                }
            },
            cancelCompose = ::cancelCompose,
            canMoveNatively = canMoveNatively,
        )
    }

    fun cancel() {
        gestures.resetTouchInput()
        cancelCompose()
    }

    private fun cancelCompose() {
        if (pointers.clear() && !target.isDisposed) target.scene.cancelPointerInput()
    }
}
