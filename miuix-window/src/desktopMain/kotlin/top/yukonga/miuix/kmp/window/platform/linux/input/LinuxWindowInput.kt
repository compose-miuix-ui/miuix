// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.input

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.platform.LocalViewConfiguration
import com.sun.jna.Native
import top.yukonga.miuix.kmp.window.internal.findSkiaLayer
import top.yukonga.miuix.kmp.window.platform.input.DesktopTouchScene
import top.yukonga.miuix.kmp.window.platform.linux.window.LinuxWindowFocus
import top.yukonga.miuix.kmp.window.platform.linux.window.LinuxWindowModality
import top.yukonga.miuix.kmp.window.platform.linux.x11.LinuxXInputSession
import java.awt.Component
import java.awt.Window
import java.awt.event.HierarchyEvent
import java.awt.event.HierarchyListener
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

@Composable
internal fun LinuxWindowInputEffect(
    window: Window,
    gestures: LinuxWindowGestures,
    moveable: Boolean,
    resizable: Boolean,
    canDrag: Boolean = true,
    canMaximize: Boolean = false,
) {
    val viewConfiguration = LocalViewConfiguration.current
    SideEffect {
        gestures.configure(
            moveable = moveable,
            resizable = resizable,
            canDrag = canDrag,
            canMaximize = canMaximize,
            viewConfiguration = viewConfiguration,
        )
    }
    DisposableEffect(window, gestures) {
        val uninstallGestures = gestures.install()
        val focus = LinuxWindowFocus.install(window)
        val input = LinuxWindowInput.create(
            window = window,
            gestures = gestures,
            focus = focus,
        )
        onDispose {
            input?.close()
            focus?.close()
            uninstallGestures()
        }
    }
}

/**
 * Attaches the native session to the canvas lifetime and connects its independent input channels.
 * Both channels pass AWT's window eligibility before reaching gestures or Compose.
 * A stopping session retains its slot until the reader confirms that native resources are closed.
 */
