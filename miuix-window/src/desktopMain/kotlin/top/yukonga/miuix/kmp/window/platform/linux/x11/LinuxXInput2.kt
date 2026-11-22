// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("unused")

package top.yukonga.miuix.kmp.window.platform.linux.x11

import com.sun.jna.Library
import com.sun.jna.Memory
import com.sun.jna.Native
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.Structure
import com.sun.jna.platform.unix.X11
import com.sun.jna.ptr.IntByReference

/**
 * XI 2.2 touch bindings, matching XInput2.h. Only direct touch is accepted; touchpads
 * and pointer devices keep their existing AWT input path.
 */
@Suppress("FunctionName")
internal interface LinuxXInput2 : Library {
    fun XIQueryVersion(
        display: X11.Display,
        major: IntByReference,
        minor: IntByReference,
    ): Int

    fun XIQueryDevice(
        display: X11.Display,
        device: Int,
        count: IntByReference,
    ): Pointer?

    fun XIFreeDeviceInfo(
        info: Pointer,
    )

    fun XISelectEvents(
        display: X11.Display,
        window: X11.Window,
        masks: EventMask,
        count: Int,
    ): Int

    fun XIGetSelectedEvents(
        display: X11.Display,
        window: X11.Window,
        count: IntByReference,
    ): Pointer?

    fun XIGrabTouchBegin(
        display: X11.Display,
        device: Int,
        window: X11.Window,
        ownerEvents: Int,
        mask: EventMask,
        count: Int,
        modifiers: GrabModifiers,
    ): Int

    fun XIAllowTouchEvents(
        display: X11.Display,
        device: Int,
        touch: Int,
        window: X11.Window,
        mode: Int,
    ): Int

    @Structure.FieldOrder("device", "length", "mask")
    class EventMask : Structure {
        constructor(
            device: Int,
            vararg events: Int,
        ) : super() {
            this.device = device
            events.forEach { event ->
                val offset = (event / 8).toLong()
                mask.setByte(offset, (mask.getByte(offset).toInt() or (1 shl event % 8)).toByte())
            }
        }

        constructor(
            pointer: Pointer,
        ) : super(pointer) {
            read()
        }

        @JvmField
        var device: Int = 0

        @JvmField
        var length: Int = 4

        @JvmField
        var mask: Pointer = Memory(length.toLong()).apply {
            clear()
        }
    }

    @Structure.FieldOrder("modifiers", "status")
    class GrabModifiers : Structure() {
        @JvmField
        var modifiers: Int = Int.MIN_VALUE

        // XIAnyModifier
        @JvmField
        var status: Int = 0
    }

    @Structure.FieldOrder("device", "name", "use", "attachment", "enabled", "count", "classes")
    class DeviceInfo(
        pointer: Pointer,
    ) : Structure(pointer) {
        @JvmField
        var device: Int = 0

        @JvmField
        var name: Pointer? = null

        @JvmField
        var use: Int = 0

        @JvmField
        var attachment: Int = 0

        @JvmField
        var enabled: Int = 0

        @JvmField
        var count: Int = 0

        @JvmField
        var classes: Pointer? = null

        init {
            read()
        }

        val isDirectTouch: Boolean
            get() = enabled != 0 && (0 until count).any { index ->
                val info = classes?.getPointer(index.toLong() * Native.POINTER_SIZE)
                // XIAnyClassInfo.type; XITouchClassInfo.mode
                info != null && info.getInt(0) == 8 && info.getInt(8) == 1
            }
    }

    @Structure.FieldOrder("length", "mask")
    class ButtonState : Structure() {
        @JvmField
        var length: Int = 0

        @JvmField
        var mask: Pointer? = null
    }

    @Structure.FieldOrder("length", "mask", "values")
    class ValuatorState : Structure() {
        @JvmField
        var length: Int = 0

        @JvmField
        var mask: Pointer? = null

        @JvmField
        var values: Pointer? = null
    }

    @Structure.FieldOrder("base", "latched", "locked", "effective")
    class ModifierState : Structure() {
        @JvmField
        var base: Int = 0

        @JvmField
        var latched: Int = 0

        @JvmField
        var locked: Int = 0

        @JvmField
        var effective: Int = 0
    }

    @Structure.FieldOrder(
        "type",
        "serial",
        "sendEvent",
        "display",
        "extension",
        "eventType",
        "time",
        "device",
        "source",
        "detail",
        "flags",
        "valuators",
        "rawValues",
    )
    class RawEvent(
        pointer: Pointer,
    ) : Structure(pointer) {
        @JvmField
        var type: Int = 0

        @JvmField
        var serial: NativeLong = NativeLong()

        @JvmField
        var sendEvent: Int = 0

        @JvmField
        var display: Pointer? = null

        @JvmField
        var extension: Int = 0

        @JvmField
        var eventType: Int = 0

        @JvmField
        var time: NativeLong = NativeLong()

        @JvmField
        var device: Int = 0

        @JvmField
        var source: Int = 0

        @JvmField
        var detail: Int = 0

        @JvmField
        var flags: Int = 0

        @JvmField
        var valuators: ValuatorState = ValuatorState()

        @JvmField
        var rawValues: Pointer? = null

        init {
            read()
        }
    }

