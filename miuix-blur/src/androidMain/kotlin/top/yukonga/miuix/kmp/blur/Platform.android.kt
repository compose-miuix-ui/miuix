// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

@SuppressLint("ObsoleteSdkInt")
@ChecksSdkIntAtLeast(Build.VERSION_CODES.S)
actual fun isRenderEffectSupported(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

@SuppressLint("ObsoleteSdkInt")
@ChecksSdkIntAtLeast(Build.VERSION_CODES.TIRAMISU)
actual fun isRuntimeShaderSupported(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
