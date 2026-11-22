// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.input

import androidx.compose.runtime.State
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.platform.ViewConfiguration
import top.yukonga.miuix.kmp.window.WindowResizeEdge
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxTouchTapRecognizer
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.CaptionHit
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Command
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Input
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Phase
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Pointer
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.LinuxWindowGestureState.Position
import top.yukonga.miuix.kmp.window.platform.linux.input.gesture.WindowGestureEffects
import top.yukonga.miuix.kmp.window.platform.linux.window.LinuxCaptionBarHitTest
import top.yukonga.miuix.kmp.window.platform.linux.window.UndecoratedWindowResizer
import java.awt.Window
import java.awt.event.MouseEvent

internal val LocalLinuxWindowGestures = staticCompositionLocalOf<LinuxWindowGestures?> { null }

/**
 * Adapts AWT/Compose input to the window's single gesture state machine and executes its commands.
 * The platform adapter owns only dispatch-scoped Compose hit queries, never gesture ownership.
 */
internal class LinuxWindowGestures(
    private val window: Window,
    private val caption: LinuxCaptionBarHitTest,
    private val resizer: UndecoratedWindowResizer,
    private val captionHovered: State<Boolean>,
    private val effects: WindowGestureEffects,
) : LinuxWindowMouseInput.Handler {
    enum class TouchResult { Handled, PendingNativeMove, PreparedNativeMove }

    private val state = LinuxWindowGestureState()
    private var captionQuery: CaptionQuery? = null

    fun configure(
        moveable: Boolean,
        resizable: Boolean,
        canDrag: Boolean,
        canMaximize: Boolean,
        viewConfiguration: ViewConfiguration,
    ) {
        execute(
            state.configure(
                LinuxWindowGestureState.Configuration(
                    moveable = moveable,
                    resizable = resizable,
                    canDrag = canDrag,
                    canMaximize = canMaximize,
                    taps = LinuxTouchTapRecognizer.Configuration(
                        touchSlop = viewConfiguration.touchSlop,
                        longPressTimeoutMillis = viewConfiguration.longPressTimeoutMillis,
                        doubleTapMinTimeMillis = viewConfiguration.doubleTapMinTimeMillis,
                        doubleTapTimeoutMillis = viewConfiguration.doubleTapTimeoutMillis,
                    ),
                ),
            ),
        )
    }

    fun install(): () -> Unit {
        val uninstallMover = effects.mover.install()
        val unregister = LinuxWindowMouseInput.register(
            window = window,
            handler = this,
            priority = 1,
        )
        return {
            unregister()
            uninstallMover()
        }
    }

    fun onCaptionPress(
        event: LinuxTouchEvent,
    ): Boolean {
        val query = captionQuery ?: return false
        if (query.dispatch.event !== event) return false
        query.hit = true
        return true
    }

    fun dispatchTouch(
        event: LinuxTouchEvent,
        dispatchToCompose: () -> Unit,
        cancelCompose: () -> Unit,
        canMoveNatively: Boolean = false,
    ): TouchResult {
        val phase = when (event.type) {
            PointerEventType.Press -> Phase.Press
            PointerEventType.Move -> Phase.Move
            PointerEventType.Release -> Phase.Release
            else -> return TouchResult.Handled
        }
        val scale = window.graphicsConfiguration.defaultTransform
        val input = Input(
            pointer = Pointer.Touch(event.id),
            phase = phase,
            position = Position(event.screenPosition.x, event.screenPosition.y),
            timeMillis = event.timeMillis,
            scaleX = scale.scaleX,
            scaleY = scale.scaleY,
        )
        val hit = if (phase == Phase.Press) {
            hitAt(event.contentPosition)
        } else {
            LinuxWindowGestureState.Hit()
        }
        try {
            val dispatch = TouchDispatch(
                event = event,
                deliver = dispatchToCompose,
                cancel = cancelCompose,
                canMoveNatively = canMoveNatively,
            )
            val result = execute(state.onInput(input, hit), dispatch)
            return if (canMoveNatively && state.isPendingTouchMove(event.id)) {
                TouchResult.PendingNativeMove
            } else {
                result
            }
        } catch (exception: Throwable) {
            cancel()
            cancelCompose()
            throw exception
        }
    }

    override fun onMouseEvent(
        event: MouseEvent,
    ): Boolean {
        val phase = when (event.id) {
            MouseEvent.MOUSE_MOVED, MouseEvent.MOUSE_ENTERED -> Phase.Enter
            MouseEvent.MOUSE_EXITED -> Phase.Exit
            MouseEvent.MOUSE_PRESSED -> Phase.Press
            MouseEvent.MOUSE_DRAGGED -> Phase.Move
            MouseEvent.MOUSE_RELEASED -> Phase.Release
            MouseEvent.MOUSE_CLICKED -> Phase.Click
            else -> return false
        }
        val position = LinuxWindowMouseInput.positionInContent(event, window)
        val hit = if (phase == Phase.Press) {
            val target = hitAt(position)
            if (target.caption == CaptionHit.ResolveInCompose) {
                // Enter and press can arrive before recomposition updates the hover state.
                target.copy(
                    caption = if (captionHovered.value) CaptionHit.Caption else CaptionHit.Content,
                )
            } else {
                target
            }
        } else {
            LinuxWindowGestureState.Hit(edge = resizer.edgeAt(position))
        }
        val decision = state.onInput(
            Input(
                pointer = Pointer.Mouse,
                phase = phase,
                position = Position(event.xOnScreen, event.yOnScreen),
                primary = event.button == MouseEvent.BUTTON1,
                clickCount = event.clickCount,
            ),
            hit,
        )
        execute(decision)
        return decision.consumed
    }

    override fun afterMouseEvent(
        event: MouseEvent,
    ) = resizer.afterMouseEvent(event)

    override fun cancel() {
        captionQuery = null
        execute(state.cancel())
    }

    fun endNativeMouseMove(): Boolean {
        if (!effects.mover.isNativeMouseMove) return false
        cancel()
        return true
    }

    fun releaseNativeMouseMove(): Boolean {
        // The WM still ends the drag on release; only its end notification is lost.
        if (!effects.mover.releaseNativeMouseMove()) return false
        cancel()
        return true
    }

    fun resetTouchInput() {
        captionQuery = null
        execute(state.resetTouchInput())
    }

    fun commitNativeTouchMove(
        id: Long,
    ) {
        // XI2 has already rejected the contact; retain quarantine until its physical release.
        if (!effects.mover.commitTouchMove(id)) cancel()
    }

    private fun hitAt(
        position: Offset,
    ): LinuxWindowGestureState.Hit {
        val edge = resizer.edgeAt(position)
        if (edge != WindowResizeEdge.None) return LinuxWindowGestureState.Hit(edge = edge)
        val target = when (caption.hitTest(position, window)) {
            LinuxCaptionBarHitTest.Result.Caption -> CaptionHit.Caption
            LinuxCaptionBarHitTest.Result.Excluded -> CaptionHit.Content
            LinuxCaptionBarHitTest.Result.RequiresComposeHitTest -> CaptionHit.ResolveInCompose
        }
        return LinuxWindowGestureState.Hit(caption = target)
    }

    private fun execute(
        decision: LinuxWindowGestureState.Decision,
        touch: TouchDispatch? = null,
    ): TouchResult {
        var result = TouchResult.Handled
        for (command in decision.commands) {
            when (command) {
                Command.DeliverTouch -> checkNotNull(touch).deliver()

                Command.CancelCompose -> checkNotNull(touch).cancel()

                is Command.ResolveCaption -> {
                    result = resolveCaption(command.request, checkNotNull(touch))
                }

                is Command.StartMove -> {
                    result = startMove(command, touch)
                }

                is Command.Move -> effects.mover.update(command.position)

                Command.EndMove -> effects.mover.finish()

                Command.ToggleMaximize -> effects.mover.toggleMaximize()

                is Command.StartResize -> effects.resizer.start(command.edge, command.position)

                is Command.Resize -> effects.resizer.update(command.position)

                is Command.EndResize -> effects.resizer.finish(command.applyPending)

                is Command.ShowEdge -> effects.resizer.showEdge(command.edge)

                is Command.EnableResize -> effects.resizer.setEnabled(command.enabled)
            }
        }
        return result
    }

    private fun startMove(
        command: Command.StartMove,
        touch: TouchDispatch?,
    ): TouchResult {
        if (touch?.canMoveNatively == true &&
            effects.mover.prepareTouchMove(
                id = touch.event.id,
                start = command.start,
                current = command.current,
            )
        ) {
            return TouchResult.PreparedNativeMove
        }
        effects.mover.start(
            pointer = command.pointer,
            start = command.start,
            current = command.current,
        )
        return TouchResult.Handled
    }

    private fun resolveCaption(
        request: Long,
        dispatch: TouchDispatch,
    ): TouchResult {
        val query = CaptionQuery(dispatch)
        captionQuery = query
        try {
            dispatch.deliver()
        } finally {
            if (captionQuery === query) captionQuery = null
        }
        return execute(state.resolveCaption(request, query.hit), dispatch)
    }

    private class CaptionQuery(
        val dispatch: TouchDispatch,
        var hit: Boolean = false,
    )

    private class TouchDispatch(
        val event: LinuxTouchEvent,
        val deliver: () -> Unit,
        val cancel: () -> Unit,
        val canMoveNatively: Boolean,
    )
}