@OptIn(InternalComposeUiApi::class)
internal class LinuxWindowInput private constructor(
    private val window: Window,
    private val canvas: Component,
    private val gestures: LinuxWindowGestures,
    private val target: DesktopTouchScene?,
    private val touch: LinuxTouchInput?,
    private val mouse: LinuxMouseInput,
) : AutoCloseable,
    LinuxXInputSession.Listener {
    private var session: LinuxXInputSession? = null
    private var restartAfterClose = false
    private var closed = false
    private var nativeInputEligible = true

    private val windowListener = object : WindowAdapter() {
        override fun windowLostFocus(
            event: WindowEvent,
        ) = cancelInput()

        override fun windowIconified(
            event: WindowEvent,
        ) = cancelInput()
    }

    private val hierarchyListener = HierarchyListener { event ->
        if (event.changeFlags and (
                HierarchyEvent.SHOWING_CHANGED or HierarchyEvent.DISPLAYABILITY_CHANGED
                ).toLong() != 0L
        ) {
            if (canvas.isShowing) attach() else detach()
        }
    }

    init {
        window.addWindowFocusListener(windowListener)
        window.addWindowListener(windowListener)
        canvas.addHierarchyListener(hierarchyListener)
        attach()
    }

    private fun attach() {
        if (closed || !canvas.isShowing) return
        val existing = session
        if (existing != null) {
            restartAfterClose = existing.state == LinuxXInputSession.State.Stopping
            return
        }
        try {
            val handle = Native.getComponentID(canvas)
            if (handle == 0L) {
                mouse.updateNativeCapabilities(
                    LinuxXInputSession.Capabilities(
                        touch = false,
                        hover = false,
                    ),
                )
                return
            }
            mouse.awaitNativeCapabilities()
            session = LinuxXInputSession(
                handle = handle,
                windowHandle = Native.getComponentID(window),
                requestTouch = touch != null,
                listener = this,
            ).also { it.start() }
        } catch (exception: Exception) {
            mouse.updateNativeCapabilities(
                LinuxXInputSession.Capabilities(
                    touch = false,
                    hover = false,
                ),
            )
            logUnavailable(exception)
        } catch (error: LinkageError) {
            mouse.updateNativeCapabilities(
                LinuxXInputSession.Capabilities(
                    touch = false,
                    hover = false,
                ),
            )
            logUnavailable(error)
        }
    }

    private fun detach() {
        restartAfterClose = false
        cancelInput()
        session?.requestStop()
    }

    private fun cancelTouch() {
        session?.invalidateTouches()
        touch?.cancel()
    }

    private fun cancelInput() {
        gestures.cancel()
        cancelTouch()
        mouse.cancel()
        // Window lifetime cancels every Compose pointer source, including AWT compatibility input.
        if (target != null && !target.isDisposed) target.scene.cancelPointerInput()
    }

    override fun onReady(
        capabilities: LinuxXInputSession.Capabilities,
    ) {
        mouse.updateNativeCapabilities(capabilities)
    }

    override fun onTouch(
        event: LinuxXInputSession.Touch,
        canMoveNatively: Boolean,
    ): LinuxXInputSession.TouchAction {
        if (!acceptNativeInput()) return LinuxXInputSession.TouchAction.Reject
        return when (touch?.dispatch(event, canMoveNatively)) {
            null -> LinuxXInputSession.TouchAction.Reject
            LinuxWindowGestures.TouchResult.Handled -> LinuxXInputSession.TouchAction.Accept
            LinuxWindowGestures.TouchResult.PendingNativeMove -> LinuxXInputSession.TouchAction.Pending
            LinuxWindowGestures.TouchResult.PreparedNativeMove -> LinuxXInputSession.TouchAction.Move
        }
    }

    override fun onTouchMove(
        id: Long,
    ) {
        // Ownership was rejected even if the WM request fails. Suppress its AWT replay.
        mouse.suppressTouchEmulation()
        if (acceptNativeInput()) gestures.commitNativeTouchMove(id)
    }

    override fun onMouseMoveEnd() {
        if (gestures.endNativeMouseMove()) mouse.onNativeMouseRelease()
    }

    override fun onHover(
        event: LinuxXInputSession.Hover,
    ) {
        if (acceptNativeInput()) mouse.onHover(event)
    }

    private fun acceptNativeInput(): Boolean {
        // XI2 callbacks run on the EDT but bypass AWT's modal event filter.
        val eligible = !closed && canvas.isShowing && window.isEnabled &&
            !LinuxWindowModality.isBlocked(window)
        // Cancel on the eligible -> blocked edge only, not for every hover sample while blocked.
        // Native state can only begin from an eligible sample; AWT filters its own input.
        if (!eligible && nativeInputEligible) cancelInput()
        nativeInputEligible = eligible
        return eligible
    }

    override fun onDevicesChanged() {
        cancelTouch()
    }

    override fun onClosed() {
        // The display, grabs and registrations are gone before compatibility input is restored.
        session = null
        mouse.updateNativeCapabilities(
            LinuxXInputSession.Capabilities(
                touch = false,
                hover = false,
            ),
        )
        touch?.cancel()
        // A failed reader can no longer report raw button release. Requested stops already
        // cancelled input; leave unrelated AWT and Compose input running.
        if (gestures.releaseNativeMouseMove()) mouse.onNativeMouseRelease()
        if (closed) {
            mouse.close()
        } else if (restartAfterClose) {
            restartAfterClose = false
            attach()
        }
    }

    override fun close() {
        if (closed) return
        closed = true
        restartAfterClose = false
        window.removeWindowFocusListener(windowListener)
        window.removeWindowListener(windowListener)
        canvas.removeHierarchyListener(hierarchyListener)
        cancelInput()
        val existing = session
        if (existing != null) existing.requestStop() else mouse.close()
    }

    companion object {
        private fun logUnavailable(
            exception: Throwable,
        ) {
            System.getLogger(LinuxWindowInput::class.java.name).log(
                System.Logger.Level.WARNING,
                "Cannot attach native XI2 input; keeping AWT input",
                exception,
            )
        }

        fun create(
            window: Window,
            gestures: LinuxWindowGestures,
            focus: LinuxWindowFocus?,
        ): LinuxWindowInput? {
            val canvas = window.findSkiaLayer()?.canvas ?: return null
            val target = DesktopTouchScene.create(window)
            val touch = target?.let {
                LinuxTouchInput(
                    canvas = canvas,
                    target = target,
                    gestures = gestures,
                    focus = { timestamp ->
                        canvas.requestFocusInWindow()
                        focus?.onPointerPress(canvas, timestamp)
                    },
                )
            }
            // Hover remains usable even when Compose's private scene adapter is unavailable.
            return LinuxWindowInput(
                window = window,
                canvas = canvas,
                gestures = gestures,
                target = target,
                touch = touch,
                mouse = LinuxMouseInput(window, canvas),
            )
        }
    }
}
