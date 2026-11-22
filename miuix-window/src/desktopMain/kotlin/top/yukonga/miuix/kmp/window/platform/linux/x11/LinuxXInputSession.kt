// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.x11

import com.sun.jna.Memory
import com.sun.jna.Native
import com.sun.jna.NativeLong
import com.sun.jna.platform.unix.X11
import com.sun.jna.ptr.IntByReference
import javax.swing.SwingUtilities
import kotlin.concurrent.thread

/**
 * One XI2 connection. The reader exclusively owns its display, subscriptions, grabs and cookies.
 * Listeners receive copied samples on the EDT; no native structure escapes the reader.
 * requestStop invalidates queued deliveries immediately. onClosed runs only after XCloseDisplay,
 * so the owner can keep a stopping session alive without blocking the EDT or overlapping grabs.
 */
internal class LinuxXInputSession(
    private val handle: Long,
    private val windowHandle: Long,
    private val requestTouch: Boolean,
    private val listener: Listener,
) {
    interface Listener {
        fun onReady(
            capabilities: Capabilities,
        )

        fun onTouch(
            event: Touch,
            canMoveNatively: Boolean,
        ): TouchAction

        fun onTouchMove(
            id: Long,
        )

        fun onMouseMoveEnd()

        fun onHover(
            event: Hover,
        )

        fun onDevicesChanged()

        fun onClosed()
    }

    data class Capabilities(
        val touch: Boolean,
        val hover: Boolean,
    )

    enum class TouchPhase { Down, Move, Up }

    enum class TouchAction { Accept, Reject, Pending, Move }

    // Only regular XI2 caption contacts remain undecided. Passive-grab contacts are
    // accepted immediately, so closing the session cannot replay them as AWT clicks.
    private val pendingTouches = mutableMapOf<Long, Long>()
    private var handedOffTouch: Touch? = null

    enum class HoverPhase { Move, Enter, Exit }

    enum class State { Starting, Active, Stopping, Closed }

    data class Touch(
        val id: Long,
        val phase: TouchPhase,
        val x: Double,
        val y: Double,
        val rootX: Double,
        val rootY: Double,
        val modifiers: Int,
        val timeMillis: Long,
        val x11Timestamp: Long,
        val directTouch: Boolean,
    )

    data class Hover(
        val phase: HoverPhase,
        val x: Double,
        val y: Double,
        val rootX: Double,
        val rootY: Double,
        val modifiers: Int,
        val directTouch: Boolean,
        val emulated: Boolean,
        val crossingMode: Int = 0,
    )

    // Lifecycle state is EDT-confined; stop and touch cancellation signals cross into the reader.
    var state = State.Starting
        private set

    @Volatile
    private var stopRequested = false

    @Volatile
    private var touchEpoch = 0L

    // The reader updates only a still-queued Move from the same input stream. The EDT takes
    // its latest sample before calling listeners, without holding the producer lock in Compose.
    private class HoverMove(
        val source: Int,
        var sample: Hover,
    )

    private val hoverLock = Any()
    private var pendingHoverMove: HoverMove? = null

    fun start() {
        check(SwingUtilities.isEventDispatchThread() && state == State.Starting)
        thread(name = "SaltUI-XI2-$handle", isDaemon = true) {
            try {
                if (!stopRequested) readConnection()
            } catch (exception: Exception) {
                logUnavailable(exception)
            } catch (error: LinkageError) {
                logUnavailable(error)
            } finally {
                stopRequested = true
                SwingUtilities.invokeLater {
                    state = State.Closed
                    listener.onClosed()
                }
            }
        }
    }

    fun requestStop() {
        check(SwingUtilities.isEventDispatchThread())
        if (state == State.Closed) return
        state = State.Stopping
        stopRequested = true
        sealHoverMove()
    }

    fun invalidateTouches() {
        check(SwingUtilities.isEventDispatchThread())
        touchEpoch++
    }

    private fun readConnection() {
        val x11 = LinuxX11.INSTANCE
        val xi = LinuxXInput2.instance
        val display = x11.XOpenDisplay(null) ?: return
        var awtTouch: AutoCloseable? = null
        try {
            val opcode = IntByReference()
            if (!x11.XQueryExtension(
                    display,
                    "XInputExtension",
                    opcode,
                    IntByReference(),
                    IntByReference(),
                )
            ) {
                return
            }
            val major = IntByReference(2)
            val minor = IntByReference(2)
            if (xi.XIQueryVersion(display, major, minor) != 0 || major.value < 2) return
            val xid = X11.Window(handle)
            val supportsTouchVersion = major.value > 2 || minor.value >= 2
            if (!selectRootEvents(display, supportsTouchVersion)) return
            val supportsTouch = requestTouch && supportsTouchVersion
            if (supportsTouch && LinuxX11WindowMover.supportsTouchMove(
                    display,
                    X11.Window(windowHandle),
                )
            ) {
                awtTouch = LinuxAwtInput.suspendTouch(handle)
            }
            // Qt rejects a regular touch selection. A passive grab would replay into an
            // AWT core grab instead, preventing the WM from receiving subsequent motion.
            val regularTouch = awtTouch != null
            val hover = selectWindowEvents(display, xid, regularTouch)
            val touch = if (regularTouch) hover else supportsTouch && grabTouches(display, xid)
            if (!touch && !hover) return
            x11.XFlush(display)
            val directDevices = queryDirectDevices(display)
            dispatchAndWait {
                if (!stopRequested) {
                    state = State.Active
                    listener.onReady(Capabilities(touch, hover))
                }
            }
            readEvents(
                display = display,
                xid = xid,
                opcode = opcode.value,
                capabilities = Capabilities(touch, hover),
                initialDirectDevices = directDevices,
                canMoveTouch = regularTouch,
            )
        } finally {
            // This removes every registration and grab, including partial initialization.
            x11.XCloseDisplay(display)
            awtTouch?.close()
        }
    }

    private fun grabTouches(
        display: X11.Display,
        xid: X11.Window,
    ): Boolean = try {
        val modifiers = LinuxXInput2.GrabModifiers()
        val result = LinuxXInput2.instance.XIGrabTouchBegin(
            display,
            LinuxXInput2.ALL_MASTER_DEVICES,
            xid,
            0,
            LinuxXInput2.EventMask(
                LinuxXInput2.ALL_MASTER_DEVICES,
                LinuxXInput2.TOUCH_BEGIN,
                LinuxXInput2.TOUCH_UPDATE,
                LinuxXInput2.TOUCH_END,
            ),
            1,
            modifiers,
        )
        result == 0 && modifiers.status == 0
    } catch (error: LinkageError) {
        // A client libXi without touch-grab support can still provide XI2 hover.
        System.getLogger(LinuxXInputSession::class.java.name).log(
            System.Logger.Level.WARNING,
            "XI2 touch grab unavailable",
            error,
        )
        false
    }

    private fun selectRootEvents(
        display: X11.Display,
        touch: Boolean,
    ): Boolean {
        val events = mutableListOf(
            LinuxXInput2.DEVICE_CHANGED,
            LinuxXInput2.HIERARCHY_CHANGED,
            LinuxXInput2.RAW_BUTTON_RELEASE,
        )
        if (touch) events += LinuxXInput2.RAW_TOUCH_END
        return LinuxXInput2.instance.XISelectEvents(
            display = display,
            window = LinuxX11.INSTANCE.XDefaultRootWindow(display),
            masks = LinuxXInput2.EventMask(LinuxXInput2.ALL_DEVICES, *events.toIntArray()),
            count = 1,
        ) == 0
    }

    private fun selectWindowEvents(
        display: X11.Display,
        xid: X11.Window,
        touch: Boolean,
    ): Boolean {
        val events = mutableListOf(
            LinuxXInput2.MOTION,
            LinuxXInput2.ENTER,
            LinuxXInput2.LEAVE,
        )
        if (touch) {
            events += listOf(
                LinuxXInput2.TOUCH_BEGIN,
                LinuxXInput2.TOUCH_UPDATE,
                LinuxXInput2.TOUCH_END,
            )
        }
        return LinuxXInput2.instance.XISelectEvents(
            display,
            xid,
            LinuxXInput2.EventMask(LinuxXInput2.ALL_MASTER_DEVICES, *events.toIntArray()),
            1,
        ) == 0
    }

    private fun queryDirectDevices(
        display: X11.Display,
    ): Set<Int> {
        val xi = LinuxXInput2.instance
        val count = IntByReference()
        val data = xi.XIQueryDevice(display, LinuxXInput2.ALL_DEVICES, count)
            ?: error("Cannot query XI2 input devices")
        try {
            val size = LinuxXInput2.DeviceInfo(data).size()
            return (0 until count.value).mapNotNull { index ->
                val info = LinuxXInput2.DeviceInfo(data.share(index.toLong() * size))
                info.device.takeIf { info.isDirectTouch }
            }.toSet()
        } finally {
            xi.XIFreeDeviceInfo(data)
        }
    }

    private fun readEvents(
        display: X11.Display,
        xid: X11.Window,
        opcode: Int,
        capabilities: Capabilities,
        initialDirectDevices: Set<Int>,
        canMoveTouch: Boolean,
    ) {
        val x11 = LinuxX11.INSTANCE
        val event = X11.XEvent()
        var directDevices = initialDirectDevices
        Memory(8).use { fd ->
            fd.clear()
            fd.setInt(0, x11.XConnectionNumber(display))
            fd.setShort(4, 1) // POLLIN
            while (!stopRequested) {
                pendingTouches.entries.removeAll { it.value != touchEpoch }
                if (x11.XPending(display) == 0) {
                    pollEvents(fd)
                    continue
                }
                x11.XNextEvent(display, event)
                if (event.type != 35) continue // GenericEvent
                val cookie = LinuxX11.EventCookie(event.pointer)
                if (cookie.extension != opcode || x11.XGetEventData(display, cookie) == 0) continue
                try {
                    when (cookie.eventType) {
                        LinuxXInput2.DEVICE_CHANGED -> {
                            val changed = LinuxXInput2.DeviceChangedEvent(
                                requireNotNull(cookie.data),
                            )
                            // A slave switch only changes which slave drives a master, e.g. on
                            // every mouse/touch handoff. Sources are slaves and keep their classes.
                            if (changed.reason != LinuxXInput2.SLAVE_SWITCH) {
                                directDevices = refreshDevices(display)
                            }
                        }

                        LinuxXInput2.HIERARCHY_CHANGED -> directDevices = refreshDevices(display)

                        LinuxXInput2.RAW_BUTTON_RELEASE -> {
                            // dix emulates touch pointer events only at delivery, so touches
                            // never yield raw button events; no direct-device filter is needed.
                            val raw = LinuxXInput2.RawEvent(requireNotNull(cookie.data))
                            if (raw.detail == 1) dispatch { listener.onMouseMoveEnd() }
                        }

                        LinuxXInput2.RAW_TOUCH_END -> onRawTouchEnd(
                            LinuxXInput2.RawEvent(requireNotNull(cookie.data)),
                        )

                        LinuxXInput2.TOUCH_BEGIN,
                        LinuxXInput2.TOUCH_UPDATE,
                        LinuxXInput2.TOUCH_END,
                        -> if (capabilities.touch) {
                            val native = LinuxXInput2.DeviceEvent(requireNotNull(cookie.data))
                            onTouch(
                                display = display,
                                xid = xid,
                                native = native,
                                directTouch = native.source in directDevices,
                                canMoveTouch = canMoveTouch,
                            )
                        }

                        LinuxXInput2.MOTION -> if (capabilities.hover) {
                            val native = LinuxXInput2.DeviceEvent(requireNotNull(cookie.data))
                            val sample = Hover(
                                phase = HoverPhase.Move,
                                x = native.eventX,
                                y = native.eventY,
                                rootX = native.rootX,
                                rootY = native.rootY,
                                modifiers = native.modifiers.effective,
                                directTouch = native.source in directDevices,
                                emulated = native.flags and LinuxXInput2.POINTER_EMULATED != 0,
                            )
                            dispatchHover(sample, native.source)
                        }

                        LinuxXInput2.ENTER, LinuxXInput2.LEAVE -> if (capabilities.hover) {
                            val native = LinuxXInput2.CrossingEvent(requireNotNull(cookie.data))
                            val sample = Hover(
                                phase = if (cookie.eventType == LinuxXInput2.ENTER) {
                                    HoverPhase.Enter
                                } else {
                                    HoverPhase.Exit
                                },
                                x = native.eventX,
                                y = native.eventY,
                                rootX = native.rootX,
                                rootY = native.rootY,
                                modifiers = native.modifiers.effective,
                                directTouch = native.source in directDevices,
                                emulated = false,
                                crossingMode = native.mode,
                            )
                            dispatchHover(sample, native.source)
                        }
                    }
                } finally {
                    x11.XFreeEventData(display, cookie)
                }
            }
        }
    }

    private fun refreshDevices(
        display: X11.Display,
    ): Set<Int> {
        val devices = queryDirectDevices(display)
        // Finish cancellation before a new press can be accepted.
        dispatchAndWait {
            if (!stopRequested) listener.onDevicesChanged()
        }
        return devices
    }

    private fun pollEvents(
        fd: Memory,
    ) {
        // Bounded stop latency, with no EDT join that could deadlock touch acceptance.
        val result = LinuxTouchPoll.instance.poll(fd, NativeLong(1), 100)
        if (result < 0) {
            if (Native.getLastError() == 4) return // EINTR
            error("XI2 connection polling failed")
        }
        if (result > 0 && fd.getShort(6).toInt() and 0x38 != 0) {
            error("XI2 connection closed while polling")
        }
    }

    private fun onRawTouchEnd(
        raw: LinuxXInput2.RawEvent,
    ) {
        val id = (raw.source.toLong() shl 32) or (raw.detail.toLong() and 0xffffffffL)
        val active = handedOffTouch ?: return
        if (active.id != id) return
        handedOffTouch = null
        dispatch { listener.onTouch(active.copy(phase = TouchPhase.Up), canMoveNatively = false) }
    }

    private fun onTouch(
        display: X11.Display,
        xid: X11.Window,
        native: LinuxXInput2.DeviceEvent,
        directTouch: Boolean,
        canMoveTouch: Boolean,
    ) {
        val epoch = touchEpoch
        val sample = touchSample(native, directTouch)
        // RejectTouch produces a synthetic end; retain capture until the physical raw end.
        if (sample.phase == TouchPhase.Up && handedOffTouch?.id == sample.id) return
        val pending = sample.id in pendingTouches
        if (sample.phase != TouchPhase.Down && !pending) {
            dispatch { if (epoch == touchEpoch) listener.onTouch(sample, canMoveNatively = false) }
            return
        }
        val canHandoff = canMoveTouch && directTouch && sample.phase != TouchPhase.Up &&
            native.flags and LinuxXInput2.TOUCH_EMULATING_POINTER != 0 &&
            native.flags and LinuxXInput2.TOUCH_PENDING_END == 0
        val action = decideTouch(
            sample,
            canHandoff,
            epoch,
            pending,
        )
        if (action == TouchAction.Pending && canHandoff) {
            pendingTouches[sample.id] = epoch
            return
        }
        pendingTouches.remove(sample.id)
        if (!canMoveTouch || action == TouchAction.Move || action == TouchAction.Reject) {
            allowTouch(
                display,
                xid,
                native,
                action,
            )
        }
        if (action == TouchAction.Move) {
            handoffTouch(
                display,
                sample,
                epoch,
            )
        } else {
            LinuxX11.INSTANCE.XFlush(display)
        }
    }

    private fun decideTouch(
        sample: Touch,
        canHandoff: Boolean,
        epoch: Long,
        pending: Boolean,
    ): TouchAction {
        var action = TouchAction.Accept
        dispatchAndWait {
            if (!stopRequested && epoch == touchEpoch) action = listener.onTouch(sample, canHandoff)
        }
        // A cancelled pending contact must not replay as an AWT click. Rejecting a new press
        // is different: it lets AWT modality handle the contact.
        return if (stopRequested || epoch != touchEpoch ||
            (pending && action == TouchAction.Reject)
        ) {
            TouchAction.Accept
        } else {
            action
        }
    }

    private fun allowTouch(
        display: X11.Display,
        xid: X11.Window,
        native: LinuxXInput2.DeviceEvent,
        action: TouchAction,
    ) {
        val reject = action == TouchAction.Reject || action == TouchAction.Move
        LinuxXInput2.instance.XIAllowTouchEvents(
            display,
            native.device,
            native.detail,
            xid,
            if (reject) LinuxXInput2.REJECT_TOUCH else LinuxXInput2.ACCEPT_TOUCH,
        )
    }

    private fun handoffTouch(
        display: X11.Display,
        sample: Touch,
        epoch: Long,
    ) {
        handedOffTouch = sample
        LinuxX11.INSTANCE.XUngrabPointer(display, NativeLong(0))
        // Complete rejection before the prepared WM request starts on its own connection.
        LinuxX11.INSTANCE.XSync(display, false)
        dispatchAndWait {
            if (!stopRequested && epoch == touchEpoch) listener.onTouchMove(sample.id)
        }
    }

    private fun touchSample(
        native: LinuxXInput2.DeviceEvent,
        directTouch: Boolean,
    ) = Touch(
        id = (native.source.toLong() shl 32) or (native.detail.toLong() and 0xffffffffL),
        phase = when (native.eventType) {
            LinuxXInput2.TOUCH_BEGIN -> TouchPhase.Down
            LinuxXInput2.TOUCH_END -> TouchPhase.Up
            else -> TouchPhase.Move
        },
        x = native.eventX,
        y = native.eventY,
        rootX = native.rootX,
        rootY = native.rootY,
        modifiers = native.modifiers.effective,
        timeMillis = System.nanoTime() / 1_000_000,
        x11Timestamp = native.time.toLong() and 0xffffffffL,
        directTouch = directTouch,
    )

    private fun dispatchHover(
        sample: Hover,
        source: Int,
    ) {
        if (sample.phase != HoverPhase.Move) {
            dispatch { listener.onHover(sample) }
            return
        }
        val delivery = synchronized(hoverLock) {
            val pending = pendingHoverMove
            val previous = pending?.sample
            val sameStream = pending?.source == source &&
                previous?.modifiers == sample.modifiers &&
                previous.directTouch == sample.directTouch &&
                previous.emulated == sample.emulated
            if (pending != null && sameStream) {
                pending.sample = sample
                null
            } else {
                HoverMove(source, sample).also { pendingHoverMove = it }
            }
        } ?: return
        post {
            val latest = synchronized(hoverLock) {
                if (pendingHoverMove === delivery) pendingHoverMove = null
                delivery.sample
            }
            listener.onHover(latest)
        }
    }

    private fun sealHoverMove() = synchronized(hoverLock) {
        // Keep the already-queued sample, but prevent later Move events crossing this boundary.
        pendingHoverMove = null
    }

    private fun dispatchAndWait(
        action: () -> Unit,
    ) {
        sealHoverMove()
        SwingUtilities.invokeAndWait { action() }
    }

    private fun dispatch(
        action: () -> Unit,
    ) {
        sealHoverMove()
        post(action)
    }

    private fun post(
        action: () -> Unit,
    ) {
        SwingUtilities.invokeLater {
            if (!stopRequested) {
                try {
                    action()
                } catch (exception: Throwable) {
                    System.getLogger("MiuixWindow").log(
                        System.Logger.Level.WARNING,
                        "Uncaught exception in XI2 event dispatch",
                        exception,
                    )
                }
            }
        }
    }

    private fun logUnavailable(
        exception: Throwable,
    ) {
        System.getLogger(LinuxXInputSession::class.java.name).log(
            System.Logger.Level.WARNING,
            "Native XI2 input stopped; restoring AWT input",
            exception,
        )
    }
}
