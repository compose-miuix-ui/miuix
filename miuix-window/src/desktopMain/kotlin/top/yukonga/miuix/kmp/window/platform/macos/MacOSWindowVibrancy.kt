// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:Suppress("ktlint:standard:function-naming", "FunctionName")

package top.yukonga.miuix.kmp.window.platform.macos

import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.awt.ComposeWindow
import com.sun.jna.Callback
import com.sun.jna.Memory
import com.sun.jna.Native
import com.sun.jna.NativeLibrary
import com.sun.jna.Pointer
import java.awt.Window

internal class MacOSWindowVibrancy internal constructor(
    private val windowHandle: () -> Long,
    private val backend: MacOSVibrancyBackend?,
) {
    constructor(window: Window) : this(
        windowHandle = {
            when (window) {
                is ComposeWindow -> window.windowHandle
                is ComposeDialog -> window.windowHandle
                else -> 0L
            }
        },
        backend = MacOSNativeVibrancy.instance,
    )

    private var installation: Installation? = null

    fun update(enabled: Boolean, isDarkTheme: Boolean): Boolean {
        val native = backend ?: return false
        if (!enabled) {
            remove(native)
            return installation != null
        }

        val current = installation
        if (current != null) {
            runCatching {
                native.updateAppearance(current.effectView, isDarkTheme)
            }
            return true
        }

        val handle = windowHandle()
        if (handle == 0L) return false

        installation = runCatching {
            native.install(Pointer(handle), isDarkTheme)
        }.getOrNull()
        return installation != null
    }

    fun dispose(): Boolean {
        backend?.let(::remove)
        return installation != null
    }

    private fun remove(native: MacOSVibrancyBackend) {
        val current = installation ?: return
        val removed = runCatching {
            native.remove(current)
        }.isSuccess
        if (removed) installation = null
    }

    internal data class Installation(
        val nativeWindow: Pointer,
        val effectView: Pointer,
        val constraints: List<Pointer>,
        val contentLayer: Pointer?,
        val originalContentLayerIsOpaque: Boolean,
        val originalBackgroundColor: Pointer?,
        val originalIsOpaque: Boolean,
    )
}

internal interface MacOSVibrancyBackend {
    fun install(nativeWindow: Pointer, isDarkTheme: Boolean): MacOSWindowVibrancy.Installation?
    fun updateAppearance(effectView: Pointer, isDarkTheme: Boolean)
    fun remove(installation: MacOSWindowVibrancy.Installation)
}

