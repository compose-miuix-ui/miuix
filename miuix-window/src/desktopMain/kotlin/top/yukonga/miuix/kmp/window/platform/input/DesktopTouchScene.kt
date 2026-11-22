// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.input

import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.awt.ComposeDialog
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.scene.ComposeScene
import java.awt.Component
import java.awt.Container
import java.awt.Window
import java.lang.reflect.Field
import javax.swing.SwingUtilities

/**
 * ComposeWindow does not expose its ComposeScene. Keep the Compose internals here so
 * upgrading Compose only requires checking this adapter. Resolve it before consuming native input.
 */
@OptIn(InternalComposeUiApi::class)
internal class DesktopTouchScene private constructor(
    val scene: ComposeScene,
    private val mediator: Any,
    private val container: Container,
    private val disposedField: Field,
    private val boundsField: Field,
) {
    val isDisposed: Boolean
        get() = disposedField.getBoolean(mediator)

    fun positionInScene(canvas: Component, clientPosition: Offset): Offset {
        val origin = SwingUtilities.convertPoint(canvas, 0, 0, container)
        val transform = canvas.graphicsConfiguration.defaultTransform
        val bounds = boundsField.get(mediator) as Rect?
        return clientPosition + Offset(
            (origin.x * transform.scaleX).toFloat(),
            (origin.y * transform.scaleY).toFloat(),
        ) - (bounds?.topLeft ?: Offset.Zero)
    }

    companion object {
        fun create(window: Window): DesktopTouchScene? = try {
            val windowClass = when (window) {
                is ComposeWindow -> ComposeWindow::class.java
                is ComposeDialog -> ComposeDialog::class.java
                else -> error("Unsupported Compose window")
            }
            val panel = windowClass.field("composePanel").get(window)
            val composeContainer = panel.javaClass.field("_composeContainer").get(panel)
            val mediator = composeContainer.javaClass.field("mediator").get(composeContainer)
            val sceneDelegate = mediator.javaClass
                .field("scene\$delegate").get(mediator) as Lazy<*>
            DesktopTouchScene(
                scene = sceneDelegate.value as ComposeScene,
                mediator = mediator,
                container = mediator.javaClass.field("container").get(mediator) as Container,
                disposedField = mediator.javaClass.field("isDisposed"),
                boundsField = mediator.javaClass.field("sceneBoundsInPx"),
            )
        } catch (exception: Exception) {
            System.getLogger(DesktopTouchScene::class.java.name).log(
                System.Logger.Level.WARNING,
                "Cannot access ComposeScene; native touch is unavailable",
                exception,
            )
            null
        }

        private fun Class<*>.field(name: String): Field = getDeclaredField(name).apply { isAccessible = true }
    }
}
