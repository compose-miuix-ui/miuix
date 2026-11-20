// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.FlexAlignItems
import androidx.compose.foundation.layout.FlexBox
import androidx.compose.foundation.layout.FlexJustifyContent
import androidx.compose.foundation.layout.FlexWrap
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.CircularProgressIndicator
import top.yukonga.miuix.kmp.basic.InfiniteProgressIndicator
import top.yukonga.miuix.kmp.basic.LinearProgressIndicator
import top.yukonga.miuix.kmp.basic.SmallTitle

fun LazyListScope.progressIndicatorSection() {
    item(key = "progressIndicator") {
        SmallTitle(text = "ProgressIndicator")
        val progressValues = remember { listOf(0.0f, 0.25f, 0.5f, 0.75f, 1.0f, null) }
        val animatedProgress = rememberInfiniteTransition().animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse,
            ),
        )

        LinearProgressIndicator(
            progress = { animatedProgress.value },
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(bottom = 12.dp),
        )
        progressValues.forEach { progressValue ->
            LinearProgressIndicator(
                progress = progressValue,
                modifier = Modifier
                    .padding(horizontal = 15.dp) // Increased from 12.dp.
                    .padding(bottom = 12.dp),
            )
        }
        FlexBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            config = {
                wrap(FlexWrap.Wrap)
                justifyContent(FlexJustifyContent.SpaceEvenly)
                alignItems(FlexAlignItems.Center)
            },
        ) {
            CircularProgressIndicator(
                progress = { animatedProgress.value },
            )
            progressValues.forEach { progressValue ->
                CircularProgressIndicator(
                    progress = progressValue,
                )
            }
            InfiniteProgressIndicator()
        }
    }
}
