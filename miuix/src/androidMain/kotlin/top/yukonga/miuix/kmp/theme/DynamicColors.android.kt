// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.theme

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.materialkolor.hct.Hct

@Composable
actual fun platformDynamicColors(dark: Boolean): Colors {

    val context = LocalContext.current
    val roles = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        systemMd3Roles(context, dark) // Use Android 12+ system dynamic colors
    } else {
        return monetSystemColors(dark) // Fallback to Monet implementation for older Android versions
    }
    return mapMd3RolesToMiuixColorsCommon(roles, dark)
}

@SuppressLint("NewApi")
private fun systemColor(context: Context, resId: Int): Color =
    Color(context.resources.getColor(resId, context.theme))

private fun toneFrom(base: Color, tone: Double): Color {
    val hct = Hct.fromInt(base.toArgb())
    val adjusted = Hct.from(hct.hue, hct.chroma, tone)
    return Color(adjusted.toInt())
}

@SuppressLint("InlinedApi")
private fun systemMd3Roles(context: Context, dark: Boolean): MonetRoles {

    fun resAccent1(index: Int): Int = when (index) {
        0 -> android.R.color.system_accent1_0
        10 -> android.R.color.system_accent1_10
        50 -> android.R.color.system_accent1_50
        100 -> android.R.color.system_accent1_100
        200 -> android.R.color.system_accent1_200
        300 -> android.R.color.system_accent1_300
        400 -> android.R.color.system_accent1_400
        500 -> android.R.color.system_accent1_500
        600 -> android.R.color.system_accent1_600
        700 -> android.R.color.system_accent1_700
        800 -> android.R.color.system_accent1_800
        900 -> android.R.color.system_accent1_900
        1000 -> android.R.color.system_accent1_1000
        else -> android.R.color.system_accent1_500
    }

    fun resAccent2(index: Int): Int = when (index) {
        0 -> android.R.color.system_accent2_0
        10 -> android.R.color.system_accent2_10
        50 -> android.R.color.system_accent2_50
        100 -> android.R.color.system_accent2_100
        200 -> android.R.color.system_accent2_200
        300 -> android.R.color.system_accent2_300
        400 -> android.R.color.system_accent2_400
        500 -> android.R.color.system_accent2_500
        600 -> android.R.color.system_accent2_600
        700 -> android.R.color.system_accent2_700
        800 -> android.R.color.system_accent2_800
        900 -> android.R.color.system_accent2_900
        1000 -> android.R.color.system_accent2_1000
        else -> android.R.color.system_accent2_500
    }

    fun resAccent3(index: Int): Int = when (index) {
        0 -> android.R.color.system_accent3_0
        10 -> android.R.color.system_accent3_10
        50 -> android.R.color.system_accent3_50
        100 -> android.R.color.system_accent3_100
        200 -> android.R.color.system_accent3_200
        300 -> android.R.color.system_accent3_300
        400 -> android.R.color.system_accent3_400
        500 -> android.R.color.system_accent3_500
        600 -> android.R.color.system_accent3_600
        700 -> android.R.color.system_accent3_700
        800 -> android.R.color.system_accent3_800
        900 -> android.R.color.system_accent3_900
        1000 -> android.R.color.system_accent3_1000
        else -> android.R.color.system_accent3_500
    }

    fun resNeutral1(index: Int): Int = when (index) {
        0 -> android.R.color.system_neutral1_0
        10 -> android.R.color.system_neutral1_10
        50 -> android.R.color.system_neutral1_50
        100 -> android.R.color.system_neutral1_100
        200 -> android.R.color.system_neutral1_200
        300 -> android.R.color.system_neutral1_300
        400 -> android.R.color.system_neutral1_400
        500 -> android.R.color.system_neutral1_500
        600 -> android.R.color.system_neutral1_600
        700 -> android.R.color.system_neutral1_700
        800 -> android.R.color.system_neutral1_800
        900 -> android.R.color.system_neutral1_900
        1000 -> android.R.color.system_neutral1_1000
        else -> android.R.color.system_neutral1_500
    }

    fun resNeutral2(index: Int): Int = when (index) {
        0 -> android.R.color.system_neutral2_0
        10 -> android.R.color.system_neutral2_10
        50 -> android.R.color.system_neutral2_50
        100 -> android.R.color.system_neutral2_100
        200 -> android.R.color.system_neutral2_200
        300 -> android.R.color.system_neutral2_300
        400 -> android.R.color.system_neutral2_400
        500 -> android.R.color.system_neutral2_500
        600 -> android.R.color.system_neutral2_600
        700 -> android.R.color.system_neutral2_700
        800 -> android.R.color.system_neutral2_800
        900 -> android.R.color.system_neutral2_900
        1000 -> android.R.color.system_neutral2_1000
        else -> android.R.color.system_neutral2_500
    }

    val a1 = { idx: Int -> systemColor(context, resAccent1(idx)) }
    val a2 = { idx: Int -> systemColor(context, resAccent2(idx)) }
    val a3 = { idx: Int -> systemColor(context, resAccent3(idx)) }
    val n1 = { idx: Int -> systemColor(context, resNeutral1(idx)) }
    val n2 = { idx: Int -> systemColor(context, resNeutral2(idx)) }

    return if (!dark) {
        MonetRoles(
            primary = a1(600),
            onPrimary = a1(0),
            primaryFixed = a1(200),
            onPrimaryFixed = a1(0),
            error = Color(0xFFB3261E),
            onError = Color(0xFFFFFFFF),
            errorContainer = Color(0xFFF9DEDC),
            onErrorContainer = Color(0xFF410E0B),
            primaryContainer = a1(100),
            onPrimaryContainer = a1(900),
            secondary = a2(600),
            onSecondary = a2(0),
            secondaryContainer = a2(100),
            onSecondaryContainer = a2(900),
            tertiaryContainer = a3(100),
            onTertiaryContainer = a3(900),
            background = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(100), 98.0) else n1(100),
            onBackground = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(100), 10.0) else n1(10),
            surface = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(100), 98.0) else n1(100),
            onSurface = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(100), 10.0) else n1(10),
            surfaceVariant = if (Build.VERSION.SDK_INT >= 34) toneFrom(n2(200), 90.0) else n2(200),
            surfaceContainer = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(100), 94.0) else n1(100),
            surfaceContainerHigh = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(100), 92.0) else n1(100),
            surfaceContainerHighest = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(100), 90.0) else n1(100),
            outline = if (Build.VERSION.SDK_INT >= 34) toneFrom(n2(200), 50.0) else n2(500),
            outlineVariant = if (Build.VERSION.SDK_INT >= 34) toneFrom(n2(200), 80.0) else n2(200),
            onSurfaceVariant = if (Build.VERSION.SDK_INT >= 34) toneFrom(n2(200), 30.0) else n2(700),
        )
    } else {
        MonetRoles(
            primary = a1(200),
            onPrimary = a1(800),
            primaryFixed = a1(200),
            onPrimaryFixed = a1(800),
            error = Color(0xFFB3261E),
            onError = Color(0xFF690005),
            errorContainer = Color(0xFF8C1D18),
            onErrorContainer = Color(0xFFFFDAD4),
            primaryContainer = a1(700),
            onPrimaryContainer = a1(100),
            secondary = a2(200),
            onSecondary = a2(800),
            secondaryContainer = a2(700),
            onSecondaryContainer = a2(100),
            tertiaryContainer = a3(700),
            onTertiaryContainer = a3(100),
            background = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(10), 6.0) else n1(10),
            onBackground = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(10), 90.0) else n1(90),
            surface = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(10), 6.0) else n1(10),
            onSurface = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(10), 90.0) else n1(90),
            surfaceVariant = if (Build.VERSION.SDK_INT >= 34) toneFrom(n2(700), 30.0) else n2(700),
            surfaceContainer = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(10), 12.0) else n1(10),
            surfaceContainerHigh = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(10), 17.0) else n1(10),
            surfaceContainerHighest = if (Build.VERSION.SDK_INT >= 34) toneFrom(n1(10), 22.0) else n1(10),
            outline = if (Build.VERSION.SDK_INT >= 34) toneFrom(n2(700), 60.0) else n2(500),
            outlineVariant = if (Build.VERSION.SDK_INT >= 34) toneFrom(n2(700), 30.0) else n2(700),
            onSurfaceVariant = if (Build.VERSION.SDK_INT >= 34) toneFrom(n2(700), 80.0) else n2(200),
        )
    }
}
