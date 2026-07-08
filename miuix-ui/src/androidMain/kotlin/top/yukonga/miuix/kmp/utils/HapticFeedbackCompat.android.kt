// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import android.os.Build

internal actual fun hapticFeedbackApiLevel(): Int? = Build.VERSION.SDK_INT
