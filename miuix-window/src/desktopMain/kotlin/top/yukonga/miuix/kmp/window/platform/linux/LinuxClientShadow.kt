// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowDecoration
import com.sun.jna.Library
import com.sun.jna.Memory
import com.sun.jna.Native
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.platform.unix.X11
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import top.yukonga.miuix.kmp.window.platform.linux.x11.LinuxX11
import top.yukonga.miuix.kmp.window.platform.linux.x11.LinuxXShape
import java.awt.Color
import java.awt.GraphicsDevice
import java.awt.GraphicsEnvironment
import java.awt.Window
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.milliseconds

private const val MAX_WAIT_FOR_SHOWING_MS = 10_000L
private const val SHOWING_POLL_INTERVAL_MS = 50L

/**
 * Suspends until window is showing or a timeout is reached.
 *
 * X11 window properties such as `_GTK_FRAME_EXTENTS` must only be written after the window is
 * mapped, because AWT rewrites them when the window is mapped and would override earlier values.
 *
 * @return `true` if the window is showing, `false` on timeout.
 */
internal suspend fun Window.awaitShowing(): Boolean {
    var waited = 0L
    while (!isShowing && waited < MAX_WAIT_FOR_SHOWING_MS) {
        delay(SHOWING_POLL_INTERVAL_MS.milliseconds)
        waited += SHOWING_POLL_INTERVAL_MS
    }
    return isShowing
}

/**
 * Client-drawn window shadow for Linux.
 *
 * Salt windows are always created undecorated at the AWT level, so the window manager provides
 * no shadow or frame. When [WindowDecoration.SystemDefault] is requested, the window is created
 * transparent, the content is inset by [margin] and a drop shadow is drawn into the transparent
 * area by the frame composable. This object handles the X11 side:
 *
 * - `_GTK_FRAME_EXTENTS` tells the window manager which part of the window is the actual
 *   content, so snapping and positioning ignore the shadow area (same mechanism GTK CSD
 *   applications use).
 * - An XFixes input shape restricts pointer input to the content rect, so the transparent
 *   shadow area is click-through instead of swallowing clicks meant for windows behind.
 *
 * When the window is maximized or fullscreen the margin is removed, matching the platform
 * convention of dropping shadows for maximized windows.
 */

internal object LinuxClientShadow {
    /** The size of the transparent shadow area around the window content. */
    val margin = 16.dp

    /** The corner radius of the window content while the shadow is shown. */
    val cornerRadius = 12.dp

    /** The vertical offset of the client-drawn drop shadow. */
    val shadowOffsetY = 2.dp

    /** The peak alpha of the client-drawn drop shadow at the content edge. */
    val shadowAlpha = 0.22f

    /**
     * Whether per-pixel translucent windows are available, which the client-drawn shadow
     * requires for the transparent shadow area.
     */
    val isTranslucencySupported: Boolean by lazy {
        try {
            val translucency = GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                .defaultScreenDevice
                .isWindowTranslucencySupported(translucency)
        } catch (_: Exception) {
            false
        }
    }

    /**
     * Returns whether a window with [decoration] should use the client-drawn shadow decoration:
     * [WindowDecoration.SystemDefault] requests the platform window look, which on Linux is
     * provided entirely by the client-drawn shadow, and the shadow requires per-pixel
     * translucency for its transparent area. [WindowDecoration.Undecorated] windows stay
     * fully undecorated.
     */
    @OptIn(ExperimentalComposeUiApi::class)
    fun shouldUseClientShadow(decoration: WindowDecoration): Boolean = decoration == WindowDecoration.SystemDefault &&
        LinuxWindowBackend.current != LinuxWindowBackend.FallbackSystem &&
        isTranslucencySupported

