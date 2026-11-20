// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.uitest

import App
import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowInsetsControllerCompat
import org.lsposed.hiddenapibypass.HiddenApiBypass

class MainActivity : ComponentActivity() {
    private var colorModeState = 0
    private var predictiveBackEnabled = true
    private var lastEdgeToEdgeKey = Int.MIN_VALUE
    override fun onCreate(savedInstanceState: Bundle?) {
        colorModeState = savedInstanceState?.getInt(KEY_COLOR_MODE, 0) ?: 0
        predictiveBackEnabled = savedInstanceState?.getBoolean(KEY_PREDICTIVE_BACK, true) ?: true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            HiddenApiBypass.addHiddenApiExemptions(
                "Landroid/content/pm/ApplicationInfo;->setEnableOnBackInvokedCallback",
            )
            setEnableOnBackInvokedCallback(applicationInfo, predictiveBackEnabled)
        }
        super.onCreate(savedInstanceState)
        updateEdgeToEdge(colorModeState)

        setContent {
            var colorMode by remember { mutableIntStateOf(colorModeState) }
            val onColorModeChange = remember { { next: Int -> colorMode = next } }
            val onPredictiveBackChange: ((Boolean) -> Unit)? = remember {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    { enabled ->
                        predictiveBackEnabled = enabled
                        setEnableOnBackInvokedCallback(applicationInfo, enabled)
                        recreateWithoutTransition()
                    }
                } else {
                    null
                }
            }
            LaunchedEffect(colorMode) {
                if (colorModeState != colorMode) {
                    colorModeState = colorMode
                    updateEdgeToEdge(colorMode)
                }
            }

            App(
                onColorModeChange = onColorModeChange,
                initialColorMode = colorModeState,
                initialPredictiveBack = predictiveBackEnabled,
                onPredictiveBackChange = onPredictiveBackChange,
            )
        }
    }

    override fun onResume() {
        super.onResume()
        updateEdgeToEdge(colorModeState)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) updateEdgeToEdge(colorModeState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        updateEdgeToEdge(colorModeState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COLOR_MODE, colorModeState)
        outState.putBoolean(KEY_PREDICTIVE_BACK, predictiveBackEnabled)
    }

    private fun updateEdgeToEdge(colorMode: Int) {
        val systemDark = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        val isDark = when (colorMode) {
            2, 5 -> true
            1, 4 -> false
            else -> systemDark
        }
        val key = (colorMode shl 1) or if (systemDark) 1 else 0
        if (lastEdgeToEdgeKey != key) {
            lastEdgeToEdgeKey = key
            enableEdgeToEdge(
                statusBarStyle = systemBarStyle(colorMode),
                navigationBarStyle = systemBarStyle(colorMode),
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false // Xiaomi moment, this code must be here
            }
        }
        enforceSystemBarsAppearance(isDark)
        window.decorView.post {
            enforceSystemBarsAppearance(isDark)
        }
    }

    private fun systemBarStyle(colorMode: Int): SystemBarStyle = when (colorMode) {
        1, 4 -> SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)

        2, 5 -> SystemBarStyle.dark(Color.TRANSPARENT)

        else -> SystemBarStyle.auto(
            lightScrim = Color.TRANSPARENT,
            darkScrim = Color.TRANSPARENT,
            detectDarkMode = { resources ->
                (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
            },
        )
    }

    private fun enforceSystemBarsAppearance(isDark: Boolean) {
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = !isDark
            isAppearanceLightNavigationBars = !isDark
        }
    }

    @Suppress("DEPRECATION")
    private fun recreateWithoutTransition() {
        overridePendingTransition(0, 0)
        recreate()
        overridePendingTransition(0, 0)
    }

    private companion object {
        const val KEY_COLOR_MODE = "color_mode"
        const val KEY_PREDICTIVE_BACK = "predictive_back"

        @SuppressLint("PrivateApi")
        fun setEnableOnBackInvokedCallback(appInfo: ApplicationInfo, enable: Boolean) {
            runCatching {
                val method = ApplicationInfo::class.java.getDeclaredMethod(
                    "setEnableOnBackInvokedCallback",
                    Boolean::class.javaPrimitiveType,
                )
                method.isAccessible = true
                method.invoke(appInfo, enable)
            }
        }
    }
}