private class MacOSNativeVibrancy private constructor(
    private val objectiveC: NativeLibrary,
    private val dispatch: Dispatch,
    private val mainQueue: Pointer,
) : MacOSVibrancyBackend {
    private val getClass = objectiveC.getFunction("objc_getClass")
    private val registerSelector = objectiveC.getFunction("sel_registerName")
    private val sendMessage = objectiveC.getFunction("objc_msgSend")
    private val selectors = mutableMapOf<String, Pointer>()

    override fun install(
        nativeWindow: Pointer,
        isDarkTheme: Boolean,
    ): MacOSWindowVibrancy.Installation? = runOnMainThread {
        installOnMainThread(nativeWindow, isDarkTheme)
    }

    private fun installOnMainThread(
        nativeWindow: Pointer,
        isDarkTheme: Boolean,
    ): MacOSWindowVibrancy.Installation? {
        val originalContentView = messagePointer(nativeWindow, "contentView") ?: return null
        val contentContainer = messagePointer(originalContentView, "superview") ?: return null
        val contentLayer = messagePointer(originalContentView, "layer") ?: return null
        val originalContentLayerIsOpaque = messageBoolean(contentLayer, "isOpaque")
        val originalBackgroundColor = messagePointer(nativeWindow, "backgroundColor") ?: return null
        val originalIsOpaque = messageBoolean(nativeWindow, "isOpaque")
        val clearColor = classPointer("NSColor")
            ?.let { messagePointer(it, "clearColor") }
            ?: return null
        val effectClass = classPointer("NSVisualEffectView") ?: return null
        val allocatedEffect = messagePointer(effectClass, "alloc") ?: return null
        val effectView = messagePointer(allocatedEffect, "init") ?: return null

        var originalBackgroundColorRetained = false
        var nativeWindowRetained = false
        var contentLayerRetained = false
        val retainedConstraints = mutableListOf<Pointer>()

        try {
            messageVoid(originalBackgroundColor, "retain")
            originalBackgroundColorRetained = true
            messageVoid(nativeWindow, "retain")
            nativeWindowRetained = true
            messageVoid(contentLayer, "retain")
            contentLayerRetained = true

            updateAppearanceOnMainThread(effectView, isDarkTheme)
            messageVoid(effectView, "setMaterial:", MATERIAL_UNDER_WINDOW_BACKGROUND)
            messageVoid(effectView, "setBlendingMode:", BLENDING_BEHIND_WINDOW)
            messageVoid(effectView, "setState:", STATE_FOLLOWS_WINDOW_ACTIVE_STATE)
            messageVoid(effectView, "setTranslatesAutoresizingMaskIntoConstraints:", 0.toByte())

            val constraints = listOf(
                constraint(effectView, "leadingAnchor", originalContentView, "leadingAnchor"),
                constraint(effectView, "trailingAnchor", originalContentView, "trailingAnchor"),
                constraint(effectView, "topAnchor", originalContentView, "topAnchor"),
                constraint(effectView, "bottomAnchor", originalContentView, "bottomAnchor"),
            )
            constraints.forEach { constraint ->
                messageVoid(constraint, "retain")
                retainedConstraints += constraint
            }

            messageVoid(
                contentContainer,
                "addSubview:positioned:relativeTo:",
                effectView,
                WINDOW_BELOW,
                originalContentView,
            )
            retainedConstraints.forEach { messageVoid(it, "setActive:", 1.toByte()) }

            messageVoid(nativeWindow, "setOpaque:", 0.toByte())
            messageVoid(contentLayer, "setOpaque:", 0.toByte())
            messageVoid(nativeWindow, "setBackgroundColor:", clearColor)

            return MacOSWindowVibrancy.Installation(
                nativeWindow = nativeWindow,
                effectView = effectView,
                constraints = retainedConstraints.toList(),
                contentLayer = contentLayer,
                originalContentLayerIsOpaque = originalContentLayerIsOpaque,
                originalBackgroundColor = originalBackgroundColor,
                originalIsOpaque = originalIsOpaque,
            )
        } catch (_: Throwable) {
            cleanupOnMainThread(
                nativeWindow = nativeWindow,
                effectView = effectView,
                constraints = retainedConstraints,
                contentLayer = contentLayer,
                originalContentLayerIsOpaque = originalContentLayerIsOpaque,
                originalBackgroundColor = originalBackgroundColor,
                originalIsOpaque = originalIsOpaque,
                releaseOriginalBackgroundColor = originalBackgroundColorRetained,
                releaseContentLayer = contentLayerRetained,
                releaseNativeWindow = nativeWindowRetained,
            )
            return null
        }
    }

    override fun updateAppearance(effectView: Pointer, isDarkTheme: Boolean) {
        runOnMainThread {
            updateAppearanceOnMainThread(effectView, isDarkTheme)
        }
    }

    private fun updateAppearanceOnMainThread(effectView: Pointer, isDarkTheme: Boolean) {
        val appearanceClass = classPointer("NSAppearance") ?: return
        val appearanceName = nsString(
            if (isDarkTheme) "NSAppearanceNameVibrantDark" else "NSAppearanceNameVibrantLight",
        ) ?: return
        val appearance = messagePointer(appearanceClass, "appearanceNamed:", appearanceName) ?: return
        messageVoid(effectView, "setAppearance:", appearance)
    }

    override fun remove(installation: MacOSWindowVibrancy.Installation) {
        runOnMainThread {
            cleanupOnMainThread(
                nativeWindow = installation.nativeWindow,
                effectView = installation.effectView,
                constraints = installation.constraints,
                contentLayer = installation.contentLayer,
                originalContentLayerIsOpaque = installation.originalContentLayerIsOpaque,
                originalBackgroundColor = installation.originalBackgroundColor,
                originalIsOpaque = installation.originalIsOpaque,
            )
        }
    }

    private fun cleanupOnMainThread(
        nativeWindow: Pointer,
        effectView: Pointer,
        constraints: List<Pointer>,
        contentLayer: Pointer?,
        originalContentLayerIsOpaque: Boolean,
        originalBackgroundColor: Pointer?,
        originalIsOpaque: Boolean,
        releaseOriginalBackgroundColor: Boolean = true,
        releaseContentLayer: Boolean = true,
        releaseNativeWindow: Boolean = true,
    ) {
        constraints.forEach { constraint ->
            runCatching { messageVoid(constraint, "setActive:", 0.toByte()) }
        }
        runCatching { messageVoid(effectView, "removeFromSuperview") }
        originalBackgroundColor?.let { backgroundColor ->
            runCatching { messageVoid(nativeWindow, "setBackgroundColor:", backgroundColor) }
        }
        contentLayer?.let { layer ->
            runCatching {
                messageVoid(
                    layer,
                    "setOpaque:",
                    if (originalContentLayerIsOpaque) 1.toByte() else 0.toByte(),
                )
            }
        }
        runCatching {
            messageVoid(
                nativeWindow,
                "setOpaque:",
                if (originalIsOpaque) 1.toByte() else 0.toByte(),
            )
        }
        constraints.forEach { constraint ->
            runCatching { messageVoid(constraint, "release") }
        }
        runCatching { messageVoid(effectView, "release") }
        if (releaseOriginalBackgroundColor) {
            originalBackgroundColor?.let { backgroundColor ->
                runCatching { messageVoid(backgroundColor, "release") }
            }
        }
        if (releaseContentLayer) {
            contentLayer?.let { layer ->
                runCatching { messageVoid(layer, "release") }
            }
        }
        if (releaseNativeWindow) {
            runCatching { messageVoid(nativeWindow, "release") }
        }
    }

    private fun <T> runOnMainThread(block: () -> T): T {
        if (isMainThread()) return block()

        var value: Any? = null
        var failure: Throwable? = null
        val callback = object : DispatchCallback {
            override fun invoke(context: Pointer?) {
                try {
                    value = block()
                } catch (throwable: Throwable) {
                    failure = throwable
                }
            }
        }
        dispatch.dispatch_sync_f(mainQueue, null, callback)
        failure?.let { throw it }

        @Suppress("UNCHECKED_CAST")
        return value as T
    }

    private fun isMainThread(): Boolean {
        val threadClass = classPointer("NSThread") ?: return false
        return messageBoolean(threadClass, "isMainThread")
    }

    private fun nsString(value: String): Pointer? {
        val stringClass = classPointer("NSString") ?: return null
        val bytes = value.toByteArray(Charsets.UTF_8)
        val memory = Memory((bytes.size + 1).toLong())
        memory.write(0, bytes, 0, bytes.size)
        memory.setByte(bytes.size.toLong(), 0)
        return messagePointer(stringClass, "stringWithUTF8String:", memory)
    }

    private fun constraint(
        firstView: Pointer,
        firstAnchorName: String,
        secondView: Pointer,
        secondAnchorName: String,
    ): Pointer {
        val firstAnchor = messagePointer(firstView, firstAnchorName)
            ?: error("Unable to read $firstAnchorName")
        val secondAnchor = messagePointer(secondView, secondAnchorName)
            ?: error("Unable to read $secondAnchorName")
        return messagePointer(firstAnchor, "constraintEqualToAnchor:", secondAnchor)
            ?: error("Unable to create vibrancy layout constraint")
    }

    private fun classPointer(name: String): Pointer? = getClass.invokePointer(arrayOf(name))?.takeUnless(::isNull)

    private fun selector(name: String): Pointer = synchronized(selectors) {
        selectors.getOrPut(name) {
            registerSelector.invokePointer(arrayOf(name))
                ?: error("Unable to register Objective-C selector $name")
        }
    }

    private fun messagePointer(receiver: Pointer, name: String, vararg arguments: Any?): Pointer? = sendMessage.invokePointer(arrayOf(receiver, selector(name), *arguments))
        ?.takeUnless(::isNull)

    private fun messageBoolean(receiver: Pointer, name: String, vararg arguments: Any?): Boolean {
        val value = sendMessage.invoke(
            Byte::class.javaPrimitiveType,
            arrayOf(receiver, selector(name), *arguments),
        ) as Byte
        return value.toInt() != 0
    }

    private fun messageVoid(receiver: Pointer, name: String, vararg arguments: Any?) {
        sendMessage.invokeVoid(arrayOf(receiver, selector(name), *arguments))
    }

    private fun isNull(pointer: Pointer): Boolean = Pointer.nativeValue(pointer) == 0L

    companion object {
        val instance: MacOSNativeVibrancy? by lazy {
            runCatching {
                val objectiveC = NativeLibrary.getInstance("objc")
                val system = NativeLibrary.getInstance("System")
                val mainQueue = runCatching {
                    system.getFunction("dispatch_get_main_queue").invokePointer(emptyArray())
                }.getOrNull() ?: system.getGlobalVariableAddress("_dispatch_main_q")
                require(Pointer.nativeValue(mainQueue) != 0L)

                MacOSNativeVibrancy(
                    objectiveC = objectiveC,
                    dispatch = Native.load("System", Dispatch::class.java),
                    mainQueue = mainQueue,
                )
            }.getOrNull()
        }

        private const val MATERIAL_UNDER_WINDOW_BACKGROUND = 21L
        private const val BLENDING_BEHIND_WINDOW = 0L
        private const val STATE_FOLLOWS_WINDOW_ACTIVE_STATE = 0L
        private const val WINDOW_BELOW = -1L
    }
}

private interface Dispatch : com.sun.jna.Library {
    fun dispatch_sync_f(queue: Pointer, context: Pointer?, callback: DispatchCallback)
}

private interface DispatchCallback : Callback {
    fun invoke(context: Pointer?)
}
