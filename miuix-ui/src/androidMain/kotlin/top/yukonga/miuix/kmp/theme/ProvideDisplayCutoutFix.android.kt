// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import android.graphics.Insets
import android.os.Build
import android.view.DisplayCutout
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsAnimation
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalView
import java.util.WeakHashMap

private val cutoutFixRegistrations = WeakHashMap<View, CutoutFixRegistration>()

/**
 * Android implementation of [ProvideDisplayCutoutFix].
 *
 * Some OEM devices (landscape tablets, foldables) drop the display cutout from the insets
 * dispatched to the View tree, so it is read back from [WindowManager.getCurrentWindowMetrics] and
 * merged in. Two paths need the merge: [View.OnApplyWindowInsetsListener] for regular dispatches,
 * and [WindowInsetsAnimation.Callback.onProgress] for the per-frame insets of a system window
 * animation (e.g. the IME), which never reach the listener.
 */
@Composable
actual fun ProvideDisplayCutoutFix() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R || !LocalDisplayCutoutFixEnabled.current) return

    val view = LocalView.current
    DisposableEffect(view) {
        val targetView = (view.parent as? View) ?: view.rootView
        synchronized(cutoutFixRegistrations) {
            val registration = cutoutFixRegistrations[targetView] ?: CutoutFixRegistration(targetView).also {
                cutoutFixRegistrations[targetView] = it
                it.install()
            }
            registration.refCount++
        }

        onDispose {
            synchronized(cutoutFixRegistrations) {
                val registration = cutoutFixRegistrations[targetView] ?: return@synchronized
                if (--registration.refCount <= 0) {
                    cutoutFixRegistrations.remove(targetView)
                    registration.uninstall()
                }
            }
        }
    }
}

/**
 * Insets supplement shared by every composition attached to the same [View]. The [DisplayCutout] is
 * resolved on regular dispatches and cached, so animation frames need no window manager query.
 */
@RequiresApi(Build.VERSION_CODES.R)
private class CutoutFixRegistration(private val view: View) {
    var refCount: Int = 0

    private var resolved: Boolean = false
    private var cutout: DisplayCutout? = null
    private var cutoutInsets: Insets = Insets.of(0, 0, 0, 0)

    private val insetsListener = View.OnApplyWindowInsetsListener { v, insets ->
        resolve()
        v.onApplyWindowInsets(fix(insets))
    }

    private val animationCallback = object : WindowInsetsAnimation.Callback(DISPATCH_MODE_CONTINUE_ON_SUBTREE) {
        override fun onProgress(
            insets: WindowInsets,
            runningAnimations: MutableList<WindowInsetsAnimation>,
        ): WindowInsets {
            if (!resolved) resolve()
            return fix(insets)
        }
    }

    private val attachListener = object : View.OnAttachStateChangeListener {
        override fun onViewAttachedToWindow(v: View) {
            v.requestApplyInsets()
        }

        override fun onViewDetachedFromWindow(v: View) {}
    }

    fun install() {
        view.setOnApplyWindowInsetsListener(insetsListener)
        view.setWindowInsetsAnimationCallback(animationCallback)
        view.addOnAttachStateChangeListener(attachListener)
        if (view.isAttachedToWindow) {
            view.requestApplyInsets()
        }
    }

    fun uninstall() {
        view.setOnApplyWindowInsetsListener(null)
        view.setWindowInsetsAnimationCallback(null)
        view.removeOnAttachStateChangeListener(attachListener)
    }

    private fun resolve() {
        resolved = true
        val cutout = view.context.getSystemService(WindowManager::class.java)
            ?.currentWindowMetrics?.windowInsets?.displayCutout
        this.cutout = cutout
        cutoutInsets = if (cutout == null) {
            Insets.of(0, 0, 0, 0)
        } else {
            Insets.of(
                cutout.safeInsetLeft,
                cutout.safeInsetTop,
                cutout.safeInsetRight,
                cutout.safeInsetBottom,
            )
        }
    }

    private fun fix(insets: WindowInsets): WindowInsets {
        val systemCutout = insets.getInsets(WindowInsets.Type.displayCutout())
        val left = maxOf(systemCutout.left, cutoutInsets.left)
        val top = maxOf(systemCutout.top, cutoutInsets.top)
        val right = maxOf(systemCutout.right, cutoutInsets.right)
        val bottom = maxOf(systemCutout.bottom, cutoutInsets.bottom)
        val cutout = cutout ?: return insets
        if (left == systemCutout.left && top == systemCutout.top &&
            right == systemCutout.right && bottom == systemCutout.bottom
        ) {
            return insets
        }
        return WindowInsets.Builder(insets)
            .setDisplayCutout(cutout)
            .setInsets(WindowInsets.Type.displayCutout(), Insets.of(left, top, right, bottom))
            .build()
    }
}