    @Structure.FieldOrder(
        "type",
        "serial",
        "sendEvent",
        "display",
        "extension",
        "eventType",
        "time",
        "device",
        "source",
        "reason",
        "classCount",
        "classes",
    )
    class DeviceChangedEvent(
        pointer: Pointer,
    ) : Structure(pointer) {
        @JvmField
        var type: Int = 0

        @JvmField
        var serial: NativeLong = NativeLong()

        @JvmField
        var sendEvent: Int = 0

        @JvmField
        var display: Pointer? = null

        @JvmField
        var extension: Int = 0

        @JvmField
        var eventType: Int = 0

        @JvmField
        var time: NativeLong = NativeLong()

        @JvmField
        var device: Int = 0

        @JvmField
        var source: Int = 0

        @JvmField
        var reason: Int = 0

        @JvmField
        var classCount: Int = 0

        @JvmField
        var classes: Pointer? = null

        init {
            read()
        }
    }

    @Structure.FieldOrder(
        "type",
        "serial",
        "sendEvent",
        "display",
        "extension",
        "eventType",
        "time",
        "device",
        "source",
        "detail",
        "root",
        "event",
        "child",
        "rootX",
        "rootY",
        "eventX",
        "eventY",
        "flags",
        "buttons",
        "valuators",
        "modifiers",
        "group",
    )
    class DeviceEvent(
        pointer: Pointer,
    ) : Structure(pointer) {
        @JvmField
        var type: Int = 0

        @JvmField
        var serial: NativeLong = NativeLong()

        @JvmField
        var sendEvent: Int = 0

        @JvmField
        var display: Pointer? = null

        @JvmField
        var extension: Int = 0

        @JvmField
        var eventType: Int = 0

        @JvmField
        var time: NativeLong = NativeLong()

        @JvmField
        var device: Int = 0

        @JvmField
        var source: Int = 0

        @JvmField
        var detail: Int = 0

        @JvmField
        var root: NativeLong = NativeLong()

        @JvmField
        var event: NativeLong = NativeLong()

        @JvmField
        var child: NativeLong = NativeLong()

        @JvmField
        var rootX: Double = 0.0

        @JvmField
        var rootY: Double = 0.0

        @JvmField
        var eventX: Double = 0.0

        @JvmField
        var eventY: Double = 0.0

        @JvmField
        var flags: Int = 0

        @JvmField
        var buttons: ButtonState = ButtonState()

        @JvmField
        var valuators: ValuatorState = ValuatorState()

        @JvmField
        var modifiers: ModifierState = ModifierState()

        @JvmField
        var group: ModifierState = ModifierState()

        init {
            read()
        }
    }

    @Structure.FieldOrder(
        "type",
        "serial",
        "sendEvent",
        "display",
        "extension",
        "eventType",
        "time",
        "device",
        "source",
        "detail",
        "root",
        "event",
        "child",
        "rootX",
        "rootY",
        "eventX",
        "eventY",
        "mode",
        "focus",
        "sameScreen",
        "buttons",
        "modifiers",
        "group",
    )
    class CrossingEvent(
        pointer: Pointer,
    ) : Structure(pointer) {
        @JvmField
        var type: Int = 0

        @JvmField
        var serial: NativeLong = NativeLong()

        @JvmField
        var sendEvent: Int = 0

        @JvmField
        var display: Pointer? = null

        @JvmField
        var extension: Int = 0

        @JvmField
        var eventType: Int = 0

        @JvmField
        var time: NativeLong = NativeLong()

        @JvmField
        var device: Int = 0

        @JvmField
        var source: Int = 0

        @JvmField
        var detail: Int = 0

        @JvmField
        var root: NativeLong = NativeLong()

        @JvmField
        var event: NativeLong = NativeLong()

        @JvmField
        var child: NativeLong = NativeLong()

        @JvmField
        var rootX: Double = 0.0

        @JvmField
        var rootY: Double = 0.0

        @JvmField
        var eventX: Double = 0.0

        @JvmField
        var eventY: Double = 0.0

        @JvmField
        var mode: Int = 0

        @JvmField
        var focus: Int = 0

        @JvmField
        var sameScreen: Int = 0

        @JvmField
        var buttons: ButtonState = ButtonState()

        @JvmField
        var modifiers: ModifierState = ModifierState()

        @JvmField
        var group: ModifierState = ModifierState()

        init {
            read()
        }
    }

    companion object {
        val instance: LinuxXInput2 = Native.load("Xi", LinuxXInput2::class.java)
        const val ALL_DEVICES = 0
        const val ALL_MASTER_DEVICES = 1
        const val DEVICE_CHANGED = 1
        const val MOTION = 6
        const val ENTER = 7
        const val LEAVE = 8
        const val HIERARCHY_CHANGED = 11

        // XIDeviceChangedEvent.reason
        const val SLAVE_SWITCH = 1
        const val RAW_BUTTON_RELEASE = 16
        const val TOUCH_BEGIN = 18
        const val TOUCH_UPDATE = 19
        const val TOUCH_END = 20
        const val RAW_TOUCH_END = 24
        const val ACCEPT_TOUCH = 6
        const val REJECT_TOUCH = 7
        const val POINTER_EMULATED = 1 shl 16
        const val TOUCH_PENDING_END = 1 shl 16
        const val TOUCH_EMULATING_POINTER = 1 shl 17
    }
}

internal interface LinuxTouchPoll : Library {
    fun poll(
        fds: Pointer,
        count: NativeLong,
        timeout: Int,
    ): Int

    companion object {
        val instance: LinuxTouchPoll = Native.load("c", LinuxTouchPoll::class.java)
    }
}
