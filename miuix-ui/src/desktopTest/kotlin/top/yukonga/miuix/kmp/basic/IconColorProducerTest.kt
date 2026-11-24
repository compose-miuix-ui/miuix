// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalTestApi::class)
class IconColorProducerTest {
    @Test
    fun colorProducerUpdatesTintWithoutReRecordingPainter() = runComposeUiTest {
        val tint = mutableStateOf(Color.Red)
        var drawRecords = 0
        val painter = object : Painter() {
            override val intrinsicSize: Size = Size(24f, 24f)

            override fun DrawScope.onDraw() {
                drawRecords++
                drawRect(Color.White)
            }
        }
        setContent {
            Icon(
                painter = painter,
                tint = ColorProducer { tint.value },
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        }
        fun pixel() = onRoot().captureToImage().toPixelMap()[10, 10]

        assertEquals(Color.Red, pixel())
        assertEquals(1, drawRecords)

        runOnIdle { tint.value = Color.Green }
        assertEquals(Color.Green, pixel())
        assertEquals(1, drawRecords, "Changing ColorProducer state must not re-record the painter layer")

        runOnIdle { tint.value = Color.Unspecified }
        assertEquals(Color.White, pixel())
        assertEquals(1, drawRecords)

        runOnIdle { tint.value = Color.Blue }
        assertEquals(Color.Blue, pixel())
        assertEquals(1, drawRecords)
    }
}
