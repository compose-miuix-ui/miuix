// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class ProgressIndicatorTest {
    @Test
    fun lambdaProgressUpdatesSemanticsAndDrawWithoutRecomposingScope() = runComposeUiTest {
        val progressState = mutableFloatStateOf(0.2f)
        var compositionCount = 0
        val colors = ProgressIndicatorColors(
            foregroundColor = Color.Red,
            disabledForegroundColor = Color.Gray,
            backgroundColor = Color.Blue,
        )

        setContent {
            compositionCount++
            Column {
                LinearProgressIndicator(
                    progress = { progressState.floatValue },
                    modifier = Modifier.width(100.dp).testTag("linear"),
                    colors = colors,
                )
                CircularProgressIndicator(
                    progress = { progressState.floatValue },
                    modifier = Modifier.testTag("circular"),
                    colors = colors,
                )
            }
        }

        waitForIdle()
        assertEquals(1, compositionCount)

        val linearNode = onNodeWithTag("linear")
        val circularNode = onNodeWithTag("circular")

        linearNode.assert(
            SemanticsMatcher.expectValue(
                SemanticsProperties.ProgressBarRangeInfo,
                ProgressBarRangeInfo(0.2f, 0f..1f),
            ),
        )
        val initialLinearPixel = linearNode.captureToImage().toPixelMap()
        val midX = initialLinearPixel.width / 2
        val midY = initialLinearPixel.height / 2
        assertTrue(initialLinearPixel[midX, midY].blue > 0.9f, "At 0.2 progress, midpoint should show background")

        progressState.floatValue = 0.8f
        waitForIdle()

        assertEquals(1, compositionCount, "Frame-rate progress updates via lambda must not recompose parent scope")
        linearNode.assert(
            SemanticsMatcher.expectValue(
                SemanticsProperties.ProgressBarRangeInfo,
                ProgressBarRangeInfo(0.8f, 0f..1f),
            ),
        )
        circularNode.assert(
            SemanticsMatcher.expectValue(
                SemanticsProperties.ProgressBarRangeInfo,
                ProgressBarRangeInfo(0.8f, 0f..1f),
            ),
        )
        val updatedLinearPixel = linearNode.captureToImage().toPixelMap()
        assertTrue(updatedLinearPixel[midX, midY].red > 0.9f, "At 0.8 progress, midpoint must redraw with foreground")
    }

    @Test
    fun indeterminateProgressAnimatesWithoutRecomposingScope() = runComposeUiTest {
        mainClock.autoAdvance = false
        var compositionCount = 0
        val colors = ProgressIndicatorColors(
            foregroundColor = Color.Red,
            disabledForegroundColor = Color.Gray,
            backgroundColor = Color.Blue,
        )

        setContent {
            SideEffect { compositionCount++ }
            Column {
                LinearProgressIndicator(
                    modifier = Modifier.width(100.dp).testTag("indeterminate_linear"),
                    colors = colors,
                )
                CircularProgressIndicator(
                    modifier = Modifier.testTag("indeterminate_circular"),
                    colors = colors,
                )
                InfiniteProgressIndicator(
                    modifier = Modifier.testTag("infinite"),
                )
            }
        }

        mainClock.advanceTimeBy(100)
        val initialSnapshot = onNodeWithTag("indeterminate_linear").captureToImage().toPixelMap()

        mainClock.advanceTimeBy(300)
        val laterSnapshot = onNodeWithTag("indeterminate_linear").captureToImage().toPixelMap()

        assertEquals(1, compositionCount, "Indeterminate indicators must animate in draw phase without recomposition")
        var differs = false
        val midY = initialSnapshot.height / 2
        for (x in 0 until initialSnapshot.width) {
            if (initialSnapshot[x, midY] != laterSnapshot[x, midY]) {
                differs = true
                break
            }
        }
        assertTrue(differs, "Indeterminate linear indicator must redraw across animation frames")
    }
}
