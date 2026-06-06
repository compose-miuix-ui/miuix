// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.basic

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Basic.Close: ImageVector
    get() {
        if (_close != null) return _close!!
        _close = ImageVector.Builder("Close", 24.dp, 24.dp, 24f, 24f).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2.2f,
                strokeLineCap = StrokeCap.Round,
            ) {
                moveTo(6f, 6f)
                lineTo(18f, 18f)
                moveTo(18f, 6f)
                lineTo(6f, 18f)
            }
        }.build()
        return _close!!
    }

private var _close: ImageVector? = null
