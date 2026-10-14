// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.macos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import java.awt.AWTEvent
import java.awt.Component
import java.awt.Toolkit
import java.awt.Window
import java.awt.event.AWTEventListener
import java.awt.event.MouseEvent

@Composable
internal fun MacOSCaptionBarDragHandler(
    onDrag: () -> Boolean,
    onDoubleClick: (() -> Unit)?,
    window: Window,
    captionBarHeightInWindowCoordinates: Float,
    isHitTestInCaptionBar: Boolean,
    canDrag: Boolean = true,
) {
    val currentOnDrag by rememberUpdatedState(onDrag)
    val currentOnDoubleClick by rememberUpdatedState(onDoubleClick)
    val currentCaptionBarHeight by rememberUpdatedState(captionBarHeightInWindowCoordinates)
    val currentIsHitTestInCaptionBar by rememberUpdatedState(isHitTestInCaptionBar)
    val currentCanDrag by rememberUpdatedState(canDrag)

    DisposableEffect(window) {
        val dragState = MacOSCaptionBarDragState()
        val mouseEventListener = AWTEventListener { awtEvent ->
            val event = awtEvent as? MouseEvent ?: return@AWTEventListener

            when (event.id) {
                MouseEvent.MOUSE_CLICKED -> {
                    val onDoubleClickAction = currentOnDoubleClick
                    if (
                        onDoubleClickAction != null &&
                        event.button == MouseEvent.BUTTON1 &&
                        event.clickCount == 2 &&
                        event.component.isInside(window) &&
                        currentIsHitTestInCaptionBar &&
                        currentCanDrag &&
                        isCaptionBarHit(event, currentCaptionBarHeight, window)
                    ) {
                        onDoubleClickAction()
                        event.consume()
                    }
                }

                MouseEvent.MOUSE_DRAGGED -> {
                    if (!event.component.isInside(window)) return@AWTEventListener
                    if (dragState.startPendingDrag(currentOnDrag)) {
                        event.consume()
                    }
                }

                MouseEvent.MOUSE_PRESSED -> {
                    val canStartDrag =
                        event.button == MouseEvent.BUTTON1 &&
                            event.component.isInside(window) &&
                            currentIsHitTestInCaptionBar &&
                            currentCanDrag &&
                            isCaptionBarHit(event, currentCaptionBarHeight, window)
                    dragState.onPress(canStartDrag)
                }

                MouseEvent.MOUSE_RELEASED -> {
                    dragState.onRelease()
                }
            }
        }

        Toolkit.getDefaultToolkit().addAWTEventListener(
            mouseEventListener,
            AWTEvent.MOUSE_EVENT_MASK or AWTEvent.MOUSE_MOTION_EVENT_MASK,
        )

        onDispose {
            Toolkit.getDefaultToolkit().removeAWTEventListener(mouseEventListener)
        }
    }
}

internal class MacOSCaptionBarDragState {
    private var dragPending = false

    fun onPress(canStartDrag: Boolean) {
        dragPending = canStartDrag
    }

    fun startPendingDrag(onDrag: () -> Boolean): Boolean {
        if (!dragPending) return false
        if (!onDrag()) return false
        dragPending = false
        return true
    }

    fun onRelease() {
        dragPending = false
    }
}

private fun isCaptionBarHit(
    event: MouseEvent,
    captionBarHeight: Float,
    window: Window,
): Boolean {
    val relativeY = event.yOnScreen - window.y
    return relativeY.toDouble() in 0.0..captionBarHeight.toDouble()
}

private fun Component.isInside(window: Window): Boolean {
    var component: Component? = this
    while (component != null) {
        if (component === window) return true
        component = component.parent
    }
    return false
}