    /**
     * Sets or removes the `_GTK_FRAME_EXTENTS` property (left, right, top, bottom) so the window
     * manager treats the window content (window bounds minus [marginPx] on each side) as the
     * window geometry. A [marginPx] of 0 or less removes the property.
     *
     * Must be called after the window is showing, see [awaitShowing].
     */
    fun applyGtkFrameExtents(window: Window, marginPx: Int): Boolean {
        if (!window.isDisplayable) return false

        val x11 = try {
            LinuxX11.INSTANCE
        } catch (_: UnsatisfiedLinkError) {
            return false
        }
        val display = x11.XOpenDisplay(null) ?: return false

        try {
            val windowId = Native.getComponentID(window)
            if (windowId == 0L) return false
            val xWindow = X11.Window(windowId)

            val gtkFrameExtents = x11.XInternAtom(display, "_GTK_FRAME_EXTENTS", false)
            if (gtkFrameExtents == null || gtkFrameExtents.toLong() == 0L) return false

            if (marginPx <= 0) {
                x11.XDeleteProperty(display, xWindow, gtkFrameExtents)
            } else {
                val cardinal = x11.XInternAtom(display, "CARDINAL", false)

                // XChangeProperty expects a long array for 32-bit format data
                val data = Memory(4L * NativeLong.SIZE)
                val margin = marginPx.toLong()
                // Order: left, right, top, bottom
                longArrayOf(margin, margin, margin, margin).forEachIndexed { index, value ->
                    data.setNativeLong(index.toLong() * NativeLong.SIZE, NativeLong(value))
                }

                x11.XChangeProperty(
                    display,
                    xWindow,
                    gtkFrameExtents,
                    cardinal,
                    32,
                    X11.PropModeReplace,
                    data,
                    4,
                )
            }
            x11.XFlush(display)

            return true
        } catch (_: Exception) {
            return false
        } finally {
            x11.XCloseDisplay(display)
        }
    }

    /**
     * Restricts the window input region to the content rect (window bounds minus [marginPx] on
     * each side) via the XFixes shape extension, making the transparent shadow area
     * click-through. A [marginPx] of 0 or fewer restores input for the whole window.
     */
    fun updateInputShape(window: Window, marginPx: Int): Boolean {
        if (!window.isDisplayable) return false

        val xext = try {
            LinuxXShape.INSTANCE
        } catch (_: UnsatisfiedLinkError) {
            return false
        }
        val x11 = try {
            LinuxX11.INSTANCE
        } catch (_: UnsatisfiedLinkError) {
            return false
        }
        val display = x11.XOpenDisplay(null) ?: return false

        try {
            val windowId = Native.getComponentID(window)
            if (windowId == 0L) return false

            val transform = window.graphicsConfiguration?.defaultTransform
            val scaleX = transform?.scaleX ?: 1.0
            val scaleY = transform?.scaleY ?: 1.0
            val widthPx = (window.width * scaleX).roundToInt()
            val heightPx = (window.height * scaleY).roundToInt()

            val margin = marginPx.coerceIn(0, minOf(widthPx, heightPx) / 2)
            val contentX = margin
            val contentY = margin
            val contentWidth = (widthPx - 2 * margin).coerceAtLeast(0)
            val contentHeight = (heightPx - 2 * margin).coerceAtLeast(0)

            // XRectangle: 4 x 16-bit values (x, y, width, height)
            val rectangle = Memory(8)
            rectangle.setShort(0, contentX.toShort())
            rectangle.setShort(2, contentY.toShort())
            rectangle.setShort(4, contentWidth.toShort())
            rectangle.setShort(6, contentHeight.toShort())

            xext.XShapeCombineRectangles(
                display,
                X11.Window(windowId),
                LinuxXShape.SHAPE_INPUT,
                0,
                0,
                rectangle,
                1,
                LinuxXShape.SHAPE_SET,
                LinuxXShape.UNSORTED,
            )
            x11.XFlush(display)

            return true
        } catch (_: Exception) {
            return false
        } finally {
            x11.XCloseDisplay(display)
        }
    }
}

