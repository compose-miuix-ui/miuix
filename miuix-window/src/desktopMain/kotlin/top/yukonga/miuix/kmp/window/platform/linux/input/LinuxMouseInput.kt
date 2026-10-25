// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.input

import top.yukonga.miuix.kmp.window.platform.linux.x11.LinuxXInputSession
import java.awt.Component
import java.awt.Window
import java.awt.event.InputEvent
import java.awt.event.MouseEvent
import kotlin.math.roundToInt

/**
 * Chooses one mouse source before window handlers and Compose receive input.
 * LinuxWindowInput validates window eligibility before passing native hover here.
 * XI2 supplies unpressed hover with device provenance.
 * AWT supplies buttons, wheel and grabbed drag.
 * If native hover is unavailable, the complete AWT path remains enabled independently of touch.
 */
internal class LinuxMouseInput(
    private val window: Window,
    private val canvas: Component,
) : AutoCloseable {
    private var awaitingNativeCapabilities = true
    private var nativeCapabilities = LinuxXInputSession.Capabilities(touch = false, hover = false)
    private val pressedButtons = mutableSetOf<Int>()
    private var suppressTouchMouse = false
    private val unregister = LinuxWindowMouseInput.registerSource(window, this)

    fun suppressTouchEmulation() {
        suppressTouchMouse = true
    }

    fun onNativeMouseRelease() {
        pressedButtons.remove(MouseEvent.BUTTON1)
    }

    fun awaitNativeCapabilities() {
        awaitingNativeCapabilities = true
    }

    fun updateNativeCapabilities(
        capabilities: LinuxXInputSession.Capabilities,
    ) {
        nativeCapabilities = capabilities
        awaitingNativeCapabilities = false
    }

    // During attachment/re-attachment, AWT cannot distinguish touch-generated crossings.
    // Keep them gated until registration resolves; explicit failure restores full AWT hover.
    private val nativeHoverSelected: Boolean
        get() = awaitingNativeCapabilities || nativeCapabilities.hover

    fun dispatchAwt(
        event: MouseEvent,
        deliver: (MouseEvent) -> Unit,
    ) {
        if (event.component !== canvas) {
            deliver(event)
            return
        }
        if (event.id == MouseEvent.MOUSE_PRESSED) {
            suppressTouchMouse = false
        } else if (suppressTouchMouse &&
            (
                event.id == MouseEvent.MOUSE_DRAGGED ||
                    event.id == MouseEvent.MOUSE_RELEASED ||
                    event.id == MouseEvent.MOUSE_CLICKED
                )
        ) {
            return
        }
        when (event.id) {
            MouseEvent.MOUSE_PRESSED -> {
                if (nativeHoverSelected && pressedButtons.isEmpty()) {
                    // AWT buttons can overtake XI2 hover. Resolve the real mouse hit target before
                    // its press, through the same window delivery path without queue re-entry.
                    deliver(
                        MouseEvent(
                            canvas,
                            MouseEvent.MOUSE_MOVED,
                            event.`when`,
                            event.modifiersEx and KEYBOARD_MODIFIERS_MASK,
                            event.x,
                            event.y,
                            event.xOnScreen,
                            event.yOnScreen,
                            0,
                            false,
                            MouseEvent.NOBUTTON,
                        ),
                    )
                }
                pressedButtons.add(event.button)
            }

            MouseEvent.MOUSE_RELEASED -> {
                // A release queued before hide/focus loss must not complete the cancelled press.
                if (!pressedButtons.remove(event.button)) return
            }

            MouseEvent.MOUSE_ENTERED,
            MouseEvent.MOUSE_EXITED,
            MouseEvent.MOUSE_MOVED,
            MouseEvent.MOUSE_DRAGGED,
            -> if (nativeHoverSelected && pressedButtons.isEmpty()) return
        }
        deliver(normalizeRelease(event))
    }

    private fun normalizeRelease(
        event: MouseEvent,
    ): MouseEvent {
        if (event.id != MouseEvent.MOUSE_RELEASED ||
            event.button == MouseEvent.NOBUTTON
        ) {
            return event
        }
        val releasedMask = InputEvent.getMaskForButton(event.button)
        if (event.modifiersEx and releasedMask == 0) return event
        // X11 compatibility touch can report the pre-release button mask. Compose derives
        // pressed state from modifiersEx, so the released button must be cleared explicitly.
        return MouseEvent(
            canvas,
            event.id,
            event.`when`,
            event.modifiersEx and releasedMask.inv(),
            event.x,
            event.y,
            event.xOnScreen,
            event.yOnScreen,
            event.clickCount,
            event.isPopupTrigger,
            event.button,
        )
    }

    fun onHover(
        event: LinuxXInputSession.Hover,
    ) {
        // All source selection lives here, including grab/ungrab crossings and emulated motion.
        // Without a touch grab, touch uses X11's compatibility mouse path and needs its hover too.
        val touchOwnsPointer = nativeCapabilities.touch && (event.directTouch || event.emulated)
        if (awaitingNativeCapabilities || !nativeCapabilities.hover || touchOwnsPointer ||
            event.crossingMode != 0 ||
            pressedButtons.isNotEmpty()
        ) {
            return
        }
        val transform = canvas.graphicsConfiguration.defaultTransform
        LinuxWindowMouseInput.dispatchToWindow(
            window,
            MouseEvent(
                canvas,
                when (event.phase) {
                    LinuxXInputSession.HoverPhase.Move -> MouseEvent.MOUSE_MOVED
                    LinuxXInputSession.HoverPhase.Enter -> MouseEvent.MOUSE_ENTERED
                    LinuxXInputSession.HoverPhase.Exit -> MouseEvent.MOUSE_EXITED
                },
                System.currentTimeMillis(),
                keyboardModifiers(event.modifiers),
                (event.x / transform.scaleX).roundToInt(),
                (event.y / transform.scaleY).roundToInt(),
                (event.rootX / transform.scaleX).roundToInt(),
                (event.rootY / transform.scaleY).roundToInt(),
                0,
                false,
                MouseEvent.NOBUTTON,
            ),
        )
    }

    fun cancel() {
        pressedButtons.clear()
    }

    override fun close() = unregister()

    companion object {
        private const val KEYBOARD_MODIFIERS_MASK = InputEvent.SHIFT_DOWN_MASK or
            InputEvent.CTRL_DOWN_MASK or InputEvent.ALT_DOWN_MASK or
            InputEvent.META_DOWN_MASK or InputEvent.ALT_GRAPH_DOWN_MASK

        private fun keyboardModifiers(
            state: Int,
        ): Int {
            var modifiers = 0
            if (state and 1 != 0) modifiers = modifiers or InputEvent.SHIFT_DOWN_MASK
            if (state and 4 != 0) modifiers = modifiers or InputEvent.CTRL_DOWN_MASK
            if (state and 8 != 0) modifiers = modifiers or InputEvent.ALT_DOWN_MASK
            if (state and 64 != 0) modifiers = modifiers or InputEvent.META_DOWN_MASK
            if (state and 128 != 0) modifiers = modifiers or InputEvent.ALT_GRAPH_DOWN_MASK
            return modifiers
        }
    }
}
