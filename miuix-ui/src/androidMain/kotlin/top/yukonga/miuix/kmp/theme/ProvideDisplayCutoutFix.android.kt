// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import android.graphics.Insets
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import java.util.WeakHashMap

private val cutoutListenerRefCounts = WeakHashMap<View, Int>()
private val cutoutAttachListeners = WeakHashMap<View, View.OnAttachStateChangeListener>()

/**
 * Android implementation of [ProvideDisplayCutoutFix].
 *
 * On certain OEM Android devices (such as landscape tablet devices and foldable devices),
 * the system window insets dispatch mechanism may fail to report the display cutout to the View
 * tree even when the window extends into the cutout area. This composable monitors the WindowManager's
 * current window metrics and supplements the View tree's [WindowInsets] if the cutout was omitted
 * or incomplete in the system dispatch.
 */
@Composable
actual fun ProvideDisplayCutoutFix() {
    val enabled = LocalDisplayCutoutFixEnabled.current
    if (!enabled) return

    val view = LocalView.current
    DisposableEffect(view, enabled) {
        val targetView = (view.parent as? View) ?: view.rootView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            synchronized(cutoutListenerRefCounts) {
                val count = cutoutListenerRefCounts[targetView] ?: 0
                if (count == 0) {
                    val insetsListener = View.OnApplyWindowInsetsListener { v, insets ->
                        val sysCutout = insets.getInsets(WindowInsets.Type.displayCutout())
                        val wm = v.context.getSystemService(WindowManager::class.java)
                        val metricsCutout = wm?.currentWindowMetrics?.windowInsets?.displayCutout
                        val resolvedInsets = if (metricsCutout != null) {
                            val mLeft = metricsCutout.safeInsetLeft
                            val mTop = metricsCutout.safeInsetTop
                            val mRight = metricsCutout.safeInsetRight
                            val mBottom = metricsCutout.safeInsetBottom
                            if (mLeft > sysCutout.left || mTop > sysCutout.top || mRight > sysCutout.right || mBottom > sysCutout.bottom) {
                                WindowInsets.Builder(insets)
                                    .setDisplayCutout(metricsCutout)
                                    .setInsets(
                                        WindowInsets.Type.displayCutout(),
                                        Insets.of(
                                            maxOf(sysCutout.left, mLeft),
                                            maxOf(sysCutout.top, mTop),
                                            maxOf(sysCutout.right, mRight),
                                            maxOf(sysCutout.bottom, mBottom),
                                        ),
                                    )
                                    .build()
                            } else {
                                insets
                            }
                        } else {
                            insets
                        }
                        v.onApplyWindowInsets(resolvedInsets)
                    }

                    val attachListener = object : View.OnAttachStateChangeListener {
                        override fun onViewAttachedToWindow(v: View) {
                            v.requestApplyInsets()
                        }

                        override fun onViewDetachedFromWindow(v: View) {}
                    }

                    targetView.setOnApplyWindowInsetsListener(insetsListener)
                    targetView.addOnAttachStateChangeListener(attachListener)
                    cutoutAttachListeners[targetView] = attachListener

                    if (targetView.isAttachedToWindow) {
                        targetView.requestApplyInsets()
                    }
                }
                cutoutListenerRefCounts[targetView] = count + 1
            }
        }

        onDispose {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                synchronized(cutoutListenerRefCounts) {
                    val count = (cutoutListenerRefCounts[targetView] ?: 1) - 1
                    if (count <= 0) {
                        cutoutListenerRefCounts.remove(targetView)
                        cutoutAttachListeners.remove(targetView)?.let {
                            targetView.removeOnAttachStateChangeListener(it)
                        }
                        targetView.setOnApplyWindowInsetsListener(null)
                    } else {
                        cutoutListenerRefCounts[targetView] = count
                    }
                }
            }
        }
    }
}