private val shadowBaseColor = androidx.compose.ui.graphics.Color.Black

/**
 * Draws the client-drawn window shadow (see [LinuxClientShadow]): layered rounded rectangles
 * with a quadratic alpha falloff, extending into the transparent shadow margin.
 *
 * Must be applied before the background in the modifier chain.
 */

internal fun Modifier.linuxClientShadow(): Modifier = drawWithCache {
    val cornerRadiusPx = LinuxClientShadow.cornerRadius.toPx()
    val contentRadiusPx = cornerRadiusPx.coerceAtMost(minOf(size.width, size.height) / 2)
    val contentBounds = Rect(Offset.Zero, size)
    val cornerCutout = Path().apply {
        fillType = PathFillType.EvenOdd
        addRect(contentBounds)
        addRoundRect(RoundRect(contentBounds, CornerRadius(contentRadiusPx)))
    }
    val offsetYPx = LinuxClientShadow.shadowOffsetY.toPx()
    val maxGrowPx = (LinuxClientShadow.margin - 2.dp).toPx().coerceAtLeast(1f)
    val maxAlpha = LinuxClientShadow.shadowAlpha

    onDrawWithContent {
        clipRect {
            this@onDrawWithContent.drawContent()
            drawPath(cornerCutout, shadowBaseColor, blendMode = BlendMode.DstOut)
        }

        // Layered from outside in; per-layer alpha chosen so the cumulative alpha at normalized
        // distance t from the content edge is maxAlpha * (1 - t)^2
        val layers = 24
        for (i in layers downTo 1) {
            val t = i.toFloat() / layers
            val tInner = (i - 1).toFloat() / layers
            val alpha = maxAlpha * ((1 - tInner) * (1 - tInner) - (1 - t) * (1 - t))
            val grow = maxGrowPx * t
            drawRoundRect(
                color = shadowBaseColor,
                topLeft = Offset(-grow, -grow + offsetYPx),
                size = Size(size.width + 2 * grow, size.height + 2 * grow),
                cornerRadius = CornerRadius(cornerRadiusPx + grow),
                alpha = alpha,
                blendMode = BlendMode.DstOver,
            )
        }
    }
}

/**
 * Applies and maintains the X11 side of the client-drawn shadow (see [LinuxClientShadow]) for
 * [window] with the given shadow [margin].
 */

@Composable
internal fun LinuxClientShadowEffect(
    window: Window,
    margin: Dp,
) {
    val density = LocalDensity.current
    val marginPx = with(density) { margin.roundToPx() }.coerceAtLeast(0)

    LaunchedEffect(window, marginPx) {
        if (window.awaitShowing()) {
            // The transparent background for the shadow area can only be set once the window
            // is undecorated; setting it while the frame is still decorated throws
            // IllegalComponentStateException
            runCatching { window.background = Color(0, 0, 0, 0) }

            withContext(Dispatchers.IO) {
                // Re-apply a few times; window manager state transitions (e.g. restoring from
                // maximized) can race with the first write and silently drop it
                repeat(3) { attempt ->
                    LinuxClientShadow.applyGtkFrameExtents(window, marginPx)
                    LinuxClientShadow.updateInputShape(window, marginPx)
                    if (attempt < 2) delay(300.milliseconds)
                }
            }
        }
    }

    DisposableEffect(window, marginPx) {
        val listener = object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                // Re-apply both: the input shape depends on the window size, and resizes also
                // happen on maximize/restore transitions that may drop the frame extents
                LinuxClientShadow.applyGtkFrameExtents(window, marginPx)
                LinuxClientShadow.updateInputShape(window, marginPx)
            }
        }
        window.addComponentListener(listener)
        onDispose {
            window.removeComponentListener(listener)
            LinuxClientShadow.applyGtkFrameExtents(window, 0)
            LinuxClientShadow.updateInputShape(window, 0)
        }
    }
}
