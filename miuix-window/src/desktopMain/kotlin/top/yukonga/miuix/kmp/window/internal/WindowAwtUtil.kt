// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.internal

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.window.WindowExceptionHandler
import androidx.compose.ui.window.WindowExceptionHandlerFactory
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.WinDef
import org.jetbrains.skiko.SkiaLayer
import java.awt.AlphaComposite
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Component
import java.awt.Container
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Window
import javax.swing.JComponent
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JLayeredPane
import javax.swing.JWindow

val ComposeWindow.hwnd: WinDef.HWND
    get() = WinDef.HWND(Pointer(windowHandle))

val ComposeDialog.hwnd: WinDef.HWND
    get() = WinDef.HWND(Pointer(windowHandle))

val Window.hwnd: WinDef.HWND
    get() = when (this) {
        is ComposeWindow -> hwnd
        is ComposeDialog -> hwnd
        else -> throw IllegalArgumentException("Unsupported window type: ${this::class.simpleName}")
    }

val Window.isUndecorated: Boolean
    get() = when (this) {
        is ComposeWindow -> isUndecorated
        is ComposeDialog -> isUndecorated
        else -> throw IllegalArgumentException("Unsupported window type: ${this::class.simpleName}")
    }

val Window.nativeWindowHandle: Long
    get() = when (this) {
        is ComposeWindow -> windowHandle
        is ComposeDialog -> windowHandle
        else -> 0L
    }

fun ComposeWindow.findSkiaLayer(): SkiaLayer? = findComponent<SkiaLayer>()

fun ComposeDialog.findSkiaLayer(): SkiaLayer? = findComponent<SkiaLayer>()

fun Window.findSkiaLayer(): SkiaLayer? = when (this) {
    is ComposeWindow -> findSkiaLayer()
    is ComposeDialog -> findSkiaLayer()
    else -> throw IllegalArgumentException("Unsupported window type: ${this::class.simpleName}")
}

fun Window.hackContentPane() {
    val oldContentPane = contentPane ?: return

    val newContentPane: JComponent = HackedContentPane()
    newContentPane.name = "$name.contentPane"
    newContentPane.layout = object : BorderLayout() {
        override fun addLayoutComponent(comp: Component, constraints: Any?) {
            super.addLayoutComponent(comp, constraints ?: CENTER)
        }
    }

    newContentPane.background = Color(0, 0, 0, 0)
    newContentPane.isOpaque = false
    newContentPane.size = oldContentPane.size

    newContentPane.enableInputMethods(true)
    oldContentPane.components.forEach { component ->
        newContentPane.add(component)
    }

    contentPane = newContentPane
}

private class HackedContentPane : JLayeredPane() {
    override fun paint(g: Graphics) {
        if (background.alpha != 255) {
            val graphics = g.create()
            try {
                if (graphics is Graphics2D) {
                    graphics.color = background
                    graphics.composite = AlphaComposite.getInstance(AlphaComposite.SRC)
                    graphics.fillRect(0, 0, width, height)
                }
            } finally {
                graphics.dispose()
            }
        }
        super.paint(g)
    }
}

private var Window.contentPane: Container?
    get() = when (this) {
        is JFrame -> contentPane
        is JDialog -> contentPane
        is JWindow -> contentPane
        else -> null
    }
    set(value) = when (this) {
        is JFrame -> contentPane = value
        is JDialog -> contentPane = value
        is JWindow -> contentPane = value
        else -> throw IllegalStateException("Unsupported window type for contentPane")
    }

private fun <T : JComponent> findComponent(container: Container, klass: Class<T>): T? {
    for (component in container.components) {
        if (klass.isInstance(component)) {
            @Suppress("UNCHECKED_CAST")
            return component as T
        }
        if (component is Container) {
            val found = findComponent(component, klass)
            if (found != null) {
                return found
            }
        }
    }
    return null
}

private inline fun <reified T : JComponent> Container.findComponent(): T? = findComponent(this, T::class.java)

@OptIn(ExperimentalComposeUiApi::class)
internal fun isIgnorableComposeDisposalException(throwable: Throwable): Boolean {
    var current: Throwable? = throwable
    while (current != null) {
        if (current is IllegalArgumentException && current.message?.contains("RootNodeOwner is already disposed") == true) {
            return true
        }
        current = current.cause
    }
    return false
}

@OptIn(ExperimentalComposeUiApi::class)
internal fun createSafeWindowExceptionHandlerFactory(
    parentFactory: WindowExceptionHandlerFactory,
): WindowExceptionHandlerFactory = WindowExceptionHandlerFactory { window ->
    val defaultHandler = parentFactory.exceptionHandler(window)
    WindowExceptionHandler { throwable ->
        if (isIgnorableComposeDisposalException(throwable)) {
            return@WindowExceptionHandler
        }
        defaultHandler.onException(throwable)
    }
}
